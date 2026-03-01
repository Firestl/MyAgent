package com.bumptech.glide.util;

import supwisdom.j4;
import supwisdom.p4;

/* JADX INFO: loaded from: classes.dex */
public final class CachedHashCodeArrayMap<K, V> extends j4<K, V> {
    public int hashCode;

    @Override // supwisdom.p4, java.util.Map
    public void clear() {
        this.hashCode = 0;
        super.clear();
    }

    @Override // supwisdom.p4, java.util.Map
    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = super.hashCode();
        }
        return this.hashCode;
    }

    @Override // supwisdom.p4, java.util.Map
    public V put(K k, V v) {
        this.hashCode = 0;
        return (V) super.put(k, v);
    }

    @Override // supwisdom.p4
    public void putAll(p4<? extends K, ? extends V> p4Var) {
        this.hashCode = 0;
        super.putAll(p4Var);
    }

    @Override // supwisdom.p4
    public V removeAt(int i) {
        this.hashCode = 0;
        return (V) super.removeAt(i);
    }

    @Override // supwisdom.p4
    public V setValueAt(int i, V v) {
        this.hashCode = 0;
        return (V) super.setValueAt(i, v);
    }
}
