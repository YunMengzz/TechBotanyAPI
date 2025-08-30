package cn.yunmeng.techbotany.api.block;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * 新建的Block对应的类，每个新建的Block都要有一个YBlock
 * 享元
 */
public class YBlock {

    private Material material;
    private String registryName;
    private String localizeName;
    // 可采集等级，低于这个采集等级的时候在采集的时候会提醒一次
    private HarvestLevel harvestLevel;

    public BlockTileData createTileData(BlockLocation loc){
        return new BlockTileData(registryName, loc);
    }

    public YBlock(Material material){
        this.material = material;
    }

    public String getRegistryName() {
        return registryName;
    }

    public final void setRegistryName(String registryName) {
        this.registryName = registryName;
    }

    public String getLocalizeName() {
        return localizeName;
    }

    public final void setLocalizeName(String localizeName) {
        this.localizeName = localizeName;
    }

    public HarvestLevel getHarvestLevel() {
        return harvestLevel;
    }

    public final void setHarvestLevel(HarvestLevel harvestLevel) {
        this.harvestLevel = harvestLevel;
    }

    /**
     * 当方块被破坏的时候调用，drops是即将掉落的物品集合。
     * @param drops 掉落的物品
     * @param blockLoc 方块的位置
     * @param fortune 时运，没有时运默认为0
     * @param harLevel 玩家用的采集等级
     * @return 是否被取消 如果返回true将不会掉落drops
     */
    public boolean blockBreak(Player player, List<ItemStack> drops, BlockLocation blockLoc, int fortune, HarvestLevel harLevel){

        return false;
    }

    public void onBlockRightClick(){}

}
