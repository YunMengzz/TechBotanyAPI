package cn.yunmeng.techbotany.api.block;

import cn.yunmeng.techbotany.api.dao.DataSerializable;

import java.util.HashMap;

/**
 *
 * 每个在世界中的YBlock都有他自己的BlockTileData，用json存储，用于存储方块数据。
 * 也用于存储这个方块是不是Tb新加的方块
 * 若要每tick刷新，需实现ITickable
 * TODO 问题：如果material是sand or Anvil... can physics drop 的block怎么办(自己坐标会变更) 活塞推拉。
 */
public class BlockTileData implements DataSerializable {

    private String registryName;
    private BlockLocation loc;

    public BlockTileData(String registryName, BlockLocation loc) {
        this.registryName = registryName;
        this.loc = loc;
    }

    public BlockTileData(String registryName){
        this.registryName = registryName;
    }

    public BlockTileData(YBlock block, BlockLocation loc){
        if (block == null) return;
        registryName = block.getRegistryName();
        this.loc = loc;
    }

    @Override
    public HashMap<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("registryName", registryName);
        return map;
    }

    public static BlockTileData deserialize(HashMap<String, Object> map) {
        if (map == null) return null;
        return new BlockTileData((String) map.get("registryName"));
    }

    public String getRegistryName() {
        return registryName;
    }

    public BlockLocation getLoc() {
        return loc;
    }

    public void setLoc(BlockLocation loc) {
        this.loc = loc;
    }
}
