package com.example.kevin.instacam;

import java.util.ArrayList;

/**
 * Created by kevin on 5/23/15.
 */
public class Feed extends ArrayList<Photo> {
    private static Feed sInstance = null;

    private Feed(){}

    public static Feed getInstance(){
        if(sInstance == null){
            sInstance = new Feed();
        }
        return sInstance;
    }
}
