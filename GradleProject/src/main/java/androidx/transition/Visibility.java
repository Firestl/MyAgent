package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;
import supwisdom.fh;
import supwisdom.l8;
import supwisdom.og;
import supwisdom.sg;
import supwisdom.tg;
import supwisdom.yf;
import supwisdom.yg;
import supwisdom.zg;

/* JADX INFO: loaded from: classes.dex */
public abstract class Visibility extends Transition {
    public static final String[] K = {"android:visibility:visibility", "android:visibility:parent"};
    public int J;

    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ yg f1441a;
        public final /* synthetic */ View b;

        public a(yg ygVar, View view) {
            this.f1441a = ygVar;
            this.b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1441a.b(this.b);
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1444a;
        public boolean b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ViewGroup f1445e;
        public ViewGroup f;
    }

    public Visibility() {
        this.J = 3;
    }

    public Animator a(ViewGroup viewGroup, View view, tg tgVar, tg tgVar2) {
        return null;
    }

    @Override // androidx.transition.Transition
    public void a(tg tgVar) {
        d(tgVar);
    }

    public Animator b(ViewGroup viewGroup, View view, tg tgVar, tg tgVar2) {
        return null;
    }

    public void b(int i) {
        if ((i & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.J = i;
    }

    @Override // androidx.transition.Transition
    public void c(tg tgVar) {
        d(tgVar);
    }

    public final void d(tg tgVar) {
        tgVar.f9283a.put("android:visibility:visibility", Integer.valueOf(tgVar.b.getVisibility()));
        tgVar.f9283a.put("android:visibility:parent", tgVar.b.getParent());
        int[] iArr = new int[2];
        tgVar.b.getLocationOnScreen(iArr);
        tgVar.f9283a.put("android:visibility:screenLocation", iArr);
    }

    @Override // androidx.transition.Transition
    public String[] n() {
        return K;
    }

    public int r() {
        return this.J;
    }

    @Override // androidx.transition.Transition
    public Animator a(ViewGroup viewGroup, tg tgVar, tg tgVar2) {
        c cVarB = b(tgVar, tgVar2);
        if (!cVarB.f1444a) {
            return null;
        }
        if (cVarB.f1445e == null && cVarB.f == null) {
            return null;
        }
        return cVarB.b ? a(viewGroup, tgVar, cVarB.c, tgVar2, cVarB.d) : b(viewGroup, tgVar, cVarB.c, tgVar2, cVarB.d);
    }

    public Visibility(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.J = 3;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, og.c);
        int iB = l8.b(typedArrayObtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionVisibilityMode", 0, 0);
        typedArrayObtainStyledAttributes.recycle();
        if (iB != 0) {
            b(iB);
        }
    }

    public final c b(tg tgVar, tg tgVar2) {
        c cVar = new c();
        cVar.f1444a = false;
        cVar.b = false;
        if (tgVar != null && tgVar.f9283a.containsKey("android:visibility:visibility")) {
            cVar.c = ((Integer) tgVar.f9283a.get("android:visibility:visibility")).intValue();
            cVar.f1445e = (ViewGroup) tgVar.f9283a.get("android:visibility:parent");
        } else {
            cVar.c = -1;
            cVar.f1445e = null;
        }
        if (tgVar2 != null && tgVar2.f9283a.containsKey("android:visibility:visibility")) {
            cVar.d = ((Integer) tgVar2.f9283a.get("android:visibility:visibility")).intValue();
            cVar.f = (ViewGroup) tgVar2.f9283a.get("android:visibility:parent");
        } else {
            cVar.d = -1;
            cVar.f = null;
        }
        if (tgVar != null && tgVar2 != null) {
            if (cVar.c == cVar.d && cVar.f1445e == cVar.f) {
                return cVar;
            }
            int i = cVar.c;
            int i2 = cVar.d;
            if (i != i2) {
                if (i == 0) {
                    cVar.b = false;
                    cVar.f1444a = true;
                } else if (i2 == 0) {
                    cVar.b = true;
                    cVar.f1444a = true;
                }
            } else if (cVar.f == null) {
                cVar.b = false;
                cVar.f1444a = true;
            } else if (cVar.f1445e == null) {
                cVar.b = true;
                cVar.f1444a = true;
            }
        } else if (tgVar == null && cVar.d == 0) {
            cVar.b = true;
            cVar.f1444a = true;
        } else if (tgVar2 == null && cVar.c == 0) {
            cVar.b = false;
            cVar.f1444a = true;
        }
        return cVar;
    }

    public static class b extends AnimatorListenerAdapter implements Transition.f, yf.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f1442a;
        public final int b;
        public final ViewGroup c;
        public final boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1443e;
        public boolean f = false;

        public b(View view, int i, boolean z) {
            this.f1442a = view;
            this.b = i;
            this.c = (ViewGroup) view.getParent();
            this.d = z;
            a(true);
        }

        public final void a() {
            if (!this.f) {
                fh.a(this.f1442a, this.b);
                ViewGroup viewGroup = this.c;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            a(false);
        }

        @Override // androidx.transition.Transition.f
        public void a(Transition transition) {
        }

        @Override // androidx.transition.Transition.f
        public void b(Transition transition) {
            a(false);
        }

        @Override // androidx.transition.Transition.f
        public void c(Transition transition) {
            a();
            transition.b(this);
        }

        @Override // androidx.transition.Transition.f
        public void d(Transition transition) {
            a(true);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            a();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, supwisdom.yf.a
        public void onAnimationPause(Animator animator) {
            if (this.f) {
                return;
            }
            fh.a(this.f1442a, this.b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, supwisdom.yf.a
        public void onAnimationResume(Animator animator) {
            if (this.f) {
                return;
            }
            fh.a(this.f1442a, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        public final void a(boolean z) {
            ViewGroup viewGroup;
            if (!this.d || this.f1443e == z || (viewGroup = this.c) == null) {
                return;
            }
            this.f1443e = z;
            zg.a(viewGroup, z);
        }
    }

    public Animator a(ViewGroup viewGroup, tg tgVar, int i, tg tgVar2, int i2) {
        if ((this.J & 1) != 1 || tgVar2 == null) {
            return null;
        }
        if (tgVar == null) {
            View view = (View) tgVar2.b.getParent();
            if (b(b(view, false), c(view, false)).f1444a) {
                return null;
            }
        }
        return a(viewGroup, tgVar2.b, tgVar, tgVar2);
    }

    @Override // androidx.transition.Transition
    public boolean a(tg tgVar, tg tgVar2) {
        if (tgVar == null && tgVar2 == null) {
            return false;
        }
        if (tgVar != null && tgVar2 != null && tgVar2.f9283a.containsKey("android:visibility:visibility") != tgVar.f9283a.containsKey("android:visibility:visibility")) {
            return false;
        }
        c cVarB = b(tgVar, tgVar2);
        if (cVarB.f1444a) {
            return cVarB.c == 0 || cVarB.d == 0;
        }
        return false;
    }

    public Animator b(ViewGroup viewGroup, tg tgVar, int i, tg tgVar2, int i2) {
        int id;
        if ((this.J & 2) != 2) {
            return null;
        }
        View viewA = tgVar != null ? tgVar.b : null;
        View view = tgVar2 != null ? tgVar2.b : null;
        if (view == null || view.getParent() == null) {
            if (view != null) {
                viewA = view;
            } else {
                if (viewA != null) {
                    if (viewA.getParent() != null) {
                        if (viewA.getParent() instanceof View) {
                            View view2 = (View) viewA.getParent();
                            if (!b(c(view2, true), b(view2, true)).f1444a) {
                                viewA = sg.a(viewGroup, viewA, view2);
                            } else if (view2.getParent() != null || (id = view2.getId()) == -1 || viewGroup.findViewById(id) == null || !this.v) {
                                viewA = null;
                            }
                        }
                    }
                }
                viewA = null;
                view = null;
            }
            view = null;
        } else if (i2 == 4 || viewA == view) {
            viewA = null;
        } else {
            if (!this.v) {
                viewA = sg.a(viewGroup, viewA, (View) viewA.getParent());
            }
            view = null;
        }
        if (viewA == null || tgVar == null) {
            if (view == null) {
                return null;
            }
            int visibility = view.getVisibility();
            fh.a(view, 0);
            Animator animatorB = b(viewGroup, view, tgVar, tgVar2);
            if (animatorB != null) {
                b bVar = new b(view, i2, true);
                animatorB.addListener(bVar);
                yf.a(animatorB, bVar);
                a(bVar);
            } else {
                fh.a(view, visibility);
            }
            return animatorB;
        }
        int[] iArr = (int[]) tgVar.f9283a.get("android:visibility:screenLocation");
        int i3 = iArr[0];
        int i4 = iArr[1];
        int[] iArr2 = new int[2];
        viewGroup.getLocationOnScreen(iArr2);
        viewA.offsetLeftAndRight((i3 - iArr2[0]) - viewA.getLeft());
        viewA.offsetTopAndBottom((i4 - iArr2[1]) - viewA.getTop());
        yg ygVarA = zg.a(viewGroup);
        ygVarA.a(viewA);
        Animator animatorB2 = b(viewGroup, viewA, tgVar, tgVar2);
        if (animatorB2 == null) {
            ygVarA.b(viewA);
        } else {
            animatorB2.addListener(new a(ygVarA, viewA));
        }
        return animatorB2;
    }
}
