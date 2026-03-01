package supwisdom;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public abstract class kw0 extends iw0 {
    public final AtomicInteger c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map<File, Long> f8214e;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            File[] fileArrListFiles = kw0.this.f7993a.listFiles();
            if (fileArrListFiles != null) {
                int iA = 0;
                for (File file : fileArrListFiles) {
                    iA += kw0.this.a(file);
                    kw0.this.f8214e.put(file, Long.valueOf(file.lastModified()));
                }
                kw0.this.c.set(iA);
            }
        }
    }

    public kw0(File file, ow0 ow0Var, int i) {
        super(file, ow0Var);
        this.f8214e = Collections.synchronizedMap(new HashMap());
        this.d = i;
        this.c = new AtomicInteger();
        a();
    }

    public abstract int a(File file);

    public final void a() {
        new Thread(new a()).start();
    }

    public final int b() {
        File key;
        if (this.f8214e.isEmpty()) {
            return -1;
        }
        Set<Map.Entry<File, Long>> setEntrySet = this.f8214e.entrySet();
        synchronized (this.f8214e) {
            key = null;
            Long value = null;
            for (Map.Entry<File, Long> entry : setEntrySet) {
                if (key == null) {
                    key = entry.getKey();
                    value = entry.getValue();
                } else {
                    Long value2 = entry.getValue();
                    if (value2.longValue() < value.longValue()) {
                        key = entry.getKey();
                        value = value2;
                    }
                }
            }
        }
        int iA = 0;
        if (key != null) {
            if (key.exists()) {
                iA = a(key);
                if (key.delete()) {
                    this.f8214e.remove(key);
                }
            } else {
                this.f8214e.remove(key);
            }
        }
        return iA;
    }

    @Override // supwisdom.iw0, supwisdom.jw0
    public File a(String str) {
        File fileA = super.a(str);
        Long lValueOf = Long.valueOf(System.currentTimeMillis());
        fileA.setLastModified(lValueOf.longValue());
        this.f8214e.put(fileA, lValueOf);
        return fileA;
    }

    @Override // supwisdom.jw0
    public void a(String str, File file) {
        int iB;
        int iA = a(file);
        int iAddAndGet = this.c.get();
        while (iAddAndGet + iA > this.d && (iB = b()) != -1) {
            iAddAndGet = this.c.addAndGet(-iB);
        }
        this.c.addAndGet(iA);
        Long lValueOf = Long.valueOf(System.currentTimeMillis());
        file.setLastModified(lValueOf.longValue());
        this.f8214e.put(file, lValueOf);
    }
}
