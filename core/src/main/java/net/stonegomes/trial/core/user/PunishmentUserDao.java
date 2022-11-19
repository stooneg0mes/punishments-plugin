package net.stonegomes.trial.core.user;

import java.util.Collection;
import java.util.UUID;

public interface PunishmentUserDao {

    /**
     * Replace the user in the storage.
     * @param punishmentUser the user
     */
    void replaceUser(PunishmentUser punishmentUser);

    /**
     * Find a user on the storage.
     * @param uuid the user uuid
     * @return the found user
     */
    PunishmentUser findUser(UUID uuid);

    /**
     * Find all users on the storage.
     * @return all found users
     */
    Collection<PunishmentUser> findAll();

}
