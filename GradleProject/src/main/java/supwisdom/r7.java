package supwisdom;

import android.app.Notification;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import supwisdom.q7;

/* JADX INFO: compiled from: NotificationCompatBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class r7 implements p7 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Notification.Builder f9018a;
    public final q7.d b;
    public RemoteViews c;
    public RemoteViews d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<Bundle> f9019e = new ArrayList();
    public final Bundle f = new Bundle();
    public int g;
    public RemoteViews h;

    public r7(q7.d dVar) {
        Icon icon;
        List<String> listA;
        this.b = dVar;
        Context context = dVar.f8885a;
        if (Build.VERSION.SDK_INT >= 26) {
            this.f9018a = new Notification.Builder(dVar.f8885a, dVar.K);
        } else {
            this.f9018a = new Notification.Builder(dVar.f8885a);
        }
        Notification notification = dVar.S;
        this.f9018a.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, dVar.i).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(dVar.f8886e).setContentText(dVar.f).setContentInfo(dVar.k).setContentIntent(dVar.g).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(dVar.h, (notification.flags & 128) != 0).setLargeIcon(dVar.j).setNumber(dVar.l).setProgress(dVar.t, dVar.u, dVar.v);
        if (Build.VERSION.SDK_INT < 21) {
            this.f9018a.setSound(notification.sound, notification.audioStreamType);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.f9018a.setSubText(dVar.q).setUsesChronometer(dVar.o).setPriority(dVar.m);
            Iterator<q7.a> it = dVar.b.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            Bundle bundle = dVar.D;
            if (bundle != null) {
                this.f.putAll(bundle);
            }
            if (Build.VERSION.SDK_INT < 20) {
                if (dVar.z) {
                    this.f.putBoolean("android.support.localOnly", true);
                }
                String str = dVar.w;
                if (str != null) {
                    this.f.putString("android.support.groupKey", str);
                    if (dVar.x) {
                        this.f.putBoolean("android.support.isGroupSummary", true);
                    } else {
                        this.f.putBoolean("android.support.useSideChannel", true);
                    }
                }
                String str2 = dVar.y;
                if (str2 != null) {
                    this.f.putString("android.support.sortKey", str2);
                }
            }
            this.c = dVar.H;
            this.d = dVar.I;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            this.f9018a.setShowWhen(dVar.n);
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 19 && i < 21 && (listA = a(a(dVar.c), dVar.V)) != null && !listA.isEmpty()) {
            this.f.putStringArray("android.people", (String[]) listA.toArray(new String[listA.size()]));
        }
        if (Build.VERSION.SDK_INT >= 20) {
            this.f9018a.setLocalOnly(dVar.z).setGroup(dVar.w).setGroupSummary(dVar.x).setSortKey(dVar.y);
            this.g = dVar.P;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.f9018a.setCategory(dVar.C).setColor(dVar.E).setVisibility(dVar.F).setPublicVersion(dVar.G).setSound(notification.sound, notification.audioAttributes);
            List listA2 = Build.VERSION.SDK_INT < 28 ? a(a(dVar.c), dVar.V) : dVar.V;
            if (listA2 != null && !listA2.isEmpty()) {
                Iterator it2 = listA2.iterator();
                while (it2.hasNext()) {
                    this.f9018a.addPerson((String) it2.next());
                }
            }
            this.h = dVar.J;
            if (dVar.d.size() > 0) {
                Bundle bundle2 = dVar.b().getBundle("android.car.EXTENSIONS");
                bundle2 = bundle2 == null ? new Bundle() : bundle2;
                Bundle bundle3 = new Bundle(bundle2);
                Bundle bundle4 = new Bundle();
                for (int i2 = 0; i2 < dVar.d.size(); i2++) {
                    bundle4.putBundle(Integer.toString(i2), s7.a(dVar.d.get(i2)));
                }
                bundle2.putBundle("invisible_actions", bundle4);
                bundle3.putBundle("invisible_actions", bundle4);
                dVar.b().putBundle("android.car.EXTENSIONS", bundle2);
                this.f.putBundle("android.car.EXTENSIONS", bundle3);
            }
        }
        if (Build.VERSION.SDK_INT >= 23 && (icon = dVar.U) != null) {
            this.f9018a.setSmallIcon(icon);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            this.f9018a.setExtras(dVar.D).setRemoteInputHistory(dVar.s);
            RemoteViews remoteViews = dVar.H;
            if (remoteViews != null) {
                this.f9018a.setCustomContentView(remoteViews);
            }
            RemoteViews remoteViews2 = dVar.I;
            if (remoteViews2 != null) {
                this.f9018a.setCustomBigContentView(remoteViews2);
            }
            RemoteViews remoteViews3 = dVar.J;
            if (remoteViews3 != null) {
                this.f9018a.setCustomHeadsUpContentView(remoteViews3);
            }
        }
        if (Build.VERSION.SDK_INT >= 26) {
            this.f9018a.setBadgeIconType(dVar.L).setSettingsText(dVar.r).setShortcutId(dVar.M).setTimeoutAfter(dVar.O).setGroupAlertBehavior(dVar.P);
            if (dVar.B) {
                this.f9018a.setColorized(dVar.A);
            }
            if (!TextUtils.isEmpty(dVar.K)) {
                this.f9018a.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (Build.VERSION.SDK_INT >= 28) {
            Iterator<u7> it3 = dVar.c.iterator();
            while (it3.hasNext()) {
                this.f9018a.addPerson(it3.next().h());
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f9018a.setAllowSystemGeneratedContextualActions(dVar.Q);
            this.f9018a.setBubbleMetadata(q7.c.a(dVar.R));
            z7 z7Var = dVar.N;
            if (z7Var != null) {
                this.f9018a.setLocusId(z7Var.c());
            }
        }
        if (dVar.T) {
            if (this.b.x) {
                this.g = 2;
            } else {
                this.g = 1;
            }
            this.f9018a.setVibrate(null);
            this.f9018a.setSound(null);
            int i3 = notification.defaults & (-2);
            notification.defaults = i3;
            int i4 = i3 & (-3);
            notification.defaults = i4;
            this.f9018a.setDefaults(i4);
            if (Build.VERSION.SDK_INT >= 26) {
                if (TextUtils.isEmpty(this.b.w)) {
                    this.f9018a.setGroup("silent");
                }
                this.f9018a.setGroupAlertBehavior(this.g);
            }
        }
    }

    public static List<String> a(List<String> list, List<String> list2) {
        if (list == null) {
            return list2;
        }
        if (list2 == null) {
            return list;
        }
        k4 k4Var = new k4(list.size() + list2.size());
        k4Var.addAll(list);
        k4Var.addAll(list2);
        return new ArrayList(k4Var);
    }

    public Notification b() {
        Bundle bundleA;
        RemoteViews remoteViewsD;
        RemoteViews remoteViewsB;
        q7.e eVar = this.b.p;
        if (eVar != null) {
            eVar.a(this);
        }
        RemoteViews remoteViewsC = eVar != null ? eVar.c(this) : null;
        Notification notificationC = c();
        if (remoteViewsC != null) {
            notificationC.contentView = remoteViewsC;
        } else {
            RemoteViews remoteViews = this.b.H;
            if (remoteViews != null) {
                notificationC.contentView = remoteViews;
            }
        }
        if (Build.VERSION.SDK_INT >= 16 && eVar != null && (remoteViewsB = eVar.b(this)) != null) {
            notificationC.bigContentView = remoteViewsB;
        }
        if (Build.VERSION.SDK_INT >= 21 && eVar != null && (remoteViewsD = this.b.p.d(this)) != null) {
            notificationC.headsUpContentView = remoteViewsD;
        }
        if (Build.VERSION.SDK_INT >= 16 && eVar != null && (bundleA = q7.a(notificationC)) != null) {
            eVar.a(bundleA);
        }
        return notificationC;
    }

    public Notification c() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            return this.f9018a.build();
        }
        if (i >= 24) {
            Notification notificationBuild = this.f9018a.build();
            if (this.g != 0) {
                if (notificationBuild.getGroup() != null && (notificationBuild.flags & 512) != 0 && this.g == 2) {
                    a(notificationBuild);
                }
                if (notificationBuild.getGroup() != null && (notificationBuild.flags & 512) == 0 && this.g == 1) {
                    a(notificationBuild);
                }
            }
            return notificationBuild;
        }
        if (i >= 21) {
            this.f9018a.setExtras(this.f);
            Notification notificationBuild2 = this.f9018a.build();
            RemoteViews remoteViews = this.c;
            if (remoteViews != null) {
                notificationBuild2.contentView = remoteViews;
            }
            RemoteViews remoteViews2 = this.d;
            if (remoteViews2 != null) {
                notificationBuild2.bigContentView = remoteViews2;
            }
            RemoteViews remoteViews3 = this.h;
            if (remoteViews3 != null) {
                notificationBuild2.headsUpContentView = remoteViews3;
            }
            if (this.g != 0) {
                if (notificationBuild2.getGroup() != null && (notificationBuild2.flags & 512) != 0 && this.g == 2) {
                    a(notificationBuild2);
                }
                if (notificationBuild2.getGroup() != null && (notificationBuild2.flags & 512) == 0 && this.g == 1) {
                    a(notificationBuild2);
                }
            }
            return notificationBuild2;
        }
        if (i >= 20) {
            this.f9018a.setExtras(this.f);
            Notification notificationBuild3 = this.f9018a.build();
            RemoteViews remoteViews4 = this.c;
            if (remoteViews4 != null) {
                notificationBuild3.contentView = remoteViews4;
            }
            RemoteViews remoteViews5 = this.d;
            if (remoteViews5 != null) {
                notificationBuild3.bigContentView = remoteViews5;
            }
            if (this.g != 0) {
                if (notificationBuild3.getGroup() != null && (notificationBuild3.flags & 512) != 0 && this.g == 2) {
                    a(notificationBuild3);
                }
                if (notificationBuild3.getGroup() != null && (notificationBuild3.flags & 512) == 0 && this.g == 1) {
                    a(notificationBuild3);
                }
            }
            return notificationBuild3;
        }
        if (i >= 19) {
            SparseArray<Bundle> sparseArrayA = s7.a(this.f9019e);
            if (sparseArrayA != null) {
                this.f.putSparseParcelableArray("android.support.actionExtras", sparseArrayA);
            }
            this.f9018a.setExtras(this.f);
            Notification notificationBuild4 = this.f9018a.build();
            RemoteViews remoteViews6 = this.c;
            if (remoteViews6 != null) {
                notificationBuild4.contentView = remoteViews6;
            }
            RemoteViews remoteViews7 = this.d;
            if (remoteViews7 != null) {
                notificationBuild4.bigContentView = remoteViews7;
            }
            return notificationBuild4;
        }
        if (i < 16) {
            return this.f9018a.getNotification();
        }
        Notification notificationBuild5 = this.f9018a.build();
        Bundle bundleA = q7.a(notificationBuild5);
        Bundle bundle = new Bundle(this.f);
        for (String str : this.f.keySet()) {
            if (bundleA.containsKey(str)) {
                bundle.remove(str);
            }
        }
        bundleA.putAll(bundle);
        SparseArray<Bundle> sparseArrayA2 = s7.a(this.f9019e);
        if (sparseArrayA2 != null) {
            q7.a(notificationBuild5).putSparseParcelableArray("android.support.actionExtras", sparseArrayA2);
        }
        RemoteViews remoteViews8 = this.c;
        if (remoteViews8 != null) {
            notificationBuild5.contentView = remoteViews8;
        }
        RemoteViews remoteViews9 = this.d;
        if (remoteViews9 != null) {
            notificationBuild5.bigContentView = remoteViews9;
        }
        return notificationBuild5;
    }

    public static List<String> a(List<u7> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<u7> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().g());
        }
        return arrayList;
    }

    @Override // supwisdom.p7
    public Notification.Builder a() {
        return this.f9018a;
    }

    public final void a(q7.a aVar) {
        Notification.Action.Builder builder;
        Bundle bundle;
        int i = Build.VERSION.SDK_INT;
        if (i < 20) {
            if (i >= 16) {
                this.f9019e.add(s7.a(this.f9018a, aVar));
                return;
            }
            return;
        }
        IconCompat iconCompatE = aVar.e();
        if (Build.VERSION.SDK_INT >= 23) {
            builder = new Notification.Action.Builder(iconCompatE != null ? iconCompatE.f() : null, aVar.i(), aVar.a());
        } else {
            builder = new Notification.Action.Builder(iconCompatE != null ? iconCompatE.a() : 0, aVar.i(), aVar.a());
        }
        if (aVar.f() != null) {
            for (RemoteInput remoteInput : v7.a(aVar.f())) {
                builder.addRemoteInput(remoteInput);
            }
        }
        if (aVar.d() != null) {
            bundle = new Bundle(aVar.d());
        } else {
            bundle = new Bundle();
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.b());
        if (Build.VERSION.SDK_INT >= 24) {
            builder.setAllowGeneratedReplies(aVar.b());
        }
        bundle.putInt("android.support.action.semanticAction", aVar.g());
        if (Build.VERSION.SDK_INT >= 28) {
            builder.setSemanticAction(aVar.g());
        }
        if (Build.VERSION.SDK_INT >= 29) {
            builder.setContextual(aVar.j());
        }
        bundle.putBoolean("android.support.action.showsUserInterface", aVar.h());
        builder.addExtras(bundle);
        this.f9018a.addAction(builder.build());
    }

    public final void a(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        int i = notification.defaults & (-2);
        notification.defaults = i;
        notification.defaults = i & (-3);
    }
}
