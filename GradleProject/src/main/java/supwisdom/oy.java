package supwisdom;

import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import com.tencent.connect.common.Constants;

/* JADX INFO: loaded from: classes.dex */
public abstract class oy {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ew f8744a;
    public final xy b;

    public oy(ew ewVar) {
        this.f8744a = ewVar;
        this.b = new xy(ewVar);
    }

    public static oy a(ew ewVar) {
        if (ewVar.a(1)) {
            return new ly(ewVar);
        }
        if (!ewVar.a(2)) {
            return new py(ewVar);
        }
        int iA = xy.a(ewVar, 1, 4);
        if (iA == 4) {
            return new fy(ewVar);
        }
        if (iA == 5) {
            return new gy(ewVar);
        }
        int iA2 = xy.a(ewVar, 1, 5);
        if (iA2 == 12) {
            return new hy(ewVar);
        }
        if (iA2 == 13) {
            return new iy(ewVar);
        }
        switch (xy.a(ewVar, 1, 7)) {
            case 56:
                return new jy(ewVar, "310", Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE);
            case 57:
                return new jy(ewVar, "320", Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE);
            case 58:
                return new jy(ewVar, "310", "13");
            case 59:
                return new jy(ewVar, "320", "13");
            case 60:
                return new jy(ewVar, "310", Constants.VIA_REPORT_TYPE_WPA_STATE);
            case 61:
                return new jy(ewVar, "320", Constants.VIA_REPORT_TYPE_WPA_STATE);
            case 62:
                return new jy(ewVar, "310", Constants.VIA_REPORT_TYPE_START_GROUP);
            case 63:
                return new jy(ewVar, "320", Constants.VIA_REPORT_TYPE_START_GROUP);
            default:
                throw new IllegalStateException("unknown decoder: " + ewVar);
        }
    }

    public final ew b() {
        return this.f8744a;
    }

    public abstract String c() throws FormatException, NotFoundException;

    public final xy a() {
        return this.b;
    }
}
