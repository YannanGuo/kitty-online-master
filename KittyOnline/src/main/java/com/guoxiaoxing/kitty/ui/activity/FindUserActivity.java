package com.guoxiaoxing.kitty.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.guoxiaoxing.kitty.R;
import com.guoxiaoxing.kitty.adapter.FindUserAdapter;
import com.guoxiaoxing.kitty.api.remote.OSChinaApi;
import com.guoxiaoxing.kitty.model.FindUserList;
import com.guoxiaoxing.kitty.model.ListEntity;
import com.guoxiaoxing.kitty.model.User;
import com.guoxiaoxing.kitty.ui.base.BaseActivity;
import com.guoxiaoxing.kitty.ui.empty.EmptyLayout;
import com.guoxiaoxing.kitty.util.StringUtils;
import com.guoxiaoxing.kitty.util.UIHelper;
import com.guoxiaoxing.kitty.util.XmlUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.ByteArrayInputStream;
import java.util.List;

import butterknife.Bind;
import cz.msebera.android.httpclient.Header;

/**
 * Created by 火蚁 on 15/5/27.
 */
public class FindUserActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private SearchView mSearchView;

    @Bind(R.id.lv_list)
    ListView mListView;

    @Bind(R.id.error_layout)
    EmptyLayout mErrorLayout;

    private FindUserAdapter mAdapter;

    private AsyncHttpResponseHandler mHandle = new AsyncHttpResponseHandler() {

        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
            ListEntity<User> list = XmlUtils.toBean(FindUserList.class,
                    new ByteArrayInputStream(arg2));
            executeOnLoadDataSuccess(list.getList());
            mActionBar.setTitle("找人 ");
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                              Throwable arg3) {
            mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem search = menu.findItem(R.id.search_content);
        mSearchView = (SearchView) search.getActionView();
        mSearchView.setIconifiedByDefault(false);
        setSearch();
        return super.onCreateOptionsMenu(menu);
    }

    private void setSearch() {
        mSearchView.setQueryHint("输入用户昵称");
        TextView textView = (TextView) mSearchView
                .findViewById(R.id.search_src_text);
        textView.setTextColor(Color.WHITE);

        mSearchView
                .setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                    @Override
                    public boolean onQueryTextSubmit(String arg0) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String arg0) {
                        search(arg0);
                        return false;
                    }
                });
    }

    private void search(String nickName) {
        if (nickName == null || StringUtils.isEmpty(nickName)) {
            return;
        }
        mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
        mListView.setVisibility(View.GONE);
        OSChinaApi.findUser(nickName, mHandle);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find_user;
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    public void initView() {
        mAdapter = new FindUserAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);

        mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                search(mSearchView.getQuery().toString());
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        User user = mAdapter.getItem(position);
        if (user != null) {
            UIHelper.showUserCenter(FindUserActivity.this, user.getId(),
                    user.getUsername());
        }
    }

    private void executeOnLoadDataSuccess(List<User> data) {
        mAdapter.clear();
        mAdapter.addData(data);
        mListView.setVisibility(View.VISIBLE);
    }
}
