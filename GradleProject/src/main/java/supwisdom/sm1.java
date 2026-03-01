package supwisdom;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: CalendarReminderUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class sm1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Uri f9191a = CalendarContract.Calendars.CONTENT_URI;
    public static Uri b = CalendarContract.Events.CONTENT_URI;
    public static Uri c = CalendarContract.Reminders.CONTENT_URI;
    public static String d = "boohee";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f9192e = "BOOHEE@boohee.com";
    public static String f = "com.android.boohee";
    public static String g = "BOOHEE账户";

    public static long a(Context context) {
        TimeZone timeZone = TimeZone.getDefault();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", d);
        contentValues.put("account_name", f9192e);
        contentValues.put("account_type", f);
        contentValues.put("calendar_displayName", g);
        contentValues.put("visible", (Integer) 1);
        contentValues.put("calendar_color", (Integer) (-16776961));
        contentValues.put("calendar_access_level", (Integer) 700);
        contentValues.put("sync_events", (Integer) 1);
        contentValues.put("calendar_timezone", timeZone.getID());
        contentValues.put("ownerAccount", f9192e);
        contentValues.put("canOrganizerRespond", (Integer) 0);
        Uri uriInsert = context.getContentResolver().insert(f9191a.buildUpon().appendQueryParameter("caller_is_syncadapter", "true").appendQueryParameter("account_name", f9192e).appendQueryParameter("account_type", f).build(), contentValues);
        if (uriInsert == null) {
            return -1L;
        }
        return ContentUris.parseId(uriInsert);
    }

    public static int b(Context context) {
        int iC = c(context);
        if (iC >= 0) {
            return iC;
        }
        if (a(context) >= 0) {
            return c(context);
        }
        return -1;
    }

    public static int c(Context context) {
        Cursor cursorQuery = context.getContentResolver().query(f9191a, null, null, null, null);
        if (cursorQuery == null) {
            return -1;
        }
        try {
            if (cursorQuery.getCount() <= 0) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return -1;
            }
            cursorQuery.moveToFirst();
            int i = cursorQuery.getInt(cursorQuery.getColumnIndex(com.umeng.analytics.pro.bq.d));
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return i;
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    public static void a(Context context, String str, String str2, String str3, long j, long j2, int i, String str4) {
        int iB;
        if (context != null && (iB = b(context)) >= 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j);
            long time = calendar.getTime().getTime();
            calendar.setTimeInMillis(j2);
            long time2 = calendar.getTime().getTime();
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", str2);
            contentValues.put("description", str3);
            contentValues.put("calendar_id", Integer.valueOf(iB));
            contentValues.put("dtstart", Long.valueOf(time));
            contentValues.put("dtend", Long.valueOf(time2));
            contentValues.put("hasAlarm", (Integer) 1);
            contentValues.put("eventTimezone", "Asia/Shanghai");
            Uri uriInsert = context.getContentResolver().insert(b, contentValues);
            if (str4 != null && !"".equals(str4)) {
                contentValues.put("rrule", str4);
            }
            if (uriInsert == null) {
            }
        }
    }
}
