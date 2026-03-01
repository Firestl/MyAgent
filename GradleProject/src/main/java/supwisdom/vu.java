package supwisdom;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public interface vu {

    public interface a {
        void onRefresh(int i);
    }

    void a();

    void a(int i, int i2, float f);

    void a(Canvas canvas);

    void a(ViewGroup viewGroup, View view, a aVar);

    void a(JSONObject jSONObject, int i, int i2, float f);

    boolean a(MotionEvent motionEvent);

    boolean b();

    void c();

    boolean d();

    boolean e();

    void setRefreshEnable(boolean z);
}
