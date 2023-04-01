package com.github.standobyte.jojo.network.packets.fromserver;

import java.util.Optional;
import java.util.function.Supplier;

import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.client.ui.screen.hamon.HamonScreen;
import com.github.standobyte.jojo.init.power.hamon.ModHamon;
import com.github.standobyte.jojo.network.NetworkUtil;
import com.github.standobyte.jojo.power.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.CharacterHamonTechnique;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.network.NetworkEvent;

public class TrHamonCharacterTechniquePacket {
    private final int entityId;
    private final Optional<CharacterHamonTechnique> technique;
    private final boolean playPickSound;
    
    public static TrHamonCharacterTechniquePacket reset(int entityId) {
        return new TrHamonCharacterTechniquePacket(entityId, Optional.empty(), false);
    }
    
    public TrHamonCharacterTechniquePacket(int entityId, CharacterHamonTechnique technique) {
        this(entityId, technique, false);
    }
    
    public TrHamonCharacterTechniquePacket(int entityId, CharacterHamonTechnique technique, boolean playPickSound) {
        this(entityId, Optional.of(technique), playPickSound);
    }
    
    private TrHamonCharacterTechniquePacket(int entityId, Optional<CharacterHamonTechnique> technique, boolean playPickSound) {
        this.entityId = entityId;
        this.technique = technique;
        this.playPickSound = playPickSound;
    }
    
    public static void encode(TrHamonCharacterTechniquePacket msg, PacketBuffer buf) {
        buf.writeInt(msg.entityId);
        NetworkUtil.writeOptional(buf, msg.technique, (buffer, technique) -> buffer.writeRegistryId(technique));
        buf.writeBoolean(msg.playPickSound);
    }
    
    public static TrHamonCharacterTechniquePacket decode(PacketBuffer buf) {
        return new TrHamonCharacterTechniquePacket(
                buf.readInt(), 
                NetworkUtil.readOptional(buf, buffer -> buffer.readRegistryIdSafe(CharacterHamonTechnique.class)), 
                buf.readBoolean());
    }

    public static void handle(TrHamonCharacterTechniquePacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Entity entity = ClientUtil.getEntityById(msg.entityId);
            if (entity instanceof LivingEntity) {
                LivingEntity user = (LivingEntity) entity;
                INonStandPower.getNonStandPowerOptional(user).ifPresent(power -> {
                    power.getTypeSpecificData(ModHamon.HAMON.get()).ifPresent(hamon -> {
                        if (msg.technique.isPresent()) {
                            hamon.pickHamonTechnique(user, msg.technique.get());
                            if (msg.playPickSound && user.is(ClientUtil.getClientPlayer())) {
                                SoundEvent techniquePickMusic = msg.technique.get().getMusicOnPick();
                                if (techniquePickMusic != null) {
                                    ClientUtil.playMusic(techniquePickMusic, 1.0F, 1.0F);
                                }
                            }
                        }
                        else {
                            hamon.resetCharacterTechnique(user);
                        }
                        if (user.is(ClientUtil.getClientPlayer())) {
                            HamonScreen.updateTabs();
                        }
                    });
                });
            }
        });
        ctx.get().setPacketHandled(true);
    }
}