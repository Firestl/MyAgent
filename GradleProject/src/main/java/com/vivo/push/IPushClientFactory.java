package com.vivo.push;

import android.content.Intent;
import com.vivo.push.c.y;

/* JADX INFO: loaded from: classes2.dex */
public interface IPushClientFactory {
    y createReceiveTask(o oVar);

    o createReceiverCommand(Intent intent);

    l createTask(o oVar);
}
