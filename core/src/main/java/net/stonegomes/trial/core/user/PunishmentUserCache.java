package net.stonegomes.trial.core.user;

import java.util.UUID;

public interface PunishmentUserCache {

    PunishmentUser getUser(UUID uuid);

    PunishmentUser removeUser(UUID uuid);

    void putUser(UUID uuid, PunishmentUser punishmentUser);

    default boolean containsUser(UUID uuid) {
        return getUser(uuid) != null;
    }

}
