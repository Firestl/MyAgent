package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import com.facebook.imageutils.JfifUtil;
import com.google.zxing.oned.Code39Reader;
import com.tencent.ijk.media.player.IjkMediaPlayer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bouncycastle.pqc.crypto.qtesla.HashUtils;
import tv.cjump.jni.DeviceUtils;

/* JADX INFO: loaded from: classes.dex */
public final class ey extends vx {
    public static final int[] k = {7, 5, 4, 3, 1};
    public static final int[] l = {4, 20, 52, 104, 204};
    public static final int[] m = {0, 348, 1388, 2948, 3988};
    public static final int[][] n = {new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};
    public static final int[][] o = {new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, 180, 118, 143, 7, 21, 63}, new int[]{189, com.igexin.push.config.c.G, 13, 39, 117, 140, 209, 205}, new int[]{193, 157, 49, 147, 19, 57, 171, 91}, new int[]{62, 186, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_256_RATE, 197, 169, 85, 44, 132}, new int[]{185, 133, 188, 142, 4, 12, 36, 108}, new int[]{113, 128, 173, 97, 80, 29, 87, 50}, new int[]{150, 28, 84, 41, 123, 158, 52, 156}, new int[]{46, 138, 203, 187, 139, 206, 196, 166}, new int[]{76, 17, 51, 153, 37, 111, 122, 155}, new int[]{43, 129, 176, 106, 107, 110, 119, 146}, new int[]{16, 48, 144, 10, 30, 90, 59, 177}, new int[]{109, 116, 137, 200, 178, 112, 125, 164}, new int[]{70, IjkMediaPlayer.MEDIA_HLS_KEY_ERROR, JfifUtil.MARKER_RST0, 202, 184, 130, 179, 115}, new int[]{134, 191, 151, 31, 93, 68, 204, 190}, new int[]{Code39Reader.ASTERISK_ENCODING, 22, 66, 198, 172, 94, 71, 2}, new int[]{6, 18, 54, 162, 64, 192, 154, 40}, new int[]{120, 149, 25, 75, 14, 42, 126, 167}, new int[]{79, 26, 78, 23, 69, 207, 199, 175}, new int[]{103, 98, 83, 38, 114, com.igexin.push.core.a.c.h.b, 182, 124}, new int[]{161, 61, DeviceUtils.EM_AARCH64, 127, 170, 88, 53, 159}, new int[]{55, 165, 73, 8, 24, 72, 5, 15}, new int[]{45, 135, 194, 160, 58, 174, 100, 89}};
    public static final int[][] p = {new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};
    public final List<cy> g = new ArrayList(11);
    public final List<dy> h = new ArrayList();
    public final int[] i = new int[2];
    public boolean j;

    public static boolean b(List<cy> list) {
        boolean z;
        for (int[] iArr : p) {
            if (list.size() <= iArr.length) {
                int i = 0;
                while (true) {
                    if (i >= list.size()) {
                        z = true;
                        break;
                    }
                    if (list.get(i).a().c() != iArr[i]) {
                        z = false;
                        break;
                    }
                    i++;
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void c(int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length / 2; i++) {
            int i2 = iArr[i];
            int i3 = (length - i) - 1;
            iArr[i] = iArr[i3];
            iArr[i3] = i2;
        }
    }

    public final void a(int i) throws NotFoundException {
        boolean z;
        boolean z2;
        boolean z3;
        int iA = vx.a(e());
        int iA2 = vx.a(c());
        int i2 = (iA + iA2) - i;
        boolean z4 = true;
        boolean z5 = (iA & 1) == 1;
        boolean z6 = (iA2 & 1) == 0;
        if (iA > 13) {
            z = false;
            z2 = true;
        } else {
            z = iA < 4;
            z2 = false;
        }
        if (iA2 > 13) {
            z3 = true;
        } else {
            z = iA2 < 4;
            z3 = false;
        }
        if (i2 == 1) {
            if (z5) {
                if (z6) {
                    throw NotFoundException.getNotFoundInstance();
                }
                z4 = z;
                z2 = true;
            } else {
                if (!z6) {
                    throw NotFoundException.getNotFoundInstance();
                }
                z4 = z;
                z3 = true;
            }
        } else if (i2 == -1) {
            if (z5) {
                if (z6) {
                    throw NotFoundException.getNotFoundInstance();
                }
            } else {
                if (!z6) {
                    throw NotFoundException.getNotFoundInstance();
                }
                z4 = z;
                z = true;
            }
        } else {
            if (i2 != 0) {
                throw NotFoundException.getNotFoundInstance();
            }
            if (z5) {
                if (!z6) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (iA >= iA2) {
                    z4 = z;
                    z = true;
                    z2 = true;
                }
                z3 = true;
            } else {
                if (z6) {
                    throw NotFoundException.getNotFoundInstance();
                }
                z4 = z;
            }
        }
        if (z4) {
            if (z2) {
                throw NotFoundException.getNotFoundInstance();
            }
            vx.b(e(), f());
        }
        if (z2) {
            vx.a(e(), f());
        }
        if (z) {
            if (z3) {
                throw NotFoundException.getNotFoundInstance();
            }
            vx.b(c(), f());
        }
        if (z3) {
            vx.a(c(), d());
        }
    }

    public final boolean g() {
        cy cyVar = this.g.get(0);
        wx wxVarB = cyVar.b();
        wx wxVarC = cyVar.c();
        if (wxVarC == null) {
            return false;
        }
        int iA = wxVarC.a();
        int i = 2;
        for (int i2 = 1; i2 < this.g.size(); i2++) {
            cy cyVar2 = this.g.get(i2);
            iA += cyVar2.b().a();
            i++;
            wx wxVarC2 = cyVar2.c();
            if (wxVarC2 != null) {
                iA += wxVarC2.a();
                i++;
            }
        }
        return ((i + (-4)) * 211) + (iA % 211) == wxVarB.b();
    }

    @Override // supwisdom.ox, supwisdom.wv
    public void reset() {
        this.g.clear();
        this.h.clear();
    }

    public cy b(ew ewVar, List<cy> list, int i) throws NotFoundException {
        xx xxVarA;
        wx wxVarA;
        boolean z = list.size() % 2 == 0;
        if (this.j) {
            z = !z;
        }
        int iA = -1;
        boolean z2 = true;
        do {
            a(ewVar, list, iA);
            xxVarA = a(ewVar, i, z);
            if (xxVarA == null) {
                iA = a(ewVar, this.i[0]);
            } else {
                z2 = false;
            }
        } while (z2);
        wx wxVarA2 = a(ewVar, xxVarA, z, true);
        if (!list.isEmpty() && list.get(list.size() - 1).d()) {
            throw NotFoundException.getNotFoundInstance();
        }
        try {
            wxVarA = a(ewVar, xxVarA, z, false);
        } catch (NotFoundException unused) {
            wxVarA = null;
        }
        return new cy(wxVarA2, wxVarA, xxVarA, true);
    }

    public final List<cy> a(boolean z) {
        List<cy> listA = null;
        if (this.h.size() > 25) {
            this.h.clear();
            return null;
        }
        this.g.clear();
        if (z) {
            Collections.reverse(this.h);
        }
        try {
            listA = a(new ArrayList(), 0);
        } catch (NotFoundException unused) {
        }
        if (z) {
            Collections.reverse(this.h);
        }
        return listA;
    }

    public static xv a(List<cy> list) throws FormatException, NotFoundException {
        String strC = oy.a(by.a(list)).c();
        yv[] yvVarArrA = list.get(0).a().a();
        yv[] yvVarArrA2 = list.get(list.size() - 1).a().a();
        return new xv(strC, null, new yv[]{yvVarArrA[0], yvVarArrA[1], yvVarArrA2[0], yvVarArrA2[1]}, BarcodeFormat.RSS_EXPANDED);
    }

    public wx a(ew ewVar, xx xxVar, boolean z, boolean z2) throws NotFoundException {
        int[] iArrA = a();
        iArrA[0] = 0;
        iArrA[1] = 0;
        iArrA[2] = 0;
        iArrA[3] = 0;
        iArrA[4] = 0;
        iArrA[5] = 0;
        iArrA[6] = 0;
        iArrA[7] = 0;
        if (z2) {
            ox.b(ewVar, xxVar.b()[0], iArrA);
        } else {
            ox.a(ewVar, xxVar.b()[1], iArrA);
            int i = 0;
            for (int length = iArrA.length - 1; i < length; length--) {
                int i2 = iArrA[i];
                iArrA[i] = iArrA[length];
                iArrA[length] = i2;
                i++;
            }
        }
        float fA = vx.a(iArrA) / 17;
        float f = (xxVar.b()[1] - xxVar.b()[0]) / 15.0f;
        if (Math.abs(fA - f) / f <= 0.3f) {
            int[] iArrE = e();
            int[] iArrC = c();
            float[] fArrF = f();
            float[] fArrD = d();
            for (int i3 = 0; i3 < iArrA.length; i3++) {
                float f2 = (iArrA[i3] * 1.0f) / fA;
                int i4 = (int) (0.5f + f2);
                if (i4 < 1) {
                    if (f2 < 0.3f) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                    i4 = 1;
                } else if (i4 > 8) {
                    if (f2 > 8.7f) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                    i4 = 8;
                }
                int i5 = i3 / 2;
                if ((i3 & 1) == 0) {
                    iArrE[i5] = i4;
                    fArrF[i5] = f2 - i4;
                } else {
                    iArrC[i5] = i4;
                    fArrD[i5] = f2 - i4;
                }
            }
            a(17);
            int iC = (((xxVar.c() * 4) + (z ? 0 : 2)) + (!z2 ? 1 : 0)) - 1;
            int i6 = 0;
            int i7 = 0;
            for (int length2 = iArrE.length - 1; length2 >= 0; length2--) {
                if (a(xxVar, z, z2)) {
                    i6 += iArrE[length2] * o[iC][length2 * 2];
                }
                i7 += iArrE[length2];
            }
            int i8 = 0;
            for (int length3 = iArrC.length - 1; length3 >= 0; length3--) {
                if (a(xxVar, z, z2)) {
                    i8 += iArrC[length3] * o[iC][(length3 * 2) + 1];
                }
            }
            int i9 = i6 + i8;
            if ((i7 & 1) == 0 && i7 <= 13 && i7 >= 4) {
                int i10 = (13 - i7) / 2;
                int i11 = k[i10];
                return new wx((ay.a(iArrE, i11, true) * l[i10]) + ay.a(iArrC, 9 - i11, false) + m[i10], i9);
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // supwisdom.ox
    public xv a(int i, ew ewVar, Map<DecodeHintType, ?> map) throws FormatException, NotFoundException {
        this.g.clear();
        this.j = false;
        try {
            return a(a(i, ewVar));
        } catch (NotFoundException unused) {
            this.g.clear();
            this.j = true;
            return a(a(i, ewVar));
        }
    }

    public List<cy> a(int i, ew ewVar) throws NotFoundException {
        while (true) {
            try {
                this.g.add(b(ewVar, this.g, i));
            } catch (NotFoundException e2) {
                if (!this.g.isEmpty()) {
                    if (g()) {
                        return this.g;
                    }
                    boolean z = !this.h.isEmpty();
                    a(i, false);
                    if (z) {
                        List<cy> listA = a(false);
                        if (listA != null) {
                            return listA;
                        }
                        List<cy> listA2 = a(true);
                        if (listA2 != null) {
                            return listA2;
                        }
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
                throw e2;
            }
        }
    }

    public final void a(ew ewVar, List<cy> list, int i) throws NotFoundException {
        int[] iArrB = b();
        iArrB[0] = 0;
        iArrB[1] = 0;
        iArrB[2] = 0;
        iArrB[3] = 0;
        int iC = ewVar.c();
        if (i < 0) {
            i = list.isEmpty() ? 0 : list.get(list.size() - 1).a().b()[1];
        }
        boolean z = list.size() % 2 != 0;
        if (this.j) {
            z = !z;
        }
        boolean z2 = false;
        while (i < iC) {
            z2 = !ewVar.a(i);
            if (!z2) {
                break;
            } else {
                i++;
            }
        }
        boolean z3 = z2;
        int i2 = 0;
        int i3 = i;
        while (i < iC) {
            if (ewVar.a(i) ^ z3) {
                iArrB[i2] = iArrB[i2] + 1;
            } else {
                if (i2 == 3) {
                    if (z) {
                        c(iArrB);
                    }
                    if (vx.b(iArrB)) {
                        int[] iArr = this.i;
                        iArr[0] = i3;
                        iArr[1] = i;
                        return;
                    }
                    if (z) {
                        c(iArrB);
                    }
                    i3 += iArrB[0] + iArrB[1];
                    iArrB[0] = iArrB[2];
                    iArrB[1] = iArrB[3];
                    iArrB[2] = 0;
                    iArrB[3] = 0;
                    i2--;
                } else {
                    i2++;
                }
                iArrB[i2] = 1;
                z3 = !z3;
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public static int a(ew ewVar, int i) {
        if (ewVar.a(i)) {
            return ewVar.b(ewVar.c(i));
        }
        return ewVar.c(ewVar.b(i));
    }

    public static boolean a(xx xxVar, boolean z, boolean z2) {
        return (xxVar.c() == 0 && z && z2) ? false : true;
    }

    public static boolean a(Iterable<cy> iterable, Iterable<dy> iterable2) {
        boolean z;
        boolean z2;
        Iterator<dy> it = iterable2.iterator();
        do {
            z = false;
            if (!it.hasNext()) {
                return false;
            }
            dy next = it.next();
            Iterator<cy> it2 = iterable.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = true;
                    break;
                }
                cy next2 = it2.next();
                Iterator<cy> it3 = next.a().iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        z2 = false;
                        break;
                    }
                    if (next2.equals(it3.next())) {
                        z2 = true;
                        break;
                    }
                }
                if (!z2) {
                    break;
                }
            }
        } while (!z);
        return true;
    }

    public final xx a(ew ewVar, int i, boolean z) {
        int i2;
        int i3;
        int i4;
        if (z) {
            int i5 = this.i[0] - 1;
            while (i5 >= 0 && !ewVar.a(i5)) {
                i5--;
            }
            int i6 = i5 + 1;
            int[] iArr = this.i;
            i4 = iArr[0] - i6;
            i2 = iArr[1];
            i3 = i6;
        } else {
            int[] iArr2 = this.i;
            int i7 = iArr2[0];
            int iC = ewVar.c(iArr2[1] + 1);
            i2 = iC;
            i3 = i7;
            i4 = iC - this.i[1];
        }
        int[] iArrB = b();
        System.arraycopy(iArrB, 0, iArrB, 1, iArrB.length - 1);
        iArrB[0] = i4;
        try {
            return new xx(vx.a(iArrB, n), new int[]{i3, i2}, i3, i2, i);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    public static void a(List<cy> list, List<dy> list2) {
        boolean z;
        Iterator<dy> it = list2.iterator();
        while (it.hasNext()) {
            dy next = it.next();
            if (next.a().size() != list.size()) {
                Iterator<cy> it2 = next.a().iterator();
                while (true) {
                    z = false;
                    boolean z2 = true;
                    if (!it2.hasNext()) {
                        z = true;
                        break;
                    }
                    cy next2 = it2.next();
                    Iterator<cy> it3 = list.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            z2 = false;
                            break;
                        } else if (next2.equals(it3.next())) {
                            break;
                        }
                    }
                    if (!z2) {
                        break;
                    }
                }
                if (z) {
                    it.remove();
                }
            }
        }
    }

    public final void a(int i, boolean z) {
        boolean zA = false;
        int i2 = 0;
        boolean zA2 = false;
        while (true) {
            if (i2 >= this.h.size()) {
                break;
            }
            dy dyVar = this.h.get(i2);
            if (dyVar.b() > i) {
                zA = dyVar.a(this.g);
                break;
            } else {
                zA2 = dyVar.a(this.g);
                i2++;
            }
        }
        if (zA || zA2 || a((Iterable<cy>) this.g, (Iterable<dy>) this.h)) {
            return;
        }
        this.h.add(i2, new dy(this.g, i, z));
        a(this.g, this.h);
    }

    public final List<cy> a(List<dy> list, int i) throws NotFoundException {
        while (i < this.h.size()) {
            dy dyVar = this.h.get(i);
            this.g.clear();
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.g.addAll(list.get(i2).a());
            }
            this.g.addAll(dyVar.a());
            if (b(this.g)) {
                if (g()) {
                    return this.g;
                }
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(list);
                arrayList.add(dyVar);
                try {
                    return a(arrayList, i + 1);
                } catch (NotFoundException unused) {
                    continue;
                }
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
