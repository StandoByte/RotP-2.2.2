package com.github.standobyte.jojo.network.packets.fromclient;

import java.util.function.Supplier;

import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.network.NetworkUtil;
import com.github.standobyte.jojo.power.IPower;
import com.github.standobyte.jojo.power.IPower.PowerClassification;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class ClLayoutQuickAccessPacket {
    private final PowerClassification classification;
    private final Action<?> action;

    public ClLayoutQuickAccessPacket(PowerClassification classification, Action<?> action) {
        this.classification = classification;
        this.action = action;
    }

    public static void encode(ClLayoutQuickAccessPacket msg, PacketBuffer buf) {
        buf.writeEnum(msg.classification);
        NetworkUtil.writeOptionally(buf, msg.action, (buffer, action) -> buffer.writeRegistryId(action));
    }

    public static ClLayoutQuickAccessPacket decode(PacketBuffer buf) {
        return new ClLayoutQuickAccessPacket(buf.readEnum(PowerClassification.class), 
                NetworkUtil.readOptional(buf, buffer -> {
                    Action<?> action = buffer.readRegistryIdSafe(Action.class);
                    return action;
                }).orElse(null));
    }

    public static void handle(ClLayoutQuickAccessPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            IPower.getPowerOptional(player, msg.classification).ifPresent(power -> {
                setQuickAccess(power, msg.action);
            });
        });
        ctx.get().setPacketHandled(true);
    }
    
    private static <P extends IPower<P, ?>> void setQuickAccess(IPower<?, ?> power, Action<P> action) {
        ((P) power).getActionsLayout().setQuickAccessAction(action);
    }
}
