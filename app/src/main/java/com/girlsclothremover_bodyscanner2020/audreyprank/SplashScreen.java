package com.girlsclothremover_bodyscanner2020.audreyprank;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.girlsclothremover_bodyscanner2020.audreyprank.activity.HomePage;
import com.girlsclothremover_bodyscanner2020.audreyprank.config.Constants;
import com.girlsclothremover_bodyscanner2020.audreyprank.model.AppConfigModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity {
    int SPLASH_TIME_OUT = 2000;
    public static AppConfigModel appConfigModel = new AppConfigModel();
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
       // init();
        getremoteconfig();
    }
    private void getremoteconfig() {
        try {
            FirebaseApp.initializeApp(this);
            mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

            FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                    .setMinimumFetchIntervalInSeconds(0)
                    .build();
            mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);

            mFirebaseRemoteConfig.fetchAndActivate()
                    .addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
                        @Override
                        public void onComplete(@NonNull com.google.android.gms.tasks.Task<Boolean> task) {
                            if (task.isSuccessful()) {
                                appConfigModel.IS_GOOGLE = mFirebaseRemoteConfig.getString(Constants.IS_GOOGLE);
                                appConfigModel.IS_FACEBOOK= mFirebaseRemoteConfig.getString(Constants.IS_FACEBOOK);
                                appConfigModel.GOOGLE_BANNER_ID= mFirebaseRemoteConfig.getString(Constants.GOOGLE_BANNER_ID);
                                appConfigModel.GOOGLE_INTERITIAL_ID= mFirebaseRemoteConfig.getString(Constants.GOOGLE_INTERITIAL_ID);
                                appConfigModel.GOOGLE_REWARDED_ID= mFirebaseRemoteConfig.getString(Constants.GOOGLE_REWARDED_ID);
                                appConfigModel.FACEBOOK_BANNER_ID= mFirebaseRemoteConfig.getString(Constants.FACEBOOK_BANNER_ID);
                                appConfigModel.FACEBOOK_INTERITIAL_ID= mFirebaseRemoteConfig.getString(Constants.FACEBOOK_INTERITIAL_ID);
                                appConfigModel.FACEBOOK_REWARDED_ID=     mFirebaseRemoteConfig.getString(Constants.FACEBOOK_REWARDED_ID);
                                Intent intent = new Intent(SplashScreen.this, HomePage.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(SplashScreen.this, "Fetch failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    private void init() {
//        try {
//            ButterKnife.bind(this);
//            if (getSupportActionBar() != null)
//                getSupportActionBar().hide();
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                        Intent intent = new Intent(SplashScreen.this, HomePage.class);
//                        startActivity(intent);
//                        finish();
//                }
//            }, SPLASH_TIME_OUT);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }
}