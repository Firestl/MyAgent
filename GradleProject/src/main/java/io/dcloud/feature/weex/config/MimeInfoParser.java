package io.dcloud.feature.weex.config;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.taobao.weex.WXSDKInstance;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.HashSet;

/* JADX INFO: loaded from: classes3.dex */
public class MimeInfoParser {
    public HashSet<String> registerFileTypeSet = new HashSet<>();
    public HashSet<String> registerFileFormatSet = new HashSet<>();
    public HashSet<String> registerEncodeFormatSet = new HashSet<>();
    public final String DATA_FLAG = "data:";
    public final String FILE_TYPE_FLAG = "/";
    public final String FILE_FORMAT_FLAG = ";";
    public final String ENCODE_FORMAT_FLAG = ",";

    public static class MimeInfo {
        public String dataContent;
        public String encodeFormat;
        public String fileFormat;
        public String fileType;

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isValid(MimeInfoParser mimeInfoParser) {
            return (TextUtils.isEmpty(this.fileType) || TextUtils.isEmpty(this.fileFormat) || TextUtils.isEmpty(this.encodeFormat) || TextUtils.isEmpty(this.dataContent) || !mimeInfoParser.registerFileTypeSet.contains(this.fileType) || !mimeInfoParser.registerFileFormatSet.contains(this.fileFormat) || !mimeInfoParser.registerEncodeFormatSet.contains(this.encodeFormat)) ? false : true;
        }

        public InputStream getDataBytes(WXSDKInstance wXSDKInstance) {
            if ("base64".equals(this.encodeFormat)) {
                return new ByteArrayInputStream(Base64.decode(this.dataContent, 2));
            }
            if ("text".equals(this.encodeFormat)) {
                return new ByteArrayInputStream(this.dataContent.getBytes());
            }
            if (!"filepath".equals(this.encodeFormat) || wXSDKInstance == null) {
                return null;
            }
            try {
                return new FileInputStream(new File(new URI(wXSDKInstance.getURIAdapter().rewrite(wXSDKInstance, "file", Uri.parse(this.dataContent)).toString())));
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        public String getDataContent() {
            return this.dataContent;
        }

        public String getEncodeFormat() {
            return this.encodeFormat;
        }

        public String getFileFormat() {
            return this.fileFormat;
        }

        public String getFileType() {
            return this.fileType;
        }

        public void setDataContent(String str) {
            this.dataContent = str;
        }

        public void setEncodeFormat(String str) {
            this.encodeFormat = str;
        }

        public void setFileFormat(String str) {
            this.fileFormat = str;
        }

        public void setFileType(String str) {
            this.fileType = str;
        }
    }

    public MimeInfoParser() {
        this.registerFileTypeSet.add("keystore");
        this.registerFileTypeSet.add("cert");
        this.registerFileFormatSet.add("p12");
        this.registerFileFormatSet.add("pem");
        this.registerEncodeFormatSet.add("filepath");
        this.registerEncodeFormatSet.add("base64");
        this.registerEncodeFormatSet.add("text");
    }

    public static InputStream getFilePathStream(WXSDKInstance wXSDKInstance, String str) {
        if (wXSDKInstance != null) {
            try {
                File file = new File(new URI(wXSDKInstance.getURIAdapter().rewrite(wXSDKInstance, "file", Uri.parse(str)).toString()));
                if (file.exists()) {
                    return new FileInputStream(file);
                }
                File file2 = new File(UniPathParser.getAndroidPath(str));
                if (file2.exists()) {
                    return new FileInputStream(file2);
                }
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static MimeInfoParser getInstance() {
        return new MimeInfoParser();
    }

    private MimeInfo parseMimeInfo(String str) {
        MimeInfo mimeInfo = new MimeInfo();
        if (!str.startsWith("data:") || !str.contains("/") || !str.contains(";") || !str.contains(",")) {
            return null;
        }
        String strSubstring = str.substring(5);
        String strSubstring2 = strSubstring.substring(0, strSubstring.indexOf("/"));
        mimeInfo.fileType = strSubstring2;
        String strSubstring3 = strSubstring.substring((strSubstring2 + "/").length());
        String strSubstring4 = strSubstring3.substring(0, strSubstring3.indexOf(";"));
        mimeInfo.fileFormat = strSubstring4;
        String strSubstring5 = strSubstring3.substring((strSubstring4 + ";").length());
        String strSubstring6 = strSubstring5.substring(0, strSubstring5.indexOf(","));
        mimeInfo.encodeFormat = strSubstring6;
        mimeInfo.dataContent = strSubstring5.substring((strSubstring6 + ",").length());
        return mimeInfo;
    }

    public MimeInfo obtainMimeInfo(String str) {
        MimeInfo mimeInfo = parseMimeInfo(str);
        if (mimeInfo == null || mimeInfo.isValid(this)) {
            return mimeInfo;
        }
        return null;
    }
}
