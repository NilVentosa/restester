package xyz.ventosa.restester.runner.parsed;

import xyz.ventosa.restester.json.pojo.ResponsePOJO;

public class ExpectedResponse {
    private int code = -1;

    public ExpectedResponse(ResponsePOJO responsePOJO) {
        this.setCode(responsePOJO.getCode());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
