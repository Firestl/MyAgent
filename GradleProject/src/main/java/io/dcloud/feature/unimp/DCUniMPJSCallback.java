package io.dcloud.feature.unimp;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class DCUniMPJSCallback implements Parcelable {
    public static final Parcelable.Creator<DCUniMPJSCallback> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f6673a;
    public String b;
    public String c;

    public static class a implements Parcelable.Creator<DCUniMPJSCallback> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public DCUniMPJSCallback createFromParcel(Parcel parcel) {
            return new DCUniMPJSCallback(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public DCUniMPJSCallback[] newArray(int i) {
            return new DCUniMPJSCallback[i];
        }
    }

    public DCUniMPJSCallback(Parcel parcel) {
        this.c = parcel.readString();
        this.f6673a = parcel.readString();
        this.b = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void invoke(Object obj) {
        c.a().a(this.c, this.f6673a, this.b, obj, false);
    }

    public void invokeAndKeepAlive(Object obj) {
        c.a().a(this.c, this.f6673a, this.b, obj, true);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.f6673a);
        parcel.writeString(this.b);
    }

    public DCUniMPJSCallback(String str, String str2, String str3) {
        this.c = str;
        this.f6673a = str2;
        this.b = str3;
    }
}
