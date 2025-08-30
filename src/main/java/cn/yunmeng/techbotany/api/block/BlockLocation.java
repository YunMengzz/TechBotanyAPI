package cn.yunmeng.techbotany.api.block;

import org.bukkit.Location;
import org.bukkit.World;

public class BlockLocation {

    private int x;
    private int y;
    private int z;
    private World world;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockLocation that = (BlockLocation) o;
        return x == that.x && y == that.y && z == that.z && world.getName().equals(that.world.getName());
    }

    public BlockLocation(int x, int y, int z, World world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public BlockLocation(Location loc) {
        world = loc.getWorld();
        x = loc.getBlockX();
        y = loc.getBlockY();
        z = loc.getBlockZ();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public World getWorld() {
        return world;
    }
}
