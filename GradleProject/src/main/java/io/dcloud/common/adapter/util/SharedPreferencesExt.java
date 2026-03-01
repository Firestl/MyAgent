package io.dcloud.common.adapter.util;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class SharedPreferencesExt implements SharedPreferences {
    public static final byte BOOLEAN = 2;
    public static final byte FLOAT = 3;
    public static final byte INT = 1;
    public static final byte LONG = 4;
    public static final String N_BASE = "pdr";
    public static final byte STRING = 5;
    public static final byte STRING_SET = 6;
    public static final HashMap<String, LinkedHashMap> mLinkedHashMapCollenction = new HashMap<>(4);
    public W2AEditor editor;
    public File mFileHandler;
    public long mFileModifyTime;
    public LinkedHashMap<String, Object> mKeyValue;

    public static class W2AEditor implements SharedPreferences.Editor {
        public LinkedHashMap<String, Object> mCache;
        public SharedPreferencesExt mSP;

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            this.mSP.mKeyValue.putAll(this.mCache);
            this.mCache.clear();
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            this.mCache.clear();
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            this.mSP.mKeyValue.putAll(this.mCache);
            this.mSP.saveLocal();
            this.mCache.clear();
            return false;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            this.mCache.put(str, Boolean.valueOf(z));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f) {
            this.mCache.put(str, Float.valueOf(f));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i) {
            this.mCache.put(str, Integer.valueOf(i));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j) {
            this.mCache.put(str, Long.valueOf(j));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, String str2) {
            this.mCache.put(str, str2);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, Set<String> set) {
            this.mCache.put(str, set);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String str) {
            this.mCache.remove(str);
            return this;
        }

        public W2AEditor(SharedPreferencesExt sharedPreferencesExt) {
            this.mCache = new LinkedHashMap<>(16);
            this.mSP = null;
            this.mSP = sharedPreferencesExt;
        }
    }

    public SharedPreferencesExt(Context context, String str) {
        this(context, str, 0);
    }

    private void checkModify() {
        if (this.mFileModifyTime != this.mFileHandler.lastModified()) {
            reset();
        }
    }

    private void reset() {
        try {
            this.mKeyValue.clear();
            this.mFileModifyTime = this.mFileHandler.lastModified();
            FileInputStream fileInputStream = new FileInputStream(this.mFileHandler);
            byte[] bArr = new byte[4];
            byte[] bArr2 = new byte[1];
            byte[] bArr3 = new byte[256];
            while (fileInputStream.read(bArr) > 0) {
                int i = ByteUtil.toInt(bArr);
                if (i > bArr3.length) {
                    bArr3 = new byte[i];
                }
                String str = new String(bArr3, 0, fileInputStream.read(bArr3, 0, i), "UTF-8");
                fileInputStream.read(bArr2);
                byte b = bArr2[0];
                fileInputStream.read(bArr);
                int i2 = ByteUtil.toInt(bArr);
                if (i2 > bArr3.length) {
                    bArr3 = new byte[i2];
                }
                load(this.mKeyValue, str, bArr3, fileInputStream.read(bArr3, 0, i2), b);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean saveLocal() {
        save(this.mKeyValue);
        return false;
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        checkModify();
        return this.mKeyValue.containsKey(str);
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        checkModify();
        return this.editor;
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        checkModify();
        return (HashMap) this.mKeyValue.clone();
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z) {
        try {
            checkModify();
            return this.mKeyValue.containsKey(str) ? ((Boolean) this.mKeyValue.get(str)).booleanValue() : z;
        } catch (Exception e2) {
            e2.printStackTrace();
            return z;
        }
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String str, float f) {
        try {
            checkModify();
            return this.mKeyValue.containsKey(str) ? ((Float) this.mKeyValue.get(str)).floatValue() : f;
        } catch (Exception e2) {
            e2.printStackTrace();
            return f;
        }
    }

    @Override // android.content.SharedPreferences
    public int getInt(String str, int i) {
        try {
            checkModify();
            return this.mKeyValue.containsKey(str) ? ((Integer) this.mKeyValue.get(str)).intValue() : i;
        } catch (Exception e2) {
            e2.printStackTrace();
            return i;
        }
    }

    @Override // android.content.SharedPreferences
    public long getLong(String str, long j) {
        try {
            checkModify();
            return this.mKeyValue.containsKey(str) ? ((Long) this.mKeyValue.get(str)).longValue() : j;
        } catch (Exception e2) {
            e2.printStackTrace();
            return j;
        }
    }

    @Override // android.content.SharedPreferences
    public String getString(String str, String str2) {
        try {
            checkModify();
            return this.mKeyValue.containsKey(str) ? (String) this.mKeyValue.get(str) : str2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return str2;
        }
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String str, Set<String> set) {
        try {
            checkModify();
            return (Set) this.mKeyValue.get(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return set;
        }
    }

    public boolean hasChaged() {
        return this.mFileHandler.lastModified() != this.mFileModifyTime;
    }

    public void load(LinkedHashMap<String, Object> linkedHashMap, String str, byte[] bArr, int i, byte b) {
        int i2 = 0;
        switch (b) {
            case 1:
                linkedHashMap.put(str, Integer.valueOf(ByteUtil.toInt(new byte[]{bArr[0], bArr[1], bArr[2], bArr[3]})));
                break;
            case 2:
                linkedHashMap.put(str, Boolean.valueOf(bArr[0] == 1));
                break;
            case 3:
                byte[] bArr2 = new byte[i];
                while (i2 < i && i2 < bArr.length) {
                    bArr2[i2] = bArr[i2];
                    i2++;
                }
                linkedHashMap.put(str, Float.valueOf(ByteUtil.bytesToFloat(bArr2)));
                break;
            case 4:
                byte[] bArr3 = new byte[i];
                while (i2 < i && i2 < bArr.length) {
                    bArr3[i2] = bArr[i2];
                    i2++;
                }
                linkedHashMap.put(str, Long.valueOf(ByteUtil.bytesToLong(bArr3)));
                break;
            case 5:
                try {
                    linkedHashMap.put(str, new String(bArr, 0, i, "UTF-8"));
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                    return;
                }
                break;
            case 6:
                try {
                    linkedHashMap.put(str, new String(bArr, 0, i, "UTF-8"));
                } catch (UnsupportedEncodingException e3) {
                    e3.printStackTrace();
                    return;
                }
                break;
        }
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }

    public void save(LinkedHashMap<String, Object> linkedHashMap) {
        byte[] bytes;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.mFileHandler, false);
            byte[] bArr = new byte[1];
            for (Map.Entry<String, Object> entry : linkedHashMap.entrySet()) {
                byte[] bytes2 = entry.getKey().getBytes("UTF-8");
                fileOutputStream.write(ByteUtil.toBytes(bytes2.length));
                fileOutputStream.write(bytes2);
                Object value = entry.getValue();
                Class<?> cls = value.getClass();
                if (cls == Integer.TYPE || cls == Integer.class) {
                    bArr[0] = 1;
                    bytes = ByteUtil.toBytes(((Integer) value).intValue());
                } else if (cls == Boolean.TYPE || cls == Boolean.class) {
                    bArr[0] = 2;
                    bytes = ((Boolean) value).booleanValue() ? new byte[]{1} : new byte[]{0};
                } else if (cls == String.class) {
                    bArr[0] = 5;
                    bytes = ((String) value).getBytes("UTF-8");
                } else if (cls == Long.TYPE || cls == Long.class) {
                    bArr[0] = 4;
                    bytes = ByteUtil.longToBytes(((Long) value).longValue());
                } else if (cls == Float.TYPE || cls == Float.class) {
                    bArr[0] = 3;
                    bytes = ByteUtil.floatToBytes(((Float) value).floatValue());
                } else if (cls == Set.class) {
                    bArr[0] = 6;
                    bytes = value.toString().getBytes("UTF-8");
                }
                fileOutputStream.write(bArr);
                fileOutputStream.write(ByteUtil.toBytes(bytes.length));
                fileOutputStream.write(bytes);
            }
            this.mFileModifyTime = this.mFileHandler.lastModified();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }

    public SharedPreferencesExt(Context context, String str, int i) {
        this.editor = null;
        this.mFileHandler = null;
        this.mFileModifyTime = System.currentTimeMillis();
        this.mKeyValue = null;
        this.mKeyValue = mLinkedHashMapCollenction.get(str);
        this.editor = new W2AEditor();
        String str2 = "/data/data/" + context.getPackageName() + "/shared_prefs_ext/" + str;
        if (this.mKeyValue != null) {
            this.mFileHandler = new File(str2);
            return;
        }
        this.mKeyValue = new LinkedHashMap<>(16);
        File file = new File(str2);
        this.mFileHandler = file;
        if (!file.getParentFile().exists()) {
            this.mFileHandler.getParentFile().mkdirs();
        }
        if (this.mFileHandler.exists()) {
            reset();
        } else {
            try {
                this.mFileHandler.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        mLinkedHashMapCollenction.put(str, this.mKeyValue);
    }
}
