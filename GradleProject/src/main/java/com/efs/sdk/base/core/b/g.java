package com.efs.sdk.base.core.b;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.efs.sdk.base.core.b.a;
import com.efs.sdk.base.core.controller.ControllerCenter;
import com.efs.sdk.base.core.f.f;
import com.efs.sdk.base.core.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class g extends Handler implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ConcurrentHashMap<String, a> f1809a;
    public com.efs.sdk.base.core.e.a.d b;
    public com.efs.sdk.base.core.e.a.c c;

    public static class a extends FileOutputStream {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f1810a;
        public File b;

        public a(File file) {
            super(file);
            this.b = file;
            this.f1810a = System.currentTimeMillis();
        }
    }

    public g() {
        super(com.efs.sdk.base.core.util.concurrent.a.f1864a.getLooper());
        this.f1809a = new ConcurrentHashMap<>();
        this.b = new com.efs.sdk.base.core.e.a.d();
        this.c = new com.efs.sdk.base.core.e.a.c();
    }

    public static long b(String str) {
        Map<String, String> mapC = com.efs.sdk.base.core.config.a.c.a().c();
        String strConcat = "record_accumulation_time_".concat(String.valueOf(str));
        if (!mapC.containsKey(strConcat)) {
            return 60000L;
        }
        String str2 = mapC.get(strConcat);
        if (TextUtils.isEmpty(str2)) {
            return 60000L;
        }
        try {
            return Math.max(Long.parseLong(str2) * 1000, 1000L);
        } catch (Throwable th) {
            Log.e("efs.cache", "get cache interval error", th);
            return 60000L;
        }
    }

    private void c(String str) {
        a aVar;
        if (this.f1809a.containsKey(str) && (aVar = this.f1809a.get(str)) != null) {
            try {
                aVar.flush();
                com.efs.sdk.base.core.util.b.a(aVar);
                a(aVar.b);
            } catch (Throwable th) {
                try {
                    th.printStackTrace();
                    this.f1809a.remove(str);
                    if ("wa".equalsIgnoreCase(str)) {
                        return;
                    }
                    f.a.f1852a.c.c();
                } finally {
                    this.f1809a.remove(str);
                    if (!"wa".equalsIgnoreCase(str)) {
                        f.a.f1852a.c.c();
                    }
                }
            }
        }
    }

    @Override // com.efs.sdk.base.core.b.e
    public final void a(com.efs.sdk.base.core.d.b bVar) {
        Message messageObtain = Message.obtain();
        messageObtain.obj = bVar;
        messageObtain.what = 0;
        sendMessage(messageObtain);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i = message.what;
        if (i != 0) {
            if (i != 1) {
                return;
            }
            Object obj = message.obj;
            if (obj instanceof String) {
                c(obj.toString());
                return;
            }
            return;
        }
        com.efs.sdk.base.core.d.b bVar = (com.efs.sdk.base.core.d.b) message.obj;
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                a aVarB = b(bVar);
                if (aVarB == null) {
                    Log.w("efs.cache", "writer is null for type " + bVar.f1840a.f1838a);
                    return;
                }
                if (aVarB.getChannel().position() + ((long) bVar.c.length) > 819200) {
                    c(bVar.f1840a.f1838a);
                    aVarB = b(bVar);
                    if (aVarB == null) {
                        Log.w("efs.cache", "writer is null for type " + bVar.f1840a.f1838a);
                        return;
                    }
                }
                aVarB.write(Base64.encode(bVar.c, 11));
                aVarB.write("\n".getBytes());
                return;
            } catch (Throwable th) {
                Log.e("efs.cache", "cache file error", th);
            }
        }
    }

    @Override // com.efs.sdk.base.core.b.e
    public final boolean a(File file, com.efs.sdk.base.core.d.b bVar) {
        if (!bVar.b()) {
            a(file);
            return false;
        }
        if (!file.exists()) {
            return false;
        }
        bVar.d = file;
        bVar.c();
        bVar.b(1);
        return true;
    }

    private a b(com.efs.sdk.base.core.d.b bVar) {
        a aVar;
        Throwable th;
        a aVarPutIfAbsent;
        if (this.f1809a.containsKey(bVar.f1840a.f1838a)) {
            return this.f1809a.get(bVar.f1840a.f1838a);
        }
        File file = new File(com.efs.sdk.base.core.util.a.e(ControllerCenter.getGlobalEnvStruct().mAppContext, ControllerCenter.getGlobalEnvStruct().getAppid()), com.efs.sdk.base.core.util.b.a(bVar));
        try {
            aVar = new a(file);
        } catch (Throwable th2) {
            aVar = null;
            th = th2;
        }
        try {
            aVarPutIfAbsent = this.f1809a.putIfAbsent(bVar.f1840a.f1838a, aVar);
        } catch (Throwable th3) {
            th = th3;
            th.printStackTrace();
        }
        if (aVarPutIfAbsent != null) {
            com.efs.sdk.base.core.util.b.a(aVar);
            com.efs.sdk.base.core.util.b.b(file);
            return aVarPutIfAbsent;
        }
        Message messageObtain = Message.obtain();
        messageObtain.obj = bVar.f1840a.f1838a;
        messageObtain.what = 1;
        sendMessageDelayed(messageObtain, b(bVar.f1840a.f1838a));
        if (!"wa".equalsIgnoreCase(bVar.f1840a.f1838a)) {
            f.a.f1852a.c.b();
        }
        return aVar;
    }

    @Override // com.efs.sdk.base.core.b.e
    public final void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.obj = str;
        messageObtain.what = 1;
        sendMessage(messageObtain);
    }

    @Override // com.efs.sdk.base.core.b.e
    public final void a(File file) {
        byte[] bArr;
        com.efs.sdk.base.core.d.b bVarB = com.efs.sdk.base.core.util.b.b(file.getName());
        if (bVarB == null) {
            com.efs.sdk.base.core.b.a unused = a.b.f1805a;
            com.efs.sdk.base.core.b.a.b(file);
        } else if (!a(bVarB, file) || (bArr = bVarB.c) == null || bArr.length <= 0) {
            com.efs.sdk.base.core.b.a unused2 = a.b.f1805a;
            com.efs.sdk.base.core.b.a.b(file);
        } else {
            com.efs.sdk.base.core.util.b.a(new File(com.efs.sdk.base.core.util.a.f(ControllerCenter.getGlobalEnvStruct().mAppContext, ControllerCenter.getGlobalEnvStruct().getAppid()), com.efs.sdk.base.core.util.b.a(bVarB)), bVarB.c);
            com.efs.sdk.base.core.util.b.b(file);
        }
    }

    private boolean a(com.efs.sdk.base.core.d.b bVar, File file) {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        FileReader fileReader = null;
        try {
            FileReader fileReader2 = new FileReader(file);
            try {
                bufferedReader = new BufferedReader(fileReader2);
                try {
                    for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                        String strB = com.efs.sdk.base.core.util.b.b.b(line.getBytes());
                        if (!TextUtils.isEmpty(strB)) {
                            sb.append(strB);
                            sb.append("\n");
                        }
                    }
                    bVar.a(sb.toString().getBytes());
                    bVar.c();
                    this.c.a(bVar);
                    bVar.d = file;
                    com.efs.sdk.base.core.util.b.a(bufferedReader);
                    com.efs.sdk.base.core.util.b.a(fileReader2);
                    return true;
                } catch (Throwable th) {
                    th = th;
                    fileReader = fileReader2;
                    try {
                        Log.e("efs.cache", "local decode error", th);
                        com.efs.sdk.base.core.util.b.a(bufferedReader);
                        com.efs.sdk.base.core.util.b.a(fileReader);
                        return false;
                    } catch (Throwable th2) {
                        com.efs.sdk.base.core.util.b.a(bufferedReader);
                        com.efs.sdk.base.core.util.b.a(fileReader);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
        }
    }
}
