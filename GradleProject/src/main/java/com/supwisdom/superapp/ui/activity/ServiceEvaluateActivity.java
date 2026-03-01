package com.supwisdom.superapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.view.RatingBarView;
import com.supwisdom.zueb.R;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import supwisdom.ct1;
import supwisdom.et1;
import supwisdom.fn1;
import supwisdom.mj1;
import supwisdom.xs1;

/* JADX INFO: loaded from: classes2.dex */
public class ServiceEvaluateActivity extends WXBaseActivity implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f4365a;
    public RatingBarView c;
    public RatingBarView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public RatingBarView f4366e;
    public TextView f;
    public TextView g;
    public TextView h;
    public String b = "";
    public int i = 5;
    public int j = 5;
    public int k = 5;

    public class a implements RatingBarView.a {
        public a() {
        }

        @Override // com.supwisdom.superapp.view.RatingBarView.a
        public void a(float f, int i) {
            ServiceEvaluateActivity.this.f.setText(Float.toString(f));
            ServiceEvaluateActivity.this.i = i;
        }
    }

    public class b implements RatingBarView.a {
        public b() {
        }

        @Override // com.supwisdom.superapp.view.RatingBarView.a
        public void a(float f, int i) {
            ServiceEvaluateActivity.this.g.setText(Float.toString(f));
            ServiceEvaluateActivity.this.j = i;
        }
    }

    public class c implements RatingBarView.a {
        public c() {
        }

        @Override // com.supwisdom.superapp.view.RatingBarView.a
        public void a(float f, int i) {
            ServiceEvaluateActivity.this.h.setText(Float.toString(f));
            ServiceEvaluateActivity.this.k = i;
        }
    }

    public class d implements Callback<et1> {
        public d() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<et1> call, Throwable th) {
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, Response<et1> response) {
            try {
                JSONObject jSONObject = new JSONObject(response.body().string());
                int i = jSONObject.getInt("code");
                String string = jSONObject.getString("message");
                if (i == 0) {
                    Toast.makeText(ServiceEvaluateActivity.this, string, 0).show();
                    ServiceEvaluateActivity.this.finish();
                } else if ("".equals(string)) {
                } else {
                    Toast.makeText(ServiceEvaluateActivity.this, string, 0).show();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public final void l() {
        this.b = getIntent().getStringExtra("appId");
    }

    public final void m() {
        this.c = (RatingBarView) findViewById(R.id.ratingbar_process);
        this.d = (RatingBarView) findViewById(R.id.ratingbar_service);
        this.f4366e = (RatingBarView) findViewById(R.id.ratingbar_guide);
        this.f = (TextView) findViewById(R.id.tv_process);
        this.g = (TextView) findViewById(R.id.tv_service);
        this.h = (TextView) findViewById(R.id.tv_guide);
        TextView textView = (TextView) findViewById(R.id.tv_commit_evaluate);
        this.f4365a = textView;
        textView.setOnClickListener(this);
        this.c.setOnStarChangeListener(new a());
        this.d.setOnStarChangeListener(new b());
        this.f4366e.setOnStarChangeListener(new c());
    }

    public final void n() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("serviceId", this.b);
            jSONObject.put("flowReasonableScore", this.i);
            jSONObject.put("serviceSpeedScore", this.j);
            jSONObject.put("guideAccuracyScore", this.k);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().y(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new d());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.tv_commit_evaluate) {
            return;
        }
        n();
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        setContentView(R.layout.activity_service_evaluate);
        m();
        l();
    }
}
