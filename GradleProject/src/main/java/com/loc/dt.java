package com.loc;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import com.taobao.weex.common.Constants;

/* JADX INFO: compiled from: AmapSensorManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dt implements SensorEventListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SensorManager f3749a;
    public Sensor b;
    public Sensor c;
    public Sensor d;
    public Context s;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3750e = false;
    public double f = 0.0d;
    public float g = 0.0f;
    public float t = 1013.25f;
    public float u = 0.0f;
    public Handler h = new Handler();
    public double i = 0.0d;
    public double j = 0.0d;
    public double k = 0.0d;
    public double l = 0.0d;
    public double[] m = new double[3];
    public volatile double n = 0.0d;
    public long o = 0;
    public long p = 0;
    public final int q = 100;
    public final int r = 30;

    public dt(Context context) {
        this.s = null;
        this.f3749a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        try {
            this.s = context;
            if (this.f3749a == null) {
                this.f3749a = (SensorManager) context.getSystemService(com.umeng.analytics.pro.bm.ac);
            }
            try {
                this.b = this.f3749a.getDefaultSensor(6);
            } catch (Throwable unused) {
            }
            try {
                this.c = this.f3749a.getDefaultSensor(11);
            } catch (Throwable unused2) {
            }
            try {
                this.d = this.f3749a.getDefaultSensor(1);
            } catch (Throwable unused3) {
            }
        } catch (Throwable th) {
            ej.a(th, "AMapSensorManager", "<init>");
        }
    }

    public final void a() {
        SensorManager sensorManager = this.f3749a;
        if (sensorManager == null || this.f3750e) {
            return;
        }
        this.f3750e = true;
        try {
            if (this.b != null) {
                sensorManager.registerListener(this, this.b, 3, this.h);
            }
        } catch (Throwable th) {
            ej.a(th, "AMapSensorManager", "registerListener mPressure");
        }
        try {
            if (this.c != null) {
                this.f3749a.registerListener(this, this.c, 3, this.h);
            }
        } catch (Throwable th2) {
            ej.a(th2, "AMapSensorManager", "registerListener mRotationVector");
        }
        try {
            if (this.d != null) {
                this.f3749a.registerListener(this, this.d, 3, this.h);
            }
        } catch (Throwable th3) {
            ej.a(th3, "AMapSensorManager", "registerListener mAcceleroMeterVector");
        }
    }

    public final void b() {
        SensorManager sensorManager = this.f3749a;
        if (sensorManager == null || !this.f3750e) {
            return;
        }
        this.f3750e = false;
        try {
            if (this.b != null) {
                sensorManager.unregisterListener(this, this.b);
            }
        } catch (Throwable unused) {
        }
        try {
            if (this.c != null) {
                this.f3749a.unregisterListener(this, this.c);
            }
        } catch (Throwable unused2) {
        }
        try {
            if (this.d != null) {
                this.f3749a.unregisterListener(this, this.d);
            }
        } catch (Throwable unused3) {
        }
    }

    public final float c() {
        return this.u;
    }

    public final double d() {
        return this.l;
    }

    public final void e() {
        try {
            b();
            this.b = null;
            this.c = null;
            this.f3749a = null;
            this.d = null;
            this.f3750e = false;
        } catch (Throwable th) {
            ej.a(th, "AMapSensorManager", Constants.Event.SLOT_LIFECYCLE.DESTORY);
        }
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr;
        if (sensorEvent == null) {
            return;
        }
        try {
            int type = sensorEvent.sensor.getType();
            if (type == 1) {
                if (this.d != null) {
                    float[] fArr2 = (float[]) sensorEvent.values.clone();
                    this.m[0] = (this.m[0] * 0.800000011920929d) + ((double) (fArr2[0] * 0.19999999f));
                    this.m[1] = (this.m[1] * 0.800000011920929d) + ((double) (fArr2[1] * 0.19999999f));
                    this.m[2] = (this.m[2] * 0.800000011920929d) + ((double) (fArr2[2] * 0.19999999f));
                    this.i = ((double) fArr2[0]) - this.m[0];
                    this.j = ((double) fArr2[1]) - this.m[1];
                    this.k = ((double) fArr2[2]) - this.m[2];
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - this.o >= 100) {
                        double dSqrt = Math.sqrt((this.i * this.i) + (this.j * this.j) + (this.k * this.k));
                        this.p++;
                        this.o = jCurrentTimeMillis;
                        this.n += dSqrt;
                        if (this.p >= 30) {
                            this.l = this.n / this.p;
                            this.n = 0.0d;
                            this.p = 0L;
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            if (type == 6) {
                try {
                    if (this.b != null) {
                        float[] fArr3 = (float[]) sensorEvent.values.clone();
                        if (fArr3 != null) {
                            this.g = fArr3[0];
                        }
                        if (fArr3 != null) {
                            this.f = ep.a(SensorManager.getAltitude(this.t, fArr3[0]));
                            return;
                        }
                        return;
                    }
                    return;
                } catch (Throwable unused) {
                    return;
                }
            }
            if (type != 11) {
                return;
            }
            try {
                if (this.c == null || (fArr = (float[]) sensorEvent.values.clone()) == null) {
                    return;
                }
                float[] fArr4 = new float[9];
                SensorManager.getRotationMatrixFromVector(fArr4, fArr);
                SensorManager.getOrientation(fArr4, new float[3]);
                float degrees = (float) Math.toDegrees(r12[0]);
                this.u = degrees;
                if (degrees <= 0.0f) {
                    degrees += 360.0f;
                }
                this.u = (float) Math.floor(degrees);
            } catch (Throwable unused2) {
            }
        } catch (Throwable unused3) {
        }
    }
}
