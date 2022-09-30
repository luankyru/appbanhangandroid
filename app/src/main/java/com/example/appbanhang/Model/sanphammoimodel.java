package com.example.appbanhang.Model;

import java.util.List;

public class sanphammoimodel {
    boolean success;
    String message;
    List<sanpham> result;

    public sanphammoimodel(boolean success, String message, List<sanpham> result) {
        this.success = success;
        this.message = message;
        this.result = result;

    }

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

    public List<sanpham> getResult() {
        return result;
    }

    public void setResult(List<sanpham> result) {
        this.result = result;
    }
}
