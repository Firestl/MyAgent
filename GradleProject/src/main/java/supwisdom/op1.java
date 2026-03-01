package supwisdom;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import supwisdom.gs1;
import supwisdom.ss1;
import supwisdom.vs1;

/* JADX INFO: loaded from: classes2.dex */
public class op1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public vs1 f8692a;
    public gs1.a b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8693e;
    public d[] f;
    public ss1.a g;
    public vs1.a h;

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Annotation[] f8694a;
        public Annotation[][] b;
        public vs1 c;
        public gs1.a d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f8695e;
        public String f;
        public boolean g;
        public d[] h;

        public b(np1 np1Var, Method method) {
            this.c = np1Var.a();
            this.d = np1Var.b();
            this.f8694a = method.getAnnotations();
            this.b = method.getParameterAnnotations();
        }

        public op1 a() {
            Annotation[] annotationArr = this.f8694a;
            if (annotationArr != null && annotationArr.length > 0) {
                for (Annotation annotation : annotationArr) {
                    if (annotation instanceof jp1) {
                        this.f8695e = "GET";
                        this.g = false;
                        this.f = ((jp1) annotation).value();
                    } else if (annotation instanceof kp1) {
                        this.f8695e = "POST";
                        this.g = true;
                        this.f = ((kp1) annotation).value();
                    }
                }
            }
            Annotation[][] annotationArr2 = this.b;
            if (annotationArr2 != null && annotationArr2.length > 0) {
                int length = annotationArr2.length;
                this.h = new d[length];
                for (int i = 0; i < length; i++) {
                    Annotation[] annotationArr3 = this.b[i];
                    if (annotationArr3 != null && annotationArr3.length > 0) {
                        for (Annotation annotation2 : annotationArr3) {
                            if (annotation2 instanceof lp1) {
                                this.h[i] = new e(((lp1) annotation2).value());
                            } else if (annotation2 instanceof ip1) {
                                this.h[i] = new c(((ip1) annotation2).value());
                            }
                        }
                    }
                }
            }
            return new op1(this);
        }
    }

    public static class c extends d {
        public c(String str) {
            super();
            this.f8696a = str;
        }

        @Override // supwisdom.op1.d
        public void a(op1 op1Var, String str) {
            String str2 = this.f8696a;
            if (op1Var.g == null) {
                op1Var.g = new ss1.a();
            }
            op1Var.g.a(str2, str);
        }
    }

    public static abstract class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f8696a;

        public d() {
        }

        public abstract void a(op1 op1Var, String str);
    }

    public static class e extends d {
        public e(String str) {
            super();
            this.f8696a = str;
        }

        @Override // supwisdom.op1.d
        public void a(op1 op1Var, String str) {
            String str2 = this.f8696a;
            if (op1Var.h == null) {
                op1Var.h = op1Var.f8692a.a(op1Var.d);
            }
            op1Var.h.b(str2, str);
        }
    }

    public op1(b bVar) {
        this.f8692a = bVar.c;
        this.b = bVar.d;
        this.c = bVar.f8695e;
        this.d = bVar.f;
        this.f8693e = bVar.g;
        this.f = bVar.h;
        if (this.f8693e) {
            this.g = new ss1.a();
        }
    }
}
