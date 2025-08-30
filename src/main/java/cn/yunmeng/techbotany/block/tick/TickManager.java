package cn.yunmeng.techbotany.block.tick;

import cn.yunmeng.techbotany.api.block.BlockLocation;
import cn.yunmeng.techbotany.api.block.BlockTileData;
import cn.yunmeng.techbotany.api.block.ITickable;

import java.util.HashMap;

/**
 * 管理tick刷新TileData类
 */
public class TickManager {

    // 存储需要每tick刷新的BlockTileData
    private static HashMap<BlockLocation, ITickable> map = new HashMap<>();

    public static void addTickFlush(BlockTileData data){
        if (data == null) return;
        /*for (BlockTileData d : list) {
            if (d.getLoc().equals(data.getLoc())) {
                removeList.add(d);
            }
        }
        for (BlockTileData d : removeList) {
            list.remove(d);
        }
        list.add(data);*/
        if (data instanceof ITickable) {
            map.put(data.getLoc(), (ITickable) data);
        }


    }


    public static void removeTickFlush(BlockLocation loc){
        map.remove(loc);
    }

    public static void executeAll(){
        for (BlockLocation loc : map.keySet()) {
            ITickable data = map.get(loc);
            if (data != null){
                data.tick();
            }
        }
    }

}
