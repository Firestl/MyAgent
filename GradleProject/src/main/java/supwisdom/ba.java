package supwisdom;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: PrecomputedTextCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class ba implements Spannable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Spannable f7040a;
    public final a b;
    public final PrecomputedText c;

    /* JADX INFO: compiled from: PrecomputedTextCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextPaint f7041a;
        public final TextDirectionHeuristic b;
        public final int c;
        public final int d;

        /* JADX INFO: renamed from: supwisdom.ba$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PrecomputedTextCompat.java */
        public static class C0210a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final TextPaint f7042a;
            public TextDirectionHeuristic b;
            public int c;
            public int d;

            public C0210a(TextPaint textPaint) {
                this.f7042a = textPaint;
                if (Build.VERSION.SDK_INT >= 23) {
                    this.c = 1;
                    this.d = 1;
                } else {
                    this.d = 0;
                    this.c = 0;
                }
                if (Build.VERSION.SDK_INT >= 18) {
                    this.b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.b = null;
                }
            }

            public C0210a a(int i) {
                this.c = i;
                return this;
            }

            public C0210a b(int i) {
                this.d = i;
                return this;
            }

            public C0210a a(TextDirectionHeuristic textDirectionHeuristic) {
                this.b = textDirectionHeuristic;
                return this;
            }

            public a a() {
                return new a(this.f7042a, this.b, this.c, this.d);
            }
        }

        @SuppressLint({"NewApi"})
        public a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            if (Build.VERSION.SDK_INT >= 29) {
                new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(textDirectionHeuristic).build();
            }
            this.f7041a = textPaint;
            this.b = textDirectionHeuristic;
            this.c = i;
            this.d = i2;
        }

        public int a() {
            return this.c;
        }

        public int b() {
            return this.d;
        }

        public TextDirectionHeuristic c() {
            return this.b;
        }

        public TextPaint d() {
            return this.f7041a;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (a(aVar)) {
                return Build.VERSION.SDK_INT < 18 || this.b == aVar.c();
            }
            return false;
        }

        public int hashCode() {
            int i = Build.VERSION.SDK_INT;
            return i >= 24 ? ia.a(Float.valueOf(this.f7041a.getTextSize()), Float.valueOf(this.f7041a.getTextScaleX()), Float.valueOf(this.f7041a.getTextSkewX()), Float.valueOf(this.f7041a.getLetterSpacing()), Integer.valueOf(this.f7041a.getFlags()), this.f7041a.getTextLocales(), this.f7041a.getTypeface(), Boolean.valueOf(this.f7041a.isElegantTextHeight()), this.b, Integer.valueOf(this.c), Integer.valueOf(this.d)) : i >= 21 ? ia.a(Float.valueOf(this.f7041a.getTextSize()), Float.valueOf(this.f7041a.getTextScaleX()), Float.valueOf(this.f7041a.getTextSkewX()), Float.valueOf(this.f7041a.getLetterSpacing()), Integer.valueOf(this.f7041a.getFlags()), this.f7041a.getTextLocale(), this.f7041a.getTypeface(), Boolean.valueOf(this.f7041a.isElegantTextHeight()), this.b, Integer.valueOf(this.c), Integer.valueOf(this.d)) : i >= 18 ? ia.a(Float.valueOf(this.f7041a.getTextSize()), Float.valueOf(this.f7041a.getTextScaleX()), Float.valueOf(this.f7041a.getTextSkewX()), Integer.valueOf(this.f7041a.getFlags()), this.f7041a.getTextLocale(), this.f7041a.getTypeface(), this.b, Integer.valueOf(this.c), Integer.valueOf(this.d)) : i >= 17 ? ia.a(Float.valueOf(this.f7041a.getTextSize()), Float.valueOf(this.f7041a.getTextScaleX()), Float.valueOf(this.f7041a.getTextSkewX()), Integer.valueOf(this.f7041a.getFlags()), this.f7041a.getTextLocale(), this.f7041a.getTypeface(), this.b, Integer.valueOf(this.c), Integer.valueOf(this.d)) : ia.a(Float.valueOf(this.f7041a.getTextSize()), Float.valueOf(this.f7041a.getTextScaleX()), Float.valueOf(this.f7041a.getTextSkewX()), Integer.valueOf(this.f7041a.getFlags()), this.f7041a.getTypeface(), this.b, Integer.valueOf(this.c), Integer.valueOf(this.d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(Operators.BLOCK_START_STR);
            sb.append("textSize=" + this.f7041a.getTextSize());
            sb.append(", textScaleX=" + this.f7041a.getTextScaleX());
            sb.append(", textSkewX=" + this.f7041a.getTextSkewX());
            if (Build.VERSION.SDK_INT >= 21) {
                sb.append(", letterSpacing=" + this.f7041a.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f7041a.isElegantTextHeight());
            }
            int i = Build.VERSION.SDK_INT;
            if (i >= 24) {
                sb.append(", textLocale=" + this.f7041a.getTextLocales());
            } else if (i >= 17) {
                sb.append(", textLocale=" + this.f7041a.getTextLocale());
            }
            sb.append(", typeface=" + this.f7041a.getTypeface());
            if (Build.VERSION.SDK_INT >= 26) {
                sb.append(", variationSettings=" + this.f7041a.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.b);
            sb.append(", breakStrategy=" + this.c);
            sb.append(", hyphenationFrequency=" + this.d);
            sb.append(Operators.BLOCK_END_STR);
            return sb.toString();
        }

        public boolean a(a aVar) {
            if ((Build.VERSION.SDK_INT >= 23 && (this.c != aVar.a() || this.d != aVar.b())) || this.f7041a.getTextSize() != aVar.d().getTextSize() || this.f7041a.getTextScaleX() != aVar.d().getTextScaleX() || this.f7041a.getTextSkewX() != aVar.d().getTextSkewX()) {
                return false;
            }
            if ((Build.VERSION.SDK_INT >= 21 && (this.f7041a.getLetterSpacing() != aVar.d().getLetterSpacing() || !TextUtils.equals(this.f7041a.getFontFeatureSettings(), aVar.d().getFontFeatureSettings()))) || this.f7041a.getFlags() != aVar.d().getFlags()) {
                return false;
            }
            int i = Build.VERSION.SDK_INT;
            if (i >= 24) {
                if (!this.f7041a.getTextLocales().equals(aVar.d().getTextLocales())) {
                    return false;
                }
            } else if (i >= 17 && !this.f7041a.getTextLocale().equals(aVar.d().getTextLocale())) {
                return false;
            }
            return this.f7041a.getTypeface() == null ? aVar.d().getTypeface() == null : this.f7041a.getTypeface().equals(aVar.d().getTypeface());
        }

        public a(PrecomputedText.Params params) {
            this.f7041a = params.getTextPaint();
            this.b = params.getTextDirection();
            this.c = params.getBreakStrategy();
            this.d = params.getHyphenationFrequency();
            int i = Build.VERSION.SDK_INT;
        }
    }

    public a a() {
        return this.b;
    }

    public PrecomputedText b() {
        Spannable spannable = this.f7040a;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.f7040a.charAt(i);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f7040a.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f7040a.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f7040a.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.c.getSpans(i, i2, cls) : (T[]) this.f7040a.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f7040a.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.f7040a.nextSpanTransition(i, i2, cls);
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.c.removeSpan(obj);
        } else {
            this.f7040a.removeSpan(obj);
        }
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void setSpan(Object obj, int i, int i2, int i3) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.c.setSpan(obj, i, i2, i3);
        } else {
            this.f7040a.setSpan(obj, i, i2, i3);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return this.f7040a.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f7040a.toString();
    }
}
