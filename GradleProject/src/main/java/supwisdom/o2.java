package supwisdom;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.R;
import supwisdom.f3;

/* JADX INFO: compiled from: AppCompatDrawableManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class o2 {
    public static final PorterDuff.Mode b = PorterDuff.Mode.SRC_IN;
    public static o2 c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public f3 f8601a;

    /* JADX INFO: compiled from: AppCompatDrawableManager.java */
    public class a implements f3.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int[] f8602a = {R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha};
        public final int[] b = {R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha};
        public final int[] c = {R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl_dark, R.drawable.abc_text_select_handle_middle_mtrl_dark, R.drawable.abc_text_select_handle_right_mtrl_dark, R.drawable.abc_text_select_handle_left_mtrl_light, R.drawable.abc_text_select_handle_middle_mtrl_light, R.drawable.abc_text_select_handle_right_mtrl_light};
        public final int[] d = {R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult};

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int[] f8603e = {R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material};
        public final int[] f = {R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material, R.drawable.abc_btn_check_material_anim, R.drawable.abc_btn_radio_material_anim};

        public final ColorStateList a(Context context) {
            return b(context, 0);
        }

        public final ColorStateList b(Context context) {
            return b(context, k3.b(context, R.attr.colorAccent));
        }

        public final ColorStateList c(Context context) {
            return b(context, k3.b(context, R.attr.colorButtonNormal));
        }

        public final ColorStateList d(Context context) {
            int[][] iArr = new int[3][];
            int[] iArr2 = new int[3];
            ColorStateList colorStateListC = k3.c(context, R.attr.colorSwitchThumbNormal);
            if (colorStateListC == null || !colorStateListC.isStateful()) {
                iArr[0] = k3.b;
                iArr2[0] = k3.a(context, R.attr.colorSwitchThumbNormal);
                iArr[1] = k3.f8116e;
                iArr2[1] = k3.b(context, R.attr.colorControlActivated);
                iArr[2] = k3.f;
                iArr2[2] = k3.b(context, R.attr.colorSwitchThumbNormal);
            } else {
                iArr[0] = k3.b;
                iArr2[0] = colorStateListC.getColorForState(iArr[0], 0);
                iArr[1] = k3.f8116e;
                iArr2[1] = k3.b(context, R.attr.colorControlActivated);
                iArr[2] = k3.f;
                iArr2[2] = colorStateListC.getDefaultColor();
            }
            return new ColorStateList(iArr, iArr2);
        }

        @Override // supwisdom.f3.e
        public Drawable a(f3 f3Var, Context context, int i) {
            if (i == R.drawable.abc_cab_background_top_material) {
                return new LayerDrawable(new Drawable[]{f3Var.b(context, R.drawable.abc_cab_background_internal_bg), f3Var.b(context, R.drawable.abc_cab_background_top_mtrl_alpha)});
            }
            return null;
        }

        public final ColorStateList b(Context context, int i) {
            int iB = k3.b(context, R.attr.colorControlHighlight);
            return new ColorStateList(new int[][]{k3.b, k3.d, k3.c, k3.f}, new int[]{k3.a(context, R.attr.colorButtonNormal), n8.b(iB, i), n8.b(iB, i), i});
        }

        public final void a(Drawable drawable, int i, PorterDuff.Mode mode) {
            if (y2.a(drawable)) {
                drawable = drawable.mutate();
            }
            if (mode == null) {
                mode = o2.b;
            }
            drawable.setColorFilter(o2.a(i, mode));
        }

        public final boolean a(int[] iArr, int i) {
            for (int i2 : iArr) {
                if (i2 == i) {
                    return true;
                }
            }
            return false;
        }

        @Override // supwisdom.f3.e
        public ColorStateList a(Context context, int i) {
            if (i == R.drawable.abc_edit_text_material) {
                return b1.b(context, R.color.abc_tint_edittext);
            }
            if (i == R.drawable.abc_switch_track_mtrl_alpha) {
                return b1.b(context, R.color.abc_tint_switch_track);
            }
            if (i == R.drawable.abc_switch_thumb_material) {
                return d(context);
            }
            if (i == R.drawable.abc_btn_default_mtrl_shape) {
                return c(context);
            }
            if (i == R.drawable.abc_btn_borderless_material) {
                return a(context);
            }
            if (i == R.drawable.abc_btn_colored_material) {
                return b(context);
            }
            if (i != R.drawable.abc_spinner_mtrl_am_alpha && i != R.drawable.abc_spinner_textfield_background_material) {
                if (a(this.b, i)) {
                    return k3.c(context, R.attr.colorControlNormal);
                }
                if (a(this.f8603e, i)) {
                    return b1.b(context, R.color.abc_tint_default);
                }
                if (a(this.f, i)) {
                    return b1.b(context, R.color.abc_tint_btn_checkable);
                }
                if (i == R.drawable.abc_seekbar_thumb_material) {
                    return b1.b(context, R.color.abc_tint_seek_thumb);
                }
                return null;
            }
            return b1.b(context, R.color.abc_tint_spinner);
        }

        @Override // supwisdom.f3.e
        public boolean b(Context context, int i, Drawable drawable) {
            if (i == R.drawable.abc_seekbar_track_material) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                a(layerDrawable.findDrawableByLayerId(android.R.id.background), k3.b(context, R.attr.colorControlNormal), o2.b);
                a(layerDrawable.findDrawableByLayerId(android.R.id.secondaryProgress), k3.b(context, R.attr.colorControlNormal), o2.b);
                a(layerDrawable.findDrawableByLayerId(android.R.id.progress), k3.b(context, R.attr.colorControlActivated), o2.b);
                return true;
            }
            if (i != R.drawable.abc_ratingbar_material && i != R.drawable.abc_ratingbar_indicator_material && i != R.drawable.abc_ratingbar_small_material) {
                return false;
            }
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            a(layerDrawable2.findDrawableByLayerId(android.R.id.background), k3.a(context, R.attr.colorControlNormal), o2.b);
            a(layerDrawable2.findDrawableByLayerId(android.R.id.secondaryProgress), k3.b(context, R.attr.colorControlActivated), o2.b);
            a(layerDrawable2.findDrawableByLayerId(android.R.id.progress), k3.b(context, R.attr.colorControlActivated), o2.b);
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0061 A[RETURN] */
        @Override // supwisdom.f3.e
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean a(android.content.Context r7, int r8, android.graphics.drawable.Drawable r9) {
            /*
                r6 = this;
                android.graphics.PorterDuff$Mode r0 = supwisdom.o2.a()
                int[] r1 = r6.f8602a
                boolean r1 = r6.a(r1, r8)
                r2 = 16842801(0x1010031, float:2.3693695E-38)
                r3 = -1
                r4 = 0
                r5 = 1
                if (r1 == 0) goto L17
                int r2 = androidx.appcompat.R.attr.colorControlNormal
            L14:
                r8 = -1
            L15:
                r1 = 1
                goto L44
            L17:
                int[] r1 = r6.c
                boolean r1 = r6.a(r1, r8)
                if (r1 == 0) goto L22
                int r2 = androidx.appcompat.R.attr.colorControlActivated
                goto L14
            L22:
                int[] r1 = r6.d
                boolean r1 = r6.a(r1, r8)
                if (r1 == 0) goto L2d
                android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
                goto L14
            L2d:
                int r1 = androidx.appcompat.R.drawable.abc_list_divider_mtrl_alpha
                if (r8 != r1) goto L3c
                r2 = 16842800(0x1010030, float:2.3693693E-38)
                r8 = 1109603123(0x42233333, float:40.8)
                int r8 = java.lang.Math.round(r8)
                goto L15
            L3c:
                int r1 = androidx.appcompat.R.drawable.abc_dialog_material_background
                if (r8 != r1) goto L41
                goto L14
            L41:
                r8 = -1
                r1 = 0
                r2 = 0
            L44:
                if (r1 == 0) goto L61
                boolean r1 = supwisdom.y2.a(r9)
                if (r1 == 0) goto L50
                android.graphics.drawable.Drawable r9 = r9.mutate()
            L50:
                int r7 = supwisdom.k3.b(r7, r2)
                android.graphics.PorterDuffColorFilter r7 = supwisdom.o2.a(r7, r0)
                r9.setColorFilter(r7)
                if (r8 == r3) goto L60
                r9.setAlpha(r8)
            L60:
                return r5
            L61:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.o2.a.a(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
        }

        @Override // supwisdom.f3.e
        public PorterDuff.Mode a(int i) {
            if (i == R.drawable.abc_switch_thumb_material) {
                return PorterDuff.Mode.MULTIPLY;
            }
            return null;
        }
    }

    public static synchronized o2 b() {
        if (c == null) {
            c();
        }
        return c;
    }

    public static synchronized void c() {
        if (c == null) {
            o2 o2Var = new o2();
            c = o2Var;
            o2Var.f8601a = f3.a();
            c.f8601a.a(new a());
        }
    }

    public synchronized Drawable a(Context context, int i) {
        return this.f8601a.b(context, i);
    }

    public synchronized Drawable a(Context context, int i, boolean z) {
        return this.f8601a.a(context, i, z);
    }

    public synchronized void a(Context context) {
        this.f8601a.b(context);
    }

    public synchronized ColorStateList b(Context context, int i) {
        return this.f8601a.c(context, i);
    }

    public static void a(Drawable drawable, n3 n3Var, int[] iArr) {
        f3.a(drawable, n3Var, iArr);
    }

    public static synchronized PorterDuffColorFilter a(int i, PorterDuff.Mode mode) {
        return f3.a(i, mode);
    }
}
