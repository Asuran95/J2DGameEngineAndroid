package com.asuran.j2dgameengine.utils;

public class ListenerWrapper<T> {

    private T obj;

    public void setListener(T obj){
        this.obj = obj;
    }

    public T getListener(){
        return obj;
    }
}
