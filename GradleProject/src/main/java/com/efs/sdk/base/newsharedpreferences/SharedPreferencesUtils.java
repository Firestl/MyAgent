package com.efs.sdk.base.newsharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class SharedPreferencesUtils {
    public static final HashMap<String, SharedPreferencesWrapper> sSpCache = new HashMap<>();

    public static class SharedPreferencesWrapper {
        public volatile SharedPreferences mSharedPreferences;

        public SharedPreferencesWrapper() {
        }
    }

    public static SharedPreferences getNewSharedPreferences(Context context, String str, boolean z) {
        return getNewSharedPreferences(context, str, z, false);
    }

    public static File getNewSharedPrefsFile(Context context, String str) {
        return new File(context.getApplicationInfo().dataDir, "shared_prefs" + File.separatorChar + str + ".sp");
    }

    public static SharedPreferences getSharedPreferences(Context context, String str) {
        return getSharedPreferences(context, str, false);
    }

    public static synchronized void handleReplace(Context context, String str, boolean z) {
        if (context == null) {
            return;
        }
        SharedPreferences newSharedPreferences = getNewSharedPreferences(context, "sp_replace_flag", z, true);
        if (!newSharedPreferences.contains(str)) {
            SharedPreferences newSharedPreferences2 = getNewSharedPreferences(context, str, z, true);
            SharedPreferences.Editor editorEdit = newSharedPreferences2.edit();
            if (((SharedPreferencesNewImpl) newSharedPreferences2).getModifyID() <= 1) {
                Map<String, ?> all = context.getSharedPreferences(str, 0).getAll();
                Log.e("caisq", "replace " + str + "   " + all.size() + "   " + newSharedPreferences2.hashCode());
                if (all.size() > 0) {
                    for (Map.Entry<String, ?> entry : all.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if (key != null && key.trim().length() > 0 && value != null) {
                            if (value instanceof String) {
                                editorEdit.putString(key, (String) value);
                            } else if (value instanceof Long) {
                                editorEdit.putLong(key, ((Long) value).longValue());
                            } else if (value instanceof Integer) {
                                editorEdit.putInt(key, ((Integer) value).intValue());
                            } else if (value instanceof Float) {
                                editorEdit.putFloat(key, ((Float) value).floatValue());
                            } else if (value instanceof Boolean) {
                                editorEdit.putBoolean(key, ((Boolean) value).booleanValue());
                            }
                        }
                    }
                    editorEdit.apply();
                }
            }
            newSharedPreferences.edit().putBoolean(str, true).apply();
        }
    }

    public static Object invokeObjectMethod(Object obj, String str, Class[] clsArr, Object[] objArr) {
        try {
            Method method = obj.getClass().getMethod(str, clsArr);
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void onDestroy() {
        synchronized (sSpCache) {
            if (sSpCache.size() > 0) {
                Iterator<SharedPreferencesWrapper> it = sSpCache.values().iterator();
                while (it.hasNext()) {
                    SharedPreferences sharedPreferences = it.next().mSharedPreferences;
                    if (sharedPreferences != null) {
                        ((SharedPreferencesNewImpl) sharedPreferences).onDestroy();
                    }
                }
            }
        }
    }

    public static SharedPreferences getNewSharedPreferences(Context context, String str, boolean z, boolean z2) {
        SharedPreferencesWrapper sharedPreferencesWrapper;
        synchronized (sSpCache) {
            sharedPreferencesWrapper = sSpCache.get(str);
            if (sharedPreferencesWrapper == null) {
                sharedPreferencesWrapper = new SharedPreferencesWrapper();
                sSpCache.put(str, sharedPreferencesWrapper);
            }
        }
        if (sharedPreferencesWrapper.mSharedPreferences == null) {
            synchronized (sharedPreferencesWrapper) {
                if (sharedPreferencesWrapper.mSharedPreferences == null && sharedPreferencesWrapper.mSharedPreferences == null) {
                    sharedPreferencesWrapper.mSharedPreferences = new SharedPreferencesNewImpl(getNewSharedPrefsFile(context, str), z);
                }
            }
        }
        return sharedPreferencesWrapper.mSharedPreferences;
    }

    public static SharedPreferences getSharedPreferences(Context context, String str, boolean z) {
        return getNewSharedPreferences(context, str, z);
    }
}
