package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.io.Closeable;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Iterator;

/* JADX INFO: compiled from: IsoFile.java */
/* JADX INFO: loaded from: classes.dex */
public class js extends xs0 implements Closeable {
    public ReadableByteChannel f;

    public js() {
        super("");
        new ls(new String[0]);
    }

    public static byte[] a(String str) {
        byte[] bArr = new byte[4];
        if (str != null) {
            for (int i = 0; i < Math.min(4, str.length()); i++) {
                bArr[i] = (byte) str.charAt(i);
            }
        }
        return bArr;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f.close();
    }

    @Override // supwisdom.ws0, supwisdom.os
    public void getBox(WritableByteChannel writableByteChannel) throws IOException {
        for (os osVar : this.f9804e) {
            if (writableByteChannel instanceof FileChannel) {
                FileChannel fileChannel = (FileChannel) writableByteChannel;
                fileChannel.position();
                osVar.getBox(writableByteChannel);
                fileChannel.position();
            } else {
                osVar.getBox(writableByteChannel);
            }
        }
    }

    @Override // supwisdom.ws0, supwisdom.os
    public long getSize() {
        Iterator<os> it = this.f9804e.iterator();
        long size = 0;
        while (it.hasNext()) {
            size += it.next().getSize();
        }
        return size;
    }

    @Override // supwisdom.xs0
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IsoFile[");
        if (this.f9804e == null) {
            sb.append("unparsed");
        } else {
            for (int i = 0; i < this.f9804e.size(); i++) {
                if (i > 0) {
                    sb.append(";");
                }
                sb.append(this.f9804e.get(i).toString());
            }
        }
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }
}
