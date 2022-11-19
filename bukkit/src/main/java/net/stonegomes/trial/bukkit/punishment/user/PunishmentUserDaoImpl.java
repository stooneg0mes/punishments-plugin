package net.stonegomes.trial.bukkit.punishment.user;

import de.leonhard.storage.Yaml;
import de.leonhard.storage.sections.FlatFileSection;
import lombok.RequiredArgsConstructor;
import net.stonegomes.trial.bukkit.PunishmentsPlugin;
import net.stonegomes.trial.core.Punishment;
import net.stonegomes.trial.core.user.PunishmentUser;
import net.stonegomes.trial.core.user.PunishmentUserDao;

import java.util.*;

@RequiredArgsConstructor
public class PunishmentUserDaoImpl implements PunishmentUserDao {

    private final PunishmentsPlugin plugin;
    private final Yaml storage;

    @Override
    public void replaceUser(PunishmentUser punishmentUser) {
        final FlatFileSection userSection = storage.getSection(punishmentUser.getUuid().toString());

        for (Punishment punishment : punishmentUser.getPunishments()) {
            final String punishmentId = punishment.getUuid().toString();
            userSection.setSerializable(punishmentId, punishment);
        }
    }

    @Override
    public PunishmentUser findUser(UUID uuid) {
        final FlatFileSection userSection = storage.getSection(uuid.toString());
        final List<Punishment> punishments = new ArrayList<>();

        for (String key : userSection.keySet()) {
            final Punishment punishment = userSection.getSerializable(key, Punishment.class);
            punishments.add(punishment);
        }

        return plugin.getPunishmentUserFactory().createPunishmentUser(
            uuid,
            punishments
        );
    }

    @Override
    public Collection<PunishmentUser> findAll() {
        final Set<PunishmentUser> punishmentUsers = new HashSet<>();

        for (String key : storage.keySet()) {
            final PunishmentUser findUser = findUser(UUID.fromString(key));
            punishmentUsers.add(findUser);
        }

        return punishmentUsers;
    }

}
