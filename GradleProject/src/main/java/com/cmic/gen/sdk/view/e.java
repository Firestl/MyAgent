package com.cmic.gen.sdk.view;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cmic.gen.sdk.auth.GenAuthnHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ArrayList<WeakReference<Activity>> f1771a;

    public static int a(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.widthPixels;
    }

    public static int a(Context context, float f) {
        if (f < 0.0f) {
            return (int) f;
        }
        try {
            return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
        } catch (Exception unused) {
            return (int) f;
        }
    }

    public static SpannableString a(final Context context, String str, String str2, final d dVar, final ArrayList<d> arrayList, ArrayList<String> arrayList2) {
        int iIndexOf;
        SpannableString spannableString = new SpannableString(str);
        try {
            ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.cmic.gen.sdk.view.e.1
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    d dVar2 = dVar;
                    if (dVar2 == null || dVar2.isShowing()) {
                        return;
                    }
                    dVar.show();
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setUnderlineText(false);
                    try {
                        textPaint.setColor(GenAuthnHelper.getInstance(context).getAuthThemeConfig().getClauseColor());
                    } catch (Exception unused) {
                        textPaint.setColor(-16007674);
                    }
                }
            };
            ClickableSpan clickableSpan2 = arrayList.size() >= 1 ? new ClickableSpan() { // from class: com.cmic.gen.sdk.view.e.2
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    if (arrayList.get(0) == null || ((d) arrayList.get(0)).isShowing()) {
                        return;
                    }
                    ((d) arrayList.get(0)).show();
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setUnderlineText(false);
                    try {
                        textPaint.setColor(GenAuthnHelper.getInstance(context).getAuthThemeConfig().getClauseColor());
                    } catch (Exception unused) {
                        textPaint.setColor(-16007674);
                    }
                }
            } : null;
            ClickableSpan clickableSpan3 = arrayList.size() >= 2 ? new ClickableSpan() { // from class: com.cmic.gen.sdk.view.e.3
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    if (arrayList.get(1) == null || ((d) arrayList.get(1)).isShowing()) {
                        return;
                    }
                    ((d) arrayList.get(1)).show();
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setUnderlineText(false);
                    try {
                        textPaint.setColor(GenAuthnHelper.getInstance(context).getAuthThemeConfig().getClauseColor());
                    } catch (Exception unused) {
                        textPaint.setColor(-16007674);
                    }
                }
            } : null;
            ClickableSpan clickableSpan4 = arrayList.size() >= 3 ? new ClickableSpan() { // from class: com.cmic.gen.sdk.view.e.4
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    if (arrayList.get(2) == null || ((d) arrayList.get(2)).isShowing()) {
                        return;
                    }
                    ((d) arrayList.get(2)).show();
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setUnderlineText(false);
                    try {
                        textPaint.setColor(GenAuthnHelper.getInstance(context).getAuthThemeConfig().getClauseColor());
                    } catch (Exception unused) {
                        textPaint.setColor(-16007674);
                    }
                }
            } : null;
            ClickableSpan clickableSpan5 = arrayList.size() == 4 ? new ClickableSpan() { // from class: com.cmic.gen.sdk.view.e.5
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    if (arrayList.get(3) == null || ((d) arrayList.get(3)).isShowing()) {
                        return;
                    }
                    ((d) arrayList.get(3)).show();
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setUnderlineText(false);
                    try {
                        textPaint.setColor(GenAuthnHelper.getInstance(context).getAuthThemeConfig().getClauseColor());
                    } catch (Exception unused) {
                        textPaint.setColor(-16007674);
                    }
                }
            } : null;
            GenAuthnHelper.getInstance(context).getAuthThemeConfig();
            int iIndexOf2 = str.indexOf(str2);
            spannableString.setSpan(clickableSpan, iIndexOf2, str2.length() + iIndexOf2, 34);
            if (arrayList.size() >= 1) {
                String str3 = arrayList2.get(0);
                iIndexOf = str.indexOf(str3);
                spannableString.setSpan(clickableSpan2, iIndexOf, str3.length() + iIndexOf, 34);
            } else {
                iIndexOf = 0;
            }
            if (arrayList.size() >= 2) {
                int length = iIndexOf + arrayList2.get(0).length();
                String str4 = arrayList2.get(1);
                iIndexOf = str.indexOf(str4, length);
                spannableString.setSpan(clickableSpan3, iIndexOf, str4.length() + iIndexOf, 34);
            }
            if (arrayList.size() >= 3) {
                int length2 = arrayList2.get(1).length() + iIndexOf;
                String str5 = arrayList2.get(2);
                int iIndexOf3 = str.indexOf(str5, length2);
                spannableString.setSpan(clickableSpan4, iIndexOf3, str5.length() + iIndexOf3, 34);
            }
            if (arrayList.size() == 4) {
                int length3 = iIndexOf + arrayList2.get(2).length();
                String str6 = arrayList2.get(3);
                int iIndexOf4 = str.indexOf(str6, length3);
                spannableString.setSpan(clickableSpan5, iIndexOf4, str6.length() + iIndexOf4, 34);
            }
            return spannableString;
        } catch (Exception e2) {
            e2.printStackTrace();
            return spannableString;
        }
    }

    public static RelativeLayout a(Context context, View view, int i, int i2, String str, View.OnClickListener onClickListener) {
        GenAuthThemeConfig authThemeConfig = GenAuthnHelper.getInstance(context).getAuthThemeConfig();
        RelativeLayout relativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, view != null ? -2 : a(context, 49.0f));
        layoutParams.addRule(10, -1);
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.setId(i);
        TextView textView = new TextView(context);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13, -1);
        textView.setLayoutParams(layoutParams2);
        textView.setTextColor(authThemeConfig.getNavTextColor());
        textView.setTextSize(2, authThemeConfig.getNavTextSize());
        textView.setText(str);
        if (view != null) {
            relativeLayout.addView(view);
            relativeLayout.addView(textView);
            return relativeLayout;
        }
        relativeLayout.addView(textView);
        ImageButton imageButton = new ImageButton(context);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(a(context, authThemeConfig.getNavReturnImgWidth()), a(context, authThemeConfig.getNavReturnImgHeight()));
        layoutParams3.addRule(9, -1);
        layoutParams3.addRule(15, -1);
        layoutParams3.setMargins(a(context, 12.0f), 0, 0, 0);
        imageButton.setLayoutParams(layoutParams3);
        imageButton.setId(i2);
        imageButton.setOnClickListener(onClickListener);
        imageButton.setBackgroundColor(0);
        relativeLayout.addView(imageButton);
        try {
            relativeLayout.setBackgroundColor(GenAuthnHelper.getInstance(context).getAuthThemeConfig().getNavColor());
        } catch (Exception unused) {
            relativeLayout.setBackgroundColor(-16742704);
        }
        imageButton.setImageResource(c.b(context, "umcsdk_return_bg"));
        return relativeLayout;
    }

    public static int b(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.heightPixels;
    }
}
