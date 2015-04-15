package fr.creekorful.reward;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;

public class Reward extends JavaPlugin
{
    static String version = "0.1";
    public static String rewardFileName = "reward.txt";
    public ArrayList<String> rewardPlayerName = new ArrayList<String>();
    public ArrayList<String> rewardPlayerSeverity = new ArrayList<String>();
    public ArrayList<String> rewardPlayerAmount = new ArrayList<String>();
    functions func = new functions(this);


    @Override
    public void onEnable() {
        saveDefaultConfig();

        try {

            BufferedReader waterBufRead = new BufferedReader(new InputStreamReader(new FileInputStream("plugins/Reward/" + rewardFileName)));
            String currentLine;

            //READ FILE AND PUT INTO ARRAYS
            while((currentLine = waterBufRead.readLine())!= null){
                func.initRewardList(currentLine);
            }
            waterBufRead.close();

        } catch (IOException e) { //si on n'arrive pas a ouvrir les fichiers
            File rewardFile = new File("plugins/Reward/" + rewardFileName);
            try {
                //on les crée
                rewardFile.createNewFile();
                onEnable(); //on rapelle la fonction enable
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        getLogger().info("Reward " + version + " enabled !");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disable Reward " + version + " !");

        FileWriter rewardWriter;
        try {

            rewardWriter = new FileWriter("plugins/Reward/" + rewardFileName);

            BufferedWriter rewardBufferedWriter = new BufferedWriter(rewardWriter);

            for(int i = 0; i < rewardPlayerName.size(); i++){
                rewardBufferedWriter.write(rewardPlayerName.get(i) + ":" + rewardPlayerSeverity.get(i) + ":" + rewardPlayerAmount.get(i));
                rewardBufferedWriter.newLine();
            }
            rewardBufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        saveDefaultConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("reward")) { // If the player typed /reward then do the following...

            if(args.length == 0){
                getLogger().info("Error correct usage /reward add ; /reward remove ; /reward list");
                return false;
            }

            if(!args[0].equals("list") && !args[0].equals("add") && !args[0].equals("remove")) //if the command is unknow
                getLogger().info("Unknow command. Command are /list ; /add ; /remove");

            if(args[0].equals("list")) {
                func.getAllRewardedPlayer(sender);
                return true;
            }

            if(sender.isOp()) //the other command require op access
            {
                if (args[0].equals("add")) {
                    if (args.length == 4) {
                        func.addPlayerToRewardList(sender, args[1], args[2], args[3]);
                        return true;
                    }

                } else if (args[0].equals("remove")) {
                    if (args.length == 2) {
                        func.removePlayerFromRewardList(sender, args[1]);
                        return true;
                    }
                }

                else if(args[0].equals("save"))
                {
                    FileWriter rewardWriter;
                    try {

                        rewardWriter = new FileWriter("plugins/Reward/" + rewardFileName);

                        BufferedWriter rewardBufferedWriter = new BufferedWriter(rewardWriter);

                        for(int i = 0; i < rewardPlayerName.size(); i++){
                            rewardBufferedWriter.write(rewardPlayerName.get(i) + ":" + rewardPlayerSeverity.get(i) + ":" + rewardPlayerAmount.get(i));
                            rewardBufferedWriter.newLine();
                        }
                        rewardBufferedWriter.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else if(args[0].equals("reload"))
                {

                    try {

                        BufferedReader waterBufRead = new BufferedReader(new InputStreamReader(new FileInputStream("plugins/Reward/" + rewardFileName)));
                        String currentLine;

                        //READ FILE AND PUT INTO ARRAYS
                        rewardPlayerName.clear();
                        rewardPlayerSeverity.clear();
                        rewardPlayerAmount.clear();
                        
                        while((currentLine = waterBufRead.readLine())!= null){
                            func.initRewardList(currentLine);
                        }
                        waterBufRead.close();

                    } catch (IOException e) { //si on n'arrive pas a ouvrir les fichiers
                    }
                }
            }
            else getLogger().info("Error this command require op right !");

        }

        else return false;
        return false;
    }
}
