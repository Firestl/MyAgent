package supwisdom;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.taobao.weex.common.Constants;

/* JADX INFO: compiled from: TipsAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public class qj1 extends ArrayAdapter<String> {
    public static String b = "%1$d.\"%2$s\"";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8929a;

    public qj1(Context context, int i) {
        super(context, i);
    }

    public void a(int i) {
        this.f8929a = i;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(getContext(), getContext().getResources().getIdentifier("bdspeech_suggestion_item", Constants.Name.LAYOUT, getContext().getPackageName()), null);
        }
        try {
            TextView textView = view instanceof TextView ? (TextView) view : (TextView) view.findViewWithTag("textView");
            textView.setTextColor(this.f8929a);
            textView.setText(String.format(b, Integer.valueOf(i + 1), getItem(i)));
            return view;
        } catch (ClassCastException e2) {
            Log.e("ArrayAdapter", "You must supply a resource ID for a TextView");
            throw new IllegalStateException("ArrayAdapter requires the resource ID to be a TextView", e2);
        }
    }

    public qj1(Context context) {
        this(context, 0);
    }
}
