package net.stonegomes.trial.core.user;

import net.stonegomes.trial.core.Punishment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface PunishmentUserFactory {

    /**
     * Create a user with a given uuid
     * @param uuid the uuid
     * @return the created user
     */
    PunishmentUser createPunishmentUser(UUID uuid);

    /**
     * Create a user with a given uuid and list of punishments
     * @param uuid the uuid
     * @param punishments the list of punishments
     * @return the created user
     */
    PunishmentUser createPunishmentUser(UUID uuid, List<Punishment> punishments);

}
