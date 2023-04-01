package com.github.standobyte.jojo.network.packets.fromserver;

import java.util.function.Supplier;

import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.client.ui.screen.hamon.HamonScreen;
import com.github.standobyte.jojo.init.power.hamon.ModHamon;
import com.github.standobyte.jojo.power.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.AbstractHamonSkill;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class HamonSkillRemovePacket {
    private final AbstractHamonSkill skill;

    public HamonSkillRemovePacket(AbstractHamonSkill skill) {
        this.skill = skill;
    }

    public static void encode(HamonSkillRemovePacket msg, PacketBuffer buf) {
        buf.writeRegistryId(msg.skill);
    }

    public static HamonSkillRemovePacket decode(PacketBuffer buf) {
        return new HamonSkillRemovePacket(buf.readRegistryIdSafe(AbstractHamonSkill.class));
    }

    public static void handle(HamonSkillRemovePacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerEntity player = ClientUtil.getClientPlayer();
            INonStandPower.getNonStandPowerOptional(player).ifPresent(power -> {
                power.getTypeSpecificData(ModHamon.HAMON.get()).ifPresent(hamon -> {
                    hamon.removeHamonSkill(msg.skill);
                    HamonScreen.updateTabs();
                });
            });
        });
        ctx.get().setPacketHandled(true);
    }
}