package com.spring_jersey.fabric8;

/**
 * Created by Administrator on 2017/12/19.
 */
public interface KubeUtils<T> {
    public T getClient();
    public void setClient(T t);
}
