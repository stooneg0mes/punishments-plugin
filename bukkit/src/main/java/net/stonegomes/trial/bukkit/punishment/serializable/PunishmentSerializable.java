package net.stonegomes.trial.bukkit.punishment.serializable;

import de.leonhard.storage.internal.serialize.SimplixSerializable;
import lombok.NonNull;
import net.stonegomes.trial.bukkit.punishment.PunishmentImpl;
import net.stonegomes.trial.core.Punishment;
import net.stonegomes.trial.core.PunishmentType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PunishmentSerializable implements SimplixSerializable<Punishment> {

    @Override
    public Punishment deserialize(@NonNull Object obj) throws ClassCastException {
        final Map<String, Object> punishmentData = (Map<String, Object>) obj;

        return PunishmentImpl.builder()
            .uuid((UUID) punishmentData.get("uuid"))
            .type((PunishmentType) punishmentData.get("type"))
            .punishmentTime((Long) punishmentData.get("time"))
            .reason((String) punishmentData.get("reason"))
            .author((String) punishmentData.get("author"))
            .punishmentDuration((Long) punishmentData.get("duration"))
            .active((Boolean) punishmentData.get("active"))
            .build();
    }

    @Override
    public Object serialize(@NonNull Punishment punishment) throws ClassCastException {
        final Map<String, Object> punishmentData = new HashMap<>();

        punishmentData.put("uuid", punishment.getUuid());
        punishmentData.put("type", punishment.getType());
        punishmentData.put("time", punishment.getPunishmentTime());
        punishmentData.put("reason", punishment.getReason());
        punishmentData.put("author", punishment.getAuthor());
        punishmentData.put("duration", punishment.getPunishmentDuration());
        punishmentData.put("active", punishment.isActive());

        return punishmentData;
    }

    @Override
    public Class<Punishment> getClazz() {
        return Punishment.class;
    }

}
