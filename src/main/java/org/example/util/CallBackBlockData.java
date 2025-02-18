package org.example.util;

//|word,translate
//1)extract data from file
public interface CallBackBlockData<T> {
    void send(T data);
}
