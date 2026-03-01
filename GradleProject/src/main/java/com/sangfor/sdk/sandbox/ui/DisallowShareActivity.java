package com.sangfor.sdk.sandbox.ui;

import android.os.Bundle;
import android.widget.Toast;
import com.sangfor.sdk.base.BaseActivity;
import com.sangfor.sdk.sandbox.common.Sangfor_j.Sangfor_a;
import supwisdom.cb1;
import supwisdom.jb1;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class DisallowShareActivity extends BaseActivity {

    /* JADX INFO: compiled from: Proguard */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f3942a;

        static {
            int[] iArr = new int[Sangfor_a.values().length];
            f3942a = iArr;
            try {
                iArr[Sangfor_a.SAFE_MEDIA_SERVICE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3942a[Sangfor_a.SAFE_DOWNLOADS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public final String b() {
        return getApplicationInfo().loadLabel(getPackageManager()).toString();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(null);
        jb1.a();
        if (cb1.b().b(getIntent())) {
            return;
        }
        int i = a.f3942a[Sangfor_a.values()[getIntent().getIntExtra("extra_safe_component_type", Sangfor_a.NONE.ordinal())].ordinal()];
        if (i == 1) {
            str = jb1.c.A2;
        } else if (i != 2) {
            str = jb1.c.b2 + b();
        } else {
            str = jb1.c.B2;
        }
        if (getIntent().getBooleanExtra("extra_not_allow_external_sdcard", false)) {
            str = jb1.c.M2;
        }
        Toast.makeText(this, str, 0).show();
        finish();
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        cb1.b().a(this);
    }
}
