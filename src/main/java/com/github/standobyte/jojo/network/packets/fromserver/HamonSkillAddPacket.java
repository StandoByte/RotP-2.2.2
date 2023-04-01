package com.github.standobyte.jojo.network.packets.fromserver;

import java.util.function.Supplier;

import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.client.ui.screen.hamon.HamonScreen;
import com.github.standobyte.jojo.init.power.hamon.ModHamon;
import com.github.standobyte.jojo.power.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.AbstractHamonSkill;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.CharacterTechniqueHamonSkill;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class HamonSkillAddPacket {
    private final AbstractHamonSkill skill;
    private final Type packetType;
    
    public static HamonSkillAddPacket learnNewSkill(AbstractHamonSkill skill) {
        return new HamonSkillAddPacket(Type.LEARN, skill);
    }
    
    public static HamonSkillAddPacket masterTechniqueSkill(AbstractHamonSkill skill) {
        return new HamonSkillAddPacket(Type.MASTER, skill);
    }

    private HamonSkillAddPacket(Type packetType, AbstractHamonSkill skill) {
        this.packetType = packetType;
        this.skill = skill;
    }

    public static void encode(HamonSkillAddPacket msg, PacketBuffer buf) {
        buf.writeEnum(msg.packetType);
        buf.writeRegistryId(msg.skill);
    }

    public static HamonSkillAddPacket decode(PacketBuffer buf) {
        return new HamonSkillAddPacket(buf.readEnum(Type.class), buf.readRegistryIdSafe(AbstractHamonSkill.class));
    }

    public static void handle(HamonSkillAddPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerEntity player = ClientUtil.getClientPlayer();
            INonStandPower.getNonStandPowerOptional(player).ifPresent(power -> {
                power.getTypeSpecificData(ModHamon.HAMON.get()).ifPresent(hamon -> {
                    switch (msg.packetType) {
                    case LEARN:
                        hamon.addHamonSkill(player, msg.skill, false, false);
                        break;
                    case MASTER:
                        if (msg.skill instanceof CharacterTechniqueHamonSkill) {
                            hamon.masterTechniqueSkill((CharacterTechniqueHamonSkill) msg.skill);
                        }
                        break;
                    }
                    HamonScreen.updateTabs();
                });
            });
        });
        ctx.get().setPacketHandled(true);
    }

    private enum Type {
        LEARN,
        MASTER
    }
}