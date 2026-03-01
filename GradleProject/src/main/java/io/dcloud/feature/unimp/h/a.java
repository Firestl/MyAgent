package io.dcloud.feature.unimp.h;

/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: io.dcloud.feature.unimp.h.a$a, reason: collision with other inner class name */
    public enum EnumC0183a {
        UniMP0("io.dcloud.feature.sdk.multi.DCUniMPService0", "io.dcloud.feature.sdk.multi.DCUniMPEntry0", "io.dcloud.feature.sdk.multi.DCUniMPActivity0", "io.dcloud.feature.sdk.multi.DCUniMPTopEntry0", "io.dcloud.feature.sdk.multi.DCUniMPTopActivity0", "io.dcloud.feature.sdk.multi.DCUniMPNoRecentsEntry0"),
        UniMP1("io.dcloud.feature.sdk.multi.DCUniMPService1", "io.dcloud.feature.sdk.multi.DCUniMPEntry1", "io.dcloud.feature.sdk.multi.DCUniMPActivity1", "io.dcloud.feature.sdk.multi.DCUniMPTopEntry1", "io.dcloud.feature.sdk.multi.DCUniMPTopActivity1", "io.dcloud.feature.sdk.multi.DCUniMPNoRecentsEntry1"),
        UniMP2("io.dcloud.feature.sdk.multi.DCUniMPService2", "io.dcloud.feature.sdk.multi.DCUniMPEntry2", "io.dcloud.feature.sdk.multi.DCUniMPActivity2", "io.dcloud.feature.sdk.multi.DCUniMPTopEntry2", "io.dcloud.feature.sdk.multi.DCUniMPTopActivity2", "io.dcloud.feature.sdk.multi.DCUniMPNoRecentsEntry2"),
        UniMP3("io.dcloud.feature.sdk.multi.DCUniMPService3", "io.dcloud.feature.sdk.multi.DCUniMPEntry3", "io.dcloud.feature.sdk.multi.DCUniMPActivity3", "io.dcloud.feature.sdk.multi.DCUniMPTopEntry3", "io.dcloud.feature.sdk.multi.DCUniMPTopActivity3", "io.dcloud.feature.sdk.multi.DCUniMPNoRecentsEntry3"),
        UniMP4("io.dcloud.feature.sdk.multi.DCUniMPService4", "io.dcloud.feature.sdk.multi.DCUniMPEntry4", "io.dcloud.feature.sdk.multi.DCUniMPActivity4", "io.dcloud.feature.sdk.multi.DCUniMPTopEntry4", "io.dcloud.feature.sdk.multi.DCUniMPTopActivity4", "io.dcloud.feature.sdk.multi.DCUniMPNoRecentsEntry4");


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f6699a;
        public String b;
        public String c;
        public String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f6700e;
        public String f;

        EnumC0183a(String str, String str2, String str3, String str4, String str5, String str6) {
            this.f6699a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.f6700e = str5;
            this.f = str6;
        }

        public String a() {
            return this.c;
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.f;
        }

        public String d() {
            return this.f6699a;
        }

        public String e() {
            return this.f6700e;
        }

        public String f() {
            return this.d;
        }
    }

    public static EnumC0183a a(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? EnumC0183a.UniMP0 : EnumC0183a.UniMP4 : EnumC0183a.UniMP3 : EnumC0183a.UniMP2 : EnumC0183a.UniMP1;
    }
}
