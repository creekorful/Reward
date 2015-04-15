package fr.creekorful.reward;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

import java.util.ArrayList;

// example:
// creekorful:HIGH:100,000,000$

public class functions
{
    private fr.creekorful.reward.Reward _MainClass;

    public functions(fr.creekorful.reward.Reward a)
    {
        this._MainClass = a;
    }

    public void addPlayerToRewardList(CommandSender sender, String playerName, String severity, String amount)
    {
        _MainClass.rewardPlayerName.add(playerName);
        _MainClass.rewardPlayerSeverity.add(severity);
        _MainClass.rewardPlayerAmount.add(amount);
        _MainClass.getLogger().info("Added " + playerName + " to reward list !");
        sender.sendMessage("Added " + playerName + " to reward list !");
    }

    public boolean removePlayerFromRewardList(CommandSender sender, String playername)
    {
        for(int i = 0; i < _MainClass.rewardPlayerName.size(); i++)
        {
            if(_MainClass.rewardPlayerName.get(i).equals(playername))
            {
                _MainClass.rewardPlayerName.remove(i);
                _MainClass.rewardPlayerSeverity.remove(i);
                _MainClass.rewardPlayerAmount.remove(i);
                _MainClass.getLogger().info("Player " + playername + " was removed from the reward list!");
                sender.sendMessage("Player " + playername + " was removed from the reward list!");
                return true;
            }
        }

        _MainClass.getLogger().info("Error, player: " + playername + " wasn't found !");
        sender.sendMessage("Error, player: " + playername + " wasn't found !");
        return false;
    }

    public void getAllRewardedPlayer(CommandSender playerSender)
    {
        if(_MainClass.rewardPlayerName.size() == 0)
            _MainClass.getLogger().info("Error no rewarded player !");

        playerSender.sendMessage("---------REWARD LIST---------");
        for(int i = 0; i < _MainClass.rewardPlayerName.size(); i++) {

            if(_MainClass.rewardPlayerSeverity.get(i).equals("LEGENDARY"))
                playerSender.sendMessage("[" + Integer.toString(i+1) + "] " + ChatColor.WHITE + "Name: " + _MainClass.rewardPlayerName.get(i) + " severity: " + ChatColor.GOLD + _MainClass.rewardPlayerSeverity.get(i) + ChatColor.WHITE + " amount: " + _MainClass.rewardPlayerAmount.get(i));
            else if(_MainClass.rewardPlayerSeverity.get(i).equals("VERY-HIGH"))
                playerSender.sendMessage("[" + Integer.toString(i+1) + "] " + ChatColor.WHITE + "Name: " + _MainClass.rewardPlayerName.get(i) + " severity: " + ChatColor.BLACK + _MainClass.rewardPlayerSeverity.get(i) + ChatColor.WHITE + " amount: " + _MainClass.rewardPlayerAmount.get(i));
            else if (_MainClass.rewardPlayerSeverity.get(i).equals("HIGH"))
                playerSender.sendMessage("[" + Integer.toString(i+1) + "] " + ChatColor.WHITE + "Name: " + _MainClass.rewardPlayerName.get(i) + " severity: " + ChatColor.RED + _MainClass.rewardPlayerSeverity.get(i) + ChatColor.WHITE + " amount: " + _MainClass.rewardPlayerAmount.get(i));
            else if (_MainClass.rewardPlayerSeverity.get(i).equals("MEDIUM"))
                playerSender.sendMessage("[" + Integer.toString(i+1) + "] " + ChatColor.WHITE + "Name: " + _MainClass.rewardPlayerName.get(i) + " severity: " + ChatColor.YELLOW + _MainClass.rewardPlayerSeverity.get(i) + ChatColor.WHITE + " amount: " + _MainClass.rewardPlayerAmount.get(i));
            else if (_MainClass.rewardPlayerSeverity.get(i).equals("LOW"))
                playerSender.sendMessage("[" + Integer.toString(i+1) + "] " + ChatColor.WHITE + "Name: " + _MainClass.rewardPlayerName.get(i) + " severity: " + ChatColor.BLUE + _MainClass.rewardPlayerSeverity.get(i) + ChatColor.WHITE + " amount: " + _MainClass.rewardPlayerAmount.get(i));
        }
    }

    public void initRewardList(String line)
    {
        _MainClass.getLogger().info("Current line: " + line);
        String player = line.substring(0, line.indexOf(':'));
        String severity = line.substring(line.indexOf(':')+1, line.lastIndexOf(':'));
        String amount = line.substring(line.lastIndexOf(':')+1, line.length());

        _MainClass.rewardPlayerName.add(player);
        _MainClass.rewardPlayerSeverity.add(severity);
        _MainClass.rewardPlayerAmount.add(amount);

        _MainClass.getLogger().info("Player: " + player + " severity: " + severity + " amount: " + amount + " added to reward list !");
    }

    private int[] sortRewardList() //used to sort the ArrayList by amout, return the position of the index arraylist by amount sorting (TODO)
    {
        int[] sortArrayPos = new int[_MainClass.rewardPlayerName.size()];
        return sortArrayPos;
    }

    private int stringAmountToInt(String amount) //convert amount like 1,000,000$ to int like 1000000
    {
        int ret;
        String retStr = "";
        for(int i = 0; i < amount.length(); i++)
        {
            if(amount.toCharArray()[i] != ',' && amount.toCharArray()[i] != '$')
                retStr += amount.toCharArray()[i];
        }

        ret = Integer.getInteger(retStr);
        return ret;
    }
}
