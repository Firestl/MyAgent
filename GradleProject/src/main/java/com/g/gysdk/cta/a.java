package com.g.gysdk.cta;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.g.gysdk.a.aj;
import com.g.gysdk.a.am;
import com.g.gysdk.a.ap;
import com.g.gysdk.cta.ELoginThemeConfig;
import com.g.gysdk.view.ELoginWebActivity;
import com.xiaomi.mipush.sdk.Constants;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class a {
    public static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int a(Context context, String str) {
        return a(context, "drawable", str);
    }

    public static int a(Context context, String str, String str2) {
        int identifier = context.getResources().getIdentifier(str2, str, context.getPackageName());
        if (identifier == 0) {
            aj.c("get " + str + Constants.COLON_SEPARATOR + str2 + " failed, please check andResGuard whiteList");
        }
        return identifier;
    }

    public static SpannableString a(final String str, final String str2, final ELoginThemeConfig.Builder builder, final Context context) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ClickableSpan() { // from class: com.g.gysdk.cta.a.1
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                b.a().onPrivacyClick(str, str2);
                if (builder.isUseNormalWebActivity()) {
                    try {
                        ELoginWebActivity.start(context, str2, str);
                    } catch (Throwable th) {
                        ap.b(th.toString());
                        ap.a(th);
                    }
                }
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                try {
                    textPaint.setColor(builder.getClauseColor());
                    textPaint.setUnderlineText(false);
                    textPaint.setTypeface(builder.getPrivacyClauseTextViewTypeface());
                } catch (Throwable th) {
                    ap.a(th);
                }
            }
        }, 0, str.length(), 33);
        return spannableString;
    }

    public static void a(Activity activity, int i, int i2, int i3, int i4, boolean z) {
        try {
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = a(activity.getApplicationContext(), i);
            attributes.height = a(activity.getApplicationContext(), i2);
            attributes.x = i3;
            if (z) {
                attributes.gravity = 80;
            } else {
                attributes.y = i4;
            }
            window.setAttributes(attributes);
        } catch (Throwable th) {
            ap.b("设置主题失败:" + am.a(th));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(android.widget.TextView r9, java.lang.String r10, java.lang.String r11, com.g.gysdk.cta.ELoginThemeConfig.Builder r12, android.content.Context r13) {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.g.gysdk.cta.a.a(android.widget.TextView, java.lang.String, java.lang.String, com.g.gysdk.cta.ELoginThemeConfig$Builder, android.content.Context):void");
    }

    public static void a(Integer num, Integer num2, Activity activity) {
        try {
            if (Build.VERSION.SDK_INT < 19) {
                return;
            }
            if (num == null && num2 == null) {
                return;
            }
            Window window = activity.getWindow();
            if (Build.VERSION.SDK_INT < 21) {
                if (num != null && num.intValue() == 0) {
                    window.addFlags(67108864);
                }
                if (num2 == null || num2.intValue() != 0) {
                    return;
                }
                window.addFlags(134217728);
                return;
            }
            window.clearFlags(67108864);
            window.clearFlags(134217728);
            window.addFlags(Integer.MIN_VALUE);
            int systemUiVisibility = window.getDecorView().getSystemUiVisibility() | 256;
            if (num.intValue() == 0) {
                systemUiVisibility |= 1024;
            }
            if (num2.intValue() == 0) {
                systemUiVisibility |= 512;
            }
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
            if (num != null) {
                window.setStatusBarColor(num.intValue());
            }
            if (num2 != null) {
                window.setNavigationBarColor(num2.intValue());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(boolean z, Activity activity) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                Window window = activity.getWindow();
                int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
                window.getDecorView().setSystemUiVisibility(z ? systemUiVisibility | 8192 : systemUiVisibility & (-8193));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
