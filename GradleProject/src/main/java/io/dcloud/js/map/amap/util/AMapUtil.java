package io.dcloud.js.map.amap.util;

import android.text.Html;
import android.text.Spanned;
import android.widget.EditText;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusStep;
import com.amap.api.services.route.RouteBusLineItem;
import com.amap.api.services.route.RouteRailwayItem;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.js.map.amap.adapter.AMapR;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AMapUtil {
    public static final String HtmlBlack = "#000000";
    public static final String HtmlGray = "#808080";

    public static boolean IsEmptyOrNullString(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String checkEditText(EditText editText) {
        return (editText == null || editText.getText() == null || editText.getText().toString().trim().equals("")) ? "" : editText.getText().toString().trim();
    }

    public static String colorFont(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<font color=");
        stringBuffer.append(str2);
        stringBuffer.append(Operators.G);
        stringBuffer.append(str);
        stringBuffer.append("</font>");
        return stringBuffer.toString();
    }

    public static ArrayList<LatLng> convertArrList(List<LatLonPoint> list) {
        ArrayList<LatLng> arrayList = new ArrayList<>();
        Iterator<LatLonPoint> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToLatLng(it.next()));
        }
        return arrayList;
    }

    public static LatLng convertToLatLng(LatLonPoint latLonPoint) {
        return new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
    }

    public static LatLonPoint convertToLatLonPoint(LatLng latLng) {
        return new LatLonPoint(latLng.latitude, latLng.longitude);
    }

    public static String convertToTime(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j));
    }

    public static String getBusPathDes(BusPath busPath) {
        if (busPath == null) {
            return "";
        }
        return String.valueOf(getFriendlyTime((int) busPath.getDuration()) + " | " + getFriendlyLength((int) busPath.getDistance()) + " | 步行" + getFriendlyLength((int) busPath.getWalkDistance()));
    }

    public static String getBusPathTitle(BusPath busPath) {
        List<BusStep> steps;
        if (busPath == null || (steps = busPath.getSteps()) == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (BusStep busStep : steps) {
            StringBuffer stringBuffer2 = new StringBuffer();
            if (busStep.getBusLines().size() > 0) {
                for (RouteBusLineItem routeBusLineItem : busStep.getBusLines()) {
                    if (routeBusLineItem != null) {
                        stringBuffer2.append(getSimpleBusLineName(routeBusLineItem.getBusLineName()));
                        stringBuffer2.append(" / ");
                    }
                }
                stringBuffer.append(stringBuffer2.substring(0, stringBuffer2.length() - 3));
                stringBuffer.append(" > ");
            }
            if (busStep.getRailway() != null) {
                RouteRailwayItem railway = busStep.getRailway();
                stringBuffer.append(railway.getTrip() + "(" + railway.getDeparturestop().getName() + " - " + railway.getArrivalstop().getName() + ")");
                stringBuffer.append(" > ");
            }
        }
        return stringBuffer.substring(0, stringBuffer.length() - 3);
    }

    public static int getDriveActionID(String str) {
        return (str == null || str.equals("")) ? AMapR.AMAP_DRAWABLE_DIR3 : "左转".equals(str) ? AMapR.AMAP_DRAWABLE_DIR2 : "右转".equals(str) ? AMapR.AMAP_DRAWABLE_DIR1 : ("向左前方行驶".equals(str) || "靠左".equals(str)) ? AMapR.AMAP_DRAWABLE_DIR6 : ("向右前方行驶".equals(str) || "靠右".equals(str)) ? AMapR.AMAP_DRAWABLE_DIR5 : ("向左后方行驶".equals(str) || "左转调头".equals(str)) ? AMapR.AMAP_DRAWABLE_DIR7 : "向右后方行驶".equals(str) ? AMapR.AMAP_DRAWABLE_DIR8 : "直行".equals(str) ? AMapR.AMAP_DRAWABLE_DIR3 : "减速行驶".equals(str) ? AMapR.AMAP_DRAWABLE_DIR4 : AMapR.AMAP_DRAWABLE_DIR3;
    }

    public static String getFriendlyLength(int i) {
        if (i > 10000) {
            return (i / 1000) + ChString.Kilometer;
        }
        if (i > 1000) {
            return new DecimalFormat("##0.0").format(i / 1000.0f) + ChString.Kilometer;
        }
        if (i > 100) {
            return ((i / 50) * 50) + ChString.Meter;
        }
        int i2 = (i / 10) * 10;
        return (i2 != 0 ? i2 : 10) + ChString.Meter;
    }

    public static String getFriendlyTime(int i) {
        if (i > 3600) {
            return (i / 3600) + "小时" + ((i % 3600) / 60) + "分钟";
        }
        if (i >= 60) {
            return (i / 60) + "分钟";
        }
        return i + "秒";
    }

    public static String getSimpleBusLineName(String str) {
        return str == null ? "" : str.replaceAll("\\(.*?\\)", "");
    }

    public static int getWalkActionID(String str) {
        return (str == null || str.equals("")) ? AMapR.AMAP_DRAWABLE_DIR13 : "左转".equals(str) ? AMapR.AMAP_DRAWABLE_DIR2 : "右转".equals(str) ? AMapR.AMAP_DRAWABLE_DIR1 : ("向左前方".equals(str) || "靠左".equals(str) || str.contains("向左前方")) ? AMapR.AMAP_DRAWABLE_DIR6 : ("向右前方".equals(str) || "靠右".equals(str) || str.contains("向右前方")) ? AMapR.AMAP_DRAWABLE_DIR5 : ("向左后方".equals(str) || str.contains("向左后方")) ? AMapR.AMAP_DRAWABLE_DIR7 : ("向右后方".equals(str) || str.contains("向右后方")) ? AMapR.AMAP_DRAWABLE_DIR8 : "直行".equals(str) ? AMapR.AMAP_DRAWABLE_DIR3 : "通过人行横道".equals(str) ? AMapR.AMAP_DRAWABLE_DIR9 : "通过过街天桥".equals(str) ? AMapR.AMAP_DRAWABLE_DIR11 : "通过地下通道".equals(str) ? AMapR.AMAP_DRAWABLE_DIR10 : AMapR.AMAP_DRAWABLE_DIR13;
    }

    public static String makeHtmlNewLine() {
        return "<br />";
    }

    public static String makeHtmlSpace(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("&nbsp;");
        }
        return sb.toString();
    }

    public static Spanned stringToSpan(String str) {
        if (str == null) {
            return null;
        }
        return Html.fromHtml(str.replace("\n", "<br />"));
    }
}
