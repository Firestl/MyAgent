package com.newcapec.virtualcard.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;
import com.newcapec.virtualcard.R;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.List;
import supwisdom.b;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordInputView extends AppCompatEditText {
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3860e;
    public float f;
    public float g;
    public int h;
    public int i;
    public float j;
    public float k;
    public Paint l;
    public Paint m;
    public List<String> n;
    public a o;

    public interface a {
    }

    public PasswordInputView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = new Paint(1);
        this.m = new Paint(1);
        this.n = new ArrayList();
        Resources resources = getResources();
        int color = resources.getColor(R.color.virtual_card_password_input_border_color);
        float dimension = resources.getDimension(R.dimen.virtual_card_password_input_border_width);
        float dimension2 = resources.getDimension(R.dimen.virtual_card_password_input_border_radius);
        int integer = resources.getInteger(R.integer.virtual_card_password_input_password_length);
        int color2 = resources.getColor(R.color.virtual_card_password_input_text_color);
        float dimension3 = resources.getDimension(R.dimen.virtual_card_password_input_text_width);
        float dimension4 = resources.getDimension(R.dimen.virtual_card_password_input_text_radius);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.PasswordInputView, 0, 0);
        try {
            this.f3860e = typedArrayObtainStyledAttributes.getColor(R.styleable.PasswordInputView_borderColor, color);
            this.f = typedArrayObtainStyledAttributes.getDimension(R.styleable.PasswordInputView_PasswordBorderWidth, dimension);
            this.g = typedArrayObtainStyledAttributes.getDimension(R.styleable.PasswordInputView_borderRadius, dimension2);
            this.h = typedArrayObtainStyledAttributes.getInt(R.styleable.PasswordInputView_passwordLength, integer);
            this.i = typedArrayObtainStyledAttributes.getColor(R.styleable.PasswordInputView_passwordColor, color2);
            this.j = typedArrayObtainStyledAttributes.getDimension(R.styleable.PasswordInputView_passwordWidth, dimension3);
            this.k = typedArrayObtainStyledAttributes.getDimension(R.styleable.PasswordInputView_passwordRadius, dimension4);
            typedArrayObtainStyledAttributes.recycle();
            this.m.setStrokeWidth(this.f);
            this.m.setColor(this.f3860e);
            this.l.setStrokeWidth(this.j);
            this.l.setStyle(Paint.Style.FILL);
            this.l.setColor(this.i);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void a() {
        this.n.clear();
        setText("");
    }

    public void a(String str) {
        String str2;
        a aVar = this.o;
        if (aVar != null) {
            this.n.add(((b) aVar).a(str));
            str2 = getText().toString() + Operators.MUL;
        } else {
            str2 = getText().toString() + str;
        }
        setText(str2);
    }

    public void b() {
        if (!this.n.isEmpty()) {
            this.n.remove(r0.size() - 1);
        }
        String string = getText().toString();
        if (!TextUtils.isEmpty(string)) {
            string = string.substring(0, string.length() - 1);
        }
        setText(string);
    }

    public int getBorderColor() {
        return this.f3860e;
    }

    public float getBorderRadius() {
        return this.g;
    }

    public float getBorderWidth() {
        return this.f;
    }

    public List<String> getCipherTextList() {
        return this.n;
    }

    public int getPasswordColor() {
        return this.i;
    }

    public int getPasswordLength() {
        return this.h;
    }

    public float getPasswordRadius() {
        return this.k;
    }

    public float getPasswordWidth() {
        return this.j;
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        int i;
        int width = getWidth();
        int height = getHeight();
        float f = height;
        RectF rectF = new RectF(0.0f, 0.0f, width, f);
        this.m.setColor(this.f3860e);
        this.m.setStrokeWidth(this.f);
        float f2 = this.g;
        canvas.drawRoundRect(rectF, f2, f2, this.m);
        RectF rectF2 = new RectF(rectF.left + 2.0f, rectF.top + 2.0f, rectF.right - 2.0f, rectF.bottom - 2.0f);
        this.m.setColor(-1);
        float f3 = this.g;
        canvas.drawRoundRect(rectF2, f3, f3, this.m);
        this.m.setColor(this.f3860e);
        this.m.setStrokeWidth(2.0f);
        int i2 = 1;
        while (true) {
            i = this.h;
            if (i2 >= i) {
                break;
            }
            float f4 = (width * i2) / i;
            canvas.drawLine(f4, 0.0f, f4, f, this.m);
            i2++;
        }
        float f5 = height / 2;
        float f6 = (width / i) / 2;
        for (int i3 = 0; i3 < this.d; i3++) {
            canvas.drawCircle(((width * i3) / this.h) + f6, f5, this.j, this.l);
        }
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        this.d = charSequence.toString().length();
        invalidate();
    }

    public void setBorderColor(int i) {
        this.f3860e = i;
        this.m.setColor(i);
        invalidate();
    }

    public void setBorderRadius(float f) {
        this.g = f;
        invalidate();
    }

    public void setBorderWidth(float f) {
        this.f = f;
        this.m.setStrokeWidth(f);
        invalidate();
    }

    public void setEncryptInterface(a aVar) {
        this.o = aVar;
    }

    public void setPasswordColor(int i) {
        this.i = i;
        this.l.setColor(i);
        invalidate();
    }

    public void setPasswordLength(int i) {
        this.h = i;
        invalidate();
    }

    public void setPasswordRadius(float f) {
        this.k = f;
        invalidate();
    }

    public void setPasswordWidth(float f) {
        this.j = f;
        this.l.setStrokeWidth(f);
        invalidate();
    }
}
