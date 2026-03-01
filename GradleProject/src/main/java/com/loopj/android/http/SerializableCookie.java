package com.loopj.android.http;

import cz.msebera.android.httpclient.impl.cookie.BasicClientCookie;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import supwisdom.jo1;

/* JADX INFO: loaded from: classes2.dex */
public class SerializableCookie implements Serializable {
    public static final long serialVersionUID = 6374381828722046732L;
    public transient BasicClientCookie clientCookie;
    public final transient jo1 cookie;

    public SerializableCookie(jo1 jo1Var) {
        this.cookie = jo1Var;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        BasicClientCookie basicClientCookie = new BasicClientCookie((String) objectInputStream.readObject(), (String) objectInputStream.readObject());
        this.clientCookie = basicClientCookie;
        basicClientCookie.setComment((String) objectInputStream.readObject());
        this.clientCookie.setDomain((String) objectInputStream.readObject());
        this.clientCookie.setExpiryDate((Date) objectInputStream.readObject());
        this.clientCookie.setPath((String) objectInputStream.readObject());
        this.clientCookie.setVersion(objectInputStream.readInt());
        this.clientCookie.setSecure(objectInputStream.readBoolean());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.cookie.getName());
        objectOutputStream.writeObject(this.cookie.getValue());
        objectOutputStream.writeObject(this.cookie.getComment());
        objectOutputStream.writeObject(this.cookie.getDomain());
        objectOutputStream.writeObject(this.cookie.getExpiryDate());
        objectOutputStream.writeObject(this.cookie.getPath());
        objectOutputStream.writeInt(this.cookie.getVersion());
        objectOutputStream.writeBoolean(this.cookie.isSecure());
    }

    public jo1 getCookie() {
        jo1 jo1Var = this.cookie;
        BasicClientCookie basicClientCookie = this.clientCookie;
        return basicClientCookie != null ? basicClientCookie : jo1Var;
    }
}
