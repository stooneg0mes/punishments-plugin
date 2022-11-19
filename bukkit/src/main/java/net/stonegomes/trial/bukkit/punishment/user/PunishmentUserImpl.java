package net.stonegomes.trial.bukkit.punishment.user;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.stonegomes.trial.core.Punishment;
import net.stonegomes.trial.core.PunishmentType;
import net.stonegomes.trial.core.user.PunishmentUser;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Builder
@Getter
public class PunishmentUserImpl implements PunishmentUser {

    private final UUID uuid;
    private final List<Punishment> punishments;

    @Override
    public void addPunishment(Punishment punishment) {
        punishments.add(punishment);
    }

    @Override
    public void removePunishment(Punishment punishment) {
        punishments.remove(punishment);
    }

    @Override
    public void removePunishment(UUID uuid) {
        final Punishment punishment = findPunishment(uuid);
        if (punishment == null) return;

        punishments.remove(punishment);
    }

    @Override
    public Punishment findPunishment(UUID uuid) {
        return punishments.stream()
            .filter(punishment -> punishment.getUuid().equals(uuid))
            .findFirst()
            .orElse(null);
    }

    @Override
    public Punishment findActivePunishment(PunishmentType... punishmentTypes) {
        for (PunishmentType punishmentType : punishmentTypes) {
            final Punishment findPunishment = punishments.stream()
                .filter(punishment -> punishment.getType() == punishmentType && punishment.isActive())
                .findFirst()
                .orElse(null);
            if (findPunishment != null) return findPunishment;
        }

        return null;
    }

    @Override
    public String getName() {
        final Player player = Bukkit.getPlayer(uuid);
        if (player == null) {
            final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
            return offlinePlayer.getName();
        } else {
            return player.getName();
        }
    }

}
