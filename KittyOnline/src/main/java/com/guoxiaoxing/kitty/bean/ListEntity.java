package com.guoxiaoxing.kitty.bean;

import java.io.Serializable;
import java.util.List;

public interface ListEntity<T> extends Serializable {

    public List<T> getList();
}
