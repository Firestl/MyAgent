package io.dcloud.common.adapter.ui;

import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.RecordView;
import java.util.regex.Pattern;
import org.bouncycastle.math.ec.custom.sec.SecP160R1Field;

/* JADX INFO: loaded from: classes2.dex */
public class CustomeizedInputConnection extends InputConnectionWrapper {
    public String composingText;
    public int mCursorPos;
    public StringBuffer mInputString;
    public int mInputType;
    public CustomeizedInputConnection mLastCustomeizedInputConnection;
    public InputConnection mTarget;
    public IWebview mWebview;
    public static Pattern patternPhone = Pattern.compile("^1[0-9]{10}$");
    public static Pattern patternID18 = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
    public static Pattern patternID15 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
    public static Pattern patternEmail = Pattern.compile("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
    public static int sDefaultInputType = -1;

    public CustomeizedInputConnection(IWebview iWebview, InputConnection inputConnection, EditorInfo editorInfo, CustomeizedInputConnection customeizedInputConnection) {
        super(inputConnection, false);
        this.mInputType = -1;
        this.mWebview = null;
        this.mInputString = new StringBuffer();
        this.mCursorPos = -1;
        this.mLastCustomeizedInputConnection = null;
        this.composingText = null;
        this.mTarget = inputConnection;
        this.mWebview = iWebview;
        this.mLastCustomeizedInputConnection = customeizedInputConnection;
        checkLastInputConnection();
        recordInputType(editorInfo);
        recordText();
        clearComposingText();
    }

    private void clearComposingText() {
        this.composingText = null;
    }

    private void log(String str, String str2) {
    }

    private boolean needRecord(int i, String str) {
        if (i == 1) {
            return patternPhone.matcher(str).find();
        }
        if (i == 2) {
            return patternEmail.matcher(str).find();
        }
        if (i != 7) {
            return RecordView.Utils.needRecord(i);
        }
        if (str.length() == 18 && patternID18.matcher(str).find()) {
            return true;
        }
        return str.length() == 15 && patternID15.matcher(str).find();
    }

    private void recordInputType(EditorInfo editorInfo) {
        int i = sDefaultInputType;
        this.mInputType = i;
        int i2 = editorInfo.inputType;
        int i3 = i2 & 15;
        if (i3 == 1) {
            int i4 = i2 & 4080;
            if (i4 == 32 || i4 == 208) {
                this.mInputType = 2;
            }
        } else if (i3 != 3) {
            this.mInputType = i;
        } else {
            this.mInputType = 1;
        }
        log("AssistantInput", "recordInputType mInputType =" + this.mInputType + ";typeClass=" + i3);
        RecordView recordView = AdaWebview.mRecordView;
        if (recordView == null || !recordView.mShowed) {
            return;
        }
        showRecordView(recordView.mAnchorY, this.mInputType);
    }

    private void recordText() {
        CharSequence textBeforeCursor = super.getTextBeforeCursor(255, 0);
        if (textBeforeCursor == null || textBeforeCursor.length() <= 0) {
            return;
        }
        StringBuffer stringBuffer = this.mInputString;
        stringBuffer.delete(0, stringBuffer.length());
        CharSequence textAfterCursor = super.getTextAfterCursor(255, 0);
        this.mCursorPos = textBeforeCursor.length();
        StringBuffer stringBuffer2 = this.mInputString;
        stringBuffer2.append(textBeforeCursor);
        stringBuffer2.append(textAfterCursor);
        log("AssistantInput", "recordText mCursorPos=" + this.mCursorPos + ";mInputString=" + ((Object) this.mInputString));
    }

    public void checkLastInputConnection() {
        CustomeizedInputConnection customeizedInputConnection = this.mLastCustomeizedInputConnection;
        if (customeizedInputConnection == null || Build.VERSION.SDK_INT < 24) {
            return;
        }
        customeizedInputConnection.finishComposingText();
    }

    public void closeRecordView() {
        RecordView recordView = AdaWebview.mRecordView;
        if (recordView != null) {
            recordView.update(recordView.mAnchorY, -1);
            AdaWebview.mRecordView.conceal();
            AdaWebview.mRecordView.mShowed = false;
        }
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean commitText(CharSequence charSequence, int i) {
        recordText();
        int i2 = this.mCursorPos;
        if (i2 < 0 || i2 >= this.mInputString.length()) {
            this.mInputString.append(charSequence);
        } else {
            this.mInputString.insert(this.mCursorPos, charSequence);
        }
        log("AssistantInput", "commitText mCursorPos=" + this.mCursorPos + ";msg=" + ((Object) this.mInputString));
        return super.commitText(charSequence, i);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean finishComposingText() {
        String string = this.mInputString.toString();
        if (!TextUtils.isEmpty(string)) {
            if (!TextUtils.isEmpty(this.composingText) && string.contains(this.composingText)) {
                int iIndexOf = string.indexOf(this.composingText);
                string = string.substring(0, iIndexOf) + string.substring(iIndexOf + this.composingText.length());
            }
            String strTrim = string.trim();
            log("AssistantInput", "finishComposingText mInputType=" + this.mInputType + ";msg=" + strTrim);
            if (AdaWebview.mRecordView != null && needRecord(this.mInputType, strTrim)) {
                AdaWebview.mRecordView.record(strTrim, this.mInputType);
                int i = this.mInputType;
                if (i != -1 && i == AdaWebview.sCustomeizedInputConnection.mInputType) {
                    RecordView recordView = AdaWebview.mRecordView;
                    recordView.update(recordView.mAnchorY, i);
                }
                StringBuffer stringBuffer = this.mInputString;
                stringBuffer.delete(0, stringBuffer.length());
            }
        }
        InputConnection inputConnection = this.mTarget;
        if (inputConnection != null) {
            return inputConnection.finishComposingText();
        }
        return true;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public CharSequence getTextBeforeCursor(int i, int i2) {
        log("AssistantInput", "getTextBeforeCursor mCursorPos=" + this.mCursorPos + ";n=" + i);
        recordText();
        clearComposingText();
        return super.getTextBeforeCursor(i, i2);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean setComposingText(CharSequence charSequence, int i) {
        this.composingText = charSequence.toString();
        log("AssistantInput", "setComposingText mCursorPos=" + this.mCursorPos + ";msg=" + ((Object) charSequence));
        return super.setComposingText(charSequence, i);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean setSelection(int i, int i2) {
        if (i == i2) {
            this.mCursorPos = i;
        }
        return super.setSelection(i, i2);
    }

    public void setText(String str) {
        clearComposingText();
        if (!TextUtils.equals(Build.MODEL, "ZTE B880")) {
            recordText();
            deleteSurroundingText(SecP160R1Field.PInv, Integer.MAX_VALUE);
            super.setSelection(0, Integer.MAX_VALUE);
            super.commitText(str, str.length());
            return;
        }
        StringBuffer stringBuffer = this.mInputString;
        stringBuffer.delete(0, stringBuffer.length());
        this.mCursorPos = str.length();
        this.mInputString.append(str);
        deleteSurroundingText(SecP160R1Field.PInv, Integer.MAX_VALUE);
        super.setSelection(0, Integer.MAX_VALUE);
        super.commitText(str, str.length());
    }

    public void showRecordView(int i, boolean z) {
        showRecordView(i, !z ? -1 : this.mInputType);
    }

    public void update(int i) {
        super.sendKeyEvent(new KeyEvent(0, i > 0 ? 22 : 21));
    }

    public void updateInputType(int i) {
        this.mInputType = i;
    }

    public void showRecordView(int i, int i2) {
        if (this.mWebview.obtainApp() == null) {
            return;
        }
        if (AdaWebview.mRecordView == null) {
            AdaWebview.mRecordView = new RecordView(this.mWebview.getContext(), (ViewGroup) this.mWebview.obtainApp().obtainWebAppRootView().obtainMainView(), this.mWebview.obtainApp().obtainAppId());
        }
        AdaWebview.mRecordView.update(i, i2);
        AdaWebview.mRecordView.mShowed = true;
    }
}
