package master.flame.danmaku.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakuIterator;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.util.SystemClock;

/* JADX INFO: loaded from: classes3.dex */
public class DanmakuFilters {
    public static final int FILTER_TYPE_DUPLICATE_MERGE = 128;
    public static final int FILTER_TYPE_ELAPSED_TIME = 4;
    public static final int FILTER_TYPE_MAXIMUM_LINES = 256;
    public static final int FILTER_TYPE_OVERLAPPING = 512;
    public static final int FILTER_TYPE_TEXTCOLOR = 8;
    public static final int FILTER_TYPE_TYPE = 1;
    public static final int FILTER_TYPE_USER_GUEST = 64;
    public static final int FILTER_TYPE_USER_HASH = 32;
    public static final int FILTER_TYPE_USER_ID = 16;
    public static final int FILYER_TYPE_QUANTITY = 2;
    public static final String TAG_DUPLICATE_FILTER = "1017_Filter";
    public static final String TAG_ELAPSED_TIME_FILTER = "1012_Filter";
    public static final String TAG_GUEST_FILTER = "1016_Filter";
    public static final String TAG_MAXIMUN_LINES_FILTER = "1018_Filter";
    public static final String TAG_OVERLAPPING_FILTER = "1019_Filter";
    public static final String TAG_QUANTITY_DANMAKU_FILTER = "1011_Filter";
    public static final String TAG_TEXT_COLOR_DANMAKU_FILTER = "1013_Filter";
    public static final String TAG_TYPE_DANMAKU_FILTER = "1010_Filter";
    public static final String TAG_USER_HASH_FILTER = "1015_Filter";
    public static final String TAG_USER_ID_FILTER = "1014_Filter";
    public final Exception filterException = new Exception("not suuport this filter tag");
    public final Map<String, IDanmakuFilter<?>> filters = Collections.synchronizedSortedMap(new TreeMap());
    public final Map<String, IDanmakuFilter<?>> filtersSecondary = Collections.synchronizedSortedMap(new TreeMap());
    public IDanmakuFilter<?>[] mFilterArray = new IDanmakuFilter[0];
    public IDanmakuFilter<?>[] mFilterArraySecondary = new IDanmakuFilter[0];

    public static abstract class BaseDanmakuFilter<T> implements IDanmakuFilter<T> {
        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void clear() {
        }
    }

    public static class ElapsedTimeFilter extends BaseDanmakuFilter<Object> {
        public long mMaxTime = 20;

        private synchronized boolean needFilter(BaseDanmaku baseDanmaku, int i, int i2, DanmakuTimer danmakuTimer, boolean z) {
            if (danmakuTimer != null) {
                if (baseDanmaku.isOutside()) {
                    return SystemClock.uptimeMillis() - danmakuTimer.currMillisecond >= this.mMaxTime;
                }
            }
            return false;
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.BaseDanmakuFilter, master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void clear() {
            reset();
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public boolean filter(BaseDanmaku baseDanmaku, int i, int i2, DanmakuTimer danmakuTimer, boolean z, DanmakuContext danmakuContext) {
            boolean zNeedFilter = needFilter(baseDanmaku, i, i2, danmakuTimer, z);
            if (zNeedFilter) {
                baseDanmaku.mFilterParam |= 4;
            }
            return zNeedFilter;
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public synchronized void reset() {
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void setData(Object obj) {
            reset();
        }
    }

    public static class GuestFilter extends BaseDanmakuFilter<Boolean> {
        public Boolean mBlock = false;

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public boolean filter(BaseDanmaku baseDanmaku, int i, int i2, DanmakuTimer danmakuTimer, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = this.mBlock.booleanValue() && baseDanmaku.isGuest;
            if (z2) {
                baseDanmaku.mFilterParam |= 64;
            }
            return z2;
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void reset() {
            this.mBlock = false;
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void setData(Boolean bool) {
            this.mBlock = bool;
        }
    }

    public interface IDanmakuFilter<T> {
        void clear();

        boolean filter(BaseDanmaku baseDanmaku, int i, int i2, DanmakuTimer danmakuTimer, boolean z, DanmakuContext danmakuContext);

        void reset();

        void setData(T t);
    }

    public static class MaximumLinesFilter extends BaseDanmakuFilter<Map<Integer, Integer>> {
        public Map<Integer, Integer> mMaximumLinesPairs;

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public boolean filter(BaseDanmaku baseDanmaku, int i, int i2, DanmakuTimer danmakuTimer, boolean z, DanmakuContext danmakuContext) {
            Map<Integer, Integer> map = this.mMaximumLinesPairs;
            boolean z2 = false;
            if (map != null) {
                Integer num = map.get(Integer.valueOf(baseDanmaku.getType()));
                if (num != null && i >= num.intValue()) {
                    z2 = true;
                }
                if (z2) {
                    baseDanmaku.mFilterParam |= 256;
                }
            }
            return z2;
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void reset() {
            this.mMaximumLinesPairs = null;
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void setData(Map<Integer, Integer> map) {
            this.mMaximumLinesPairs = map;
        }
    }

    public static class OverlappingFilter extends BaseDanmakuFilter<Map<Integer, Boolean>> {
        public Map<Integer, Boolean> mEnabledPairs;

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public boolean filter(BaseDanmaku baseDanmaku, int i, int i2, DanmakuTimer danmakuTimer, boolean z, DanmakuContext danmakuContext) {
            Map<Integer, Boolean> map = this.mEnabledPairs;
            boolean z2 = false;
            if (map != null) {
                Boolean bool = map.get(Integer.valueOf(baseDanmaku.getType()));
                if (bool != null && bool.booleanValue() && z) {
                    z2 = true;
                }
                if (z2) {
                    baseDanmaku.mFilterParam |= 512;
                }
            }
            return z2;
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void reset() {
            this.mEnabledPairs = null;
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void setData(Map<Integer, Boolean> map) {
            this.mEnabledPairs = map;
        }
    }

    public static class QuantityDanmakuFilter extends BaseDanmakuFilter<Integer> {
        public int mMaximumSize = -1;
        public BaseDanmaku mLastSkipped = null;
        public float mFilterFactor = 1.0f;

        private boolean needFilter(BaseDanmaku baseDanmaku, int i, int i2, DanmakuTimer danmakuTimer, boolean z, DanmakuContext danmakuContext) {
            if (this.mMaximumSize > 0 && baseDanmaku.getType() == 1) {
                BaseDanmaku baseDanmaku2 = this.mLastSkipped;
                if (baseDanmaku2 != null && !baseDanmaku2.isTimeOut()) {
                    long actualTime = baseDanmaku.getActualTime() - this.mLastSkipped.getActualTime();
                    if ((actualTime >= 0 && actualTime < danmakuContext.mDanmakuFactory.MAX_Duration_Scroll_Danmaku.value * this.mFilterFactor) || i > this.mMaximumSize) {
                        return true;
                    }
                    this.mLastSkipped = baseDanmaku;
                    return false;
                }
                this.mLastSkipped = baseDanmaku;
            }
            return false;
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.BaseDanmakuFilter, master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void clear() {
            reset();
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public synchronized boolean filter(BaseDanmaku baseDanmaku, int i, int i2, DanmakuTimer danmakuTimer, boolean z, DanmakuContext danmakuContext) {
            boolean zNeedFilter;
            zNeedFilter = needFilter(baseDanmaku, i, i2, danmakuTimer, z, danmakuContext);
            if (zNeedFilter) {
                baseDanmaku.mFilterParam |= 2;
            }
            return zNeedFilter;
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public synchronized void reset() {
            this.mLastSkipped = null;
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void setData(Integer num) {
            reset();
            if (num == null || num.intValue() == this.mMaximumSize) {
                return;
            }
            int iIntValue = num.intValue() + (num.intValue() / 5);
            this.mMaximumSize = iIntValue;
            this.mFilterFactor = 1.0f / iIntValue;
        }
    }

    public static class TextColorFilter extends BaseDanmakuFilter<List<Integer>> {
        public List<Integer> mWhiteList = new ArrayList();

        private void addToWhiteList(Integer num) {
            if (this.mWhiteList.contains(num)) {
                return;
            }
            this.mWhiteList.add(num);
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public boolean filter(BaseDanmaku baseDanmaku, int i, int i2, DanmakuTimer danmakuTimer, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = (baseDanmaku == null || this.mWhiteList.contains(Integer.valueOf(baseDanmaku.textColor))) ? false : true;
            if (z2) {
                baseDanmaku.mFilterParam |= 8;
            }
            return z2;
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void reset() {
            this.mWhiteList.clear();
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void setData(List<Integer> list) {
            reset();
            if (list != null) {
                Iterator<Integer> it = list.iterator();
                while (it.hasNext()) {
                    addToWhiteList(it.next());
                }
            }
        }
    }

    public static class TypeDanmakuFilter extends BaseDanmakuFilter<List<Integer>> {
        public final List<Integer> mFilterTypes = Collections.synchronizedList(new ArrayList());

        public void disableType(Integer num) {
            if (this.mFilterTypes.contains(num)) {
                this.mFilterTypes.remove(num);
            }
        }

        public void enableType(Integer num) {
            if (this.mFilterTypes.contains(num)) {
                return;
            }
            this.mFilterTypes.add(num);
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public boolean filter(BaseDanmaku baseDanmaku, int i, int i2, DanmakuTimer danmakuTimer, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = baseDanmaku != null && this.mFilterTypes.contains(Integer.valueOf(baseDanmaku.getType()));
            if (z2) {
                baseDanmaku.mFilterParam = 1 | baseDanmaku.mFilterParam;
            }
            return z2;
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void reset() {
            this.mFilterTypes.clear();
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void setData(List<Integer> list) {
            reset();
            if (list != null) {
                Iterator<Integer> it = list.iterator();
                while (it.hasNext()) {
                    enableType(it.next());
                }
            }
        }
    }

    public static abstract class UserFilter<T> extends BaseDanmakuFilter<List<T>> {
        public List<T> mBlackList = new ArrayList();

        private void addToBlackList(T t) {
            if (this.mBlackList.contains(t)) {
                return;
            }
            this.mBlackList.add(t);
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public abstract boolean filter(BaseDanmaku baseDanmaku, int i, int i2, DanmakuTimer danmakuTimer, boolean z, DanmakuContext danmakuContext);

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void reset() {
            this.mBlackList.clear();
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void setData(List<T> list) {
            reset();
            if (list != null) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    addToBlackList(it.next());
                }
            }
        }
    }

    public static class UserHashFilter extends UserFilter<String> {
        @Override // master.flame.danmaku.controller.DanmakuFilters.UserFilter, master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public boolean filter(BaseDanmaku baseDanmaku, int i, int i2, DanmakuTimer danmakuTimer, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = baseDanmaku != null && this.mBlackList.contains(baseDanmaku.userHash);
            if (z2) {
                baseDanmaku.mFilterParam |= 32;
            }
            return z2;
        }
    }

    public static class UserIdFilter extends UserFilter<Integer> {
        @Override // master.flame.danmaku.controller.DanmakuFilters.UserFilter, master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public boolean filter(BaseDanmaku baseDanmaku, int i, int i2, DanmakuTimer danmakuTimer, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = baseDanmaku != null && this.mBlackList.contains(Integer.valueOf(baseDanmaku.userId));
            if (z2) {
                baseDanmaku.mFilterParam |= 16;
            }
            return z2;
        }
    }

    private void throwFilterException() {
        try {
            throw this.filterException;
        } catch (Exception unused) {
        }
    }

    public void clear() {
        for (IDanmakuFilter<?> iDanmakuFilter : this.mFilterArray) {
            if (iDanmakuFilter != null) {
                iDanmakuFilter.clear();
            }
        }
        for (IDanmakuFilter<?> iDanmakuFilter2 : this.mFilterArraySecondary) {
            if (iDanmakuFilter2 != null) {
                iDanmakuFilter2.clear();
            }
        }
    }

    public void filter(BaseDanmaku baseDanmaku, int i, int i2, DanmakuTimer danmakuTimer, boolean z, DanmakuContext danmakuContext) {
        for (IDanmakuFilter<?> iDanmakuFilter : this.mFilterArray) {
            if (iDanmakuFilter != null) {
                boolean zFilter = iDanmakuFilter.filter(baseDanmaku, i, i2, danmakuTimer, z, danmakuContext);
                baseDanmaku.filterResetFlag = danmakuContext.mGlobalFlagValues.FILTER_RESET_FLAG;
                if (zFilter) {
                    return;
                }
            }
        }
    }

    public boolean filterSecondary(BaseDanmaku baseDanmaku, int i, int i2, DanmakuTimer danmakuTimer, boolean z, DanmakuContext danmakuContext) {
        for (IDanmakuFilter<?> iDanmakuFilter : this.mFilterArraySecondary) {
            if (iDanmakuFilter != null) {
                boolean zFilter = iDanmakuFilter.filter(baseDanmaku, i, i2, danmakuTimer, z, danmakuContext);
                baseDanmaku.filterResetFlag = danmakuContext.mGlobalFlagValues.FILTER_RESET_FLAG;
                if (zFilter) {
                    return true;
                }
            }
        }
        return false;
    }

    public IDanmakuFilter<?> get(String str) {
        return get(str, true);
    }

    public IDanmakuFilter<?> registerFilter(String str) {
        return registerFilter(str, true);
    }

    public void release() {
        clear();
        this.filters.clear();
        this.mFilterArray = new IDanmakuFilter[0];
        this.filtersSecondary.clear();
        this.mFilterArraySecondary = new IDanmakuFilter[0];
    }

    public void reset() {
        for (IDanmakuFilter<?> iDanmakuFilter : this.mFilterArray) {
            if (iDanmakuFilter != null) {
                iDanmakuFilter.reset();
            }
        }
        for (IDanmakuFilter<?> iDanmakuFilter2 : this.mFilterArraySecondary) {
            if (iDanmakuFilter2 != null) {
                iDanmakuFilter2.reset();
            }
        }
    }

    public void unregisterFilter(String str) {
        unregisterFilter(str, true);
    }

    public IDanmakuFilter<?> get(String str, boolean z) {
        IDanmakuFilter<?> iDanmakuFilter = (z ? this.filters : this.filtersSecondary).get(str);
        return iDanmakuFilter == null ? registerFilter(str, z) : iDanmakuFilter;
    }

    public IDanmakuFilter<?> registerFilter(String str, boolean z) {
        if (str == null) {
            throwFilterException();
            return null;
        }
        IDanmakuFilter<?> overlappingFilter = this.filters.get(str);
        if (overlappingFilter == null) {
            if (TAG_TYPE_DANMAKU_FILTER.equals(str)) {
                overlappingFilter = new TypeDanmakuFilter();
            } else if (TAG_QUANTITY_DANMAKU_FILTER.equals(str)) {
                overlappingFilter = new QuantityDanmakuFilter();
            } else if (TAG_ELAPSED_TIME_FILTER.equals(str)) {
                overlappingFilter = new ElapsedTimeFilter();
            } else if (TAG_TEXT_COLOR_DANMAKU_FILTER.equals(str)) {
                overlappingFilter = new TextColorFilter();
            } else if (TAG_USER_ID_FILTER.equals(str)) {
                overlappingFilter = new UserIdFilter();
            } else if (TAG_USER_HASH_FILTER.equals(str)) {
                overlappingFilter = new UserHashFilter();
            } else if (TAG_GUEST_FILTER.equals(str)) {
                overlappingFilter = new GuestFilter();
            } else if (TAG_DUPLICATE_FILTER.equals(str)) {
                overlappingFilter = new DuplicateMergingFilter();
            } else if (TAG_MAXIMUN_LINES_FILTER.equals(str)) {
                overlappingFilter = new MaximumLinesFilter();
            } else if (TAG_OVERLAPPING_FILTER.equals(str)) {
                overlappingFilter = new OverlappingFilter();
            }
        }
        if (overlappingFilter == null) {
            throwFilterException();
            return null;
        }
        overlappingFilter.setData(null);
        if (z) {
            this.filters.put(str, overlappingFilter);
            this.mFilterArray = (IDanmakuFilter[]) this.filters.values().toArray(this.mFilterArray);
        } else {
            this.filtersSecondary.put(str, overlappingFilter);
            this.mFilterArraySecondary = (IDanmakuFilter[]) this.filtersSecondary.values().toArray(this.mFilterArraySecondary);
        }
        return overlappingFilter;
    }

    public void unregisterFilter(String str, boolean z) {
        IDanmakuFilter<?> iDanmakuFilterRemove = (z ? this.filters : this.filtersSecondary).remove(str);
        if (iDanmakuFilterRemove != null) {
            iDanmakuFilterRemove.clear();
            if (z) {
                this.mFilterArray = (IDanmakuFilter[]) this.filters.values().toArray(this.mFilterArray);
            } else {
                this.mFilterArraySecondary = (IDanmakuFilter[]) this.filtersSecondary.values().toArray(this.mFilterArraySecondary);
            }
        }
    }

    public static class DuplicateMergingFilter extends BaseDanmakuFilter<Void> {
        public final IDanmakus blockedDanmakus = new Danmakus(4);
        public final LinkedHashMap<String, BaseDanmaku> currentDanmakus = new LinkedHashMap<>();
        public final IDanmakus passedDanmakus = new Danmakus(4);

        private final void removeTimeoutDanmakus(IDanmakus iDanmakus, long j) {
            IDanmakuIterator it = iDanmakus.iterator();
            long jUptimeMillis = SystemClock.uptimeMillis();
            while (it.hasNext()) {
                try {
                    if (!it.next().isTimeOut()) {
                        return;
                    }
                    it.remove();
                    if (SystemClock.uptimeMillis() - jUptimeMillis > j) {
                        return;
                    }
                } catch (Exception unused) {
                    return;
                }
            }
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.BaseDanmakuFilter, master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void clear() {
            reset();
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public boolean filter(BaseDanmaku baseDanmaku, int i, int i2, DanmakuTimer danmakuTimer, boolean z, DanmakuContext danmakuContext) {
            boolean zNeedFilter = needFilter(baseDanmaku, i, i2, danmakuTimer, z);
            if (zNeedFilter) {
                baseDanmaku.mFilterParam |= 128;
            }
            return zNeedFilter;
        }

        public synchronized boolean needFilter(BaseDanmaku baseDanmaku, int i, int i2, DanmakuTimer danmakuTimer, boolean z) {
            removeTimeoutDanmakus(this.blockedDanmakus, 2L);
            removeTimeoutDanmakus(this.passedDanmakus, 2L);
            removeTimeoutDanmakus(this.currentDanmakus, 3);
            if (this.blockedDanmakus.contains(baseDanmaku) && !baseDanmaku.isOutside()) {
                return true;
            }
            if (this.passedDanmakus.contains(baseDanmaku)) {
                return false;
            }
            if (!this.currentDanmakus.containsKey(baseDanmaku.text)) {
                this.currentDanmakus.put(String.valueOf(baseDanmaku.text), baseDanmaku);
                this.passedDanmakus.addItem(baseDanmaku);
                return false;
            }
            this.currentDanmakus.put(String.valueOf(baseDanmaku.text), baseDanmaku);
            this.blockedDanmakus.removeItem(baseDanmaku);
            this.blockedDanmakus.addItem(baseDanmaku);
            return true;
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public synchronized void reset() {
            this.passedDanmakus.clear();
            this.blockedDanmakus.clear();
            this.currentDanmakus.clear();
        }

        @Override // master.flame.danmaku.controller.DanmakuFilters.IDanmakuFilter
        public void setData(Void r1) {
        }

        private void removeTimeoutDanmakus(LinkedHashMap<String, BaseDanmaku> linkedHashMap, int i) {
            Iterator<Map.Entry<String, BaseDanmaku>> it = linkedHashMap.entrySet().iterator();
            long jUptimeMillis = SystemClock.uptimeMillis();
            while (it.hasNext()) {
                try {
                    if (!it.next().getValue().isTimeOut()) {
                        return;
                    }
                    it.remove();
                    if (SystemClock.uptimeMillis() - jUptimeMillis > i) {
                        return;
                    }
                } catch (Exception unused) {
                    return;
                }
            }
        }
    }
}
