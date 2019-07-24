package com.example.electricityproject.data.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/19
 *@Time: 20:25:25
 *@Description:
 * */
@Entity
public class Shop {
    @Id(autoincrement = true)
    private Long id;
    private String shopInfo;
    @Generated(hash = 774635571)
    public Shop(Long id, String shopInfo) {
        this.id = id;
        this.shopInfo = shopInfo;
    }
    @Generated(hash = 633476670)
    public Shop() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getShopInfo() {
        return this.shopInfo;
    }
    public void setShopInfo(String shopInfo) {
        this.shopInfo = shopInfo;
    }
    
}
