package com.getui.gtc.a;

import android.text.TextUtils;
import com.getui.gtc.base.util.ScheduleQueue;
import com.taobao.weex.el.parse.Operators;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicBoolean f2086a = new AtomicBoolean(false);

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? "" : str.contains("|") ? str.replace("|", Operators.DOLLAR_STR) : str;
    }

    public static void a() {
        if (f2086a.getAndSet(true)) {
            return;
        }
        b[] bVarArr = {new c(), new d(), new e(), new f()};
        for (int i = 0; i < 4; i++) {
            ScheduleQueue.getInstance().addSchedule(bVarArr[i], 10000L);
        }
    }
}
