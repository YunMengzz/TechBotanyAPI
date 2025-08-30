package cn.yunmeng.techbotany.api;

import cn.yunmeng.techbotany.api.block.YBlockAPI;
import cn.yunmeng.techbotany.api.item.YItemAPI;
import cn.yunmeng.techbotany.block.impl.YBlockAPIImpl;
import cn.yunmeng.techbotany.item.impl.YItemAPIImpl;

public class TechBotanyAPI {

    public static YItemAPI getItemAPI(){
        return YItemAPIImpl.inst();
    }

    public static YBlockAPI getBlockAPI() {
        return YBlockAPIImpl.inst();
    }

}
