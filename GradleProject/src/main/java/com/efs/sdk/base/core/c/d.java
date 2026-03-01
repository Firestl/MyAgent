package com.efs.sdk.base.core.c;

import android.os.Handler;
import android.os.Message;
import com.efs.sdk.base.core.b.a;
import com.efs.sdk.base.core.b.h;
import com.efs.sdk.base.core.c.f;
import com.efs.sdk.base.core.config.GlobalInfoManager;
import com.efs.sdk.base.core.controller.ControllerCenter;
import com.efs.sdk.base.core.f.f;
import com.efs.sdk.base.core.util.Log;
import com.efs.sdk.base.core.util.NetworkUtil;
import com.efs.sdk.base.core.util.concurrent.WorkThreadUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class d extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1814a;
    public c b;
    public c c;
    public List<String> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AtomicInteger f1815e;
    public com.efs.sdk.base.core.b.f f;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f1816a = new d(0);
    }

    public /* synthetic */ d(byte b) {
        this();
    }

    public static d a() {
        return a.f1816a;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        List<File> listEmptyList;
        super.handleMessage(message);
        if (f.a.f1820a.a()) {
            int i = message.what;
            if (i != 0) {
                if (i != 1) {
                    return;
                }
                Object obj = message.obj;
                if (obj != null) {
                    this.d.remove(obj.toString());
                }
                int iIncrementAndGet = message.arg1 != 0 ? this.f1815e.incrementAndGet() : 0;
                if (this.d.isEmpty()) {
                    if (iIncrementAndGet < 5) {
                        sendEmptyMessage(0);
                        return;
                    }
                    this.f1815e.set(0);
                    sendEmptyMessageDelayed(0, ControllerCenter.getGlobalEnvStruct().getLogSendDelayMills());
                    Log.i("efs.send_log", "request error cnt gt 5, next request delay 10s");
                    return;
                }
                return;
            }
            String netStatus = GlobalInfoManager.getInstance().getNetStatus();
            if ("denied".equalsIgnoreCase(netStatus) || NetworkUtil.NETWORK_CLASS_DISCONNECTED.equalsIgnoreCase(netStatus)) {
                Log.i("efs.send_log", "log cann't be send because net status is ".concat(String.valueOf(netStatus)));
                sendEmptyMessageDelayed(0, ControllerCenter.getGlobalEnvStruct().getLogSendIntervalMills());
                return;
            }
            List<com.efs.sdk.base.core.d.b> listEmptyList2 = Collections.emptyList();
            try {
                com.efs.sdk.base.core.b.a aVar = a.b.f1805a;
                int i2 = this.f1814a;
                com.efs.sdk.base.core.b.f fVar = this.f;
                aVar.a();
                aVar.a();
                File fileF = com.efs.sdk.base.core.util.a.f(ControllerCenter.getGlobalEnvStruct().mAppContext, ControllerCenter.getGlobalEnvStruct().getAppid());
                if (fileF.exists()) {
                    List<File> listD = com.efs.sdk.base.core.util.b.d(fileF);
                    if (aVar.b) {
                        com.efs.sdk.base.core.f.f fVar2 = f.a.f1852a;
                        int size = listD.size();
                        if (fVar2.b != null && ControllerCenter.getGlobalEnvStruct().isEnableWaStat()) {
                            com.efs.sdk.base.core.f.b bVar = new com.efs.sdk.base.core.f.b("efs_core", "log_lag", fVar2.f1851a.c);
                            bVar.put("cnt", Integer.valueOf(size));
                            fVar2.b.send(bVar);
                        }
                        aVar.b = false;
                    }
                    Collections.sort(listD, aVar.d);
                    ArrayList arrayList = new ArrayList(i2);
                    for (int size2 = listD.size() - 1; size2 >= 0 && arrayList.size() < i2; size2--) {
                        File file = listD.get(size2);
                        if (file.exists() && (fVar == null || !fVar.a(file))) {
                            arrayList.add(file);
                        }
                    }
                    listEmptyList = arrayList;
                } else {
                    listEmptyList = Collections.emptyList();
                }
                ArrayList arrayList2 = new ArrayList(i2);
                for (File file2 : listEmptyList) {
                    com.efs.sdk.base.core.d.b bVarA = aVar.a(file2);
                    if (bVarA == null) {
                        Log.w("efs.cache", "file upload error, name is " + file2.getName());
                    } else {
                        arrayList2.add(bVarA);
                    }
                }
                listEmptyList2 = arrayList2;
            } catch (Throwable unused) {
            }
            for (com.efs.sdk.base.core.d.b bVar2 : listEmptyList2) {
                if ("wa".equals(bVar2.f1840a.f1838a) || b.a().a(bVar2.f1840a.f1838a, bVar2.a())) {
                    c cVar = this.b;
                    if ("wa".equals(bVar2.f1840a.f1838a)) {
                        cVar = this.c;
                    }
                    String string = UUID.randomUUID().toString();
                    this.d.add(string);
                    if (WorkThreadUtil.submit(new e(bVar2, cVar, string)) == null) {
                        a(string, -1);
                    }
                }
            }
            if (this.d.size() <= 0) {
                sendEmptyMessageDelayed(0, ControllerCenter.getGlobalEnvStruct().getLogSendIntervalMills());
            }
        }
    }

    public d() {
        super(com.efs.sdk.base.core.util.concurrent.a.f1864a.getLooper());
        this.f1814a = 5;
        this.d = new ArrayList();
        this.f1815e = new AtomicInteger(0);
        this.b = new com.efs.sdk.base.core.c.a();
        this.c = new com.efs.sdk.base.core.f.e();
        this.f = new h();
    }

    public final void a(Object obj, int i) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.obj = obj;
        messageObtain.arg1 = i;
        sendMessage(messageObtain);
    }
}
