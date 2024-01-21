package me.gzimmer.owteammixer.model;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

@Getter
public class Player implements Serializable {
    private String displayName;
    @NotNull private final String bnet;
    private String tankRank = null;
    private String dpsRank = null;
    private String supportRank = null;
    @Setter
    private Match match = null;

    public Player(@NotNull String bnet, @NotNull String displayName) {
        this(bnet);
        this.displayName = displayName;
    }

    public Player(@NotNull String bnet) {
        this.bnet = bnet;
    }

    public void setRank(String role, String div, int tier) {
        switch (role) {
            case "tank" -> this.tankRank = div + "_" + tier;
            case "damage"-> this.dpsRank = div + "_" + tier;
            case "support"-> this.supportRank = div + "_" + tier;
        }
    }

    @Override
    public String toString() {
        return bnet.split("-")[0];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return Objects.equals(getBnet(), player.getBnet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBnet());
    }
}
