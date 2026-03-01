package com.supwisdom.superapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.supwisdom.zueb.R;

/* JADX INFO: loaded from: classes2.dex */
public class CertifactionTypeView extends ConstraintLayout {
    public ImageView t;
    public ImageView u;
    public TextView v;
    public TextView w;
    public TextView x;

    public interface a {
    }

    public CertifactionTypeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setListener(a aVar) {
    }

    public CertifactionTypeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = View.inflate(context, R.layout.item_personal_message, this);
        this.t = (ImageView) viewInflate.findViewById(R.id.iv_icon);
        this.u = (ImageView) viewInflate.findViewById(R.id.iv_point);
        this.v = (TextView) viewInflate.findViewById(R.id.tv_title);
        this.w = (TextView) viewInflate.findViewById(R.id.tv_content);
        this.x = (TextView) viewInflate.findViewById(R.id.tv_action);
    }
}
