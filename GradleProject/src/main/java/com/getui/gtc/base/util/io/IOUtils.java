package com.getui.gtc.base.util.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class IOUtils {
    public static final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    public static final char pad = '=';

    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    public static void copy(byte[] bArr, OutputStream outputStream) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            byte[] bArr2 = new byte[1024];
            while (true) {
                int i = byteArrayInputStream.read(bArr2);
                if (i == -1) {
                    return;
                } else {
                    outputStream.write(bArr2, 0, i);
                }
            }
        } catch (IOException e2) {
            safeClose(byteArrayInputStream);
            throw e2;
        }
    }

    public static void encode(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, i);
        copy(inputStream, base64OutputStream);
        base64OutputStream.commit();
    }

    public static byte[] encode(byte[] bArr, int i) throws RuntimeException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                encode(byteArrayInputStream, byteArrayOutputStream, i);
                safeClose(byteArrayInputStream);
                safeClose(byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e2) {
                throw new RuntimeException("Unexpected I/O error", e2);
            }
        } catch (Throwable th) {
            safeClose(byteArrayInputStream);
            safeClose(byteArrayOutputStream);
            throw th;
        }
    }

    public static byte[] gzip(byte[] bArr) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        GZIPOutputStream gZIPOutputStream;
        GZIPOutputStream gZIPOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            } catch (Throwable th) {
                th = th;
            }
            try {
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.finish();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                safeClose(gZIPOutputStream);
                safeClose(byteArrayOutputStream);
                return byteArray;
            } catch (Throwable th2) {
                th = th2;
                gZIPOutputStream2 = gZIPOutputStream;
                safeClose(gZIPOutputStream2);
                safeClose(byteArrayOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
        }
    }

    public static byte[] readFile(File file) throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = null;
            }
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            byteArrayOutputStream = null;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                fileInputStream.close();
            } catch (IOException unused) {
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused2) {
            }
            return byteArray;
        } catch (Throwable th4) {
            th = th4;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused3) {
                }
            }
            if (byteArrayOutputStream == null) {
                throw th;
            }
            try {
                byteArrayOutputStream.close();
                throw th;
            } catch (IOException unused4) {
                throw th;
            }
        }
    }

    public static void safeClose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void saveToFile(InputStream inputStream, File file) throws Throwable {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    safeClose(fileOutputStream);
                    return;
                }
                fileOutputStream.write(bArr, 0, i);
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            safeClose(fileOutputStream2);
            throw th;
        }
    }

    public static void saveToFile(InputStream inputStream, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(str);
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    safeClose(fileOutputStream);
                    return;
                }
                fileOutputStream.write(bArr, 0, i);
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            safeClose(fileOutputStream2);
            throw th;
        }
    }

    public static void saveToFile(byte[] bArr, File file) throws Throwable {
        FileOutputStream fileOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
        try {
            byte[] bArr2 = new byte[1024];
            while (true) {
                int i = byteArrayInputStream.read(bArr2);
                if (i == -1) {
                    safeClose(byteArrayInputStream);
                    safeClose(fileOutputStream);
                    return;
                }
                fileOutputStream.write(bArr2, 0, i);
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayInputStream2 = byteArrayInputStream;
            safeClose(byteArrayInputStream2);
            safeClose(fileOutputStream);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r5v6, types: [java.io.Closeable, java.util.zip.GZIPInputStream] */
    public static byte[] unGzip(byte[] bArr) throws Throwable {
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        ?? gZIPInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
        } catch (Throwable th2) {
            th = th2;
            byteArrayInputStream = null;
            byteArrayOutputStream = null;
        }
        try {
            gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int i = gZIPInputStream.read();
                        if (i == -1) {
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            safeClose(gZIPInputStream);
                            safeClose(byteArrayOutputStream);
                            safeClose(byteArrayInputStream);
                            return byteArray;
                        }
                        byteArrayOutputStream.write(i);
                    } catch (Throwable th3) {
                        th = th3;
                        safeClose(gZIPInputStream);
                        safeClose(byteArrayOutputStream);
                        safeClose(byteArrayInputStream);
                        throw th;
                    }
                }
            } catch (Throwable th4) {
                byteArrayOutputStream = null;
                th = th4;
            }
        } catch (Throwable th5) {
            th = th5;
            byteArrayOutputStream = null;
            th = th;
            gZIPInputStream = byteArrayOutputStream;
            safeClose(gZIPInputStream);
            safeClose(byteArrayOutputStream);
            safeClose(byteArrayInputStream);
            throw th;
        }
    }
}
