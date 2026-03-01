package net.grandcentrix.tray.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import io.dcloud.common.constant.AbsoluteConst;
import java.util.Date;
import supwisdom.vr1;
import supwisdom.xr1;
import supwisdom.zr1;

/* JADX INFO: loaded from: classes3.dex */
public class TrayContentProvider extends ContentProvider {
    public static UriMatcher c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public zr1 f6786a;
    public zr1 b;

    public SQLiteDatabase a(Uri uri) {
        return d(uri) ? this.b.getReadableDatabase() : this.f6786a.getReadableDatabase();
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        a(providerInfo.authority);
        vr1.b("TrayContentProvider registered for authority: " + providerInfo.authority);
    }

    public String b(Uri uri) {
        if (uri == null) {
            return null;
        }
        int iMatch = c.match(uri);
        return (iMatch == 110 || iMatch == 120 || iMatch == 130) ? "TrayInternal" : "TrayPreferences";
    }

    public SQLiteDatabase c(Uri uri) {
        return d(uri) ? this.b.getWritableDatabase() : this.f6786a.getWritableDatabase();
    }

    public boolean d(Uri uri) {
        return !AbsoluteConst.FALSE.equals(uri.getQueryParameter("backup"));
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int delete(android.net.Uri r6, java.lang.String r7, java.lang.String[] r8) {
        /*
            r5 = this;
            android.content.UriMatcher r0 = net.grandcentrix.tray.provider.TrayContentProvider.c
            int r0 = r0.match(r6)
            r1 = 10
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L38
            r1 = 20
            if (r0 == r1) goto L51
            r1 = 30
            if (r0 == r1) goto L69
            r1 = 110(0x6e, float:1.54E-43)
            if (r0 == r1) goto L38
            r1 = 120(0x78, float:1.68E-43)
            if (r0 == r1) goto L51
            r1 = 130(0x82, float:1.82E-43)
            if (r0 != r1) goto L21
            goto L69
        L21:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r0 = "Delete is not supported for Uri: "
            r8.append(r0)
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            r7.<init>(r6)
            throw r7
        L38:
            java.lang.String r0 = "KEY = ?"
            java.lang.String r7 = supwisdom.xr1.a(r7, r0)
            java.lang.String[] r0 = new java.lang.String[r3]
            java.util.List r1 = r6.getPathSegments()
            r4 = 2
            java.lang.Object r1 = r1.get(r4)
            java.lang.String r1 = (java.lang.String) r1
            r0[r2] = r1
            java.lang.String[] r8 = supwisdom.xr1.a(r8, r0)
        L51:
            java.lang.String r0 = "MODULE = ?"
            java.lang.String r7 = supwisdom.xr1.a(r7, r0)
            java.lang.String[] r0 = new java.lang.String[r3]
            java.util.List r1 = r6.getPathSegments()
            java.lang.Object r1 = r1.get(r3)
            java.lang.String r1 = (java.lang.String) r1
            r0[r2] = r1
            java.lang.String[] r8 = supwisdom.xr1.a(r8, r0)
        L69:
            java.lang.String r0 = "backup"
            java.lang.String r0 = r6.getQueryParameter(r0)
            if (r0 != 0) goto L8f
            supwisdom.zr1 r0 = r5.f6786a
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            java.lang.String r1 = r5.b(r6)
            int r0 = r0.delete(r1, r7, r8)
            supwisdom.zr1 r1 = r5.b
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()
            java.lang.String r2 = r5.b(r6)
            int r7 = r1.delete(r2, r7, r8)
            int r0 = r0 + r7
            goto L9b
        L8f:
            android.database.sqlite.SQLiteDatabase r0 = r5.c(r6)
            java.lang.String r1 = r5.b(r6)
            int r0 = r0.delete(r1, r7, r8)
        L9b:
            if (r0 <= 0) goto La9
            android.content.Context r7 = r5.getContext()
            android.content.ContentResolver r7 = r7.getContentResolver()
            r8 = 0
            r7.notifyChange(r6, r8)
        La9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.grandcentrix.tray.provider.TrayContentProvider.delete(android.net.Uri, java.lang.String, java.lang.String[]):int");
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        Date date = new Date();
        int iMatch = c.match(uri);
        if (iMatch != 10 && iMatch != 110) {
            throw new IllegalArgumentException("Insert is not supported for Uri: " + uri);
        }
        contentValues.put("CREATED", Long.valueOf(date.getTime()));
        contentValues.put("UPDATED", Long.valueOf(date.getTime()));
        contentValues.put("MODULE", uri.getPathSegments().get(1));
        contentValues.put("KEY", uri.getPathSegments().get(2));
        int iA = a(c(uri), b(uri), "MODULE = ?AND KEY = ?", new String[]{contentValues.getAsString("MODULE"), contentValues.getAsString("KEY")}, contentValues, new String[]{"CREATED"});
        if (iA >= 0) {
            getContext().getContentResolver().notifyChange(uri, null);
            return uri;
        }
        if (iA == -1) {
            vr1.c("Couldn't update or insert data. Uri: " + uri);
        } else {
            vr1.c("unknown SQLite error");
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.b = new zr1(getContext(), true);
        this.f6786a = new zr1(getContext(), false);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0063 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0065  */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.database.Cursor query(android.net.Uri r15, java.lang.String[] r16, java.lang.String r17, java.lang.String[] r18, java.lang.String r19) {
        /*
            Method dump skipped, instruction units count: 233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.grandcentrix.tray.provider.TrayContentProvider.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    @Override // android.content.ContentProvider
    public void shutdown() {
        this.b.close();
        this.f6786a.close();
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("not implemented");
    }

    public int a(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr, ContentValues contentValues, String[] strArr2) {
        return xr1.a(sQLiteDatabase, str, str2, strArr, contentValues, strArr2);
    }

    public static void a(String str) {
        UriMatcher uriMatcher = new UriMatcher(-1);
        c = uriMatcher;
        uriMatcher.addURI(str, "preferences", 30);
        c.addURI(str, "preferences/*", 20);
        c.addURI(str, "preferences/*/*", 10);
        c.addURI(str, "internal_preferences", 130);
        c.addURI(str, "internal_preferences/*", 120);
        c.addURI(str, "internal_preferences/*/*", 110);
    }
}
