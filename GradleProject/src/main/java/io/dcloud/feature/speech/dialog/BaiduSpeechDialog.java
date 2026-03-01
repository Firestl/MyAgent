package io.dcloud.feature.speech.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.widget.TextView;
import com.baidu.aip.core.recog.MyRecognizer;
import com.baidu.aip.core.recog.listener.MessageStatusRecogListener;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.PdrUtil;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class BaiduSpeechDialog extends Dialog {
    public TextView dialogTitle;
    public Activity mAttachActivity;
    public Context mContext;
    public MyRecognizer recognizer;
    public VolumeView volumeView;

    public BaiduSpeechDialog(Context context) {
        super(context);
        this.mContext = context;
        requestWindowFeature(1);
        setContentView(SpeechR.LAYOUT_DIALOG);
        initView(this.mContext);
        getWindow().setBackgroundDrawable(null);
    }

    private int getVoiceVolume(int i) {
        if (i >= 0 && i < 200) {
            return 1;
        }
        if (200 <= i && i < 400) {
            return 2;
        }
        if (400 <= i && i < 600) {
            return 3;
        }
        if (600 <= i && i < 800) {
            return 4;
        }
        if (800 <= i && i < 1000) {
            return 5;
        }
        if (1000 > i || i >= 1200) {
            return 1200 <= i ? 7 : 1;
        }
        return 6;
    }

    private void initView(Context context) {
        if (!(context instanceof Activity)) {
            throw new RuntimeException("not an activity");
        }
        this.mAttachActivity = (Activity) context;
        this.dialogTitle = (TextView) findViewById(SpeechR.ID_VOICE_TITLE);
        VolumeView volumeView = (VolumeView) findViewById(SpeechR.ID_VOICE_VOLUME);
        this.volumeView = volumeView;
        volumeView.setMaxVolume(7);
        this.volumeView.setVisibility(0);
        this.volumeView.setCurrentVolume(1);
        this.volumeView.setVolumeColor(Color.parseColor("#9C9C9C"));
    }

    public void setVoiceText(String str) {
        if (PdrUtil.isEmpty(str)) {
            return;
        }
        this.dialogTitle.setText(str);
    }

    public int setVolume(int i) {
        int voiceVolume = getVoiceVolume(i);
        if (voiceVolume > 0) {
            this.volumeView.setCurrentVolume(voiceVolume);
        }
        return voiceVolume;
    }

    @SuppressLint({"HandlerLeak"})
    public void startRecog(Map<String, Object> map, Handler handler) {
        this.recognizer = new MyRecognizer(this.mAttachActivity, new MessageStatusRecogListener(handler));
        if (((Boolean) map.get(AbsoluteConst.JSON_KEY_USERINTERFACE)).booleanValue()) {
            show();
        }
        map.remove(AbsoluteConst.JSON_KEY_USERINTERFACE);
        this.recognizer.start(map);
    }

    public boolean stopRecog() {
        if (isShowing()) {
            dismiss();
        }
        MyRecognizer myRecognizer = this.recognizer;
        if (myRecognizer == null) {
            return false;
        }
        try {
            myRecognizer.stop();
            this.recognizer.cancel();
            this.recognizer.release();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
