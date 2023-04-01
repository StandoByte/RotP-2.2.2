package com.github.standobyte.jojo.network.packets.fromserver;

import java.util.function.Supplier;

import com.github.standobyte.jojo.client.ui.hud.ActionsOverlayGui;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class AFPacket {
    private final Type type;
    
    public AFPacket(Type type) {
        this.type = type;
    }
    
    public static void encode(AFPacket msg, PacketBuffer buf) {
        buf.writeEnum(msg.type);
    }
    
    public static AFPacket decode(PacketBuffer buf) {
        return new AFPacket(buf.readEnum(Type.class));
    }

    public static void handle(AFPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            switch (msg.type) {
            case STAND_HUD:
                ActionsOverlayGui.getInstance().onStandSummon();
                break;
            }
        });
        ctx.get().setPacketHandled(true);
    }
    
    public static enum Type {
        STAND_HUD
    }
}
