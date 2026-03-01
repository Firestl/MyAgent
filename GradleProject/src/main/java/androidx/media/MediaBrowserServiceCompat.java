package androidx.media;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import supwisdom.j4;
import supwisdom.ja;
import supwisdom.n7;
import supwisdom.oe;
import supwisdom.pe;
import supwisdom.qe;
import supwisdom.re;
import supwisdom.se;

/* JADX INFO: loaded from: classes.dex */
public abstract class MediaBrowserServiceCompat extends Service {
    public static final boolean f = Log.isLoggable("MBServiceCompat", 3);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g f1321a;
    public f c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public MediaSessionCompat.Token f1322e;
    public final j4<IBinder, f> b = new j4<>();
    public final q d = new q();

    public class a extends m<List<MediaBrowserCompat.MediaItem>> {
        public final /* synthetic */ f f;
        public final /* synthetic */ String g;
        public final /* synthetic */ Bundle h;
        public final /* synthetic */ Bundle i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Object obj, f fVar, String str, Bundle bundle, Bundle bundle2) {
            super(obj);
            this.f = fVar;
            this.g = str;
            this.h = bundle;
            this.i = bundle2;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        public void a(List<MediaBrowserCompat.MediaItem> list) {
            if (MediaBrowserServiceCompat.this.b.get(this.f.c.asBinder()) != this.f) {
                if (MediaBrowserServiceCompat.f) {
                    Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + this.f.f1323a + " id=" + this.g);
                    return;
                }
                return;
            }
            if ((a() & 1) != 0) {
                list = MediaBrowserServiceCompat.this.a(list, this.h);
            }
            try {
                this.f.c.a(this.g, list, this.h, this.i);
            } catch (RemoteException unused) {
                Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + this.g + " package=" + this.f.f1323a);
            }
        }
    }

    public class b extends m<MediaBrowserCompat.MediaItem> {
        public final /* synthetic */ ResultReceiver f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        public void a(MediaBrowserCompat.MediaItem mediaItem) {
            if ((a() & 2) != 0) {
                this.f.send(-1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("media_item", mediaItem);
            this.f.send(0, bundle);
        }
    }

    public class c extends m<List<MediaBrowserCompat.MediaItem>> {
        public final /* synthetic */ ResultReceiver f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        public void a(List<MediaBrowserCompat.MediaItem> list) {
            if ((a() & 4) != 0 || list == null) {
                this.f.send(-1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArray("search_results", (Parcelable[]) list.toArray(new MediaBrowserCompat.MediaItem[0]));
            this.f.send(0, bundle);
        }
    }

    public class d extends m<Bundle> {
        public final /* synthetic */ ResultReceiver f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void a(Bundle bundle) {
            this.f.send(0, bundle);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.media.MediaBrowserServiceCompat.m
        public void a(Bundle bundle) {
            this.f.send(-1, bundle);
        }
    }

    public static final class e {
        public Bundle a() {
            throw null;
        }

        public String b() {
            throw null;
        }
    }

    public class f implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f1323a;
        public final Bundle b;
        public final o c;
        public final HashMap<String, List<ja<IBinder, Bundle>>> d = new HashMap<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public e f1324e;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = f.this;
                MediaBrowserServiceCompat.this.b.remove(fVar.c.asBinder());
            }
        }

        public f(String str, int i, int i2, Bundle bundle, o oVar) {
            this.f1323a = str;
            new se(str, i, i2);
            this.b = bundle;
            this.c = oVar;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            MediaBrowserServiceCompat.this.d.post(new a());
        }
    }

    public interface g {
        IBinder a(Intent intent);

        void a();
    }

    public class k extends j {
        public k(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            super();
        }
    }

    public class l implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Messenger f1328a;

        public l() {
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            this.f1328a = new Messenger(MediaBrowserServiceCompat.this.d);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public IBinder a(Intent intent) {
            if ("android.media.browse.MediaBrowserService".equals(intent.getAction())) {
                return this.f1328a.getBinder();
            }
            return null;
        }
    }

    public static class m<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f1329a;
        public boolean b;
        public boolean c;
        public boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1330e;

        public m(Object obj) {
            this.f1329a = obj;
        }

        public void a(int i) {
            this.f1330e = i;
        }

        public void a(T t) {
            throw null;
        }

        public void b(T t) {
            if (!this.c && !this.d) {
                this.c = true;
                a(t);
            } else {
                throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.f1329a);
            }
        }

        public int a() {
            return this.f1330e;
        }

        public void a(Bundle bundle) {
            throw new UnsupportedOperationException("It is not supported to send an error for " + this.f1329a);
        }

        public void b(Bundle bundle) {
            if (!this.c && !this.d) {
                this.d = true;
                a(bundle);
            } else {
                throw new IllegalStateException("sendError() called when either sendResult() or sendError() had already been called for: " + this.f1329a);
            }
        }

        public boolean b() {
            return this.b || this.c || this.d;
        }
    }

    public class n {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ o f1332a;
            public final /* synthetic */ String b;
            public final /* synthetic */ int c;
            public final /* synthetic */ int d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ Bundle f1333e;

            public a(o oVar, String str, int i, int i2, Bundle bundle) {
                this.f1332a = oVar;
                this.b = str;
                this.c = i;
                this.d = i2;
                this.f1333e = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder iBinderAsBinder = this.f1332a.asBinder();
                MediaBrowserServiceCompat.this.b.remove(iBinderAsBinder);
                f fVar = MediaBrowserServiceCompat.this.new f(this.b, this.c, this.d, this.f1333e, this.f1332a);
                MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
                mediaBrowserServiceCompat.c = fVar;
                e eVarA = mediaBrowserServiceCompat.a(this.b, this.d, this.f1333e);
                fVar.f1324e = eVarA;
                MediaBrowserServiceCompat mediaBrowserServiceCompat2 = MediaBrowserServiceCompat.this;
                mediaBrowserServiceCompat2.c = null;
                if (eVarA != null) {
                    try {
                        mediaBrowserServiceCompat2.b.put(iBinderAsBinder, fVar);
                        iBinderAsBinder.linkToDeath(fVar, 0);
                        if (MediaBrowserServiceCompat.this.f1322e == null) {
                            return;
                        }
                        fVar.f1324e.b();
                        throw null;
                    } catch (RemoteException unused) {
                        Log.w("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + this.b);
                        MediaBrowserServiceCompat.this.b.remove(iBinderAsBinder);
                        return;
                    }
                }
                Log.i("MBServiceCompat", "No root for client " + this.b + " from service " + a.class.getName());
                try {
                    this.f1332a.a();
                } catch (RemoteException unused2) {
                    Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + this.b);
                }
            }
        }

        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ o f1334a;

            public b(o oVar) {
                this.f1334a = oVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVarRemove = MediaBrowserServiceCompat.this.b.remove(this.f1334a.asBinder());
                if (fVarRemove != null) {
                    fVarRemove.c.asBinder().unlinkToDeath(fVarRemove, 0);
                }
            }
        }

        public class c implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ o f1335a;
            public final /* synthetic */ String b;
            public final /* synthetic */ IBinder c;
            public final /* synthetic */ Bundle d;

            public c(o oVar, String str, IBinder iBinder, Bundle bundle) {
                this.f1335a = oVar;
                this.b = str;
                this.c = iBinder;
                this.d = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.b.get(this.f1335a.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.a(this.b, fVar, this.c, this.d);
                    return;
                }
                Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + this.b);
            }
        }

        public class d implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ o f1337a;
            public final /* synthetic */ String b;
            public final /* synthetic */ IBinder c;

            public d(o oVar, String str, IBinder iBinder) {
                this.f1337a = oVar;
                this.b = str;
                this.c = iBinder;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.b.get(this.f1337a.asBinder());
                if (fVar == null) {
                    Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + this.b);
                    return;
                }
                if (MediaBrowserServiceCompat.this.a(this.b, fVar, this.c)) {
                    return;
                }
                Log.w("MBServiceCompat", "removeSubscription called for " + this.b + " which is not subscribed");
            }
        }

        public class e implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ o f1338a;
            public final /* synthetic */ String b;
            public final /* synthetic */ ResultReceiver c;

            public e(o oVar, String str, ResultReceiver resultReceiver) {
                this.f1338a = oVar;
                this.b = str;
                this.c = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.b.get(this.f1338a.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.a(this.b, fVar, this.c);
                    return;
                }
                Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + this.b);
            }
        }

        public class f implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ o f1339a;
            public final /* synthetic */ String b;
            public final /* synthetic */ int c;
            public final /* synthetic */ int d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ Bundle f1340e;

            public f(o oVar, String str, int i, int i2, Bundle bundle) {
                this.f1339a = oVar;
                this.b = str;
                this.c = i;
                this.d = i2;
                this.f1340e = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder iBinderAsBinder = this.f1339a.asBinder();
                MediaBrowserServiceCompat.this.b.remove(iBinderAsBinder);
                f fVar = MediaBrowserServiceCompat.this.new f(this.b, this.c, this.d, this.f1340e, this.f1339a);
                MediaBrowserServiceCompat.this.b.put(iBinderAsBinder, fVar);
                try {
                    iBinderAsBinder.linkToDeath(fVar, 0);
                } catch (RemoteException unused) {
                    Log.w("MBServiceCompat", "IBinder is already dead.");
                }
            }
        }

        public class g implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ o f1341a;

            public g(o oVar) {
                this.f1341a = oVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder iBinderAsBinder = this.f1341a.asBinder();
                f fVarRemove = MediaBrowserServiceCompat.this.b.remove(iBinderAsBinder);
                if (fVarRemove != null) {
                    iBinderAsBinder.unlinkToDeath(fVarRemove, 0);
                }
            }
        }

        public class h implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ o f1342a;
            public final /* synthetic */ String b;
            public final /* synthetic */ Bundle c;
            public final /* synthetic */ ResultReceiver d;

            public h(o oVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.f1342a = oVar;
                this.b = str;
                this.c = bundle;
                this.d = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.b.get(this.f1342a.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.b(this.b, this.c, fVar, this.d);
                    return;
                }
                Log.w("MBServiceCompat", "search for callback that isn't registered query=" + this.b);
            }
        }

        public class i implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ o f1344a;
            public final /* synthetic */ String b;
            public final /* synthetic */ Bundle c;
            public final /* synthetic */ ResultReceiver d;

            public i(o oVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.f1344a = oVar;
                this.b = str;
                this.c = bundle;
                this.d = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.b.get(this.f1344a.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.a(this.b, this.c, fVar, this.d);
                    return;
                }
                Log.w("MBServiceCompat", "sendCustomAction for callback that isn't registered action=" + this.b + ", extras=" + this.c);
            }
        }

        public n() {
        }

        public void a(String str, int i2, int i3, Bundle bundle, o oVar) {
            if (MediaBrowserServiceCompat.this.a(str, i3)) {
                MediaBrowserServiceCompat.this.d.a(new a(oVar, str, i2, i3, bundle));
                return;
            }
            throw new IllegalArgumentException("Package/uid mismatch: uid=" + i3 + " package=" + str);
        }

        public void b(o oVar) {
            MediaBrowserServiceCompat.this.d.a(new g(oVar));
        }

        public void b(String str, Bundle bundle, ResultReceiver resultReceiver, o oVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.d.a(new i(oVar, str, bundle, resultReceiver));
        }

        public void a(o oVar) {
            MediaBrowserServiceCompat.this.d.a(new b(oVar));
        }

        public void a(String str, IBinder iBinder, Bundle bundle, o oVar) {
            MediaBrowserServiceCompat.this.d.a(new c(oVar, str, iBinder, bundle));
        }

        public void a(String str, IBinder iBinder, o oVar) {
            MediaBrowserServiceCompat.this.d.a(new d(oVar, str, iBinder));
        }

        public void a(String str, ResultReceiver resultReceiver, o oVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.d.a(new e(oVar, str, resultReceiver));
        }

        public void a(o oVar, String str, int i2, int i3, Bundle bundle) {
            MediaBrowserServiceCompat.this.d.a(new f(oVar, str, i2, i3, bundle));
        }

        public void a(String str, Bundle bundle, ResultReceiver resultReceiver, o oVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.d.a(new h(oVar, str, bundle, resultReceiver));
        }
    }

    public interface o {
        void a() throws RemoteException;

        void a(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle, Bundle bundle2) throws RemoteException;

        IBinder asBinder();
    }

    public static class p implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Messenger f1346a;

        public p(Messenger messenger) {
            this.f1346a = messenger;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.o
        public void a() throws RemoteException {
            a(2, null);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.o
        public IBinder asBinder() {
            return this.f1346a.getBinder();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.o
        public void a(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle, Bundle bundle2) throws RemoteException {
            Bundle bundle3 = new Bundle();
            bundle3.putString("data_media_item_id", str);
            bundle3.putBundle("data_options", bundle);
            bundle3.putBundle("data_notify_children_changed_options", bundle2);
            if (list != null) {
                bundle3.putParcelableArrayList("data_media_item_list", list instanceof ArrayList ? (ArrayList) list : new ArrayList<>(list));
            }
            a(3, bundle3);
        }

        public final void a(int i, Bundle bundle) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = 2;
            messageObtain.setData(bundle);
            this.f1346a.send(messageObtain);
        }
    }

    public final class q extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final n f1347a;

        public q() {
            this.f1347a = MediaBrowserServiceCompat.this.new n();
        }

        public void a(Runnable runnable) {
            if (Thread.currentThread() == getLooper().getThread()) {
                runnable.run();
            } else {
                post(runnable);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Bundle data = message.getData();
            switch (message.what) {
                case 1:
                    Bundle bundle = data.getBundle("data_root_hints");
                    MediaSessionCompat.ensureClassLoader(bundle);
                    this.f1347a.a(data.getString("data_package_name"), data.getInt(MediaSessionCompat.DATA_CALLING_PID), data.getInt(MediaSessionCompat.DATA_CALLING_UID), bundle, new p(message.replyTo));
                    break;
                case 2:
                    this.f1347a.a(new p(message.replyTo));
                    break;
                case 3:
                    Bundle bundle2 = data.getBundle("data_options");
                    MediaSessionCompat.ensureClassLoader(bundle2);
                    this.f1347a.a(data.getString("data_media_item_id"), n7.a(data, "data_callback_token"), bundle2, new p(message.replyTo));
                    break;
                case 4:
                    this.f1347a.a(data.getString("data_media_item_id"), n7.a(data, "data_callback_token"), new p(message.replyTo));
                    break;
                case 5:
                    this.f1347a.a(data.getString("data_media_item_id"), (ResultReceiver) data.getParcelable("data_result_receiver"), new p(message.replyTo));
                    break;
                case 6:
                    Bundle bundle3 = data.getBundle("data_root_hints");
                    MediaSessionCompat.ensureClassLoader(bundle3);
                    this.f1347a.a(new p(message.replyTo), data.getString("data_package_name"), data.getInt(MediaSessionCompat.DATA_CALLING_PID), data.getInt(MediaSessionCompat.DATA_CALLING_UID), bundle3);
                    break;
                case 7:
                    this.f1347a.b(new p(message.replyTo));
                    break;
                case 8:
                    Bundle bundle4 = data.getBundle("data_search_extras");
                    MediaSessionCompat.ensureClassLoader(bundle4);
                    this.f1347a.a(data.getString("data_search_query"), bundle4, (ResultReceiver) data.getParcelable("data_result_receiver"), new p(message.replyTo));
                    break;
                case 9:
                    Bundle bundle5 = data.getBundle("data_custom_action_extras");
                    MediaSessionCompat.ensureClassLoader(bundle5);
                    this.f1347a.b(data.getString("data_custom_action"), bundle5, (ResultReceiver) data.getParcelable("data_result_receiver"), new p(message.replyTo));
                    break;
                default:
                    Log.w("MBServiceCompat", "Unhandled message: " + message + "\n  Service version: 2\n  Client version: " + message.arg1);
                    break;
            }
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(Message message, long j) {
            Bundle data = message.getData();
            data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            data.putInt(MediaSessionCompat.DATA_CALLING_UID, Binder.getCallingUid());
            data.putInt(MediaSessionCompat.DATA_CALLING_PID, Binder.getCallingPid());
            return super.sendMessageAtTime(message, j);
        }
    }

    public abstract e a(String str, int i2, Bundle bundle);

    public void a(String str) {
    }

    public void a(String str, Bundle bundle) {
    }

    public abstract void a(String str, m<List<MediaBrowserCompat.MediaItem>> mVar);

    public void a(String str, m<List<MediaBrowserCompat.MediaItem>> mVar, Bundle bundle) {
        mVar.a(1);
        a(str, mVar);
    }

    public void b(String str, m<MediaBrowserCompat.MediaItem> mVar) {
        mVar.a(2);
        mVar.b((MediaBrowserCompat.MediaItem) null);
    }

    @Override // android.app.Service
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f1321a.a(intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            this.f1321a = new k(this);
        } else if (i2 >= 26) {
            this.f1321a = new j();
        } else if (i2 >= 23) {
            this.f1321a = new i();
        } else if (i2 >= 21) {
            this.f1321a = new h();
        } else {
            this.f1321a = new l();
        }
        this.f1321a.a();
    }

    public class h implements g, pe.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<Bundle> f1326a = new ArrayList();
        public Object b;
        public Messenger c;

        public class a extends m<List<MediaBrowserCompat.MediaItem>> {
            public final /* synthetic */ pe.c f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(h hVar, Object obj, pe.c cVar) {
                super(obj);
                this.f = cVar;
            }

            @Override // androidx.media.MediaBrowserServiceCompat.m
            public void a(List<MediaBrowserCompat.MediaItem> list) {
                ArrayList arrayList;
                if (list != null) {
                    arrayList = new ArrayList();
                    for (MediaBrowserCompat.MediaItem mediaItem : list) {
                        Parcel parcelObtain = Parcel.obtain();
                        mediaItem.writeToParcel(parcelObtain, 0);
                        arrayList.add(parcelObtain);
                    }
                } else {
                    arrayList = null;
                }
                this.f.a(arrayList);
            }
        }

        public h() {
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            Object objA = pe.a(MediaBrowserServiceCompat.this, this);
            this.b = objA;
            pe.a(objA);
        }

        @Override // supwisdom.pe.d
        public void b(String str, pe.c<List<Parcel>> cVar) {
            MediaBrowserServiceCompat.this.a(str, new a(this, str, cVar));
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public IBinder a(Intent intent) {
            return pe.a(this.b, intent);
        }

        @Override // supwisdom.pe.d
        public pe.a a(String str, int i, Bundle bundle) {
            Bundle bundle2;
            if (bundle == null || bundle.getInt("extra_client_version", 0) == 0) {
                bundle2 = null;
            } else {
                bundle.remove("extra_client_version");
                this.c = new Messenger(MediaBrowserServiceCompat.this.d);
                bundle2 = new Bundle();
                bundle2.putInt("extra_service_version", 2);
                n7.a(bundle2, "extra_messenger", this.c.getBinder());
                MediaSessionCompat.Token token = MediaBrowserServiceCompat.this.f1322e;
                if (token != null) {
                    IMediaSession extraBinder = token.getExtraBinder();
                    n7.a(bundle2, "extra_session_binder", extraBinder == null ? null : extraBinder.asBinder());
                } else {
                    this.f1326a.add(bundle2);
                }
            }
            MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat.c = mediaBrowserServiceCompat.new f(str, -1, i, bundle, null);
            e eVarA = MediaBrowserServiceCompat.this.a(str, i, bundle);
            MediaBrowserServiceCompat.this.c = null;
            if (eVarA == null) {
                return null;
            }
            if (bundle2 == null) {
                eVarA.a();
                throw null;
            }
            eVarA.a();
            throw null;
        }
    }

    public class i extends h implements qe.b {

        public class a extends m<MediaBrowserCompat.MediaItem> {
            public final /* synthetic */ pe.c f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(i iVar, Object obj, pe.c cVar) {
                super(obj);
                this.f = cVar;
            }

            @Override // androidx.media.MediaBrowserServiceCompat.m
            public void a(MediaBrowserCompat.MediaItem mediaItem) {
                if (mediaItem == null) {
                    this.f.a((Object) null);
                    return;
                }
                Parcel parcelObtain = Parcel.obtain();
                mediaItem.writeToParcel(parcelObtain, 0);
                this.f.a(parcelObtain);
            }
        }

        public i() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.h, androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            Object objA = qe.a(MediaBrowserServiceCompat.this, this);
            this.b = objA;
            pe.a(objA);
        }

        @Override // supwisdom.qe.b
        public void a(String str, pe.c<Parcel> cVar) {
            MediaBrowserServiceCompat.this.b(str, new a(this, str, cVar));
        }
    }

    public class j extends i implements re.c {

        public class a extends m<List<MediaBrowserCompat.MediaItem>> {
            public final /* synthetic */ re.b f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(j jVar, Object obj, re.b bVar) {
                super(obj);
                this.f = bVar;
            }

            @Override // androidx.media.MediaBrowserServiceCompat.m
            public void a(List<MediaBrowserCompat.MediaItem> list) {
                ArrayList arrayList;
                if (list != null) {
                    arrayList = new ArrayList();
                    for (MediaBrowserCompat.MediaItem mediaItem : list) {
                        Parcel parcelObtain = Parcel.obtain();
                        mediaItem.writeToParcel(parcelObtain, 0);
                        arrayList.add(parcelObtain);
                    }
                } else {
                    arrayList = null;
                }
                this.f.a(arrayList, a());
            }
        }

        public j() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.i, androidx.media.MediaBrowserServiceCompat.h, androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            Object objA = re.a(MediaBrowserServiceCompat.this, this);
            this.b = objA;
            pe.a(objA);
        }

        @Override // supwisdom.re.c
        public void a(String str, re.b bVar, Bundle bundle) {
            MediaBrowserServiceCompat.this.a(str, new a(this, str, bVar), bundle);
        }
    }

    public void a(String str, Bundle bundle, m<Bundle> mVar) {
        mVar.b((Bundle) null);
    }

    public void b(String str, Bundle bundle, m<List<MediaBrowserCompat.MediaItem>> mVar) {
        mVar.a(4);
        mVar.b((List<MediaBrowserCompat.MediaItem>) null);
    }

    public boolean a(String str, int i2) {
        if (str == null) {
            return false;
        }
        for (String str2 : getPackageManager().getPackagesForUid(i2)) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void b(String str, Bundle bundle, f fVar, ResultReceiver resultReceiver) {
        c cVar = new c(this, str, resultReceiver);
        b(str, bundle, cVar);
        if (cVar.b()) {
            return;
        }
        throw new IllegalStateException("onSearch must call detach() or sendResult() before returning for query=" + str);
    }

    public void a(String str, f fVar, IBinder iBinder, Bundle bundle) {
        List<ja<IBinder, Bundle>> arrayList = fVar.d.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        for (ja<IBinder, Bundle> jaVar : arrayList) {
            if (iBinder == jaVar.f8034a && oe.a(bundle, jaVar.b)) {
                return;
            }
        }
        arrayList.add(new ja<>(iBinder, bundle));
        fVar.d.put(str, arrayList);
        a(str, fVar, bundle, (Bundle) null);
        a(str, bundle);
    }

    public boolean a(String str, f fVar, IBinder iBinder) {
        boolean z = false;
        try {
            if (iBinder == null) {
                return fVar.d.remove(str) != null;
            }
            List<ja<IBinder, Bundle>> list = fVar.d.get(str);
            if (list != null) {
                Iterator<ja<IBinder, Bundle>> it = list.iterator();
                while (it.hasNext()) {
                    if (iBinder == it.next().f8034a) {
                        it.remove();
                        z = true;
                    }
                }
                if (list.size() == 0) {
                    fVar.d.remove(str);
                }
            }
            return z;
        } finally {
            a(str);
        }
    }

    public void a(String str, f fVar, Bundle bundle, Bundle bundle2) {
        a aVar = new a(str, fVar, str, bundle, bundle2);
        if (bundle == null) {
            a(str, aVar);
        } else {
            a(str, aVar, bundle);
        }
        if (aVar.b()) {
            return;
        }
        throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + fVar.f1323a + " id=" + str);
    }

    public List<MediaBrowserCompat.MediaItem> a(List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
        if (list == null) {
            return null;
        }
        int i2 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
        int i3 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
        if (i2 == -1 && i3 == -1) {
            return list;
        }
        int i4 = i3 * i2;
        int size = i4 + i3;
        if (i2 >= 0 && i3 >= 1 && i4 < list.size()) {
            if (size > list.size()) {
                size = list.size();
            }
            return list.subList(i4, size);
        }
        return Collections.emptyList();
    }

    public void a(String str, f fVar, ResultReceiver resultReceiver) {
        b bVar = new b(this, str, resultReceiver);
        b(str, bVar);
        if (bVar.b()) {
            return;
        }
        throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + str);
    }

    public void a(String str, Bundle bundle, f fVar, ResultReceiver resultReceiver) {
        d dVar = new d(this, str, resultReceiver);
        a(str, bundle, dVar);
        if (dVar.b()) {
            return;
        }
        throw new IllegalStateException("onCustomAction must call detach() or sendResult() or sendError() before returning for action=" + str + " extras=" + bundle);
    }
}
