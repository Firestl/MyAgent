package io.dcloud.js.file;

import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.constant.AbsoluteConst;
import java.io.File;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: io.dcloud.js.file.a$a, reason: collision with other inner class name */
    public static class C0196a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f6745a;
        public long b;
        public int c = 0;
        public int d = 0;

        public JSONObject a() {
            try {
                return new JSONObject(String.format(Locale.ENGLISH, "{lastModifiedDate : %d,size : %d,directoryCount : %d,fileCount : %d}", Long.valueOf(this.f6745a), Long.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d)));
            } catch (JSONException e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
    }

    public static JSONObject a(String str, long j, long j2, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("lastModifiedDate", j);
            jSONObject.put("type", str);
            jSONObject.put(AbsoluteConst.JSON_KEY_SIZE, j2);
            jSONObject.put("name", str2);
            jSONObject.put("fullPath", str3);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static JSONArray b(String str, String str2) {
        JSONArray jSONArray = new JSONArray();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        String str3 = File.separator;
        sb.append(str.endsWith(str3) ? "" : str3);
        String string = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str2);
        if (str2.endsWith(str3)) {
            str3 = "";
        }
        sb2.append(str3);
        String string2 = sb2.toString();
        String[] list = new File(string).list();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                String str4 = string + list[i];
                File file = new File(str4);
                JSONObject jSONObject = new JSONObject();
                boolean zIsDirectory = file.isDirectory();
                String str5 = list[i];
                String str6 = string2 + str5;
                try {
                    jSONObject.put("isDirectory", zIsDirectory);
                    jSONObject.put("isFile", !zIsDirectory);
                    jSONObject.put("name", str5);
                    jSONObject.put("remoteURL", str6);
                    jSONObject.put("fullPath", str4);
                    jSONArray.put(jSONObject);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return jSONArray;
    }

    public static JSONObject a(String str, int i, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", str);
            jSONObject.put("type", i);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("name", str2);
            jSONObject2.put("fullPath", str3);
            jSONObject2.put("remoteURL", str4);
            jSONObject.put("root", jSONObject2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static JSONObject a(String str, String str2, String str3, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isDirectory", z);
            jSONObject.put("isFile", !z);
            jSONObject.put("name", str);
            jSONObject.put("remoteURL", str3);
            jSONObject.put("fullPath", str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static JSONObject a(String str, String str2, boolean z, String str3, String str4, int i, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("isDirectory", z);
            jSONObject2.put("isFile", !z);
            jSONObject2.put("name", str);
            jSONObject2.put("remoteURL", str3);
            jSONObject2.put("fullPath", str2);
            jSONObject2.put("fsName", str4);
            jSONObject2.put("type", i);
            jSONObject2.put("fsRoot", jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject2;
    }

    public static JSONObject a(String str, String str2) {
        File file = new File(str);
        return a(str2, file.lastModified(), DHFile.getFileSize(file), file.getName(), str);
    }

    public static JSONObject a(String str, boolean z) {
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        C0196a c0196a = new C0196a();
        c0196a.f6745a = file.lastModified();
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null) {
                for (File file2 : fileArrListFiles) {
                    a(file2, c0196a, z);
                }
            }
        } else {
            c0196a.b = file.length();
        }
        return c0196a.a();
    }

    public static void a(File file, C0196a c0196a, boolean z) {
        File[] fileArrListFiles;
        if (file.isDirectory()) {
            if (z && (fileArrListFiles = file.listFiles()) != null) {
                for (File file2 : fileArrListFiles) {
                    a(file2, c0196a, z);
                }
            }
            c0196a.c++;
            return;
        }
        c0196a.b += file.length();
        c0196a.d++;
    }
}
