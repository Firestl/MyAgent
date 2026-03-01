package io.dcloud.common.util.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.base.R;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.internal.sdk.SDK;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes2.dex */
public class DCStorage {
    public static final int ERROR_DEF = -1;
    public static final int ERROR_FULL = -2;
    public static final int ERROR_NO_KEY = -3;
    public static final int SUCCESS = 1;
    public static DCStorage mInstance;
    public DCSQLiteOpenHelper mDatabaseSupplier;
    public ExecutorService mExecutorService;
    public String TABLET_TAG = "_storage";
    public String DBFILE_NAME = "_dbfile";
    public String DCDBFILE_START = "DCDBFile_";
    public String ERROR_TAG = "__ERROR__";

    public class StorageInfo {
        public int code;
        public String meg;
        public Object v;

        public StorageInfo() {
        }
    }

    public DCStorage(Context context) {
        this.mDatabaseSupplier = DCSQLiteOpenHelper.getSQLiteOpenHelper(context);
    }

    private void clearDBFile(Context context, String str) {
        File file = new File(getBaseDBFilePath(context, str));
        if (file.exists()) {
            file.delete();
        }
    }

    private String getBaseDBFilePath(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + "/apps/" + str + "/dbfile/";
    }

    private String getCurrentTableName(String str) {
        return "DC_" + Math.abs(str.hashCode()) + this.TABLET_TAG;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0059, code lost:
    
        if (r2 == null) goto L45;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String getDBFileValue(java.lang.String r6) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = io.dcloud.common.util.PdrUtil.isEmpty(r6)
            java.lang.String r1 = ""
            if (r0 != 0) goto L65
            java.lang.String r0 = r5.DCDBFILE_START
            boolean r0 = r6.startsWith(r0)
            if (r0 == 0) goto L16
            r0 = 9
            java.lang.String r6 = r6.substring(r0)
        L16:
            java.io.File r0 = new java.io.File
            r0.<init>(r6)
            r2 = 0
            boolean r0 = r0.exists()
            if (r0 == 0) goto L65
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4e java.io.FileNotFoundException -> L55
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4e java.io.FileNotFoundException -> L55
            r4.<init>(r6)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4e java.io.FileNotFoundException -> L55
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4e java.io.FileNotFoundException -> L55
        L31:
            java.lang.String r6 = r3.readLine()     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L46 java.io.FileNotFoundException -> L49
            if (r6 == 0) goto L3b
            r0.append(r6)     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L46 java.io.FileNotFoundException -> L49
            goto L31
        L3b:
            java.lang.String r1 = r0.toString()     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L46 java.io.FileNotFoundException -> L49
            r3.close()     // Catch: java.io.IOException -> L65
            goto L65
        L43:
            r6 = move-exception
            r2 = r3
            goto L5f
        L46:
            r6 = move-exception
            r2 = r3
            goto L4f
        L49:
            r6 = move-exception
            r2 = r3
            goto L56
        L4c:
            r6 = move-exception
            goto L5f
        L4e:
            r6 = move-exception
        L4f:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L4c
            if (r2 == 0) goto L65
            goto L5b
        L55:
            r6 = move-exception
        L56:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L4c
            if (r2 == 0) goto L65
        L5b:
            r2.close()     // Catch: java.io.IOException -> L65
            goto L65
        L5f:
            if (r2 == 0) goto L64
            r2.close()     // Catch: java.io.IOException -> L64
        L64:
            throw r6
        L65:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.db.DCStorage.getDBFileValue(java.lang.String):java.lang.String");
    }

    public static DCStorage getDCStorage(Context context) {
        if (mInstance == null && context != null) {
            mInstance = new DCStorage(context);
        }
        return mInstance;
    }

    private String getMsgForCode(Context context, int i) {
        return i != -3 ? i != -2 ? i != 1 ? "" : context.getString(R.string.dcloud_storage_success) : context.getString(R.string.dcloud_storage_ceiling_error) : context.getString(R.string.dcloud_storage_not_find_error);
    }

    private void removeDBFile(Context context, String str, String str2) {
        removeDBFile(getBaseDBFilePath(context, str) + str2 + this.DBFILE_NAME);
    }

    private String saveDBFileValue(Context context, String str, String str2, String str3) {
        String baseDBFilePath = getBaseDBFilePath(context, str);
        File file = new File(baseDBFilePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (file.isFile()) {
            file.delete();
            file.mkdirs();
        }
        String str4 = baseDBFilePath + str2.hashCode() + this.DBFILE_NAME;
        File file2 = new File(str4);
        if (file2.exists()) {
            file2.delete();
        }
        try {
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(str4);
            fileOutputStream.write(str3.getBytes());
            fileOutputStream.close();
            return this.DCDBFILE_START + str4;
        } catch (IOException e2) {
            e2.printStackTrace();
            return this.ERROR_TAG + e2.getMessage() + str4;
        }
    }

    public synchronized void checkSPstorageToDB(Context context, String str) {
        SharedPreferences orCreateBundle = SP.getOrCreateBundle(context, str + "_storages");
        SharedPreferences.Editor editorEdit = orCreateBundle.edit();
        Map<String, ?> all = orCreateBundle.getAll();
        if (all != null && all.size() > 0) {
            for (String str2 : all.keySet()) {
                if (performSetItem(context, str, str2, (String) all.get(str2)).code == 1) {
                    editorEdit.remove(str2);
                }
            }
        }
        editorEdit.commit();
    }

    public void close() {
        execute(new Runnable() { // from class: io.dcloud.common.util.db.DCStorage.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (DCStorage.this.mDatabaseSupplier != null) {
                        DCStorage.this.mDatabaseSupplier.closeDatabase();
                    }
                    if (DCStorage.this.mExecutorService != null) {
                        DCStorage.this.mExecutorService.shutdown();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                DCStorage.this.mExecutorService = null;
            }
        });
    }

    public void execute(Runnable runnable) {
        if (this.mExecutorService == null) {
            this.mExecutorService = Executors.newSingleThreadExecutor();
        }
        if (runnable == null || this.mExecutorService.isShutdown() || this.mExecutorService.isTerminated()) {
            return;
        }
        this.mExecutorService.execute(runnable);
    }

    public synchronized StorageInfo performClear(Context context, String str) {
        SQLiteDatabase database = this.mDatabaseSupplier.getDatabase(getCurrentTableName(str));
        StorageInfo storageInfo = new StorageInfo();
        if (database == null) {
            storageInfo.code = -1;
            storageInfo.meg = context.getString(R.string.dcloud_storage_no_db_error);
            return storageInfo;
        }
        storageInfo.code = 1;
        try {
            database.execSQL("delete from " + getCurrentTableName(str));
            if (!SDK.isUniMPSDK()) {
                this.mDatabaseSupplier.ensureDatabase(getCurrentTableName(str));
            }
            clearDBFile(context, str);
        } catch (Exception e2) {
            storageInfo.code = -1;
            storageInfo.meg = context.getString(R.string.dcloud_storage_native_error) + e2.getMessage();
        }
        return storageInfo;
    }

    public synchronized StorageInfo performGetAllKeys(String str) {
        SQLiteDatabase database = this.mDatabaseSupplier.getDatabase(getCurrentTableName(str));
        StorageInfo storageInfo = new StorageInfo();
        Context context = DCLoudApplicationImpl.self().getContext();
        if (database == null) {
            storageInfo.code = -1;
            storageInfo.meg = context.getString(R.string.dcloud_storage_no_db_error);
            return storageInfo;
        }
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = database.query(getCurrentTableName(str), new String[]{"key"}, null, null, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                try {
                    arrayList.add(cursorQuery.getString(cursorQuery.getColumnIndex("key")));
                } catch (Exception e2) {
                    storageInfo.code = -1;
                    storageInfo.meg = context.getString(R.string.dcloud_storage_native_error) + e2.getMessage();
                }
            } finally {
                cursorQuery.close();
            }
        }
        storageInfo.code = 1;
        storageInfo.v = arrayList;
        return storageInfo;
    }

    public synchronized StorageInfo performGetItem(String str, String str2) {
        SQLiteDatabase database = this.mDatabaseSupplier.getDatabase(getCurrentTableName(str));
        StorageInfo storageInfo = new StorageInfo();
        Context context = DCLoudApplicationImpl.self().getContext();
        if (database == null) {
            storageInfo.code = -1;
            storageInfo.meg = context.getString(R.string.dcloud_storage_no_db_error);
            return storageInfo;
        }
        Cursor cursorQuery = database.query(getCurrentTableName(str), new String[]{"value"}, "key=?", new String[]{str2}, null, null, null);
        try {
            if (!cursorQuery.moveToNext()) {
                storageInfo.code = -3;
                storageInfo.meg = getMsgForCode(context, -3);
                return storageInfo;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("timestamp", DCSQLiteOpenHelper.sDateFormatter.format(new Date()));
            this.mDatabaseSupplier.getDatabase(getCurrentTableName(str)).update(getCurrentTableName(str), contentValues, "key= ?", new String[]{str2});
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("value"));
            if (TextUtils.isEmpty(string) || !string.startsWith(this.DCDBFILE_START)) {
                storageInfo.v = string;
            } else {
                String dBFileValue = getDBFileValue(string);
                if (PdrUtil.isEmpty(dBFileValue)) {
                    storageInfo.v = string;
                } else {
                    storageInfo.v = dBFileValue;
                }
            }
            storageInfo.code = 1;
            return storageInfo;
        } catch (Exception e2) {
            storageInfo.code = -1;
            storageInfo.meg = context.getString(R.string.dcloud_storage_native_error) + e2.getMessage();
            return storageInfo;
        } finally {
            cursorQuery.close();
        }
    }

    public synchronized StorageInfo performGetLength(String str) {
        SQLiteDatabase database = this.mDatabaseSupplier.getDatabase(getCurrentTableName(str));
        StorageInfo storageInfo = new StorageInfo();
        Context context = DCLoudApplicationImpl.self().getContext();
        if (database == null) {
            storageInfo.code = -1;
            storageInfo.meg = context.getString(R.string.dcloud_storage_no_db_error);
            return storageInfo;
        }
        SQLiteStatement sQLiteStatementCompileStatement = null;
        try {
            try {
                sQLiteStatementCompileStatement = database.compileStatement("SELECT count(key) FROM " + getCurrentTableName(str));
                storageInfo.v = Long.valueOf(sQLiteStatementCompileStatement.simpleQueryForLong());
                storageInfo.code = 1;
                if (sQLiteStatementCompileStatement != null) {
                    sQLiteStatementCompileStatement.close();
                }
                return storageInfo;
            } catch (Exception e2) {
                storageInfo.code = -1;
                storageInfo.meg = context.getString(R.string.dcloud_storage_native_error) + e2.getMessage();
                if (sQLiteStatementCompileStatement != null) {
                    sQLiteStatementCompileStatement.close();
                }
                return storageInfo;
            }
        } catch (Throwable unused) {
            if (0 != 0) {
                sQLiteStatementCompileStatement.close();
            }
            return storageInfo;
        }
    }

    public synchronized StorageInfo performRemoveItem(Context context, String str, String str2) {
        SQLiteDatabase database = this.mDatabaseSupplier.getDatabase(getCurrentTableName(str));
        StorageInfo storageInfo = new StorageInfo();
        if (database == null) {
            storageInfo.code = -1;
            storageInfo.meg = context.getString(R.string.dcloud_storage_no_db_error);
            return storageInfo;
        }
        try {
            int iDelete = database.delete(getCurrentTableName(str), "key=?", new String[]{str2});
            if (iDelete > 0) {
                removeDBFile(context, str, str2);
            }
            if (iDelete == 1) {
                storageInfo.code = 1;
            } else {
                storageInfo.code = -3;
                storageInfo.meg = getMsgForCode(context, -3);
            }
        } catch (Exception e2) {
            storageInfo.code = -1;
            storageInfo.meg = context.getString(R.string.dcloud_storage_native_error) + e2.getMessage();
        }
        return storageInfo;
    }

    public synchronized StorageInfo performSetItem(Context context, String str, String str2, String str3) {
        SQLiteStatement sQLiteStatement;
        SQLiteDatabase database = this.mDatabaseSupplier.getDatabase(getCurrentTableName(str));
        StorageInfo storageInfo = new StorageInfo();
        if (database == null) {
            storageInfo.code = -1;
            storageInfo.meg = context.getString(R.string.dcloud_storage_no_db_error);
            return storageInfo;
        }
        if (PdrUtil.isEmpty(str2)) {
            storageInfo.code = -1;
            storageInfo.meg = context.getString(R.string.dcloud_storage_key_error);
            return storageInfo;
        }
        try {
            String str4 = "INSERT OR REPLACE INTO " + getCurrentTableName(str) + " VALUES (?,?,?);";
            sQLiteStatement = null;
            String str5 = DCSQLiteOpenHelper.sDateFormatter.format(new Date());
            try {
                if (str3.getBytes().length >= 1800000) {
                    str3 = saveDBFileValue(context, str, str2, str3);
                    if (TextUtils.isEmpty(str3) || str3.startsWith(this.ERROR_TAG)) {
                        storageInfo.code = -1;
                        storageInfo.meg = context.getString(R.string.dcloud_storage_write_big_error);
                        if (!TextUtils.isEmpty(str3) && str3.startsWith(this.ERROR_TAG)) {
                            storageInfo.meg += " error " + str3.replace(this.ERROR_TAG, "");
                        }
                        return storageInfo;
                    }
                }
                SQLiteStatement sQLiteStatementCompileStatement = database.compileStatement(str4);
                sQLiteStatementCompileStatement.clearBindings();
                sQLiteStatementCompileStatement.bindString(1, str2);
                sQLiteStatementCompileStatement.bindString(2, str3);
                sQLiteStatementCompileStatement.bindString(3, str5);
                sQLiteStatementCompileStatement.execute();
                storageInfo.code = 1;
                if (sQLiteStatementCompileStatement != null) {
                    sQLiteStatementCompileStatement.close();
                }
                return storageInfo;
            } catch (Exception e2) {
                if (e2 instanceof SQLiteFullException) {
                    storageInfo.code = -2;
                    storageInfo.meg = getMsgForCode(context, -2);
                    if (0 != 0) {
                        sQLiteStatement.close();
                    }
                    return storageInfo;
                }
                storageInfo.code = -1;
                storageInfo.meg = context.getString(R.string.dcloud_storage_native_error) + e2.getMessage();
                if (0 != 0) {
                    sQLiteStatement.close();
                }
                return storageInfo;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                sQLiteStatement.close();
            }
            throw th;
        }
    }

    private void removeDBFile(String str) {
        if (PdrUtil.isEmpty(str)) {
            return;
        }
        if (str.startsWith(this.DCDBFILE_START)) {
            str = str.substring(9);
        }
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }
}
