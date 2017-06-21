package top.onee.ssm.expand.util;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Xml转换工具
 * Created by VOREVER on 12/06/2017.
 */
public class XmlUtil {

    /**
     * 将自定义数据对象转化为CDATA包裹的XML字符串
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static String objectToCdataXml(Object obj) throws IllegalAccessException {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.get(obj) != null && !"".equals(f.get(obj))) {
                String name = f.getName();
                XStreamAlias anno = f.getAnnotation(XStreamAlias.class);
                if (anno != null) {
                    name = anno.value();
                }
                sb.append("<" + name + ">");
                sb.append("<![CDATA[");
                sb.append(f.get(obj));
                sb.append("]]>");
                sb.append("</" + name + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 将自定义数据对象转化为XML字符串
     *
     * @param clazz  自定义数据类型
     * @param object 自定义数据对象
     * @return XML字符串
     * @throws JAXBException 异常
     */
    public static String objectToXml(Class clazz, Object object) throws JAXBException {
        String xml = null;
        JAXBContext context = JAXBContext.newInstance(clazz);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // 去掉生成xml的默认报文头
        m.setProperty(Marshaller.JAXB_FRAGMENT, true);
        Writer w = new StringWriter();
        m.marshal(object, w);
        xml = w.toString();
        return xml;
    }

    /**
     * xml字符串转为map对象
     *
     * @param xmlString
     * @return
     */
    public static Map<String, Object> xmlToMap(String xmlString) {
        try {
            //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream is = StringUtils.getStringStream(xmlString);
            Document document = builder.parse(is);

            //获取到document里面的全部结点
            NodeList allNodes = document.getFirstChild().getChildNodes();
            Node node;
            Map<String, Object> map = new HashMap<>();
            int i = 0;
            while (i < allNodes.getLength()) {
                node = allNodes.item(i);
                if (node instanceof Element) {
                    map.put(node.getNodeName(), node.getTextContent());
                }
                i++;
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
