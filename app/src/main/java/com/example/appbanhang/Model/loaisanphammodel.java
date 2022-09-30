package com.example.appbanhang.Model;

import java.util.List;

public class loaisanphammodel {
    boolean success;
    String message;
    List<loaisanpham> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<loaisanpham> getResult() {
        return result;
    }

    public void setResult(List<loaisanpham> result) {
        this.result = result;
    }
}
