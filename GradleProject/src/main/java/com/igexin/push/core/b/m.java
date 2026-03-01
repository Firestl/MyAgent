package com.igexin.push.core.b;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.sdk.message.GTPopupMessage;
import com.loc.z;
import com.taobao.weex.common.Constants;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.feature.uniapp.adapter.AbsURIAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class m extends BaseActionBean implements Serializable {
    public static boolean k = true;
    public static int l;
    public static int m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f3355a;
    public b b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3356e;
    public String f;
    public String g;
    public String h;
    public String i;
    public GTPopupMessage j;

    public enum a {
        closePopup,
        intent,
        url
    }

    public static class b implements Serializable {
        public String A;
        public String B;
        public String C;
        public String D;
        public String E;
        public float F;
        public float G;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3358a;
        public String b;
        public String c;
        public String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f3359e;
        public String f;
        public ArrayList<b> g;
        public String h;
        public boolean i;
        public String j;
        public String k;
        public int l;
        public int m;
        public int n;
        public GTPopupMessage o;
        public String p;
        public float q = 0.0f;
        public String r;
        public String s;
        public String t;
        public String u;
        public String v;
        public String w;
        public String x;
        public String y;
        public String z;

        public b(JSONObject jSONObject, String str, String str2, float f, float f2) {
            String str3;
            JSONObject jSONObjectOptJSONObject;
            if (jSONObject == null) {
                return;
            }
            this.F = f;
            this.G = f2;
            this.f3358a = jSONObject.optString("type");
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject(Constants.Name.LAYOUT);
            if (jSONObjectOptJSONObject2 != null) {
                this.b = jSONObjectOptJSONObject2.optString(AbsoluteConst.JSON_KEY_ALIGN);
                this.r = jSONObjectOptJSONObject2.optString("width", null);
                this.s = jSONObjectOptJSONObject2.optString("height", null);
                JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject2.optJSONObject("margin");
                if (jSONObjectOptJSONObject3 != null) {
                    this.u = jSONObjectOptJSONObject3.optString("top", null);
                    this.v = jSONObjectOptJSONObject3.optString("bottom", null);
                    this.w = jSONObjectOptJSONObject3.optString("right", null);
                    this.x = jSONObjectOptJSONObject3.optString("left", null);
                }
                JSONObject jSONObjectOptJSONObject4 = jSONObjectOptJSONObject2.optJSONObject("padding");
                if (jSONObjectOptJSONObject4 != null) {
                    this.y = jSONObjectOptJSONObject4.optString("top", null);
                    this.z = jSONObjectOptJSONObject4.optString("bottom", null);
                    this.A = jSONObjectOptJSONObject4.optString("right", null);
                    this.B = jSONObjectOptJSONObject4.optString("left", null);
                }
                this.t = jSONObjectOptJSONObject2.optString(Constants.Name.MAX_HEIGHT);
            }
            JSONObject jSONObjectOptJSONObject5 = jSONObject.optJSONObject("properties");
            if (jSONObjectOptJSONObject5 != null) {
                this.d = jSONObjectOptJSONObject5.optString("name", "");
                this.f3359e = jSONObjectOptJSONObject5.optString("elementType");
                this.f = jSONObjectOptJSONObject5.optString("elementId", "");
                this.h = jSONObjectOptJSONObject5.optString("image");
                this.C = jSONObjectOptJSONObject5.optString("cornerRadius", null);
                this.k = jSONObjectOptJSONObject5.optString("text");
                this.D = jSONObjectOptJSONObject5.optString(AbsURIAdapter.FONT, null);
                this.c = jSONObjectOptJSONObject5.optString(Constants.Name.TEXT_ALIGN, null);
                this.i = Constants.Value.BOLD.equals(jSONObjectOptJSONObject5.optString(Constants.Name.FONT_WEIGHT));
                JSONObject jSONObjectOptJSONObject6 = jSONObjectOptJSONObject5.optJSONObject("color");
                if (jSONObjectOptJSONObject6 != null) {
                    str3 = "";
                    this.l = Color.argb((int) (jSONObjectOptJSONObject6.optDouble("a") * 255.0d), (int) jSONObjectOptJSONObject6.optDouble("r"), (int) jSONObjectOptJSONObject6.optDouble(z.f), (int) jSONObjectOptJSONObject6.optDouble("b"));
                } else {
                    str3 = "";
                }
                JSONObject jSONObjectOptJSONObject7 = jSONObjectOptJSONObject5.optJSONObject("backgroundColor");
                if (jSONObjectOptJSONObject7 != null) {
                    this.m = Color.argb((int) (jSONObjectOptJSONObject7.optDouble("a") * 255.0d), (int) jSONObjectOptJSONObject7.optDouble("r"), (int) jSONObjectOptJSONObject7.optDouble(z.f), (int) jSONObjectOptJSONObject7.optDouble("b"));
                }
                this.E = jSONObjectOptJSONObject5.optString(Constants.Name.BORDER_WIDTH, null);
                JSONObject jSONObjectOptJSONObject8 = jSONObjectOptJSONObject5.optJSONObject(Constants.Name.BORDER_COLOR);
                if (jSONObjectOptJSONObject8 != null) {
                    this.n = Color.argb((int) (jSONObjectOptJSONObject8.optDouble("a") * 255.0d), (int) jSONObjectOptJSONObject8.optDouble("r"), (int) jSONObjectOptJSONObject8.optDouble(z.f), (int) jSONObjectOptJSONObject8.optDouble("b"));
                }
                this.j = jSONObjectOptJSONObject5.optString(Constants.Name.BACKGROUND_IMAGE);
            } else {
                str3 = "";
            }
            JSONObject jSONObjectOptJSONObject9 = jSONObject.optJSONObject("action");
            if (jSONObjectOptJSONObject9 != null && (jSONObjectOptJSONObject = jSONObjectOptJSONObject9.optJSONObject("ANDROID")) != null) {
                GTPopupMessage gTPopupMessage = new GTPopupMessage();
                gTPopupMessage.setElementType(this.f3359e);
                GTPopupMessage.GtAction gtAction = new GTPopupMessage.GtAction(jSONObjectOptJSONObject.optString("actionType"), jSONObjectOptJSONObject.optString(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK), jSONObjectOptJSONObject.optString("url"), jSONObjectOptJSONObject.optBoolean("closePopup", false));
                String str4 = this.f;
                StringBuilder sb = new StringBuilder();
                sb.append(this.d);
                sb.append(TextUtils.isEmpty(this.k) ? str3 : "-" + this.k);
                GTPopupMessage.EventProperties eventProperties = new GTPopupMessage.EventProperties(str, str2, str4, sb.toString());
                gTPopupMessage.setAction(gtAction);
                gTPopupMessage.setEventProperties(eventProperties);
                this.o = gTPopupMessage;
                this.p = jSONObjectOptJSONObject.optString("clickActionId", str3);
            }
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("subviews");
            if (jSONArrayOptJSONArray != null) {
                this.g = new ArrayList<>();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    this.g.add(new b(jSONArrayOptJSONArray.optJSONObject(i), str, str2, f, f2));
                }
            }
        }

        public static float a(String str) {
            if (str == null || !str.contains("px")) {
                return 0.0f;
            }
            return (float) (((double) com.igexin.push.core.e.l.getResources().getDisplayMetrics().scaledDensity) * Double.parseDouble(str.substring(0, str.length() - 2)));
        }

        public static int a(String str, float f, float f2) {
            if (str == null || !str.contains("px")) {
                return -2;
            }
            return (int) ((((double) (m.k ? Math.min(m.m / f2, m.l / f) : Math.min(m.m / f, m.l / f2))) * Double.parseDouble(str.substring(0, str.length() - 2))) + 0.5d);
        }

        private Object a(String str, Object obj) {
            if (this.q == 0.0f) {
                this.q = com.igexin.push.core.e.l.getResources().getDisplayMetrics().density;
            }
            if (str == null || !str.contains("px")) {
                return obj;
            }
            double d = Double.parseDouble(str.substring(0, str.length() - 2));
            return obj instanceof Float ? Double.valueOf(d * ((double) this.q)) : obj instanceof Integer ? Integer.valueOf((int) ((d * ((double) this.q)) + 0.5d)) : Integer.valueOf((int) ((d * ((double) this.q)) + 0.5d));
        }

        public static Object a(String str, Object obj, float f, float f2) {
            float f3;
            int i;
            if (str == null || !str.contains("px")) {
                return obj;
            }
            if (m.k) {
                f3 = m.m / f2;
                i = m.l;
            } else {
                f3 = m.l / f2;
                i = m.m;
            }
            float fMin = Math.min(f3, i / f);
            float f4 = Float.parseFloat(str.substring(0, str.length() - 2));
            if (obj instanceof Float) {
                return Float.valueOf(f4 * fMin);
            }
            boolean z = obj instanceof Integer;
            return Integer.valueOf((int) ((f4 * fMin) + 0.5f));
        }

        public final int a() {
            return a(this.r, this.F, this.G);
        }

        public final int b() {
            return a(this.s, this.F, this.G);
        }

        public final int c() {
            return ((Integer) a(this.u, 0, this.F, this.G)).intValue();
        }

        public final int d() {
            return ((Integer) a(this.v, 0, this.F, this.G)).intValue();
        }

        public final int e() {
            return ((Integer) a(this.w, 0, this.F, this.G)).intValue();
        }

        public final int f() {
            return ((Integer) a(this.x, 0, this.F, this.G)).intValue();
        }

        public final int g() {
            return ((Integer) a(this.y, 0, this.F, this.G)).intValue();
        }

        public final int h() {
            return ((Integer) a(this.z, 0, this.F, this.G)).intValue();
        }

        public final int i() {
            return ((Integer) a(this.A, 0, this.F, this.G)).intValue();
        }

        public final int j() {
            return ((Integer) a(this.B, 0, this.F, this.G)).intValue();
        }

        public final int k() {
            return ((Integer) a(this.C, 0, this.F, this.G)).intValue();
        }

        public final float l() {
            return ((Float) a(this.D, Float.valueOf(0.0f), this.F, this.G)).floatValue();
        }

        public final int m() {
            return ((Integer) a(this.E, 0, this.F, this.G)).intValue();
        }

        public final int n() {
            return ((Integer) a(this.t, 0, this.F, this.G)).intValue();
        }
    }

    public static void a(Context context) {
        int i;
        int i2 = context.getResources().getConfiguration().orientation;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int iMin = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        if (i2 == 1) {
            k = true;
            l = iMin;
            i = displayMetrics.heightPixels;
        } else {
            k = false;
            l = iMin;
            i = displayMetrics.widthPixels;
        }
        m = i;
    }

    private void a(String str) {
        this.f3356e = str;
    }

    private void a(JSONObject jSONObject) {
        float f;
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("statisticsInfo");
        if (jSONObjectOptJSONObject != null) {
            this.c = jSONObjectOptJSONObject.optString("planName", "");
            this.d = jSONObjectOptJSONObject.optString("nodeName", "");
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("properties");
        float f2 = 1.0f;
        if (jSONObjectOptJSONObject2 != null) {
            String strOptString = jSONObjectOptJSONObject2.optString("baseWidth");
            String strOptString2 = jSONObjectOptJSONObject2.optString("baseHeight");
            f2 = Float.parseFloat(strOptString.substring(0, strOptString.length() - 2));
            f = Float.parseFloat(strOptString2.substring(0, strOptString2.length() - 2));
        } else {
            f = 1.0f;
        }
        this.i = jSONObject.optString("showActionId", "");
        float f3 = f2;
        float f4 = f;
        this.f3355a = new b(jSONObject.optJSONObject(AbsoluteConst.JSON_KEY_MASK), this.c, this.d, f3, f4);
        this.b = new b(jSONObject.optJSONObject("template"), this.c, this.d, f3, f4);
        GTPopupMessage.EventProperties eventProperties = new GTPopupMessage.EventProperties(this.c, this.d, "", "");
        GTPopupMessage gTPopupMessage = new GTPopupMessage();
        gTPopupMessage.setEventProperties(eventProperties);
        this.j = gTPopupMessage;
    }

    private void b(String str) {
        this.f = str;
    }

    private void c(String str) {
        this.g = str;
    }

    private String d() {
        return this.g;
    }

    private void d(String str) {
        this.h = str;
    }

    private String e() {
        return this.i;
    }

    private GTPopupMessage f() {
        return this.j;
    }

    private b g() {
        return this.b;
    }

    private b h() {
        return this.f3355a;
    }

    private String i() {
        return this.f3356e;
    }

    private String j() {
        return this.h;
    }

    private String k() {
        return this.f;
    }
}
