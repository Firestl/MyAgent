package supwisdom;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: compiled from: SampleEntry.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class pt extends ws0 implements qs {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8827e;
    public List<os> f;

    public pt(String str) {
        super(str);
        this.f8827e = 1;
        this.f = new LinkedList();
    }

    public void a(int i) {
        this.f8827e = i;
    }

    public void c(ByteBuffer byteBuffer) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        WritableByteChannel writableByteChannelNewChannel = Channels.newChannel(byteArrayOutputStream);
        try {
            Iterator<os> it = this.f.iterator();
            while (it.hasNext()) {
                it.next().getBox(writableByteChannelNewChannel);
            }
            writableByteChannelNewChannel.close();
            byteBuffer.put(byteArrayOutputStream.toByteArray());
        } catch (IOException unused) {
            throw new RuntimeException("Cannot happen. Everything should be in memory and therefore no exceptions.");
        }
    }

    public void d(ByteBuffer byteBuffer) {
        byteBuffer.put(new byte[6]);
        ks.a(byteBuffer, this.f8827e);
    }

    public List<os> e() {
        return this.f;
    }

    public void a(os osVar) {
        osVar.setParent(this);
        this.f.add(osVar);
    }
}
