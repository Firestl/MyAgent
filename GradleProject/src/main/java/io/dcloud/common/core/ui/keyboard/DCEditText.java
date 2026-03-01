package io.dcloud.common.core.ui.keyboard;

import android.content.Context;
import android.graphics.Rect;
import android.widget.EditText;
import io.dcloud.common.core.ui.DCKeyboardManager;

/* JADX INFO: loaded from: classes2.dex */
public class DCEditText extends EditText {
    public float mCursorSpacing;
    public String mInputMode;
    public String mInstanceId;
    public OnKeyboardHeightChangeListener mKeyboardHeightChangeListener;

    public interface OnKeyboardHeightChangeListener {
        void onChange(boolean z, int i);
    }

    public DCEditText(Context context, String str) {
        super(context);
        this.mCursorSpacing = 0.0f;
        this.mInputMode = DCKeyboardManager.SOFT_INPUT_MODE_ADJUST_PAN;
        this.mInstanceId = str;
    }

    public void destroy() {
        clearFocus();
        if (DCKeyboardManager.getInstance().getNativeInput() == this) {
            DCKeyboardManager.getInstance().setNativeInput(null, 0.0f);
        }
    }

    public OnKeyboardHeightChangeListener getKeyboardHeightChangeListener() {
        return this.mKeyboardHeightChangeListener;
    }

    @Override // android.widget.TextView, android.view.View
    public void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        DCKeyboardManager.getInstance().nativeEditTextFocus(this.mInstanceId, this, z, this.mInputMode, this.mCursorSpacing);
    }

    public void setCursorSpacing(float f) {
        this.mCursorSpacing = f;
    }

    public void setInputSoftMode(String str) {
        this.mInputMode = str;
    }

    public void setkeyBoardHeightChangeListener(OnKeyboardHeightChangeListener onKeyboardHeightChangeListener) {
        this.mKeyboardHeightChangeListener = onKeyboardHeightChangeListener;
    }
}
