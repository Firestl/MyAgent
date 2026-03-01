package com.getui.gtc.dim.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Base64;
import cn.com.chinatelecom.account.api.a.d;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.crypt.CryptTools;
import com.getui.gtc.base.crypt.SecureCryptTools;
import com.getui.gtc.base.db.AbstractTable;
import com.getui.gtc.dim.b.h;
import com.getui.gtc.dim.e.c;
import io.dcloud.common.util.Md5Utils;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class b extends AbstractTable {
    public final h a(String str) {
        h hVar;
        Cursor cursor = null;
        hVar = null;
        h hVar2 = null;
        try {
            Cursor cursorQuery = getReadableDatabase().query(d.f1473a, new String[]{"t", "b"}, "a=?", new String[]{String.valueOf(str)}, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToNext()) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex("b"));
                        hVar = new h(null, cursorQuery.getLong(cursorQuery.getColumnIndex("t")));
                        try {
                            if (!TextUtils.isEmpty(string)) {
                                if (string.equals("-1")) {
                                    File file = new File(GtcProvider.context().getCacheDir(), CryptTools.digestToHexString(Md5Utils.ALGORITHM, (str + com.umeng.analytics.process.a.d).getBytes()));
                                    if (file.exists()) {
                                        string = new String(c.a(file));
                                    }
                                }
                                hVar.f2169a = c.a(SecureCryptTools.getInstance().decrypt(Base64.decode(string, 0)));
                            }
                            hVar2 = hVar;
                        } catch (Throwable th) {
                            th = th;
                            cursor = cursorQuery;
                            try {
                                com.getui.gtc.dim.e.b.a(th);
                                return hVar;
                            } finally {
                                if (cursor != null) {
                                    cursor.close();
                                }
                            }
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    hVar = null;
                }
            }
            if (cursorQuery == null) {
                return hVar2;
            }
            cursorQuery.close();
            return hVar2;
        } catch (Throwable th3) {
            th = th3;
            hVar = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() {
        /*
            Method dump skipped, instruction units count: 271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.a.b.a():void");
    }

    public final boolean a(String str, Object obj) {
        String strEncodeToString;
        try {
            try {
                strEncodeToString = Base64.encodeToString(SecureCryptTools.getInstance().encrypt(c.b(obj)), 0);
                if (strEncodeToString.length() > 5120) {
                    c.a(strEncodeToString.getBytes(), new File(GtcProvider.context().getCacheDir(), CryptTools.digestToHexString(Md5Utils.ALGORITHM, (str + com.umeng.analytics.process.a.d).getBytes())));
                    strEncodeToString = "-1";
                }
            } catch (Throwable unused) {
                strEncodeToString = "";
                com.getui.gtc.dim.e.b.b("dim storage save failed: ".concat(String.valueOf(obj)));
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("a", str);
            contentValues.put("t", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("b", strEncodeToString);
            com.getui.gtc.dim.e.b.a(str + " update dim storage cache = " + strEncodeToString);
            return replace(null, contentValues) != -1;
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a(th);
            return false;
        }
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public String createSql() {
        return "CREATE TABLE IF NOT EXISTS d (a TEXT PRIMARY KEY, t TEXT, b TEXT)";
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public String getTableName() {
        return d.f1473a;
    }
}
