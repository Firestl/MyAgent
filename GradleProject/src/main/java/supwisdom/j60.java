package supwisdom;

import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.util.Base64;
import java.util.Map;

/* JADX INFO: compiled from: TtmlRenderUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class j60 {
    public static k60 a(k60 k60Var, String[] strArr, Map<String, k60> map) {
        if (k60Var == null && strArr == null) {
            return null;
        }
        int i = 0;
        if (k60Var == null && strArr.length == 1) {
            return map.get(strArr[0]);
        }
        if (k60Var == null && strArr.length > 1) {
            k60 k60Var2 = new k60();
            int length = strArr.length;
            while (i < length) {
                k60Var2.a(map.get(strArr[i]));
                i++;
            }
            return k60Var2;
        }
        if (k60Var != null && strArr != null && strArr.length == 1) {
            k60Var.a(map.get(strArr[0]));
            return k60Var;
        }
        if (k60Var != null && strArr != null && strArr.length > 1) {
            int length2 = strArr.length;
            while (i < length2) {
                k60Var.a(map.get(strArr[i]));
                i++;
            }
        }
        return k60Var;
    }

    public static void a(SpannableStringBuilder spannableStringBuilder, int i, int i2, k60 k60Var) {
        if (k60Var.a() != -1) {
            spannableStringBuilder.setSpan(new StyleSpan(k60Var.a()), i, i2, 33);
        }
        if (k60Var.b()) {
            spannableStringBuilder.setSpan(new StrikethroughSpan(), i, i2, 33);
        }
        if (k60Var.c()) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i, i2, 33);
        }
        if (k60Var.f()) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(k60Var.e()), i, i2, 33);
        }
        if (k60Var.h()) {
            spannableStringBuilder.setSpan(new BackgroundColorSpan(k60Var.g()), i, i2, 33);
        }
        if (k60Var.d() != null) {
            spannableStringBuilder.setSpan(new TypefaceSpan(k60Var.d()), i, i2, 33);
        }
        if (k60Var.j() != null) {
            spannableStringBuilder.setSpan(new AlignmentSpan.Standard(k60Var.j()), i, i2, 33);
        }
        int iK = k60Var.k();
        if (iK == 1) {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) k60Var.l(), true), i, i2, 33);
        } else if (iK == 2) {
            spannableStringBuilder.setSpan(new RelativeSizeSpan(k60Var.l()), i, i2, 33);
        } else {
            if (iK != 3) {
                return;
            }
            spannableStringBuilder.setSpan(new RelativeSizeSpan(k60Var.l() / 100.0f), i, i2, 33);
        }
    }

    public static void a(SpannableStringBuilder spannableStringBuilder) {
        int length = spannableStringBuilder.length() - 1;
        while (length >= 0 && spannableStringBuilder.charAt(length) == ' ') {
            length--;
        }
        if (length < 0 || spannableStringBuilder.charAt(length) == '\n') {
            return;
        }
        spannableStringBuilder.append('\n');
    }

    public static String a(String str) {
        return str.replaceAll(Base64.CRLF, "\n").replaceAll(" *\n *", "\n").replaceAll("\n", Operators.SPACE_STR).replaceAll("[ \t\\x0B\f\r]+", Operators.SPACE_STR);
    }
}
