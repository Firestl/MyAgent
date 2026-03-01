package com.getui.gtc.dyc;

import android.util.Base64;
import com.getui.gtc.base.crypt.SecureCryptTools;
import com.getui.gtc.base.util.io.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class h implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2209a;
    public long c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f2210e;
    public Map<String, String> f;

    public static h e(String str) throws Throwable {
        Throwable th;
        ObjectInputStream objectInputStream;
        try {
            try {
                objectInputStream = new ObjectInputStream(new ByteArrayInputStream(SecureCryptTools.getInstance().decrypt(Base64.decode(str.getBytes(), 0))));
            } catch (IOException e2) {
                e = e2;
                objectInputStream = null;
                com.getui.gtc.dyc.a.a.a.a(e);
                IOUtils.safeClose(objectInputStream);
                return null;
            } catch (ClassNotFoundException e3) {
                e = e3;
                objectInputStream = null;
                com.getui.gtc.dyc.a.a.a.a(e);
                IOUtils.safeClose(objectInputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                IOUtils.safeClose(null);
                throw th;
            }
            try {
                h hVar = (h) objectInputStream.readObject();
                IOUtils.safeClose(objectInputStream);
                return hVar;
            } catch (IOException e4) {
                e = e4;
                com.getui.gtc.dyc.a.a.a.a(e);
                IOUtils.safeClose(objectInputStream);
                return null;
            } catch (ClassNotFoundException e5) {
                e = e5;
                com.getui.gtc.dyc.a.a.a.a(e);
                IOUtils.safeClose(objectInputStream);
                return null;
            }
        } catch (Throwable th3) {
            th = th3;
            IOUtils.safeClose(null);
            throw th;
        }
    }

    public String a() {
        return this.f2209a;
    }

    public void a(long j) {
        this.c = j;
    }

    public void a(String str) {
        this.f2209a = str;
    }

    public void a(Map<String, String> map) {
        this.f = map;
    }

    public long c() {
        return this.c;
    }

    public void c(String str) {
        this.f2210e = str;
    }

    public String d() {
        return this.f2210e;
    }

    public void d(String str) {
        this.d = str;
    }

    public String e() {
        return this.d;
    }

    public Map<String, String> f() {
        return this.f;
    }

    public String g() throws Throwable {
        ObjectOutputStream objectOutputStream;
        IOException e2;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream2 = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        } catch (IOException e3) {
            objectOutputStream = null;
            e2 = e3;
        } catch (Throwable th) {
            th = th;
            IOUtils.safeClose(objectOutputStream2);
            throw th;
        }
        try {
            try {
                objectOutputStream.writeObject(this);
            } catch (Throwable th2) {
                th = th2;
                objectOutputStream2 = objectOutputStream;
                IOUtils.safeClose(objectOutputStream2);
                throw th;
            }
        } catch (IOException e4) {
            e2 = e4;
            com.getui.gtc.dyc.a.a.a.a(e2);
        }
        IOUtils.safeClose(objectOutputStream);
        return Base64.encodeToString(SecureCryptTools.getInstance().encrypt(byteArrayOutputStream.toByteArray()), 0);
    }
}
