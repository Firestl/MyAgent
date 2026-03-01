package io.dcloud.common.util;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/* JADX INFO: loaded from: classes2.dex */
public class XmlUtil {
    public static final int CDATA = 4;
    public static final int ELEMENT = 1;
    public static final int TEXT = 3;

    public static class DHNode {
        public Node mNode;

        public boolean equals(Object obj) {
            return obj instanceof DHNode ? this.mNode.equals(((DHNode) obj).mNode) : super.equals(obj);
        }

        public DHNode(Node node) {
            this.mNode = node;
        }
    }

    public static DHNode XML_Parser(InputStream inputStream) {
        try {
            return new DHNode(((Document) XML_ParserDocument(inputStream).mNode).getDocumentElement());
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static DHNode XML_ParserDocument(InputStream inputStream) {
        try {
            DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
            documentBuilderFactoryNewInstance.setExpandEntityReferences(false);
            return new DHNode(documentBuilderFactoryNewInstance.newDocumentBuilder().parse(inputStream));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static void addChild(DHNode dHNode, DHNode dHNode2, int i) {
        dHNode.mNode.appendChild(dHNode2.mNode);
    }

    public static void attrFill2HashMap(HashMap<String, String> map, DHNode dHNode) {
        NamedNodeMap attributes;
        if (dHNode == null || map == null) {
            return;
        }
        Node node = dHNode.mNode;
        if (!(node instanceof Element) || (attributes = ((Element) node).getAttributes()) == null || attributes.getLength() <= 0) {
            return;
        }
        for (int i = 0; i < attributes.getLength(); i++) {
            Attr attr = (Attr) attributes.item(i);
            map.put(attr.getName(), attr.getValue());
        }
    }

    public static String elementToString(DHNode dHNode) {
        return new StringWriter().toString();
    }

    public static String getAttributeValue(DHNode dHNode, String str, String str2) {
        String attributeValue = getAttributeValue(dHNode, str);
        return attributeValue == null ? str2 : attributeValue;
    }

    public static ArrayList<String[]> getAttributes(DHNode dHNode) {
        NamedNodeMap attributes;
        ArrayList<String[]> arrayList = null;
        if (dHNode == null) {
            return null;
        }
        Node node = dHNode.mNode;
        if ((node instanceof Element) && (attributes = ((Element) node).getAttributes()) != null && attributes.getLength() > 0) {
            arrayList = new ArrayList<>();
            for (int i = 0; i < attributes.getLength(); i++) {
                Attr attr = (Attr) attributes.item(i);
                arrayList.add(new String[]{attr.getName(), attr.getValue(), attr.getNamespaceURI()});
            }
        }
        return arrayList;
    }

    public static DHNode getChild(DHNode dHNode, int i) {
        NodeList childNodes;
        if (dHNode == null || (childNodes = dHNode.mNode.getChildNodes()) == null) {
            return null;
        }
        return new DHNode(childNodes.item(i));
    }

    public static DHNode getElement(DHNode dHNode, String str) {
        Node nodeItem;
        if (dHNode == null) {
            return null;
        }
        Node node = dHNode.mNode;
        if (!(node instanceof Element) || (nodeItem = ((Element) node).getElementsByTagName(str).item(0)) == null) {
            return null;
        }
        return new DHNode(nodeItem);
    }

    public static DHNode getElementDocument(DHNode dHNode) {
        return new DHNode(dHNode.mNode.getOwnerDocument());
    }

    public static String getElementName(DHNode dHNode) {
        return dHNode.mNode.getNodeName();
    }

    public static ArrayList<DHNode> getElements(DHNode dHNode, String str) {
        NodeList elementsByTagName;
        if (dHNode == null) {
            return null;
        }
        Node node = dHNode.mNode;
        if (!(node instanceof Element) || (elementsByTagName = ((Element) node).getElementsByTagName(str)) == null) {
            return null;
        }
        ArrayList<DHNode> arrayList = new ArrayList<>(2);
        int length = elementsByTagName.getLength();
        for (int i = 0; i < length; i++) {
            arrayList.add(new DHNode(elementsByTagName.item(i)));
        }
        return arrayList;
    }

    public static int getNodeType(DHNode dHNode, int i) {
        if (dHNode == null) {
            return -1;
        }
        return dHNode.mNode.getNodeType();
    }

    public static DHNode getRootElement(DHNode dHNode) {
        try {
            Node node = dHNode.mNode;
            if (node instanceof Document) {
                return new DHNode(((Document) node).getDocumentElement());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public static String getText(DHNode dHNode) {
        StringBuffer stringBuffer = new StringBuffer();
        if (dHNode == null) {
            return stringBuffer.toString();
        }
        Node node = dHNode.mNode;
        if (node instanceof Element) {
            NodeList childNodes = ((Element) node).getChildNodes();
            int length = childNodes.getLength();
            for (int i = 0; i < length; i++) {
                stringBuffer.append(childNodes.item(i).getNodeValue());
            }
        }
        return stringBuffer.toString();
    }

    public static String getTextValue(DHNode dHNode) {
        return ((Text) dHNode.mNode).getNodeValue();
    }

    public static boolean isElement(Object obj) {
        return obj instanceof Element;
    }

    public static boolean isText(DHNode dHNode) {
        return dHNode.mNode instanceof Text;
    }

    public static DHNode newDocument() {
        DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
        documentBuilderFactoryNewInstance.setExpandEntityReferences(false);
        try {
            return new DHNode(documentBuilderFactoryNewInstance.newDocumentBuilder().newDocument());
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static DHNode newElement(DHNode dHNode, String str) {
        return new DHNode(dHNode.mNode.getOwnerDocument().createElement(str));
    }

    public static void removeAttribute(DHNode dHNode, String str) {
        Node node = dHNode.mNode;
        if (node == null) {
            return;
        }
        node.getAttributes().removeNamedItem(str);
    }

    public static void removeChild(DHNode dHNode, int i) {
        Node node = dHNode.mNode;
        node.removeChild(node.getChildNodes().item(i));
    }

    public static void setAttributeValue(DHNode dHNode, String str, String str2) {
        Node node = dHNode.mNode;
        if (node instanceof Element) {
            ((Element) node).setAttribute(str, str2);
        }
    }

    public static void setText(DHNode dHNode, String str) {
        dHNode.mNode.setNodeValue(str);
    }

    public static int getAttributeValue(DHNode dHNode, String str, int i) {
        try {
            return Integer.parseInt(getAttributeValue(dHNode, str, String.valueOf(i)));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            return i;
        }
    }

    public static void removeChild(DHNode dHNode, DHNode dHNode2) {
        if (dHNode2 == null) {
            return;
        }
        dHNode.mNode.removeChild(dHNode2.mNode);
    }

    public static String getAttributeValue(DHNode dHNode, String str) {
        if (dHNode == null) {
            return null;
        }
        Node node = dHNode.mNode;
        if (node instanceof Element) {
            return ((Element) node).getAttribute(str);
        }
        return null;
    }
}
