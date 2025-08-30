package cn.yunmeng.techbotany.block.impl;

import cn.yunmeng.techbotany.api.TechBotanyAPI;
import cn.yunmeng.techbotany.api.block.BlockLocation;
import cn.yunmeng.techbotany.api.block.BlockTileData;
import cn.yunmeng.techbotany.api.block.TileDataProvider;
import cn.yunmeng.techbotany.block.tick.TickManager;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.HashMap;

public class TileDataProviderImpl implements TileDataProvider {

    private volatile static TileDataProvider instance;

    // 分世界存储，查找的时候更有效率
    private static HashMap<String, HashMap<BlockLocation, BlockTileData>> tileData;

    private TileDataProviderImpl(){};

    @Override
    public boolean addData(BlockTileData data) {
        if (data == null || data.getLoc() == null) return false;
        if (TechBotanyAPI.getBlockAPI().getBlockRegistry().getYBlock(data.getRegistryName()) != null) {
            BlockLocation loc = data.getLoc();
            if (loc.getWorld() == null) return false;
            tileData.get(loc.getWorld().getName()).put(loc,data);
            TickManager.addTickFlush(data);
            return true;
        }

        return false;
    }

    @Override
    public BlockTileData removeData(BlockLocation loc) {
        if (loc == null || loc.getWorld() == null) return null;
        // remove tick flush
        TickManager.removeTickFlush(loc);
        return tileData.get(loc.getWorld().getName()).remove(loc);
    }

    @Override
    public BlockTileData getData(BlockLocation loc) {
        if (loc == null || loc.getWorld() == null) return null;
        return tileData.get(loc.getWorld().getName()).get(loc);
    }

    @Override
    public boolean isTbBlock(BlockLocation loc) {
        if (loc == null || loc.getWorld() == null) return false;
        return tileData.get(loc.getWorld().getName()).containsKey(loc);
    }

    /**
     * 初始化方法
     */
    public static void init(){
        tileData = new HashMap<>();
        for (World world : Bukkit.getWorlds()) {
            tileData.put(world.getName(), new HashMap<BlockLocation, BlockTileData>());
        }
    }

    /**
     * singleton
     * @return instance
     */
    public static TileDataProvider inst(){
        if (instance == null) {
            synchronized (TileDataProviderImpl.class) {
                if (instance == null) {
                    instance = new TileDataProviderImpl();
                }
            }
        }
        return instance;
    }



}
