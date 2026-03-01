package com.newcapec.virtualcard.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.newcapec.virtualcard.R;

/* JADX INFO: loaded from: classes2.dex */
public class NumberKeyboardWidget extends a.a.a.f.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c f3857a;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f3858a;

        public a(View view) {
            this.f3858a = view;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NumberKeyboardWidget.this.a((TextView) this.f3858a);
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            c cVar = NumberKeyboardWidget.this.f3857a;
            if (cVar != null) {
                ((supwisdom.c) cVar).f7129a.b.b();
            }
        }
    }

    public interface c {
    }

    public NumberKeyboardWidget(Context context) {
        super(context);
    }

    public NumberKeyboardWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void a(TextView textView) {
        String string = textView.getText().toString();
        c cVar = this.f3857a;
        if (cVar != null) {
            ((supwisdom.c) cVar).a(string);
        }
    }

    @Override // a.a.a.f.a
    public void b() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_number_keyboard);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View childAt = linearLayout.getChildAt(i);
            if (childAt instanceof LinearLayout) {
                int i2 = 0;
                while (true) {
                    LinearLayout linearLayout2 = (LinearLayout) childAt;
                    if (i2 < linearLayout2.getChildCount()) {
                        View childAt2 = linearLayout2.getChildAt(i2);
                        if (childAt2 instanceof TextView) {
                            childAt2.setOnClickListener(new a(childAt2));
                        }
                        i2++;
                    }
                }
            }
        }
        findViewById(R.id.btn_del).setOnClickListener(new b());
    }

    @Override // a.a.a.f.a
    public int getLayoutResId() {
        return R.layout.virtual_card_widget_number_keyboard;
    }

    public void setNumberKeyboardListener(c cVar) {
        this.f3857a = cVar;
    }
}
