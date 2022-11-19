package net.stonegomes.trial.bukkit.listener;

import lombok.RequiredArgsConstructor;
import net.stonegomes.trial.bukkit.PunishmentsPlugin;
import net.stonegomes.trial.bukkit.util.TimeFormatter;
import net.stonegomes.trial.core.Punishment;
import net.stonegomes.trial.core.PunishmentType;
import net.stonegomes.trial.core.user.PunishmentUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;

@RequiredArgsConstructor
public class AsyncPlayerChatListener implements Listener {

    private final PunishmentsPlugin plugin;

    @EventHandler
    public void handlePlayerLogin(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final PunishmentUser punishmentUser = plugin.getPunishmentUserCache().getUser(player.getUniqueId());
        if (punishmentUser == null) return;

        final Punishment activePunishment = punishmentUser.findActivePunishment(PunishmentType.TEMPORARY_MUTE, PunishmentType.MUTE);
        if (activePunishment == null) return;

        String[] muteMessage = new String[0];
        switch (activePunishment.getType()) {
            case TEMPORARY_MUTE -> {
                if (activePunishment.getTimeLeft() <= System.currentTimeMillis()) {
                    activePunishment.setActive(false);
                    return;
                }

                muteMessage = new String[]{
                    "",
                    "§c§lPUNISHMENTS",
                    "§cYou have been muted in the server chat.",
                    "",
                    "§cTime: §f" + TimeFormatter.formatTime(activePunishment.getPunishmentDuration()) + "§c.",
                    "§cReason: §f" + activePunishment.getReason(),
                    "§cAuthor: §f" + activePunishment.getAuthor(),
                    ""
                };
            }
            case MUTE -> muteMessage = new String[]{
                "",
                "§c§lPUNISHMENTS",
                "§cYou have been muted in the server chat.",
                "",
                "§cTime: §fPermanent§c.",
                "§cReason: §f" + activePunishment.getReason(),
                "§cAuthor: §f" + activePunishment.getAuthor(),
                ""
            };
        }

        event.setCancelled(true);
        player.sendMessage(muteMessage);
    }

}