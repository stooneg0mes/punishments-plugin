package net.stonegomes.trial.bukkit.punishment;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.stonegomes.trial.core.Punishment;
import net.stonegomes.trial.core.PunishmentType;

import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Builder
@Data
public class PunishmentImpl implements Punishment {

    private final UUID uniqueId;
    private final PunishmentType type;
    private final Date date;
    private final String reason, author;
    private final Long punishmentTime, punishmentDuration;

    private boolean active;

}
