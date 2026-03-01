package supwisdom;

import android.content.Context;
import com.dcloud.android.downloader.domain.DownloadInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import supwisdom.xt;

/* JADX INFO: loaded from: classes.dex */
public final class rt implements st, xt.a {
    public static rt h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ExecutorService f9089a;
    public final ConcurrentHashMap<Integer, Object> b;
    public final List<DownloadInfo> c;
    public final vt d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final cu f9090e;
    public final ut f;
    public long g;

    public rt(Context context, ut utVar) {
        utVar = utVar == null ? new ut() : utVar;
        this.f = utVar;
        if (utVar.d() == null) {
            this.f9090e = new au(context, utVar);
        } else {
            this.f9090e = utVar.d();
        }
        if (this.f9090e.a() == null) {
            this.c = new ArrayList();
        } else {
            this.c = this.f9090e.a();
        }
        this.b = new ConcurrentHashMap<>();
        this.f9090e.b();
        this.f9089a = Executors.newFixedThreadPool(utVar.e());
        this.d = new wt(this.f9090e);
    }

    @Override // supwisdom.st
    public DownloadInfo a(int i) {
        DownloadInfo next;
        Iterator<DownloadInfo> it = this.c.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (next.getId() == i) {
                break;
            }
        }
        return next == null ? this.f9090e.a(i) : next;
    }

    @Override // supwisdom.st
    public void b(DownloadInfo downloadInfo) {
        this.c.add(downloadInfo);
        d(downloadInfo);
    }

    @Override // supwisdom.st
    public void c(DownloadInfo downloadInfo) {
        downloadInfo.setStatus(7);
        this.b.remove(Integer.valueOf(downloadInfo.getId()));
        this.c.remove(downloadInfo);
        this.f9090e.a(downloadInfo);
        this.d.a(downloadInfo);
    }

    public final void d(DownloadInfo downloadInfo) {
        if (this.b.size() >= this.f.e()) {
            downloadInfo.setStatus(3);
            this.d.a(downloadInfo);
            return;
        }
        xt xtVar = new xt(this.f9089a, this.d, downloadInfo, this.f, this);
        this.b.put(Integer.valueOf(downloadInfo.getId()), xtVar);
        downloadInfo.setStatus(1);
        this.d.a(downloadInfo);
        xtVar.f();
    }

    @Override // supwisdom.st
    public void onDestroy() {
    }

    @Override // supwisdom.xt.a
    public void onDownloadSuccess(DownloadInfo downloadInfo) {
        this.b.remove(Integer.valueOf(downloadInfo.getId()));
        this.c.remove(downloadInfo);
        b();
    }

    public final void b() {
        for (DownloadInfo downloadInfo : this.c) {
            if (downloadInfo.getStatus() == 3) {
                d(downloadInfo);
                return;
            }
        }
    }

    public static st a(Context context, ut utVar) {
        synchronized (rt.class) {
            if (h == null) {
                h = new rt(context, utVar);
            }
        }
        return h;
    }

    public boolean a() {
        if (System.currentTimeMillis() - this.g <= 500) {
            return false;
        }
        this.g = System.currentTimeMillis();
        return true;
    }

    @Override // supwisdom.st
    public void a(DownloadInfo downloadInfo) {
        if (a()) {
            this.b.remove(Integer.valueOf(downloadInfo.getId()));
            d(downloadInfo);
        }
    }
}
