package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.Serializable;
import supwisdom.qo1;
import supwisdom.uo1;
import supwisdom.wn1;
import supwisdom.yn1;
import supwisdom.yo1;

/* JADX INFO: loaded from: classes2.dex */
public class BufferedHeader implements wn1, Cloneable, Serializable {
    public static final long serialVersionUID = -2768352615787625448L;
    public final CharArrayBuffer buffer;
    public final String name;
    public final int valuePos;

    public BufferedHeader(CharArrayBuffer charArrayBuffer) throws ParseException {
        yo1.a(charArrayBuffer, "Char array buffer");
        int iIndexOf = charArrayBuffer.indexOf(58);
        if (iIndexOf == -1) {
            throw new ParseException("Invalid header: " + charArrayBuffer.toString());
        }
        String strSubstringTrimmed = charArrayBuffer.substringTrimmed(0, iIndexOf);
        if (strSubstringTrimmed.length() != 0) {
            this.buffer = charArrayBuffer;
            this.name = strSubstringTrimmed;
            this.valuePos = iIndexOf + 1;
        } else {
            throw new ParseException("Invalid header: " + charArrayBuffer.toString());
        }
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // supwisdom.wn1
    public CharArrayBuffer getBuffer() {
        return this.buffer;
    }

    @Override // supwisdom.xn1
    public yn1[] getElements() throws ParseException {
        uo1 uo1Var = new uo1(0, this.buffer.length());
        uo1Var.a(this.valuePos);
        return qo1.f8943a.a(this.buffer, uo1Var);
    }

    @Override // supwisdom.xn1
    public String getName() {
        return this.name;
    }

    @Override // supwisdom.xn1
    public String getValue() {
        CharArrayBuffer charArrayBuffer = this.buffer;
        return charArrayBuffer.substringTrimmed(this.valuePos, charArrayBuffer.length());
    }

    public int getValuePos() {
        return this.valuePos;
    }

    public String toString() {
        return this.buffer.toString();
    }
}
