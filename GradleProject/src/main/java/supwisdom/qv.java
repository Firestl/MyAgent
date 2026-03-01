package supwisdom;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;
import android.widget.Toast;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class qv extends Toast {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Toast f8957a;

    public qv(Context context) {
        super(context);
        this.f8957a = this;
    }

    public static void a(View view, Context context) {
        if (Build.VERSION.SDK_INT == 25) {
            try {
                Field declaredField = View.class.getDeclaredField("mContext");
                declaredField.setAccessible(true);
                declaredField.set(view, context);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static qv makeText(Context context, CharSequence charSequence, int i) {
        Toast toastMakeText = Toast.makeText(context, charSequence, i);
        a(toastMakeText.getView(), new pv(context));
        return new qv(context, toastMakeText);
    }

    @Override // android.widget.Toast
    public int getDuration() {
        Toast toast = this.f8957a;
        return toast instanceof qv ? super.getDuration() : toast.getDuration();
    }

    @Override // android.widget.Toast
    public int getGravity() {
        Toast toast = this.f8957a;
        return toast instanceof qv ? super.getGravity() : toast.getGravity();
    }

    @Override // android.widget.Toast
    public float getHorizontalMargin() {
        Toast toast = this.f8957a;
        return toast instanceof qv ? super.getHorizontalMargin() : toast.getHorizontalMargin();
    }

    @Override // android.widget.Toast
    public float getVerticalMargin() {
        Toast toast = this.f8957a;
        return toast instanceof qv ? super.getVerticalMargin() : toast.getVerticalMargin();
    }

    @Override // android.widget.Toast
    public View getView() {
        Toast toast = this.f8957a;
        return toast instanceof qv ? super.getView() : toast.getView();
    }

    @Override // android.widget.Toast
    public int getXOffset() {
        Toast toast = this.f8957a;
        return toast instanceof qv ? super.getXOffset() : toast.getXOffset();
    }

    @Override // android.widget.Toast
    public int getYOffset() {
        Toast toast = this.f8957a;
        return toast instanceof qv ? super.getYOffset() : toast.getYOffset();
    }

    @Override // android.widget.Toast
    public void setDuration(int i) {
        Toast toast = this.f8957a;
        if (toast instanceof qv) {
            super.setDuration(i);
        } else {
            toast.setDuration(i);
        }
    }

    @Override // android.widget.Toast
    public void setGravity(int i, int i2, int i3) {
        Toast toast = this.f8957a;
        if (toast instanceof qv) {
            super.setGravity(i, i2, i3);
        } else {
            toast.setGravity(i, i2, i3);
        }
    }

    @Override // android.widget.Toast
    public void setMargin(float f, float f2) {
        Toast toast = this.f8957a;
        if (toast instanceof qv) {
            super.setMargin(f, f2);
        } else {
            toast.setMargin(f, f2);
        }
    }

    @Override // android.widget.Toast
    public void setText(int i) {
        Toast toast = this.f8957a;
        if (toast instanceof qv) {
            super.setText(i);
        } else {
            toast.setText(i);
        }
    }

    @Override // android.widget.Toast
    public void setView(View view) {
        Toast toast = this.f8957a;
        if (toast instanceof qv) {
            super.setView(view);
        } else {
            toast.setView(view);
        }
        a(view, new pv(view.getContext()));
    }

    @Override // android.widget.Toast
    public void show() {
        Toast toast = this.f8957a;
        if (toast instanceof qv) {
            super.show();
        } else {
            toast.show();
        }
    }

    public qv(Context context, Toast toast) {
        super(context);
        this.f8957a = toast;
    }

    public static qv makeText(Context context, int i, int i2) throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(i), i2);
    }

    @Override // android.widget.Toast
    public void setText(CharSequence charSequence) {
        Toast toast = this.f8957a;
        if (toast instanceof qv) {
            super.setText(charSequence);
        } else {
            toast.setText(charSequence);
        }
    }
}
