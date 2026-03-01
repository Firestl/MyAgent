package supwisdom;

import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.util.Log;

/* JADX INFO: compiled from: WebvttCue.java */
/* JADX INFO: loaded from: classes.dex */
public final class u60 extends y50 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f9374e;
    public final long f;

    /* JADX INFO: compiled from: WebvttCue.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9375a;

        static {
            int[] iArr = new int[Layout.Alignment.values().length];
            f9375a = iArr;
            try {
                iArr[Layout.Alignment.ALIGN_NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9375a[Layout.Alignment.ALIGN_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9375a[Layout.Alignment.ALIGN_OPPOSITE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: compiled from: WebvttCue.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f9376a;
        public long b;
        public SpannableStringBuilder c;
        public Layout.Alignment d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f9377e;
        public int f;
        public int g;
        public float h;
        public int i;
        public float j;

        public b() {
            a();
        }

        public void a() {
            this.f9376a = 0L;
            this.b = 0L;
            this.c = null;
            this.d = null;
            this.f9377e = Float.MIN_VALUE;
            this.f = Integer.MIN_VALUE;
            this.g = Integer.MIN_VALUE;
            this.h = Float.MIN_VALUE;
            this.i = Integer.MIN_VALUE;
            this.j = Float.MIN_VALUE;
        }

        public u60 b() {
            if (this.h != Float.MIN_VALUE && this.i == Integer.MIN_VALUE) {
                c();
            }
            return new u60(this.f9376a, this.b, this.c, this.d, this.f9377e, this.f, this.g, this.h, this.i, this.j);
        }

        public b c(int i) {
            this.i = i;
            return this;
        }

        public b c(float f) {
            this.j = f;
            return this;
        }

        public final b c() {
            Layout.Alignment alignment = this.d;
            if (alignment == null) {
                this.i = Integer.MIN_VALUE;
            } else {
                int i = a.f9375a[alignment.ordinal()];
                if (i == 1) {
                    this.i = 0;
                } else if (i == 2) {
                    this.i = 1;
                } else if (i != 3) {
                    Log.w("WebvttCueBuilder", "Unrecognized alignment: " + this.d);
                    this.i = 0;
                } else {
                    this.i = 2;
                }
            }
            return this;
        }

        public b b(long j) {
            this.b = j;
            return this;
        }

        public b b(int i) {
            this.g = i;
            return this;
        }

        public b b(float f) {
            this.h = f;
            return this;
        }

        public b a(long j) {
            this.f9376a = j;
            return this;
        }

        public b a(SpannableStringBuilder spannableStringBuilder) {
            this.c = spannableStringBuilder;
            return this;
        }

        public b a(Layout.Alignment alignment) {
            this.d = alignment;
            return this;
        }

        public b a(float f) {
            this.f9377e = f;
            return this;
        }

        public b a(int i) {
            this.f = i;
            return this;
        }
    }

    public u60(CharSequence charSequence) {
        this(0L, 0L, charSequence);
    }

    public boolean a() {
        return this.c == Float.MIN_VALUE && this.d == Float.MIN_VALUE;
    }

    public u60(long j, long j2, CharSequence charSequence) {
        this(j, j2, charSequence, null, Float.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE);
    }

    public u60(long j, long j2, CharSequence charSequence, Layout.Alignment alignment, float f, int i, int i2, float f2, int i3, float f3) {
        super(charSequence, alignment, f, i, i2, f2, i3, f3);
        this.f9374e = j;
        this.f = j2;
    }
}
