package fr.creekorful.reward.command;

import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.creekorful.reward.manager.RewardManager;
import fr.creekorful.reward.pojo.Player;

public class RewardCommand implements CommandExecutor {

    interface SubCommand {
        boolean onCommand(CommandSender sender, RewardManager rewardManager, String[] args);
    }

    private RewardManager rewardManager;

    // Initialize sub commands
    private static Map<String, SubCommand> subCommands;
    private static Map<String, SubCommand> adminSubCommands;

    static {
        // User commands
        subCommands.put("list", (sender, rewardManager, args) -> {
            final int[] index = {0};

            rewardManager.getRewardedPlayers().forEach(player -> {
                sender.sendMessage(displayPlayer(player, index[0]));
                index[0]++;
            });

            return true;
        });

        // Admin commands
        adminSubCommands.put("save", (sender, rewardManager, args) -> {
            rewardManager.saveRewardList();
            return true;
        });

        adminSubCommands.put("remove", (sender, rewardManager, args) -> {
            if (rewardManager.removePlayer(args[1])) {
                sender.sendMessage("Player " + args[1] + " removed successfully !");
            } else {
                sender.sendMessage("Error while trying to remove player: " + args[1] + " : player is not in the list");
            }

            return true;
        });

        adminSubCommands.put("reload", (sender, rewardManager, args) -> {
            rewardManager.reloadRewardList();
            return true;
        });
    }

    public RewardCommand(RewardManager rewardManager) {
        this.rewardManager = rewardManager;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Validate provided arguments
        String subCommand = args[0];
        if (args.length == 0 || isCommandValid(subCommand)) {
            return false; // Should provide error message according to commands.yml definition
        }

        // If this is an normal command
        if (subCommands.keySet().contains(subCommand)) {
            subCommands.get(subCommand).onCommand(sender, rewardManager, args); // todo args - first one
        }

        // Else this is an admin commands
        else {
            if (!sender.isOp()) {
                sender.sendMessage(ChatColor.RED + " you must be op to run this command !");
                return false;
            }

            // Here process command
            adminSubCommands.get(subCommand).onCommand(sender, rewardManager, args);
        }

        return false;
    }

    private static boolean isCommandValid(String command) {
        return subCommands.keySet().contains(command) || adminSubCommands.keySet().contains(command);
    }

    private static String displayPlayer(Player player, int index) {
        return "[" + Integer.toString(index + 1) + "] " + ChatColor.WHITE + "Name: " + player.getName() +
                " severity: " + player.getSeverity().getColor() + player.getSeverity() + ChatColor.WHITE + " amount: " +
                formatAmount(player.getRansom());
    }

    private static String formatAmount(float amount) {
        // todo
        return null;
    }
}
