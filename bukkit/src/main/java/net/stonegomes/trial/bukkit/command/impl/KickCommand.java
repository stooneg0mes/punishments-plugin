package net.stonegomes.trial.bukkit.command.impl;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import net.stonegomes.trial.bukkit.PunishmentsPlugin;
import net.stonegomes.trial.bukkit.command.PunishmentCommand;
import net.stonegomes.trial.bukkit.punishment.PunishmentImpl;
import net.stonegomes.trial.core.Punishment;
import net.stonegomes.trial.core.PunishmentType;
import net.stonegomes.trial.core.user.PunishmentUser;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class KickCommand extends PunishmentCommand {

    public KickCommand(PunishmentsPlugin plugin) {
        super(plugin);
    }

    @Command(
        name = "kick",
        usage = "kick <player> [reason]",
        permission = "punishments.admin.kick",
        target = CommandTarget.ALL
    )
    public void handleCommand(Context<CommandSender> context, Player player, @Optional String optionalReason) {
        final Date date = Date.from(Instant.now());
        final String reason = optionalReason == null ? "No reason provided" : optionalReason;
        final String author = context.getSender().getName();

        final Punishment punishment = PunishmentImpl.builder()
            .uniqueId(UUID.randomUUID())
            .type(PunishmentType.KICK)
            .date(date)
            .reason(reason)
            .author(author)
            .punishmentTime(null)
            .punishmentDuration(null)
            .active(false)
            .build();

        final PunishmentUser punishmentUser = getOrCreateUser(player.getUniqueId());
        punishmentUser.addPunishment(punishment);

        context.sendMessage("§eYou kicked the player §f'" + player.getName() +"'§e successfully.");
        player.kickPlayer("§cYou got kicked from the server.");
    }

}
