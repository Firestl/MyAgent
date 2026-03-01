package io.dcloud.common.DHInterface;

import io.dcloud.common.adapter.util.MessageHandler;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class FeatureMessageDispatcher {
    public static CopyOnWriteArrayList<MessageListener> sFeatureMessage = new CopyOnWriteArrayList<>();

    @Deprecated
    public interface MessageListener {
        void onReceiver(Object obj);
    }

    public static abstract class StrongMessageListener implements MessageListener {
        public Object mFlag;

        public StrongMessageListener(Object obj) {
            this.mFlag = obj;
        }

        @Override // io.dcloud.common.DHInterface.FeatureMessageDispatcher.MessageListener
        public abstract void onReceiver(Object obj);
    }

    public static boolean contains(Object obj) {
        try {
            for (MessageListener messageListener : sFeatureMessage) {
                if ((messageListener instanceof StrongMessageListener) && obj.equals(((StrongMessageListener) messageListener).mFlag)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static void dispatchMessage(Object obj, final Object obj2) {
        try {
            for (MessageListener messageListener : sFeatureMessage) {
                if (messageListener instanceof StrongMessageListener) {
                    if (obj.equals(((StrongMessageListener) messageListener).mFlag)) {
                        MessageHandler.sendMessage(new MessageHandler.IMessages() { // from class: io.dcloud.common.DHInterface.FeatureMessageDispatcher.1
                            @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
                            public void execute(Object obj3) {
                                ((MessageListener) obj3).onReceiver(obj2);
                            }
                        }, messageListener);
                    }
                } else if (obj2 != null) {
                    MessageHandler.sendMessage(new MessageHandler.IMessages() { // from class: io.dcloud.common.DHInterface.FeatureMessageDispatcher.2
                        @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
                        public void execute(Object obj3) {
                            ((MessageListener) obj3).onReceiver(obj2);
                        }
                    }, messageListener);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void registerListener(MessageListener messageListener) {
        sFeatureMessage.add(messageListener);
    }

    public static void unregisterListener(MessageListener messageListener) {
        sFeatureMessage.remove(messageListener);
    }

    @Deprecated
    public static void dispatchMessage(Object obj) {
        dispatchMessage(null, obj);
    }
}
