package com.girlsclothremover_bodyscanner2020.audreyprank.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.AdListener;
import com.girlsclothremover_bodyscanner2020.audreyprank.R;
import com.girlsclothremover_bodyscanner2020.audreyprank.SplashScreen;
import com.girlsclothremover_bodyscanner2020.audreyprank.UpdateApp.AppUpdateChecker;
import com.girlsclothremover_bodyscanner2020.audreyprank.config.CommonFunctions;
import com.girlsclothremover_bodyscanner2020.audreyprank.config.Constants;
import com.girlsclothremover_bodyscanner2020.audreyprank.config.TapAppConfig;
import com.girlsclothremover_bodyscanner2020.audreyprank.model.AdsPriorityResponse;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.gson.Gson;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePage extends AppCompatActivity {
    @BindView(R.id.img_start1)
    Button img_start;
    @BindView(R.id.rate)
    Button rate;
    @BindView(R.id.share)
    Button share;
    private InterstitialAd interstitialAd;
    private com.google.android.gms.ads.InterstitialAd interstitialAd_admob;
    private NativeAdLayout nativeAdLayout;
    private LinearLayout adViewFB;
    private NativeAd nativeAd;
    @BindView(R.id.native_ad_container)
    NativeAdLayout native_ad_container;
    @BindView(R.id.my_template)
    TemplateView templateView;
    @BindView(R.id.native_ad_container1)
    NativeAdLayout native_ad_container1;
    @BindView(R.id.my_template1)
    TemplateView templateView1;
    private NativeAd nativeAd1;
    private NativeAdLayout nativeAdLayout1;
    private LinearLayout adViewFB1;
    AdsPriorityResponse adsProriyResponse;

    private AdView mAdView;
    com.facebook.ads.AdView fbAdView;
    String google_banner, google_interitial, google_rewarded;
    String fb_banner, fb_interitial, fb_rewarded;
    View adContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        adContainer = findViewById(R.id.adMobView);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdSettings.addTestDevice("9778d35d-3637-4e7d-9b28-6eba42ee2098");

        AudienceNetworkAds.initialize(this);

        google_banner = SplashScreen.appConfigModel.GOOGLE_BANNER_ID;
        google_interitial = SplashScreen.appConfigModel.GOOGLE_INTERITIAL_ID;
        google_rewarded = SplashScreen.appConfigModel.GOOGLE_REWARDED_ID;
        fb_banner = SplashScreen.appConfigModel.FACEBOOK_BANNER_ID;
        fb_interitial = SplashScreen.appConfigModel.FACEBOOK_INTERITIAL_ID;
        fb_rewarded = SplashScreen.appConfigModel.FACEBOOK_REWARDED_ID;

        mAdView = new AdView(this);
        mAdView.setAdSize(AdSize.LARGE_BANNER);
        loadBannerAds();
        init();

    }

    private void init() {
        try {
            ButterKnife.bind(this);
            img_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startIntent();
                }
            });
            rate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rateus();
                }
            });
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shareapp();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressLint("WrongConstant")
    private void rateus() {
        StringBuilder sb = new StringBuilder();
        sb.append("market://details?id=");
        sb.append(getPackageName());
        String str = "android.intent.action.VIEW";
        Intent intent = new Intent(str, Uri.parse(sb.toString()));
        intent.addFlags(1208483840);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("http://play.google.com/store/apps/details?id=");
            sb2.append(getPackageName());
            startActivity(new Intent(str, Uri.parse(sb2.toString())));
        }
    }
    public void shareapp() {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", getResources().getString(R.string.app_name));
            StringBuilder sb = new StringBuilder();
            sb.append("\nHey, I am using Real Girl Body Scanner 2020 App to scan body.If you want the same try using the app by clicking\n\n");
            sb.append("https://play.google.com/store/apps/details?id=");
            sb.append(getPackageName());
            sb.append("\n\n");
            intent.putExtra("android.intent.extra.TEXT", sb.toString());
            startActivity(Intent.createChooser(intent, "choose one"));
        } catch (Exception unused) {
        }
    }
    public void loadBannerAds() {
        if (SplashScreen.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
            load_bannerAd_admob();
        } else {
            load_bannerAd_fb();
        }
    }

    private void load_bannerAd_admob() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = new AdView(this);
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId(google_banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        ((RelativeLayout) adContainer).addView(mAdView);
        mAdView.loadAd(adRequest);

    }

    private void load_bannerAd_fb() {
        fbAdView = new com.facebook.ads.AdView(HomePage.this, fb_banner, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        ((RelativeLayout) adContainer).addView(fbAdView);
        com.facebook.ads.AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e("TAG", "onError-------- " + ad + "   " + adError);

                Toast.makeText(
                        HomePage.this,
                        "Error: " + adError.getErrorMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                Log.e("TAG", "onAdLoaded-------- " + ad);
                // Ad loaded callback
            }

            @Override
            public void onAdClicked(Ad ad) {
                Log.e("TAG", "onAdClicked-------- " + ad);
                // Ad clicked callback
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                Log.e("TAG", "onLoggingImpression-------- " + ad);
                // Ad impression logged callback
            }
        };
        fbAdView.loadAd(fbAdView.buildLoadAdConfig().withAdListener(adListener).build());

    }


    private void startIntent() {

        Intent i = new Intent(HomePage.this, RecyclyeViews.class);
        i.putExtra(Constants.GOOGLE_INTERITIAL_ID, google_interitial);
        i.putExtra(Constants.FACEBOOK_INTERITIAL_ID, fb_interitial);
        i.putExtra(Constants.GOOGLE_BANNER_ID, google_banner);
        i.putExtra(Constants.FACEBOOK_BANNER_ID, fb_banner);
        i.putExtra(Constants.GOOGLE_REWARDED_ID, google_rewarded);
        i.putExtra(Constants.FACEBOOK_REWARDED_ID, fb_rewarded);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        if (fbAdView != null) {
            fbAdView.destroy();
        }
        super.onDestroy();
    }


}