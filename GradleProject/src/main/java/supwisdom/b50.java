package supwisdom;

import com.google.android.exoplayer2.f.a;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import supwisdom.l50;

/* JADX INFO: compiled from: GaplessInfoHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class b50 {
    public static final l50.a c = new a();
    public static final Pattern d = Pattern.compile("^ [0-9a-fA-F]{8} ([0-9a-fA-F]{8}) ([0-9a-fA-F]{8})");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f7024a = -1;
    public int b = -1;

    /* JADX INFO: compiled from: GaplessInfoHolder.java */
    public static class a implements l50.a {
        @Override // supwisdom.l50.a
        public boolean a(int i, int i2, int i3, int i4, int i5) {
            return i2 == 67 && i3 == 79 && i4 == 77 && (i5 == 77 || i == 2);
        }
    }

    public boolean a(int i) {
        int i2 = i >> 12;
        int i3 = i & 4095;
        if (i2 <= 0 && i3 <= 0) {
            return false;
        }
        this.f7024a = i2;
        this.b = i3;
        return true;
    }

    public boolean a(com.google.android.exoplayer2.f.a aVar) {
        for (int i = 0; i < aVar.a(); i++) {
            a.InterfaceC0053a interfaceC0053aA = aVar.a(i);
            if (interfaceC0053aA instanceof com.google.android.exoplayer2.f.b.e) {
                com.google.android.exoplayer2.f.b.e eVar = (com.google.android.exoplayer2.f.b.e) interfaceC0053aA;
                if (a(eVar.c, eVar.d)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean a(String str, String str2) {
        if (!"iTunSMPB".equals(str)) {
            return false;
        }
        Matcher matcher = d.matcher(str2);
        if (matcher.find()) {
            try {
                int i = Integer.parseInt(matcher.group(1), 16);
                int i2 = Integer.parseInt(matcher.group(2), 16);
                if (i > 0 || i2 > 0) {
                    this.f7024a = i;
                    this.b = i2;
                    return true;
                }
            } catch (NumberFormatException unused) {
            }
        }
        return false;
    }

    public boolean a() {
        return (this.f7024a == -1 || this.b == -1) ? false : true;
    }
}
