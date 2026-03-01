package io.dcloud.common.util.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import io.dcloud.common.adapter.util.Logger;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class DCSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String COLUMN_KEY = "key";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_VALUE = "value";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS default_wx_storage (key TEXT PRIMARY KEY,value TEXT NOT NULL,timestamp TEXT NOT NULL)";
    public static final String DATABASE_NAME = "DCStorage";
    public static final int DATABASE_VERSION = 1;
    public static final int SLEEP_TIME_MS = 30;
    public static final String TABLE_STORAGE = "default_wx_storage";
    public static final String TAG_STORAGE = "dc_storage";
    public static DCSQLiteOpenHelper mInstance;
    public static SimpleDateFormat sDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    public Context mContext;
    public SQLiteDatabase mDb;

    public DCSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.mContext = context;
    }

    private void createTableIfNotExists(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = sQLiteDatabase.rawQuery("SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = 'default_wx_storage'", null);
                if (cursorRawQuery != null && cursorRawQuery.getCount() > 0) {
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                        return;
                    }
                    return;
                } else {
                    sQLiteDatabase.execSQL(CREATE_TABLE);
                    if (cursorRawQuery == null) {
                        return;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursorRawQuery == null) {
                    return;
                }
            }
            cursorRawQuery.close();
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }

    private boolean deleteDB() {
        closeDatabase();
        return this.mContext.deleteDatabase(DATABASE_NAME);
    }

    public static DCSQLiteOpenHelper getSQLiteOpenHelper(Context context) {
        if (mInstance == null) {
            mInstance = new DCSQLiteOpenHelper(context);
        }
        return mInstance;
    }

    public synchronized void closeDatabase() {
        SQLiteDatabase sQLiteDatabase = this.mDb;
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            this.mDb.close();
            this.mDb = null;
        }
        mInstance = null;
    }

    public synchronized void ensureDatabase(String str) {
        SQLiteDatabase sQLiteDatabase = this.mDb;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            for (int i = 0; i < 2; i++) {
                if (i <= 0) {
                    this.mDb = getWritableDatabase();
                    break;
                    break;
                }
                try {
                    deleteDB();
                    this.mDb = getWritableDatabase();
                    break;
                } catch (SQLiteException e2) {
                    try {
                        e2.printStackTrace();
                        try {
                            Thread.sleep(30L);
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                        }
                    } catch (Throwable th) {
                        this.mDb = null;
                        Logger.d(TAG_STORAGE, "ensureDatabase failed, throwable = " + th.getMessage());
                    }
                }
            }
            if (this.mDb == null) {
                return;
            }
            if (TextUtils.isEmpty(str)) {
                createTableIfNotExists(this.mDb);
            } else {
                createTableIfNotExists(this.mDb, str);
            }
        }
    }

    public SQLiteDatabase getDatabase() {
        ensureDatabase(null);
        return this.mDb;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public SQLiteDatabase getDatabase(String str) {
        ensureDatabase(str);
        return this.mDb;
    }

    private void createTableIfNotExists(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = sQLiteDatabase.rawQuery("SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = '" + str + "'", null);
                if (cursorRawQuery != null && cursorRawQuery.getCount() > 0) {
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                        return;
                    }
                    return;
                }
                sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + str + " (key TEXT PRIMARY KEY,value TEXT NOT NULL,timestamp TEXT NOT NULL)");
                if (cursorRawQuery == null) {
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursorRawQuery == null) {
                    return;
                }
            }
            cursorRawQuery.close();
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }
}
