package supwisdom;

import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: SubripDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class d60 extends c60 {
    public static final Pattern o = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+),(\\d+))\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+),(\\d+))?\\s*");
    public final StringBuilder n;

    public d60() {
        super("SubripDecoder");
        this.n = new StringBuilder();
    }

    @Override // supwisdom.c60
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public e60 a(byte[] bArr, int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        j80 j80Var = new j80();
        o80 o80Var = new o80(bArr, i);
        while (true) {
            String strY = o80Var.y();
            if (strY == null) {
                y50[] y50VarArr = new y50[arrayList.size()];
                arrayList.toArray(y50VarArr);
                return new e60(y50VarArr, j80Var.b());
            }
            if (strY.length() != 0) {
                try {
                    Integer.parseInt(strY);
                    String strY2 = o80Var.y();
                    Matcher matcher = o.matcher(strY2);
                    if (matcher.matches()) {
                        boolean z2 = true;
                        j80Var.a(a(matcher, 1));
                        if (TextUtils.isEmpty(matcher.group(6))) {
                            z2 = false;
                        } else {
                            j80Var.a(a(matcher, 6));
                        }
                        this.n.setLength(0);
                        while (true) {
                            String strY3 = o80Var.y();
                            if (TextUtils.isEmpty(strY3)) {
                                break;
                            }
                            if (this.n.length() > 0) {
                                this.n.append("<br>");
                            }
                            this.n.append(strY3.trim());
                        }
                        arrayList.add(new y50(Html.fromHtml(this.n.toString())));
                        if (z2) {
                            arrayList.add(null);
                        }
                    } else {
                        Log.w("SubripDecoder", "Skipping invalid timing: " + strY2);
                    }
                } catch (NumberFormatException unused) {
                    Log.w("SubripDecoder", "Skipping invalid index: " + strY);
                }
            }
        }
    }

    public static long a(Matcher matcher, int i) {
        return ((Long.parseLong(matcher.group(i + 1)) * 60 * 60 * 1000) + (Long.parseLong(matcher.group(i + 2)) * 60 * 1000) + (Long.parseLong(matcher.group(i + 3)) * 1000) + Long.parseLong(matcher.group(i + 4))) * 1000;
    }
}
