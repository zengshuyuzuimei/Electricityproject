package com.example.electricityproject.data.bean;

import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/20
 *@Time: 10:15:48
 *@Description:
 * */
public class MyCircleBean {

    /**
     * result : [{"commodityId":6,"content":"欢迎大家来我的圈子欣赏我的小吴，又是敲开心得一天呀！！！","createTime":1563635737000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2019-07-08/20190708164654.jpg","id":2416,"image":"http://172.17.8.100/images/small/circle_pic/2019-07-20/5729420190720101537.jpg","nickName":"wN_190E3","userId":6602},{"commodityId":5,"content":"今天的小吴特别帅好喜欢呀","createTime":1563635673000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2019-07-08/20190708164654.jpg","id":2415,"image":"http://172.17.8.100/images/small/circle_pic/2019-07-20/9823120190720101433.jpg","nickName":"wN_190E3","userId":6602},{"commodityId":1,"content":"给大家推荐一个超级超级好用的东西，我个人超级喜欢呀呀呀~~~","createTime":1563635413000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2019-07-08/20190708164654.jpg","id":2414,"image":"http://172.17.8.100/images/small/circle_pic/2019-07-20/1584220190720101013.png","nickName":"wN_190E3","userId":6602}]
     * message : 查詢成功
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
         * commodityId : 6
         * content : 欢迎大家来我的圈子欣赏我的小吴，又是敲开心得一天呀！！！
         * createTime : 1563635737000
         * greatNum : 0
         * headPic : http://172.17.8.100/images/small/head_pic/2019-07-08/20190708164654.jpg
         * id : 2416
         * image : http://172.17.8.100/images/small/circle_pic/2019-07-20/5729420190720101537.jpg
         * nickName : wN_190E3
         * userId : 6602
         */

        private int commodityId;
        private String content;
        private long createTime;
        private int greatNum;
        private String headPic;
        private int id;
        private String image;
        private String nickName;
        private int userId;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
