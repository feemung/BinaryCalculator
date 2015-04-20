package com.feemung.binarycalculator.cache;

/**
 * Created by laocow on 2015/4/15.
 */
public class Cache {
    public static Cache instance;
    private String input="0";
    public static Cache getInstance(){
        if(instance==null){
            instance=new Cache();
        }
        return instance;
    }
    private Cache(){}

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
