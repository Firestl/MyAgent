package supwisdom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.taobao.weex.common.Constants;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.TitleNViewUtil;
import io.dcloud.common.util.language.LanguageUtil;
import io.dcloud.feature.weex_amap.adapter.Constant;
import io.dcloud.js.map.amap.util.AMapUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;

/* JADX INFO: loaded from: classes.dex */
public class ev extends FrameLayout {
    public String A;
    public String B;
    public LinearLayout C;
    public View D;
    public int E;
    public String F;
    public View.OnTouchListener G;
    public View.OnClickListener H;
    public View.OnClickListener I;
    public int J;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f7524a;
    public LinearLayout b;
    public JSONObject c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f7525e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public JSONArray n;
    public String o;
    public int p;
    public JSONObject q;
    public ArrayList<RelativeLayout> r;
    public RelativeLayout s;
    public ICallBack t;
    public ICallBack u;
    public ICallBack v;
    public float w;
    public IApp x;
    public String y;
    public String z;

    public class a implements AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ImageView f7526a;

        public a(ImageView imageView) {
            this.f7526a = imageView;
        }

        @Override // pl.droidsonroids.gif.AnimationListener
        public void onAnimationCompleted(int i) {
            Drawable drawable = this.f7526a.getDrawable();
            if (drawable instanceof GifDrawable) {
                GifDrawable gifDrawable = (GifDrawable) drawable;
                gifDrawable.seekToFrame(gifDrawable.getNumberOfFrames());
                gifDrawable.removeAnimationListener(this);
            }
        }
    }

    public class b implements View.OnTouchListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f7527a = 0;
        public float b = 0.0f;
        public float c = 0.0f;
        public boolean d = false;

        public b() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (ev.this.s == null || ev.this.s.getParent() == null) {
                this.d = false;
            } else {
                int action = motionEvent.getAction();
                if (action == 0) {
                    RelativeLayout relativeLayout = ev.this.s;
                    if (relativeLayout == null || relativeLayout.getVisibility() != 0) {
                        this.d = false;
                    } else {
                        this.d = new Rect(relativeLayout.getLeft(), relativeLayout.getTop() + ev.this.b.getTop(), relativeLayout.getRight(), relativeLayout.getBottom() + ev.this.b.getTop()).contains((int) motionEvent.getX(), (int) motionEvent.getY());
                    }
                    this.f7527a = System.currentTimeMillis();
                    this.b = motionEvent.getX();
                    this.c = motionEvent.getY();
                } else if (action == 1 && this.d && System.currentTimeMillis() - this.f7527a < 500 && Math.abs(motionEvent.getY() - this.c) < 70.0f && Math.abs(motionEvent.getX() - this.b) < 70.0f && ev.this.v != null) {
                    ev.this.v.onCallBack(0, null);
                }
            }
            return this.d;
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iIntValue = ((Integer) view.getTag()).intValue();
            ev.this.b(iIntValue);
            if (ev.this.t != null) {
                ev.this.t.onCallBack(iIntValue, null);
            }
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ev.this.u != null) {
                ev.this.u.onCallBack(0, null);
            }
        }
    }

    public ev(Context context, JSONObject jSONObject, float f, IApp iApp) {
        super(context);
        this.f7525e = "#7A7E83";
        this.f = "#3cc51f";
        this.g = AMapUtil.HtmlBlack;
        this.h = TitleNViewUtil.TRANSPARENT_BUTTON_TEXT_COLOR;
        this.i = "#00000000";
        this.y = "24px";
        this.z = "3px";
        this.A = "10px";
        this.E = -65536;
        this.G = new b();
        this.H = new c();
        this.I = new d();
        this.J = 0;
        this.f7524a = context;
        this.w = f;
        this.x = iApp;
        setClipChildren(false);
        this.c = jSONObject;
        if (jSONObject == null) {
            this.c = new JSONObject();
        }
        this.d = this.c.getString("color");
        this.j = this.c.getString("selectedColor");
        this.k = this.c.getString("backgroundColor");
        this.l = this.c.getString(Constants.Name.BACKGROUND_IMAGE);
        this.F = this.c.getString("backgroundRepeat");
        if (this.c.containsKey("redDotColor")) {
            String string = this.c.getString("redDotColor");
            if (!PdrUtil.isEmpty(string)) {
                this.E = PdrUtil.stringToColor(string);
            }
        }
        this.m = this.c.getString(Constants.Name.BORDER_STYLE);
        if (this.c.containsKey(Constants.Name.FONT_SIZE)) {
            this.A = this.c.getString(Constants.Name.FONT_SIZE);
        }
        if (this.c.containsKey(AbsoluteConst.JSON_KEY_ICON_WIDTH)) {
            this.y = this.c.getString(AbsoluteConst.JSON_KEY_ICON_WIDTH);
        }
        float fApplyDimension = TypedValue.applyDimension(1, 72.0f, getResources().getDisplayMetrics());
        this.B = !this.c.containsKey("height") ? "50px" : this.c.getString("height");
        if (this.c.containsKey("spacing")) {
            this.z = this.c.getString("spacing");
        }
        this.p = (int) PdrUtil.parseFloat(this.B, 0.0f, fApplyDimension, f);
        this.o = this.c.getString("selected") == null ? "0" : this.c.getString("selected");
        this.n = this.c.getJSONArray("list");
        this.q = this.c.getJSONObject("midButton");
        this.r = new ArrayList<>();
        this.D = new View(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, 1);
        layoutParams.bottomMargin = this.p;
        layoutParams.gravity = 80;
        addView(this.D, layoutParams);
        LinearLayout linearLayout = new LinearLayout(context);
        this.b = linearLayout;
        linearLayout.setOrientation(0);
        this.b.setGravity(80);
        this.b.setClipChildren(false);
        addView(this.b, new ViewGroup.LayoutParams(-1, this.p));
        e();
        d();
        f();
        setOnTouchListener(this.G);
    }

    public final void f() {
        for (int i = 0; i < this.r.size(); i++) {
            String str = this.o;
            if (str != null) {
                int i2 = Integer.parseInt(str);
                RelativeLayout relativeLayout = this.r.get(i);
                TextView textView = (TextView) relativeLayout.findViewById(R.id.tabTV);
                ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.tabIV);
                JSONObject jSONObject = (JSONObject) textView.getTag();
                if (i2 == i) {
                    textView.setTextColor(a(this.j, this.f));
                    if (jSONObject != null) {
                        String string = jSONObject.getString("selectedIconPath");
                        try {
                            String strA = a(string);
                            GifDrawable gifDrawable = strA.startsWith("file:///android_asset/") ? new GifDrawable(this.f7524a.getAssets(), strA.replace("file:///android_asset/", "")) : new GifDrawable(getContext().getContentResolver(), Uri.parse(strA));
                            gifDrawable.setLoopCount(1);
                            gifDrawable.addAnimationListener(new a(imageView));
                            imageView.setImageDrawable(gifDrawable);
                        } catch (Exception unused) {
                            a(string, imageView);
                        }
                    }
                } else {
                    textView.setTextColor(a(this.d, this.f7525e));
                    if (jSONObject != null) {
                        a(jSONObject.getString(Constant.JSONKEY.ICONPATH), imageView);
                    }
                }
            }
        }
    }

    public final void g() {
        d();
    }

    public int getMidHeight() {
        JSONObject jSONObject = this.q;
        if (jSONObject == null) {
            return 0;
        }
        return (int) PdrUtil.parseFloat(jSONObject.getString("height"), 0.0f, 0.0f, this.w);
    }

    public int getTabHeight() {
        return this.p;
    }

    public String getTabHeightStr() {
        return this.B;
    }

    public int getTabItemDisplayedSize() {
        int i = 0;
        for (int i2 = 0; i2 < this.n.size(); i2++) {
            JSONObject jSONObject = this.n.getJSONObject(i2);
            if (jSONObject != null && (!jSONObject.containsKey("visible") || jSONObject.getBoolean("visible").booleanValue())) {
                i++;
            }
        }
        return i;
    }

    public final void h() {
        JSONObject jSONObject = this.q;
        boolean booleanValue = (jSONObject == null || !jSONObject.containsKey("visible")) ? true : this.q.getBooleanValue("visible");
        if (booleanValue) {
            booleanValue = b();
        }
        if (!booleanValue) {
            RelativeLayout relativeLayout = this.s;
            if (relativeLayout == null || relativeLayout.getParent() == null) {
                return;
            }
            this.b.removeView(this.s);
            return;
        }
        JSONObject jSONObject2 = this.q;
        if (jSONObject2 == null) {
            return;
        }
        float f = jSONObject2.getString("height") != null ? PdrUtil.parseFloat(this.q.getString("height"), 0.0f, 0.0f, this.w) : -1.0f;
        float f2 = this.q.getString("width") != null ? PdrUtil.parseFloat(this.q.getString("width"), 0.0f, 0.0f, this.w) : -1.0f;
        String string = this.q.getString("text");
        JSONObject jSONObject3 = this.q.getJSONObject("textLocales");
        if (jSONObject3 != null) {
            string = LanguageUtil.getString(jSONObject3, string);
        }
        float f3 = PdrUtil.parseFloat(this.q.getString(AbsoluteConst.JSON_KEY_ICON_WIDTH) != null ? this.q.getString(AbsoluteConst.JSON_KEY_ICON_WIDTH) : this.y, 0.0f, 0.0f, this.w);
        String string2 = this.q.getString(Constant.JSONKEY.ICONPATH);
        String string3 = this.q.getString(Constants.Name.BACKGROUND_IMAGE);
        RelativeLayout relativeLayout2 = this.s;
        if (relativeLayout2 == null) {
            relativeLayout2 = (RelativeLayout) LayoutInflater.from(this.f7524a).inflate(R.layout.dcloud_tabbar_mid, (ViewGroup) null);
            this.s = relativeLayout2;
        }
        ((GradientDrawable) ((ImageView) relativeLayout2.findViewById(R.id.itemDot)).getDrawable()).setColor(this.E);
        float f4 = (int) PdrUtil.parseFloat(this.y, 0.0f, 0.0f, this.w);
        float f5 = (int) PdrUtil.parseFloat(this.z, 0.0f, 0.0f, this.w);
        float f6 = (int) PdrUtil.parseFloat(this.A, 0.0f, 0.0f, this.w);
        ImageView imageView = (ImageView) relativeLayout2.findViewById(R.id.tabIV);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        int i = (int) f3;
        layoutParams.height = i;
        layoutParams.width = i;
        imageView.setLayoutParams(layoutParams);
        a(string2, imageView);
        TextView textView = (TextView) relativeLayout2.findViewById(R.id.tabTV);
        textView.setTextSize(0, PdrUtil.parseFloat(this.A, 0.0f, 0.0f, this.w));
        textView.setTextColor(a(this.d, this.f7525e));
        textView.setText(string);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        layoutParams2.bottomMargin = (int) ((this.p - ((f4 + f5) + f6)) / 2.0f);
        textView.setLayoutParams(layoutParams2);
        if (TextUtils.isEmpty(string)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
        a(string3, (ImageView) relativeLayout2.findViewById(R.id.bgImg));
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams((int) f2, (int) f);
        if (f2 == -1.0f) {
            layoutParams3.weight = 1.0f;
        }
        if (this.s.getParent() != null) {
            this.b.removeView(this.s);
        }
        this.b.addView(this.s, this.J, layoutParams3);
    }

    public void setDoubleCallbackListener(ICallBack iCallBack) {
    }

    public void setMask(JSONObject jSONObject) {
        String string = (jSONObject == null || !jSONObject.containsKey("color")) ? null : jSONObject.getString("color");
        if ("none".equals(string)) {
            if (this.C != null) {
                ((FrameLayout) getParent()).removeView(this.C);
                this.C = null;
                return;
            }
            return;
        }
        if (this.C == null) {
            LinearLayout linearLayout = new LinearLayout(this.f7524a);
            this.C = linearLayout;
            linearLayout.setOnClickListener(this.I);
            this.C.setBackgroundColor(a(string, this.i));
            ((FrameLayout) getParent()).addView(this.C, new ViewGroup.LayoutParams(-1, -1));
        }
    }

    public void setMaskCallbackListener(ICallBack iCallBack) {
        this.u = iCallBack;
    }

    public void setMidCallbackListener(ICallBack iCallBack) {
        this.v = iCallBack;
    }

    public void setSingleCallbackListener(ICallBack iCallBack) {
        this.t = iCallBack;
    }

    public void setTabBarBadge(JSONObject jSONObject) {
        int iIntValue = jSONObject.getInteger("index").intValue();
        String string = jSONObject.getString("text");
        ViewGroup viewGroupA = a(iIntValue);
        if (viewGroupA != null) {
            ImageView imageView = (ImageView) viewGroupA.findViewById(R.id.itemDot);
            TextView textView = (TextView) viewGroupA.findViewById(R.id.itemBadge);
            imageView.setVisibility(4);
            textView.setText(string);
            a(viewGroupA, textView);
            textView.setVisibility(0);
        }
    }

    public void setTabBarItem(JSONObject jSONObject) {
        a(jSONObject.getInteger("index").intValue(), jSONObject.getString("text"), jSONObject.getString(Constant.JSONKEY.ICONPATH), jSONObject.getString("selectedIconPath"), jSONObject.containsKey("visible") ? jSONObject.getBooleanValue("visible") : true);
    }

    public void setTabBarStyle(JSONObject jSONObject) {
        if (jSONObject.containsKey("color")) {
            this.d = jSONObject.getString("color");
        }
        if (jSONObject.containsKey("selectedColor")) {
            this.j = jSONObject.getString("selectedColor");
        }
        if (jSONObject.containsKey("backgroundColor")) {
            this.k = jSONObject.getString("backgroundColor");
        }
        if (jSONObject.containsKey(Constants.Name.BACKGROUND_IMAGE)) {
            this.l = jSONObject.getString(Constants.Name.BACKGROUND_IMAGE);
        }
        if (jSONObject.containsKey(Constants.Name.BORDER_STYLE)) {
            this.m = jSONObject.getString(Constants.Name.BORDER_STYLE);
        }
        if (jSONObject.containsKey("height")) {
            String string = jSONObject.getString("height");
            this.B = string;
            this.p = (int) PdrUtil.parseFloat(string, 0.0f, 0.0f, this.w);
        }
        if (jSONObject.containsKey("midButton")) {
            this.q = jSONObject.getJSONObject("midButton");
        }
        if (jSONObject.containsKey(Constants.Name.FONT_SIZE)) {
            this.A = jSONObject.getString(Constants.Name.FONT_SIZE);
        }
        if (jSONObject.containsKey(AbsoluteConst.JSON_KEY_ICON_WIDTH)) {
            this.y = jSONObject.getString(AbsoluteConst.JSON_KEY_ICON_WIDTH);
        }
        if (jSONObject.containsKey("backgroundRepeat")) {
            this.F = jSONObject.getString("backgroundRepeat");
        }
        e();
        g();
        f();
        if (jSONObject.containsKey("redDotColor")) {
            String string2 = jSONObject.getString("redDotColor");
            if (PdrUtil.isEmpty(string2)) {
                return;
            }
            if (this.E != PdrUtil.stringToColor(string2)) {
                this.E = PdrUtil.stringToColor(string2);
                for (int i = 0; i < this.r.size(); i++) {
                    RelativeLayout relativeLayout = this.r.get(i);
                    if (relativeLayout != null) {
                        ((GradientDrawable) ((ImageView) relativeLayout.findViewById(R.id.itemDot)).getDrawable()).setColor(this.E);
                    }
                }
            }
        }
    }

    public boolean b() {
        int tabItemDisplayedSize = getTabItemDisplayedSize();
        boolean z = tabItemDisplayedSize % 2 == 0;
        if (z) {
            int i = tabItemDisplayedSize / 2;
            int i2 = 0;
            for (int i3 = 0; i3 < this.n.size(); i3++) {
                JSONObject jSONObject = this.n.getJSONObject(i3);
                if (jSONObject != null) {
                    if (!jSONObject.containsKey("visible") || jSONObject.getBoolean("visible").booleanValue()) {
                        i2++;
                    }
                    if (i2 == i) {
                        this.J = i3 + 1;
                    }
                }
            }
        }
        return z;
    }

    public void c() {
        this.r.clear();
        this.s = null;
    }

    public final void d() {
        for (int i = 0; i < this.n.size(); i++) {
            a(i, (JSONObject) this.n.get(i));
        }
        h();
    }

    public final void e() {
        e eVar;
        if (this.b.getBackground() instanceof e) {
            eVar = (e) this.b.getBackground();
        } else {
            eVar = new e();
            this.b.setBackground(eVar);
        }
        eVar.a(this.F, this.l);
        eVar.a(a(this.k, this.h));
        eVar.b(this.l);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.b.getLayoutParams();
        layoutParams.gravity = 80;
        layoutParams.height = this.p;
        this.b.setLayoutParams(layoutParams);
        this.D.setBackgroundColor(a(this.m, this.g));
    }

    public class e extends Drawable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Shader f7531a = null;
        public String b = null;
        public String c = "no-repeat";
        public int d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Rect f7532e;
        public Paint f;
        public Paint g;

        public e() {
        }

        public Paint a() {
            if (this.f == null) {
                this.f = new Paint();
            }
            return this.f;
        }

        public Paint b() {
            if (this.g == null) {
                this.g = new Paint(1);
            }
            return this.g;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.f7532e == null) {
                this.f7532e = getBounds();
            }
            if (this.f7531a != null) {
                b().setShader(this.f7531a);
                a().setColor(Color.argb(a().getAlpha(), 255, 255, 255));
                canvas.drawRect(this.f7532e, a());
            } else {
                String str = this.b;
                if (str != null) {
                    b(str);
                    this.b = null;
                    a().setColor(Color.argb(a().getAlpha(), 255, 255, 255));
                    canvas.drawRect(this.f7532e, a());
                    b().setShader(this.f7531a);
                } else {
                    b().setColor(this.d);
                }
            }
            canvas.drawRect(this.f7532e, b());
            if (this.f7531a != null) {
                b().setShader(null);
            }
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            if (this.f7531a == null && this.b == null) {
                return;
            }
            b().setAlpha(i);
            a().setAlpha(i);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
        }

        public final Shader a(List<String> list, float f, float f2) {
            float[] fArrA = a(list.get(0).trim(), f, f2);
            if (fArrA == null) {
                return null;
            }
            return new LinearGradient(fArrA[0], fArrA[1], fArrA[2], fArrA[3], PdrUtil.stringToColor(list.get(1).trim()), PdrUtil.stringToColor(list.get(2).trim()), Shader.TileMode.CLAMP);
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x0073  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x007b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void b(java.lang.String r7) {
            /*
                Method dump skipped, instruction units count: 221
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.ev.e.b(java.lang.String):void");
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        public final float[] a(String str, float f, float f2) {
            float[] fArr = {0.0f, 0.0f, 0.0f, 0.0f};
            if (!TextUtils.isEmpty(str)) {
                str = str.replaceAll("\\s*", "").toLowerCase();
            }
            str.hashCode();
            byte b = -1;
            switch (str.hashCode()) {
                case -1352032154:
                    if (str.equals("tobottom")) {
                        b = 0;
                    }
                    break;
                case -1137407871:
                    if (str.equals("toright")) {
                        b = 1;
                    }
                    break;
                case -868157182:
                    if (str.equals("toleft")) {
                        b = 2;
                    }
                    break;
                case -172068863:
                    if (str.equals("totopleft")) {
                        b = 3;
                    }
                    break;
                case 110550266:
                    if (str.equals("totop")) {
                        b = 4;
                    }
                    break;
                case 1176531318:
                    if (str.equals("tobottomright")) {
                        b = 5;
                    }
                    break;
            }
            if (b == 0) {
                fArr[3] = f2;
            } else if (b == 1) {
                fArr[2] = f;
            } else if (b == 2) {
                fArr[0] = f;
            } else if (b == 3) {
                fArr[0] = f;
                fArr[1] = f2;
            } else if (b == 4) {
                fArr[1] = f2;
            } else {
                if (b != 5) {
                    return null;
                }
                fArr[2] = f;
                fArr[3] = f2;
            }
            return fArr;
        }

        public final List<String> a(String str) {
            String strNextToken;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            str.trim();
            try {
                if (str.startsWith("linear-gradient")) {
                    StringTokenizer stringTokenizer = new StringTokenizer(str.substring(str.indexOf("(") + 1, str.lastIndexOf(")")), ",");
                    ArrayList arrayList = new ArrayList();
                    while (true) {
                        String str2 = null;
                        while (stringTokenizer.hasMoreTokens()) {
                            strNextToken = stringTokenizer.nextToken();
                            if (strNextToken.contains("(")) {
                                str2 = strNextToken + ",";
                            } else {
                                if (strNextToken.contains(")")) {
                                    break;
                                }
                                if (str2 != null) {
                                    str2 = str2 + strNextToken + ",";
                                } else {
                                    arrayList.add(strNextToken);
                                }
                            }
                        }
                        return arrayList;
                        arrayList.add(str2 + strNextToken);
                    }
                }
            } catch (Exception unused) {
            }
            return null;
        }

        public final Bitmap a(Bitmap bitmap, int i, int i2) {
            if (bitmap == null) {
                return null;
            }
            if (this.c.equals("repeat")) {
                return bitmap;
            }
            int height = bitmap.getHeight();
            int width = bitmap.getWidth();
            float f = i / width;
            float f2 = i2 / height;
            Matrix matrix = new Matrix();
            if (this.c.equals("repeat-x")) {
                matrix.preScale(1.0f, f2);
            } else if (this.c.equals("repeat-y")) {
                matrix.preScale(f, 1.0f);
            } else {
                matrix.preScale(f, f2);
            }
            return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        }

        public void a(int i) {
            if (this.f7531a != null) {
                setAlpha(Color.alpha(i));
            } else {
                this.d = i;
            }
            invalidateSelf();
        }

        public void a(String str, String str2) {
            if (PdrUtil.isEmpty(str) || str.equals(this.c) || TextUtils.isEmpty(str2)) {
                return;
            }
            this.c = str;
            this.f7531a = null;
            b(str2);
        }
    }

    public void a() {
        LinearLayout linearLayout = this.C;
        if (linearLayout != null) {
            linearLayout.bringToFront();
        }
    }

    public void c(JSONObject jSONObject) {
        ViewGroup viewGroupA = a(jSONObject.getInteger("index").intValue());
        if (viewGroupA != null) {
            ImageView imageView = (ImageView) viewGroupA.findViewById(R.id.itemDot);
            ((TextView) viewGroupA.findViewById(R.id.itemBadge)).setVisibility(4);
            a(viewGroupA, imageView);
            imageView.setVisibility(0);
        }
    }

    public final ViewGroup a(int i) {
        if (this.r.size() - 1 > i) {
            return this.r.get(i);
        }
        return null;
    }

    public void d(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.q = jSONObject;
        }
        h();
    }

    public final String a(String str) {
        String strConvert2AbsFullPath = this.x.convert2AbsFullPath(str);
        if (strConvert2AbsFullPath != null && PdrUtil.isDeviceRootDir(strConvert2AbsFullPath)) {
            return "file://" + strConvert2AbsFullPath;
        }
        if (strConvert2AbsFullPath != null && strConvert2AbsFullPath.startsWith("/") && strConvert2AbsFullPath.length() > 1) {
            strConvert2AbsFullPath = strConvert2AbsFullPath.substring(1);
        }
        if (strConvert2AbsFullPath != null && strConvert2AbsFullPath.startsWith("android_asset/")) {
            strConvert2AbsFullPath = strConvert2AbsFullPath.replace("android_asset/", "");
        }
        return "file:///android_asset/" + strConvert2AbsFullPath;
    }

    public void b(JSONObject jSONObject) {
        ViewGroup viewGroupA = a(jSONObject.getInteger("index").intValue());
        if (viewGroupA != null) {
            ((TextView) viewGroupA.findViewById(R.id.itemBadge)).setVisibility(4);
        }
    }

    public void b(int i) {
        this.o = String.valueOf(i);
        f();
    }

    public void a(JSONObject jSONObject) {
        ViewGroup viewGroupA = a(jSONObject.getInteger("index").intValue());
        if (viewGroupA != null) {
            ((ImageView) viewGroupA.findViewById(R.id.itemDot)).setVisibility(4);
        }
    }

    public static int a(String str, String str2) {
        try {
            if (str == null) {
                return PdrUtil.stringToColor(str2);
            }
            return PdrUtil.stringToColor(str);
        } catch (Exception unused) {
            return PdrUtil.stringToColor(str2);
        }
    }

    public final void a(String str, ImageView imageView) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            Glide.with(getContext()).load(a(str)).dontAnimate().placeholder(imageView.getDrawable()).into(imageView);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void a(int i, String str, String str2, String str3, boolean z) {
        if (i >= this.n.size()) {
            return;
        }
        JSONObject jSONObject = this.n.getJSONObject(i);
        if (str != null) {
            jSONObject.put("text", (Object) str);
        }
        if (str2 != null) {
            jSONObject.put(Constant.JSONKEY.ICONPATH, (Object) str2);
        }
        if (str3 != null) {
            jSONObject.put("selectedIconPath", (Object) str3);
        }
        jSONObject.put("visible", (Object) Boolean.valueOf(z));
        a(i, jSONObject);
    }

    public final void a(int i, JSONObject jSONObject) {
        RelativeLayout relativeLayout;
        GifDrawable gifDrawable;
        jSONObject.getString("pagePath");
        String string = jSONObject.getString("text");
        JSONObject jSONObject2 = jSONObject.getJSONObject("textLocales");
        if (jSONObject2 != null) {
            string = LanguageUtil.getString(jSONObject2, string);
        }
        String string2 = jSONObject.getString(Constant.JSONKEY.ICONPATH);
        String string3 = jSONObject.getString("selectedIconPath");
        int i2 = Integer.parseInt(this.o);
        boolean booleanValue = jSONObject.containsKey("visible") ? jSONObject.getBooleanValue("visible") : true;
        if (this.r.size() - 1 < i) {
            relativeLayout = (RelativeLayout) LayoutInflater.from(this.f7524a).inflate(R.layout.dcloud_tabbar_item, (ViewGroup) null);
            relativeLayout.setTag(Integer.valueOf(i));
            relativeLayout.setOnClickListener(this.H);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            layoutParams.weight = 1.0f;
            this.b.addView(relativeLayout, layoutParams);
            this.r.add(relativeLayout);
        } else {
            relativeLayout = this.r.get(i);
        }
        ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.tabIV);
        if (!PdrUtil.isEmpty(string2) && i2 != i) {
            imageView.setVisibility(0);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams2.height = (int) PdrUtil.parseFloat(this.y, 0.0f, 0.0f, this.w);
            layoutParams2.width = (int) PdrUtil.parseFloat(this.y, 0.0f, 0.0f, this.w);
            imageView.setLayoutParams(layoutParams2);
            a(string2, imageView);
        } else {
            imageView.setVisibility(8);
        }
        if (!PdrUtil.isEmpty(string3) && i2 == i) {
            imageView.setVisibility(0);
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams3.height = (int) PdrUtil.parseFloat(this.y, 0.0f, 0.0f, this.w);
            layoutParams3.width = (int) PdrUtil.parseFloat(this.y, 0.0f, 0.0f, this.w);
            imageView.setLayoutParams(layoutParams3);
            String strA = a(string3);
            try {
                if (strA.startsWith("file:///android_asset/")) {
                    gifDrawable = new GifDrawable(this.f7524a.getAssets(), strA.replace("file:///android_asset/", ""));
                } else {
                    gifDrawable = new GifDrawable(getContext().getContentResolver(), Uri.parse(strA));
                }
                gifDrawable.setLoopCount(1);
                imageView.setImageDrawable(gifDrawable);
            } catch (Exception unused) {
                a(string3, imageView);
            }
        }
        TextView textView = (TextView) relativeLayout.findViewById(R.id.tabTV);
        textView.setTag(jSONObject);
        ((GradientDrawable) ((ImageView) relativeLayout.findViewById(R.id.itemDot)).getDrawable()).setColor(this.E);
        if (!PdrUtil.isEmpty(string)) {
            textView.setVisibility(0);
            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) textView.getLayoutParams();
            layoutParams4.topMargin = (int) PdrUtil.parseFloat(this.z, 0.0f, 0.0f, this.w);
            textView.setLayoutParams(layoutParams4);
            textView.setTextSize(0, PdrUtil.parseFloat(this.A, 0.0f, 0.0f, this.w));
            if (i2 != i) {
                textView.setTextColor(a(this.d, this.f7525e));
            } else {
                textView.setTextColor(a(this.j, this.f));
            }
            textView.setText(string);
        } else {
            textView.setVisibility(8);
        }
        if (booleanValue) {
            relativeLayout.setVisibility(0);
        } else {
            relativeLayout.setVisibility(8);
        }
    }

    public final void a(ViewGroup viewGroup, View view) {
        float f;
        float f2;
        View viewFindViewById = viewGroup.findViewById(R.id.tabIV);
        float height = viewGroup.getHeight() / this.w;
        float height2 = viewGroup.findViewById(R.id.contentWrapper).getHeight() / this.w;
        float height3 = view.getHeight() / this.w;
        float f3 = (height - height2) / 2.0f;
        if (viewFindViewById.getVisibility() == 0) {
            height3 /= 2.0f;
        }
        float f4 = f3 - height3;
        float f5 = 0.0f;
        if (f4 <= 2.0f) {
            height3 = ((f3 <= height3 || f4 >= 2.0f) && (f3 >= height3 || f3 <= 2.0f)) ? 0.0f : f3 - 2.0f;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = (int) ((-height3) * this.w);
        if (viewFindViewById.getVisibility() == 0) {
            if (view.getId() == R.id.itemDot) {
                f = this.w;
                f2 = -5.0f;
            } else {
                if (view.getId() == R.id.itemBadge) {
                    f = this.w;
                    f2 = -9.0f;
                }
                layoutParams.leftMargin = (int) (f5 - ((viewGroup.findViewById(R.id.contentWrapper).getWidth() - viewFindViewById.getWidth()) / 2));
            }
            f5 = f * f2;
            layoutParams.leftMargin = (int) (f5 - ((viewGroup.findViewById(R.id.contentWrapper).getWidth() - viewFindViewById.getWidth()) / 2));
        }
        view.setLayoutParams(layoutParams);
    }
}
