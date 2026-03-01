package androidx.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.Map;
import supwisdom.gg;
import supwisdom.hg;
import supwisdom.sg;
import supwisdom.tg;

/* JADX INFO: loaded from: classes.dex */
public class ChangeImageTransform extends Transition {
    public static final String[] J = {"android:changeImageTransform:matrix", "android:changeImageTransform:bounds"};
    public static final TypeEvaluator<Matrix> K = new a();
    public static final Property<ImageView, Matrix> L = new b(Matrix.class, "animatedTransform");

    public static class a implements TypeEvaluator<Matrix> {
        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
            return null;
        }
    }

    public static class b extends Property<ImageView, Matrix> {
        public b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Matrix get(ImageView imageView) {
            return null;
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(ImageView imageView, Matrix matrix) {
            gg.a(imageView, matrix);
        }
    }

    public static /* synthetic */ class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1422a;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            f1422a = iArr;
            try {
                iArr[ImageView.ScaleType.FIT_XY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1422a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public ChangeImageTransform() {
    }

    public static Matrix b(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        float width = imageView.getWidth();
        float f = intrinsicWidth;
        int intrinsicHeight = drawable.getIntrinsicHeight();
        float height = imageView.getHeight();
        float f2 = intrinsicHeight;
        float fMax = Math.max(width / f, height / f2);
        int iRound = Math.round((width - (f * fMax)) / 2.0f);
        int iRound2 = Math.round((height - (f2 * fMax)) / 2.0f);
        Matrix matrix = new Matrix();
        matrix.postScale(fMax, fMax);
        matrix.postTranslate(iRound, iRound2);
        return matrix;
    }

    @Override // androidx.transition.Transition
    public void a(tg tgVar) {
        d(tgVar);
    }

    @Override // androidx.transition.Transition
    public void c(tg tgVar) {
        d(tgVar);
    }

    public final void d(tg tgVar) {
        View view = tgVar.b;
        if ((view instanceof ImageView) && view.getVisibility() == 0) {
            ImageView imageView = (ImageView) view;
            if (imageView.getDrawable() == null) {
                return;
            }
            Map<String, Object> map = tgVar.f9283a;
            map.put("android:changeImageTransform:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            map.put("android:changeImageTransform:matrix", c(imageView));
        }
    }

    @Override // androidx.transition.Transition
    public String[] n() {
        return J;
    }

    public ChangeImageTransform(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static Matrix c(ImageView imageView) {
        int i = c.f1422a[imageView.getScaleType().ordinal()];
        return i != 1 ? i != 2 ? new Matrix(imageView.getImageMatrix()) : b(imageView) : d(imageView);
    }

    @Override // androidx.transition.Transition
    public Animator a(ViewGroup viewGroup, tg tgVar, tg tgVar2) {
        ObjectAnimator objectAnimatorA;
        if (tgVar != null && tgVar2 != null) {
            Rect rect = (Rect) tgVar.f9283a.get("android:changeImageTransform:bounds");
            Rect rect2 = (Rect) tgVar2.f9283a.get("android:changeImageTransform:bounds");
            if (rect != null && rect2 != null) {
                Matrix matrix = (Matrix) tgVar.f9283a.get("android:changeImageTransform:matrix");
                Matrix matrix2 = (Matrix) tgVar2.f9283a.get("android:changeImageTransform:matrix");
                boolean z = (matrix == null && matrix2 == null) || (matrix != null && matrix.equals(matrix2));
                if (rect.equals(rect2) && z) {
                    return null;
                }
                ImageView imageView = (ImageView) tgVar2.b;
                Drawable drawable = imageView.getDrawable();
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                gg.a(imageView);
                if (intrinsicWidth == 0 || intrinsicHeight == 0) {
                    objectAnimatorA = a(imageView);
                } else {
                    if (matrix == null) {
                        matrix = hg.f7835a;
                    }
                    if (matrix2 == null) {
                        matrix2 = hg.f7835a;
                    }
                    L.set(imageView, matrix);
                    objectAnimatorA = a(imageView, matrix, matrix2);
                }
                gg.a(imageView, objectAnimatorA);
                return objectAnimatorA;
            }
        }
        return null;
    }

    public static Matrix d(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        Matrix matrix = new Matrix();
        matrix.postScale(imageView.getWidth() / drawable.getIntrinsicWidth(), imageView.getHeight() / drawable.getIntrinsicHeight());
        return matrix;
    }

    public final ObjectAnimator a(ImageView imageView) {
        return ObjectAnimator.ofObject(imageView, (Property<ImageView, V>) L, (TypeEvaluator) K, (Object[]) new Matrix[]{null, null});
    }

    public final ObjectAnimator a(ImageView imageView, Matrix matrix, Matrix matrix2) {
        return ObjectAnimator.ofObject(imageView, (Property<ImageView, V>) L, (TypeEvaluator) new sg.a(), (Object[]) new Matrix[]{matrix, matrix2});
    }
}
