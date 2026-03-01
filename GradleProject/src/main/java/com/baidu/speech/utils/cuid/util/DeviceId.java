package com.baidu.speech.utils.cuid.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import android.system.ErrnoException;
import android.system.Os;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.speech.utils.MD5Util;
import com.baidu.speech.utils.cuid.security.AESUtil;
import com.baidu.speech.utils.cuid.security.Base64;
import com.baidu.speech.utils.cuid.security.SHA1Util;
import com.huawei.hms.framework.common.ContainerUtils;
import com.taobao.weex.wson.Wson;
import com.umeng.commonsdk.statistics.idtracking.b;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.crypto.Cipher;
import org.bouncycastle.jcajce.provider.asymmetric.edec.KeyFactorySpi;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceId {
    public static final String ACTION_GLAXY_CUID = "com.baidu.intent.action.GALAXY";
    public static final String AES_KEY = new String(Base64.decode(new byte[]{77, 122, 65, 121, 77, 84, 73, 120, 77, 68, 73, 61})) + new String(Base64.decode(new byte[]{90, 71, Wson.NUMBER_LONG_TYPE, 106, Wson.NUMBER_DOUBLE_TYPE, 87, 82, KeyFactorySpi.Ed25519_type, 89, 87, 73, 61}));
    public static final boolean CONFIG_WRITE_V1_STORAGE = false;
    public static final boolean DEBUG = false;
    public static final String DEFAULT_TM_DEVICEID = "";
    public static final String EXT_DIR = "backups/.SystemConfig";
    public static final String EXT_FILE = ".cuid";
    public static final String EXT_FILE_V2 = ".cuid2";
    public static final String KEY_DEVICE_ID = "com.baidu.deviceid";
    public static final String KEY_DEVICE_ID_V2 = "com.baidu.deviceid.v2";
    public static final String KEY_FLAG = "bd_setting_i";
    public static final String META_KEY_GALAXY_SF = "galaxy_sf";
    public static final String META_KEY_GLAXY_DATA = "galaxy_data";
    public static final String OLD_EXT_DIR = "baidu";
    public static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";
    public static final int SDK_ANDROID_M = 23;
    public static final String SELF_CUID_FILE = "libcuid.so";
    public static final int STORAGE_SDCARD_V1 = 4;
    public static final int STORAGE_SDCARD_V2 = 8;
    public static final int STORAGE_SELF_FILE = 16;
    public static final int STORAGE_SYSTEM_SETTING_V1 = 1;
    public static final int STORAGE_SYSTEM_SETTING_V2 = 2;
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
    public static final String TAG = "DeviceId";
    public static CUIDInfo sCachedCuidInfo = null;
    public static final String sDEncoded = "ZGV2aWNlaWQ=";
    public static boolean sDataCuidInfoShable = true;
    public static final String sIEncoded = "aW1laQ==";
    public static final String sVEncoded = "dmVy";
    public final Context mContext;
    public PublicKey mPublicKey;
    public int mSaveMask = 0;

    public static class CUIDBuddyInfo {
        public ApplicationInfo appInfo;
        public boolean isSelf;
        public int priority;
        public boolean sigMatched;

        public CUIDBuddyInfo() {
            this.priority = 0;
            this.sigMatched = false;
            this.isSelf = false;
        }
    }

    public static class CUIDInfo {
        public static final String I_EMPTY = "0";
        public static final String I_FIXED = "O";
        public static final int PROTOCAL_MAX_LENGTH = 14;
        public static final int VERSION_DEF = 2;
        public String deviceId;
        public String flag;
        public int oldValueLength;
        public int version;

        public CUIDInfo() {
            this.version = 2;
            this.oldValueLength = 0;
        }

        public static CUIDInfo createCuidInfoFromV1Cache(String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            CUIDInfo cUIDInfo = new CUIDInfo();
            cUIDInfo.deviceId = str;
            int length = TextUtils.isEmpty(str2) ? 0 : str2.length();
            cUIDInfo.oldValueLength = length;
            if (length < 14) {
                if (TextUtils.isEmpty(str2)) {
                    str2 = "0";
                }
                cUIDInfo.flag = str2;
            }
            return cUIDInfo;
        }

        public static CUIDInfo createFromJson(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                Iterator<String> itKeys = jSONObject.keys();
                String str2 = "0";
                String strOptString = "0";
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    if (!DeviceId.getBase64DecodeStr(DeviceId.sDEncoded).equals(next) && !DeviceId.getBase64DecodeStr(DeviceId.sVEncoded).equals(next)) {
                        strOptString = jSONObject.optString(next, "0");
                    }
                }
                String string = jSONObject.getString(DeviceId.getBase64DecodeStr(DeviceId.sDEncoded));
                int i = jSONObject.getInt(DeviceId.getBase64DecodeStr(DeviceId.sVEncoded));
                int length = TextUtils.isEmpty(strOptString) ? 0 : strOptString.length();
                if (!TextUtils.isEmpty(string)) {
                    CUIDInfo cUIDInfo = new CUIDInfo();
                    cUIDInfo.deviceId = string;
                    cUIDInfo.version = i;
                    cUIDInfo.oldValueLength = length;
                    if (length < 14) {
                        if (!TextUtils.isEmpty(strOptString)) {
                            str2 = strOptString;
                        }
                        cUIDInfo.flag = str2;
                    }
                    return cUIDInfo;
                }
            } catch (JSONException e2) {
                DeviceId.handleThrowable(e2);
            }
            return null;
        }

        public static boolean isIENormal(int i) {
            return i >= 14;
        }

        public static boolean isIENull(String str) {
            return TextUtils.isEmpty(str);
        }

        public String getFinalCUID() {
            String str = this.flag;
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
            return this.deviceId + "|" + str;
        }

        public boolean isIENormal() {
            return isIENormal(this.oldValueLength);
        }

        public boolean isIENull() {
            return isIENull(this.flag);
        }

        public String toPersitString() {
            try {
                return new JSONObject().put(DeviceId.getBase64DecodeStr(DeviceId.sDEncoded), this.deviceId).put(DeviceId.getBase64DecodeStr(DeviceId.sIEncoded), this.flag).put(DeviceId.getBase64DecodeStr(DeviceId.sVEncoded), this.version).toString();
            } catch (JSONException e2) {
                DeviceId.handleThrowable(e2);
                return null;
            }
        }
    }

    public static class TargetApiSupport {
        @TargetApi(21)
        public static boolean doChmodSafely(String str, int i) {
            try {
                Os.chmod(str, i);
                return true;
            } catch (ErrnoException e2) {
                DeviceId.handleThrowable(e2);
                return false;
            } catch (Exception e3) {
                DeviceId.handleThrowable(e3);
                return false;
            }
        }
    }

    public DeviceId(Context context) throws Throwable {
        this.mContext = context.getApplicationContext();
        initPublicKey();
    }

    public static String byte2hex(byte[] bArr) {
        StringBuilder sb;
        if (bArr == null) {
            throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
        }
        String string = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb = new StringBuilder();
                sb.append(string);
                string = "0";
            } else {
                sb = new StringBuilder();
            }
            sb.append(string);
            sb.append(hexString);
            string = sb.toString();
        }
        return string.toLowerCase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkSelfPermission(String str) {
        return this.mContext.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    private List<CUIDBuddyInfo> collectBuddyInfos(Intent intent, boolean z) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = this.mContext.getPackageManager();
        List<ResolveInfo> listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (listQueryBroadcastReceivers != null) {
            for (ResolveInfo resolveInfo : listQueryBroadcastReceivers) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                if (activityInfo != null && activityInfo.applicationInfo != null) {
                    try {
                        Bundle bundle = packageManager.getReceiverInfo(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name), 128).metaData;
                        if (bundle != null) {
                            String string = bundle.getString(META_KEY_GLAXY_DATA);
                            if (!TextUtils.isEmpty(string)) {
                                byte[] bArrDecode = Base64.decode(string.getBytes("utf-8"));
                                JSONObject jSONObject = new JSONObject(new String(bArrDecode));
                                CUIDBuddyInfo cUIDBuddyInfo = new CUIDBuddyInfo();
                                cUIDBuddyInfo.priority = jSONObject.getInt("priority");
                                cUIDBuddyInfo.appInfo = resolveInfo.activityInfo.applicationInfo;
                                if (this.mContext.getPackageName().equals(resolveInfo.activityInfo.applicationInfo.packageName)) {
                                    cUIDBuddyInfo.isSelf = true;
                                }
                                if (z) {
                                    String string2 = bundle.getString(META_KEY_GALAXY_SF);
                                    if (!TextUtils.isEmpty(string2)) {
                                        PackageInfo packageInfo = packageManager.getPackageInfo(resolveInfo.activityInfo.applicationInfo.packageName, 64);
                                        JSONArray jSONArray = jSONObject.getJSONArray("sigs");
                                        int length = jSONArray.length();
                                        String[] strArr = new String[length];
                                        for (int i = 0; i < length; i++) {
                                            strArr[i] = jSONArray.getString(i);
                                        }
                                        if (isSigsEqual(strArr, formatAndroidSigArray(packageInfo.signatures))) {
                                            byte[] bArrDecryptByPublicKey = decryptByPublicKey(Base64.decode(string2.getBytes()), this.mPublicKey);
                                            if (bArrDecryptByPublicKey != null && Arrays.equals(bArrDecryptByPublicKey, SHA1Util.sha1(bArrDecode))) {
                                                cUIDBuddyInfo.sigMatched = true;
                                            }
                                        }
                                    }
                                }
                                arrayList.add(cUIDBuddyInfo);
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        Collections.sort(arrayList, new Comparator<CUIDBuddyInfo>() { // from class: com.baidu.speech.utils.cuid.util.DeviceId.1
            @Override // java.util.Comparator
            public int compare(CUIDBuddyInfo cUIDBuddyInfo2, CUIDBuddyInfo cUIDBuddyInfo3) {
                int i2 = cUIDBuddyInfo3.priority - cUIDBuddyInfo2.priority;
                if (i2 == 0) {
                    if (cUIDBuddyInfo2.isSelf && cUIDBuddyInfo3.isSelf) {
                        return 0;
                    }
                    if (cUIDBuddyInfo2.isSelf) {
                        return -1;
                    }
                    if (cUIDBuddyInfo3.isSelf) {
                        return 1;
                    }
                }
                return i2;
            }
        });
        return arrayList;
    }

    public static byte[] decryptByPublicKey(byte[] bArr, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(2, publicKey);
        return cipher.doFinal(bArr);
    }

    public static String decryptCUIDInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new String(AESUtil.decrypt(AES_KEY, AES_KEY, Base64.decode(str.getBytes())));
        } catch (Exception e2) {
            handleThrowable(e2);
            return "";
        }
    }

    public static String encryptCUIDInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Base64.encode(AESUtil.encrypt(AES_KEY, AES_KEY, str.getBytes()), "utf-8");
        } catch (UnsupportedEncodingException | Exception e2) {
            handleThrowable(e2);
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean fixCUIDInfoByIE(CUIDInfo cUIDInfo) {
        String str;
        if (cUIDInfo.isIENormal()) {
            str = CUIDInfo.I_FIXED;
        } else {
            if (!cUIDInfo.isIENull()) {
                return false;
            }
            str = "0";
        }
        cUIDInfo.flag = str;
        return true;
    }

    private String[] formatAndroidSigArray(Signature[] signatureArr) {
        int length = signatureArr.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = byte2hex(SHA1Util.sha1(signatureArr[i].toByteArray()));
        }
        return strArr;
    }

    public static String getAndroidId(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), b.f5435a);
        return TextUtils.isEmpty(string) ? "" : string;
    }

    public static String getBase64DecodeStr(String str) {
        return new String(Base64.decode(str.getBytes()));
    }

    public static String getCUID(Context context) {
        return getOrCreateCUIDInfo(context).getFinalCUID();
    }

    private CUIDInfo getCUIDInfo() throws Throwable {
        boolean z;
        String defaultFlag;
        List<CUIDBuddyInfo> listCollectBuddyInfos = collectBuddyInfos(new Intent(ACTION_GLAXY_CUID).setPackage(this.mContext.getPackageName()), true);
        boolean z2 = false;
        if (listCollectBuddyInfos == null || listCollectBuddyInfos.size() == 0) {
            for (int i = 0; i < 3; i++) {
                Log.w("DeviceId", "galaxy lib host missing meta-data,make sure you know the right way to integrate galaxy");
            }
            z = false;
        } else {
            z = listCollectBuddyInfos.get(0).sigMatched;
            if (!z) {
                for (int i2 = 0; i2 < 3; i2++) {
                    Log.w("DeviceId", "galaxy config err, In the release version of the signature should be matched");
                }
            }
        }
        File file = new File(this.mContext.getFilesDir(), SELF_CUID_FILE);
        CUIDInfo cUIDInfoCreateFromJson = file.exists() ? CUIDInfo.createFromJson(decryptCUIDInfo(getFileContent(file))) : null;
        if (cUIDInfoCreateFromJson == null) {
            this.mSaveMask |= 16;
            List<CUIDBuddyInfo> listCollectBuddyInfos2 = collectBuddyInfos(new Intent(ACTION_GLAXY_CUID), z);
            if (listCollectBuddyInfos2 != null) {
                String name = "files";
                File filesDir = this.mContext.getFilesDir();
                if (!"files".equals(filesDir.getName())) {
                    Log.e("DeviceId", "fetal error:: app files dir name is unexpectedly :: " + filesDir.getAbsolutePath());
                    name = filesDir.getName();
                }
                for (CUIDBuddyInfo cUIDBuddyInfo : listCollectBuddyInfos2) {
                    if (!cUIDBuddyInfo.isSelf) {
                        File file2 = new File(new File(cUIDBuddyInfo.appInfo.dataDir, name), SELF_CUID_FILE);
                        if (file2.exists() && (cUIDInfoCreateFromJson = CUIDInfo.createFromJson(decryptCUIDInfo(getFileContent(file2)))) != null) {
                            break;
                        }
                    }
                }
            }
        }
        if (cUIDInfoCreateFromJson == null) {
            cUIDInfoCreateFromJson = CUIDInfo.createFromJson(decryptCUIDInfo(getSystemSettingValue(KEY_DEVICE_ID_V2)));
        }
        boolean zCheckSelfPermission = checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE");
        if (cUIDInfoCreateFromJson == null && zCheckSelfPermission) {
            this.mSaveMask |= 2;
            cUIDInfoCreateFromJson = getCuidInfoFromExternalV2();
        }
        if (cUIDInfoCreateFromJson == null) {
            this.mSaveMask |= 8;
            cUIDInfoCreateFromJson = getCUidInfoFromSystemSettingV1();
        }
        if (cUIDInfoCreateFromJson == null && zCheckSelfPermission) {
            this.mSaveMask |= 1;
            defaultFlag = getDefaultFlag("");
            cUIDInfoCreateFromJson = getExternalV1DeviceId(defaultFlag);
            z2 = true;
        } else {
            defaultFlag = null;
        }
        if (cUIDInfoCreateFromJson == null) {
            this.mSaveMask |= 4;
            if (!z2) {
                defaultFlag = getDefaultFlag("");
            }
            cUIDInfoCreateFromJson = new CUIDInfo();
            String androidId = getAndroidId(this.mContext);
            cUIDInfoCreateFromJson.deviceId = MD5Util.toMd5((Build.VERSION.SDK_INT < 23 ? defaultFlag + androidId + UUID.randomUUID().toString() : "com.baidu" + androidId).getBytes(), true);
            cUIDInfoCreateFromJson.flag = defaultFlag;
        }
        fixCUIDInfoByIE(cUIDInfoCreateFromJson);
        writeJobThread(cUIDInfoCreateFromJson);
        return cUIDInfoCreateFromJson;
    }

    private CUIDInfo getCUidInfoFromSystemSettingV1() {
        return CUIDInfo.createCuidInfoFromV1Cache(getSystemSettingValue(KEY_DEVICE_ID), getSystemSettingValue(KEY_FLAG));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CUIDInfo getCuidInfoFromExternalV2() throws Throwable {
        File file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
        if (!file.exists()) {
            return null;
        }
        String fileContent = getFileContent(file);
        if (TextUtils.isEmpty(fileContent)) {
            return null;
        }
        try {
            return CUIDInfo.createFromJson(new String(AESUtil.decrypt(AES_KEY, AES_KEY, Base64.decode(fileContent.getBytes()))));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDefaultFlag(String str) {
        return "0";
    }

    public static String getDeviceID(Context context) {
        return getOrCreateCUIDInfo(context).deviceId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CUIDInfo getExternalV1DeviceId(String str) {
        String str2;
        BufferedReader bufferedReader;
        StringBuilder sb;
        String str3 = "";
        File file = new File(Environment.getExternalStorageDirectory(), "baidu/.cuid");
        if (!file.exists()) {
            file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid");
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            sb = new StringBuilder();
        } catch (FileNotFoundException | IOException | Exception unused) {
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
            sb.append(io.dcloud.common.util.Base64.CRLF);
            str2 = "";
            return CUIDInfo.createCuidInfoFromV1Cache(str3, str2);
        }
        bufferedReader.close();
        String[] strArrSplit = new String(AESUtil.decrypt(AES_KEY, AES_KEY, Base64.decode(sb.toString().getBytes()))).split(ContainerUtils.KEY_VALUE_DELIMITER);
        if (strArrSplit == null || strArrSplit.length != 2) {
            str2 = "";
        } else {
            str2 = strArrSplit[0];
            try {
                str3 = strArrSplit[1];
            } catch (FileNotFoundException | IOException | Exception unused2) {
            }
        }
        return CUIDInfo.createCuidInfoFromV1Cache(str3, str2);
    }

    public static String getFileContent(File file) throws Throwable {
        FileReader fileReader;
        char[] cArr;
        CharArrayWriter charArrayWriter;
        FileReader fileReader2 = null;
        try {
            fileReader = new FileReader(file);
        } catch (Exception e2) {
            e = e2;
            fileReader = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            try {
                cArr = new char[8192];
                charArrayWriter = new CharArrayWriter();
            } catch (Throwable th2) {
                th = th2;
                fileReader2 = fileReader;
            }
            while (true) {
                int i = fileReader.read(cArr);
                if (i <= 0) {
                    break;
                }
                charArrayWriter.write(cArr, 0, i);
                th = th2;
                fileReader2 = fileReader;
                if (fileReader2 != null) {
                    try {
                        fileReader2.close();
                    } catch (Exception e3) {
                        handleThrowable(e3);
                    }
                }
                throw th;
            }
            String string = charArrayWriter.toString();
            try {
                fileReader.close();
            } catch (Exception e4) {
                handleThrowable(e4);
            }
            return string;
        } catch (Exception e5) {
            e = e5;
            handleThrowable(e);
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e6) {
                    handleThrowable(e6);
                }
            }
            return null;
        }
    }

    public static CUIDInfo getOrCreateCUIDInfo(Context context) {
        if (sCachedCuidInfo == null) {
            synchronized (CUIDInfo.class) {
                if (sCachedCuidInfo == null) {
                    SystemClock.uptimeMillis();
                    sCachedCuidInfo = new DeviceId(context).getCUIDInfo();
                    SystemClock.uptimeMillis();
                }
            }
        }
        return sCachedCuidInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSystemSettingValue(String str) {
        try {
            return Settings.System.getString(this.mContext.getContentResolver(), str);
        } catch (Exception e2) {
            handleThrowable(e2);
            return null;
        }
    }

    private Runnable getWriteRunnable(final CUIDInfo cUIDInfo) {
        return new Runnable() { // from class: com.baidu.speech.utils.cuid.util.DeviceId.2
            /* JADX WARN: Removed duplicated region for block: B:16:0x0072  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00b6 A[PHI: r0
  0x00b6: PHI (r0v19 java.lang.String) = (r0v2 java.lang.String), (r0v2 java.lang.String), (r0v18 java.lang.String) binds: [B:27:0x00b4, B:34:0x00d3, B:33:0x00ca] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:4:0x002f  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() throws java.lang.Throwable {
                /*
                    Method dump skipped, instruction units count: 313
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.baidu.speech.utils.cuid.util.DeviceId.AnonymousClass2.run():void");
            }
        };
    }

    public static void handleThrowable(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasWriteSettingsPermission() {
        return checkSelfPermission("android.permission.WRITE_SETTINGS");
    }

    private void initPublicKey() throws Throwable {
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            try {
                byteArrayInputStream = new ByteArrayInputStream(CuidCertStore.getCertBytes());
            } catch (Exception unused) {
            } catch (Throwable th2) {
                byteArrayInputStream = null;
                th = th2;
            }
            try {
                this.mPublicKey = CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream).getPublicKey();
                byteArrayInputStream.close();
            } catch (Exception unused2) {
                byteArrayInputStream2 = byteArrayInputStream;
                if (byteArrayInputStream2 == null) {
                } else {
                    byteArrayInputStream2.close();
                }
            } catch (Throwable th3) {
                th = th3;
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Exception e2) {
                        handleThrowable(e2);
                    }
                }
                throw th;
            }
        } catch (Exception e3) {
            handleThrowable(e3);
        }
    }

    private boolean isSigsEqual(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return false;
        }
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            hashSet.add(str);
        }
        HashSet hashSet2 = new HashSet();
        for (String str2 : strArr2) {
            hashSet2.add(str2);
        }
        return hashSet.equals(hashSet2);
    }

    public static void setCuidDataShable(Context context, boolean z) {
        File file = new File(context.getApplicationContext().getFilesDir(), SELF_CUID_FILE);
        Context applicationContext = context.getApplicationContext();
        if (file.exists() && (sCachedCuidInfo == null || sDataCuidInfoShable != z)) {
            tryToModifyChmodForSelfFile(applicationContext, z);
        }
        sDataCuidInfoShable = z;
    }

    public static void setExternalDeviceId(String str, String str2) {
        File file;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file2 = new File(Environment.getExternalStorageDirectory(), EXT_DIR);
        File file3 = new File(file2, EXT_FILE);
        try {
            if (file2.exists() && !file2.isDirectory()) {
                Random random = new Random();
                File parentFile = file2.getParentFile();
                String name = file2.getName();
                do {
                    file = new File(parentFile, name + random.nextInt() + ".tmp");
                } while (file.exists());
                file2.renameTo(file);
                file.delete();
            }
            file2.mkdirs();
            FileWriter fileWriter = new FileWriter(file3, false);
            fileWriter.write(Base64.encode(AESUtil.encrypt(AES_KEY, AES_KEY, (str + ContainerUtils.KEY_VALUE_DELIMITER + str2).getBytes()), "utf-8"));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException | Exception unused) {
        }
    }

    public static void setExternalV2DeviceId(String str) {
        File file;
        File file2 = new File(Environment.getExternalStorageDirectory(), EXT_DIR);
        File file3 = new File(file2, EXT_FILE_V2);
        try {
            if (file2.exists() && !file2.isDirectory()) {
                Random random = new Random();
                File parentFile = file2.getParentFile();
                String name = file2.getName();
                do {
                    file = new File(parentFile, name + random.nextInt() + ".tmp");
                } while (file.exists());
                file2.renameTo(file);
                file.delete();
            }
            file2.mkdirs();
            FileWriter fileWriter = new FileWriter(file3, false);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException | Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean tryPutSystemSettingValue(String str, String str2) {
        try {
            return Settings.System.putString(this.mContext.getContentResolver(), str, str2);
        } catch (Exception e2) {
            handleThrowable(e2);
            return false;
        }
    }

    @SuppressLint({"NewApi"})
    public static boolean tryToModifyChmodForSelfFile(Context context, boolean z) {
        File file = new File(context.getApplicationContext().getFilesDir(), SELF_CUID_FILE);
        if (!file.exists()) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            return TargetApiSupport.doChmodSafely(file.getAbsolutePath(), z ? 436 : 432);
        }
        try {
            if (z) {
                return file.setReadable(true, false);
            }
            return file.setReadable(false, false) && file.setReadable(true, true);
        } catch (Exception e2) {
            handleThrowable(e2);
            return false;
        }
    }

    private synchronized void writeJobThread(CUIDInfo cUIDInfo) {
        new Thread(getWriteRunnable(cUIDInfo)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public boolean writeToCuidFile(String str) {
        int i;
        File file;
        int i2 = (!sDataCuidInfoShable || Build.VERSION.SDK_INT >= 24) ? 0 : 1;
        FileOutputStream fileOutputStreamOpenFileOutput = null;
        try {
            try {
                fileOutputStreamOpenFileOutput = this.mContext.openFileOutput(SELF_CUID_FILE, i2);
                fileOutputStreamOpenFileOutput.write(str.getBytes());
                fileOutputStreamOpenFileOutput.flush();
                if (fileOutputStreamOpenFileOutput != null) {
                    try {
                        fileOutputStreamOpenFileOutput.close();
                    } catch (Exception e2) {
                        handleThrowable(e2);
                    }
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    if (i2 == 0 && sDataCuidInfoShable) {
                        i = 436;
                        file = new File(this.mContext.getFilesDir(), SELF_CUID_FILE);
                    } else if (!sDataCuidInfoShable) {
                        i = 432;
                        file = new File(this.mContext.getFilesDir(), SELF_CUID_FILE);
                    }
                    return TargetApiSupport.doChmodSafely(file.getAbsolutePath(), i);
                }
                return true;
            } catch (Exception e3) {
                handleThrowable(e3);
                if (fileOutputStreamOpenFileOutput != null) {
                    try {
                        fileOutputStreamOpenFileOutput.close();
                    } catch (Exception e4) {
                        handleThrowable(e4);
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (fileOutputStreamOpenFileOutput != null) {
                try {
                    fileOutputStreamOpenFileOutput.close();
                } catch (Exception e5) {
                    handleThrowable(e5);
                }
            }
            throw th;
        }
    }

    public static void writeToFile(File file, byte[] bArr) throws Throwable {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        fileOutputStream2.write(bArr);
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                    } catch (IOException e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        handleThrowable(e);
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (SecurityException e3) {
                        e = e3;
                        fileOutputStream = fileOutputStream2;
                        handleThrowable(e);
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                handleThrowable(e4);
                            }
                        }
                        throw th;
                    }
                } catch (IOException e5) {
                    e = e5;
                } catch (SecurityException e6) {
                    e = e6;
                }
            } catch (IOException e7) {
                handleThrowable(e7);
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
