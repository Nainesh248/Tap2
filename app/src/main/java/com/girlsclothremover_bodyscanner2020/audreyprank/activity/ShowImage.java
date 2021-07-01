package com.girlsclothremover_bodyscanner2020.audreyprank.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.girlsclothremover_bodyscanner2020.audreyprank.R;
import com.girlsclothremover_bodyscanner2020.audreyprank.config.CommonFunctions;
import com.girlsclothremover_bodyscanner2020.audreyprank.config.Constants;
import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowImage extends AppCompatActivity {

    @BindView(R.id.img_girl)
    ImageView img_girl;
    String path;
    private NativeAdLayout nativeAdLayout;
    private LinearLayout adViewFB;
    private NativeAd nativeAd;
    @BindView(R.id.native_ad_container)
    NativeAdLayout native_ad_container;
    @BindView(R.id.my_template)
    TemplateView templateView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
       // loadNativeAd();
        path = getIntent().getExtras().getString(Constants.image2);
        Glide.with(this)
                .load(path)
                .centerCrop()
                .placeholder(R.drawable.no_image)
                .into(img_girl);

    }


//    public void loadNativeAd() {
//        if (CommonFunctions.getPreference(ShowImage.this, Constants.priority, "").equalsIgnoreCase(Constants.google)) {
//            load_nativead_admob();
//        } else {
//            loadFbNativeAd();
//        }
//    }
    public void load_nativead_admob() {
        List<String> testDeviceIds = Arrays.asList(Constants.ads_testDevice_id);
        RequestConfiguration configuration =
                new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build();
        MobileAds.setRequestConfiguration(configuration);
        String appid = CommonFunctions.getPreference(ShowImage.this, Constants.admobAppId, "");
        MobileAds.initialize(this, appid);
        AdLoader adLoader = new AdLoader.Builder(this, CommonFunctions.getPreference(ShowImage.this,Constants.google_native_banner,""))
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        native_ad_container.setVisibility(View.GONE);
                        templateView.setVisibility(View.VISIBLE);
                        templateView.setNativeAd(unifiedNativeAd);
//                        Toast.makeText(HomePage.this, "LoadAd Successfully", Toast.LENGTH_LONG).show();

                    }
                }).withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        native_ad_container.setVisibility(View.GONE);
                        templateView.setVisibility(View.GONE);
//                        Toast.makeText(HomePage.this, "Error", Toast.LENGTH_LONG).show();
                        Log.e("TAG", "ERROR" + loadAdError.toString());
                        super.onAdFailedToLoad(loadAdError);
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }

//    void loadFbNativeAd() {
//        nativeAd = new NativeAd(this,  CommonFunctions.getPreference(ShowImage.this,Constants.facebook_native_banner,""));
//        nativeAd.setAdListener(new NativeAdListener() {
//            @Override
//            public void onMediaDownloaded(Ad ad) {
//            }
//
//            @Override
//            public void onError(Ad ad, AdError adError) {
//                native_ad_container.setVisibility(View.GONE);
//                templateView.setVisibility(View.GONE);
//                Log.e("TAG", String.valueOf(adError.getErrorCode()));
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//                native_ad_container.setVisibility(View.VISIBLE);
//                templateView.setVisibility(View.GONE);
//                if (nativeAd == null || nativeAd != ad) {
//                    return;
//                }
//                inflateAd(nativeAd);
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//
//            }
//
//            @Override
//            public void onLoggingImpression(Ad ad) {
//
//            }
//        });
//        nativeAd.loadAd();
//    }

//   private void inflateAd(NativeAd nativeAd) {
//        nativeAd.unregisterView();
//        nativeAdLayout = findViewById(R.id.native_ad_container);
//        LayoutInflater inflater = LayoutInflater.from(ShowImage.this);
//        adViewFB = (LinearLayout) inflater.inflate(R.layout.native_ad_layout, nativeAdLayout, false);
//        nativeAdLayout.addView(adViewFB);
//        LinearLayout adChoicesContainer = findViewById(R.id.ad_choices_container);
//        AdOptionsView adOptionsView = new AdOptionsView(ShowImage.this, nativeAd, nativeAdLayout);
//        adChoicesContainer.removeAllViews();
//        adChoicesContainer.addView(adOptionsView, 0);
//        AdIconView nativeAdIcon = adViewFB.findViewById(R.id.native_ad_icon);
//        TextView nativeAdTitle = adViewFB.findViewById(R.id.native_ad_title);
//        MediaView nativeAdMedia = adViewFB.findViewById(R.id.native_ad_media);
//        TextView nativeAdSocialContext = adViewFB.findViewById(R.id.native_ad_social_context);
//        TextView nativeAdBody = adViewFB.findViewById(R.id.native_ad_body);
//        TextView sponsoredLabel = adViewFB.findViewById(R.id.native_ad_sponsored_label);
//        Button nativeAdCallToAction = adViewFB.findViewById(R.id.native_ad_call_to_action);
//        nativeAdTitle.setText(nativeAd.getAdvertiserName());
//        nativeAdBody.setText(nativeAd.getAdBodyText());
//        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
//        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
//        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
//        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());
//        List<View> clickableViews = new ArrayList<>();
//        clickableViews.add(nativeAdTitle);
//        clickableViews.add(nativeAdCallToAction);
//        nativeAd.registerViewForInteraction(adViewFB, nativeAdMedia, nativeAdIcon, clickableViews);
//    }

}