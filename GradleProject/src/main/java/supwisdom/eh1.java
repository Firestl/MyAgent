package supwisdom;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import com.squareup.picasso.Picasso;
import com.taobao.weex.common.Constants;
import java.io.IOException;
import supwisdom.lh1;

/* JADX INFO: compiled from: MediaStoreRequestHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class eh1 extends xg1 {
    public static final String[] b = {Constants.Name.ORIENTATION};

    /* JADX INFO: compiled from: MediaStoreRequestHandler.java */
    public enum a {
        MICRO(3, 96, 96),
        MINI(1, 512, 384),
        FULL(2, -1, -1);


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f7501a;
        public final int b;
        public final int c;

        a(int i, int i2, int i3) {
            this.f7501a = i;
            this.b = i2;
            this.c = i3;
        }
    }

    public eh1(Context context) {
        super(context);
    }

    @Override // supwisdom.xg1, supwisdom.lh1
    public boolean a(jh1 jh1Var) {
        Uri uri = jh1Var.d;
        return "content".equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }

    @Override // supwisdom.xg1, supwisdom.lh1
    public lh1.a a(jh1 jh1Var, int i) throws IOException {
        Bitmap thumbnail;
        ContentResolver contentResolver = this.f9764a.getContentResolver();
        int iA = a(contentResolver, jh1Var.d);
        String type = contentResolver.getType(jh1Var.d);
        boolean z = type != null && type.startsWith("video/");
        if (jh1Var.c()) {
            a aVarA = a(jh1Var.h, jh1Var.i);
            if (!z && aVarA == a.FULL) {
                return new lh1.a(null, c(jh1Var), Picasso.LoadedFrom.DISK, iA);
            }
            long id = ContentUris.parseId(jh1Var.d);
            BitmapFactory.Options optionsB = lh1.b(jh1Var);
            optionsB.inJustDecodeBounds = true;
            lh1.a(jh1Var.h, jh1Var.i, aVarA.b, aVarA.c, optionsB, jh1Var);
            if (z) {
                thumbnail = MediaStore.Video.Thumbnails.getThumbnail(contentResolver, id, aVarA == a.FULL ? 1 : aVarA.f7501a, optionsB);
            } else {
                thumbnail = MediaStore.Images.Thumbnails.getThumbnail(contentResolver, id, aVarA.f7501a, optionsB);
            }
            if (thumbnail != null) {
                return new lh1.a(thumbnail, null, Picasso.LoadedFrom.DISK, iA);
            }
        }
        return new lh1.a(null, c(jh1Var), Picasso.LoadedFrom.DISK, iA);
    }

    public static a a(int i, int i2) {
        a aVar = a.MICRO;
        if (i <= aVar.b && i2 <= aVar.c) {
            return aVar;
        }
        a aVar2 = a.MINI;
        return (i > aVar2.b || i2 > aVar2.c) ? a.FULL : aVar2;
    }

    public static int a(ContentResolver contentResolver, Uri uri) {
        Cursor cursorQuery = null;
        try {
            cursorQuery = contentResolver.query(uri, b, null, null, null);
            if (cursorQuery != null && cursorQuery.moveToFirst()) {
                int i = cursorQuery.getInt(0);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return i;
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return 0;
        } catch (RuntimeException unused) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return 0;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }
}
