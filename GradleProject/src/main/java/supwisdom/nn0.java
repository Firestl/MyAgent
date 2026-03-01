package supwisdom;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: KeyManager.java */
/* JADX INFO: loaded from: classes.dex */
public interface nn0<P> {
    KeyData a(ByteString byteString) throws GeneralSecurityException;

    boolean a(String str);

    P b(ByteString byteString) throws GeneralSecurityException;
}
