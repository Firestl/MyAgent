package supwisdom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: FragmentHostCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class cd<E> extends zc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Activity f7177a;
    public final Context b;
    public final Handler c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ed f7178e;

    public cd(FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, new Handler(), 0);
    }

    @Override // supwisdom.zc
    public View a(int i) {
        return null;
    }

    public void a(Fragment fragment) {
    }

    public void a(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i, Bundle bundle) {
        if (i != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        this.b.startActivity(intent);
    }

    public void a(Fragment fragment, String[] strArr, int i) {
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // supwisdom.zc
    public boolean a() {
        return true;
    }

    public boolean a(String str) {
        return false;
    }

    public Activity b() {
        return this.f7177a;
    }

    public boolean b(Fragment fragment) {
        return true;
    }

    public Context c() {
        return this.b;
    }

    public Handler d() {
        return this.c;
    }

    public abstract E e();

    public LayoutInflater f() {
        return LayoutInflater.from(this.b);
    }

    public int g() {
        return this.d;
    }

    public boolean h() {
        return true;
    }

    public void i() {
    }

    public cd(Activity activity, Context context, Handler handler, int i) {
        this.f7178e = new ed();
        this.f7177a = activity;
        na.a(context, "context == null");
        this.b = context;
        na.a(handler, "handler == null");
        this.c = handler;
        this.d = i;
    }

    public void a(Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        if (i == -1) {
            j7.a(this.f7177a, intentSender, i, intent, i2, i3, i4, bundle);
            return;
        }
        throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
    }
}
