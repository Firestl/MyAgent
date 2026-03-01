package io.dcloud.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/* JADX INFO: loaded from: classes2.dex */
public class IOUtil {
    public static final int BUF_SIZE = 20480;

    public static final InputStream byte2InputStream(byte[] bArr) {
        return new ByteArrayInputStream(bArr);
    }

    public static void close(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[20480];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                close(byteArrayOutputStream);
                return byteArray;
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0023: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:18:0x0023 */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0026 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String readStringFile(java.lang.String r3) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L15 java.io.IOException -> L17
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L15 java.io.IOException -> L17
            byte[] r3 = getBytes(r1)     // Catch: java.io.IOException -> L13 java.lang.Throwable -> L22
            java.lang.String r2 = new java.lang.String     // Catch: java.io.IOException -> L13 java.lang.Throwable -> L22
            r2.<init>(r3)     // Catch: java.io.IOException -> L13 java.lang.Throwable -> L22
            r1.close()     // Catch: java.io.IOException -> L12
        L12:
            return r2
        L13:
            r3 = move-exception
            goto L19
        L15:
            r3 = move-exception
            goto L24
        L17:
            r3 = move-exception
            r1 = r0
        L19:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L22
            if (r1 == 0) goto L21
            r1.close()     // Catch: java.io.IOException -> L21
        L21:
            return r0
        L22:
            r3 = move-exception
            r0 = r1
        L24:
            if (r0 == 0) goto L29
            r0.close()     // Catch: java.io.IOException -> L29
        L29:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.IOUtil.readStringFile(java.lang.String):java.lang.String");
    }

    public static String toString(InputStream inputStream) throws IOException {
        return inputStream == null ? "" : toStringBuffer(inputStream).toString();
    }

    public static StringBuilder toStringBuffer(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                inputStream.close();
                return sb;
            }
            sb.append(line);
            sb.append("\n");
        }
    }

    public static boolean writeStringFile(String str, String str2) throws Throwable {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream2, "utf-8");
                    outputStreamWriter.write(str2, 0, str2.length());
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    close(fileOutputStream2);
                    return true;
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    close(fileOutputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    close(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
        }
    }

    public static void close(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
}
