package cn.yunmeng.techbotany.item.nms;

import cn.yunmeng.techbotany.TechBotany;
import cn.yunmeng.techbotany.api.TechBotanyAPI;
import cn.yunmeng.techbotany.api.item.YItem;
import cn.yunmeng.techbotany.api.nms.NMSUtils;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NBTUtils {
    private static Class<?> craftIsCls;
    private static Class<?> nmsIsCls;
    private static Class<?> nbtTagCls;
    private static Class<?> nbtBaseCls;

    static{
        if (!NMSUtils.isUpper17()) {
            craftIsCls = NMSUtils.getOBCClass("inventory.CraftItemStack");
            nmsIsCls = NMSUtils.getNMSClass("ItemStack");
            nbtTagCls = NMSUtils.getNMSClass("NBTTagCompound");
            nbtBaseCls = NMSUtils.getNMSClass("NBTBase");
        }
    }

    public static ItemStack setNbt(ItemStack item, String key, String value) {
        if (item == null || key == null || value == null) return null;
        if (!NMSUtils.isUpper17()) {
            /*
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt == null) nbt = new NBTTagCompound();
                nbt.setString(key, value);
                nmsItem.setTag(nbt);
                return CraftItemStack.asBukkitCopy(nmsItem);
             */
            try {
                Method asNMSCopy = craftIsCls.getMethod("asNMSCopy", ItemStack.class);
                Object nmsItem = asNMSCopy.invoke(null, item);
                Object nbt = nmsIsCls.getMethod("getTag").invoke(nmsItem);
                if (nbt == null) nbt = nbtTagCls.getConstructor().newInstance();
                nbtTagCls.getMethod("setString", String.class, String.class).invoke(nbt, key, value);
                nmsIsCls.getMethod("setTag", nbtTagCls).invoke(nmsItem, nbt);
                return (ItemStack) craftIsCls.getMethod("asBukkitCopy", nmsIsCls).invoke(null, nmsItem);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        } else {
            // 1.17+ TODO

        }
        return null;
    }


    public static ItemStack setRegistryName(ItemStack item, String registryName){
        if (!NMSUtils.isUpper17()) {
            try {
                Method asNMSCopy = craftIsCls.getMethod("asNMSCopy", ItemStack.class);
                Object nmsItem = asNMSCopy.invoke(null, item);
                Object nbt = nmsIsCls.getMethod("getTag").invoke(nmsItem);
                if (nbt == null) nbt = nbtTagCls.getConstructor().newInstance();
                Object techBotany = nbtTagCls.getConstructor().newInstance();
                nbtTagCls.getMethod("setString", String.class, String.class).invoke(techBotany, "registryName", registryName);
                nbtTagCls.getMethod("set", String.class, nbtBaseCls).invoke(nbt, TechBotany.nbtTag, techBotany);
                nmsIsCls.getMethod("setTag", nbtTagCls).invoke(nmsItem, nbt);
                return (ItemStack) craftIsCls.getMethod("asBukkitCopy", nmsIsCls).invoke(null, nmsItem);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        } else {
            // TODO
        }
        return null;
    }

    /**
     * 获取item对应的tb YItem
     * @param item
     * @return 不是返回null，是返回YItem
     */
    public static YItem getTbItem(ItemStack item){
        if (item == null) return null;
        if (!isTbItem(item)) {
            return null;
        }
        if (!NMSUtils.isUpper17()) {
            try {
                Object nmsItem = craftIsCls.getMethod("asNMSCopy", ItemStack.class).invoke(null, item);
                Object nbt = nmsIsCls.getMethod("getTag").invoke(nmsItem);
                Object techBotany = nbtTagCls.getMethod("getCompound", String.class).invoke(nbt, TechBotany.nbtTag);
                if (techBotany == null) return null;
                String registryName = (String) nbtTagCls.getMethod("getString", String.class).invoke(techBotany, "registryName");
                if (registryName != null) {
                    return TechBotanyAPI.getItemAPI().getItemRegistry().getYItem(registryName);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else {
            //TODO 1.17+
        }
        return null;
    }

    /**
     * 判断item是否是TB的Item
     * @param item
     * @return
     */
    public static boolean isTbItem(ItemStack item){
        if (item == null) return false;
        if (!NMSUtils.isUpper17()) {
            try {
                Object nmsItem = craftIsCls.getMethod("asNMSCopy", ItemStack.class).invoke(null, item);
                Object nbt = nmsIsCls.getMethod("getTag").invoke(nmsItem);
                return (Boolean) nbtTagCls.getMethod("hasKey", String.class).invoke(nbt, TechBotany.nbtTag);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else {
            //TODO 1.17+
        }
        return false;
    }

}
