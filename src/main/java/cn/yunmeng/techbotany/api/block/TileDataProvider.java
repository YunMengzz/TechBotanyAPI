package cn.yunmeng.techbotany.api.block;

public interface TileDataProvider {

    /**
     * 更新tileData数据或者新添加tileData数据
     * @param data 要更新/添加的Data
     * @return success or not
     */
    boolean addData(BlockTileData data);

    /**
     * remove Data
     * @param loc BlockLocation
     * @return 删除的Data
     */
    BlockTileData removeData(BlockLocation loc);

    /**
     * 获取data
     */
    BlockTileData getData(BlockLocation loc);

    /**
     * 判断在loc位置上是不是tb方块
     */
    boolean isTbBlock(BlockLocation loc);

}
