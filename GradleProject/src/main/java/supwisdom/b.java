package supwisdom;

import com.newcapec.virtualcard.R;
import com.newcapec.virtualcard.VirtualCard;
import com.newcapec.virtualcard.activity.PasswordActivity;
import com.newcapec.virtualcard.widget.PasswordInputView;

/* JADX INFO: loaded from: classes.dex */
public class b implements PasswordInputView.a {
    public b(PasswordActivity passwordActivity) {
    }

    public String a(String str) {
        return VirtualCard.isT6 ? str : gw0.b(str, "1", b0.a(R.string.des_key));
    }
}
