package me.gzimmer.owteammixer.model;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Getter
public class Player implements Serializable {
    private String displayName;
    @NotNull private final String bnet;
    private String tankRank = null;
    private String dpsRank = null;
    private String supportRank = null;

    public Player(@NotNull String bnet, @NotNull String displayName) {
        this(bnet);
        this.displayName = displayName;
    }

    public Player(@NotNull String bnet) {
        this.bnet = bnet;
    }

    public void setRank(String role, String rank, int div) {
        switch (role) {
            case "tank" -> this.tankRank = rank + "_" + div;
            case "damage"-> this.dpsRank = rank + "_" + div;
            case "support"-> this.supportRank = rank + "_" + div;
        }
    }
}
