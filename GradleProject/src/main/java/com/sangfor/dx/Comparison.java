package com.sangfor.dx;

import supwisdom.d61;
import supwisdom.o41;
import supwisdom.q41;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class Comparison {
    public static final /* synthetic */ Comparison[] $VALUES;
    public static final Comparison EQ;
    public static final Comparison GE;
    public static final Comparison GT;
    public static final Comparison LE;
    public static final Comparison LT;
    public static final Comparison NE;

    /* JADX INFO: compiled from: Proguard */
    public enum a extends Comparison {
        public a(String str, int i) {
            super(str, i, null);
        }

        @Override // com.sangfor.dx.Comparison
        public o41 rop(d61 d61Var) {
            return q41.h(d61Var);
        }
    }

    static {
        a aVar = new a("LT", 0);
        LT = aVar;
        Comparison comparison = new Comparison("LE", 1) { // from class: com.sangfor.dx.Comparison.b
            {
                a aVar2 = null;
            }

            @Override // com.sangfor.dx.Comparison
            public o41 rop(d61 d61Var) {
                return q41.g(d61Var);
            }
        };
        LE = comparison;
        Comparison comparison2 = new Comparison("EQ", 2) { // from class: com.sangfor.dx.Comparison.c
            {
                a aVar2 = null;
            }

            @Override // com.sangfor.dx.Comparison
            public o41 rop(d61 d61Var) {
                return q41.d(d61Var);
            }
        };
        EQ = comparison2;
        Comparison comparison3 = new Comparison("GE", 3) { // from class: com.sangfor.dx.Comparison.d
            {
                a aVar2 = null;
            }

            @Override // com.sangfor.dx.Comparison
            public o41 rop(d61 d61Var) {
                return q41.e(d61Var);
            }
        };
        GE = comparison3;
        Comparison comparison4 = new Comparison("GT", 4) { // from class: com.sangfor.dx.Comparison.e
            {
                a aVar2 = null;
            }

            @Override // com.sangfor.dx.Comparison
            public o41 rop(d61 d61Var) {
                return q41.f(d61Var);
            }
        };
        GT = comparison4;
        Comparison comparison5 = new Comparison("NE", 5) { // from class: com.sangfor.dx.Comparison.f
            {
                a aVar2 = null;
            }

            @Override // com.sangfor.dx.Comparison
            public o41 rop(d61 d61Var) {
                return q41.i(d61Var);
            }
        };
        NE = comparison5;
        $VALUES = new Comparison[]{aVar, comparison, comparison2, comparison3, comparison4, comparison5};
    }

    public Comparison(String str, int i) {
    }

    public static Comparison valueOf(String str) {
        return (Comparison) Enum.valueOf(Comparison.class, str);
    }

    public static Comparison[] values() {
        return (Comparison[]) $VALUES.clone();
    }

    public abstract o41 rop(d61 d61Var);

    public /* synthetic */ Comparison(String str, int i, a aVar) {
        this(str, i);
    }
}
