package net.zoizoi.plugin.commandboard.System;

import net.zoizoi.plugin.commandboard.Logic.CommandBoardLogic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;

public class PluginEventsListener implements Listener {

    public PluginEventsListener() {
    }

    // 左クリック
    @EventHandler
    public void BlockDamageEvent(BlockDamageEvent e){
        if(CommandBoardLogic.CheckSign(e.getBlock())){
            CommandBoardLogic.OnLeftClick(e.getBlock());
        }
    }

    // 破壊
    @EventHandler
    public void BlockBreakEvent(BlockBreakEvent e){
        if(CommandBoardLogic.CheckSign(e.getBlock())){
            CommandBoardLogic.OnBreakSign(e);
        }
    }

    // 設置
    @EventHandler
    public void BlockPlaceEvent(BlockPlaceEvent e){
        if(CommandBoardLogic.CheckSign(e.getBlock())){
            CommandBoardLogic.OnMakeSign(e);
        }
    }

    // 右クリック
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent e){
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(CommandBoardLogic.CheckSign(e.getClickedBlock())){
            CommandBoardLogic.OnRightClick(e.getClickedBlock());
        }
    }
}
