package supwisdom;

import android.view.View;

/* JADX INFO: compiled from: VisibilityPropagation.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class kh extends rg {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f8174a = {"android:visibilityPropagation:visibility", "android:visibilityPropagation:center"};

    @Override // supwisdom.rg
    public void a(tg tgVar) {
        View view = tgVar.b;
        Integer numValueOf = (Integer) tgVar.f9283a.get("android:visibility:visibility");
        if (numValueOf == null) {
            numValueOf = Integer.valueOf(view.getVisibility());
        }
        tgVar.f9283a.put("android:visibilityPropagation:visibility", numValueOf);
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        iArr[0] = iArr[0] + Math.round(view.getTranslationX());
        iArr[0] = iArr[0] + (view.getWidth() / 2);
        iArr[1] = iArr[1] + Math.round(view.getTranslationY());
        iArr[1] = iArr[1] + (view.getHeight() / 2);
        tgVar.f9283a.put("android:visibilityPropagation:center", iArr);
    }

    public int b(tg tgVar) {
        Integer num;
        if (tgVar == null || (num = (Integer) tgVar.f9283a.get("android:visibilityPropagation:visibility")) == null) {
            return 8;
        }
        return num.intValue();
    }

    public int c(tg tgVar) {
        return a(tgVar, 0);
    }

    public int d(tg tgVar) {
        return a(tgVar, 1);
    }

    @Override // supwisdom.rg
    public String[] a() {
        return f8174a;
    }

    public static int a(tg tgVar, int i) {
        int[] iArr;
        if (tgVar == null || (iArr = (int[]) tgVar.f9283a.get("android:visibilityPropagation:center")) == null) {
            return -1;
        }
        return iArr[i];
    }
}
