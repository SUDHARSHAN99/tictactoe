package com.design.patterns.singleton;

public class BasicSingleton {

    static BasicSingleton basicSingleton = null;
    private BasicSingleton(){

    }

  // This will create a multiple objects
    public static BasicSingleton getInstance(){
        if(basicSingleton == null){
            basicSingleton=new BasicSingleton();
        }
        return basicSingleton;
    }
}
