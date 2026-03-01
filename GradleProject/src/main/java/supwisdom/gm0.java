package supwisdom;

import android.graphics.Outline;

/* JADX INFO: compiled from: CircularBorderDrawableLollipop.java */
/* JADX INFO: loaded from: classes.dex */
public class gm0 extends fm0 {
    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        copyBounds(this.b);
        outline.setOval(this.b);
    }
}
