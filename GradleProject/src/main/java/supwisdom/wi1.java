package supwisdom;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: NeverOpenTipsDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class wi1 extends Dialog {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f9636a;
    public a b;

    /* JADX INFO: compiled from: NeverOpenTipsDialog.java */
    public interface a {
        void a();

        void b();
    }

    public wi1(Context context) {
        super(context, R.style.dialog_transparent);
        this.f9636a = context;
        a();
    }

    public final void a() {
        Window window = getWindow();
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        View viewInflate = LayoutInflater.from(this.f9636a).inflate(R.layout.dialog_never_open_tips, (ViewGroup) null);
        a(viewInflate);
        setContentView(viewInflate);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = -2;
        attributes.width = -2;
        attributes.gravity = 17;
        window.setAttributes(attributes);
    }

    public /* synthetic */ void b(View view) {
        dismiss();
        a aVar = this.b;
        if (aVar != null) {
            aVar.b();
        }
    }

    public /* synthetic */ void c(View view) {
        dismiss();
        a aVar = this.b;
        if (aVar != null) {
            aVar.a();
        }
    }

    public final void a(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_know);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_think_again);
        textView.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.bi1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f7072a.b(view2);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.ai1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f6926a.c(view2);
            }
        });
    }

    public void a(a aVar) {
        this.b = aVar;
    }
}
