package supwisdom;

import android.util.Log;
import com.github.faucamp.simplertmp.packets.RtmpHeader;
import com.github.faucamp.simplertmp.packets.SetPeerBandwidth;
import com.github.faucamp.simplertmp.packets.UserControl;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: RtmpDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class w00 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public x00 f9573a;

    /* JADX INFO: compiled from: RtmpDecoder.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9574a;

        static {
            int[] iArr = new int[RtmpHeader.MessageType.values().length];
            f9574a = iArr;
            try {
                iArr[RtmpHeader.MessageType.SET_CHUNK_SIZE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9574a[RtmpHeader.MessageType.ABORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9574a[RtmpHeader.MessageType.USER_CONTROL_MESSAGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9574a[RtmpHeader.MessageType.WINDOW_ACKNOWLEDGEMENT_SIZE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9574a[RtmpHeader.MessageType.SET_PEER_BANDWIDTH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9574a[RtmpHeader.MessageType.AUDIO.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9574a[RtmpHeader.MessageType.VIDEO.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9574a[RtmpHeader.MessageType.COMMAND_AMF0.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9574a[RtmpHeader.MessageType.DATA_AMF0.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f9574a[RtmpHeader.MessageType.ACKNOWLEDGEMENT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public w00(x00 x00Var) {
        this.f9573a = x00Var;
    }

    public f10 a(InputStream inputStream) throws IOException {
        f10 y00Var;
        RtmpHeader rtmpHeaderB = RtmpHeader.b(inputStream, this.f9573a);
        u00 u00VarA = this.f9573a.a(rtmpHeaderB.b());
        u00VarA.a(rtmpHeaderB);
        if (rtmpHeaderB.d() > this.f9573a.b()) {
            if (!u00VarA.a(inputStream, this.f9573a.b())) {
                return null;
            }
            inputStream = u00VarA.c();
        }
        switch (a.f9574a[rtmpHeaderB.c().ordinal()]) {
            case 1:
                g10 g10Var = new g10(rtmpHeaderB);
                g10Var.a(inputStream);
                Log.d("RtmpDecoder", "readPacket(): Setting chunk size to: " + g10Var.d());
                this.f9573a.c(g10Var.d());
                return null;
            case 2:
                y00Var = new y00(rtmpHeaderB);
                break;
            case 3:
                y00Var = new UserControl(rtmpHeaderB);
                break;
            case 4:
                y00Var = new j10(rtmpHeaderB);
                break;
            case 5:
                y00Var = new SetPeerBandwidth(rtmpHeaderB);
                break;
            case 6:
                y00Var = new a10(rtmpHeaderB);
                break;
            case 7:
                y00Var = new i10(rtmpHeaderB);
                break;
            case 8:
                y00Var = new b10(rtmpHeaderB);
                break;
            case 9:
                y00Var = new d10(rtmpHeaderB);
                break;
            case 10:
                y00Var = new z00(rtmpHeaderB);
                break;
            default:
                throw new IOException("No packet body implementation for message type: " + rtmpHeaderB.c());
        }
        y00Var.a(inputStream);
        return y00Var;
    }
}
