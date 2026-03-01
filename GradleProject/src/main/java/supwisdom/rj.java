package supwisdom;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import java.util.List;

/* JADX INFO: compiled from: SensorManagerProxyImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class rj implements qj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SensorManager f9066a;

    public rj(SensorManager sensorManager) {
        this.f9066a = sensorManager;
    }

    @Override // supwisdom.qj
    public boolean a(SensorEventListener sensorEventListener, int i, int i2, Handler handler) {
        List<Sensor> sensorList = this.f9066a.getSensorList(i);
        if (sensorList.isEmpty()) {
            return false;
        }
        return this.f9066a.registerListener(sensorEventListener, sensorList.get(0), i2, handler);
    }

    @Override // supwisdom.qj
    public void a(SensorEventListener sensorEventListener, int i) {
        List<Sensor> sensorList = this.f9066a.getSensorList(i);
        if (sensorList.isEmpty()) {
            return;
        }
        try {
            this.f9066a.unregisterListener(sensorEventListener, sensorList.get(0));
        } catch (Throwable unused) {
            xi.c("Failed to unregister device sensor " + sensorList.get(0).getName());
        }
    }
}
