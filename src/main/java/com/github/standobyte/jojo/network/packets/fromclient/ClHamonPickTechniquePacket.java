package com.github.standobyte.jojo.network.packets.fromclient;

import java.util.function.Supplier;

import com.github.standobyte.jojo.init.power.hamon.ModHamon;
import com.github.standobyte.jojo.power.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.CharacterHamonTechnique;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class ClHamonPickTechniquePacket {
    private final CharacterHamonTechnique technique;
    
    public ClHamonPickTechniquePacket(CharacterHamonTechnique technique) {
        this.technique = technique;
    }
    
    public static void encode(ClHamonPickTechniquePacket msg, PacketBuffer buf) {
        buf.writeRegistryId(msg.technique);
    }
    
    public static ClHamonPickTechniquePacket decode(PacketBuffer buf) {
        return new ClHamonPickTechniquePacket(buf.readRegistryIdSafe(CharacterHamonTechnique.class));
    }
    
    public static void handle(ClHamonPickTechniquePacket msg, Supplier<NetworkEvent.Context> ctx) {
//        ctx.get().enqueueWork(() -> {
//            ServerPlayerEntity player = ctx.get().getSender();
//            INonStandPower.getNonStandPowerOptional(player).ifPresent(power -> {
//                power.getTypeSpecificData(ModHamon.HAMON.get()).ifPresent(hamon -> {
//                    hamon.pickHamonTechnique(player, msg.technique);
//                });
//            });
//        });
        ctx.get().setPacketHandled(true);
    }

}
