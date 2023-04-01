package com.github.standobyte.jojo.network.packets.fromserver;

import java.util.function.Supplier;

import com.github.standobyte.jojo.capability.entity.LivingUtilCapProvider;
import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.power.IPower;
import com.github.standobyte.jojo.power.IPowerType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class TrSyncLivingCapState {
    private final int entityId;
    private final PacketType packetType;
    private final boolean state;
    
    public static TrSyncLivingCapState sendoWaveKick(int entityId, boolean isInKick) {
        return new TrSyncLivingCapState(entityId, PacketType.SENDO_WAVE_KICK, isInKick);
    }

    private TrSyncLivingCapState(int entityId, PacketType packetType, boolean state) {
        this.entityId = entityId;
        this.packetType = packetType;
        this.state = state;
    }

    public static <P extends IPower<P, T>, T extends IPowerType<P, T>> void encode(TrSyncLivingCapState msg, PacketBuffer buf) {
        buf.writeEnum(msg.packetType);
        buf.writeInt(msg.entityId);
        switch (msg.packetType) {
        case SENDO_WAVE_KICK:
            buf.writeBoolean(msg.state);
            break;
        }
    }

    public static TrSyncLivingCapState decode(PacketBuffer buf) {
        PacketType packetType = buf.readEnum(PacketType.class);
        switch (packetType) {
        case SENDO_WAVE_KICK:
            return sendoWaveKick(buf.readInt(), buf.readBoolean());
        }
        return null;
    }

    public static void handle(TrSyncLivingCapState msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Entity entity = ClientUtil.getEntityById(msg.entityId);
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).getCapability(LivingUtilCapProvider.CAPABILITY).ifPresent(cap -> {
                    switch (msg.packetType) {
                    case SENDO_WAVE_KICK:
                        cap.setSendoWaveKick(msg.state);
                        break;
                    }
                });
            }
        });
        ctx.get().setPacketHandled(true);
    }

    private static enum PacketType {
        SENDO_WAVE_KICK
    }
}
