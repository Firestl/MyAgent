package com.baidu.aip.core.mini;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import com.baidu.speech.asr.SpeechConstant;
import com.bumptech.glide.manager.DefaultConnectivityMonitorFactory;
import com.facebook.common.util.UriUtil;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.util.StringUtil;
import io.dcloud.feature.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;
import org.json.JSONObject;
import supwisdom.y7;

/* JADX INFO: loaded from: classes.dex */
public class AutoCheck {
    public static final String TAG = "AutoCheck";
    public static final boolean isOnlineLited = false;
    public Context context;
    public boolean enableOffline;
    public Handler handler;
    public boolean hasError;
    public String name;
    public boolean isFinished = false;
    public LinkedHashMap<String, Check> checks = new LinkedHashMap<>();

    public static class AppInfoCheck extends Check {
        public String appId;
        public String appKey;
        public String secretKey;

        public AppInfoCheck(Context context, Map<String, Object> map) throws PackageManager.NameNotFoundException {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (map.get("appid") != null) {
                this.appId = map.get("appid").toString();
            } else {
                int i = bundle.getInt("com.baidu.speech.APP_ID", 0);
                if (i > 0) {
                    this.appId = "" + i;
                }
            }
            if (map.get("key") != null) {
                this.appKey = map.get("key").toString();
            } else {
                this.appKey = bundle.getString("com.baidu.speech.API_KEY", "");
            }
            if (map.get("secret") != null) {
                this.secretKey = map.get("secret").toString();
            } else {
                this.secretKey = bundle.getString("com.baidu.speech.SECRET_KEY", "");
            }
        }

        @Override // com.baidu.aip.core.mini.AutoCheck.Check
        public void check() {
            appendLogMessage("try to check appId " + this.appId + " ,appKey=" + this.appKey + " ,secretKey" + this.secretKey);
            String str = this.appId;
            if (str == null || str.isEmpty()) {
                this.errorMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_appid_empty);
                this.fixMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_appid_fill);
                return;
            }
            String str2 = this.appKey;
            if (str2 == null || str2.isEmpty()) {
                this.errorMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_appkey_empty);
                this.fixMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_appkey_fill);
                return;
            }
            String str3 = this.secretKey;
            if (str3 == null || str3.isEmpty()) {
                this.errorMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_secretkey_empty);
                this.fixMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_secretkey_fill);
                return;
            }
            try {
                checkOnline();
            } catch (UnknownHostException e2) {
                this.infoMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_error_no_network) + e2.getMessage();
            } catch (Exception e3) {
                this.errorMessage = e3.getClass().getCanonicalName() + Constants.COLON_SEPARATOR + e3.getMessage();
                this.fixMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_fixmessage_check_appid_appkey_again);
            }
        }

        public void checkOnline() throws Exception {
            String line;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials&client_id=" + this.appKey + "&client_secret=" + this.secretKey).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1000);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            do {
                line = bufferedReader.readLine();
                if (line != null) {
                    sb.append(line);
                }
            } while (line != null);
            String string = sb.toString();
            if (!string.contains("audio_voice_assistant_get")) {
                this.errorMessage = "appid:" + this.appId + DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_error_no_audio_voice_assistant_get);
                this.fixMessage = "secretKey";
                return;
            }
            appendLogMessage("openapi return " + string);
            JSONObject jSONObject = new JSONObject(string);
            String strOptString = jSONObject.optString("error");
            if (strOptString != null && !strOptString.isEmpty()) {
                this.errorMessage = StringUtil.format(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_error_appkey_message_format), strOptString, sb);
                this.fixMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_fixmessage_check_appid_appkey_again);
                return;
            }
            String string2 = jSONObject.getString("access_token");
            if (string2 != null) {
                if (string2.endsWith("-" + this.appId)) {
                    return;
                }
            }
            this.errorMessage = StringUtil.format(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_error_appkey_message_format), this.appId, string2);
            this.fixMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_fixmessage_check_appid_appkey_again);
        }
    }

    public static class ApplicationIdCheck extends Check {
        public String appId;
        public Context context;

        public ApplicationIdCheck(Context context, String str) {
            this.appId = str;
            this.context = context;
        }

        private String getApplicationId() {
            return this.context.getPackageName();
        }

        @Override // com.baidu.aip.core.mini.AutoCheck.Check
        public void check() {
            this.infoMessage = StringUtil.format(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_sdk_fix_message_step), this.appId) + getApplicationId();
        }
    }

    public static abstract class Check {
        public String errorMessage = null;
        public String fixMessage = null;
        public String infoMessage = null;
        public StringBuilder logMessage = new StringBuilder();

        public void appendLogMessage(String str) {
            this.logMessage.append(str + "\n");
        }

        public abstract void check();

        public String getErrorMessage() {
            return this.errorMessage;
        }

        public String getFixMessage() {
            return this.fixMessage;
        }

        public String getInfoMessage() {
            return this.infoMessage;
        }

        public String getLogMessage() {
            return this.logMessage.toString();
        }

        public boolean hasError() {
            return this.errorMessage != null;
        }

        public boolean hasFix() {
            return this.fixMessage != null;
        }

        public boolean hasInfo() {
            return this.infoMessage != null;
        }

        public boolean hasLog() {
            return !this.logMessage.toString().isEmpty();
        }
    }

    public static class FileCheck extends Check {
        public boolean allowAssets;
        public boolean allowRes;
        public Context context;
        public String key;
        public Map<String, Object> params;

        public FileCheck(Context context, Map<String, Object> map, String str) {
            this.allowRes = false;
            this.allowAssets = true;
            this.context = context;
            this.params = map;
            this.key = str;
            if (str.equals(SpeechConstant.IN_FILE)) {
                this.allowRes = true;
                this.allowAssets = false;
            }
        }

        @Override // com.baidu.aip.core.mini.AutoCheck.Check
        public void check() {
            if (this.params.containsKey(this.key)) {
                String string = this.params.get(this.key).toString();
                if (this.allowAssets && string.startsWith("assets")) {
                    String strSubstring = string.substring(10);
                    if (!":///".equals(string.substring(6, 10)) || strSubstring.isEmpty()) {
                        this.errorMessage = StringUtil.format(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_msg_param_error_format), this.key, string);
                        this.fixMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_msg_param_error_format_fix);
                    }
                    try {
                        this.context.getAssets().open(strSubstring);
                    } catch (IOException e2) {
                        this.errorMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_msg_asset_file_loss) + strSubstring;
                        this.fixMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_msg_asset_file_loss_fix);
                        e2.printStackTrace();
                    }
                    appendLogMessage(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_msg_asset_check_done) + strSubstring);
                }
                if (this.allowRes && string.startsWith(UriUtil.LOCAL_RESOURCE_SCHEME)) {
                    String strSubstring2 = string.substring(7);
                    if (!":///".equals(string.substring(3, 7)) || strSubstring2.isEmpty()) {
                        this.errorMessage = StringUtil.format(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_msg_param_error_format), this.key, string);
                        this.fixMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_msg_param_error_format_fix_two);
                    }
                    if (FileCheck.class.getClassLoader().getResourceAsStream(strSubstring2) == null) {
                        this.errorMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_msg_res_file_loss) + strSubstring2;
                        this.fixMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_msg_res_file_loss_fix);
                    }
                    appendLogMessage(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_msg_res_check_done) + strSubstring2);
                }
                if (string.startsWith("/")) {
                    if (!new File(string).canRead()) {
                        this.errorMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_msg_file_path_not_exist) + string;
                        this.fixMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_msg_file_path_not_exist_fix);
                    }
                    appendLogMessage(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_msg_file_path_check_done) + string);
                }
            }
        }
    }

    public static class JniCheck extends Check {
        public Context context;
        public String[] soNames = {"libBaiduSpeechSDK.so", "libvad.dnn.so", "libbd_easr_s1_merge_normal_20151216.dat.so", "libbdEASRAndroid.so", "libbdSpilWakeup.so"};

        public JniCheck(Context context) {
            this.context = context;
        }

        @Override // com.baidu.aip.core.mini.AutoCheck.Check
        public void check() {
            String str = this.context.getApplicationInfo().nativeLibraryDir;
            appendLogMessage(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_jni_dir) + str);
            File[] fileArrListFiles = new File(str).listFiles();
            TreeSet treeSet = new TreeSet();
            if (fileArrListFiles != null) {
                for (File file : fileArrListFiles) {
                    treeSet.add(file.getName());
                }
            }
            for (String str2 : this.soNames) {
                if (!treeSet.contains(str2)) {
                    this.errorMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_jni_error_msg_one) + str + DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_jni_error_msg_two) + str2 + DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_jni_error_msg_three) + treeSet.toString();
                    this.fixMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_jni_fix_message);
                    return;
                }
            }
        }
    }

    public static class PermissionCheck extends Check {
        public Context context;

        public PermissionCheck(Context context) {
            this.context = context;
        }

        @Override // com.baidu.aip.core.mini.AutoCheck.Check
        public void check() {
            String[] strArr = {"android.permission.RECORD_AUDIO", DefaultConnectivityMonitorFactory.NETWORK_PERMISSION, "android.permission.INTERNET", "android.permission.READ_PHONE_STATE"};
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 4; i++) {
                String str = strArr[i];
                if (y7.a(this.context, str) != 0) {
                    arrayList.add(str);
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            this.errorMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_loss_permission) + arrayList;
            this.fixMessage = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_copy_permission_from_manifest);
        }
    }

    public static class PrintConfig {
        public boolean withEachCheckInfo;
        public boolean withInfo;
        public boolean withLog;
        public boolean withLogOnSuccess;

        public PrintConfig() {
            this.withEachCheckInfo = false;
            this.withInfo = false;
            this.withLog = false;
            this.withLogOnSuccess = false;
        }
    }

    public AutoCheck(Context context, Handler handler, boolean z) {
        this.context = context;
        this.handler = handler;
        this.enableOffline = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AutoCheck checkAsrInternal(Map<String, Object> map) {
        commonSetting(map);
        this.checks.put(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_vertify_out_audio), new FileCheck(this.context, map, SpeechConstant.IN_FILE));
        this.checks.put(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_vertify_offline_bsg), new FileCheck(this.context, map, SpeechConstant.ASR_OFFLINE_ENGINE_GRAMMER_FILE_PATH));
        Iterator<Map.Entry<String, Check>> it = this.checks.entrySet().iterator();
        while (it.hasNext()) {
            Check value = it.next().getValue();
            value.check();
            if (value.hasError()) {
                break;
            }
        }
        return this;
    }

    private void commonSetting(Map<String, Object> map) {
        this.checks.put(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_check_android_permission), new PermissionCheck(this.context));
        this.checks.put(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_check_android_so), new JniCheck(this.context));
        try {
            AppInfoCheck appInfoCheck = new AppInfoCheck(this.context, map);
            this.checks.put(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_check_appid_appkey), appInfoCheck);
            if (this.enableOffline) {
                this.checks.put(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_check_package_name), new ApplicationIdCheck(this.context, appInfoCheck.appId));
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    private String formatString(PrintConfig printConfig) {
        StringBuilder sb = new StringBuilder();
        this.hasError = false;
        for (Map.Entry<String, Check> entry : this.checks.entrySet()) {
            Check value = entry.getValue();
            String key = entry.getKey();
            if (value.hasError()) {
                if (!this.hasError) {
                    this.hasError = true;
                }
                sb.append(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_sb_text_error));
                sb.append(key);
                sb.append(" 】  ");
                sb.append(value.getErrorMessage());
                sb.append("\n");
                if (value.hasFix()) {
                    sb.append(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_sb_text_fix_method));
                    sb.append(key);
                    sb.append(" 】  ");
                    sb.append(value.getFixMessage());
                    sb.append("\n");
                }
            } else if (printConfig.withEachCheckInfo) {
                sb.append(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_sb_text_no_error_report));
                sb.append(key);
                sb.append(" 】  ");
                sb.append("\n");
            }
            if (printConfig.withInfo && value.hasInfo()) {
                sb.append(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_sb_text_check_again));
                sb.append(key);
                sb.append("】 ");
                sb.append(value.getInfoMessage());
                sb.append("\n");
            }
            if (printConfig.withLog && (printConfig.withLogOnSuccess || this.hasError)) {
                if (value.hasLog()) {
                    sb.append("【log】:" + value.getLogMessage());
                    sb.append("\n");
                }
            }
        }
        if (!this.hasError) {
            sb.append("【" + this.name + DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_sb_text_success_no_error));
        }
        return sb.toString();
    }

    public void checkAsr(final Map<String, Object> map) {
        new Thread(new Runnable() { // from class: com.baidu.aip.core.mini.AutoCheck.1
            @Override // java.lang.Runnable
            public void run() {
                AutoCheck autoCheckCheckAsrInternal = AutoCheck.this.checkAsrInternal(map);
                AutoCheck.this.name = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_text_recognition);
                synchronized (autoCheckCheckAsrInternal) {
                    AutoCheck.this.isFinished = true;
                    AutoCheck.this.handler.sendMessage(AutoCheck.this.handler.obtainMessage(10086, autoCheckCheckAsrInternal));
                }
            }
        }).start();
    }

    public String obtainAllMessage() {
        PrintConfig printConfig = new PrintConfig();
        printConfig.withLog = true;
        printConfig.withInfo = true;
        printConfig.withLogOnSuccess = true;
        return formatString(printConfig);
    }

    public String obtainDebugMessage() {
        PrintConfig printConfig = new PrintConfig();
        printConfig.withInfo = true;
        return formatString(printConfig);
    }

    public String obtainErrorMessage() {
        return formatString(new PrintConfig());
    }
}
