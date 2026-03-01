package supwisdom;

import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import java.util.List;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class aa1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static sa1<Object> f6877a;
    public static qa1<Handler> b;
    public static ta1<IInterface> c;
    public static qa1<Object> d;

    /* JADX INFO: compiled from: Proguard */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static qa1<Intent> f6878a;
        public static qa1<IBinder> b;
        public static qa1<String> c;

        static {
            ja1.a((Class<?>) a.class, "android.app.ActivityThread$ActivityClientRecord");
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static ra1 f6879a;
        public static ra1 b;
        public static ra1 c;

        static {
            ja1.a((Class<?>) b.class, "android.app.ActivityThread$H");
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static qa1<List<Intent>> f6880a;

        static {
            ja1.a((Class<?>) c.class, "android.app.ActivityThread$NewIntentData");
        }
    }

    static {
        ja1.a((Class<?>) aa1.class, "android.app.ActivityThread");
    }
}
