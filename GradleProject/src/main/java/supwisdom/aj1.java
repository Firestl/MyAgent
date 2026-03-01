package supwisdom;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.supwisdom.zueb.R;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodePayListResult;
import com.synjones.mobilegroup.libofflinecodesdk.core.OfflineCodePayController;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.GetPayListListener;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: PayWayDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class aj1 extends Dialog implements View.OnClickListener {
    public static List<ExternalCodePayListResult.DataBean> i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f6930a;
    public RecyclerView b;
    public ImageView c;
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b f6931e;
    public String f;
    public String g;
    public int h;

    /* JADX INFO: compiled from: PayWayDialog.java */
    public class a extends RecyclerView.g<b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public List<ExternalCodePayListResult.DataBean> f6932a;

        /* JADX INFO: renamed from: supwisdom.aj1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PayWayDialog.java */
        public class ViewOnClickListenerC0209a implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f6933a;

            public ViewOnClickListenerC0209a(int i) {
                this.f6933a = i;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a aVar = a.this;
                aVar.notifyItemChanged(aj1.this.h);
                aj1.this.h = this.f6933a;
                a aVar2 = a.this;
                aVar2.notifyItemChanged(aj1.this.h);
                a aVar3 = a.this;
                aj1.this.g = ((ExternalCodePayListResult.DataBean) aVar3.f6932a.get(this.f6933a)).name;
                a aVar4 = a.this;
                aj1.this.f = String.valueOf(((ExternalCodePayListResult.DataBean) aVar4.f6932a.get(this.f6933a)).typeId);
                a.this.notifyItemChanged(this.f6933a);
            }
        }

        /* JADX INFO: compiled from: PayWayDialog.java */
        public class b extends RecyclerView.b0 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public ImageButton f6934a;
            public TextView b;
            public LinearLayout c;

            public b(a aVar, View view) {
                super(view);
                this.f6934a = (ImageButton) view.findViewById(R.id.iv_select);
                this.b = (TextView) view.findViewById(R.id.tv_payway);
                this.c = (LinearLayout) view.findViewById(R.id.ll_payType);
            }
        }

        public a(List<ExternalCodePayListResult.DataBean> list) {
            this.f6932a = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            List<ExternalCodePayListResult.DataBean> list = this.f6932a;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public long getItemId(int i) {
            return 0L;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(b bVar, @SuppressLint({RecyclerView.TAG}) int i) {
            bVar.itemView.setSelected(aj1.this.h == i);
            bVar.b.setText(this.f6932a.get(i).name);
            if (aj1.this.h == i) {
                bVar.c.setBackgroundResource(R.drawable.shape_blue_27);
                bVar.f6934a.setSelected(true);
            } else {
                bVar.c.setBackgroundResource(R.drawable.shape_gray_27);
                bVar.f6934a.setSelected(false);
            }
            bVar.itemView.setOnClickListener(new ViewOnClickListenerC0209a(i));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public b onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new b(this, LayoutInflater.from(aj1.this.f6930a).inflate(R.layout.item_pay_way, viewGroup, false));
        }
    }

    /* JADX INFO: compiled from: PayWayDialog.java */
    public interface b {
        void a(String str, String str2);
    }

    public aj1(Context context, b bVar) {
        super(context, R.style.Dialog);
        this.h = 0;
        new ArrayList();
        new ArrayList();
        this.f6930a = context;
        this.f6931e = bVar;
        b();
    }

    public final void b() {
        Window window = getWindow();
        window.requestFeature(1);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.dialog_payway);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 80;
        window.setAttributes(attributes);
        this.b = (RecyclerView) findViewById(R.id.rv_pay_way);
        this.d = (TextView) findViewById(R.id.btn_ok);
        this.c = (ImageView) findViewById(R.id.backBt);
        this.b.setLayoutManager(new LinearLayoutManager(this.f6930a));
        this.d.setOnClickListener(this);
        this.c.setOnClickListener(this);
    }

    public void c() {
        if (isShowing()) {
            return;
        }
        show();
        List<ExternalCodePayListResult.DataBean> list = i;
        if (list != null) {
            this.b.setAdapter(new a(list));
        } else {
            a();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String str;
        int id = view.getId();
        if (id == R.id.backBt) {
            dismiss();
            return;
        }
        if (id == R.id.btn_ok && this.f6931e != null) {
            dismiss();
            String str2 = this.g;
            if (str2 == null || (str = this.f) == null) {
                return;
            }
            this.f6931e.a(str2, str);
        }
    }

    public final void a() {
        OfflineCodePayController.getInstance().getPaymentList(new GetPayListListener() { // from class: supwisdom.fi1
            @Override // com.synjones.mobilegroup.libofflinecodesdk.listeners.GetPayListListener
            public final void onGetPayListResult(ExternalCodePayListResult externalCodePayListResult) {
                this.f7609a.a(externalCodePayListResult);
            }
        });
    }

    public /* synthetic */ void a(ExternalCodePayListResult externalCodePayListResult) {
        List<ExternalCodePayListResult.DataBean> list;
        if ("0".equals(externalCodePayListResult.code)) {
            if (TextUtils.isEmpty(this.f) && TextUtils.isEmpty(this.g) && (list = externalCodePayListResult.data) != null && list.size() > 0) {
                this.g = externalCodePayListResult.data.get(0).name;
                this.f = String.valueOf(externalCodePayListResult.data.get(0).typeId);
            }
            List<ExternalCodePayListResult.DataBean> list2 = externalCodePayListResult.data;
            i = list2;
            this.b.setAdapter(new a(list2));
            return;
        }
        Toast.makeText(getContext(), externalCodePayListResult.msg, 0).show();
    }
}
