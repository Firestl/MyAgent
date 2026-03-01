package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class v51 extends x51 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f9481a;
    public final j61 b;

    static {
        new v51("");
    }

    public v51(String str) {
        if (str == null) {
            throw new NullPointerException("string == null");
        }
        this.f9481a = str.intern();
        this.b = new j61(a(str));
    }

    public static byte[] a(String str) {
        int length = str.length();
        byte[] bArr = new byte[length * 3];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt != 0 && cCharAt < 128) {
                bArr[i] = (byte) cCharAt;
                i++;
            } else if (cCharAt < 2048) {
                bArr[i] = (byte) (((cCharAt >> 6) & 31) | 192);
                bArr[i + 1] = (byte) ((cCharAt & Operators.CONDITION_IF) | 128);
                i += 2;
            } else {
                bArr[i] = (byte) (((cCharAt >> '\f') & 15) | 224);
                bArr[i + 1] = (byte) (((cCharAt >> 6) & 63) | 128);
                bArr[i + 2] = (byte) ((cCharAt & Operators.CONDITION_IF) | 128);
                i += 3;
            }
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }

    @Override // supwisdom.u41
    public int b(u41 u41Var) {
        return this.f9481a.compareTo(((v51) u41Var).f9481a);
    }

    @Override // supwisdom.u41
    public String c() {
        return "utf8";
    }

    public j61 d() {
        return this.b;
    }

    public String e() {
        return this.f9481a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof v51) {
            return this.f9481a.equals(((v51) obj).f9481a);
        }
        return false;
    }

    public int f() {
        return this.f9481a.length();
    }

    public int g() {
        return this.b.a();
    }

    @Override // supwisdom.c61
    public b61 getType() {
        return b61.z;
    }

    public String h() {
        return Operators.QUOTE + toHuman() + Operators.QUOTE;
    }

    public int hashCode() {
        return this.f9481a.hashCode();
    }

    @Override // supwisdom.t61
    public String toHuman() {
        int length = this.f9481a.length();
        StringBuilder sb = new StringBuilder((length * 3) / 2);
        int i = 0;
        while (i < length) {
            char cCharAt = this.f9481a.charAt(i);
            if (cCharAt >= ' ' && cCharAt < 127) {
                if (cCharAt == '\'' || cCharAt == '\"' || cCharAt == '\\') {
                    sb.append('\\');
                }
                sb.append(cCharAt);
            } else if (cCharAt > 127) {
                sb.append("\\u");
                sb.append(Character.forDigit(cCharAt >> '\f', 16));
                sb.append(Character.forDigit((cCharAt >> '\b') & 15, 16));
                sb.append(Character.forDigit((cCharAt >> 4) & 15, 16));
                sb.append(Character.forDigit(cCharAt & 15, 16));
            } else if (cCharAt == '\t') {
                sb.append("\\t");
            } else if (cCharAt == '\n') {
                sb.append("\\n");
            } else if (cCharAt != '\r') {
                char cCharAt2 = i < length + (-1) ? this.f9481a.charAt(i + 1) : (char) 0;
                boolean z = cCharAt2 >= '0' && cCharAt2 <= '7';
                sb.append('\\');
                for (int i2 = 6; i2 >= 0; i2 -= 3) {
                    char c = (char) (((cCharAt >> i2) & 7) + 48);
                    if (c != '0' || z) {
                        sb.append(c);
                        z = true;
                    }
                }
                if (!z) {
                    sb.append('0');
                }
            } else {
                sb.append("\\r");
            }
            i++;
        }
        return sb.toString();
    }

    public String toString() {
        return "string{\"" + toHuman() + "\"}";
    }

    public String a(int i) {
        String str;
        String human = toHuman();
        if (human.length() <= i - 2) {
            str = "";
        } else {
            human = human.substring(0, i - 5);
            str = "...";
        }
        return Operators.QUOTE + human + str + Operators.QUOTE;
    }
}
