package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 20:12:01
 *@Description:
 * */
public interface Home2Contract {
    public interface Home2View{
        public void show(String string);
        public void showGrid(String string);
        public void showLine(String string);
        public void showStag(String string);
    }
    public interface Home2Presenter{
        public void attachView();
        public void detachView();
    }
    public interface Home2Model{
        public void request(Home2CallBack home2CallBack);
        public interface Home2CallBack{
            public void getData(String string);
        }
        public void gridRequest(gridCallBack gridCallBack);
        public interface gridCallBack{
            public void getData(String string);
        }
        public void stagRequest(StagCallBack stagCallBack);
        public interface StagCallBack{
            public void getData(String string);
        }
        public void lineRequest(LineCallBack lineCallBack);
        public interface LineCallBack{
            public void getData(String string);
        }
    }
}
