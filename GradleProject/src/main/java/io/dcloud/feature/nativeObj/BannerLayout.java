package io.dcloud.feature.nativeObj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import dc.squareup.okhttp3.internal.platform.AndroidPlatform;
import io.dcloud.PdrR;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.util.TitleNViewUtil;
import io.dcloud.feature.nativeObj.data.NativeImageDataItem;
import io.dcloud.feature.nativeObj.photoview.BounceBackViewPager;
import io.dcloud.feature.nativeObj.photoview.subscaleview.SubsamplingScaleImageView;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import supwisdom.zh;

/* JADX INFO: loaded from: classes3.dex */
public class BannerLayout extends RelativeLayout {
    public static final String CIRCULAR_INDICATOR = "default";
    public static final String NONE_INDICATOR = "none";
    public static final String WORD_INDICATOR = "number";
    public int MAX_VALUE;
    public int WHAT_AUTO_PLAY;
    public int autoPlayDuration;
    public int currentPosition;
    public Handler handler;
    public ImageLoader imageLoader;
    public LinearLayout indicatorContainer;
    public int indicatorMargin;
    public Position indicatorPosition;
    public Shape indicatorShape;
    public int indicatorSpace;
    public boolean isAllowImageDownload;
    public boolean isAutoPlay;
    public boolean isImageLoop;
    public boolean isImagePhoto;
    public int itemCount;
    public String mIndicatorType;
    public ArrayList<NativeImageDataItem> mUrls;
    public OnBannerItemClickListener onBannerItemClickListener;
    public ViewPager pager;
    public int ringIndicatorColor;
    public int scrollDuration;
    public Drawable selectedDrawable;
    public int selectedIndicatorColor;
    public int selectedIndicatorHeight;
    public int selectedIndicatorWidth;
    public Drawable unSelectedDrawable;
    public int unSelectedIndicatorColor;
    public int unSelectedIndicatorHeight;
    public int unSelectedIndicatorWidth;

    /* JADX INFO: renamed from: io.dcloud.feature.nativeObj.BannerLayout$6, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass6 {
        public static final /* synthetic */ int[] $SwitchMap$io$dcloud$feature$nativeObj$BannerLayout$Position;
        public static final /* synthetic */ int[] $SwitchMap$io$dcloud$feature$nativeObj$BannerLayout$Shape;

        static {
            int[] iArr = new int[Position.values().length];
            $SwitchMap$io$dcloud$feature$nativeObj$BannerLayout$Position = iArr;
            try {
                iArr[Position.centerBottom.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$BannerLayout$Position[Position.centerTop.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$BannerLayout$Position[Position.leftBottom.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$BannerLayout$Position[Position.leftTop.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$BannerLayout$Position[Position.rightBottom.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$BannerLayout$Position[Position.rightTop.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$BannerLayout$Position[Position.none.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[Shape.values().length];
            $SwitchMap$io$dcloud$feature$nativeObj$BannerLayout$Shape = iArr2;
            try {
                iArr2[Shape.rect.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$BannerLayout$Shape[Shape.oval.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public class FixedSpeedScroller extends Scroller {
        public int mDuration;

        public FixedSpeedScroller(Context context) {
            super(context);
            this.mDuration = 1000;
        }

        @Override // android.widget.Scroller
        public void startScroll(int i, int i2, int i3, int i4, int i5) {
            super.startScroll(i, i2, i3, i4, this.mDuration);
        }

        @Override // android.widget.Scroller
        public void startScroll(int i, int i2, int i3, int i4) {
            super.startScroll(i, i2, i3, i4, this.mDuration);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
            this.mDuration = 1000;
        }

        public FixedSpeedScroller(BannerLayout bannerLayout, Context context, Interpolator interpolator, int i) {
            this(context, interpolator);
            this.mDuration = i;
        }
    }

    public interface ImageLoader extends Serializable {
        void displayImage(Context context, String str, View view, int i);
    }

    public class LoopPagerAdapter extends zh {
        public int mChildCount = 0;
        public List<View> views;

        public LoopPagerAdapter(List<View> list) {
            this.views = list;
        }

        @Override // supwisdom.zh
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != null) {
                childAt.setTag(null);
            }
        }

        @Override // supwisdom.zh
        public int getCount() {
            return (this.views.size() != 1 && BannerLayout.this.isImageLoop) ? BannerLayout.this.MAX_VALUE : this.views.size();
        }

        @Override // supwisdom.zh
        public int getItemPosition(Object obj) {
            int i = this.mChildCount;
            if (i <= 0) {
                return super.getItemPosition(obj);
            }
            this.mChildCount = i - 1;
            return -2;
        }

        public List<View> getViews() {
            return this.views;
        }

        @Override // supwisdom.zh
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            if (this.views.size() <= 0) {
                return null;
            }
            if (BannerLayout.this.isImageLoop) {
                i %= this.views.size();
            }
            View view = this.views.get(i);
            if (viewGroup.equals(view.getParent())) {
                viewGroup.removeView(view);
            }
            if (view.getTag() != null && (i == 0 || BannerLayout.this.isAllowImageDownload)) {
                BannerLayout.this.imageLoader.displayImage(BannerLayout.this.getContext(), ((NativeImageDataItem) view.getTag()).getUrl(), view, i);
            }
            viewGroup.addView(view);
            return view;
        }

        @Override // supwisdom.zh
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override // supwisdom.zh
        public void notifyDataSetChanged() {
            this.mChildCount = getCount();
            super.notifyDataSetChanged();
        }

        public void notifyItemsView(List<View> list) {
            this.views = list;
            notifyDataSetChanged();
        }
    }

    public interface OnBannerItemClickListener {
        void onItemClick(int i);

        void onItemLongClick(int i);
    }

    public enum Position {
        centerBottom,
        rightBottom,
        leftBottom,
        centerTop,
        rightTop,
        leftTop,
        none
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: io.dcloud.feature.nativeObj.BannerLayout.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        public int currentPosition;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.currentPosition);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.currentPosition = parcel.readInt();
        }
    }

    public enum Shape {
        rect,
        oval
    }

    public BannerLayout(Context context, boolean z, boolean z2) {
        this(context, null, z, z2);
    }

    private View getImageView(NativeImageDataItem nativeImageDataItem, final int i) {
        View view;
        if (this.isImagePhoto) {
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            relativeLayout.setClickable(true);
            SubsamplingScaleImageView subsamplingScaleImageView = new SubsamplingScaleImageView(getContext());
            subsamplingScaleImageView.setOnClickListener(new View.OnClickListener() { // from class: io.dcloud.feature.nativeObj.BannerLayout.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (BannerLayout.this.onBannerItemClickListener != null) {
                        BannerLayout.this.onBannerItemClickListener.onItemClick(i);
                    }
                }
            });
            subsamplingScaleImageView.setOnLongClickListener(new View.OnLongClickListener() { // from class: io.dcloud.feature.nativeObj.BannerLayout.3
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view2) {
                    if (BannerLayout.this.onBannerItemClickListener == null) {
                        return true;
                    }
                    BannerLayout.this.onBannerItemClickListener.onItemLongClick(i);
                    return true;
                }
            });
            subsamplingScaleImageView.setOrientation(-1);
            relativeLayout.addView(subsamplingScaleImageView, new RelativeLayout.LayoutParams(-1, -1));
            ProgressBar progressBar = new ProgressBar(getContext());
            try {
                progressBar.setIndeterminateDrawable(getContext().getResources().getDrawable(PdrR.DRAWBLE_PROGRESSBAR_WHITE_CIRCLE));
            } catch (Exception unused) {
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(100, 100);
            layoutParams.addRule(13);
            relativeLayout.addView(progressBar, layoutParams);
            view = relativeLayout;
        } else {
            BannerImageView bannerImageView = new BannerImageView(getContext(), nativeImageDataItem);
            bannerImageView.setOnClickListener(new View.OnClickListener() { // from class: io.dcloud.feature.nativeObj.BannerLayout.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (BannerLayout.this.onBannerItemClickListener != null) {
                        BannerLayout.this.onBannerItemClickListener.onItemClick(i);
                    }
                }
            });
            view = bannerImageView;
        }
        view.setTag(nativeImageDataItem);
        return view;
    }

    private TextView getIndicatorTextView() {
        TextView textView = new TextView(getContext());
        this.indicatorContainer.addView(textView);
        textView.setGravity(17);
        textView.setTextColor(-1);
        textView.setPadding(10, 5, 10, 5);
        textView.setTextSize(15.0f);
        textView.setWidth(((int) textView.getPaint().measureText(this.itemCount + "/" + this.itemCount)) + 40);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor(TitleNViewUtil.TRANSPARENT_BUTTON_BACKGROUND_COLOR));
        gradientDrawable.setCornerRadius(45.0f);
        textView.setBackgroundDrawable(gradientDrawable);
        return textView;
    }

    private void init(boolean z, boolean z2) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        int i = AnonymousClass6.$SwitchMap$io$dcloud$feature$nativeObj$BannerLayout$Shape[this.indicatorShape.ordinal()];
        if (i == 1) {
            gradientDrawable.setShape(0);
            gradientDrawable2.setShape(0);
            gradientDrawable3.setShape(0);
        } else if (i == 2) {
            gradientDrawable.setShape(1);
            gradientDrawable2.setShape(1);
            gradientDrawable3.setShape(1);
        }
        gradientDrawable3.setSize(this.selectedIndicatorWidth, this.selectedIndicatorHeight);
        gradientDrawable3.setColor(this.ringIndicatorColor);
        gradientDrawable.setColor(this.unSelectedIndicatorColor);
        gradientDrawable.setSize(this.unSelectedIndicatorWidth, this.unSelectedIndicatorHeight);
        this.unSelectedDrawable = new LayerDrawable(new Drawable[]{gradientDrawable});
        gradientDrawable2.setColor(this.selectedIndicatorColor);
        gradientDrawable2.setSize(this.selectedIndicatorWidth, this.selectedIndicatorHeight);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gradientDrawable3, gradientDrawable2});
        layerDrawable.setLayerInset(0, 0, 0, 0, 0);
        layerDrawable.setLayerInset(1, 2, 2, 2, 2);
        this.selectedDrawable = layerDrawable;
        this.isImagePhoto = z2;
        this.isImageLoop = z;
        int i2 = (int) (getResources().getDisplayMetrics().density * 5.0f);
        this.selectedIndicatorHeight = i2;
        this.selectedIndicatorWidth = i2;
        this.unSelectedIndicatorHeight = i2;
        this.unSelectedIndicatorWidth = i2;
    }

    private void setViews(List<View> list, int i) {
        ViewPager viewPager = this.pager;
        if (viewPager != null) {
            LoopPagerAdapter loopPagerAdapter = (LoopPagerAdapter) viewPager.getAdapter();
            if (loopPagerAdapter.getViews().size() <= 1 || list.size() != 1) {
                loopPagerAdapter.notifyItemsView(list);
            } else {
                removeAllViews();
                this.pager = null;
            }
        }
        if (this.pager == null) {
            ViewPager bounceBackViewPager = this.isImagePhoto ? new BounceBackViewPager(getContext()) : new ViewPager(getContext());
            this.pager = bounceBackViewPager;
            addView(bounceBackViewPager);
            this.pager.setAdapter(new LoopPagerAdapter(list));
        }
        setSliderTransformDuration(this.scrollDuration);
        initIndicatiorContainer();
        if (!this.isImageLoop || list.size() <= 1) {
            int i2 = i + 0;
            this.currentPosition = i2;
            this.pager.setCurrentItem(i2);
            switchIndicator(this.currentPosition);
        } else {
            int i3 = this.MAX_VALUE / 2;
            int i4 = (i3 - (i3 % this.itemCount)) + i;
            this.pager.setCurrentItem(i4);
            int i5 = i4 % this.itemCount;
            this.currentPosition = i5;
            switchIndicator(i5);
        }
        this.pager.setOnPageChangeListener(new ViewPager.l() { // from class: io.dcloud.feature.nativeObj.BannerLayout.5
            @Override // androidx.viewpager.widget.ViewPager.l, androidx.viewpager.widget.ViewPager.i
            public void onPageScrollStateChanged(int i6) {
                super.onPageScrollStateChanged(i6);
            }

            @Override // androidx.viewpager.widget.ViewPager.l, androidx.viewpager.widget.ViewPager.i
            public void onPageScrolled(int i6, float f, int i7) {
                super.onPageScrolled(i6, f, i7);
            }

            @Override // androidx.viewpager.widget.ViewPager.l, androidx.viewpager.widget.ViewPager.i
            public void onPageSelected(int i6) {
                View view;
                BannerLayout bannerLayout = BannerLayout.this;
                if (bannerLayout.isImageLoop) {
                    i6 %= BannerLayout.this.itemCount;
                }
                bannerLayout.currentPosition = i6;
                if (!BannerLayout.this.isAllowImageDownload && BannerLayout.this.imageLoader != null && (view = ((LoopPagerAdapter) BannerLayout.this.pager.getAdapter()).getViews().get(BannerLayout.this.currentPosition)) != null) {
                    BannerLayout.this.imageLoader.displayImage(BannerLayout.this.getContext(), ((NativeImageDataItem) view.getTag()).getUrl(), view, BannerLayout.this.currentPosition);
                }
                BannerLayout bannerLayout2 = BannerLayout.this;
                bannerLayout2.switchIndicator(bannerLayout2.currentPosition);
            }
        });
        if (this.isAutoPlay) {
            startAutoPlay();
        }
    }

    private void startAutoPlay() {
        stopAutoPlay();
        if (this.isAutoPlay) {
            this.handler.sendEmptyMessageDelayed(this.WHAT_AUTO_PLAY, this.autoPlayDuration);
        }
    }

    private void stopAutoPlay() {
        ViewPager viewPager = this.pager;
        if (viewPager != null) {
            viewPager.setCurrentItem(viewPager.getCurrentItem(), false);
        }
        if (this.isAutoPlay) {
            this.handler.removeMessages(this.WHAT_AUTO_PLAY);
            ViewPager viewPager2 = this.pager;
            if (viewPager2 != null) {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem(), false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchIndicator(int i) {
        int i2 = 0;
        if (!this.mIndicatorType.equals("number")) {
            if (this.mIndicatorType.equals("default")) {
                while (i2 < this.indicatorContainer.getChildCount()) {
                    ((ImageView) this.indicatorContainer.getChildAt(i2)).setImageDrawable(i2 == i ? this.selectedDrawable : this.unSelectedDrawable);
                    i2++;
                }
                return;
            }
            return;
        }
        View childAt = this.indicatorContainer.getChildAt(0);
        if (childAt instanceof TextView) {
            ((TextView) childAt).setText((i + 1) + "/" + this.itemCount);
        }
    }

    public void addViewUrls(ArrayList<NativeImageDataItem> arrayList, int i) {
        if (this.pager == null) {
            setViewUrls(arrayList, i);
            return;
        }
        ArrayList<NativeImageDataItem> arrayList2 = this.mUrls;
        if (arrayList2 != null) {
            arrayList2.addAll(arrayList);
        } else {
            this.mUrls = arrayList;
        }
        ArrayList arrayList3 = new ArrayList();
        this.itemCount = this.mUrls.size();
        for (int i2 = 0; i2 < this.mUrls.size(); i2++) {
            arrayList3.add(getImageView(this.mUrls.get(i2), i2));
        }
        initIndicatiorContainer();
        if (this.isImageLoop) {
            int i3 = this.MAX_VALUE / 2;
            int i4 = (i3 - (i3 % this.itemCount)) + i;
            this.pager.setCurrentItem(i4);
            int i5 = this.itemCount;
            int i6 = i4 % i5;
            this.currentPosition = i6;
            switchIndicator(i6 % i5);
        } else {
            int i7 = i + 0;
            this.currentPosition = i7;
            this.pager.setCurrentItem(i7);
            switchIndicator(this.currentPosition);
        }
        ((LoopPagerAdapter) this.pager.getAdapter()).notifyItemsView(arrayList3);
    }

    public void clearBannerData() {
        ViewPager viewPager = this.pager;
        if (viewPager != null) {
            viewPager.setAdapter(null);
            this.pager.removeAllViews();
            this.pager = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            stopAutoPlay();
        } else if (action == 1 || action == 3) {
            startAutoPlay();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    public ViewPager getPager() {
        ViewPager viewPager = this.pager;
        if (viewPager != null) {
            return viewPager;
        }
        return null;
    }

    public ArrayList<NativeImageDataItem> getUrls() {
        return this.mUrls;
    }

    public void initIndicatiorContainer() {
        View view = this.indicatorContainer;
        if (view != null) {
            removeView(view);
        }
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.indicatorContainer = linearLayout;
        linearLayout.setGravity(16);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        switch (AnonymousClass6.$SwitchMap$io$dcloud$feature$nativeObj$BannerLayout$Position[this.indicatorPosition.ordinal()]) {
            case 1:
                layoutParams.addRule(14);
                layoutParams.addRule(12);
                break;
            case 2:
                layoutParams.addRule(14);
                layoutParams.addRule(10);
                break;
            case 3:
                layoutParams.addRule(9);
                layoutParams.addRule(12);
                break;
            case 4:
                layoutParams.addRule(9);
                layoutParams.addRule(10);
                break;
            case 5:
                layoutParams.addRule(11);
                layoutParams.addRule(12);
                break;
            case 6:
                layoutParams.addRule(11);
                layoutParams.addRule(10);
                break;
            case 7:
                layoutParams = null;
                break;
        }
        if (layoutParams != null) {
            int i = this.indicatorMargin;
            int statusHeight = (i / 2) + DeviceInfo.getStatusHeight(getContext());
            int i2 = this.indicatorMargin;
            layoutParams.setMargins(i, statusHeight, i2, i2);
            addView(this.indicatorContainer, layoutParams);
            if (this.mIndicatorType.equals("number")) {
                getIndicatorTextView().setText("1/" + this.itemCount);
            } else {
                for (int i3 = 0; i3 < this.itemCount; i3++) {
                    ImageView imageView = new ImageView(getContext());
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
                    int i4 = this.indicatorSpace;
                    imageView.setPadding(i4, i4, i4, i4);
                    imageView.setImageDrawable(this.unSelectedDrawable);
                    this.indicatorContainer.addView(imageView);
                }
            }
            if (this.itemCount == 1) {
                this.indicatorContainer.setVisibility(4);
            } else {
                this.indicatorContainer.setVisibility(0);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAutoPlay();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAutoPlay();
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.currentPosition = savedState.currentPosition;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.currentPosition = this.currentPosition;
        return savedState;
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            startAutoPlay();
        } else {
            stopAutoPlay();
        }
    }

    public void setAllowImageDownload(boolean z, boolean z2) {
        ViewPager viewPager;
        this.isAllowImageDownload = z;
        if (!z2 || (viewPager = this.pager) == null || viewPager.getAdapter() == null) {
            return;
        }
        this.pager.getAdapter().notifyDataSetChanged();
    }

    public void setAutoPlay(boolean z, int i) {
        this.isAutoPlay = z;
        this.autoPlayDuration = i;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public void setImageLoop(Boolean bool) {
        this.isImageLoop = bool.booleanValue();
    }

    public void setIndicatorContainerData(Position position, int i, int i2, int i3, String str) {
        if (position != null) {
            this.indicatorPosition = position;
        }
        this.indicatorMargin = i;
        this.indicatorSpace = i2;
        this.selectedIndicatorHeight = i3;
        this.selectedIndicatorWidth = i3;
        this.unSelectedIndicatorHeight = i3;
        this.unSelectedIndicatorWidth = i3;
        if (!TextUtils.isEmpty(str)) {
            this.mIndicatorType = str;
        }
        if (this.mIndicatorType.equals("default")) {
            this.indicatorPosition = Position.centerBottom;
        } else if (this.mIndicatorType.equals("number")) {
            this.indicatorPosition = Position.centerTop;
        } else if (this.mIndicatorType.equals("none")) {
            this.indicatorPosition = Position.none;
        }
    }

    public void setIndicatorType(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mIndicatorType = str;
        }
        if (this.mIndicatorType.equals("default")) {
            this.indicatorPosition = Position.centerBottom;
        } else if (this.mIndicatorType.equals("number")) {
            this.indicatorPosition = Position.centerTop;
        } else if (this.mIndicatorType.equals("none")) {
            this.indicatorPosition = Position.none;
        }
    }

    public void setOnBannerItemClickListener(OnBannerItemClickListener onBannerItemClickListener) {
        this.onBannerItemClickListener = onBannerItemClickListener;
    }

    public void setScrollDuration(int i) {
        this.scrollDuration = i;
    }

    public void setSliderTransformDuration(int i) {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(this.pager, new FixedSpeedScroller(this, this.pager.getContext(), null, i));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setViewUrls(ArrayList<NativeImageDataItem> arrayList, int i) {
        ArrayList<NativeImageDataItem> arrayList2 = this.mUrls;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
        this.mUrls = arrayList;
        ArrayList arrayList3 = new ArrayList();
        int size = arrayList.size();
        this.itemCount = size;
        if (size < 1) {
            throw new IllegalStateException("item count not equal zero");
        }
        if (size == 2) {
            if (this.isImageLoop) {
                arrayList3.add(getImageView(arrayList.get(0), 0));
                arrayList3.add(getImageView(arrayList.get(1), 1));
            }
            arrayList3.add(getImageView(arrayList.get(0), 0));
            arrayList3.add(getImageView(arrayList.get(1), 1));
        } else {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                arrayList3.add(getImageView(arrayList.get(i2), i2));
            }
        }
        setViews(arrayList3, i);
    }

    public void setmIndicatorType(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mIndicatorType = str;
    }

    public BannerLayout(Context context, AttributeSet attributeSet, boolean z, boolean z2) {
        this(context, attributeSet, 0, z, z2);
    }

    public BannerLayout(Context context, AttributeSet attributeSet, int i, boolean z, boolean z2) {
        super(context, attributeSet, i);
        this.mIndicatorType = "default";
        this.WHAT_AUTO_PLAY = 1000;
        this.isAutoPlay = false;
        this.selectedIndicatorColor = -1;
        this.unSelectedIndicatorColor = -5592406;
        this.ringIndicatorColor = -5592406;
        this.indicatorShape = Shape.oval;
        this.selectedIndicatorHeight = 15;
        this.selectedIndicatorWidth = 15;
        this.unSelectedIndicatorHeight = 15;
        this.unSelectedIndicatorWidth = 15;
        this.indicatorPosition = Position.centerBottom;
        this.autoPlayDuration = AndroidPlatform.MAX_LOG_LENGTH;
        this.scrollDuration = 900;
        this.indicatorSpace = 3;
        this.indicatorMargin = 10;
        this.isAllowImageDownload = true;
        this.isImagePhoto = false;
        this.isImageLoop = false;
        this.MAX_VALUE = 150;
        this.handler = new Handler(new Handler.Callback() { // from class: io.dcloud.feature.nativeObj.BannerLayout.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (message.what == BannerLayout.this.WHAT_AUTO_PLAY && BannerLayout.this.pager != null && BannerLayout.this.isAutoPlay && BannerLayout.this.mUrls != null && BannerLayout.this.mUrls.size() > 1) {
                    if (BannerLayout.this.isImageLoop) {
                        BannerLayout.this.pager.setCurrentItem(BannerLayout.this.pager.getCurrentItem() + 1, true);
                    } else {
                        int currentItem = BannerLayout.this.pager.getCurrentItem() + 1;
                        if (currentItem >= BannerLayout.this.mUrls.size()) {
                            return false;
                        }
                        BannerLayout.this.pager.setCurrentItem(currentItem, true);
                    }
                    BannerLayout.this.handler.sendEmptyMessageDelayed(BannerLayout.this.WHAT_AUTO_PLAY, BannerLayout.this.autoPlayDuration);
                }
                return false;
            }
        });
        SubsamplingScaleImageView.setPreferredBitmapConfig(Bitmap.Config.ARGB_8888);
        init(z, z2);
    }
}
