package com.github.xiaogegechen.module_d.model.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 数据库中存放的book表
 */
@Entity
public class BookInDB {

    /**
     * 自增id
     */
    @Id
    private Long id;

    /**
     * 所属目录项的id
     */
    private int catalogId;

    /**
     * 书名
     */
    private String title;

    /**
     * 所属目录项，
     */
    private String catalog;

    /**
     * 标签
     */
    private String tags;

    /**
     * 	书名简介
     */
    private String sub1;

    /**
     * 图书内容简介
     */
    private String sub2;

    /**
     * 	图书封面url
     */
    private String img;

    /**
     * 	阅读人数
     */
    private String reading;

    /**
     * 	网购地址
     */
    private String online;

    /**
     * 发布时间
     */
    private String bytime;

    public BookInDB() {
    }

    @Generated(hash = 315013106)
    public BookInDB(Long id, int catalogId, String title, String catalog,
            String tags, String sub1, String sub2, String img, String reading,
            String online, String bytime) {
        this.id = id;
        this.catalogId = catalogId;
        this.title = title;
        this.catalog = catalog;
        this.tags = tags;
        this.sub1 = sub1;
        this.sub2 = sub2;
        this.img = img;
        this.reading = reading;
        this.online = online;
        this.bytime = bytime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSub1() {
        return sub1;
    }

    public void setSub1(String sub1) {
        this.sub1 = sub1;
    }

    public String getSub2() {
        return sub2;
    }

    public void setSub2(String sub2) {
        this.sub2 = sub2;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getBytime() {
        return bytime;
    }

    public void setBytime(String bytime) {
        this.bytime = bytime;
    }

    @Override
    public String toString() {
        return "BookInDB{" +
                "id=" + id +
                ", catalogId=" + catalogId +
                ", title='" + title + '\'' +
                ", catalog='" + catalog + '\'' +
                ", tags='" + tags + '\'' +
                ", sub1='" + sub1 + '\'' +
                ", sub2='" + sub2 + '\'' +
                ", img='" + img + '\'' +
                ", reading='" + reading + '\'' +
                ", online='" + online + '\'' +
                ", bytime='" + bytime + '\'' +
                '}';
    }
}
