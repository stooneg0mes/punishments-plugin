package net.stonegomes.trial.bukkit.command;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import net.stonegomes.trial.core.user.PunishmentUser;
import org.bukkit.command.CommandSender;

public class HistoryCommand {

    @Command(
        name = "history",
        usage = "history <player>",
        permission = "punishments.admin.history",
        target = CommandTarget.ALL
    )
    public void handleCommand(Context<CommandSender> context, PunishmentUser punishmentUser) {

    }

}
