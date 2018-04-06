package com.example.nguyenthanhxuan.quanlychitieubaocao;

import java.util.Date;

/**
 * Created by Nguyen Thanh Xuan on 4/4/2018.
 */

public class Money {
    private int id;
    private String content;
    private String date;
    private Long money1;
    private String category;

    public Money() {
    }

    public Money(int id, String content, String date, Long money1, String category) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.money1 = money1;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getMoney1() {
        return money1;
    }

    public void setMoney1(Long money1) {
        this.money1 = money1;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "Money{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", money1=" + money1 +
                ", category='" + category + '\'' +
                '}';
    }
}
