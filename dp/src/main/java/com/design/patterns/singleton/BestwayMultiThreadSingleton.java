package com.design.patterns.singleton;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BestwayMultiThreadSingleton {

    static BestwayMultiThreadSingleton multiThreadSingleton = null;
    private static Lock lock;
    private BestwayMultiThreadSingleton(){
        lock =  new ReentrantLock();
    }

    // This will create a multiple objects and this will create object even if not required
    public  static BestwayMultiThreadSingleton getInstance(){

        if(multiThreadSingleton == null){
            lock.lock();
            if(multiThreadSingleton == null){
                multiThreadSingleton=new BestwayMultiThreadSingleton();
            }
            lock.unlock();

        }

        return multiThreadSingleton;
    }
}
