package com.vivo.push.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import com.taobao.weex.common.Constants;

/* JADX INFO: compiled from: DefaultNotifyLayoutAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i implements BaseNotifyLayoutAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Resources f5634a;
    public String b;

    @Override // com.vivo.push.util.BaseNotifyLayoutAdapter
    public final int getNotificationLayout() {
        return this.f5634a.getIdentifier("push_notify", Constants.Name.LAYOUT, this.b);
    }

    @Override // com.vivo.push.util.BaseNotifyLayoutAdapter
    public final int getSuitIconId() {
        Resources resources;
        String str;
        String str2;
        if (j.g) {
            resources = this.f5634a;
            str = this.b;
            str2 = "notify_icon_rom30";
        } else if (j.f) {
            resources = this.f5634a;
            str = this.b;
            str2 = "notify_icon_rom20";
        } else {
            resources = this.f5634a;
            str = this.b;
            str2 = "notify_icon";
        }
        return resources.getIdentifier(str2, "id", str);
    }

    @Override // com.vivo.push.util.BaseNotifyLayoutAdapter
    public final int getTitleColor() {
        int iIntValue;
        try {
            iIntValue = ((Integer) z.a("com.android.internal.R$color", "vivo_notification_title_text_color")).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            iIntValue = 0;
        }
        if (iIntValue > 0) {
            return this.f5634a.getColor(iIntValue);
        }
        if (j.g) {
            return -1;
        }
        if (!j.f) {
            return -16777216;
        }
        if (j.g) {
            return Color.parseColor("#ff999999");
        }
        return -1;
    }

    @Override // com.vivo.push.util.BaseNotifyLayoutAdapter
    public final void init(Context context) {
        this.b = context.getPackageName();
        this.f5634a = context.getResources();
    }
}
