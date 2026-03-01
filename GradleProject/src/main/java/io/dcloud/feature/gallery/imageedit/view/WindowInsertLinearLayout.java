package io.dcloud.feature.gallery.imageedit.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public class WindowInsertLinearLayout extends LinearLayout {

    public class a implements ViewGroup.OnHierarchyChangeListener {
        public a() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            if (Build.VERSION.SDK_INT >= 20) {
                WindowInsertLinearLayout.this.requestApplyInsets();
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
        }
    }

    public WindowInsertLinearLayout(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public WindowInsets dispatchApplyWindowInsets(WindowInsets windowInsets) {
        if (Build.VERSION.SDK_INT >= 20) {
            for (int i = 0; i < getChildCount(); i++) {
                getChildAt(i).dispatchApplyWindowInsets(windowInsets);
            }
        }
        return windowInsets;
    }

    public WindowInsertLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnHierarchyChangeListener(new a());
    }
}
