package org.bouncycastle.util.io.pem;

import com.google.crypto.tink.subtle.PemKeyType;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Base64;

/* JADX INFO: loaded from: classes3.dex */
public class PemWriter extends BufferedWriter {
    public static final int LINE_LENGTH = 64;
    public char[] buf;
    public final int nlLength;

    public PemWriter(Writer writer) {
        super(writer);
        this.buf = new char[64];
        String strLineSeparator = Strings.lineSeparator();
        this.nlLength = strLineSeparator != null ? strLineSeparator.length() : 2;
    }

    private void writeEncoded(byte[] bArr) throws IOException {
        int i;
        byte[] bArrEncode = Base64.encode(bArr);
        int length = 0;
        while (length < bArrEncode.length) {
            int i2 = 0;
            while (true) {
                char[] cArr = this.buf;
                if (i2 == cArr.length || (i = length + i2) >= bArrEncode.length) {
                    break;
                }
                cArr[i2] = (char) bArrEncode[i];
                i2++;
            }
            write(this.buf, 0, i2);
            newLine();
            length += this.buf.length;
        }
    }

    private void writePostEncapsulationBoundary(String str) throws IOException {
        write("-----END " + str + PemKeyType.MARKER);
        newLine();
    }

    private void writePreEncapsulationBoundary(String str) throws IOException {
        write("-----BEGIN " + str + PemKeyType.MARKER);
        newLine();
    }

    public int getOutputSize(PemObject pemObject) {
        int length = ((pemObject.getType().length() + 10 + this.nlLength) * 2) + 6 + 4;
        if (!pemObject.getHeaders().isEmpty()) {
            for (PemHeader pemHeader : pemObject.getHeaders()) {
                length += pemHeader.getName().length() + 2 + pemHeader.getValue().length() + this.nlLength;
            }
            length += this.nlLength;
        }
        return length + (((pemObject.getContent().length + 2) / 3) * 4) + ((((r5 + 64) - 1) / 64) * this.nlLength);
    }

    public void writeObject(PemObjectGenerator pemObjectGenerator) throws IOException {
        PemObject pemObjectGenerate = pemObjectGenerator.generate();
        writePreEncapsulationBoundary(pemObjectGenerate.getType());
        if (!pemObjectGenerate.getHeaders().isEmpty()) {
            for (PemHeader pemHeader : pemObjectGenerate.getHeaders()) {
                write(pemHeader.getName());
                write(": ");
                write(pemHeader.getValue());
                newLine();
            }
            newLine();
        }
        writeEncoded(pemObjectGenerate.getContent());
        writePostEncapsulationBoundary(pemObjectGenerate.getType());
    }
}
