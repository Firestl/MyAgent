package supwisdom;

import com.alibaba.fastjson.JSON;
import com.newcapec.virtualcard.VirtualCard;
import com.newcapec.virtualcard.activity.PasswordActivity;
import com.newcapec.virtualcard.widget.NumberKeyboardWidget;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c implements NumberKeyboardWidget.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PasswordActivity f7129a;

    public c(PasswordActivity passwordActivity) {
        this.f7129a = passwordActivity;
    }

    public void a(String str) {
        try {
            if (this.f7129a.b.getText().toString().length() >= 6) {
                return;
            }
            this.f7129a.b.a(str);
            if (this.f7129a.b.getText().toString().length() == 6) {
                PasswordActivity passwordActivity = this.f7129a;
                List<String> cipherTextList = passwordActivity.b.getCipherTextList();
                d0.c("password:" + JSON.toJSONString(cipherTextList));
                passwordActivity.c.show();
                m pVar = VirtualCard.isT6 ? new p() : new r();
                pVar.a(new d(passwordActivity));
                pVar.a((String[]) cipherTextList.toArray(new String[6]));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            this.f7129a.b("密码加密失败");
        }
    }
}
