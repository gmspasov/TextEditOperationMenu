package com.example.texteditoperationmenu;

public class StringWithIcon {
    private String text;
    private int iconId;
    private MainActivity.TextEditOperations operation;

    public MainActivity.TextEditOperations getOperation() {
        return operation;
    }

    public void setOperation(MainActivity.TextEditOperations operation) {
        this.operation = operation;
    }

    public String getText() {
        return text;
    }

    public int getIconId() {
        return iconId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public StringWithIcon(String text, int iconId, MainActivity.TextEditOperations operation) {
        this.text = text;
        this.iconId = iconId;
        this.operation = operation;
    }
}
