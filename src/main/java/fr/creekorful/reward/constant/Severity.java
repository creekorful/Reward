package fr.creekorful.reward.constant;

import org.bukkit.ChatColor;

public enum Severity {
    LEGENDARY(ChatColor.GOLD),
    VERY_HIGH(ChatColor.BLACK),
    HIGH(ChatColor.RED),
    MEDIUM(ChatColor.YELLOW),
    LOW(ChatColor.BLUE);

    private ChatColor color;

    Severity(ChatColor color) {
        this.color = color;
    }

    public ChatColor getColor() {
        return color;
    }
}
