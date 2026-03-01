package supwisdom;

import android.content.Context;
import android.os.AsyncTask;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static JSONObject f7891a = new JSONObject();

    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f7892a;

        public a(Context context) {
            this.f7892a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            String string;
            InputStreamReader inputStreamReader;
            BufferedReader bufferedReader;
            StringBuilder sb;
            try {
                inputStreamReader = new InputStreamReader(this.f7892a.getAssets().open("errorMsg.json"), StandardCharsets.UTF_8);
                bufferedReader = new BufferedReader(inputStreamReader);
                sb = new StringBuilder();
            } catch (IOException e2) {
                e2.printStackTrace();
                string = "{}";
            }
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                } else {
                    sb.append(line);
                }
                i.f7891a = JSON.parseObject(string);
                d0.a("RespCodeMsgMap", "init: " + i.f7891a.size());
            }
            bufferedReader.close();
            inputStreamReader.close();
            string = sb.toString();
            i.f7891a = JSON.parseObject(string);
            d0.a("RespCodeMsgMap", "init: " + i.f7891a.size());
        }
    }

    public static void a(Context context) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new a(context));
    }
}
