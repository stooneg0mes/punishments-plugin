package net.stonegomes.trial.bukkit.punishment;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.stonegomes.trial.core.Punishment;
import net.stonegomes.trial.core.PunishmentType;

import java.util.Date;

@RequiredArgsConstructor
@Builder
@Data
public class PunishmentImpl implements Punishment {

    private final int id;
    private final PunishmentType type;
    private final Date date;
    private final String reason, author;
    private final Long punishmentTime, punishmentDuration;

    private boolean active;

}
