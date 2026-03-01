package com.baidu.aip.face.stat;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.baidu.aip.face.stat.NetUtil;
import com.baidu.idl.face.platform.common.ConstantHelper;
import com.baidu.idl.facesdk.FaceInfo;
import com.google.gson.internal.bind.TypeAdapters;
import com.umeng.commonsdk.statistics.idtracking.g;
import io.dcloud.common.constant.AbsoluteConst;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class Ast {
    public static final String AS_FILE_NAME = "ast";
    public static final String FACE_HIT_KEY_LASSTTIME = "FACE_HIT_KEY_LASSTTIME";
    public static final long SAVE_INTERVAL = 15000;
    public static final long UPADTE_DEFUALT_DELAY_TIME = 15000;
    public static final String UPLOAD_LASSTTIME = "UPLOAD_LASSTTIME";
    public static Ast instance;
    public File asFile;
    public Context context;
    public Dev dev;
    public boolean isInit;
    public long lastSavetime;
    public Properties properties;
    public String scene;
    public String faceHitKey = "";
    public SparseArray<Integer> faceIds = new SparseArray<>();
    public ExecutorService es = Executors.newSingleThreadExecutor();
    public Future future = null;

    private void clear() {
        this.properties.clear();
    }

    private String generateFaceHitKey(String str) {
        return new SimpleDateFormat("yyyy_MM_dd_HH").format(new Date()) + "_" + str;
    }

    public static Ast getInstance() {
        if (instance == null) {
            synchronized (Ast.class) {
                instance = new Ast();
            }
        }
        return instance;
    }

    private boolean initFile() {
        this.asFile = new File(this.context.getFilesDir(), AS_FILE_NAME);
        this.properties = new Properties();
        if (FileUtil.createFile(this.asFile)) {
            return FileUtil.loadPropertiesFile(this.asFile, this.properties);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void netRequest() throws Throwable {
        if (this.properties.size() == 0) {
            return;
        }
        NetUtil.uploadData(new NetUtil.RequestAdapter<Object>() { // from class: com.baidu.aip.face.stat.Ast.2
            @Override // com.baidu.aip.face.stat.NetUtil.RequestAdapter
            public String getRequestString() {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("mh", "faceSdkStatistic");
                    JSONArray jSONArray = new JSONArray();
                    for (Map.Entry entry : ((Properties) Ast.this.properties.clone()).entrySet()) {
                        String str = (String) entry.getKey();
                        String str2 = (String) entry.getValue();
                        if (!str.equalsIgnoreCase(Ast.FACE_HIT_KEY_LASSTTIME) && !str.equalsIgnoreCase(Ast.UPLOAD_LASSTTIME)) {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("type", "facesdk");
                            jSONObject2.put("scene", Ast.this.scene);
                            jSONObject2.put("appid", Ast.this.dev.getPackagename());
                            jSONObject2.put(ConstantHelper.LOG_DE, Ast.this.dev.getBrand());
                            jSONObject2.put(g.f5442a, Ast.this.dev.getUniqueID());
                            jSONObject2.put("os", "Android");
                            jSONObject2.put(ConstantHelper.LOG_OS, Ast.this.dev.getSysVersion());
                            jSONObject2.put("version", Ast.this.dev.getSdkVersion());
                            if (str.contains("liveness")) {
                                jSONObject2.put("isliving", "true");
                            } else {
                                jSONObject2.put("isliving", AbsoluteConst.FALSE);
                            }
                            jSONObject2.put("finish", "1");
                            String[] strArrSplit = str.split("_");
                            if (strArrSplit.length > 4) {
                                jSONObject2.put(TypeAdapters.AnonymousClass27.YEAR, strArrSplit[0]);
                                jSONObject2.put(TypeAdapters.AnonymousClass27.MONTH, strArrSplit[1]);
                                jSONObject2.put("day", strArrSplit[2]);
                                jSONObject2.put("hour", strArrSplit[3]);
                            }
                            jSONObject2.put("num", str2);
                            jSONArray.put(jSONObject2);
                        }
                    }
                    jSONObject.put("dt", jSONArray);
                    return jSONObject.toString();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    return "";
                }
            }

            @Override // com.baidu.aip.face.stat.NetUtil.RequestAdapter
            public String getURL() {
                return "http://brain.baidu.com/record/api";
            }

            @Override // com.baidu.aip.face.stat.NetUtil.RequestAdapter
            public void parseResponse(InputStream inputStream) throws JSONException, IOException {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    try {
                        int i = inputStream.read(bArr);
                        if (i <= 0) {
                            break;
                        } else {
                            byteArrayOutputStream.write(bArr, 0, i);
                        }
                    } catch (Throwable th) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        throw th;
                    }
                }
                byteArrayOutputStream.flush();
                if (new JSONObject(new String(byteArrayOutputStream.toByteArray(), "UTF-8")).optInt("code") == 0) {
                    Ast.this.properties.clear();
                    Ast.this.dev.setFirstRun(false);
                    FileUtil.savePropertiesFile(Ast.this.asFile, Ast.this.properties);
                    Ast.this.properties.setProperty(Ast.UPLOAD_LASSTTIME, String.valueOf(System.currentTimeMillis()));
                }
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        });
    }

    private void sendData() {
        Future future = this.future;
        if (future == null || future.isDone()) {
            this.future = this.es.submit(new Runnable() { // from class: com.baidu.aip.face.stat.Ast.1
                @Override // java.lang.Runnable
                public void run() throws Throwable {
                    Ast.this.netRequest();
                }
            });
        }
    }

    public void faceHit(String str, int i, FaceInfo[] faceInfoArr) throws Throwable {
        if (faceInfoArr == null) {
            this.faceIds.clear();
            return;
        }
        int i2 = 0;
        for (FaceInfo faceInfo : faceInfoArr) {
            if (this.faceIds.get(faceInfo.face_id) == null) {
                SparseArray<Integer> sparseArray = this.faceIds;
                int i3 = faceInfo.face_id;
                sparseArray.put(i3, Integer.valueOf(i3));
                i2++;
            }
        }
        if (i2 == 0) {
            return;
        }
        faceHit(str, i, i2);
    }

    public void immediatelyUpload() {
        sendData();
    }

    public boolean init(Context context, String str, String str2) {
        if (!this.isInit && context != null) {
            this.context = context.getApplicationContext();
            Dev dev = new Dev();
            this.dev = dev;
            dev.init(context);
            this.dev.setSdkVersion(str);
            this.scene = str2;
            initFile();
        }
        return true;
    }

    public void faceHit() throws Throwable {
        faceHit("liveness", 15000L, 1);
    }

    public void faceHit(String str) throws Throwable {
        faceHit(str, 15000L, 1);
    }

    public void faceHit(String str, int i) throws Throwable {
        faceHit(str, 15000L, i);
    }

    public void faceHit(String str, long j, int i) throws Throwable {
        long j2;
        String strGenerateFaceHitKey = generateFaceHitKey(str);
        String property = this.properties.getProperty(strGenerateFaceHitKey);
        if (TextUtils.isEmpty(property)) {
            this.properties.setProperty(strGenerateFaceHitKey, String.valueOf(i));
            this.properties.setProperty(FACE_HIT_KEY_LASSTTIME, String.valueOf(System.currentTimeMillis()));
        } else {
            this.properties.setProperty(strGenerateFaceHitKey, String.valueOf(Integer.parseInt(property) + i));
        }
        long j3 = 0;
        try {
            j2 = Long.parseLong(this.properties.getProperty(FACE_HIT_KEY_LASSTTIME));
        } catch (Exception e2) {
            e2.printStackTrace();
            j2 = 0;
        }
        if (System.currentTimeMillis() - j2 > 15000) {
            System.currentTimeMillis();
            FileUtil.savePropertiesFile(this.asFile, this.properties);
            this.properties.setProperty(FACE_HIT_KEY_LASSTTIME, String.valueOf(System.currentTimeMillis()));
        }
        try {
            j3 = Long.parseLong(this.properties.getProperty(UPLOAD_LASSTTIME));
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (this.dev.getFirstRun() || System.currentTimeMillis() - j3 >= j) {
            sendData();
        }
    }
}
