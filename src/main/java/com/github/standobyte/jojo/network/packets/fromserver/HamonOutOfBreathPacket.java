package com.github.standobyte.jojo.network.packets.fromserver;

import java.util.function.Supplier;

import com.github.standobyte.jojo.client.ui.hud.ActionsOverlayGui;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class HamonOutOfBreathPacket {
    private final boolean mask;
    
    public HamonOutOfBreathPacket(boolean mask) {
        this.mask = mask;
    }
    
    public static void encode(HamonOutOfBreathPacket msg, PacketBuffer buf) {
        buf.writeBoolean(msg.mask);
    }
    
    public static HamonOutOfBreathPacket decode(PacketBuffer buf) {
        return new HamonOutOfBreathPacket(buf.readBoolean());
    }

    public static void handle(HamonOutOfBreathPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ActionsOverlayGui.getInstance().setOutOfBreath(msg.mask);
        });
        ctx.get().setPacketHandled(true);
    }
}
