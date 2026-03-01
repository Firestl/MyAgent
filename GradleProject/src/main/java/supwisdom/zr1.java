package supwisdom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* JADX INFO: compiled from: TrayDBHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public class zr1 extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f10022a;
    public final boolean b;

    public zr1(Context context, boolean z) {
        super(context, z ? "tray.db" : "tray_backup_excluded.db", (SQLiteDatabase.CursorFactory) null, 2);
        this.b = z;
        this.f10022a = 2;
    }

    public final void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE TrayPreferences ( _id INTEGER PRIMARY KEY, KEY TEXT NOT NULL, VALUE TEXT, MODULE TEXT, CREATED INT DEFAULT 0, UPDATED INT DEFAULT 0, UNIQUE (MODULE, KEY));");
    }

    public final void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE TrayPreferences ADD COLUMN MIGRATED_KEY TEXT");
        sQLiteDatabase.execSQL("CREATE TABLE TrayInternal ( _id INTEGER PRIMARY KEY, KEY TEXT NOT NULL, VALUE TEXT, MODULE TEXT, CREATED INT DEFAULT 0, UPDATED INT DEFAULT 0, MIGRATED_KEY TEXT, UNIQUE (MODULE, KEY));");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        vr1.b(a() + "onCreate with version " + this.f10022a);
        a(sQLiteDatabase);
        vr1.b(a() + "created database version 1");
        int i = this.f10022a;
        if (i > 1) {
            onUpgrade(sQLiteDatabase, 1, i);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        vr1.b(a() + "upgrading Database from version " + i + " to version " + i2);
        if (i2 > 2) {
            throw new IllegalStateException("onUpgrade doesn't support the upgrade to version " + i2);
        }
        if (i != 1) {
            throw new IllegalArgumentException("onUpgrade() with oldVersion <= 0 is useless");
        }
        b(sQLiteDatabase);
        vr1.b(a() + "upgraded Database to version 2");
    }

    public final String a() {
        StringBuilder sb = new StringBuilder();
        sb.append("tray internal db (");
        sb.append(this.b ? "backup" : "no backup");
        sb.append("): ");
        return sb.toString();
    }
}
