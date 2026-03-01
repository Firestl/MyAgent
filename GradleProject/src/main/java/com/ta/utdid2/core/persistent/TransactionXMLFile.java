package com.ta.utdid2.core.persistent;

import com.efs.sdk.base.newsharedpreferences.SharedPreferencesNewImpl;
import com.ta.utdid2.core.persistent.MySharedPreferences;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class TransactionXMLFile {
    public static final Object GLOBAL_COMMIT_LOCK = new Object();
    public static final int MODE_PRIVATE = 0;
    public static final int MODE_WORLD_READABLE = 1;
    public static final int MODE_WORLD_WRITEABLE = 2;
    public File mPreferencesDir;
    public final Object mSync = new Object();
    public HashMap<File, MySharedPreferencesImpl> sSharedPrefs = new HashMap<>();

    public static final class MySharedPreferencesImpl implements MySharedPreferences {
        public static final Object mContent = new Object();
        public boolean hasChange = false;
        public final File mBackupFile;
        public final File mFile;
        public WeakHashMap<MySharedPreferences.OnSharedPreferenceChangeListener, Object> mListeners;
        public Map mMap;
        public final int mMode;

        public final class EditorImpl implements MySharedPreferences.MyEditor {
            public final Map<String, Object> mModified = new HashMap();
            public boolean mClear = false;

            public EditorImpl() {
            }

            @Override // com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor
            public MySharedPreferences.MyEditor clear() {
                synchronized (this) {
                    this.mClear = true;
                }
                return this;
            }

            @Override // com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor
            public boolean commit() {
                boolean z;
                ArrayList arrayList;
                HashSet<MySharedPreferences.OnSharedPreferenceChangeListener> hashSet;
                boolean zWriteFileLocked;
                synchronized (TransactionXMLFile.GLOBAL_COMMIT_LOCK) {
                    z = MySharedPreferencesImpl.this.mListeners.size() > 0;
                    arrayList = null;
                    if (z) {
                        arrayList = new ArrayList();
                        hashSet = new HashSet(MySharedPreferencesImpl.this.mListeners.keySet());
                    } else {
                        hashSet = null;
                    }
                    synchronized (this) {
                        if (this.mClear) {
                            MySharedPreferencesImpl.this.mMap.clear();
                            this.mClear = false;
                        }
                        for (Map.Entry<String, Object> entry : this.mModified.entrySet()) {
                            String key = entry.getKey();
                            Object value = entry.getValue();
                            if (value == this) {
                                MySharedPreferencesImpl.this.mMap.remove(key);
                            } else {
                                MySharedPreferencesImpl.this.mMap.put(key, value);
                            }
                            if (z) {
                                arrayList.add(key);
                            }
                        }
                        this.mModified.clear();
                    }
                    zWriteFileLocked = MySharedPreferencesImpl.this.writeFileLocked();
                    if (zWriteFileLocked) {
                        MySharedPreferencesImpl.this.setHasChange(true);
                    }
                }
                if (z) {
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        String str = (String) arrayList.get(size);
                        for (MySharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : hashSet) {
                            if (onSharedPreferenceChangeListener != null) {
                                onSharedPreferenceChangeListener.onSharedPreferenceChanged(MySharedPreferencesImpl.this, str);
                            }
                        }
                    }
                }
                return zWriteFileLocked;
            }

            @Override // com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor
            public MySharedPreferences.MyEditor putBoolean(String str, boolean z) {
                synchronized (this) {
                    this.mModified.put(str, Boolean.valueOf(z));
                }
                return this;
            }

            @Override // com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor
            public MySharedPreferences.MyEditor putFloat(String str, float f) {
                synchronized (this) {
                    this.mModified.put(str, Float.valueOf(f));
                }
                return this;
            }

            @Override // com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor
            public MySharedPreferences.MyEditor putInt(String str, int i) {
                synchronized (this) {
                    this.mModified.put(str, Integer.valueOf(i));
                }
                return this;
            }

            @Override // com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor
            public MySharedPreferences.MyEditor putLong(String str, long j) {
                synchronized (this) {
                    this.mModified.put(str, Long.valueOf(j));
                }
                return this;
            }

            @Override // com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor
            public MySharedPreferences.MyEditor putString(String str, String str2) {
                synchronized (this) {
                    this.mModified.put(str, str2);
                }
                return this;
            }

            @Override // com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor
            public MySharedPreferences.MyEditor remove(String str) {
                synchronized (this) {
                    this.mModified.put(str, this);
                }
                return this;
            }
        }

        public MySharedPreferencesImpl(File file, int i, Map map) {
            this.mFile = file;
            this.mBackupFile = TransactionXMLFile.makeBackupFile(file);
            this.mMode = i;
            this.mMap = map == null ? new HashMap() : map;
            this.mListeners = new WeakHashMap<>();
        }

        private FileOutputStream createFileOutputStream(File file) {
            FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException unused) {
                if (!file.getParentFile().mkdir()) {
                    return null;
                }
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (FileNotFoundException unused2) {
                    return null;
                }
            }
            return fileOutputStream;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean writeFileLocked() {
            if (this.mFile.exists()) {
                if (this.mBackupFile.exists()) {
                    this.mFile.delete();
                } else if (!this.mFile.renameTo(this.mBackupFile)) {
                    return false;
                }
            }
            try {
                FileOutputStream fileOutputStreamCreateFileOutputStream = createFileOutputStream(this.mFile);
                if (fileOutputStreamCreateFileOutputStream == null) {
                    return false;
                }
                XmlUtils.writeMapXml(this.mMap, fileOutputStreamCreateFileOutputStream);
                fileOutputStreamCreateFileOutputStream.close();
                this.mBackupFile.delete();
                return true;
            } catch (IOException | XmlPullParserException unused) {
                if (this.mFile.exists()) {
                    this.mFile.delete();
                }
                return false;
            }
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public boolean checkFile() {
            return this.mFile != null && new File(this.mFile.getAbsolutePath()).exists();
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public boolean contains(String str) {
            boolean zContainsKey;
            synchronized (this) {
                zContainsKey = this.mMap.containsKey(str);
            }
            return zContainsKey;
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public MySharedPreferences.MyEditor edit() {
            return new EditorImpl();
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public Map<String, ?> getAll() {
            HashMap map;
            synchronized (this) {
                map = new HashMap(this.mMap);
            }
            return map;
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public boolean getBoolean(String str, boolean z) {
            synchronized (this) {
                Boolean bool = (Boolean) this.mMap.get(str);
                if (bool != null) {
                    z = bool.booleanValue();
                }
            }
            return z;
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public float getFloat(String str, float f) {
            synchronized (this) {
                Float f2 = (Float) this.mMap.get(str);
                if (f2 != null) {
                    f = f2.floatValue();
                }
            }
            return f;
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public int getInt(String str, int i) {
            synchronized (this) {
                Integer num = (Integer) this.mMap.get(str);
                if (num != null) {
                    i = num.intValue();
                }
            }
            return i;
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public long getLong(String str, long j) {
            synchronized (this) {
                Long l = (Long) this.mMap.get(str);
                if (l != null) {
                    j = l.longValue();
                }
            }
            return j;
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public String getString(String str, String str2) {
            synchronized (this) {
                String str3 = (String) this.mMap.get(str);
                if (str3 != null) {
                    str2 = str3;
                }
            }
            return str2;
        }

        public boolean hasFileChanged() {
            boolean z;
            synchronized (this) {
                z = this.hasChange;
            }
            return z;
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public void registerOnSharedPreferenceChangeListener(MySharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
            synchronized (this) {
                this.mListeners.put(onSharedPreferenceChangeListener, mContent);
            }
        }

        public void replace(Map map) {
            if (map != null) {
                synchronized (this) {
                    this.mMap = map;
                }
            }
        }

        public void setHasChange(boolean z) {
            synchronized (this) {
                this.hasChange = z;
            }
        }

        @Override // com.ta.utdid2.core.persistent.MySharedPreferences
        public void unregisterOnSharedPreferenceChangeListener(MySharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
            synchronized (this) {
                this.mListeners.remove(onSharedPreferenceChangeListener);
            }
        }
    }

    public TransactionXMLFile(String str) {
        if (str == null || str.length() <= 0) {
            throw new RuntimeException("Directory can not be empty");
        }
        this.mPreferencesDir = new File(str);
    }

    private File getPreferencesDir() {
        File file;
        synchronized (this.mSync) {
            file = this.mPreferencesDir;
        }
        return file;
    }

    private File getSharedPrefsFile(String str) {
        return makeFilename(getPreferencesDir(), str + ".xml");
    }

    public static File makeBackupFile(File file) {
        return new File(file.getPath() + SharedPreferencesNewImpl.BACKUP_FILE_SUFFIX);
    }

    private File makeFilename(File file, String str) {
        if (str.indexOf(File.separatorChar) < 0) {
            return new File(file, str);
        }
        throw new IllegalArgumentException("File " + str + " contains a path separator");
    }

    public MySharedPreferences getMySharedPreferences(String str, int i) {
        File sharedPrefsFile = getSharedPrefsFile(str);
        synchronized (GLOBAL_COMMIT_LOCK) {
            MySharedPreferencesImpl mySharedPreferencesImpl = this.sSharedPrefs.get(sharedPrefsFile);
            if (mySharedPreferencesImpl != null && !mySharedPreferencesImpl.hasFileChanged()) {
                return mySharedPreferencesImpl;
            }
            File fileMakeBackupFile = makeBackupFile(sharedPrefsFile);
            if (fileMakeBackupFile.exists()) {
                sharedPrefsFile.delete();
                fileMakeBackupFile.renameTo(sharedPrefsFile);
            }
            if (sharedPrefsFile.exists()) {
                sharedPrefsFile.canRead();
            }
            HashMap mapXml = null;
            if (sharedPrefsFile.exists() && sharedPrefsFile.canRead()) {
                try {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(sharedPrefsFile);
                        mapXml = XmlUtils.readMapXml(fileInputStream);
                        fileInputStream.close();
                    } catch (FileNotFoundException e2) {
                        e2.printStackTrace();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                } catch (FileNotFoundException | IOException unused) {
                } catch (XmlPullParserException unused2) {
                    FileInputStream fileInputStream2 = new FileInputStream(sharedPrefsFile);
                    int iAvailable = fileInputStream2.available();
                    byte[] bArr = new byte[iAvailable];
                    fileInputStream2.read(bArr);
                    new String(bArr, 0, iAvailable, "UTF-8");
                }
            }
            synchronized (GLOBAL_COMMIT_LOCK) {
                if (mySharedPreferencesImpl != null) {
                    mySharedPreferencesImpl.replace(mapXml);
                } else {
                    mySharedPreferencesImpl = this.sSharedPrefs.get(sharedPrefsFile);
                    if (mySharedPreferencesImpl == null) {
                        mySharedPreferencesImpl = new MySharedPreferencesImpl(sharedPrefsFile, i, mapXml);
                        this.sSharedPrefs.put(sharedPrefsFile, mySharedPreferencesImpl);
                    }
                }
            }
            return mySharedPreferencesImpl;
        }
    }
}
