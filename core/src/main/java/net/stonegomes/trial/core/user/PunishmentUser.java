package net.stonegomes.trial.core.user;

import net.stonegomes.trial.core.Punishment;
import net.stonegomes.trial.core.PunishmentType;

import java.util.List;
import java.util.UUID;

public interface PunishmentUser {

    /**
     * Get the user uniqueId on the server.
     * @return the user uniqueId
     */
    UUID getUniqueId();

    /**
     * Get all punishments the user had.
     * @return the user punishments
     */
    List<Punishment> getPunishments();

    /**
     * Add a punishment to the user's punishments.
     * @param punishment the punishment
     */
    void addPunishment(Punishment punishment);

    /**
     * Remove a punishment from the user's punishments.
     * @param punishment the punishment
     */
    void removePunishment(Punishment punishment);

    /**
     * Remove a punishment by the uuid from the user's punishments.
     * @param uuid the punishment uuid
     */
    void removePunishment(UUID uuid);

    /**
     * Find a punishment by the uuid.
     * @param uuid the uuid
     * @return the found punishment
     */
    Punishment findPunishment(UUID uuid);

    /**
     * Find an active punishment of the given types.
     * @param punishmentTypes the punishment types
     * @return the found punishment
     */
    Punishment findActivePunishment(PunishmentType... punishmentTypes);

    /**
     * Get the user's name.
     * @return the name
     */
    String getName();

}
