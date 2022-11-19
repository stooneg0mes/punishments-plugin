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

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class KickCommand {

    private final PunishmentUserFactory punishmentUserFactory;
    private final PunishmentUserCache punishmentUserCache;

    @Command(
        name = "kick",
        usage = "kick <player> [reason]",
        permission = "punishments.admin.kick",
        target = CommandTarget.ALL
    )
    public void handleCommand(Context<CommandSender> context, Player player, @Optional String optionalReason) {
        final String reason = optionalReason == null ? "No reason provided" : optionalReason;
        final String author = context.getSender().getName();

        final Punishment punishment = PunishmentImpl.builder()
            .uniqueId(UUID.randomUUID())
            .type(PunishmentType.KICK)
            .reason(reason)
            .author(author)
            .punishmentTime(System.currentTimeMillis())
            .punishmentDuration(null)
            .active(false)
            .build();

        final PunishmentUser punishmentUser = punishmentUserCache.getUser(player.getUniqueId());
        if (punishmentUser == null) {
            final PunishmentUser newUser = punishmentUserFactory.createPunishmentUser(
                player.getUniqueId(),
                List.of(punishment)
            );

            punishmentUserCache.putUser(newUser.getUniqueId(), newUser);
        } else {
            punishmentUser.addPunishment(punishment);
        }

        context.sendMessage("§eYou kicked the player §f'" + player.getName() +"'§e successfully.");
        player.kickPlayer("§cYou got kicked from the server.");
    }

}
