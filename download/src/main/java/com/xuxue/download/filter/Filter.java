package com.xuxue.download.filter;

public interface Filter<T> {

    boolean contain(T t);

    void add(T t);
}
