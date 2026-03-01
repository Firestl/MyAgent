package com.sangfor.sdk.storageipc.provider;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class KeyValue implements Parcelable {
    public static final Parcelable.Creator<KeyValue> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3959a;
    public String b;

    /* JADX INFO: compiled from: Proguard */
    public static class a implements Parcelable.Creator<KeyValue> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public KeyValue createFromParcel(Parcel parcel) {
            return new KeyValue(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public KeyValue[] newArray(int i) {
            return new KeyValue[i];
        }
    }

    public KeyValue() {
    }

    public String a() {
        return this.f3959a;
    }

    public String b() {
        return this.b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3959a);
        parcel.writeString(this.b);
    }

    public KeyValue(String str, String str2) {
        this.f3959a = str;
        this.b = str2;
    }

    public KeyValue(Parcel parcel) {
        this.f3959a = parcel.readString();
        this.b = parcel.readString();
    }
}
