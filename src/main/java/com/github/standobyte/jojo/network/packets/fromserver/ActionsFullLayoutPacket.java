package com.github.standobyte.jojo.network.packets.fromserver;

import java.util.function.Supplier;

import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.power.ActionHotbarData.ActionHotbarLayout;
import com.github.standobyte.jojo.power.ActionHotbarData;
import com.github.standobyte.jojo.power.IPower;
import com.github.standobyte.jojo.power.IPower.ActionType;
import com.github.standobyte.jojo.power.IPower.PowerClassification;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class ActionsFullLayoutPacket {
    private final PowerClassification classification;
    private final ActionType hotbar;
    private final ActionHotbarLayout<?> layout;

    public ActionsFullLayoutPacket(PowerClassification classification, ActionType hotbar, 
            ActionHotbarLayout<?> layout) {
        this.classification = classification;
        this.hotbar = hotbar;
        this.layout = layout;
    }

    public static void encode(ActionsFullLayoutPacket msg, PacketBuffer buf) {
        buf.writeEnum(msg.classification);
        buf.writeEnum(msg.hotbar);
        msg.layout.toBuf(buf);
    }

    public static ActionsFullLayoutPacket decode(PacketBuffer buf) {
        return new ActionsFullLayoutPacket(buf.readEnum(PowerClassification.class), 
                buf.readEnum(ActionType.class), ActionHotbarLayout.fromBuf(buf));
    }

    public static void handle(ActionsFullLayoutPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            IPower.getPowerOptional(ClientUtil.getClientPlayer(), msg.classification).ifPresent(power -> {
                // FIXME (layout editing) more guarding
                setLayout(power.getActions(msg.hotbar), msg.layout);
            });
        });
        ctx.get().setPacketHandled(true);
    }
    
    private static <P extends IPower<P, ?>> void setLayout(ActionHotbarData<P> actions, ActionHotbarLayout<?> layout) {
        actions.setLayout((ActionHotbarLayout<P>) layout);
    }
}
