package io.ventosa.restester;

import io.ventosa.restester.json.pojo.ResponsePOJO;

public class TestResponse {
    private int code;

    public TestResponse(ResponsePOJO responsePOJO) {
        this.setCode(responsePOJO.getCode());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}