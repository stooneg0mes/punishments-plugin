package net.stonegomes.trial.bukkit;

import lombok.Getter;
import net.stonegomes.trial.bukkit.punishment.user.PunishmentUserCacheImpl;
import net.stonegomes.trial.bukkit.punishment.user.PunishmentUserDaoImpl;
import net.stonegomes.trial.bukkit.punishment.user.PunishmentUserFactoryImpl;
import net.stonegomes.trial.core.user.PunishmentUserCache;
import net.stonegomes.trial.core.user.PunishmentUserDao;
import net.stonegomes.trial.core.user.PunishmentUserFactory;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class PunishmentsPlugin extends JavaPlugin {

    private PunishmentUserFactory punishmentUserFactory;
    private PunishmentUserCache punishmentUserCache;
    private PunishmentUserDao punishmentUserDao;

    @Override
    public void onEnable() {
        punishmentUserFactory = new PunishmentUserFactoryImpl();
        punishmentUserCache = new PunishmentUserCacheImpl();
        punishmentUserDao = new PunishmentUserDaoImpl();
    }

}
