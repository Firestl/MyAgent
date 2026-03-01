package supwisdom;

import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import supwisdom.qe;

/* JADX INFO: compiled from: MediaBrowserServiceCompatApi26.java */
/* JADX INFO: loaded from: classes.dex */
public class re {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Field f9052a;

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi26.java */
    public static class a extends qe.a {
        public a(Context context, c cVar) {
            super(context, cVar);
        }

        @Override // android.service.media.MediaBrowserService
        public void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            ((c) this.f8791a).a(str, new b(result), bundle);
        }
    }

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi26.java */
    public interface c extends qe.b {
        void a(String str, b bVar, Bundle bundle);
    }

    static {
        try {
            Field declaredField = MediaBrowserService.Result.class.getDeclaredField("mFlags");
            f9052a = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e2) {
            Log.w("MBSCompatApi26", e2);
        }
    }

    public static Object a(Context context, c cVar) {
        return new a(context, cVar);
    }

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi26.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public MediaBrowserService.Result f9053a;

        public b(MediaBrowserService.Result result) {
            this.f9053a = result;
        }

        public void a(List<Parcel> list, int i) {
            try {
                re.f9052a.setInt(this.f9053a, i);
            } catch (IllegalAccessException e2) {
                Log.w("MBSCompatApi26", e2);
            }
            this.f9053a.sendResult(a(list));
        }

        public List<MediaBrowser.MediaItem> a(List<Parcel> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcel parcel : list) {
                parcel.setDataPosition(0);
                arrayList.add(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
            }
            return arrayList;
        }
    }
}
