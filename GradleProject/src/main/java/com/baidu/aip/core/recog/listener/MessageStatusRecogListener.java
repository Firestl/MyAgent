package com.baidu.aip.core.recog.listener;

import android.os.Handler;
import android.os.Message;
import com.baidu.aip.core.recog.RecogResult;
import com.baidu.speech.asr.SpeechConstant;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.feature.R;

/* JADX INFO: loaded from: classes.dex */
public class MessageStatusRecogListener extends StatusRecogListener {
    public static final String TAG = "MesStatusRecogListener";
    public Handler handler;
    public long speechEndTime = 0;
    public boolean needTime = true;

    public MessageStatusRecogListener(Handler handler) {
        this.handler = handler;
    }

    private void sendMessage(String str) {
        sendMessage(str, 9001);
    }

    private void sendStatusMessage(String str, String str2) {
        sendMessage(Operators.ARRAY_START_STR + str + Operators.ARRAY_END_STR + str2, this.status);
    }

    @Override // com.baidu.aip.core.recog.listener.StatusRecogListener, com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrBegin() {
        super.onAsrBegin();
    }

    @Override // com.baidu.aip.core.recog.listener.StatusRecogListener, com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrEnd() {
        super.onAsrEnd();
        this.speechEndTime = System.currentTimeMillis();
    }

    @Override // com.baidu.aip.core.recog.listener.StatusRecogListener, com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrExit() {
        super.onAsrExit();
        sendStatusMessage(SpeechConstant.CALLBACK_EVENT_ASR_EXIT, DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_exception_distinguish_engine_idle));
    }

    @Override // com.baidu.aip.core.recog.listener.StatusRecogListener, com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrFinalResult(String[] strArr, RecogResult recogResult) {
        super.onAsrFinalResult(strArr, recogResult);
        this.speechEndTime = 0L;
        sendMessage(strArr, this.status, true);
    }

    @Override // com.baidu.aip.core.recog.listener.StatusRecogListener, com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrFinish(RecogResult recogResult) {
        super.onAsrFinish(recogResult);
        String[] resultsRecognition = recogResult.getResultsRecognition();
        if (resultsRecognition == null || resultsRecognition.length <= 0) {
            return;
        }
        sendMessage(recogResult.getResultsRecognition(), this.status, true);
    }

    @Override // com.baidu.aip.core.recog.listener.StatusRecogListener, com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrFinishError(int i, int i2, String str, RecogResult recogResult) {
        super.onAsrFinishError(i, i2, str, recogResult);
        this.speechEndTime = 0L;
        sendMessage("{errorCode:'" + i + "',subErrorCode:'" + i2 + "',descMessage:'" + str + "'}", this.status, true);
        this.speechEndTime = 0L;
    }

    @Override // com.baidu.aip.core.recog.listener.StatusRecogListener, com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrLongFinish() {
        super.onAsrLongFinish();
        sendStatusMessage(SpeechConstant.CALLBACK_EVENT_ASR_LONG_SPEECH, DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_exception_long_voice_distinguish_done));
    }

    @Override // com.baidu.aip.core.recog.listener.StatusRecogListener, com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrOnlineNluResult(String str) {
        super.onAsrOnlineNluResult(str);
        if (str.isEmpty()) {
            return;
        }
        sendMessage(new String[]{str}, this.status, true);
    }

    @Override // com.baidu.aip.core.recog.listener.StatusRecogListener, com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrPartialResult(String[] strArr, RecogResult recogResult) {
        super.onAsrPartialResult(strArr, recogResult);
        sendMessage(strArr[0], this.status, true);
    }

    @Override // com.baidu.aip.core.recog.listener.StatusRecogListener, com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrReady() {
        super.onAsrReady();
        this.speechEndTime = 0L;
    }

    @Override // com.baidu.aip.core.recog.listener.StatusRecogListener, com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrVolume(int i, int i2) {
        super.onAsrVolume(i, i2);
        sendMessage(Integer.valueOf(i2), this.status, true);
    }

    private void sendMessage(String str, int i) {
        sendMessage(str, i, false);
    }

    private void sendMessage(Object obj, int i, boolean z) {
        Message messageObtain = Message.obtain();
        messageObtain.what = i;
        messageObtain.arg1 = this.status;
        if (z) {
            messageObtain.arg2 = 1;
        }
        messageObtain.obj = obj;
        this.handler.sendMessage(messageObtain);
    }
}
