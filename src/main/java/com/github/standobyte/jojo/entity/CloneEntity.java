package com.github.standobyte.jojo.entity;

import com.github.standobyte.jojo.init.ModEntityTypes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class CloneEntity extends Entity implements IEntityAdditionalSpawnData {
    public LivingEntity original;

    public CloneEntity(LivingEntity original, World world) {
        this(ModEntityTypes.ENTITY_CLONE.get(), world);
        this.original = original;
    }
    
    public CloneEntity(EntityType<?> p_i48580_1_, World p_i48580_2_) {
        super(p_i48580_1_, p_i48580_2_);
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {
    }
    
    public LivingEntity getOriginalEntity() {
        return original;
    }
    
    @Override
    public void tick() {
        super.tick();
        if (original == null) {
            remove();
            return;
        }
        if (!level.isClientSide()) {
            if (level.getEntities(this, getBoundingBox()).contains(original)) {
                original.hurt(new DamageSource("clone").bypassArmor().bypassMagic(), 10000F);
                kill();
            }
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        buffer.writeBoolean(original != null);
        if (original != null) buffer.writeInt(original.getId());
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        if (additionalData.readBoolean()) {
            Entity entity = level.getEntity(additionalData.readInt());
            if (entity instanceof LivingEntity) original = (LivingEntity) entity;
        }
    }

}
