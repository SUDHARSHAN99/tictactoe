package com.design.patterns.singleton;

public class EagerLoadSingleton {

    static EagerLoadSingleton eagerLoadSingleton = new EagerLoadSingleton();
    private EagerLoadSingleton(){

    }

    // This will create a multiple objects and this will create object even if not required
    public static EagerLoadSingleton getInstance(){
        if(eagerLoadSingleton == null){
            eagerLoadSingleton=new EagerLoadSingleton();
        }
        return eagerLoadSingleton;
    }
}
