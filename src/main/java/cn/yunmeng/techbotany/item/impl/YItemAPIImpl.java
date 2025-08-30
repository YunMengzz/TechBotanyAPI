package cn.yunmeng.techbotany.item.impl;

import cn.yunmeng.techbotany.api.item.ItemRegistry;
import cn.yunmeng.techbotany.api.item.YItem;
import cn.yunmeng.techbotany.api.item.YItemAPI;
import cn.yunmeng.techbotany.item.nms.NBTUtils;
import org.bukkit.inventory.ItemStack;

public class YItemAPIImpl implements YItemAPI {

    private volatile static YItemAPIImpl instance;

    private YItemAPIImpl(){}

    public ItemRegistry getItemRegistry() {
        return ItemRegistryImpl.inst();
    }

    public YItem getYItem(ItemStack item) {
        return NBTUtils.getTbItem(item);
    }

    public boolean isTbItem(ItemStack item) {
        return NBTUtils.isTbItem(item);
    }


    public static YItemAPI inst(){
        if (instance == null) {
            synchronized (ItemRegistryImpl.class) {
                if (instance == null) {
                    instance = new YItemAPIImpl();
                }
            }
        }
        return instance;
    }
}
