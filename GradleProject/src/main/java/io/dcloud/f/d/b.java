package io.dcloud.f.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.util.Md5Utils;
import io.dcloud.f.d.a;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes2.dex */
public class b {
    public static b d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f6515a;
    public a.c b;
    public String c;

    public b(Context context, String str) {
        this.f6515a = context;
        this.c = str;
        if (this.b == null) {
            try {
                String strA = a(context);
                SharedPreferences orCreateBundle = SP.getOrCreateBundle(context, str);
                String string = orCreateBundle.getString(strA, null);
                if (string == null) {
                    this.b = a.c();
                    orCreateBundle.edit().putString(strA, this.b.toString()).commit();
                } else {
                    this.b = a.a(string);
                }
            } catch (GeneralSecurityException unused) {
            }
        }
    }

    public static b a(Context context, String str) {
        if (d == null) {
            synchronized (b.class) {
                if (d == null) {
                    d = new b(context, str);
                }
            }
        }
        return d;
    }

    public static String c(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
            messageDigest.update(bytes, 0, bytes.length);
            return Base64.encodeToString(messageDigest.digest(), 2);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public void b(String str, String str2) {
        try {
            File dir = this.f6515a.getDir(Md5Utils.md5(this.c), 0);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, Md5Utils.md5(str));
            if (!file.exists()) {
                try {
                    dir.createNewFile();
                } catch (IOException unused) {
                }
            }
            try {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(b(str2));
                bufferedWriter.flush();
                bufferedWriter.close();
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException unused2) {
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private String a(Context context) throws GeneralSecurityException {
        return c(a.a(context.getPackageName(), context.getPackageName().getBytes(), 1000).toString());
    }

    public String a(String str, String str2) {
        File file = new File(this.f6515a.getDir(Md5Utils.md5(this.c), 0).getAbsolutePath() + "/" + Md5Utils.md5(str));
        if (file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        sb.append(line);
                    } else {
                        bufferedReader.close();
                        fileReader.close();
                        return a(sb.toString());
                    }
                }
            } catch (IOException unused) {
            }
        }
        return str2;
    }

    private String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return a.a(str, this.b).toString();
        } catch (UnsupportedEncodingException | GeneralSecurityException unused) {
            return null;
        }
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return a.b(new a.C0167a(str), this.b);
        } catch (UnsupportedEncodingException | GeneralSecurityException unused) {
            return null;
        }
    }
}
