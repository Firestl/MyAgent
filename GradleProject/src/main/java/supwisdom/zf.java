package supwisdom;

import android.graphics.Rect;
import android.view.ViewGroup;
import androidx.transition.Transition;

/* JADX INFO: compiled from: CircularPropagation.java */
/* JADX INFO: loaded from: classes.dex */
public class zf extends kh {
    public float b = 3.0f;

    @Override // supwisdom.rg
    public long a(ViewGroup viewGroup, Transition transition, tg tgVar, tg tgVar2) {
        int i;
        int iRound;
        int iCenterX;
        if (tgVar == null && tgVar2 == null) {
            return 0L;
        }
        if (tgVar2 == null || b(tgVar) == 0) {
            i = -1;
        } else {
            tgVar = tgVar2;
            i = 1;
        }
        int iC = c(tgVar);
        int iD = d(tgVar);
        Rect rectC = transition.c();
        if (rectC != null) {
            iCenterX = rectC.centerX();
            iRound = rectC.centerY();
        } else {
            viewGroup.getLocationOnScreen(new int[2]);
            int iRound2 = Math.round(r5[0] + (viewGroup.getWidth() / 2) + viewGroup.getTranslationX());
            iRound = Math.round(r5[1] + (viewGroup.getHeight() / 2) + viewGroup.getTranslationY());
            iCenterX = iRound2;
        }
        float fA = a(iC, iD, iCenterX, iRound) / a(0.0f, 0.0f, viewGroup.getWidth(), viewGroup.getHeight());
        long jB = transition.b();
        if (jB < 0) {
            jB = 300;
        }
        return Math.round(((jB * ((long) i)) / this.b) * fA);
    }

    public static float a(float f, float f2, float f3, float f4) {
        float f5 = f3 - f;
        float f6 = f4 - f2;
        return (float) Math.sqrt((f5 * f5) + (f6 * f6));
    }
}
