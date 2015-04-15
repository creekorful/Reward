package fr.creekorful.reward;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

// example:
// creekorful:HIGH:100,000,000$

public class functions
{
    private fr.creekorful.reward.Reward _MainClass;

    public functions(fr.creekorful.reward.Reward a)
    {
        this._MainClass = a;
    }

    public void addPlayerToRewardList(String playerName, String severity, String amount)
    {
        _MainClass.rewardPlayerName.add(playerName);
        _MainClass.rewardPlayerSeverity.add(severity);
        _MainClass.rewardPlayerAmount.add(amount);
        _MainClass.getLogger().info("Added " + playerName + " to reward list !");
    }

    public boolean removePlayerFromRewardList(String playername)
    {
        for(int i = 0; i < _MainClass.rewardPlayerName.size(); i++)
        {
            if(_MainClass.rewardPlayerName.get(i).equals(playername))
            {
                _MainClass.rewardPlayerName.remove(i);
                _MainClass.rewardPlayerSeverity.remove(i);
                _MainClass.rewardPlayerAmount.remove(i);
                _MainClass.getLogger().info("Player " + playername + " was removed !");
                return true;
            }
        }

        _MainClass.getLogger().info("Error, player: " + playername + " wasnt found !");
        return false;
    }

    private String parsePlayer(int playerIndex)
    {
        return "Slol";
    }

    public void getAllRewardedPlayer(CommandSender playerSender)
    {
        for(int i = 0; i < _MainClass.rewardPlayerName.size(); i++)
        {
            if(_MainClass.rewardPlayerSeverity.get(i).equals("HIGH"))
                playerSender.sendMessage(ChatColor.WHITE + "Name: " + _MainClass.rewardPlayerName.get(i) + " severity: " + ChatColor.RED + _MainClass.rewardPlayerSeverity + ChatColor.WHITE + " amount:" + _MainClass.rewardPlayerAmount);
            else if(_MainClass.rewardPlayerSeverity.get(i).equals("MEDIUM"))
                playerSender.sendMessage(ChatColor.WHITE + "Name: " + _MainClass.rewardPlayerName.get(i) + " severity: " + ChatColor.YELLOW + _MainClass.rewardPlayerSeverity + ChatColor.WHITE + " amount:" + _MainClass.rewardPlayerAmount);
            else if(_MainClass.rewardPlayerSeverity.get(i).equals("LOW"))
                playerSender.sendMessage(ChatColor.WHITE + "Name: " + _MainClass.rewardPlayerName.get(i) + " severity: " + ChatColor.BLUE + _MainClass.rewardPlayerSeverity + ChatColor.WHITE + " amount:" + _MainClass.rewardPlayerAmount);
        }
    }

    public void initRewardList(String line)
    {
        String player = line.substring(0, line.indexOf(':'));
        String severiry = line.substring(line.indexOf(':'), line.lastIndexOf(':'));
        String amount = line.substring(line.lastIndexOf(':'), line.length());

        _MainClass.rewardPlayerName.add(player);
        _MainClass.rewardPlayerSeverity.add(severiry);
        _MainClass.rewardPlayerAmount.add(amount);

        _MainClass.getLogger().info("Player " + player + " severity: " + severiry + " amount: " + amount + " added to reward list !");
    }
}
