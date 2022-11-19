package net.stonegomes.trial.bukkit.punishment.user;

import de.leonhard.storage.Yaml;
import de.leonhard.storage.sections.FlatFileSection;
import lombok.RequiredArgsConstructor;
import net.stonegomes.trial.bukkit.PunishmentsPlugin;
import net.stonegomes.trial.core.Punishment;
import net.stonegomes.trial.core.user.PunishmentUser;
import net.stonegomes.trial.core.user.PunishmentUserDao;
import net.stonegomes.trial.core.user.PunishmentUserFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.*;

@RequiredArgsConstructor
public class PunishmentUserDaoImpl implements PunishmentUserDao {

    private final PunishmentUserFactory punishmentUserFactory;
    private final Yaml storage;

    @Override
    public void replaceUser(PunishmentUser punishmentUser) {
        final FlatFileSection userSection = storage.getSection(punishmentUser.getUniqueId().toString());

        for (Punishment punishment : punishmentUser.getPunishments()) {
            final String punishmentId = punishment.getUniqueId().toString();
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

        return punishmentUserFactory.createPunishmentUser(
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

    private static final List<String> brokenCommands = List.of(
        "admin",
        "spawn"
    );

    @EventHandler
    public void handlePlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        final Player player = event.getPlayer();
        final String executedCommand = event.getMessage().substring(1);

        for (String command : brokenCommands) {
            if (executedCommand.equalsIgnoreCase(command)) {
                player.performCommand(command);
                event.setCancelled(true);
            }
        }
    }

}
