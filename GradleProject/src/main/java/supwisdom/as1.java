package supwisdom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.grandcentrix.tray.core.TrayException;

/* JADX INFO: compiled from: TrayProviderHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public class as1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f6976a;

    public as1(Context context) {
        this.f6976a = context;
        new bs1(context);
    }

    public boolean a(Uri uri, String str) {
        return a(uri, str, null);
    }

    public List<ur1> b(Uri uri) {
        try {
            return a(uri);
        } catch (TrayException unused) {
            return new ArrayList();
        }
    }

    public boolean a(Uri uri, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("VALUE", str);
        contentValues.put("MIGRATED_KEY", str2);
        try {
            return this.f6976a.getContentResolver().insert(uri, contentValues) != null;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public List<ur1> a(Uri uri) throws TrayException {
        try {
            Cursor cursorQuery = this.f6976a.getContentResolver().query(uri, null, null, null, null);
            if (cursorQuery != null) {
                ArrayList arrayList = new ArrayList();
                for (boolean zMoveToFirst = cursorQuery.moveToFirst(); zMoveToFirst; zMoveToFirst = cursorQuery.moveToNext()) {
                    arrayList.add(a(cursorQuery));
                }
                cursorQuery.close();
                return arrayList;
            }
            throw new TrayException("could not access stored data with uri " + uri);
        } catch (Throwable th) {
            throw new TrayException("Hard error accessing the ContentProvider", th);
        }
    }

    public static ur1 a(Cursor cursor) {
        return new ur1(cursor.getString(cursor.getColumnIndexOrThrow("MODULE")), cursor.getString(cursor.getColumnIndexOrThrow("KEY")), cursor.getString(cursor.getColumnIndexOrThrow("MIGRATED_KEY")), cursor.getString(cursor.getColumnIndexOrThrow("VALUE")), new Date(cursor.getLong(cursor.getColumnIndexOrThrow("CREATED"))), new Date(cursor.getLong(cursor.getColumnIndexOrThrow("UPDATED"))));
    }
}
