package com.loc;

import android.net.Uri;
import android.text.TextUtils;

/* JADX INFO: compiled from: IPV6Request.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class q extends av {
    @Override // com.loc.av
    public String d() {
        if (TextUtils.isEmpty(c())) {
            return c();
        }
        String strC = c();
        Uri uri = Uri.parse(strC);
        if (uri.getAuthority().startsWith("dualstack-")) {
            return strC;
        }
        return uri.buildUpon().authority("dualstack-" + uri.getAuthority()).build().toString();
    }

    @Override // com.loc.av
    public final boolean i() {
        return true;
    }
}
