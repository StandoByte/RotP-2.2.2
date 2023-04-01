package com.github.standobyte.jojo.entity;

import javax.annotation.Nullable;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.init.ModEntityTypes;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.power.stand.StandUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class KQBombEntity extends Entity {
    @Nullable private Entity host;
    public LivingEntity standUser;

    public KQBombEntity(LivingEntity standUser, World world) {
        this(ModEntityTypes.KQ_BOMB.get(), world);
        this.standUser = standUser;
    }
    
    public KQBombEntity(EntityType<?> p_i48580_1_, World p_i48580_2_) {
        super(p_i48580_1_, p_i48580_2_);
    }
    
    public void setHost(Entity entity) {
        this.host = entity;
    }
    
    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide()) {
            if (host != null) this.moveTo(host.getEntity().getBoundingBox().getCenter().subtract(0, 0.5, 0));
            if (!level.getEntities(this, getBoundingBox().inflate(0.1), e -> {
                if (e.is(standUser) || e instanceof StandEntity && StandUtil.getStandUser((StandEntity) e).is(standUser)
                        || e.is(host) || e.getType() == this.getType()) return false;
                return true;
            }).isEmpty()) {
                if (standUser != null) level.playSound(null, 
                        standUser.getX(), standUser.getEyeY(), standUser.getZ(), 
                        ModSounds.killer_queen_detonate.get(), standUser.getSoundSource(), 1.0F, 1.0F);
                detonate();
            }
        }
    }
    
    public void detonate() {
        if (!level.isClientSide()) {
            this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 4.0F, Explosion.Mode.BREAK);
            remove();
        }
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {}

    @Override
    protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {}

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
