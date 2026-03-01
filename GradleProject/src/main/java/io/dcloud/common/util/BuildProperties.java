package io.dcloud.common.util;

import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class BuildProperties {
    public static String BUILD_PROPERTIES_FILE_NAME = ".buildPropertiesMD5.data";
    public static String BUILD_PROPERTIES_FILE_PATH = "";
    public static volatile BuildProperties ourInstance;
    public Properties properties;

    public BuildProperties() throws Exception {
        Properties properties = new Properties();
        this.properties = properties;
        properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
    }

    public static BuildProperties getInstance() throws Exception {
        if (ourInstance == null) {
            synchronized (BuildProperties.class) {
                if (ourInstance == null) {
                    ourInstance = new BuildProperties();
                }
            }
        }
        return ourInstance;
    }

    public boolean containsKey(Object obj) {
        return this.properties.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.properties.containsValue(obj);
    }

    public Set<Map.Entry<Object, Object>> entrySet() {
        return this.properties.entrySet();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0067 A[Catch: Exception -> 0x00a8, TryCatch #0 {Exception -> 0x00a8, blocks: (B:8:0x001a, B:10:0x002b, B:12:0x0050, B:14:0x005a, B:20:0x0067, B:22:0x0073, B:23:0x007b, B:25:0x0081, B:26:0x0097, B:28:0x009d, B:17:0x0061), top: B:34:0x001a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getBuildPropertiesLimit(android.content.Context r7) throws java.lang.Throwable {
        /*
            r6 = this;
            boolean r0 = io.dcloud.common.adapter.util.DeviceInfo.isSDcardExists()
            java.lang.String r1 = ""
            if (r0 == 0) goto Ld
            java.lang.String r7 = io.dcloud.common.adapter.util.DeviceInfo.sBaseFsCachePath
            io.dcloud.common.util.BuildProperties.BUILD_PROPERTIES_FILE_PATH = r7
            goto L1a
        Ld:
            if (r7 != 0) goto L10
            return r1
        L10:
            java.io.File r7 = r7.getFilesDir()
            java.lang.String r7 = r7.getAbsolutePath()
            io.dcloud.common.util.BuildProperties.BUILD_PROPERTIES_FILE_PATH = r7
        L1a:
            java.io.File r7 = new java.io.File     // Catch: java.lang.Exception -> La8
            java.io.File r0 = android.os.Environment.getRootDirectory()     // Catch: java.lang.Exception -> La8
            java.lang.String r2 = "build.prop"
            r7.<init>(r0, r2)     // Catch: java.lang.Exception -> La8
            java.lang.String r7 = io.dcloud.common.util.Md5Utils.md5(r7)     // Catch: java.lang.Exception -> La8
            if (r7 == 0) goto Lac
            r0 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> La8
            r2.<init>()     // Catch: java.lang.Exception -> La8
            java.lang.String r3 = io.dcloud.common.util.BuildProperties.BUILD_PROPERTIES_FILE_PATH     // Catch: java.lang.Exception -> La8
            r2.append(r3)     // Catch: java.lang.Exception -> La8
            java.lang.String r3 = java.io.File.separator     // Catch: java.lang.Exception -> La8
            r2.append(r3)     // Catch: java.lang.Exception -> La8
            java.lang.String r3 = io.dcloud.common.util.BuildProperties.BUILD_PROPERTIES_FILE_NAME     // Catch: java.lang.Exception -> La8
            r2.append(r3)     // Catch: java.lang.Exception -> La8
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> La8
            java.io.File r3 = new java.io.File     // Catch: java.lang.Exception -> La8
            r3.<init>(r2)     // Catch: java.lang.Exception -> La8
            boolean r4 = r3.exists()     // Catch: java.lang.Exception -> La8
            r5 = 1
            if (r4 == 0) goto L61
            java.lang.String r3 = io.dcloud.common.util.IOUtil.readStringFile(r2)     // Catch: java.lang.Exception -> La8
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> La8
            if (r4 != 0) goto L65
            boolean r3 = r3.equals(r7)     // Catch: java.lang.Exception -> La8
            if (r3 != 0) goto L65
            goto L64
        L61:
            r3.createNewFile()     // Catch: java.lang.Exception -> La8
        L64:
            r0 = 1
        L65:
            if (r0 == 0) goto Lac
            io.dcloud.common.util.IOUtil.writeStringFile(r2, r7)     // Catch: java.lang.Exception -> La8
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch: java.lang.Exception -> La8
            r7.<init>()     // Catch: java.lang.Exception -> La8
            java.util.Properties r0 = r6.properties     // Catch: java.lang.Exception -> La8
            if (r0 == 0) goto L97
            java.util.Set r0 = r0.entrySet()     // Catch: java.lang.Exception -> La8
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Exception -> La8
        L7b:
            boolean r2 = r0.hasNext()     // Catch: java.lang.Exception -> La8
            if (r2 == 0) goto L97
            java.lang.Object r2 = r0.next()     // Catch: java.lang.Exception -> La8
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch: java.lang.Exception -> La8
            java.lang.Object r3 = r2.getKey()     // Catch: java.lang.Exception -> La8
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> La8
            java.lang.Object r2 = r2.getValue()     // Catch: java.lang.Exception -> La8
            r7.put(r3, r2)     // Catch: java.lang.Exception -> La8
            goto L7b
        L97:
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Exception -> La8
            if (r7 == 0) goto Lac
            java.lang.String r0 = r7.trim()     // Catch: java.lang.Exception -> La8
            boolean r0 = r1.equals(r0)     // Catch: java.lang.Exception -> La8
            if (r0 != 0) goto Lac
            return r7
        La8:
            r7 = move-exception
            r7.printStackTrace()
        Lac:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.BuildProperties.getBuildPropertiesLimit(android.content.Context):java.lang.String");
    }

    public String getProperty(String str) {
        return this.properties.getProperty(str);
    }

    public boolean isEmpty() {
        return this.properties.isEmpty();
    }

    public Set keySet() {
        return this.properties.keySet();
    }

    public Enumeration keys() {
        return this.properties.keys();
    }

    public int size() {
        return this.properties.size();
    }

    public Collection values() {
        return this.properties.values();
    }

    public String getProperty(String str, String str2) {
        return this.properties.getProperty(str, str2);
    }
}
