package com.github.standobyte.jojo.network.packets.fromclient;

import java.util.function.Supplier;

import com.github.standobyte.jojo.advancements.ModCriteriaTriggers;
import com.github.standobyte.jojo.init.power.hamon.ModHamon;
import com.github.standobyte.jojo.power.nonstand.INonStandPower;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class ClHamonAbandonButtonPacket {
    
    public ClHamonAbandonButtonPacket() {
    }
    
    public static void encode(ClHamonAbandonButtonPacket msg, PacketBuffer buf) {
        
    }
    
    public static ClHamonAbandonButtonPacket decode(PacketBuffer buf) {
        return new ClHamonAbandonButtonPacket();
    }
    
    public static void handle(ClHamonAbandonButtonPacket msg, Supplier<NetworkEvent.Context> ctx) {
//        ctx.get().enqueueWork(() -> {
//            ServerPlayerEntity player = ctx.get().getSender();
//            INonStandPower.getNonStandPowerOptional(player).ifPresent(power -> {
//                if (power.getType() == ModHamon.HAMON.get()) {
//                    power.clear();
//                    ModCriteriaTriggers.ABANDON_HAMON.get().trigger(player);
//                }
//            });
//        });
        ctx.get().setPacketHandled(true);
    }

}
