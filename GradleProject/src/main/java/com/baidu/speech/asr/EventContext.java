package com.baidu.speech.asr;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.list.template.CellDataManager;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class EventContext extends ContextWrapper {
    public static final String TAG = "EventContext";
    public static final Logger logger = Logger.getLogger(TAG);

    public static class SmartLogger {
        public static final String TAG = "baidu_speech";
        public static final Logger logger;

        static {
            Logger logger2 = Logger.getLogger(TAG);
            logger = logger2;
            logger2.setLevel(Level.OFF);
        }

        public static <T> T wrap(Object obj, String[] strArr) {
            return (T) wrap(TAG, obj, strArr);
        }

        public static <T> T wrap(String str, final Object obj, final String... strArr) {
            final boolean zIsLoggable = Log.isLoggable(TAG, 3);
            if (Log.isLoggable(TAG, 3)) {
                logger.setLevel(Level.ALL);
            }
            ArrayList arrayList = new ArrayList();
            Class<?> superclass = obj.getClass();
            do {
                arrayList.addAll(Arrays.asList(superclass.getInterfaces()));
                superclass = superclass.getSuperclass();
            } while (superclass != Object.class);
            return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(), (Class[]) arrayList.toArray(new Class[0]), new InvocationHandler() { // from class: com.baidu.speech.asr.EventContext.SmartLogger.1
                @Override // java.lang.reflect.InvocationHandler
                public Object invoke(Object obj2, Method method, Object[] objArr) throws Throwable {
                    Object objInvoke = method.invoke(obj, objArr);
                    StringBuffer stringBuffer = new StringBuffer();
                    String[] strArr2 = strArr;
                    stringBuffer.append(((strArr2 == null || strArr2.length <= 0) ? obj.getClass().getName() : strArr2[0]) + CellDataManager.VIRTUAL_COMPONENT_SEPRATOR + Integer.toHexString(obj.hashCode()));
                    stringBuffer.append(Operators.DOT_STR + method.getName() + "(");
                    if (objArr != null) {
                        for (Object obj3 : objArr) {
                            String strSubstring = obj3 + "";
                            if (!zIsLoggable) {
                                String strReplaceAll = strSubstring.replaceAll("[\r\n]]", "");
                                strSubstring = strReplaceAll.substring(0, Math.min(50, strReplaceAll.length()));
                            }
                            stringBuffer.append(strSubstring + ", ");
                        }
                    }
                    stringBuffer.append(") : " + objInvoke);
                    SmartLogger.logger.info(stringBuffer.toString());
                    return objInvoke;
                }
            });
        }
    }

    public EventContext(Context context) {
        super(context);
    }

    public static short[] byteToShortArray(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return new short[0];
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i2);
        byteBufferAllocate.order(ByteOrder.nativeOrder());
        byteBufferAllocate.put(bArr, i, i2);
        byteBufferAllocate.clear();
        short[] sArr = new short[i2 / 2];
        byteBufferAllocate.asShortBuffer().get(sArr);
        return sArr;
    }

    public static long computePower(short[] sArr, int i) {
        long j = 0;
        if (sArr == null) {
            return 0L;
        }
        System.currentTimeMillis();
        int iMin = Math.min(i / 2, 512);
        if (iMin <= 0) {
            return 0L;
        }
        for (int i2 = 0; i2 < iMin; i2++) {
            int i3 = i2 * 2;
            j += (long) (sArr[i3] * sArr[i3]);
        }
        return (long) Math.sqrt(j / ((long) iMin));
    }

    public long computePower(byte[] bArr, int i) {
        int i2 = i / 2;
        short[] sArr = new short[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 * 2;
            sArr[i3] = (short) ((bArr[i4 + 0] & 255) | (bArr[i4 + 1] << 8));
        }
        return computePower(sArr, i2);
    }

    public SharedPreferences getSdkSharedPreferences() {
        return super.getSharedPreferences("bds", 0);
    }

    public String httpRequest(String str, Map<String, String> map, byte[] bArr, boolean z) throws Exception {
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                if (Log.isLoggable(TAG, 3) || logger.isLoggable(Level.ALL)) {
                    logger.info("cur time: " + (System.currentTimeMillis() % 1000000) + ", http req: " + str);
                }
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection2.setConnectTimeout(8000);
                    httpURLConnection2.setReadTimeout(8000);
                    httpURLConnection2.setInstanceFollowRedirects(false);
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        httpURLConnection2.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                    if (bArr != null || z) {
                        httpURLConnection2.setRequestMethod("POST");
                    }
                    httpURLConnection2.connect();
                    if (bArr != null) {
                        httpURLConnection2.getOutputStream().write(bArr);
                    }
                    String next = new Scanner(httpURLConnection2.getInputStream()).useDelimiter("\\A").next();
                    if (Log.isLoggable(TAG, 3) || logger.isLoggable(Level.ALL)) {
                        logger.info("http res: " + next);
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return next;
                } catch (Exception e2) {
                    e = e2;
                    if (Log.isLoggable(TAG, 3) || logger.isLoggable(Level.ALL)) {
                        logger.log(Level.WARNING, "", (Throwable) e);
                    }
                    throw e;
                } catch (Throwable th) {
                    httpURLConnection = httpURLConnection2;
                    th = th;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public String join(List<String> list, String str) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str2 : list) {
            if (z) {
                z = false;
            } else {
                sb.append(str);
            }
            sb.append(str2);
        }
        return sb.toString();
    }

    public byte[] loadBytesFromUri(String str) throws IOException {
        InputStream resourceAsStream;
        InputStream fileInputStream = null;
        try {
            if (!str.contains("://")) {
                str = "file://" + str;
            }
            Matcher matcher = Pattern.compile("(.*?)://(.*)").matcher(str);
            if (matcher.find()) {
                String strGroup = matcher.group(1);
                String strGroup2 = matcher.group(2);
                if (strGroup.equalsIgnoreCase("file")) {
                    fileInputStream = new FileInputStream(strGroup2);
                } else {
                    if (strGroup.equalsIgnoreCase(UriUtil.LOCAL_ASSET_SCHEME) || strGroup.equalsIgnoreCase("assets")) {
                        String str2 = strGroup2.startsWith("/") ? "" : "/";
                        resourceAsStream = getClass().getResourceAsStream("/assets" + str2 + strGroup2);
                    } else if (strGroup.equalsIgnoreCase(UriUtil.LOCAL_RESOURCE_SCHEME)) {
                        resourceAsStream = getClass().getResourceAsStream(strGroup2);
                    }
                    fileInputStream = resourceAsStream;
                }
            }
            if (fileInputStream == null) {
                throw new IOException("bad data source");
            }
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int i = fileInputStream.read(bArr, 0, 1024);
                if (-1 == i) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
            return byteArrayOutputStream.toByteArray();
        } finally {
            if (0 != 0) {
                fileInputStream.close();
            }
        }
    }

    public JSONObject loadJsonFromUri(String str) {
        return loadJsonFromUri(str, false, false);
    }

    public JSONObject loadJsonFromUri(String str, boolean z, boolean z2) {
        try {
            return loadJsonFromUriOrThrow(str, z, z2);
        } catch (Exception unused) {
            return null;
        }
    }

    public JSONObject loadJsonFromUriOrThrow(String str) throws JSONException, IOException {
        return loadJsonFromUriOrThrow(str, false, false);
    }

    public JSONObject loadJsonFromUriOrThrow(String str, boolean z, boolean z2) throws JSONException, IOException {
        String strLoadStringFromUri = loadStringFromUri(str, z);
        if (z2) {
            strLoadStringFromUri = URLDecoder.decode(strLoadStringFromUri, "UTF-8");
        }
        return new JSONObject(strLoadStringFromUri);
    }

    public String loadStringFromUri(String str) throws IOException {
        return loadStringFromUri(str, false);
    }

    public String loadStringFromUri(String str, boolean z) throws IOException {
        byte[] bArrLoadBytesFromUri = loadBytesFromUri(str);
        return z ? new String(Base64.decode(bArrLoadBytesFromUri, 0), "UTF-8") : new String(bArrLoadBytesFromUri, "UTF-8");
    }

    public <T> T loggerIt(Object obj, String... strArr) {
        return (T) SmartLogger.wrap(obj, strArr);
    }

    public Object searchItemFromJson(JSONObject jSONObject, String str) throws JSONException {
        Object objSearchItemFromJson;
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return null;
        }
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (next.equals(str)) {
                return jSONObject.get(str);
            }
            Object obj = jSONObject.get(next);
            if ((obj instanceof JSONObject) && (objSearchItemFromJson = searchItemFromJson((JSONObject) obj, str)) != null) {
                return objSearchItemFromJson;
            }
        }
        return null;
    }
}
