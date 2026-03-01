package io.dcloud.common.adapter.io;

import android.content.Context;
import com.dmcbig.mediapicker.utils.FileUtils;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.util.FileUtil;
import io.dcloud.common.util.IOUtil;
import io.dcloud.common.util.PdrUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/* JADX INFO: loaded from: classes2.dex */
public class DHFile {
    public static final int BUF_SIZE = 204800;
    public static final byte FS_JAR = 0;
    public static final byte FS_NATIVE = 2;
    public static final byte FS_RMS = 1;
    public static final int READ = 1;
    public static final int READ_WRITE = 3;
    public static final String ROOTPATH = "/";
    public static final int WRITE = 2;

    public static void addFile(String str, byte[] bArr) throws IOException {
        Object objCreateFileHandler = createFileHandler(str);
        createNewFile(objCreateFileHandler);
        OutputStream outputStream = getOutputStream(objCreateFileHandler, false);
        if (outputStream != null) {
            try {
                outputStream.write(bArr, 0, bArr.length);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean canRead(String str) throws IOException {
        return getFile(getRealPath(str)).canRead();
    }

    public static boolean checkIsNeedReload(String str) {
        return str.endsWith(".png") || str.endsWith(FileUtils.JPEG_FILE_SUFFIX) || str.endsWith(".xml") || str.endsWith(".bmp");
    }

    public static boolean copyAssetsFile(String str, String str2) {
        boolean zWriteFile = false;
        InputStream resInputStream = null;
        try {
            resInputStream = PlatformUtil.getResInputStream(str);
            if (resInputStream != null) {
                zWriteFile = writeFile(resInputStream, str2);
            } else if (checkIsNeedReload(str)) {
                Logger.d("PlatFU copyAssetsFile fail ！！！！  is = null < " + str + " > to < " + str2 + " >");
            }
            Logger.d("PlatFU copyAssetsFile < " + str + " > to < " + str2 + " >");
        } catch (Exception e2) {
            Logger.d("PlatFU copyAssetsFile " + str2 + " error!!!  is it a dir ?");
            StringBuilder sb = new StringBuilder();
            sb.append("PlatFU copyAssetsFile ");
            sb.append(e2);
            Logger.d(sb.toString());
            if (checkIsNeedReload(str)) {
                Logger.d("PlatFU copyAssetsFile fail ！！！！ Exception< " + str + " > to < " + str2 + " >");
            }
        } finally {
            IOUtil.close(resInputStream);
        }
        return zWriteFile;
    }

    public static void copyDir(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        try {
            if (str.charAt(0) == '/') {
                str = str.substring(1, str.length());
            }
            if (str.charAt(str.length() - 1) == '/') {
                str = str.substring(0, str.length() - 1);
            }
            String[] strArrListResFiles = PlatformUtil.listResFiles(str);
            if (createNewFile(str2) != -1) {
                for (String str3 : strArrListResFiles) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(str);
                    stringBuffer.append('/');
                    stringBuffer.append(str3);
                    String string = stringBuffer.toString();
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(str2);
                    stringBuffer2.append(str3);
                    String string2 = stringBuffer2.toString();
                    if (!copyAssetsFile(string, string2)) {
                        if (!checkIsNeedReload(str3)) {
                            copyDir(string, string2 + "/");
                        } else if (!copyAssetsFile(string, string2) && !copyAssetsFile(string, string2)) {
                            Logger.d("PlatFU copyDir fail 3 times!!!!" + string);
                            copyDir(string, string2 + "/");
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v11, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v26 */
    /* JADX WARN: Type inference failed for: r12v27 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r14v2, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.lang.StringBuilder] */
    public static int copyFile(String str, String str2, boolean z, boolean z2) throws Throwable {
        InputStream inputStream;
        Exception e2;
        OutputStream outputStream;
        Throwable th;
        InputStream fileInputStream;
        Context context;
        int i;
        OutputStream outputStream2;
        ?? realPath = getRealPath(str);
        String realPath2 = getRealPath(str2);
        InputStream inputStream2 = null;
        inputStream2 = null;
        outputStream = null;
        OutputStream outputStream3 = null;
        inputStream2 = null;
        OutputStream outputStream4 = null;
        int i2 = -1;
        try {
            try {
                context = DCLoudApplicationImpl.self().getContext();
            } catch (Exception e3) {
                e2 = e3;
                outputStream = null;
            } catch (Throwable th2) {
                th = th2;
                inputStream = null;
                try {
                    Thread.sleep(10L);
                } catch (Exception unused) {
                }
                IOUtil.close(inputStream);
                IOUtil.close(outputStream4);
                throw th;
            }
        } catch (Exception unused2) {
        }
        if (!FileUtil.checkPathAccord(context, realPath2) && AndroidResources.sAppTargetSdkVersion > 28) {
            try {
                Thread.sleep(10L);
            } catch (Exception unused3) {
            }
            IOUtil.close((InputStream) null);
            IOUtil.close((OutputStream) null);
            return -3;
        }
        if (isExist(realPath2)) {
            if (z2) {
                try {
                    Thread.sleep(10L);
                } catch (Exception unused4) {
                }
                IOUtil.close((InputStream) null);
                IOUtil.close((OutputStream) null);
                return -2;
            }
            if (z && !isDirectory(new File(realPath2))) {
                deleteFile(realPath2);
            }
        }
        File file = new File((String) realPath);
        if (!file.exists()) {
            try {
                Thread.sleep(10L);
            } catch (Exception unused5) {
            }
            IOUtil.close((InputStream) null);
            IOUtil.close((OutputStream) null);
            return -1;
        }
        if (file.isDirectory()) {
            String[] list = list(file);
            ?? sb = new StringBuilder();
            sb.append(realPath);
            String str3 = File.separator;
            sb.append(realPath.endsWith(str3) ? "" : str3);
            String string = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(realPath2);
            if (realPath2.endsWith(str3)) {
                str3 = "";
            }
            sb2.append(str3);
            String string2 = sb2.toString();
            int iCopyFile = -1;
            for (i = 0; i < list.length; i++) {
                iCopyFile = copyFile(string + list[i], string2 + list[i], z, z2);
                if (iCopyFile != 1) {
                    try {
                        Thread.sleep(10L);
                    } catch (Exception unused6) {
                    }
                    IOUtil.close((InputStream) null);
                    IOUtil.close((OutputStream) null);
                    return iCopyFile;
                }
            }
            outputStream2 = null;
            i2 = iCopyFile;
        } else {
            if (FileUtil.checkPathAccord(context, new String[]{realPath}) || AndroidResources.sAppTargetSdkVersion <= 28) {
                fileInputStream = new FileInputStream(file);
            } else {
                if (!FileUtil.isFilePathForPublic(context, realPath)) {
                    try {
                        Thread.sleep(10L);
                    } catch (Exception unused7) {
                    }
                    IOUtil.close((InputStream) null);
                    IOUtil.close((OutputStream) null);
                    return -3;
                }
                fileInputStream = FileUtil.getFileInputStream(context, file);
            }
            try {
                Object objCreateFileHandler = createFileHandler(realPath2);
                if (!isExist(objCreateFileHandler)) {
                    createNewFile(objCreateFileHandler);
                }
                outputStream3 = getOutputStream(objCreateFileHandler);
                byte[] bArr = new byte[BUF_SIZE];
                if (fileInputStream != null) {
                    while (true) {
                        int i3 = fileInputStream.read(bArr);
                        if (i3 <= 0) {
                            break;
                        }
                        outputStream3.write(bArr, 0, i3);
                        outputStream3.flush();
                    }
                    i2 = 1;
                }
                inputStream2 = fileInputStream;
                outputStream2 = outputStream3;
            } catch (Exception e4) {
                e2 = e4;
                OutputStream outputStream5 = outputStream3;
                inputStream2 = fileInputStream;
                outputStream = outputStream5;
                try {
                    Logger.i("copyFile:" + e2);
                    Thread.sleep(10L);
                    realPath = outputStream;
                } catch (Throwable th3) {
                    th = th3;
                    InputStream inputStream3 = inputStream2;
                    outputStream3 = outputStream;
                    fileInputStream = inputStream3;
                    Throwable th4 = th;
                    inputStream = fileInputStream;
                    th = th4;
                    outputStream4 = outputStream3;
                    Thread.sleep(10L);
                    IOUtil.close(inputStream);
                    IOUtil.close(outputStream4);
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
                Throwable th42 = th;
                inputStream = fileInputStream;
                th = th42;
                outputStream4 = outputStream3;
                Thread.sleep(10L);
                IOUtil.close(inputStream);
                IOUtil.close(outputStream4);
                throw th;
            }
        }
        Thread.sleep(10L);
        realPath = outputStream2;
        IOUtil.close(inputStream2);
        IOUtil.close((OutputStream) realPath);
        return i2;
    }

    public static Object createFileHandler(String str) {
        return str.replace('\\', '/');
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0061 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte createNewFile(java.lang.Object r7) {
        /*
            r0 = -1
            if (r7 != 0) goto L4
            return r0
        L4:
            boolean r1 = r7 instanceof java.lang.String
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L31
            java.lang.String r7 = (java.lang.String) r7
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "createNewFile 0:"
            r1.append(r4)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            io.dcloud.common.adapter.util.Logger.d(r1)
            java.io.File r1 = new java.io.File
            r1.<init>(r7)
            java.lang.String r4 = "/"
            boolean r7 = r7.endsWith(r4)
            if (r7 == 0) goto L2f
            r7 = 1
            goto L39
        L2f:
            r7 = 0
            goto L39
        L31:
            boolean r1 = r7 instanceof java.io.File
            if (r1 == 0) goto L78
            r1 = r7
            java.io.File r1 = (java.io.File) r1
            goto L2f
        L39:
            java.io.File r4 = r1.getParentFile()
            boolean r5 = r4.exists()
            if (r5 != 0) goto L5b
            boolean r4 = r4.mkdirs()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "createNewFile: parentPath mkdirs "
            r5.append(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            io.dcloud.common.adapter.util.Logger.d(r4)
        L5b:
            boolean r4 = r1.exists()
            if (r4 == 0) goto L63
            r7 = -2
            return r7
        L63:
            if (r7 == 0) goto L6a
            boolean r3 = r1.mkdirs()
            goto L75
        L6a:
            boolean r3 = r1.createNewFile()     // Catch: java.io.IOException -> L6f
            goto L75
        L6f:
            r7 = move-exception
            java.lang.String r1 = "createNewFile:"
            io.dcloud.common.adapter.util.Logger.w(r1, r7)
        L75:
            if (r3 == 0) goto L78
            r0 = 1
        L78:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.adapter.io.DHFile.createNewFile(java.lang.Object):byte");
    }

    public static boolean delete(Object obj) {
        boolean zDelete;
        if (obj == null) {
            return false;
        }
        try {
            File file = getFile(obj);
            if (!file.exists()) {
                return false;
            }
            if (file.isFile()) {
                return file.delete();
            }
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                for (int i = 0; i < fileArrListFiles.length; i++) {
                    Logger.d("delete:" + fileArrListFiles[i].getPath());
                    if (fileArrListFiles[i].isDirectory()) {
                        zDelete = delete(file.getPath() + "/" + fileArrListFiles[i].getName());
                    } else {
                        zDelete = fileArrListFiles[i].delete();
                        Thread.sleep(2L);
                    }
                    if (!zDelete) {
                        return false;
                    }
                }
            }
            boolean zDelete2 = file.delete();
            Logger.i("delete " + obj + Constants.COLON_SEPARATOR + String.valueOf(zDelete2));
            return zDelete2;
        } catch (Exception e2) {
            Logger.w("DHFile.delete", e2);
            return false;
        }
    }

    public static int deleteFile(String str) throws IOException {
        return delete(new File(getRealPath(str))) ? 1 : -1;
    }

    public static boolean exists(Object obj) {
        boolean zExists;
        try {
            if (obj instanceof String) {
                String strSubstring = (String) obj;
                if (strSubstring.endsWith("/")) {
                    strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
                }
                zExists = new File(strSubstring).exists();
            } else {
                if (!(obj instanceof File)) {
                    return false;
                }
                zExists = ((File) obj).exists();
            }
            return zExists;
        } catch (Exception unused) {
            return false;
        }
    }

    public static File getFile(Object obj) {
        if (!(obj instanceof String)) {
            if (obj instanceof File) {
                return (File) obj;
            }
            return null;
        }
        String strSubstring = (String) obj;
        if (strSubstring.endsWith("/")) {
            strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
        }
        return new File(strSubstring);
    }

    public static String getFileName(Object obj) {
        return getName(obj);
    }

    public static String getFilePath(Object obj) {
        return getPath(obj);
    }

    public static long getFileSize(File file) {
        long fileSize = 0;
        if (!file.isDirectory()) {
            return 0 + file.length();
        }
        for (File file2 : file.listFiles()) {
            fileSize += getFileSize(file2);
        }
        return fileSize;
    }

    public static String getFileUrl(Object obj) {
        return getPath(obj);
    }

    public static InputStream getInputStream(Object obj) throws IOException {
        File file;
        if (obj instanceof String) {
            String strSubstring = (String) obj;
            if (strSubstring.startsWith("file://")) {
                strSubstring = strSubstring.substring(7);
            }
            file = new File(strSubstring);
        } else {
            file = obj instanceof File ? (File) obj : null;
        }
        if (file == null || !file.exists() || file.isDirectory()) {
            return null;
        }
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException unused) {
            Logger.e("DHFile getInputStream not found file: " + file.getPath());
            return null;
        } catch (SecurityException e2) {
            Logger.w("getInputStream2", e2);
            return null;
        }
    }

    public static long getLastModify(String str) throws IOException {
        File file = new File(getRealPath(str));
        if (file.exists()) {
            return file.lastModified();
        }
        return 0L;
    }

    public static String getName(Object obj) {
        if (!(obj instanceof String)) {
            return ((File) obj).getName();
        }
        String strSubstring = (String) obj;
        if (strSubstring.endsWith("/")) {
            strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
        }
        return strSubstring.substring(strSubstring.lastIndexOf(47) + 1);
    }

    public static OutputStream getOutputStream(Object obj) throws IOException {
        File file = obj instanceof String ? new File((String) obj) : obj instanceof File ? (File) obj : null;
        if (file == null) {
            return null;
        }
        if (!file.canWrite()) {
            Logger.i("getOutputStream:can not write");
            return null;
        }
        try {
            return new FileOutputStream(file);
        } catch (FileNotFoundException e2) {
            Logger.w("getOutputStream:", e2);
            return null;
        }
    }

    public static Object getParent(Object obj) throws IOException {
        StringBuffer stringBuffer = new StringBuffer(getPath(obj));
        if (((File) obj).isDirectory()) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        stringBuffer.delete(stringBuffer.toString().lastIndexOf(47), stringBuffer.length());
        return createFileHandler(stringBuffer.toString());
    }

    public static String getPath(Object obj) {
        if (obj instanceof String) {
            String str = (String) obj;
            return str.substring(0, str.lastIndexOf(47) + 1);
        }
        if (obj instanceof File) {
            return ((File) obj).getPath();
        }
        return null;
    }

    public static String getRealPath(String str) {
        String str2 = DeviceInfo.sBaseFsRootPath;
        StringBuffer stringBuffer = new StringBuffer();
        if ("".equals(str)) {
            return str2;
        }
        try {
            char[] charArray = str.toCharArray();
            int i = 0;
            while (i < charArray.length) {
                int i2 = 3;
                if ((charArray[0] == 'C' || charArray[0] == 'c') && i == 0) {
                    stringBuffer.append(str2);
                    i = 3;
                }
                if ((charArray[0] == 'D' || charArray[0] == 'd') && i == 0) {
                    stringBuffer.append(str2);
                } else {
                    i2 = i;
                }
                if (charArray[i2] == '\\') {
                    stringBuffer.append('/');
                } else {
                    stringBuffer.append(charArray[i2]);
                }
                i = i2 + 1;
            }
            return stringBuffer.toString();
        } catch (ArrayIndexOutOfBoundsException unused) {
            return str;
        }
    }

    public static boolean hasFile() {
        try {
            return new File("/sdcard/.system/45a3c43f-5991-4a65-a420-0a8a71874f72").exists();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isDirectory(Object obj) throws IOException {
        return ((File) obj).isDirectory();
    }

    public static boolean isExist(String str) throws IOException {
        return exists(getFile(getRealPath(str)));
    }

    public static boolean isHidden(Object obj) throws IOException {
        File file = getFile(obj);
        if (file == null) {
            return false;
        }
        return file.isHidden();
    }

    public static long length(Object obj) {
        try {
            return ((File) obj).length();
        } catch (Exception e2) {
            Logger.w("length:", e2);
            return -1L;
        }
    }

    public static String[] list(Object obj) throws IOException {
        Object[] objArrListFiles = listFiles(obj);
        if (objArrListFiles == null) {
            return null;
        }
        String[] strArr = new String[objArrListFiles.length];
        for (int i = 0; i < objArrListFiles.length; i++) {
            File file = (File) objArrListFiles[i];
            if (file.isDirectory()) {
                strArr[i] = file.getName() + "/";
            } else {
                strArr[i] = file.getName();
            }
        }
        return strArr;
    }

    public static String[] listDir(Object obj) throws IOException {
        Object[] objArrListFiles = listFiles(obj);
        if (objArrListFiles == null) {
            return null;
        }
        String[] strArr = new String[objArrListFiles.length];
        for (int i = 0; i < objArrListFiles.length; i++) {
            File file = (File) objArrListFiles[i];
            if (file.isDirectory()) {
                strArr[i] = file.getName() + "/";
            }
        }
        return strArr;
    }

    public static Object[] listFiles(Object obj) throws IOException {
        File file = obj instanceof String ? new File((String) obj) : obj instanceof File ? (File) obj : null;
        if (file == null) {
            return null;
        }
        file.isDirectory();
        try {
            return file.listFiles();
        } catch (Exception e2) {
            Logger.w("listFiles:", e2);
            return null;
        }
    }

    public static String[] listRoot() throws IOException {
        return new File("/").list();
    }

    public static Object openFile(String str, int i, boolean z) throws IOException {
        return createFileHandler(getRealPath(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:59:0x006b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] readAll(java.lang.Object r5) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.InputStream r5 = getInputStream(r5)     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L2b java.lang.SecurityException -> L38 java.io.FileNotFoundException -> L45
            if (r5 == 0) goto L1e
            byte[] r0 = io.dcloud.common.util.IOUtil.getBytes(r5)     // Catch: java.lang.Throwable -> L16 java.io.IOException -> L18 java.lang.SecurityException -> L1a java.io.FileNotFoundException -> L1c
            if (r5 == 0) goto L15
            r5.close()     // Catch: java.io.IOException -> L11
            goto L15
        L11:
            r5 = move-exception
            r5.printStackTrace()
        L15:
            return r0
        L16:
            r0 = move-exception
            goto L69
        L18:
            r1 = move-exception
            goto L2d
        L1a:
            r1 = move-exception
            goto L3a
        L1c:
            r1 = move-exception
            goto L47
        L1e:
            if (r5 == 0) goto L68
            r5.close()     // Catch: java.io.IOException -> L24
            goto L68
        L24:
            r5 = move-exception
            goto L65
        L26:
            r5 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
            goto L69
        L2b:
            r1 = move-exception
            r5 = r0
        L2d:
            java.lang.String r2 = "readAll 2:"
            io.dcloud.common.adapter.util.Logger.w(r2, r1)     // Catch: java.lang.Throwable -> L16
            if (r5 == 0) goto L68
            r5.close()     // Catch: java.io.IOException -> L24
            goto L68
        L38:
            r1 = move-exception
            r5 = r0
        L3a:
            java.lang.String r2 = "readAll 1:"
            io.dcloud.common.adapter.util.Logger.w(r2, r1)     // Catch: java.lang.Throwable -> L16
            if (r5 == 0) goto L68
            r5.close()     // Catch: java.io.IOException -> L24
            goto L68
        L45:
            r1 = move-exception
            r5 = r0
        L47:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L16
            r2.<init>()     // Catch: java.lang.Throwable -> L16
            java.lang.String r3 = "readAll 0:"
            r2.append(r3)     // Catch: java.lang.Throwable -> L16
            java.lang.String r1 = r1.getLocalizedMessage()     // Catch: java.lang.Throwable -> L16
            r2.append(r1)     // Catch: java.lang.Throwable -> L16
            java.lang.String r1 = r2.toString()     // Catch: java.lang.Throwable -> L16
            io.dcloud.common.adapter.util.Logger.i(r1)     // Catch: java.lang.Throwable -> L16
            if (r5 == 0) goto L68
            r5.close()     // Catch: java.io.IOException -> L24
            goto L68
        L65:
            r5.printStackTrace()
        L68:
            return r0
        L69:
            if (r5 == 0) goto L73
            r5.close()     // Catch: java.io.IOException -> L6f
            goto L73
        L6f:
            r5 = move-exception
            r5.printStackTrace()
        L73:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.adapter.io.DHFile.readAll(java.lang.Object):byte[]");
    }

    public static int rename(String str, String str2) throws IOException {
        String strSubstring;
        String realPath = getRealPath(str);
        if (realPath.endsWith("/")) {
            if (!str2.endsWith("/")) {
                str2 = str2 + "/";
            }
            strSubstring = realPath.substring(0, realPath.length() - 1);
        } else {
            strSubstring = null;
        }
        if (strSubstring == null) {
            return -1;
        }
        if (!PdrUtil.isDeviceRootDir(str2)) {
            str2 = strSubstring.substring(0, strSubstring.lastIndexOf("/") + 1) + str2;
        }
        String realPath2 = getRealPath(str2);
        File file = new File(realPath);
        if (file.exists()) {
            File file2 = new File(realPath2);
            if (!file2.exists() && file.renameTo(file2)) {
                return 1;
            }
        }
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r4v8 */
    public static boolean writeFile(InputStream inputStream, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        File file = new File(str);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        ?? r4 = 0;
        r4 = 0;
        FileOutputStream fileOutputStream2 = null;
        boolean z = false;
        try {
            try {
                FileOutputStream fileOutputStream3 = new FileOutputStream(file);
                fileOutputStream = fileOutputStream3;
                if (inputStream != null) {
                    try {
                        byte[] bArr = new byte[BUF_SIZE];
                        while (true) {
                            int i = inputStream.read(bArr);
                            if (i <= 0) {
                                break;
                            }
                            fileOutputStream3.write(bArr, 0, i);
                        }
                        z = true;
                        fileOutputStream = fileOutputStream3;
                        r4 = bArr;
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream2 = fileOutputStream3;
                        e.printStackTrace();
                        fileOutputStream = fileOutputStream2;
                        r4 = fileOutputStream2;
                    } catch (Throwable th) {
                        th = th;
                        r4 = fileOutputStream3;
                        IOUtil.close((OutputStream) r4);
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
            }
            IOUtil.close(fileOutputStream);
            return z;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean isHidden(String str) throws IOException {
        File file = new File(getRealPath(str));
        if (file.exists()) {
            return isHidden(file);
        }
        return false;
    }

    public static Object openFile(String str, int i) throws IOException {
        return openFile(str, i, false);
    }

    public static boolean isExist(Object obj) throws IOException {
        File file = getFile(obj);
        if (file == null) {
            return false;
        }
        return file.exists();
    }

    public static OutputStream getOutputStream(Object obj, boolean z) throws IOException {
        File file;
        if (obj instanceof String) {
            file = new File((String) obj);
        } else {
            file = obj instanceof File ? (File) obj : null;
        }
        if (file == null) {
            return null;
        }
        if (file.canWrite()) {
            try {
                return new FileOutputStream(file, z);
            } catch (FileNotFoundException e2) {
                Logger.w("getOutputStream:", e2);
                return null;
            }
        }
        Logger.i("getOutputStream:can not write");
        return null;
    }

    public static void writeFile(byte[] bArr, int i, String str) {
        File file = new File(str);
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            Logger.i(str + "cannot create!");
            return;
        }
        if (file.exists()) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rws");
                randomAccessFile.setLength(bArr.length + i);
                randomAccessFile.seek(i);
                randomAccessFile.write(bArr);
                randomAccessFile.close();
                return;
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
                return;
            } catch (IOException e3) {
                e3.printStackTrace();
                return;
            }
        }
        try {
            file.createNewFile();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e5) {
            e5.printStackTrace();
        }
        if (fileOutputStream != null) {
            try {
                if (bArr != null) {
                    try {
                        try {
                            fileOutputStream.write(bArr, 0, bArr.length);
                            fileOutputStream.flush();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                            fileOutputStream.close();
                            return;
                        }
                    } catch (Throwable th) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e7) {
                            e7.printStackTrace();
                        }
                        throw th;
                    }
                }
                fileOutputStream.close();
            } catch (IOException e8) {
                e8.printStackTrace();
            }
        }
    }

    public static void writeFile(InputStream inputStream, int i, String str) {
        File file = new File(str);
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            Logger.i(str + "cannot create!");
            return;
        }
        if (file.exists()) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rws");
                randomAccessFile.seek(file.length());
                byte[] bArr = new byte[8192];
                while (true) {
                    int i2 = inputStream.read(bArr, 0, 8192);
                    if (i2 != -1) {
                        randomAccessFile.write(bArr, 0, i2);
                    } else {
                        randomAccessFile.close();
                        return;
                    }
                }
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e5) {
                e5.printStackTrace();
            }
            try {
                try {
                    if (fileOutputStream != null) {
                        try {
                            byte[] bArr2 = new byte[8192];
                            while (true) {
                                int i3 = inputStream.read(bArr2, 0, 8192);
                                if (i3 == -1) {
                                    break;
                                } else {
                                    fileOutputStream.write(bArr2, 0, i3);
                                }
                            }
                            fileOutputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                            fileOutputStream.close();
                        }
                    }
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
                throw th;
            }
        }
    }

    public static int copyFile(String str, String str2) {
        return copyFile(str, str2, false, false);
    }
}
