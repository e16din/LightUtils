package com.e16din.lightutils.model;

/**
 * Created by e16din on 10.08.15.
 */
public interface Callback3<T, E, F> {
    void run(T obj1, E obj2, F... objects);
}
