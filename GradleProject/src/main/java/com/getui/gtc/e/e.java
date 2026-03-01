package com.getui.gtc.e;

import com.getui.gtc.base.db.AbstractTable;

/* JADX INFO: loaded from: classes.dex */
public class e extends AbstractTable {
    @Override // com.getui.gtc.base.db.AbstractTable
    public String createSql() {
        return "create table if not exists t(_id integer primary key autoincrement, s bigint, r bigint)";
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public String getTableName() {
        return "t";
    }
}
