package supwisdom;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: CollectDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class ri1 extends Dialog {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f9065a;
    public String b;

    public ri1(Context context, String str) {
        super(context, R.style.MyDialogStyle);
        this.f9065a = context;
        this.b = str;
        a();
    }

    public final void a() {
        Window window = getWindow();
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_collection);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = a(this.f9065a, 150.0f);
        attributes.width = a(this.f9065a, 160.0f);
        attributes.gravity = 17;
        window.setAttributes(attributes);
        ImageView imageView = (ImageView) findViewById(R.id.iv_collect_status);
        TextView textView = (TextView) findViewById(R.id.tv_collect_status);
        if ("0".equals(this.b)) {
            textView.setText("收藏已取消");
            imageView.setImageDrawable(this.f9065a.getDrawable(R.drawable.icon_collect_cancel));
        } else if ("1".equals(this.b)) {
            textView.setText("收藏成功");
            imageView.setImageDrawable(this.f9065a.getDrawable(R.drawable.icon_collect_success));
        }
    }

    public int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
