package net.stonegomes.trial.bukkit.view;

import me.saiintbrisson.minecraft.*;
import net.stonegomes.trial.bukkit.util.ItemStackBuilder;
import net.stonegomes.trial.bukkit.util.TimeFormatter;
import net.stonegomes.trial.core.Punishment;
import net.stonegomes.trial.core.PunishmentType;
import net.stonegomes.trial.core.user.PunishmentUser;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class HistoryPaginatedView extends PaginatedView<Punishment> {

    private static final DateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy '-' hh:mm");

    private static final Map<PunishmentType, Material> ICONS = new HashMap<>(){{
        put(PunishmentType.TEMPORARY_BAN, Material.CLOCK);
        put(PunishmentType.TEMPORARY_MUTE, Material.CLOCK);

        put(PunishmentType.BAN, Material.ANVIL);
        put(PunishmentType.MUTE, Material.ANVIL);

        put(PunishmentType.KICK, Material.BONE);
    }};

    public HistoryPaginatedView() {
        super(4, "");

        setLayout(
            "XXXXXXXXX",
            "XOOOOOOOX",
            "XOOOOOOOX",
            "<XXXXXXX>"
        );

        setCancelOnClick(true);
        setCancelOnClone(true);
        setCancelOnDrag(true);
        setCancelOnDrop(true);
        setCancelOnPickup(true);
        setCancelOnMoveOut(true);
        setCancelOnShiftClick(true);
    }

    @Override
    protected void onRender(@NotNull ViewContext context) {
        final PunishmentUser punishmentUser = context.get("punishmentUser");
        setSource(punishmentUser.getPunishments());

        if (context.paginated().hasNextPage()) {
            context.slot(4, 9).withItem(new ItemStackBuilder(Material.ARROW)
                .name("§aNext Page")
                .lore("§7Click here to go to the next page.")
            ).onClick(handler -> context.paginated().switchToNextPage());
        }

        if (context.paginated().hasPreviousPage()) {
            context.slot(4, 0).withItem(new ItemStackBuilder(Material.ARROW)
                .name("§aPrevious Page")
                .lore("§7Click here to go to the previous page.")
            ).onClick(handler -> context.paginated().switchToPreviousPage());
        }
    }

    @Override
    protected void onOpen(@NotNull OpenViewContext context) {
        final PunishmentUser punishmentUser = context.get("punishmentUser");
        context.setContainerTitle("Punishment History | '" + punishmentUser.getName() + "'");
    }

    @Override
    protected void onItemRender(@NotNull PaginatedViewSlotContext<Punishment> context, @NotNull ViewItem viewItem, @NotNull Punishment value) {
        final Material icon = ICONS.get(value.getType());

        final String status = value.isActive() ? "Active" : "Inactive";
        final String duration = value.hasPunishmentDuration() ? TimeFormatter.formatTime(value.getPunishmentDuration()) : "/-/";
        final String timeLeft = value.hasPunishmentDuration() ? TimeFormatter.formatTime(value.getTimeLeft()) : "/-/";
        final String dateFormatted = SIMPLE_DATE_FORMAT.format(value.getPunishmentTime());

        viewItem.withItem(new ItemStackBuilder(icon)
            .name("§f" + value.getType().getName())
            .lore(
                "§7Author: §f" + value.getAuthor(),
                "§7Reason: §f" + value.getReason(),
                "§7Status: §f" + status,
                "§7Duration §f" + duration,
                "§7Time left: §f" + timeLeft,
                "§7Date: §f" + dateFormatted
            )
        );
    }

}
