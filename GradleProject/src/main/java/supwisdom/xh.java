package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import androidx.versionedparcelable.VersionedParcel;
import com.bumptech.glide.load.engine.GlideException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: VersionedParcelParcel.java */
/* JADX INFO: loaded from: classes.dex */
public class xh extends VersionedParcel {
    public final SparseIntArray d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Parcel f9765e;
    public final int f;
    public final int g;
    public final String h;
    public int i;
    public int j;
    public int k;

    public xh(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new j4(), new j4(), new j4());
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean a(int i) {
        while (this.j < this.g) {
            int i2 = this.k;
            if (i2 == i) {
                return true;
            }
            if (String.valueOf(i2).compareTo(String.valueOf(i)) > 0) {
                return false;
            }
            this.f9765e.setDataPosition(this.j);
            int i3 = this.f9765e.readInt();
            this.k = this.f9765e.readInt();
            this.j += i3;
        }
        return this.k == i;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void b(int i) {
        a();
        this.i = i;
        this.d.put(i, this.f9765e.dataPosition());
        c(0);
        c(i);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void c(int i) {
        this.f9765e.writeInt(i);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean d() {
        return this.f9765e.readInt() != 0;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public byte[] e() {
        int i = this.f9765e.readInt();
        if (i < 0) {
            return null;
        }
        byte[] bArr = new byte[i];
        this.f9765e.readByteArray(bArr);
        return bArr;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public CharSequence f() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.f9765e);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public int g() {
        return this.f9765e.readInt();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public <T extends Parcelable> T h() {
        return (T) this.f9765e.readParcelable(xh.class.getClassLoader());
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public String i() {
        return this.f9765e.readString();
    }

    public xh(Parcel parcel, int i, int i2, String str, j4<String, Method> j4Var, j4<String, Method> j4Var2, j4<String, Class> j4Var3) {
        super(j4Var, j4Var2, j4Var3);
        this.d = new SparseIntArray();
        this.i = -1;
        this.j = 0;
        this.k = -1;
        this.f9765e = parcel;
        this.f = i;
        this.g = i2;
        this.j = i;
        this.h = str;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public VersionedParcel b() {
        Parcel parcel = this.f9765e;
        int iDataPosition = parcel.dataPosition();
        int i = this.j;
        if (i == this.f) {
            i = this.g;
        }
        return new xh(parcel, iDataPosition, i, this.h + GlideException.IndentedAppendable.INDENT, this.f1447a, this.b, this.c);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void b(String str) {
        this.f9765e.writeString(str);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void a() {
        int i = this.i;
        if (i >= 0) {
            int i2 = this.d.get(i);
            int iDataPosition = this.f9765e.dataPosition();
            this.f9765e.setDataPosition(i2);
            this.f9765e.writeInt(iDataPosition - i2);
            this.f9765e.setDataPosition(iDataPosition);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void a(byte[] bArr) {
        if (bArr != null) {
            this.f9765e.writeInt(bArr.length);
            this.f9765e.writeByteArray(bArr);
        } else {
            this.f9765e.writeInt(-1);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void a(Parcelable parcelable) {
        this.f9765e.writeParcelable(parcelable, 0);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void a(boolean z) {
        this.f9765e.writeInt(z ? 1 : 0);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void a(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.f9765e, 0);
    }
}
