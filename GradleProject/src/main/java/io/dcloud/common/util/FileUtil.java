package io.dcloud.common.util;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import com.taobao.weex.el.parse.Operators;
import com.umeng.analytics.pro.bq;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.util.DeviceInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class FileUtil {
    public static boolean checkFilePathLegalization(Context context, String str) {
        if (checkPrivatePath(context, str)) {
            return true;
        }
        return needMediaStoreOpenFile(context) && !TextUtils.isEmpty(getPathForPublicType(str));
    }

    public static boolean checkPathAccord(Context context, String... strArr) {
        boolean z = true;
        if (needMediaStoreOpenFile(context)) {
            for (String str : strArr) {
                if (!checkPrivatePath(context, str)) {
                    z = false;
                }
            }
        }
        return z;
    }

    public static boolean checkPrivatePath(Context context, String str) {
        if (TextUtils.isEmpty(DeviceInfo.sPrivateDir)) {
            DeviceInfo.initAppDir(context);
        }
        if (str.startsWith("file://")) {
            str = str.replace("file://", "");
        }
        if (DeviceInfo.sPrivateExternalDir.startsWith("/") && !str.startsWith("/")) {
            str = "/" + str;
        }
        return str.contains(DeviceInfo.sPrivateDir) || str.contains(DeviceInfo.sPrivateExternalDir) || isAssetFile(str) || Build.VERSION.SDK_INT < 29;
    }

    public static Uri copyMediaFile(Context context, InputStream inputStream, String str, String str2, String str3, String str4) throws Exception {
        String fileTypeForSuffix = getFileTypeForSuffix(str2);
        if (TextUtils.isEmpty(fileTypeForSuffix)) {
            return null;
        }
        Uri contentUriForSuffix = getContentUriForSuffix(str2);
        File file = new File(DeviceInfo.sPublicDCIMDir + "/" + str4 + str);
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.DOT_STR);
        sb.append(str2);
        String string = sb.toString();
        String strReplace = str.replace(string, "");
        ContentResolver contentResolver = context.getContentResolver();
        int i = 1;
        while (file.exists()) {
            str = strReplace + "(" + i + ")" + string;
            i++;
            file = new File(DeviceInfo.sPublicDCIMDir + "/" + str4 + str);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", str);
        contentValues.put("mime_type", fileTypeForSuffix);
        contentValues.put("title", str);
        contentValues.put("relative_path", str3 + str4);
        Uri uriInsert = contentResolver.insert(contentUriForSuffix, contentValues);
        if (uriInsert == null) {
            return null;
        }
        OutputStream outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(uriInsert);
        byte[] bArr = new byte[DHFile.BUF_SIZE];
        if (outputStreamOpenOutputStream != null) {
            while (true) {
                int i2 = inputStream.read(bArr);
                if (i2 <= 0) {
                    break;
                }
                outputStreamOpenOutputStream.write(bArr, 0, i2);
                outputStreamOpenOutputStream.flush();
            }
            outputStreamOpenOutputStream.close();
        }
        inputStream.close();
        return uriInsert;
    }

    public static Uri copyMediaFileToDCIM(Context context, String str) {
        if (needMediaStoreOpenFile(context)) {
            try {
                File file = new File(str);
                if (!file.exists()) {
                    return null;
                }
                InputStream fileInputStream = checkPrivatePath(context, str) ? new FileInputStream(file) : getFileInputStream(context, file);
                if (fileInputStream == null) {
                    return null;
                }
                return copyMediaFile(context, fileInputStream, getFileNameForPath(str), getFileNameWithSuffix(str), "DCIM/", "Camera/");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static Uri createPublicDocumentsFile(Context context, String str, String str2) {
        if (!needMediaStoreOpenFile(context)) {
            return null;
        }
        Uri contentUri = MediaStore.Files.getContentUri("external");
        File file = new File(DeviceInfo.sPublicDocumentsDir + "/" + str);
        if (file.exists()) {
            return getFileUri(context, file, contentUri);
        }
        ContentResolver contentResolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", str);
        contentValues.put("mime_type", str2);
        contentValues.put("title", str);
        contentValues.put("relative_path", "Documents/");
        try {
            return contentResolver.insert(contentUri, contentValues);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void deleteFile(Context context, String str) {
        if (context != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                File fileStreamPath = context.getFileStreamPath(str);
                if (fileStreamPath.exists()) {
                    fileStreamPath.delete();
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0164  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.net.Uri getContentUriForSuffix(java.lang.String r1) {
        /*
            Method dump skipped, instruction units count: 538
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.FileUtil.getContentUriForSuffix(java.lang.String):android.net.Uri");
    }

    public static InputStream getFileInputStream(Context context, String str) {
        if (str.startsWith("file://")) {
            str = str.replace("file://", "");
        }
        return getFileInputStream(context, new File(str));
    }

    public static String getFileNameForPath(String str) {
        int iLastIndexOf;
        if (TextUtils.isEmpty(str) || (iLastIndexOf = str.lastIndexOf("/")) == -1) {
            return null;
        }
        return str.substring(iLastIndexOf + 1);
    }

    public static String getFileNameWithSuffix(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int iLastIndexOf = str.lastIndexOf(Operators.DOT_STR);
        if (iLastIndexOf == -1) {
            return null;
        }
        return str.substring(iLastIndexOf + 1);
    }

    public static String getFileProviderUriToPath(Context context, Uri uri) {
        try {
            Method declaredMethod = FileProvider.class.getDeclaredMethod("getPathStrategy", Context.class, String.class);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, context, uri.getAuthority());
            if (objInvoke != null) {
                Method declaredMethod2 = Class.forName(FileProvider.class.getName() + "$PathStrategy").getDeclaredMethod("getFileForUri", Uri.class);
                declaredMethod2.setAccessible(true);
                Object objInvoke2 = declaredMethod2.invoke(objInvoke, uri);
                if (objInvoke2 instanceof File) {
                    return ((File) objInvoke2).getAbsolutePath();
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0164  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getFileTypeForSuffix(java.lang.String r1) {
        /*
            Method dump skipped, instruction units count: 534
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.FileUtil.getFileTypeForSuffix(java.lang.String):java.lang.String");
    }

    public static Uri getFileUri(Context context, File file, Uri uri) {
        Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{bq.d}, "_data=? ", new String[]{file.getAbsolutePath()}, null);
        if (cursorQuery == null || !cursorQuery.moveToFirst()) {
            if (cursorQuery == null) {
                return null;
            }
            cursorQuery.close();
            return null;
        }
        int i = cursorQuery.getInt(cursorQuery.getColumnIndex(bq.d));
        cursorQuery.close();
        return Uri.withAppendedPath(uri, "" + i);
    }

    public static InputStream getImageFileStream(Context context, File file) {
        Uri imageFileUri = getImageFileUri(context, file.getPath());
        if (imageFileUri == null) {
            return null;
        }
        try {
            return context.getContentResolver().openInputStream(imageFileUri);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Uri getImageFileUri(Context context, String str) {
        return getImageFileUri(context, new File(str));
    }

    public static String getPathForPublicType(String str) {
        if (str.contains(DeviceInfo.sPublicDCIMDir)) {
            return Environment.DIRECTORY_DCIM;
        }
        if (str.contains(DeviceInfo.sPublicDownloadDir)) {
            return Environment.DIRECTORY_DOWNLOADS;
        }
        if (str.contains(DeviceInfo.sPublicMoviesDir)) {
            return Environment.DIRECTORY_MOVIES;
        }
        if (str.contains(DeviceInfo.sPublicMusicDir)) {
            return Environment.DIRECTORY_MUSIC;
        }
        if (str.contains(DeviceInfo.sPublicPicturesDir)) {
            return Environment.DIRECTORY_PICTURES;
        }
        if (str.contains(DeviceInfo.sPublicDocumentsDir)) {
            return Environment.DIRECTORY_DOCUMENTS;
        }
        if (str.contains(DeviceInfo.sPublicRingtonesDir)) {
            return Environment.DIRECTORY_RINGTONES;
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    public static String getPathFromUri(Context context, Uri uri) {
        Uri uri2;
        if (uri == null) {
            return null;
        }
        if (!(Build.VERSION.SDK_INT >= 19) || !DocumentsContract.isDocumentUri(context, uri)) {
            String scheme = uri.getScheme();
            if ("content".equals(scheme)) {
                return queryAbsolutePath(context, uri);
            }
            if ("file".equals(scheme)) {
                return uri.getPath();
            }
            return null;
        }
        String authority = uri.getAuthority();
        if ("com.android.externalstorage.documents".equals(authority)) {
            String[] strArrSplit = DocumentsContract.getDocumentId(uri).split(Constants.COLON_SEPARATOR);
            String str = strArrSplit[0];
            return "primary".equals(str) ? Environment.getExternalStorageDirectory().getAbsolutePath().concat("/").concat(strArrSplit[1]) : "/storage/".concat(str).concat("/").concat(strArrSplit[1]);
        }
        if ("com.android.providers.downloads.documents".equals(authority)) {
            String documentId = DocumentsContract.getDocumentId(uri);
            return documentId.startsWith("raw:") ? documentId.replaceFirst("raw:", "") : queryAbsolutePath(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(documentId)));
        }
        if ("com.android.providers.media.documents".equals(authority)) {
            String[] strArrSplit2 = DocumentsContract.getDocumentId(uri).split(Constants.COLON_SEPARATOR);
            String str2 = strArrSplit2[0];
            if ("image".equals(str2)) {
                uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(str2)) {
                uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(str2)) {
                uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }
            return queryAbsolutePath(context, ContentUris.withAppendedId(uri2, Long.parseLong(strArrSplit2[1])));
        }
        return null;
    }

    public static DCFileUriData getShareImageUri(Context context, File file, String str, Intent intent) {
        DCFileUriData dCFileUriData = new DCFileUriData();
        String path = file.getPath();
        dCFileUriData.filePath = path;
        if (!isExternalPublicDir(path) && Build.VERSION.SDK_INT >= 29 && getPathForPublicType(path) == null) {
            String str2 = context.getExternalFilesDir(Environment.DIRECTORY_DCIM).getPath() + "/" + str.hashCode() + Operators.DOT_STR + getFileNameWithSuffix(str);
            File file2 = new File(str2);
            File parentFile = file2.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            dCFileUriData.fileUri = FileProvider.getUriForFile(context, context.getPackageName() + ".dc.fileprovider", file2);
            dCFileUriData.isReplace = true;
            dCFileUriData.fileReplacePath = str2;
            if (intent != null) {
                intent.addFlags(1);
            }
        } else if (isExternalPublicDir(path)) {
            File parentFile2 = file.getParentFile();
            if (!parentFile2.exists()) {
                parentFile2.mkdirs();
            }
            dCFileUriData.fileUri = FileProvider.getUriForFile(context, context.getPackageName() + ".dc.fileprovider", file);
        } else {
            dCFileUriData.fileUri = Uri.fromFile(file);
        }
        return dCFileUriData;
    }

    public static Uri getVideoFileUri(Context context, String str) {
        File file = new File(str);
        return checkPrivatePath(context, str) ? Uri.fromFile(file) : getFileUri(context, file, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
    }

    public static boolean isAssetFile(String str) {
        return str.startsWith("apps/") || str.startsWith("/android_asset/") || str.startsWith("android_asset/");
    }

    public static boolean isExternalPublicDir(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(DeviceInfo.sPrivateExternalDir);
        sb.append("/file/");
        return str.contains(sb.toString());
    }

    public static boolean isFilePathForPublic(Context context, String str) {
        if (PdrUtil.isEmpty(str)) {
            return false;
        }
        if (str.startsWith("file://")) {
            str = str.replace("file://", "");
        }
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        if (!needMediaStoreOpenFile(context)) {
            return true;
        }
        String fileNameWithSuffix = getFileNameWithSuffix(str);
        return (PdrUtil.isEmpty(fileNameWithSuffix) || getFileUri(context, file, getContentUriForSuffix(fileNameWithSuffix)) == null) ? false : true;
    }

    public static boolean needMediaStoreOpenFile(Context context) {
        int i = Build.VERSION.SDK_INT;
        return (i >= 29 && context.getApplicationInfo().targetSdkVersion >= 29) || i >= 30;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003a A[PHI: r8
  0x003a: PHI (r8v4 android.database.Cursor) = (r8v3 android.database.Cursor), (r8v5 android.database.Cursor) binds: [B:20:0x0038, B:13:0x002e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String queryAbsolutePath(android.content.Context r8, android.net.Uri r9) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r7 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            r4 = 0
            r5 = 0
            r6 = 0
            r2 = r9
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            if (r8 == 0) goto L2e
            boolean r9 = r8.moveToFirst()     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L3e
            if (r9 == 0) goto L2e
            int r9 = r8.getColumnIndexOrThrow(r0)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L3e
            java.lang.String r9 = r8.getString(r9)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L3e
            r8.close()     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L3e
            if (r8 == 0) goto L2b
            r8.close()
        L2b:
            return r9
        L2c:
            r9 = move-exception
            goto L35
        L2e:
            if (r8 == 0) goto L3d
            goto L3a
        L31:
            r9 = move-exception
            goto L40
        L33:
            r9 = move-exception
            r8 = r7
        L35:
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L3e
            if (r8 == 0) goto L3d
        L3a:
            r8.close()
        L3d:
            return r7
        L3e:
            r9 = move-exception
            r7 = r8
        L40:
            if (r7 == 0) goto L45
            r7.close()
        L45:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.FileUtil.queryAbsolutePath(android.content.Context, android.net.Uri):java.lang.String");
    }

    public static Object readData4Disk(Context context, String str) throws Throwable {
        ObjectInputStream objectInputStream;
        ObjectInputStream objectInputStream2 = null;
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(context.getFileStreamPath(str)));
            } catch (Exception unused) {
                objectInputStream = null;
            } catch (Throwable th) {
                th = th;
            }
            try {
                Object object = objectInputStream.readObject();
                objectInputStream.close();
                IOUtil.close(objectInputStream);
                return object;
            } catch (Exception unused2) {
                IOUtil.close(objectInputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                objectInputStream2 = objectInputStream;
                IOUtil.close(objectInputStream2);
                throw th;
            }
        }
        return null;
    }

    public static void saveData2Disk(Context context, Object obj, String str) throws Throwable {
        ObjectOutputStream objectOutputStream;
        if (context == null || obj == null || !(obj instanceof Serializable) || TextUtils.isEmpty(str)) {
            return;
        }
        ObjectOutputStream objectOutputStream2 = null;
        try {
            File fileStreamPath = context.getFileStreamPath(str);
            if (!fileStreamPath.exists()) {
                fileStreamPath.createNewFile();
            }
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileStreamPath));
        } catch (IOException unused) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
            IOUtil.close(objectOutputStream);
        } catch (IOException unused2) {
            objectOutputStream2 = objectOutputStream;
            IOUtil.close(objectOutputStream2);
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream2 = objectOutputStream;
            IOUtil.close(objectOutputStream2);
            throw th;
        }
    }

    public static void writeStream2File(InputStream inputStream, File file) throws Throwable {
        FileOutputStream fileOutputStream = null;
        if (inputStream != null) {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = inputStream.read(bArr);
                            if (i == -1) {
                                break;
                            } else {
                                fileOutputStream2.write(bArr, 0, i);
                            }
                        }
                        fileOutputStream2.close();
                        inputStream.close();
                        fileOutputStream = fileOutputStream2;
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        IOUtil.close(inputStream);
                        IOUtil.close(fileOutputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
        IOUtil.close(inputStream);
        IOUtil.close(fileOutputStream);
    }

    public static Uri getImageFileUri(Context context, File file) {
        if (checkPrivatePath(context, file.getPath())) {
            return Uri.fromFile(file);
        }
        return getFileUri(context, file, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }

    public static InputStream getFileInputStream(Context context, File file) {
        Uri fileUri;
        if (file != null && !TextUtils.isEmpty(file.getPath())) {
            try {
                String path = file.getPath();
                if (checkPrivatePath(context, path)) {
                    return new FileInputStream(file);
                }
                String fileNameWithSuffix = getFileNameWithSuffix(path);
                if (needMediaStoreOpenFile(context)) {
                    if (!TextUtils.isEmpty(fileNameWithSuffix)) {
                        Uri fileUri2 = getFileUri(context, file, getContentUriForSuffix(fileNameWithSuffix));
                        if (fileUri2 != null) {
                            return context.getContentResolver().openInputStream(fileUri2);
                        }
                    } else {
                        String pathForPublicType = getPathForPublicType(path);
                        if (!TextUtils.isEmpty(pathForPublicType)) {
                            switch (pathForPublicType) {
                                case "DCIM":
                                    Uri fileUri3 = getFileUri(context, file, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    if (fileUri3 != null) {
                                        return context.getContentResolver().openInputStream(fileUri3);
                                    }
                                    break;
                                case "Download":
                                    if (Build.VERSION.SDK_INT >= 29 && (fileUri = getFileUri(context, file, MediaStore.Downloads.EXTERNAL_CONTENT_URI)) != null) {
                                        return context.getContentResolver().openInputStream(fileUri);
                                    }
                                    break;
                                case "Movies":
                                    Uri fileUri4 = getFileUri(context, file, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                                    if (fileUri4 != null) {
                                        return context.getContentResolver().openInputStream(fileUri4);
                                    }
                                    break;
                                case "Music":
                                    Uri fileUri5 = getFileUri(context, file, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                                    if (fileUri5 != null) {
                                        return context.getContentResolver().openInputStream(fileUri5);
                                    }
                                    break;
                                case "Pictures":
                                    Uri fileUri6 = getFileUri(context, file, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    if (fileUri6 != null) {
                                        return context.getContentResolver().openInputStream(fileUri6);
                                    }
                                    break;
                                case "Documents":
                                    Uri fileUri7 = getFileUri(context, file, MediaStore.Files.getContentUri("external"));
                                    if (fileUri7 != null) {
                                        return context.getContentResolver().openInputStream(fileUri7);
                                    }
                                    break;
                                case "Ringtones":
                                    Uri fileUri8 = getFileUri(context, file, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                                    if (fileUri8 != null) {
                                        return context.getContentResolver().openInputStream(fileUri8);
                                    }
                                    break;
                                default:
                                    Uri fileUri9 = getFileUri(context, file, MediaStore.Files.getContentUri("external"));
                                    if (fileUri9 != null) {
                                        return context.getContentResolver().openInputStream(fileUri9);
                                    }
                                    break;
                            }
                        }
                    }
                } else {
                    return new FileInputStream(file);
                }
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static Uri copyMediaFileToDCIM(Context context, InputStream inputStream, String str) {
        if (!needMediaStoreOpenFile(context) || inputStream == null) {
            return null;
        }
        try {
            String fileNameWithSuffix = getFileNameWithSuffix(str);
            if (TextUtils.isEmpty(getFileTypeForSuffix(fileNameWithSuffix))) {
                return null;
            }
            return copyMediaFile(context, inputStream, str, fileNameWithSuffix, "DCIM/", "Camera/");
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
