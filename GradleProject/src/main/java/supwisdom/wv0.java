package supwisdom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cookie.SerializableCookie;
import com.lzy.okgo.model.Progress;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.constant.AbsoluteConst;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: DBHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class wv0 extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Lock f9669e = new ReentrantLock();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public yv0 f9670a;
    public yv0 b;
    public yv0 c;
    public yv0 d;

    public wv0() {
        this(fv0.i().e());
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(this.f9670a.a());
        sQLiteDatabase.execSQL(this.b.a());
        sQLiteDatabase.execSQL(this.c.a());
        sQLiteDatabase.execSQL(this.d.a());
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        onUpgrade(sQLiteDatabase, i, i2);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (xv0.a(sQLiteDatabase, this.f9670a)) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cache");
        }
        if (xv0.a(sQLiteDatabase, this.b)) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cookie");
        }
        if (xv0.a(sQLiteDatabase, this.c)) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS download");
        }
        if (xv0.a(sQLiteDatabase, this.d)) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS upload");
        }
        onCreate(sQLiteDatabase);
    }

    public wv0(Context context) {
        super(context, "okgo.db", (SQLiteDatabase.CursorFactory) null, 1);
        this.f9670a = new yv0(IApp.ConfigProperty.CONFIG_CACHE);
        this.b = new yv0(SerializableCookie.COOKIE);
        this.c = new yv0(AbsoluteConst.SPNAME_DOWNLOAD);
        this.d = new yv0("upload");
        yv0 yv0Var = this.f9670a;
        yv0Var.a(new vv0("key", "VARCHAR", true, true));
        yv0Var.a(new vv0(CacheEntity.LOCAL_EXPIRE, "INTEGER"));
        yv0Var.a(new vv0(CacheEntity.HEAD, "BLOB"));
        yv0Var.a(new vv0("data", "BLOB"));
        yv0 yv0Var2 = this.b;
        yv0Var2.a(new vv0("host", "VARCHAR"));
        yv0Var2.a(new vv0("name", "VARCHAR"));
        yv0Var2.a(new vv0(SerializableCookie.DOMAIN, "VARCHAR"));
        yv0Var2.a(new vv0(SerializableCookie.COOKIE, "BLOB"));
        yv0Var2.a(new vv0("host", "name", SerializableCookie.DOMAIN));
        yv0 yv0Var3 = this.c;
        yv0Var3.a(new vv0("tag", "VARCHAR", true, true));
        yv0Var3.a(new vv0("url", "VARCHAR"));
        yv0Var3.a(new vv0(Progress.FOLDER, "VARCHAR"));
        yv0Var3.a(new vv0(Progress.FILE_PATH, "VARCHAR"));
        yv0Var3.a(new vv0(Progress.FILE_NAME, "VARCHAR"));
        yv0Var3.a(new vv0(Progress.FRACTION, "VARCHAR"));
        yv0Var3.a(new vv0("totalSize", "INTEGER"));
        yv0Var3.a(new vv0(Progress.CURRENT_SIZE, "INTEGER"));
        yv0Var3.a(new vv0("status", "INTEGER"));
        yv0Var3.a(new vv0("priority", "INTEGER"));
        yv0Var3.a(new vv0("date", "INTEGER"));
        yv0Var3.a(new vv0("request", "BLOB"));
        yv0Var3.a(new vv0(Progress.EXTRA1, "BLOB"));
        yv0Var3.a(new vv0(Progress.EXTRA2, "BLOB"));
        yv0Var3.a(new vv0(Progress.EXTRA3, "BLOB"));
        yv0 yv0Var4 = this.d;
        yv0Var4.a(new vv0("tag", "VARCHAR", true, true));
        yv0Var4.a(new vv0("url", "VARCHAR"));
        yv0Var4.a(new vv0(Progress.FOLDER, "VARCHAR"));
        yv0Var4.a(new vv0(Progress.FILE_PATH, "VARCHAR"));
        yv0Var4.a(new vv0(Progress.FILE_NAME, "VARCHAR"));
        yv0Var4.a(new vv0(Progress.FRACTION, "VARCHAR"));
        yv0Var4.a(new vv0("totalSize", "INTEGER"));
        yv0Var4.a(new vv0(Progress.CURRENT_SIZE, "INTEGER"));
        yv0Var4.a(new vv0("status", "INTEGER"));
        yv0Var4.a(new vv0("priority", "INTEGER"));
        yv0Var4.a(new vv0("date", "INTEGER"));
        yv0Var4.a(new vv0("request", "BLOB"));
        yv0Var4.a(new vv0(Progress.EXTRA1, "BLOB"));
        yv0Var4.a(new vv0(Progress.EXTRA2, "BLOB"));
        yv0Var4.a(new vv0(Progress.EXTRA3, "BLOB"));
    }
}
