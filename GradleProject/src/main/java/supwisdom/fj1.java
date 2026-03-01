package supwisdom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.supwisdom.zueb.R;
import java.util.List;

/* JADX INFO: compiled from: SecureQuestionDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class fj1 extends ql0 {
    public Context h;
    public List<String> i;
    public RecyclerView j;
    public a k;

    /* JADX INFO: compiled from: SecureQuestionDialog.java */
    public interface a {
        void a(String str);
    }

    /* JADX INFO: compiled from: SecureQuestionDialog.java */
    public class b extends RecyclerView.g<a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public List<String> f7610a;

        /* JADX INFO: compiled from: SecureQuestionDialog.java */
        public class a extends RecyclerView.b0 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public TextView f7611a;

            public a(b bVar, View view) {
                super(view);
                this.f7611a = (TextView) view.findViewById(R.id.tv_select_question);
            }
        }

        public b(List<String> list) {
            this.f7610a = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(final a aVar, final int i) {
            final String str = this.f7610a.get(i);
            aVar.f7611a.setText(str);
            aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.li1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f8293a.a(i, aVar, str, view);
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            List<String> list = this.f7610a;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public a onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new a(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_question, viewGroup, false));
        }

        public /* synthetic */ void a(int i, a aVar, String str, View view) {
            if (fj1.this.k != null) {
                fj1.this.k.a(this.f7610a.get(i));
            }
            aVar.f7611a.setText(str);
        }
    }

    public fj1(Context context, List<String> list) {
        super(context, R.style.dialog_transparent);
        this.h = context;
        this.i = list;
        c();
    }

    public /* synthetic */ void b(View view) {
        dismiss();
    }

    public final void c() {
        Window window = getWindow();
        View viewInflate = LayoutInflater.from(this.h).inflate(R.layout.dialog_secure_question, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_cancel);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_sure);
        this.j = (RecyclerView) viewInflate.findViewById(R.id.rv_question);
        this.j.setLayoutManager(new LinearLayoutManager(this.h));
        this.j.setAdapter(new b(this.i));
        setContentView(viewInflate);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 17;
        window.setAttributes(attributes);
        textView.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.ji1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f8068a.a(view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.ki1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f8179a.b(view);
            }
        });
    }

    public /* synthetic */ void a(View view) {
        dismiss();
    }

    public void a(a aVar) {
        this.k = aVar;
    }
}
