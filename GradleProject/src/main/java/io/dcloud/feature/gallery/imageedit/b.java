package io.dcloud.feature.gallery.imageedit;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import io.dcloud.base.R;
import io.dcloud.feature.gallery.imageedit.c.d;
import io.dcloud.feature.gallery.imageedit.view.IMGColorGroup;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public class b extends Dialog implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public EditText f6526a;
    public a b;
    public d c;
    public IMGColorGroup d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6527e;
    public TextView f;

    public interface a {
        void a(d dVar);
    }

    public b(Context context, a aVar) {
        super(context, R.style.ImageTextDialog);
        this.f6527e = -1;
        setContentView(R.layout.image_text_dialog);
        this.b = aVar;
        Window window = getWindow();
        if (window != null) {
            window.setLayout(-1, -1);
        }
    }

    private void b() {
        try {
            Field declaredField = TextView.class.getDeclaredField("mCursorDrawableRes");
            declaredField.setAccessible(true);
            declaredField.set(this.f6526a, Integer.valueOf(R.drawable.image_edit_cursor));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(d dVar) {
        this.c = dVar;
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        this.f6527e = this.d.getCheckColor();
        if (!this.f.isSelected()) {
            this.f6526a.setTextColor(this.f6527e);
            this.f6526a.setBackgroundColor(0);
        } else {
            if (this.f6527e == -1) {
                this.f6526a.setTextColor(-16777216);
            } else {
                this.f6526a.setTextColor(-1);
            }
            this.f6526a.setBackgroundColor(this.f6527e);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_done) {
            a();
            this.f6527e = -1;
            this.f6526a.setBackgroundColor(0);
            return;
        }
        if (id == R.id.tv_cancel) {
            dismiss();
            this.f6527e = -1;
            this.f6526a.setBackgroundColor(0);
        } else if (id == R.id.textview_1) {
            view.setSelected(!view.isSelected());
            if (!view.isSelected()) {
                this.f6526a.setTextColor(this.f6527e);
                this.f6526a.setBackgroundColor(0);
            } else {
                if (this.f6527e == -1) {
                    this.f6526a.setTextColor(-16777216);
                } else {
                    this.f6526a.setTextColor(-1);
                }
                this.f6526a.setBackgroundColor(this.f6527e);
            }
        }
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        IMGColorGroup iMGColorGroup = (IMGColorGroup) findViewById(R.id.cg_colors);
        this.d = iMGColorGroup;
        iMGColorGroup.setOnCheckedChangeListener(this);
        this.f6526a = (EditText) findViewById(R.id.et_text);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_done).setOnClickListener(this);
        TextView textView = (TextView) findViewById(R.id.textview_1);
        this.f = textView;
        textView.setOnClickListener(this);
        this.f.setSelected(false);
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
        this.f6526a.setPadding(30, 30, 30, 30);
        d dVar = this.c;
        if (dVar != null) {
            this.f6526a.setText(dVar.c());
            int iB = this.c.b();
            this.f6527e = iB;
            this.f6526a.setTextColor(iB);
            b();
            int iA = this.c.a();
            if (iA == 0) {
                this.f.setSelected(false);
                this.f6526a.setBackgroundColor(0);
            } else {
                this.f6527e = iA;
                this.f6526a.setBackgroundColor(iA);
                this.f.setSelected(true);
            }
            if (!this.c.d()) {
                EditText editText = this.f6526a;
                editText.setSelection(editText.length());
            }
            this.c = null;
        } else {
            this.f6526a.setText("");
            this.f.setSelected(false);
        }
        getCurrentFocus();
        this.d.setCheckColor(this.f6527e);
        this.f6526a.requestFocus();
    }

    private void a() {
        int i;
        String string = this.f6526a.getText().toString();
        if (!TextUtils.isEmpty(string) && this.b != null) {
            int i2 = -1;
            if (this.f.isSelected()) {
                i = this.f6527e;
                if (i == -1) {
                    i2 = -16777216;
                }
            } else {
                i = 0;
                i2 = this.f6527e;
            }
            this.b.a(new d(string, i2, i));
        }
        dismiss();
    }
}
