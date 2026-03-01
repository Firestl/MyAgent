package com.vivo.push.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import com.vivo.push.model.InsideNotificationItem;

/* JADX INFO: compiled from: DefaultNotifyDataAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h implements BaseNotifyDataAdapter {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f5632e;
    public static int f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Resources f5633a;
    public String b;
    public String c;
    public String d;

    public static boolean a(int i) {
        return (i == -1 || i == 0) ? false : true;
    }

    public static boolean a(String str) {
        if (Build.VERSION.SDK_INT < 26) {
            return false;
        }
        if (!TextUtils.isEmpty(str)) {
            return true;
        }
        o.d("DefaultNotifyDataAdapter", "systemVersion is not suit ");
        return false;
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final int getDefaultNotifyIcon() {
        if (a(f5632e)) {
            return f5632e;
        }
        String str = this.d;
        int iA = !a(str) ? -1 : a(str, "_notifyicon");
        f5632e = iA;
        if (a(iA)) {
            return f5632e;
        }
        for (String strSubstring = this.c; !TextUtils.isEmpty(strSubstring); strSubstring = strSubstring.substring(0, strSubstring.length() - 1)) {
            int identifier = this.f5633a.getIdentifier("vivo_push_rom" + strSubstring + "_notifyicon", "drawable", this.b);
            if (identifier > 0) {
                return identifier;
            }
        }
        return this.f5633a.getIdentifier("vivo_push_notifyicon", "drawable", this.b);
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final int getDefaultSmallIconId() {
        if (a(f)) {
            return f;
        }
        String str = this.d;
        int iA = !a(str) ? -1 : a(str, "_icon");
        f = iA;
        if (a(iA)) {
            return f;
        }
        for (String strSubstring = this.c; !TextUtils.isEmpty(strSubstring); strSubstring = strSubstring.substring(0, strSubstring.length() - 1)) {
            int identifier = this.f5633a.getIdentifier("vivo_push_rom" + strSubstring + "_icon", "drawable", this.b);
            if (identifier > 0) {
                return identifier;
            }
        }
        return this.f5633a.getIdentifier("vivo_push_icon", "drawable", this.b);
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final int getNotifyMode(InsideNotificationItem insideNotificationItem) {
        return Build.VERSION.SDK_INT >= 21 ? 2 : 1;
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final void init(Context context) {
        this.b = context.getPackageName();
        this.f5633a = context.getResources();
        this.c = j.a();
        this.d = Build.VERSION.RELEASE;
    }

    private int a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String[] strArrSplit = str.split("\\.");
            if (strArrSplit != null && strArrSplit.length > 0) {
                str = strArrSplit[0];
            }
            try {
                for (int i = Integer.parseInt(str); i > 0; i--) {
                    String str3 = "vivo_push_ard" + i + str2;
                    o.c("DefaultNotifyDataAdapter", "get notify icon : ".concat(String.valueOf(str3)));
                    int identifier = this.f5633a.getIdentifier(str3, "drawable", this.b);
                    if (identifier > 0) {
                        o.c("DefaultNotifyDataAdapter", "find notify icon : ".concat(String.valueOf(str3)));
                        return identifier;
                    }
                }
            } catch (Exception e2) {
                o.a("DefaultNotifyDataAdapter", e2);
            }
        }
        return -1;
    }
}
