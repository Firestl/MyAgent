package supwisdom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Matrix;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;
import androidx.transition.R;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ImageViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class gg {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Method f7731a;
    public static boolean b;

    /* JADX INFO: compiled from: ImageViewUtils.java */
    public static class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ImageView f7732a;

        public a(ImageView imageView) {
            this.f7732a = imageView;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ImageView.ScaleType scaleType = (ImageView.ScaleType) this.f7732a.getTag(R.id.save_scale_type);
            this.f7732a.setScaleType(scaleType);
            this.f7732a.setTag(R.id.save_scale_type, null);
            if (scaleType == ImageView.ScaleType.MATRIX) {
                ImageView imageView = this.f7732a;
                imageView.setImageMatrix((Matrix) imageView.getTag(R.id.save_image_matrix));
                this.f7732a.setTag(R.id.save_image_matrix, null);
            }
            animator.removeListener(this);
        }
    }

    public static void a(ImageView imageView) {
        if (Build.VERSION.SDK_INT < 21) {
            ImageView.ScaleType scaleType = imageView.getScaleType();
            imageView.setTag(R.id.save_scale_type, scaleType);
            ImageView.ScaleType scaleType2 = ImageView.ScaleType.MATRIX;
            if (scaleType == scaleType2) {
                imageView.setTag(R.id.save_image_matrix, imageView.getImageMatrix());
            } else {
                imageView.setScaleType(scaleType2);
            }
            imageView.setImageMatrix(hg.f7835a);
        }
    }

    public static void a(ImageView imageView, Matrix matrix) {
        if (Build.VERSION.SDK_INT < 21) {
            imageView.setImageMatrix(matrix);
            return;
        }
        a();
        Method method = f7731a;
        if (method != null) {
            try {
                method.invoke(imageView, matrix);
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
    }

    public static void a() {
        if (b) {
            return;
        }
        try {
            Method declaredMethod = ImageView.class.getDeclaredMethod("animateTransform", Matrix.class);
            f7731a = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e2) {
            Log.i("ImageViewUtils", "Failed to retrieve animateTransform method", e2);
        }
        b = true;
    }

    public static void a(ImageView imageView, Animator animator) {
        if (Build.VERSION.SDK_INT < 21) {
            animator.addListener(new a(imageView));
        }
    }
}
