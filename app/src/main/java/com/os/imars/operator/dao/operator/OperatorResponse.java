package com.os.imars.operator.dao.operator;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OperatorResponse implements Serializable {

    @SerializedName("response")
    private Response response;

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }


    public static class Response implements Serializable {

        @SerializedName("status")
        private int status;

        @SerializedName("message")
        private String message;

        @SerializedName("data")
        private OperatorData data;

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setData(OperatorData data) {
            this.data = data;
        }

        public OperatorData getData() {
            return data;
        }


    }
}
