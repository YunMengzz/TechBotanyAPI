package cn.yunmeng.techbotany.api.item;

import org.bukkit.inventory.ItemStack;

public interface ItemRegistry {

    /**
     * https://www.xbiquge.la/30/30557/23662742.html
     * 注册物品方法
     * @param item 该物品的registryName必须是唯一的
     * @return 注册是否成功
     */
    public abstract boolean registerItem(YItem item);

    /**
     * 获取单位数量的ItemStack方法
     * @param registryName 注册名
     * @return 该registryName对应的amount为1的ItemStack对象
     */
    public abstract ItemStack getItemStack(String registryName);

    /**
     *
     * @param registryName
     * @return
     */
    YItem getYItem(String registryName);


}
