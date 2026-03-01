package supwisdom;

import android.content.Context;
import android.os.Process;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes2.dex */
public class sp1 {
    public static sp1 c;
    public static String d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f9201a = null;
    public int b;

    public class a extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Process f9202a;
        public BufferedReader b = null;
        public boolean c = true;
        public String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f9203e;
        public FileOutputStream f;

        public a(sp1 sp1Var, String str, String str2) {
            this.d = null;
            this.f = null;
            this.f9203e = str;
            try {
                this.f = new FileOutputStream(new File(str2, "log-" + sp1Var.a() + ".log"));
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
            this.d = "logcat *:e *:i | grep \"(" + this.f9203e + ")\"";
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            FileOutputStream fileOutputStream;
            String line;
            try {
                try {
                    try {
                        this.f9202a = Runtime.getRuntime().exec(this.d);
                        this.b = new BufferedReader(new InputStreamReader(this.f9202a.getInputStream()), 1024);
                        while (this.c && (line = this.b.readLine()) != null && this.c) {
                            if (line.length() != 0 && this.f != null && line.contains(this.f9203e)) {
                                this.f.write((line + "\n").getBytes());
                            }
                        }
                        Process process = this.f9202a;
                        if (process != null) {
                            process.destroy();
                            this.f9202a = null;
                        }
                        BufferedReader bufferedReader = this.b;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                                this.b = null;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        fileOutputStream = this.f;
                    } catch (Throwable th) {
                        Process process2 = this.f9202a;
                        if (process2 != null) {
                            process2.destroy();
                            this.f9202a = null;
                        }
                        BufferedReader bufferedReader2 = this.b;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                                this.b = null;
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        FileOutputStream fileOutputStream2 = this.f;
                        if (fileOutputStream2 == null) {
                            throw th;
                        }
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        this.f = null;
                        throw th;
                    }
                } catch (IOException e5) {
                    e5.printStackTrace();
                    Process process3 = this.f9202a;
                    if (process3 != null) {
                        process3.destroy();
                        this.f9202a = null;
                    }
                    BufferedReader bufferedReader3 = this.b;
                    if (bufferedReader3 != null) {
                        try {
                            bufferedReader3.close();
                            this.b = null;
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    FileOutputStream fileOutputStream3 = this.f;
                    if (fileOutputStream3 == null) {
                        return;
                    } else {
                        fileOutputStream3.close();
                    }
                }
            } catch (IOException e7) {
                e7.printStackTrace();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
                this.f = null;
            }
        }
    }

    public sp1(Context context) {
        a(context);
        this.b = Process.myPid();
    }

    public void a(Context context) {
        try {
            d = context.getExternalFilesDir("SynpayLog").getAbsolutePath();
            File file = new File(d);
            if (file.exists()) {
                a(file);
            } else {
                file.mkdirs();
            }
        } catch (Exception unused) {
        }
    }

    public String a() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
    }

    public final void a(File file) {
        File file2;
        String path = file.getPath();
        if (file.exists() && file.isDirectory()) {
            String[] list = file.list();
            for (int i = 0; i < list.length; i++) {
                String str = File.separator;
                if (path.endsWith(str)) {
                    file2 = new File(path + list[i]);
                } else {
                    file2 = new File(path + str + list[i]);
                }
                if (file2.isFile()) {
                    file2.delete();
                }
                if (file2.isDirectory()) {
                    a(new File(path + "/" + list[i]));
                }
            }
        }
    }
}
