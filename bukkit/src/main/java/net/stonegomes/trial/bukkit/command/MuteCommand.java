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
import net.stonegomes.trial.core.user.PunishmentUserCache;
import net.stonegomes.trial.core.user.PunishmentUserFactory;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class MuteCommand {

    private final PunishmentUserFactory punishmentUserFactory;
    private final PunishmentUserCache punishmentUserCache;

    @Command(
        name = "mute",
        usage = "mute <player> [reason]",
        permission = "punishments.admin.mute",
        target = CommandTarget.ALL
    )
    public void handleCommand(Context<CommandSender> context, Player player, @Optional String optionalReason) {
        final String reason = optionalReason == null ? "No reason provided" : optionalReason;
        final String author = context.getSender().getName();

        final Punishment punishment = PunishmentImpl.builder()
            .uniqueId(UUID.randomUUID())
            .type(PunishmentType.MUTE)
            .reason(reason)
            .author(author)
            .punishmentTime(System.currentTimeMillis())
            .punishmentDuration(null)
            .active(true)
            .build();

        final PunishmentUser punishmentUser = punishmentUserCache.getUser(player.getUniqueId());
        if (punishmentUser == null) {
            final PunishmentUser newUser = punishmentUserFactory.createPunishmentUser(
                player.getUniqueId(),
                List.of(punishment)
            );

            punishmentUserCache.putUser(newUser.getUniqueId(), newUser);
        } else {
            final Punishment activePunishment = punishmentUser.findActivePunishment(
                PunishmentType.TEMPORARY_MUTE,
                PunishmentType.MUTE
            );
            if (activePunishment != null) {
                context.sendMessage("§cThis player is already muted at the moment.");
                return;
            }

            punishmentUser.addPunishment(punishment);
        }

        context.sendMessage("§eYou permanently muted the player §f'" + player.getName() +"'§e successfully.");
        player.sendMessage(
            "",
            "§c§lPUNISHMENTS",
            "§cYou have been muted in the server chat.",
            "",
            "§cTime: §fPermanent§c.",
            "§cReason: §f" + punishment.getReason(),
            "§cAuthor: §f" + punishment.getAuthor(),
            ""
        );
    }

}
