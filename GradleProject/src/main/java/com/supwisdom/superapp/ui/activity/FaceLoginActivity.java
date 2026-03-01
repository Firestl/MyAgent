package com.supwisdom.superapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.AccountInfo;
import com.supwisdom.zueb.R;
import java.util.List;
import supwisdom.fn1;
import supwisdom.in1;
import supwisdom.kh1;
import supwisdom.sh1;
import supwisdom.tm1;

/* JADX INFO: loaded from: classes2.dex */
public class FaceLoginActivity extends WXBaseActivity implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f4090a;
    public TextView b;
    public TextView c;
    public ImageView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ImageView f4091e;
    public View f;
    public ProgressBar g;
    public List<AccountInfo> h;
    public AccountInfo i;
    public RecyclerView j;

    public class a extends RecyclerView.g {

        /* JADX INFO: renamed from: com.supwisdom.superapp.ui.activity.FaceLoginActivity$a$a, reason: collision with other inner class name */
        public class ViewOnClickListenerC0085a implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f4093a;
            public final /* synthetic */ AccountInfo b;

            public ViewOnClickListenerC0085a(int i, AccountInfo accountInfo) {
                this.f4093a = i;
                this.b = accountInfo;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FaceLoginActivity faceLoginActivity = FaceLoginActivity.this;
                faceLoginActivity.i = (AccountInfo) faceLoginActivity.h.get(this.f4093a);
                FaceLoginActivity faceLoginActivity2 = FaceLoginActivity.this;
                faceLoginActivity2.c.setText(faceLoginActivity2.i.getAccount());
                kh1 kh1VarA = Picasso.a((Context) FaceLoginActivity.this).a("file://" + this.b.getUserPath());
                kh1VarA.a(new tm1());
                kh1VarA.a(FaceLoginActivity.this.f4091e);
                FaceLoginActivity.this.j.setVisibility(8);
                FaceLoginActivity.this.d.setRotation(0.0f);
            }
        }

        public class b extends RecyclerView.b0 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public TextView f4094a;
            public ImageView b;
            public ImageView c;

            public b(a aVar, View view) {
                super(view);
                this.f4094a = (TextView) view.findViewById(R.id.accountNameItemTxt);
                this.b = (ImageView) view.findViewById(R.id.itemAccountUserIv);
                this.c = (ImageView) view.findViewById(R.id.itemAccountItemDel);
            }
        }

        public a() {
        }

        public /* synthetic */ void a(int i, View view) {
            if (FaceLoginActivity.this.h.size() == 1) {
                return;
            }
            FaceLoginActivity.this.h.remove(i);
            notifyItemRemoved(i);
            notifyDataSetChanged();
            sh1.c.b(fn1.A, new Gson().toJson(FaceLoginActivity.this.h));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            if (FaceLoginActivity.this.h == null) {
                return 0;
            }
            return FaceLoginActivity.this.h.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void onBindViewHolder(RecyclerView.b0 b0Var, final int i) {
            b bVar = (b) b0Var;
            AccountInfo accountInfo = (AccountInfo) FaceLoginActivity.this.h.get(i);
            bVar.f4094a.setText(accountInfo.getAccount());
            kh1 kh1VarA = Picasso.a((Context) FaceLoginActivity.this).a("file://" + accountInfo.getUserPath());
            kh1VarA.a(new tm1());
            kh1VarA.a(bVar.b);
            if (FaceLoginActivity.this.h.size() == 1) {
                bVar.c.setVisibility(8);
            }
            bVar.itemView.setOnClickListener(new ViewOnClickListenerC0085a(i, accountInfo));
            bVar.c.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.xj1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f9784a.a(i, view);
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public RecyclerView.b0 onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new b(this, LayoutInflater.from(FaceLoginActivity.this).inflate(R.layout.item_face_account, viewGroup, false));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.backBt) {
            finish();
            return;
        }
        if (id == R.id.chooseBtn) {
            if (this.j.getVisibility() == 0) {
                this.j.setVisibility(8);
                this.d.setRotation(0.0f);
                return;
            } else {
                this.d.setRotation(180.0f);
                this.j.setVisibility(0);
                return;
            }
        }
        if (id == R.id.loginBt && this.f.isSelected()) {
            Intent intent = new Intent(this, (Class<?>) FaceLivenessExpActivity.class);
            intent.putExtra("verifyType", "login");
            intent.putExtra("account", this.i.getAccount());
            intent.putExtra("deviceId", this.i.getDeviceId());
            intent.putExtra("privateKey", this.i.getPrivateKey());
            startActivity(intent);
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.trackPageName = getResources().getString(R.string.faceLogin_activity);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        setContentView(R.layout.activity_face_account);
        this.f4090a = (TextView) findViewById(R.id.loginTxt);
        this.f4091e = (ImageView) findViewById(R.id.faceUserPicIv);
        this.b = (TextView) findViewById(R.id.backBt);
        this.f = findViewById(R.id.loginBt);
        this.g = (ProgressBar) findViewById(R.id.loading);
        this.c = (TextView) findViewById(R.id.accountNameTxt);
        this.j = (RecyclerView) findViewById(R.id.accountRv);
        this.d = (ImageView) findViewById(R.id.chooseBtn);
        this.f.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.f.setSelected(true);
        List<AccountInfo> listA = in1.a();
        this.h = listA;
        AccountInfo accountInfo = listA.get(listA.size() - 1);
        this.i = accountInfo;
        this.c.setText(accountInfo.getAccount());
        kh1 kh1VarA = Picasso.a((Context) this).a("file://" + this.i.getUserPath());
        kh1VarA.a(new tm1());
        kh1VarA.a(this.f4091e);
        this.j.setLayoutManager(new LinearLayoutManager(this));
        this.j.setAdapter(new a());
    }
}
