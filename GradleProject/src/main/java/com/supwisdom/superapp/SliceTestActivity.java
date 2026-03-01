package com.supwisdom.superapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXException;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXRenderStrategy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import supwisdom.df;

/* JADX INFO: loaded from: classes2.dex */
public class SliceTestActivity extends AppCompatActivity {
    public static int f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public RecyclerView f3969a;
    public TextView b;
    public b d;
    public final List<String> c = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Set<WXSDKInstance> f3970e = new HashSet();

    public static class SearchModule extends WXModule {
        @JSMethod(uiThread = true)
        public void search(JSONObject jSONObject) {
            Log.e("TestModuel", jSONObject.toJSONString());
        }
    }

    public class b extends RecyclerView.g<c> {
        public b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(c cVar, int i) {
            String str = (String) SliceTestActivity.this.c.get(i);
            if (cVar.a()) {
                Log.d("SliceTestActivity", "refresh onBindViewHolder " + i);
                cVar.a(str, i);
                return;
            }
            Log.d("SliceTestActivity", "render onBindViewHolder " + i);
            cVar.b(str, i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            return SliceTestActivity.this.c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public c onCreateViewHolder(ViewGroup viewGroup, int i) {
            Log.d("SliceTestActivity", "onCreateViewHolder");
            FrameLayout frameLayout = new FrameLayout(SliceTestActivity.this);
            frameLayout.setLayoutParams(new RecyclerView.LayoutParams(-2, -2));
            return SliceTestActivity.this.new c(frameLayout);
        }
    }

    public class c extends RecyclerView.b0 implements IWXRenderListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WXSDKInstance f3972a;
        public boolean b;
        public TextView c;

        public c(View view) {
            super(view);
            WXSDKInstance wXSDKInstance = new WXSDKInstance(SliceTestActivity.this);
            this.f3972a = wXSDKInstance;
            wXSDKInstance.registerRenderListener(this);
            SliceTestActivity.this.f3970e.add(this.f3972a);
            this.c = new TextView(SliceTestActivity.this);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 5;
            ((ViewGroup) view).addView(this.c, layoutParams);
        }

        public boolean a() {
            return this.b;
        }

        public void b(String str, int i) {
            this.f3972a.render("testPage", SliceTestActivity.this.l(), (Map<String, Object>) null, str, WXRenderStrategy.DATA_RENDER);
            this.c.setText(String.valueOf(i));
            this.b = true;
        }

        @Override // com.taobao.weex.IWXRenderListener
        public void onException(WXSDKInstance wXSDKInstance, String str, String str2) {
        }

        @Override // com.taobao.weex.IWXRenderListener
        public void onRefreshSuccess(WXSDKInstance wXSDKInstance, int i, int i2) {
        }

        @Override // com.taobao.weex.IWXRenderListener
        public void onRenderSuccess(WXSDKInstance wXSDKInstance, int i, int i2) {
        }

        @Override // com.taobao.weex.IWXRenderListener
        public void onViewCreated(WXSDKInstance wXSDKInstance, View view) {
            ((ViewGroup) this.itemView).addView(view, -2, -2);
        }

        public void a(String str, int i) {
            this.f3972a.refreshInstance(str);
            this.c.setText(String.valueOf(i));
        }
    }

    public void addCellClick(View view) {
        int i = f;
        f = i + 1;
        if (i % 2 == 0) {
            this.c.add("{\"model\":{\"tips\":[{\"show\":\"雪纺\",\"q\":\"连衣裙 雪纺\",\"params\":[{\"key\":\"from\",\"value\":\"tips_1\"},{\"key\":\"vClickTrace\",\"value\":\"%7B%22tips_oriq%22%3A%22%E8%BF%9E%E8%A1%A3%E8%A3%99%22%2C%22tips_srppage%22%3A%222%22%2C%22tips_type%22%3A%221%22%2C%22tips_pos%22%3A%221%22%2C%22pre_rn%22%3A%220189c4d06e11f32262fa896f5f364f76%22%7D\"}]},{\"show\":\"中长款\",\"q\":\"连衣裙 中长款\",\"params\":[{\"key\":\"from\",\"value\":\"tips_1\"},{\"key\":\"vClickTrace\",\"value\":\"%7B%22tips_oriq%22%3A%22%E8%BF%9E%E8%A1%A3%E8%A3%99%22%2C%22tips_srppage%22%3A%222%22%2C%22tips_type%22%3A%221%22%2C%22tips_pos%22%3A%222%22%2C%22pre_rn%22%3A%220189c4d06e11f32262fa896f5f364f76%22%7D\"}]},{\"show\":\"假两件\",\"q\":\"连衣裙 假两件\",\"params\":[{\"key\":\"from\",\"value\":\"tips_1\"},{\"key\":\"vClickTrace\",\"value\":\"%7B%22tips_oriq%22%3A%22%E8%BF%9E%E8%A1%A3%E8%A3%99%22%2C%22tips_srppage%22%3A%222%22%2C%22tips_type%22%3A%221%22%2C%22tips_pos%22%3A%223%22%2C%22pre_rn%22%3A%220189c4d06e11f32262fa896f5f364f76%22%7D\"}]},{\"show\":\"A字款\",\"q\":\"连衣裙 A字款\",\"params\":[{\"key\":\"from\",\"value\":\"tips_1\"},{\"key\":\"vClickTrace\",\"value\":\"%7B%22tips_oriq%22%3A%22%E8%BF%9E%E8%A1%A3%E8%A3%99%22%2C%22tips_srppage%22%3A%222%22%2C%22tips_type%22%3A%221%22%2C%22tips_pos%22%3A%224%22%2C%22pre_rn%22%3A%220189c4d06e11f32262fa896f5f364f76%22%7D\"}]},{\"show\":\"气质淑女\",\"q\":\"连衣裙 气质淑女\",\"params\":[{\"key\":\"from\",\"value\":\"tips_1\"},{\"key\":\"vClickTrace\",\"value\":\"%7B%22tips_oriq%22%3A%22%E8%BF%9E%E8%A1%A3%E8%A3%99%22%2C%22tips_srppage%22%3A%222%22%2C%22tips_type%22%3A%221%22%2C%22tips_pos%22%3A%225%22%2C%22pre_rn%22%3A%220189c4d06e11f32262fa896f5f364f76%22%7D\"}]}],\"pos\":\"3\",\"src\":\"graph\",\"topic\":\"细选" + f + "\",\"type\":\"1\",\"tItemType\":\"wx_text\",\"tShowTmpl\":\"wx_text\",\"rl\":\"query_type-1|tip_show_type-1|tip_show_page-2\"},\"status\":{\"layoutStyle\":0}}");
        } else {
            this.c.add("{\"model\":{\"tips\":[{\"show\":\"雪纺\",\"q\":\"连衣裙 雪纺\",\"params\":[{\"key\":\"from\",\"value\":\"tips_1\"},{\"key\":\"vClickTrace\",\"value\":\"%7B%22tips_oriq%22%3A%22%E8%BF%9E%E8%A1%A3%E8%A3%99%22%2C%22tips_srppage%22%3A%222%22%2C%22tips_type%22%3A%221%22%2C%22tips_pos%22%3A%221%22%2C%22pre_rn%22%3A%220189c4d06e11f32262fa896f5f364f76%22%7D\"}]},{\"show\":\"中长款\",\"q\":\"连衣裙 中长款\",\"params\":[{\"key\":\"from\",\"value\":\"tips_1\"},{\"key\":\"vClickTrace\",\"value\":\"%7B%22tips_oriq%22%3A%22%E8%BF%9E%E8%A1%A3%E8%A3%99%22%2C%22tips_srppage%22%3A%222%22%2C%22tips_type%22%3A%221%22%2C%22tips_pos%22%3A%222%22%2C%22pre_rn%22%3A%220189c4d06e11f32262fa896f5f364f76%22%7D\"}]},{\"show\":\"假两件\",\"q\":\"连衣裙 假两件\",\"params\":[{\"key\":\"from\",\"value\":\"tips_1\"},{\"key\":\"vClickTrace\",\"value\":\"%7B%22tips_oriq%22%3A%22%E8%BF%9E%E8%A1%A3%E8%A3%99%22%2C%22tips_srppage%22%3A%222%22%2C%22tips_type%22%3A%221%22%2C%22tips_pos%22%3A%223%22%2C%22pre_rn%22%3A%220189c4d06e11f32262fa896f5f364f76%22%7D\"}]},{\"show\":\"A字款\",\"q\":\"连衣裙 A字款\",\"params\":[{\"key\":\"from\",\"value\":\"tips_1\"},{\"key\":\"vClickTrace\",\"value\":\"%7B%22tips_oriq%22%3A%22%E8%BF%9E%E8%A1%A3%E8%A3%99%22%2C%22tips_srppage%22%3A%222%22%2C%22tips_type%22%3A%221%22%2C%22tips_pos%22%3A%224%22%2C%22pre_rn%22%3A%220189c4d06e11f32262fa896f5f364f76%22%7D\"}]},{\"show\":\"气质淑女\",\"q\":\"连衣裙 气质淑女\",\"params\":[{\"key\":\"from\",\"value\":\"tips_1\"},{\"key\":\"vClickTrace\",\"value\":\"%7B%22tips_oriq%22%3A%22%E8%BF%9E%E8%A1%A3%E8%A3%99%22%2C%22tips_srppage%22%3A%222%22%2C%22tips_type%22%3A%221%22%2C%22tips_pos%22%3A%225%22%2C%22pre_rn%22%3A%220189c4d06e11f32262fa896f5f364f76%22%7D\"}]}],\"pos\":\"3\",\"src\":\"graph\",\"topic\":\"细选" + f + "\",\"type\":\"1\",\"tItemType\":\"wx_text\",\"tShowTmpl\":\"wx_text\",\"rl\":\"query_type-1|tip_show_type-1|tip_show_page-2\"},\"status\":{\"layoutStyle\":1}}");
        }
        this.d.notifyItemInserted(this.c.size() - 1);
    }

    public final String l() {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("lite_template/case.js"), "UTF-8"));
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
            return sb.toString();
        }
        bufferedReader.close();
        return sb.toString();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            WXSDKEngine.registerModule("searchEvent", SearchModule.class);
        } catch (WXException e2) {
            e2.printStackTrace();
        }
        setContentView(com.supwisdom.zueb.R.layout.activity_slice_test);
        this.f3969a = (RecyclerView) findViewById(com.supwisdom.zueb.R.id.recycler_view);
        this.b = (TextView) findViewById(com.supwisdom.zueb.R.id.report_text);
        this.f3969a.setLayoutManager(new LinearLayoutManager(this));
        this.f3969a.addItemDecoration(new df(this, 1));
        b bVar = new b();
        this.d = bVar;
        this.f3969a.setAdapter(bVar);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Iterator<WXSDKInstance> it = this.f3970e.iterator();
        while (it.hasNext()) {
            it.next().destroy();
        }
        this.f3970e.clear();
    }
}
