package net.stonegomes.trial.bukkit.punishment.user;

import net.stonegomes.trial.core.user.PunishmentUser;
import net.stonegomes.trial.core.user.PunishmentUserCache;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PunishmentUserCacheImpl implements PunishmentUserCache {

    private static final Map<UUID, PunishmentUser> USER_CACHE = new ConcurrentHashMap<>();

    @Override
    public PunishmentUser getUser(UUID uuid) {
        return USER_CACHE.get(uuid);
    }

    @Override
    public PunishmentUser removeUser(UUID uuid) {
        return USER_CACHE.remove(uuid);
    }

    @Override
    public void putUser(UUID uuid, PunishmentUser punishmentUser) {
        USER_CACHE.put(uuid, punishmentUser);
    }

}
