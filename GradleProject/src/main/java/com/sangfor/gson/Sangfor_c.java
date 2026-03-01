package com.sangfor.gson;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class Sangfor_c {
    public static final Sangfor_c Sangfor_a;
    public static final Sangfor_c Sangfor_b;
    public static final Sangfor_c Sangfor_c;
    public static final Sangfor_c Sangfor_d;
    public static final Sangfor_c Sangfor_e;
    public static final Sangfor_c Sangfor_f;
    public static final /* synthetic */ Sangfor_c[] Sangfor_g;

    /* JADX INFO: compiled from: Proguard */
    public enum a extends Sangfor_c {
        public a(String str, int i) {
            super(str, i, null);
        }
    }

    static {
        a aVar = new a("IDENTITY", 0);
        Sangfor_a = aVar;
        Sangfor_c sangfor_c = new Sangfor_c("UPPER_CAMEL_CASE", 1) { // from class: com.sangfor.gson.Sangfor_c.b
            {
                a aVar2 = null;
            }
        };
        Sangfor_b = sangfor_c;
        Sangfor_c sangfor_c2 = new Sangfor_c("UPPER_CAMEL_CASE_WITH_SPACES", 2) { // from class: com.sangfor.gson.Sangfor_c.c
            {
                a aVar2 = null;
            }
        };
        Sangfor_c = sangfor_c2;
        Sangfor_c sangfor_c3 = new Sangfor_c("LOWER_CASE_WITH_UNDERSCORES", 3) { // from class: com.sangfor.gson.Sangfor_c.d
            {
                a aVar2 = null;
            }
        };
        Sangfor_d = sangfor_c3;
        Sangfor_c sangfor_c4 = new Sangfor_c("LOWER_CASE_WITH_DASHES", 4) { // from class: com.sangfor.gson.Sangfor_c.e
            {
                a aVar2 = null;
            }
        };
        Sangfor_e = sangfor_c4;
        Sangfor_c sangfor_c5 = new Sangfor_c("LOWER_CASE_WITH_DOTS", 5) { // from class: com.sangfor.gson.Sangfor_c.f
            {
                a aVar2 = null;
            }
        };
        Sangfor_f = sangfor_c5;
        Sangfor_g = new Sangfor_c[]{aVar, sangfor_c, sangfor_c2, sangfor_c3, sangfor_c4, sangfor_c5};
    }

    public Sangfor_c(String str, int i) {
    }

    public static String Sangfor_a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (Character.isUpperCase(cCharAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    public /* synthetic */ Sangfor_c(String str, int i, a aVar) {
        this(str, i);
    }

    public static String Sangfor_a(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        char cCharAt = str.charAt(0);
        int length = str.length();
        while (i < length - 1 && !Character.isLetter(cCharAt)) {
            sb.append(cCharAt);
            i++;
            cCharAt = str.charAt(i);
        }
        if (Character.isUpperCase(cCharAt)) {
            return str;
        }
        sb.append(Sangfor_a(Character.toUpperCase(cCharAt), str, i + 1));
        return sb.toString();
    }

    public static String Sangfor_a(char c2, String str, int i) {
        if (i < str.length()) {
            return c2 + str.substring(i);
        }
        return String.valueOf(c2);
    }
}
