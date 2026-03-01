package supwisdom;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: NotificationDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class yi1 extends Dialog {
    public static int c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f9887a;
    public a b;

    /* JADX INFO: compiled from: NotificationDialog.java */
    public interface a {
        void a();

        void b();

        void c();
    }

    public yi1(Context context) {
        super(context, R.style.dialog_transparent);
        this.f9887a = context;
        a();
    }

    public final void a() {
        Window window = getWindow();
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        View viewInflate = LayoutInflater.from(this.f9887a).inflate(R.layout.dialog_nitification, (ViewGroup) null);
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
        a aVar = this.b;
        if (aVar != null) {
            aVar.a();
        }
    }

    public final void a(View view) {
        SharedPreferences sharedPreferences = this.f9887a.getSharedPreferences("CLICK_COUNT", 0);
        final SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        Button button = (Button) view.findViewById(R.id.btn_open_now);
        Button button2 = (Button) view.findViewById(R.id.btn_open_not_now);
        final Button button3 = (Button) view.findViewById(R.id.btn_never_open);
        c = sharedPreferences.getInt("CLICK_COUNT", 0);
        Log.i("TAG", "initView: mClickNumNormal = " + c);
        if (sharedPreferences.getInt("CLICK_COUNT", 0) >= 3) {
            button3.setVisibility(0);
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.ei1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f7503a.b(view2);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.ci1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f7215a.a(editorEdit, button3, view2);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.di1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f7347a.a(editorEdit, view2);
            }
        });
    }

    public /* synthetic */ void a(SharedPreferences.Editor editor, Button button, View view) {
        a aVar = this.b;
        if (aVar != null) {
            aVar.b();
        }
        int i = c + 1;
        c = i;
        editor.putInt("CLICK_COUNT", i);
        editor.apply();
        if (c > 3) {
            button.setVisibility(0);
        }
    }

    public /* synthetic */ void a(SharedPreferences.Editor editor, View view) {
        a aVar = this.b;
        if (aVar != null) {
            aVar.c();
        }
        editor.clear();
        editor.apply();
    }

    public void a(a aVar) {
        this.b = aVar;
    }
}
