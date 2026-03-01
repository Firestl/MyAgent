package supwisdom;

import android.content.Context;
import android.util.Log;
import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: WakeUpRecogUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class rj1 implements EventListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public EventManager f9067a;
    public EventManager b;
    public String c;
    public b d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f9068e;

    /* JADX INFO: compiled from: WakeUpRecogUtil.java */
    public interface b {
        void a(int i, int i2, String str, pj1 pj1Var);

        void a(pj1 pj1Var);

        void a(String[] strArr, pj1 pj1Var);

        void b(String[] strArr, pj1 pj1Var);

        void onAsrAudio(byte[] bArr, int i, int i2);

        void onAsrBegin();

        void onAsrEnd();

        void onAsrExit();

        void onAsrLongFinish();

        void onAsrOnlineNluResult(String str);

        void onAsrReady();

        void onAsrVolume(int i, int i2);

        void onOfflineLoaded();

        void onOfflineUnLoaded();
    }

    /* JADX INFO: compiled from: WakeUpRecogUtil.java */
    public interface c {
        void d(String str);
    }

    /* JADX INFO: compiled from: WakeUpRecogUtil.java */
    public class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f9069a;
        public int b;
        public String c;

        public d(rj1 rj1Var) {
            this.f9069a = -1;
            this.b = -1;
        }
    }

    /* JADX INFO: compiled from: WakeUpRecogUtil.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static rj1 f9070a = new rj1();
    }

    public static rj1 e() {
        return e.f9070a;
    }

    public void a(b bVar) {
        this.d = bVar;
    }

    public void b() {
        HashMap map = new HashMap();
        map.put(SpeechConstant.WP_WORDS_FILE, "assets://WakeUp.bin");
        this.b.send(SpeechConstant.WAKEUP_START, new JSONObject(map).toString(), null, 0, 0);
    }

    public void c() {
        this.f9067a.send(SpeechConstant.ASR_STOP, null, null, 0, 0);
    }

    public void d() {
        this.b.send(SpeechConstant.WAKEUP_STOP, null, null, 0, 0);
    }

    @Override // com.baidu.speech.EventListener
    public void onEvent(String str, String str2, byte[] bArr, int i, int i2) {
        Log.d("MainActivity", String.format("event: name=%s, params=%s", str, str2));
        if (str.equals(SpeechConstant.CALLBACK_EVENT_WAKEUP_SUCCESS)) {
            this.f9068e.d(str2);
            return;
        }
        if (SpeechConstant.CALLBACK_EVENT_WAKEUP_STOPED.equals(str)) {
            return;
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_LOADED)) {
            this.d.onOfflineLoaded();
            return;
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_UNLOADED)) {
            this.d.onOfflineUnLoaded();
            return;
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_READY)) {
            this.d.onAsrReady();
            return;
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_BEGIN)) {
            this.d.onAsrBegin();
            return;
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_END)) {
            this.d.onAsrEnd();
            return;
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)) {
            pj1 pj1VarE = pj1.e(str2);
            String[] strArrC = pj1VarE.c();
            if (pj1VarE.f()) {
                this.d.b(strArrC, pj1VarE);
                return;
            } else if (pj1VarE.h()) {
                this.d.a(strArrC, pj1VarE);
                return;
            } else {
                if (pj1VarE.g()) {
                    this.d.onAsrOnlineNluResult(new String(bArr, i, i2));
                    return;
                }
                return;
            }
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_FINISH)) {
            pj1 pj1VarE2 = pj1.e(str2);
            if (pj1VarE2.e()) {
                this.d.a(pj1VarE2.b(), pj1VarE2.d(), pj1VarE2.a(), pj1VarE2);
                return;
            } else {
                this.d.a(pj1VarE2);
                return;
            }
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_LONG_SPEECH)) {
            this.d.onAsrLongFinish();
            return;
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_EXIT)) {
            this.d.onAsrExit();
            return;
        }
        if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_VOLUME)) {
            d dVarA = a(str2);
            this.d.onAsrVolume(dVarA.f9069a, dVarA.b);
        } else if (str.equals(SpeechConstant.CALLBACK_EVENT_ASR_AUDIO)) {
            this.d.onAsrAudio(bArr, i, i2);
        }
    }

    public rj1() {
        this.c = "{\"accept-audio-data\":false,\"disable-punctuation\":true,\"accept-audio-volume\":true,\"pid\":1936}";
    }

    public void a(Context context) {
        EventManager eventManagerCreate = EventManagerFactory.create(context, "asr");
        this.f9067a = eventManagerCreate;
        eventManagerCreate.registerListener(this);
    }

    public void a() {
        this.f9067a.send(SpeechConstant.ASR_START, this.c, null, 0, 0);
    }

    public final d a(String str) {
        d dVar = new d();
        dVar.c = str;
        try {
            JSONObject jSONObject = new JSONObject(str);
            dVar.f9069a = jSONObject.getInt("volume-percent");
            dVar.b = jSONObject.getInt("volume");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return dVar;
    }
}
