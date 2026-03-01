package com.bumptech.glide.util.pool;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import supwisdom.ka;
import supwisdom.la;
import supwisdom.ma;

/* JADX INFO: loaded from: classes.dex */
public final class FactoryPools {
    public static final int DEFAULT_POOL_SIZE = 20;
    public static final Resetter<Object> EMPTY_RESETTER = new Resetter<Object>() { // from class: com.bumptech.glide.util.pool.FactoryPools.1
        @Override // com.bumptech.glide.util.pool.FactoryPools.Resetter
        public void reset(Object obj) {
        }
    };
    public static final String TAG = "FactoryPools";

    public interface Factory<T> {
        T create();
    }

    public static final class FactoryPool<T> implements ka<T> {
        public final Factory<T> factory;
        public final ka<T> pool;
        public final Resetter<T> resetter;

        public FactoryPool(ka<T> kaVar, Factory<T> factory, Resetter<T> resetter) {
            this.pool = kaVar;
            this.factory = factory;
            this.resetter = resetter;
        }

        @Override // supwisdom.ka
        public T acquire() {
            T tAcquire = this.pool.acquire();
            if (tAcquire == null) {
                tAcquire = this.factory.create();
                if (Log.isLoggable(FactoryPools.TAG, 2)) {
                    Log.v(FactoryPools.TAG, "Created new " + tAcquire.getClass());
                }
            }
            if (tAcquire instanceof Poolable) {
                ((Poolable) tAcquire).getVerifier().setRecycled(false);
            }
            return tAcquire;
        }

        @Override // supwisdom.ka
        public boolean release(T t) {
            if (t instanceof Poolable) {
                ((Poolable) t).getVerifier().setRecycled(true);
            }
            this.resetter.reset(t);
            return this.pool.release(t);
        }
    }

    public interface Poolable {
        StateVerifier getVerifier();
    }

    public interface Resetter<T> {
        void reset(T t);
    }

    public static <T extends Poolable> ka<T> build(ka<T> kaVar, Factory<T> factory) {
        return build(kaVar, factory, emptyResetter());
    }

    public static <T> Resetter<T> emptyResetter() {
        return (Resetter<T>) EMPTY_RESETTER;
    }

    public static <T extends Poolable> ka<T> simple(int i, Factory<T> factory) {
        return build(new la(i), factory);
    }

    public static <T extends Poolable> ka<T> threadSafe(int i, Factory<T> factory) {
        return build(new ma(i), factory);
    }

    public static <T> ka<List<T>> threadSafeList() {
        return threadSafeList(20);
    }

    public static <T> ka<T> build(ka<T> kaVar, Factory<T> factory, Resetter<T> resetter) {
        return new FactoryPool(kaVar, factory, resetter);
    }

    public static <T> ka<List<T>> threadSafeList(int i) {
        return build(new ma(i), new Factory<List<T>>() { // from class: com.bumptech.glide.util.pool.FactoryPools.2
            @Override // com.bumptech.glide.util.pool.FactoryPools.Factory
            public List<T> create() {
                return new ArrayList();
            }
        }, new Resetter<List<T>>() { // from class: com.bumptech.glide.util.pool.FactoryPools.3
            @Override // com.bumptech.glide.util.pool.FactoryPools.Resetter
            public void reset(List<T> list) {
                list.clear();
            }
        });
    }
}
