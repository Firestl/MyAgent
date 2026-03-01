package io.dcloud.media.video.ijkplayer.danmaku;

import cn.com.chinatelecom.account.api.a.d;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.util.JSUtil;
import java.io.IOException;
import java.util.Locale;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.parser.android.AndroidFileSource;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/* JADX INFO: loaded from: classes3.dex */
public class BiliDanmukuParser extends BaseDanmakuParser {
    public float mDispScaleX;
    public float mDispScaleY;

    public class XmlContentHandler extends DefaultHandler {
        public static final String TRUE_STRING = "true";
        public Danmakus result = null;
        public BaseDanmaku item = null;
        public boolean completed = false;
        public int index = 0;

        public XmlContentHandler() {
        }

        private String decodeXmlString(String str) {
            if (str.contains("&amp;")) {
                str = str.replace("&amp;", "&");
            }
            if (str.contains("&quot;")) {
                str = str.replace("&quot;", JSUtil.QUOTE);
            }
            if (str.contains("&gt;")) {
                str = str.replace("&gt;", Operators.G);
            }
            return str.contains("&lt;") ? str.replace("&lt;", Operators.L) : str;
        }

        /* JADX WARN: Removed duplicated region for block: B:47:0x0126  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x0134  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x0140  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x014c  */
        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void characters(char[] r30, int r31, int r32) {
            /*
                Method dump skipped, instruction units count: 545
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.media.video.ijkplayer.danmaku.BiliDanmukuParser.XmlContentHandler.characters(char[], int, int):void");
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endDocument() throws SAXException {
            this.completed = true;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) throws SAXException {
            BaseDanmaku baseDanmaku = this.item;
            if (baseDanmaku != null) {
                if (baseDanmaku.duration != null) {
                    if (str2.length() == 0) {
                        str2 = str3;
                    }
                    if (str2.equalsIgnoreCase(d.f1473a)) {
                        this.item.setTimer(BiliDanmukuParser.this.mTimer);
                        this.result.addItem(this.item);
                    }
                }
                this.item = null;
            }
        }

        public Danmakus getResult() {
            return this.result;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startDocument() throws SAXException {
            this.result = new Danmakus();
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (str2.length() == 0) {
                str2 = str3;
            }
            if (str2.toLowerCase(Locale.getDefault()).trim().equals(d.f1473a)) {
                String[] strArrSplit = attributes.getValue("p").split(",");
                if (strArrSplit.length > 0) {
                    long j = (long) (Float.parseFloat(strArrSplit[0]) * 1000.0f);
                    int i = Integer.parseInt(strArrSplit[1]);
                    float f = Float.parseFloat(strArrSplit[2]);
                    int i2 = (int) (((-16777216) | Long.parseLong(strArrSplit[3])) & (-1));
                    BaseDanmaku baseDanmakuCreateDanmaku = BiliDanmukuParser.this.mContext.mDanmakuFactory.createDanmaku(i, BiliDanmukuParser.this.mContext);
                    this.item = baseDanmakuCreateDanmaku;
                    if (baseDanmakuCreateDanmaku != null) {
                        baseDanmakuCreateDanmaku.setTime(j);
                        this.item.textSize = f * (BiliDanmukuParser.this.mDispDensity - 0.6f);
                        BaseDanmaku baseDanmaku = this.item;
                        baseDanmaku.textColor = i2;
                        baseDanmaku.textShadowColor = i2 <= -16777216 ? -1 : -16777216;
                    }
                }
            }
        }
    }

    static {
        System.setProperty("org.xml.sax.driver", "org.xmlpull.v1.sax2.Driver");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPercentageNumber(float f) {
        return f >= 0.0f && f <= 1.0f;
    }

    @Override // master.flame.danmaku.danmaku.parser.BaseDanmakuParser
    public BaseDanmakuParser setDisplayer(IDisplayer iDisplayer) {
        super.setDisplayer(iDisplayer);
        this.mDispScaleX = this.mDispWidth / 682.0f;
        this.mDispScaleY = this.mDispHeight / 438.0f;
        return this;
    }

    @Override // master.flame.danmaku.danmaku.parser.BaseDanmakuParser
    public Danmakus parse() {
        IDataSource<?> iDataSource = this.mDataSource;
        if (iDataSource == null) {
            return null;
        }
        AndroidFileSource androidFileSource = (AndroidFileSource) iDataSource;
        try {
            XMLReader xMLReaderCreateXMLReader = XMLReaderFactory.createXMLReader();
            XmlContentHandler xmlContentHandler = new XmlContentHandler();
            xMLReaderCreateXMLReader.setContentHandler(xmlContentHandler);
            xMLReaderCreateXMLReader.parse(new InputSource(androidFileSource.data()));
            return xmlContentHandler.getResult();
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        } catch (SAXException e3) {
            e3.printStackTrace();
            return null;
        }
    }
}
