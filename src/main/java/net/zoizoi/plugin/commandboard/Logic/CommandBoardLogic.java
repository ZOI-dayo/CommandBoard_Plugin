package net.zoizoi.plugin.commandboard.Logic;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandBoardLogic {
    private static ArrayList<Material> signboards = new ArrayList<>(Arrays.asList(
            Material.SPRUCE_SIGN,
            Material.SPRUCE_WALL_SIGN,
            Material.ACACIA_SIGN,
            Material.ACACIA_WALL_SIGN,
            Material.BIRCH_SIGN,
            Material.BIRCH_WALL_SIGN,
            Material.DARK_OAK_SIGN,
            Material.DARK_OAK_WALL_SIGN,
            Material.JUNGLE_SIGN,
            Material.JUNGLE_WALL_SIGN,
            Material.OAK_SIGN,
            Material.OAK_WALL_SIGN
    ));

    public static boolean CheckSign(Block signboard) {
        if (signboards.contains(signboard.getType())) return false;
        Sign sign = (Sign) signboard.getState();
        String[] lines = sign.getLines(); // 全行取得
        String line = sign.getLine(0); // 引数は[0-3]
        if (line != "CommandBoard") return false;
        return true;
    }

    public static void OnMakeSign(SignChangeEvent e) {
        e.getPlayer().sendMessage("[CommandBoard] Create new sign.");
    }

    public static void OnBreakSign(BlockBreakEvent e) {
        e.getPlayer().sendMessage("[CommandBoard] Delete a sign.");
    }

    public static void OnLeftClick(Block signboard) {
        Sign sign = (Sign) signboard.getState();

        String[] lines = sign.getLines();//全行取得
        String line = sign.getLine(0);//引数は[0-3]

        sign.setLine(0, "0行目です");//行に文字列をセット

        sign.update(); //更新
    }

    public static void OnRightClick(Block signboard) {
        Sign sign = (Sign) signboard.getState();

        String[] lines = sign.getLines();//全行取得
        String line = sign.getLine(0);//引数は[0-3]

        sign.setLine(0, "0行目です");//行に文字列をセット

        sign.update(); //更新
    }
}
