package supwisdom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.taobao.weex.ui.module.WXModalUIModule;
import java.util.Collections;
import org.json.JSONObject;
import supwisdom.vo;

/* JADX INFO: loaded from: classes.dex */
public class ro {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static a f9077a;

    public interface a {
        void a(boolean z, JSONObject jSONObject, String str);
    }

    public static boolean a(pp ppVar, Context context) {
        return bq.b(ppVar, context, Collections.singletonList(new vo.b("com.taobao.taobao", 0, "")));
    }

    public static boolean a(pp ppVar, Activity activity, int i, String str, String str2, a aVar) {
        try {
            so.a(ppVar, "biz", "TbStart");
            activity.startActivityForResult(new Intent(str2, Uri.parse(str)), i);
            f9077a = aVar;
            return true;
        } catch (Throwable th) {
            aVar.a(false, null, "UNKNOWN_ERROR");
            so.a(ppVar, "biz", "TbActFail", th);
            return false;
        }
    }

    public static boolean a(pp ppVar, int i, int i2, Intent intent) {
        if (i != 1010) {
            return false;
        }
        a aVar = f9077a;
        if (aVar == null) {
            return true;
        }
        f9077a = null;
        if (i2 != -1) {
            if (i2 != 0) {
                so.a(ppVar, "biz", "TbUnknown", "" + i2);
            } else {
                so.b(ppVar, "biz", "TbCancel", intent != null ? intent.toUri(1) : "");
                aVar.a(false, null, "CANCELED");
            }
        } else {
            so.b(ppVar, "biz", "TbOk", intent.toUri(1));
            aVar.a(true, bq.a(intent), WXModalUIModule.OK);
        }
        return true;
    }
}
