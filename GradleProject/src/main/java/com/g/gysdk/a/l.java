package com.g.gysdk.a;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public com.getui.gtc.dyc.b.c f2012a = new com.getui.gtc.dyc.b.d() { // from class: com.g.gysdk.a.l.1
        @Override // com.getui.gtc.dyc.b.d
        public void a(Exception exc) {
            l.this.a(exc);
        }

        @Override // com.getui.gtc.dyc.b.c
        public void a(Map map, Map map2) {
            l.this.a(map, map2);
        }

        @Override // com.getui.gtc.dyc.b.c
        public void b(String str) {
            l.this.a(str);
        }
    };

    public com.getui.gtc.dyc.b.c a() {
        return this.f2012a;
    }

    public void a(Exception exc) {
    }

    public abstract void a(String str);

    public abstract void a(Map<String, String> map, Map<String, String> map2);
}
