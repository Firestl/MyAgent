package com.vivo.push.model;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: ConfigItem.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5618a;
    public String b;

    public a(String str, String str2) {
        this.f5618a = str;
        this.b = str2;
    }

    public final String a() {
        return this.f5618a;
    }

    public final String b() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        String str = this.f5618a;
        if (str == null) {
            if (aVar.f5618a != null) {
                return false;
            }
        } else if (!str.equals(aVar.f5618a)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        String str = this.f5618a;
        return (str == null ? 0 : str.hashCode()) + 31;
    }

    public final String toString() {
        return "ConfigItem{mKey='" + this.f5618a + Operators.SINGLE_QUOTE + ", mValue='" + this.b + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
