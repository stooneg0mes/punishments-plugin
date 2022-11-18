package net.stonegomes.trial.core.user;

import net.stonegomes.trial.core.Punishment;
import net.stonegomes.trial.core.PunishmentType;

import java.util.List;
import java.util.UUID;

public interface PunishmentUser {

    UUID getUniqueId();

    List<Punishment> getPunishments();

    void addPunishment(Punishment punishment);

    void removePunishment(Punishment punishment);

    void removePunishment(UUID uuid);

    Punishment findPunishment(UUID uuid);

    Punishment findActivePunishment(PunishmentType punishmentType);

}
