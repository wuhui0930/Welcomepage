package com.personal.welcomepage;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import adapter.WelcomeAdapter;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPager viewPager;
    private WelcomeAdapter viewadapter;
    private List<View> views;
    private static final int[] images = {R.mipmap.bgm1, R.mipmap.bgm2, R.mipmap.bgm3, R.mipmap.bgm4};
    private ImageView[] dots;
    //当前指数
    private int currentindex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);


        views = new ArrayList<View>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < images.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(params);
            iv.setImageResource(images[i]);
            views.add(iv);
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewadapter = new WelcomeAdapter(views);
        viewPager.setAdapter(viewadapter);
        viewPager.setOnClickListener(this);
        initdots();
    }

    private void initdots() {
        LinearLayout dots_ll = (LinearLayout) findViewById(R.id.dots);
        dots = new ImageView[images.length];
        for (int i = 0; i < images.length; i++) {
            dots[i] = (ImageView) dots_ll.getChildAt(i);
            dots[i].setEnabled(true);
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);
        }
        currentindex = 0;
        dots[currentindex].setEnabled(false);
    }

    private void setCurView(int i) {
        if (i < 0 || i >= images.length) {
            return;
        }
        viewPager.setCurrentItem(i);
    }

    private void setCurDot(int positon) {
        if (positon < 0 || positon > images.length - 1 || currentindex == positon) {
            return;
        }

        dots[positon].setEnabled(false);
        dots[currentindex].setEnabled(true);

        currentindex = positon;
    }
    //当当前页面被滑动时调用
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.v("logs","333:"+position);
    }

    //当新的页面被选中时调用
    @Override
    public void onPageSelected(int position) {
//        setCurDot(position);
        Log.v("logs","222:"+position);
    }

    //当滑动状态改变时调用
    @Override
    public void onPageScrollStateChanged(int state) {
        Log.v("logs","111:"+state);
    }

    @Override
    public void onClick(View view) {
        int position = (Integer) view.getTag();
        setCurView(position);
        setCurDot(position);
    }
}
