package supwisdom;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.dom.transition.WXTransition;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXScroller;
import com.taobao.weex.ui.component.WXText;
import com.taobao.weex.ui.view.WXTextView;
import com.taobao.weex.ui.view.border.BorderDrawable;
import com.taobao.weex.utils.WXUtils;
import io.dcloud.common.constant.AbsoluteConst;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import supwisdom.yi;

/* JADX INFO: compiled from: WXViewUpdateService.java */
/* JADX INFO: loaded from: classes.dex */
public final class ak {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<String, yj> f6935a;
    public static final m c;
    public static final l b = new l();
    public static final List<String> d = Arrays.asList("width", "height", "margin-left", "margin-right", "margin-top", "margin-bottom", AbsoluteConst.JSON_KEY_PADDING_LEFT, AbsoluteConst.JSON_KEY_PADDING_RIGHT, "padding-top", "padding-bottom");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Handler f6936e = new Handler(Looper.getMainLooper());

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class b implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f6937a;
            public final /* synthetic */ int b;

            public a(b bVar, View view, int i) {
                this.f6937a = view;
                this.b = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                Drawable background = this.f6937a.getBackground();
                if (background == null) {
                    this.f6937a.setBackgroundColor(this.b);
                } else if (background instanceof BorderDrawable) {
                    ((BorderDrawable) background).setColor(this.b);
                } else if (background instanceof ColorDrawable) {
                    ((ColorDrawable) background).setColor(this.b);
                }
            }
        }

        public b() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            if (obj instanceof Integer) {
                ak.b(new a(this, view, ((Integer) obj).intValue()));
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class c implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f6938a;
            public final /* synthetic */ double b;
            public final /* synthetic */ yi.c c;

            public a(c cVar, View view, double d, yi.c cVar2) {
                this.f6938a = view;
                this.b = d;
                this.c = cVar2;
            }

            @Override // java.lang.Runnable
            public void run() {
                Drawable background = this.f6938a.getBackground();
                if (background == null || !(background instanceof BorderDrawable)) {
                    return;
                }
                ((BorderDrawable) background).setBorderRadius(CSSShorthand.CORNER.BORDER_BOTTOM_LEFT, (float) ak.b(this.b, this.c));
            }
        }

        public c() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            if (obj instanceof Double) {
                ak.b(new a(this, view, ((Double) obj).doubleValue(), cVar));
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class d implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f6939a;
            public final /* synthetic */ double b;
            public final /* synthetic */ yi.c c;

            public a(d dVar, View view, double d, yi.c cVar) {
                this.f6939a = view;
                this.b = d;
                this.c = cVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                Drawable background = this.f6939a.getBackground();
                if (background == null || !(background instanceof BorderDrawable)) {
                    return;
                }
                ((BorderDrawable) background).setBorderRadius(CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT, (float) ak.b(this.b, this.c));
            }
        }

        public d() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            if (obj instanceof Double) {
                ak.b(new a(this, view, ((Double) obj).doubleValue(), cVar));
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class e implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f6940a;
            public final /* synthetic */ double b;
            public final /* synthetic */ yi.c c;

            public a(e eVar, View view, double d, yi.c cVar) {
                this.f6940a = view;
                this.b = d;
                this.c = cVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                Drawable background = this.f6940a.getBackground();
                if (background == null || !(background instanceof BorderDrawable)) {
                    return;
                }
                ((BorderDrawable) background).setBorderRadius(CSSShorthand.CORNER.BORDER_TOP_LEFT, (float) ak.b(this.b, this.c));
            }
        }

        public e() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            if (obj instanceof Double) {
                ak.b(new a(this, view, ((Double) obj).doubleValue(), cVar));
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class f implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f6941a;
            public final /* synthetic */ double b;
            public final /* synthetic */ yi.c c;

            public a(f fVar, View view, double d, yi.c cVar) {
                this.f6941a = view;
                this.b = d;
                this.c = cVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                Drawable background = this.f6941a.getBackground();
                if (background == null || !(background instanceof BorderDrawable)) {
                    return;
                }
                ((BorderDrawable) background).setBorderRadius(CSSShorthand.CORNER.BORDER_TOP_RIGHT, (float) ak.b(this.b, this.c));
            }
        }

        public f() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            if (obj instanceof Double) {
                ak.b(new a(this, view, ((Double) obj).doubleValue(), cVar));
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class g implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f6942a;
            public final /* synthetic */ ArrayList b;
            public final /* synthetic */ yi.c c;

            public a(g gVar, View view, ArrayList arrayList, yi.c cVar) {
                this.f6942a = view;
                this.b = arrayList;
                this.c = cVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                Drawable background = this.f6942a.getBackground();
                if (background == null || !(background instanceof BorderDrawable)) {
                    return;
                }
                double dDoubleValue = this.b.get(0) instanceof Double ? ((Double) this.b.get(0)).doubleValue() : 0.0d;
                double dDoubleValue2 = this.b.get(1) instanceof Double ? ((Double) this.b.get(1)).doubleValue() : 0.0d;
                double dDoubleValue3 = this.b.get(2) instanceof Double ? ((Double) this.b.get(2)).doubleValue() : 0.0d;
                double dDoubleValue4 = this.b.get(3) instanceof Double ? ((Double) this.b.get(3)).doubleValue() : 0.0d;
                BorderDrawable borderDrawable = (BorderDrawable) background;
                borderDrawable.setBorderRadius(CSSShorthand.CORNER.BORDER_TOP_LEFT, (float) ak.b(dDoubleValue, this.c));
                borderDrawable.setBorderRadius(CSSShorthand.CORNER.BORDER_TOP_RIGHT, (float) ak.b(dDoubleValue2, this.c));
                borderDrawable.setBorderRadius(CSSShorthand.CORNER.BORDER_BOTTOM_LEFT, (float) ak.b(dDoubleValue3, this.c));
                borderDrawable.setBorderRadius(CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT, (float) ak.b(dDoubleValue4, this.c));
            }
        }

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f6943a;
            public final /* synthetic */ double b;
            public final /* synthetic */ yi.c c;

            public b(g gVar, View view, double d, yi.c cVar) {
                this.f6943a = view;
                this.b = d;
                this.c = cVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                Drawable background = this.f6943a.getBackground();
                if (background == null || !(background instanceof BorderDrawable)) {
                    return;
                }
                BorderDrawable borderDrawable = (BorderDrawable) background;
                borderDrawable.setBorderRadius(CSSShorthand.CORNER.BORDER_TOP_LEFT, (float) ak.b(this.b, this.c));
                borderDrawable.setBorderRadius(CSSShorthand.CORNER.BORDER_TOP_RIGHT, (float) ak.b(this.b, this.c));
                borderDrawable.setBorderRadius(CSSShorthand.CORNER.BORDER_BOTTOM_LEFT, (float) ak.b(this.b, this.c));
                borderDrawable.setBorderRadius(CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT, (float) ak.b(this.b, this.c));
            }
        }

        public g() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            if (!(obj instanceof ArrayList)) {
                if (obj instanceof Double) {
                    ak.b(new b(this, view, ((Double) obj).doubleValue(), cVar));
                }
            } else {
                ArrayList arrayList = (ArrayList) obj;
                if (arrayList.size() != 4) {
                    return;
                }
                ak.b(new a(this, view, arrayList, cVar));
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class h implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f6944a;
            public final /* synthetic */ int b;
            public final /* synthetic */ WXComponent c;

            public a(h hVar, View view, int i, WXComponent wXComponent) {
                this.f6944a = view;
                this.b = i;
                this.c = wXComponent;
            }

            @Override // java.lang.Runnable
            public void run() {
                View view = this.f6944a;
                if (view instanceof TextView) {
                    ((TextView) view).setTextColor(this.b);
                    return;
                }
                if ((this.c instanceof WXText) && (view instanceof WXTextView)) {
                    try {
                        ((WXTextView) view).setTextColor(this.b);
                        this.f6944a.invalidate();
                    } catch (Throwable th) {
                        xi.a("can not update text color, try fallback to call the old API", th);
                        Layout textLayout = ((WXTextView) this.f6944a).getTextLayout();
                        if (textLayout != null) {
                            TextPaint paint = textLayout.getPaint();
                            if (paint != null) {
                                paint.setColor(this.b);
                            }
                            this.f6944a.invalidate();
                        }
                    }
                }
            }
        }

        public h() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            if (obj instanceof Integer) {
                ak.b(new a(this, view, ((Integer) obj).intValue(), wXComponent));
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class i implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f6945a;
            public final /* synthetic */ double b;
            public final /* synthetic */ yi.c c;

            public a(i iVar, View view, double d, yi.c cVar) {
                this.f6945a = view;
                this.b = d;
                this.c = cVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f6945a.setScrollX((int) ak.b(this.b, this.c));
                this.f6945a.setScrollY((int) ak.b(this.b, this.c));
            }
        }

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f6946a;
            public final /* synthetic */ double b;
            public final /* synthetic */ yi.c c;
            public final /* synthetic */ double d;

            public b(i iVar, View view, double d, yi.c cVar, double d2) {
                this.f6946a = view;
                this.b = d;
                this.c = cVar;
                this.d = d2;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f6946a.setScrollX((int) ak.b(this.b, this.c));
                this.f6946a.setScrollY((int) ak.b(this.d, this.c));
            }
        }

        public i() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            View viewB = ak.b(wXComponent);
            if (viewB == null) {
                return;
            }
            if (obj instanceof Double) {
                ak.b(new a(this, viewB, ((Double) obj).doubleValue(), cVar));
                return;
            }
            if (obj instanceof ArrayList) {
                ArrayList arrayList = (ArrayList) obj;
                if (arrayList.size() >= 2 && (arrayList.get(0) instanceof Double) && (arrayList.get(1) instanceof Double)) {
                    ak.b(new b(this, viewB, ((Double) arrayList.get(0)).doubleValue(), cVar, ((Double) arrayList.get(1)).doubleValue()));
                }
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class j implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f6947a;
            public final /* synthetic */ double b;
            public final /* synthetic */ yi.c c;

            public a(j jVar, View view, double d, yi.c cVar) {
                this.f6947a = view;
                this.b = d;
                this.c = cVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f6947a.setScrollX((int) ak.b(this.b, this.c));
            }
        }

        public j() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            View viewB = ak.b(wXComponent);
            if (viewB != null && (obj instanceof Double)) {
                ak.b(new a(this, viewB, ((Double) obj).doubleValue(), cVar));
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class k implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f6948a;
            public final /* synthetic */ double b;
            public final /* synthetic */ yi.c c;

            public a(k kVar, View view, double d, yi.c cVar) {
                this.f6948a = view;
                this.b = d;
                this.c = cVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f6948a.setScrollY((int) ak.b(this.b, this.c));
            }
        }

        public k() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            View viewB;
            if ((obj instanceof Double) && (viewB = ak.b(wXComponent)) != null) {
                ak.b(new a(this, viewB, ((Double) obj).doubleValue(), cVar));
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class l implements yj {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f6949a;

        public void a(String str) {
            this.f6949a = str;
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            String str;
            if (!(obj instanceof Double) || TextUtils.isEmpty(this.f6949a)) {
                return;
            }
            double dDoubleValue = ((Double) obj).doubleValue();
            str = "width";
            switch (this.f6949a) {
                case "width":
                    break;
                case "height":
                    str = "height";
                    break;
                case "margin-left":
                    str = Constants.Name.MARGIN_LEFT;
                    break;
                case "margin-right":
                    str = Constants.Name.MARGIN_RIGHT;
                    break;
                case "margin-top":
                    str = Constants.Name.MARGIN_TOP;
                    break;
                case "margin-bottom":
                    str = Constants.Name.MARGIN_BOTTOM;
                    break;
                case "padding-left":
                    str = Constants.Name.PADDING_LEFT;
                    break;
                case "padding-right":
                    str = Constants.Name.PADDING_RIGHT;
                    break;
                case "padding-top":
                    str = Constants.Name.PADDING_TOP;
                    break;
                case "padding-bottom":
                    str = Constants.Name.PADDING_BOTTOM;
                    break;
                default:
                    str = null;
                    break;
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            WXTransition.asynchronouslyUpdateLayout(wXComponent, str, (float) ak.b(dDoubleValue, cVar));
            this.f6949a = null;
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class m implements yj {
        public m() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class n implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f6950a;
            public final /* synthetic */ float b;

            public a(n nVar, View view, float f) {
                this.f6950a = view;
                this.b = f;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f6950a.setAlpha(this.b);
            }
        }

        public n() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            if (obj instanceof Double) {
                ak.b(new a(this, view, (float) ((Double) obj).doubleValue()));
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class o implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Map f6951a;
            public final /* synthetic */ View b;
            public final /* synthetic */ Object c;

            public a(o oVar, Map map, View view, Object obj) {
                this.f6951a = map;
                this.b = view;
                this.c = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                int iA = tj.a(this.b.getContext(), WXUtils.getInt(this.f6951a.get(Constants.Name.PERSPECTIVE)));
                Pair<Float, Float> pairA = tj.a(WXUtils.getString(this.f6951a.get(Constants.Name.TRANSFORM_ORIGIN), null), this.b);
                if (iA != 0) {
                    this.b.setCameraDistance(iA);
                }
                if (pairA != null) {
                    this.b.setPivotX(((Float) pairA.first).floatValue());
                    this.b.setPivotY(((Float) pairA.second).floatValue());
                }
                this.b.setRotation((float) ((Double) this.c).doubleValue());
            }
        }

        public o() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            if (obj instanceof Double) {
                ak.b(new a(this, map, view, obj));
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class p implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Map f6952a;
            public final /* synthetic */ View b;
            public final /* synthetic */ Object c;

            public a(p pVar, Map map, View view, Object obj) {
                this.f6952a = map;
                this.b = view;
                this.c = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                int iA = tj.a(this.b.getContext(), WXUtils.getInt(this.f6952a.get(Constants.Name.PERSPECTIVE)));
                Pair<Float, Float> pairA = tj.a(WXUtils.getString(this.f6952a.get(Constants.Name.TRANSFORM_ORIGIN), null), this.b);
                if (iA != 0) {
                    this.b.setCameraDistance(iA);
                }
                if (pairA != null) {
                    this.b.setPivotX(((Float) pairA.first).floatValue());
                    this.b.setPivotY(((Float) pairA.second).floatValue());
                }
                this.b.setRotationX((float) ((Double) this.c).doubleValue());
            }
        }

        public p() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            if (obj instanceof Double) {
                ak.b(new a(this, map, view, obj));
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class q implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Map f6953a;
            public final /* synthetic */ View b;
            public final /* synthetic */ Object c;

            public a(q qVar, Map map, View view, Object obj) {
                this.f6953a = map;
                this.b = view;
                this.c = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                int iA = tj.a(this.b.getContext(), WXUtils.getInt(this.f6953a.get(Constants.Name.PERSPECTIVE)));
                Pair<Float, Float> pairA = tj.a(WXUtils.getString(this.f6953a.get(Constants.Name.TRANSFORM_ORIGIN), null), this.b);
                if (iA != 0) {
                    this.b.setCameraDistance(iA);
                }
                if (pairA != null) {
                    this.b.setPivotX(((Float) pairA.first).floatValue());
                    this.b.setPivotY(((Float) pairA.second).floatValue());
                }
                this.b.setRotationY((float) ((Double) this.c).doubleValue());
            }
        }

        public q() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            if (obj instanceof Double) {
                ak.b(new a(this, map, view, obj));
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class r implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Map f6954a;
            public final /* synthetic */ View b;
            public final /* synthetic */ Object c;

            public a(r rVar, Map map, View view, Object obj) {
                this.f6954a = map;
                this.b = view;
                this.c = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                int iA = tj.a(this.b.getContext(), WXUtils.getInt(this.f6954a.get(Constants.Name.PERSPECTIVE)));
                Pair<Float, Float> pairA = tj.a(WXUtils.getString(this.f6954a.get(Constants.Name.TRANSFORM_ORIGIN), null), this.b);
                if (iA != 0) {
                    this.b.setCameraDistance(iA);
                }
                if (pairA != null) {
                    this.b.setPivotX(((Float) pairA.first).floatValue());
                    this.b.setPivotY(((Float) pairA.second).floatValue());
                }
                Object obj = this.c;
                if (obj instanceof Double) {
                    float fDoubleValue = (float) ((Double) obj).doubleValue();
                    this.b.setScaleX(fDoubleValue);
                    this.b.setScaleY(fDoubleValue);
                    return;
                }
                if (obj instanceof ArrayList) {
                    ArrayList arrayList = (ArrayList) obj;
                    if (arrayList.size() >= 2 && (arrayList.get(0) instanceof Double) && (arrayList.get(1) instanceof Double)) {
                        double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
                        double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
                        this.b.setScaleX((float) dDoubleValue);
                        this.b.setScaleY((float) dDoubleValue2);
                    }
                }
            }
        }

        public r() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            ak.b(new a(this, map, view, obj));
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class s implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Map f6955a;
            public final /* synthetic */ View b;
            public final /* synthetic */ Object c;

            public a(s sVar, Map map, View view, Object obj) {
                this.f6955a = map;
                this.b = view;
                this.c = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                Pair<Float, Float> pairA = tj.a(WXUtils.getString(this.f6955a.get(Constants.Name.TRANSFORM_ORIGIN), null), this.b);
                if (pairA != null) {
                    this.b.setPivotX(((Float) pairA.first).floatValue());
                    this.b.setPivotY(((Float) pairA.second).floatValue());
                }
                this.b.setScaleX((float) ((Double) this.c).doubleValue());
            }
        }

        public s() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            if (obj instanceof Double) {
                ak.b(new a(this, map, view, obj));
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class t implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Map f6956a;
            public final /* synthetic */ View b;
            public final /* synthetic */ Object c;

            public a(t tVar, Map map, View view, Object obj) {
                this.f6956a = map;
                this.b = view;
                this.c = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                Pair<Float, Float> pairA = tj.a(WXUtils.getString(this.f6956a.get(Constants.Name.TRANSFORM_ORIGIN), null), this.b);
                if (pairA != null) {
                    this.b.setPivotX(((Float) pairA.first).floatValue());
                    this.b.setPivotY(((Float) pairA.second).floatValue());
                }
                this.b.setScaleY((float) ((Double) this.c).doubleValue());
            }
        }

        public t() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            if (obj instanceof Double) {
                ak.b(new a(this, map, view, obj));
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class u implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f6957a;
            public final /* synthetic */ double b;
            public final /* synthetic */ yi.c c;
            public final /* synthetic */ double d;

            public a(u uVar, View view, double d, yi.c cVar, double d2) {
                this.f6957a = view;
                this.b = d;
                this.c = cVar;
                this.d = d2;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f6957a.setTranslationX((float) ak.b(this.b, this.c));
                this.f6957a.setTranslationY((float) ak.b(this.d, this.c));
            }
        }

        public u() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            if (obj instanceof ArrayList) {
                ArrayList arrayList = (ArrayList) obj;
                if (arrayList.size() >= 2 && (arrayList.get(0) instanceof Double) && (arrayList.get(1) instanceof Double)) {
                    ak.b(new a(this, view, ((Double) arrayList.get(0)).doubleValue(), cVar, ((Double) arrayList.get(1)).doubleValue()));
                }
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class v implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f6958a;
            public final /* synthetic */ double b;
            public final /* synthetic */ yi.c c;

            public a(v vVar, View view, double d, yi.c cVar) {
                this.f6958a = view;
                this.b = d;
                this.c = cVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f6958a.setTranslationX((float) ak.b(this.b, this.c));
            }
        }

        public v() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            if (obj instanceof Double) {
                ak.b(new a(this, view, ((Double) obj).doubleValue(), cVar));
            }
        }
    }

    /* JADX INFO: compiled from: WXViewUpdateService.java */
    public static final class w implements yj {

        /* JADX INFO: compiled from: WXViewUpdateService.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f6959a;
            public final /* synthetic */ double b;
            public final /* synthetic */ yi.c c;

            public a(w wVar, View view, double d, yi.c cVar) {
                this.f6959a = view;
                this.b = d;
                this.c = cVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f6959a.setTranslationY((float) ak.b(this.b, this.c));
            }
        }

        public w() {
        }

        @Override // supwisdom.yj
        public void a(WXComponent wXComponent, View view, Object obj, yi.c cVar, Map<String, Object> map) {
            if (obj instanceof Double) {
                ak.b(new a(this, view, ((Double) obj).doubleValue(), cVar));
            }
        }
    }

    static {
        c = new m();
        HashMap map = new HashMap();
        f6935a = map;
        map.put("opacity", new n());
        f6935a.put("transform.translate", new u());
        f6935a.put("transform.translateX", new v());
        f6935a.put("transform.translateY", new w());
        f6935a.put("transform.scale", new r());
        f6935a.put("transform.scaleX", new s());
        f6935a.put("transform.scaleY", new t());
        f6935a.put("transform.rotate", new o());
        f6935a.put("transform.rotateZ", new o());
        f6935a.put("transform.rotateX", new p());
        f6935a.put("transform.rotateY", new q());
        f6935a.put("background-color", new b());
        f6935a.put("color", new h());
        f6935a.put("scroll.contentOffset", new i());
        f6935a.put("scroll.contentOffsetX", new j());
        f6935a.put("scroll.contentOffsetY", new k());
        f6935a.put("border-top-left-radius", new e());
        f6935a.put("border-top-right-radius", new f());
        f6935a.put("border-bottom-left-radius", new c());
        f6935a.put("border-bottom-right-radius", new d());
        f6935a.put("border-radius", new g());
    }

    public static void b(Runnable runnable) {
        f6936e.post(new zi(runnable));
    }

    public static double b(double d2, yi.c cVar) {
        return cVar.b(d2, new Object[0]);
    }

    public static View b(WXComponent wXComponent) {
        if (!(wXComponent instanceof WXScroller)) {
            xi.b("scroll offset only support on Scroller Component");
            return null;
        }
        return ((WXScroller) wXComponent).getInnerView();
    }

    public static yj a(String str) {
        yj yjVar = f6935a.get(str);
        if (yjVar != null) {
            return yjVar;
        }
        if (d.contains(str)) {
            b.a(str);
            return b;
        }
        xi.b("unknown property [" + str + Operators.ARRAY_END_STR);
        return c;
    }

    public static void a() {
        f6936e.removeCallbacksAndMessages(null);
    }
}
