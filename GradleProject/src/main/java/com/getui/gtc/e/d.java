package com.getui.gtc.e;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Base64;
import com.getui.gtc.base.crypt.SecureCryptTools;
import com.getui.gtc.base.db.AbstractTable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class d extends AbstractTable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2214a;
    public long b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f2215e;
    public String f;
    public String g;
    public String h;
    public long i;
    public long j;
    public long k;
    public final Set<String> l = new HashSet();

    private String a(int i) {
        Cursor cursorQuery = null;
        try {
            cursorQuery = getReadableDatabase().query("r", new String[]{"a", "b"}, "a=?", new String[]{String.valueOf(i)}, null, null, null);
            if (cursorQuery != null && cursorQuery.moveToNext()) {
                return cursorQuery.getString(1);
            }
            if (cursorQuery == null) {
                return "";
            }
        } catch (Throwable th) {
            try {
                com.getui.gtc.i.c.a.a(th);
                if (cursorQuery == null) {
                    return "";
                }
            } finally {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        }
        cursorQuery.close();
        return "";
    }

    public final JSONObject a() {
        try {
            String strA = a(18);
            if (TextUtils.isEmpty(strA)) {
                return null;
            }
            return new JSONObject(new String(SecureCryptTools.getInstance().decrypt(Base64.decode(strA, 0))));
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
            return null;
        }
    }

    public final void a(String str) {
        if (a(10, str)) {
            this.f2215e = str;
        }
    }

    public final void a(Collection<String> collection) {
        if (collection.size() <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.l);
        arrayList.addAll(collection);
        StringBuilder sb = new StringBuilder();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            sb.append((String) arrayList.get(i));
            if (i < size - 1) {
                sb.append(",");
            }
        }
        if (a(8, sb.toString())) {
            this.l.addAll(collection);
        }
    }

    public final void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (jSONObject.length() != 0) {
                    a(18, Base64.encodeToString(SecureCryptTools.getInstance().encrypt(jSONObject.toString().getBytes()), 0));
                    return;
                }
            } catch (Throwable th) {
                com.getui.gtc.i.c.a.b(th);
                return;
            }
        }
        a(18, "");
    }

    public final boolean a(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("a", Integer.valueOf(i));
        contentValues.put("b", Long.valueOf(j));
        return replace(null, contentValues) != -1;
    }

    public final boolean a(int i, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("a", Integer.valueOf(i));
        contentValues.put("b", str);
        return replace(null, contentValues) != -1;
    }

    public final JSONObject b() {
        try {
            String strA = a(17);
            if (!TextUtils.isEmpty(strA)) {
                return new JSONObject(new String(SecureCryptTools.getInstance().decrypt(Base64.decode(strA, 0))));
            }
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
        }
        return new JSONObject();
    }

    public final void b(String str) {
        try {
            if (a(11, Base64.encodeToString(SecureCryptTools.getInstance().encrypt(str.getBytes()), 0))) {
                this.f = str;
            }
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
        }
    }

    public final void b(JSONObject jSONObject) {
        try {
            a(17, Base64.encodeToString(SecureCryptTools.getInstance().encrypt(jSONObject.toString().getBytes()), 0));
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
        }
    }

    public final void c(String str) {
        try {
            if (a(12, Base64.encodeToString(SecureCryptTools.getInstance().encrypt(str.getBytes()), 0))) {
                this.g = str;
            }
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
        }
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public String createSql() {
        return "CREATE TABLE IF NOT EXISTS r (a TEXT PRIMARY KEY, b TEXT)";
    }

    public final void d(String str) {
        if (a(7, str)) {
            this.f2214a = str;
        }
    }

    public final void e(String str) {
        if (TextUtils.isEmpty(str) || !this.l.contains(str)) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.l);
        arrayList.remove(str);
        StringBuilder sb = new StringBuilder();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            sb.append((String) arrayList.get(i));
            if (i < size - 1) {
                sb.append(",");
            }
        }
        if (a(8, sb.toString())) {
            this.l.remove(str);
        }
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public String getTableName() {
        return "r";
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public void initCache() {
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = getReadableDatabase().query("r", new String[]{"a", "b"}, null, null, null, null, null);
                if (cursorQuery != null) {
                    while (cursorQuery.moveToNext()) {
                        switch (cursorQuery.getInt(0)) {
                            case 4:
                                this.c = cursorQuery.getString(1);
                                continue;
                            case 5:
                            default:
                                continue;
                            case 6:
                                this.b = cursorQuery.getLong(1);
                                continue;
                            case 7:
                                this.f2214a = cursorQuery.getString(1);
                                continue;
                            case 8:
                                String string = cursorQuery.getString(1);
                                if (TextUtils.isEmpty(string)) {
                                    continue;
                                } else {
                                    this.l.addAll(Arrays.asList(string.split(",")));
                                }
                                break;
                            case 9:
                                this.d = cursorQuery.getString(1);
                                continue;
                            case 10:
                                this.f2215e = cursorQuery.getString(1);
                                continue;
                            case 11:
                                try {
                                    String string2 = cursorQuery.getString(1);
                                    if (TextUtils.isEmpty(string2)) {
                                        continue;
                                    } else {
                                        this.f = new String(SecureCryptTools.getInstance().decrypt(Base64.decode(string2, 0)));
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    com.getui.gtc.i.c.a.b(th);
                                }
                                break;
                            case 12:
                                try {
                                    String string3 = cursorQuery.getString(1);
                                    if (TextUtils.isEmpty(string3)) {
                                        continue;
                                    } else {
                                        this.g = new String(SecureCryptTools.getInstance().decrypt(Base64.decode(string3, 0)));
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    com.getui.gtc.i.c.a.b(th);
                                }
                                break;
                            case 13:
                                try {
                                    String string4 = cursorQuery.getString(1);
                                    if (TextUtils.isEmpty(string4)) {
                                        continue;
                                    } else {
                                        this.h = new String(SecureCryptTools.getInstance().decrypt(Base64.decode(string4, 0)));
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    com.getui.gtc.i.c.a.b(th);
                                }
                                break;
                            case 14:
                                this.i = cursorQuery.getLong(1);
                                continue;
                            case 15:
                                this.j = cursorQuery.getLong(1);
                                continue;
                            case 16:
                                this.k = cursorQuery.getLong(1);
                                continue;
                        }
                        com.getui.gtc.i.c.a.b(th);
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Exception e2) {
                com.getui.gtc.i.c.a.a(e2);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        } catch (Throwable th4) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th4;
        }
    }
}
