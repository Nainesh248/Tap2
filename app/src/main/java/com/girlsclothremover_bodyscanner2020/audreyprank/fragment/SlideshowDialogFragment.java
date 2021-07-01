package com.girlsclothremover_bodyscanner2020.audreyprank.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.girlsclothremover_bodyscanner2020.audreyprank.R;
import com.girlsclothremover_bodyscanner2020.audreyprank.SplashScreen;
import com.girlsclothremover_bodyscanner2020.audreyprank.config.CommonFunctions;
import com.girlsclothremover_bodyscanner2020.audreyprank.config.Constants;
import com.girlsclothremover_bodyscanner2020.audreyprank.model.ImageUrl;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.util.ArrayList;
import java.util.Random;

import butterknife.ButterKnife;


public class SlideshowDialogFragment extends DialogFragment {
    private String TAG = SlideshowDialogFragment.class.getSimpleName();
    private ArrayList<ImageUrl> images;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private TextView lblCount, lblTitle, lblDate;
    private int selectedPosition = 0;
    final int min = 2;
    final int max = 5;
    public int count = new Random().nextInt((max - min) + 1) + min;
    public int count2 = new Random().nextInt((max - min) + 1) + min;
    public int count3 = new Random().nextInt((max - min) + 1) + min;
    public int count4 = new Random().nextInt((max - min) + 1) + min;
    public int count5 = new Random().nextInt((max - min) + 1) + min;
    TextView text1;
    ImageView back;

    ImageView image1;
    ImageView image2;
    TextView text2;
    TextView text3;
    ImageView image3;
    TextView text4;
    ImageView image4;
    TextView text5;
    ImageView image5;
    ImageView img_girl;
    Button btn_next_image;
    View adContainer;
    private int coinCount;
    private TextView coinCountText;
    RewardedVideoAd fbrewardedVideoAd;
    private RewardedAd rewardedAd;
    private int image;
    String ginurl,finurl,gburl,fburl,grewardedurl,frewardedburl;
    com.facebook.ads.InterstitialAd fbinterstitialAd;
    private String path1;
    private String path2;
    boolean isFirst, isSecond, isThird, isFour, isFive;
    private InterstitialAd interstitialAd;
    public static SlideshowDialogFragment newInstance() {
        SlideshowDialogFragment f = new SlideshowDialogFragment();
        return f;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image_slider, container, false);
        AudienceNetworkAds.initialize(getActivity());
        coinCountText = (TextView) v.findViewById(R.id.coin_count_text);
//        coinCountText.setText("Coins: " + coinCount--);
        coinCount = CommonFunctions.getPreference(getActivity(), Constants.coin, 20);
        coinCountText.setText("Coins: " + coinCount);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        btn_next_image = (Button) v.findViewById(R.id.img_new_image);
        img_girl = (ImageView) v.findViewById(R.id.img_girl);
        back = (ImageView) v.findViewById(R.id.back);
        text1 = (TextView) v.findViewById(R.id.text1);
        text2 = (TextView) v.findViewById(R.id.text2);
        text3 = (TextView) v.findViewById(R.id.text3);
        text4 = (TextView) v.findViewById(R.id.text4);
        text5 = (TextView) v.findViewById(R.id.text5);
        image1 = (ImageView) v.findViewById(R.id.image1);
        image2 = (ImageView) v.findViewById(R.id.image2);
        adContainer = (View) v.findViewById(R.id.adView);
        image3 = (ImageView) v.findViewById(R.id.image3);
        image4 = (ImageView) v.findViewById(R.id.image4);
        image5 = (ImageView) v.findViewById(R.id.image5);

        viewPager.beginFakeDrag();
        init();
        if (SplashScreen.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
            setInterstitialAd();
        } else {
            load_InterstitialAd_fb();
        }
        if (SplashScreen.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
            load_RewardedVideoAd_admob();
        } else {
            loadRewardedVideoAd_fb();
        }
        return v;
    }
    private void load_InterstitialAd_fb() {
        fbinterstitialAd = new com.facebook.ads.InterstitialAd(getActivity(), "YOUR_PLACEMENT_ID");
        InterstitialAdListener interstitialAdListener=new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                fbinterstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
    }
    public void setInterstitialAd(){
        interstitialAd = new InterstitialAd(getActivity());

        // set the ad unit ID
        interstitialAd.setAdUnitId(ginurl);

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        interstitialAd.loadAd(adRequest);
        interstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if (interstitialAd.isLoaded()) {

                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void init() {
        ButterKnife.bind(getActivity());
        AudienceNetworkAds.initialize(getActivity());
        FragmentActivity intentReceived = getActivity();
        Bundle data = intentReceived.getIntent().getExtras();
        if(data != null){
            grewardedurl= data.getString(Constants.GOOGLE_REWARDED_ID);
            frewardedburl= data.getString(Constants.FACEBOOK_REWARDED_ID);
            ginurl= data.getString(Constants.GOOGLE_INTERITIAL_ID);
            finurl= data.getString(Constants.FACEBOOK_INTERITIAL_ID);
        }
        images  = (ArrayList<ImageUrl>) getArguments().getSerializable("images");
        selectedPosition = getArguments().getInt("position");
        btn_next_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                tab++;
                viewPager.setCurrentItem(tab);

                text1.setVisibility(View.VISIBLE);
                text2.setVisibility(View.VISIBLE);
                text3.setVisibility(View.VISIBLE);
                text4.setVisibility(View.VISIBLE);
                text5.setVisibility(View.VISIBLE);

                image1.setVisibility(View.VISIBLE);
                image2.setVisibility(View.VISIBLE);
                image3.setVisibility(View.VISIBLE);
                image4.setVisibility(View.VISIBLE);
                image5.setVisibility(View.VISIBLE);

                btn_next_image.setVisibility(View.INVISIBLE);


                count = new Random().nextInt((max - min) + 1) + min;
                count2 = new Random().nextInt((max - min) + 1) + min;
                count3 = new Random().nextInt((max - min) + 1) + min;
                count4 = new Random().nextInt((max - min) + 1) + min;
                count5 = new Random().nextInt((max - min) + 1) + min;

                text1.setText(getResources().getString(R.string.click) + " " + Integer.toString(count));
                text2.setText(getResources().getString(R.string.click) + " " + Integer.toString(count2));
                text3.setText(getResources().getString(R.string.click) + " " + Integer.toString(count3));
                text4.setText(getResources().getString(R.string.click) + " " + Integer.toString(count4));
                text5.setText(getResources().getString(R.string.click) + " " + Integer.toString(count5));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onStop();
            }
        });




        AdSettings.addTestDevice("eaf5cb1b-531e-432d-9e78-597e34781447");

        text1.setText(getResources().getString(R.string.click) + " " + Integer.toString(count));
        text2.setText(getResources().getString(R.string.click) + " " + Integer.toString(count2));
        text3.setText(getResources().getString(R.string.click) + " " + Integer.toString(count3));
        text4.setText(getResources().getString(R.string.click) + " " + Integer.toString(count4));
        text5.setText(getResources().getString(R.string.click) + " " + Integer.toString(count5));

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (coinCount==0)
                    {
                        showDialog1();
                    }
                    isFirst = false;
                    isSecond = false;
                    isThird = false;
                    isFour = false;
                    isFive = true;
                    if (coinCount > 0){
                        count5--;
                        coinCount--;
                        coinCountText.setText("Coins: " + coinCount);
                    }
                    CommonFunctions.setPreference(getActivity(), Constants.coin, coinCount);
                    if (count5 <= 0) {
                        startIntent();
                    } else {
                        text5.setText("Click "+Integer.toString(count5));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (coinCount==0)
                    {
                        showDialog1();
                    }
                    isFirst = false;
                    isSecond = false;
                    isThird = false;
                    isFour = true;
                    isFive = false;
                    if (coinCount > 0){
                        count4--;
                        coinCount--;
                        coinCountText.setText("Coins: " + coinCount);
                    }
                    CommonFunctions.setPreference(getActivity(), Constants.coin, coinCount);
                    if (count4 <= 0) {
                        startIntent();
                    } else {
                        text4.setText("Click "+Integer.toString(count4));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (coinCount==0)
                    {
                        showDialog1();
                    }
                    isFirst = false;
                    isSecond = false;
                    isThird = true;
                    isFour = false;
                    isFive = false;
                    if (coinCount > 0){
                        count3--;
                        coinCount--;
                        coinCountText.setText("Coins: " + coinCount);
                    }
                    CommonFunctions.setPreference(getActivity(), Constants.coin, coinCount);
                    if (count3 <= 0) {
                        startIntent();

                    } else {
                        text3.setText("Click "+Integer.toString(count3));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (coinCount==0)
                    {
                        showDialog1();
                    }
                    isFirst = false;
                    isSecond = true;
                    isThird = false;
                    isFour = false;
                    isFive = false;
                    if (coinCount > 0){
                        count2--;
                        coinCount--;
                        coinCountText.setText("Coins: " + coinCount);
                    }
                    CommonFunctions.setPreference(getActivity(), Constants.coin, coinCount);
                    if (count2 <= 0) {
                        startIntent();
                    } else {
                        text2.setText("Click "+Integer.toString(count2));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (coinCount==0)
                    {
                        showDialog1();
                    }
                    isFirst = true;
                    isSecond = false;
                    isThird = false;
                    isFour = false;
                    isFive = false;
                    if (coinCount > 0){
                        count--;
                        coinCount--;
                        coinCountText.setText("Coins: " + coinCount);
                    }
                    CommonFunctions.setPreference(getActivity(), Constants.coin, coinCount);
                    if (count <= 0) {
                        startIntent();
                    } else {
                        text1.setText("Click "+Integer.toString(count));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });




        Log.e(TAG, "position: " + selectedPosition);
        Log.e(TAG, "images size: " + images.size());

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        setCurrentItem(selectedPosition);



    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setCurrentItem(int position) {
        viewPager.setCurrentItem(position, false);
    }

    //  page change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void addCoins(int coins) {
        coinCount += coins;
        coinCountText.setText("Coins: " + coinCount);
    }

    private void showDialog1() {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setTitle("Your coin is 0\n" +
                "Click on Show Ads to get 20 coins");
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.setPositiveButton("Show Ads", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
              googRewardedVideoAdleadshow();
                addCoins(20);
            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            googleinterstialadshow();
            }
        });
        adb.show();
    }


    private void checkCount() {
        if (count <= 0 && count2 <= 0 && count3 <= 0 && count4 <= 0 && count5 <= 0) {
            btn_next_image.setVisibility(View.VISIBLE);
    }
    }

    private void startIntent() {
        if (isFirst) {
            image1.setVisibility(View.INVISIBLE);
            text1.setVisibility(View.INVISIBLE);
            checkCount();
        } else if (isSecond) {
            image2.setVisibility(View.INVISIBLE);
            text2.setVisibility(View.INVISIBLE);
            checkCount();

        } else if (isThird) {
            image3.setVisibility(View.INVISIBLE);
            text3.setVisibility(View.INVISIBLE);
            checkCount();

        } else if (isFour) {
            image4.setVisibility(View.INVISIBLE);
            text4.setVisibility(View.INVISIBLE);
            checkCount();

        } else if (isFive) {
            image5.setVisibility(View.INVISIBLE);
            text5.setVisibility(View.INVISIBLE);
            checkCount();
        }
    }
    public void googleinterstialadshow(){
        interstitialAd.show();
        setInterstitialAd();
    }
    public void facebookinterstialadshow(){

    }
    public void googRewardedVideoAdleadshow(){
        RewardedAdCallback adCallback = new RewardedAdCallback() {
            @Override
            public void onRewardedAdOpened() {
                // Ad opened.
            }

            @Override
            public void onRewardedAdClosed() {
                // Ad closed.
            }

            @Override
            public void onUserEarnedReward(@NonNull com.google.android.gms.ads.rewarded.RewardItem rewardItem) {
                addCoins(20);
            }
        };
        rewardedAd.show(getActivity(),adCallback);
        load_RewardedVideoAd_admob();
    }
    public void facebookRewardedVideoAdadshow(){

    }
    private void load_RewardedVideoAd_admob() {
        rewardedAd = new RewardedAd(getActivity(),
                grewardedurl);

        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
                if (rewardedAd.isLoaded()) {

                } else {
                    Log.d("TAG", "The rewarded ad wasn't loaded yet.");
                }
            }

            @Override
            public void onRewardedAdFailedToLoad(LoadAdError adError) {
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
    }

    private void loadRewardedVideoAd_fb() {
        fbrewardedVideoAd = new RewardedVideoAd(getActivity(), frewardedburl);
        RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {
            @Override
            public void onError(Ad ad, AdError error) {
                Log.e("TAG", "Rewarded video ad failed to load: " + error.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                Log.d("TAG", "Rewarded video ad is loaded and ready to be displayed!");
                fbrewardedVideoAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                Log.d("TAG", "Rewarded video ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                Log.d("TAG", "Rewarded video ad impression logged!");
            }

            @Override
            public void onRewardedVideoCompleted() {
                Log.d("TAG", "Rewarded video completed!");
                addCoins(20);
            }

            @Override
            public void onRewardedVideoClosed() {
                Log.d("TAG", "Rewarded video ad closed!");
            }
        };
        fbrewardedVideoAd.loadAd(
                fbrewardedVideoAd.buildLoadAdConfig()
                        .withAdListener(rewardedVideoAdListener)
                        .build());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    //  adapter
    public class MyViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.image_layout ,container, false);

            ImageView imageViewPreview = (ImageView) view.findViewById(R.id.image_preview);

            ImageUrl image = images.get(position);

            Glide.with(getActivity()).load(image.getImageUrl())
                    .thumbnail(0.5f)
                    .into(imageViewPreview);

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == ((View) obj);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }


    }
}
