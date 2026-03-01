package io.dcloud.feature.unimp;

import io.dcloud.PandoraEntry;
import io.dcloud.base.R;

/* JADX INFO: loaded from: classes3.dex */
public class DCUniMPEntry extends PandoraEntry {
    @Override // android.app.Activity
    public void overridePendingTransition(int i, int i2) {
        super.overridePendingTransition(R.anim.dcloud_unimp_open_enter, R.anim.dcloud_unimp_def_motionless);
    }
}
