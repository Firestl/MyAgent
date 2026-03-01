package supwisdom;

import androidx.annotation.RecentlyNonNull;
import com.huawei.hms.framework.common.ContainerUtils;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class of0 {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<String> f8664a;
        public final Object b;

        public a(Object obj) {
            pf0.a(obj);
            this.b = obj;
            this.f8664a = new ArrayList();
        }

        @RecentlyNonNull
        public final a a(@RecentlyNonNull String str, Object obj) {
            List<String> list = this.f8664a;
            pf0.a(str);
            String str2 = str;
            String strValueOf = String.valueOf(obj);
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(strValueOf).length());
            sb.append(str2);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(strValueOf);
            list.add(sb.toString());
            return this;
        }

        @RecentlyNonNull
        public final String toString() {
            StringBuilder sb = new StringBuilder(100);
            sb.append(this.b.getClass().getSimpleName());
            sb.append(Operators.BLOCK_START);
            int size = this.f8664a.size();
            for (int i = 0; i < size; i++) {
                sb.append(this.f8664a.get(i));
                if (i < size - 1) {
                    sb.append(", ");
                }
            }
            sb.append(Operators.BLOCK_END);
            return sb.toString();
        }
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static int a(@RecentlyNonNull Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @RecentlyNonNull
    public static a a(@RecentlyNonNull Object obj) {
        return new a(obj);
    }
}
