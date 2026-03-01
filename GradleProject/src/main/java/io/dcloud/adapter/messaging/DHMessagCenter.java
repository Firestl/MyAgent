package io.dcloud.adapter.messaging;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import com.loopj.android.http.RequestParams;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;

/* JADX INFO: loaded from: classes2.dex */
public class DHMessagCenter {
    public static Context mContext;

    public static void initDHMessaging(Context context) {
        mContext = context;
    }

    public static void sendMMS(DHMessaging dHMessaging) {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setData(Uri.parse("mms:"));
            intent.setType("*/*");
            if (dHMessaging.mAttachments != null && dHMessaging.mAttachments.size() > 0) {
                intent.putExtra("android.intent.extra.STREAM", dHMessaging.mAttachments.get(0));
            }
            intent.putExtra("android.intent.extra.TEXT", dHMessaging.mBody);
            dHMessaging.mWebview.getActivity().startActivity(intent);
            Deprecated_JSUtil.execCallback(dHMessaging.mWebview, dHMessaging.mCallbackId, "", JSUtil.OK, false, false);
        } catch (Exception e2) {
            e2.printStackTrace();
            Deprecated_JSUtil.execCallback(dHMessaging.mWebview, dHMessaging.mCallbackId, DOMException.toJSON(13, e2.getLocalizedMessage()), JSUtil.ERROR, true, false);
        }
    }

    public static void sendMail(DHMessaging dHMessaging) {
        if (dHMessaging == null) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra("android.intent.extra.EMAIL", dHMessaging.mTo);
            if (dHMessaging.mCc != null && dHMessaging.mCc.length > 0) {
                intent.putExtra("android.intent.extra.CC", dHMessaging.mCc);
            }
            if (dHMessaging.mBcc != null && dHMessaging.mBcc.length > 0) {
                intent.putExtra("android.intent.extra.BCC", dHMessaging.mBcc);
            }
            intent.putExtra("android.intent.extra.SUBJECT", dHMessaging.mSubject);
            intent.putExtra("android.intent.extra.TEXT", dHMessaging.mBody);
            int size = dHMessaging.mAttachments != null ? dHMessaging.mAttachments.size() : 0;
            if (size > 1) {
                intent.setAction("android.intent.action.SEND_MULTIPLE");
                intent.putParcelableArrayListExtra("android.intent.extra.STREAM", dHMessaging.mAttachments);
                intent.setType(RequestParams.APPLICATION_OCTET_STREAM);
                intent.setType("message/rfc822");
            } else if (size == 1) {
                intent.putExtra("android.intent.extra.STREAM", dHMessaging.mAttachments.get(0));
                intent.setType(PdrUtil.getMimeType(dHMessaging.mAttachments.get(0).getPath()));
                intent.setType("message/rfc882");
                intent.setAction("android.intent.action.SEND");
            } else {
                intent.setAction("android.intent.action.SENDTO");
            }
            dHMessaging.mWebview.getActivity().startActivity(intent);
            Deprecated_JSUtil.execCallback(dHMessaging.mWebview, dHMessaging.mCallbackId, "", JSUtil.OK, false, false);
        } catch (Exception e2) {
            e2.printStackTrace();
            Deprecated_JSUtil.execCallback(dHMessaging.mWebview, dHMessaging.mCallbackId, DOMException.toJSON(13, e2.getLocalizedMessage()), JSUtil.ERROR, true, false);
        }
    }

    public static void sendMessage(DHMessaging dHMessaging) {
        if (dHMessaging != null) {
            String string = null;
            try {
                if (dHMessaging.mTo != null && dHMessaging.mTo.length > 0) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (String str : dHMessaging.mTo) {
                        stringBuffer.append(str + ";");
                    }
                    string = stringBuffer.toString();
                }
                String str2 = string;
                if (dHMessaging.mSilent) {
                    SmsManager.getDefault().sendTextMessage(str2, null, dHMessaging.mBody, null, null);
                } else {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("sms:" + str2));
                    intent.putExtra("address", str2);
                    intent.putExtra("sms_body", dHMessaging.mBody);
                    dHMessaging.mWebview.getActivity().startActivity(intent);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                Deprecated_JSUtil.execCallback(dHMessaging.mWebview, dHMessaging.mCallbackId, DOMException.toJSON(13, e2.getLocalizedMessage()), JSUtil.ERROR, true, false);
                return;
            }
        }
        Deprecated_JSUtil.execCallback(dHMessaging.mWebview, dHMessaging.mCallbackId, "", JSUtil.OK, false, false);
    }
}
