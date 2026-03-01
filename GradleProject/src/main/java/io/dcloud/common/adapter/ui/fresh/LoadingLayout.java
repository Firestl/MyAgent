package io.dcloud.common.adapter.ui.fresh;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import io.dcloud.common.adapter.ui.fresh.ILoadingLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LoadingLayout extends FrameLayout implements ILoadingLayout {
    public View mContainer;
    public ILoadingLayout.State mCurState;
    public ILoadingLayout.State mPreState;

    /* JADX INFO: renamed from: io.dcloud.common.adapter.ui.fresh.LoadingLayout$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$io$dcloud$common$adapter$ui$fresh$ILoadingLayout$State;

        static {
            int[] iArr = new int[ILoadingLayout.State.values().length];
            $SwitchMap$io$dcloud$common$adapter$ui$fresh$ILoadingLayout$State = iArr;
            try {
                iArr[ILoadingLayout.State.RESET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$dcloud$common$adapter$ui$fresh$ILoadingLayout$State[ILoadingLayout.State.RELEASE_TO_REFRESH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$dcloud$common$adapter$ui$fresh$ILoadingLayout$State[ILoadingLayout.State.PULL_TO_REFRESH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$dcloud$common$adapter$ui$fresh$ILoadingLayout$State[ILoadingLayout.State.REFRESHING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$dcloud$common$adapter$ui$fresh$ILoadingLayout$State[ILoadingLayout.State.NO_MORE_DATA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public LoadingLayout(Context context) {
        this(context, null);
    }

    public abstract View createLoadingView(Context context, AttributeSet attributeSet);

    public abstract int getContentSize();

    public ILoadingLayout.State getPreState() {
        return this.mPreState;
    }

    @Override // io.dcloud.common.adapter.ui.fresh.ILoadingLayout
    public ILoadingLayout.State getState() {
        return this.mCurState;
    }

    public void init(Context context, AttributeSet attributeSet) {
    }

    public void onNoMoreData() {
    }

    @Override // io.dcloud.common.adapter.ui.fresh.ILoadingLayout
    public void onPull(float f) {
    }

    public void onPullToRefresh() {
    }

    public void onRefreshing() {
    }

    public void onReleaseToRefresh() {
    }

    public void onReset() {
    }

    public void onStateChanged(ILoadingLayout.State state, ILoadingLayout.State state2) {
        int i = AnonymousClass1.$SwitchMap$io$dcloud$common$adapter$ui$fresh$ILoadingLayout$State[state.ordinal()];
        if (i == 1) {
            onReset();
            return;
        }
        if (i == 2) {
            onReleaseToRefresh();
            return;
        }
        if (i == 3) {
            onPullToRefresh();
        } else if (i == 4) {
            onRefreshing();
        } else {
            if (i != 5) {
                return;
            }
            onNoMoreData();
        }
    }

    public void setLastUpdatedLabel(CharSequence charSequence) {
    }

    public void setLoadingDrawable(Drawable drawable) {
    }

    public void setPullLabel(CharSequence charSequence) {
    }

    public void setRefreshingLabel(CharSequence charSequence) {
    }

    public void setReleaseLabel(CharSequence charSequence) {
    }

    @Override // io.dcloud.common.adapter.ui.fresh.ILoadingLayout
    public void setState(ILoadingLayout.State state) {
        ILoadingLayout.State state2 = this.mCurState;
        if (state2 != state) {
            this.mPreState = state2;
            this.mCurState = state;
            onStateChanged(state, state2);
        }
    }

    public void show(boolean z) {
        ViewGroup.LayoutParams layoutParams;
        if (z == (getVisibility() == 0) || (layoutParams = this.mContainer.getLayoutParams()) == null) {
            return;
        }
        if (z) {
            layoutParams.height = -2;
        } else {
            layoutParams.height = 0;
        }
        setVisibility(z ? 0 : 4);
    }

    public LoadingLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoadingLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ILoadingLayout.State state = ILoadingLayout.State.NONE;
        this.mCurState = state;
        this.mPreState = state;
        init(context, attributeSet);
    }
}
