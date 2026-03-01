package com.sangfor.gson.Sangfor_w;

import java.math.BigDecimal;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class Sangfor_g extends Number {
    public final String Sangfor_a;

    public Sangfor_g(String str) {
        this.Sangfor_a = str;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return Double.parseDouble(this.Sangfor_a);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Sangfor_g)) {
            return false;
        }
        String str = this.Sangfor_a;
        String str2 = ((Sangfor_g) obj).Sangfor_a;
        return str == str2 || str.equals(str2);
    }

    @Override // java.lang.Number
    public float floatValue() {
        return Float.parseFloat(this.Sangfor_a);
    }

    public int hashCode() {
        return this.Sangfor_a.hashCode();
    }

    @Override // java.lang.Number
    public int intValue() {
        try {
            try {
                return Integer.parseInt(this.Sangfor_a);
            } catch (NumberFormatException unused) {
                return new BigDecimal(this.Sangfor_a).intValue();
            }
        } catch (NumberFormatException unused2) {
            return (int) Long.parseLong(this.Sangfor_a);
        }
    }

    @Override // java.lang.Number
    public long longValue() {
        try {
            return Long.parseLong(this.Sangfor_a);
        } catch (NumberFormatException unused) {
            return new BigDecimal(this.Sangfor_a).longValue();
        }
    }

    public String toString() {
        return this.Sangfor_a;
    }
}
