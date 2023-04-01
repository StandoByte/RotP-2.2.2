package com.github.standobyte.jojo.network.packets.fromserver;

import java.util.function.Supplier;

import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.init.power.hamon.ModHamon;
import com.github.standobyte.jojo.power.nonstand.INonStandPower;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class TrHamonEnergyTicksPacket {
    private final int entityId;
    private final int ticks;
    
    public TrHamonEnergyTicksPacket(int entityId, int ticks) {
        this.entityId = entityId;
        this.ticks = ticks;
    }
    
    public static void encode(TrHamonEnergyTicksPacket msg, PacketBuffer buf) {
        buf.writeInt(msg.entityId);
        buf.writeShort(msg.ticks);
    }
    
    public static TrHamonEnergyTicksPacket decode(PacketBuffer buf) {
        return new TrHamonEnergyTicksPacket(buf.readInt(), buf.readShort());
    }

    public static void handle(TrHamonEnergyTicksPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Entity entity = ClientUtil.getEntityById(msg.entityId);
            if (entity instanceof LivingEntity) {
                INonStandPower.getNonStandPowerOptional((LivingEntity) entity).ifPresent(power -> {
                    power.getTypeSpecificData(ModHamon.HAMON.get()).ifPresent(hamon -> {
                        hamon.setNoEnergyDecayTicks(msg);
                    });
                });
            }
        });
        ctx.get().setPacketHandled(true);
    }
    
    public int getTicks() {
        return ticks;
    }
}
