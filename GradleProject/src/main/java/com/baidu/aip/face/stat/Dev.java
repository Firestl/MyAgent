package com.baidu.aip.face.stat;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.umeng.commonsdk.statistics.idtracking.b;
import io.dcloud.common.util.Md5Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class Dev {
    public static final String INSTALLATION = "INSTALLATION";
    public String uniqueID = "";
    public String brand = "";
    public String sysVersion = "";
    public String packagename = "";
    public String sdkVersion = "2.1.0.0";
    public boolean firstRun = false;

    private String generateUniquePsuedoID(Context context) {
        String string;
        String str = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);
        try {
            string = Build.class.getField("SERIAL").get(null).toString();
        } catch (Exception unused) {
            string = UUID.randomUUID().toString();
        }
        String string2 = Settings.Secure.getString(context.getContentResolver(), b.f5435a);
        if ("9774d56d682e549c".equals(string2)) {
            string2 = UUID.randomUUID().toString();
        }
        return md5(string + string2 + str);
    }

    public static String md5(String str) {
        try {
            String string = new BigInteger(1, MessageDigest.getInstance(Md5Utils.ALGORITHM).digest(str.getBytes())).toString(16);
            while (string.length() < 32) {
                string = "0" + string;
            }
            return string;
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.String] */
    private String readInstallationFile(File file) throws Throwable {
        RandomAccessFile randomAccessFile = 0;
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                RandomAccessFile randomAccessFile3 = new RandomAccessFile(file, "r");
                try {
                    byte[] bArr = new byte[(int) randomAccessFile3.length()];
                    randomAccessFile3.readFully(bArr);
                    String str = new String(bArr);
                    randomAccessFile3.close();
                    randomAccessFile = str;
                } catch (Exception e2) {
                    e = e2;
                    randomAccessFile2 = randomAccessFile3;
                    e.printStackTrace();
                    if (randomAccessFile2 != null) {
                        randomAccessFile2.close();
                    }
                    randomAccessFile = "";
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile = randomAccessFile3;
                    if (randomAccessFile != 0) {
                        randomAccessFile.close();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
            }
            return randomAccessFile;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x003c -> B:36:0x003f). Please report as a decompilation issue!!! */
    private void writeInstallationFile(Context context, String str) throws Throwable {
        FileOutputStream fileOutputStream = null;
        try {
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(new File(context.getFilesDir(), INSTALLATION));
                try {
                    fileOutputStream2.write(str.getBytes());
                    fileOutputStream2.close();
                } catch (FileNotFoundException e3) {
                    e = e3;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (IOException e4) {
                    e = e4;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e6) {
                e = e6;
            } catch (IOException e7) {
                e = e7;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public String getBrand() {
        return this.brand;
    }

    public boolean getFirstRun() {
        return this.firstRun;
    }

    public String getPackagename() {
        return this.packagename;
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public String getSysVersion() {
        return this.sysVersion;
    }

    public String getUniqueID() {
        return this.uniqueID;
    }

    public synchronized String getUniqueIdFromFile(Context context) {
        if (TextUtils.isEmpty(this.uniqueID)) {
            try {
                this.uniqueID = readInstallationFile(new File(context.getFilesDir(), INSTALLATION));
            } catch (IOException e2) {
                e2.printStackTrace();
                this.uniqueID = "uncreate";
            }
        }
        return this.uniqueID;
    }

    public void init(Context context) throws Throwable {
        if (context == null) {
            return;
        }
        this.brand = Build.MODEL.replace(Operators.SPACE_STR, "");
        this.sysVersion = Build.VERSION.RELEASE;
        this.packagename = context.getPackageName();
        String uniqueIdFromFile = getUniqueIdFromFile(context);
        this.uniqueID = uniqueIdFromFile;
        if (TextUtils.isEmpty(uniqueIdFromFile)) {
            this.firstRun = true;
            String strGenerateUniquePsuedoID = generateUniquePsuedoID(context);
            this.uniqueID = strGenerateUniquePsuedoID;
            writeInstallationFile(context, strGenerateUniquePsuedoID);
        }
    }

    public void setFirstRun(boolean z) {
        this.firstRun = z;
    }

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }
}
