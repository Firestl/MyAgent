package com.loopj.android.http;

import com.huawei.hms.framework.common.ContainerUtils;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import supwisdom.ao1;
import supwisdom.fo1;
import supwisdom.ho1;
import supwisdom.ru0;
import supwisdom.su0;
import supwisdom.vu0;
import supwisdom.wu0;

/* JADX INFO: loaded from: classes2.dex */
public class RequestParams implements Serializable {
    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
    public static final String LOG_TAG = "RequestParams";
    public boolean autoCloseInputStreams;
    public String contentEncoding;
    public String elapsedFieldInJsonStreamer;
    public final ConcurrentHashMap<String, List<FileWrapper>> fileArrayParams;
    public final ConcurrentHashMap<String, FileWrapper> fileParams;
    public boolean forceMultipartEntity;
    public boolean isRepeatable;
    public final ConcurrentHashMap<String, b> streamParams;
    public final ConcurrentHashMap<String, String> urlParams;
    public final ConcurrentHashMap<String, Object> urlParamsWithObjects;
    public boolean useJsonStreamer;

    public static class FileWrapper implements Serializable {
        public final String contentType;
        public final String customFileName;
        public final File file;

        public FileWrapper(File file, String str, String str2) {
            this.file = file;
            this.contentType = str;
            this.customFileName = str2;
        }
    }

    public class a extends HashMap<String, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3846a;
        public final /* synthetic */ String b;

        public a(String str, String str2) {
            this.f3846a = str;
            this.b = str2;
            put(this.f3846a, this.b);
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final InputStream f3847a;
        public final String b;
        public final String c;
        public final boolean d;

        public b(InputStream inputStream, String str, String str2, boolean z) {
            this.f3847a = inputStream;
            this.b = str;
            this.c = str2;
            this.d = z;
        }

        public static b a(InputStream inputStream, String str, String str2, boolean z) {
            if (str2 == null) {
                str2 = RequestParams.APPLICATION_OCTET_STREAM;
            }
            return new b(inputStream, str, str2, z);
        }
    }

    public RequestParams() {
        this((Map<String, String>) null);
    }

    private ao1 createFormEntity() {
        try {
            return new fo1(getParamsList(), this.contentEncoding);
        } catch (UnsupportedEncodingException e2) {
            ru0.f9092a.e(LOG_TAG, "createFormEntity failed", e2);
            return null;
        }
    }

    private ao1 createJsonStreamerEntity(vu0 vu0Var) throws IOException {
        su0 su0Var = new su0(vu0Var, (this.fileParams.isEmpty() && this.streamParams.isEmpty()) ? false : true, this.elapsedFieldInJsonStreamer);
        for (Map.Entry<String, String> entry : this.urlParams.entrySet()) {
            su0Var.a(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Object> entry2 : this.urlParamsWithObjects.entrySet()) {
            su0Var.a(entry2.getKey(), entry2.getValue());
        }
        for (Map.Entry<String, FileWrapper> entry3 : this.fileParams.entrySet()) {
            su0Var.a(entry3.getKey(), entry3.getValue());
        }
        for (Map.Entry<String, b> entry4 : this.streamParams.entrySet()) {
            b value = entry4.getValue();
            if (value.f3847a != null) {
                su0Var.a(entry4.getKey(), b.a(value.f3847a, value.b, value.c, value.d));
            }
        }
        return su0Var;
    }

    private ao1 createMultipartEntity(vu0 vu0Var) throws IOException {
        wu0 wu0Var = new wu0(vu0Var);
        wu0Var.a(this.isRepeatable);
        for (Map.Entry<String, String> entry : this.urlParams.entrySet()) {
            wu0Var.b(entry.getKey(), entry.getValue(), this.contentEncoding);
        }
        for (BasicNameValuePair basicNameValuePair : getParamsList(null, this.urlParamsWithObjects)) {
            wu0Var.b(basicNameValuePair.getName(), basicNameValuePair.getValue(), this.contentEncoding);
        }
        for (Map.Entry<String, b> entry2 : this.streamParams.entrySet()) {
            b value = entry2.getValue();
            if (value.f3847a != null) {
                wu0Var.a(entry2.getKey(), value.b, value.f3847a, value.c);
            }
        }
        for (Map.Entry<String, FileWrapper> entry3 : this.fileParams.entrySet()) {
            FileWrapper value2 = entry3.getValue();
            wu0Var.a(entry3.getKey(), value2.file, value2.contentType, value2.customFileName);
        }
        for (Map.Entry<String, List<FileWrapper>> entry4 : this.fileArrayParams.entrySet()) {
            for (FileWrapper fileWrapper : entry4.getValue()) {
                wu0Var.a(entry4.getKey(), fileWrapper.file, fileWrapper.contentType, fileWrapper.customFileName);
            }
        }
        return wu0Var;
    }

    public void add(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        Object hashSet = this.urlParamsWithObjects.get(str);
        if (hashSet == null) {
            hashSet = new HashSet();
            put(str, hashSet);
        }
        if (hashSet instanceof List) {
            ((List) hashSet).add(str2);
        } else if (hashSet instanceof Set) {
            ((Set) hashSet).add(str2);
        }
    }

    public ao1 getEntity(vu0 vu0Var) throws IOException {
        return this.useJsonStreamer ? createJsonStreamerEntity(vu0Var) : (!this.forceMultipartEntity && this.streamParams.isEmpty() && this.fileParams.isEmpty() && this.fileArrayParams.isEmpty()) ? createFormEntity() : createMultipartEntity(vu0Var);
    }

    public String getParamString() {
        return ho1.a(getParamsList(), this.contentEncoding);
    }

    public List<BasicNameValuePair> getParamsList() {
        LinkedList linkedList = new LinkedList();
        for (Map.Entry<String, String> entry : this.urlParams.entrySet()) {
            linkedList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        linkedList.addAll(getParamsList(null, this.urlParamsWithObjects));
        return linkedList;
    }

    public boolean has(String str) {
        return (this.urlParams.get(str) == null && this.streamParams.get(str) == null && this.fileParams.get(str) == null && this.urlParamsWithObjects.get(str) == null && this.fileArrayParams.get(str) == null) ? false : true;
    }

    public void put(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        this.urlParams.put(str, str2);
    }

    public void remove(String str) {
        this.urlParams.remove(str);
        this.streamParams.remove(str);
        this.fileParams.remove(str);
        this.urlParamsWithObjects.remove(str);
        this.fileArrayParams.remove(str);
    }

    public void setAutoCloseInputStreams(boolean z) {
        this.autoCloseInputStreams = z;
    }

    public void setContentEncoding(String str) {
        if (str != null) {
            this.contentEncoding = str;
        } else {
            ru0.f9092a.d(LOG_TAG, "setContentEncoding called with null attribute");
        }
    }

    public void setElapsedFieldInJsonStreamer(String str) {
        this.elapsedFieldInJsonStreamer = str;
    }

    public void setForceMultipartEntityContentType(boolean z) {
        this.forceMultipartEntity = z;
    }

    public void setHttpEntityIsRepeatable(boolean z) {
        this.isRepeatable = z;
    }

    public void setUseJsonStreamer(boolean z) {
        this.useJsonStreamer = z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : this.urlParams.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry.getKey());
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(entry.getValue());
        }
        for (Map.Entry<String, b> entry2 : this.streamParams.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry2.getKey());
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append("STREAM");
        }
        for (Map.Entry<String, FileWrapper> entry3 : this.fileParams.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry3.getKey());
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append("FILE");
        }
        for (Map.Entry<String, List<FileWrapper>> entry4 : this.fileArrayParams.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry4.getKey());
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append("FILES(SIZE=");
            sb.append(entry4.getValue().size());
            sb.append(")");
        }
        for (BasicNameValuePair basicNameValuePair : getParamsList(null, this.urlParamsWithObjects)) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(basicNameValuePair.getName());
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(basicNameValuePair.getValue());
        }
        return sb.toString();
    }

    public RequestParams(Map<String, String> map) {
        this.urlParams = new ConcurrentHashMap<>();
        this.streamParams = new ConcurrentHashMap<>();
        this.fileParams = new ConcurrentHashMap<>();
        this.fileArrayParams = new ConcurrentHashMap<>();
        this.urlParamsWithObjects = new ConcurrentHashMap<>();
        this.forceMultipartEntity = false;
        this.elapsedFieldInJsonStreamer = "_elapsed";
        this.contentEncoding = "UTF-8";
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    public void put(String str, File[] fileArr) throws FileNotFoundException {
        put(str, fileArr, (String) null, (String) null);
    }

    public void put(String str, File[] fileArr, String str2, String str3) throws FileNotFoundException {
        if (str != null) {
            ArrayList arrayList = new ArrayList();
            for (File file : fileArr) {
                if (file != null && file.exists()) {
                    arrayList.add(new FileWrapper(file, str2, str3));
                } else {
                    throw new FileNotFoundException();
                }
            }
            this.fileArrayParams.put(str, arrayList);
        }
    }

    private List<BasicNameValuePair> getParamsList(String str, Object obj) {
        Object obj2;
        LinkedList linkedList = new LinkedList();
        if (obj instanceof Map) {
            Map map = (Map) obj;
            ArrayList arrayList = new ArrayList(map.keySet());
            if (arrayList.size() > 0 && (arrayList.get(0) instanceof Comparable)) {
                Collections.sort(arrayList);
            }
            for (Object obj3 : arrayList) {
                if ((obj3 instanceof String) && (obj2 = map.get(obj3)) != null) {
                    linkedList.addAll(getParamsList(str == null ? (String) obj3 : String.format(Locale.US, "%s[%s]", str, obj3), obj2));
                }
            }
        } else if (obj instanceof List) {
            List list = (List) obj;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                linkedList.addAll(getParamsList(String.format(Locale.US, "%s[%d]", str, Integer.valueOf(i)), list.get(i)));
            }
        } else if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            int length = objArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                linkedList.addAll(getParamsList(String.format(Locale.US, "%s[%d]", str, Integer.valueOf(i2)), objArr[i2]));
            }
        } else if (obj instanceof Set) {
            Iterator it = ((Set) obj).iterator();
            while (it.hasNext()) {
                linkedList.addAll(getParamsList(str, it.next()));
            }
        } else {
            linkedList.add(new BasicNameValuePair(str, obj.toString()));
        }
        return linkedList;
    }

    public void put(String str, File file) throws FileNotFoundException {
        put(str, file, (String) null, (String) null);
    }

    public void put(String str, String str2, File file) throws FileNotFoundException {
        put(str, file, (String) null, str2);
    }

    public void put(String str, File file, String str2) throws FileNotFoundException {
        put(str, file, str2, (String) null);
    }

    public void put(String str, File file, String str2, String str3) throws FileNotFoundException {
        if (file == null || !file.exists()) {
            throw new FileNotFoundException();
        }
        if (str != null) {
            this.fileParams.put(str, new FileWrapper(file, str2, str3));
        }
    }

    public RequestParams(String str, String str2) {
        this(new a(str, str2));
    }

    public RequestParams(Object... objArr) {
        this.urlParams = new ConcurrentHashMap<>();
        this.streamParams = new ConcurrentHashMap<>();
        this.fileParams = new ConcurrentHashMap<>();
        this.fileArrayParams = new ConcurrentHashMap<>();
        this.urlParamsWithObjects = new ConcurrentHashMap<>();
        this.forceMultipartEntity = false;
        this.elapsedFieldInJsonStreamer = "_elapsed";
        this.contentEncoding = "UTF-8";
        int length = objArr.length;
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Supplied arguments must be even");
        }
        for (int i = 0; i < length; i += 2) {
            put(String.valueOf(objArr[i]), String.valueOf(objArr[i + 1]));
        }
    }

    public void put(String str, InputStream inputStream) {
        put(str, inputStream, (String) null);
    }

    public void put(String str, InputStream inputStream, String str2) {
        put(str, inputStream, str2, (String) null);
    }

    public void put(String str, InputStream inputStream, String str2, String str3) {
        put(str, inputStream, str2, str3, this.autoCloseInputStreams);
    }

    public void put(String str, InputStream inputStream, String str2, String str3, boolean z) {
        if (str == null || inputStream == null) {
            return;
        }
        this.streamParams.put(str, b.a(inputStream, str2, str3, z));
    }

    public void put(String str, Object obj) {
        if (str == null || obj == null) {
            return;
        }
        this.urlParamsWithObjects.put(str, obj);
    }

    public void put(String str, int i) {
        if (str != null) {
            this.urlParams.put(str, String.valueOf(i));
        }
    }

    public void put(String str, long j) {
        if (str != null) {
            this.urlParams.put(str, String.valueOf(j));
        }
    }
}
