package com.baidu.idl.face.platform.utils;

import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class IoUtils {
    public static final int BUFFER_SIZE = 1024;
    public static final int EOF = -1;

    public interface ProgressListener {
        void progress(long j, long j2);
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {
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

    public static byte[] loadBytes(InputStream inputStream) {
        byte[] byteArray;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                copyStream(inputStream, byteArrayOutputStream);
                byteArray = byteArrayOutputStream.toByteArray();
            } catch (IOException e2) {
                e2.printStackTrace();
                closeQuietly(byteArrayOutputStream);
                byteArray = null;
            }
            return byteArray;
        } finally {
            closeQuietly(byteArrayOutputStream);
        }
    }

    public static String loadContent(InputStream inputStream) throws IOException {
        return loadContent(inputStream, null);
    }

    public static String loadContent(InputStream inputStream, String str) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("stream may not be null.");
        }
        if (TextUtils.isEmpty(str)) {
            str = System.getProperty("file.encoding", "utf-8");
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, str);
        StringWriter stringWriter = new StringWriter();
        char[] cArr = new char[4096];
        for (int i = inputStreamReader.read(cArr); i > 0; i = inputStreamReader.read(cArr)) {
            stringWriter.write(cArr, 0, i);
        }
        return stringWriter.toString();
    }

    public static void closeQuietly(ServerSocket serverSocket) {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void copyStream(InputStream inputStream, File file) throws Throwable {
        FileOutputStream fileOutputStreamOpenNewFileOutput;
        try {
            fileOutputStreamOpenNewFileOutput = FileUtils.openNewFileOutput(file);
        } catch (Throwable th) {
            th = th;
            fileOutputStreamOpenNewFileOutput = null;
        }
        try {
            copyStream(inputStream, fileOutputStreamOpenNewFileOutput);
            closeQuietly(fileOutputStreamOpenNewFileOutput);
        } catch (Throwable th2) {
            th = th2;
            closeQuietly(fileOutputStreamOpenNewFileOutput);
            throw th;
        }
    }

    public static void closeQuietly(ZipFile zipFile) {
        if (zipFile != null) {
            try {
                zipFile.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void copyStream(InputStream inputStream, File file, long j, ProgressListener progressListener) throws Throwable {
        FileOutputStream fileOutputStreamOpenNewFileOutput;
        try {
            fileOutputStreamOpenNewFileOutput = FileUtils.openNewFileOutput(file);
        } catch (Throwable th) {
            th = th;
            fileOutputStreamOpenNewFileOutput = null;
        }
        try {
            copyStream(inputStream, fileOutputStreamOpenNewFileOutput, j, progressListener);
            closeQuietly(fileOutputStreamOpenNewFileOutput);
        } catch (Throwable th2) {
            th = th2;
            closeQuietly(fileOutputStreamOpenNewFileOutput);
            throw th;
        }
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream, long j, ProgressListener progressListener) throws IOException {
        byte[] bArr = new byte[1024];
        long j2 = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            }
            outputStream.write(bArr, 0, i);
            j2 += (long) i;
            if (progressListener != null) {
                progressListener.progress(j2, j);
            }
        }
    }
}
