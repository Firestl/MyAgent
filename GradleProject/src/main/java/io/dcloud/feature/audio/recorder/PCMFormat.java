package io.dcloud.feature.audio.recorder;

/* JADX INFO: loaded from: classes2.dex */
public enum PCMFormat {
    PCM_8BIT(1, 3),
    PCM_16BIT(2, 2);

    public int audioFormat;
    public int bytesPerFrame;

    PCMFormat(int i, int i2) {
        this.bytesPerFrame = i;
        this.audioFormat = i2;
    }

    public int getAudioFormat() {
        return this.audioFormat;
    }

    public int getBytesPerFrame() {
        return this.bytesPerFrame;
    }
}
