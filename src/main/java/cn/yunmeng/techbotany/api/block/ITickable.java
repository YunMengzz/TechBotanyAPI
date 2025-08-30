package cn.yunmeng.techbotany.api.block;

/**
 * 用于标识BlockTileData需不需要每tick刷新，每tick刷新执行的指令
 */
public interface ITickable {

    void tick();

}
