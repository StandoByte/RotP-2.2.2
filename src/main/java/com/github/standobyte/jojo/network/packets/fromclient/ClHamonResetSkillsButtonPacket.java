package com.github.standobyte.jojo.network.packets.fromclient;

import java.util.function.Supplier;

import com.github.standobyte.jojo.init.power.hamon.ModHamon;
import com.github.standobyte.jojo.power.nonstand.INonStandPower;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class ClHamonResetSkillsButtonPacket {
    private final HamonSkillsTab type;
    
    public ClHamonResetSkillsButtonPacket(HamonSkillsTab type) {
        this.type = type;
    }
    
    public static void encode(ClHamonResetSkillsButtonPacket msg, PacketBuffer buf) {
        buf.writeEnum(msg.type);
    }
    
    public static ClHamonResetSkillsButtonPacket decode(PacketBuffer buf) {
        return new ClHamonResetSkillsButtonPacket(buf.readEnum(HamonSkillsTab.class));
    }
    
    public static void handle(ClHamonResetSkillsButtonPacket msg, Supplier<NetworkEvent.Context> ctx) {
//        ctx.get().enqueueWork(() -> {
//            ServerPlayerEntity player = ctx.get().getSender();
//            INonStandPower.getNonStandPowerOptional(player).ifPresent(power -> {
//                power.getTypeSpecificData(ModHamon.HAMON.get()).ifPresent(hamon -> {
//                    hamon.resetHamonSkills(player, msg.type);
//                });
//            });
//        });
        ctx.get().setPacketHandled(true);
    }
    
    
    
    public static enum HamonSkillsTab {
        STRENGTH,
        CONTROL,
        TECHNIQUE
    }
}
