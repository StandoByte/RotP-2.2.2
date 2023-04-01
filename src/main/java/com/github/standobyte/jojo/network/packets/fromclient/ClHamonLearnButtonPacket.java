package com.github.standobyte.jojo.network.packets.fromclient;

import java.util.function.Supplier;

import com.github.standobyte.jojo.init.power.hamon.ModHamon;
import com.github.standobyte.jojo.power.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.AbstractHamonSkill;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.CharacterTechniqueHamonSkill;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class ClHamonLearnButtonPacket {
    private final AbstractHamonSkill skill;
    private final Type packetType;
    
    public static ClHamonLearnButtonPacket learnNewSkill(AbstractHamonSkill skill) {
        return new ClHamonLearnButtonPacket(Type.LEARN, skill);
    }
    
    public static ClHamonLearnButtonPacket masterTechniqueSkill(AbstractHamonSkill skill) {
        return new ClHamonLearnButtonPacket(Type.MASTER, skill);
    }
    
    private ClHamonLearnButtonPacket(Type packetType, AbstractHamonSkill skill) {
        this.skill = skill;
        this.packetType = packetType;
    }
    
    public static void encode(ClHamonLearnButtonPacket msg, PacketBuffer buf) {
        buf.writeEnum(msg.packetType);
        buf.writeRegistryId(msg.skill);
    }
    
    public static ClHamonLearnButtonPacket decode(PacketBuffer buf) {
        return new ClHamonLearnButtonPacket(buf.readEnum(Type.class), buf.readRegistryIdSafe(AbstractHamonSkill.class));
    }
    
    public static void handle(ClHamonLearnButtonPacket msg, Supplier<NetworkEvent.Context> ctx) {
//        ctx.get().enqueueWork(() -> {
//            ServerPlayerEntity player = ctx.get().getSender();
//            INonStandPower.getNonStandPowerOptional(player).ifPresent(power -> {
//                power.getTypeSpecificData(ModHamon.HAMON.get()).ifPresent(hamon -> {
//                    switch (msg.packetType) {
//                    case LEARN:
//                        msg.skill.learnNewSkill(hamon, player);
//                        break;
//                    case MASTER:
//                        if (msg.skill instanceof CharacterTechniqueHamonSkill) {
//                            hamon.masterTechniqueSkill((CharacterTechniqueHamonSkill) msg.skill);
//                        }
//                        break;
//                    }
//                });
//            });
//        });
        ctx.get().setPacketHandled(true);
    }

    private enum Type {
        LEARN,
        MASTER
    }
}
