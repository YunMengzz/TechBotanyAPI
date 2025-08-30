package cn.yunmeng.techbotany.api.nms;

import org.bukkit.Bukkit;

public class NMSUtils {

    private static final String versionPackage;
    private static final int minecraftVersion;

    static{
        String str = Bukkit.getServer().getClass().getPackage().getName();
        String[] args = str.split("\\.");
        versionPackage = args[3];
        minecraftVersion = Integer.parseInt(versionPackage.split("_")[1]);
    }

    public static Class<?> getNMSClass(String clsName){
        try {
            return Class.forName("net.minecraft.server." + versionPackage + "." + clsName);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Class<?> getOBCClass(String clsName){
        try {
            return Class.forName("org.bukkit.craftbukkit." + versionPackage + "." + clsName);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static boolean isUpper17(){
        return minecraftVersion >= 17;
    }

}
