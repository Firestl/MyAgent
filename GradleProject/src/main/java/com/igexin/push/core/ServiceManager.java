package com.igexin.push.core;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Pair;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.util.CommonUtil;
import com.igexin.push.g.h;
import com.igexin.push.g.o;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.IPushCore;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushService;
import com.igexin.sdk.main.PushCoreLoader;
import com.taobao.weex.bridge.WXBridgeManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class ServiceManager {
    public static Context b = null;
    public static final String c = "ServiceManager";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IPushCore f3294a;
    public final AtomicBoolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3295e;
    public Class f;
    public Class g;
    public AtomicBoolean h;
    public final ServiceConnection i;
    public Pair<Integer, String> initType;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final ServiceManager f3305a = new ServiceManager(0);
    }

    public ServiceManager() {
        this.d = new AtomicBoolean(false);
        this.h = new AtomicBoolean(false);
        this.i = new ServiceConnection() { // from class: com.igexin.push.core.ServiceManager.7
            @Override // android.content.ServiceConnection
            public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            }

            @Override // android.content.ServiceConnection
            public final void onServiceDisconnected(ComponentName componentName) {
            }
        };
    }

    public /* synthetic */ ServiceManager(byte b2) {
        this();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(Intent intent, int i, int i2) {
        if (this.f3294a == null) {
            return 2;
        }
        com.igexin.c.a.c.a.a("ServiceManager|inInit = true, call onServiceStartCommand...", new Object[0]);
        return this.f3294a.onServiceStartCommand(intent, i, i2);
    }

    public static void a() {
        com.igexin.c.a.c.a.a("ServiceManager|onLowMemory...", new Object[0]);
    }

    private void a(final Service service) {
        com.igexin.c.a.c.a.a("ServiceManager|startGTCore ++++", new Object[0]);
        if (!com.igexin.push.g.g.a()) {
            com.igexin.b.a.a().f3132a.execute(new h.AnonymousClass1(service, new h.a() { // from class: com.igexin.push.core.ServiceManager.2
                @Override // com.igexin.push.g.h.a
                public final void a(boolean z) {
                    com.igexin.c.a.c.a.a(ServiceManager.c, "load encrypt error, report bi result = " + z + " ###########");
                    com.igexin.c.a.c.a.a("ServiceManager|load encrypt error, report bi result = " + z + " ###########", new Object[0]);
                    service.stopSelf();
                }
            }));
            return;
        }
        PushCoreLoader.getInstance().init(service);
        this.f3294a = PushCoreLoader.getInstance().getPushCore();
        if (PushCoreLoader.getInstance().getGtcCore() != null) {
            PushCoreLoader.getInstance().getGtcCore().start(service);
        }
        IPushCore iPushCore = this.f3294a;
        if (iPushCore != null) {
            iPushCore.start(service);
        }
    }

    public static void a(Context context) {
        b = context.getApplicationContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(Service service) {
        com.igexin.c.a.c.a.a("ServiceManager|start by system ####", new Object[0]);
        if (!g(service)) {
            service.stopSelf();
            return 2;
        }
        com.igexin.c.a.c.a.a("ServiceManager|intent = null", new Object[0]);
        if (!this.d.getAndSet(true)) {
            a(service);
        }
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(Service service, Intent intent, int i, int i2) {
        com.igexin.c.a.c.a.a("ServiceManager|start from initialize...", new Object[0]);
        com.igexin.c.a.c.a.d.a().a("[ServiceManager] ServiceManager start from initialize...");
        a(service);
        IPushCore iPushCore = this.f3294a;
        if (iPushCore != null) {
            return iPushCore.onServiceStartCommand(intent, i, i2);
        }
        return 2;
    }

    private void b() {
        com.igexin.c.a.c.a.a("ServiceManager|onDestroy...", new Object[0]);
        IPushCore iPushCore = this.f3294a;
        if (iPushCore != null) {
            iPushCore.onServiceDestroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c(Service service, Intent intent, int i, int i2) {
        com.igexin.c.a.c.a.a("ServiceManager|start from guard...", new Object[0]);
        if (!g(service)) {
            this.d.set(false);
            service.stopSelf();
            return 2;
        }
        a(service);
        IPushCore iPushCore = this.f3294a;
        if (iPushCore != null) {
            return iPushCore.onServiceStartCommand(intent, i, i2);
        }
        return 2;
    }

    private boolean c(final Context context, final Intent intent) {
        com.igexin.b.a.a().a("pushservice").execute(new Runnable() { // from class: com.igexin.push.core.ServiceManager.6
            private void a() {
                try {
                    com.igexin.c.a.c.a.a("ServiceManager|startPService by bind", new Object[0]);
                    intent.setType("PB-" + System.nanoTime());
                    intent.setClass(context, ServiceManager.this.b(context));
                    context.getApplicationContext().bindService(intent, ServiceManager.this.i, 1);
                } catch (Throwable th) {
                    com.igexin.c.a.c.a.b(ServiceManager.c, "startPService exception = " + th.toString());
                    com.igexin.c.a.c.a.a(th);
                }
            }

            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (Build.VERSION.SDK_INT < 26 || !com.igexin.push.g.c.g()) {
                        context.getApplicationContext().startService(intent);
                    } else {
                        a();
                    }
                } catch (Throwable th) {
                    com.igexin.c.a.c.a.b(ServiceManager.c, "startPService exception = " + th.toString());
                    com.igexin.c.a.c.a.a(th);
                    if (th instanceof IllegalStateException) {
                        a();
                    }
                }
            }
        });
        return true;
    }

    public static String d(Context context) {
        return (String) o.b(context, "ua", "");
    }

    public static String e(Context context) {
        return (String) o.b(context, o.f3602a, "");
    }

    public static boolean g(Context context) {
        return !com.igexin.push.g.j.a(context);
    }

    public static ServiceManager getInstance() {
        return a.f3305a;
    }

    public final int a(final Service service, final Intent intent, final int i, final int i2) {
        final Context applicationContext = service.getApplicationContext();
        com.igexin.b.a.a().a("pushservice").execute(new Runnable() { // from class: com.igexin.push.core.ServiceManager.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    com.igexin.c.a.c.a.a(ServiceManager.c, "| PushService startCommand... time = " + System.currentTimeMillis() + " intent = " + intent);
                    if (intent == null) {
                        ServiceManager.this.initType = Pair.create(0, null);
                        ServiceManager.this.b(service);
                        return;
                    }
                    o.a(applicationContext, intent);
                    Bundle extras = intent.getExtras();
                    if (extras != null) {
                        for (String str : extras.keySet()) {
                            extras.get(str);
                            com.igexin.c.a.c.a.a("ServiceManager|key [" + str + "]: " + extras.get(str), new Object[0]);
                        }
                    } else {
                        com.igexin.c.a.c.a.a("ServiceManager|no extras", new Object[0]);
                    }
                    String stringExtra = intent.getStringExtra("action");
                    boolean booleanExtra = intent.getBooleanExtra("isGuard", false);
                    boolean booleanExtra2 = intent.getBooleanExtra("isGuardForce", false);
                    if (ServiceManager.this.d.getAndSet(true)) {
                        if (((Integer) ServiceManager.this.initType.first).equals(0) && booleanExtra2) {
                            ServiceManager.this.initType = Pair.create(1, intent.getStringExtra("pkg"));
                            o.a(applicationContext, o.f, ServiceManager.this.initType.first);
                        }
                        ServiceManager.this.a(intent, i, i2);
                        return;
                    }
                    if (booleanExtra) {
                        ServiceManager.this.initType = Pair.create(1, intent.getStringExtra("pkg"));
                        ServiceManager.this.c(service, intent, i, i2);
                        o.a(applicationContext, o.f, ServiceManager.this.initType.first);
                        return;
                    }
                    ServiceManager.this.initType = Pair.create(0, null);
                    if (!(true ^ com.igexin.push.g.j.a(applicationContext)) && !PushConsts.ACTION_SERVICE_INITIALIZE.equals(stringExtra)) {
                        ServiceManager.this.d.set(false);
                        service.stopSelf();
                        return;
                    }
                    ServiceManager serviceManager = ServiceManager.this;
                    Service service2 = service;
                    Intent intent2 = intent;
                    int i3 = i;
                    int i4 = i2;
                    serviceManager.b(service2, intent2, i3, i4);
                    o.a(applicationContext, o.f, ServiceManager.this.initType.first);
                } catch (Throwable th) {
                    com.igexin.c.a.c.a.a(th);
                }
            }
        });
        return 2;
    }

    public final IBinder a(Service service, Intent intent) {
        com.igexin.c.a.c.a.a("ServiceManager|onBind...", new Object[0]);
        a(service, intent, 0, 0);
        IPushCore iPushCore = this.f3294a;
        if (iPushCore != null) {
            return iPushCore.onServiceBind(intent);
        }
        return null;
    }

    public final void a(final Activity activity) {
        try {
            Context applicationContext = activity.getApplicationContext();
            b = applicationContext;
            GtcProvider.setContext(applicationContext);
            final long jCurrentTimeMillis = System.currentTimeMillis();
            final String name = activity.getClass().getName();
            final Intent intent = activity.getIntent();
            com.igexin.b.a.a().a("gd").execute(new Runnable() { // from class: com.igexin.push.core.ServiceManager.4
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        if (!com.igexin.push.g.j.a(ServiceManager.b)) {
                            if (intent != null && intent.getExtras() != null) {
                                try {
                                    Bundle extras = intent.getExtras();
                                    IBinder binder = extras.getBinder(WXBridgeManager.METHOD_CALLBACK);
                                    if (binder != null) {
                                        new com.igexin.a.b(binder).a(new Bundle());
                                        extras.remove(WXBridgeManager.METHOD_CALLBACK);
                                    }
                                } catch (Throwable th) {
                                    com.igexin.c.a.c.a.a(th);
                                }
                            }
                            long jA = com.igexin.push.core.d.d.a().a(com.igexin.push.core.d.d.b, 0);
                            long jA2 = com.igexin.push.core.d.d.a().a(com.igexin.push.core.d.d.c, 0);
                            boolean z = (jA == 0 || jA2 == 0 || jA2 >= jA) ? false : true;
                            if (jA != 0 && (jCurrentTimeMillis - jA < 5000 || jCurrentTimeMillis - jA2 < 5000)) {
                                z = true;
                            }
                            Activity activity2 = activity;
                            com.igexin.push.core.a.b.d();
                            Intent intent2 = new Intent(activity2, (Class<?>) com.igexin.push.core.a.b.a((Context) activity));
                            if (intent != null && intent.hasExtra("action") && intent.hasExtra("isSlave")) {
                                intent2.putExtra("action", intent.getStringExtra("action"));
                                intent2.putExtra("isSlave", intent.getBooleanExtra("isSlave", false));
                                if (intent.hasExtra("op_app")) {
                                    intent2.putExtra("op_app", intent.getStringExtra("op_app"));
                                }
                            }
                            if (intent != null && intent.hasExtra("pkg")) {
                                intent2.putExtra("pkg", intent.getStringExtra("pkg"));
                            }
                            intent2.putExtra("isGuard", true);
                            intent2.putExtra("isGuardForce", z);
                            ServiceManager.this.b(activity, intent2);
                            com.igexin.c.a.c.a.a("ServiceManager|start PushService from da " + name, new Object[0]);
                        }
                    } finally {
                        try {
                        } finally {
                        }
                    }
                }
            });
        } catch (Throwable th) {
            activity.finish();
            com.igexin.c.a.c.a.a(th);
        }
    }

    public final void a(final Context context, final Intent intent) {
        try {
            Context applicationContext = context.getApplicationContext();
            b = applicationContext;
            GtcProvider.setContext(applicationContext);
            final long jCurrentTimeMillis = System.currentTimeMillis();
            com.igexin.b.a.a().a("gd").execute(new Runnable() { // from class: com.igexin.push.core.ServiceManager.5
                @Override // java.lang.Runnable
                public final void run() {
                    if (com.igexin.push.g.j.a(ServiceManager.b)) {
                        return;
                    }
                    Intent intent2 = intent;
                    if (intent2 != null && intent2.getExtras() != null) {
                        try {
                            Bundle extras = intent.getExtras();
                            IBinder binder = extras.getBinder(WXBridgeManager.METHOD_CALLBACK);
                            if (binder != null) {
                                new com.igexin.a.b(binder).a(new Bundle());
                                extras.remove(WXBridgeManager.METHOD_CALLBACK);
                            }
                        } catch (Throwable th) {
                            com.igexin.c.a.c.a.a(th);
                        }
                    }
                    long jA = com.igexin.push.core.d.d.a().a(com.igexin.push.core.d.d.b, 0);
                    long jA2 = com.igexin.push.core.d.d.a().a(com.igexin.push.core.d.d.c, 0);
                    boolean z = (jA == 0 || jA2 == 0 || jA2 >= jA) ? false : true;
                    if (jA != 0 && jCurrentTimeMillis - jA < 5000) {
                        z = true;
                    }
                    Context context2 = context;
                    com.igexin.push.core.a.b.d();
                    Intent intent3 = new Intent(context2, (Class<?>) com.igexin.push.core.a.b.a(context));
                    Intent intent4 = intent;
                    if (intent4 != null && intent4.hasExtra("action") && intent.hasExtra("isSlave")) {
                        intent3.putExtra("action", intent.getStringExtra("action"));
                        intent3.putExtra("isSlave", intent.getBooleanExtra("isSlave", false));
                        if (intent.hasExtra("op_app")) {
                            intent3.putExtra("op_app", intent.getStringExtra("op_app"));
                        }
                    }
                    Intent intent5 = intent;
                    if (intent5 != null && intent5.hasExtra("pkg")) {
                        intent3.putExtra("pkg", intent.getStringExtra("pkg"));
                    }
                    intent3.putExtra("isGuard", true);
                    intent3.putExtra("isGuardForce", z);
                    ServiceManager.this.b(context, intent3);
                    com.igexin.c.a.c.a.a("ServiceManager|start PushService from da", new Object[0]);
                }
            });
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
    }

    public final Class b(Context context) {
        ComponentName componentName;
        try {
            if (this.g == null) {
                Class cls = (Class) com.igexin.push.g.d.a(context, PushService.class).second;
                this.g = cls;
                if (cls == null) {
                    String str = (String) o.b(context, o.b, "");
                    if (!TextUtils.isEmpty(str)) {
                        this.g = Class.forName(str);
                    }
                }
                if (this.g == null) {
                    this.g = PushService.class;
                }
            }
            componentName = new ComponentName(context, (Class<?>) this.g);
        } catch (Throwable th) {
            try {
                this.g = PushService.class;
                com.igexin.c.a.c.a.a(th);
                componentName = new ComponentName(context, (Class<?>) this.g);
            } catch (Throwable th2) {
                context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, (Class<?>) this.g), 1, 1);
                throw th2;
            }
        }
        context.getPackageManager().setComponentEnabledSetting(componentName, 1, 1);
        return this.g;
    }

    public final boolean b(Context context, Intent intent) {
        return c(context.getApplicationContext(), intent);
    }

    public final Class c(Context context) {
        Class cls = this.f;
        if (cls != null) {
            return cls;
        }
        Class cls2 = (Class) com.igexin.push.g.d.a(context, GTIntentService.class).second;
        this.f = cls2;
        if (cls2 != null) {
            return cls2;
        }
        try {
            String str = (String) o.b(context, o.c, "");
            if (!TextUtils.isEmpty(str)) {
                Class<?> cls3 = Class.forName(str);
                this.f = cls3;
                return cls3;
            }
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
        return this.f;
    }

    public final void f(Context context) {
        b = context.getApplicationContext();
        final boolean zIsMainProcess = CommonUtil.isMainProcess();
        if (this.h.getAndSet(true)) {
            final long jCurrentTimeMillis = System.currentTimeMillis();
            com.igexin.b.a.a().a("gd").execute(new Runnable() { // from class: com.igexin.push.core.ServiceManager.3
                @Override // java.lang.Runnable
                public final void run() {
                    com.igexin.push.core.d.d.a().a(zIsMainProcess ? com.igexin.push.core.d.d.b : com.igexin.push.core.d.d.c, Long.valueOf(jCurrentTimeMillis));
                    StringBuilder sb = new StringBuilder("init in ");
                    sb.append(zIsMainProcess ? "main process " : "other process ");
                    sb.append(jCurrentTimeMillis);
                    com.igexin.c.a.c.a.b(ServiceManager.c, sb.toString());
                }
            });
        }
    }
}
