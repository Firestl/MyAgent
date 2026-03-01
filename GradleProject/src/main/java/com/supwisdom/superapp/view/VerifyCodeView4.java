package com.supwisdom.superapp.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.supwisdom.superapp.view.VerifyCodeView;
import com.supwisdom.zueb.R;

/* JADX INFO: loaded from: classes2.dex */
public class VerifyCodeView4 extends RelativeLayout {
    public static TextView[] d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f4412e = 4;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public EditText f4413a;
    public String b;
    public VerifyCodeView.b c;

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            VerifyCodeView4 verifyCodeView4 = VerifyCodeView4.this;
            verifyCodeView4.b = verifyCodeView4.f4413a.getText().toString();
            if (VerifyCodeView4.this.c != null) {
                if (VerifyCodeView4.this.b.length() >= VerifyCodeView4.f4412e) {
                    InputMethodManager inputMethodManager = (InputMethodManager) VerifyCodeView4.this.getContext().getSystemService("input_method");
                    if (inputMethodManager != null) {
                        inputMethodManager.hideSoftInputFromWindow(VerifyCodeView4.this.f4413a.getWindowToken(), 2);
                    }
                    VerifyCodeView4.this.c.a();
                } else {
                    VerifyCodeView4.this.c.b();
                }
            }
            for (int i = 0; i < VerifyCodeView4.f4412e; i++) {
                if (i < VerifyCodeView4.this.b.length()) {
                    VerifyCodeView4.d[i].setText(String.valueOf(VerifyCodeView4.this.b.charAt(i)));
                } else {
                    VerifyCodeView4.d[i].setText("");
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

    public VerifyCodeView4(Context context) {
        this(context, null);
    }

    public String getEditContent() {
        return this.b;
    }

    public void setInputCompleteListener(VerifyCodeView.b bVar) {
        this.c = bVar;
    }

    public VerifyCodeView4(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VerifyCodeView4(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View.inflate(context, R.layout.view_verify_code4, this);
        TextView[] textViewArr = new TextView[f4412e];
        d = textViewArr;
        textViewArr[0] = (TextView) findViewById(R.id.tv_0);
        d[1] = (TextView) findViewById(R.id.tv_1);
        d[2] = (TextView) findViewById(R.id.tv_2);
        d[3] = (TextView) findViewById(R.id.tv_3);
        EditText editText = (EditText) findViewById(R.id.edit_text_view);
        this.f4413a = editText;
        editText.setCursorVisible(false);
        a();
    }

    public final void a() {
        this.f4413a.addTextChangedListener(new a());
    }
}
