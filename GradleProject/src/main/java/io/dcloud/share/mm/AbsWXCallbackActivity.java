package io.dcloud.share.mm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMusicVideoObject;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import io.dcloud.ProcessMediator;
import io.dcloud.common.DHInterface.FeatureMessageDispatcher;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.IntentConst;
import supwisdom.ne;

/* JADX INFO: loaded from: classes3.dex */
public class AbsWXCallbackActivity extends Activity implements IWXAPIEventHandler {
    private void goToMsg(BaseReq baseReq) {
        WXMediaMessage.IMediaObject iMediaObject = ((ShowMessageFromWX.Req) baseReq).message.mediaObject;
        if (iMediaObject instanceof WXMusicVideoObject) {
            return;
        }
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        launchIntentForPackage.putExtra(AbsoluteConst.KEY_WX_SHOW_MESSAGE, ((WXAppExtendObject) iMediaObject).extInfo);
        launchIntentForPackage.putExtra(IntentConst.STREAM_LAUNCHER, "miniProgram");
        launchIntentForPackage.addFlags(524288);
        startActivity(launchIntentForPackage);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            WXAPIFactory.createWXAPI(this, AndroidResources.getMetaValue("WX_APPID"), false).handleIntent(getIntent(), this);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onReq(BaseReq baseReq) {
        FeatureMessageDispatcher.dispatchMessage(baseReq);
        if (ProcessMediator.isMultiProcess) {
            Intent intent = new Intent();
            boolean zIsEmpty = TextUtils.isEmpty(baseReq.transaction);
            String str = ProcessMediator.RESULT_ACTION;
            if (!zIsEmpty) {
                str = ProcessMediator.RESULT_ACTION + baseReq.transaction;
            }
            intent.setAction(str);
            Bundle bundle = new Bundle();
            bundle.putString("style", "BaseReq");
            baseReq.toBundle(bundle);
            intent.putExtra("result", bundle);
            ne.a(this).a(intent);
        } else if (baseReq.getType() == 4) {
            goToMsg(baseReq);
        }
        finish();
    }

    public void onResp(BaseResp baseResp) {
        FeatureMessageDispatcher.dispatchMessage(baseResp);
        if (ProcessMediator.isMultiProcess) {
            Intent intent = new Intent();
            boolean zIsEmpty = TextUtils.isEmpty(baseResp.transaction);
            String str = ProcessMediator.RESULT_ACTION;
            if (!zIsEmpty) {
                str = ProcessMediator.RESULT_ACTION + baseResp.transaction;
            }
            intent.setAction(str);
            Bundle bundle = new Bundle();
            bundle.putString("style", "BaseResp");
            baseResp.toBundle(bundle);
            intent.putExtra("result", bundle);
            ne.a(this).a(intent);
        } else if (baseResp.getType() == 19) {
            goToMsg(baseResp);
        }
        overridePendingTransition(0, 0);
        finish();
    }

    private void goToMsg(BaseResp baseResp) {
        String str = ((WXLaunchMiniProgram.Resp) baseResp).extMsg;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        launchIntentForPackage.putExtra(AbsoluteConst.KEY_WX_SHOW_MESSAGE, str);
        launchIntentForPackage.putExtra(IntentConst.STREAM_LAUNCHER, "miniProgram");
        launchIntentForPackage.addFlags(524288);
        startActivity(launchIntentForPackage);
    }
}
