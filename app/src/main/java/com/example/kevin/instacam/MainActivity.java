package com.example.kevin.instacam;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ShareActionProvider;
import android.widget.Toolbar;

import java.io.File;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class MainActivity extends ActionBarActivity implements MaterialTabListener{

    private static final int NEW_PHOTO_REQUEST = 10;
    private static final String TAG = "MainActivity";
    private FeedFragment mFeedFragment;
    private ProfileFragment mProfileFragment;
    private MaterialTabHost mTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton cameraFAB = (ImageButton)findViewById(R.id.camera_fab);
        cameraFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewPhotoActivity.class);
                startActivityForResult(i, NEW_PHOTO_REQUEST);
            }
        });

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTabBar = (MaterialTabHost)findViewById(R.id.tab_bar);
        mTabBar.addTab(mTabBar.newTab().setIcon(getResources().getDrawable(R.drawable.ic_home)).setTabListener(this));
        mTabBar.addTab(mTabBar.newTab().setIcon(getResources().getDrawable(R.drawable.ic_perm_identity)).setTabListener(this));

        mFeedFragment  = (FeedFragment)getFragmentManager().findFragmentById(R.id.feed_container);
        if(mFeedFragment == null){
            mFeedFragment = new FeedFragment();

            getFragmentManager().beginTransaction()
                    .add(R.id.feed_container, mFeedFragment)
                    .commit();
        }

    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        int position = materialTab.getPosition();
        mTabBar.setSelectedNavigationItem(position);

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = mFeedFragment;
                break;
            case 1:
                if (mProfileFragment == null) {
                    mProfileFragment = new ProfileFragment();
                }
                fragment = mProfileFragment;
                break;
        }


        getFragmentManager().beginTransaction()
                .replace(R.id.feed_container, fragment)
                .commit();
    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if  (requestCode == NEW_PHOTO_REQUEST){
            if (resultCode == RESULT_OK) {
                Photo photo = (Photo)data.getSerializableExtra(NewPhotoActivity.PHOTO_EXTRA);
                mFeedFragment.addPhoto(photo);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
