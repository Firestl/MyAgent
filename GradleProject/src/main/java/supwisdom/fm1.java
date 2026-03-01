package supwisdom;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.supwisdom.zueb.R;
import java.net.URLDecoder;
import java.util.List;

/* JADX INFO: compiled from: ShareAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public class fm1 extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<hm1> f7618a;
    public Activity b;
    public a c;

    /* JADX INFO: compiled from: ShareAdapter.java */
    public interface a {
        void b(int i);
    }

    /* JADX INFO: compiled from: ShareAdapter.java */
    public class b extends RecyclerView.b0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f7619a;
        public ImageView b;

        public b(fm1 fm1Var, View view) {
            super(view);
            this.f7619a = (TextView) view.findViewById(R.id.tv_share_title);
            this.b = (ImageView) view.findViewById(R.id.iv_share_icon);
        }
    }

    public fm1(Activity activity, List<hm1> list) {
        this.b = activity;
        this.f7618a = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(b bVar, final int i) {
        String strDecode = URLDecoder.decode(this.f7618a.get(i).b());
        Glide.with(this.b).asBitmap().load(this.f7618a.get(i).a()).into(bVar.b);
        bVar.f7619a.setText(strDecode);
        bVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.em1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f7513a.a(i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        List<hm1> list = this.f7618a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public b onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new b(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_share_data, viewGroup, false));
    }

    public /* synthetic */ void a(int i, View view) {
        a aVar = this.c;
        if (aVar != null) {
            aVar.b(i);
        }
    }

    public void a(a aVar) {
        this.c = aVar;
    }
}
