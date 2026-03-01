package com.umeng.analytics.filter;

import android.util.Base64;
import io.dcloud.common.util.Md5Utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: SmartDict.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {
    public static final String b = "Ă";
    public MessageDigest c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5153e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f5152a = Md5Utils.ALGORITHM;
    public Set<Object> d = new HashSet();

    public d(boolean z, String str) {
        int i = 0;
        this.f5153e = false;
        this.f5153e = z;
        try {
            this.c = MessageDigest.getInstance(Md5Utils.ALGORITHM);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        }
        if (str != null) {
            if (!z) {
                String[] strArrSplit = str.split(b);
                int length = strArrSplit.length;
                while (i < length) {
                    this.d.add(strArrSplit[i]);
                    i++;
                }
                return;
            }
            try {
                byte[] bArrDecode = Base64.decode(str.getBytes(), 0);
                while (i < bArrDecode.length / 4) {
                    int i2 = i * 4;
                    this.d.add(Integer.valueOf(((bArrDecode[i2 + 0] & 255) << 24) + ((bArrDecode[i2 + 1] & 255) << 16) + ((bArrDecode[i2 + 2] & 255) << 8) + (bArrDecode[i2 + 3] & 255)));
                    i++;
                }
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
            }
        }
    }

    private Integer c(String str) {
        try {
            this.c.update(str.getBytes());
            byte[] bArrDigest = this.c.digest();
            return Integer.valueOf(((bArrDigest[0] & 255) << 24) + ((bArrDigest[1] & 255) << 16) + ((bArrDigest[2] & 255) << 8) + (bArrDigest[3] & 255));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean a(String str) {
        return this.f5153e ? this.d.contains(c(str)) : this.d.contains(str);
    }

    public void b(String str) {
        if (this.f5153e) {
            this.d.add(c(str));
        } else {
            this.d.add(str);
        }
    }

    public String toString() {
        if (!this.f5153e) {
            StringBuilder sb = new StringBuilder();
            for (Object obj : this.d) {
                if (sb.length() > 0) {
                    sb.append(b);
                }
                sb.append(obj.toString());
            }
            return sb.toString();
        }
        byte[] bArr = new byte[this.d.size() * 4];
        Iterator<Object> it = this.d.iterator();
        int i = 0;
        while (it.hasNext()) {
            int iIntValue = ((Integer) it.next()).intValue();
            int i2 = i + 1;
            bArr[i] = (byte) (((-16777216) & iIntValue) >> 24);
            int i3 = i2 + 1;
            bArr[i2] = (byte) ((16711680 & iIntValue) >> 16);
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((65280 & iIntValue) >> 8);
            i = i4 + 1;
            bArr[i4] = (byte) (iIntValue & 255);
        }
        return new String(Base64.encode(bArr, 0));
    }

    public void a() {
        StringBuilder sb = new StringBuilder();
        Iterator<Object> it = this.d.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (sb.length() > 0) {
                sb.append(",");
            }
        }
        System.out.println(sb.toString());
    }
}
