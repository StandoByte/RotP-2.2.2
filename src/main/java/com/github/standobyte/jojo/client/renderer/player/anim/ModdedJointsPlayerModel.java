package com.github.standobyte.jojo.client.renderer.player.anim;

import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.stand.StandEntityAction.Phase;
import com.github.standobyte.jojo.capability.entity.LivingUtilCapProvider;
import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.client.model.entity.stand.HumanoidStandModel;
import com.github.standobyte.jojo.client.model.pose.IModelPose;
import com.github.standobyte.jojo.client.model.pose.ModelPose;
import com.github.standobyte.jojo.client.model.pose.ModelPose.ModelAnim;
import com.github.standobyte.jojo.client.model.pose.ModelPoseSided;
import com.github.standobyte.jojo.client.model.pose.ModelPoseTransition;
import com.github.standobyte.jojo.client.model.pose.ModelPoseTransitionMultiple;
import com.github.standobyte.jojo.client.model.pose.RotationAngle;
import com.github.standobyte.jojo.client.model.pose.anim.barrage.PlayerArmsBarrageAnimation;
import com.github.standobyte.jojo.init.power.hamon.ModHamon;
import com.github.standobyte.jojo.power.nonstand.INonStandPower;
import com.github.standobyte.jojo.util.reflection.ClientReflection;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.util.Util;

@Deprecated
public class ModdedJointsPlayerModel extends PlayerModel<AbstractClientPlayerEntity> {
    private final ModelRenderer leftForeArm;
    private final ModelRenderer leftSleeveForeArm;
    private final ModelRenderer rightForeArm;
    private final ModelRenderer rightSleeveForeArm;
    private final ModelRenderer leftLowerLeg;
    private final ModelRenderer leftPantsLowerLeg;
    private final ModelRenderer rightLowerLeg;
    private final ModelRenderer rightPantsLowerLeg;
    boolean layer;
    private boolean barrage;
    private float xRotation;
    private float yRotation;
    private AbstractClientPlayerEntity entity;    
    protected ModelRenderer upperPart;
    private PlayerArmsBarrageAnimation barrageAnim;
    
    public ModdedJointsPlayerModel(float boxesInflation, boolean slim) {
        super(boxesInflation, slim);

        if (slim) {
            ClientReflection.setCubes(leftArm, Util.make(new ObjectArrayList<>(), list -> 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    32, 48, -1.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, 
                    boxesInflation, boxesInflation, boxesInflation, false, texWidth, texHeight), LimbPart.UPPER))));
            
            leftForeArm = new ModelRenderer(this);
            leftForeArm.setPos(2.0F, 4.0F, 2.0F);
            ClientReflection.setCubes(leftForeArm, Util.make(new ObjectArrayList<>(), list -> 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    32, 54, -3.0F, 0.0F, -4.0F, 3.0F, 6.0F, 4.0F, 
                    boxesInflation, boxesInflation, boxesInflation, false, texWidth, texHeight), LimbPart.LOWER))));

            
            ClientReflection.setCubes(leftSleeve, Util.make(new ObjectArrayList<>(), list -> 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    48, 48, -1.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, 
                    boxesInflation + 0.25F, boxesInflation + 0.25F, boxesInflation + 0.25F, false, texWidth, texHeight), LimbPart.UPPER))));
            
            leftSleeveForeArm = new ModelRenderer(this);
            leftSleeveForeArm.setPos(2.0F, 4.25F, 2.25F);
            ClientReflection.setCubes(leftSleeveForeArm, Util.make(new ObjectArrayList<>(), list -> 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    48, 54, -3.0F, 0.25F, -4.25F, 3.0F, 6.0F, 4.0F, 
                    boxesInflation + 0.25F, boxesInflation + 0.25F, boxesInflation + 0.25F, false, texWidth, texHeight), LimbPart.LOWER))));
            
            
            ClientReflection.setCubes(rightArm, Util.make(new ObjectArrayList<>(), list -> 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    40, 16, -2.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, 
                    boxesInflation, boxesInflation, boxesInflation, false, texWidth, texHeight), LimbPart.UPPER))));
            
            rightForeArm = new ModelRenderer(this);
            rightForeArm.setPos(-2.0F, 4.0F, 2.0F);
            ClientReflection.setCubes(rightForeArm, Util.make(new ObjectArrayList<>(), list -> 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    40, 22, 0.0F, 0.0F, -4.0F, 3.0F, 6.0F, 4.0F, 
                    boxesInflation, boxesInflation, boxesInflation, false, texWidth, texHeight), LimbPart.LOWER))));
            
            
            ClientReflection.setCubes(rightSleeve, Util.make(new ObjectArrayList<>(), list -> 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    40, 32, -1.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                    boxesInflation + 0.25F, boxesInflation + 0.25F, boxesInflation + 0.25F, false, texWidth, texHeight), LimbPart.UPPER))));
            
            rightSleeveForeArm = new ModelRenderer(this);
            rightSleeveForeArm.setPos(-2.0F, 4.25F, 2.25F);
            ClientReflection.setCubes(rightSleeveForeArm, Util.make(new ObjectArrayList<>(), list -> 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    40, 38, 0.0F, 0.25F, -4.25F, 3.0F, 6.0F, 4.0F, 
                    boxesInflation + 0.25F, boxesInflation + 0.25F, boxesInflation + 0.25F, false, texWidth, texHeight), LimbPart.LOWER))));
        }
        
        else {
            ClientReflection.setCubes(leftArm, Util.make(new ObjectArrayList<>(), list -> 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    32, 48, -1.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                    boxesInflation, boxesInflation, boxesInflation, false, texWidth, texHeight), LimbPart.UPPER))));
            
            leftForeArm = new ModelRenderer(this);
            leftForeArm.setPos(3.0F, 4.0F, 2.0F);
            ClientReflection.setCubes(leftForeArm, Util.make(new ObjectArrayList<>(), list -> 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    32, 54, -4.0F, 0.0F, -4.0F, 4.0F, 6.0F, 4.0F, 
                    boxesInflation, boxesInflation, boxesInflation, false, texWidth, texHeight), LimbPart.LOWER))));

            
            ClientReflection.setCubes(leftSleeve, Util.make(new ObjectArrayList<>(), list -> 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    48, 48, -1.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                    boxesInflation + 0.25F, boxesInflation + 0.25F, boxesInflation + 0.25F, false, texWidth, texHeight), LimbPart.UPPER))));
            
            leftSleeveForeArm = new ModelRenderer(this);
            leftSleeveForeArm.setPos(3.0F, 4.25F, 2.25F);
            ClientReflection.setCubes(leftSleeveForeArm, Util.make(new ObjectArrayList<>(), list -> 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    48, 54, -4.0F, 0.25F, -4.25F, 4.0F, 6.0F, 4.0F, 
                    boxesInflation + 0.25F, boxesInflation + 0.25F, boxesInflation + 0.25F, false, texWidth, texHeight), LimbPart.LOWER))));
            
            
            ClientReflection.setCubes(rightArm, Util.make(new ObjectArrayList<>(), list -> 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    40, 16, -3.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                    boxesInflation, boxesInflation, boxesInflation, false, texWidth, texHeight), LimbPart.UPPER))));
            
            rightForeArm = new ModelRenderer(this);
            rightForeArm.setPos(-3.0F, 4.0F, 2.0F);
            ClientReflection.setCubes(rightForeArm, Util.make(new ObjectArrayList<>(), list -> 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    40, 22, 0.0F, 0.0F, -4.0F, 4.0F, 6.0F, 4.0F, 
                    boxesInflation, boxesInflation, boxesInflation, false, texWidth, texHeight), LimbPart.LOWER))));
            
            
            ClientReflection.setCubes(rightSleeve, Util.make(new ObjectArrayList<>(), list -> 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    40, 32, -3.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                    boxesInflation + 0.25F, boxesInflation + 0.25F, boxesInflation + 0.25F, false, texWidth, texHeight), LimbPart.UPPER))));
            
            rightSleeveForeArm = new ModelRenderer(this);
            rightSleeveForeArm.setPos(-3.0F, 4.25F, 2.25F);
            ClientReflection.setCubes(rightSleeveForeArm, Util.make(new ObjectArrayList<>(), list -> 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    40, 38, 0.0F, 0.25F, -4.25F, 4.0F, 6.0F, 4.0F, 
                    boxesInflation + 0.25F, boxesInflation + 0.25F, boxesInflation + 0.25F, false, texWidth, texHeight), LimbPart.LOWER))));
        }
        leftArm.addChild(leftForeArm);
        rightArm.addChild(rightForeArm);
        leftSleeve.addChild(leftSleeveForeArm);
        rightSleeve.addChild(rightSleeveForeArm);
        
        
        ClientReflection.setCubes(leftLeg, Util.make(new ObjectArrayList<>(), list -> 
        list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                16, 48, -2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                boxesInflation, boxesInflation, boxesInflation, false, texWidth, texHeight), LimbPart.UPPER))));
        
        leftLowerLeg = new ModelRenderer(this);
        leftLowerLeg.setPos(2.0F, 6.0F, -2.0F);
        leftLeg.addChild(leftLowerLeg);
        ClientReflection.setCubes(leftLowerLeg, Util.make(new ObjectArrayList<>(), list -> 
        list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                16, 54, -4.0F, 0.0F, 0.0F, 4.0F, 6.0F, 4.0F, 
                boxesInflation, boxesInflation, boxesInflation, false, texWidth, texHeight), LimbPart.LOWER))));
        
        
        ClientReflection.setCubes(leftPants, Util.make(new ObjectArrayList<>(), list -> 
        list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                0, 48, -2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                boxesInflation + 0.25F, boxesInflation + 0.25F, boxesInflation + 0.25F, false, texWidth, texHeight), LimbPart.UPPER))));
        
        leftPantsLowerLeg = new ModelRenderer(this);
        leftPantsLowerLeg.setPos(2.0F, 6.25F, -2.25F);
        leftPants.addChild(leftPantsLowerLeg);
        ClientReflection.setCubes(leftPantsLowerLeg, Util.make(new ObjectArrayList<>(), list -> 
        list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                0, 54, -4.0F, 0.25F, 0.25F, 4.0F, 6.0F, 4.0F, 
                boxesInflation + 0.25F, boxesInflation + 0.25F, boxesInflation + 0.25F, false, texWidth, texHeight), LimbPart.LOWER))));
        
        
        ClientReflection.setCubes(rightLeg, Util.make(new ObjectArrayList<>(), list -> 
        list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                0, 16, -2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                boxesInflation, boxesInflation, boxesInflation, false, texWidth, texHeight), LimbPart.UPPER))));
        
        rightLowerLeg = new ModelRenderer(this);
        rightLowerLeg.setPos(-2.0F, 6.0F, -2.0F);
        rightLeg.addChild(rightLowerLeg);
        ClientReflection.setCubes(rightLowerLeg, Util.make(new ObjectArrayList<>(), list -> 
        list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                0, 22, 0.0F, 0.0F, 0.0F, 4.0F, 6.0F, 4.0F, 
                boxesInflation, boxesInflation, boxesInflation, false, texWidth, texHeight), LimbPart.LOWER))));
        
        
        ClientReflection.setCubes(rightPants, Util.make(new ObjectArrayList<>(), list -> 
        list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                0, 32, -2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F,
                boxesInflation + 0.25F, boxesInflation + 0.25F, boxesInflation + 0.25F, false, texWidth, texHeight), LimbPart.UPPER))));
        
        rightPantsLowerLeg = new ModelRenderer(this);
        rightPantsLowerLeg.setPos(-2.0F, 6.25F, -2.25F);
        rightPants.addChild(rightPantsLowerLeg);
        ClientReflection.setCubes(rightPantsLowerLeg, Util.make(new ObjectArrayList<>(), list -> 
        list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                0, 38, 0.0F, 0.25F, 0.25F, 4.0F, 6.0F, 4.0F,
                boxesInflation + 0.25F, boxesInflation + 0.25F, boxesInflation + 0.25F, false, texWidth, texHeight), LimbPart.LOWER))));
        
        init();
    }
    
    

    public boolean hasCustomAnim(AbstractClientPlayerEntity player) {
        return INonStandPower.getNonStandPowerOptional(player).map(power -> {
            Action<?> heldAction = power.getHeldAction(true);
            if (heldAction == ModHamon.JONATHAN_OVERDRIVE_BARRAGE.get()) {
                return true;
            }
            return false;
        }).orElse(false)
                ||
        player.getCapability(LivingUtilCapProvider.CAPABILITY).map(cap -> {
            return cap.isInSendoWaveKick();
        })
        .orElse(false)
                ||
        false;
    }

    @Override
    public void setupAnim(AbstractClientPlayerEntity player, float walkAnimPos, float walkAnimSpeed, float ticks, float yRotationOffset, float xRotation) {
        boolean prevBarrage = barrage;
        ClientUtil.setRotationAngle(leftForeArm, 0, 0, 0);
        ClientUtil.setRotationAngle(rightForeArm, 0, 0, 0);
        ClientUtil.setRotationAngle(leftLowerLeg, 0, 0, 0);
        ClientUtil.setRotationAngle(rightLowerLeg, 0, 0, 0);
        
        INonStandPower.getNonStandPowerOptional(player).ifPresent(power -> {
            barrage = power.getHeldAction() == ModHamon.JONATHAN_OVERDRIVE_BARRAGE.get();
            if (barrage) {
                attackTime = ticks % 2 / 2F;
            }
        });
        if (!prevBarrage && barrage) {
            barrageAnim.onAnimStart(player, yRotationOffset, xRotation, layer);
        }

        this.xRotation = xRotation;
        this.yRotation = yRotationOffset;
        super.setupAnim(player, walkAnimPos, walkAnimSpeed, ticks, yRotationOffset, xRotation);
        
        player.getCapability(LivingUtilCapProvider.CAPABILITY).ifPresent(cap -> {
            if (cap.isInSendoWaveKick()) {
                sendoWaveKick();
            }
        });

//        setRotationAngle(body, 0, 0, 0);
//        setRotationAngle(leftArm, -0.5133F, 0.1084F, 0.1897F);
//        setRotationAngle(leftForeArm, 0.0F, 0.0F, 0.6109F);
//        setRotationAngle(rightArm, -0.8202F, -0.2756F, 1.7397F);
//        setRotationAngle(rightForeArm, -1.5708F, 0.0F, 0.0F);
//        setRotationAngle(leftLeg, 0.0F, 0.0F, -0.1309F);
//        setRotationAngle(rightLeg, 0.0F, 0.0F, 0.2618F);

        leftPants.copyFrom(leftLeg);
        rightPants.copyFrom(rightLeg);
        leftSleeve.copyFrom(leftArm);
        rightSleeve.copyFrom(rightArm);
        jacket.copyFrom(body);
        copyRot(leftPantsLowerLeg, leftLowerLeg);
        copyRot(rightPantsLowerLeg, rightLowerLeg);
        copyRot(leftSleeveForeArm, leftForeArm);
        copyRot(rightSleeveForeArm, rightForeArm);
        if (!false) barrageAnim.getBarrageSwingsHolder(player).updateSwings(Minecraft.getInstance());
    }

    @Override
    protected void setupAttackAnimation(AbstractClientPlayerEntity player, float ticks) {
        if (barrage) {
            this.entity = player;
            overdriveBarrage(player, ticks);
        }
        else {
            super.setupAttackAnimation(player, ticks);
        }
    }
    
    private void overdriveBarrage(AbstractClientPlayerEntity player, float ticks) {
        HandSide side = getAttackArm(player);
        // FIXME (player anim) rotation
        barrageAnim.animate(Phase.PERFORM, 0, player, ticks, yRotation, xRotation, side, false);
    }

    private void sendoWaveKick() {
        ClientUtil.setRotationAngleDegrees(upperPart, 0, -15, 0);
        ClientUtil.setRotationAngleDegrees(leftArm, 45, 0, -30);
        ClientUtil.setRotationAngleDegrees(rightArm, 30, 0, 7.5F);
        ClientUtil.setRotationAngleDegrees(leftLeg, 45, 0, 0);
        ClientUtil.setRotationAngleDegrees(rightLeg, -75, 0, 0);
        ClientUtil.setRotationAngleDegrees(rightLowerLeg, 87.5F, 0, 0);
    }
    
    
    
    private void init() {
        upperPart = new ModelRenderer(this);
        upperPart.setPos(0.0F, 0.0F, 0.0F);
        upperPart.addChild(body);
        upperPart.addChild(leftArm);
        upperPart.addChild(rightArm);
        
        
        oppositeHandside = Util.make(HashBiMap.create(), map -> {
            map.put(leftArm, rightArm);
            map.put(leftForeArm, rightForeArm);
            map.put(leftLeg, rightLeg);
            map.put(leftLowerLeg, rightLowerLeg);
        });


        // FIXME (player anim) rotation
        ModelAnim<AbstractClientPlayerEntity> armsXRotation = (rotationAmount, entity, ticks, yRotationOffset, xRotation) -> {
//            leftArm.xRotSecond = xRotation * MathUtil.DEG_TO_RAD;
//            rightArm.xRotSecond = xRotation * MathUtil.DEG_TO_RAD;
        };

        RotationAngle[] barrageRightImpact = new RotationAngle[] {
                RotationAngle.fromDegrees(body, 0, 0, 0),
                RotationAngle.fromDegrees(upperPart, 0, -30, 0),
                RotationAngle.fromDegrees(leftArm, 22.5F, 0, -60),
                RotationAngle.fromDegrees(leftForeArm, -90, 0, 0),
                RotationAngle.fromDegrees(rightArm, -90, 0, 90),
                RotationAngle.fromDegrees(rightForeArm, 0, 0, 0)
        };
        
        IModelPose<AbstractClientPlayerEntity> barrageHitStart = new ModelPoseSided<>(
                new ModelPose<AbstractClientPlayerEntity>(barrageRightImpact).setAdditionalAnim(armsXRotation),
                new ModelPose<AbstractClientPlayerEntity>(mirrorAngles(barrageRightImpact)).setAdditionalAnim(armsXRotation));
        
        IModelPose<AbstractClientPlayerEntity> barrageHitImpact = new ModelPoseSided<>(
                new ModelPose<AbstractClientPlayerEntity>(mirrorAngles(barrageRightImpact)).setAdditionalAnim(armsXRotation),
                new ModelPose<AbstractClientPlayerEntity>(barrageRightImpact).setAdditionalAnim(armsXRotation));
        
        IModelPose<AbstractClientPlayerEntity> barrageRecovery = new ModelPose<>(new RotationAngle[] {
                RotationAngle.fromDegrees(body, 0, 0, 0),
                RotationAngle.fromDegrees(upperPart, 0, 0, 0),
                RotationAngle.fromDegrees(leftArm, 22.5F, 0, -22.5F),
                RotationAngle.fromDegrees(leftForeArm, -75, 7.5F, 22.5F),
                RotationAngle.fromDegrees(rightArm, 22.5F, 0, 22.5F),
                RotationAngle.fromDegrees(rightForeArm, -75, -7.5F, -22.5F)
        });
        

        barrageAnim = new PlayerArmsBarrageAnimation(this, 
                
                new ModelPoseTransition<AbstractClientPlayerEntity>(barrageHitStart, barrageHitImpact).setEasing(HumanoidStandModel::barrageHitEasing), 
                
                new ModelPoseTransitionMultiple.Builder<AbstractClientPlayerEntity>(new ModelPose<AbstractClientPlayerEntity>(
                        RotationAngle.fromDegrees(body, 0, 0, 0),
                        RotationAngle.fromDegrees(upperPart, 0, 0, 0),
                        RotationAngle.fromDegrees(leftArm, -33.75F, 0, -75),
                        RotationAngle.fromDegrees(leftForeArm, -67.5F, 0, 0),
                        RotationAngle.fromDegrees(rightArm, -33.75F, 0, 75),
                        RotationAngle.fromDegrees(rightForeArm, -67.5F, 0, 0)).setAdditionalAnim(armsXRotation))
                .addPose(0.25F, barrageRecovery)
                .addPose(0.5F, barrageRecovery)
                .build(new ModelPose<AbstractClientPlayerEntity>(
                        new RotationAngle[] {
                                new RotationAngle(body, 0, 0, 0),
                                new RotationAngle(rightArm, 0, 0, 0),
                                new RotationAngle(rightForeArm, 0, 0, 0),
                                new RotationAngle(leftArm, 0, 0, 0),
                                new RotationAngle(leftForeArm, 0, 0, 0),
                                new RotationAngle(rightLeg, 0, 0, 0),
                                new RotationAngle(rightLowerLeg, 0, 0, 0),
                                new RotationAngle(leftLeg, 0, 0, 0),
                                new RotationAngle(leftLowerLeg, 0, 0, 0)
                        })), 
                4);
    }
    


    public void copyPropertiesAndJointsTo(ModdedJointsPlayerModel model) {
        copyPropertiesTo(model);
        model.leftForeArm.copyFrom(this.leftForeArm);
        model.rightForeArm.copyFrom(this.rightForeArm);
        model.leftLowerLeg.copyFrom(this.leftLowerLeg);
        model.rightLowerLeg.copyFrom(this.rightLowerLeg);
    }
    
    private static void copyRot(ModelRenderer to, ModelRenderer from) {
        to.xRot = from.xRot;
        to.yRot = from.yRot;
        to.zRot = from.zRot;
    }
    
    private ModelRenderer.ModelBox fixLimbPolygons(ModelRenderer.ModelBox box, LimbPart part) {
        ModelRenderer.TexturedQuad[] polygons = ClientReflection.getPolygons(box);
        switch (part) {
        case UPPER:
            ClientReflection.setPolygons(box, new ModelRenderer.TexturedQuad[] {
                    polygons[0],
                    polygons[1],
                    polygons[2],
                    polygons[4],
                    polygons[5]
            });
            break;
        case LOWER:
            shiftRemap(polygons[3], 0, -6);
            ClientReflection.setPolygons(box, new ModelRenderer.TexturedQuad[] {
                    polygons[0],
                    polygons[1],
                    polygons[3],
                    polygons[4],
                    polygons[5]
            });
            break;
        }
        return box;
    }
    
    private void shiftRemap(ModelRenderer.TexturedQuad quad, float texXShift, float texYShift) {
        float uShift = texXShift / texWidth;
        float vShift = texYShift / texHeight;
        for (int i = 0; i < quad.vertices.length; i++) {
            quad.vertices[i] = shiftRemap(quad.vertices[i], uShift, vShift);
        }
    }
    
    private ModelRenderer.PositionTextureVertex shiftRemap(ModelRenderer.PositionTextureVertex vertex, float uShift, float vShift) {
        return vertex.remap(vertex.u + uShift, vertex.v + vShift);
    }
    
    private static enum LimbPart {
        UPPER,
        LOWER;
    }
    
    @Override
    public ModelRenderer getArm(HandSide side) {
        return super.getArm(side);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(this.upperPart, this.rightLeg, this.leftLeg, this.hat, this.leftPants, this.rightPants, this.leftSleeve, this.rightSleeve, this.jacket);
    }
    
    
    
    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, 
            int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        if (barrage && !layer) {
            layer = true;
            barrageAnim.getBarrageSwingsHolder(entity).renderBarrageSwings(this, entity, 
                    matrixStack, buffer, packedLight, packedOverlay, 
                    // FIXME (player anim) rotation
                    yRotation, xRotation, red, green, blue, alpha);
        }
    }
    
    
    protected RotationAngle[] mirrorAngles(RotationAngle[] angles) {
        RotationAngle[] mirrored = new RotationAngle[angles.length];
        for (int i = 0; i < angles.length; i++) {
            RotationAngle angle = angles[i];
            mirrored[i] = new RotationAngle(getOppositeHandside(angle.modelRenderer), angle.angleX, -angle.angleY, -angle.angleZ);
        }
        return mirrored;
    }   
    
    protected BiMap<ModelRenderer, ModelRenderer> oppositeHandside;
    public final ModelRenderer getOppositeHandside(ModelRenderer modelRenderer) {
        return oppositeHandside.computeIfAbsent(modelRenderer, k -> oppositeHandside.inverse().getOrDefault(modelRenderer, modelRenderer));
    }
    
    public void setArmOnlyVisibility(HandSide side) {
        setAllVisible(false);
        getArm(side).visible = true;
    }
}
