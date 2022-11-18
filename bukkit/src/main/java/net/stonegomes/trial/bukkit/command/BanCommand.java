package net.stonegomes.trial.bukkit.command;

import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import net.stonegomes.trial.bukkit.PunishmentsPlugin;
import net.stonegomes.trial.core.Punishment;
import net.stonegomes.trial.core.user.PunishmentUser;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class BanCommand {

    private final PunishmentsPlugin plugin;

    @Command(
        name = "ban",
        usage = "ban <player> [reason]",
        permission = "punishments.admin.ban",
        target = CommandTarget.ALL
    )
    public void handleCommand(Context<CommandSender> context, Player player, @Optional String reason) {
        final PunishmentUser punishmentUser = plugin.getPunishmentUserCache().getUser(player.getUniqueId());
        if (punishmentUser == null) {

        } else {

        }
    }

}
