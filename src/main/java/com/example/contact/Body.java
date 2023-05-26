package com.example.contact;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Body {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String tel;
    private String nianji;
    private String zhiwei;
    private String info;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Body(Long id, String name, String tel, String nianji, String zhiwei, String info) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.nianji = nianji;
        this.zhiwei = zhiwei;
        this.info = info;
    }

    public Body() {
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getNianji() {
        return nianji;
    }

    public String getZhiwei() {
        return zhiwei;
    }

    public String getInfo() {
        return info;
    }

    public static Long generateUniqueId() {
        // 在这里实现生成唯一ID的逻辑
        // 例如，使用当前时间戳作为唯一ID
        return System.currentTimeMillis();
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Body{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", nianji='" + nianji + '\'' +
                ", zhiwei='" + zhiwei + '\'' +
                ", info='" + info + '\'' +
                '}';
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
}
