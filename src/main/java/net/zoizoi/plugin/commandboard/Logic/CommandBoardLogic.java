package net.zoizoi.plugin.commandboard.Logic;

import net.zoizoi.plugin.commandboard.System.PluginConfig;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

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

    public enum Mode {
        OnChange,
        OnClick;
    }

    public static Mode StringToMode(String str) {
        if (str.equals("OnChange")) {
            return Mode.OnChange;
        }
        if (str.equals("OnClick")) {
            return Mode.OnClick;
        }
        return null;
    }

    private static LinkedHashMap<Sign, String> Signs = new LinkedHashMap<>(); // 看板の座標とidの関係
    private static LinkedHashMap<String, Number> Places = new LinkedHashMap<>(); // idとselectorの値の関係

    // それが、このプラグインが扱う看板かどうかの検査
    public static boolean CheckSign(Block signboard) {
        if (signboards.contains(signboard.getType())) return false;
        Sign sign = (Sign) signboard.getState();
        String[] lines = sign.getLines();
        if (lines[0] != "CommandBoard") return false;
        return true;
    }

    // 看板の設置時
    public static void OnMakeSign(BlockPlaceEvent e) {
        e.getPlayer().sendMessage("[CommandBoard] Create new sign.");
        Sign sign = (Sign) e.getBlock().getState();
        String ID = sign.getLine(1);
        if (!PluginConfig.config.contains("commands." + ID)) {
            e.getPlayer().sendMessage("[CommandBoard] コンフィグに設定が記入されていないため看板が作成できません。");
            return;
        }
        Signs.put(sign, ID);
        String LineText = PluginConfig.config.getString("commands." + ID + ".selectors.1.text");
        assert LineText != null;
        sign.setLine(1, LineText);
        sign.update(); //更新
        Places.replace(ID, 1);
    }

    // 看板の破壊時
    public static void OnBreakSign(BlockBreakEvent e) {
        Sign sign = (Sign) e.getBlock().getState();
        e.getPlayer().sendMessage("[CommandBoard] Delete a sign.");
        Signs.remove(sign);
    }

    // 看板が左クリックされた時のイベント
    public static void OnLeftClick(Block signboard, Player player) {
        Sign sign = (Sign) signboard.getState();

        String[] lines = sign.getLines();
        String ID = lines[1];
        String command = PluginConfig.config.getString("commands." + ID + ".selectors." + Places.get(ID) + ".command");
        assert command != null;
        player.performCommand(command);
    }

    // 看板が右クリックされた時のイベント
    public static void OnRightClick(Block signboard, Player player) {
        Sign sign = (Sign) signboard.getState();

        String[] lines = sign.getLines();
        String ID = lines[1];
        Mode mode = StringToMode(lines[2]);

        // Change
        int ChangeID;
        if (PluginConfig.config.contains("commands." + ID + ".selectors." + (Integer.parseInt(Places.get(ID).toString()) + 1))) {
            ChangeID = Integer.parseInt(Places.get(ID).toString()) + 1;
        } else {
            ChangeID = 1;
        }
        sign.setLine(1, "commands." + ID + ".selectors." + ChangeID + ".text");
        sign.update(); //更新
        Places.replace(ID, ChangeID);

        // Run
        if (mode == Mode.OnChange) {
            String command = PluginConfig.config.getString("commands." + ID + ".selectors." + Places.get(ID) + ".command");
            assert command != null;
            player.performCommand(command);
        }
    }
}