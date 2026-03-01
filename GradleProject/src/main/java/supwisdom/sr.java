package supwisdom;

import android.os.Bundle;
import android.util.Log;

/* JADX INFO: compiled from: DDMediaMessage.java */
/* JADX INFO: loaded from: classes.dex */
public class sr {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9205a;
    public String b;
    public String c;
    public byte[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f9206e;
    public b f;

    /* JADX INFO: compiled from: DDMediaMessage.java */
    public interface b {
        int a();

        boolean checkArgs();

        void serialize(Bundle bundle);

        int type();

        void unserialize(Bundle bundle);
    }

    public sr() {
        this(null);
    }

    public final boolean a() {
        byte[] bArr = this.d;
        if (bArr != null && bArr.length > 32768) {
            Log.e("DDMediaMessage", "checkArgs fail, thumbData is invalid");
            return false;
        }
        String str = this.b;
        if (str != null && str.length() > 512) {
            Log.e("DDMediaMessage", "checkArgs fail, title is invalid");
            return false;
        }
        String str2 = this.c;
        if (str2 != null && str2.length() > 1024) {
            Log.e("DDMediaMessage", "checkArgs fail, content is invalid");
            return false;
        }
        b bVar = this.f;
        if (bVar != null) {
            return bVar.checkArgs();
        }
        Log.e("DDMediaMessage", "checkArgs fail, mediaObject is null");
        return false;
    }

    public final int b() {
        b bVar = this.f;
        if (bVar == null) {
            return Integer.MAX_VALUE;
        }
        return bVar.a();
    }

    public final int c() {
        b bVar = this.f;
        if (bVar == null) {
            return 0;
        }
        return bVar.type();
    }

    public sr(b bVar) {
        this.f = bVar;
    }

    /* JADX INFO: compiled from: DDMediaMessage.java */
    public static class a {
        public static Bundle a(sr srVar) {
            b bVar;
            Bundle bundle = new Bundle();
            bundle.putInt("android.intent.ding.EXTRA_AP_OBJECT_SDK_VERSION", srVar.f9205a);
            bundle.putString("android.intent.ding.EXTRA_AP_OBJECT_TITLE", srVar.b);
            bundle.putString("android.intent.ding.EXTRA_AP_OBJECT_DESCRIPTION", srVar.c);
            bundle.putByteArray("android.intent.ding.EXTRA_AP_OBJECT_THUMB_DATA", srVar.d);
            bundle.putString("android.intent.ding.EXTRA_AP_OBJECT_THUMB_URL", srVar.f9206e);
            if (srVar != null && (bVar = srVar.f) != null) {
                bundle.putString("android.intent.ding.EXTRA_AP_OBJECT_IDENTIFIER", bVar.getClass().getName());
                srVar.f.serialize(bundle);
            }
            return bundle;
        }

        public static sr a(Bundle bundle) {
            if (bundle == null) {
                return null;
            }
            sr srVar = new sr();
            srVar.f9205a = bundle.getInt("android.intent.ding.EXTRA_AP_OBJECT_SDK_VERSION");
            srVar.b = bundle.getString("android.intent.ding.EXTRA_AP_OBJECT_TITLE");
            srVar.c = bundle.getString("android.intent.ding.EXTRA_AP_OBJECT_DESCRIPTION");
            srVar.d = bundle.getByteArray("android.intent.ding.EXTRA_AP_OBJECT_THUMB_DATA");
            srVar.f9206e = bundle.getString("android.intent.ding.EXTRA_AP_OBJECT_THUMB_URL");
            String string = bundle.getString("android.intent.ding.EXTRA_AP_OBJECT_IDENTIFIER");
            if (string != null && string.length() > 0) {
                try {
                    b bVar = (b) Class.forName(string).newInstance();
                    srVar.f = bVar;
                    bVar.unserialize(bundle);
                    return srVar;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    Log.e("DDMediaMessage", "get media object from bundle failed: unknown ident " + string);
                }
            }
            return srVar;
        }
    }
}
