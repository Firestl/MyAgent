package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.R;

/* JADX INFO: loaded from: classes.dex */
public class MockView extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Paint f1238a;
    public Paint b;
    public Paint c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1239e;
    public String f;
    public Rect g;
    public int h;
    public int i;
    public int j;
    public int k;

    public MockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1238a = new Paint();
        this.b = new Paint();
        this.c = new Paint();
        this.d = true;
        this.f1239e = true;
        this.f = null;
        this.g = new Rect();
        this.h = Color.argb(255, 0, 0, 0);
        this.i = Color.argb(255, 200, 200, 200);
        this.j = Color.argb(255, 50, 50, 50);
        this.k = 4;
        a(context, attributeSet);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MockView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R.styleable.MockView_mock_label) {
                    this.f = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == R.styleable.MockView_mock_showDiagonals) {
                    this.d = typedArrayObtainStyledAttributes.getBoolean(index, this.d);
                } else if (index == R.styleable.MockView_mock_diagonalsColor) {
                    this.h = typedArrayObtainStyledAttributes.getColor(index, this.h);
                } else if (index == R.styleable.MockView_mock_labelBackgroundColor) {
                    this.j = typedArrayObtainStyledAttributes.getColor(index, this.j);
                } else if (index == R.styleable.MockView_mock_labelColor) {
                    this.i = typedArrayObtainStyledAttributes.getColor(index, this.i);
                } else if (index == R.styleable.MockView_mock_showLabel) {
                    this.f1239e = typedArrayObtainStyledAttributes.getBoolean(index, this.f1239e);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        if (this.f == null) {
            try {
                this.f = context.getResources().getResourceEntryName(getId());
            } catch (Exception unused) {
            }
        }
        this.f1238a.setColor(this.h);
        this.f1238a.setAntiAlias(true);
        this.b.setColor(this.i);
        this.b.setAntiAlias(true);
        this.c.setColor(this.j);
        this.k = Math.round(this.k * (getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.d) {
            width--;
            height--;
            float f = width;
            float f2 = height;
            canvas.drawLine(0.0f, 0.0f, f, f2, this.f1238a);
            canvas.drawLine(0.0f, f2, f, 0.0f, this.f1238a);
            canvas.drawLine(0.0f, 0.0f, f, 0.0f, this.f1238a);
            canvas.drawLine(f, 0.0f, f, f2, this.f1238a);
            canvas.drawLine(f, f2, 0.0f, f2, this.f1238a);
            canvas.drawLine(0.0f, f2, 0.0f, 0.0f, this.f1238a);
        }
        String str = this.f;
        if (str == null || !this.f1239e) {
            return;
        }
        this.b.getTextBounds(str, 0, str.length(), this.g);
        float fWidth = (width - this.g.width()) / 2.0f;
        float fHeight = ((height - this.g.height()) / 2.0f) + this.g.height();
        this.g.offset((int) fWidth, (int) fHeight);
        Rect rect = this.g;
        int i = rect.left;
        int i2 = this.k;
        rect.set(i - i2, rect.top - i2, rect.right + i2, rect.bottom + i2);
        canvas.drawRect(this.g, this.c);
        canvas.drawText(this.f, fWidth, fHeight, this.b);
    }

    public MockView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1238a = new Paint();
        this.b = new Paint();
        this.c = new Paint();
        this.d = true;
        this.f1239e = true;
        this.f = null;
        this.g = new Rect();
        this.h = Color.argb(255, 0, 0, 0);
        this.i = Color.argb(255, 200, 200, 200);
        this.j = Color.argb(255, 50, 50, 50);
        this.k = 4;
        a(context, attributeSet);
    }
}
