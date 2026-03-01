package org.bouncycastle.asn1.x509;

/* JADX INFO: loaded from: classes3.dex */
public class X509NameTokenizer {
    public StringBuffer buf;
    public int index;
    public char separator;
    public String value;

    public X509NameTokenizer(String str) {
        this(str, ',');
    }

    public X509NameTokenizer(String str, char c) {
        this.buf = new StringBuffer();
        this.value = str;
        this.index = -1;
        this.separator = c;
    }

    public boolean hasMoreTokens() {
        return this.index != this.value.length();
    }

    public String nextToken() {
        if (this.index == this.value.length()) {
            return null;
        }
        int i = this.index + 1;
        this.buf.setLength(0);
        boolean z = false;
        boolean z2 = false;
        while (i != this.value.length()) {
            char cCharAt = this.value.charAt(i);
            if (cCharAt != '\"') {
                if (!z && !z2) {
                    if (cCharAt == '\\') {
                        this.buf.append(cCharAt);
                        z = true;
                    } else {
                        if (cCharAt == this.separator) {
                            break;
                        }
                        this.buf.append(cCharAt);
                    }
                }
                i++;
            } else if (!z) {
                z2 = !z2;
            }
            this.buf.append(cCharAt);
            z = false;
            i++;
        }
        this.index = i;
        return this.buf.toString();
    }
}
