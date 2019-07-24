package com.example.electricityproject.data.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/24
 *@Time: 09:06:54
 *@Description:
 * */
@Entity
public class Flow {
    @Id(autoincrement = true)
    private Long id;
    private String flows;
    @Generated(hash = 446253345)
    public Flow(Long id, String flows) {
        this.id = id;
        this.flows = flows;
    }
    @Generated(hash = 1952844042)
    public Flow() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFlows() {
        return this.flows;
    }
    public void setFlows(String flows) {
        this.flows = flows;
    }
    
}
