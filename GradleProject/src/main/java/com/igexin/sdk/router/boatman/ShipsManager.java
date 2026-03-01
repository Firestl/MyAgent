package com.igexin.sdk.router.boatman;

import com.igexin.sdk.router.boatman.receive.Site;

/* JADX INFO: loaded from: classes2.dex */
public class ShipsManager implements IShips {
    public static final String TAG_EXTENSION_INIT = "tag_extension_init";
    public static final String TAG_FEEDBACK = "tag_feedback";
    public static final String TAG_GKT = "tag_gkt";
    public static final String TAG_GT = "tag_gt";
    public static ShipsManager mInstance;
    public final ShipsManagerImpl mBase = new ShipsManagerImpl();

    public static ShipsManager get() {
        if (mInstance == null) {
            synchronized (ShipsManager.class) {
                if (mInstance == null) {
                    mInstance = new ShipsManager();
                }
            }
        }
        return mInstance;
    }

    public ShipsManagerImpl getShip() {
        return this.mBase;
    }

    @Override // com.igexin.sdk.router.boatman.IShips
    public boolean isRegistered(Site site) {
        return this.mBase.isRegistered(site);
    }

    @Override // com.igexin.sdk.router.boatman.IShips
    public void register(Site site) {
        this.mBase.register(site);
    }

    @Override // com.igexin.sdk.router.boatman.IShips
    public void unRegister(Site site) {
        this.mBase.unRegister(site);
    }
}
