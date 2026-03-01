package com.getui.gtc.base.crypt;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import io.dcloud.common.util.Md5Utils;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f2128a;
    public c b;
    public Map<String, SecretKey> c = new HashMap();
    public Map<String, SecretKey> d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Map<String, KeyPair> f2129e = new HashMap();
    public Map<String, IvParameterSpec> f = new HashMap();
    public String g;

    private KeyPair d(String str) {
        try {
            String strDigestToHexString = CryptTools.digestToHexString(Md5Utils.ALGORITHM, (str + "-rsa1024alias").getBytes());
            if (this.f2129e.containsKey(strDigestToHexString)) {
                return this.f2129e.get(strDigestToHexString);
            }
            KeyPair keyPairA = this.f2128a.a(null, strDigestToHexString, false);
            if (keyPairA == null) {
                return null;
            }
            this.f2129e.put(strDigestToHexString, keyPairA);
            return keyPairA;
        } catch (Throwable unused) {
            return null;
        }
    }

    public final List<CryptException> a(Context context) throws CryptException {
        SecretKey secretKeyA;
        ArrayList arrayList = new ArrayList();
        this.g = String.valueOf(Process.myPid());
        String str = this.g + "-rsa1024alias";
        String str2 = this.g + "-aes128alias";
        String str3 = this.g + "-ivalias";
        String strDigestToHexString = CryptTools.digestToHexString(Md5Utils.ALGORITHM, str.getBytes());
        String strDigestToHexString2 = CryptTools.digestToHexString(Md5Utils.ALGORITHM, str2.getBytes());
        String strDigestToHexString3 = CryptTools.digestToHexString(Md5Utils.ALGORITHM, str3.getBytes());
        this.f2128a = new a();
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                KeyPair keyPairA = this.f2128a.a(context, strDigestToHexString, true);
                this.f2129e.put(strDigestToHexString, keyPairA);
                c cVar = new c(context, keyPairA);
                this.b = cVar;
                secretKeyA = cVar.a(strDigestToHexString2);
            } catch (Throwable th) {
                arrayList.add(new CryptException("above api 18, but second secret key create failed with android key store!", th));
                secretKeyA = null;
            }
        } else {
            secretKeyA = null;
        }
        if (secretKeyA == null) {
            try {
                c cVar2 = new c(context, null);
                this.b = cVar2;
                secretKeyA = cVar2.a(strDigestToHexString2);
            } catch (Throwable th2) {
                throw new CryptException("second secret key create failed!", th2);
            }
        }
        this.d.put(strDigestToHexString2, secretKeyA);
        try {
            c cVar3 = this.b;
            IvParameterSpec ivParameterSpecB = cVar3.b(strDigestToHexString3, true, cVar3.f2127a);
            if (ivParameterSpecB == null) {
                throw new CryptException("iv parameter spec create failed!");
            }
            this.f.put(strDigestToHexString3, ivParameterSpecB);
            return arrayList;
        } catch (Throwable th3) {
            throw new CryptException("iv parameter spec create failed!", th3);
        }
    }

    public final SecretKey a() {
        return b(this.g);
    }

    public final SecretKey a(String str) {
        try {
            String strDigestToHexString = CryptTools.digestToHexString(Md5Utils.ALGORITHM, (str + "-aes128alias").getBytes());
            if (this.c.containsKey(strDigestToHexString)) {
                return this.c.get(strDigestToHexString);
            }
            SecretKey secretKeyA = this.f2128a.a(strDigestToHexString);
            if (secretKeyA == null) {
                return null;
            }
            this.c.put(strDigestToHexString, secretKeyA);
            return secretKeyA;
        } catch (Throwable unused) {
            return null;
        }
    }

    public final SecretKey b(String str) {
        try {
            String strDigestToHexString = CryptTools.digestToHexString(Md5Utils.ALGORITHM, (str + "-aes128alias").getBytes());
            if (this.d.containsKey(strDigestToHexString)) {
                return this.d.get(strDigestToHexString);
            }
            SecretKey secretKeyA = this.b.a(strDigestToHexString, false, d(str));
            if (secretKeyA == null) {
                return null;
            }
            this.d.put(strDigestToHexString, secretKeyA);
            return secretKeyA;
        } catch (Throwable unused) {
            return null;
        }
    }

    public final IvParameterSpec b() {
        return c(this.g);
    }

    public final IvParameterSpec c(String str) {
        try {
            String strDigestToHexString = CryptTools.digestToHexString(Md5Utils.ALGORITHM, (str + "-ivalias").getBytes());
            if (this.f.containsKey(strDigestToHexString)) {
                return this.f.get(strDigestToHexString);
            }
            IvParameterSpec ivParameterSpecB = this.b.b(strDigestToHexString, false, d(str));
            if (ivParameterSpecB == null) {
                return null;
            }
            this.f.put(strDigestToHexString, ivParameterSpecB);
            return ivParameterSpecB;
        } catch (Throwable unused) {
            return null;
        }
    }
}
