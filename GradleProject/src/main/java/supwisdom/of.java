package supwisdom;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class of extends oa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RecyclerView f8662a;
    public final oa b = new a(this);

    /* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
    public static class a extends oa {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final of f8663a;

        public a(of ofVar) {
            this.f8663a = ofVar;
        }

        @Override // supwisdom.oa
        public void onInitializeAccessibilityNodeInfo(View view, xb xbVar) {
            super.onInitializeAccessibilityNodeInfo(view, xbVar);
            if (this.f8663a.b() || this.f8663a.f8662a.getLayoutManager() == null) {
                return;
            }
            this.f8663a.f8662a.getLayoutManager().onInitializeAccessibilityNodeInfoForItem(view, xbVar);
        }

        @Override // supwisdom.oa
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (super.performAccessibilityAction(view, i, bundle)) {
                return true;
            }
            if (this.f8663a.b() || this.f8663a.f8662a.getLayoutManager() == null) {
                return false;
            }
            return this.f8663a.f8662a.getLayoutManager().performAccessibilityActionForItem(view, i, bundle);
        }
    }

    public of(RecyclerView recyclerView) {
        this.f8662a = recyclerView;
    }

    public oa a() {
        return this.b;
    }

    public boolean b() {
        return this.f8662a.hasPendingAdapterUpdates();
    }

    @Override // supwisdom.oa
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName(RecyclerView.class.getName());
        if (!(view instanceof RecyclerView) || b()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().onInitializeAccessibilityEvent(accessibilityEvent);
        }
    }

    @Override // supwisdom.oa
    public void onInitializeAccessibilityNodeInfo(View view, xb xbVar) {
        super.onInitializeAccessibilityNodeInfo(view, xbVar);
        xbVar.a((CharSequence) RecyclerView.class.getName());
        if (b() || this.f8662a.getLayoutManager() == null) {
            return;
        }
        this.f8662a.getLayoutManager().onInitializeAccessibilityNodeInfo(xbVar);
    }

    @Override // supwisdom.oa
    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (super.performAccessibilityAction(view, i, bundle)) {
            return true;
        }
        if (b() || this.f8662a.getLayoutManager() == null) {
            return false;
        }
        return this.f8662a.getLayoutManager().performAccessibilityAction(i, bundle);
    }
}
