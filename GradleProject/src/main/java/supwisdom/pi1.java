package supwisdom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.supwisdom.superapp.ui.activity.FaceLivenessExpActivity;
import com.supwisdom.superapp.ui.activity.VertificateActivity;
import com.supwisdom.zueb.R;
import java.util.ArrayList;

/* JADX INFO: compiled from: CertifactionTypeDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class pi1 extends ql0 {
    public Context h;
    public LinearLayout i;

    public pi1(Context context) {
        super(context, R.style.dialog_transparent);
        this.h = context;
        c();
    }

    public /* synthetic */ void a(View view) {
        dismiss();
    }

    public final void c() {
        Window window = getWindow();
        setCanceledOnTouchOutside(false);
        setCancelable(true);
        View viewInflate = LayoutInflater.from(this.h).inflate(R.layout.dialog_certifaction_type, (ViewGroup) null, false);
        this.i = (LinearLayout) viewInflate.findViewById(R.id.ll_type);
        ((Button) viewInflate.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() { // from class: supwisdom.vh1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f9513a.a(view);
            }
        });
        setContentView(viewInflate);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 17;
        window.setAttributes(attributes);
    }

    public void a(ArrayList<Integer> arrayList, String str) {
        for (int i = 0; i < arrayList.size(); i++) {
            View viewInflate = LayoutInflater.from(this.h).inflate(R.layout.item_certication_type, (ViewGroup) null);
            ImageView imageView = (ImageView) viewInflate.findViewById(R.id.iv_type);
            TextView textView = (TextView) viewInflate.findViewById(R.id.tv_type);
            final Intent intent = new Intent();
            if (arrayList.get(i).intValue() == 0) {
                imageView.setImageDrawable(this.h.getDrawable(R.drawable.mobile));
                textView.setText(this.h.getString(R.string.reserve_mobile));
                intent.putExtra("type", 1);
                intent.putExtra("account", str);
                intent.setClass(this.h, VertificateActivity.class);
            } else if (arrayList.get(i).intValue() == 2) {
                imageView.setImageDrawable(this.h.getDrawable(R.drawable.account_password));
                textView.setText(this.h.getString(R.string.account_password));
                intent.putExtra("type", 3);
                intent.setClass(this.h, VertificateActivity.class);
            } else if (arrayList.get(i).intValue() == 3) {
                imageView.setImageDrawable(this.h.getDrawable(R.drawable.face));
                textView.setText(this.h.getString(R.string.face));
                intent.putExtra("verifyType", "information_complete");
                intent.setClass(this.h, FaceLivenessExpActivity.class);
            } else if (arrayList.get(i).intValue() == 1) {
                imageView.setImageDrawable(this.h.getDrawable(R.drawable.idcard_by_hand));
                textView.setText(this.h.getString(R.string.idcard_by_hand));
                intent.putExtra("verifyType", "information_complete");
                intent.setClass(this.h, FaceLivenessExpActivity.class);
            } else {
                imageView.setImageDrawable(this.h.getDrawable(R.drawable.identify_card));
                textView.setText(this.h.getString(R.string.identify_card));
                intent.putExtra("type", 2);
                intent.putExtra("account", str);
                intent.setClass(this.h, VertificateActivity.class);
            }
            this.i.addView(viewInflate);
            viewInflate.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.uh1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f9411a.a(intent, view);
                }
            });
        }
    }

    public /* synthetic */ void a(Intent intent, View view) {
        dismiss();
        ((Activity) this.h).startActivityForResult(intent, 0);
    }
}
