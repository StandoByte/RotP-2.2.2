package com.github.standobyte.jojo.client.renderer.player.anim;

import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.init.ModEntityTypes;
import com.github.standobyte.jojo.init.power.hamon.ModHamon;
import com.github.standobyte.jojo.init.power.hamon.ModHamonSkills;
import com.github.standobyte.jojo.init.power.vampirism.ModVampirism;
import com.github.standobyte.jojo.power.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.nonstand.type.hamon.HamonData;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.CharacterHamonTechnique;
import com.github.standobyte.jojo.util.utils.MathUtil;
import com.google.common.collect.ImmutableList;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.common.util.LazyOptional;

@Deprecated
public class ModdedRotationsPlayerModel extends PlayerModel<AbstractClientPlayerEntity> {
    public final ModelRenderer wholeBody;
    public final ModelRenderer upperBodyPart;
    

    public ModdedRotationsPlayerModel(float boxesInflation, boolean slim) {
        super(boxesInflation, slim);
        
        upperBodyPart = new ModelRenderer(this);
        upperBodyPart.setPos(0.0F, 0.0F, 0.0F);
        upperBodyPart.addChild(body);
        upperBodyPart.addChild(jacket);
//        upperBodyPart.addChild(cloak);
        upperBodyPart.addChild(leftArm);
        upperBodyPart.addChild(rightArm);
        upperBodyPart.addChild(leftSleeve);
        upperBodyPart.addChild(rightSleeve);
        
        wholeBody = new ModelRenderer(this);
        wholeBody.setPos(0.0F, 0.0F, 0.0F);
        wholeBody.addChild(upperBodyPart);
        wholeBody.addChild(leftLeg);
        wholeBody.addChild(rightLeg);
        wholeBody.addChild(leftPants);
        wholeBody.addChild(rightPants);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(wholeBody);
    }

    public boolean hasCustomAnim(AbstractClientPlayerEntity player) {
        Entity vehicle = player.getVehicle();
        if (vehicle != null && vehicle.getType() == ModEntityTypes.LEAVES_GLIDER.get()) {
            return true;
        }

        return INonStandPower.getNonStandPowerOptional(player).map(power -> {
            Action<?> heldAction = power.getHeldAction(true);
            if (
                    heldAction == ModVampirism.VAMPIRISM_BLOOD_DRAIN.get()
                    || heldAction == ModVampirism.VAMPIRISM_FREEZE.get()
                    || heldAction == ModVampirism.VAMPIRISM_BLOOD_GIFT.get()
                    || heldAction == ModHamon.ZEPPELI_TORNADO_OVERDRIVE.get()
                    || heldAction == ModHamon.ZEPPELI_TORNADO_OVERDRIVE_HORIZONTAL.get()
                    ) {
                return true;
            }
            
            if (power.getTypeSpecificData(ModHamon.HAMON.get()).map(hamon -> {
                return hamon.getCharacterTechnique() != null;
            }).orElse(false)) {
                return heldAction == ModHamon.HAMON_BREATH.get();
            }
            
            return false;
        }).orElse(false);
    }

    @Override
    public void setupAnim(AbstractClientPlayerEntity player, float walkAnimPos, float walkAnimSpeed, float ticks, float yRotationOffset, float xRotation) {
        super.setupAnim(player, walkAnimPos, walkAnimSpeed, ticks, yRotationOffset, xRotation);

        Entity vehicle = player.getVehicle();
        if (vehicle != null && vehicle.getType() == ModEntityTypes.LEAVES_GLIDER.get()) {
            gliderFlight();
        }

        LazyOptional<INonStandPower> powerOptional = INonStandPower.getNonStandPowerOptional(player);
        powerOptional.ifPresent(power -> {
            float partialTick = ticks - player.tickCount;

            Action<?> heldAction = power.getHeldAction(true);
            if (heldAction == ModVampirism.VAMPIRISM_BLOOD_DRAIN.get() || heldAction == ModVampirism.VAMPIRISM_BLOOD_GIFT.get()) {
                vampireArmDrain(power.getHeldActionTarget(), player);
            }
            else if (heldAction == ModVampirism.VAMPIRISM_FREEZE.get()) {
                vampireArmsFreeze(xRotation);
            }
            else if (heldAction == ModHamon.ZEPPELI_TORNADO_OVERDRIVE.get() || heldAction == ModHamon.ZEPPELI_TORNADO_OVERDRIVE_HORIZONTAL.get()) {
                tornadoOverdriveLegs();
            }
            else if (heldAction == ModHamon.HAMON_BREATH.get()) {
                hamonBreathPose(power.getTypeSpecificData(ModHamon.HAMON.get()).map(HamonData::getCharacterTechnique).orElse(null));
            }
        });
        
        leftPants.copyFrom(leftLeg);
        rightPants.copyFrom(rightLeg);
        leftSleeve.copyFrom(leftArm);
        rightSleeve.copyFrom(rightArm);
        jacket.copyFrom(body);
    }
    
    private void gliderFlight() {
        leftArm.xRot = 3.1416F;
        leftArm.yRot = 0;
        leftArm.zRot = 0;
        rightArm.xRot = 3.1416F;
        rightArm.yRot = 0;
        rightArm.zRot = 0;
    }
    
    private void vampireArmDrain(ActionTarget target, LivingEntity user) {
        // FIXME (player anim) sync held action target
        if (target != null) {
            target.getBoundingBox(user.level).ifPresent(aabb -> {
                Vector3d targetPos = aabb.getCenter();
                Vector3d armOriginPos;
                ModelRenderer arm;
                switch (user.getMainArm()) {
                case LEFT:
                    arm = leftArm;
                    armOriginPos = user.position().add(new Vector3d(
                            user.getBbWidth() * 0.25, 
                            user.getBbHeight() * 0.6875, 
                            user.getBbWidth() * 0.5)
                            .yRot((180 - user.yBodyRot) * MathUtil.DEG_TO_RAD));
                    break;
                case RIGHT:
                    arm = rightArm;
                    armOriginPos = user.position().add(new Vector3d(
                            user.getBbWidth() * 0.75, 
                            user.getBbHeight() * 0.6875, 
                            user.getBbWidth() * 0.5)
                            .yRot((180 - user.yBodyRot) * MathUtil.DEG_TO_RAD));
                    break;
                default:
                    arm = null;
                    armOriginPos = null;
                }
                Vector3d armVec = targetPos.subtract(armOriginPos).yRot((user.yBodyRot) * MathUtil.DEG_TO_RAD).normalize();
                arm.xRot = (float) (-Math.asin(armVec.y) - Math.PI / 2);
                arm.yRot = (float) -MathHelper.atan2(armVec.x, armVec.z);
                if (user.getMainArm() == HandSide.LEFT) arm.yRot *= -1;
            });
        }
    }
    
    private void vampireArmsFreeze(float xRotation) {
        leftArm.xRot = (xRotation - 90F) * MathUtil.DEG_TO_RAD;
        rightArm.xRot = (xRotation - 90F) * MathUtil.DEG_TO_RAD;
    }
    
    private void tornadoOverdriveLegs() {
        ClientUtil.setRotationAngle(leftLeg, 0, 0, 0.1745F);
        ClientUtil.setRotationAngle(rightLeg, 0, 0, -0.1745F);
    }
    
    private void hamonBreathPose(CharacterHamonTechnique technique) {
        if (technique == ModHamonSkills.CHARACTER_JONATHAN.get()) {
            ClientUtil.setRotationAngleDegrees(wholeBody, 0, 12.5F, 7.5F);
            ClientUtil.setRotationAngleDegrees(leftArm, -61.2011F, 15.9621F, 127.87F);
            ClientUtil.setRotationAngleDegrees(rightArm, 11.5752F, -4.7511F, 22.0182F);
            ClientUtil.setRotationAngleDegrees(leftLeg, -7.5F, 0, -25F);
            ClientUtil.setRotationAngleDegrees(rightLeg, 9.7822F, -2.5759F, 2.1658F);
        }
    }
}
