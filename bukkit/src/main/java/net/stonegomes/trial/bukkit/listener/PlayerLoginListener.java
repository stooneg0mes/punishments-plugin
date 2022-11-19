package net.stonegomes.trial.bukkit.listener;

import lombok.RequiredArgsConstructor;
import net.stonegomes.trial.bukkit.PunishmentsPlugin;
import net.stonegomes.trial.bukkit.util.TimeFormatter;
import net.stonegomes.trial.core.Punishment;
import net.stonegomes.trial.core.PunishmentType;
import net.stonegomes.trial.core.user.PunishmentUser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

@RequiredArgsConstructor
public class PlayerLoginListener implements Listener {

    private final PunishmentsPlugin plugin;

    @EventHandler
    public void handlePlayerLogin(PlayerLoginEvent event) {
        final PunishmentUser punishmentUser = plugin.getPunishmentUserCache().getUser(event.getPlayer().getUniqueId());
        if (punishmentUser == null) return;

        final Punishment activePunishment = punishmentUser.findActivePunishment(PunishmentType.TEMPORARY_BAN, PunishmentType.BAN);
        if (activePunishment == null) return;

        String[] banMessage = new String[0];
        switch (activePunishment.getType()) {
            case TEMPORARY_BAN -> banMessage = new String[]{
                "",
                "§c§lPUNISHMENTS",
                "§c(You have been temporary banned from the server)",
                "",
                "§cAuthor: §f" + activePunishment.getAuthor(),
                "§cReason: §f" + activePunishment.getReason(),
                "§cTime: §f" + TimeFormatter.formatTime(activePunishment.getTimeLeft(), true),
                ""
            };
            case BAN -> banMessage = new String[]{
                "",
                "§c§lPUNISHMENTS",
                "§c(You have been permanently banned from the server)",
                "",
                "§cAuthor: §f" + activePunishment.getAuthor(),
                "§cReason: §f" + activePunishment.getReason(),
                "§cTime: §fPermanent",
                ""
            };
        }

        event.disallow(PlayerLoginEvent.Result.KICK_OTHER, String.join("\n", banMessage));
    }

}