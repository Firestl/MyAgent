package com.dmcbig.mediapicker.entity;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class Folder implements Parcelable {
    public static final Parcelable.Creator<Folder> CREATOR = new Parcelable.Creator<Folder>() { // from class: com.dmcbig.mediapicker.entity.Folder.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Folder createFromParcel(Parcel parcel) {
            return new Folder(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Folder[] newArray(int i) {
            return new Folder[i];
        }
    };
    public int count;
    public ArrayList<Media> medias;
    public String name;

    public Folder(String str) {
        this.medias = new ArrayList<>();
        this.name = str;
    }

    public void addMedias(Media media) {
        this.medias.add(media);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ArrayList<Media> getMedias() {
        return this.medias;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeInt(this.count);
        parcel.writeTypedList(this.medias);
    }

    public Folder(Parcel parcel) {
        this.medias = new ArrayList<>();
        this.name = parcel.readString();
        this.count = parcel.readInt();
        this.medias = parcel.createTypedArrayList(Media.CREATOR);
    }
}
