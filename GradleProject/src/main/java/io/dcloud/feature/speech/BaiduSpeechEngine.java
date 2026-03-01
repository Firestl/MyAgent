package io.dcloud.feature.speech;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import com.baidu.speech.asr.SpeechConstant;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.feature.R;
import io.dcloud.feature.speech.dialog.BaiduSpeechDialog;
import java.util.Arrays;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class BaiduSpeechEngine extends AbsSpeechEngine {
    public BaiduSpeechDialog dialog;
    public boolean isContinue = false;
    public int nBest = 1;

    @SuppressLint({"HandlerLeak"})
    public Handler mHandler = new Handler() { // from class: io.dcloud.feature.speech.BaiduSpeechEngine.3
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 4) {
                BaiduSpeechEngine.this.dialog.setVoiceText(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_msg_listening_now));
            }
            if (i == 100) {
                BaiduSpeechEngine.this.mListener.onStateChange((byte) 11, Integer.valueOf(BaiduSpeechEngine.this.dialog.setVolume(((Integer) message.obj).intValue())), true);
                return;
            }
            switch (i) {
                case 11:
                    BaiduSpeechEngine.this.dialog.setVoiceText((String) message.obj);
                    BaiduSpeechEngine.this.mListener.onStateChange((byte) 10, message.obj, true);
                    break;
                case 12:
                    try {
                        JSONObject jSONObject = new JSONObject((String) message.obj);
                        BaiduSpeechEngine.this.mListener.onStateChange((byte) 7, new String[]{jSONObject.opt("subErrorCode") + "", (String) jSONObject.opt("descMessage")}, false);
                        break;
                    } catch (JSONException unused) {
                    }
                    BaiduSpeechEngine.this.dialog.dismiss();
                    break;
                case 13:
                    Object obj = message.obj;
                    if (obj != null && (obj instanceof String[])) {
                        String[] strArr = (String[]) obj;
                        BaiduSpeechEngine.this.dialog.setVoiceText(strArr[0]);
                        if (strArr.length > BaiduSpeechEngine.this.nBest) {
                            strArr = (String[]) Arrays.copyOfRange(strArr, 0, BaiduSpeechEngine.this.nBest);
                        }
                        BaiduSpeechEngine baiduSpeechEngine = BaiduSpeechEngine.this;
                        baiduSpeechEngine.mListener.onStateChange((byte) 8, strArr, baiduSpeechEngine.isContinue);
                        if (!BaiduSpeechEngine.this.isContinue) {
                            BaiduSpeechEngine.this.mHandler.postDelayed(new Runnable() { // from class: io.dcloud.feature.speech.BaiduSpeechEngine.3.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    BaiduSpeechEngine.this.dialog.dismiss();
                                }
                            }, 500L);
                        }
                        break;
                    }
                    break;
            }
        }
    };

    @Override // io.dcloud.feature.speech.AbsSpeechEngine
    public void startRecognize(JSONObject jSONObject) {
        BaiduSpeechDialog baiduSpeechDialog = new BaiduSpeechDialog(this.mContext);
        this.dialog = baiduSpeechDialog;
        baiduSpeechDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: io.dcloud.feature.speech.BaiduSpeechEngine.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                BaiduSpeechEngine.this.dialog.stopRecog();
                BaiduSpeechEngine.this.mListener.onStateChange((byte) 2, null, false);
            }
        });
        this.dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: io.dcloud.feature.speech.BaiduSpeechEngine.2
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                if (BaiduSpeechEngine.this.isContinue) {
                    return;
                }
                BaiduSpeechEngine.this.mListener.onStateChange((byte) 7, new String[]{"-10", DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_exception_distinguish)}, false);
            }
        });
        String strOptString = jSONObject.optString(AbsoluteConst.JSON_KEY_LANG, "zh-cn");
        boolean zOptBoolean = jSONObject.optBoolean(AbsoluteConst.JSON_KEY_PUNCTUATION, true);
        this.isContinue = jSONObject.optBoolean(AbsoluteConst.JSON_KEY_CONTINUE, false);
        boolean zOptBoolean2 = jSONObject.optBoolean(AbsoluteConst.JSON_KEY_USERINTERFACE, true);
        int iOptInt = jSONObject.optInt(AbsoluteConst.JSON_KEY_NBEST, 1);
        this.nBest = iOptInt;
        if (iOptInt <= 0) {
            this.nBest = 1;
        }
        HashMap map = new HashMap();
        byte b = -1;
        int iHashCode = strOptString.hashCode();
        if (iHashCode != 96599618) {
            if (iHashCode != 115814250) {
                if (iHashCode == 1383241525 && strOptString.equals("zh-cantonese")) {
                    b = 2;
                }
            } else if (strOptString.equals("zh-cn")) {
                b = 0;
            }
        } else if (strOptString.equals("en-us")) {
            b = 1;
        }
        if (b != 0) {
            if (b == 1) {
                map.put("pid", 1737);
            } else if (b == 2) {
                map.put("pid", 16372);
            }
        } else if (zOptBoolean) {
            map.put("pid", 15372);
        } else {
            map.put("pid", 1537);
        }
        map.put(SpeechConstant.DISABLE_PUNCTUATION, Boolean.valueOf(!zOptBoolean));
        if (this.isContinue) {
            map.put(SpeechConstant.VAD_ENDPOINT_TIMEOUT, 0);
        } else {
            map.put(SpeechConstant.VAD_ENDPOINT_TIMEOUT, 800);
        }
        map.put(AbsoluteConst.JSON_KEY_USERINTERFACE, Boolean.valueOf(zOptBoolean2));
        this.dialog.startRecog(map, this.mHandler);
        this.mListener.onStateChange((byte) 1, null, false);
    }

    @Override // io.dcloud.feature.speech.AbsSpeechEngine
    public void stopRecognize(boolean z) {
        if (this.dialog.stopRecog()) {
            this.mListener.onStateChange((byte) 2, null, false);
        }
    }
}
