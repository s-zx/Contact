package com.example.contact;

import lombok.Data;

@Data
public class InfoVO {
    private Long id;
    private String name;
    private String tel;
    private String nianji;
    private String zhiwei;
    private String info;

    // 省略构造函数、getter和setter方法

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setNianji(String nianji) {
        this.nianji = nianji;
    }

    public void setZhiwei(String zhiwei) {
        this.zhiwei = zhiwei;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    // 可选的toString()方法，用于输出对象的字符串表示
    @Override
    public String toString() {
        return "InfoVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", nianji='" + nianji + '\'' +
                ", zhiwei='" + zhiwei + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}

