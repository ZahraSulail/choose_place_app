package com.barmej.interactiveapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Tag constant to display messages
    private static final String TAG = MainActivity.class.getSimpleName();

    //Bundle constant to safe state of the activity
    private static final String BUNDLE_CURRENT_INDEX = "BUNDLE_CURRENT_INDEX";

    /**
     * TextView to display gift names
     */
    private TextView mPlaceNameTextView;

    // Random variable
    private Random mRandom;

    /*
     Place object
     */
    Place suggesionPlace;

    //ImageView varaible to display the places pictures
    private ImageView placeImageView;

    // An Array to store pictures sources
    private Place[] mPlace = {
            new Place( R.string.park, R.drawable.park ),
            new Place( R.string.bike, R.drawable.bike ),
            new Place( R.string.walking, R.drawable.walking ),
            new Place( R.string.swiming, R.drawable.swimming ),
            new Place( R.string.shop, R.drawable.shop ),
            new Place( R.string.running, R.drawable.running ),
            new Place( R.string.resturant, R.drawable.restaurant ),
            new Place( R.string.museum, R.drawable.museum ),
            new Place( R.string.football, R.drawable.football ),
            new Place( R.string.beach, R.drawable.beach ),
    };

    //Intial value of mCurrentIndex variable
    int mCurrentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        //Find image view by id
        placeImageView = findViewById( R.id.place_image_view );
        mPlaceNameTextView = findViewById( R.id.text_view_place );

        //Create Random object
        mRandom = new Random();

        showImage();
    }

    /*
      Restore the state of activity
    */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState( savedInstanceState );
        if (savedInstanceState.containsKey( BUNDLE_CURRENT_INDEX )) {
            mCurrentIndex = savedInstanceState.getInt( BUNDLE_CURRENT_INDEX );
            if (mCurrentIndex != -1) {
                showImage();
            }
        }
        Log.i( TAG, "onRestoreInstanceState" );
    }

    // Save the state of activity
    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putInt( BUNDLE_CURRENT_INDEX, mCurrentIndex );
        super.onSaveInstanceState( outState );
    }

    // Display method to display pictures randomly
    public void display(View view) {
        if (mCurrentIndex < mPlace.length) {
            mCurrentIndex = getRandomNumber();
        } else {
            mCurrentIndex = -1;
        }
        showImage();
    }

    // Show image method
    private void showImage() {
        suggesionPlace = mPlace[mCurrentIndex];
        Drawable placeDrawable = ContextCompat.getDrawable( this, suggesionPlace.getPicture() );
        placeImageView.setImageDrawable( placeDrawable );
        mPlaceNameTextView.setText( suggesionPlace.getName() );
    }


    //Next button code
    public void next(View view) {
        suggesionPlace = mPlace[mCurrentIndex];
        if (mCurrentIndex < mPlace.length - 1) {
            mCurrentIndex++;
        } else {
            mCurrentIndex = 0;
        }
        showImage();
    }

    // Previous button code
    public void previous(View view) {
        suggesionPlace = mPlace[mCurrentIndex];
        if (mCurrentIndex > 0) {
            mCurrentIndex--;
            showImage();
            Log.i( TAG, "Previous Place" );
        } else {
            mCurrentIndex = mPlace.length - 1;
            showImage();
        }
    }

    /*
  Get random number from the place array
 */
    private int getRandomNumber() {
        int newRandom = mCurrentIndex;
        while (newRandom == mCurrentIndex) {
            newRandom = mRandom.nextInt( mPlace.length );
        }
        return newRandom;
    }

    //To restart the app
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i( TAG, "Restarted" );
    }
}
