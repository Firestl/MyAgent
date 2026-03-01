package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: TrayItem.java */
/* JADX INFO: loaded from: classes3.dex */
public class ur1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Date f9433a;
    public final String b;
    public final String c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Date f9434e;
    public final String f;

    public ur1(String str, String str2, String str3, String str4, Date date, Date date2) {
        this.f9433a = date;
        this.b = str2;
        this.d = str;
        this.f9434e = date2;
        this.f = str4;
        this.c = str3;
    }

    public String a() {
        return this.f;
    }

    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy", Locale.US);
        return Operators.BLOCK_START_STR + "key: " + this.b + ", value: " + this.f + ", module: " + this.d + ", created: " + simpleDateFormat.format(this.f9433a) + ", updated: " + simpleDateFormat.format(this.f9434e) + ", migratedKey: " + this.c + Operators.BLOCK_END_STR;
    }
}
