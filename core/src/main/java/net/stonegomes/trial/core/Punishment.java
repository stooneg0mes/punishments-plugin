package net.stonegomes.trial.core;

import java.util.Date;
import java.util.UUID;

public interface Punishment {

    UUID getUniqueId();

    PunishmentType getType();

    long getPunishmentTime();

    String getReason();

    String getAuthor();

    boolean isActive();

    void setActive(boolean active);

    Long getPunishmentDuration();

    default boolean hasPunishmentDuration() {
        return getPunishmentDuration() != null;
    }

    default long getTimeLeft() {
        return getPunishmentTime() + getPunishmentDuration();
    }

}
