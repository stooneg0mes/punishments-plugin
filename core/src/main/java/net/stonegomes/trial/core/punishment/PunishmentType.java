package net.stonegomes.trial.core.punishment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PunishmentType {

    TEMPORARY_MUTE("Temporary mute"),
    TEMPORARY_BAN("Temporary ban"),
    MUTE("Mute"),
    KICK("Kick"),
    BAN("Ban");

    private final String name;

}
