package com.e16din.lightutils;

/**
 * Created by e16din on 10.08.15.
 */
public interface Callback<T> {
    void run(T... objects);
}
