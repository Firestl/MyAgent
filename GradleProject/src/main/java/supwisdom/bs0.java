package supwisdom;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;

/* JADX INFO: compiled from: EngineWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public interface bs0<T> {

    /* JADX INFO: compiled from: EngineWrapper.java */
    public static class a implements bs0<Cipher> {
        @Override // supwisdom.bs0
        public Cipher a(String str, Provider provider) throws GeneralSecurityException {
            return provider == null ? Cipher.getInstance(str) : Cipher.getInstance(str, provider);
        }
    }

    /* JADX INFO: compiled from: EngineWrapper.java */
    public static class b implements bs0<KeyAgreement> {
        @Override // supwisdom.bs0
        public KeyAgreement a(String str, Provider provider) throws GeneralSecurityException {
            return provider == null ? KeyAgreement.getInstance(str) : KeyAgreement.getInstance(str, provider);
        }
    }

    /* JADX INFO: compiled from: EngineWrapper.java */
    public static class c implements bs0<KeyFactory> {
        @Override // supwisdom.bs0
        public KeyFactory a(String str, Provider provider) throws GeneralSecurityException {
            return provider == null ? KeyFactory.getInstance(str) : KeyFactory.getInstance(str, provider);
        }
    }

    /* JADX INFO: compiled from: EngineWrapper.java */
    public static class d implements bs0<KeyPairGenerator> {
        @Override // supwisdom.bs0
        public KeyPairGenerator a(String str, Provider provider) throws GeneralSecurityException {
            return provider == null ? KeyPairGenerator.getInstance(str) : KeyPairGenerator.getInstance(str, provider);
        }
    }

    /* JADX INFO: compiled from: EngineWrapper.java */
    public static class e implements bs0<Mac> {
        @Override // supwisdom.bs0
        public Mac a(String str, Provider provider) throws GeneralSecurityException {
            return provider == null ? Mac.getInstance(str) : Mac.getInstance(str, provider);
        }
    }

    /* JADX INFO: compiled from: EngineWrapper.java */
    public static class f implements bs0<MessageDigest> {
        @Override // supwisdom.bs0
        public MessageDigest a(String str, Provider provider) throws GeneralSecurityException {
            return provider == null ? MessageDigest.getInstance(str) : MessageDigest.getInstance(str, provider);
        }
    }

    /* JADX INFO: compiled from: EngineWrapper.java */
    public static class g implements bs0<Signature> {
        @Override // supwisdom.bs0
        public Signature a(String str, Provider provider) throws GeneralSecurityException {
            return provider == null ? Signature.getInstance(str) : Signature.getInstance(str, provider);
        }
    }

    T a(String str, Provider provider) throws GeneralSecurityException;
}
