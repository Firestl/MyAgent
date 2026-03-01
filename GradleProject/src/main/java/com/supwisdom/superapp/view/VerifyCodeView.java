package com.supwisdom.superapp.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.supwisdom.zueb.R;

/* JADX INFO: loaded from: classes2.dex */
public class VerifyCodeView extends RelativeLayout {
    public static TextView[] d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f4409e = 6;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public EditText f4410a;
    public String b;
    public b c;

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            VerifyCodeView verifyCodeView = VerifyCodeView.this;
            verifyCodeView.b = verifyCodeView.f4410a.getText().toString();
            if (VerifyCodeView.this.c != null) {
                if (VerifyCodeView.this.b.length() >= VerifyCodeView.f4409e) {
                    VerifyCodeView.this.c.a();
                } else {
                    VerifyCodeView.this.c.b();
                }
            }
            for (int i = 0; i < VerifyCodeView.f4409e; i++) {
                if (i < VerifyCodeView.this.b.length()) {
                    VerifyCodeView.d[i].setText(String.valueOf(VerifyCodeView.this.b.charAt(i)));
                } else {
                    VerifyCodeView.d[i].setText("");
                }
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public interface b {
        void a();

        void b();
    }

    public VerifyCodeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public String getEditContent() {
        return this.b;
    }

    public void setInputCompleteListener(b bVar) {
        this.c = bVar;
    }

    public VerifyCodeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View.inflate(context, R.layout.view_verify_code, this);
        TextView[] textViewArr = new TextView[f4409e];
        d = textViewArr;
        textViewArr[0] = (TextView) findViewById(R.id.tv_0);
        d[1] = (TextView) findViewById(R.id.tv_1);
        d[2] = (TextView) findViewById(R.id.tv_2);
        d[3] = (TextView) findViewById(R.id.tv_3);
        d[4] = (TextView) findViewById(R.id.tv_4);
        d[5] = (TextView) findViewById(R.id.tv_5);
        EditText editText = (EditText) findViewById(R.id.edit_text_view);
        this.f4410a = editText;
        editText.setCursorVisible(false);
        a();
    }

    public final void a() {
        this.f4410a.addTextChangedListener(new a());
    }
}
