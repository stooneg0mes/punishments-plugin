package net.stonegomes.trial.core;

import java.util.Date;

public interface Punishment {

    int getId();

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
