package supwisdom;

import com.github.faucamp.simplertmp.amf.AmfType;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: AmfDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class n00 {

    /* JADX INFO: compiled from: AmfDecoder.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f8459a;

        static {
            int[] iArr = new int[AmfType.values().length];
            f8459a = iArr;
            try {
                iArr[AmfType.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8459a[AmfType.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8459a[AmfType.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8459a[AmfType.OBJECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8459a[AmfType.NULL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8459a[AmfType.UNDEFINED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f8459a[AmfType.MAP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f8459a[AmfType.ARRAY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static m00 a(InputStream inputStream) throws IOException {
        m00 q00Var;
        AmfType amfTypeValueOf = AmfType.valueOf((byte) inputStream.read());
        switch (a.f8459a[amfTypeValueOf.ordinal()]) {
            case 1:
                q00Var = new q00();
                break;
            case 2:
                q00Var = new l00();
                break;
            case 3:
                q00Var = new s00();
                break;
            case 4:
                q00Var = new r00();
                break;
            case 5:
                return new p00();
            case 6:
                return new t00();
            case 7:
                q00Var = new o00();
                break;
            case 8:
                q00Var = new k00();
                break;
            default:
                throw new IOException("Unknown/unimplemented AMF data type: " + amfTypeValueOf);
        }
        q00Var.a(inputStream);
        return q00Var;
    }
}
