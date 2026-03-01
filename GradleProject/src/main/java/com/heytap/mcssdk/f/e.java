package com.heytap.mcssdk.f;

import android.content.Context;
import android.content.Intent;
import com.heytap.msp.push.mode.MessageStat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2580a = "type";
    public static final String b = "count";
    public static final String c = "list";
    public static final int d = 1017;

    public static void a(Context context, MessageStat messageStat) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(messageStat);
        a(context, linkedList);
    }

    public static void a(Context context, List<MessageStat> list) {
        LinkedList linkedList = new LinkedList();
        linkedList.addAll(list);
        c.b("isSupportStatisticByMcs:" + a(context) + ",list size:" + linkedList.size());
        if (linkedList.size() <= 0 || !a(context)) {
            return;
        }
        b(context, linkedList);
    }

    public static boolean a(Context context) {
        String strL = com.heytap.mcssdk.d.k().l();
        return g.a(context, strL) && g.b(context, strL) >= 1017;
    }

    public static void b(Context context, List<MessageStat> list) {
        try {
            Intent intent = new Intent();
            intent.setAction(com.heytap.mcssdk.d.k().m());
            intent.setPackage(com.heytap.mcssdk.d.k().l());
            intent.putExtra("appPackage", context.getPackageName());
            intent.putExtra("type", com.heytap.mcssdk.a.b.o);
            intent.putExtra(b, list.size());
            ArrayList<String> arrayList = new ArrayList<>();
            Iterator<MessageStat> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toJsonObject());
            }
            intent.putStringArrayListExtra("list", arrayList);
            context.startService(intent);
        } catch (Exception e2) {
            c.e("statisticMessage--Exception" + e2.getMessage());
        }
    }
}
