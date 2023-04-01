package com.github.standobyte.jojo.network.packets.fromclient;

import java.util.Set;
import java.util.function.Supplier;

import com.github.standobyte.jojo.init.power.hamon.ModHamon;
import com.github.standobyte.jojo.network.PacketManager;
import com.github.standobyte.jojo.network.packets.fromserver.HamonExercisesPacket;
import com.github.standobyte.jojo.network.packets.fromserver.HamonTeachersSkillsPacket;
import com.github.standobyte.jojo.power.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.nonstand.type.hamon.HamonPowerType;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.AbstractHamonSkill;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class ClHamonWindowOpenedPacket {
    
    public ClHamonWindowOpenedPacket() {}
    
    public static void encode(ClHamonWindowOpenedPacket msg, PacketBuffer buf) {}
    
    public static ClHamonWindowOpenedPacket decode(PacketBuffer buf) {
        return new ClHamonWindowOpenedPacket();
    }
    
    public static void handle(ClHamonWindowOpenedPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            HamonPowerType.coughCoughCough(player);
//            INonStandPower.getNonStandPowerOptional(player).ifPresent(power -> {
//                power.getTypeSpecificData(ModHamon.HAMON.get()).ifPresent(hamon -> {
//                    PacketManager.sendToClient(new HamonExercisesPacket(hamon), player);
//                    Set<AbstractHamonSkill> skills = HamonPowerType.nearbyTeachersSkills(player);
//                    PacketManager.sendToClient(skills == null ? new HamonTeachersSkillsPacket() : 
//                        new HamonTeachersSkillsPacket(skills), player);
//                });
//            });
        });
        ctx.get().setPacketHandled(true);
    }

}
