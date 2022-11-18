package net.stonegomes.trial.core.user;

import net.stonegomes.trial.core.Punishment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface PunishmentUserFactory {

    PunishmentUser createPunishmentUser(UUID uuid);

    PunishmentUser createPunishmentUser(UUID uuid, List<Punishment> punishments);

}
