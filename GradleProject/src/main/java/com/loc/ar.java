package com.loc;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BinaryRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ar extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f3649a;
    public t b;

    public ar(Context context, t tVar) {
        if (context != null) {
            this.f3649a = context.getApplicationContext();
        }
        this.b = tVar;
    }

    public static byte[] q() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(u.a("PANDORA$"));
            byteArrayOutputStream.write(new byte[]{1});
            byteArrayOutputStream.write(new byte[]{0});
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                y.a(th, "bre", "gbh");
                try {
                    byteArrayOutputStream.close();
                    return null;
                } catch (Throwable th2) {
                    y.a(th2, "bre", "gbh");
                    return null;
                }
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    y.a(th3, "bre", "gbh");
                }
            }
        }
    }

    private byte[] r() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(new byte[]{3});
            if (j()) {
                byte[] bArrA = m.a(this.f3649a, l(), this.b != null && "navi".equals(this.b.a()));
                byteArrayOutputStream.write(u.a(bArrA.length));
                byteArrayOutputStream.write(bArrA);
            } else {
                byteArrayOutputStream.write(new byte[]{0, 0});
            }
            byte[] bArrA2 = u.a(g());
            if (bArrA2 == null || bArrA2.length <= 0) {
                byteArrayOutputStream.write(new byte[]{0, 0});
            } else {
                byteArrayOutputStream.write(u.a(bArrA2.length));
                byteArrayOutputStream.write(bArrA2);
            }
            byte[] bArrA3 = u.a(k());
            if (bArrA3 == null || bArrA3.length <= 0) {
                byteArrayOutputStream.write(new byte[]{0, 0});
            } else {
                byteArrayOutputStream.write(u.a(bArrA3.length));
                byteArrayOutputStream.write(bArrA3);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                y.a(th, "bre", "gpd");
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    y.a(th2, "bre", "gred");
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    y.a(th3, "bre", "gred");
                }
            }
        }
    }

    private byte[] s() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArrA_ = a_();
            if (bArrA_ != null && bArrA_.length != 0) {
                byteArrayOutputStream.write(new byte[]{1});
                byteArrayOutputStream.write(u.a(bArrA_.length));
                byteArrayOutputStream.write(bArrA_);
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(new byte[]{0});
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                y.a(th, "bre", "grrd");
            }
            return byteArray;
        } catch (Throwable th2) {
            try {
                y.a(th2, "bre", "grrd");
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    y.a(th3, "bre", "grrd");
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th4) {
                    y.a(th4, "bre", "grrd");
                }
            }
        }
    }

    private byte[] t() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArrF = f();
            if (bArrF != null && bArrF.length != 0) {
                byteArrayOutputStream.write(new byte[]{1});
                byte[] bArrA = o.a(bArrF);
                byteArrayOutputStream.write(u.a(bArrA.length));
                byteArrayOutputStream.write(bArrA);
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(new byte[]{0});
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                y.a(th, "bre", "gred");
            }
            return byteArray;
        } catch (Throwable th2) {
            try {
                y.a(th2, "bre", "gred");
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    y.a(th3, "bre", "gred");
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th4) {
                    y.a(th4, "bre", "gred");
                }
            }
        }
    }

    public abstract byte[] a_();

    @Override // com.loc.av
    public Map<String, String> b_() {
        String strF = k.f(this.f3649a);
        String strA = m.a();
        String strA2 = m.a(this.f3649a, strA, "key=" + strF);
        HashMap map = new HashMap();
        map.put("ts", strA);
        map.put("key", strF);
        map.put("scode", strA2);
        return map;
    }

    @Override // com.loc.av
    public final byte[] e() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(q());
            byteArrayOutputStream.write(r());
            byteArrayOutputStream.write(s());
            byteArrayOutputStream.write(t());
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                y.a(th, "bre", "geb");
                try {
                    byteArrayOutputStream.close();
                    return null;
                } catch (Throwable th2) {
                    y.a(th2, "bre", "geb");
                    return null;
                }
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    y.a(th3, "bre", "geb");
                }
            }
        }
    }

    public abstract byte[] f();

    public String g() {
        return "2.1";
    }

    public boolean j() {
        return true;
    }

    public String k() {
        return String.format("platform=Android&sdkversion=%s&product=%s", this.b.c(), this.b.a());
    }

    public boolean l() {
        return false;
    }
}
