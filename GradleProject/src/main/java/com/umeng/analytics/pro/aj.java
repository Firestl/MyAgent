package com.umeng.analytics.pro;

import android.text.TextUtils;
import java.util.ArrayList;

/* JADX INFO: compiled from: TimePeriodChain.java */
/* JADX INFO: loaded from: classes2.dex */
public class aj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5166a;
    public ArrayList<ak> b = new ArrayList<>();

    public aj(String str) {
        this.f5166a = "";
        this.f5166a = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        a();
    }

    private void a() {
        try {
            if (!this.f5166a.contains(",")) {
                String str = this.f5166a;
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                String strTrim = str.trim();
                if (this.b != null) {
                    this.b.add(new ak(strTrim));
                    return;
                }
                return;
            }
            for (String str2 : this.f5166a.split(",")) {
                if (!TextUtils.isEmpty(str2)) {
                    String strTrim2 = str2.trim();
                    if (this.b != null) {
                        this.b.add(new ak(strTrim2));
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public boolean a(int i) {
        try {
            if (this.b == null) {
                return false;
            }
            int size = this.b.size();
            for (int i2 = 0; i2 < size; i2++) {
                ak akVar = this.b.get(i2);
                if (akVar != null && akVar.a(i)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }
}
