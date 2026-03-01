package com.ta.utdid2.device;

import android.content.Context;
import android.provider.Settings;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.android.utils.IntUtils;
import com.ta.utdid2.android.utils.PhoneInfoUtils;
import com.ta.utdid2.android.utils.StringUtils;
import com.ta.utdid2.core.persistent.PersistentConfiguration;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.zip.Adler32;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public class UTUtdid {
    public static final String HMAC_KEY = "d6fc3a4a06adbde89223bvefedc24fecde188aaa9161";
    public static final String S_GLOBAL_PERSISTENT_CONFIG_KEY = "Alvin2";
    public static final String S_LOCAL_STORAGE_KEY = "ContextData";
    public static final String S_LOCAL_STORAGE_NAME = ".DataStorage";
    public static final String UM_SETTINGS_STORAGE = "dxCRMxhQkdGePGnp";
    public static final String UM_SETTINGS_STORAGE_NEW = "mqBRboGZkQPcAkyk";
    public static UTUtdid s_umutdid;
    public String mCBDomain;
    public String mCBKey;
    public Context mContext;
    public PersistentConfiguration mPC;
    public PersistentConfiguration mTaoPC;
    public UTUtdidHelper mUtdidHelper;
    public static final Object CREATE_LOCK = new Object();
    public static final String S_GLOBAL_PERSISTENT_CONFIG_DIR = ".UTSystemConfig" + File.separator + "Global";
    public String mUtdid = null;
    public Pattern mPattern = Pattern.compile("[^0-9a-zA-Z=/+]+");

    public UTUtdid(Context context) {
        this.mContext = null;
        this.mUtdidHelper = null;
        this.mCBKey = "xx_utdid_key";
        this.mCBDomain = "xx_utdid_domain";
        this.mPC = null;
        this.mTaoPC = null;
        this.mContext = context;
        this.mTaoPC = new PersistentConfiguration(context, S_GLOBAL_PERSISTENT_CONFIG_DIR, S_GLOBAL_PERSISTENT_CONFIG_KEY, false, true);
        this.mPC = new PersistentConfiguration(context, S_LOCAL_STORAGE_NAME, S_LOCAL_STORAGE_KEY, false, true);
        this.mUtdidHelper = new UTUtdidHelper();
        this.mCBKey = String.format("K_%d", Integer.valueOf(StringUtils.hashCode(this.mCBKey)));
        this.mCBDomain = String.format("D_%d", Integer.valueOf(StringUtils.hashCode(this.mCBDomain)));
    }

    public static String _calcHmac(byte[] bArr) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(HMAC_KEY.getBytes(), mac.getAlgorithm()));
        return Base64.encodeToString(mac.doFinal(bArr), 2);
    }

    private final byte[] _generateUtdid() throws Exception {
        String imei;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int iCurrentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int iNextInt = new Random().nextInt();
        byte[] bytes = IntUtils.getBytes(iCurrentTimeMillis);
        byte[] bytes2 = IntUtils.getBytes(iNextInt);
        byteArrayOutputStream.write(bytes, 0, 4);
        byteArrayOutputStream.write(bytes2, 0, 4);
        byteArrayOutputStream.write(3);
        byteArrayOutputStream.write(0);
        try {
            imei = PhoneInfoUtils.getImei(this.mContext);
        } catch (Exception unused) {
            imei = "" + new Random().nextInt();
        }
        byteArrayOutputStream.write(IntUtils.getBytes(StringUtils.hashCode(imei)), 0, 4);
        byteArrayOutputStream.write(IntUtils.getBytes(StringUtils.hashCode(_calcHmac(byteArrayOutputStream.toByteArray()))));
        return byteArrayOutputStream.toByteArray();
    }

    public static long getMetadataCheckSum(Device device) {
        if (device == null) {
            return 0L;
        }
        String str = String.format("%s%s%s%s%s", device.getUtdid(), device.getDeviceId(), Long.valueOf(device.getCreateTimestamp()), device.getImsi(), device.getImei());
        if (StringUtils.isEmpty(str)) {
            return 0L;
        }
        Adler32 adler32 = new Adler32();
        adler32.reset();
        adler32.update(str.getBytes());
        return adler32.getValue();
    }

    private String getUtdidFromTaoPPC() {
        PersistentConfiguration persistentConfiguration = this.mTaoPC;
        if (persistentConfiguration == null) {
            return null;
        }
        String string = persistentConfiguration.getString("UTDID");
        if (StringUtils.isEmpty(string) || this.mUtdidHelper.packUtdidStr(string) == null) {
            return null;
        }
        return string;
    }

    public static UTUtdid instance(Context context) {
        if (context != null && s_umutdid == null) {
            synchronized (CREATE_LOCK) {
                if (s_umutdid == null) {
                    s_umutdid = new UTUtdid(context);
                }
            }
        }
        return s_umutdid;
    }

    private boolean isValidUTDID(String str) {
        if (str != null) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (24 == str.length() && !this.mPattern.matcher(str).find()) {
                return true;
            }
        }
        return false;
    }

    private void saveUtdidToLocalStorage(String str) {
        PersistentConfiguration persistentConfiguration;
        if (str == null || (persistentConfiguration = this.mPC) == null || str.equals(persistentConfiguration.getString(this.mCBKey))) {
            return;
        }
        this.mPC.putString(this.mCBKey, str);
        this.mPC.commit();
    }

    private void saveUtdidToNewSettings(String str) {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0 && isValidUTDID(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (24 != str.length() || isValidUTDID(Settings.System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE_NEW))) {
                return;
            }
            Settings.System.putString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE_NEW, str);
        }
    }

    private void saveUtdidToSettings(String str) {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") != 0 || str == null) {
            return;
        }
        syncUTDIDToSettings(str);
    }

    private void saveUtdidToTaoPPC(String str) {
        PersistentConfiguration persistentConfiguration;
        if (isValidUTDID(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (str.length() != 24 || (persistentConfiguration = this.mTaoPC) == null) {
                return;
            }
            String string = persistentConfiguration.getString("UTDID");
            String string2 = this.mTaoPC.getString("EI");
            if (StringUtils.isEmpty(string2)) {
                string2 = PhoneInfoUtils.getImei(this.mContext);
            }
            String string3 = this.mTaoPC.getString("SI");
            if (StringUtils.isEmpty(string3)) {
                string3 = PhoneInfoUtils.getImsi(this.mContext);
            }
            String string4 = this.mTaoPC.getString("DID");
            if (StringUtils.isEmpty(string4)) {
                string4 = string2;
            }
            if (string == null || !string.equals(str)) {
                Device device = new Device();
                device.setImei(string2);
                device.setImsi(string3);
                device.setUtdid(str);
                device.setDeviceId(string4);
                device.setCreateTimestamp(System.currentTimeMillis());
                this.mTaoPC.putString("UTDID", str);
                this.mTaoPC.putString("EI", device.getImei());
                this.mTaoPC.putString("SI", device.getImsi());
                this.mTaoPC.putString("DID", device.getDeviceId());
                this.mTaoPC.putLong("timestamp", device.getCreateTimestamp());
                this.mTaoPC.putLong("S", getMetadataCheckSum(device));
                this.mTaoPC.commit();
            }
        }
    }

    private void syncUTDIDToSettings(String str) {
        if (str.equals(Settings.System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE))) {
            return;
        }
        Settings.System.putString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE, str);
    }

    public synchronized String getValue() {
        if (this.mUtdid != null) {
            return this.mUtdid;
        }
        String string = Settings.System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE_NEW);
        if (isValidUTDID(string)) {
            return string;
        }
        UTUtdidHelper2 uTUtdidHelper2 = new UTUtdidHelper2();
        boolean z = false;
        String string2 = Settings.System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE);
        if (StringUtils.isEmpty(string2)) {
            z = true;
        } else {
            String strDePackWithBase64 = uTUtdidHelper2.dePackWithBase64(string2);
            if (isValidUTDID(strDePackWithBase64)) {
                saveUtdidToNewSettings(strDePackWithBase64);
                return strDePackWithBase64;
            }
            String strDePack = uTUtdidHelper2.dePack(string2);
            if (isValidUTDID(strDePack)) {
                String strPackUtdidStr = this.mUtdidHelper.packUtdidStr(strDePack);
                if (!StringUtils.isEmpty(strPackUtdidStr)) {
                    saveUtdidToSettings(strPackUtdidStr);
                    string2 = Settings.System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE);
                }
            }
            String strDePack2 = this.mUtdidHelper.dePack(string2);
            if (isValidUTDID(strDePack2)) {
                this.mUtdid = strDePack2;
                saveUtdidToTaoPPC(strDePack2);
                saveUtdidToLocalStorage(string2);
                saveUtdidToNewSettings(this.mUtdid);
                return this.mUtdid;
            }
        }
        String utdidFromTaoPPC = getUtdidFromTaoPPC();
        if (isValidUTDID(utdidFromTaoPPC)) {
            String strPackUtdidStr2 = this.mUtdidHelper.packUtdidStr(utdidFromTaoPPC);
            if (z) {
                saveUtdidToSettings(strPackUtdidStr2);
            }
            saveUtdidToNewSettings(utdidFromTaoPPC);
            saveUtdidToLocalStorage(strPackUtdidStr2);
            this.mUtdid = utdidFromTaoPPC;
            return utdidFromTaoPPC;
        }
        String string3 = this.mPC.getString(this.mCBKey);
        if (!StringUtils.isEmpty(string3)) {
            String strDePack3 = uTUtdidHelper2.dePack(string3);
            if (!isValidUTDID(strDePack3)) {
                strDePack3 = this.mUtdidHelper.dePack(string3);
            }
            if (isValidUTDID(strDePack3)) {
                String strPackUtdidStr3 = this.mUtdidHelper.packUtdidStr(strDePack3);
                if (!StringUtils.isEmpty(strDePack3)) {
                    this.mUtdid = strDePack3;
                    if (z) {
                        saveUtdidToSettings(strPackUtdidStr3);
                    }
                    saveUtdidToTaoPPC(this.mUtdid);
                    return this.mUtdid;
                }
            }
        }
        try {
            byte[] bArr_generateUtdid = _generateUtdid();
            if (bArr_generateUtdid != null) {
                String strEncodeToString = Base64.encodeToString(bArr_generateUtdid, 2);
                this.mUtdid = strEncodeToString;
                saveUtdidToTaoPPC(strEncodeToString);
                String strPack = this.mUtdidHelper.pack(bArr_generateUtdid);
                if (strPack != null) {
                    if (z) {
                        saveUtdidToSettings(strPack);
                    }
                    saveUtdidToLocalStorage(strPack);
                }
                return this.mUtdid;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }
}
