package net.stonegomes.trial.bukkit.command;

import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import net.stonegomes.trial.bukkit.PunishmentsPlugin;
import net.stonegomes.trial.bukkit.punishment.PunishmentImpl;
import net.stonegomes.trial.core.Punishment;
import net.stonegomes.trial.core.PunishmentType;
import net.stonegomes.trial.core.user.PunishmentUser;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        final CommandSender commandSender = context.getSender();
        final Punishment punishment = PunishmentImpl.builder()
            .uniqueId(UUID.randomUUID())
            .type(PunishmentType.BAN)
            .date(Date.from(Instant.now()))
            .reason(reason == null ? "No reason provided" : reason)
            .author(commandSender instanceof Player ? commandSender.getName() : "Console")
            .punishmentTime(null)
            .punishmentDuration(null)
            .build();

        final PunishmentUser punishmentUser = plugin.getPunishmentUserCache().getUser(player.getUniqueId());
        if (punishmentUser != null) {
            punishmentUser.addPunishment(punishment);
        } else {
            final PunishmentUser defaultUser = plugin.getPunishmentUserFactory().createPunishmentUser(
                player.getUniqueId(),
                List.of(punishment)
            );
            defaultUser.removePunishment(UUID.randomUUID());
            plugin.getPunishmentUserCache().putUser(defaultUser.getUniqueId(), defaultUser);
        }

        commandSender.sendMessage("§eYou permanently banned the player '§f" + player.getName() +"§e' successfully.");
        player.kickPlayer("§cYou got permanently banned from the server.");
    }

}
