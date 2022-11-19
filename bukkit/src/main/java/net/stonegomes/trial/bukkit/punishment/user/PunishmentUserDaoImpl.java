package net.stonegomes.trial.bukkit.punishment.user;

import de.leonhard.storage.Yaml;
import lombok.RequiredArgsConstructor;
import net.stonegomes.trial.core.user.PunishmentUser;
import net.stonegomes.trial.core.user.PunishmentUserDao;

import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
public class PunishmentUserDaoImpl implements PunishmentUserDao {

    private final Yaml storage;

    @Override
    public void replaceUser(PunishmentUser punishmentUser) {
        /*
        TODO
         */
    }

    @Override
    public PunishmentUser findUser(UUID uuid) {
        /*
        TODO
         */
        return null;
    }

    @Override
    public Collection<PunishmentUser> findAll() {
        /*
        TODO
         */
        return null;
    }

}
