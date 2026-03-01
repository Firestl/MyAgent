package supwisdom;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: OrientationDetector.java */
/* JADX INFO: loaded from: classes.dex */
public class nj implements SensorEventListener {
    public static nj p;
    public static final Object q = new Object();
    public static final Set<Integer> r = tj.b(15);
    public static final Set<Integer> s = tj.b(11);
    public static final Set<Integer> t = tj.b(1, 2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HandlerThread f8533a;
    public Handler b;
    public final Context c;
    public float[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float[] f8534e;
    public float[] f;
    public double[] g;
    public qj h;
    public Set<Integer> k;
    public boolean l;
    public boolean m;
    public boolean n;
    public final Set<Integer> i = new HashSet();
    public ArrayList<a> o = new ArrayList<>();
    public final List<Set<Integer>> j = tj.a((Object[]) new Set[]{r, s, t});

    /* JADX INFO: compiled from: OrientationDetector.java */
    public interface a {
        void a(double d, double d2, double d3);
    }

    public nj(Context context) {
        this.c = context.getApplicationContext();
    }

    public static nj a(Context context) {
        nj njVar;
        synchronized (q) {
            if (p == null) {
                p = new nj(context);
            }
            njVar = p;
        }
        return njVar;
    }

    public boolean b(a aVar) {
        ArrayList<a> arrayList = this.o;
        if (arrayList == null) {
            return false;
        }
        if (aVar != null) {
            return arrayList.remove(aVar);
        }
        arrayList.clear();
        return true;
    }

    public final String c() {
        if (this.n) {
            return "NOT_AVAILABLE";
        }
        Set<Integer> set = this.k;
        return set == r ? "GAME_ROTATION_VECTOR" : set == s ? "ROTATION_VECTOR" : set == t ? "ACCELEROMETER_MAGNETIC" : "NOT_AVAILABLE";
    }

    public final qj d() {
        qj qjVar = this.h;
        if (qjVar != null) {
            return qjVar;
        }
        SensorManager sensorManager = (SensorManager) this.c.getSystemService(com.umeng.analytics.pro.bm.ac);
        if (sensorManager != null) {
            this.h = new rj(sensorManager);
        }
        return this.h;
    }

    public void e() {
        xi.a("[OrientationDetector] sensor stopped");
        a(new HashSet(this.i));
        a(false);
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        int type = sensorEvent.sensor.getType();
        float[] fArr = sensorEvent.values;
        if (type == 1) {
            if (this.m) {
                a(fArr, this.d);
                return;
            }
            return;
        }
        if (type == 2) {
            if (this.m) {
                if (this.d == null) {
                    this.d = new float[3];
                }
                float[] fArr2 = this.d;
                System.arraycopy(fArr, 0, fArr2, 0, fArr2.length);
                return;
            }
            return;
        }
        if (type == 11) {
            if (this.l && this.k == s) {
                a(fArr, this.g);
                double[] dArr = this.g;
                a(dArr[0], dArr[1], dArr[2]);
                return;
            }
            return;
        }
        if (type != 15) {
            xi.b("unexpected sensor type:" + type);
            return;
        }
        if (this.l) {
            a(fArr, this.g);
            double[] dArr2 = this.g;
            a(dArr2[0], dArr2[1], dArr2[2]);
        }
    }

    public boolean b(int i) {
        xi.a("[OrientationDetector] sensor started");
        boolean zA = a(i);
        if (zA) {
            a(true);
        }
        return zA;
    }

    public void a(a aVar) {
        ArrayList<a> arrayList = this.o;
        if (arrayList == null || arrayList.contains(aVar)) {
            return;
        }
        this.o.add(aVar);
    }

    public static double[] b(float[] fArr, double[] dArr) {
        if (fArr.length != 9) {
            return dArr;
        }
        if (fArr[8] > 0.0f) {
            dArr[0] = Math.atan2(-fArr[1], fArr[4]);
            dArr[1] = Math.asin(fArr[7]);
            dArr[2] = Math.atan2(-fArr[6], fArr[8]);
        } else if (fArr[8] < 0.0f) {
            dArr[0] = Math.atan2(fArr[1], -fArr[4]);
            dArr[1] = -Math.asin(fArr[7]);
            dArr[1] = dArr[1] + (dArr[1] >= 0.0d ? -3.141592653589793d : 3.141592653589793d);
            dArr[2] = Math.atan2(fArr[6], -fArr[8]);
        } else {
            if (fArr[6] > 0.0f) {
                dArr[0] = Math.atan2(-fArr[1], fArr[4]);
                dArr[1] = Math.asin(fArr[7]);
                dArr[2] = -1.5707963267948966d;
            } else if (fArr[6] < 0.0f) {
                dArr[0] = Math.atan2(fArr[1], -fArr[4]);
                dArr[1] = -Math.asin(fArr[7]);
                dArr[1] = dArr[1] + (dArr[1] >= 0.0d ? -3.141592653589793d : 3.141592653589793d);
                dArr[2] = -1.5707963267948966d;
            } else {
                dArr[0] = Math.atan2(fArr[3], fArr[0]);
                dArr[1] = fArr[7] > 0.0f ? 1.5707963267948966d : -1.5707963267948966d;
                dArr[2] = 0.0d;
            }
        }
        if (dArr[0] < 0.0d) {
            dArr[0] = dArr[0] + 6.283185307179586d;
        }
        return dArr;
    }

    public final boolean a(int i) {
        if (this.n) {
            return false;
        }
        if (this.k != null) {
            xi.a("[OrientationDetector] register sensor:" + c());
            return a(this.k, i, true);
        }
        a();
        for (Set<Integer> set : this.j) {
            this.k = set;
            if (a(set, i, true)) {
                xi.a("[OrientationDetector] register sensor:" + c());
                return true;
            }
        }
        this.n = true;
        this.k = null;
        this.f = null;
        this.g = null;
        return false;
    }

    public final void a(float[] fArr, double[] dArr) {
        if (fArr.length > 4) {
            System.arraycopy(fArr, 0, this.f8534e, 0, 4);
            SensorManager.getRotationMatrixFromVector(this.f, this.f8534e);
        } else {
            SensorManager.getRotationMatrixFromVector(this.f, fArr);
        }
        b(this.f, dArr);
        for (int i = 0; i < 3; i++) {
            dArr[i] = Math.toDegrees(dArr[i]);
        }
    }

    public final void a(float[] fArr, float[] fArr2) {
        if (fArr == null || fArr2 == null || !SensorManager.getRotationMatrix(this.f, null, fArr, fArr2)) {
            return;
        }
        b(this.f, this.g);
        a(Math.toDegrees(this.g[0]), Math.toDegrees(this.g[1]), Math.toDegrees(this.g[2]));
    }

    public final Handler b() {
        if (this.b == null) {
            HandlerThread handlerThread = new HandlerThread("DeviceOrientation");
            this.f8533a = handlerThread;
            handlerThread.start();
            this.b = new Handler(this.f8533a.getLooper());
        }
        return this.b;
    }

    public final void a(boolean z) {
        this.l = z;
        this.m = z && this.k == t;
    }

    public final void a() {
        if (this.f == null) {
            this.f = new float[9];
        }
        if (this.g == null) {
            this.g = new double[3];
        }
        if (this.f8534e == null) {
            this.f8534e = new float[4];
        }
    }

    public final boolean a(Set<Integer> set, int i, boolean z) {
        HashSet<Integer> hashSet = new HashSet(set);
        hashSet.removeAll(this.i);
        if (hashSet.isEmpty()) {
            return true;
        }
        boolean z2 = false;
        for (Integer num : hashSet) {
            boolean zA = a(num.intValue(), i);
            if (!zA && z) {
                a(hashSet);
                return false;
            }
            if (zA) {
                this.i.add(num);
                z2 = true;
            }
        }
        return z2;
    }

    public final void a(Iterable<Integer> iterable) {
        for (Integer num : iterable) {
            if (this.i.contains(num)) {
                d().a(this, num.intValue());
                this.i.remove(num);
            }
        }
    }

    public final boolean a(int i, int i2) {
        qj qjVarD = d();
        if (qjVarD == null) {
            return false;
        }
        return qjVarD.a(this, i, i2, b());
    }

    public void a(double d, double d2, double d3) {
        ArrayList<a> arrayList = this.o;
        if (arrayList != null) {
            try {
                Iterator<a> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().a(d, d2, d3);
                }
            } catch (Throwable th) {
                xi.a("[OrientationDetector] ", th);
            }
        }
    }
}
