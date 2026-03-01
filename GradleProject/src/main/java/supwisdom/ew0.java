package supwisdom;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: IOUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class ew0 {
    public static void a(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception e2) {
            fw0.a(e2);
        }
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0034: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:22:0x0034 */
    public static byte[] a(Object obj) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream;
        Closeable closeable;
        Closeable closeable2 = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                } catch (IOException e2) {
                    e = e2;
                    objectOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    a(closeable2);
                    a((Closeable) byteArrayOutputStream);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                byteArrayOutputStream = null;
                objectOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = null;
            }
            try {
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                a((Closeable) objectOutputStream);
                a((Closeable) byteArrayOutputStream);
                return byteArray;
            } catch (IOException e4) {
                e = e4;
                fw0.a(e);
                a((Closeable) objectOutputStream);
                a((Closeable) byteArrayOutputStream);
                return null;
            }
        } catch (Throwable th3) {
            th = th3;
            closeable2 = closeable;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v7 */
    public static Object a(byte[] bArr) throws Throwable {
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream objectInputStream;
        try {
            if (bArr == 0) {
                return null;
            }
            try {
                byteArrayInputStream = new ByteArrayInputStream(bArr);
            } catch (Exception e2) {
                e = e2;
                objectInputStream = null;
                byteArrayInputStream = null;
            } catch (Throwable th) {
                byteArrayInputStream = null;
                th = th;
                bArr = 0;
            }
            try {
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
            } catch (Exception e3) {
                e = e3;
                objectInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                bArr = 0;
                a((Closeable) bArr);
                a((Closeable) byteArrayInputStream);
                throw th;
            }
            try {
                Object object = objectInputStream.readObject();
                a((Closeable) objectInputStream);
                a((Closeable) byteArrayInputStream);
                return object;
            } catch (Exception e4) {
                e = e4;
                fw0.a(e);
                a((Closeable) objectInputStream);
                a((Closeable) byteArrayInputStream);
                return null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(inputStream, byteArrayOutputStream);
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }
}
