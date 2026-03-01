package okio;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public final class SegmentPool {
    public static final long MAX_SIZE = 65536;
    public static long byteCount;

    @Nullable
    public static Segment next;

    public static void recycle(Segment segment) {
        if (segment.next != null || segment.prev != null) {
            throw new IllegalArgumentException();
        }
        if (segment.shared) {
            return;
        }
        synchronized (SegmentPool.class) {
            if (byteCount + 8192 > 65536) {
                return;
            }
            byteCount += 8192;
            segment.next = next;
            segment.limit = 0;
            segment.pos = 0;
            next = segment;
        }
    }

    public static Segment take() {
        synchronized (SegmentPool.class) {
            if (next == null) {
                return new Segment();
            }
            Segment segment = next;
            next = segment.next;
            segment.next = null;
            byteCount -= 8192;
            return segment;
        }
    }
}
