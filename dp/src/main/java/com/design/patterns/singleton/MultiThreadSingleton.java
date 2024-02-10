package com.design.patterns.singleton;

import org.hibernate.annotations.Synchronize;

public class MultiThreadSingleton {
    static MultiThreadSingleton multiThreadSingleton = null;
    private MultiThreadSingleton(){

    }

    // This will create a multiple objects and this will create object even if not required
    public synchronized static MultiThreadSingleton getInstance(){
        if(multiThreadSingleton == null){
            multiThreadSingleton=new MultiThreadSingleton();
        }
        return multiThreadSingleton;
    }
}
