package supwisdom;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class hw0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f7884a = "";
    public String b = "NEWCAPECNEWCAPEC";
    public boolean c = false;

    public static String a(String str, String str2) {
        hw0 hw0Var = new hw0();
        hw0Var.a(str2);
        return hw0Var.b(str);
    }

    public static String b(String str, String str2) {
        hw0 hw0Var = new hw0();
        hw0Var.a(str2);
        return hw0Var.c(str);
    }

    public void a(String str) {
        this.f7884a = str;
    }

    public String b(String str) {
        byte[] bytes;
        byte[] bytes2;
        try {
            j0 j0Var = new j0();
            j0Var.c = false;
            j0Var.f8006a = 0;
            if (this.c) {
                bytes = k0.a(this.f7884a);
                bytes2 = k0.a(this.b);
            } else {
                bytes = this.f7884a.getBytes();
                bytes2 = this.b.getBytes();
            }
            i0 i0Var = new i0();
            i0Var.a(j0Var, bytes);
            String str2 = new String(i0Var.a(j0Var, bytes2, k0.a(str)), "UTF-8");
            return str2.substring(0, str2.lastIndexOf("�"));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public String c(String str) {
        byte[] bytes;
        byte[] bytes2;
        try {
            j0 j0Var = new j0();
            j0Var.c = true;
            j0Var.f8006a = 1;
            if (this.c) {
                bytes = k0.a(this.f7884a);
                bytes2 = k0.a(this.b);
            } else {
                bytes = this.f7884a.getBytes();
                bytes2 = this.b.getBytes();
            }
            i0 i0Var = new i0();
            i0Var.b(j0Var, bytes);
            String strB = k0.b(i0Var.a(j0Var, bytes2, str.getBytes("UTF-8")));
            return strB.trim().length() > 0 ? Pattern.compile("\\s*|\t|\r|\n").matcher(strB).replaceAll("") : strB;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
