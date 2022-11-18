package net.stonegomes.trial.core.user;

import net.stonegomes.trial.core.Punishment;

import java.util.List;
import java.util.UUID;

public interface PunishmentUser {

    UUID getUniqueId();

    List<Punishment> getPunishments();

}
