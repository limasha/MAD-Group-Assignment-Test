package com.example.quizapp;

public class Dialog {
    String message;
    private Dialog(String message)
    {
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message)
    {
        this.message=message;
    }
}