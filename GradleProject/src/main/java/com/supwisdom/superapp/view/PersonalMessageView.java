package com.supwisdom.superapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.supwisdom.zueb.R;

/* JADX INFO: loaded from: classes2.dex */
public class PersonalMessageView extends ConstraintLayout {
    public static String A = "";
    public static String B = "";
    public static String C = "";
    public static String D = "";
    public static String z = "";
    public ImageView t;
    public ImageView u;
    public TextView v;
    public TextView w;
    public TextView x;
    public a y;

    public interface a {
        void onItemClick(int i);
    }

    public PersonalMessageView(Context context) {
        this(context, null);
    }

    public /* synthetic */ void a(int i, View view) {
        this.y.onItemClick(i);
    }

    public /* synthetic */ void b(View view) {
        this.y.onItemClick(0);
    }

    public void d(final int i) {
        if (i == 0) {
            if (this.x.getText().toString().equals(getResources().getString(R.string.to_certificate))) {
                this.x.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.qn1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f8941a.b(view);
                    }
                });
            }
        } else if (this.x.getText().toString().equals(getResources().getString(R.string.to_bind)) || this.x.getText().toString().equals(getResources().getString(R.string.to_change)) || this.x.getText().toString().equals(getResources().getString(R.string.to_unbind))) {
            this.x.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.pn1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f8811a.a(i, view);
                }
            });
        }
    }

    public void setListener(a aVar) {
        this.y = aVar;
    }

    public PersonalMessageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void a(int i, String str) {
        if (i == 11101) {
            this.v.setText(getResources().getString(R.string.QQ));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_qq_true));
            this.x.setText(getResources().getString(R.string.to_unbind));
            z = "bind";
        } else if (i == 8) {
            this.v.setText(getResources().getString(R.string.dingding));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_dingding_true));
            this.x.setText(getResources().getString(R.string.to_unbind));
            A = "bind";
        } else if (i == 4) {
            this.v.setText(getResources().getString(R.string.wechat));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_wechat_true));
            this.x.setText(getResources().getString(R.string.to_unbind));
            B = "bind";
        } else if (i == 5) {
            this.v.setText(getResources().getString(R.string.work_wechat));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_work_wechat_true));
            this.x.setText(getResources().getString(R.string.to_unbind));
            C = "bind";
        } else if (i == 6) {
            this.v.setText(getResources().getString(R.string.alipay));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_alipay_true));
            this.x.setText(getResources().getString(R.string.to_unbind));
            D = "bind";
        } else if (i == 2) {
            this.v.setText(getResources().getString(R.string.email));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_email_true));
            this.x.setText(getResources().getString(R.string.to_change));
        } else if (i == 1) {
            this.v.setText(getResources().getString(R.string.phone_number));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_phonenumber_true));
            this.x.setText(getResources().getString(R.string.to_change));
        } else if (i == 3) {
            this.v.setText(getResources().getString(R.string.safe_question));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_safequestion_true));
            this.x.setText(getResources().getString(R.string.to_change));
        } else {
            this.v.setText(getResources().getString(R.string.certification));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_certification_true));
            this.x.setText(getResources().getString(R.string.to_change));
        }
        this.u.setVisibility(8);
        if (i == 0) {
            this.w.setVisibility(8);
            this.x.setText(getResources().getString(R.string.has_certificated));
        } else {
            this.w.setVisibility(str != null ? 0 : 8);
            this.w.setText(str);
        }
        this.x.setTextColor(getResources().getColor(R.color.color_1F2021));
        this.x.setBackground(getResources().getDrawable(R.drawable.shape_radius13_colorf1f3f6));
        d(i);
    }

    public PersonalMessageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = View.inflate(context, R.layout.item_personal_message, this);
        this.t = (ImageView) viewInflate.findViewById(R.id.iv_icon);
        this.u = (ImageView) viewInflate.findViewById(R.id.iv_point);
        this.v = (TextView) viewInflate.findViewById(R.id.tv_title);
        this.w = (TextView) viewInflate.findViewById(R.id.tv_content);
        this.x = (TextView) viewInflate.findViewById(R.id.tv_action);
    }

    public void a(int i, boolean z2) {
        if (i == 11101) {
            this.v.setText(getResources().getString(R.string.QQ));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_qq_false));
        } else if (i == 8) {
            this.v.setText(getResources().getString(R.string.dingding));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_dingding_false));
        } else if (i == 4) {
            this.v.setText(getResources().getString(R.string.wechat));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_wechat_false));
        } else if (i == 5) {
            this.v.setText(getResources().getString(R.string.work_wechat));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_work_wechat_false));
        } else if (i == 6) {
            this.v.setText(getResources().getString(R.string.alipay));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_alipay_false));
        } else if (i == 2) {
            this.v.setText(getResources().getString(R.string.email));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_email_false));
        } else if (i == 1) {
            this.v.setText(getResources().getString(R.string.phone_number));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_phonenumber_false));
        } else if (i == 3) {
            this.v.setText(getResources().getString(R.string.safe_question));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_safequestion_false));
        } else {
            this.v.setText(getResources().getString(R.string.certification));
            this.t.setBackground(getResources().getDrawable(R.drawable.icon_certification_false));
        }
        this.w.setVisibility(8);
        if (z2) {
            this.u.setVisibility(0);
        }
        if (i == 0) {
            this.x.setText(getResources().getString(R.string.to_certificate));
        } else {
            this.x.setText(getResources().getString(R.string.to_bind));
        }
        this.x.setTextColor(getResources().getColor(R.color.color_ED663F));
        this.x.setBackground(getResources().getDrawable(R.drawable.shape_radius13_colored663f));
        d(i);
    }
}
