package com.getui.gtc.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseArray;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SparseArray<C0044a> f2217a = new SparseArray<>();
    public boolean b = false;
    public String c;

    /* JADX INFO: renamed from: com.getui.gtc.entity.a$a, reason: collision with other inner class name */
    public static class C0044a implements Parcelable {
        public static final Parcelable.Creator<C0044a> CREATOR = new Parcelable.Creator<C0044a>() { // from class: com.getui.gtc.entity.a.a.1
            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ C0044a createFromParcel(Parcel parcel) {
                return new C0044a(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final /* bridge */ /* synthetic */ C0044a[] newArray(int i) {
                return new C0044a[i];
            }
        };

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2218a;
        public String b;
        public String c;
        public String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f2219e;
        public String f;
        public long g;
        public String h;
        public boolean i;
        public boolean j;

        public C0044a() {
        }

        public C0044a(Parcel parcel) {
            this.f2218a = parcel.readInt();
            this.b = parcel.readString();
            this.c = parcel.readString();
            this.d = parcel.readString();
            this.f2219e = parcel.readString();
            this.f = parcel.readString();
            this.g = parcel.readLong();
            this.h = parcel.readString();
            this.i = parcel.readByte() != 0;
            this.j = parcel.readByte() != 0;
        }

        public final String a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", this.f2218a);
                jSONObject.put("version", this.b);
                jSONObject.put("name", this.c);
                jSONObject.put("cls_name", this.d);
                jSONObject.put("url", this.h);
                jSONObject.put("isdestroy", this.i);
                jSONObject.put("effective", String.valueOf(this.g));
                jSONObject.put("key", this.f);
                jSONObject.put("checksum", this.f2219e);
            } catch (Exception e2) {
                com.getui.gtc.i.c.a.a(e2);
            }
            return jSONObject.toString();
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f2218a);
            parcel.writeString(this.b);
            parcel.writeString(this.c);
            parcel.writeString(this.d);
            parcel.writeString(this.f2219e);
            parcel.writeString(this.f);
            parcel.writeLong(this.g);
            parcel.writeString(this.h);
            parcel.writeByte(this.i ? (byte) 1 : (byte) 0);
            parcel.writeByte(this.j ? (byte) 1 : (byte) 0);
        }
    }

    public static a a(Map<String, String> map) {
        String str = map.get("ext_infos");
        a aVar = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            a aVar2 = new a();
            aVar2.c = jSONObject.getString("version");
            String str2 = map.get("sdk.gtc.gws.load.enable");
            if (!TextUtils.isEmpty(str2) && !"none".equals(str2)) {
                aVar2.b = Boolean.parseBoolean(str2);
            }
            JSONArray jSONArray = jSONObject.getJSONArray("extensions");
            if (jSONArray.length() > 0) {
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    C0044a c0044a = new C0044a();
                    c0044a.f2218a = jSONObject2.getInt("id");
                    c0044a.b = jSONObject2.getString("version");
                    c0044a.c = jSONObject2.getString("name");
                    c0044a.d = jSONObject2.getString("cls_name");
                    c0044a.h = jSONObject2.getString("url");
                    c0044a.f2219e = jSONObject2.getString("checksum");
                    c0044a.f = jSONObject2.getString("key");
                    if (jSONObject2.has("isdestroy")) {
                        c0044a.i = jSONObject2.getBoolean("isdestroy");
                    }
                    if (jSONObject2.has("effective")) {
                        long j = 0;
                        try {
                            j = Long.parseLong(jSONObject2.getString("effective")) * 1000;
                        } catch (Exception e2) {
                            com.getui.gtc.i.c.a.b(e2);
                        }
                        c0044a.g = j;
                    }
                    aVar2.f2217a.put(c0044a.f2218a, c0044a);
                }
            }
            aVar = aVar2;
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
        }
        String str3 = map.get("sdk.push.plugins");
        if (aVar != null && !TextUtils.isEmpty(str3)) {
            for (String str4 : str3.split(",")) {
                try {
                    C0044a c0044aB = aVar.b(Integer.parseInt(str4));
                    if (c0044aB != null) {
                        c0044aB.j = true;
                    }
                } catch (Exception unused) {
                }
            }
        }
        return aVar;
    }

    public final C0044a a(int i) {
        SparseArray<C0044a> sparseArray = this.f2217a;
        return sparseArray.get(sparseArray.keyAt(i));
    }

    public final String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", this.c);
            JSONArray jSONArray = new JSONArray();
            jSONObject.put("extensions", jSONArray);
            int size = this.f2217a.size();
            for (int i = 0; i < size; i++) {
                jSONArray.put(i, new JSONObject(this.f2217a.get(this.f2217a.keyAt(i)).a()));
            }
        } catch (Exception e2) {
            com.getui.gtc.i.c.a.a(e2);
        }
        return jSONObject.toString();
    }

    public final C0044a b(int i) {
        return this.f2217a.get(i);
    }

    public final void c(int i) {
        this.f2217a.removeAt(i);
    }
}
