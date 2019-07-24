package com.example.electricityproject.data.bean;

import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/19
 *@Time: 14:31:35
 *@Description:
 * */
public class AddressBean {


    /**
     * result : [{"address":"北京 海淀区 八维","createTime":1563306020000,"id":27674,"phone":"15712121111","realName":"吴亦凡的小可爱","userId":6602,"whetherDefault":1,"zipCode":"101010"},{"address":"开往你的心\n\n\n","createTime":1563553350000,"id":27942,"phone":"16621100714","realName":"胡效兴小可爱","userId":6602,"whetherDefault":2,"zipCode":"101010"},{"address":"开往你的心\n\n\n","createTime":1563553350000,"id":27943,"phone":"16621100714","realName":"胡效兴小可爱","userId":6602,"whetherDefault":2,"zipCode":"101010"},{"address":"开往你的心\n\n\n","createTime":1563553353000,"id":27944,"phone":"16621100714","realName":"胡效兴小可爱","userId":6602,"whetherDefault":2,"zipCode":"101010"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * address : 北京 海淀区 八维
         * createTime : 1563306020000
         * id : 27674
         * phone : 15712121111
         * realName : 吴亦凡的小可爱
         * userId : 6602
         * whetherDefault : 1
         * zipCode : 101010
         */

        private String address;
        private long createTime;
        private int id;
        private String phone;
        private String realName;
        private int userId;
        private int whetherDefault;
        private String zipCode;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherDefault() {
            return whetherDefault;
        }

        public void setWhetherDefault(int whetherDefault) {
            this.whetherDefault = whetherDefault;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }
}
