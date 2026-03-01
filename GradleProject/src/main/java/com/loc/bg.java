package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: compiled from: HeaderAddStrategy.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bg extends bk {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f3679a;
    public String b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ak f3680e;
    public Object[] f;

    public bg(Context context, bk bkVar, ak akVar, String str, Object... objArr) {
        super(bkVar);
        this.f3679a = context;
        this.b = str;
        this.f3680e = akVar;
        this.f = objArr;
    }

    private String b() {
        try {
            return String.format(u.c(this.b), this.f);
        } catch (Throwable th) {
            th.printStackTrace();
            ab.b(th, "ofm", "gpj");
            return "";
        }
    }

    @Override // com.loc.bk
    public final byte[] a(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException {
        String strA = u.a(bArr);
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return u.a("{\"pinfo\":\"" + u.a(this.f3680e.b(u.a(b()))) + "\",\"els\":[" + strA + "]}");
    }
}
