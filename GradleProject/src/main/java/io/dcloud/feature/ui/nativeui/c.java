package io.dcloud.feature.ui.nativeui;

import android.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import io.dcloud.PandoraEntryActivity;
import io.dcloud.PdrR;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.EventDispatchManager;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class c implements PopupWindow.OnDismissListener, ISysEventListener, EventDispatchManager.ActivityEventDispatchListener {
    public int A;
    public long C;
    public String D;
    public String F;
    public AnimationDrawable G;
    public Bitmap H;
    public int I;
    public LinearLayout L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Activity f6662a;
    public NativeUIFeatureImpl b;
    public IWebview c;
    public IApp d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f6663e;
    public String f;
    public PopupWindow h;
    public TextView i;
    public View j;
    public ProgressBar k;
    public ImageView l;
    public String m;
    public String n;
    public LinearLayout o;
    public String p;
    public String q;
    public String s;
    public String t;
    public int u;
    public int v;
    public View x;
    public int y;
    public int z;
    public String g = "close";
    public boolean r = true;
    public int w = -1308622848;
    public boolean B = false;
    public int E = -1;
    public int J = -2;
    public int K = -2;

    public class a implements View.OnTouchListener {
        public a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return c.this.r;
        }
    }

    public class b implements View.OnKeyListener {
        public b() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0 && i == 4) {
                if ("close".equalsIgnoreCase(c.this.g)) {
                    c.this.a();
                    c.this.c();
                    return true;
                }
                if ("transmit".equalsIgnoreCase(c.this.g)) {
                    if (!(c.this.f6662a instanceof PandoraEntryActivity)) {
                        c.this.f6662a.onBackPressed();
                    } else if (c.this.c.canGoBack()) {
                        c.this.c.goBackOrForward(-1);
                    } else {
                        c.this.c.getActivity().onBackPressed();
                    }
                    return false;
                }
                if ("none".equalsIgnoreCase(c.this.g)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: renamed from: io.dcloud.feature.ui.nativeui.c$c, reason: collision with other inner class name */
    public class ViewOnClickListenerC0180c implements View.OnClickListener {
        public ViewOnClickListenerC0180c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            c.this.a();
            c.this.c();
        }
    }

    public c(NativeUIFeatureImpl nativeUIFeatureImpl, IWebview iWebview, String str, JSONObject jSONObject, String str2, Activity activity) {
        this.b = nativeUIFeatureImpl;
        this.c = iWebview;
        this.d = iWebview.obtainApp();
        this.f6663e = str2;
        this.f6662a = activity;
        if (activity instanceof PandoraEntryActivity) {
            this.x = ((AdaFrameItem) iWebview.obtainFrameView()).obtainMainView();
        } else {
            this.x = ((ViewGroup) activity.findViewById(R.id.content)).getChildAt(0);
        }
        this.y = this.d.getInt(0);
        this.z = this.d.getInt(1);
        a(iWebview, jSONObject);
        b();
        a(this.o);
        a(str);
        d();
    }

    private void e() {
        this.o.setFocusable(true);
        this.o.setFocusableInTouchMode(true);
        this.o.setOnKeyListener(new b());
    }

    private void f() {
        if (AbsoluteConst.JSON_VALUE_BLOCK.equalsIgnoreCase(this.q)) {
            this.o.setOrientation(1);
            return;
        }
        if (AbsoluteConst.JSON_VALUE_INLINE.equalsIgnoreCase(this.q)) {
            this.o.setOrientation(0);
        } else if ("none".equalsIgnoreCase(this.q)) {
            this.j.setVisibility(8);
            this.k.setVisibility(8);
        }
    }

    private void g() {
        if (TextUtils.isEmpty(this.F)) {
            return;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        byte[] bArrA = new byte[0];
        try {
            bArrA = a(this.d.obtainResInStream(this.c.obtainFullUrl(), this.F));
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        BitmapFactory.decodeByteArray(bArrA, 0, bArrA.length, options);
        int i = options.outWidth;
        int i2 = options.outHeight;
        if (i == 0 || i2 == 0) {
            return;
        }
        options.inSampleSize = a(i2);
        options.inJustDecodeBounds = false;
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrA, 0, bArrA.length, options);
        this.H = bitmapDecodeByteArray;
        if (i % i2 != 0) {
            if (bitmapDecodeByteArray != null) {
                this.k.setVisibility(8);
                this.l.setVisibility(0);
                this.l.setImageBitmap(this.H);
                return;
            }
            return;
        }
        if (bitmapDecodeByteArray != null) {
            int width = bitmapDecodeByteArray.getWidth();
            int height = this.H.getHeight();
            int i3 = width / height;
            if (this.C <= 0) {
                this.C = 100L;
            }
            AnimationDrawable animationDrawable = new AnimationDrawable();
            for (int i4 = 0; i4 < i3; i4++) {
                animationDrawable.addFrame(new BitmapDrawable(Bitmap.createBitmap(this.H, i4 * height, 0, height, height)), (int) this.C);
            }
            animationDrawable.setOneShot(false);
            ViewGroup.LayoutParams layoutParams = this.l.getLayoutParams();
            if (layoutParams != null) {
                int i5 = this.E;
                if (i5 > 0) {
                    height = i5;
                }
                layoutParams.width = height;
                layoutParams.height = height;
                this.l.setLayoutParams(layoutParams);
            }
            this.k.setVisibility(8);
            this.l.setVisibility(0);
            this.l.setBackground(animationDrawable);
            this.G = (AnimationDrawable) this.l.getBackground();
        }
    }

    private void h() {
        Drawable drawable = PdrUtil.isEquals(this.t, "snow") ? PdrUtil.isEquals(this.s, "black") ? this.f6662a.getResources().getDrawable(PdrR.DRAWBLE_PROGRESSBAR_BLACK_SNOW) : this.f6662a.getResources().getDrawable(PdrR.DRAWBLE_PROGRESSBAR_WHITE_SNOW) : PdrUtil.isEquals(this.s, "black") ? this.f6662a.getResources().getDrawable(PdrR.DRAWBLE_PROGRESSBAR_BLACK_CIRCLE) : this.f6662a.getResources().getDrawable(PdrR.DRAWBLE_PROGRESSBAR_WHITE_CIRCLE);
        if (this.E > 0) {
            ProgressBar progressBar = this.k;
            int i = this.E;
            progressBar.setLayoutParams(new LinearLayout.LayoutParams(i, i));
        } else {
            int intrinsicHeight = (int) (((double) drawable.getIntrinsicHeight()) * 0.3d);
            this.k.setLayoutParams(new LinearLayout.LayoutParams(intrinsicHeight, intrinsicHeight));
        }
        this.k.setIndeterminateDrawable(drawable);
    }

    private void i() {
        LinearLayout linearLayout = this.o;
        int i = this.u;
        int i2 = this.v;
        linearLayout.setPadding(i, i2, i, i2);
        GradientDrawable gradientDrawable = (GradientDrawable) this.o.getBackground();
        int i3 = this.A;
        if (i3 > 0) {
            gradientDrawable.setCornerRadius(i3);
        }
        gradientDrawable.setColor(this.w);
        if (this.B) {
            this.o.setOnClickListener(new ViewOnClickListenerC0180c());
        }
    }

    private void j() {
        this.i.setTextColor(!PdrUtil.isEmpty(this.m) ? PdrUtil.stringToColor(this.m) : -1);
        if (PdrUtil.isEmpty(this.n)) {
            this.i.setGravity(17);
        } else if ("left".equals(this.n)) {
            this.i.setGravity(3);
        } else if ("right".equals(this.n)) {
            this.i.setGravity(5);
        } else {
            this.i.setGravity(17);
        }
        int i = this.I;
        if (i > 0) {
            this.i.setTextSize(0, i);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        Deprecated_JSUtil.execCallback(this.c, this.f6663e, null, JSUtil.OK, false, false);
        this.h = null;
        if (!this.r || (!TextUtils.isEmpty(this.g) && !"close".equalsIgnoreCase(this.g))) {
            this.d.unregisterSysEventListener(this, ISysEventListener.SysEventType.onKeyUp);
            EventDispatchManager.getInstance().removeListener(this);
        }
        Bitmap bitmap = this.H;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.H.recycle();
        System.gc();
    }

    @Override // io.dcloud.common.DHInterface.ISysEventListener
    public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
        if (sysEventType != ISysEventListener.SysEventType.onKeyUp || ((Integer) ((Object[]) obj)[0]).intValue() != 4) {
            return false;
        }
        if ("none".equalsIgnoreCase(this.g)) {
            return true;
        }
        if ("transmit".equalsIgnoreCase(this.g)) {
            return false;
        }
        a();
        c();
        return true;
    }

    private void a(IWebview iWebview, JSONObject jSONObject) {
        if (!JSONUtil.isNull(jSONObject, "background")) {
            this.w = PdrUtil.stringToColor(JSONUtil.getString(jSONObject, "background"));
        }
        if (!PdrUtil.isEmpty(JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_MODAL))) {
            this.r = !PdrUtil.isEquals(AbsoluteConst.FALSE, r0);
        }
        float scale = iWebview.getScale();
        this.A = (int) (PdrUtil.parseInt(JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_ROUND), 10) * scale);
        String string = JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_PADLOCK);
        if (!PdrUtil.isEmpty(string)) {
            this.B = Boolean.valueOf(string).booleanValue() | this.B;
        }
        this.J = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject, "width"), this.y, this.J, scale);
        this.K = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject, "height"), this.z, this.K, scale);
        String string2 = JSONUtil.getString(jSONObject, "back");
        if (!TextUtils.isEmpty(string2)) {
            this.g = string2;
        }
        this.s = JSONUtil.getString(jSONObject, "style");
        if (!JSONUtil.isNull(jSONObject, "color")) {
            this.m = JSONUtil.getString(jSONObject, "color");
        }
        this.p = JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_PADDIN);
        if (JSONUtil.isNull(jSONObject, "padding")) {
            int i = PdrUtil.parseInt(this.p, this.y, PdrUtil.parseInt("3%", this.y, 0));
            this.v = i;
            this.u = i;
        } else {
            String string3 = JSONUtil.getString(jSONObject, "padding");
            if (string3.indexOf(37) >= 0) {
                this.u = PdrUtil.convertToScreenInt(string3, this.y, this.u, scale);
                this.v = PdrUtil.convertToScreenInt(string3, this.z, this.v, scale);
            } else {
                int iConvertToScreenInt = PdrUtil.convertToScreenInt(string3, this.y, this.v, scale);
                this.v = iConvertToScreenInt;
                this.u = iConvertToScreenInt;
            }
        }
        if (!JSONUtil.isNull(jSONObject, AbsoluteConst.JSON_KEY_TEXTALIGN)) {
            this.n = JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_TEXTALIGN);
        }
        JSONObject jSONObject2 = JSONUtil.getJSONObject(jSONObject, "loading");
        if (jSONObject2 != null) {
            this.q = JSONUtil.getString(jSONObject2, "display");
            this.C = JSONUtil.getLong(jSONObject2, "interval");
            this.D = JSONUtil.getString(jSONObject2, "icon");
            this.E = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject2, "height"), this.y, -1, scale);
            this.t = JSONUtil.getString(jSONObject2, "type");
        }
        if (!TextUtils.isEmpty(this.D)) {
            this.F = this.D;
        }
        this.I = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_SIZE), this.y, 0, scale);
    }

    private void b() {
        LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) this.f6662a.getSystemService("layout_inflater")).inflate(NativeUIR.LAYOUT_DIALOG_LAYOUT_LOADING_DCLOUD, (ViewGroup) null, false);
        this.L = linearLayout;
        LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(NativeUIR.DCLOUD_LOADING_LAYOUT_ROOT);
        this.o = linearLayout2;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout2.getLayoutParams();
        layoutParams.width = this.J;
        layoutParams.height = this.K;
        this.o.setLayoutParams(layoutParams);
    }

    private void d() {
        int i = -1;
        int i2 = -2;
        if (Build.VERSION.SDK_INT < 23 || !this.r) {
            i = -2;
        } else {
            this.r = false;
            i2 = -1;
        }
        if (!this.r || (!TextUtils.isEmpty(this.g) && !"close".equalsIgnoreCase(this.g))) {
            if (this.f6662a instanceof PandoraEntryActivity) {
                this.d.registerSysEventListener(this, ISysEventListener.SysEventType.onKeyUp);
            } else {
                EventDispatchManager.getInstance().addListener(this);
            }
        }
        PopupWindow popupWindow = new PopupWindow(this.L, i, i2, this.r);
        this.h = popupWindow;
        popupWindow.showAtLocation(this.x, 17, 0, 0);
        this.h.setOnDismissListener(this);
        this.h.setBackgroundDrawable(new BitmapDrawable());
        this.h.setOutsideTouchable(true);
        this.h.setTouchInterceptor(new a());
        AnimationDrawable animationDrawable = this.G;
        if (animationDrawable == null || animationDrawable.isRunning()) {
            return;
        }
        this.G.start();
    }

    public void c() {
        this.b.a(this.f);
        this.d.unregisterSysEventListener(this, ISysEventListener.SysEventType.onKeyUp);
        EventDispatchManager.getInstance().removeListener(this);
    }

    public void b(String str) {
        String strTrim = this.i.getText().toString().trim();
        if (!TextUtils.isEmpty(strTrim)) {
            if (strTrim.length() == str.length()) {
                if (this.i.getLayoutParams() != null) {
                    this.i.setLayoutParams(new LinearLayout.LayoutParams(this.i.getWidth(), this.i.getHeight()));
                }
            } else {
                this.i.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            }
        }
        if (TextUtils.isEmpty(str)) {
            this.j.setVisibility(8);
            this.i.setVisibility(8);
        } else {
            this.i.setText(str);
        }
    }

    private void a(View view) {
        this.i = (TextView) view.findViewById(NativeUIR.ID_TEXT_LOADING_DCLOUD);
        this.k = (ProgressBar) view.findViewById(NativeUIR.ID_PROGRESSBAR_LOADING_DCLOUD);
        this.l = (ImageView) view.findViewById(NativeUIR.ID_IMAGE_LOADING_DCLOUD);
        this.j = view.findViewById(NativeUIR.ID_WAITING_SEPARATOR_DCLOUD);
    }

    private void a(String str) {
        f();
        j();
        h();
        g();
        b(str);
        e();
        i();
    }

    private int a(int i) {
        int iMin = Math.min(this.y, this.z);
        int i2 = this.u;
        int i3 = (iMin - i2) - i2;
        if (i3 <= 0 || i <= i3) {
            return 1;
        }
        return i / i3;
    }

    private byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                inputStream.close();
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public void a() {
        PopupWindow popupWindow = this.h;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        try {
            this.h.dismiss();
        } catch (Exception unused) {
            onDismiss();
        }
    }
}
