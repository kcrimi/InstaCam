package com.example.kevin.instacam;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private List<Photo> mPhotos;
    private ImageView mCoverImage;
    private ImageView mAvatar;
    private TextView mBirthday;
    private TextView mPostCount;
    private TextView mUsername;

    private UiLifecycleHelper mUiLifecycleHelper;

    public ProfileFragment() {
        mPhotos = Feed.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        setRetainInstance(true);

        LoginButton loginButton = (LoginButton)v.findViewById(R.id.profile_logout_button);

        User user = User.getCurrentUser();
        mCoverImage = (ImageView)v.findViewById(R.id.profile_cover_image);
        mUsername = (TextView)v.findViewById(R.id.profile_user_name);
        mAvatar = (ImageView)v.findViewById(R.id.profile_user_avatar);
        mBirthday = (TextView)v.findViewById(R.id.profile_birthday);
        mPostCount = (TextView)v.findViewById(R.id.profile_post_count);


        Picasso.with(getActivity()).load(user.getCoverUrl()).into(mCoverImage);
        mUsername.setText(user.getFirstName() + " " + user.getLastName());
        Picasso.with(getActivity()).load(user.getAvatarUrl()).into(mAvatar);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        mBirthday.setText(sdf.format(user.getBirthday()));
        mPostCount.setText(""+mPhotos.size());

        mUiLifecycleHelper = new UiLifecycleHelper(getActivity(), new Session.StatusCallback() {
            @Override
            public void call(Session session, SessionState sessionState, Exception e) {
                onSessionStateChanged(session, sessionState, e);
            Log.d("Profile","Session state changed");
            }
        });

        return v;
    }

    private void onSessionStateChanged(Session session, SessionState sessionState, Exception e) {
        if (sessionState.isClosed()) {
            Log.d("Profile","Session state changed");
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mUiLifecycleHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        mUiLifecycleHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mUiLifecycleHelper.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mUiLifecycleHelper.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUiLifecycleHelper.onDestroy();
    }

}
