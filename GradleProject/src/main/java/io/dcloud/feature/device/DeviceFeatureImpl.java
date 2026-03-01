package io.dcloud.feature.device;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.PowerManager;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import com.huawei.agconnect.exception.AGCServerException;
import com.taobao.weex.el.parse.Operators;
import com.umeng.analytics.pro.bm;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.TelephonyUtil;
import io.dcloud.f.c.a;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceFeatureImpl implements IFeature, ISysEventListener {
    public static int g = 255;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SensorManager f6516a;
    public Sensor c;
    public Context d;
    public PowerManager.WakeLock b = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6517e = -1;
    public final SensorEventListener f = new e(this);

    public class a extends PermissionUtil.StreamPermissionRequest {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6518a;
        public final /* synthetic */ String[] b;
        public final /* synthetic */ boolean c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(IApp iApp, IWebview iWebview, String[] strArr, boolean z) {
            super(iApp);
            this.f6518a = iWebview;
            this.b = strArr;
            this.c = z;
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onDenied(String str) {
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onGranted(String str) {
            DeviceFeatureImpl.this.a(this.f6518a, this.b[0], this.c);
        }
    }

    public class b extends PermissionUtil.Request {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6519a;
        public final /* synthetic */ String b;

        public b(DeviceFeatureImpl deviceFeatureImpl, IWebview iWebview, String str) {
            this.f6519a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onDenied(String str) {
            Deprecated_JSUtil.execCallback(this.f6519a, this.b, "{'imei':'','imsi':[],'uuid':'" + TelephonyUtil.getIMEI(this.f6519a.getContext(), false, true) + "'}", JSUtil.ERROR, true, false);
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onGranted(String str) {
            DeviceInfo.updateIMEI();
            DeviceInfo.getUpdateIMSI();
            Deprecated_JSUtil.execCallback(this.f6519a, this.b, StringUtil.format("{'imei':'%s','imsi':['%s'],'uuid':'%s'}", DeviceInfo.sIMEI, DeviceInfo.sIMSI, TextUtils.isEmpty(DeviceInfo.sIMEI) ? TelephonyUtil.getIMEI(this.f6519a.getContext(), false, true) : DeviceInfo.sIMEI), JSUtil.OK, true, false);
        }
    }

    public class c implements a.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6520a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;

        public c(IWebview iWebview, String str, String str2) {
            this.f6520a = iWebview;
            this.b = str;
            this.c = str2;
        }

        @Override // io.dcloud.f.c.a.b
        public void a(String str, boolean z) {
            if (z) {
                DeviceFeatureImpl.this.a(this.f6520a, this.b, this.c, str);
            } else {
                Deprecated_JSUtil.execCallback(this.f6520a, this.c, DOMException.toJSON(AGCServerException.TOKEN_INVALID, "not support"), JSUtil.ERROR, true, false);
            }
        }
    }

    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6521a;
        public final /* synthetic */ float b;

        public d(IWebview iWebview, float f) {
            this.f6521a = iWebview;
            this.b = f;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeviceFeatureImpl.this.a(this.f6521a, this.b);
        }
    }

    public class e implements SensorEventListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final float[] f6522a = {2.0f, 2.5f, 0.5f};
        public float[] b = new float[3];
        public long c;

        public e(DeviceFeatureImpl deviceFeatureImpl) {
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] fArr = new float[3];
            boolean z = false;
            for (int i = 0; i < 3; i++) {
                fArr[i] = Math.round(this.f6522a[i] * (sensorEvent.values[i] - this.b[i]) * 0.45f);
                if (Math.abs(fArr[i]) > 0.0f) {
                    z = true;
                }
                this.b[i] = sensorEvent.values[i];
            }
            if (z) {
                Logger.i("sensorChanged " + sensorEvent.sensor.getName() + " (" + sensorEvent.values[0] + ", " + sensorEvent.values[1] + ", " + sensorEvent.values[2] + ") diff(" + fArr[0] + Operators.SPACE_STR + fArr[1] + Operators.SPACE_STR + fArr[2] + ")");
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            if (jUptimeMillis - this.c > 1000) {
                this.c = 0L;
                float f = fArr[0];
                float f2 = fArr[1];
                boolean z2 = Math.abs(f) > 3.0f;
                boolean z3 = Math.abs(f2) > 3.0f;
                if (z2 || z3) {
                    if (z2 && z3) {
                        return;
                    }
                    if (z2) {
                        if (f < 0.0f) {
                            Logger.i("test", "<<<<<<<< LEFT <<<<<<<<<<<<");
                        } else {
                            Logger.i("test", ">>>>>>>>> RITE >>>>>>>>>>>");
                        }
                    } else if (f2 < -2.0f) {
                        Logger.i("test", "<<<<<<<< UP <<<<<<<<<<<<");
                    } else {
                        Logger.i("test", ">>>>>>>>> DOWN >>>>>>>>>>>");
                    }
                    this.c = jUptimeMillis;
                }
            }
        }
    }

    static {
        try {
            Resources system = Resources.getSystem();
            int identifier = system.getIdentifier("config_screenBrightnessSettingMaximum", "integer", "android");
            if (identifier != 0) {
                g = system.getInteger(identifier);
            }
        } catch (Exception unused) {
            g = 255;
        }
    }

    private void b(IWebview iWebview, float f) {
        Log.d("dqqdo", "setScreenBrightness  ---   " + f);
        iWebview.obtainWindowView().post(new d(iWebview, f));
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0153  */
    @Override // io.dcloud.common.DHInterface.IFeature
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String execute(io.dcloud.common.DHInterface.IWebview r17, java.lang.String r18, java.lang.String[] r19) {
        /*
            Method dump skipped, instruction units count: 1088
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.device.DeviceFeatureImpl.execute(io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String[]):java.lang.String");
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        SensorManager sensorManager = (SensorManager) absMgr.getContext().getSystemService(bm.ac);
        this.f6516a = sensorManager;
        this.c = sensorManager.getDefaultSensor(1);
        Context context = absMgr.getContext();
        this.d = context;
        PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(10, "My Lock");
        this.b = wakeLockNewWakeLock;
        wakeLockNewWakeLock.setReferenceCounted(false);
        this.f6517e = ((AudioManager) absMgr.getContext().getSystemService("audio")).getStreamMaxVolume(3);
    }

    @Override // io.dcloud.common.DHInterface.ISysEventListener
    public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
        if (sysEventType == ISysEventListener.SysEventType.onResume) {
            this.f6516a.registerListener(this.f, this.c, 0);
        } else if (sysEventType == ISysEventListener.SysEventType.onStop) {
            this.f6516a.unregisterListener(this.f);
        }
        return false;
    }

    private String a(IApp iApp, float f) {
        int i = iApp.getInt(0);
        int i2 = iApp.getInt(2);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", i);
            jSONObject.put("height", i2);
            jSONObject.put("resolutionWidth", i / f);
            jSONObject.put("resolutionHeight", i2 / f);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(io.dcloud.common.DHInterface.IWebview r7, java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.device.DeviceFeatureImpl.a(io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String, java.lang.String):void");
    }

    private int a(Activity activity) {
        return (int) (activity.getWindow().getAttributes().screenBrightness * g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(IWebview iWebview, float f) {
        Window window = iWebview.getActivity().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (f <= 1.0f && f > 0.0f) {
            attributes.screenBrightness = f;
        } else {
            attributes.screenBrightness = -1.0f;
        }
        if (f == -1.0f) {
            attributes.screenBrightness = -1.0f;
            window.setAttributes(attributes);
        } else {
            if (f > 1.0f || f <= 0.0f) {
                return;
            }
            attributes.screenBrightness = f;
            window.setAttributes(attributes);
        }
    }

    private int a(float f) {
        if (f > 1.0f || f < 0.0f) {
            return 0;
        }
        return (int) (f * this.f6517e);
    }

    public void a(IWebview iWebview, String str, boolean z) {
        iWebview.getActivity().startActivity(new Intent(!z ? "android.intent.action.CALL" : "android.intent.action.DIAL", Uri.parse("tel:" + str)));
    }
}
