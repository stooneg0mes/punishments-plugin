package net.stonegomes.trial.bukkit.command;

import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import net.stonegomes.trial.bukkit.PunishmentsPlugin;
import net.stonegomes.trial.bukkit.view.HistoryPaginatedView;
import net.stonegomes.trial.core.user.PunishmentUser;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

@RequiredArgsConstructor
public class HistoryCommand {

    private final PunishmentsPlugin plugin;

    @Command(
        name = "history",
        usage = "history <player>",
        permission = "punishments.admin.history",
        target = CommandTarget.PLAYER
    )
    public void handleCommand(Context<Player> context, PunishmentUser punishmentUser) {
        plugin.getViewFrame().open(
            HistoryPaginatedView.class,
            context.getSender(),
            Map.of("punishmentUser", punishmentUser)
        );
    }

}
