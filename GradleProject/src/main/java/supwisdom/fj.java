package supwisdom;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.taobao.weex.appfram.pickers.WXPickersModule;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.list.BasicListComponent;
import com.taobao.weex.ui.view.gesture.WXGesture;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import supwisdom.si;

/* JADX INFO: compiled from: BindingXTouchHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class fj extends aj implements View.OnTouchListener, GestureDetector.OnGestureListener {
    public float l;
    public float m;
    public double n;
    public double o;
    public GestureDetector p;
    public boolean q;
    public boolean r;

    public fj(Context context, yi yiVar, Object... objArr) {
        super(context, yiVar, objArr);
        this.p = new GestureDetector(context, this, new Handler(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper()));
    }

    public void a(boolean z) {
        this.r = z;
    }

    public void b(boolean z) {
        this.q = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    @Override // supwisdom.vi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(java.lang.String r3, java.lang.String r4) {
        /*
            r2 = this;
            int r3 = r4.hashCode()
            r0 = 110749(0x1b09d, float:1.55192E-40)
            r1 = 1
            if (r3 == r0) goto L1a
            r0 = 97520651(0x5d00c0b, float:1.956465E-35)
            if (r3 == r0) goto L10
            goto L24
        L10:
            java.lang.String r3 = "flick"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L24
            r3 = 1
            goto L25
        L1a:
            java.lang.String r3 = "pan"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L24
            r3 = 0
            goto L25
        L24:
            r3 = -1
        L25:
            if (r3 == 0) goto L2e
            if (r3 == r1) goto L2a
            goto L31
        L2a:
            r2.a(r1)
            goto L31
        L2e:
            r2.b(r1)
        L31:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.fj.c(java.lang.String, java.lang.String):void");
    }

    public boolean d() {
        return this.r;
    }

    public boolean e() {
        return this.q;
    }

    @Override // supwisdom.vi
    public void onActivityPause() {
    }

    @Override // supwisdom.vi
    public void onActivityResume() {
    }

    @Override // supwisdom.aj, supwisdom.vi
    public void onDestroy() {
        super.onDestroy();
        if (this.f6927a != null) {
            this.f6927a.clear();
            this.f6927a = null;
        }
        this.i = null;
        this.c = null;
        this.r = false;
        this.q = false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!this.r) {
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float rawY;
        float f3;
        if (!this.q) {
            xi.a("pan gesture is not enabled");
            return false;
        }
        if (motionEvent == null) {
            f3 = this.l;
            rawY = this.m;
        } else {
            float rawX = motionEvent.getRawX();
            rawY = motionEvent.getRawY();
            f3 = rawX;
        }
        if (motionEvent2 == null) {
            return false;
        }
        float rawX2 = motionEvent2.getRawX() - f3;
        float rawY2 = motionEvent2.getRawY() - rawY;
        try {
            if (xi.f9767a) {
                xi.a(String.format(Locale.getDefault(), "[TouchHandler] pan moved. (x:%f,y:%f)", Float.valueOf(rawX2), Float.valueOf(rawY2)));
            }
            lj.a(this.d, rawX2, rawY2, this.h.a());
            if (!a(this.i, this.d)) {
                a(this.f6927a, this.d, BasicListComponent.DragTriggerType.PAN);
            }
        } catch (Exception e2) {
            xi.a("runtime error", e2);
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        try {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                this.l = motionEvent.getRawX();
                this.m = motionEvent.getRawY();
                a("start", 0.0d, 0.0d, new Object[0]);
                System.currentTimeMillis();
                motionEvent.getRawX();
                motionEvent.getRawY();
            } else if (actionMasked == 1) {
                this.l = 0.0f;
                this.m = 0.0f;
                motionEvent.getRawX();
                motionEvent.getRawY();
                c();
                a(WXGesture.END, this.n, this.o, new Object[0]);
                this.n = 0.0d;
                this.o = 0.0d;
            } else if (actionMasked != 2) {
                if (actionMasked == 3) {
                    this.l = 0.0f;
                    this.m = 0.0f;
                    c();
                    a(WXPickersModule.CANCEL, this.n, this.o, new Object[0]);
                }
            } else if (this.l == 0.0f && this.m == 0.0f) {
                this.l = motionEvent.getRawX();
                this.m = motionEvent.getRawY();
                a("start", 0.0d, 0.0d, new Object[0]);
            } else {
                this.n = motionEvent.getRawX() - this.l;
                this.o = motionEvent.getRawY() - this.m;
            }
        } catch (Exception e2) {
            xi.a("runtime error ", e2);
        }
        return this.p.onTouchEvent(motionEvent);
    }

    @Override // supwisdom.aj, supwisdom.vi
    public void a(String str, Map<String, Object> map, jj jjVar, List<Map<String, Object>> list, si.d dVar) {
        super.a(str, map, jjVar, list, dVar);
    }

    @Override // supwisdom.vi
    public boolean b(String str, String str2) {
        View viewA = this.h.b().a(str, TextUtils.isEmpty(this.f) ? this.f6928e : this.f);
        if (viewA == null) {
            xi.b("[ExpressionTouchHandler] onCreate failed. sourceView not found:" + str);
            return false;
        }
        viewA.setOnTouchListener(this);
        try {
            Method declaredMethod = viewA.getClass().getDeclaredMethod("addPan", Object.class);
            if (declaredMethod != null) {
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(viewA, this);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        xi.a("[ExpressionTouchHandler] onCreate success. {source:" + str + ",type:" + str2 + Operators.BLOCK_END_STR);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    @Override // supwisdom.vi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            int r0 = r8.hashCode()
            r1 = 110749(0x1b09d, float:1.55192E-40)
            r2 = 1
            r3 = 0
            if (r0 == r1) goto L1b
            r1 = 97520651(0x5d00c0b, float:1.956465E-35)
            if (r0 == r1) goto L11
            goto L25
        L11:
            java.lang.String r0 = "flick"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L25
            r0 = 1
            goto L26
        L1b:
            java.lang.String r0 = "pan"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L25
            r0 = 0
            goto L26
        L25:
            r0 = -1
        L26:
            if (r0 == 0) goto L2f
            if (r0 == r2) goto L2b
            goto L32
        L2b:
            r6.a(r3)
            goto L32
        L2f:
            r6.b(r3)
        L32:
            boolean r0 = r6.e()
            if (r0 != 0) goto L9c
            boolean r0 = r6.d()
            if (r0 != 0) goto L9c
            java.lang.String r0 = r6.f
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L49
            java.lang.String r0 = r6.f6928e
            goto L4b
        L49:
            java.lang.String r0 = r6.f
        L4b:
            supwisdom.yi r1 = r6.h
            supwisdom.yi$d r1 = r1.b()
            java.lang.Object[] r4 = new java.lang.Object[r2]
            r4[r3] = r0
            android.view.View r0 = r1.a(r7, r4)
            if (r0 == 0) goto L7a
            r1 = 0
            r0.setOnTouchListener(r1)
            java.lang.Class r1 = r0.getClass()     // Catch: java.lang.Exception -> L76
            java.lang.String r4 = "removePan"
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch: java.lang.Exception -> L76
            java.lang.reflect.Method r1 = r1.getDeclaredMethod(r4, r5)     // Catch: java.lang.Exception -> L76
            if (r1 == 0) goto L7a
            r1.setAccessible(r2)     // Catch: java.lang.Exception -> L76
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L76
            r1.invoke(r0, r3)     // Catch: java.lang.Exception -> L76
            goto L7a
        L76:
            r0 = move-exception
            r0.printStackTrace()
        L7a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "remove touch listener success.["
            r0.append(r1)
            r0.append(r7)
            java.lang.String r7 = ","
            r0.append(r7)
            r0.append(r8)
            java.lang.String r7 = "]"
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            supwisdom.xi.a(r7)
            return r2
        L9c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.fj.a(java.lang.String, java.lang.String):boolean");
    }

    @Override // supwisdom.aj
    public void c(Map<String, Object> map) {
        a("exit", ((Double) map.get("internal_x")).doubleValue(), ((Double) map.get("internal_y")).doubleValue(), new Object[0]);
    }

    @Override // supwisdom.aj
    public void a(String str, Map<String, Object> map) {
        a("interceptor", ((Double) map.get("internal_x")).doubleValue(), ((Double) map.get("internal_y")).doubleValue(), Collections.singletonMap("interceptor", str));
    }

    public final void a(String str, double d, double d2, Object... objArr) {
        if (this.c != null) {
            HashMap map = new HashMap();
            map.put("state", str);
            double dA = this.h.a().a(d, new Object[0]);
            double dA2 = this.h.a().a(d2, new Object[0]);
            map.put("deltaX", Double.valueOf(dA));
            map.put("deltaY", Double.valueOf(dA2));
            map.put("token", this.g);
            if (objArr != null && objArr.length > 0 && (objArr[0] instanceof Map)) {
                map.putAll((Map) objArr[0]);
            }
            this.c.a(map);
            xi.a(">>>>>>>>>>>fire event:(" + str + "," + dA + "," + dA2 + ")");
        }
    }
}
