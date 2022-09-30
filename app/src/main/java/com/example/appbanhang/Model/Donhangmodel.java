package com.example.appbanhang.Model;

import java.util.List;

public class Donhangmodel {
    boolean success;
    String message;
    List<Donhang> result;

    public Donhangmodel(boolean success, String message, List<Donhang> result) {
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

    public List<Donhang> getResult() {
        return result;
    }

    public void setResult(List<Donhang> result) {
        this.result = result;
    }
}
