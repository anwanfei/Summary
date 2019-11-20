package com.anfly.summary.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class FoodDbBean {
    @Id(autoincrement = true)
    private long id;
    private String url;
    private String title;
    private String des;
    @Generated(hash = 895849011)
    public FoodDbBean(long id, String url, String title, String des) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.des = des;
    }
    @Generated(hash = 649472808)
    public FoodDbBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDes() {
        return this.des;
    }
    public void setDes(String des) {
        this.des = des;
    }
}
