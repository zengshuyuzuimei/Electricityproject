package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/20
 *@Time: 11:50:49
 *@Description:
 * */
public interface NewAddressContract {
    public interface NewAddressView{
        public void show(String string);
    }
    public interface NewAddressPresenter{
        public void attachView();
        public void detachView();
    }
    public interface NewAddressModel{
        public void request(int userId, String sessionId, String new_address, String new_name, String new_phone, String new_code, String new_detail, NewAddressCallBack newAddressCallBack);
        public interface NewAddressCallBack{
            public void getData(String string);
        }
    }
}
