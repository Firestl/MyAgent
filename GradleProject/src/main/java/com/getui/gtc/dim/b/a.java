package com.getui.gtc.dim.b;

import android.text.TextUtils;
import com.alibaba.fastjson.parser.JSONLexer;
import com.efs.sdk.base.newsharedpreferences.SharedPreferencesNewImpl;
import com.umeng.analytics.pro.co;
import com.umeng.analytics.pro.db;
import dc.squareup.okhttp3.internal.publicsuffix.PublicSuffixDatabase;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2153a;
    public final String b;
    public final long c;
    public final long d;

    public a(String str, String str2, long j, long j2) {
        this.f2153a = str;
        this.b = str2;
        this.c = j;
        this.d = j2;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        byte b = -1;
        switch (str.hashCode()) {
            case 320894021:
                if (str.equals("dim-2-1-16-1")) {
                    b = 40;
                }
                break;
            case 320894022:
                if (str.equals("dim-2-1-16-2")) {
                    b = 41;
                }
                break;
            case 320894982:
                if (str.equals("dim-2-1-17-1")) {
                    b = 3;
                }
                break;
            case 320894983:
                if (str.equals("dim-2-1-17-2")) {
                    b = 0;
                }
                break;
            case 320895943:
                if (str.equals("dim-2-1-18-1")) {
                    b = 47;
                }
                break;
            case 320895944:
                if (str.equals("dim-2-1-18-2")) {
                    b = 44;
                }
                break;
            case 320895945:
                if (str.equals("dim-2-1-18-3")) {
                    b = 50;
                }
                break;
            case 320896904:
                if (str.equals("dim-2-1-19-1")) {
                    b = 36;
                }
                break;
            case 320896905:
                if (str.equals("dim-2-1-19-2")) {
                    b = 37;
                }
                break;
            case 320919007:
                if (str.equals("dim-2-1-21-1")) {
                    b = 8;
                }
                break;
            case 320919008:
                if (str.equals("dim-2-1-21-2")) {
                    b = 9;
                }
                break;
            case 320919009:
                if (str.equals("dim-2-1-21-3")) {
                    b = 7;
                }
                break;
            case 320919011:
                if (str.equals("dim-2-1-21-5")) {
                    b = 6;
                }
                break;
            case 378146557:
                if (str.equals("dim-2-3-10-1")) {
                    b = 38;
                }
                break;
            case 378146558:
                if (str.equals("dim-2-3-10-2")) {
                    b = 39;
                }
                break;
            case 378147518:
                if (str.equals("dim-2-3-11-1")) {
                    b = 42;
                }
                break;
            case 378147519:
                if (str.equals("dim-2-3-11-2")) {
                    b = 43;
                }
                break;
            case 378149440:
                if (str.equals("dim-2-3-13-1")) {
                    b = 45;
                }
                break;
            case 378149441:
                if (str.equals("dim-2-3-13-2")) {
                    b = 46;
                }
                break;
            case 378150401:
                if (str.equals("dim-2-3-14-1")) {
                    b = 48;
                }
                break;
            case 378150402:
                if (str.equals("dim-2-3-14-2")) {
                    b = 49;
                }
                break;
            case 378151362:
                if (str.equals("dim-2-3-15-1")) {
                    b = 51;
                }
                break;
            case 378151363:
                if (str.equals("dim-2-3-15-2")) {
                    b = 52;
                }
                break;
            case 378152323:
                if (str.equals("dim-2-3-16-1")) {
                    b = 54;
                }
                break;
            case 378152324:
                if (str.equals("dim-2-3-16-2")) {
                    b = 55;
                }
                break;
            case 1672919129:
                if (str.equals("dim-2-1-1-1")) {
                    b = 12;
                }
                break;
            case 1672919131:
                if (str.equals("dim-2-1-1-3")) {
                    b = 13;
                }
                break;
            case 1672919132:
                if (str.equals("dim-2-1-1-4")) {
                    b = db.l;
                }
                break;
            case 1672920090:
                if (str.equals("dim-2-1-2-1")) {
                    b = 17;
                }
                break;
            case 1672920092:
                if (str.equals("dim-2-1-2-3")) {
                    b = SharedPreferencesNewImpl.FINISH_MARK;
                }
                break;
            case 1672920093:
                if (str.equals("dim-2-1-2-4")) {
                    b = 19;
                }
                break;
            case 1672921051:
                if (str.equals("dim-2-1-3-1")) {
                    b = 22;
                }
                break;
            case 1672922012:
                if (str.equals("dim-2-1-4-1")) {
                    b = 30;
                }
                break;
            case 1672923934:
                if (str.equals("dim-2-1-6-1")) {
                    b = 25;
                }
                break;
            case 1672923936:
                if (str.equals("dim-2-1-6-3")) {
                    b = JSONLexer.EOI;
                }
                break;
            case 1672923937:
                if (str.equals("dim-2-1-6-4")) {
                    b = 27;
                }
                break;
            case 1672924895:
                if (str.equals("dim-2-1-7-1")) {
                    b = 53;
                }
                break;
            case 1672925856:
                if (str.equals("dim-2-1-8-1")) {
                    b = PublicSuffixDatabase.EXCEPTION_MARKER;
                }
                break;
            case 1674766171:
                if (str.equals("dim-2-3-1-1")) {
                    b = 11;
                }
                break;
            case 1674766172:
                if (str.equals("dim-2-3-1-2")) {
                    b = 10;
                }
                break;
            case 1674767132:
                if (str.equals("dim-2-3-2-1")) {
                    b = 2;
                }
                break;
            case 1674767133:
                if (str.equals("dim-2-3-2-2")) {
                    b = 1;
                }
                break;
            case 1674768093:
                if (str.equals("dim-2-3-3-1")) {
                    b = 5;
                }
                break;
            case 1674768094:
                if (str.equals("dim-2-3-3-2")) {
                    b = 4;
                }
                break;
            case 1674769054:
                if (str.equals("dim-2-3-4-1")) {
                    b = 16;
                }
                break;
            case 1674769055:
                if (str.equals("dim-2-3-4-2")) {
                    b = 15;
                }
                break;
            case 1674770015:
                if (str.equals("dim-2-3-5-1")) {
                    b = 21;
                }
                break;
            case 1674770016:
                if (str.equals("dim-2-3-5-2")) {
                    b = 20;
                }
                break;
            case 1674770976:
                if (str.equals("dim-2-3-6-1")) {
                    b = 24;
                }
                break;
            case 1674770977:
                if (str.equals("dim-2-3-6-2")) {
                    b = 23;
                }
                break;
            case 1674771937:
                if (str.equals("dim-2-3-7-1")) {
                    b = 28;
                }
                break;
            case 1674771938:
                if (str.equals("dim-2-3-7-2")) {
                    b = 29;
                }
                break;
            case 1674772898:
                if (str.equals("dim-2-3-8-1")) {
                    b = co.j;
                }
                break;
            case 1674772899:
                if (str.equals("dim-2-3-8-2")) {
                    b = 32;
                }
                break;
            case 1674773859:
                if (str.equals("dim-2-3-9-1")) {
                    b = 34;
                }
                break;
            case 1674773860:
                if (str.equals("dim-2-3-9-2")) {
                    b = 35;
                }
                break;
        }
        switch (b) {
            case 0:
            case 1:
            case 2:
                return new a("dim-2-3-2-1", "dim-2-3-2-2", 5000L, 1800000L);
            case 3:
            case 4:
            case 5:
                return new a("dim-2-3-3-1", "dim-2-3-3-2", 5000L, 1800000L);
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return new a("dim-2-3-1-1", "dim-2-3-1-2", 21600000L, 86400000L);
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                return new a("dim-2-3-4-1", "dim-2-3-4-2", 86400000L, 604800000L);
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return new a("dim-2-3-5-1", "dim-2-3-5-2", 86400000L, 604800000L);
            case 22:
            case 23:
            case 24:
                return new a("dim-2-3-6-1", "dim-2-3-6-2", 86400000L, 604800000L);
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
                return new a("dim-2-3-7-1", "dim-2-3-7-2", 86400000L, 604800000L);
            case 30:
            case 31:
            case 32:
                return new a("dim-2-3-8-1", "dim-2-3-8-2", 86400000L, 604800000L);
            case 33:
            case 34:
            case 35:
                return new a("dim-2-3-9-1", "dim-2-3-9-2", 86400000L, 604800000L);
            case 36:
            case 37:
            case 38:
            case 39:
                return new a("dim-2-3-10-1", "dim-2-3-10-2", 5000L, 1800000L);
            case 40:
            case 41:
            case 42:
            case 43:
                return new a("dim-2-3-11-1", "dim-2-3-11-2", 5000L, 1800000L);
            case 44:
            case 45:
            case 46:
                return new a("dim-2-3-13-1", "dim-2-3-13-2", 5000L, 1800000L);
            case 47:
            case 48:
            case 49:
                return new a("dim-2-3-14-1", "dim-2-3-14-2", 5000L, 1800000L);
            case 50:
            case 51:
            case 52:
                return new a("dim-2-3-15-1", "dim-2-3-15-2", 5000L, 1800000L);
            case 53:
            case 54:
            case 55:
                return new a("dim-2-3-16-1", "dim-2-3-16-2", 86400000L, 604800000L);
            default:
                return null;
        }
    }
}
