package supwisdom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class av extends View {
    public int alpha;
    public int curProgress;
    public float h;
    public Paint p;
    public int w;
    public int webviewProgress;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            av avVar = av.this;
            int i = avVar.alpha - 5;
            avVar.alpha = i;
            if (i > 0) {
                avVar.postDelayed(this, 5L);
            } else {
                ViewGroup viewGroup = (ViewGroup) avVar.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(av.this);
                }
            }
            av.this.invalidate();
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            av avVar = av.this;
            int i = avVar.webviewProgress;
            int i2 = avVar.curProgress;
            int i3 = (i - i2) / 10;
            int i4 = i2 + (i3 <= 10 ? i3 < 1 ? 1 : i3 : 10);
            avVar.curProgress = i4;
            if (i > i4) {
                avVar.postDelayed(this, 5L);
            } else if (i >= avVar.w) {
                avVar.fadeDisappear();
            }
            av.this.invalidate();
        }
    }

    public av(Context context) {
        super(context);
        this.p = new Paint();
        this.curProgress = 0;
        this.webviewProgress = 0;
        this.alpha = 255;
        this.w = context.getResources().getDisplayMetrics().widthPixels;
    }

    public void fadeDisappear() {
        postDelayed(new a(), 50L);
    }

    public void finishProgress() {
        updateProgress(100);
    }

    public float getHeightInt() {
        return this.h;
    }

    public boolean isFinish() {
        return this.curProgress >= this.w;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.p.setAlpha(this.alpha);
        canvas.drawRect(0.0f, 0.0f, this.curProgress, this.h, this.p);
    }

    public void setAlphaInt(int i) {
        this.alpha = i;
    }

    public void setColorInt(int i) {
        this.p.setColor(Color.argb(this.alpha, Color.red(i), Color.green(i), Color.blue(i)));
    }

    public void setCurProgress(int i) {
        this.curProgress = i;
    }

    public void setHeightInt(int i) {
        this.h = i;
    }

    public void setMaxProgress(int i) {
        this.w = i;
    }

    public void setWebviewProgress(int i) {
        this.webviewProgress = i;
    }

    public void updateProgress(int i) {
        int i2 = (this.w * i) / 100;
        if (this.curProgress >= this.webviewProgress) {
            postDelayed(new b(), 5L);
        }
        this.webviewProgress = i2;
    }
}
