package io.dcloud.common.util;

import com.huawei.secure.android.common.util.ZipUtil;
import io.dcloud.common.adapter.util.Logger;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class ZipUtils {
    public static final int BUFF_SIZE = 1048576;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v7, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r1v9 */
    public static String inflaterString(byte[] bArr) {
        InflaterInputStream inflaterInputStream = new InflaterInputStream(new ByteArrayInputStream(bArr), new Inflater(true));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String str = 1024;
        try {
            try {
                try {
                    str = new byte[1024];
                    while (true) {
                        int i = inflaterInputStream.read(str);
                        if (i == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(str, 0, i);
                    }
                    String str2 = new String(byteArrayOutputStream.toByteArray());
                    inflaterInputStream.close();
                    byteArrayOutputStream.close();
                    str = str2;
                } catch (Exception unused) {
                    String str3 = new String(byteArrayOutputStream.toByteArray());
                    inflaterInputStream.close();
                    byteArrayOutputStream.close();
                    str = str3;
                } catch (Throwable th) {
                    try {
                        new String(byteArrayOutputStream.toByteArray());
                        inflaterInputStream.close();
                        byteArrayOutputStream.close();
                        byteArrayOutputStream.flush();
                    } catch (Exception unused2) {
                    }
                    throw th;
                }
                byteArrayOutputStream.flush();
                return str;
            } catch (Exception unused3) {
                return str;
            }
        } catch (Exception unused4) {
            return "";
        }
    }

    public static void upZipFile(File file, String str) throws IOException {
        if (!str.endsWith("/")) {
            str = str + File.separatorChar;
        }
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            if (zipEntryNextElement.getName().contains(ZipUtil.f3018e)) {
                Logger.d("ZIP", "util.ZipUtils Path traversal attack prevented path=" + zipEntryNextElement.getName());
            } else {
                InputStream inputStream = zipFile.getInputStream(zipEntryNextElement);
                String strReplace = new String((str + zipEntryNextElement.getName()).getBytes(), "UTF-8").replace("\\", "/");
                File file3 = new File(strReplace);
                File parentFile = file3.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                if (!strReplace.endsWith("/")) {
                    if (file3.exists()) {
                        file3.delete();
                    }
                    file3.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file3);
                    byte[] bArr = new byte[1048576];
                    while (true) {
                        int i = inputStream.read(bArr);
                        if (i <= 0) {
                            break;
                        } else {
                            fileOutputStream.write(bArr, 0, i);
                        }
                    }
                    inputStream.close();
                    fileOutputStream.close();
                }
            }
        }
        zipFile.close();
    }

    public static void zipFile(File file, ZipOutputStream zipOutputStream, String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.trim().length() == 0 ? "" : File.separator);
        sb.append(file.getName());
        String str2 = new String(sb.toString().getBytes("UTF-8"), "UTF-8");
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                zipFile(file2, zipOutputStream, str2);
            }
            return;
        }
        byte[] bArr = new byte[1048576];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 1048576);
        zipOutputStream.putNextEntry(new ZipEntry(str2));
        while (true) {
            int i = bufferedInputStream.read(bArr);
            if (i == -1) {
                bufferedInputStream.close();
                zipOutputStream.flush();
                zipOutputStream.closeEntry();
                return;
            }
            zipOutputStream.write(bArr, 0, i);
        }
    }

    public static void zipFiles(File[] fileArr, File file) throws IOException {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file), 1048576));
        for (File file2 : fileArr) {
            zipFile(file2, zipOutputStream, "");
        }
        zipOutputStream.close();
    }

    public static byte[] zipString(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes());
            gZIPOutputStream.flush();
            gZIPOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
