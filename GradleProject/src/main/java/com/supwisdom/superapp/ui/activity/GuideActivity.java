package com.supwisdom.superapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.viewpager.widget.ViewPager;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.zueb.R;
import supwisdom.zh;

/* JADX INFO: loaded from: classes2.dex */
public class GuideActivity extends WXBaseActivity implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewPager f4116a;
    public Button b;
    public int[] c = {R.drawable.g1, R.drawable.g2, R.drawable.g3, R.drawable.g4};

    public class a implements ViewPager.i {
        public a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.i
        public void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.i
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.i
        public void onPageSelected(int i) {
            GuideActivity guideActivity = GuideActivity.this;
            if (i == guideActivity.c.length - 1) {
                guideActivity.b.setVisibility(0);
            } else {
                guideActivity.b.setVisibility(4);
            }
        }
    }

    public class b extends zh {
        public b() {
        }

        @Override // supwisdom.zh
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        @Override // supwisdom.zh
        public int getCount() {
            return GuideActivity.this.c.length;
        }

        @Override // supwisdom.zh
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            ImageView imageView = new ImageView(viewGroup.getContext());
            imageView.setImageResource(GuideActivity.this.c[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewGroup.addView(imageView);
            return imageView;
        }

        @Override // supwisdom.zh
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.startBt) {
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, false);
        setContentView(R.layout.layout_guide);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        this.f4116a = viewPager;
        viewPager.setPageMargin(0);
        Button button = (Button) findViewById(R.id.startBt);
        this.b = button;
        button.setOnClickListener(this);
        this.f4116a.addOnPageChangeListener(new a());
        this.f4116a.setAdapter(new b());
    }
}
