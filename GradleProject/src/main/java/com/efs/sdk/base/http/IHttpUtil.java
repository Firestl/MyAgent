package com.efs.sdk.base.http;

import java.io.File;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface IHttpUtil {
    HttpResponse get(String str, Map<String, String> map);

    HttpResponse post(String str, Map<String, String> map, File file);

    HttpResponse post(String str, Map<String, String> map, byte[] bArr);

    HttpResponse postAsFile(String str, Map<String, String> map, byte[] bArr);
}
