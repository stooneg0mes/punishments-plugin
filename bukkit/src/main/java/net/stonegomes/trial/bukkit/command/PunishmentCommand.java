package net.stonegomes.trial.bukkit.command;

import lombok.RequiredArgsConstructor;
import net.stonegomes.trial.bukkit.PunishmentsPlugin;
import net.stonegomes.trial.core.user.PunishmentUser;

import java.util.UUID;

@RequiredArgsConstructor
public class PunishmentCommand {

    final PunishmentsPlugin plugin;

    protected PunishmentUser getOrCreateUser(UUID uuid) {
        if (plugin.getPunishmentUserCache().containsUser(uuid)) {
            return plugin.getPunishmentUserCache().getUser(uuid);
        } else {
            return plugin.getPunishmentUserFactory().createPunishmentUser(uuid);
        }
    }

}
