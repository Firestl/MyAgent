package com.sangfor.dx;

import supwisdom.az0;
import supwisdom.o41;
import supwisdom.q41;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class UnaryOp {
    public static final /* synthetic */ UnaryOp[] $VALUES;
    public static final UnaryOp NEGATE;
    public static final UnaryOp NOT;

    /* JADX INFO: compiled from: Proguard */
    public enum a extends UnaryOp {
        public a(String str, int i) {
            super(str, i, null);
        }

        @Override // com.sangfor.dx.UnaryOp
        public o41 rop(az0<?> az0Var) {
            return q41.k(az0Var.b);
        }
    }

    static {
        a aVar = new a("NOT", 0);
        NOT = aVar;
        UnaryOp unaryOp = new UnaryOp("NEGATE", 1) { // from class: com.sangfor.dx.UnaryOp.b
            {
                a aVar2 = null;
            }

            @Override // com.sangfor.dx.UnaryOp
            public o41 rop(az0<?> az0Var) {
                return q41.i(az0Var.b);
            }
        };
        NEGATE = unaryOp;
        $VALUES = new UnaryOp[]{aVar, unaryOp};
    }

    public UnaryOp(String str, int i) {
    }

    public static UnaryOp valueOf(String str) {
        return (UnaryOp) Enum.valueOf(UnaryOp.class, str);
    }

    public static UnaryOp[] values() {
        return (UnaryOp[]) $VALUES.clone();
    }

    public abstract o41 rop(az0<?> az0Var);

    public /* synthetic */ UnaryOp(String str, int i, a aVar) {
        this(str, i);
    }
}
