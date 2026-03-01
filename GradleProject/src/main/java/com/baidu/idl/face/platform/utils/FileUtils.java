package com.baidu.idl.face.platform.utils;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class FileUtils {
    public static final int S_IRGRP = 32;
    public static final int S_IROTH = 4;
    public static final int S_IRUSR = 256;
    public static final int S_IRWXG = 56;
    public static final int S_IRWXO = 7;
    public static final int S_IRWXU = 448;
    public static final int S_IWGRP = 16;
    public static final int S_IWOTH = 2;
    public static final int S_IWUSR = 128;
    public static final int S_IXGRP = 8;
    public static final int S_IXOTH = 1;
    public static final int S_IXUSR = 64;
    public static final Pattern SAFE_FILENAME_PATTERN = Pattern.compile("[\\w%+,./=_-]+");
    public static final Pattern RESERVED_CHARS_PATTERN = Pattern.compile("[\\\\/:\\*\\?\\\"<>|]");

    public static void cleanDir(File file) {
        deleteDir(file, false);
    }

    public static long computeFolderSize(File file) {
        long jComputeFolderSize;
        long length = 0;
        if (file == null) {
            return 0L;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isFile()) {
                    jComputeFolderSize = file2.length();
                } else if (file2.isDirectory()) {
                    length += file2.length();
                    jComputeFolderSize = computeFolderSize(file2);
                }
                length += jComputeFolderSize;
            }
        }
        return length;
    }

    public static void copyDirectory(File file, File file2) throws Throwable {
        if (file.exists()) {
            file2.mkdirs();
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                return;
            }
            for (File file3 : fileArrListFiles) {
                if (file3.isDirectory()) {
                    copyDirectory(file3, new File(file2, file3.getName()));
                } else {
                    copyFile(file3, new File(file2, file3.getName()));
                }
            }
        }
    }

    public static void copyFile(String str, String str2) throws Throwable {
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(str);
                try {
                    IoUtils.copyStream(fileInputStream2, new File(str2));
                    IoUtils.closeQuietly(fileInputStream2);
                } catch (IOException e2) {
                    e = e2;
                    fileInputStream = fileInputStream2;
                    e.printStackTrace();
                    IoUtils.closeQuietly(fileInputStream);
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    IoUtils.closeQuietly(fileInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
        }
    }

    public static void deleteDir(String str) {
        deleteDir(new File(str));
    }

    public static boolean deleteFileIfExist(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static void ensureDir(File file) {
        if (!file.exists()) {
            file.mkdirs();
        } else if (file.isFile()) {
            file.delete();
            file.mkdirs();
        }
    }

    public static boolean ensureMkdir(File file) {
        if (file == null) {
            return false;
        }
        int i = 1;
        File file2 = file;
        while (file2.exists()) {
            file2 = new File(file.getParent(), file.getName() + "(" + i + ")");
            i++;
        }
        return file2.mkdir();
    }

    public static void ensureParent(File file) {
        File parentFile;
        if (file == null || (parentFile = file.getParentFile()) == null || parentFile.exists()) {
            return;
        }
        parentFile.mkdirs();
    }

    public static boolean existsFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return existsFile(new File(str));
    }

    public static String getExtension(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return getExtension(new File(str));
    }

    public static String getFileNameWithoutExtension(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf != -1 ? str.substring(0, iLastIndexOf) : str;
    }

    public static String getFileNameWithoutExtensionByPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return getFileNameWithoutExtension(new File(str));
    }

    public static File getUserDir() {
        return new File(System.getProperty("user.dir"));
    }

    public static File getUserHome() {
        return new File(System.getProperty("user.home"));
    }

    public static boolean isFilenameSafe(File file) {
        return SAFE_FILENAME_PATTERN.matcher(file.getPath()).matches();
    }

    public static boolean isFilenameValid(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return !RESERVED_CHARS_PATTERN.matcher(str).find();
    }

    public static FileOutputStream openNewFileOutput(File file) throws IOException {
        deleteFileIfExist(file);
        ensureParent(file);
        file.createNewFile();
        return new FileOutputStream(file);
    }

    public static Map<String, String> readConfig(File file) throws Throwable {
        HashMap map = new HashMap();
        String fileText = readFileText(file);
        if (TextUtils.isEmpty(fileText)) {
            return map;
        }
        for (String str : fileText.split("\n")) {
            String strTrim = str.trim();
            if (!TextUtils.isEmpty(strTrim) && !strTrim.startsWith("#")) {
                String[] strArrSplit = strTrim.split(ContainerUtils.KEY_VALUE_DELIMITER, 2);
                map.put(strArrSplit[0].trim(), strArrSplit[1].trim());
            }
        }
        return map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.Closeable] */
    public static byte[] readFileBytes(File file) throws Throwable {
        FileInputStream fileInputStream;
        ?? ExistsFile = existsFile(file);
        ?? r1 = 0;
        try {
            if (ExistsFile != 0) {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (IOException e2) {
                    e = e2;
                    fileInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    IoUtils.closeQuietly((Closeable) r1);
                    throw th;
                }
                try {
                    byte[] bArrLoadBytes = IoUtils.loadBytes(fileInputStream);
                    IoUtils.closeQuietly(fileInputStream);
                    return bArrLoadBytes;
                } catch (IOException e3) {
                    e = e3;
                    e.printStackTrace();
                    IoUtils.closeQuietly(fileInputStream);
                    return null;
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            r1 = ExistsFile;
        }
    }

    public static String readFileText(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return readFileText(new File(str));
    }

    public static void writeToFile(File file, String str) throws Throwable {
        writeToFile(file, str, false, "utf-8");
    }

    public static void writeToFileNio(InputStream inputStream, File file) throws Throwable {
        ReadableByteChannel readableByteChannelNewChannel;
        FileChannel fileChannel;
        FileOutputStream fileOutputStream;
        FileChannel channel = null;
        try {
            int iAvailable = inputStream.available();
            readableByteChannelNewChannel = Channels.newChannel(inputStream);
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (IOException e2) {
                e = e2;
                fileChannel = null;
            } catch (Throwable th) {
                th = th;
                fileChannel = null;
            }
            try {
                channel = fileOutputStream.getChannel();
                channel.transferFrom(readableByteChannelNewChannel, 0L, iAvailable);
                IoUtils.closeQuietly(fileOutputStream);
                IoUtils.closeQuietly(readableByteChannelNewChannel);
                IoUtils.closeQuietly(channel);
            } catch (IOException e3) {
                e = e3;
                fileChannel = channel;
                channel = fileOutputStream;
                try {
                    e.printStackTrace();
                    IoUtils.closeQuietly(channel);
                    IoUtils.closeQuietly(readableByteChannelNewChannel);
                    IoUtils.closeQuietly(fileChannel);
                } catch (Throwable th2) {
                    th = th2;
                    IoUtils.closeQuietly(channel);
                    IoUtils.closeQuietly(readableByteChannelNewChannel);
                    IoUtils.closeQuietly(fileChannel);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileChannel = channel;
                channel = fileOutputStream;
                IoUtils.closeQuietly(channel);
                IoUtils.closeQuietly(readableByteChannelNewChannel);
                IoUtils.closeQuietly(fileChannel);
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
            readableByteChannelNewChannel = null;
            fileChannel = null;
        } catch (Throwable th4) {
            th = th4;
            readableByteChannelNewChannel = null;
            fileChannel = null;
        }
    }

    public static void cleanDir(File file, FilenameFilter filenameFilter) {
        deleteDir(file, false, filenameFilter);
    }

    public static void deleteDir(File file) {
        deleteDir(file, true);
    }

    public static void writeToFile(File file, String str, boolean z) throws Throwable {
        writeToFile(file, str, z, "utf-8");
    }

    public static void cleanDir(File file, FileFilter fileFilter) {
        deleteDir(file, false, fileFilter);
    }

    public static void deleteDir(File file, FileFilter fileFilter) {
        deleteDir(file, true, fileFilter);
    }

    public static boolean existsFile(File file) {
        return file != null && file.exists() && file.isFile();
    }

    public static String getExtension(File file) {
        if (file == null) {
            return null;
        }
        String name = file.getName();
        int iLastIndexOf = name.lastIndexOf(46);
        return iLastIndexOf >= 0 ? name.substring(iLastIndexOf + 1) : "";
    }

    public static String getFileNameWithoutExtension(File file) {
        if (file == null) {
            return null;
        }
        String name = file.getName();
        int iLastIndexOf = name.lastIndexOf(46);
        return iLastIndexOf >= 0 ? name.substring(0, iLastIndexOf) : name;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.Closeable] */
    public static String readFileText(File file) throws Throwable {
        FileInputStream fileInputStream;
        ?? ExistsFile = existsFile(file);
        ?? r1 = 0;
        try {
            if (ExistsFile != 0) {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (IOException e2) {
                    e = e2;
                    fileInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    IoUtils.closeQuietly((Closeable) r1);
                    throw th;
                }
                try {
                    String strLoadContent = IoUtils.loadContent(fileInputStream);
                    IoUtils.closeQuietly(fileInputStream);
                    return strLoadContent;
                } catch (IOException e3) {
                    e = e3;
                    e.printStackTrace();
                    IoUtils.closeQuietly(fileInputStream);
                    return null;
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            r1 = ExistsFile;
        }
    }

    public static void writeToFile(File file, String str, String str2) throws Throwable {
        writeToFile(file, str, false, str2);
    }

    public static void deleteDir(File file, FilenameFilter filenameFilter) {
        deleteDir(file, true, filenameFilter);
    }

    public static void writeToFile(File file, String str, boolean z, String str2) throws Throwable {
        if (file == null || TextUtils.isEmpty(str)) {
            return;
        }
        ensureParent(file);
        OutputStreamWriter outputStreamWriter = null;
        try {
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(file, z), str2);
                try {
                    outputStreamWriter2.write(str);
                    IoUtils.closeQuietly(outputStreamWriter2);
                } catch (IOException e2) {
                    e = e2;
                    outputStreamWriter = outputStreamWriter2;
                    e.printStackTrace();
                    IoUtils.closeQuietly(outputStreamWriter);
                } catch (Throwable th) {
                    th = th;
                    outputStreamWriter = outputStreamWriter2;
                    IoUtils.closeQuietly(outputStreamWriter);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void deleteDir(File file, boolean z) {
        if (file == null || !file.isDirectory()) {
            return;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    deleteDir(file2, z);
                } else {
                    file2.delete();
                }
            }
        }
        if (z) {
            file.delete();
        }
    }

    public static boolean deleteFileIfExist(File file) {
        if (file != null && file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static void copyFile(File file, File file2) throws Throwable {
        FileOutputStream fileOutputStream;
        FileChannel channel;
        FileChannel fileChannel;
        FileInputStream fileInputStream = null;
        channel = null;
        FileChannel channel2 = null;
        fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (IOException e2) {
                e = e2;
                fileOutputStream = null;
                channel = null;
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
                channel = null;
            }
            try {
                channel = fileInputStream2.getChannel();
                try {
                    channel2 = fileOutputStream.getChannel();
                    channel.transferTo(0L, channel.size(), channel2);
                    IoUtils.closeQuietly(fileInputStream2);
                    IoUtils.closeQuietly(channel);
                    IoUtils.closeQuietly(fileOutputStream);
                    IoUtils.closeQuietly(channel2);
                } catch (IOException e3) {
                    e = e3;
                    fileChannel = channel2;
                    fileInputStream = fileInputStream2;
                    try {
                        e.printStackTrace();
                        IoUtils.closeQuietly(fileInputStream);
                        IoUtils.closeQuietly(channel);
                        IoUtils.closeQuietly(fileOutputStream);
                        IoUtils.closeQuietly(fileChannel);
                    } catch (Throwable th2) {
                        th = th2;
                        IoUtils.closeQuietly(fileInputStream);
                        IoUtils.closeQuietly(channel);
                        IoUtils.closeQuietly(fileOutputStream);
                        IoUtils.closeQuietly(fileChannel);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileChannel = channel2;
                    fileInputStream = fileInputStream2;
                    IoUtils.closeQuietly(fileInputStream);
                    IoUtils.closeQuietly(channel);
                    IoUtils.closeQuietly(fileOutputStream);
                    IoUtils.closeQuietly(fileChannel);
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
                channel = null;
                fileInputStream = fileInputStream2;
                fileChannel = channel;
                e.printStackTrace();
                IoUtils.closeQuietly(fileInputStream);
                IoUtils.closeQuietly(channel);
                IoUtils.closeQuietly(fileOutputStream);
                IoUtils.closeQuietly(fileChannel);
            } catch (Throwable th4) {
                th = th4;
                channel = null;
                fileInputStream = fileInputStream2;
                fileChannel = channel;
                IoUtils.closeQuietly(fileInputStream);
                IoUtils.closeQuietly(channel);
                IoUtils.closeQuietly(fileOutputStream);
                IoUtils.closeQuietly(fileChannel);
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            fileOutputStream = null;
            channel = null;
        } catch (Throwable th5) {
            th = th5;
            fileOutputStream = null;
            channel = null;
        }
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x001c: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:17:0x001c */
    public static String readFileText(String str, String str2) throws Throwable {
        FileInputStream fileInputStream;
        Closeable closeable;
        Closeable closeable2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (IOException e2) {
                e = e2;
                fileInputStream = null;
            } catch (Throwable th) {
                th = th;
                IoUtils.closeQuietly(closeable2);
                throw th;
            }
            try {
                String strLoadContent = IoUtils.loadContent(fileInputStream, str2);
                IoUtils.closeQuietly(fileInputStream);
                return strLoadContent;
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                IoUtils.closeQuietly(fileInputStream);
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            closeable2 = closeable;
            IoUtils.closeQuietly(closeable2);
            throw th;
        }
    }

    public static final void writeToFile(File file, byte[] bArr) throws Throwable {
        if (file == null || bArr == null) {
            return;
        }
        ensureParent(file);
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    fileOutputStream2.write(bArr);
                    IoUtils.closeQuietly(fileOutputStream2);
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    IoUtils.closeQuietly(fileOutputStream);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    IoUtils.closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static void deleteDir(File file, boolean z, FileFilter fileFilter) {
        if (file == null || !file.isDirectory()) {
            return;
        }
        File[] fileArrListFiles = file.listFiles(fileFilter);
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    deleteDir(file2, z, fileFilter);
                } else {
                    file2.delete();
                }
            }
        }
        if (z) {
            file.delete();
        }
    }

    public static void writeToFileNio(File file, byte[] bArr) throws Throwable {
        FileChannel fileChannel;
        ReadableByteChannel readableByteChannelNewChannel;
        FileOutputStream fileOutputStream;
        FileChannel channel = null;
        try {
            readableByteChannelNewChannel = Channels.newChannel(new ByteArrayInputStream(bArr));
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (IOException e2) {
                e = e2;
                fileChannel = null;
            } catch (Throwable th) {
                th = th;
                fileChannel = null;
            }
        } catch (IOException e3) {
            e = e3;
            fileChannel = null;
            readableByteChannelNewChannel = null;
        } catch (Throwable th2) {
            th = th2;
            fileChannel = null;
            readableByteChannelNewChannel = null;
        }
        try {
            channel = fileOutputStream.getChannel();
            channel.transferFrom(readableByteChannelNewChannel, 0L, bArr.length);
            IoUtils.closeQuietly(fileOutputStream);
            IoUtils.closeQuietly(readableByteChannelNewChannel);
            IoUtils.closeQuietly(channel);
        } catch (IOException e4) {
            e = e4;
            fileChannel = channel;
            channel = fileOutputStream;
            try {
                e.printStackTrace();
                IoUtils.closeQuietly(channel);
                IoUtils.closeQuietly(readableByteChannelNewChannel);
                IoUtils.closeQuietly(fileChannel);
            } catch (Throwable th3) {
                th = th3;
                IoUtils.closeQuietly(channel);
                IoUtils.closeQuietly(readableByteChannelNewChannel);
                IoUtils.closeQuietly(fileChannel);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            fileChannel = channel;
            channel = fileOutputStream;
            IoUtils.closeQuietly(channel);
            IoUtils.closeQuietly(readableByteChannelNewChannel);
            IoUtils.closeQuietly(fileChannel);
            throw th;
        }
    }

    public static void deleteDir(File file, boolean z, FilenameFilter filenameFilter) {
        if (file == null || !file.isDirectory()) {
            return;
        }
        File[] fileArrListFiles = file.listFiles(filenameFilter);
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    deleteDir(file2, z, filenameFilter);
                } else {
                    file2.delete();
                }
            }
        }
        if (z) {
            file.delete();
        }
    }
}
