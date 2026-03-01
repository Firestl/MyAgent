package io.dcloud.feature.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import com.taobao.weex.common.Constants;
import com.umeng.analytics.pro.bm;
import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.PdrUtil;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class b implements IEventCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<IWebview, a> f6606a = new HashMap<>();
    public SensorManager b;
    public Sensor c;

    public b(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(bm.ac);
        this.b = sensorManager;
        this.c = sensorManager.getDefaultSensor(1);
    }

    public String a(IWebview iWebview, String str, String[] strArr) {
        if (!str.equals("start")) {
            if (!str.equals(Constants.Value.STOP)) {
                return "";
            }
            a(iWebview);
            return "";
        }
        AdaFrameView adaFrameView = (AdaFrameView) iWebview.obtainFrameView();
        Logger.d("AccelerometerManager.execute start listen frameView=" + adaFrameView);
        adaFrameView.addFrameViewListener(this);
        a(iWebview, strArr[0], strArr[1]);
        return "";
    }

    @Override // io.dcloud.common.DHInterface.IEventCallback
    public Object onCallBack(String str, Object obj) {
        if ((!PdrUtil.isEquals(str, AbsoluteConst.EVENTS_WINDOW_CLOSE) && !PdrUtil.isEquals(str, "close")) || !(obj instanceof IWebview)) {
            return null;
        }
        IWebview iWebview = (IWebview) obj;
        a(iWebview);
        ((AdaFrameView) iWebview.obtainFrameView()).removeFrameViewListener(this);
        return null;
    }

    private void a(IWebview iWebview, String str, String str2) {
        a aVar = this.f6606a.get(iWebview);
        if (aVar == null) {
            aVar = new a(this, iWebview, str);
            this.f6606a.put(iWebview, aVar);
        }
        int i = PdrUtil.parseInt(str2, 500);
        this.b.registerListener(aVar, this.c, (i >= 0 ? i : 500) * 1000);
    }

    public void a(IWebview iWebview) {
        a aVarRemove = this.f6606a.remove(iWebview);
        if (aVarRemove != null) {
            Logger.d("AccelerometerManager stop pWebViewImpl=" + iWebview);
            this.b.unregisterListener(aVarRemove);
        }
    }

    public void a(String str) {
        Collection<a> collectionValues = this.f6606a.values();
        if (!collectionValues.isEmpty()) {
            Iterator<a> it = collectionValues.iterator();
            while (it.hasNext()) {
                this.b.unregisterListener(it.next());
            }
        }
        this.f6606a.clear();
    }
}
