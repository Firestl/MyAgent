package com.sangfor.sdk.base;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class ServiceInfo implements Parcelable {
    public static final Parcelable.Creator<ServiceInfo> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3916a;
    public String b;
    public int c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3917e;
    public String f;
    public String g;

    /* JADX INFO: compiled from: Proguard */
    public static class a implements Parcelable.Creator<ServiceInfo> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ServiceInfo createFromParcel(Parcel parcel) {
            return new ServiceInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ServiceInfo[] newArray(int i) {
            return new ServiceInfo[i];
        }
    }

    public ServiceInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "ServiceInfo{authId='" + this.f3916a + Operators.SINGLE_QUOTE + ", authName='" + this.b + Operators.SINGLE_QUOTE + ", authType='" + this.c + Operators.SINGLE_QUOTE + ", description='" + this.d + Operators.SINGLE_QUOTE + ", iconPath='" + this.f3917e + Operators.SINGLE_QUOTE + ", iconType='" + this.f + Operators.SINGLE_QUOTE + ", subType='" + this.g + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3916a);
        parcel.writeString(this.b);
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.f3917e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
    }

    public ServiceInfo(Parcel parcel) {
        this.f3916a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readInt();
        this.d = parcel.readString();
        this.f3917e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
    }
}
