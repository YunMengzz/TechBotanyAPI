package cn.yunmeng.techbotany.api.event;

import cn.yunmeng.techbotany.api.block.YBlock;
import cn.yunmeng.techbotany.api.item.YItem;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;

public class TbPlayerRightClickEvent extends Event {

    private static final HandlerList list = new HandlerList();

    // interactEvent 封装一堆bukkit内容
    private PlayerInteractEvent interactEvent;
    // 手上有的物品
    private YItem yItem;
    private YBlock clickedYBlock;

    public HandlerList getHandlers() {
        return list;
    }

    public TbPlayerRightClickEvent(PlayerInteractEvent interactEvent, YItem yItem, YBlock clickedYBlock) {
        this.interactEvent = interactEvent;
        this.yItem = yItem;
        this.clickedYBlock = clickedYBlock;
    }

    public static HandlerList getHandlerList() {
        return list;
    }

    public PlayerInteractEvent getInteractEvent() {
        return interactEvent;
    }

    public YItem getyItem() {
        return yItem;
    }

    public YBlock getClickedYBlock() {
        return clickedYBlock;
    }
}
