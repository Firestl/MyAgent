package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;
import supwisdom.ed;
import supwisdom.hd;
import supwisdom.xc;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f1290a;
    public final ArrayList<String> b;
    public final int[] c;
    public final int[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1291e;
    public final int f;
    public final String g;
    public final int h;
    public final int i;
    public final CharSequence j;
    public final int k;
    public final CharSequence l;
    public final ArrayList<String> m;
    public final ArrayList<String> n;
    public final boolean o;

    public static class a implements Parcelable.Creator<BackStackState> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BackStackState[] newArray(int i) {
            return new BackStackState[i];
        }
    }

    public BackStackState(xc xcVar) {
        int size = xcVar.f7826a.size();
        this.f1290a = new int[size * 5];
        if (!xcVar.h) {
            throw new IllegalStateException("Not on back stack");
        }
        this.b = new ArrayList<>(size);
        this.c = new int[size];
        this.d = new int[size];
        int i = 0;
        int i2 = 0;
        while (i < size) {
            hd.a aVar = xcVar.f7826a.get(i);
            int i3 = i2 + 1;
            this.f1290a[i2] = aVar.f7828a;
            ArrayList<String> arrayList = this.b;
            Fragment fragment = aVar.b;
            arrayList.add(fragment != null ? fragment.mWho : null);
            int[] iArr = this.f1290a;
            int i4 = i3 + 1;
            iArr[i3] = aVar.c;
            int i5 = i4 + 1;
            iArr[i4] = aVar.d;
            int i6 = i5 + 1;
            iArr[i5] = aVar.f7829e;
            iArr[i6] = aVar.f;
            this.c[i] = aVar.g.ordinal();
            this.d[i] = aVar.h.ordinal();
            i++;
            i2 = i6 + 1;
        }
        this.f1291e = xcVar.f;
        this.f = xcVar.g;
        this.g = xcVar.i;
        this.h = xcVar.t;
        this.i = xcVar.j;
        this.j = xcVar.k;
        this.k = xcVar.l;
        this.l = xcVar.m;
        this.m = xcVar.n;
        this.n = xcVar.o;
        this.o = xcVar.p;
    }

    public xc a(ed edVar) {
        xc xcVar = new xc(edVar);
        int i = 0;
        int i2 = 0;
        while (i < this.f1290a.length) {
            hd.a aVar = new hd.a();
            int i3 = i + 1;
            aVar.f7828a = this.f1290a[i];
            if (ed.H) {
                Log.v("FragmentManager", "Instantiate " + xcVar + " op #" + i2 + " base fragment #" + this.f1290a[i3]);
            }
            String str = this.b.get(i2);
            if (str != null) {
                aVar.b = edVar.g.get(str);
            } else {
                aVar.b = null;
            }
            aVar.g = Lifecycle.State.values()[this.c[i2]];
            aVar.h = Lifecycle.State.values()[this.d[i2]];
            int[] iArr = this.f1290a;
            int i4 = i3 + 1;
            int i5 = iArr[i3];
            aVar.c = i5;
            int i6 = i4 + 1;
            int i7 = iArr[i4];
            aVar.d = i7;
            int i8 = i6 + 1;
            int i9 = iArr[i6];
            aVar.f7829e = i9;
            int i10 = iArr[i8];
            aVar.f = i10;
            xcVar.b = i5;
            xcVar.c = i7;
            xcVar.d = i9;
            xcVar.f7827e = i10;
            xcVar.a(aVar);
            i2++;
            i = i8 + 1;
        }
        xcVar.f = this.f1291e;
        xcVar.g = this.f;
        xcVar.i = this.g;
        xcVar.t = this.h;
        xcVar.h = true;
        xcVar.j = this.i;
        xcVar.k = this.j;
        xcVar.l = this.k;
        xcVar.m = this.l;
        xcVar.n = this.m;
        xcVar.o = this.n;
        xcVar.p = this.o;
        xcVar.a(1);
        return xcVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.f1290a);
        parcel.writeStringList(this.b);
        parcel.writeIntArray(this.c);
        parcel.writeIntArray(this.d);
        parcel.writeInt(this.f1291e);
        parcel.writeInt(this.f);
        parcel.writeString(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        TextUtils.writeToParcel(this.j, parcel, 0);
        parcel.writeInt(this.k);
        TextUtils.writeToParcel(this.l, parcel, 0);
        parcel.writeStringList(this.m);
        parcel.writeStringList(this.n);
        parcel.writeInt(this.o ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.f1290a = parcel.createIntArray();
        this.b = parcel.createStringArrayList();
        this.c = parcel.createIntArray();
        this.d = parcel.createIntArray();
        this.f1291e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = parcel.readString();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.k = parcel.readInt();
        this.l = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.m = parcel.createStringArrayList();
        this.n = parcel.createStringArrayList();
        this.o = parcel.readInt() != 0;
    }
}
