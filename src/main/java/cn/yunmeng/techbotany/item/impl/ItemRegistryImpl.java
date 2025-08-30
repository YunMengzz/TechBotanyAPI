package cn.yunmeng.techbotany.item.impl;

import cn.yunmeng.techbotany.api.item.ItemRegistry;
import cn.yunmeng.techbotany.api.item.YItem;
import cn.yunmeng.techbotany.item.nms.NBTUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 单例即可
 * ItemRegistry控制类，实现物品库注册
 */
public class ItemRegistryImpl implements ItemRegistry {

    private static HashMap<String, YItem> items = new HashMap<>();
    private static HashMap<String, ItemStack> itemStacks = new HashMap<>();

    private volatile static ItemRegistryImpl instance;

    private ItemRegistryImpl(){}

    public boolean registerItem(YItem item) {
        if (item == null || items.containsKey(item.getRegistryName())) {
            return false;
        }
        ItemStack itemStack = new ItemStack(item.getMaterial(), 1);
        itemStack = NBTUtils.setRegistryName(itemStack, item.getRegistryName());
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(item.getLore());
        // TODO 获取lang的本地化名
        // meta.setDisplayName();
        itemStack.setItemMeta(meta);
        items.put(item.getRegistryName(), item);
        itemStacks.put(item.getRegistryName(), itemStack);
        return true;
    }

    public ItemStack getItemStack(String registryName) {
        return registryName == null ? null : itemStacks.get(registryName).clone();
    }

    public YItem getYItem(String registryName){
        return registryName == null ? null : items.get(registryName).cloneItem();
    }

    /**
     * 单例模式简单实现
     * @return ItemRegistry实现类的实例
     */
    public static ItemRegistry inst(){
        if (instance == null) {
            synchronized (ItemRegistryImpl.class) {
                if (instance == null) {
                    instance = new ItemRegistryImpl();
                }
            }
        }
        return instance;
    }


}
