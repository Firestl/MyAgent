package supwisdom;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import supwisdom.i4;

/* JADX INFO: compiled from: CardViewApi17Impl.java */
/* JADX INFO: loaded from: classes.dex */
public class c4 extends e4 {

    /* JADX INFO: compiled from: CardViewApi17Impl.java */
    public class a implements i4.a {
        public a(c4 c4Var) {
        }

        @Override // supwisdom.i4.a
        public void a(Canvas canvas, RectF rectF, float f, Paint paint) {
            canvas.drawRoundRect(rectF, f, f, paint);
        }
    }

    @Override // supwisdom.e4, supwisdom.g4
    public void a() {
        i4.r = new a(this);
    }
}
