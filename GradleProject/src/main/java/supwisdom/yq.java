package supwisdom;

import io.dcloud.common.adapter.util.Logger;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class yq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public File f9904a;
    public jr b;

    public yq(String str, jr jrVar) {
        this.f9904a = null;
        this.b = null;
        this.f9904a = new File(str);
        this.b = jrVar;
    }

    public static String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "id");
            jSONObject.put("error", str);
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public final void a() {
        new Thread(new zq(this)).start();
    }

    public final synchronized void b() {
        if (this.f9904a == null) {
            return;
        }
        if (this.f9904a.exists() && this.f9904a.isDirectory() && this.f9904a.list().length != 0) {
            ArrayList arrayList = new ArrayList();
            for (String str : this.f9904a.list()) {
                arrayList.add(str);
            }
            Collections.sort(arrayList);
            String str2 = (String) arrayList.get(arrayList.size() - 1);
            int size = arrayList.size();
            if (str2.equals(new SimpleDateFormat(Logger.TIMESTAMP_YYYY_MM_DD).format(Calendar.getInstance().getTime()) + ".log")) {
                if (arrayList.size() < 2) {
                    return;
                }
                str2 = (String) arrayList.get(arrayList.size() - 2);
                size--;
            }
            if (!this.b.a(a(mq.a(this.f9904a.getAbsolutePath(), str2)))) {
                size--;
            }
            for (int i = 0; i < size; i++) {
                new File(this.f9904a, (String) arrayList.get(i)).delete();
            }
        }
    }
}
