package com.getui.gtc.dim.c;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Build;
import android.os.Parcel;
import android.text.TextUtils;
import com.umeng.analytics.pro.f;
import io.dcloud.feature.weex_amap.adapter.Constant;
import java.lang.reflect.Field;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static int a(String str) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            TextUtils.writeToParcel(str, parcelObtain, 0);
            return parcelObtain.dataPosition() - 4;
        } finally {
            parcelObtain.recycle();
        }
    }

    public static String a(Location location) throws JSONException {
        double d;
        double d2;
        double d3;
        JSONObject jSONObject;
        int i = Build.VERSION.SDK_INT;
        if (i < 28) {
            jSONObject = b(location);
        } else {
            Parcel parcelObtain = Parcel.obtain();
            try {
                location.writeToParcel(parcelObtain, 0);
                parcelObtain.setDataPosition(0);
                if (i == 28) {
                    parcelObtain.readString();
                    parcelObtain.readLong();
                    parcelObtain.readLong();
                    parcelObtain.readByte();
                    d = parcelObtain.readDouble();
                    d2 = parcelObtain.readDouble();
                } else {
                    if (i != 29 && i != 30) {
                        if (i < 31 || i > 33) {
                            throw new UnsupportedOperationException("cannot read location,API>33");
                        }
                        if (i != 33) {
                            parcelObtain.readString();
                        } else {
                            parcelObtain.setDataPosition(a(location.getProvider()));
                        }
                        parcelObtain.readInt();
                        parcelObtain.readLong();
                        parcelObtain.readLong();
                        if (location.hasElapsedRealtimeUncertaintyNanos()) {
                            parcelObtain.readDouble();
                        }
                        double d4 = parcelObtain.readDouble();
                        d2 = parcelObtain.readDouble();
                        d3 = location.hasAltitude() ? parcelObtain.readDouble() : 0.0d;
                        d = d4;
                        jSONObject = new JSONObject();
                        jSONObject.put(Constant.JSONKEY.LATITUDE, d);
                        jSONObject.put(Constant.JSONKEY.LONGITUDE, d2);
                        jSONObject.put("altitude", d3);
                    }
                    parcelObtain.readString();
                    parcelObtain.readLong();
                    parcelObtain.readLong();
                    parcelObtain.readDouble();
                    parcelObtain.readInt();
                    d = parcelObtain.readDouble();
                    d2 = parcelObtain.readDouble();
                }
                d3 = parcelObtain.readDouble();
                jSONObject = new JSONObject();
                jSONObject.put(Constant.JSONKEY.LATITUDE, d);
                jSONObject.put(Constant.JSONKEY.LONGITUDE, d2);
                jSONObject.put("altitude", d3);
            } finally {
                parcelObtain.recycle();
            }
        }
        jSONObject.put("hasAccuracy", location.hasAccuracy());
        jSONObject.put("time", location.getTime());
        jSONObject.put(f.M, location.getProvider());
        if (i >= 17) {
            jSONObject.put("elapsedRealtimeNanos", location.getElapsedRealtimeNanos());
        }
        jSONObject.put("accuracy", String.valueOf(location.getAccuracy()));
        return jSONObject.toString();
    }

    @SuppressLint({"SoonBlockedPrivateApi"})
    public static JSONObject b(Location location) {
        JSONObject jSONObject = new JSONObject();
        try {
            Field declaredField = Location.class.getDeclaredField("mLatitude");
            declaredField.setAccessible(true);
            Field declaredField2 = Location.class.getDeclaredField("mLongitude");
            declaredField2.setAccessible(true);
            Field declaredField3 = Location.class.getDeclaredField("mAltitude");
            declaredField3.setAccessible(true);
            jSONObject.put(Constant.JSONKEY.LATITUDE, declaredField.getDouble(location));
            jSONObject.put(Constant.JSONKEY.LONGITUDE, declaredField2.getDouble(location));
            jSONObject.put("altitude", declaredField3.getDouble(location));
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a("location getBelow28", th);
        }
        return jSONObject;
    }
}
