package fr.creekorful.reward;

import org.bukkit.plugin.java.JavaPlugin;

import fr.creekorful.reward.command.RewardCommand;
import fr.creekorful.reward.manager.RewardManager;
import fr.creekorful.reward.dao.JsonRewardListDao;

public class Reward extends JavaPlugin {

    private RewardManager rewardManager;

    @Override
    public void onEnable() {
        rewardManager = new RewardManager(getLogger(),
                                          new JsonRewardListDao(getLogger(), getDataFolder(), "reward-list.json"));

        // Set up command
        getCommand("reward").setExecutor(new RewardCommand(rewardManager));
    }

    @Override
    public void onDisable() {
        rewardManager.saveRewardList();
        super.onDisable();
    }
}
