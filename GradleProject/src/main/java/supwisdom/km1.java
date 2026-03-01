package supwisdom;

import com.taobao.weex.WXSDKManager;
import com.taobao.weex.utils.WXLogUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import supwisdom.se1;

/* JADX INFO: compiled from: Downloader.java */
/* JADX INFO: loaded from: classes2.dex */
public class km1 {

    /* JADX INFO: compiled from: Downloader.java */
    public static class a implements fe1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b f8184a;

        public a(b bVar) {
            this.f8184a = bVar;
        }

        @Override // supwisdom.fe1
        public void a(se1 se1Var, IOException iOException) {
            this.f8184a.a(iOException);
        }

        @Override // supwisdom.fe1
        public void a(ue1 ue1Var) throws Throwable {
            this.f8184a.a(ue1Var);
        }
    }

    /* JADX INFO: compiled from: Downloader.java */
    public static abstract class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f8185a;
        public String b;

        /* JADX INFO: compiled from: Downloader.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ File f8186a;

            public a(File file) {
                this.f8186a = file;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.a(this.f8186a);
            }
        }

        /* JADX INFO: renamed from: supwisdom.km1$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Downloader.java */
        public class RunnableC0217b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ long f8187a;
            public final /* synthetic */ long b;

            public RunnableC0217b(long j, long j2) {
                this.f8187a = j;
                this.b = j2;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.a((this.f8187a * 1.0f) / this.b);
            }
        }

        public b(String str, String str2) {
            this.f8185a = str;
            this.b = str2;
        }

        public abstract void a(float f);

        public abstract void a(File file);

        public abstract void a(Exception exc);

        public final File b(ue1 ue1Var) throws Throwable {
            FileOutputStream fileOutputStream;
            byte[] bArr = new byte[40960];
            InputStream inputStream = null;
            try {
                InputStream inputStreamA = ue1Var.a().a();
                try {
                    long jB = ue1Var.a().b();
                    long j = 0;
                    WXLogUtils.e(jB + "");
                    File file = new File(this.f8185a);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File file2 = new File(file, this.b);
                    fileOutputStream = new FileOutputStream(file2);
                    while (true) {
                        try {
                            int i = inputStreamA.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            long j2 = j + ((long) i);
                            fileOutputStream.write(bArr, 0, i);
                            a(new RunnableC0217b(j2, jB));
                            j = j2;
                        } catch (Throwable th) {
                            th = th;
                            inputStream = inputStreamA;
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    }
                    fileOutputStream.flush();
                    if (inputStreamA != null) {
                        inputStreamA.close();
                    }
                    fileOutputStream.close();
                    return file2;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = null;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
            }
        }

        public final void a(ue1 ue1Var) throws Throwable {
            File fileB = b(ue1Var);
            if (fileB == null || !fileB.exists() || fileB.length() <= 0) {
                a(new RuntimeException("Failed to save file"));
            } else {
                a(new a(fileB));
            }
        }

        public final void a(Runnable runnable) {
            WXSDKManager.getInstance().getWXRenderManager().postOnUiThread(runnable, 0L);
        }
    }

    public static void a(String str, b bVar) {
        re1 re1Var = new re1();
        se1.b bVar2 = new se1.b();
        bVar2.b(str);
        bVar2.b();
        re1Var.a(bVar2.a()).a(new a(bVar));
    }
}
