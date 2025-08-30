package cn.yunmeng.techbotany.api.item;

import cn.yunmeng.techbotany.api.block.YBlock;
import cn.yunmeng.techbotany.api.event.TbPlayerRightClickEvent;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 新建的item对应的类，所有的新建item都要继承这个类override方法。
 * 享元。
 */
public class YItem {

    // 注册名，每个新创建的物品的registryName必须不同，否则注册无效
    private String registryName;
    // 本地化名，lang文件中的语言(对应显示的display)
    private String localizedName;
    // lore
    private List<String> lore;
    // 这个物品的材料。
    private Material material;
    // 这个物品对应是否有block形态
    // 默认是false，这里如果要为true 必须满足条件。
    // 1. 下面的block变量一定非空
    // 2. item的material必须要右键能放置方块(也就是能触发BlockPlaceEvent)
    private boolean hasBlock = false;
    // 这个地方设计的和forge不同，forge是把block和item进行了类似于绑定的操作
    // 这里运用item对应放置出来的block，block有掉落物品计算对应的方法。
    private YBlock block;

    public YItem(Material material) {
        this.material = material;
    }

    public void onItemRightClick(/*TbPlayerRightClickEvent event*/){

    }

    public void onHitEntity(){}

    public void onDestroyBlock(){}

    public String getRegistryName() {
        return registryName;
    }

    public final void setRegistryName(String registryName) {
        this.registryName = registryName;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public final void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public List<String> getLore() {
        return lore == null ? new ArrayList<String>() : lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public boolean isHasBlock() {
        return hasBlock;
    }

    public YBlock getBlock() {
        return block;
    }

    public void setBlock(YBlock block) {
        if (block != null) {
            this.block = block;
            this.hasBlock = true;
        }
    }

    public YItem cloneItem() {
        YItem item = new YItem(material);
        item.setRegistryName(registryName);
        item.setLocalizedName(localizedName);
        ArrayList<String> newLore = new ArrayList<String>();
        Collections.copy(newLore, lore);
        item.setLore(newLore);
        item.setBlock(block);
        return item;
    }
}
