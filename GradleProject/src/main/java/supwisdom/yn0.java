package supwisdom;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.OutputPrefixType;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import supwisdom.cp0;
import supwisdom.dp0;

/* JADX INFO: compiled from: Util.java */
/* JADX INFO: loaded from: classes.dex */
public class yn0 {
    static {
        Charset.forName("UTF-8");
    }

    public static dp0 a(cp0 cp0Var) {
        dp0.b bVarP = dp0.p();
        bVarP.a(cp0Var.q());
        Iterator<cp0.c> it = cp0Var.p().iterator();
        while (it.hasNext()) {
            bVarP.a(a(it.next()));
        }
        return bVarP.build();
    }

    public static void b(cp0.c cVar) throws GeneralSecurityException {
        if (!cVar.r()) {
            throw new GeneralSecurityException(String.format("key %d has no key data", Integer.valueOf(cVar.o())));
        }
        if (cVar.p() == OutputPrefixType.UNKNOWN_PREFIX) {
            throw new GeneralSecurityException(String.format("key %d has unknown prefix", Integer.valueOf(cVar.o())));
        }
        if (cVar.q() == KeyStatusType.UNKNOWN_STATUS) {
            throw new GeneralSecurityException(String.format("key %d has unknown status", Integer.valueOf(cVar.o())));
        }
    }

    public static dp0.c a(cp0.c cVar) {
        dp0.c.a aVarP = dp0.c.p();
        aVarP.a(cVar.n().o());
        aVarP.a(cVar.q());
        aVarP.a(cVar.p());
        aVarP.a(cVar.o());
        return aVarP.build();
    }

    public static void b(cp0 cp0Var) throws GeneralSecurityException {
        int iQ = cp0Var.q();
        int i = 0;
        boolean z = false;
        boolean z2 = true;
        for (cp0.c cVar : cp0Var.p()) {
            if (cVar.q() == KeyStatusType.ENABLED) {
                b(cVar);
                if (cVar.o() == iQ) {
                    if (z) {
                        throw new GeneralSecurityException("keyset contains multiple primary keys");
                    }
                    z = true;
                }
                if (cVar.n().n() != KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC) {
                    z2 = false;
                }
                i++;
            }
        }
        if (i == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        }
        if (!z && !z2) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }
}
