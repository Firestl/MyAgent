package supwisdom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: ChangeQuestionDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class qi1 extends ql0 {
    public Context h;
    public TextView i;
    public TextView j;

    /* JADX INFO: compiled from: ChangeQuestionDialog.java */
    public interface a {
        void a(int i);
    }

    public qi1(Context context, String str, String str2) {
        super(context, R.style.dialog_transparent);
        this.h = context;
        a(str, str2);
    }

    public final void a(String str, String str2) {
        Window window = getWindow();
        View viewInflate = LayoutInflater.from(this.h).inflate(R.layout.dialog_change_question, (ViewGroup) null);
        this.i = (TextView) viewInflate.findViewById(R.id.tv_question1);
        this.j = (TextView) viewInflate.findViewById(R.id.tv_question2);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_cancel);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_sure);
        setContentView(viewInflate);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 17;
        window.setAttributes(attributes);
        this.i.setText(str);
        this.j.setText(str2);
        textView.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.zh1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10004a.a(view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.xh1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f9766a.b(view);
            }
        });
    }

    public /* synthetic */ void b(View view) {
        dismiss();
    }

    public /* synthetic */ void a(View view) {
        dismiss();
    }

    public void a(final a aVar) {
        this.i.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.yh1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                aVar.a(1);
            }
        });
        this.j.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.wh1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                aVar.a(2);
            }
        });
    }
}
