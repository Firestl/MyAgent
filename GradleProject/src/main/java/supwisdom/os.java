package supwisdom;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;

/* JADX INFO: compiled from: Box.java */
/* JADX INFO: loaded from: classes.dex */
public interface os {
    void getBox(WritableByteChannel writableByteChannel) throws IOException;

    long getSize();

    void setParent(qs qsVar);
}
