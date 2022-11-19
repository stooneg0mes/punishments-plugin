package net.stonegomes.trial.core.user;

import java.util.UUID;

public interface PunishmentUserCache {

    /**
     * Get a user from the cache by the uuid.
     * @param uuid the user uuid
     * @return the cached user
     */
    PunishmentUser getUser(UUID uuid);

    /**
     * Remove a user from the cache by the uuid.
     * @param uuid the user uuid
     * @return the removed user
     */
    PunishmentUser removeUser(UUID uuid);

    /**
     * Put a user on the cache.
     * @param uuid the user uuid
     * @param punishmentUser the user
     */
    void putUser(UUID uuid, PunishmentUser punishmentUser);

    /**
     * Check if the user is on the cache.
     * @param uuid the user uuid
     * @return if the user is cached
     */
    boolean containsUser(UUID uuid);
}
