package com.example.kevin.instacam;

import android.util.Log;

import com.facebook.model.GraphObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kevin on 5/22/15.
 */
public class User implements Serializable{

    private static final String TAG = "User";
    private static User sCurrentUser;

    private String mFirstName;
    private String mLastName;
    private Date mBirthday;
    private String mAvatarUrl;
    private String mCoverUrl;

    public User(GraphObject graphObject){
        Log.d("Check1", graphObject.toString() );
        Log.d("check2", graphObject.getProperty("cover").toString());
        mFirstName = (String)graphObject.getProperty("first_name");
        mLastName = (String)graphObject.getProperty("last_name");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        try {
            mBirthday = sdf.parse((String)graphObject.getProperty("birthday"));
        } catch (ParseException e) {
            Log.d(TAG,"Failed parsing "+graphObject.getProperty("birthday"));
        }
        mAvatarUrl = (String)graphObject.getPropertyAs("picture",GraphObject.class)
                .getPropertyAs("data", GraphObject.class)
                .getProperty("url");

        mCoverUrl = (String)graphObject.getPropertyAs("cover",GraphObject.class)
                .getProperty("source");
    }

    public static User getCurrentUser() {
        return sCurrentUser;
    }

    public static void setCurrentUser(GraphObject graphObject) {
        if (sCurrentUser == null) {
            sCurrentUser = new User(graphObject);
        }
    }

    public Date getBirthday() {
        return mBirthday;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public String getCoverUrl() {
        return mCoverUrl;
    }
}
