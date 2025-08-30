package cn.yunmeng.techbotany.block.impl;

import cn.yunmeng.techbotany.api.block.*;

public class YBlockAPIImpl implements YBlockAPI {

    private static volatile YBlockAPI instance;

    private YBlockAPIImpl(){}

    public static YBlockAPI inst(){
        if (instance == null) {
            synchronized (YBlockAPIImpl.class) {
                if (instance == null) {
                    instance = new YBlockAPIImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public BlockRegistry getBlockRegistry() {
        return BlockRegistryImpl.inst();
    }

    @Override
    public TileDataProvider getTileDataProvider() {
        return TileDataProviderImpl.inst();
    }

}
