package io.dcloud.feature.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.umeng.analytics.pro.bm;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;

/* JADX INFO: loaded from: classes3.dex */
public class e implements SensorEventListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SensorManager f6610a;
    public IWebview b;
    public Sensor c;
    public String d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f6611e = null;

    public e(IWebview iWebview) {
        this.b = iWebview;
        SensorManager sensorManager = (SensorManager) iWebview.getContext().getSystemService(bm.ac);
        this.f6610a = sensorManager;
        this.c = sensorManager.getDefaultSensor(8);
    }

    public void a(float f) {
        String str = this.d;
        if (str != null) {
            Deprecated_JSUtil.execCallback(this.b, str, String.valueOf(f), JSUtil.OK, true, true);
            if (this.f6611e == null) {
                b();
            }
            this.d = null;
        }
        String str2 = this.f6611e;
        if (str2 != null) {
            Deprecated_JSUtil.execCallback(this.b, str2, String.valueOf(f), JSUtil.OK, true, true);
        }
    }

    public void b() {
        this.f6610a.unregisterListener(this);
        this.d = null;
        this.f6611e = null;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 8) {
            float[] fArr = sensorEvent.values;
            if (fArr != null) {
                a(fArr[0]);
            } else {
                a(0, "NO Proximity Message");
            }
        }
    }

    public void a(int i, String str) {
        b();
        String str2 = this.d;
        if (str2 != null) {
            Deprecated_JSUtil.execCallback(this.b, str2, DOMException.toJSON(i, str), JSUtil.ERROR, true, true);
            if (this.f6611e == null) {
                b();
            }
            this.d = null;
        }
        String str3 = this.f6611e;
        if (str3 != null) {
            Deprecated_JSUtil.execCallback(this.b, str3, DOMException.toJSON(i, str), JSUtil.ERROR, true, true);
        }
    }

    public void a() {
        this.f6610a.registerListener(this, this.c, 500000);
    }
}
