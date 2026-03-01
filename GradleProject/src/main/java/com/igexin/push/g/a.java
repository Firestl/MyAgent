package com.igexin.push.g;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileReader;

/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3578a = "BasicCheck";

    public static String a(int i) {
        String strTrim;
        try {
            strTrim = a(String.format("/proc/%d/cmdline", Integer.valueOf(i))).trim();
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            strTrim = null;
        }
        try {
            if (TextUtils.isEmpty(strTrim)) {
                return a(String.format("/proc/%d/stat", Integer.valueOf(i))).split("\\s+")[1].replace("(", "").replace(")", "");
            }
        } catch (Exception e3) {
            com.igexin.c.a.c.a.a(e3);
        }
        return strTrim;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r7v9 */
    public static String a(String str) throws Throwable {
        FileReader fileReader;
        Throwable th;
        BufferedReader bufferedReader;
        try {
            try {
                StringBuilder sb = new StringBuilder();
                fileReader = new FileReader((String) str);
                try {
                    bufferedReader = new BufferedReader(fileReader);
                    while (true) {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            sb.append(line);
                            sb.append("\n");
                        } catch (Exception e2) {
                            e = e2;
                            com.igexin.c.a.c.a.a(e);
                            if (fileReader != null) {
                                try {
                                    fileReader.close();
                                } catch (Exception e3) {
                                    com.igexin.c.a.c.a.a(e3);
                                }
                            }
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception e4) {
                                    com.igexin.c.a.c.a.a(e4);
                                }
                            }
                            return null;
                        }
                    }
                    if (sb.length() > 2) {
                        String strSubstring = sb.substring(0, sb.length() - 2);
                        try {
                            fileReader.close();
                        } catch (Exception e5) {
                            com.igexin.c.a.c.a.a(e5);
                        }
                        try {
                            bufferedReader.close();
                        } catch (Exception e6) {
                            com.igexin.c.a.c.a.a(e6);
                        }
                        return strSubstring;
                    }
                    String string = sb.toString();
                    try {
                        fileReader.close();
                    } catch (Exception e7) {
                        com.igexin.c.a.c.a.a(e7);
                    }
                    try {
                        bufferedReader.close();
                    } catch (Exception e8) {
                        com.igexin.c.a.c.a.a(e8);
                    }
                    return string;
                } catch (Exception e9) {
                    e = e9;
                    bufferedReader = null;
                } catch (Throwable th2) {
                    th = th2;
                    str = 0;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Exception e10) {
                            com.igexin.c.a.c.a.a(e10);
                        }
                    }
                    if (str == 0) {
                        throw th;
                    }
                    try {
                        str.close();
                        throw th;
                    } catch (Exception e11) {
                        com.igexin.c.a.c.a.a(e11);
                        throw th;
                    }
                }
            } catch (Exception e12) {
                e = e12;
                bufferedReader = null;
                fileReader = null;
            } catch (Throwable th3) {
                fileReader = null;
                th = th3;
                str = 0;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static boolean a() {
        try {
            Class.forName("com.igexin.push.g.g");
            return true;
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            return false;
        }
    }

    public static boolean b() {
        try {
            for (String str : com.igexin.push.config.d.B.split(",")) {
                if (n.e().toLowerCase().contains(str.toLowerCase())) {
                    return true;
                }
            }
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
        return false;
    }

    public static boolean c() {
        try {
            for (String str : com.igexin.push.core.e.aI.split(",")) {
                if (com.igexin.push.core.e.F.toLowerCase().contains(str.toLowerCase())) {
                    return true;
                }
            }
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
        return false;
    }
}
