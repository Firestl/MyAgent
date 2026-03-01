package supwisdom;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.supwisdom.superapp.ui.activity.AccountInfoActivity;
import com.supwisdom.superapp.ui.activity.ForgetPasswordActivity;
import com.supwisdom.superapp.ui.activity.H5Activity;
import com.supwisdom.zueb.R;
import java.util.List;

/* JADX INFO: compiled from: QuestionDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class dj1 extends Dialog implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f7349a;
    public JSONObject b;
    public TextView c;
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f7350e;
    public RecyclerView f;
    public a g;
    public Boolean h;
    public Boolean i;

    /* JADX INFO: compiled from: QuestionDialog.java */
    public class a extends RecyclerView.g<b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Context f7351a;
        public List<gm1> b;

        /* JADX INFO: renamed from: supwisdom.dj1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: QuestionDialog.java */
        public class ViewOnClickListenerC0213a implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f7352a;

            public ViewOnClickListenerC0213a(String str) {
                this.f7352a = str;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (this.f7352a != null) {
                    Intent intent = new Intent(a.this.f7351a, (Class<?>) H5Activity.class);
                    intent.setData(Uri.parse(this.f7352a));
                    a.this.f7351a.startActivity(intent);
                }
                dj1.this.dismiss();
            }
        }

        /* JADX INFO: compiled from: QuestionDialog.java */
        public class b extends RecyclerView.b0 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public TextView f7353a;

            public b(a aVar, View view) {
                super(view);
                this.f7353a = (TextView) view.findViewById(R.id.tv_question_name);
            }
        }

        public a(Context context, List<gm1> list) {
            this.f7351a = context;
            this.b = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            List<gm1> list = this.b;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(b bVar, int i) {
            String strA = this.b.get(i).a();
            String strB = this.b.get(i).b();
            bVar.f7353a.setText(strA);
            bVar.itemView.setOnClickListener(new ViewOnClickListenerC0213a(strB));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public b onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new b(this, LayoutInflater.from(this.f7351a).inflate(R.layout.layout_quesion_item, viewGroup, false));
        }
    }

    public dj1(Context context, JSONObject jSONObject, Boolean bool, Boolean bool2) {
        super(context, R.style.Dialog);
        this.h = true;
        this.i = true;
        this.f7349a = context;
        this.b = jSONObject;
        this.h = bool;
        this.i = bool2;
        a();
        b();
    }

    public final void a() {
        Window window = getWindow();
        window.requestFeature(1);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.layout_question_dialog);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 80;
        window.setAttributes(attributes);
        Log.i("TAG", "questionExtra: =====" + this.b);
        this.f = (RecyclerView) findViewById(R.id.recycler_question);
        this.f.setLayoutManager(new LinearLayoutManager(this.f7349a));
        this.c = (TextView) findViewById(R.id.tv_account_active);
        this.d = (TextView) findViewById(R.id.tv_forget);
        this.f7350e = (TextView) findViewById(R.id.tv_question_cancel);
        this.c.setVisibility(this.h.booleanValue() ? 0 : 8);
        this.d.setVisibility(this.i.booleanValue() ? 0 : 8);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.f7350e.setOnClickListener(this);
    }

    public final void b() {
        if (this.b != null) {
            JSONArray jSONArray = new JSONArray();
            if (this.b.getString("configCustomEntryLink1Name") != null && this.b.getString("configCustomEntryLink1Url") != null) {
                JSONObject jSONObject = new JSONObject();
                if ((!com.igexin.push.core.b.m.equals(this.b.getString("configCustomEntryLink1Name")) || !com.igexin.push.core.b.m.equals(this.b.getString("configCustomEntryLink1Url"))) && !TextUtils.isEmpty(this.b.getString("configCustomEntryLink1Name"))) {
                    jSONObject.put("questionName", (Object) this.b.getString("configCustomEntryLink1Name"));
                    jSONObject.put("questionUrl", (Object) this.b.getString("configCustomEntryLink1Url"));
                    jSONArray.add(jSONObject);
                }
            }
            if (this.b.getString("configCustomEntryLink2Name") != null && this.b.getString("configCustomEntryLink2Url") != null) {
                JSONObject jSONObject2 = new JSONObject();
                if ((!com.igexin.push.core.b.m.equals(this.b.getString("configCustomEntryLink2Name")) || !com.igexin.push.core.b.m.equals(this.b.getString("configCustomEntryLink2Url"))) && !TextUtils.isEmpty(this.b.getString("configCustomEntryLink2Name"))) {
                    jSONObject2.put("questionName", (Object) this.b.getString("configCustomEntryLink2Name"));
                    jSONObject2.put("questionUrl", (Object) this.b.getString("configCustomEntryLink2Url"));
                    jSONArray.add(jSONObject2);
                }
            }
            if (this.b.getString("configCustomEntryLink3Name") != null && this.b.getString("configCustomEntryLink1Url") != null) {
                JSONObject jSONObject3 = new JSONObject();
                if ((!com.igexin.push.core.b.m.equals(this.b.getString("configCustomEntryLink3Name")) || !com.igexin.push.core.b.m.equals(this.b.getString("configCustomEntryLink3Url"))) && !TextUtils.isEmpty(this.b.getString("configCustomEntryLink3Name"))) {
                    jSONObject3.put("questionName", (Object) this.b.getString("configCustomEntryLink3Name"));
                    jSONObject3.put("questionUrl", (Object) this.b.getString("configCustomEntryLink3Url"));
                    jSONArray.add(jSONObject3);
                }
            }
            if (this.b.getString("configCustomEntryLink4Name") != null && this.b.getString("configCustomEntryLink1Url") != null) {
                JSONObject jSONObject4 = new JSONObject();
                if ((!com.igexin.push.core.b.m.equals(this.b.getString("configCustomEntryLink4Name")) || !com.igexin.push.core.b.m.equals(this.b.getString("configCustomEntryLink4Url"))) && !TextUtils.isEmpty(this.b.getString("configCustomEntryLink4Name"))) {
                    jSONObject4.put("questionName", (Object) this.b.getString("configCustomEntryLink4Name"));
                    jSONObject4.put("questionUrl", (Object) this.b.getString("configCustomEntryLink4Url"));
                    jSONArray.add(jSONObject4);
                }
            }
            a aVar = new a(this.f7349a, JSON.parseArray(jSONArray.toJSONString(), gm1.class));
            this.g = aVar;
            this.f.setAdapter(aVar);
        }
    }

    @Override // android.view.View.OnClickListener
    @SuppressLint({"NonConstantResourceId"})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_account_active) {
            Intent intent = new Intent();
            intent.setClass(this.f7349a, AccountInfoActivity.class);
            this.f7349a.startActivity(intent);
            dismiss();
            return;
        }
        if (id != R.id.tv_forget) {
            if (id != R.id.tv_question_cancel) {
                return;
            }
            dismiss();
        } else {
            Intent intent2 = new Intent();
            intent2.setClass(this.f7349a, ForgetPasswordActivity.class);
            this.f7349a.startActivity(intent2);
            dismiss();
        }
    }
}
