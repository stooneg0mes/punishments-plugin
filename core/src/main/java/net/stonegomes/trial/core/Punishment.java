package net.stonegomes.trial.core;

import java.util.Date;
import java.util.UUID;

public interface Punishment {

    UUID getUniqueId();

    PunishmentType getType();

    Date getDate();

    String getReason();

    String getAuthor();

    boolean isActive();

    void setActive(boolean active);

    Long getPunishmentTime();

    default boolean hasPunishmentTime() {
        return getPunishmentTime() != null;
    }

    Long getPunishmentDuration();

    default boolean hasPunishmentDuration() {
        return getPunishmentDuration() != null;
    }

    default long getTimeLeft() {
        return getPunishmentTime() + getPunishmentDuration();
    }

}
