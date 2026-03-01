package com.bumptech.glide.load.resource.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import supwisdom.b1;
import supwisdom.k1;
import supwisdom.k8;
import supwisdom.y7;

/* JADX INFO: loaded from: classes.dex */
public final class DrawableDecoderCompat {
    public static volatile boolean shouldCallAppCompatResources = true;

    public static Drawable getDrawable(Context context, Context context2, int i) {
        return getDrawable(context, context2, i, null);
    }

    public static Drawable loadDrawableV4(Context context, int i, Resources.Theme theme) {
        return k8.b(context.getResources(), i, theme);
    }

    public static Drawable loadDrawableV7(Context context, int i, Resources.Theme theme) {
        if (theme != null) {
            context = new k1(context, theme);
        }
        return b1.c(context, i);
    }

    public static Drawable getDrawable(Context context, int i, Resources.Theme theme) {
        return getDrawable(context, context, i, theme);
    }

    public static Drawable getDrawable(Context context, Context context2, int i, Resources.Theme theme) {
        try {
            if (shouldCallAppCompatResources) {
                return loadDrawableV7(context2, i, theme);
            }
        } catch (Resources.NotFoundException unused) {
        } catch (IllegalStateException e2) {
            if (!context.getPackageName().equals(context2.getPackageName())) {
                return y7.c(context2, i);
            }
            throw e2;
        } catch (NoClassDefFoundError unused2) {
            shouldCallAppCompatResources = false;
        }
        if (theme == null) {
            theme = context2.getTheme();
        }
        return loadDrawableV4(context2, i, theme);
    }
}
