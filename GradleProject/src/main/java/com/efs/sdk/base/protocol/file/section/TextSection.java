package com.efs.sdk.base.protocol.file.section;

/* JADX INFO: loaded from: classes.dex */
public class TextSection extends AbsSection {
    public String body;

    public TextSection(String str) {
        super("text");
        this.name = str;
    }

    @Override // com.efs.sdk.base.protocol.file.section.AbsSection
    public String changeToStr() {
        return getDeclarationLine() + "\n" + this.body + "\n";
    }

    public void setBody(String str) {
        this.body = str;
    }
}
