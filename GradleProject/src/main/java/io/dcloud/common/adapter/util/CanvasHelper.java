package io.dcloud.common.adapter.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import io.dcloud.common.util.IOUtil;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class CanvasHelper {
    public static final int BASELINE = 0;
    public static final int BOTTOM = 80;
    public static final int DEVIANT = 5;
    public static final int HCENTER = 1;
    public static final int LEFT = 3;
    public static final int RIGHT = 5;
    public static final int TOP = 48;
    public static final int VCENTER = 16;
    public static BitmapDrawable sDrawable;

    public static void clearData() {
        BitmapDrawable bitmapDrawable = sDrawable;
        if (bitmapDrawable != null) {
            bitmapDrawable.getBitmap().recycle();
            sDrawable = null;
        }
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void drawClipBitmap(Canvas canvas, Bitmap bitmap, Paint paint, int i, int i2, int i3, int i4, int i5, int i6) {
        canvas.save();
        canvas.clipRect(i, i2, i3, i4);
        canvas.drawBitmap(bitmap, i5, i6, paint);
        canvas.restore();
    }

    public static void drawNinePatchs(Canvas canvas, Bitmap bitmap, int[] iArr, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        Paint paint = new Paint();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = 1;
        while (i22 <= 9) {
            if (i22 == 1) {
                int i23 = iArr[0];
                int i24 = iArr[1];
                i8 = i;
                i9 = i8;
                i5 = i2;
                i10 = i5;
                i11 = i23;
                i12 = i11;
                i13 = i24;
                i14 = i13;
                i6 = i + i23;
                i7 = i2 + i24;
            } else if (i22 == 2) {
                int i25 = (width - iArr[0]) - iArr[2];
                int i26 = iArr[1];
                int i27 = i + iArr[0];
                i8 = i;
                i5 = i2;
                i10 = i5;
                i12 = i25;
                i13 = i26;
                i14 = i13;
                i6 = i27 + i25;
                i7 = i2 + i26;
                i9 = i27;
                i11 = (i3 - iArr[0]) - iArr[3];
            } else if (i22 == 3) {
                int i28 = iArr[2];
                int i29 = iArr[1];
                int i30 = i + i3;
                i5 = i2;
                i10 = i5;
                i11 = i28;
                i12 = i11;
                i13 = i29;
                i14 = i13;
                i6 = i30;
                i7 = i2 + i29;
                i9 = i30 - i28;
                i8 = i30 - width;
            } else if (i22 == 4) {
                int i31 = iArr[0];
                int i32 = (height - iArr[1]) - iArr[3];
                int i33 = i2 + iArr[1];
                i8 = i;
                i9 = i8;
                i10 = i2;
                i11 = i31;
                i12 = i11;
                i14 = i32;
                i5 = i33;
                i6 = i + i31;
                i7 = i33 + i32;
                i13 = (i4 - iArr[1]) - iArr[3];
            } else {
                if (i22 == 5) {
                    int i34 = (width - iArr[0]) - iArr[2];
                    int i35 = (height - iArr[1]) - iArr[3];
                    int i36 = i + iArr[0];
                    int i37 = i2 + iArr[1];
                    int i38 = (i3 - iArr[0]) - iArr[2];
                    i15 = (i4 - iArr[1]) - iArr[3];
                    i8 = i;
                    i10 = i2;
                    i12 = i34;
                    i14 = i35;
                    i5 = i37;
                    i6 = i36 + i34;
                    i9 = i36;
                    i7 = i37 + i35;
                    i11 = i38;
                } else if (i22 == 6) {
                    int i39 = iArr[2];
                    int i40 = (height - iArr[1]) - iArr[3];
                    int i41 = i + i3;
                    int i42 = i41 - i39;
                    int i43 = i2 + iArr[1];
                    i15 = (i4 - iArr[1]) - iArr[3];
                    i10 = i2;
                    i11 = i39;
                    i12 = i11;
                    i14 = i40;
                    i6 = i41;
                    i5 = i43;
                    i9 = i42;
                    i7 = i43 + i40;
                    i8 = i42 - (width - i39);
                } else if (i22 == 7) {
                    int i44 = iArr[0];
                    int i45 = iArr[3];
                    int i46 = (i2 + i4) - i45;
                    i8 = i;
                    i9 = i8;
                    i11 = i44;
                    i12 = i11;
                    i13 = i45;
                    i14 = i13;
                    i5 = i46;
                    i6 = i + i44;
                    i7 = i46 + i45;
                    i10 = (i46 - height) + i45;
                } else if (i22 == 8) {
                    int i47 = (width - iArr[0]) - iArr[2];
                    int i48 = iArr[3];
                    int i49 = i + iArr[0];
                    int i50 = (i2 + i4) - i48;
                    i8 = i;
                    i12 = i47;
                    i14 = i48;
                    i5 = i50;
                    i6 = i49 + i47;
                    i9 = i49;
                    i7 = i50 + i48;
                    i10 = (i50 - height) + i48;
                    i11 = (i3 - iArr[0]) - iArr[2];
                    i13 = iArr[3];
                } else if (i22 == 9) {
                    int i51 = iArr[2];
                    int i52 = iArr[3];
                    int i53 = i + i3;
                    int i54 = (i2 + i4) - i52;
                    i11 = i51;
                    i12 = i11;
                    i13 = i52;
                    i14 = i13;
                    i6 = i53;
                    i5 = i54;
                    i9 = i53 - i51;
                    i7 = i54 + i52;
                    i8 = i53 - width;
                    i10 = (i54 - height) + i52;
                } else {
                    i5 = i16;
                    i6 = i17;
                    i7 = i18;
                    i8 = i19;
                    i9 = i20;
                    i10 = i21;
                    i11 = 0;
                    i12 = 0;
                    i13 = 0;
                    i14 = 0;
                }
                i13 = i15;
            }
            int i55 = (i13 / i14) + (i13 % i14 > 0 ? 1 : 0);
            int i56 = (i11 / i12) + (i11 % i12 > 0 ? 1 : 0);
            int i57 = 0;
            while (i57 < i55) {
                int i58 = 0;
                while (i58 < i56) {
                    int i59 = i58 * i12;
                    int i60 = i6 + i59;
                    int i61 = i11 + i9;
                    int i62 = i60 > i61 ? i61 : i60;
                    int i63 = i57 * i14;
                    int i64 = i7 + i63;
                    int i65 = i13 + i5;
                    int i66 = i58;
                    int i67 = i62;
                    drawClipBitmap(canvas, bitmap, paint, i9 + i59, i5 + i63, i67, i64 > i65 ? i65 : i64, i8 + i59, i10 + i63);
                    i58 = i66 + 1;
                    i57 = i57;
                    i56 = i56;
                    i55 = i55;
                }
                i57++;
            }
            i22++;
            i16 = i5;
            i17 = i6;
            i18 = i7;
            i19 = i8;
            i20 = i9;
            i21 = i10;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void drawString(android.graphics.Canvas r4, java.lang.String r5, int r6, int r7, int r8, android.graphics.Paint r9) {
        /*
            if (r4 != 0) goto L3
            return
        L3:
            if (r9 != 0) goto L6
            return
        L6:
            float r0 = r9.getTextSize()
            int r0 = (int) r0
            r1 = r8 & 3
            r2 = 3
            r3 = 5
            if (r1 != r2) goto L17
            android.graphics.Paint$Align r1 = android.graphics.Paint.Align.LEFT
            r9.setTextAlign(r1)
            goto L2c
        L17:
            r1 = r8 & 5
            if (r1 != r3) goto L21
            android.graphics.Paint$Align r1 = android.graphics.Paint.Align.RIGHT
            r9.setTextAlign(r1)
            goto L2c
        L21:
            r1 = r8 & 1
            r2 = 1
            if (r1 != r2) goto L27
            goto L2c
        L27:
            android.graphics.Paint$Align r1 = android.graphics.Paint.Align.LEFT
            r9.setTextAlign(r1)
        L2c:
            r1 = r8 & 48
            r2 = 48
            if (r1 != r2) goto L36
            int r7 = r7 + r0
            int r0 = r0 / r3
        L34:
            int r7 = r7 - r0
            goto L53
        L36:
            r1 = r8 & 80
            r2 = 80
            if (r1 != r2) goto L48
            android.graphics.Paint$FontMetrics r8 = r9.getFontMetrics()
            float r8 = r8.descent
            r0 = 1073741824(0x40000000, float:2.0)
            float r8 = r8 / r0
            int r8 = (int) r8
            int r7 = r7 - r8
            goto L53
        L48:
            r1 = 16
            r8 = r8 & r1
            int r7 = r7 + r0
            if (r8 != r1) goto L51
            int r7 = r7 >> 0
            goto L53
        L51:
            int r0 = r0 / r3
            goto L34
        L53:
            if (r5 == 0) goto L5a
            float r6 = (float) r6
            float r7 = (float) r7
            r4.drawText(r5, r6, r7, r9)
        L5a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.adapter.util.CanvasHelper.drawString(android.graphics.Canvas, java.lang.String, int, int, int, android.graphics.Paint):void");
    }

    public static Bitmap getBitmap(String str) {
        Bitmap bitmapDecodeStream = null;
        try {
            InputStream inputStream = PlatformUtil.getInputStream(str);
            bitmapDecodeStream = BitmapFactory.decodeStream(inputStream);
            IOUtil.close(inputStream);
            return bitmapDecodeStream;
        } catch (Exception e2) {
            e2.printStackTrace();
            return bitmapDecodeStream;
        }
    }

    public static Drawable getDrawable() {
        if (sDrawable == null) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.RGB_565);
            new Canvas(bitmapCreateBitmap).drawColor(0);
            sDrawable = new BitmapDrawable(bitmapCreateBitmap);
        }
        return sDrawable;
    }

    public static int getFontHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return ((int) Math.ceil(fontMetrics.descent - fontMetrics.top)) + 2;
    }

    public static float getViablePx(int i) {
        return i * DeviceInfo.sDensity;
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static Drawable getDrawable(Context context, String str) {
        Bitmap bitmap = getBitmap(str);
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    public static Drawable getDrawable(String str) {
        Bitmap bitmap = getBitmap(str);
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(bitmap);
    }
}
