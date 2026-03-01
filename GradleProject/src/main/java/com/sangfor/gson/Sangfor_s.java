package com.sangfor.gson;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class Sangfor_s {
    public static final Sangfor_s Sangfor_a;
    public static final Sangfor_s Sangfor_b;
    public static final /* synthetic */ Sangfor_s[] Sangfor_c;

    /* JADX INFO: compiled from: Proguard */
    public enum a extends Sangfor_s {
        public a(String str, int i) {
            super(str, i, null);
        }
    }

    static {
        a aVar = new a("DEFAULT", 0);
        Sangfor_a = aVar;
        Sangfor_s sangfor_s = new Sangfor_s("STRING", 1) { // from class: com.sangfor.gson.Sangfor_s.b
            {
                a aVar2 = null;
            }
        };
        Sangfor_b = sangfor_s;
        Sangfor_c = new Sangfor_s[]{aVar, sangfor_s};
    }

    public Sangfor_s(String str, int i) {
    }

    public /* synthetic */ Sangfor_s(String str, int i, a aVar) {
        this(str, i);
    }
}
