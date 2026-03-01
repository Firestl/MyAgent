package supwisdom;

import android.net.NetworkInfo;
import com.squareup.picasso.Picasso;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import supwisdom.rh1;

/* JADX INFO: compiled from: PicassoExecutorService.java */
/* JADX INFO: loaded from: classes2.dex */
public class ih1 extends ThreadPoolExecutor {

    /* JADX INFO: compiled from: PicassoExecutorService.java */
    public static final class a extends FutureTask<tg1> implements Comparable<a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final tg1 f7955a;

        public a(tg1 tg1Var) {
            super(tg1Var, null);
            this.f7955a = tg1Var;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(a aVar) {
            Picasso.Priority priorityK = this.f7955a.k();
            Picasso.Priority priorityK2 = aVar.f7955a.k();
            return priorityK == priorityK2 ? this.f7955a.f9285a - aVar.f7955a.f9285a : priorityK2.ordinal() - priorityK.ordinal();
        }
    }

    public ih1() {
        super(3, 3, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new rh1.f());
    }

    public void a(NetworkInfo networkInfo) {
        if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) {
            a(3);
            return;
        }
        int type = networkInfo.getType();
        if (type != 0) {
            if (type == 1 || type == 6 || type == 9) {
                a(4);
                return;
            } else {
                a(3);
                return;
            }
        }
        int subtype = networkInfo.getSubtype();
        switch (subtype) {
            case 1:
            case 2:
                a(1);
                return;
            case 3:
            case 4:
            case 5:
            case 6:
                break;
            default:
                switch (subtype) {
                    case 12:
                        break;
                    case 13:
                    case 14:
                    case 15:
                        a(3);
                        break;
                    default:
                        a(3);
                        break;
                }
                return;
        }
        a(2);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        a aVar = new a((tg1) runnable);
        execute(aVar);
        return aVar;
    }

    public final void a(int i) {
        setCorePoolSize(i);
        setMaximumPoolSize(i);
    }
}
