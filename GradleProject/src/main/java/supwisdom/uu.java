package supwisdom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.view.animation.Animation;
import android.widget.ImageView;
import io.dcloud.feature.weex.adapter.widget.refresh.DCWeexCircleImageView;

/* JADX INFO: loaded from: classes.dex */
public class uu extends ImageView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Animation.AnimationListener f9445a;
    public int b;
    public boolean c;

    public class a extends OvalShape {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Paint f9446a = new Paint();
        public int b;

        public a(int i, int i2) {
            uu.this.b = i;
            this.b = i2;
            float f = this.b / 2;
            this.f9446a.setShader(new RadialGradient(f, f, uu.this.b, new int[]{1023410176, 0}, (float[]) null, Shader.TileMode.CLAMP));
        }

        @Override // android.graphics.drawable.shapes.OvalShape, android.graphics.drawable.shapes.RectShape, android.graphics.drawable.shapes.Shape
        public void draw(Canvas canvas, Paint paint) {
            float width = uu.this.getWidth() / 2;
            float height = uu.this.getHeight() / 2;
            canvas.drawCircle(width, height, (this.b / 2) + uu.this.b, this.f9446a);
            canvas.drawCircle(width, height, this.b / 2, paint);
        }
    }

    public uu(Context context, int i, float f, boolean z) {
        ShapeDrawable shapeDrawable;
        super(context);
        this.c = true;
        float f2 = getContext().getResources().getDisplayMetrics().density;
        int i2 = (int) (f * f2 * 2.0f);
        int i3 = (int) (1.0f * f2);
        int i4 = (int) (0.0f * f2);
        this.c = z;
        this.b = (int) (2.5f * f2);
        if (a()) {
            shapeDrawable = new ShapeDrawable(new OvalShape());
            ku.a(this, f2 * 4.0f);
        } else {
            ShapeDrawable shapeDrawable2 = new ShapeDrawable(new a(this.b, i2));
            ku.a(this, 1, shapeDrawable2.getPaint());
            shapeDrawable2.getPaint().setShadowLayer(this.b, i4, i3, DCWeexCircleImageView.KEY_SHADOW_COLOR);
            int i5 = this.b;
            setPadding(i5, i5, i5, i5);
            shapeDrawable = shapeDrawable2;
        }
        shapeDrawable.getPaint().setColor(i);
        setBackgroundDrawable(shapeDrawable);
    }

    @Override // android.view.View
    public void onAnimationEnd() {
        super.onAnimationEnd();
        Animation.AnimationListener animationListener = this.f9445a;
        if (animationListener != null) {
            animationListener.onAnimationEnd(getAnimation());
        }
    }

    @Override // android.view.View
    public void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animationListener = this.f9445a;
        if (animationListener != null) {
            animationListener.onAnimationStart(getAnimation());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (a()) {
            return;
        }
        setMeasuredDimension(getMeasuredWidth() + (this.b * 2), getMeasuredHeight() + (this.b * 2));
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(i);
        }
    }

    public final boolean a() {
        return Build.VERSION.SDK_INT >= 21 && this.c;
    }

    public void a(Animation.AnimationListener animationListener) {
        this.f9445a = animationListener;
    }
}
