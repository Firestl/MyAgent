package io.dcloud.feature.nativeObj.data;

import android.os.Parcel;
import android.os.Parcelable;
import io.dcloud.common.util.PdrUtil;

/* JADX INFO: loaded from: classes3.dex */
public class NativeImageDataItem implements Parcelable {
    public static final Parcelable.Creator<NativeImageDataItem> CREATOR = new Parcelable.Creator<NativeImageDataItem>() { // from class: io.dcloud.feature.nativeObj.data.NativeImageDataItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NativeImageDataItem createFromParcel(Parcel parcel) {
            return new NativeImageDataItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NativeImageDataItem[] newArray(int i) {
            return new NativeImageDataItem[i];
        }
    };
    public String align;
    public String height;
    public String url;
    public String verticalAlign;
    public String width;

    public NativeImageDataItem() {
        this.url = "";
        this.align = "center";
        this.verticalAlign = "middle";
        this.height = "auto";
        this.width = "auto";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getHeight(int i, float f) {
        if (this.height.equals("auto")) {
            return -100;
        }
        return PdrUtil.convertToScreenInt(this.height, i, i, f);
    }

    public String getUrl() {
        return this.url;
    }

    public int getWidth(int i, float f) {
        if (this.width.equals("auto")) {
            return -100;
        }
        return PdrUtil.convertToScreenInt(this.width, i, i, f);
    }

    public void setUrl(String str) {
        this.url = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeString(this.align);
        parcel.writeString(this.verticalAlign);
        parcel.writeString(this.height);
        parcel.writeString(this.width);
    }

    public NativeImageDataItem(Parcel parcel) {
        this.url = "";
        this.align = "center";
        this.verticalAlign = "middle";
        this.height = "auto";
        this.width = "auto";
        this.url = parcel.readString();
        this.align = parcel.readString();
        this.verticalAlign = parcel.readString();
        this.height = parcel.readString();
        this.width = parcel.readString();
    }
}
