package com.example.kevin.instacam;

import android.os.Environment;

import java.io.File;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by kevin on 5/20/15.
 */
public class Photo implements Serializable{
    private static final File sDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
    private UUID mID;
    private String mCaption;
    private User mUser;

    Photo() {
        mID = UUID.randomUUID();
    }

    public UUID getID() {
        return mID;
    }

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public File getFile(){
        return new File(sDirectory, mID.toString());
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}
