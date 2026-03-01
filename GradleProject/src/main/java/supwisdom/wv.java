package supwisdom;

import com.dcloud.zxing2.ChecksumException;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface wv {
    xv a(sv svVar, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException, NotFoundException;

    void reset();
}
