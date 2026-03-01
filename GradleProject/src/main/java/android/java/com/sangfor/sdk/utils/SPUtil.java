package android.java.com.sangfor.sdk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes.dex */
public class SPUtil {
    public static final String IS_AUTHORED_BOOL = "IS_AUTHORED_BOO1L";
    public static final String TAG = "SPUtil";
    public static Context mContext;
    public static SharedPreferences mSp;
    public static String mSpName;
    public static Object threadLock = new Object();

    public static void clear() {
        synchronized (threadLock) {
            SharedPreferences sharedPreferences = mSp;
            if (sharedPreferences == null) {
                Log.e(TAG, "clear failed, SPUtil is not initialized!");
                return;
            }
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.clear();
            editorEdit.commit();
        }
    }

    public static boolean contains(String str) {
        synchronized (threadLock) {
            SharedPreferences sharedPreferences = mSp;
            if (sharedPreferences == null) {
                Log.e(TAG, "contains error, SPUtil is not initialized!");
                return false;
            }
            return sharedPreferences.contains(str);
        }
    }

    public static Object get(String str, Object obj) {
        synchronized (threadLock) {
            SharedPreferences sharedPreferences = mSp;
            if (sharedPreferences == null) {
                Log.e(TAG, "get failed, SPUtil is not initialized! just return defaultObject");
                return obj;
            }
            if (obj instanceof String) {
                return sharedPreferences.getString(str, (String) obj);
            }
            if (obj instanceof Integer) {
                return Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue()));
            }
            if (obj instanceof Boolean) {
                return Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue()));
            }
            if (obj instanceof Float) {
                return Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue()));
            }
            if (!(obj instanceof Long)) {
                return null;
            }
            return Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue()));
        }
    }

    public static void init(Context context) {
        synchronized (threadLock) {
            mContext = context;
            String str = mContext.getPackageName() + "_sangfor_preferences";
            mSpName = str;
            mSp = mContext.getSharedPreferences(str, 0);
        }
    }

    public static boolean put(String str, Object obj) {
        synchronized (threadLock) {
            SharedPreferences sharedPreferences = mSp;
            if (sharedPreferences == null) {
                Log.e(TAG, "put failed, SPUtil is not initialized!");
                return false;
            }
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            if (obj instanceof String) {
                editorEdit.putString(str, (String) obj);
            } else if (obj instanceof Integer) {
                editorEdit.putInt(str, ((Integer) obj).intValue());
            } else if (obj instanceof Float) {
                editorEdit.putFloat(str, ((Float) obj).floatValue());
            } else if (obj instanceof Long) {
                editorEdit.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof Boolean) {
                editorEdit.putBoolean(str, ((Boolean) obj).booleanValue());
            } else {
                editorEdit.putString(str, obj.toString());
            }
            return editorEdit.commit();
        }
    }

    public static void remove(String str) {
        synchronized (threadLock) {
            SharedPreferences sharedPreferences = mSp;
            if (sharedPreferences == null) {
                Log.e(TAG, "remove failed, SPUtil is not initialized!");
                return;
            }
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.remove(str);
            editorEdit.commit();
        }
    }
}
