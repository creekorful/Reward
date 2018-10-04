package fr.creekorful.reward.dao;

import java.util.List;

import fr.creekorful.reward.pojo.Player;

/**
 * Describe the reward list data access object (dao unit)
 */
public interface RewardListDao {

    /**
     * Load player from dao storage unit
     *
     * @return the list of wanted players (the reward list)
     */
    List<Player> loadRewardList();

    /**
     * Save player list to persist storage unit
     * This will erase (override) any previous data
     *
     * @param playersToSave the list of player to save
     */
    void saveRewardList(List<Player> playersToSave);
}
