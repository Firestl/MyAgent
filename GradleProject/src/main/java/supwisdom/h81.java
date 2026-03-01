package supwisdom;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;
import com.sangfor.sdk.utils.SFLogN;
import supwisdom.i71;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class h81 extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public n71 f7817a;
    public n71 b;

    public h81(Context context) {
        super(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, i71.c.r);
        int i = i71.c.i;
        layoutParams.setMargins(i, i71.c.f, i, 0);
        setLayoutParams(layoutParams);
        setOrientation(1);
        setGravity(17);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i71.b.g);
        gradientDrawable.setCornerRadius(i71.c.f7925a);
        setBackground(gradientDrawable);
        n71 n71Var = new n71(context);
        this.f7817a = n71Var;
        n71Var.a(i71.b.b4);
        addView(this.f7817a);
        n71 n71Var2 = new n71(context);
        this.b = n71Var2;
        n71Var2.a(i71.b.c4);
        this.b.setPadding(0, i71.c.q, 0, 0);
        addView(this.b);
    }

    public void a(String str) {
        n71 n71Var = this.b;
        if (n71Var != null) {
            n71Var.b(str);
        } else {
            SFLogN.d("TerminalInfoLayout", "updateDeviceNameText is fail, deviceTextView is null");
        }
    }

    public void b(String str) {
        n71 n71Var = this.f7817a;
        if (n71Var != null) {
            n71Var.b(str);
        } else {
            SFLogN.d("TerminalInfoLayout", "updateOwnerText is fail, ownerTextView is null");
        }
    }
}
