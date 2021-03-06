package com.guoxiaoxing.kitty.model;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户说说实体类
 */

@AVClassName("UserTalk")
public class UserTalk extends AVObject {

    public static final Creator CREATOR = AVObjectCreator.instance;

    public static final String USER_NAME = "userName";
    public static final String USER_LOGO = "userLogo";
    public static final String TALK_TIME = "talkTIme";
    public static final String TALK_CONTENT = "talkContent";
    public static final String LIKE_TOTAL = "likeTotal";
    public static final String IMAGE_URLS = "imageUrls";
    public static final String TALK_COMMENT= "talkComment";



    public String getUserName() {
        return getString(USER_NAME);
    }

    public void setUserName(String userName) {
        put(USER_NAME, userName);
    }

    public String getUserLogo() {
        return getString(USER_LOGO);
    }

    public void setUserLogo(String userLogo) {
        put(USER_LOGO, userLogo);
    }

    public String getTalkTime() {
        return getString(TALK_TIME);
    }

    public void setTalkTime(String talkTime) {
        put(TALK_TIME, talkTime);
    }

    public String getTalkContent() {
        return getString(TALK_CONTENT);
    }

    public void setTalkContent(String talkContent) {
        put(TALK_CONTENT, talkContent);
    }


    public int getLikeTotal() {
        return getInt(LIKE_TOTAL);
    }

    public void setLikeTotal(int likeTotal) {
        put(LIKE_TOTAL, likeTotal);
    }

    public List<String> getImageUrlList() {
        return getList(IMAGE_URLS);
    }

    public void setImageUrlList(ArrayList<String> imageUrlList) {
        put(IMAGE_URLS, imageUrlList);
    }

    public List<String> getCommentList(){

        List<String> list = new ArrayList<>();
        list.addAll(getList(TALK_COMMENT));
        list.addAll(getList(TALK_COMMENT));
        list.addAll(getList(TALK_COMMENT));
        list.addAll(getList(TALK_COMMENT));
        list.addAll(getList(TALK_COMMENT));
        return list;
    }

    public void setCommentList(ArrayList<String> commentList){
        put(TALK_COMMENT, commentList);
    }

}
