package supwisdom;

import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class yb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f9861a;

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    public static class a extends AccessibilityNodeProvider {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final yb f9862a;

        public a(yb ybVar) {
            this.f9862a = ybVar;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            xb xbVarA = this.f9862a.a(i);
            if (xbVarA == null) {
                return null;
            }
            return xbVarA.z();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            List<xb> listA = this.f9862a.a(str, i);
            if (listA == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int size = listA.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(listA.get(i2).z());
            }
            return arrayList;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i, int i2, Bundle bundle) {
            return this.f9862a.a(i, i2, bundle);
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    public static class b extends a {
        public b(yb ybVar) {
            super(ybVar);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int i) {
            xb xbVarB = this.f9862a.b(i);
            if (xbVarB == null) {
                return null;
            }
            return xbVarB.z();
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    public static class c extends b {
        public c(yb ybVar) {
            super(ybVar);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public void addExtraDataToAccessibilityNodeInfo(int i, AccessibilityNodeInfo accessibilityNodeInfo, String str, Bundle bundle) {
            this.f9862a.a(i, xb.a(accessibilityNodeInfo), str, bundle);
        }
    }

    public yb() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            this.f9861a = new c(this);
            return;
        }
        if (i >= 19) {
            this.f9861a = new b(this);
        } else if (i >= 16) {
            this.f9861a = new a(this);
        } else {
            this.f9861a = null;
        }
    }

    public Object a() {
        return this.f9861a;
    }

    public List<xb> a(String str, int i) {
        return null;
    }

    public xb a(int i) {
        return null;
    }

    public void a(int i, xb xbVar, String str, Bundle bundle) {
    }

    public boolean a(int i, int i2, Bundle bundle) {
        return false;
    }

    public xb b(int i) {
        return null;
    }

    public yb(Object obj) {
        this.f9861a = obj;
    }
}
