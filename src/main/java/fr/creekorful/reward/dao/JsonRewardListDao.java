package fr.creekorful.reward.dao;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.creekorful.reward.pojo.Player;

/**
 * Instance of RewardListDao that use a json file to persist the reward list
 */
public class JsonRewardListDao implements RewardListDao {

    private Logger logger;
    private File pluginDir;
    private String fileName;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Instantiate the json reward list dao
     *
     * @param logger the plugin logger
     * @param pluginDir the plugin data direction {@see org.bukkit.plugin.java.JavaPlugin#getDataFolder()}
     * @param fileName the name of the json file
     */
    public JsonRewardListDao(Logger logger, File pluginDir, String fileName) {
        this.logger = logger;
        this.pluginDir = pluginDir;
        this.fileName = fileName;

        // Check if file exist
        File file = new File(pluginDir, fileName);

        if (!file.exists()) {
            // todo create file
        }
    }

    /**
     * @see RewardListDao#loadRewardList()
     */
    public List<Player> loadRewardList() {
        File playerFile = new File(pluginDir, fileName);
        try {
            return Arrays.stream(objectMapper.readValue(playerFile, Player[].class)).collect(Collectors.toList());
        } catch (IOException e) {
            logger.severe(e.toString());
            return null;
        }
    }

    /**
     * @see RewardListDao#saveRewardList(List)
     */
    public void saveRewardList(List<Player> playersToSave) {
        File playerFile = new File(pluginDir, fileName);
        try {
            objectMapper.writeValue(playerFile, playersToSave);
        } catch (IOException e) {
            logger.severe(e.toString());
        }
    }
}
