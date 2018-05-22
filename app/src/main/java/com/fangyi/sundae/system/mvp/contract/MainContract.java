package com.fangyi.sundae.system.mvp.contract;

import com.fangyi.component_library.base.BasePresenter;
import com.fangyi.component_library.mvp.IModel;
import com.fangyi.component_library.mvp.IView;


/**
 * Create By admin On 2017/7/11
 * 功能：
 */
public interface MainContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
    }

    //方法
    abstract class Presenter extends BasePresenter<View, Model> {

    }
}
