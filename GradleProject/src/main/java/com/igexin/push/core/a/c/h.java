package com.igexin.push.core.a.c;

import android.R;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.igexin.assist.sdk.AssistPushConsts;
import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import com.igexin.sdk.GetuiActivity;
import com.igexin.sdk.main.FeedbackImpl;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.list.template.CellDataManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

/* JADX INFO: loaded from: classes2.dex */
public class h implements PushMessageInterface {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3323a = com.igexin.push.core.b.f + h.class.getName();
    public static final int b = 131;
    public static final String c = "push_small";

    /* JADX INFO: renamed from: com.igexin.push.core.a.c.h$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ NotificationManager f3326a;
        public final /* synthetic */ int b;
        public final /* synthetic */ PushTaskBean c;

        public AnonymousClass2(NotificationManager notificationManager, int i, PushTaskBean pushTaskBean) {
            this.f3326a = notificationManager;
            this.b = i;
            this.c = pushTaskBean;
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                StatusBarNotification[] activeNotifications = this.f3326a.getActiveNotifications();
                boolean z = false;
                if (activeNotifications != null && activeNotifications.length > 0) {
                    int length = activeNotifications.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        if (activeNotifications[i].getId() == this.b) {
                            z = true;
                            break;
                        }
                        i++;
                    }
                }
                if (z) {
                    return;
                }
                String unused = h.f3323a;
                FeedbackImpl.getInstance().feedbackMessageAction(this.c, "10160", "show notification failed");
            } catch (Throwable th) {
                com.igexin.c.a.c.a.a(th);
            }
        }
    }

    public enum a {
        UNSET(0),
        BIG_IMAGE(1),
        LONG_TEXT(2),
        PURE_IMAGE(3);


        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f3328e;

        a(int i) {
            this.f3328e = i;
        }

        private int a() {
            return this.f3328e;
        }
    }

    public static int a(com.igexin.push.core.b.l lVar, boolean z) {
        int identifier = 0;
        if (!z) {
            if (!TextUtils.isEmpty(com.igexin.push.core.e.aL) && (identifier = com.igexin.push.core.e.l.getResources().getIdentifier(com.igexin.push.core.e.aL, "drawable", com.igexin.push.core.e.g)) == 0) {
                identifier = com.igexin.push.core.e.l.getResources().getIdentifier(com.igexin.push.core.e.aL, "mipmap", com.igexin.push.core.e.g);
            }
            int identifier2 = com.igexin.push.core.e.l.getResources().getIdentifier("push", "drawable", com.igexin.push.core.e.g);
            if (identifier2 == 0) {
                identifier2 = com.igexin.push.core.e.l.getResources().getIdentifier("push", "mipmap", com.igexin.push.core.e.g);
            }
            if (TextUtils.isEmpty(lVar.f) || com.igexin.push.core.b.m.equals(lVar.f)) {
                return identifier > 0 ? identifier : identifier2;
            }
            if (lVar.f.startsWith(CellDataManager.VIRTUAL_COMPONENT_SEPRATOR)) {
                String str = lVar.f;
                return str.substring(1, str.length()).endsWith("email") ? R.drawable.sym_action_email : R.drawable.sym_def_app_icon;
            }
            int identifier3 = com.igexin.push.core.e.l.getResources().getIdentifier(lVar.f, "drawable", com.igexin.push.core.e.g);
            if (identifier3 == 0) {
                identifier3 = com.igexin.push.core.e.l.getResources().getIdentifier(lVar.f, "mipmap", com.igexin.push.core.e.g);
            }
            return identifier3 > 0 ? identifier3 : identifier > 0 ? identifier : identifier2;
        }
        if (!TextUtils.isEmpty(com.igexin.push.core.e.aK)) {
            int identifier4 = com.igexin.push.core.e.l.getResources().getIdentifier(com.igexin.push.core.e.aK, "drawable", com.igexin.push.core.e.g);
            if (identifier4 == 0) {
                identifier4 = com.igexin.push.core.e.l.getResources().getIdentifier(com.igexin.push.core.e.aK, "mipmap", com.igexin.push.core.e.g);
            }
            if (identifier4 > 0) {
                return identifier4;
            }
        }
        int identifier5 = com.igexin.push.core.e.l.getResources().getIdentifier(c, "drawable", com.igexin.push.core.e.g);
        if (identifier5 == 0) {
            identifier5 = com.igexin.push.core.e.l.getResources().getIdentifier(c, "mipmap", com.igexin.push.core.e.g);
        }
        if (identifier5 != 0) {
            com.igexin.c.a.c.a.a(f3323a + "|push_small.png is set, use default push_small", new Object[0]);
            return identifier5;
        }
        com.igexin.c.a.c.a.a(f3323a, "|push_small.png is missing");
        com.igexin.c.a.c.a.a(f3323a + "|push_small.png is missing", new Object[0]);
        return com.igexin.push.core.e.l.getApplicationInfo().icon;
    }

    public static int a(String str) {
        int iCharAt = 0;
        for (int i = 0; i != str.length(); i++) {
            iCharAt = (iCharAt * b) + str.charAt(i);
        }
        if (iCharAt == Integer.MIN_VALUE) {
            iCharAt = 1;
        }
        return Math.abs(iCharAt);
    }

    public static Notification a(String str, int i, com.igexin.push.core.b.l lVar) {
        Notification.Builder builder;
        if (TextUtils.isEmpty(str) || com.igexin.push.core.e.aj.containsKey(str) || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        com.igexin.push.core.e.aj.put(str, new HashSet<>());
        PendingIntent pendingIntentB = b(str);
        if (Build.VERSION.SDK_INT >= 26) {
            builder = new Notification.Builder(com.igexin.push.core.e.l);
            NotificationManager notificationManager = (NotificationManager) com.igexin.push.core.e.l.getSystemService("notification");
            try {
                Constructor<?> constructor = Class.forName("android.app.NotificationChannel").getConstructor(String.class, CharSequence.class, Integer.TYPE);
                Class<?> cls = notificationManager.getClass();
                if (((Parcelable) cls.getMethod("getNotificationChannel", String.class).invoke(notificationManager, lVar.j)) == null) {
                    cls.getMethod("createNotificationChannel", Class.forName("android.app.NotificationChannel")).invoke(notificationManager, (Parcelable) constructor.newInstance(lVar.j, lVar.k, Integer.valueOf(lVar.l)));
                }
                builder.getClass().getMethod("setChannelId", String.class).invoke(builder, lVar.j);
            } catch (Throwable th) {
                com.igexin.c.a.c.a.a(th);
            }
        } else {
            builder = new Notification.Builder(com.igexin.push.core.e.l);
        }
        Notification notificationBuild = builder.setContentTitle("summary").setContentText("summary").setDeleteIntent(pendingIntentB).setAutoCancel(false).setGroup(str).setSmallIcon(i).setGroupSummary(true).build();
        if (Build.VERSION.SDK_INT < 21 || TextUtils.isEmpty(lVar.w)) {
            return notificationBuild;
        }
        builder.setCategory(lVar.w);
        return notificationBuild;
    }

    public static PendingIntent a(String str, int i, String str2, String str3, int i2, com.igexin.push.core.b.l lVar) {
        Intent intent = new Intent();
        intent.putExtra("taskid", str2);
        intent.putExtra("messageid", str3);
        intent.putExtra("appid", com.igexin.push.core.e.f3403a);
        intent.putExtra("actionid", lVar.getDoActionId());
        intent.putExtra("accesstoken", com.igexin.push.core.e.aC);
        intent.putExtra("notifID", i2);
        StringBuilder sb = new StringBuilder();
        sb.append(lVar.h);
        intent.putExtra("notifyStyle", sb.toString());
        intent.putExtra("id", lVar.y);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(lVar.C);
        intent.putExtra("bigStyle", sb2.toString());
        intent.putExtra("isFloat", false);
        intent.putExtra("checkpackage", com.igexin.push.core.e.l.getPackageName());
        intent.putExtra("feedbackid", lVar.getActionId().substring(lVar.getActionId().length() - 1));
        String str4 = lVar.f3353a;
        if (str4 == null) {
            str4 = "";
        }
        intent.putExtra("title", str4);
        String str5 = lVar.b;
        if (str5 == null) {
            str5 = "";
        }
        intent.putExtra("content", str5);
        intent.putExtra("redisplayFreq", i);
        intent.putExtra("groupId", str);
        String str6 = lVar.t;
        if (str6 == null) {
            str6 = "";
        }
        intent.putExtra("url", str6);
        String str7 = lVar.u;
        if (str7 == null) {
            str7 = "";
        }
        intent.putExtra(RemoteMessageConst.Notification.INTENT_URI, str7);
        String str8 = lVar.v;
        intent.putExtra(AssistPushConsts.MSG_TYPE_PAYLOAD, str8 != null ? str8 : "");
        int i3 = Build.VERSION.SDK_INT >= 23 ? 201326592 : 134217728;
        try {
            Intent intent2 = new Intent(com.igexin.push.core.e.l, (Class<?>) GetuiActivity.class);
            intent2.setFlags(268435456);
            intent2.putExtra("action", "com.igexin.action.notification.click");
            intent2.putExtra("broadcast_intent", intent);
            return PendingIntent.getActivity(com.igexin.push.core.e.l, new Random().nextInt(1000), intent2, i3);
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            Intent intent3 = new Intent("com.igexin.action.notification.click");
            intent3.setAction("com.igexin.action.notification.click");
            intent3.putExtra("action", "com.igexin.action.notification.click");
            intent3.putExtra("broadcast_intent", intent);
            return PendingIntent.getBroadcast(com.igexin.push.core.e.l, new Random().nextInt(1000), intent3, i3);
        }
    }

    public static PendingIntent a(String str, int i, String str2, String str3, String str4, com.igexin.push.core.b.l lVar) {
        try {
            Context context = com.igexin.push.core.e.l;
            com.igexin.push.core.a.b.d();
            Intent intent = new Intent(context, (Class<?>) com.igexin.push.core.a.b.a(com.igexin.push.core.e.l));
            intent.putExtra("taskid", str3);
            intent.putExtra("messageid", str4);
            intent.putExtra("appid", com.igexin.push.core.e.f3403a);
            intent.putExtra("appkey", str2);
            intent.putExtra("actionid", lVar.getDoActionId());
            StringBuilder sb = new StringBuilder();
            sb.append(lVar.h);
            intent.putExtra("notifyStyle", sb.toString());
            intent.putExtra("id", lVar.y);
            intent.putExtra("feedbackid", lVar.getActionId().substring(lVar.getActionId().length() + (-1)));
            intent.putExtra("action", "com.igexin.action.notification.delete");
            intent.putExtra("redisplayFreq", i);
            intent.putExtra("groupId", str);
            int i2 = 134217728;
            if (com.igexin.push.g.n.a(com.igexin.push.core.e.l) >= 31 && Build.VERSION.SDK_INT >= 30) {
                i2 = 201326592;
            }
            return PendingIntent.getService(com.igexin.push.core.e.l, new Random().nextInt(1000), intent, i2);
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            com.igexin.c.a.c.a.a(f3323a + "|getDelPendingIntent err：" + e2.toString(), new Object[0]);
            return null;
        }
    }

    public static Bitmap a(com.igexin.push.core.b.l lVar) {
        Bitmap bitmapA;
        String str = lVar.D;
        if (TextUtils.isEmpty(str)) {
            bitmapA = null;
        } else {
            bitmapA = com.igexin.push.g.l.a(str);
            String str2 = f3323a;
            StringBuilder sb = new StringBuilder("|use net logo bitmap is null = ");
            sb.append(bitmapA == null);
            com.igexin.c.a.c.a.a(str2, sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(f3323a);
            sb2.append("|use net logo bitmap is null = ");
            sb2.append(bitmapA == null);
            com.igexin.c.a.c.a.a(sb2.toString(), new Object[0]);
        }
        if (bitmapA == null) {
            return BitmapFactory.decodeResource(com.igexin.push.core.e.l.getResources(), a(lVar, false));
        }
        return bitmapA;
    }

    public static void a(Notification notification) {
        int i;
        if (com.igexin.push.g.a.b() || (i = Build.VERSION.SDK_INT) < 21 || i >= 24) {
            return;
        }
        try {
            Field field = Class.forName("com.android.internal.R$id").getField("right_icon");
            field.setAccessible(true);
            int i2 = field.getInt(null);
            if (notification.contentView != null) {
                notification.contentView.setViewVisibility(i2, 8);
                notification.bigContentView.setViewVisibility(i2, 8);
            }
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
    }

    public static void a(Notification notification, com.igexin.push.core.b.l lVar) {
        notification.defaults = 4;
        notification.ledARGB = -16711936;
        notification.ledOnMS = 1000;
        notification.ledOffMS = 3000;
        notification.flags = 1;
        notification.flags = lVar.f3354e ? 1 | 16 : 1 | 32;
        if (lVar.c) {
            notification.defaults |= 2;
        }
        if (lVar.d) {
            if (TextUtils.isEmpty(lVar.p)) {
                notification.defaults |= 1;
            } else {
                notification.sound = c(lVar.p);
            }
        }
        int i = lVar.o;
        if (i > 0) {
            com.igexin.push.g.d.a(i, false);
            com.igexin.push.g.d.c(lVar.o, false);
            com.igexin.push.g.d.b(lVar.o, false);
        }
        notification.icon = a(lVar, true);
    }

    public static void a(com.igexin.push.core.b.l lVar, String str, String str2, String str3, String str4) {
        com.igexin.push.core.l.a().a(str, str2, str3, str4, lVar.t, lVar.u, lVar.v);
    }

    /* JADX WARN: Removed duplicated region for block: B:147:0x033c A[Catch: all -> 0x035a, TRY_LEAVE, TryCatch #1 {all -> 0x035a, blocks: (B:125:0x02f2, B:127:0x02fa, B:129:0x0304, B:131:0x030a, B:133:0x030e, B:147:0x033c, B:137:0x0324, B:139:0x0327, B:141:0x032b, B:144:0x0336), top: B:161:0x02f2 }] */
    @android.annotation.SuppressLint({"WrongConstant"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(com.igexin.push.extension.mod.PushTaskBean r22, com.igexin.push.core.b.l r23, int r24) {
        /*
            Method dump skipped, instruction units count: 865
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.a.c.h.a(com.igexin.push.extension.mod.PushTaskBean, com.igexin.push.core.b.l, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final String str2, final String str3, final BaseActionBean baseActionBean, final int i) {
        String str4;
        String str5;
        String str6 = "width=" + com.igexin.push.core.e.k + "&height=" + com.igexin.push.core.e.j;
        if (str.contains(str6)) {
            str4 = str;
        } else {
            if (str.indexOf(Operators.CONDITION_IF_STRING) > 0) {
                str5 = str + "&" + str6;
            } else {
                str5 = str + Operators.CONDITION_IF_STRING + str6;
            }
            str4 = str5;
        }
        com.igexin.push.core.h.b bVar = new com.igexin.push.core.h.b(str4, str, str2, baseActionBean, i, new com.igexin.push.core.h.d() { // from class: com.igexin.push.core.a.c.h.1
            @Override // com.igexin.push.core.h.d
            public final void a() {
                BaseActionBean baseActionBean2 = baseActionBean;
                if (((com.igexin.push.core.b.l) baseActionBean2).H >= 3) {
                    ((com.igexin.push.core.b.l) baseActionBean2).F = true;
                }
                BaseActionBean baseActionBean3 = baseActionBean;
                if (((com.igexin.push.core.b.l) baseActionBean3).I >= 3) {
                    ((com.igexin.push.core.b.l) baseActionBean3).G = true;
                }
                BaseActionBean baseActionBean4 = baseActionBean;
                if (!((com.igexin.push.core.b.l) baseActionBean4).F || !((com.igexin.push.core.b.l) baseActionBean4).G) {
                    h.this.a(str, str2, str3, baseActionBean, i);
                    return;
                }
                if (com.igexin.push.core.e.a(str2) == 0) {
                    com.igexin.push.core.e.c.a();
                    com.igexin.push.core.e.c.a(com.igexin.push.core.b.ah, str2);
                    Map<String, PushTaskBean> map = com.igexin.push.core.e.ah;
                    com.igexin.push.core.a.b.d();
                    PushTaskBean pushTaskBean = map.get(com.igexin.push.core.a.b.a(str2, str3));
                    if (pushTaskBean != null) {
                        pushTaskBean.setStatus(com.igexin.push.core.b.ah);
                    }
                    com.igexin.push.core.a.b.d();
                    com.igexin.push.core.a.b.a(str2, str3, "1");
                }
            }

            @Override // com.igexin.push.core.h.d
            public final void a(BaseActionBean baseActionBean2) {
                int i2 = i;
                if (i2 == 2) {
                    ((com.igexin.push.core.b.l) baseActionBean).F = true;
                } else if (i2 == 8) {
                    ((com.igexin.push.core.b.l) baseActionBean).G = true;
                }
                com.igexin.push.core.b.l lVar = (com.igexin.push.core.b.l) baseActionBean2;
                if (lVar.F && lVar.G && com.igexin.push.core.e.a(str2) == 0) {
                    com.igexin.push.core.e.c.a();
                    com.igexin.push.core.e.c.a(com.igexin.push.core.b.ah, str2);
                    Map<String, PushTaskBean> map = com.igexin.push.core.e.ah;
                    com.igexin.push.core.a.b.d();
                    PushTaskBean pushTaskBean = map.get(com.igexin.push.core.a.b.a(str2, str3));
                    if (pushTaskBean != null) {
                        pushTaskBean.setStatus(com.igexin.push.core.b.ah);
                    }
                    com.igexin.push.core.a.b.d();
                    com.igexin.push.core.a.b.a(str2, str3, "1");
                }
            }
        });
        if (i == 2) {
            ((com.igexin.push.core.b.l) baseActionBean).H++;
        } else if (i == 8) {
            ((com.igexin.push.core.b.l) baseActionBean).I++;
        }
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.f.a.e(bVar), false, true);
    }

    @TargetApi(26)
    public static Notification.Builder b(com.igexin.push.core.b.l lVar) {
        Notification.Builder builder = new Notification.Builder(com.igexin.push.core.e.l);
        NotificationManager notificationManager = (NotificationManager) com.igexin.push.core.e.l.getSystemService("notification");
        try {
            Class<?> cls = Class.forName("android.app.NotificationChannel");
            Constructor<?> constructor = cls.getConstructor(String.class, CharSequence.class, Integer.TYPE);
            Class<?> cls2 = notificationManager.getClass();
            if (((Parcelable) cls2.getMethod("getNotificationChannel", String.class).invoke(notificationManager, lVar.j)) == null) {
                Parcelable parcelable = (Parcelable) constructor.newInstance(lVar.j, lVar.k, Integer.valueOf(lVar.l));
                Method method = cls2.getMethod("createNotificationChannel", Class.forName("android.app.NotificationChannel"));
                Method method2 = cls.getMethod("enableVibration", Boolean.TYPE);
                Method method3 = cls.getMethod("setSound", Uri.class, AudioAttributes.class);
                method2.invoke(parcelable, Boolean.valueOf(lVar.c));
                if (!lVar.d) {
                    method3.invoke(parcelable, null, null);
                } else if (!TextUtils.isEmpty(lVar.p)) {
                    method3.invoke(parcelable, c(lVar.p), null);
                }
                method.invoke(notificationManager, parcelable);
            }
            builder.getClass().getMethod("setChannelId", String.class).invoke(builder, lVar.j);
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
        return builder;
    }

    public static PendingIntent b(String str) {
        try {
            Context context = com.igexin.push.core.e.l;
            com.igexin.push.core.a.b.d();
            Intent intent = new Intent(context, (Class<?>) com.igexin.push.core.a.b.a(com.igexin.push.core.e.l));
            intent.putExtra("isSummary", true);
            intent.putExtra("action", "com.igexin.action.notification.delete");
            intent.putExtra("groupId", str);
            int i = 134217728;
            if (com.igexin.push.g.n.a(com.igexin.push.core.e.l) >= 31 && Build.VERSION.SDK_INT >= 30) {
                i = 201326592;
            }
            return PendingIntent.getService(com.igexin.push.core.e.l, new Random().nextInt(1000), intent, i);
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            return null;
        }
    }

    public static Uri c(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return Uri.parse("android.resource://" + com.igexin.push.core.e.l.getPackageName() + "/raw/" + str.toLowerCase());
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:159:0x0384 A[Catch: all -> 0x03a4, TRY_LEAVE, TryCatch #0 {all -> 0x03a4, blocks: (B:138:0x033a, B:140:0x0342, B:142:0x034c, B:144:0x0352, B:146:0x0357, B:159:0x0384, B:150:0x036d, B:152:0x0370, B:154:0x0374, B:157:0x037f), top: B:175:0x033a }] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x039f  */
    @Override // com.igexin.push.extension.mod.PushMessageInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean executeAction(com.igexin.push.extension.mod.PushTaskBean r25, com.igexin.push.extension.mod.BaseActionBean r26) {
        /*
            Method dump skipped, instruction units count: 1010
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.a.c.h.executeAction(com.igexin.push.extension.mod.PushTaskBean, com.igexin.push.extension.mod.BaseActionBean):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00ba  */
    @Override // com.igexin.push.extension.mod.PushMessageInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.igexin.push.extension.mod.BaseActionBean parseAction(org.json.JSONObject r19) {
        /*
            Method dump skipped, instruction units count: 720
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.a.c.h.parseAction(org.json.JSONObject):com.igexin.push.extension.mod.BaseActionBean");
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public PushMessageInterface.ActionPrepareState prepareExecuteAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        boolean z;
        if (!(baseActionBean instanceof com.igexin.push.core.b.l)) {
            return PushMessageInterface.ActionPrepareState.stop;
        }
        com.igexin.push.core.b.l lVar = (com.igexin.push.core.b.l) baseActionBean;
        String str = lVar.g;
        String str2 = lVar.A;
        String taskId = pushTaskBean.getTaskId();
        String messageId = pushTaskBean.getMessageId();
        boolean z2 = true;
        if (str2 != null) {
            String strA = com.igexin.push.g.j.a(str2);
            if (strA.equals("")) {
                lVar.G = false;
                z = true;
            } else {
                lVar.E = strA;
                z = false;
            }
        } else {
            z = false;
        }
        if (str != null) {
            String strA2 = com.igexin.push.g.j.a(str);
            if ("".equals(strA2)) {
                lVar.F = false;
            } else {
                lVar.D = strA2;
                z2 = false;
            }
        } else {
            z2 = false;
        }
        if (!z2 && !z) {
            return PushMessageInterface.ActionPrepareState.success;
        }
        if (z2) {
            a(str, taskId, messageId, baseActionBean, 2);
        }
        if (z) {
            a(str2, taskId, messageId, baseActionBean, 8);
        }
        return PushMessageInterface.ActionPrepareState.wait;
    }
}
