package com.alexblackmore.recyclerviewpractise;

public class Sport {
    private String title;
    private String info;
    private String desc;
    private final int imageResource;

    public Sport(String title, String info, String desc, int imageResource) {
        this.title = title;
        this.info = info;
        this.desc = desc;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public String getDesc() {
        return desc;
    }

    public int getImageResource() {
        return imageResource;
    }
}
