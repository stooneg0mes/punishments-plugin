package net.stonegomes.trial.core.user;

import java.util.Collection;
import java.util.UUID;

public interface PunishmentUserDao {

    void replaceUser(PunishmentUser punishmentUser);

    PunishmentUser findUser(UUID uuid);

    Collection<PunishmentUser> findAll();

}
