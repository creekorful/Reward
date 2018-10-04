package fr.creekorful.reward.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import fr.creekorful.reward.dao.RewardListDao;
import fr.creekorful.reward.pojo.Player;

/**
 * Used to manage logic layer to add / save / delete / players to and from the persistence unit
 * This allow caching of player
 */
public class RewardManager {

    private List<Player> cachedRewardList = new ArrayList<Player>();
    private RewardListDao rewardListDao;
    private Logger logger;

    /**
     * Instantiate the reward manager
     *
     * @param logger the plugin logger
     * @param rewardListDao the dao used to CRUD the reward list
     */
    public RewardManager(final Logger logger, final RewardListDao rewardListDao) {
        this.logger = logger;
        this.rewardListDao = rewardListDao;
        this.cachedRewardList = rewardListDao.loadRewardList();
    }

    /**
     * Add a player to the reward list
     *
     * @param player the player to add to the reward list
     */
    public void addPlayer(final Player player) {
        logger.info("Add player: " + player + " to reward list !");
        cachedRewardList.add(player);
    }

    /**
     * Remove a player from the reward list
     *
     * @param player the player to be removed from the reward list
     * @return true if the player has been removed successfully otherwise false
     */
    public boolean removePlayer(final Player player) {
        if (cachedRewardList.contains(player)) {
            logger.info("Remove player: " + player + " from reward list !");
            cachedRewardList.remove(player);
            return true;
        } else {
            logger.severe("Trying to remove non existing player: " + player + " from the reward list !");
            return false;
        }
    }

    /**
     * Remove player from the reward list using provided player name
     *
     * @param playerName the player to be removed name's
     * @return true if the player has been removed successfully otherwise false
     */
    public boolean removePlayer(final String playerName) {
        Optional<Player> player = cachedRewardList.stream().filter(p -> p.getName().equals(playerName)).findFirst();

        if (player.isPresent()) {
            return removePlayer(player.get());
        } else {
            return false;
        }
    }

    /**
     * Save (persist) the reward list into the storage unit
     * This will erase any previous data from the storage unit
     */
    public void saveRewardList() {
        logger.info("Saving " + cachedRewardList.size() + " players to reward list !");
        rewardListDao.saveRewardList(cachedRewardList);
    }

    /**
     * Reload the reward list from the storage unit
     * This will erase any previously loaded data
     */
    public void reloadRewardList() {
        logger.info("Reloading reward list from file");
        cachedRewardList = rewardListDao.loadRewardList();
    }

    /**
     * @return the reward list (from the cache)
     */
    public List<Player> getRewardedPlayers() {
        return new ArrayList<>(cachedRewardList);
    }
}
