package com.example.electricityproject.data.bean;

import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/20
 *@Time: 11:22:09
 *@Description:
 * */
public class MyMoneyBean {

    /**
     * result : {"balance":99999343,"detailList":[{"amount":139,"consumerTime":1563845695000,"orderId":"201907221718083386602","userId":6602},{"amount":139,"consumerTime":1563787375000,"orderId":"201907221711487746602","userId":6602},{"amount":189,"consumerTime":1563776911000,"orderId":"201907161443503316602","userId":6602},{"amount":189,"consumerTime":1563776880000,"orderId":"201907161513382726602","userId":6602}]}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * balance : 99999343
         * detailList : [{"amount":139,"consumerTime":1563845695000,"orderId":"201907221718083386602","userId":6602},{"amount":139,"consumerTime":1563787375000,"orderId":"201907221711487746602","userId":6602},{"amount":189,"consumerTime":1563776911000,"orderId":"201907161443503316602","userId":6602},{"amount":189,"consumerTime":1563776880000,"orderId":"201907161513382726602","userId":6602}]
         */

        private int balance;
        private List<DetailListBean> detailList;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            /**
             * amount : 139
             * consumerTime : 1563845695000
             * orderId : 201907221718083386602
             * userId : 6602
             */

            private int amount;
            private long consumerTime;
            private String orderId;
            private int userId;

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public long getConsumerTime() {
                return consumerTime;
            }

            public void setConsumerTime(long consumerTime) {
                this.consumerTime = consumerTime;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }
}
