package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.UninitializedMessageException;
import supwisdom.uq0;

/* JADX INFO: compiled from: AbstractParser.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class jp0<MessageType extends uq0> implements dr0<MessageType> {
    static {
        xp0.a();
    }

    public final UninitializedMessageException b(MessageType messagetype) {
        return messagetype instanceof ip0 ? ((ip0) messagetype).i() : new UninitializedMessageException(messagetype);
    }

    public final MessageType a(MessageType messagetype) throws InvalidProtocolBufferException {
        if (messagetype == null || messagetype.g()) {
            return messagetype;
        }
        throw b(messagetype).asInvalidProtocolBufferException().setUnfinishedMessage(messagetype);
    }

    public MessageType b(ByteString byteString, xp0 xp0Var) throws InvalidProtocolBufferException {
        try {
            rp0 rp0VarNewCodedInput = byteString.newCodedInput();
            MessageType messagetypeA = a(rp0VarNewCodedInput, xp0Var);
            try {
                rp0VarNewCodedInput.a(0);
                return messagetypeA;
            } catch (InvalidProtocolBufferException e2) {
                throw e2.setUnfinishedMessage(messagetypeA);
            }
        } catch (InvalidProtocolBufferException e3) {
            throw e3;
        }
    }

    @Override // supwisdom.dr0
    public MessageType a(ByteString byteString, xp0 xp0Var) throws InvalidProtocolBufferException {
        MessageType messagetype = (MessageType) b(byteString, xp0Var);
        a(messagetype);
        return messagetype;
    }
}
