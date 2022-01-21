package cn.beichenhpy.utils.xml;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 修改记录
 *    修改人：beichenhpy 修改日期: 2022/1/21 14:48
 * </pre>
 */
public class XML2JSON {


    /**
     * xml->json
     *
     * @param xmlStr xml
     * @return json
     * @throws DocumentException xml处理异常
     */
    public static JSONObject xml2Json(String xmlStr) throws DocumentException {
        Document doc = DocumentHelper.parseText(xmlStr);
        JSONObject json = new JSONObject();
        doParseXmlElements(doc.getRootElement(), json);
        return json;
    }

    /**
     * 转换xml->json
     *
     * @param element xml的每个成员
     * @param json    空json
     */
    private static void doParseXmlElements(Element element, JSONObject json) {
        List<Element> chdEl = element.elements();
        for (Element e : chdEl) {
            if (!e.elements().isEmpty()) {
                JSONObject child = new JSONObject();
                doParseXmlElements(e, child);
                Object o = json.get(e.getName());
                if (o != null) {
                    JSONArray jsonArray = null;
                    if (o instanceof JSONObject) {
                        JSONObject jsonObject = (JSONObject) o;
                        json.remove(e.getName());
                        jsonArray = new JSONArray();
                        jsonArray.add(jsonObject);
                        jsonArray.add(child);
                    }
                    if (o instanceof JSONArray) {
                        jsonArray = (JSONArray) o;
                        jsonArray.add(child);
                    }
                    json.put(e.getName(), jsonArray);
                } else {
                    if (!child.isEmpty()) {
                        json.put(e.getName(), child);
                    }
                }
            } else {
                if (!e.getText().isEmpty()) {
                    json.put(e.getName(), e.getText());
                }
            }
        }
    }


    /**
     * 递归找值
     *
     * @param object xml转换后的Json
     * @param name   需要找的key
     * @param result 返回找到的结果
     */
    public static void findValue(JSONObject object, String name, JSONObject result) {
        for (Map.Entry<String, Object> entry : object.entrySet()) {
            Object value = entry.getValue();
            if (entry.getKey().equals(name)) {
                result.put(name, value);
            } else if (value instanceof JSONObject) {
                findValue((JSONObject) value, name, result);
            } else if (value instanceof JSONArray) {
                JSONArray array = (JSONArray) value;
                for (Object o : array) {
                    findValue((JSONObject) o, name, result);
                }
            }
        }
    }




    public static void main(String[] args) throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "\n" +
                "<soapenv:Body>\n" +
                "\n" +
                "    <limitQueryJsonResponse xmlns=\"http://wsServer.webservice.eai.infoservice.com\">\n" +
                "\n" +
                "        <limitQueryJsonReturn>{&quot;STATUS&quot;:&quot;0&quot;,&quot;EXCEPTION&quot;:&quot;&#x63A5;&#x53E3;&#x6821;&#x9A8C;&#x5F02;&#x5E38;&quot;,&quot;jsonData&quot;:{&quot;bookResult&quot;:[]}}</limitQueryJsonReturn>\n" +
                "\n" +
                "    </limitQueryJsonResponse>\n" +
                "\n" +
                "\n" +
                "    <limitQueryJsonResponse xmlns=\"http://wsServer.webservice.eai.infoservice.com\">\n" +
                "\n" +
                "        <limitQueryJsonReturn>{&quot;STATUS&quot;:&quot;0&quot;,&quot;EXCEPTION&quot;:&quot;&#x63A5;&#x53E3;&#x6821;&#x9A8C;&#x5F02;&#x5E38;&quot;,&quot;jsonData&quot;:{&quot;bookResult&quot;:[]}}</limitQueryJsonReturn>\n" +
                "\n" +
                "    </limitQueryJsonResponse>\n" +
                "\n" +
                "</soapenv:Body>\n" +
                "\n" +
                "</soapenv:Envelope>";

        JSONObject jsonObject = xml2Json(xml);
        System.out.println(jsonObject);
        JSONObject result = new JSONObject();
        findValue(jsonObject, "limitQueryJsonReturn", result);
        System.out.println(result);

    }

}
