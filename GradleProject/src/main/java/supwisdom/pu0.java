package supwisdom;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.hihonor.push.sdk.internal.HonorPushErrorEnum;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import supwisdom.rt0;

/* JADX INFO: loaded from: classes.dex */
public class pu0 implements Handler.Callback {
    public static final pu0 c = new pu0();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Handler f8830a;
    public final Map<mu0, a> b = new ConcurrentHashMap(5, 0.75f, 1);

    public static class b implements du0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public bu0<?> f8833a;

        public b(bu0<?> bu0Var) {
            this.f8833a = bu0Var;
        }
    }

    public pu0() {
        HandlerThread handlerThread = new HandlerThread("HonorApiManager");
        handlerThread.start();
        this.f8830a = new Handler(handlerThread.getLooper(), this);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        a aVar;
        int i = message.what;
        if (i != 1) {
            if (i != 2) {
                return false;
            }
            bu0 bu0Var = (bu0) message.obj;
            mu0 mu0Var = bu0Var.d;
            if (mu0Var != null && this.b.containsKey(mu0Var) && (aVar = this.b.get(mu0Var)) != null) {
                synchronized (aVar) {
                    String str = "resolveResult apiCall " + bu0Var.b;
                    aVar.b.remove(bu0Var);
                    if (aVar.f8831a.peek() == null || aVar.b.peek() == null) {
                        aVar.a();
                        pu0.this.b.remove(aVar.f8832e);
                    }
                }
            }
            return true;
        }
        bu0<?> bu0Var2 = (bu0) message.obj;
        mu0 mu0Var2 = bu0Var2.d;
        a aVar2 = this.b.get(mu0Var2);
        if (aVar2 == null) {
            Log.i("HonorApiManager", "connect and send request, create new connection manager.");
            aVar2 = new a(mu0Var2);
            this.b.put(mu0Var2, aVar2);
        }
        synchronized (aVar2) {
            tt0.a(pu0.this.f8830a);
            String str2 = "sendRequest " + bu0Var2.b;
            if (((xt0) aVar2.c).a()) {
                aVar2.a(bu0Var2);
            } else {
                aVar2.f8831a.add(bu0Var2);
                HonorPushErrorEnum honorPushErrorEnum = aVar2.d;
                if (honorPushErrorEnum == null || honorPushErrorEnum.getErrorCode() == 0) {
                    synchronized (aVar2) {
                        tt0.a(pu0.this.f8830a);
                        if (((xt0) aVar2.c).a()) {
                            Log.i("HonorApiManager", "client is connected");
                        } else {
                            if (((xt0) aVar2.c).f9809a.get() == 5) {
                                Log.i("HonorApiManager", "client is isConnecting");
                            } else {
                                xt0 xt0Var = (xt0) aVar2.c;
                                xt0Var.getClass();
                                Log.i("PushConnectionClient", " ==== PUSHSDK VERSION 70061303 ====");
                                int i2 = xt0Var.f9809a.get();
                                Log.i("PushConnectionClient", "enter connect, connection Status: " + i2);
                                if (i2 != 3 && i2 != 5 && i2 != 4) {
                                    gu0 gu0Var = gu0.b;
                                    int iB = tt0.b(gu0Var.a());
                                    if (iB == HonorPushErrorEnum.SUCCESS.getErrorCode()) {
                                        xt0Var.f9809a.set(5);
                                        ut0 ut0VarA = tt0.a(gu0Var.a());
                                        Log.i("PushConnectionClient", "enter bindCoreService.");
                                        au0 au0Var = new au0(ut0VarA);
                                        xt0Var.d = au0Var;
                                        au0Var.b = new vt0(xt0Var);
                                        if (ut0VarA.a()) {
                                            Intent intent = new Intent();
                                            String strC = au0Var.f6983a.c();
                                            String strB = au0Var.f6983a.b();
                                            String strD = au0Var.f6983a.d();
                                            if (TextUtils.isEmpty(strD)) {
                                                intent.setAction(strB);
                                                intent.setPackage(strC);
                                            } else {
                                                intent.setComponent(new ComponentName(strC, strD));
                                            }
                                            synchronized (au0.f6982e) {
                                                if (gu0Var.a().bindService(intent, au0Var, 1)) {
                                                    Handler handler = au0Var.c;
                                                    if (handler != null) {
                                                        handler.removeMessages(1001);
                                                    } else {
                                                        au0Var.c = new Handler(Looper.getMainLooper(), new zt0(au0Var));
                                                    }
                                                    au0Var.c.sendEmptyMessageDelayed(1001, 10000L);
                                                } else {
                                                    au0Var.d = true;
                                                    au0Var.a(8002001);
                                                }
                                            }
                                        } else {
                                            String str3 = "bind core is null : " + au0Var.f6983a;
                                            au0Var.a(8002004);
                                        }
                                    } else {
                                        xt0Var.a(iB);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    aVar2.a(aVar2.d);
                }
            }
        }
        return true;
    }

    public class a implements rt0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Queue<bu0<?>> f8831a = new LinkedList();
        public final Queue<bu0<?>> b = new LinkedList();
        public final rt0 c = new xt0(this);
        public HonorPushErrorEnum d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final mu0 f8832e;

        public a(mu0 mu0Var) {
            this.f8832e = mu0Var;
        }

        public final synchronized void a(HonorPushErrorEnum honorPushErrorEnum) {
            Log.i("HonorApiManager", "onConnectionFailed");
            tt0.a(pu0.this.f8830a);
            Iterator<bu0<?>> it = this.f8831a.iterator();
            while (it.hasNext()) {
                it.next().b(honorPushErrorEnum.toApiException(), null);
            }
            this.f8831a.clear();
            this.d = honorPushErrorEnum;
            a();
            pu0.this.b.remove(this.f8832e);
        }

        public final synchronized void b() {
            Log.i("HonorApiManager", "onConnected");
            tt0.a(pu0.this.f8830a);
            this.d = null;
            Iterator<bu0<?>> it = this.f8831a.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            this.f8831a.clear();
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x00a4 A[Catch: all -> 0x00ad, TRY_LEAVE, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x0013, B:7:0x001d, B:9:0x0028, B:12:0x002e, B:15:0x0035, B:19:0x0053, B:21:0x008f, B:25:0x00a4, B:24:0x0094, B:18:0x003b), top: B:35:0x0001, inners: #0, #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x008f A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final synchronized void a(supwisdom.bu0<?> r8) {
            /*
                r7 = this;
                monitor-enter(r7)
                java.util.Queue<supwisdom.bu0<?>> r0 = r7.b     // Catch: java.lang.Throwable -> Lad
                r0.add(r8)     // Catch: java.lang.Throwable -> Lad
                supwisdom.rt0 r0 = r7.c     // Catch: java.lang.Throwable -> Lad
                supwisdom.pu0$b r1 = new supwisdom.pu0$b     // Catch: java.lang.Throwable -> Lad
                r1.<init>(r8)     // Catch: java.lang.Throwable -> Lad
                com.hihonor.push.sdk.h0 r2 = new com.hihonor.push.sdk.h0     // Catch: java.lang.Throwable -> Lad
                r8.getClass()     // Catch: java.lang.Throwable -> Lad
                r3 = 0
                java.lang.Class r4 = r8.getClass()     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> Lad
                java.lang.reflect.Type r4 = r4.getGenericSuperclass()     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> Lad
                if (r4 == 0) goto L2b
                java.lang.reflect.ParameterizedType r4 = (java.lang.reflect.ParameterizedType) r4     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> Lad
                java.lang.reflect.Type[] r4 = r4.getActualTypeArguments()     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> Lad
                r5 = 0
                r4 = r4[r5]     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> Lad
                if (r4 == 0) goto L2b
                java.lang.Class r4 = (java.lang.Class) r4     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> Lad
                goto L2c
            L2b:
                r4 = r3
            L2c:
                if (r4 == 0) goto L53
                boolean r5 = r4.isPrimitive()     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> Lad
                if (r5 == 0) goto L35
                goto L53
            L35:
                java.lang.Object r3 = r4.newInstance()     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> Lad
                goto L53
            L3a:
                r4 = move-exception
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lad
                r5.<init>()     // Catch: java.lang.Throwable -> Lad
                java.lang.String r6 = "In newResponseInstance, instancing exception."
                r5.append(r6)     // Catch: java.lang.Throwable -> Lad
                java.lang.String r4 = r4.getMessage()     // Catch: java.lang.Throwable -> Lad
                r5.append(r4)     // Catch: java.lang.Throwable -> Lad
                java.lang.String r4 = r5.toString()     // Catch: java.lang.Throwable -> Lad
                supwisdom.wt0.a(r4)     // Catch: java.lang.Throwable -> Lad
            L53:
                r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> Lad
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lad
                r1.<init>()     // Catch: java.lang.Throwable -> Lad
                java.lang.String r3 = "start transport parse. "
                r1.append(r3)     // Catch: java.lang.Throwable -> Lad
                java.lang.String r3 = r8.b     // Catch: java.lang.Throwable -> Lad
                r1.append(r3)     // Catch: java.lang.Throwable -> Lad
                java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lad
                java.lang.String r3 = "IpcTransport"
                android.util.Log.i(r3, r1)     // Catch: java.lang.Throwable -> Lad
                supwisdom.xt0 r0 = (supwisdom.xt0) r0     // Catch: java.lang.Throwable -> Lad
                com.hihonor.push.framework.aidl.IPushInvoke r0 = r0.b     // Catch: java.lang.Throwable -> Lad
                java.lang.String r1 = r8.b     // Catch: java.lang.Throwable -> Lad
                com.hihonor.push.framework.aidl.entity.RequestHeader r3 = r8.f7102e     // Catch: java.lang.Throwable -> Lad
                com.hihonor.push.framework.aidl.IMessageEntity r8 = r8.c     // Catch: java.lang.Throwable -> Lad
                android.os.Bundle r4 = new android.os.Bundle     // Catch: java.lang.Throwable -> Lad
                r4.<init>()     // Catch: java.lang.Throwable -> Lad
                android.os.Bundle r5 = new android.os.Bundle     // Catch: java.lang.Throwable -> Lad
                r5.<init>()     // Catch: java.lang.Throwable -> Lad
                com.hihonor.push.framework.aidl.MessageCodec.formMessageEntity(r3, r4)     // Catch: java.lang.Throwable -> Lad
                com.hihonor.push.framework.aidl.MessageCodec.formMessageEntity(r8, r5)     // Catch: java.lang.Throwable -> Lad
                com.hihonor.push.framework.aidl.DataBuffer r8 = new com.hihonor.push.framework.aidl.DataBuffer     // Catch: java.lang.Throwable -> Lad
                r8.<init>(r1, r4, r5)     // Catch: java.lang.Throwable -> Lad
                if (r0 == 0) goto La4
                r0.call(r8, r2)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lad
                goto La4
            L93:
                r8 = move-exception
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lad
                r0.<init>()     // Catch: java.lang.Throwable -> Lad
                java.lang.String r1 = "transport remote error. "
                r0.append(r1)     // Catch: java.lang.Throwable -> Lad
                r0.append(r8)     // Catch: java.lang.Throwable -> Lad
                r0.toString()     // Catch: java.lang.Throwable -> Lad
            La4:
                java.lang.String r8 = "IpcTransport"
                java.lang.String r0 = "end transport parse."
                android.util.Log.i(r8, r0)     // Catch: java.lang.Throwable -> Lad
                monitor-exit(r7)
                return
            Lad:
                r8 = move-exception
                monitor-exit(r7)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.pu0.a.a(supwisdom.bu0):void");
        }

        public void a() {
            tt0.a(pu0.this.f8830a);
            xt0 xt0Var = (xt0) this.c;
            int i = xt0Var.f9809a.get();
            Log.i("PushConnectionClient", "enter disconnect, connection Status: " + i);
            if (i != 3) {
                if (i != 5) {
                    return;
                }
                xt0Var.f9809a.set(4);
            } else {
                au0 au0Var = xt0Var.d;
                if (au0Var != null) {
                    au0Var.b();
                }
                xt0Var.f9809a.set(1);
            }
        }
    }
}
