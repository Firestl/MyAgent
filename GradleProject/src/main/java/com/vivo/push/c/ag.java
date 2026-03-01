package com.vivo.push.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.vivo.push.cache.ClientConfigManagerImpl;
import java.util.List;

/* JADX INFO: compiled from: SendCommandTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ag extends com.vivo.push.l {
    public ag(com.vivo.push.o oVar) {
        super(oVar);
    }

    @Override // com.vivo.push.l
    public final void a(com.vivo.push.o oVar) {
        Context context = this.f5616a;
        if (context == null) {
            com.vivo.push.util.o.d("SendCommandTask", "SendCommandTask " + oVar + " ; mContext is Null");
            return;
        }
        if (oVar == null) {
            com.vivo.push.util.o.d("SendCommandTask", "SendCommandTask pushCommand is Null");
            return;
        }
        com.vivo.push.model.b bVarA = com.vivo.push.util.s.a(context);
        int iB = oVar.b();
        if (iB != 0) {
            if (iB == 2009) {
                com.vivo.push.util.o.a(ClientConfigManagerImpl.getInstance(this.f5616a).isDebug());
                if (com.vivo.push.util.o.a()) {
                    com.vivo.push.e.a().i();
                    com.vivo.push.util.b bVar = new com.vivo.push.util.b();
                    bVar.a(this.f5616a, "com.vivo.push_preferences.hybridapptoken_v1");
                    bVar.a();
                    com.vivo.push.util.b bVar2 = new com.vivo.push.util.b();
                    bVar2.a(this.f5616a, "com.vivo.push_preferences.appconfig_v1");
                    bVar2.a();
                    if (!com.vivo.push.e.a().e()) {
                        ClientConfigManagerImpl.getInstance(this.f5616a).clearPush();
                    }
                }
            } else if (iB != 2011) {
                switch (iB) {
                    case 2002:
                    case 2003:
                    case 2004:
                    case 2005:
                        if (bVarA == null || bVarA.c()) {
                            com.vivo.push.e.a().a(((com.vivo.push.b.c) oVar).h(), 1005);
                        } else {
                            com.vivo.push.b.c cVar = (com.vivo.push.b.c) oVar;
                            int iA = com.vivo.push.util.r.a(cVar);
                            if (iA != 0) {
                                com.vivo.push.e.a().a(cVar.h(), iA);
                                return;
                            }
                        }
                        break;
                }
            } else {
                com.vivo.push.util.o.a(ClientConfigManagerImpl.getInstance(this.f5616a).isDebug(((com.vivo.push.b.w) oVar).d()));
            }
        } else if (com.vivo.push.e.a().e()) {
            Context context2 = this.f5616a;
            Intent intent = new Intent();
            intent.setPackage(context2.getPackageName());
            intent.setClassName(context2.getPackageName(), "com.vivo.push.sdk.service.CommandService");
            List<ResolveInfo> listQueryIntentServices = context2.getPackageManager().queryIntentServices(intent, 128);
            if (listQueryIntentServices == null || listQueryIntentServices.size() <= 0) {
                com.vivo.push.util.o.d("ModuleUtil", "disableDeprecatedService is null");
            } else {
                PackageManager packageManager = context2.getPackageManager();
                ComponentName componentName = new ComponentName(context2, listQueryIntentServices.get(0).serviceInfo.name);
                if (packageManager.getComponentEnabledSetting(componentName) != 2) {
                    packageManager.setComponentEnabledSetting(componentName, 2, 1);
                }
            }
            Context context3 = this.f5616a;
            Intent intent2 = new Intent();
            intent2.setPackage(context3.getPackageName());
            intent2.setClassName(context3.getPackageName(), "com.vivo.push.sdk.service.LinkProxyActivity");
            List<ResolveInfo> listQueryIntentActivities = context3.getPackageManager().queryIntentActivities(intent2, 128);
            if (listQueryIntentActivities == null || listQueryIntentActivities.size() <= 0) {
                com.vivo.push.util.o.d("ModuleUtil", "disableDeprecatedActivity is null");
            } else {
                PackageManager packageManager2 = context3.getPackageManager();
                ComponentName componentName2 = new ComponentName(context3, listQueryIntentActivities.get(0).activityInfo.name);
                if (packageManager2.getComponentEnabledSetting(componentName2) != 2) {
                    packageManager2.setComponentEnabledSetting(componentName2, 2, 1);
                }
            }
        }
        if (bVarA == null) {
            com.vivo.push.util.o.d("SendCommandTask", "SendCommandTask " + oVar + " ; pushPkgInfo is Null");
            return;
        }
        String strA = bVarA.a();
        if (bVarA.c()) {
            com.vivo.push.e.a().a(((com.vivo.push.b.c) oVar).h(), 1004);
            oVar = new com.vivo.push.b.e();
            com.vivo.push.util.o.d("SendCommandTask", "SendCommandTask " + oVar + " ; pkgName is InBlackList ");
        }
        com.vivo.push.a.a.a(this.f5616a, strA, oVar);
    }
}
