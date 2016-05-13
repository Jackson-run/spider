package com.xuxue.tools.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * 支持路径查找的Json对象
 * Created by HanHan on 2016/5/13.
 */
public class JSONPathObject {

    public static final char ARRAYBEGIN = '[';

    public static final char ARRAYEND = ']';

    public static final char OBJECTBEGIN = '{';

    public static final char OBJECTEND = '}';

    private final Object json;


    public JSONPathObject(String jsonString) {
        jsonString = jsonString.trim();
        if (jsonString.charAt(0) == ARRAYBEGIN &&
                jsonString.charAt(jsonString.length() - 1) == ARRAYEND) {
            json = new JSONArray(jsonString);
        } else if (jsonString.charAt(0) == OBJECTBEGIN &&
                jsonString.charAt(jsonString.length() - 1) == OBJECTEND) {
            json = new JSONObject(jsonString);
        } else {
            json = new Object();
        }
    }

    /**
     * 根据路径返回在这个json上查找得到的内容
     *
     * @param path
     * @return
     */
    public Object searchPath(String path) {
        List<SearchElement> elements = parsePath(path);
        Object temp = null;
        SearchElementType type = null;
        for (int i = 0; i < elements.size(); i++) {
            System.out.println("for call" + elements.size());
            type = elements.get(i).getSearchElementType();
            if (type == SearchElementType.ARRAY && i != 0) {
                JSONArray array = (JSONArray) temp;
                temp = array.get(elements.get(i).getIndexByNumber());
            } else if (type == SearchElementType.OBJECT & i != 0) {
                JSONObject obj = (JSONObject) temp;
                temp = obj.get(elements.get(i).getIndex());
            } else if (type == SearchElementType.ARRAY && i == 0) {
                JSONArray array = (JSONArray) json;
                temp = array.get(elements.get(i).getIndexByNumber());
            } else if (type == SearchElementType.OBJECT && i == 0) {
                JSONObject obj = (JSONObject) json;
                temp = obj.get(elements.get(i).getIndex());
            }
        }
        return temp;

    }

    /**
     * 将一个字符串解析成Json路径
     *
     * @param path
     * @return
     */
    private List<SearchElement> parsePath(String path) {
        path = path.substring(1, path.length() - 1);
        List<SearchElement> elements = new ArrayList<SearchElement>();
        String[] paths = path.split("\\]\\[");
        for (String p : paths) {
            SearchElement element = new SearchElement();
            if (p.contains("'") || p.contains("\"")) {
                element.setSearchElementType(SearchElementType.OBJECT);
                element.setIndex(p.replace("'", "").replace("\"", ""));
            } else {
                element.setSearchElementType(SearchElementType.ARRAY);
                element.setIndex(p);
            }
            elements.add(element);
        }
        return elements;
    }

    public enum SearchElementType {
        ARRAY,
        OBJECT
    }

    public static class SearchElement {

        private String index;

        private SearchElementType type;

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public SearchElementType getSearchElementType() {
            return this.type;
        }

        public void setSearchElementType(SearchElementType type) {
            this.type = type;
        }

        public int getIndexByNumber() {
            if (this.getSearchElementType() == SearchElementType.ARRAY) {
                return Integer.parseInt(index);
            }
            return -1;
        }

    }

}
