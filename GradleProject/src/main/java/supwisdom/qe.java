package supwisdom;

import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import supwisdom.pe;

/* JADX INFO: compiled from: MediaBrowserServiceCompatApi23.java */
/* JADX INFO: loaded from: classes.dex */
public class qe {

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi23.java */
    public static class a extends pe.b {
        public a(Context context, b bVar) {
            super(context, bVar);
        }

        @Override // android.service.media.MediaBrowserService
        public void onLoadItem(String str, MediaBrowserService.Result<MediaBrowser.MediaItem> result) {
            ((b) this.f8791a).a(str, new pe.c<>(result));
        }
    }

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi23.java */
    public interface b extends pe.d {
        void a(String str, pe.c<Parcel> cVar);
    }

    public static Object a(Context context, b bVar) {
        return new a(context, bVar);
    }
}
