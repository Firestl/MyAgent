package supwisdom;

import com.dcloud.android.downloader.domain.DownloadInfo;
import com.dcloud.android.downloader.domain.DownloadThreadInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import supwisdom.yt;
import supwisdom.zt;

/* JADX INFO: loaded from: classes.dex */
public class xt implements yt.a, zt.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ExecutorService f9807a;
    public final vt b;
    public final DownloadInfo c;
    public final ut d;
    public final a f;
    public long h;
    public long g = System.currentTimeMillis();
    public volatile AtomicBoolean i = new AtomicBoolean(false);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<zt> f9808e = new ArrayList();

    public interface a {
        void onDownloadSuccess(DownloadInfo downloadInfo);
    }

    public xt(ExecutorService executorService, vt vtVar, DownloadInfo downloadInfo, ut utVar, a aVar) {
        this.f9807a = executorService;
        this.b = vtVar;
        this.c = downloadInfo;
        this.d = utVar;
        this.f = aVar;
    }

    @Override // supwisdom.zt.a
    public void a() {
        if (this.i.get()) {
            return;
        }
        synchronized (this) {
            if (!this.i.get()) {
                this.i.set(true);
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - this.g > 1000) {
                    c();
                    this.b.a(this.c);
                    this.g = jCurrentTimeMillis;
                }
                this.i.set(false);
            }
        }
    }

    @Override // supwisdom.zt.a
    public void b() {
        c();
        if (this.c.getProgress() == this.c.getSize()) {
            this.c.setStatus(5);
            this.b.a(this.c);
            a aVar = this.f;
            if (aVar != null) {
                aVar.onDownloadSuccess(this.c);
            }
        }
    }

    public final void c() {
        this.h = 0L;
        Iterator<DownloadThreadInfo> it = this.c.getDownloadThreadInfos().iterator();
        while (it.hasNext()) {
            this.h += it.next().getProgress();
        }
        this.c.setProgress(this.h);
    }

    public final void d() {
        this.f9807a.submit(new yt(this.b, this.c, this));
    }

    public final void e() {
        File file = new File(this.c.getPath());
        if (file.exists()) {
            file.delete();
        }
    }

    public void f() {
        if (this.c.getSize() <= 0) {
            d();
            return;
        }
        Iterator<DownloadThreadInfo> it = this.c.getDownloadThreadInfos().iterator();
        while (it.hasNext()) {
            zt ztVar = new zt(it.next(), this.b, this.d, this.c, this);
            this.f9807a.submit(ztVar);
            this.f9808e.add(ztVar);
        }
        this.c.setStatus(2);
        this.b.a(this.c);
    }

    @Override // supwisdom.yt.a
    public void a(long j, boolean z) {
        this.c.setSupportRanges(z);
        this.c.setSize(j);
        e();
        ArrayList arrayList = new ArrayList();
        if (z) {
            long size = this.c.getSize();
            int iF = this.d.f();
            long j2 = size / ((long) iF);
            int i = 0;
            while (i < iF) {
                long j3 = j2 * ((long) i);
                int i2 = i;
                DownloadThreadInfo downloadThreadInfo = new DownloadThreadInfo(i2, this.c.getId(), this.c.getDownloadUrl(), j3, i == iF + (-1) ? size : (j3 + j2) - 1);
                arrayList.add(downloadThreadInfo);
                zt ztVar = new zt(downloadThreadInfo, this.b, this.d, this.c, this);
                this.f9807a.submit(ztVar);
                this.f9808e.add(ztVar);
                i = i2 + 1;
            }
        } else {
            DownloadThreadInfo downloadThreadInfo2 = new DownloadThreadInfo(0, this.c.getId(), this.c.getDownloadUrl(), 0L, this.c.getSize());
            arrayList.add(downloadThreadInfo2);
            zt ztVar2 = new zt(downloadThreadInfo2, this.b, this.d, this.c, this);
            this.f9807a.submit(ztVar2);
            this.f9808e.add(ztVar2);
        }
        this.c.setDownloadThreadInfos(arrayList);
        this.c.setStatus(2);
        this.b.a(this.c);
    }
}
