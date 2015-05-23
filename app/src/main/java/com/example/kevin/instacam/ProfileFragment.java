package com.example.kevin.instacam;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.widget.LoginButton;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private ImageView mCoverImage;
    private ImageView mAvatar;
    private TextView mBirthday;
    private TextView mPostCount;
    private TextView mUsername;

    public ProfileFragment() {
        // Required empty public constructor
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
        mPostCount.setText("300");

        return v;
    }


}
