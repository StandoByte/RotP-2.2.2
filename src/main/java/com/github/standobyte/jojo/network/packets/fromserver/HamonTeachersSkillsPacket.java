package com.github.standobyte.jojo.network.packets.fromserver;

import java.util.Collection;
import java.util.function.Supplier;

import com.github.standobyte.jojo.client.ui.screen.hamon.HamonScreen;
import com.github.standobyte.jojo.network.NetworkUtil;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.AbstractHamonSkill;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class HamonTeachersSkillsPacket {
    private final boolean teacherNearby;
    private Collection<AbstractHamonSkill> skills;
    
    public HamonTeachersSkillsPacket() {
        teacherNearby = false;
    }
    
    public HamonTeachersSkillsPacket(Collection<AbstractHamonSkill> skills) {
        teacherNearby = true;
        this.skills = skills;
    }
    
    public static void encode(HamonTeachersSkillsPacket msg, PacketBuffer buf) {
        buf.writeBoolean(msg.teacherNearby);
        if (msg.teacherNearby) {
            NetworkUtil.writeCollection(buf, msg.skills, (buffer, skill) -> buffer.writeRegistryId(skill), false);
        }
    }
    
    public static HamonTeachersSkillsPacket decode(PacketBuffer buf) {
        return buf.readBoolean() ? new HamonTeachersSkillsPacket(NetworkUtil.readCollection(buf, 
                buffer -> buffer.readRegistryIdSafe(AbstractHamonSkill.class))) : new HamonTeachersSkillsPacket();
    }
    
    public static void handle(HamonTeachersSkillsPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (msg.teacherNearby) {
                HamonScreen.setTeacherSkills(msg.skills);
                HamonScreen.updateTabs();
            }
        });
        ctx.get().setPacketHandled(true);
    }

}
