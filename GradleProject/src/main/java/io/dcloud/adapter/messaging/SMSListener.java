package io.dcloud.adapter.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SMSListener {
    public String mCallbackId;
    public Context mContext;
    public SMSBroadcastReceiver mSMSBroadcastReceiver;
    public SMSContentObserver mSMSContentObserver;
    public IWebview mWebview;

    public class SMS {
        public String address;
        public String body;
        public long timestamp;

        public SMS(String str, String str2, long j) {
            this.address = str;
            this.body = str2;
            this.timestamp = j;
        }
    }

    public class SMSBroadcastReceiver extends BroadcastReceiver {
        public Handler mHandler;

        public SMSBroadcastReceiver(Handler handler) {
            this.mHandler = handler;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == "android.provider.Telephony.SMS_RECEIVED_DCLOUD" || action == "android.provider.Telephony.SMS_RECEIVED" || action == "android.provider.Telephony.SMS_READ") {
                Object[] objArr = (Object[]) intent.getSerializableExtra("pdus");
                int length = objArr.length;
                byte[][] bArr = new byte[length][];
                for (int i = 0; i < objArr.length; i++) {
                    bArr[i] = (byte[]) objArr[i];
                }
                byte[][] bArr2 = new byte[length][];
                SmsMessage[] smsMessageArr = new SmsMessage[length];
                for (int i2 = 0; i2 < length; i2++) {
                    bArr2[i2] = bArr[i2];
                    smsMessageArr[i2] = SmsMessage.createFromPdu(bArr2[i2]);
                }
                SmsMessage smsMessage = smsMessageArr[0];
                Message messageObtain = Message.obtain();
                messageObtain.what = 1;
                messageObtain.obj = SMSListener.this.new SMS(smsMessage.getDisplayOriginatingAddress(), smsMessage.getMessageBody(), smsMessage.getTimestampMillis());
                this.mHandler.sendMessage(messageObtain);
            }
        }
    }

    public class SMSContentObserver extends ContentObserver {
        public Context mContext;
        public Handler mHandler;

        public SMSContentObserver(Context context, Handler handler) {
            super(handler);
            this.mContext = null;
            this.mContext = context;
            this.mHandler = handler;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            try {
                Cursor cursorQuery = this.mContext.getContentResolver().query(Uri.parse("content://sms/inbox"), new String[]{"address", "body", "date"}, null, null, "date desc");
                if (cursorQuery != null) {
                    cursorQuery.moveToFirst();
                    String string = cursorQuery.getString(cursorQuery.getColumnIndex("address"));
                    String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("body"));
                    long j = cursorQuery.getLong(cursorQuery.getColumnIndex("date"));
                    Message messageObtain = Message.obtain();
                    messageObtain.what = 1;
                    messageObtain.obj = SMSListener.this.new SMS(string, string2, j);
                    this.mHandler.sendMessage(messageObtain);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                Message messageObtain2 = Message.obtain();
                messageObtain2.what = 2;
                messageObtain2.obj = SMSListener.this.new SMS("none", e2.getMessage(), System.currentTimeMillis());
                this.mHandler.sendMessage(messageObtain2);
            }
            super.onChange(z);
        }
    }

    public SMSListener(IWebview iWebview, String str) {
        this.mWebview = iWebview;
        this.mContext = iWebview.getContext();
        this.mCallbackId = str;
        iWebview.obtainFrameView().addFrameViewListener(new IEventCallback() { // from class: io.dcloud.adapter.messaging.SMSListener.1
            @Override // io.dcloud.common.DHInterface.IEventCallback
            public Object onCallBack(String str2, Object obj) {
                if (!PdrUtil.isEquals(str2, AbsoluteConst.EVENTS_WINDOW_CLOSE)) {
                    return null;
                }
                SMSListener sMSListener = SMSListener.this;
                SMSBroadcastReceiver sMSBroadcastReceiver = sMSListener.mSMSBroadcastReceiver;
                if (sMSBroadcastReceiver != null) {
                    sMSListener.mContext.unregisterReceiver(sMSBroadcastReceiver);
                }
                SMSListener sMSListener2 = SMSListener.this;
                if (sMSListener2.mSMSContentObserver == null) {
                    return null;
                }
                sMSListener2.mContext.getContentResolver().unregisterContentObserver(SMSListener.this.mSMSContentObserver);
                return null;
            }
        });
    }

    public void listen() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.setPriority(1000);
            intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
            intentFilter.addAction("android.provider.Telephony.SMS_READ");
            Handler handler = new Handler() { // from class: io.dcloud.adapter.messaging.SMSListener.2
                public String lastBody;
                public long lastTimestamp = System.currentTimeMillis();

                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    SMS sms = (SMS) message.obj;
                    if (sms.timestamp - this.lastTimestamp <= 2000 || TextUtils.equals(this.lastBody, sms.body)) {
                        return;
                    }
                    int i = message.what;
                    if (i == 1) {
                        SMSListener.this.onSucess(sms.address, sms.body, sms.timestamp);
                    } else if (i == 2) {
                        SMSListener.this.onFail(sms.body);
                    }
                    this.lastTimestamp = sms.timestamp;
                    this.lastBody = sms.body;
                }
            };
            this.mSMSBroadcastReceiver = new SMSBroadcastReceiver(handler);
            this.mWebview.getContext().registerReceiver(this.mSMSBroadcastReceiver, intentFilter);
            Uri uri = Uri.parse("content://sms");
            this.mSMSContentObserver = new SMSContentObserver(this.mWebview.getContext(), handler);
            this.mContext.getContentResolver().registerContentObserver(uri, true, this.mSMSContentObserver);
        } catch (Exception e2) {
            e2.printStackTrace();
            onFail(e2.getMessage());
        }
    }

    public void onFail(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("message", str);
            jSONObject.put("code", 13);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        JSUtil.execCallback(this.mWebview, this.mCallbackId, jSONObject, JSUtil.ERROR, true);
    }

    public void onSucess(String str, String str2, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(RemoteMessageConst.FROM, str);
            jSONObject.put("body", str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        JSUtil.execCallback(this.mWebview, this.mCallbackId, jSONObject, JSUtil.OK, true);
    }
}
