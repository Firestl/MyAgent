package com.baidu.speech.utils.analysis;

import android.content.Context;
import android.util.Log;
import com.baidu.speech.asr.SpeechConstant;
import com.baidu.speech.utils.Policy;
import com.taobao.weex.wson.Wson;
import io.dcloud.common.util.Base64;
import io.dcloud.common.util.net.NetWork;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class Analysis {
    public static final boolean DEBUG = false;
    public static final String KEY_APP_ID = "appid";
    public static final String KEY_APP_NAME = "app_name";
    public static final String KEY_APP_SIGNATURE = "app_signature";
    public static final String KEY_CUID = "wise_cuid";
    public static final String KEY_ERROR = "error";
    public static final String KEY_NET_TYPE = "net_type";
    public static final String KEY_OS = "os";
    public static final String KEY_PID = "pid";
    public static final String KEY_PKG = "pkg";
    public static final String KEY_PLATFORM = "platform";
    public static final String KEY_RECOGNITION_RESULT = "recog_results";
    public static final String KEY_RECOGNITION_RESULT_CMD_ID = "cmd_id";
    public static final String KEY_RECOGNITION_RESULT_CMD_TYPE = "cmd_type";
    public static final String KEY_RECOGNITION_RESULT_ERROR_CODE = "error_code";
    public static final String KEY_RECOGNITION_RESULT_TIME = "time";
    public static final String KEY_RECOGNITION_RESULT_VOICE_TO_TEXT = "voice_to_text_result";
    public static final String KEY_RESPONSE_ERROR_CODE = "errno";
    public static final String KEY_RESPONSE_UPLOAD_DATA = "data";
    public static final String KEY_RESPONSE_UPLOAD_PERIOD = "secs";
    public static final String KEY_SCREEN = "screen";
    public static final String KEY_SDK_NAME = "sdk_name";
    public static final String KEY_SDK_VERSION = "sdk_version";
    public static final String KEY_TYPE = "type";
    public static final String TAG = "Analysis";

    public static class Item {
        public static final String TYPE_ASR_LONGSPEECH = "asr_longspeech";
        public static final String TYPE_NORMAL = "asr_normal";
        public static final String TYPE_TTS = "tts";
        public static final String TYPE_WAKEUP = "wakeup";
        public int appId = 0;
        public int decoder;
        public int error_code;
        public int pid;
        public String pkg;
        public long time;
        public String type;

        public String toString() {
            HashMap map = new HashMap();
            map.put("time", Long.valueOf(this.time));
            map.put(Analysis.KEY_RECOGNITION_RESULT_ERROR_CODE, Integer.valueOf(this.error_code));
            map.put(Analysis.KEY_RECOGNITION_RESULT_CMD_TYPE, 0);
            map.put(Analysis.KEY_RECOGNITION_RESULT_CMD_ID, 0);
            map.put(Analysis.KEY_RECOGNITION_RESULT_VOICE_TO_TEXT, "");
            map.put("pid", Integer.valueOf(this.pid));
            map.put(SpeechConstant.DECODER, Integer.valueOf(this.decoder));
            map.put("type", this.type);
            map.put("pkg", this.pkg);
            map.put("app_id", Integer.valueOf(this.appId));
            return new JSONObject(map).toString();
        }
    }

    public static void asyncUploadAll(final Context context) {
        new Thread(new Runnable() { // from class: com.baidu.speech.utils.analysis.Analysis.1
            @Override // java.lang.Runnable
            public void run() {
                Analysis.syncUploadAll(context);
            }
        }).start();
    }

    public static String buildStatUrl(Context context, String str) throws UnsupportedEncodingException {
        LinkedList linkedList = new LinkedList();
        linkedList.add("wise_cuid=" + URLEncoder.encode(Policy.uid(context), "utf-8"));
        linkedList.add("sdk_version=" + URLEncoder.encode(Utility.getSdkVersion(), "utf-8"));
        linkedList.add("app_name=" + URLEncoder.encode(Utility.getAppName(context), "utf-8"));
        linkedList.add("platform=" + URLEncoder.encode(Utility.getPlatform(context), "utf-8"));
        linkedList.add("os=" + URLEncoder.encode(Utility.getOS(), "utf-8"));
        StringBuilder sb = new StringBuilder();
        sb.append("net_type=");
        sb.append(URLEncoder.encode(Utility.getNetType(context) + "", "utf-8"));
        linkedList.add(sb.toString());
        linkedList.add("appid=" + URLEncoder.encode(str, "utf-8"));
        linkedList.add("screen=" + URLEncoder.encode(Utility.getScreen(context), "utf-8"));
        linkedList.add("sdk_name=" + URLEncoder.encode(Utility.getSdkName(), "utf-8"));
        linkedList.add("app_signature=" + URLEncoder.encode(Utility.getSignatureMD5(context), "utf-8"));
        return "https://yuyin.baidu.com/voice?osname=voiceopen&action=usereventflow&" + join(linkedList, "&");
    }

    public static String httpRequest(String str, Map<String, String> map, byte[] bArr, boolean z) throws Exception {
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                if (Log.isLoggable("Analysis", 3)) {
                    Log.i("Analysis", "cur time: " + (System.currentTimeMillis() % 1000000) + ", http req: " + str);
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
                    if (Log.isLoggable("Analysis", 3)) {
                        Log.i("Analysis", "http res: " + next);
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return next;
                } catch (Exception e2) {
                    e = e2;
                    httpURLConnection = httpURLConnection2;
                    if (Log.isLoggable("Analysis", 3)) {
                        Log.w("Analysis", "", e);
                    }
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    httpURLConnection = httpURLConnection2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static String join(List<String> list, String str) {
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

    public static void log(Context context, Item item) {
        try {
            File file = new File(context.getFilesDir(), "open-analysis");
            file.mkdirs();
            if (file.exists()) {
                if (item.appId == 0) {
                    if (Log.isLoggable("Analysis", 3)) {
                        Log.i("Analysis", "appid is 0, ignore " + item);
                        return;
                    }
                    return;
                }
                File file2 = new File(file, System.currentTimeMillis() + ".txt");
                FileWriter fileWriter = new FileWriter(file2);
                String string = item.toString();
                if (Log.isLoggable("Analysis", 3)) {
                    Log.i("Analysis", "write to " + file2 + ", \t content: " + string);
                }
                fileWriter.write(item.toString());
                fileWriter.write(Base64.CRLF);
                fileWriter.close();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static synchronized void syncUploadAll(Context context) {
        File[] fileArrListFiles;
        try {
            File file = new File(context.getFilesDir(), "open-analysis");
            file.mkdirs();
            if (file.exists() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                for (int i = 0; i < fileArrListFiles.length && i <= 10; i++) {
                    File file2 = fileArrListFiles[i];
                    Scanner scannerUseDelimiter = null;
                    try {
                        try {
                            scannerUseDelimiter = new Scanner(new FileInputStream(file2)).useDelimiter("\\A");
                            JSONObject jSONObject = new JSONObject(scannerUseDelimiter.nextLine());
                            String str = "" + jSONObject.getInt("app_id");
                            byte[] bArrEncryptGZIP = Utility.encryptGZIP(new JSONObject().put(KEY_RECOGNITION_RESULT, new JSONArray().put(jSONObject)).toString());
                            if (bArrEncryptGZIP.length >= 2) {
                                bArrEncryptGZIP[0] = 117;
                                bArrEncryptGZIP[1] = Wson.MAP_TYPE;
                            }
                            String str2 = "records=" + URLEncoder.encode(Utility.encryptBASE64(bArrEncryptGZIP), "utf-8");
                            byte[] bytes = str2.getBytes("utf-8");
                            String strBuildStatUrl = buildStatUrl(context, str);
                            HashMap map = new HashMap();
                            map.put("Content-Type", NetWork.CONTENT_TYPE_UPLOAD);
                            String strHttpRequest = httpRequest(strBuildStatUrl, map, bytes, true);
                            file2.delete();
                            if (Log.isLoggable("Analysis", 3)) {
                                Log.i("Analysis", "POST " + strBuildStatUrl + "\n\t\trequest data:" + str2 + "\n\t\tresponse data:\n" + strHttpRequest);
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            if (Log.isLoggable("Analysis", 3)) {
                                Log.i("Analysis", "", e2);
                            }
                            if (scannerUseDelimiter != null) {
                            }
                        }
                        if (scannerUseDelimiter != null) {
                            scannerUseDelimiter.close();
                        }
                    } catch (Throwable th) {
                        if (scannerUseDelimiter != null) {
                            scannerUseDelimiter.close();
                        }
                        throw th;
                    }
                }
            }
        } catch (NullPointerException e3) {
            e3.printStackTrace();
        }
    }
}
