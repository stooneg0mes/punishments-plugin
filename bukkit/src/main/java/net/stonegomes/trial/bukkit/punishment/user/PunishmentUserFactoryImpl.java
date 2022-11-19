package net.stonegomes.trial.bukkit.punishment.user;

import net.stonegomes.trial.core.Punishment;
import net.stonegomes.trial.core.user.PunishmentUser;
import net.stonegomes.trial.core.user.PunishmentUserFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PunishmentUserFactoryImpl implements PunishmentUserFactory {

    @Override
    public PunishmentUser createPunishmentUser(UUID uuid) {
        return PunishmentUserImpl.builder()
            .uuid(uuid)
            .punishments(new ArrayList<>())
            .build();
    }

    @Override
    public PunishmentUser createPunishmentUser(UUID uuid, List<Punishment> punishments) {
        return PunishmentUserImpl.builder()
            .uuid(uuid)
            .punishments(punishments)
            .build();
    }

}
