package supwisdom;

import android.util.Log;
import com.github.faucamp.simplertmp.packets.RtmpHeader;
import com.github.faucamp.simplertmp.packets.SetPeerBandwidth;
import com.github.faucamp.simplertmp.packets.UserControl;
import com.huawei.hms.framework.common.ExceptionCode;
import io.dcloud.common.constant.AbsoluteConst;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: RtmpConnection.java */
/* JADX INFO: loaded from: classes.dex */
public class v00 {
    public static final Pattern J = Pattern.compile("^rtmp://([^/:]+)(:(\\d+))*/([^/]+)(/(.*))*$");
    public s00 A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public long H;
    public long I;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public i00 f9459a;
    public int b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f9460e;
    public String f;
    public String g;
    public String h;
    public String i;
    public Socket j;
    public x00 m;
    public w00 n;
    public BufferedInputStream o;
    public BufferedOutputStream p;
    public Thread q;
    public s00 y;
    public q00 z;
    public String k = "";
    public String l = "";
    public volatile boolean r = false;
    public volatile boolean s = false;
    public final Object t = new Object();
    public final Object u = new Object();
    public AtomicInteger v = new AtomicInteger(0);
    public int w = 0;
    public int x = 0;

    /* JADX INFO: compiled from: RtmpConnection.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Log.d("RtmpConnection", "starting main rx handler loop");
                v00.this.f();
            } catch (IOException e2) {
                Logger.getLogger(v00.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
        }
    }

    /* JADX INFO: compiled from: RtmpConnection.java */
    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9462a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[RtmpHeader.MessageType.values().length];
            b = iArr;
            try {
                iArr[RtmpHeader.MessageType.ABORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[RtmpHeader.MessageType.USER_CONTROL_MESSAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[RtmpHeader.MessageType.WINDOW_ACKNOWLEDGEMENT_SIZE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[RtmpHeader.MessageType.SET_PEER_BANDWIDTH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[RtmpHeader.MessageType.COMMAND_AMF0.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[UserControl.Type.values().length];
            f9462a = iArr2;
            try {
                iArr2[UserControl.Type.STREAM_BEGIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9462a[UserControl.Type.PING_REQUEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9462a[UserControl.Type.STREAM_EOF.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public v00(i00 i00Var) {
        this.f9459a = i00Var;
    }

    public boolean b(String str) {
        if (str == null) {
            this.f9459a.a(new IllegalArgumentException("No publish type specified"));
            return false;
        }
        this.f = str;
        return c();
    }

    public final boolean c() {
        if (!this.r) {
            this.f9459a.a(new IllegalStateException("Not connected to RTMP server"));
            return false;
        }
        if (this.w != 0) {
            this.f9459a.a(new IllegalStateException("Current stream object has existed"));
            return false;
        }
        Log.d("RtmpConnection", "createStream(): Sending releaseStream command...");
        int i = this.x + 1;
        this.x = i;
        b10 b10Var = new b10("releaseStream", i);
        b10Var.b().b(5);
        b10Var.a(new p00());
        b10Var.a(this.f9460e);
        a((f10) b10Var);
        Log.d("RtmpConnection", "createStream(): Sending FCPublish command...");
        int i2 = this.x + 1;
        this.x = i2;
        b10 b10Var2 = new b10("FCPublish", i2);
        b10Var2.b().b(5);
        b10Var2.a(new p00());
        b10Var2.a(this.f9460e);
        a((f10) b10Var2);
        Log.d("RtmpConnection", "createStream(): Sending createStream command...");
        u00 u00VarA = this.m.a(3);
        int i3 = this.x + 1;
        this.x = i3;
        b10 b10Var3 = new b10("createStream", i3, u00VarA);
        b10Var3.a(new p00());
        a((f10) b10Var3);
        synchronized (this.u) {
            try {
                this.u.wait(5000L);
            } catch (InterruptedException unused) {
            }
        }
        if (this.s) {
            this.f9459a.a("Connected" + this.k);
        } else {
            j();
        }
        return this.s;
    }

    public final void d() {
        if (!this.r) {
            this.f9459a.a(new IllegalStateException("Not connected to RTMP server"));
            return;
        }
        if (this.w == 0) {
            this.f9459a.a(new IllegalStateException("No current stream object exists"));
            return;
        }
        Log.d("RtmpConnection", "fmlePublish(): Sending publish command...");
        b10 b10Var = new b10("publish", 0);
        b10Var.b().b(5);
        b10Var.b().c(this.w);
        b10Var.a(new p00());
        b10Var.a(this.f9460e);
        b10Var.a(this.f);
        a((f10) b10Var);
    }

    public AtomicInteger e() {
        return this.v;
    }

    public final void f() throws IOException {
        while (!Thread.interrupted()) {
            try {
                f10 f10VarA = this.n.a(this.o);
                if (f10VarA != null) {
                    int i = b.b[f10VarA.b().c().ordinal()];
                    if (i == 1) {
                        this.m.a(((y00) f10VarA).d()).a();
                    } else if (i == 2) {
                        UserControl userControl = (UserControl) f10VarA;
                        int i2 = b.f9462a[userControl.e().ordinal()];
                        if (i2 != 1) {
                            if (i2 == 2) {
                                u00 u00VarA = this.m.a(2);
                                Log.d("RtmpConnection", "handleRxPacketLoop(): Sending PONG reply..");
                                a(new UserControl(userControl, u00VarA));
                            } else if (i2 == 3) {
                                Log.i("RtmpConnection", "handleRxPacketLoop(): Stream EOF reached, closing RTMP writer...");
                            }
                        } else if (this.w != userControl.d()) {
                            this.f9459a.a(new IllegalStateException("Current stream ID error!"));
                        }
                    } else if (i == 3) {
                        int iD = ((j10) f10VarA).d();
                        Log.d("RtmpConnection", "handleRxPacketLoop(): Setting acknowledgement window size: " + iD);
                        this.m.b(iD);
                    } else if (i == 4) {
                        this.m.b(((SetPeerBandwidth) f10VarA).d());
                        int iA = this.m.a();
                        u00 u00VarA2 = this.m.a(2);
                        Log.d("RtmpConnection", "handleRxPacketLoop(): Send acknowledgement window size: " + iA);
                        a(new j10(iA, u00VarA2));
                        this.j.setSendBufferSize(iA);
                    } else if (i != 5) {
                        Log.w("RtmpConnection", "handleRxPacketLoop(): Not handling unimplemented/unknown packet of type: " + f10VarA.b().c());
                    } else {
                        a((b10) f10VarA);
                    }
                }
            } catch (EOFException unused) {
                Thread.currentThread().interrupt();
            } catch (SocketException e2) {
                Log.e("RtmpConnection", "Caught SocketException while reading/decoding packet, shutting down: " + e2.getMessage());
                this.f9459a.a(e2);
            } catch (IOException e3) {
                Log.e("RtmpConnection", "Caught exception while reading/decoding packet, shutting down: " + e3.getMessage());
                this.f9459a.a(e3);
            }
        }
    }

    public final void g() {
        if (!this.r) {
            this.f9459a.a(new IllegalStateException("Not connected to RTMP server"));
            return;
        }
        if (this.w == 0) {
            this.f9459a.a(new IllegalStateException("No current stream object exists"));
            return;
        }
        Log.d("RtmpConnection", "onMetaData(): Sending empty onMetaData...");
        d10 d10Var = new d10("@setDataFrame");
        d10Var.b().c(this.w);
        d10Var.a("onMetaData");
        o00 o00Var = new o00();
        o00Var.a("duration", 0);
        o00Var.a("width", this.B);
        o00Var.a("height", this.C);
        o00Var.a("videodatarate", 0);
        o00Var.a("framerate", 0);
        o00Var.a("audiodatarate", 0);
        o00Var.a("audiosamplerate", 44100);
        o00Var.a("audiosamplesize", 16);
        o00Var.a("stereo", true);
        o00Var.a("filesize", 0);
        d10Var.a(o00Var);
        a(d10Var);
    }

    public final void h() {
        this.r = false;
        this.s = false;
        this.h = null;
        this.g = null;
        this.i = null;
        this.d = null;
        this.f9460e = null;
        this.f = null;
        this.w = 0;
        this.x = 0;
        this.v.set(0);
        this.l = "";
        this.y = null;
        this.z = null;
        this.A = null;
        this.j = null;
        this.m = null;
        this.n = null;
    }

    public final boolean i() {
        if (this.r) {
            this.f9459a.a(new IllegalStateException("Already connected to RTMP server"));
            return false;
        }
        u00.g();
        Log.d("RtmpConnection", "rtmpConnect(): Building 'connect' invoke packet");
        u00 u00VarA = this.m.a(3);
        int i = this.x + 1;
        this.x = i;
        b10 b10Var = new b10(ExceptionCode.CONNECT, i, u00VarA);
        b10Var.b().c(0);
        r00 r00Var = new r00();
        r00Var.a(AbsoluteConst.XML_APP, this.d);
        r00Var.a("flashVer", "LNX 11,2,202,233");
        r00Var.a("swfUrl", this.g);
        r00Var.a("tcUrl", this.h);
        r00Var.a("fpad", false);
        r00Var.a("capabilities", 239);
        r00Var.a("audioCodecs", 3575);
        r00Var.a("videoCodecs", 252);
        r00Var.a("videoFunction", 1);
        r00Var.a("pageUrl", this.i);
        r00Var.a("objectEncoding", 0);
        b10Var.a(r00Var);
        a((f10) b10Var);
        this.f9459a.b("Connecting");
        synchronized (this.t) {
            try {
                this.t.wait(5000L);
            } catch (InterruptedException unused) {
            }
        }
        if (!this.r) {
            j();
        }
        return this.r;
    }

    public final void j() {
        Socket socket = this.j;
        if (socket != null) {
            try {
                socket.shutdownInput();
                this.j.shutdownOutput();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            Thread thread = this.q;
            if (thread != null) {
                thread.interrupt();
                try {
                    this.q.join();
                } catch (InterruptedException unused) {
                    this.q.interrupt();
                }
                this.q = null;
            }
            try {
                this.j.close();
                Log.d("RtmpConnection", "socket closed");
            } catch (Exception e3) {
                Log.e("RtmpConnection", "shutdown(): failed to close socket", e3);
            }
            this.f9459a.b();
        }
        h();
    }

    public final void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        e10 e10Var = new e10();
        e10Var.a(outputStream);
        e10Var.b(outputStream);
        outputStream.flush();
        e10Var.a(inputStream);
        e10Var.b(inputStream);
        e10Var.c(outputStream);
        outputStream.flush();
        e10Var.c(inputStream);
    }

    public final void b() {
        if (!this.r) {
            this.f9459a.a(new IllegalStateException("Not connected to RTMP server"));
            return;
        }
        if (this.w == 0) {
            this.f9459a.a(new IllegalStateException("No current stream object exists"));
            return;
        }
        if (!this.s) {
            this.f9459a.a(new IllegalStateException("Not get _result(Netstream.Publish.Start)"));
            return;
        }
        Log.d("RtmpConnection", "closeStream(): setting current stream ID to 0");
        b10 b10Var = new b10("closeStream", 0);
        b10Var.b().b(5);
        b10Var.b().c(this.w);
        b10Var.a(new p00());
        a((f10) b10Var);
        this.f9459a.c();
    }

    public boolean a(String str) {
        Matcher matcher = J.matcher(str);
        if (matcher.matches()) {
            this.h = str.substring(0, str.lastIndexOf(47));
            this.g = "";
            this.i = "";
            this.c = matcher.group(1);
            String strGroup = matcher.group(3);
            this.b = strGroup != null ? Integer.parseInt(strGroup) : 1935;
            this.d = matcher.group(4);
            String strGroup2 = matcher.group(6);
            this.f9460e = strGroup2;
            if (this.d != null && strGroup2 != null) {
                Log.d("RtmpConnection", "connect() called. Host: " + this.c + ", port: " + this.b + ", appName: " + this.d + ", publishPath: " + this.f9460e);
                x00 x00Var = new x00();
                this.m = x00Var;
                this.n = new w00(x00Var);
                this.j = new Socket();
                try {
                    this.j.connect(new InetSocketAddress(this.c, this.b), 3000);
                    this.o = new BufferedInputStream(this.j.getInputStream(), 1024);
                    this.p = new BufferedOutputStream(this.j.getOutputStream(), 1024);
                    Log.d("RtmpConnection", "connect(): socket connection established, doing handhake...");
                    a(this.o, this.p);
                    Log.d("RtmpConnection", "connect(): handshake done");
                    Thread thread = new Thread(new a());
                    this.q = thread;
                    thread.start();
                    return i();
                } catch (IOException e2) {
                    e2.printStackTrace();
                    this.f9459a.a(e2);
                    return false;
                }
            }
            this.f9459a.a(new IllegalArgumentException("Invalid RTMP URL. Must be in format: rtmp://host[:port]/application/streamName"));
            return false;
        }
        this.f9459a.a(new IllegalArgumentException("Invalid RTMP URL. Must be in format: rtmp://host[:port]/application/streamName"));
        return false;
    }

    public void b(byte[] bArr, int i, int i2) {
        if (bArr != null && bArr.length != 0 && i2 >= 0) {
            if (!this.r) {
                this.f9459a.a(new IllegalStateException("Not connected to RTMP server"));
                return;
            }
            if (this.w == 0) {
                this.f9459a.a(new IllegalStateException("No current stream object exists"));
                return;
            }
            if (!this.s) {
                this.f9459a.a(new IllegalStateException("Not get _result(Netstream.Publish.Start)"));
                return;
            }
            i10 i10Var = new i10();
            i10Var.a(bArr, i);
            i10Var.b().a(i2);
            i10Var.b().c(this.w);
            a(i10Var);
            this.v.decrementAndGet();
            b(i10Var.b().d());
            this.f9459a.d();
            return;
        }
        this.f9459a.a(new IllegalArgumentException("Invalid Video Data"));
    }

    public final void b(int i) {
        this.E += i;
        int i2 = this.D;
        if (i2 == 0) {
            this.H = System.nanoTime() / 1000000;
            this.D++;
            return;
        }
        int i3 = i2 + 1;
        this.D = i3;
        if (i3 >= 48) {
            double dNanoTime = (System.nanoTime() / 1000000) - this.H;
            this.f9459a.c((((double) this.D) * 1000.0d) / dNanoTime);
            this.f9459a.b(((((double) this.E) * 8.0d) * 1000.0d) / dNanoTime);
            this.D = 0;
            this.E = 0;
        }
    }

    public void a() {
        if (this.j != null) {
            b();
        }
        j();
    }

    public void a(byte[] bArr, int i, int i2) {
        if (bArr != null && bArr.length != 0 && i2 >= 0) {
            if (!this.r) {
                this.f9459a.a(new IllegalStateException("Not connected to RTMP server"));
                return;
            }
            if (this.w == 0) {
                this.f9459a.a(new IllegalStateException("No current stream object exists"));
                return;
            }
            if (!this.s) {
                this.f9459a.a(new IllegalStateException("Not get _result(Netstream.Publish.Start)"));
                return;
            }
            a10 a10Var = new a10();
            a10Var.a(bArr, i);
            a10Var.b().a(i2);
            a10Var.b().c(this.w);
            a(a10Var);
            a(a10Var.b().d());
            this.f9459a.a();
            return;
        }
        this.f9459a.a(new IllegalArgumentException("Invalid Audio Data"));
    }

    public final String b(b10 b10Var) {
        String str;
        String str2;
        r00 r00Var = (r00) b10Var.d().get(1);
        if (r00Var.a("data") instanceof r00) {
            r00 r00Var2 = (r00) r00Var.a("data");
            this.y = (s00) r00Var2.a("srs_server_ip");
            this.z = (q00) r00Var2.a("srs_pid");
            this.A = (s00) r00Var2.a("srs_id");
        }
        StringBuilder sb = new StringBuilder();
        String str3 = "";
        sb.append("");
        if (this.y == null) {
            str = "";
        } else {
            str = " ip: " + this.y.a();
        }
        sb.append(str);
        String string = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(string);
        if (this.z == null) {
            str2 = "";
        } else {
            str2 = " pid: " + ((int) this.z.a());
        }
        sb2.append(str2);
        String string2 = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(string2);
        if (this.A != null) {
            str3 = " id: " + this.A.a();
        }
        sb3.append(str3);
        return sb3.toString();
    }

    public final void a(int i) {
        this.G += i;
        int i2 = this.F;
        if (i2 == 0) {
            this.I = System.nanoTime() / 1000000;
            this.F++;
            return;
        }
        int i3 = i2 + 1;
        this.F = i3;
        if (i3 >= 48) {
            this.f9459a.a(((((double) this.G) * 8.0d) * 1000.0d) / ((System.nanoTime() / 1000000) - this.I));
            this.F = 0;
            this.G = 0;
        }
    }

    public final void a(f10 f10Var) {
        try {
            u00 u00VarA = this.m.a(f10Var.b().b());
            u00VarA.b(f10Var.b());
            if (!(f10Var instanceof i10) && !(f10Var instanceof a10)) {
                f10Var.b().a((int) u00VarA.d());
            }
            f10Var.a(this.p, this.m.c(), u00VarA);
            if (f10Var instanceof b10) {
                this.m.a(((b10) f10Var).f(), ((b10) f10Var).e());
            }
            this.p.flush();
        } catch (SocketException e2) {
            if (this.l.contentEquals(e2.getMessage())) {
                return;
            }
            this.l = e2.getMessage();
            Log.e("RtmpConnection", "Caught SocketException during write loop, shutting down: " + e2.getMessage());
            this.f9459a.a(e2);
        } catch (IOException e3) {
            Log.e("RtmpConnection", "Caught IOException during write loop, shutting down: " + e3.getMessage());
            this.f9459a.a(e3);
        }
    }

    public final void a(b10 b10Var) throws IOException {
        String strE = b10Var.e();
        if (strE.equals("_result")) {
            String strD = this.m.d(b10Var.f());
            Log.d("RtmpConnection", "handleRxInvoke: Got result for invoked method: " + strD);
            if (ExceptionCode.CONNECT.equals(strD)) {
                this.k = b(b10Var);
                this.r = true;
                synchronized (this.t) {
                    this.t.notifyAll();
                }
                return;
            }
            if ("createStream".contains(strD)) {
                this.w = (int) ((q00) b10Var.d().get(1)).a();
                Log.d("RtmpConnection", "handleRxInvoke(): Stream ID to publish: " + this.w);
                if (this.f9460e == null || this.f == null) {
                    return;
                }
                d();
                return;
            }
            if ("releaseStream".contains(strD)) {
                Log.d("RtmpConnection", "handleRxInvoke(): 'releaseStream'");
                return;
            }
            if ("FCPublish".contains(strD)) {
                Log.d("RtmpConnection", "handleRxInvoke(): 'FCPublish'");
                return;
            }
            Log.w("RtmpConnection", "handleRxInvoke(): '_result' message received for unknown method: " + strD);
            return;
        }
        if (strE.equals("onBWDone")) {
            Log.d("RtmpConnection", "handleRxInvoke(): 'onBWDone'");
            return;
        }
        if (strE.equals("onFCPublish")) {
            Log.d("RtmpConnection", "handleRxInvoke(): 'onFCPublish'");
            return;
        }
        if (strE.equals("onStatus")) {
            String strA = ((s00) ((r00) b10Var.d().get(1)).a("code")).a();
            Log.d("RtmpConnection", "handleRxInvoke(): onStatus " + strA);
            if (strA.equals("NetStream.Publish.Start")) {
                g();
                this.s = true;
                synchronized (this.u) {
                    this.u.notifyAll();
                }
                return;
            }
            return;
        }
        Log.e("RtmpConnection", "handleRxInvoke(): Unknown/unhandled server invoke: " + b10Var);
    }

    public void a(int i, int i2) {
        this.B = i;
        this.C = i2;
    }
}
