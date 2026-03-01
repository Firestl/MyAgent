package io.dcloud.feature.contacts;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class JsContactsMgr {
    public static final String INVALID_ARGUMENT_ERROR = "1";
    public static final String IO_ERROR = "4";
    public static final String NOT_SUPPORTED_ERROR = "5";
    public static final String PENDING_OPERATION_ERROR = "3";
    public static final String PERMISSION_DENIED_ERROR = "20";
    public static final String TIMEOUT_ERROR = "2";
    public static final String UNKNOWN_ERROR = "0";
    public ContactAccessor mContactAccessor;

    public JsContactsMgr(Context context) {
        this.mContactAccessor = new ContactAccessorImpl(context);
    }

    public void dispose(String str) {
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0083 A[Catch: JSONException -> 0x00a1, TryCatch #0 {JSONException -> 0x00a1, blocks: (B:26:0x007b, B:28:0x0083, B:30:0x008c, B:24:0x0077), top: B:41:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String execute(final io.dcloud.common.DHInterface.IWebview r10, java.lang.String r11, final java.lang.String[] r12) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.contacts.JsContactsMgr.execute(io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String[]):java.lang.String");
    }
}
