package com.igexin.push.g;

import com.loopj.android.http.RequestParams;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3594a;
    public String b;
    public File c;
    public byte[] d;

    public i(File file) {
        this.c = file;
    }

    public i(String str) {
        this(new File(str));
    }

    public i(String str, byte[] bArr) {
        this.f3594a = str;
        this.d = bArr;
    }

    public i(String str, byte[] bArr, String str2) {
        this(str, bArr);
        this.b = str2;
    }

    public i(byte[] bArr) {
        this.d = bArr;
    }

    public static String a(byte[] bArr) {
        Object obj = null;
        if (bArr != null && bArr.length >= 10) {
            if (bArr[0] == 71 && bArr[1] == 73 && bArr[2] == 70) {
                obj = "GIF";
            } else if (bArr[1] == 80 && bArr[2] == 78 && bArr[3] == 71) {
                obj = "PNG";
            } else if (bArr[6] == 74 && bArr[7] == 70 && bArr[8] == 73 && bArr[9] == 70) {
                obj = "JPG";
            } else if (bArr[0] == 66 && bArr[1] == 77) {
                obj = "BMP";
            }
        }
        return "JPG".equals(obj) ? "image/jpeg" : "GIF".equals(obj) ? "image/gif" : "PNG".equals(obj) ? "image/png" : "BMP".equals(obj) ? "image/bmp" : RequestParams.APPLICATION_OCTET_STREAM;
    }

    private String b() {
        File file;
        if (this.f3594a == null && (file = this.c) != null && file.exists()) {
            this.f3594a = this.c.getName();
        }
        return this.f3594a;
    }

    public static String b(byte[] bArr) {
        if (bArr != null && bArr.length >= 10) {
            if (bArr[0] == 71 && bArr[1] == 73 && bArr[2] == 70) {
                return "GIF";
            }
            if (bArr[1] == 80 && bArr[2] == 78 && bArr[3] == 71) {
                return "PNG";
            }
            if (bArr[6] == 74 && bArr[7] == 70 && bArr[8] == 73 && bArr[9] == 70) {
                return "JPG";
            }
            if (bArr[0] == 66 && bArr[1] == 77) {
                return "BMP";
            }
        }
        return null;
    }

    private String c() throws Throwable {
        if (this.b == null) {
            byte[] bArrA = a();
            Object obj = null;
            if (bArrA != null && bArrA.length >= 10) {
                if (bArrA[0] == 71 && bArrA[1] == 73 && bArrA[2] == 70) {
                    obj = "GIF";
                } else if (bArrA[1] == 80 && bArrA[2] == 78 && bArrA[3] == 71) {
                    obj = "PNG";
                } else if (bArrA[6] == 74 && bArrA[7] == 70 && bArrA[8] == 73 && bArrA[9] == 70) {
                    obj = "JPG";
                } else if (bArrA[0] == 66 && bArrA[1] == 77) {
                    obj = "BMP";
                }
            }
            this.b = "JPG".equals(obj) ? "image/jpeg" : "GIF".equals(obj) ? "image/gif" : "PNG".equals(obj) ? "image/png" : "BMP".equals(obj) ? "image/bmp" : RequestParams.APPLICATION_OCTET_STREAM;
        }
        return this.b;
    }

    public final byte[] a() throws Throwable {
        File file;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        FileInputStream fileInputStream;
        if (this.d == null && (file = this.c) != null && file.exists()) {
            try {
                fileInputStream = new FileInputStream(this.c);
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        try {
                            int i = fileInputStream.read();
                            if (i == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(i);
                        } catch (Throwable th2) {
                            th = th2;
                            if (byteArrayOutputStream != null) {
                                byteArrayOutputStream.close();
                            }
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            throw th;
                        }
                    }
                    this.d = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    fileInputStream.close();
                } catch (Throwable th3) {
                    byteArrayOutputStream = null;
                    th = th3;
                }
            } catch (Throwable th4) {
                byteArrayOutputStream = null;
                th = th4;
                fileInputStream = null;
            }
        }
        return this.d;
    }
}
