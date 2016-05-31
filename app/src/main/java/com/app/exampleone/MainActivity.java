package com.app.exampleone;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.app.exampleone.adapter.SampleItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.item_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.item_image)
    ImageView mImage;

    @BindView(R.id.item_image_alpha)
    ImageView mImageAlpha;

    @BindView(R.id.item_progress_bar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Init
        setupInit();

        // Image View
        setupImage("http://www.menucool.com/slider/jsImgSlider/images/image-slider-2.jpg");

        // Recycler View
        setupRecyclerView();
    }

    private void setupInit() {
        // Toolbar
        setSupportActionBar(mToolbar);
        setTitle("Example One");

        // Define Alpha
        mImageAlpha.getBackground().setAlpha(50);
        mToolbar.getChildAt(0).setAlpha(0);
    }

    private void setupImage(String url) {
        Glide.with(this).load(url).placeholder(R.color.colorAccent).into(new GlideDrawableImageViewTarget(mImage) {
            @Override
            public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                super.onResourceReady(drawable, anim);

                if (mProgressBar != null)
                    mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);

                if (mProgressBar != null)
                    mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void setupRecyclerView() {
        FastItemAdapter fastAdapter = new FastItemAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(fastAdapter);

        for (int i = 0; i < 20; i++) {
            fastAdapter.add(new SampleItem().withName("Item " + i));
        }
    }
}
