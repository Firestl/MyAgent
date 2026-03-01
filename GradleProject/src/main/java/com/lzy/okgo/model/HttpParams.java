package com.lzy.okgo.model;

import com.huawei.hms.framework.common.ContainerUtils;
import com.loopj.android.http.RequestParams;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.util.net.NetWork;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import supwisdom.dw0;
import supwisdom.xs1;

/* JADX INFO: loaded from: classes2.dex */
public class HttpParams implements Serializable {
    public static final boolean IS_REPLACE = true;
    public static final long serialVersionUID = 7369819159227055048L;
    public LinkedHashMap<String, List<FileWrapper>> fileParamsMap;
    public LinkedHashMap<String, List<String>> urlParamsMap;
    public static final xs1 MEDIA_TYPE_PLAIN = xs1.b(NetWork.CONTENT_TYPE_COMMON);
    public static final xs1 MEDIA_TYPE_JSON = xs1.b("application/json;charset=utf-8");
    public static final xs1 MEDIA_TYPE_STREAM = xs1.b(RequestParams.APPLICATION_OCTET_STREAM);

    public static class FileWrapper implements Serializable {
        public static final long serialVersionUID = -2356139899636767776L;
        public transient xs1 contentType;
        public File file;
        public String fileName;
        public long fileSize;

        public FileWrapper(File file, String str, xs1 xs1Var) {
            this.file = file;
            this.fileName = str;
            this.contentType = xs1Var;
            this.fileSize = file.length();
        }

        private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            this.contentType = xs1.b((String) objectInputStream.readObject());
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.contentType.toString());
        }

        public String toString() {
            return "FileWrapper{file=" + this.file + ", fileName=" + this.fileName + ", contentType=" + this.contentType + ", fileSize=" + this.fileSize + Operators.BLOCK_END_STR;
        }
    }

    public HttpParams() {
        init();
    }

    private void init() {
        this.urlParamsMap = new LinkedHashMap<>();
        this.fileParamsMap = new LinkedHashMap<>();
    }

    public void clear() {
        this.urlParamsMap.clear();
        this.fileParamsMap.clear();
    }

    public void put(HttpParams httpParams) {
        if (httpParams != null) {
            LinkedHashMap<String, List<String>> linkedHashMap = httpParams.urlParamsMap;
            if (linkedHashMap != null && !linkedHashMap.isEmpty()) {
                this.urlParamsMap.putAll(httpParams.urlParamsMap);
            }
            LinkedHashMap<String, List<FileWrapper>> linkedHashMap2 = httpParams.fileParamsMap;
            if (linkedHashMap2 == null || linkedHashMap2.isEmpty()) {
                return;
            }
            this.fileParamsMap.putAll(httpParams.fileParamsMap);
        }
    }

    public void putFileParams(String str, List<File> list) {
        if (str == null || list == null || list.isEmpty()) {
            return;
        }
        Iterator<File> it = list.iterator();
        while (it.hasNext()) {
            put(str, it.next());
        }
    }

    public void putFileWrapperParams(String str, List<FileWrapper> list) {
        if (str == null || list == null || list.isEmpty()) {
            return;
        }
        Iterator<FileWrapper> it = list.iterator();
        while (it.hasNext()) {
            put(str, it.next());
        }
    }

    public void putUrlParams(String str, List<String> list) {
        if (str == null || list == null || list.isEmpty()) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            put(str, it.next(), false);
        }
    }

    public void remove(String str) {
        removeUrl(str);
        removeFile(str);
    }

    public void removeFile(String str) {
        this.fileParamsMap.remove(str);
    }

    public void removeUrl(String str) {
        this.urlParamsMap.remove(str);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : this.urlParamsMap.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry.getKey());
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(entry.getValue());
        }
        for (Map.Entry<String, List<FileWrapper>> entry2 : this.fileParamsMap.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry2.getKey());
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(entry2.getValue());
        }
        return sb.toString();
    }

    public HttpParams(String str, String str2) {
        init();
        put(str, str2, true);
    }

    public void put(Map<String, String> map, boolean... zArr) {
        if (map == null || map.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue(), zArr);
        }
    }

    public HttpParams(String str, File file) {
        init();
        put(str, file);
    }

    public void put(String str, String str2, boolean... zArr) {
        if (zArr != null && zArr.length > 0) {
            put(str, str2, zArr[0]);
        } else {
            put(str, str2, true);
        }
    }

    public void put(String str, int i, boolean... zArr) {
        if (zArr != null && zArr.length > 0) {
            put(str, String.valueOf(i), zArr[0]);
        } else {
            put(str, String.valueOf(i), true);
        }
    }

    public void put(String str, long j, boolean... zArr) {
        if (zArr != null && zArr.length > 0) {
            put(str, String.valueOf(j), zArr[0]);
        } else {
            put(str, String.valueOf(j), true);
        }
    }

    public void put(String str, float f, boolean... zArr) {
        if (zArr != null && zArr.length > 0) {
            put(str, String.valueOf(f), zArr[0]);
        } else {
            put(str, String.valueOf(f), true);
        }
    }

    public void put(String str, double d, boolean... zArr) {
        if (zArr != null && zArr.length > 0) {
            put(str, String.valueOf(d), zArr[0]);
        } else {
            put(str, String.valueOf(d), true);
        }
    }

    public void put(String str, char c, boolean... zArr) {
        if (zArr != null && zArr.length > 0) {
            put(str, String.valueOf(c), zArr[0]);
        } else {
            put(str, String.valueOf(c), true);
        }
    }

    public void put(String str, boolean z, boolean... zArr) {
        if (zArr != null && zArr.length > 0) {
            put(str, String.valueOf(z), zArr[0]);
        } else {
            put(str, String.valueOf(z), true);
        }
    }

    private void put(String str, String str2, boolean z) {
        if (str == null || str2 == null) {
            return;
        }
        List<String> arrayList = this.urlParamsMap.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.urlParamsMap.put(str, arrayList);
        }
        if (z) {
            arrayList.clear();
        }
        arrayList.add(str2);
    }

    public void put(String str, File file) {
        put(str, file, file.getName());
    }

    public void put(String str, File file, String str2) {
        put(str, file, str2, dw0.a(str2));
    }

    public void put(String str, FileWrapper fileWrapper) {
        if (str == null || fileWrapper == null) {
            return;
        }
        put(str, fileWrapper.file, fileWrapper.fileName, fileWrapper.contentType);
    }

    public void put(String str, File file, String str2, xs1 xs1Var) {
        if (str != null) {
            List<FileWrapper> arrayList = this.fileParamsMap.get(str);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.fileParamsMap.put(str, arrayList);
            }
            arrayList.add(new FileWrapper(file, str2, xs1Var));
        }
    }
}
