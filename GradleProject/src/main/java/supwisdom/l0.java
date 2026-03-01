package supwisdom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.newcapec.virtualcard.R;

/* JADX INFO: loaded from: classes.dex */
public class l0 extends Toast {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Toast f8225a;

    public static void a(Context context, String str) {
        LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.virtual_card_toast, (ViewGroup) null);
        TextView textView = (TextView) linearLayout.findViewById(R.id.tv_toast_msg);
        if (f8225a == null) {
            f8225a = new Toast(context);
        }
        textView.setText(str);
        f8225a.setView(linearLayout);
        f8225a.setGravity(7, 0, 0);
        f8225a.setDuration(0);
        f8225a.show();
    }
}
