package com.getui.gtc.a.a;

import android.net.Network;
import com.taobao.weex.common.WXRequest;
import io.dcloud.common.util.net.NetWork;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2092a;
    public byte[] b;
    public Network c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public e f2093e;
    public boolean f;
    public boolean g;
    public int h;
    public int i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public HashMap<String, String> n;

    public f() {
        this.c = null;
        this.h = 30000;
        this.i = WXRequest.DEFAULT_TIMEOUT_MS;
        this.j = true;
        this.k = true;
        this.l = false;
        this.m = true;
        this.n = new HashMap<>();
    }

    public f(String str) {
        this.c = null;
        this.h = 30000;
        this.i = WXRequest.DEFAULT_TIMEOUT_MS;
        this.j = true;
        this.k = true;
        this.l = false;
        this.m = true;
        HashMap<String, String> map = new HashMap<>();
        this.n = map;
        this.f2092a = str;
        map.put("Content-Type", NetWork.CONTENT_TYPE_UPLOAD);
    }

    public void a() {
    }

    public void a(int i) {
    }

    public void a(Map<String, List<String>> map, byte[] bArr) {
    }
}
