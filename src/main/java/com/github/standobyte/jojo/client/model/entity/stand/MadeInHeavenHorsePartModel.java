package com.github.standobyte.jojo.client.model.entity.stand;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class MadeInHeavenHorsePartModel<T extends StandEntity> extends HumanoidStandModel<T> {
    protected final ModelRenderer horseBody;
    protected final ModelRenderer horseHeadParts;
    private final ModelRenderer leg3;
    private final ModelRenderer leg4;
    private final ModelRenderer[] ridingParts;

    public MadeInHeavenHorsePartModel() {
        super(64, 64);
        this.texWidth = 64;
        this.texHeight = 64;
        this.horseBody = new ModelRenderer(this, 0, 32);
        this.horseBody.addBox(-5.0F, -8.0F, -17.0F, 10.0F, 10.0F, 22.0F, 0.05F);
        this.horseBody.setPos(0.0F, 11.0F, 5.0F);
        this.horseHeadParts = new ModelRenderer(this, 0, 35);
        this.horseHeadParts.addBox(-2.05F, -6.0F, -2.0F, 4.0F, 12.0F, 7.0F);
        this.horseHeadParts.xRot = ((float)Math.PI / 6F);
        ModelRenderer modelrenderer = new ModelRenderer(this, 0, 13);
        modelrenderer.addBox(-3.0F, -11.0F, -2.0F, 6.0F, 5.0F, 7.0F, 0);
        ModelRenderer modelrenderer1 = new ModelRenderer(this, 56, 36);
        modelrenderer1.addBox(-1.0F, -11.0F, 5.01F, 2.0F, 16.0F, 2.0F, 0);
        ModelRenderer modelrenderer2 = new ModelRenderer(this, 0, 25);
        modelrenderer2.addBox(-2.0F, -11.0F, -7.0F, 4.0F, 5.0F, 5.0F, 0);
        this.horseHeadParts.addChild(modelrenderer);
        this.horseHeadParts.addChild(modelrenderer1);
        this.horseHeadParts.addChild(modelrenderer2);
        this.addEarModels(this.horseHeadParts);
        this.leg3 = new ModelRenderer(this, 48, 21);
        this.leg3.mirror = true;
        this.leg3.addBox(-3.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, 0);
        this.leg3.setPos(4.0F, 6.0F, -12.0F);
        this.leg4 = new ModelRenderer(this, 48, 21);
        this.leg4.addBox(-1.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, 0);
        this.leg4.setPos(-4.0F, 6.0F, -12.0F);
        ModelRenderer modelrenderer3 = new ModelRenderer(this, 26, 0);
        modelrenderer3.addBox(-5.0F, -8.0F, -9.0F, 10.0F, 9.0F, 9.0F, 0.5F);
        this.horseBody.addChild(modelrenderer3);
        ModelRenderer modelrenderer4 = new ModelRenderer(this, 29, 5);
        modelrenderer4.addBox(2.0F, -9.0F, -6.0F, 1.0F, 2.0F, 2.0F, 0);
        this.horseHeadParts.addChild(modelrenderer4);
        ModelRenderer modelrenderer5 = new ModelRenderer(this, 29, 5);
        modelrenderer5.addBox(-3.0F, -9.0F, -6.0F, 1.0F, 2.0F, 2.0F, 0);
        this.horseHeadParts.addChild(modelrenderer5);
        ModelRenderer modelrenderer6 = new ModelRenderer(this, 32, 2);
        modelrenderer6.addBox(3.1F, -6.0F, -8.0F, 0.0F, 3.0F, 16.0F, 0);
        modelrenderer6.xRot = (-(float)Math.PI / 6F);
        this.horseHeadParts.addChild(modelrenderer6);
        ModelRenderer modelrenderer7 = new ModelRenderer(this, 32, 2);
        modelrenderer7.addBox(-3.1F, -6.0F, -8.0F, 0.0F, 3.0F, 16.0F, 0);
        modelrenderer7.xRot = (-(float)Math.PI / 6F);
        this.horseHeadParts.addChild(modelrenderer7);
        ModelRenderer modelrenderer8 = new ModelRenderer(this, 1, 1);
        modelrenderer8.addBox(-3.0F, -11.0F, -1.9F, 6.0F, 5.0F, 6.0F, 0.2F);
        this.horseHeadParts.addChild(modelrenderer8);
        ModelRenderer modelrenderer9 = new ModelRenderer(this, 19, 0);
        modelrenderer9.addBox(-2.0F, -11.0F, -4.0F, 4.0F, 5.0F, 2.0F, 0.2F);
        this.horseHeadParts.addChild(modelrenderer9);
        this.ridingParts = new ModelRenderer[]{modelrenderer6, modelrenderer7};
    }

    protected void addEarModels(ModelRenderer p_199047_1_) {
       ModelRenderer modelrenderer = new ModelRenderer(this, 19, 16);
       modelrenderer.addBox(0.55F, -13.0F, 4.0F, 2.0F, 3.0F, 1.0F, -0.001F);
       ModelRenderer modelrenderer1 = new ModelRenderer(this, 19, 16);
       modelrenderer1.addBox(-2.55F, -13.0F, 4.0F, 2.0F, 3.0F, 1.0F, -0.001F);
       p_199047_1_.addChild(modelrenderer);
       p_199047_1_.addChild(modelrenderer1);
    }

    public Iterable<ModelRenderer> headParts() {
       return Iterables.concat(super.headParts(), ImmutableList.of(this.horseHeadParts));
    }

    protected Iterable<ModelRenderer> bodyParts() {
       return Iterables.concat(super.bodyParts(), ImmutableList.of(this.horseBody, this.leg3, this.leg4));
    }

    private static final float Y_OFFS = 8;
    public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        super.setupAnim(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);

       horseBody.y = 11 + Y_OFFS;
       horseHeadParts.y += Y_OFFS;
       leg3.y += Y_OFFS;
       leg4.y += Y_OFFS;
    }

    public void prepareMobModel(T p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
       super.prepareMobModel(p_212843_1_, p_212843_2_, p_212843_3_, p_212843_4_);
       float f = MathHelper.rotlerp(p_212843_1_.yBodyRotO, p_212843_1_.yBodyRot, p_212843_4_);
       float f1 = MathHelper.rotlerp(p_212843_1_.yHeadRotO, p_212843_1_.yHeadRot, p_212843_4_);
       float f2 = MathHelper.lerp(p_212843_4_, p_212843_1_.xRotO, p_212843_1_.xRot);
       float f3 = f1 - f;
       float f4 = f2 * ((float)Math.PI / 180F);
       if (f3 > 20.0F) {
          f3 = 20.0F;
       }

       if (f3 < -20.0F) {
          f3 = -20.0F;
       }

       if (p_212843_3_ > 0.2F) {
          f4 += MathHelper.cos(p_212843_2_ * 0.4F) * 0.15F * p_212843_3_;
       }

       float f5 = 0;
       float f6 = 0;
       float f7 = 1.0F - f6;
       float f8 = 0;
       float f9 = (float)p_212843_1_.tickCount + p_212843_4_;
       this.horseHeadParts.y = 4.0F;
       this.horseHeadParts.z = -12.0F;
       this.horseBody.xRot = 0.0F;
       this.horseHeadParts.xRot = ((float)Math.PI / 6F) + f4;
       this.horseHeadParts.yRot = f3 * ((float)Math.PI / 180F);
       float f10 = p_212843_1_.isInWater() ? 0.2F : 1.0F;
       float f11 = MathHelper.cos(f10 * p_212843_2_ * 0.6662F + (float)Math.PI);
       float f12 = f11 * 0.8F * p_212843_3_;
       float f13 = (1.0F - Math.max(f6, f5)) * (((float)Math.PI / 6F) + f4 + f8 * MathHelper.sin(f9) * 0.05F);
       this.horseHeadParts.xRot = f6 * (0.2617994F + f4) + f5 * (2.1816616F + MathHelper.sin(f9) * 0.05F) + f13;
       this.horseHeadParts.yRot = f6 * f3 * ((float)Math.PI / 180F) + (1.0F - Math.max(f6, f5)) * this.horseHeadParts.yRot;
       this.horseHeadParts.y = f6 * -4.0F + f5 * 11.0F + (1.0F - Math.max(f6, f5)) * this.horseHeadParts.y;
       this.horseHeadParts.z = f6 * -4.0F + f5 * -12.0F + (1.0F - Math.max(f6, f5)) * this.horseHeadParts.z;
       this.horseBody.xRot = f6 * (-(float)Math.PI / 4F) + f7 * this.horseBody.xRot;
       float f14 = 0.2617994F * f6;
       float f15 = MathHelper.cos(f9 * 0.6F + (float)Math.PI);
       float f16 = ((-(float)Math.PI / 3F) + f15) * f6 + f12 * f7;
       float f17 = ((-(float)Math.PI / 3F) - f15) * f6 - f12 * f7;
       this.leg3.y = 2.0F * f6 + 14.0F * f7;
       this.leg3.z = -6.0F * f6 - 10.0F * f7;
       this.leg4.y = this.leg3.y;
       this.leg4.z = this.leg3.z;
       this.leg3.xRot = f16;
       this.leg4.xRot = f17;
    }

}
