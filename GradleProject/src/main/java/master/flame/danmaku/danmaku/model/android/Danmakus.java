package master.flame.danmaku.danmaku.model.android;

import com.taobao.weex.ui.view.gesture.WXGesture;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.Danmaku;
import master.flame.danmaku.danmaku.model.IDanmakuIterator;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.util.DanmakuUtils;

/* JADX INFO: loaded from: classes3.dex */
public class Danmakus implements IDanmakus {
    public static final int ST_BY_LIST = 4;
    public static final int ST_BY_TIME = 0;
    public static final int ST_BY_YPOS = 1;
    public static final int ST_BY_YPOS_DESC = 2;
    public BaseDanmaku endItem;
    public BaseDanmaku endSubItem;
    public Collection<BaseDanmaku> items;
    public DanmakuIterator iterator;
    public BaseComparator mComparator;
    public boolean mDuplicateMergingEnabled;
    public int mSize;
    public int mSortType;
    public BaseDanmaku startItem;
    public BaseDanmaku startSubItem;
    public Danmakus subItems;

    public class BaseComparator implements Comparator<BaseDanmaku> {
        public boolean mDuplicateMergingEnable;

        public BaseComparator(boolean z) {
            setDuplicateMergingEnabled(z);
        }

        public void setDuplicateMergingEnabled(boolean z) {
            this.mDuplicateMergingEnable = z;
        }

        @Override // java.util.Comparator
        public int compare(BaseDanmaku baseDanmaku, BaseDanmaku baseDanmaku2) {
            if (this.mDuplicateMergingEnable && DanmakuUtils.isDuplicate(baseDanmaku, baseDanmaku2)) {
                return 0;
            }
            return DanmakuUtils.compare(baseDanmaku, baseDanmaku2);
        }
    }

    public class DanmakuIterator implements IDanmakuIterator {
        public Iterator<BaseDanmaku> it;
        public Collection<BaseDanmaku> mData;
        public boolean mIteratorUsed;

        public DanmakuIterator(Collection<BaseDanmaku> collection) {
            setDatas(collection);
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x000f  */
        @Override // master.flame.danmaku.danmaku.model.IDanmakuIterator
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public synchronized boolean hasNext() {
            /*
                r1 = this;
                monitor-enter(r1)
                java.util.Iterator<master.flame.danmaku.danmaku.model.BaseDanmaku> r0 = r1.it     // Catch: java.lang.Throwable -> L12
                if (r0 == 0) goto Lf
                java.util.Iterator<master.flame.danmaku.danmaku.model.BaseDanmaku> r0 = r1.it     // Catch: java.lang.Throwable -> L12
                boolean r0 = r0.hasNext()     // Catch: java.lang.Throwable -> L12
                if (r0 == 0) goto Lf
                r0 = 1
                goto L10
            Lf:
                r0 = 0
            L10:
                monitor-exit(r1)
                return r0
            L12:
                r0 = move-exception
                monitor-exit(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: master.flame.danmaku.danmaku.model.android.Danmakus.DanmakuIterator.hasNext():boolean");
        }

        @Override // master.flame.danmaku.danmaku.model.IDanmakuIterator
        public synchronized BaseDanmaku next() {
            this.mIteratorUsed = true;
            return this.it != null ? this.it.next() : null;
        }

        @Override // master.flame.danmaku.danmaku.model.IDanmakuIterator
        public synchronized void remove() {
            this.mIteratorUsed = true;
            if (this.it != null) {
                this.it.remove();
                Danmakus.access$010(Danmakus.this);
            }
        }

        @Override // master.flame.danmaku.danmaku.model.IDanmakuIterator
        public synchronized void reset() {
            if (this.mIteratorUsed || this.it == null) {
                if (this.mData == null || Danmakus.this.mSize <= 0) {
                    this.it = null;
                } else {
                    this.it = this.mData.iterator();
                }
                this.mIteratorUsed = false;
            }
        }

        public synchronized void setDatas(Collection<BaseDanmaku> collection) {
            if (this.mData != collection) {
                this.mIteratorUsed = false;
                this.it = null;
            }
            this.mData = collection;
        }
    }

    public class TimeComparator extends BaseComparator {
        public TimeComparator(boolean z) {
            super(z);
        }

        @Override // master.flame.danmaku.danmaku.model.android.Danmakus.BaseComparator, java.util.Comparator
        public int compare(BaseDanmaku baseDanmaku, BaseDanmaku baseDanmaku2) {
            return super.compare(baseDanmaku, baseDanmaku2);
        }
    }

    public class YPosComparator extends BaseComparator {
        public YPosComparator(boolean z) {
            super(z);
        }

        @Override // master.flame.danmaku.danmaku.model.android.Danmakus.BaseComparator, java.util.Comparator
        public int compare(BaseDanmaku baseDanmaku, BaseDanmaku baseDanmaku2) {
            if (this.mDuplicateMergingEnable && DanmakuUtils.isDuplicate(baseDanmaku, baseDanmaku2)) {
                return 0;
            }
            return Float.compare(baseDanmaku.getTop(), baseDanmaku2.getTop());
        }
    }

    public class YPosDescComparator extends BaseComparator {
        public YPosDescComparator(boolean z) {
            super(z);
        }

        @Override // master.flame.danmaku.danmaku.model.android.Danmakus.BaseComparator, java.util.Comparator
        public int compare(BaseDanmaku baseDanmaku, BaseDanmaku baseDanmaku2) {
            if (this.mDuplicateMergingEnable && DanmakuUtils.isDuplicate(baseDanmaku, baseDanmaku2)) {
                return 0;
            }
            return Float.compare(baseDanmaku2.getTop(), baseDanmaku.getTop());
        }
    }

    public Danmakus() {
        this(0, false);
    }

    public static /* synthetic */ int access$010(Danmakus danmakus) {
        int i = danmakus.mSize;
        danmakus.mSize = i - 1;
        return i;
    }

    private BaseDanmaku createItem(String str) {
        return new Danmaku(str);
    }

    private void setDuplicateMergingEnabled(boolean z) {
        this.mComparator.setDuplicateMergingEnabled(z);
        this.mDuplicateMergingEnabled = z;
    }

    private Collection<BaseDanmaku> subset(long j, long j2) {
        Collection<BaseDanmaku> collection;
        if (this.mSortType == 4 || (collection = this.items) == null || collection.size() == 0) {
            return null;
        }
        if (this.subItems == null) {
            this.subItems = new Danmakus(this.mDuplicateMergingEnabled);
        }
        if (this.startSubItem == null) {
            this.startSubItem = createItem("start");
        }
        if (this.endSubItem == null) {
            this.endSubItem = createItem(WXGesture.END);
        }
        this.startSubItem.setTime(j);
        this.endSubItem.setTime(j2);
        return ((SortedSet) this.items).subSet(this.startSubItem, this.endSubItem);
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public boolean addItem(BaseDanmaku baseDanmaku) {
        Collection<BaseDanmaku> collection = this.items;
        if (collection == null) {
            return false;
        }
        try {
            if (!collection.add(baseDanmaku)) {
                return false;
            }
            this.mSize++;
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public void clear() {
        Collection<BaseDanmaku> collection = this.items;
        if (collection != null) {
            collection.clear();
            this.mSize = 0;
            this.iterator = new DanmakuIterator(this.items);
        }
        if (this.subItems != null) {
            this.subItems = null;
            this.startItem = createItem("start");
            this.endItem = createItem(WXGesture.END);
        }
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public boolean contains(BaseDanmaku baseDanmaku) {
        Collection<BaseDanmaku> collection = this.items;
        return collection != null && collection.contains(baseDanmaku);
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public BaseDanmaku first() {
        Collection<BaseDanmaku> collection = this.items;
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        return this.mSortType == 4 ? (BaseDanmaku) ((LinkedList) this.items).peek() : (BaseDanmaku) ((SortedSet) this.items).first();
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public boolean isEmpty() {
        Collection<BaseDanmaku> collection = this.items;
        return collection == null || collection.isEmpty();
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public IDanmakuIterator iterator() {
        this.iterator.reset();
        return this.iterator;
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public BaseDanmaku last() {
        Collection<BaseDanmaku> collection = this.items;
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        return this.mSortType == 4 ? (BaseDanmaku) ((LinkedList) this.items).peekLast() : (BaseDanmaku) ((SortedSet) this.items).last();
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public boolean removeItem(BaseDanmaku baseDanmaku) {
        if (baseDanmaku == null) {
            return false;
        }
        if (baseDanmaku.isOutside()) {
            baseDanmaku.setVisibility(false);
        }
        if (!this.items.remove(baseDanmaku)) {
            return false;
        }
        this.mSize--;
        return true;
    }

    public void setItems(Collection<BaseDanmaku> collection) {
        if (!this.mDuplicateMergingEnabled || this.mSortType == 4) {
            this.items = collection;
        } else {
            this.items.clear();
            this.items.addAll(collection);
            collection = this.items;
        }
        if (collection instanceof List) {
            this.mSortType = 4;
        }
        this.mSize = collection == null ? 0 : collection.size();
        DanmakuIterator danmakuIterator = this.iterator;
        if (danmakuIterator == null) {
            this.iterator = new DanmakuIterator(collection);
        } else {
            danmakuIterator.setDatas(collection);
        }
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public void setSubItemsDuplicateMergingEnabled(boolean z) {
        this.mDuplicateMergingEnabled = z;
        this.endItem = null;
        this.startItem = null;
        if (this.subItems == null) {
            this.subItems = new Danmakus(z);
        }
        this.subItems.setDuplicateMergingEnabled(z);
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public int size() {
        return this.mSize;
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public IDanmakus sub(long j, long j2) {
        Collection<BaseDanmaku> collection = this.items;
        if (collection == null || collection.size() == 0) {
            return null;
        }
        if (this.subItems == null) {
            if (this.mSortType == 4) {
                Danmakus danmakus = new Danmakus(4);
                this.subItems = danmakus;
                danmakus.setItems(this.items);
            } else {
                this.subItems = new Danmakus(this.mDuplicateMergingEnabled);
            }
        }
        if (this.mSortType == 4) {
            return this.subItems;
        }
        if (this.startItem == null) {
            this.startItem = createItem("start");
        }
        if (this.endItem == null) {
            this.endItem = createItem(WXGesture.END);
        }
        if (this.subItems != null && j - this.startItem.getActualTime() >= 0 && j2 <= this.endItem.getActualTime()) {
            return this.subItems;
        }
        this.startItem.setTime(j);
        this.endItem.setTime(j2);
        this.subItems.setItems(((SortedSet) this.items).subSet(this.startItem, this.endItem));
        return this.subItems;
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public IDanmakus subnew(long j, long j2) {
        Collection<BaseDanmaku> collectionSubset = subset(j, j2);
        if (collectionSubset == null || collectionSubset.isEmpty()) {
            return null;
        }
        return new Danmakus(new LinkedList(collectionSubset));
    }

    public Danmakus(int i) {
        this(i, false);
    }

    public Danmakus(int i, boolean z) {
        BaseComparator yPosDescComparator;
        this.mSize = 0;
        this.mSortType = 0;
        if (i == 0) {
            yPosDescComparator = new TimeComparator(z);
        } else if (i == 1) {
            yPosDescComparator = new YPosComparator(z);
        } else {
            yPosDescComparator = i == 2 ? new YPosDescComparator(z) : null;
        }
        if (i == 4) {
            this.items = new LinkedList();
        } else {
            this.mDuplicateMergingEnabled = z;
            yPosDescComparator.setDuplicateMergingEnabled(z);
            this.items = new TreeSet(yPosDescComparator);
            this.mComparator = yPosDescComparator;
        }
        this.mSortType = i;
        this.mSize = 0;
        this.iterator = new DanmakuIterator(this.items);
    }

    public Danmakus(Collection<BaseDanmaku> collection) {
        this.mSize = 0;
        this.mSortType = 0;
        setItems(collection);
    }

    public Danmakus(boolean z) {
        this(0, z);
    }
}
