package io.dcloud.common.adapter.util;

import dc.squareup.okhttp3.Dns;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DCOKDns implements Dns {
    @Override // dc.squareup.okhttp3.Dns
    public List<InetAddress> lookup(String str) throws UnknownHostException {
        if (str == null) {
            throw new UnknownHostException("hostname == null");
        }
        try {
            ArrayList arrayList = new ArrayList();
            for (InetAddress inetAddress : InetAddress.getAllByName(str)) {
                if (inetAddress instanceof Inet4Address) {
                    arrayList.add(0, inetAddress);
                } else {
                    arrayList.add(inetAddress);
                }
            }
            return arrayList;
        } catch (Exception e2) {
            UnknownHostException unknownHostException = new UnknownHostException("error");
            unknownHostException.initCause(e2);
            throw unknownHostException;
        }
    }
}
