package io.dcloud.feature.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.umeng.analytics.pro.bm;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class c implements SensorEventListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f6607a;
    public IWebview b;
    public SensorManager c;
    public Sensor d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Sensor f6608e;
    public float[] f = new float[3];
    public float[] g = new float[3];
    public float[] h = new float[3];
    public float[] i = new float[9];

    public c(IWebview iWebview, String str) {
        this.f6607a = str;
        this.b = iWebview;
        SensorManager sensorManager = (SensorManager) iWebview.getContext().getSystemService(bm.ac);
        this.c = sensorManager;
        this.d = sensorManager.getDefaultSensor(2);
        this.f6608e = this.c.getDefaultSensor(1);
    }

    public void a() {
        this.c.registerListener(this, this.d, 1);
        this.c.registerListener(this, this.f6608e, 1);
    }

    public void b() {
        this.c.unregisterListener(this);
        this.d = null;
        this.f6608e = null;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 2) {
            this.f = (float[]) sensorEvent.values.clone();
        } else if (sensorEvent.sensor.getType() == 1) {
            this.g = (float[]) sensorEvent.values.clone();
        }
        SensorManager.getRotationMatrix(this.i, null, this.g, this.f);
        SensorManager.getOrientation(this.i, this.h);
        if (this.h == null) {
            a(0, "NO Accelerometer Message");
            return;
        }
        float degrees = (float) Math.toDegrees(r6[0]);
        if (degrees < 0.0f) {
            degrees += 360.0f;
        }
        float degrees2 = (float) Math.toDegrees(this.h[1]);
        float f = -((float) Math.toDegrees(this.h[2]));
        if (degrees == 0.0f || degrees2 == 0.0f) {
            return;
        }
        a(degrees, degrees2, f);
    }

    public void a(float f, float f2, float f3) {
        Deprecated_JSUtil.excCallbackSuccess(this.b, this.f6607a, String.format(Locale.ENGLISH, "{alpha:%f,beta:%f,gamma:%f,magneticHeading:%f,trueHeading:%f,headingAccuracy:%f}", Float.valueOf(f), Float.valueOf(-f2), Float.valueOf(-f3), Float.valueOf(f), Float.valueOf(f), Float.valueOf(0.0f)), true, true);
    }

    public void a(int i, String str) {
        b();
        Deprecated_JSUtil.excCallbackError(this.b, this.f6607a, DOMException.toJSON(i, str), true);
    }
}
