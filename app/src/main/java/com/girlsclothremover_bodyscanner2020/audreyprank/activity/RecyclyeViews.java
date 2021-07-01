package com.girlsclothremover_bodyscanner2020.audreyprank.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.girlsclothremover_bodyscanner2020.audreyprank.R;
import com.girlsclothremover_bodyscanner2020.audreyprank.SplashScreen;
import com.girlsclothremover_bodyscanner2020.audreyprank.activity.MainActivity;
import com.girlsclothremover_bodyscanner2020.audreyprank.config.Constants;
import com.girlsclothremover_bodyscanner2020.audreyprank.fragment.SlideshowDialogFragment;
import com.girlsclothremover_bodyscanner2020.audreyprank.model.ImageUrl;
import com.girlsclothremover_bodyscanner2020.audreyprank.model.ModelClass;
import com.girlsclothremover_bodyscanner2020.audreyprank.model.PersonResponse;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclyeViews extends AppCompatActivity {
    @BindView(R.id.rv_add_survey)
    RecyclerView rv_favorites_item;
    PersonResponse homeData;
    //PersonAdapter favouriteAdapter;
    List<PersonResponse.Data> dataEntity = new ArrayList<>();
    private AdView mAdView;
    com.facebook.ads.AdView fbAdView;
    AdRequest adRequest;
    View adContainer;
    InterstitialAd mInterstitialAd;
    FirebaseDatabase database;
    DatabaseReference myref;
    String ginurl,finurl,gburl,fburl,grewardedurl,frewardedburl;
    final ArrayList imageUrlList = prepareData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclye_view);
        adContainer = findViewById(R.id.adView);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdSettings.addTestDevice("9778d35d-3637-4e7d-9b28-6eba42ee2098");

        AudienceNetworkAds.initialize(this);
        Intent intent = getIntent();
        ginurl= intent.getStringExtra(Constants.GOOGLE_INTERITIAL_ID);
        gburl= intent.getStringExtra(Constants.GOOGLE_BANNER_ID);
        finurl= intent.getStringExtra(Constants.FACEBOOK_INTERITIAL_ID);
        fburl= intent.getStringExtra(Constants.FACEBOOK_BANNER_ID);
        grewardedurl= intent.getStringExtra(Constants.GOOGLE_REWARDED_ID);
        frewardedburl= intent.getStringExtra(Constants.FACEBOOK_REWARDED_ID);

//        mInterstitialAd = new InterstitialAd(this);
//
//        // set the ad unit ID
//        mInterstitialAd.setAdUnitId(unitid);
//
//        AdRequest adRequest = new AdRequest.Builder()
//                .build();
//
//        // Load ads into Interstitial Ads
//        mInterstitialAd.loadAd(adRequest);
//        load_bannerAd_admob();
//        mInterstitialAd.setAdListener(new AdListener() {
//            public void onAdLoaded() {
//                showInterstitial();
//            }
//        });
        mAdView = new AdView(this);
        mAdView.setAdSize(AdSize.LARGE_BANNER);
        loadBannerAds();
        init();
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
        mAdView.setAdUnitId(gburl);
        AdRequest adRequest = new AdRequest.Builder().build();
        ((RelativeLayout) adContainer).addView(mAdView);
        mAdView.loadAd(adRequest);

    }

    private void load_bannerAd_fb() {
        fbAdView = new com.facebook.ads.AdView(RecyclyeViews.this, fburl, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        ((RelativeLayout) adContainer).addView(fbAdView);
        com.facebook.ads.AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e("TAG", "onError-------- " + ad + "   " + adError);

                Toast.makeText(
                        RecyclyeViews.this,
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
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    private void init() {
        try {
            ButterKnife.bind(this);
            AudienceNetworkAds.initialize(this);
            AdSettings.addTestDevice(Constants.facebook_HashKey);
            rv_favorites_item=(RecyclerView)findViewById(R.id.rv_add_survey);
            rv_favorites_item.setHasFixedSize(true);
            rv_favorites_item.setLayoutManager(new LinearLayoutManager(this));
            database=FirebaseDatabase.getInstance();
            myref=database.getReference("data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<ModelClass, BlogViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ModelClass, BlogViewHolder>(
                        ModelClass.class,
                        R.layout.item,
                        BlogViewHolder.class,
                        myref) {

                    @Override
                    protected void populateViewHolder(BlogViewHolder viewHolder, ModelClass model,int position){
                        viewHolder.setTitle(model.getTitle());
                        viewHolder.setImage(getApplicationContext(),model.getImage());
                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent1 = new Intent (RecyclyeViews.this, MainActivity.class);
//                                Bundle data1 = new Bundle();
//                                data1.putString(Constants.image1,);
//                                data1.putString(Constants.GOOGLE_REWARDED_ID, grewardedurl );
//                                data1.putString(Constants.FACEBOOK_REWARDED_ID, frewardedburl);
//                                data1.putString(Constants.GOOGLE_INTERITIAL_ID, ginurl);
//                                data1.putString(Constants.FACEBOOK_INTERITIAL_ID, finurl);
//                                intent1.putExtras(data1);
//                                startActivity(intent1);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("images", imageUrlList);
                                bundle.putInt("position", position);
                                bundle.putString(Constants.GOOGLE_REWARDED_ID, grewardedurl );
                                bundle.putString(Constants.FACEBOOK_REWARDED_ID, frewardedburl);
                                bundle.putString(Constants.GOOGLE_INTERITIAL_ID, ginurl);
                                bundle.putString(Constants.FACEBOOK_INTERITIAL_ID, finurl);
                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                                newFragment.setArguments(bundle);
                                newFragment.show(ft, "slideshow");
                            }
                        });

                    }
                };
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        rv_favorites_item.setLayoutManager(gridLayoutManager);
        rv_favorites_item.setAdapter(firebaseRecyclerAdapter);

    }
    private ArrayList prepareData() {

// here you should give your image URLs and that can be a link from the Internet
        String imageUrls[] = {
//                "https://api.androidhive.info/images/glide/large/deadpool.jpg",
//                "https://api.androidhive.info/images/glide/large/bvs.jpg",
//                "https://api.androidhive.info/images/glide/large/cacw.jpg",
//                "https://api.androidhive.info/images/glide/large/bourne.jpg",
//                "https://api.androidhive.info/images/glide/large/doctor.jpg",
//                "https://api.androidhive.info/images/glide/large/squad.jpg",
//                "https://api.androidhive.info/images/glide/large/dory.jpg",
//                "https://api.androidhive.info/images/glide/large/hunger.jpg"
                "https://firebasestorage.googleapis.com/v0/b/tap-2-1823e.appspot.com/o/pexels-photo-1391498.jpeg?alt=media&token=024be817-7dcb-4f97-8da9-62a9308f2804",
                "https://firebasestorage.googleapis.com/v0/b/tap-2-1823e.appspot.com/o/13d009fecf27a8b129453f727997e26e.jpg?alt=media&token=d6079df3-19dc-490d-9e99-b2b3125b66ea",
                "https://firebasestorage.googleapis.com/v0/b/tap-2-1823e.appspot.com/o/ea5adf473d423d767d7c2fb7a3e8a7794f685c50_hq.jpg?alt=media&token=ec5077ad-fdda-412b-b30f-ae3edb20938f",
                "https://firebasestorage.googleapis.com/v0/b/tap-2-1823e.appspot.com/o/bc4ae292601589c8fa9cead866051340.jpg225.jpg?alt=media&token=d304af5c-0545-4ed4-b533-8172cedfcf64",
                "https://firebasestorage.googleapis.com/v0/b/tap-2-1823e.appspot.com/o/b4112d0f4ff5ae27d63ffce1eb2cfa14--le.jpg?alt=media&token=586ac302-97df-4d1d-b87d-0a00dc758eb9",
                "https://firebasestorage.googleapis.com/v0/b/tap-2-1823e.appspot.com/o/a9cc8f8982b5eee0444b6074bf33099a.jpg?alt=media&token=126a377f-5daf-43dd-8505-fa41d3c589fb",
                "https://firebasestorage.googleapis.com/v0/b/tap-2-1823e.appspot.com/o/9ac49df7-bd39-4a6f-84a2-68bd8fc6bfd5.jpg?alt=media&token=240d974f-964a-42e8-b171-2d19fa13ba71",
                "https://firebasestorage.googleapis.com/v0/b/tap-2-1823e.appspot.com/o/6c0807ed5656f047f7bf89426ef78b9b.jpg?alt=media&token=196ed5d5-2ff8-40d9-9778-71bd4d053e14",
                "https://firebasestorage.googleapis.com/v0/b/tap-2-1823e.appspot.com/o/410QL0ms-dL.jpg?alt=media&token=574402ab-427e-43bd-a96a-a29835cb81b5"
        };

        ArrayList imageUrlList = new ArrayList<>();
        for (int i = 0; i < imageUrls.length; i++) {
            ImageUrl imageUrl = new ImageUrl();
            imageUrl.setImageUrl(imageUrls[i]);
            imageUrlList.add(imageUrl); }
        Log.d("MainActivity", "List count: " + imageUrlList.size());
        return imageUrlList; }
    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public BlogViewHolder(View itemView){
            super(itemView);
            mView=itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
        public void setTitle(String title){
            TextView post_title=(TextView)mView.findViewById(R.id.tv_person_name);
            post_title.setText(title);

        }
        public void setImage(Context ctx, String image){
            ImageView post_image=(ImageView) mView.findViewById(R.id.iv_profile);
            //      Picasso.with(ctx).load(image).into(post_image);
            Picasso.get().load(image).into(post_image);
            RequestOptions requestOptions = new RequestOptions()
                    .apply(RequestOptions.errorOf(R.drawable.no_image))
                    .apply(RequestOptions.placeholderOf(R.drawable.no_image));
            Glide.with(ctx)
                    .load(image)
                    .apply(requestOptions)
                    .into(post_image);
        }
    }
}
