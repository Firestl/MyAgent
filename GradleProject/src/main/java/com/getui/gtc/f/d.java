package com.getui.gtc.f;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    public com.getui.gtc.dyc.b.c c = new com.getui.gtc.dyc.b.d() { // from class: com.getui.gtc.f.d.1
        @Override // com.getui.gtc.dyc.b.d
        public final void a(Exception exc) {
            d.this.a(exc);
        }

        @Override // com.getui.gtc.dyc.b.c
        public final void a(Map map, Map map2) {
            d.this.a(map, map2);
        }

        @Override // com.getui.gtc.dyc.b.c
        public final void b(String str) {
            d.this.a(str);
        }
    };

    public void a(Exception exc) {
    }

    public abstract void a(String str);

    public abstract void a(Map<String, String> map, Map<String, String> map2);
}
