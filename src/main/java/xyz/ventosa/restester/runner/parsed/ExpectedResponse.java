package xyz.ventosa.restester.runner.parsed;

import xyz.ventosa.restester.json.pojo.ResponsePOJO;
import xyz.ventosa.restester.json.pojo.FieldPOJO;

import java.util.HashMap;
import java.util.Map;

public class ExpectedResponse {
    private int code = -1;
    private Map<String, String> fields;

    public ExpectedResponse(ResponsePOJO responsePOJO) {
        this.setCode(responsePOJO.getCode());
        if (responsePOJO.getFields() != null && responsePOJO.getFields().length > 0) {
            this.setFields(new HashMap<>());
            for (FieldPOJO fieldPOJO : responsePOJO.getFields()) {
                this.getFields().put(fieldPOJO.getKey(), fieldPOJO.getValue());
            }
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
}
