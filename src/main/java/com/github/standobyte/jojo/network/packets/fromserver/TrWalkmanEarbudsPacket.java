package com.github.standobyte.jojo.network.packets.fromserver;

import java.util.function.Supplier;

import com.github.standobyte.jojo.capability.entity.PlayerUtilCapProvider;
import com.github.standobyte.jojo.client.ClientUtil;

import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class TrWalkmanEarbudsPacket {
    private final int entityId;
    private final boolean earbuds;

    public TrWalkmanEarbudsPacket(int entityId, boolean earbuds) {
        this.entityId = entityId;
        this.earbuds = earbuds;
    }

    public static void encode(TrWalkmanEarbudsPacket msg, PacketBuffer buf) {
        buf.writeInt(msg.entityId);
        buf.writeBoolean(msg.earbuds);
    }

    public static TrWalkmanEarbudsPacket decode(PacketBuffer buf) {
        return new TrWalkmanEarbudsPacket(buf.readInt(), buf.readBoolean());
    }

    public static void handle(TrWalkmanEarbudsPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Entity entity = ClientUtil.getEntityById(msg.entityId);
            if (entity != null) {
            	entity.getCapability(PlayerUtilCapProvider.CAPABILITY).ifPresent(cap -> cap.setEarbuds(msg.earbuds));
            }
        });
        ctx.get().setPacketHandled(true);
    }

}
