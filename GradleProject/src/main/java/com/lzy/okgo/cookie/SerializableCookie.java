package com.lzy.okgo.cookie;

import android.content.ContentValues;
import android.database.Cursor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Locale;
import supwisdom.fw0;
import supwisdom.ns1;

/* JADX INFO: loaded from: classes2.dex */
public class SerializableCookie implements Serializable {
    public static final String COOKIE = "cookie";
    public static final String DOMAIN = "domain";
    public static final String HOST = "host";
    public static final String NAME = "name";
    public static final long serialVersionUID = 6374381323722046732L;
    public transient ns1 clientCookie;
    public transient ns1 cookie;
    public String domain;
    public String host;
    public String name;

    public SerializableCookie(String str, ns1 ns1Var) {
        this.cookie = ns1Var;
        this.host = str;
        this.name = ns1Var.e();
        this.domain = ns1Var.a();
    }

    public static String byteArrayToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(i));
        }
        return sb.toString().toUpperCase(Locale.US);
    }

    public static ns1 bytesToCookie(byte[] bArr) {
        try {
            return ((SerializableCookie) new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject()).getCookie();
        } catch (Exception e2) {
            fw0.a(e2);
            return null;
        }
    }

    public static byte[] cookieToBytes(String str, ns1 ns1Var) {
        SerializableCookie serializableCookie = new SerializableCookie(str, ns1Var);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(serializableCookie);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e2) {
            fw0.a(e2);
            return null;
        }
    }

    public static ns1 decodeCookie(String str) {
        return bytesToCookie(hexStringToByteArray(str));
    }

    public static String encodeCookie(String str, ns1 ns1Var) {
        if (ns1Var == null) {
            return null;
        }
        return byteArrayToHexString(cookieToBytes(str, ns1Var));
    }

    public static ContentValues getContentValues(SerializableCookie serializableCookie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("host", serializableCookie.host);
        contentValues.put("name", serializableCookie.name);
        contentValues.put(DOMAIN, serializableCookie.domain);
        contentValues.put(COOKIE, cookieToBytes(serializableCookie.host, serializableCookie.getCookie()));
        return contentValues;
    }

    public static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static SerializableCookie parseCursorToBean(Cursor cursor) {
        return new SerializableCookie(cursor.getString(cursor.getColumnIndex("host")), bytesToCookie(cursor.getBlob(cursor.getColumnIndex(COOKIE))));
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        String str = (String) objectInputStream.readObject();
        String str2 = (String) objectInputStream.readObject();
        long j = objectInputStream.readLong();
        String str3 = (String) objectInputStream.readObject();
        String str4 = (String) objectInputStream.readObject();
        boolean z = objectInputStream.readBoolean();
        boolean z2 = objectInputStream.readBoolean();
        boolean z3 = objectInputStream.readBoolean();
        objectInputStream.readBoolean();
        ns1.a aVar = new ns1.a();
        aVar.c(str);
        aVar.e(str2);
        aVar.a(j);
        if (z3) {
            aVar.b(str3);
        } else {
            aVar.a(str3);
        }
        aVar.d(str4);
        if (z) {
            aVar.c();
        }
        if (z2) {
            aVar.b();
        }
        this.clientCookie = aVar.a();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.cookie.e());
        objectOutputStream.writeObject(this.cookie.i());
        objectOutputStream.writeLong(this.cookie.b());
        objectOutputStream.writeObject(this.cookie.a());
        objectOutputStream.writeObject(this.cookie.f());
        objectOutputStream.writeBoolean(this.cookie.h());
        objectOutputStream.writeBoolean(this.cookie.d());
        objectOutputStream.writeBoolean(this.cookie.c());
        objectOutputStream.writeBoolean(this.cookie.g());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SerializableCookie.class != obj.getClass()) {
            return false;
        }
        SerializableCookie serializableCookie = (SerializableCookie) obj;
        String str = this.host;
        if (str == null ? serializableCookie.host != null : !str.equals(serializableCookie.host)) {
            return false;
        }
        String str2 = this.name;
        if (str2 == null ? serializableCookie.name != null : !str2.equals(serializableCookie.name)) {
            return false;
        }
        String str3 = this.domain;
        String str4 = serializableCookie.domain;
        return str3 != null ? str3.equals(str4) : str4 == null;
    }

    public ns1 getCookie() {
        ns1 ns1Var = this.cookie;
        ns1 ns1Var2 = this.clientCookie;
        return ns1Var2 != null ? ns1Var2 : ns1Var;
    }

    public int hashCode() {
        String str = this.host;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.name;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.domain;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }
}
