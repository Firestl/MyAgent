package supwisdom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import io.dcloud.common.util.StringUtil;

/* JADX INFO: loaded from: classes.dex */
public class bu extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f7100a = StringUtil.format("CREATE TABLE %s (_id integer PRIMARY KEY NOT NULL,supportRanges integer NOT NULL,createAt long NOT NULL,uri varchar(255) NOT NULL,location varchar(255),path varchar(255) NOT NULL,size long NOT NULL, progress long NOT NULL,status integer NOT NULL, value1 varchar(255), value2 varchar(255));", "download_info");
    public static final String b = StringUtil.format("CREATE TABLE %s (_id integer PRIMARY KEY NOT NULL,threadId integer NOT NULL,downloadInfoId integer NOT NULL,uri varchar(255) NOT NULL,start long NOT NULL,end long NOT NULL,progress long NOT NULL);", "download_thread_info");

    public bu(Context context, ut utVar) {
        super(context, utVar.b(), (SQLiteDatabase.CursorFactory) null, utVar.c());
    }

    public final void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(f7100a);
        sQLiteDatabase.execSQL(b);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
