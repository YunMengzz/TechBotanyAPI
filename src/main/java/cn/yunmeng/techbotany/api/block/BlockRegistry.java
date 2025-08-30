package cn.yunmeng.techbotany.api.block;

public interface BlockRegistry {

    /**
     * register block
     * @param block block which need to register.
     * @return success or not
     */
    boolean registerBlock(YBlock block);

    /**
     * 获取registryName对应的YBlock
     */
    YBlock getYBlock(String registryName);


}
