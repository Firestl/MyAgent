package com.supwisdom.superapp.view;

import android.R;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.supwisdom.superapp.view.VerificationCodeInputView;
import io.dcloud.common.util.DensityUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class VerificationCodeInputView extends RelativeLayout {
    public int A;
    public int B;
    public int C;
    public boolean D;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f4403a;
    public d b;
    public LinearLayout c;
    public RelativeLayout[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView[] f4404e;
    public View[] f;
    public View[] g;
    public EditText h;
    public ValueAnimator i;
    public List<String> j;
    public int k;
    public VCInputType l;
    public int m;
    public int n;
    public int o;
    public float p;
    public int q;
    public int r;
    public boolean s;
    public int t;
    public int u;
    public int v;
    public int w;
    public boolean x;
    public int y;
    public int z;

    public enum VCInputType {
        NUMBER,
        NUMBERPASSWORD,
        TEXT,
        TEXTPASSWORD
    }

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (editable == null || editable.length() <= 0) {
                return;
            }
            VerificationCodeInputView.this.h.setText("");
            VerificationCodeInputView.this.setCode(editable.toString());
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f4406a;

        static {
            int[] iArr = new int[VCInputType.values().length];
            f4406a = iArr;
            try {
                iArr[VCInputType.NUMBERPASSWORD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4406a[VCInputType.TEXT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4406a[VCInputType.TEXTPASSWORD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public class c extends PasswordTransformationMethod {

        public class a implements CharSequence {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public CharSequence f4408a;

            public a(c cVar, CharSequence charSequence) {
                this.f4408a = charSequence;
            }

            @Override // java.lang.CharSequence
            public char charAt(int i) {
                return (char) 8226;
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.f4408a.length();
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i, int i2) {
                return this.f4408a.subSequence(i, i2);
            }
        }

        public c() {
        }

        @Override // android.text.method.PasswordTransformationMethod, android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence charSequence, View view) {
            return new a(this, charSequence);
        }
    }

    public interface d {
        void a();

        void a(String str);
    }

    public VerificationCodeInputView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = new ArrayList();
        a(context, attributeSet);
    }

    public static /* synthetic */ Object a(float f, Object obj, Object obj2) {
        return f <= 0.5f ? obj : obj2;
    }

    private String getClipboardString() {
        ClipData.Item itemAt;
        ClipboardManager clipboardManager = (ClipboardManager) this.f4403a.getSystemService("clipboard");
        if (clipboardManager == null || !clipboardManager.hasPrimaryClip() || !clipboardManager.getPrimaryClipDescription().hasMimeType("text/plain") || (itemAt = clipboardManager.getPrimaryClip().getItemAt(0)) == null || TextUtils.isEmpty(itemAt.getText())) {
            return null;
        }
        return itemAt.getText().toString();
    }

    private String getCode() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.j.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCode(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            if (this.j.size() < this.k) {
                this.j.add(String.valueOf(str.charAt(i)));
            }
        }
        d();
    }

    private void setCursorView(View view) {
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(view, "backgroundColor", this.A, R.color.transparent);
        this.i = objectAnimatorOfInt;
        objectAnimatorOfInt.setDuration(1500L);
        this.i.setRepeatCount(-1);
        this.i.setRepeatMode(1);
        this.i.setEvaluator(new TypeEvaluator() { // from class: supwisdom.sn1
            @Override // android.animation.TypeEvaluator
            public final Object evaluate(float f, Object obj, Object obj2) {
                return VerificationCodeInputView.a(f, obj, obj2);
            }
        });
        this.i.start();
    }

    private void setInputType(TextView textView) {
        int i = b.f4406a[this.l.ordinal()];
        if (i == 1) {
            textView.setInputType(18);
            textView.setTransformationMethod(new c());
        } else if (i == 2) {
            textView.setInputType(1);
        } else if (i != 3) {
            textView.setInputType(2);
        } else {
            textView.setInputType(17);
            textView.setTransformationMethod(new c());
        }
    }

    public final void b(View view) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, this.w);
        layoutParams.addRule(12);
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(this.u);
    }

    public final void c() {
        ValueAnimator valueAnimator = this.i;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        for (int i = 0; i < this.k; i++) {
            this.g[i].setBackgroundColor(0);
            if (this.x) {
                this.f[i].setBackgroundColor(this.u);
            }
            if (this.D) {
                a(this.d[i], this.B);
            }
        }
        if (this.j.size() < this.k) {
            setCursorView(this.g[this.j.size()]);
            if (this.x) {
                this.f[this.j.size()].setBackgroundColor(this.v);
            }
            if (this.D) {
                a(this.d[this.j.size()], this.C);
            }
        }
    }

    public final void d() {
        for (int i = 0; i < this.k; i++) {
            TextView textView = this.f4404e[i];
            if (this.j.size() > i) {
                textView.setText(this.j.get(i));
            } else {
                textView.setText("");
            }
        }
        c();
        b();
    }

    public final void e() {
        int i = this.t;
        int i2 = this.k;
        this.r = (i - (this.m * i2)) / (i2 - 1);
        for (int i3 = 0; i3 < this.k; i3++) {
            this.c.getChildAt(i3).setLayoutParams(a(i3));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        e.a((Activity) getContext());
        ValueAnimator valueAnimator = this.i;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    @Override // android.widget.RelativeLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.t = getMeasuredWidth();
        e();
    }

    public void setOnInputListener(d dVar) {
        this.b = dVar;
    }

    public static class e {
        public static void a(Context context, View view) {
            ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(view, 2);
        }

        public static void a(Activity activity) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
            }
        }
    }

    public final void a(Context context, AttributeSet attributeSet) {
        this.f4403a = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.supwisdom.superapp.R.styleable.VerificationCodeInputView);
        this.k = typedArrayObtainStyledAttributes.getInteger(7, 4);
        this.l = VCInputType.values()[typedArrayObtainStyledAttributes.getInt(6, VCInputType.NUMBER.ordinal())];
        this.m = typedArrayObtainStyledAttributes.getDimensionPixelSize(15, DensityUtils.dp2px(context, 40.0f));
        this.n = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, DensityUtils.dp2px(context, 40.0f));
        this.o = typedArrayObtainStyledAttributes.getColor(9, -16777216);
        this.p = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, DensityUtils.sp2px(context, 14.0f));
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, -1);
        this.B = resourceId;
        if (resourceId < 0) {
            this.B = typedArrayObtainStyledAttributes.getColor(0, -1);
        }
        this.D = typedArrayObtainStyledAttributes.hasValue(4);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(4, -1);
        this.C = resourceId2;
        if (resourceId2 < 0) {
            this.C = typedArrayObtainStyledAttributes.getColor(4, -1);
        }
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(8);
        this.s = zHasValue;
        if (zHasValue) {
            this.q = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, 0);
        }
        this.y = typedArrayObtainStyledAttributes.getDimensionPixelOffset(3, DensityUtils.dp2px(context, 2.0f));
        this.z = typedArrayObtainStyledAttributes.getDimensionPixelOffset(2, DensityUtils.dp2px(context, 30.0f));
        this.A = typedArrayObtainStyledAttributes.getColor(1, Color.parseColor("#C3C3C3"));
        this.w = typedArrayObtainStyledAttributes.getDimensionPixelOffset(13, DensityUtils.dp2px(context, 1.0f));
        this.u = typedArrayObtainStyledAttributes.getColor(11, Color.parseColor("#F0F0F0"));
        this.v = typedArrayObtainStyledAttributes.getColor(12, Color.parseColor("#C3C3C3"));
        this.x = typedArrayObtainStyledAttributes.getBoolean(14, false);
        a();
        typedArrayObtainStyledAttributes.recycle();
    }

    public VerificationCodeInputView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = new ArrayList();
        a(context, attributeSet);
    }

    public final void b(EditText editText) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(6, this.c.getId());
        layoutParams.addRule(8, this.c.getId());
        editText.setLayoutParams(layoutParams);
        setInputType(editText);
        editText.setBackgroundColor(0);
        editText.setTextColor(0);
        editText.setCursorVisible(false);
        editText.addTextChangedListener(new a());
        editText.setOnKeyListener(new View.OnKeyListener() { // from class: supwisdom.rn1
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return this.f9076a.a(view, i, keyEvent);
            }
        });
        a(editText);
    }

    public final void b() {
        if (this.b == null) {
            return;
        }
        if (this.j.size() == this.k) {
            this.b.a(getCode());
        } else {
            this.b.a();
        }
    }

    public final void a() {
        int i = this.k;
        this.d = new RelativeLayout[i];
        this.f4404e = new TextView[i];
        this.f = new View[i];
        this.g = new View[i];
        LinearLayout linearLayout = new LinearLayout(this.f4403a);
        this.c = linearLayout;
        linearLayout.setOrientation(0);
        this.c.setGravity(1);
        this.c.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        for (int i2 = 0; i2 < this.k; i2++) {
            RelativeLayout relativeLayout = new RelativeLayout(this.f4403a);
            relativeLayout.setLayoutParams(a(i2));
            a(relativeLayout, this.B);
            this.d[i2] = relativeLayout;
            TextView textView = new TextView(this.f4403a);
            a(textView);
            relativeLayout.addView(textView);
            this.f4404e[i2] = textView;
            View view = new View(this.f4403a);
            a(view);
            relativeLayout.addView(view);
            this.g[i2] = view;
            if (this.x) {
                View view2 = new View(this.f4403a);
                b(view2);
                relativeLayout.addView(view2);
                this.f[i2] = view2;
            }
            this.c.addView(relativeLayout);
        }
        addView(this.c);
        EditText editText = new EditText(this.f4403a);
        this.h = editText;
        b(editText);
        addView(this.h);
        c();
    }

    public final void a(TextView textView) {
        textView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        textView.setTextAlignment(4);
        textView.setGravity(17);
        textView.setTextColor(this.o);
        textView.setTextSize(0, this.p);
        setInputType(textView);
        textView.setPadding(0, 0, 0, 0);
    }

    public final void a(View view) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.y, this.z);
        layoutParams.addRule(13);
        view.setLayoutParams(layoutParams);
    }

    public /* synthetic */ boolean a(View view, int i, KeyEvent keyEvent) {
        if (i != 67 || keyEvent.getAction() != 0 || this.j.size() <= 0) {
            return false;
        }
        List<String> list = this.j;
        list.remove(list.size() - 1);
        d();
        return true;
    }

    public final void a(RelativeLayout relativeLayout, int i) {
        if (i > 0) {
            relativeLayout.setBackgroundResource(i);
        } else {
            relativeLayout.setBackgroundColor(i);
        }
    }

    public final LinearLayout.LayoutParams a(int i) {
        int i2;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.m, this.n);
        if (!this.s) {
            i2 = this.r / 2;
        } else {
            int i3 = this.q;
            int i4 = i3 / 2;
            int i5 = this.r;
            i2 = i3 > i5 ? i5 / 2 : i4;
        }
        if (i == 0) {
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = i2;
        } else if (i == this.k - 1) {
            layoutParams.leftMargin = i2;
            layoutParams.rightMargin = 0;
        } else {
            layoutParams.leftMargin = i2;
            layoutParams.rightMargin = i2;
        }
        return layoutParams;
    }

    public final void a(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        e.a(getContext(), editText);
    }
}
