package com.github.standobyte.jojo.client;

import java.util.Map;
import java.util.function.UnaryOperator;

import com.github.standobyte.jojo.JojoMod;
import com.github.standobyte.jojo.client.model.ArmorModelRegistry;
import com.github.standobyte.jojo.client.model.armor.BladeHatArmorModel;
import com.github.standobyte.jojo.client.model.armor.BreathControlMaskModel;
import com.github.standobyte.jojo.client.model.armor.SatiporojaScarfArmorModel;
import com.github.standobyte.jojo.client.model.armor.StoneMaskModel;
import com.github.standobyte.jojo.client.model.entity.projectile.HamonBubbleModel;
import com.github.standobyte.jojo.client.model.entity.stand.PlayerSkinImitatingStandModel;
import com.github.standobyte.jojo.client.model.item.RoadRollerBakedModel;
import com.github.standobyte.jojo.client.particle.AirStreamParticle;
import com.github.standobyte.jojo.client.particle.BloodParticle;
import com.github.standobyte.jojo.client.particle.CDRestorationParticle;
import com.github.standobyte.jojo.client.particle.HamonAuraParticle;
import com.github.standobyte.jojo.client.particle.HamonSparkParticle;
import com.github.standobyte.jojo.client.particle.MeteoriteVirusParticle;
import com.github.standobyte.jojo.client.particle.OneTickFlameParticle;
import com.github.standobyte.jojo.client.particle.OnomatopoeiaParticle;
import com.github.standobyte.jojo.client.renderer.entity.AfterimageRenderer;
import com.github.standobyte.jojo.client.renderer.entity.CloneRenderer;
import com.github.standobyte.jojo.client.renderer.entity.CrimsonBubbleRenderer;
import com.github.standobyte.jojo.client.renderer.entity.HamonBlockChargeRenderer;
import com.github.standobyte.jojo.client.renderer.entity.HamonProjectileShieldRenderer;
import com.github.standobyte.jojo.client.renderer.entity.LeavesGliderRenderer;
import com.github.standobyte.jojo.client.renderer.entity.MRDetectorRenderer;
import com.github.standobyte.jojo.client.renderer.entity.No;
import com.github.standobyte.jojo.client.renderer.entity.PillarmanTempleEngravingRenderer;
import com.github.standobyte.jojo.client.renderer.entity.RoadRollerRenderer;
import com.github.standobyte.jojo.client.renderer.entity.SimpleEntityRenderer;
import com.github.standobyte.jojo.client.renderer.entity.SoulRenderer;
import com.github.standobyte.jojo.client.renderer.entity.TurquoiseBlueOverdriveRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.MRFlameRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.SCFlameRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.beam.LightBeamRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.beam.SpaceRipperStingyEyesRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.extending.HGBarrierRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.extending.HGGrapplingStringRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.extending.HGStringRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.extending.MRRedBindRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.extending.SFGrapplingStringRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.extending.SPStarFingerRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.extending.SatiporojaScarfBindingRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.extending.SatiporojaScarfRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.extending.SnakeMufflerRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.projectile.CDBlockBulletRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.projectile.CDBloodCutterRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.projectile.HGEmeraldRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.projectile.HamonBubbleBarrierRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.projectile.HamonBubbleCutterRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.projectile.HamonBubbleRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.projectile.HamonCutterRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.projectile.LangSpitRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.projectile.MRCrossfireHurricaneRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.projectile.MRCrossfireHurricaneSpecialRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.projectile.MRFireballRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.projectile.SCRapierRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.projectile.TommyGunBulletRenderer;
import com.github.standobyte.jojo.client.renderer.entity.damaging.stretching.ZoomPunchRenderer;
import com.github.standobyte.jojo.client.renderer.entity.itemprojectile.BladeHatRenderer;
import com.github.standobyte.jojo.client.renderer.entity.itemprojectile.ClackersRenderer;
import com.github.standobyte.jojo.client.renderer.entity.itemprojectile.KnifeRenderer;
import com.github.standobyte.jojo.client.renderer.entity.itemprojectile.StandArrowRenderer;
import com.github.standobyte.jojo.client.renderer.entity.mob.HamonMasterRenderer;
import com.github.standobyte.jojo.client.renderer.entity.mob.HungryZombieRenderer;
import com.github.standobyte.jojo.client.renderer.entity.mob.RockPaperScissorsKidRenderer;
import com.github.standobyte.jojo.client.renderer.entity.stand.MadeInHeavenRenderer;
import com.github.standobyte.jojo.client.renderer.entity.stand.SCAFRenderer;
import com.github.standobyte.jojo.client.renderer.entity.stand.StandEntityRenderer;
import com.github.standobyte.jojo.client.renderer.layer.KnifeLayer;
import com.github.standobyte.jojo.client.renderer.layer.TornadoOverdriveEffectLayer;
import com.github.standobyte.jojo.client.renderer.player.anim.ModdedJointsPlayerRenderer;
import com.github.standobyte.jojo.client.renderer.player.anim.ModdedPlayerRenderer;
import com.github.standobyte.jojo.client.renderer.player.anim.ModdedRotationsPlayerRenderer;
import com.github.standobyte.jojo.client.resources.CustomResources;
import com.github.standobyte.jojo.client.ui.hud.ActionsOverlayGui;
import com.github.standobyte.jojo.client.ui.hud.marker.CrazyDiamondAnchorMarker;
import com.github.standobyte.jojo.client.ui.hud.marker.CrazyDiamondBloodHomingMarker;
import com.github.standobyte.jojo.client.ui.hud.marker.HierophantGreenBarrierDetectionMarker;
import com.github.standobyte.jojo.client.ui.hud.marker.MarkerRenderer;
import com.github.standobyte.jojo.client.ui.screen.walkman.WalkmanScreen;
import com.github.standobyte.jojo.init.ModBlocks;
import com.github.standobyte.jojo.init.ModContainers;
import com.github.standobyte.jojo.init.ModEntityTypes;
import com.github.standobyte.jojo.init.ModItems;
import com.github.standobyte.jojo.init.ModParticles;
import com.github.standobyte.jojo.init.power.stand.ModStandActions;
import com.github.standobyte.jojo.item.ClackersItem;
import com.github.standobyte.jojo.item.StandArrowItem;
import com.github.standobyte.jojo.item.StandDiscItem;
import com.github.standobyte.jojo.item.StoneMaskItem;
import com.github.standobyte.jojo.power.stand.StandUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.particle.CloudParticle;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.FireworkRocketRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.Items;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = JojoMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    
    private static final boolean QWE = true;

    @SuppressWarnings("resource")
    private static final IItemPropertyGetter STAND_ITEM_INVISIBLE = (itemStack, clientWorld, livingEntity) -> {
        return !StandUtil.shouldStandsRender(Minecraft.getInstance().player) || QWE ? 1 : 0;
    };
    
    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BLADE_HAT.get(), BladeHatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SPACE_RIPPER_STINGY_EYES.get(), SpaceRipperStingyEyesRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.TURQUOISE_BLUE_OVERDRIVE.get(), TurquoiseBlueOverdriveRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ZOOM_PUNCH.get(), ZoomPunchRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.AFTERIMAGE.get(), AfterimageRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HAMON_PROJECTILE_SHIELD.get(), HamonProjectileShieldRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.LEAVES_GLIDER.get(), LeavesGliderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HAMON_BLOCK_CHARGE.get(), HamonBlockChargeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.AJA_STONE_BEAM.get(), LightBeamRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HAMON_CUTTER.get(), HamonCutterRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CLACKERS.get(), ClackersRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HAMON_BUBBLE.get(), HamonBubbleRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HAMON_BUBBLE_BARRIER.get(), HamonBubbleBarrierRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HAMON_BUBBLE_CUTTER.get(), HamonBubbleCutterRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CRIMSON_BUBBLE.get(), CrimsonBubbleRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SATIPOROJA_SCARF.get(), SatiporojaScarfRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SATIPOROJA_SCARF_BINDING.get(), SatiporojaScarfBindingRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SNAKE_MUFFLER.get(), SnakeMufflerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.TOMMY_GUN_BULLET.get(), TommyGunBulletRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.KNIFE.get(), KnifeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.STAND_ARROW.get(), StandArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SOUL.get(), SoulRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PILLARMAN_TEMPLE_ENGRAVING.get(), PillarmanTempleEngravingRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SP_STAR_FINGER.get(), SPStarFingerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HG_STRING.get(), HGStringRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HG_EMERALD.get(), HGEmeraldRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HG_GRAPPLING_STRING.get(), HGGrapplingStringRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HG_BARRIER.get(), HGBarrierRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SC_RAPIER.get(), SCRapierRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SC_FLAME.get(), SCFlameRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ROAD_ROLLER.get(), RoadRollerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MR_FLAME.get(), MRFlameRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MR_FIREBALL.get(), MRFireballRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MR_CROSSFIRE_HURRICANE.get(), MRCrossfireHurricaneRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MR_CROSSFIRE_HURRICANE_SPECIAL.get(), MRCrossfireHurricaneSpecialRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MR_RED_BIND.get(), MRRedBindRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MR_DETECTOR.get(), MRDetectorRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CD_BLOOD_CUTTER.get(), CDBloodCutterRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CD_BLOCK_BULLET.get(), CDBlockBulletRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.EYE_OF_ENDER_INSIDE.get(), manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer(), 1.0F, true));
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.FIREWORK_INSIDE.get(), manager -> new FireworkRocketRenderer(manager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HUNGRY_ZOMBIE.get(), HungryZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HAMON_MASTER.get(), HamonMasterRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ROCK_PAPER_SCISSORS_KID.get(), RockPaperScissorsKidRenderer::new);
        
//        RenderingRegistry.registerEntityRenderingHandler(ModStands.STAR_PLATINUM.getEntityType(), StarPlatinumRenderer::new);
//        RenderingRegistry.registerEntityRenderingHandler(ModStands.THE_WORLD.getEntityType(), TheWorldRenderer::new);
//        RenderingRegistry.registerEntityRenderingHandler(ModStands.HIEROPHANT_GREEN.getEntityType(), HierophantGreenRenderer::new);
//        RenderingRegistry.registerEntityRenderingHandler(ModStands.SILVER_CHARIOT.getEntityType(), SilverChariotRenderer::new);
//        RenderingRegistry.registerEntityRenderingHandler(ModStands.MAGICIANS_RED.getEntityType(), MagiciansRedRenderer::new);
//        RenderingRegistry.registerEntityRenderingHandler(ModStands.CRAZY_DIAMOND.getEntityType(), CrazyDiamondRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_STAR_PLATINUM.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/star_platinum.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_THE_WORLD.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/the_world.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_HIEROPHANT_GREEN.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/hierophant_green.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_SILVER_CHARIOT.getEntityType(), manager -> 
        new SCAFRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/silver_chariot.png"), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/silver_chariot_no_armor.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_MAGICIANS_RED.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(true), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/magicians_red.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_CRAZY_DIAMOND.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/crazy_diamond.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_DARK_BLUE_MOON.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(true), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/dark_blue_moon.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_KILLER_QUEEN.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/killer_queen.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_THE_HAND.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/the_hand.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_ECHOES_ACT_3.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(true), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/echoes_act_3.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_HEAVENS_DOOR.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(true), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/heavens_door.png"), 0));
//        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_BOY_II_MAN.getEntityType(), manager -> 
//        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/boy_ii_man.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_GOLD_EXPERIENCE.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/gold_experience.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_GOLD_EXPERIENCE_REQUIEM.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/gold_experience_requiem.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_KING_CRIMSON.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/king_crimson.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_STICKY_FINGERS.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/sticky_fingers.png"), 0));
//        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_PURPLE_HAZE.getEntityType(), manager -> 
//        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/purple_haze.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_KRAFT_WORK.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/kraft_work.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_GREEN_DAY.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(true), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/green_day.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_CHARIOT_REQUIEM.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/chariot_requiem.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_STONE_FREE.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/stone_free.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_WHITESNAKE.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/whitesnake.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_C_MOON.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(true), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/c_moon.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_MADE_IN_HEAVEN.getEntityType(), manager -> 
        new MadeInHeavenRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/made_in_heaven.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_KISS.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(true), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/kiss.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_WEATHER_REPORT.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/weather_report.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_DIVER_DOWN.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/diver_down.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_JUMPIN_JACK_FLASH.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/jumpin_jack_flash.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_TUSK_ACT_4.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/tusk_act_4.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_D4C.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/d4c.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_SOFT_AND_WET.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/soft_and_wet.png"), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModStandActions.STAND_WONDER_OF_U.getEntityType(), manager -> 
        new StandEntityRenderer<>(manager, new PlayerSkinImitatingStandModel<>(false), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/skins/wonder_of_u.png"), 0));

        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.KQ_BOMB.get(), No::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.STONE_FREE_GRAPPLING_STRING.get(), SFGrapplingStringRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.LANG_SPIT.get(), LangSpitRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.TUSK_BULLET.get(), No::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ENTITY_CLONE.get(), CloneRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SOFT_AND_WET_BUBBLE.get(), manager -> 
        new SimpleEntityRenderer<>(manager, new HamonBubbleModel<>(), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/projectiles/hamon_bubble.png")));
        
        ArmorModelRegistry.registerArmorModel(StoneMaskModel::new, ModItems.STONE_MASK.get());
        ArmorModelRegistry.registerArmorModel(BladeHatArmorModel::new, ModItems.BLADE_HAT.get());
        ArmorModelRegistry.registerArmorModel(BreathControlMaskModel::new, ModItems.BREATH_CONTROL_MASK.get());
        ArmorModelRegistry.registerArmorModel(SatiporojaScarfArmorModel::new, ModItems.SATIPOROJA_SCARF.get());

        event.enqueueWork(() -> {
            Minecraft mc = event.getMinecraftSupplier().get();

            ItemModelsProperties.register(ModItems.METEORIC_SCRAP.get(), new ResourceLocation(JojoMod.MOD_ID, "icon"), (itemStack, clientWorld, livingEntity) -> {
                return itemStack.getOrCreateTag().getInt("Icon");
            });
            ItemModelsProperties.register(ModItems.KNIFE.get(), new ResourceLocation(JojoMod.MOD_ID, "count"), (itemStack, clientWorld, livingEntity) -> {
                return livingEntity != null ? itemStack.getCount() : 1;
            });
            ItemModelsProperties.register(ModItems.STONE_MASK.get(), new ResourceLocation(JojoMod.MOD_ID, "stone_mask_activated"), (itemStack, clientWorld, livingEntity) -> {
                return itemStack.getTag().getByte(StoneMaskItem.NBT_ACTIVATION_KEY) > 0 ? 1 : 0;
            });
            ItemModelsProperties.register(ModItems.CLACKERS.get(), new ResourceLocation(JojoMod.MOD_ID, "clackers_spin"), (itemStack, clientWorld, livingEntity) -> {
                if (livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack) {
                    int ticksUsed = itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks();
                    return ClackersItem.clackersTexVariant(ticksUsed, ClackersItem.TICKS_MAX_POWER);
                }
                return 0;
            });
            ItemModelsProperties.register(ModItems.TOMMY_GUN.get(), new ResourceLocation(JojoMod.MOD_ID, "swing"), (itemStack, clientWorld, livingEntity) -> {
                return livingEntity != null && livingEntity.swinging && livingEntity.getItemInHand(livingEntity.swingingArm) == itemStack ? 1 : 0;
            });
            ItemModelsProperties.register(Items.BOW, new ResourceLocation(JojoMod.MOD_ID, "stand_arrow"), (itemStack, clientWorld, livingEntity) -> {
                return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack
                        && livingEntity.getProjectile(itemStack).getItem() instanceof StandArrowItem ? 1 : 0;
            });
            ItemModelsProperties.register(Items.CROSSBOW, new ResourceLocation(JojoMod.MOD_ID, "stand_arrow"), (itemStack, clientWorld, livingEntity) -> {
                return livingEntity != null && CrossbowItem.isCharged(itemStack) && (
                        CrossbowItem.containsChargedProjectile(itemStack, ModItems.STAND_ARROW.get()) || 
                        CrossbowItem.containsChargedProjectile(itemStack, ModItems.STAND_ARROW_BEETLE.get())) ? 1 : 0;
            });
            ItemModelsProperties.register(ModItems.STAND_DISC.get(), new ResourceLocation(JojoMod.MOD_ID, "stand_id"), (itemStack, clientWorld, livingEntity) -> {
                return StandDiscItem.validStandDisc(itemStack, true) ? ModStandActions.STANDS.getNumericId(StandDiscItem.getStandFromStack(itemStack, true).getType().getRegistryName()) : -1;
            });
//            ItemModelsProperties.register(ModItems.CASSETTE_RECORDED.get(), new ResourceLocation(JojoMod.MOD_ID, "cassette_distortion"), (itemStack, clientWorld, livingEntity) -> {
//                return itemStack.getCapability(CassetteCapProvider.CAPABILITY)
//                        .map(cap -> MathHelper.clamp(cap.getGeneration(), 0, CassetteCap.MAX_GENERATION))
//                        .orElse(0).floatValue();
//            });
//            ItemModelsProperties.register(ModItems.EMPEROR.get(), new ResourceLocation(JojoMod.MOD_ID, "stand_invisible"), STAND_ITEM_INVISIBLE);
//            ItemModelsProperties.register(ModItems.BEACH_BOY.get(), new ResourceLocation(JojoMod.MOD_ID, "stand_invisible"), STAND_ITEM_INVISIBLE);
//            ItemModelsProperties.register(ModItems.BEACH_BOY.get(), new ResourceLocation("cast"), (p_239422_0_, p_239422_1_, p_239422_2_) -> {
//                if (p_239422_2_ == null) {
//                   return 0.0F;
//                } else {
//                   boolean flag = p_239422_2_.getMainHandItem() == p_239422_0_;
//                   boolean flag1 = p_239422_2_.getOffhandItem() == p_239422_0_;
//                   if (p_239422_2_.getMainHandItem().getItem() instanceof FishingRodItem) {
//                      flag1 = false;
//                   }
//
//                   return (flag || flag1) && p_239422_2_ instanceof PlayerEntity && ((PlayerEntity)p_239422_2_).fishing != null ? 1.0F : 0.0F;
//                }
//             });

            RenderTypeLookup.setRenderLayer(ModBlocks.STONE_MASK.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.SLUMBERING_PILLARMAN.get(), RenderType.cutoutMipped());
            
            ScreenManager.register(ModContainers.WALKMAN.get(), WalkmanScreen::new);

            ClientEventHandler.init(mc);
            ActionsOverlayGui.init(mc);
            StandController.init(mc);
            SoulController.init(mc);
            InputHandler.init(mc);
            InputHandler.getInstance().setActionsOverlay(ActionsOverlayGui.getInstance());
            
            Map<String, PlayerRenderer> skinMap = mc.getEntityRenderDispatcher().getSkinMap();
            addLayers(skinMap.get("default"));
            addLayers(skinMap.get("slim"));
            mc.getEntityRenderDispatcher().renderers.forEach((entityType, renderer) -> addLayersIfBiped(renderer));

            MarkerRenderer.Handler.addRenderer(new HierophantGreenBarrierDetectionMarker(mc));
            MarkerRenderer.Handler.addRenderer(new CrazyDiamondAnchorMarker(mc));
            MarkerRenderer.Handler.addRenderer(new CrazyDiamondBloodHomingMarker(mc));
            
            ModdedPlayerRenderer.addModdedPlayerRenderer(slim -> new ModdedRotationsPlayerRenderer(mc.getEntityRenderDispatcher(), slim));
            ModdedPlayerRenderer.addModdedPlayerRenderer(slim -> new ModdedJointsPlayerRenderer(mc.getEntityRenderDispatcher(), slim));
        });
    }
    
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void loadCustomArmorModels(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ArmorModelRegistry.loadArmorModels();
        });
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void finalizeCustomPlayerRenderers(FMLClientSetupEvent event) {
        Map<String, PlayerRenderer> skinMap = event.getMinecraftSupplier().get().getEntityRenderDispatcher().getSkinMap();
        ModdedPlayerRenderer.addModdedLayers(skinMap.get("default"), skinMap.get("slim"));
    }

    private static void addLayers(PlayerRenderer renderer) {
        renderer.addLayer(new KnifeLayer<>(renderer));
        renderer.addLayer(new TornadoOverdriveEffectLayer<>(renderer));
        addBipedLayers(renderer);
    }
    
    private static <T extends LivingEntity, M extends BipedModel<T>> void addLayersIfBiped(EntityRenderer<?> renderer) {
        if (renderer instanceof LivingRenderer<?, ?> && ((LivingRenderer<?, ?>) renderer).getModel() instanceof BipedModel<?>) {
            addBipedLayers((LivingRenderer<T, M>) renderer);
        }
    }
    
    private static <T extends LivingEntity, M extends BipedModel<T>> void addBipedLayers(LivingRenderer<T, M> renderer) {
//        renderer.addLayer(new HamonBurnLayer<>(renderer));
    }

    @SubscribeEvent
    public static void registerItemColoring(ColorHandlerEvent.Item event) {
        ItemColors itemColors = event.getItemColors();
        
        itemColors.register((stack, layer) -> {
            if (layer != 1) return -1;
            
            return ClientUtil.discColor(StandDiscItem.getColor(stack));
        }, ModItems.STAND_DISC.get());
        
//        itemColors.register((stack, layer) -> {
//            if (layer != 1) return -1;
//
//            Optional<DyeColor> dye = stack.getCapability(CassetteCapProvider.CAPABILITY).map(cap -> cap.getDye()).orElse(Optional.empty());
//            return dye.isPresent() ? dye.get().getColorValue() : 0xeff0e0;
//        }, ModItems.CASSETTE_RECORDED.get());
    }
    
    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event) {
        registerCustomBakedModel(ModItems.ROAD_ROLLER.get().getRegistryName(), event.getModelRegistry(), 
                model -> new RoadRollerBakedModel(model));
    }
    
    private static void registerCustomBakedModel(ResourceLocation resLoc, 
            Map<ResourceLocation, IBakedModel> modelRegistry, UnaryOperator<IBakedModel> newModel) {
        ModelResourceLocation modelResLoc = new ModelResourceLocation(resLoc, "inventory");
        IBakedModel existingModel = modelRegistry.get(modelResLoc);
        if (existingModel == null) {
            throw new RuntimeException("Did not find original model in registry");
        }
        else if (existingModel.isCustomRenderer()) {
            throw new RuntimeException("Tried to replace model twice");
        }
        else {
            modelRegistry.put(modelResLoc, newModel.apply(existingModel));
        }
    }

    @SubscribeEvent
    public static void onMcConstructor(ParticleFactoryRegisterEvent event) {
        Minecraft mc = Minecraft.getInstance();
        mc.particleEngine.register(ModParticles.BLOOD.get(),                BloodParticle.Factory::new);
        mc.particleEngine.register(ModParticles.HAMON_SPARK.get(),          HamonSparkParticle.HamonParticleFactory::new);
        mc.particleEngine.register(ModParticles.HAMON_SPARK_BLUE.get(),     HamonSparkParticle.HamonParticleFactory::new);
        mc.particleEngine.register(ModParticles.HAMON_SPARK_YELLOW.get(),   HamonSparkParticle.HamonParticleFactory::new);
        mc.particleEngine.register(ModParticles.HAMON_SPARK_RED.get(),      HamonSparkParticle.HamonParticleFactory::new);
        mc.particleEngine.register(ModParticles.HAMON_SPARK_SILVER.get(),   HamonSparkParticle.HamonParticleFactory::new);
        mc.particleEngine.register(ModParticles.HAMON_AURA.get(),           HamonAuraParticle.Factory::new);
        mc.particleEngine.register(ModParticles.METEORITE_VIRUS.get(),      MeteoriteVirusParticle.Factory::new);
        mc.particleEngine.register(ModParticles.MENACING.get(),             OnomatopoeiaParticle.GoFactory::new);
        mc.particleEngine.register(ModParticles.RESOLVE.get(),              OnomatopoeiaParticle.DoFactory::new);
        mc.particleEngine.register(ModParticles.SOUL_CLOUD.get(),           SoulCloudParticleFactory::new);
        mc.particleEngine.register(ModParticles.AIR_STREAM.get(),           AirStreamParticle.Factory::new);
        mc.particleEngine.register(ModParticles.FLAME_ONE_TICK.get(),       OneTickFlameParticle.Factory::new);
        mc.particleEngine.register(ModParticles.CD_RESTORATION.get(),       CDRestorationParticle.Factory::new);
        // yep...
        CustomResources.initCustomResourceManagers(mc);
    }

    private static class SoulCloudParticleFactory extends CloudParticle.Factory {

        public SoulCloudParticleFactory(IAnimatedSprite sprite) {
            super(sprite);
        }

        @Override
        public Particle createParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
           Particle particle = super.createParticle(type, world, x, y, z, xSpeed, ySpeed, zSpeed);
           particle.setColor(1.0F, 1.0F, 0.25F);
           return particle;
        }
    }
}
