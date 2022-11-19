package net.stonegomes.trial.core;

import java.util.UUID;

public interface Punishment {

    /**
     * Get the punishment uuid.
     * @return the uuid
     */
    UUID getUuid();

    /**
     * Get the punishment type.
     * @return the type
     */
    PunishmentType getType();

    /**
     * Get the time when the punishment happened.
     * @return the time
     */
    long getPunishmentTime();

    /**
     * Get the punishment reason.
     * @return the reason
     */
    String getReason();

    /**
     * Get the punishment author.
     * @return the author
     */
    String getAuthor();

    /**
     * Check if the punishment is active.
     * @return if it is active
     */
    boolean isActive();

    /**
     * Set the punishment active or inactive.
     * @param active if the punishment should be active
     */
    void setActive(boolean active);

    /**
     * Get the duration of a temporary punishment.
     * @return the duration
     */
    Long getPunishmentDuration();

    /**
     * Check if the punishment has a duration.
     * @return if it has a duration
     */
    default boolean hasPunishmentDuration() {
        return getPunishmentDuration() != null;
    }

    /**
     * Get the time left for the punishment to end
     * @return the time left to end
     */
    default long getTimeLeft() {
        return getPunishmentTime() + getPunishmentDuration();
    }

}
