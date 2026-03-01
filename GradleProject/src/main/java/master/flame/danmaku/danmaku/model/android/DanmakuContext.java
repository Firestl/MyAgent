package master.flame.danmaku.danmaku.model.android;

import android.graphics.Typeface;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import master.flame.danmaku.controller.DanmakuFilters;
import master.flame.danmaku.danmaku.model.AbsDanmakuSync;
import master.flame.danmaku.danmaku.model.AbsDisplayer;
import master.flame.danmaku.danmaku.model.AlphaValue;
import master.flame.danmaku.danmaku.model.GlobalFlagValues;
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer;

/* JADX INFO: loaded from: classes3.dex */
public class DanmakuContext {
    public AbsDanmakuSync danmakuSync;
    public BaseCacheStuffer mCacheStuffer;
    public List<WeakReference<ConfigChangedCallback>> mCallbackList;
    public boolean mIsMaxLinesLimited;
    public boolean mIsPreventOverlappingEnabled;
    public Typeface mFont = null;
    public int transparency = AlphaValue.MAX;
    public float scaleTextSize = 1.0f;
    public boolean FTDanmakuVisibility = true;
    public boolean FBDanmakuVisibility = true;
    public boolean L2RDanmakuVisibility = true;
    public boolean R2LDanmakuVisibility = true;
    public boolean SpecialDanmakuVisibility = true;
    public List<Integer> mFilterTypes = new ArrayList();
    public int maximumNumsInScreen = -1;
    public float scrollSpeedFactor = 1.0f;
    public int refreshRateMS = 15;
    public BorderType shadowType = BorderType.SHADOW;
    public int shadowRadius = 3;
    public List<Integer> mColorValueWhiteList = new ArrayList();
    public List<Integer> mUserIdBlackList = new ArrayList();
    public List<String> mUserHashBlackList = new ArrayList();
    public boolean mBlockGuestDanmaku = false;
    public boolean mDuplicateMergingEnable = false;
    public boolean mIsAlignBottom = false;
    public final AbsDisplayer mDisplayer = new AndroidDisplayer();
    public final GlobalFlagValues mGlobalFlagValues = new GlobalFlagValues();
    public final DanmakuFilters mDanmakuFilters = new DanmakuFilters();
    public final DanmakuFactory mDanmakuFactory = DanmakuFactory.create();

    public enum BorderType {
        NONE,
        SHADOW,
        STROKEN
    }

    public interface ConfigChangedCallback {
        boolean onDanmakuConfigChanged(DanmakuContext danmakuContext, DanmakuConfigTag danmakuConfigTag, Object... objArr);
    }

    public enum DanmakuConfigTag {
        FT_DANMAKU_VISIBILITY,
        FB_DANMAKU_VISIBILITY,
        L2R_DANMAKU_VISIBILITY,
        R2L_DANMAKU_VISIBILIY,
        SPECIAL_DANMAKU_VISIBILITY,
        TYPEFACE,
        TRANSPARENCY,
        SCALE_TEXTSIZE,
        MAXIMUM_NUMS_IN_SCREEN,
        DANMAKU_STYLE,
        DANMAKU_BOLD,
        COLOR_VALUE_WHITE_LIST,
        USER_ID_BLACK_LIST,
        USER_HASH_BLACK_LIST,
        SCROLL_SPEED_FACTOR,
        BLOCK_GUEST_DANMAKU,
        DUPLICATE_MERGING_ENABLED,
        MAXIMUN_LINES,
        OVERLAPPING_ENABLE,
        ALIGN_BOTTOM;

        public boolean isVisibilityRelatedTag() {
            return equals(FT_DANMAKU_VISIBILITY) || equals(FB_DANMAKU_VISIBILITY) || equals(L2R_DANMAKU_VISIBILITY) || equals(R2L_DANMAKU_VISIBILIY) || equals(SPECIAL_DANMAKU_VISIBILITY) || equals(COLOR_VALUE_WHITE_LIST) || equals(USER_ID_BLACK_LIST);
        }
    }

    public static DanmakuContext create() {
        return new DanmakuContext();
    }

    private void notifyConfigureChanged(DanmakuConfigTag danmakuConfigTag, Object... objArr) {
        List<WeakReference<ConfigChangedCallback>> list = this.mCallbackList;
        if (list != null) {
            Iterator<WeakReference<ConfigChangedCallback>> it = list.iterator();
            while (it.hasNext()) {
                ConfigChangedCallback configChangedCallback = it.next().get();
                if (configChangedCallback != null) {
                    configChangedCallback.onDanmakuConfigChanged(this, danmakuConfigTag, objArr);
                }
            }
        }
    }

    private void setDanmakuVisible(boolean z, int i) {
        if (z) {
            this.mFilterTypes.remove(Integer.valueOf(i));
        } else {
            if (this.mFilterTypes.contains(Integer.valueOf(i))) {
                return;
            }
            this.mFilterTypes.add(Integer.valueOf(i));
        }
    }

    private <T> void setFilterData(String str, T t) {
        setFilterData(str, t, true);
    }

    public DanmakuContext addUserHashBlackList(String... strArr) {
        if (strArr != null && strArr.length != 0) {
            Collections.addAll(this.mUserHashBlackList, strArr);
            setFilterData(DanmakuFilters.TAG_USER_HASH_FILTER, this.mUserHashBlackList);
            this.mGlobalFlagValues.updateFilterFlag();
            notifyConfigureChanged(DanmakuConfigTag.USER_HASH_BLACK_LIST, this.mUserHashBlackList);
        }
        return this;
    }

    public DanmakuContext addUserIdBlackList(Integer... numArr) {
        if (numArr != null && numArr.length != 0) {
            Collections.addAll(this.mUserIdBlackList, numArr);
            setFilterData(DanmakuFilters.TAG_USER_ID_FILTER, this.mUserIdBlackList);
            this.mGlobalFlagValues.updateFilterFlag();
            notifyConfigureChanged(DanmakuConfigTag.USER_ID_BLACK_LIST, this.mUserIdBlackList);
        }
        return this;
    }

    public DanmakuContext alignBottom(boolean z) {
        if (this.mIsAlignBottom != z) {
            this.mIsAlignBottom = z;
            notifyConfigureChanged(DanmakuConfigTag.ALIGN_BOTTOM, Boolean.valueOf(z));
            this.mGlobalFlagValues.updateVisibleFlag();
        }
        return this;
    }

    public DanmakuContext blockGuestDanmaku(boolean z) {
        if (this.mBlockGuestDanmaku != z) {
            this.mBlockGuestDanmaku = z;
            if (z) {
                setFilterData(DanmakuFilters.TAG_GUEST_FILTER, Boolean.valueOf(z));
            } else {
                this.mDanmakuFilters.unregisterFilter(DanmakuFilters.TAG_GUEST_FILTER);
            }
            this.mGlobalFlagValues.updateFilterFlag();
            notifyConfigureChanged(DanmakuConfigTag.BLOCK_GUEST_DANMAKU, Boolean.valueOf(z));
        }
        return this;
    }

    public List<Integer> getColorValueWhiteList() {
        return this.mColorValueWhiteList;
    }

    public AbsDisplayer getDisplayer() {
        return this.mDisplayer;
    }

    public boolean getFBDanmakuVisibility() {
        return this.FBDanmakuVisibility;
    }

    public boolean getFTDanmakuVisibility() {
        return this.FTDanmakuVisibility;
    }

    public boolean getL2RDanmakuVisibility() {
        return this.L2RDanmakuVisibility;
    }

    public boolean getR2LDanmakuVisibility() {
        return this.R2LDanmakuVisibility;
    }

    public boolean getSpecialDanmakuVisibility() {
        return this.SpecialDanmakuVisibility;
    }

    public List<String> getUserHashBlackList() {
        return this.mUserHashBlackList;
    }

    public List<Integer> getUserIdBlackList() {
        return this.mUserIdBlackList;
    }

    public boolean isAlignBottom() {
        return this.mIsAlignBottom;
    }

    public boolean isDuplicateMergingEnabled() {
        return this.mDuplicateMergingEnable;
    }

    public boolean isMaxLinesLimited() {
        return this.mIsMaxLinesLimited;
    }

    public boolean isPreventOverlappingEnabled() {
        return this.mIsPreventOverlappingEnabled;
    }

    public DanmakuContext preventOverlapping(Map<Integer, Boolean> map) {
        this.mIsPreventOverlappingEnabled = map != null;
        if (map == null) {
            this.mDanmakuFilters.unregisterFilter(DanmakuFilters.TAG_OVERLAPPING_FILTER, false);
        } else {
            setFilterData(DanmakuFilters.TAG_OVERLAPPING_FILTER, map, false);
        }
        this.mGlobalFlagValues.updateFilterFlag();
        notifyConfigureChanged(DanmakuConfigTag.OVERLAPPING_ENABLE, map);
        return this;
    }

    public void registerConfigChangedCallback(ConfigChangedCallback configChangedCallback) {
        if (configChangedCallback == null || this.mCallbackList == null) {
            this.mCallbackList = Collections.synchronizedList(new ArrayList());
        }
        Iterator<WeakReference<ConfigChangedCallback>> it = this.mCallbackList.iterator();
        while (it.hasNext()) {
            if (configChangedCallback.equals(it.next().get())) {
                return;
            }
        }
        this.mCallbackList.add(new WeakReference<>(configChangedCallback));
    }

    public DanmakuContext removeUserHashBlackList(String... strArr) {
        if (strArr != null && strArr.length != 0) {
            for (String str : strArr) {
                this.mUserHashBlackList.remove(str);
            }
            setFilterData(DanmakuFilters.TAG_USER_HASH_FILTER, this.mUserHashBlackList);
            this.mGlobalFlagValues.updateFilterFlag();
            notifyConfigureChanged(DanmakuConfigTag.USER_HASH_BLACK_LIST, this.mUserHashBlackList);
        }
        return this;
    }

    public DanmakuContext removeUserIdBlackList(Integer... numArr) {
        if (numArr != null && numArr.length != 0) {
            for (Integer num : numArr) {
                this.mUserIdBlackList.remove(num);
            }
            setFilterData(DanmakuFilters.TAG_USER_ID_FILTER, this.mUserIdBlackList);
            this.mGlobalFlagValues.updateFilterFlag();
            notifyConfigureChanged(DanmakuConfigTag.USER_ID_BLACK_LIST, this.mUserIdBlackList);
        }
        return this;
    }

    public DanmakuContext setCacheStuffer(BaseCacheStuffer baseCacheStuffer, BaseCacheStuffer.Proxy proxy) {
        this.mCacheStuffer = baseCacheStuffer;
        if (baseCacheStuffer != null) {
            baseCacheStuffer.setProxy(proxy);
            this.mDisplayer.setCacheStuffer(this.mCacheStuffer);
        }
        return this;
    }

    public DanmakuContext setColorValueWhiteList(Integer... numArr) {
        this.mColorValueWhiteList.clear();
        if (numArr == null || numArr.length == 0) {
            this.mDanmakuFilters.unregisterFilter(DanmakuFilters.TAG_TEXT_COLOR_DANMAKU_FILTER);
        } else {
            Collections.addAll(this.mColorValueWhiteList, numArr);
            setFilterData(DanmakuFilters.TAG_TEXT_COLOR_DANMAKU_FILTER, this.mColorValueWhiteList);
        }
        this.mGlobalFlagValues.updateFilterFlag();
        notifyConfigureChanged(DanmakuConfigTag.COLOR_VALUE_WHITE_LIST, this.mColorValueWhiteList);
        return this;
    }

    public DanmakuContext setDanmakuBold(boolean z) {
        this.mDisplayer.setFakeBoldText(z);
        notifyConfigureChanged(DanmakuConfigTag.DANMAKU_BOLD, Boolean.valueOf(z));
        return this;
    }

    public DanmakuContext setDanmakuStyle(int i, float... fArr) {
        this.mDisplayer.setDanmakuStyle(i, fArr);
        notifyConfigureChanged(DanmakuConfigTag.DANMAKU_STYLE, Integer.valueOf(i), fArr);
        return this;
    }

    public DanmakuContext setDanmakuSync(AbsDanmakuSync absDanmakuSync) {
        this.danmakuSync = absDanmakuSync;
        return this;
    }

    public DanmakuContext setDanmakuTransparency(float f) {
        int i = (int) (AlphaValue.MAX * f);
        if (i != this.transparency) {
            this.transparency = i;
            this.mDisplayer.setTransparency(i);
            notifyConfigureChanged(DanmakuConfigTag.TRANSPARENCY, Float.valueOf(f));
        }
        return this;
    }

    public DanmakuContext setDuplicateMergingEnabled(boolean z) {
        if (this.mDuplicateMergingEnable != z) {
            this.mDuplicateMergingEnable = z;
            this.mGlobalFlagValues.updateFilterFlag();
            notifyConfigureChanged(DanmakuConfigTag.DUPLICATE_MERGING_ENABLED, Boolean.valueOf(z));
        }
        return this;
    }

    public DanmakuContext setFBDanmakuVisibility(boolean z) {
        setDanmakuVisible(z, 4);
        setFilterData(DanmakuFilters.TAG_TYPE_DANMAKU_FILTER, this.mFilterTypes);
        this.mGlobalFlagValues.updateFilterFlag();
        if (this.FBDanmakuVisibility != z) {
            this.FBDanmakuVisibility = z;
            notifyConfigureChanged(DanmakuConfigTag.FB_DANMAKU_VISIBILITY, Boolean.valueOf(z));
        }
        return this;
    }

    public DanmakuContext setFTDanmakuVisibility(boolean z) {
        setDanmakuVisible(z, 5);
        setFilterData(DanmakuFilters.TAG_TYPE_DANMAKU_FILTER, this.mFilterTypes);
        this.mGlobalFlagValues.updateFilterFlag();
        if (this.FTDanmakuVisibility != z) {
            this.FTDanmakuVisibility = z;
            notifyConfigureChanged(DanmakuConfigTag.FT_DANMAKU_VISIBILITY, Boolean.valueOf(z));
        }
        return this;
    }

    public DanmakuContext setL2RDanmakuVisibility(boolean z) {
        setDanmakuVisible(z, 6);
        setFilterData(DanmakuFilters.TAG_TYPE_DANMAKU_FILTER, this.mFilterTypes);
        this.mGlobalFlagValues.updateFilterFlag();
        if (this.L2RDanmakuVisibility != z) {
            this.L2RDanmakuVisibility = z;
            notifyConfigureChanged(DanmakuConfigTag.L2R_DANMAKU_VISIBILITY, Boolean.valueOf(z));
        }
        return this;
    }

    public DanmakuContext setMaximumLines(Map<Integer, Integer> map) {
        this.mIsMaxLinesLimited = map != null;
        if (map == null) {
            this.mDanmakuFilters.unregisterFilter(DanmakuFilters.TAG_MAXIMUN_LINES_FILTER, false);
        } else {
            setFilterData(DanmakuFilters.TAG_MAXIMUN_LINES_FILTER, map, false);
        }
        this.mGlobalFlagValues.updateFilterFlag();
        notifyConfigureChanged(DanmakuConfigTag.MAXIMUN_LINES, map);
        return this;
    }

    public DanmakuContext setMaximumVisibleSizeInScreen(int i) {
        this.maximumNumsInScreen = i;
        if (i == 0) {
            this.mDanmakuFilters.unregisterFilter(DanmakuFilters.TAG_QUANTITY_DANMAKU_FILTER);
            this.mDanmakuFilters.unregisterFilter(DanmakuFilters.TAG_ELAPSED_TIME_FILTER);
            notifyConfigureChanged(DanmakuConfigTag.MAXIMUM_NUMS_IN_SCREEN, Integer.valueOf(i));
            return this;
        }
        if (i == -1) {
            this.mDanmakuFilters.unregisterFilter(DanmakuFilters.TAG_QUANTITY_DANMAKU_FILTER);
            this.mDanmakuFilters.registerFilter(DanmakuFilters.TAG_ELAPSED_TIME_FILTER);
            notifyConfigureChanged(DanmakuConfigTag.MAXIMUM_NUMS_IN_SCREEN, Integer.valueOf(i));
            return this;
        }
        setFilterData(DanmakuFilters.TAG_QUANTITY_DANMAKU_FILTER, Integer.valueOf(i));
        this.mGlobalFlagValues.updateFilterFlag();
        notifyConfigureChanged(DanmakuConfigTag.MAXIMUM_NUMS_IN_SCREEN, Integer.valueOf(i));
        return this;
    }

    @Deprecated
    public DanmakuContext setOverlapping(Map<Integer, Boolean> map) {
        return preventOverlapping(map);
    }

    public DanmakuContext setR2LDanmakuVisibility(boolean z) {
        setDanmakuVisible(z, 1);
        setFilterData(DanmakuFilters.TAG_TYPE_DANMAKU_FILTER, this.mFilterTypes);
        this.mGlobalFlagValues.updateFilterFlag();
        if (this.R2LDanmakuVisibility != z) {
            this.R2LDanmakuVisibility = z;
            notifyConfigureChanged(DanmakuConfigTag.R2L_DANMAKU_VISIBILIY, Boolean.valueOf(z));
        }
        return this;
    }

    public DanmakuContext setScaleTextSize(float f) {
        if (this.scaleTextSize != f) {
            this.scaleTextSize = f;
            this.mDisplayer.clearTextHeightCache();
            this.mDisplayer.setScaleTextSizeFactor(f);
            this.mGlobalFlagValues.updateMeasureFlag();
            this.mGlobalFlagValues.updateVisibleFlag();
            notifyConfigureChanged(DanmakuConfigTag.SCALE_TEXTSIZE, Float.valueOf(f));
        }
        return this;
    }

    public DanmakuContext setScrollSpeedFactor(float f) {
        if (this.scrollSpeedFactor != f) {
            this.scrollSpeedFactor = f;
            this.mDanmakuFactory.updateDurationFactor(f);
            this.mGlobalFlagValues.updateMeasureFlag();
            this.mGlobalFlagValues.updateVisibleFlag();
            notifyConfigureChanged(DanmakuConfigTag.SCROLL_SPEED_FACTOR, Float.valueOf(f));
        }
        return this;
    }

    public DanmakuContext setSpecialDanmakuVisibility(boolean z) {
        setDanmakuVisible(z, 7);
        setFilterData(DanmakuFilters.TAG_TYPE_DANMAKU_FILTER, this.mFilterTypes);
        this.mGlobalFlagValues.updateFilterFlag();
        if (this.SpecialDanmakuVisibility != z) {
            this.SpecialDanmakuVisibility = z;
            notifyConfigureChanged(DanmakuConfigTag.SPECIAL_DANMAKU_VISIBILITY, Boolean.valueOf(z));
        }
        return this;
    }

    public DanmakuContext setTypeface(Typeface typeface) {
        if (this.mFont != typeface) {
            this.mFont = typeface;
            this.mDisplayer.clearTextHeightCache();
            this.mDisplayer.setTypeFace(typeface);
            notifyConfigureChanged(DanmakuConfigTag.TYPEFACE, new Object[0]);
        }
        return this;
    }

    public DanmakuContext setUserHashBlackList(String... strArr) {
        this.mUserHashBlackList.clear();
        if (strArr == null || strArr.length == 0) {
            this.mDanmakuFilters.unregisterFilter(DanmakuFilters.TAG_USER_HASH_FILTER);
        } else {
            Collections.addAll(this.mUserHashBlackList, strArr);
            setFilterData(DanmakuFilters.TAG_USER_HASH_FILTER, this.mUserHashBlackList);
        }
        this.mGlobalFlagValues.updateFilterFlag();
        notifyConfigureChanged(DanmakuConfigTag.USER_HASH_BLACK_LIST, this.mUserHashBlackList);
        return this;
    }

    public DanmakuContext setUserIdBlackList(Integer... numArr) {
        this.mUserIdBlackList.clear();
        if (numArr == null || numArr.length == 0) {
            this.mDanmakuFilters.unregisterFilter(DanmakuFilters.TAG_USER_ID_FILTER);
        } else {
            Collections.addAll(this.mUserIdBlackList, numArr);
            setFilterData(DanmakuFilters.TAG_USER_ID_FILTER, this.mUserIdBlackList);
        }
        this.mGlobalFlagValues.updateFilterFlag();
        notifyConfigureChanged(DanmakuConfigTag.USER_ID_BLACK_LIST, this.mUserIdBlackList);
        return this;
    }

    public void unregisterAllConfigChangedCallbacks() {
        List<WeakReference<ConfigChangedCallback>> list = this.mCallbackList;
        if (list != null) {
            list.clear();
            this.mCallbackList = null;
        }
    }

    public void unregisterConfigChangedCallback(ConfigChangedCallback configChangedCallback) {
        List<WeakReference<ConfigChangedCallback>> list;
        if (configChangedCallback == null || (list = this.mCallbackList) == null) {
            return;
        }
        Iterator<WeakReference<ConfigChangedCallback>> it = list.iterator();
        while (it.hasNext()) {
            if (configChangedCallback.equals(it.next().get())) {
                this.mCallbackList.remove(configChangedCallback);
                return;
            }
        }
    }

    private <T> void setFilterData(String str, T t, boolean z) {
        this.mDanmakuFilters.get(str, z).setData(t);
    }
}
