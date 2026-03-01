package cn.com.chinatelecom.account.api.d;

import android.net.Network;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Network f1506a;
    public int b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1507e;
    public boolean f;
    public String g;
    public String h;
    public Map<String, String> i;
    public int j;
    public int k;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1508a;
        public int b;
        public Network c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f1509e;
        public String f;
        public boolean g;
        public boolean h;
        public String i;
        public String j;
        public Map<String, String> k;

        public a a(int i) {
            this.f1508a = i;
            return this;
        }

        public a a(Network network) {
            this.c = network;
            return this;
        }

        public a a(String str) {
            this.f1509e = str;
            return this;
        }

        public a a(Map<String, String> map) {
            this.k = map;
            return this;
        }

        public a a(boolean z) {
            this.g = z;
            return this;
        }

        public a a(boolean z, String str, String str2) {
            this.h = z;
            this.i = str;
            this.j = str2;
            return this;
        }

        public g a() {
            return new g(this);
        }

        public a b(int i) {
            this.b = i;
            return this;
        }

        public a b(String str) {
            this.f = str;
            return this;
        }
    }

    public g(a aVar) {
        this.j = aVar.f1508a;
        this.k = aVar.b;
        this.f1506a = aVar.c;
        this.b = aVar.d;
        this.c = aVar.f1509e;
        this.d = aVar.f;
        this.f1507e = aVar.g;
        this.f = aVar.h;
        this.g = aVar.i;
        this.h = aVar.j;
        this.i = aVar.k;
    }

    public int a() {
        int i = this.j;
        if (i > 0) {
            return i;
        }
        return 3000;
    }

    public int b() {
        int i = this.k;
        if (i > 0) {
            return i;
        }
        return 3000;
    }
}
