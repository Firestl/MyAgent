package com.auth0.android.jwt;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.taobao.weex.el.parse.Operators;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;
import supwisdom.cs;
import supwisdom.es;
import supwisdom.fs;

/* JADX INFO: loaded from: classes.dex */
public class JWT implements Parcelable {
    public static final Parcelable.Creator<JWT> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1655a;
    public fs b;

    public static class a implements Parcelable.Creator<JWT> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JWT createFromParcel(Parcel parcel) {
            return new JWT(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JWT[] newArray(int i) {
            return new JWT[i];
        }
    }

    public class b extends TypeToken<Map<String, String>> {
        public b() {
        }
    }

    public JWT(String str) {
        b(str);
        this.f1655a = str;
    }

    public static Gson d() {
        return new GsonBuilder().registerTypeAdapter(fs.class, new es()).create();
    }

    public Map<String, cs> a() {
        return this.b.f7639e;
    }

    public Date b() {
        return this.b.b;
    }

    public String c() {
        return this.b.f7638a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return this.f1655a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1655a);
    }

    public final String a(String str) {
        try {
            return new String(Base64.decode(str, 11), "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            throw new DecodeException("Device doesn't support UTF-8 charset encoding.", e2);
        } catch (IllegalArgumentException e3) {
            throw new DecodeException("Received bytes didn't correspond to a valid Base64 encoded string.", e3);
        }
    }

    public final void b(String str) {
        String[] strArrC = c(str);
        this.b = (fs) a(a(strArrC[1]), fs.class);
        String str2 = strArrC[2];
    }

    public final String[] c(String str) {
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length == 2 && str.endsWith(Operators.DOT_STR)) {
            strArrSplit = new String[]{strArrSplit[0], strArrSplit[1], ""};
        }
        if (strArrSplit.length == 3) {
            return strArrSplit;
        }
        throw new DecodeException(String.format("The token was expected to have 3 parts, but got %s.", Integer.valueOf(strArrSplit.length)));
    }

    public final <T> T a(String str, Type type) {
        try {
            return (T) d().fromJson(str, type);
        } catch (Exception e2) {
            throw new DecodeException("The token's payload had an invalid JSON format.", e2);
        }
    }
}
