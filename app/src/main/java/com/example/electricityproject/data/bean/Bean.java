package com.example.electricityproject.data.bean;

import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/18
 *@Time: 09:06:06
 *@Description:购物车
 * */
public class Bean {

    /**
     * result : [{"categoryName":"美妆护肤","shoppingCartList":[{"commodityId":5,"commodityName":"双头两用修容笔","count":3,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg","price":39},{"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","count":4,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg","price":39},{"commodityId":7,"commodityName":"蓝色之恋","count":5,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/5/1.jpg","price":29},{"commodityId":8,"commodityName":"Lara style蜜颊润泽腮红","count":6,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/6/1.jpg","price":19},{"commodityId":10,"commodityName":"BYPHASSE蓓昂丝温和清洁净肤保湿卸妆水","count":8,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/7/1.jpg","price":69}]}]
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
         * categoryName : 美妆护肤
         * shoppingCartList : [{"commodityId":5,"commodityName":"双头两用修容笔","count":3,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg","price":39},{"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","count":4,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg","price":39},{"commodityId":7,"commodityName":"蓝色之恋","count":5,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/5/1.jpg","price":29},{"commodityId":8,"commodityName":"Lara style蜜颊润泽腮红","count":6,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/6/1.jpg","price":19},{"commodityId":10,"commodityName":"BYPHASSE蓓昂丝温和清洁净肤保湿卸妆水","count":8,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/7/1.jpg","price":69}]
         */

        private String categoryName;
        private List<ShoppingCartListBean> shoppingCartList;
        private boolean parent_select;

        public boolean getParent_select() {
            return parent_select;
        }

        public void setParent_select(boolean parent_select) {
            this.parent_select = parent_select;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public List<ShoppingCartListBean> getShoppingCartList() {
            return shoppingCartList;
        }

        public void setShoppingCartList(List<ShoppingCartListBean> shoppingCartList) {
            this.shoppingCartList = shoppingCartList;
        }

        public static class ShoppingCartListBean {
            /**
             * commodityId : 5
             * commodityName : 双头两用修容笔
             * count : 3
             * pic : http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg
             * price : 39
             */

            private int commodityId;
            private String commodityName;
            private int count;
            private String pic;
            private int price;
            private boolean child_select;

            public boolean getChild_select() {
                return child_select;
            }

            public void setChild_select(boolean child_select) {
                this.child_select = child_select;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }
    }
}
