package com.getui.gtc.base.db;

import android.content.Context;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class DbManager {
    public static final Map<Class<? extends AbstractDb>, AbstractDb> dbMap = new ConcurrentHashMap();

    public static <T extends AbstractTable> T getTable(Class<? extends AbstractDb> cls, Class<T> cls2) {
        AbstractDb abstractDb = dbMap.get(cls);
        if (abstractDb != null) {
            return (T) abstractDb.getTable(cls2);
        }
        throw new RuntimeException("db " + cls.getSimpleName() + " has not been initialized");
    }

    public static Collection<AbstractTable> getTables(Class<? extends AbstractDb> cls) {
        AbstractDb abstractDb = dbMap.get(cls);
        if (abstractDb != null) {
            return abstractDb.getTables();
        }
        throw new RuntimeException("db " + cls.getSimpleName() + "has not been initialized");
    }

    public static void init(Context context, Class<? extends AbstractDb> cls, Class<? extends AbstractTable>... clsArr) throws IllegalAccessException, InstantiationException {
        Context applicationContext = context.getApplicationContext();
        AbstractDb abstractDbNewInstance = dbMap.get(cls);
        if (abstractDbNewInstance == null) {
            abstractDbNewInstance = cls.newInstance();
            dbMap.put(cls, abstractDbNewInstance);
        }
        for (Class<? extends AbstractTable> cls2 : clsArr) {
            abstractDbNewInstance.addTable(cls2);
        }
        abstractDbNewInstance.init(applicationContext);
    }
}
