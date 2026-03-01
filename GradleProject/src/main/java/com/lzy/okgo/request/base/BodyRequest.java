package com.lzy.okgo.request.base;

import android.text.TextUtils;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.BodyRequest;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import supwisdom.bt1;
import supwisdom.ct1;
import supwisdom.dw0;
import supwisdom.fw0;
import supwisdom.xs1;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object<R>] */
/* JADX INFO: loaded from: classes2.dex */
public abstract class BodyRequest<T, R extends BodyRequest> extends Request<T, R> {
    public static final long serialVersionUID = -6459175248476927501L;
    public byte[] bs;
    public String content;
    public transient File file;
    public boolean isMultipart;
    public boolean isSpliceUrl;
    public transient xs1 mediaType;
    public ct1 requestBody;

    public BodyRequest(String str) {
        super(str);
        this.isMultipart = false;
        this.isSpliceUrl = false;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        String str = (String) objectInputStream.readObject();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mediaType = xs1.b(str);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        xs1 xs1Var = this.mediaType;
        objectOutputStream.writeObject(xs1Var == null ? "" : xs1Var.toString());
    }

    /* JADX INFO: renamed from: addFileParams, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m12addFileParams(String str, List list) {
        return addFileParams(str, (List<File>) list);
    }

    /* JADX INFO: renamed from: addFileWrapperParams, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m13addFileWrapperParams(String str, List list) {
        return addFileWrapperParams(str, (List<HttpParams.FileWrapper>) list);
    }

    @Override // com.lzy.okgo.request.base.Request
    public ct1 generateRequestBody() {
        xs1 xs1Var;
        xs1 xs1Var2;
        xs1 xs1Var3;
        if (this.isSpliceUrl) {
            this.url = dw0.a(this.baseUrl, this.params.urlParamsMap);
        }
        ct1 ct1Var = this.requestBody;
        if (ct1Var != null) {
            return ct1Var;
        }
        String str = this.content;
        if (str != null && (xs1Var3 = this.mediaType) != null) {
            return ct1.create(xs1Var3, str);
        }
        byte[] bArr = this.bs;
        if (bArr != null && (xs1Var2 = this.mediaType) != null) {
            return ct1.create(xs1Var2, bArr);
        }
        File file = this.file;
        return (file == null || (xs1Var = this.mediaType) == null) ? dw0.a(this.params, this.isMultipart) : ct1.create(xs1Var, file);
    }

    public bt1.a generateRequestBuilder(ct1 ct1Var) {
        try {
            headers(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, String.valueOf(ct1Var.contentLength()));
        } catch (IOException e2) {
            fw0.a(e2);
        }
        bt1.a aVar = new bt1.a();
        dw0.a(aVar, this.headers);
        return aVar;
    }

    public R addFileParams(String str, List<File> list) {
        this.params.putFileParams(str, list);
        return this;
    }

    public R addFileWrapperParams(String str, List<HttpParams.FileWrapper> list) {
        this.params.putFileWrapperParams(str, list);
        return this;
    }

    /* JADX INFO: renamed from: isMultipart, reason: merged with bridge method [inline-methods] */
    public R m14isMultipart(boolean z) {
        this.isMultipart = z;
        return this;
    }

    /* JADX INFO: renamed from: isSpliceUrl, reason: merged with bridge method [inline-methods] */
    public R m15isSpliceUrl(boolean z) {
        this.isSpliceUrl = z;
        return this;
    }

    /* JADX INFO: renamed from: upRequestBody, reason: merged with bridge method [inline-methods] */
    public R m26upRequestBody(ct1 ct1Var) {
        this.requestBody = ct1Var;
        return this;
    }

    /* JADX INFO: renamed from: upBytes, reason: merged with bridge method [inline-methods] */
    public R m19upBytes(byte[] bArr) {
        this.bs = bArr;
        this.mediaType = HttpParams.MEDIA_TYPE_STREAM;
        return this;
    }

    /* JADX INFO: renamed from: upFile, reason: merged with bridge method [inline-methods] */
    public R m21upFile(File file) {
        this.file = file;
        this.mediaType = dw0.a(file.getName());
        return this;
    }

    /* JADX INFO: renamed from: upString, reason: merged with bridge method [inline-methods] */
    public R m27upString(String str) {
        this.content = str;
        this.mediaType = HttpParams.MEDIA_TYPE_PLAIN;
        return this;
    }

    /* JADX INFO: renamed from: params, reason: merged with bridge method [inline-methods] */
    public R m16params(String str, File file) {
        this.params.put(str, file);
        return this;
    }

    /* JADX INFO: renamed from: upJson, reason: merged with bridge method [inline-methods] */
    public R m23upJson(String str) {
        this.content = str;
        this.mediaType = HttpParams.MEDIA_TYPE_JSON;
        return this;
    }

    /* JADX INFO: renamed from: params, reason: merged with bridge method [inline-methods] */
    public R m17params(String str, File file, String str2) {
        this.params.put(str, file, str2);
        return this;
    }

    /* JADX INFO: renamed from: upBytes, reason: merged with bridge method [inline-methods] */
    public R m20upBytes(byte[] bArr, xs1 xs1Var) {
        this.bs = bArr;
        this.mediaType = xs1Var;
        return this;
    }

    /* JADX INFO: renamed from: upFile, reason: merged with bridge method [inline-methods] */
    public R m22upFile(File file, xs1 xs1Var) {
        this.file = file;
        this.mediaType = xs1Var;
        return this;
    }

    /* JADX INFO: renamed from: upString, reason: merged with bridge method [inline-methods] */
    public R m28upString(String str, xs1 xs1Var) {
        this.content = str;
        this.mediaType = xs1Var;
        return this;
    }

    /* JADX INFO: renamed from: params, reason: merged with bridge method [inline-methods] */
    public R m18params(String str, File file, String str2, xs1 xs1Var) {
        this.params.put(str, file, str2, xs1Var);
        return this;
    }

    /* JADX INFO: renamed from: upJson, reason: merged with bridge method [inline-methods] */
    public R m25upJson(JSONObject jSONObject) {
        this.content = jSONObject.toString();
        this.mediaType = HttpParams.MEDIA_TYPE_JSON;
        return this;
    }

    /* JADX INFO: renamed from: upJson, reason: merged with bridge method [inline-methods] */
    public R m24upJson(JSONArray jSONArray) {
        this.content = jSONArray.toString();
        this.mediaType = HttpParams.MEDIA_TYPE_JSON;
        return this;
    }
}
