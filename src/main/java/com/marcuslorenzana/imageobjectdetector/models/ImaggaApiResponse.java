package com.marcuslorenzana.imageobjectdetector.models;

public class ImaggaApiResponse {
    private ImaggaApiResult result;
    private ImaggaApiStatus status;

    public ImaggaApiResult getResult() {
        return result;
    }

    public void setResult(ImaggaApiResult result) {
        this.result = result;
    }

    public ImaggaApiStatus getStatus() {
        return status;
    }

    public void setStatus(ImaggaApiStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ImaggaApiResponse{" +
                "result=" + result +
                ", status=" + status +
                '}';
    }
}
