package androidx.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import supwisdom.fe;
import supwisdom.ie;
import supwisdom.je;
import supwisdom.p0;
import supwisdom.uf;
import supwisdom.vd;
import supwisdom.vf;
import supwisdom.xd;
import supwisdom.yd;

/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends androidx.core.app.ComponentActivity implements xd, je, vf, p0 {
    public int mContentLayoutId;
    public final yd mLifecycleRegistry;
    public final OnBackPressedDispatcher mOnBackPressedDispatcher;
    public final uf mSavedStateRegistryController;
    public ie mViewModelStore;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ComponentActivity.super.onBackPressed();
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f1075a;
        public ie b;
    }

    public ComponentActivity() {
        this.mLifecycleRegistry = new yd(this);
        this.mSavedStateRegistryController = uf.a(this);
        this.mOnBackPressedDispatcher = new OnBackPressedDispatcher(new a());
        if (getLifecycle() == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getLifecycle().a(new vd() { // from class: androidx.activity.ComponentActivity.2
                @Override // supwisdom.vd
                public void a(xd xdVar, Lifecycle.Event event) {
                    if (event == Lifecycle.Event.ON_STOP) {
                        Window window = ComponentActivity.this.getWindow();
                        View viewPeekDecorView = window != null ? window.peekDecorView() : null;
                        if (viewPeekDecorView != null) {
                            viewPeekDecorView.cancelPendingInputEvents();
                        }
                    }
                }
            });
        }
        getLifecycle().a(new vd() { // from class: androidx.activity.ComponentActivity.3
            @Override // supwisdom.vd
            public void a(xd xdVar, Lifecycle.Event event) {
                if (event != Lifecycle.Event.ON_DESTROY || ComponentActivity.this.isChangingConfigurations()) {
                    return;
                }
                ComponentActivity.this.getViewModelStore().a();
            }
        });
        int i = Build.VERSION.SDK_INT;
        if (19 > i || i > 23) {
            return;
        }
        getLifecycle().a(new ImmLeaksCleaner(this));
    }

    @Deprecated
    public Object getLastCustomNonConfigurationInstance() {
        b bVar = (b) getLastNonConfigurationInstance();
        if (bVar != null) {
            return bVar.f1075a;
        }
        return null;
    }

    @Override // androidx.core.app.ComponentActivity, supwisdom.xd
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // supwisdom.p0
    public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    @Override // supwisdom.vf
    public final SavedStateRegistry getSavedStateRegistry() {
        return this.mSavedStateRegistryController.a();
    }

    @Override // supwisdom.je
    public ie getViewModelStore() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.mViewModelStore == null) {
            b bVar = (b) getLastNonConfigurationInstance();
            if (bVar != null) {
                this.mViewModelStore = bVar.b;
            }
            if (this.mViewModelStore == null) {
                this.mViewModelStore = new ie();
            }
        }
        return this.mViewModelStore;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.mOnBackPressedDispatcher.a();
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSavedStateRegistryController.a(bundle);
        fe.a(this);
        int i = this.mContentLayoutId;
        if (i != 0) {
            setContentView(i);
        }
    }

    @Deprecated
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        b bVar;
        Object objOnRetainCustomNonConfigurationInstance = onRetainCustomNonConfigurationInstance();
        ie ieVar = this.mViewModelStore;
        if (ieVar == null && (bVar = (b) getLastNonConfigurationInstance()) != null) {
            ieVar = bVar.b;
        }
        if (ieVar == null && objOnRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        b bVar2 = new b();
        bVar2.f1075a = objOnRetainCustomNonConfigurationInstance;
        bVar2.b = ieVar;
        return bVar2;
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        Lifecycle lifecycle = getLifecycle();
        if (lifecycle instanceof yd) {
            ((yd) lifecycle).d(Lifecycle.State.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.b(bundle);
    }

    public ComponentActivity(int i) {
        this();
        this.mContentLayoutId = i;
    }
}
