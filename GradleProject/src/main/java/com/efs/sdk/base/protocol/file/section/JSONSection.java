package com.efs.sdk.base.protocol.file.section;

/* JADX INFO: loaded from: classes.dex */
public class JSONSection extends AbsSection {
    public String body;

    public JSONSection(String str) {
        super("json");
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
