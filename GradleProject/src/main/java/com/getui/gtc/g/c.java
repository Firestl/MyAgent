package com.getui.gtc.g;

import android.text.TextUtils;
import com.getui.gtc.api.SdkInfo;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.util.ScheduleQueue;
import com.getui.gtc.e.c;
import com.getui.gtc.entity.a;
import com.getui.gtc.f.b;
import com.getui.gtc.f.d;
import com.getui.gtc.i.c.a;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2230a;
    public String b;
    public final Map<String, a.C0044a> c;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f2236a = new c(0);
    }

    public c() {
        this.c = new HashMap();
        try {
            this.f2230a = GtcProvider.context().getFilesDir().getAbsolutePath();
            File file = new File(this.f2230a);
            if (!file.exists()) {
                file.mkdirs();
            }
            this.b = this.f2230a + File.separator + "libs";
            File file2 = new File(this.b);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            HashSet<String> hashSet = new HashSet(c.a.f2213a.f2212a.l);
            for (String str : hashSet) {
                if (a(str)) {
                    c.a.f2213a.f2212a.e(str);
                    com.getui.gtc.i.c.a.a("remove: ".concat(String.valueOf(hashSet)));
                }
            }
            com.getui.gtc.dyc.b.a.a(GtcProvider.context(), new d() { // from class: com.getui.gtc.g.c.1
                @Override // com.getui.gtc.f.d
                public final void a(String str2) {
                }

                @Override // com.getui.gtc.f.d
                public final void a(Map<String, String> map, Map<String, String> map2) {
                    if (map == null || map2 == null) {
                        return;
                    }
                    com.getui.gtc.entity.a aVarA = com.getui.gtc.entity.a.a(map2);
                    com.getui.gtc.entity.a aVarA2 = com.getui.gtc.entity.a.a(map);
                    if (aVarA2 != null) {
                        int size = aVarA2.f2217a.size();
                        HashSet hashSet2 = new HashSet();
                        for (int i = 0; i < size; i++) {
                            a.C0044a c0044aA = aVarA2.a(i);
                            if (aVarA == null || aVarA.b(c0044aA.f2218a) == null || !aVarA.b(c0044aA.f2218a).b.equalsIgnoreCase(c0044aA.b) || !aVarA.b(c0044aA.f2218a).c.equalsIgnoreCase(c0044aA.c)) {
                                hashSet2.add(c0044aA.c);
                            }
                        }
                        com.getui.gtc.i.c.a.a("wait remove: ".concat(String.valueOf(hashSet2)));
                        c.a.f2213a.f2212a.a(hashSet2);
                    }
                    if (aVarA != null) {
                        int size2 = aVarA.f2217a.size();
                        for (int i2 = 0; i2 < size2; i2++) {
                            a.C0044a c0044aA2 = aVarA.a(i2);
                            if (!c.this.a(c0044aA2)) {
                                try {
                                    com.getui.gtc.h.b.a(c0044aA2, c.this.b + File.separator + c0044aA2.c);
                                } catch (Exception e2) {
                                    com.getui.gtc.i.c.a.a(e2);
                                }
                            }
                        }
                    }
                }
            }.c);
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
        }
    }

    public /* synthetic */ c(byte b) {
        this();
    }

    private void b(SdkInfo sdkInfo) {
        List<a.C0044a> stubs = sdkInfo.getStubs();
        for (int i = 0; i < stubs.size(); i++) {
            final a.C0044a c0044a = stubs.get(i);
            if (!b(c0044a)) {
                this.c.put(c0044a.d, c0044a);
                if (c0044a.j) {
                    b.a(GtcProvider.context(), null, null, c0044a.d, sdkInfo.getAppid(), sdkInfo.getCid(), new com.getui.gtc.g.a.b() { // from class: com.getui.gtc.g.c.2
                        @Override // com.getui.gtc.g.a.b
                        public final void a(boolean z) {
                            if (z) {
                                return;
                            }
                            c.this.c.remove(c0044a.d);
                        }
                    });
                } else {
                    try {
                        b.a(GtcProvider.context(), c0044a.d, sdkInfo.getAppid(), sdkInfo.getCid());
                    } catch (Throwable th) {
                        this.c.remove(c0044a.d);
                        com.getui.gtc.i.c.a.b("local gtcFile failed: ".concat(String.valueOf(th)));
                    }
                }
            }
        }
    }

    private boolean b(a.C0044a c0044a) {
        return this.c.containsKey(c0044a.d);
    }

    private com.getui.gtc.entity.a c(final SdkInfo sdkInfo) {
        Map<String, String> mapA = com.getui.gtc.f.b.a(sdkInfo, new b.a() { // from class: com.getui.gtc.g.c.3
            @Override // com.getui.gtc.f.b.a
            public final void a(Map<String, String> map) {
                c.this.a(sdkInfo, com.getui.gtc.entity.a.a(map));
            }
        });
        if (mapA == null) {
            return null;
        }
        return com.getui.gtc.entity.a.a(mapA);
    }

    public static boolean c(a.C0044a c0044a) {
        if (!c0044a.d.endsWith(".gws.stub.PushExtension")) {
            return false;
        }
        try {
            Class.forName("com.igexin.sdk.PushManager");
            return false;
        } catch (Throwable th) {
            a.C0045a.f2244a.f2243a.d(th);
            return th instanceof ClassNotFoundException;
        }
    }

    public final String a(SdkInfo sdkInfo, a.C0044a c0044a) {
        com.getui.gtc.entity.a aVarA;
        a.C0044a c0044aB;
        File file = new File(this.b + File.separator + c0044a.c);
        if (a(c0044a)) {
            return file.getAbsolutePath();
        }
        try {
            Map<String, String> mapA = com.getui.gtc.dyc.b.a.a(GtcProvider.context(), sdkInfo.getModuleName());
            if (mapA == null || (aVarA = com.getui.gtc.entity.a.a(mapA)) == null || (c0044aB = aVarA.b(c0044a.f2218a)) == null || !c0044aB.f2219e.equals(c0044a.f2219e)) {
                return null;
            }
            com.getui.gtc.h.b.a(c0044a, file.getAbsolutePath());
            if (com.getui.gtc.i.b.a.a(file.getAbsolutePath()).equals(c0044a.f2219e)) {
                return file.getAbsolutePath();
            }
            com.getui.gtc.i.b.a.a(file);
            throw new Exception("The net gtcFile save failed or has a wrong checksum");
        } catch (Exception e2) {
            com.getui.gtc.i.c.a.a(e2);
            return null;
        }
    }

    public final void a(SdkInfo sdkInfo) {
        b(sdkInfo);
        a(sdkInfo, c(sdkInfo));
    }

    public final void a(final SdkInfo sdkInfo, com.getui.gtc.entity.a aVar) {
        if (aVar == null) {
            return;
        }
        ArrayList<Integer> arrayList = new ArrayList();
        for (int i = 0; i < aVar.f2217a.size(); i++) {
            final a.C0044a c0044aA = aVar.a(i);
            if (!aVar.b && c(c0044aA)) {
                com.getui.gtc.i.c.a.a("no push, no gws");
            } else if (!b(c0044aA)) {
                final long jA = c.a.f2213a.b.a(c0044aA.f2218a);
                if ((c0044aA.g <= 0 || jA <= 0 || System.currentTimeMillis() - jA <= c0044aA.g) && (jA <= 0 || !c0044aA.i)) {
                    this.c.put(c0044aA.d, c0044aA);
                    ScheduleQueue.getInstance().addSchedule(new Runnable() { // from class: com.getui.gtc.g.c.4
                        @Override // java.lang.Runnable
                        public final void run() throws Throwable {
                            String strA = c.this.a(sdkInfo, c0044aA);
                            if (strA == null) {
                                c.this.c.remove(c0044aA.d);
                                return;
                            }
                            File file = new File(strA);
                            final File file2 = new File(strA + com.getui.gtc.c.a.b);
                            com.getui.gtc.i.a.a.a(file, file2, c0044aA.f);
                            if (c0044aA.j) {
                                b.a(GtcProvider.context(), file2.getAbsolutePath(), c.this.f2230a, c0044aA.d, sdkInfo.getAppid(), sdkInfo.getCid(), new com.getui.gtc.g.a.b() { // from class: com.getui.gtc.g.c.4.1
                                    @Override // com.getui.gtc.g.a.b
                                    public final void a(boolean z) {
                                        com.getui.gtc.i.b.a.a(file2);
                                        if (!z) {
                                            AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                            c.this.c.remove(c0044aA.d);
                                        } else if (jA == 0) {
                                            c.a.f2213a.b.a(c0044aA.f2218a, System.currentTimeMillis());
                                        }
                                    }
                                });
                                return;
                            }
                            try {
                                b.a(GtcProvider.context(), file2.getAbsolutePath(), c.this.f2230a, c0044aA.d, sdkInfo.getAppid(), sdkInfo.getCid());
                                if (jA == 0) {
                                    c.a.f2213a.b.a(c0044aA.f2218a, System.currentTimeMillis());
                                }
                            } catch (Throwable th) {
                                try {
                                    c.this.c.remove(c0044aA.d);
                                    com.getui.gtc.i.c.a.b("net gtcFile filed: ".concat(String.valueOf(th)));
                                } finally {
                                    com.getui.gtc.i.b.a.a(file2);
                                }
                            }
                        }
                    });
                } else {
                    arrayList.add(Integer.valueOf(i));
                }
            }
        }
        for (Integer num : arrayList) {
            a(aVar.a(num.intValue()).c);
            aVar.c(num.intValue());
        }
    }

    public final boolean a(a.C0044a c0044a) {
        File file = new File(this.b + File.separator + c0044a.c);
        if (file.exists() && file.isFile()) {
            return com.getui.gtc.i.b.a.a(file.getAbsolutePath()).equals(c0044a.f2219e);
        }
        return false;
    }

    public final boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            com.getui.gtc.i.b.a.a(this.f2230a, str);
            String strA = com.getui.gtc.g.a.a(str);
            if (TextUtils.isEmpty(strA)) {
                return true;
            }
            com.getui.gtc.i.b.a.a(new File(this.f2230a + "/" + strA));
            return true;
        } catch (Exception e2) {
            com.getui.gtc.i.c.a.b(e2);
            return false;
        }
    }
}
