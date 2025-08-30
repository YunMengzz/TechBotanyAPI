package cn.yunmeng.techbotany.block.impl;

import cn.yunmeng.techbotany.api.block.BlockRegistry;
import cn.yunmeng.techbotany.api.block.YBlock;

import java.util.HashMap;

public class BlockRegistryImpl implements BlockRegistry {

    private static HashMap<String, YBlock> blocks = new HashMap<>();

    private volatile static BlockRegistry instance;

    private BlockRegistryImpl(){}


    @Override
    public boolean registerBlock(YBlock block) {
        if (block == null || block.getRegistryName() == null || blocks.containsKey(block.getRegistryName())) return false;
        blocks.put(block.getRegistryName(), block);
        // TODO localizedName
        return true;
    }

    @Override
    public YBlock getYBlock(String registryName) {
        return blocks.getOrDefault(registryName, null);
    }

    public static BlockRegistry inst(){
        if (instance == null) {
            synchronized (BlockRegistryImpl.class) {
                if (instance == null) {
                    instance = new BlockRegistryImpl();
                }
            }
        }
        return instance;
    }

}
