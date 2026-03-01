package supwisdom;

import android.app.Dialog;
import android.content.Context;
import com.newcapec.virtualcard.R;

/* JADX INFO: loaded from: classes.dex */
public class a0 extends Dialog {
    public a0(Context context) {
        super(context, R.style.virtual_card_dialog_noFrame);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.virtual_card_dialog_wait);
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        getWindow().setDimAmount(0.1f);
    }
}
