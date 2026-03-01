package supwisdom;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import com.newcapec.virtualcard.R;

/* JADX INFO: loaded from: classes.dex */
public class x extends Dialog {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f9686a;

    public interface a {
    }

    public x(Context context) {
        super(context, R.style.virtual_card_dialog_noFrame);
        setContentView(R.layout.virtual_card_dialog_menu);
        findViewById(R.id.tv_pause).setOnClickListener(new w(this));
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.setGravity(53);
        attributes.x = getContext().getResources().getDimensionPixelSize(R.dimen.virtual_card_menu_dialog_x);
        attributes.y = getContext().getResources().getDimensionPixelSize(R.dimen.virtual_card_menu_dialog_y);
        window.setAttributes(attributes);
    }
}
