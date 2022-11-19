package net.stonegomes.trial.bukkit;

import lombok.Getter;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.saiintbrisson.minecraft.ViewFrame;
import net.stonegomes.trial.bukkit.command.*;
import net.stonegomes.trial.bukkit.listener.AsyncPlayerChatListener;
import net.stonegomes.trial.bukkit.listener.PlayerLoginListener;
import net.stonegomes.trial.bukkit.punishment.user.PunishmentUserCacheImpl;
import net.stonegomes.trial.bukkit.punishment.user.PunishmentUserDaoImpl;
import net.stonegomes.trial.bukkit.punishment.user.PunishmentUserFactoryImpl;
import net.stonegomes.trial.bukkit.view.HistoryPaginatedView;
import net.stonegomes.trial.core.user.PunishmentUserCache;
import net.stonegomes.trial.core.user.PunishmentUserDao;
import net.stonegomes.trial.core.user.PunishmentUserFactory;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class PunishmentsPlugin extends JavaPlugin {

    private PunishmentUserFactory punishmentUserFactory;
    private PunishmentUserCache punishmentUserCache;
    private PunishmentUserDao punishmentUserDao;

    private ViewFrame viewFrame;

    @Override
    public void onEnable() {
        // Initializers

        punishmentUserFactory = new PunishmentUserFactoryImpl();
        punishmentUserCache = new PunishmentUserCacheImpl();
        punishmentUserDao = new PunishmentUserDaoImpl();

        // Views

        viewFrame = ViewFrame.of(this)
            .with(new HistoryPaginatedView())
            .register();

        // Commands

        final BukkitFrame bukkitFrame = new BukkitFrame(this);
        bukkitFrame.registerCommands(
            new TemporaryMuteCommand(this),
            new TemporaryBanCommand(this),
            new HistoryCommand(this),
            new KickCommand(this),
            new MuteCommand(this),
            new BanCommand(this),
            new UnbanCommand(),
            new UnmuteCommand()
        );

        // Listeners

        final PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(new AsyncPlayerChatListener(this), this);
        pluginManager.registerEvents(new PlayerLoginListener(this), this);
    }

}
