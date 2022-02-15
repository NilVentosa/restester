package xyz.ventosa.restester.json.pojo;

public class ResponsePOJO {
    private int code;
    private FieldPOJO[] fields;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public FieldPOJO[] getFields() {
        return fields;
    }

    public void setFields(FieldPOJO[] fields) {
        this.fields = fields;
    }
}
