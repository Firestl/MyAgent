package com.sangfor.dx;

import supwisdom.d61;
import supwisdom.o41;
import supwisdom.q41;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class BinaryOp {
    public static final /* synthetic */ BinaryOp[] $VALUES;
    public static final BinaryOp ADD;
    public static final BinaryOp AND;
    public static final BinaryOp DIVIDE;
    public static final BinaryOp MULTIPLY;
    public static final BinaryOp OR;
    public static final BinaryOp REMAINDER;
    public static final BinaryOp SHIFT_LEFT;
    public static final BinaryOp SHIFT_RIGHT;
    public static final BinaryOp SUBTRACT;
    public static final BinaryOp UNSIGNED_SHIFT_RIGHT;
    public static final BinaryOp XOR;

    /* JADX INFO: compiled from: Proguard */
    public enum c extends BinaryOp {
        public c(String str, int i) {
            super(str, i, null);
        }

        @Override // com.sangfor.dx.BinaryOp
        public o41 rop(d61 d61Var) {
            return q41.a(d61Var);
        }
    }

    static {
        c cVar = new c("ADD", 0);
        ADD = cVar;
        BinaryOp binaryOp = new BinaryOp("SUBTRACT", 1) { // from class: com.sangfor.dx.BinaryOp.d
            {
                c cVar2 = null;
            }

            @Override // com.sangfor.dx.BinaryOp
            public o41 rop(d61 d61Var) {
                return q41.o(d61Var);
            }
        };
        SUBTRACT = binaryOp;
        BinaryOp binaryOp2 = new BinaryOp("MULTIPLY", 2) { // from class: com.sangfor.dx.BinaryOp.e
            {
                c cVar2 = null;
            }

            @Override // com.sangfor.dx.BinaryOp
            public o41 rop(d61 d61Var) {
                return q41.j(d61Var);
            }
        };
        MULTIPLY = binaryOp2;
        BinaryOp binaryOp3 = new BinaryOp("DIVIDE", 3) { // from class: com.sangfor.dx.BinaryOp.f
            {
                c cVar2 = null;
            }

            @Override // com.sangfor.dx.BinaryOp
            public o41 rop(d61 d61Var) {
                return q41.c(d61Var);
            }
        };
        DIVIDE = binaryOp3;
        BinaryOp binaryOp4 = new BinaryOp("REMAINDER", 4) { // from class: com.sangfor.dx.BinaryOp.g
            {
                c cVar2 = null;
            }

            @Override // com.sangfor.dx.BinaryOp
            public o41 rop(d61 d61Var) {
                return q41.l(d61Var);
            }
        };
        REMAINDER = binaryOp4;
        BinaryOp binaryOp5 = new BinaryOp("AND", 5) { // from class: com.sangfor.dx.BinaryOp.h
            {
                c cVar2 = null;
            }

            @Override // com.sangfor.dx.BinaryOp
            public o41 rop(d61 d61Var) {
                return q41.b(d61Var);
            }
        };
        AND = binaryOp5;
        BinaryOp binaryOp6 = new BinaryOp("OR", 6) { // from class: com.sangfor.dx.BinaryOp.i
            {
                c cVar2 = null;
            }

            @Override // com.sangfor.dx.BinaryOp
            public o41 rop(d61 d61Var) {
                return q41.k(d61Var);
            }
        };
        OR = binaryOp6;
        BinaryOp binaryOp7 = new BinaryOp("XOR", 7) { // from class: com.sangfor.dx.BinaryOp.j
            {
                c cVar2 = null;
            }

            @Override // com.sangfor.dx.BinaryOp
            public o41 rop(d61 d61Var) {
                return q41.q(d61Var);
            }
        };
        XOR = binaryOp7;
        BinaryOp binaryOp8 = new BinaryOp("SHIFT_LEFT", 8) { // from class: com.sangfor.dx.BinaryOp.k
            {
                c cVar2 = null;
            }

            @Override // com.sangfor.dx.BinaryOp
            public o41 rop(d61 d61Var) {
                return q41.m(d61Var);
            }
        };
        SHIFT_LEFT = binaryOp8;
        BinaryOp binaryOp9 = new BinaryOp("SHIFT_RIGHT", 9) { // from class: com.sangfor.dx.BinaryOp.a
            {
                c cVar2 = null;
            }

            @Override // com.sangfor.dx.BinaryOp
            public o41 rop(d61 d61Var) {
                return q41.n(d61Var);
            }
        };
        SHIFT_RIGHT = binaryOp9;
        BinaryOp binaryOp10 = new BinaryOp("UNSIGNED_SHIFT_RIGHT", 10) { // from class: com.sangfor.dx.BinaryOp.b
            {
                c cVar2 = null;
            }

            @Override // com.sangfor.dx.BinaryOp
            public o41 rop(d61 d61Var) {
                return q41.p(d61Var);
            }
        };
        UNSIGNED_SHIFT_RIGHT = binaryOp10;
        $VALUES = new BinaryOp[]{cVar, binaryOp, binaryOp2, binaryOp3, binaryOp4, binaryOp5, binaryOp6, binaryOp7, binaryOp8, binaryOp9, binaryOp10};
    }

    public BinaryOp(String str, int i2) {
    }

    public static BinaryOp valueOf(String str) {
        return (BinaryOp) Enum.valueOf(BinaryOp.class, str);
    }

    public static BinaryOp[] values() {
        return (BinaryOp[]) $VALUES.clone();
    }

    public abstract o41 rop(d61 d61Var);

    public /* synthetic */ BinaryOp(String str, int i2, c cVar) {
        this(str, i2);
    }
}
