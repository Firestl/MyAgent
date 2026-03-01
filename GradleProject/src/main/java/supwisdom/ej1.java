package supwisdom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.supwisdom.superapp.view.MaxHeightRecyclerView;
import com.supwisdom.zueb.R;
import java.util.ArrayList;

/* JADX INFO: compiled from: SafeQuestionDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class ej1 extends ql0 {
    public Context h;
    public MaxHeightRecyclerView i;
    public a j;
    public String k;
    public TextView l;
    public TextView m;

    /* JADX INFO: compiled from: SafeQuestionDialog.java */
    public static class a extends RecyclerView.g<b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Context f7505a;
        public ArrayList<String> b;
        public ej1 c;
        public TextView d;

        public a(Context context, ej1 ej1Var) {
            this.f7505a = context;
            this.c = ej1Var;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(final b bVar, @SuppressLint({RecyclerView.TAG}) final int i) {
            ArrayList<String> arrayList = this.b;
            if (arrayList == null || arrayList.size() == 0) {
                return;
            }
            bVar.f7506a.setText(this.b.get(i));
            bVar.f7506a.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.hi1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f7842a.a(bVar, i, view);
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            ArrayList<String> arrayList = this.b;
            if (arrayList != null) {
                return arrayList.size();
            }
            return 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public b onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new b(LayoutInflater.from(this.f7505a).inflate(R.layout.item_safe_question, viewGroup, false));
        }

        public /* synthetic */ void a(b bVar, int i, View view) {
            TextView textView = this.d;
            if (textView != null) {
                textView.setBackgroundColor(this.f7505a.getResources().getColor(R.color.ffffff));
            }
            bVar.f7506a.setBackgroundColor(this.f7505a.getResources().getColor(R.color.color_F6F6F6));
            this.d = bVar.f7506a;
            this.c.a(this.b.get(i));
        }

        public void a(ArrayList<String> arrayList) {
            this.b = arrayList;
        }
    }

    /* JADX INFO: compiled from: SafeQuestionDialog.java */
    public static class b extends RecyclerView.b0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f7506a;

        public b(View view) {
            super(view);
            this.f7506a = (TextView) view.findViewById(R.id.tv_question);
        }
    }

    public ej1(Context context) {
        super(context, R.style.dialog_transparent);
        this.h = context;
        d();
    }

    public /* synthetic */ void a(View view) {
        this.l.setSelected(false);
        dismiss();
    }

    public /* synthetic */ void b(View view) {
        this.l.setSelected(true);
        dismiss();
    }

    public String c() {
        if (this.l.isSelected()) {
            return this.k;
        }
        return null;
    }

    public final void d() {
        Window window = getWindow();
        setCanceledOnTouchOutside(false);
        View viewInflate = LayoutInflater.from(this.h).inflate(R.layout.dialog_safe_question, (ViewGroup) null);
        MaxHeightRecyclerView maxHeightRecyclerView = (MaxHeightRecyclerView) viewInflate.findViewById(R.id.rv_question);
        this.i = maxHeightRecyclerView;
        maxHeightRecyclerView.setMaxHeight(jn1.a(this.h, 137.0f));
        this.l = (TextView) viewInflate.findViewById(R.id.tv_sure);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_cancel);
        this.m = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.ii1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f7957a.a(view);
            }
        });
        this.l.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.gi1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f7737a.b(view);
            }
        });
        a aVar = new a(this.h, this);
        this.j = aVar;
        this.i.setAdapter(aVar);
        this.i.setLayoutManager(new LinearLayoutManager(this.h, 1, false));
        setContentView(viewInflate);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = jn1.a(this.h, 300.0f);
        attributes.height = -2;
        attributes.gravity = 17;
        window.setAttributes(attributes);
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void a(ArrayList<String> arrayList) {
        this.k = null;
        TextView textView = this.j.d;
        if (textView != null) {
            textView.setBackgroundColor(this.h.getResources().getColor(R.color.ffffff));
            this.j.d = null;
        }
        this.j.a(arrayList);
        this.j.notifyDataSetChanged();
    }

    public void a(String str) {
        this.k = str;
    }
}
