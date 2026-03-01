package com.zx.a.I8b7;

import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f6242a;

    public n(String str, String str2, String str3) throws Exception {
        Cipher cipherA = a();
        Object objInvoke = Class.class.getMethod(a(cipherA, "e9uUVEzVoVWhlVZ8sn1SxQ=="), new Class[0]).invoke(n.class, new Object[0]);
        this.f6242a = Class.forName(a(cipherA, "xQCe3P4Nvo89QAc9u/pHAX2XPV4G/jFlxbQMn2JZQJc=")).getConstructor(String.class, String.class, String.class, Class.forName(a(cipherA, "BXR/YZEsZikKgydkACAIi9ZlpwlaFcVU0svFCdqK+9k="))).newInstance(str, str2, null, objInvoke);
    }

    public final Cipher a() throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, new SecretKeySpec(Base64.decode("4A4l6BZlmqOanvxq+udGBw==", 2), "AES"), new IvParameterSpec(Base64.decode("PmwJuXddVGbC+te23VrmEA==", 2)));
        return cipher;
    }

    public Class b() throws Exception {
        Cipher cipherA = a();
        String strA = a(cipherA, "l4Kd4/MbY9AEp4YDnYjHmKacfKJJ9a3waN7VR+soAx/A/LcgDPs74QgTnx2q/91S");
        return (Class) Class.class.getMethod(a(cipherA, "zVquUq9GnBlh+JZR3JyQWw=="), String.class, Boolean.TYPE, Class.forName(a(cipherA, "BXR/YZEsZikKgydkACAIi9ZlpwlaFcVU0svFCdqK+9k="))).invoke(Class.class, strA, Boolean.TRUE, this.f6242a);
    }

    public final String a(Cipher cipher, String str) throws Exception {
        return new String(cipher.doFinal(Base64.decode(str, 2)), "UTF-8");
    }
}
