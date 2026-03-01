package supwisdom;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;
import com.newcapec.virtualcard.R;

/* JADX INFO: loaded from: classes.dex */
public class z extends Dialog {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f9943a;
    public TextView b;
    public a c;

    public interface a {
        void a(z zVar);
    }

    public z(Context context) {
        super(context, R.style.virtual_card_dialog_noTitle);
        setContentView(R.layout.virtual_card_dialog_tip);
        this.b = (TextView) findViewById(R.id.tv_ok);
        this.f9943a = (TextView) findViewById(R.id.tv_message);
        this.b.setOnClickListener(new y(this));
    }
}
