package supwisdom;

import androidx.annotation.RecentlyNonNull;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.util.JSUtil;
import java.util.HashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public class sh0 {
    public static void a(@RecentlyNonNull StringBuilder sb, @RecentlyNonNull HashMap<String, String> map) {
        sb.append(Operators.BLOCK_START_STR);
        boolean z = true;
        for (String str : map.keySet()) {
            if (z) {
                z = false;
            } else {
                sb.append(",");
            }
            String str2 = map.get(str);
            sb.append(JSUtil.QUOTE);
            sb.append(str);
            sb.append("\":");
            if (str2 == null) {
                sb.append(com.igexin.push.core.b.m);
            } else {
                sb.append(JSUtil.QUOTE);
                sb.append(str2);
                sb.append(JSUtil.QUOTE);
            }
        }
        sb.append(Operators.BLOCK_END_STR);
    }
}
