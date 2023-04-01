package com.github.standobyte.jojo.client.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.RisingParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;

public class HamonAuraParticle extends RisingParticle {

    protected HamonAuraParticle(ClientWorld world, double x, double y, double z, double xda, double yda, double zda, float sizeM, IAnimatedSprite sprites) {
        super(world, x, y, z, 0.1F, 0.1F, 0.1F, xda, yda, zda, sizeM, sprites, 0.3F, 8, 0.004D, true);
        this.rCol = 1;
        this.gCol = 1;
        this.bCol = 1;
        lifetime = 25 + random.nextInt(10);
    }
    
    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    
    @Override
    protected int getLightColor(float partialTick) {
        return 0xF000F0;
    }

    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite sprite) {
            this.spriteSet = sprite;
        }

        @Override
        public Particle createParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            HamonAuraParticle particle = new HamonAuraParticle(world, x, y, z, xSpeed, ySpeed, zSpeed, 1.5F, this.spriteSet);
            return particle;
        }
    }
}
