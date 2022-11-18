package net.stonegomes.trial.bukkit.punishment.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.stonegomes.trial.core.Punishment;
import net.stonegomes.trial.core.user.PunishmentUser;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Builder
@Getter
public class PunishmentUserImpl implements PunishmentUser {

    private final UUID uniqueId;
    private final List<Punishment> punishments;

}
