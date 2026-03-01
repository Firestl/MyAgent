package supwisdom;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.supwisdom.superapp.ui.activity.H5Activity;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* JADX INFO: compiled from: H5Activity.java */
/* JADX INFO: loaded from: classes2.dex */
public class wl1 implements Callback<et1> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f9642a;
    public final /* synthetic */ ImageView b;
    public final /* synthetic */ TextView c;
    public final /* synthetic */ H5Activity d;

    /* JADX INFO: compiled from: H5Activity.java */
    public class a extends TimerTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ri1 f9643a;

        public a(wl1 wl1Var, ri1 ri1Var) {
            this.f9643a = ri1Var;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            this.f9643a.dismiss();
        }
    }

    public wl1(H5Activity h5Activity, String str, ImageView imageView, TextView textView) {
        this.d = h5Activity;
        this.f9642a = str;
        this.b = imageView;
        this.c = textView;
    }

    @Override // retrofit2.Callback
    public void onFailure(Call<et1> call, Throwable th) {
    }

    @Override // retrofit2.Callback
    public void onResponse(Call<et1> call, Response<et1> response) {
        et1 et1VarBody = response.body();
        if (et1VarBody != null) {
            try {
                if (new JSONObject(et1VarBody.string()).optInt("code") == 0) {
                    this.d.b0 = this.f9642a.equals("0") ? "1" : "0";
                    this.b.setSelected(this.f9642a.equals("0"));
                    this.c.setText(this.b.isSelected() ? "取消收藏" : "收藏");
                    this.d.N.dismiss();
                    ri1 ri1Var = new ri1(this.d, this.d.b0);
                    ri1Var.show();
                    new Timer().schedule(new a(this, ri1Var), 2000L);
                } else {
                    Toast.makeText(this.d, "收藏失败", 0).show();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
