package net.stonegomes.trial.bukkit.command;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import net.stonegomes.trial.core.Punishment;
import net.stonegomes.trial.core.PunishmentType;
import net.stonegomes.trial.core.user.PunishmentUser;
import org.bukkit.command.CommandSender;

public class UnbanCommand {

    @Command(
        name = "unban",
        usage = "unban <player>",
        permission = "punishments.admin.unban",
        target = CommandTarget.ALL
    )
    public void handleCommand(Context<CommandSender> context, PunishmentUser user) {
        if (user == null) {
            context.sendMessage("§cThis user has not suffered any punishments on the server.");
            return;
        }

        final Punishment punishment = user.findActivePunishment(PunishmentType.BAN, PunishmentType.TEMPORARY_BAN);
        if (punishment == null) {
            context.sendMessage("§cThis user has no active ban at the moment.");
            return;
        }

        punishment.setActive(false);

        context.sendMessage("§eYou unbanned the user §f'" + user.getName() + "'§e successfully.");
    }

}
