package com.g.gysdk.a;

import android.content.Context;
import com.getui.gtc.base.db.AbstractDb;
import com.getui.gtc.base.db.DbManager;

/* JADX INFO: loaded from: classes.dex */
public class g extends AbstractDb {
    public static h a() {
        return (h) DbManager.getTable(g.class, h.class);
    }

    public static void a(Context context) {
        try {
            synchronized (g.class) {
                DbManager.init(context, g.class, h.class, f.class);
            }
        } catch (Throwable th) {
            ak.e("db create failed", th);
        }
    }

    public static f b() {
        return (f) DbManager.getTable(g.class, f.class);
    }

    @Override // com.getui.gtc.base.db.AbstractDb
    public String getDbName() {
        return "gy3.db";
    }

    @Override // com.getui.gtc.base.db.AbstractDb
    public int getVersion() {
        return 1;
    }
}
