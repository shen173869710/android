package com.auto.di.guan.entity;

public class BengEvent {
    private int postion;
    private boolean isOpen;

    public BengEvent(int postion, boolean isOpen) {
        this.postion = postion;
        this.isOpen = isOpen;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
