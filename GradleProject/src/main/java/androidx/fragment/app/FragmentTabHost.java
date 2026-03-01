package androidx.fragment.app;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TabHost;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import supwisdom.dd;
import supwisdom.hd;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class FragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<a> f1303a;
    public Context b;
    public dd c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TabHost.OnTabChangeListener f1304e;
    public a f;
    public boolean g;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f1305a;

        public static class a implements Parcelable.Creator<SavedState> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.f1305a + Operators.BLOCK_END_STR;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f1305a);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f1305a = parcel.readString();
        }
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f1306a;
        public final Class<?> b;
        public final Bundle c;
        public Fragment d;
    }

    @Deprecated
    public FragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1303a = new ArrayList<>();
        a(context, attributeSet);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.inflatedId}, 0, 0);
        this.d = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        int size = this.f1303a.size();
        hd hdVarA = null;
        for (int i = 0; i < size; i++) {
            a aVar = this.f1303a.get(i);
            Fragment fragmentA = this.c.a(aVar.f1306a);
            aVar.d = fragmentA;
            if (fragmentA != null && !fragmentA.isDetached()) {
                if (aVar.f1306a.equals(currentTabTag)) {
                    this.f = aVar;
                } else {
                    if (hdVarA == null) {
                        hdVarA = this.c.a();
                    }
                    hdVarA.b(aVar.d);
                }
            }
        }
        this.g = true;
        hd hdVarA2 = a(currentTabTag, hdVarA);
        if (hdVarA2 != null) {
            hdVarA2.a();
            this.c.b();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.g = false;
    }

    @Override // android.view.View
    @Deprecated
    public void onRestoreInstanceState(@SuppressLint({"UnknownNullness"}) Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentTabByTag(savedState.f1305a);
    }

    @Override // android.view.View
    @Deprecated
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1305a = getCurrentTabTag();
        return savedState;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    @Deprecated
    public void onTabChanged(String str) {
        hd hdVarA;
        if (this.g && (hdVarA = a(str, (hd) null)) != null) {
            hdVarA.a();
        }
        TabHost.OnTabChangeListener onTabChangeListener = this.f1304e;
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChanged(str);
        }
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setOnTabChangedListener(TabHost.OnTabChangeListener onTabChangeListener) {
        this.f1304e = onTabChangeListener;
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    public final hd a(String str, hd hdVar) {
        Fragment fragment;
        a aVarA = a(str);
        if (this.f != aVarA) {
            if (hdVar == null) {
                hdVar = this.c.a();
            }
            a aVar = this.f;
            if (aVar != null && (fragment = aVar.d) != null) {
                hdVar.b(fragment);
            }
            if (aVarA != null) {
                Fragment fragment2 = aVarA.d;
                if (fragment2 == null) {
                    Fragment fragmentA = this.c.c().a(this.b.getClassLoader(), aVarA.b.getName());
                    aVarA.d = fragmentA;
                    fragmentA.setArguments(aVarA.c);
                    hdVar.a(this.d, aVarA.d, aVarA.f1306a);
                } else {
                    hdVar.a(fragment2);
                }
            }
            this.f = aVarA;
        }
        return hdVar;
    }

    public final a a(String str) {
        int size = this.f1303a.size();
        for (int i = 0; i < size; i++) {
            a aVar = this.f1303a.get(i);
            if (aVar.f1306a.equals(str)) {
                return aVar;
            }
        }
        return null;
    }
}
