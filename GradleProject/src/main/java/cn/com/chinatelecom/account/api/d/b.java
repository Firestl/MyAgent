package cn.com.chinatelecom.account.api.d;

import android.content.Context;
import android.text.TextUtils;
import cn.com.chinatelecom.account.api.CtAuth;
import cn.com.chinatelecom.account.api.e.j;
import com.lzy.okgo.model.HttpHeaders;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class b extends f {
    public static final String b = "b";

    public b(Context context) {
        super(context);
    }

    @Override // cn.com.chinatelecom.account.api.d.e
    public h a(String str, String str2, int i, g gVar) {
        return a(str) ? b(str, str2, i, gVar) : c(str, str2, i, gVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0313 A[Catch: IOException -> 0x017a, TRY_ENTER, TryCatch #2 {IOException -> 0x017a, blocks: (B:58:0x0176, B:62:0x017e, B:81:0x020c, B:83:0x0211, B:95:0x0291, B:97:0x0296, B:107:0x0313, B:109:0x0318, B:119:0x0397, B:121:0x039c), top: B:135:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0318 A[Catch: IOException -> 0x017a, TRY_LEAVE, TryCatch #2 {IOException -> 0x017a, blocks: (B:58:0x0176, B:62:0x017e, B:81:0x020c, B:83:0x0211, B:95:0x0291, B:97:0x0296, B:107:0x0313, B:109:0x0318, B:119:0x0397, B:121:0x039c), top: B:135:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0397 A[Catch: IOException -> 0x017a, TRY_ENTER, TryCatch #2 {IOException -> 0x017a, blocks: (B:58:0x0176, B:62:0x017e, B:81:0x020c, B:83:0x0211, B:95:0x0291, B:97:0x0296, B:107:0x0313, B:109:0x0318, B:119:0x0397, B:121:0x039c), top: B:135:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x039c A[Catch: IOException -> 0x017a, TRY_LEAVE, TryCatch #2 {IOException -> 0x017a, blocks: (B:58:0x0176, B:62:0x017e, B:81:0x020c, B:83:0x0211, B:95:0x0291, B:97:0x0296, B:107:0x0313, B:109:0x0318, B:119:0x0397, B:121:0x039c), top: B:135:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x03ad A[Catch: IOException -> 0x03a9, TRY_LEAVE, TryCatch #11 {IOException -> 0x03a9, blocks: (B:126:0x03a5, B:130:0x03ad), top: B:136:0x03a5 }] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x03a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x020c A[Catch: IOException -> 0x017a, TRY_ENTER, TryCatch #2 {IOException -> 0x017a, blocks: (B:58:0x0176, B:62:0x017e, B:81:0x020c, B:83:0x0211, B:95:0x0291, B:97:0x0296, B:107:0x0313, B:109:0x0318, B:119:0x0397, B:121:0x039c), top: B:135:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0211 A[Catch: IOException -> 0x017a, TRY_LEAVE, TryCatch #2 {IOException -> 0x017a, blocks: (B:58:0x0176, B:62:0x017e, B:81:0x020c, B:83:0x0211, B:95:0x0291, B:97:0x0296, B:107:0x0313, B:109:0x0318, B:119:0x0397, B:121:0x039c), top: B:135:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0291 A[Catch: IOException -> 0x017a, TRY_ENTER, TryCatch #2 {IOException -> 0x017a, blocks: (B:58:0x0176, B:62:0x017e, B:81:0x020c, B:83:0x0211, B:95:0x0291, B:97:0x0296, B:107:0x0313, B:109:0x0318, B:119:0x0397, B:121:0x039c), top: B:135:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0296 A[Catch: IOException -> 0x017a, TRY_LEAVE, TryCatch #2 {IOException -> 0x017a, blocks: (B:58:0x0176, B:62:0x017e, B:81:0x020c, B:83:0x0211, B:95:0x0291, B:97:0x0296, B:107:0x0313, B:109:0x0318, B:119:0x0397, B:121:0x039c), top: B:135:0x000c }] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public cn.com.chinatelecom.account.api.d.h b(java.lang.String r10, java.lang.String r11, int r12, cn.com.chinatelecom.account.api.d.g r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 949
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.api.d.b.b(java.lang.String, java.lang.String, int, cn.com.chinatelecom.account.api.d.g):cn.com.chinatelecom.account.api.d.h");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v12, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r10v13, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v15, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r10v19 */
    /* JADX WARN: Type inference failed for: r10v29, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v30, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r10v31 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.InputStream] */
    public h c(String str, String str2, int i, g gVar) throws Throwable {
        BufferedReader bufferedReader;
        HttpURLConnection httpURLConnectionD;
        int responseCode;
        int i2;
        ?? r10;
        StringBuilder sb;
        h hVar = new h();
        BufferedReader bufferedReader2 = null;
        bufferedReader2 = null;
        bufferedReader2 = null;
        bufferedReader2 = null;
        BufferedReader bufferedReader3 = null;
        bufferedReader2 = null;
        bufferedReader2 = null;
        bufferedReader2 = null;
        ?? r3 = 0;
        try {
            try {
                try {
                    httpURLConnectionD = d(str, str2, i, gVar);
                    responseCode = httpURLConnectionD.getResponseCode();
                    i2 = 0;
                } catch (SocketTimeoutException e2) {
                    e = e2;
                    str2 = 0;
                } catch (UnknownHostException e3) {
                    e = e3;
                    str2 = 0;
                } catch (IOException e4) {
                    e = e4;
                    str2 = 0;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = null;
                }
            } catch (IOException e5) {
                e5.printStackTrace();
            }
            if (responseCode == 200) {
                str2 = httpURLConnectionD.getInputStream();
                try {
                    sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(str2));
                } catch (SocketTimeoutException e6) {
                    e = e6;
                } catch (UnknownHostException e7) {
                    e = e7;
                } catch (IOException e8) {
                    e = e8;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = null;
                }
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                        sb.append("\n");
                    } catch (SocketTimeoutException e9) {
                        e = e9;
                        bufferedReader2 = bufferedReader;
                        hVar.b = j.a(80005, cn.com.chinatelecom.account.api.a.d.a(j.f) + "-" + gVar.c + "-" + e.getMessage());
                        CtAuth.warn(b, "SocketTimeoutException-" + gVar.c + "-" + e.getMessage(), e);
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("SocketTimeoutException ：");
                        sb2.append(e.getMessage());
                        cn.com.chinatelecom.account.api.e.f.a(gVar.d, hVar.b, sb2.toString());
                        if (bufferedReader2 != null) {
                            bufferedReader2.close();
                        }
                        if (str2 != 0) {
                            str2.close();
                        }
                    } catch (UnknownHostException e10) {
                        e = e10;
                        bufferedReader2 = bufferedReader;
                        hVar.b = j.a(80006, cn.com.chinatelecom.account.api.a.d.a(j.g) + "-" + gVar.c + "-" + e.getMessage());
                        CtAuth.warn(b, "UnknownHostException-" + gVar.c + "-" + e.getMessage(), e);
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("UnknownHostException ：");
                        sb3.append(e.getMessage());
                        cn.com.chinatelecom.account.api.e.f.a(gVar.d, hVar.b, sb3.toString());
                        if (bufferedReader2 != null) {
                            bufferedReader2.close();
                        }
                        if (str2 != 0) {
                            str2.close();
                        }
                    } catch (IOException e11) {
                        e = e11;
                        bufferedReader2 = bufferedReader;
                        hVar.b = j.a(80007, cn.com.chinatelecom.account.api.a.d.a(j.h) + "-" + gVar.c + "-" + e.getMessage());
                        CtAuth.warn(b, "IOException-" + gVar.c + "-" + e.getMessage(), e);
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("IOException ：");
                        sb4.append(e.getMessage());
                        cn.com.chinatelecom.account.api.e.f.a(gVar.d, hVar.b, sb4.toString());
                        if (bufferedReader2 != null) {
                            bufferedReader2.close();
                        }
                        if (str2 != 0) {
                            str2.close();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        r3 = str2;
                        try {
                            hVar.b = j.a(80001, cn.com.chinatelecom.account.api.a.d.a(j.b) + "-" + gVar.c + "-" + th.getMessage());
                            CtAuth.warn(b, "Throwable-" + gVar.c + "-" + th.getMessage(), th);
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append("Throwable ：");
                            sb5.append(th.getMessage());
                            cn.com.chinatelecom.account.api.e.f.a(gVar.d, hVar.b, sb5.toString());
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            if (r3 != 0) {
                                r3.close();
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e12) {
                                    e12.printStackTrace();
                                    throw th;
                                }
                            }
                            if (r3 != 0) {
                                r3.close();
                            }
                            throw th;
                        }
                    }
                    return hVar;
                }
                hVar.f1510a = 0;
                String string = sb.toString();
                if (!TextUtils.isEmpty(string)) {
                    JSONObject jSONObject = new JSONObject(string);
                    hVar.b = jSONObject;
                    cn.com.chinatelecom.account.api.e.f.a(gVar.d, jSONObject, null);
                }
                cn.com.chinatelecom.account.api.e.f.a(gVar.d, hVar.b, null);
                d dVarA = cn.com.chinatelecom.account.api.e.a.a(this.f1505a, httpURLConnectionD, true);
                if (dVarA != null) {
                    hVar.c = dVarA.f1504a;
                    cn.com.chinatelecom.account.api.e.f.a(gVar.d).f(dVarA.c);
                }
                bufferedReader3 = bufferedReader;
                r10 = str2;
            } else {
                if (responseCode != 302) {
                    hVar.b = j.a(80002, cn.com.chinatelecom.account.api.a.d.a(j.c) + "-" + gVar.c + "-code : " + responseCode);
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append(" Http response code :");
                    sb6.append(responseCode);
                    String string2 = sb6.toString();
                    cn.com.chinatelecom.account.api.e.f.a(gVar.d, hVar.b, string2);
                    CtAuth.info(b, string2);
                } else {
                    if (gVar.b < 10) {
                        gVar.b++;
                        gVar.f = false;
                        String headerField = httpURLConnectionD.getHeaderField(HttpHeaders.HEAD_KEY_LOCATION);
                        d dVarA2 = cn.com.chinatelecom.account.api.e.a.a(httpURLConnectionD);
                        cn.com.chinatelecom.account.api.e.f.a(gVar.d).f(dVarA2.c);
                        if (!TextUtils.isEmpty(dVarA2.d) && !dVarA2.d.equals("0")) {
                            i2 = 1;
                        }
                        CtAuth.info(b, " method : " + i2);
                        return a(headerField, null, i2, gVar);
                    }
                    JSONObject jSONObjectA = j.a(80001, cn.com.chinatelecom.account.api.a.d.a(j.b) + "-Redirect more than 10 times");
                    hVar.b = jSONObjectA;
                    cn.com.chinatelecom.account.api.e.f.a(gVar.d, jSONObjectA, "Redirect more than 10 times");
                }
                r10 = 0;
            }
            if (bufferedReader3 != null) {
                bufferedReader3.close();
            }
            if (r10 != 0) {
                r10.close();
            }
            return hVar;
        } catch (Throwable th5) {
            th = th5;
            bufferedReader = bufferedReader2;
            r3 = str2;
        }
    }
}
