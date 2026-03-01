package supwisdom;

import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Timer f8004a;
    public TimerTask b;
    public int c;
    public int d;

    public class a extends TimerTask {
        public a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            j.this.a();
        }
    }

    public j(int i) {
        this.d = 0;
        this.c = i;
    }

    public j(int i, int i2) {
        this.d = i;
        this.c = i2;
    }

    public abstract void a();

    public void b() {
        c();
        this.b = new a();
        Timer timer = new Timer();
        this.f8004a = timer;
        timer.schedule(this.b, this.d, this.c);
    }

    public void c() {
        try {
            try {
                if (this.b != null) {
                    this.b.cancel();
                }
                if (this.f8004a != null) {
                    this.f8004a.cancel();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            this.b = null;
            this.f8004a = null;
        }
    }
}
