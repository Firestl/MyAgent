package com.ta.utdid2.device;

import android.content.Context;
import com.ta.utdid2.android.utils.PhoneInfoUtils;
import com.ta.utdid2.android.utils.StringUtils;
import java.util.zip.Adler32;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceInfo {
    public static final Object CREATE_DEVICE_METADATA_LOCK = new Object();
    public static String HMAC_KEY = "d6fc3a4a06adbde89223bvefedc24fecde188aaa9161";
    public static final byte UTDID_VERSION_CODE = 1;
    public static Device mDevice;

    public static Device _initDeviceMetadata(Context context) {
        if (context == null) {
            return null;
        }
        new Device();
        synchronized (CREATE_DEVICE_METADATA_LOCK) {
            String value = UTUtdid.instance(context).getValue();
            if (StringUtils.isEmpty(value)) {
                return null;
            }
            if (value.endsWith("\n")) {
                value = value.substring(0, value.length() - 1);
            }
            Device device = new Device();
            long jCurrentTimeMillis = System.currentTimeMillis();
            String imei = PhoneInfoUtils.getImei(context);
            String imsi = PhoneInfoUtils.getImsi(context);
            device.setDeviceId(imei);
            device.setImei(imei);
            device.setCreateTimestamp(jCurrentTimeMillis);
            device.setImsi(imsi);
            device.setUtdid(value);
            device.setCheckSum(getMetadataCheckSum(device));
            return device;
        }
    }

    public static synchronized Device getDevice(Context context) {
        if (mDevice != null) {
            return mDevice;
        }
        if (context == null) {
            return null;
        }
        Device device_initDeviceMetadata = _initDeviceMetadata(context);
        mDevice = device_initDeviceMetadata;
        return device_initDeviceMetadata;
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
}
