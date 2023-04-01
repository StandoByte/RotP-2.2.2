package com.github.standobyte.jojo.init.power.stand;

import static com.github.standobyte.jojo.init.ModEntityTypes.ENTITIES;
import static com.github.standobyte.jojo.init.power.ModCommonRegistries.ACTIONS;

import com.github.standobyte.jojo.JojoMod;
import com.github.standobyte.jojo.action.stand.CrazyDiamondBlockBullet;
import com.github.standobyte.jojo.action.stand.CrazyDiamondBlockCheckpointMake;
import com.github.standobyte.jojo.action.stand.CrazyDiamondBlockCheckpointMove;
import com.github.standobyte.jojo.action.stand.CrazyDiamondBloodCutter;
import com.github.standobyte.jojo.action.stand.CrazyDiamondHeal;
import com.github.standobyte.jojo.action.stand.CrazyDiamondHeavyPunch;
import com.github.standobyte.jojo.action.stand.CrazyDiamondLeaveObject;
import com.github.standobyte.jojo.action.stand.CrazyDiamondMisshapeBodyPart;
import com.github.standobyte.jojo.action.stand.CrazyDiamondMisshapingPunch;
import com.github.standobyte.jojo.action.stand.CrazyDiamondPreviousState;
import com.github.standobyte.jojo.action.stand.CrazyDiamondRepairItem;
import com.github.standobyte.jojo.action.stand.CrazyDiamondRestoreTerrain;
import com.github.standobyte.jojo.action.stand.HierophantGreenBarrier;
import com.github.standobyte.jojo.action.stand.HierophantGreenEmeraldSplash;
import com.github.standobyte.jojo.action.stand.HierophantGreenGrapple;
import com.github.standobyte.jojo.action.stand.HierophantGreenStringAttack;
import com.github.standobyte.jojo.action.stand.MagiciansRedCrossfireHurricane;
import com.github.standobyte.jojo.action.stand.MagiciansRedDetector;
import com.github.standobyte.jojo.action.stand.MagiciansRedFireball;
import com.github.standobyte.jojo.action.stand.MagiciansRedFlameBurst;
import com.github.standobyte.jojo.action.stand.MagiciansRedKick;
import com.github.standobyte.jojo.action.stand.MagiciansRedRedBind;
import com.github.standobyte.jojo.action.stand.SilverChariotDashAttack;
import com.github.standobyte.jojo.action.stand.SilverChariotLightAttack;
import com.github.standobyte.jojo.action.stand.SilverChariotMeleeBarrage;
import com.github.standobyte.jojo.action.stand.SilverChariotRapierLaunch;
import com.github.standobyte.jojo.action.stand.SilverChariotSweepingAttack;
import com.github.standobyte.jojo.action.stand.SilverChariotTakeOffArmor;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.action.stand.StandEntityAction.AutoSummonMode;
import com.github.standobyte.jojo.action.stand.StandEntityAction.Phase;
import com.github.standobyte.jojo.action.stand.StandEntityActionModifier;
import com.github.standobyte.jojo.action.stand.StandEntityBlock;
import com.github.standobyte.jojo.action.stand.StandEntityHeavyAttack;
import com.github.standobyte.jojo.action.stand.StandEntityLightAttack;
import com.github.standobyte.jojo.action.stand.StandEntityMeleeBarrage;
import com.github.standobyte.jojo.action.stand.StandEntityUnsummon;
import com.github.standobyte.jojo.action.stand.StarPlatinumInhale;
import com.github.standobyte.jojo.action.stand.StarPlatinumStarFinger;
import com.github.standobyte.jojo.action.stand.StarPlatinumUppercut;
import com.github.standobyte.jojo.action.stand.StarPlatinumZoom;
import com.github.standobyte.jojo.action.stand.TheWorldBarrage;
import com.github.standobyte.jojo.action.stand.TheWorldHeavyPunch;
import com.github.standobyte.jojo.action.stand.TheWorldKick;
import com.github.standobyte.jojo.action.stand.TheWorldTSHeavyAttack;
import com.github.standobyte.jojo.action.stand.TheWorldTimeStop;
import com.github.standobyte.jojo.action.stand.TheWorldTimeStopInstant;
import com.github.standobyte.jojo.action.stand.TimeResume;
import com.github.standobyte.jojo.action.stand.TimeStop;
import com.github.standobyte.jojo.action.stand.TimeStopInstant;
import com.github.standobyte.jojo.action.stand.qwe.CMoonPunch;
import com.github.standobyte.jojo.action.stand.qwe.CMoonRepel;
import com.github.standobyte.jojo.action.stand.qwe.D4CSummonClone;
import com.github.standobyte.jojo.action.stand.qwe.EchoesThreeFreeze;
import com.github.standobyte.jojo.action.stand.qwe.GoldExperienceCreateLife;
import com.github.standobyte.jojo.action.stand.qwe.HeavensDoorSafetyLock;
import com.github.standobyte.jojo.action.stand.qwe.JumpinJackFlashShootScrap;
import com.github.standobyte.jojo.action.stand.qwe.JumpinJackFlashSpit;
import com.github.standobyte.jojo.action.stand.qwe.KillerQueenDetonate;
import com.github.standobyte.jojo.action.stand.qwe.KillerQueenFirstBomb;
import com.github.standobyte.jojo.action.stand.qwe.KingCrimsonTimeErase;
import com.github.standobyte.jojo.action.stand.qwe.KissDupe;
import com.github.standobyte.jojo.action.stand.qwe.LimBizkitSummonZombie;
import com.github.standobyte.jojo.action.stand.qwe.MetallicaInvisibility;
import com.github.standobyte.jojo.action.stand.qwe.MetallicaKnives;
import com.github.standobyte.jojo.action.stand.qwe.SoftAndWetBubbles;
import com.github.standobyte.jojo.action.stand.qwe.StickyFingersTp;
import com.github.standobyte.jojo.action.stand.qwe.StoneFreeGrapple;
import com.github.standobyte.jojo.action.stand.qwe.TheHandSwipe;
import com.github.standobyte.jojo.action.stand.qwe.TuskAct4Bullet;
import com.github.standobyte.jojo.action.stand.qwe.WeatherReportLightning;
import com.github.standobyte.jojo.action.stand.qwe.WhitesnakeRemoveStandDisc;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.entity.stand.StandPose;
import com.github.standobyte.jojo.entity.stand.StandRelativeOffset;
import com.github.standobyte.jojo.entity.stand.stands.CMoonEntity;
import com.github.standobyte.jojo.entity.stand.stands.ChariotRequiemEntity;
import com.github.standobyte.jojo.entity.stand.stands.CrazyDiamondEntity;
import com.github.standobyte.jojo.entity.stand.stands.DarkBlueMoonEntity;
import com.github.standobyte.jojo.entity.stand.stands.DiverDownEntity;
import com.github.standobyte.jojo.entity.stand.stands.GoldExperienceRequiemEntity;
import com.github.standobyte.jojo.entity.stand.stands.GreenDayEntity;
import com.github.standobyte.jojo.entity.stand.stands.HierophantGreenEntity;
import com.github.standobyte.jojo.entity.stand.stands.KraftWorkEntity;
import com.github.standobyte.jojo.entity.stand.stands.MadeInHeavenEntity;
import com.github.standobyte.jojo.entity.stand.stands.MagiciansRedEntity;
import com.github.standobyte.jojo.entity.stand.stands.SilverChariotEntity;
import com.github.standobyte.jojo.entity.stand.stands.StarPlatinumEntity;
import com.github.standobyte.jojo.entity.stand.stands.TheWorldEntity;
import com.github.standobyte.jojo.entity.stand.stands.WonderOfUEntity;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.init.power.CustomRegistryHolder;
import com.github.standobyte.jojo.power.stand.IStandPower;
import com.github.standobyte.jojo.power.stand.StandInstance.StandPart;
import com.github.standobyte.jojo.power.stand.stats.StandStats;
import com.github.standobyte.jojo.power.stand.stats.TimeStopperStandStats;
import com.github.standobyte.jojo.power.stand.type.EntityStandType;
import com.github.standobyte.jojo.power.stand.type.NoManifestationStandType;
import com.github.standobyte.jojo.power.stand.type.StandType;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

/**
 * Jump to the start of a Stand's intialization:
 *             {@link ModStandActions#STAR_PLATINUM_PUNCH}  Star Platinum
 *                 {@link ModStandActions#THE_WORLD_PUNCH}  The World
 *  {@link ModStandActions#HIEROPHANT_GREEN_STRING_ATTACK}  Hierophant Green
 * {@link ModStandActions#SILVER_CHARIOT_NO_RAPIER_ATTACK}  Silver Chariot
 *             {@link ModStandActions#MAGICIANS_RED_PUNCH}  Magician's Red
 *             {@link ModStandActions#CRAZY_DIAMOND_PUNCH}  Crazy Diamond
 *
 */
public class ModStandActions {
    private static final ITextComponent PART_3_NAME = new TranslationTextComponent("jojo.story_part.3").withStyle(TextFormatting.DARK_PURPLE);
    private static final ITextComponent PART_4_NAME = new TranslationTextComponent("jojo.story_part.4").withStyle(TextFormatting.RED);
    private static final ITextComponent PART_5_NAME = new TranslationTextComponent("jojo.story_part.5").withStyle(TextFormatting.GOLD);
    private static final ITextComponent PART_6_NAME = new TranslationTextComponent("jojo.story_part.6").withStyle(TextFormatting.AQUA);
    
    private static final ITextComponent PART_7_NAME = new TranslationTextComponent("jojo.story_part.7").withStyle(TextFormatting.LIGHT_PURPLE);
    private static final ITextComponent PART_8_NAME = new TranslationTextComponent("jojo.story_part.8").withStyle(TextFormatting.WHITE);
    private static final ITextComponent PART_9_NAME = new TranslationTextComponent("jojo.story_part.9").withStyle(TextFormatting.BLUE);
    
    public static final CustomRegistryHolder<StandType<?>> STANDS = new CustomRegistryHolder<>(
            DeferredRegister.create((Class<StandType<?>>) ((Class<?>) StandType.class), JojoMod.MOD_ID), "stand_type");
    
    public static final RegistryObject<StandEntityAction> UNSUMMON_STAND_ENTITY = ACTIONS.registerEntry("stand_entity_unsummon", 
            () -> new StandEntityUnsummon());

    public static final RegistryObject<StandEntityAction> BLOCK_STAND_ENTITY = ACTIONS.registerEntry("stand_entity_block", 
            () -> new StandEntityBlock() {
                @Override
                public StandRelativeOffset getOffsetFromUser(IStandPower standPower, StandEntity standEntity, StandEntityTask task) {
                    return null;
                }
            });
    
    
    
// ======================================== Star Platinum ========================================
    
    public static final RegistryObject<StandEntityAction> STAR_PLATINUM_PUNCH = ACTIONS.registerEntry("star_platinum_punch", 
            () -> new StandEntityLightAttack(new StandEntityLightAttack.Builder()
                    .punchSound(ModSounds.STAR_PLATINUM_PUNCH_LIGHT)
                    .standSound(Phase.WINDUP, ModSounds.STAR_PLATINUM_ORA)));
    
    public static final RegistryObject<StandEntityAction> STAR_PLATINUM_BARRAGE = ACTIONS.registerEntry("star_platinum_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder()
                    .barrageHitSound(ModSounds.STAR_PLATINUM_PUNCH_BARRAGE)
                    .standSound(ModSounds.STAR_PLATINUM_ORA_ORA_ORA)));
    
    public static final RegistryObject<StandEntityHeavyAttack> STAR_PLATINUM_UPPERCUT = ACTIONS.registerEntry("star_platinum_uppercut", 
            () -> new StarPlatinumUppercut(new StandEntityHeavyAttack.Builder()
                    .punchSound(ModSounds.STAR_PLATINUM_PUNCH_HEAVY)
                    .standSound(Phase.WINDUP, ModSounds.STAR_PLATINUM_ORA_LONG)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> STAR_PLATINUM_HEAVY_PUNCH = ACTIONS.registerEntry("star_platinum_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder()
                    .punchSound(ModSounds.STAR_PLATINUM_PUNCH_HEAVY)
                    .standSound(Phase.WINDUP, ModSounds.STAR_PLATINUM_ORA_LONG)
                    .partsRequired(StandPart.ARMS)
                    .setFinisherVariation(STAR_PLATINUM_UPPERCUT)
                    .shiftVariationOf(STAR_PLATINUM_PUNCH).shiftVariationOf(STAR_PLATINUM_BARRAGE)));
    
    public static final RegistryObject<StandEntityAction> STAR_PLATINUM_STAR_FINGER = ACTIONS.registerEntry("star_platinum_star_finger", 
            () -> new StarPlatinumStarFinger(new StandEntityAction.Builder().staminaCost(375).standPerformDuration(20).cooldown(20, 60)
                    .ignoresPerformerStun()//.resolveLevelToUnlock(3)
                    .standOffsetFront().standPose(StandPose.RANGED_ATTACK)
                    .shout(ModSounds.JOTARO_STAR_FINGER).standSound(Phase.PERFORM, ModSounds.STAR_PLATINUM_STAR_FINGER)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> STAR_PLATINUM_BLOCK = ACTIONS.registerEntry("star_platinum_block", 
            () -> new StandEntityBlock());
    
    public static final RegistryObject<StandEntityAction> STAR_PLATINUM_ZOOM = ACTIONS.registerEntry("star_platinum_zoom", 
            () -> new StarPlatinumZoom(new StandEntityAction.Builder()
                    .ignoresPerformerStun()
                    .standOffsetFromUser(-0.25, -0.25, -0.3)
                    .partsRequired(StandPart.MAIN_BODY)));
    
    public static final RegistryObject<StandEntityAction> STAR_PLATINUM_INHALE = ACTIONS.registerEntry("star_platinum_inhale", 
            () -> new StarPlatinumInhale(new StandEntityAction.Builder().holdType(80).staminaCostTick(2F).cooldown(200)
                    .ignoresPerformerStun()//.resolveLevelToUnlock(2)
                    .standOffsetFromUser(0, -0.25).standSound(ModSounds.STAR_PLATINUM_INHALE)
                    .partsRequired(StandPart.MAIN_BODY)));
    
    public static final RegistryObject<TimeStop> STAR_PLATINUM_TIME_STOP = ACTIONS.registerEntry("star_platinum_time_stop", 
            () -> new TimeStop(new StandAction.Builder()/*.holdToFire(40, false)*/.staminaCost(250).staminaCostTick(7.5F)
//                    .isTrained().resolveLevelToUnlock(4)
                    .ignoresPerformerStun().autoSummonStand()
                    .shout(ModSounds.JOTARO_STAR_PLATINUM_THE_WORLD)
                    .partsRequired(StandPart.MAIN_BODY))
            .timeStopSound(ModSounds.STAR_PLATINUM_TIME_STOP)
            .addTimeResumeVoiceLine(ModSounds.JOTARO_TIME_RESUMES).timeResumeSound(ModSounds.STAR_PLATINUM_TIME_RESUME));
    
//    public static final RegistryObject<TimeResume> TIME_RESUME = ACTIONS.registerEntry("time_resume", 
//            () -> new TimeResume(new StandAction.Builder()));
    
    public static final RegistryObject<StandAction> STAR_PLATINUM_TIME_STOP_BLINK = ACTIONS.registerEntry("star_platinum_ts_blink", 
            () -> new TimeStopInstant(new StandAction.Builder()
//                    .resolveLevelToUnlock(4).isTrained()
                    .ignoresPerformerStun()
                    .partsRequired(StandPart.MAIN_BODY)
                    /*.shiftVariationOf(STAR_PLATINUM_TIME_STOP)*/, 
                    STAR_PLATINUM_TIME_STOP, ModSounds.STAR_PLATINUM_TIME_STOP_BLINK));
    
    
    public static final EntityStandRegistryObject<EntityStandType<TimeStopperStandStats>, StandEntityType<StarPlatinumEntity>> STAND_STAR_PLATINUM = 
            new EntityStandRegistryObject<>("star_platinum", 
                    STANDS, 
                    () -> new EntityStandType<>(
                            0x8E45FF, PART_3_NAME,

                            new StandAction[] {
//                                    STAR_PLATINUM_PUNCH.get(), 
                                    STAR_PLATINUM_BARRAGE.get(), 
//                                    STAR_PLATINUM_STAR_FINGER.get()
                                    },
                            new StandAction[] {
//                                    STAR_PLATINUM_BLOCK.get(), 
//                                    STAR_PLATINUM_ZOOM.get(), 
//                                    STAR_PLATINUM_INHALE.get(), 
                                    STAR_PLATINUM_TIME_STOP.get()},

                            TimeStopperStandStats.class, new TimeStopperStandStats.Builder()
                            .tier(6)
                            .power(16.0)
                            .speed(16.0)
                            .range(2.0, 10.0)
                            .durability(16.0)
                            .precision(20.0)
                            .timeStopMaxTicks(100, 180)
                            .timeStopLearningPerTick(0.25F)
                            .timeStopDecayPerDay(0F)
                            .timeStopCooldownPerTick(3F)
                            .build("Star Platinum"), 

                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.JOTARO_STAR_PLATINUM)
                            .addOst(ModSounds.STAR_PLATINUM_OST)), 

                    ENTITIES, 
                    () -> new StandEntityType<StarPlatinumEntity>(StarPlatinumEntity::new, 0.7F, 2.1F)
                    .summonSound(ModSounds.STAR_PLATINUM_SUMMON)
                    .unsummonSound(ModSounds.STAR_PLATINUM_UNSUMMON))
            .withDefaultStandAttributes();
    
    
    
// ======================================== The World ========================================
    
    public static final RegistryObject<StandEntityAction> THE_WORLD_PUNCH = ACTIONS.registerEntry("the_world_punch", 
            () -> new StandEntityLightAttack(new StandEntityLightAttack.Builder()
                    .punchSound(ModSounds.THE_WORLD_PUNCH_LIGHT)
                    .standSound(Phase.WINDUP, ModSounds.DIO_MUDA)));
    
    public static final RegistryObject<StandEntityAction> THE_WORLD_BARRAGE = ACTIONS.registerEntry("the_world_barrage", 
            () -> new TheWorldBarrage(new StandEntityMeleeBarrage.Builder()
                    .barrageHitSound(ModSounds.THE_WORLD_PUNCH_BARRAGE)
                    .standSound(ModSounds.THE_WORLD_MUDA_MUDA_MUDA), ModSounds.DIO_WRY));

    public static final RegistryObject<StandEntityHeavyAttack> THE_WORLD_KICK = ACTIONS.registerEntry("the_world_kick", 
            () -> new TheWorldKick(new StandEntityHeavyAttack.Builder()
                    .punchSound(ModSounds.THE_WORLD_KICK_HEAVY)
                    .shout(ModSounds.DIO_DIE)
                    .partsRequired(StandPart.LEGS)));

    public static final RegistryObject<StandEntityHeavyAttack> THE_WORLD_HEAVY_PUNCH = ACTIONS.registerEntry("the_world_heavy_punch", 
            () -> new TheWorldHeavyPunch(new StandEntityHeavyAttack.Builder()
                    .punchSound(ModSounds.THE_WORLD_PUNCH_HEAVY)
                    .shout(ModSounds.DIO_DIE)
                    .partsRequired(StandPart.ARMS)
                    .setFinisherVariation(THE_WORLD_KICK)
                    .shiftVariationOf(THE_WORLD_PUNCH).shiftVariationOf(THE_WORLD_BARRAGE)));

    public static final RegistryObject<StandEntityAction> THE_WORLD_BLOCK = ACTIONS.registerEntry("the_world_block", 
            () -> new StandEntityBlock());
    
    public static final RegistryObject<TimeStop> THE_WORLD_TIME_STOP = ACTIONS.registerEntry("the_world_time_stop", 
            () -> new TheWorldTimeStop(new StandAction.Builder()/*.holdToFire(30, false)*/.staminaCost(250).staminaCostTick(7.5F)
//                    .resolveLevelToUnlock(2).isTrained()
                    .ignoresPerformerStun()
                    .shout(ModSounds.DIO_THE_WORLD)
                    .partsRequired(StandPart.MAIN_BODY))
            .voiceLineWithStandSummoned(ModSounds.DIO_TIME_STOP).timeStopSound(ModSounds.THE_WORLD_TIME_STOP)
            .addTimeResumeVoiceLine(ModSounds.DIO_TIME_RESUMES, true).addTimeResumeVoiceLine(ModSounds.DIO_TIMES_UP, false)
            .timeResumeSound(ModSounds.THE_WORLD_TIME_RESUME));
    
    public static final RegistryObject<TimeStopInstant> THE_WORLD_TIME_STOP_BLINK = ACTIONS.registerEntry("the_world_ts_blink", 
            () -> new TheWorldTimeStopInstant(new StandAction.Builder()
//                    .resolveLevelToUnlock(2).isTrained()
                    .ignoresPerformerStun()
                    .partsRequired(StandPart.MAIN_BODY)
                    /*.shiftVariationOf(THE_WORLD_TIME_STOP)*/, 
                    THE_WORLD_TIME_STOP, ModSounds.THE_WORLD_TIME_STOP_BLINK));
    
    public static final RegistryObject<StandEntityAction> THE_WORLD_TS_PUNCH = ACTIONS.registerEntry("the_world_ts_punch", 
            () -> new TheWorldTSHeavyAttack(new StandEntityAction.Builder()/*.resolveLevelToUnlock(3)*/.standUserSlowDownFactor(1.0F)
                    .standPose(TheWorldTSHeavyAttack.TS_PUNCH_POSE).standWindupDuration(5).cooldown(50)
                    .partsRequired(StandPart.MAIN_BODY, StandPart.ARMS), THE_WORLD_HEAVY_PUNCH, THE_WORLD_TIME_STOP_BLINK));
    
    
    public static final EntityStandRegistryObject<EntityStandType<TimeStopperStandStats>, StandEntityType<TheWorldEntity>> STAND_THE_WORLD = 
            new EntityStandRegistryObject<>("the_world", 
                    STANDS,
                    () -> new EntityStandType<>(
                            0xFFD800, PART_3_NAME,

                            new StandAction[] {
//                                    THE_WORLD_PUNCH.get(), 
                                    THE_WORLD_BARRAGE.get(), 
//                                    THE_WORLD_TS_PUNCH.get()
                                    },
                            new StandAction[] {
//                                    THE_WORLD_BLOCK.get(), 
                                    THE_WORLD_TIME_STOP.get()
                                    },

                            TimeStopperStandStats.class, new TimeStopperStandStats.Builder()
                            .tier(6)
                            .power(16.0)
                            .speed(16.0)
                            .range(2.0, 10.0)
                            .durability(16.0)
                            .precision(12.0)
                            .timeStopMaxTicks(100, 180)
                            .timeStopLearningPerTick(0.1F)
                            .timeStopDecayPerDay(0F)
                            .timeStopCooldownPerTick(3F)
                            .build("The World"), 

                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.DIO_THE_WORLD)
                            .addOst(ModSounds.THE_WORLD_OST)
                            /*.addItemOnResolveLevel(4, new ItemStack(ModItems.ROAD_ROLLER.get()))*/), 

                    ENTITIES, 
                    () -> new StandEntityType<TheWorldEntity>(TheWorldEntity::new, 0.7F, 2.1F)
                    .summonSound(ModSounds.THE_WORLD_SUMMON))
            .withDefaultStandAttributes();
    
    
    
// ======================================== Hierophant Green ========================================
    
    public static final RegistryObject<StandEntityAction> HIEROPHANT_GREEN_STRING_ATTACK = ACTIONS.registerEntry("hierophant_green_attack", 
            () -> new HierophantGreenStringAttack(new StandEntityAction.Builder().staminaCost(75).standPerformDuration(16)
                    .standSound(ModSounds.HIEROPHANT_GREEN_TENTACLES)
                    .partsRequired(StandPart.MAIN_BODY)));
    
    public static final RegistryObject<StandEntityAction> HIEROPHANT_GREEN_STRING_BIND = ACTIONS.registerEntry("hierophant_green_attack_binding", 
            () -> new HierophantGreenStringAttack(new StandEntityAction.Builder().staminaCost(75).standPerformDuration(24).cooldown(25, 100, 0.5F)
                    .standSound(ModSounds.HIEROPHANT_GREEN_TENTACLES)
                    .partsRequired(StandPart.MAIN_BODY)
                    .shiftVariationOf(HIEROPHANT_GREEN_STRING_ATTACK)));
    
    public static final RegistryObject<StandEntityAction> HIEROPHANT_GREEN_EMERALD_SPLASH = ACTIONS.registerEntry("hierophant_green_emerald_splash", 
            () -> new HierophantGreenEmeraldSplash(new StandEntityAction.Builder().standPerformDuration(30).standRecoveryTicks(20).staminaCostTick(3)
//                    .resolveLevelToUnlock(1).isTrained()
                    .standOffsetFront().standPose(StandPose.RANGED_ATTACK).shout(ModSounds.KAKYOIN_EMERALD_SPLASH).standSound(ModSounds.HIEROPHANT_GREEN_EMERALD_SPLASH)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> HIEROPHANT_GREEN_EMERALD_SPLASH_CONCENTRATED = ACTIONS.registerEntry("hierophant_green_es_concentrated", 
            () -> new HierophantGreenEmeraldSplash(new StandEntityAction.Builder().staminaCostTick(6).standPerformDuration(5).standRecoveryTicks(20).cooldown(5, 60)
//                    .resolveLevelToUnlock(-1)
                    .standOffsetFront().standPose(StandPose.RANGED_ATTACK).shout(ModSounds.KAKYOIN_EMERALD_SPLASH).standSound(ModSounds.HIEROPHANT_GREEN_EMERALD_SPLASH)
                    .partsRequired(StandPart.ARMS)
                    .shiftVariationOf(HIEROPHANT_GREEN_EMERALD_SPLASH)));
    
    public static final RegistryObject<StandEntityAction> HIEROPHANT_GREEN_BLOCK = ACTIONS.registerEntry("hierophant_green_block", 
            () -> new StandEntityBlock());
    
    public static final RegistryObject<StandEntityAction> HIEROPHANT_GREEN_GRAPPLE = ACTIONS.registerEntry("hierophant_green_grapple", 
            () -> new HierophantGreenGrapple(new StandEntityAction.Builder().staminaCostTick(1).holdType().standUserSlowDownFactor(1.0F)
//                    .resolveLevelToUnlock(2)
                    .standPose(HierophantGreenGrapple.GRAPPLE_POSE).standOffsetFromUser(-0.5, 0.25)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> HIEROPHANT_GREEN_GRAPPLE_ENTITY = ACTIONS.registerEntry("hierophant_green_grapple_entity", 
            () -> new HierophantGreenGrapple(new StandEntityAction.Builder().staminaCostTick(1).holdType().standUserSlowDownFactor(1.0F)
//                    .resolveLevelToUnlock(2)
                    .standPose(HierophantGreenGrapple.GRAPPLE_POSE)
                    .partsRequired(StandPart.ARMS)
                    .shiftVariationOf(HIEROPHANT_GREEN_GRAPPLE)));
    
    public static final RegistryObject<StandEntityAction> HIEROPHANT_GREEN_BARRIER = ACTIONS.registerEntry("hierophant_green_barrier", 
            () -> new HierophantGreenBarrier(new StandEntityAction.Builder()
//                    .resolveLevelToUnlock(3)
                    .partsRequired(StandPart.MAIN_BODY)));
    
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<HierophantGreenEntity>> STAND_HIEROPHANT_GREEN = 
            new EntityStandRegistryObject<>("hierophant_green", 
                    STANDS, 
                    () -> new EntityStandType<>(
                            0x00B319, PART_3_NAME,

                            new StandAction[] {
//                                    HIEROPHANT_GREEN_STRING_ATTACK.get(), 
                                    HIEROPHANT_GREEN_EMERALD_SPLASH.get()
                                    },
                            new StandAction[] {
//                                    HIEROPHANT_GREEN_BLOCK.get(), 
//                                    HIEROPHANT_GREEN_GRAPPLE.get(), 
                                    HIEROPHANT_GREEN_BARRIER.get()
                                    },

                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(8.0)
                            .speed(12.0)
                            .range(50.0, 100.0)
                            .durability(10.0)
                            .precision(8.0)
                            .build("Hierophant Green"), 

                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.KAKYOIN_HIEROPHANT_GREEN)
                            .addOst(ModSounds.HIEROPHANT_GREEN_OST)), 

                    ENTITIES, 
                    () -> new StandEntityType<HierophantGreenEntity>(HierophantGreenEntity::new, 0.6F, 1.9F)
                    .summonSound(ModSounds.HIEROPHANT_GREEN_SUMMON))
            .withDefaultStandAttributes();
    
    
    
// ======================================== Silver Chariot ========================================
    
    public static final RegistryObject<StandEntityLightAttack> SILVER_CHARIOT_NO_RAPIER_ATTACK = ACTIONS.registerEntry("silver_chariot_no_rapier_attack", 
            () -> new StandEntityLightAttack(new StandEntityLightAttack.Builder()));
    
    public static final RegistryObject<StandEntityAction> SILVER_CHARIOT_ATTACK = ACTIONS.registerEntry("silver_chariot_attack", 
            () -> new SilverChariotLightAttack(new StandEntityLightAttack.Builder()
                    .punchSound(() -> null).standSound(ModSounds.SILVER_CHARIOT_SWEEP_LIGHT), 
                    SILVER_CHARIOT_NO_RAPIER_ATTACK));
    
    public static final RegistryObject<StandEntityAction> SILVER_CHARIOT_RAPIER_BARRAGE = ACTIONS.registerEntry("silver_chariot_barrage", 
            () -> new SilverChariotMeleeBarrage(new StandEntityMeleeBarrage.Builder()
                    .shout(ModSounds.POLNAREFF_HORA_HORA_HORA).barrageHitSound(ModSounds.SILVER_CHARIOT_BARRAGE)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityHeavyAttack> SILVER_CHARIOT_SWEEPING_ATTACK = ACTIONS.registerEntry("silver_chariot_sweeping_attack", 
            () -> new SilverChariotSweepingAttack(new StandEntityHeavyAttack.Builder().standPerformDuration(3)
                    .punchSound(() -> null)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> SILVER_CHARIOT_DASH_ATTACK = ACTIONS.registerEntry("silver_chariot_dash_attack", 
            () -> new SilverChariotDashAttack(new StandEntityHeavyAttack.Builder()
                    .punchSound(() -> null).standSound(ModSounds.SILVER_CHARIOT_DASH)
                    .partsRequired(StandPart.MAIN_BODY, StandPart.ARMS)
                    .setFinisherVariation(SILVER_CHARIOT_SWEEPING_ATTACK)
                    .shiftVariationOf(SILVER_CHARIOT_ATTACK).shiftVariationOf(SILVER_CHARIOT_RAPIER_BARRAGE)));
    
    public static final RegistryObject<StandEntityAction> SILVER_CHARIOT_RAPIER_LAUNCH = ACTIONS.registerEntry("silver_chariot_rapier_launch", 
            () -> new SilverChariotRapierLaunch(new StandEntityAction.Builder().cooldown(100)
                    .ignoresPerformerStun()
//                    .resolveLevelToUnlock(2)
                    .standOffsetFromUser(0, 0.25).standPose(StandPose.RANGED_ATTACK).standSound(ModSounds.SILVER_CHARIOT_RAPIER_SHOT)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> SILVER_CHARIOT_BLOCK = ACTIONS.registerEntry("silver_chariot_block", 
            () -> new StandEntityBlock());
    
    public static final RegistryObject<StandEntityAction> SILVER_CHARIOT_TAKE_OFF_ARMOR = ACTIONS.registerEntry("silver_chariot_take_off_armor", 
            () -> new SilverChariotTakeOffArmor(new StandEntityAction.Builder()
//                    .resolveLevelToUnlock(3)
                    .standSound(ModSounds.SILVER_CHARIOT_ARMOR_OFF)
                    .partsRequired(StandPart.MAIN_BODY)));
    
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<SilverChariotEntity>> STAND_SILVER_CHARIOT = 
            new EntityStandRegistryObject<>("silver_chariot", 
                    STANDS, 
                    () -> new EntityStandType<>(
                            0xBEC8D6, PART_3_NAME,
                            
                            new StandAction[] {
//                                    SILVER_CHARIOT_ATTACK.get(), 
                                    SILVER_CHARIOT_RAPIER_BARRAGE.get(), 
//                                    SILVER_CHARIOT_RAPIER_LAUNCH.get()
                                    },
                            new StandAction[] {
//                                    SILVER_CHARIOT_BLOCK.get(), 
                                    SILVER_CHARIOT_TAKE_OFF_ARMOR.get()
                                    },
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(9.0)
                            .speed(14.0)
                            .range(10.0)
                            .durability(12.0)
                            .precision(16.0)
                            .build("Silver Chariot"), 

                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.POLNAREFF_SILVER_CHARIOT)
                            .addOst(ModSounds.SILVER_CHARIOT_OST)), 

                    ENTITIES, 
                    () -> new StandEntityType<SilverChariotEntity>(SilverChariotEntity::new, 0.6F, 1.95F)
                    .summonSound(ModSounds.SILVER_CHARIOT_SUMMON)
                    .unsummonSound(ModSounds.SILVER_CHARIOT_UNSUMMON))
            .withDefaultStandAttributes();
    
    
    
// ======================================== Magician's Red ========================================
    
    public static final RegistryObject<StandEntityAction> MAGICIANS_RED_PUNCH = ACTIONS.registerEntry("magicians_red_punch", 
            () -> new StandEntityLightAttack(new StandEntityLightAttack.Builder()
                    .punchSound(ModSounds.MAGICIANS_RED_PUNCH_LIGHT)));

    public static final RegistryObject<StandEntityHeavyAttack> MAGICIANS_RED_KICK = ACTIONS.registerEntry("magicians_red_kick", 
            () -> new MagiciansRedKick(new StandEntityHeavyAttack.Builder()
                    .punchSound(ModSounds.MAGICIANS_RED_KICK_HEAVY)
                    .partsRequired(StandPart.LEGS)));

    public static final RegistryObject<StandEntityAction> MAGICIANS_RED_HEAVY_PUNCH = ACTIONS.registerEntry("magicians_red_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder()
                    .punchSound(ModSounds.MAGICIANS_RED_PUNCH_HEAVY)
                    .partsRequired(StandPart.ARMS)
                    .setFinisherVariation(MAGICIANS_RED_KICK)
                    .shiftVariationOf(MAGICIANS_RED_PUNCH)));
    
    public static final RegistryObject<StandEntityAction> MAGICIANS_RED_FLAME_BURST = ACTIONS.registerEntry("magicians_red_flame_burst", 
            () -> new MagiciansRedFlameBurst(new StandEntityAction.Builder().holdType()
                    .staminaCostTick(3)
                    .standOffsetFront().standPose(MagiciansRedFlameBurst.FLAME_BURST_POSE)
                    .partsRequired(StandPart.MAIN_BODY)));
    
    public static final RegistryObject<StandEntityAction> MAGICIANS_RED_FIREBALL = ACTIONS.registerEntry("magicians_red_fireball", 
            () -> new MagiciansRedFireball(new StandEntityAction.Builder().staminaCost(75).standPerformDuration(3)
//                    .resolveLevelToUnlock(2)
                    .standPose(StandPose.RANGED_ATTACK).standSound(ModSounds.MAGICIANS_RED_FIREBALL)
                    .partsRequired(StandPart.MAIN_BODY)));
    
    public static final RegistryObject<StandEntityAction> MAGICIANS_RED_CROSSFIRE_HURRICANE = ACTIONS.registerEntry("magicians_red_crossfire_hurricane", 
            () -> new MagiciansRedCrossfireHurricane(new StandEntityAction.Builder().staminaCost(500)
//                    .resolveLevelToUnlock(4).isTrained()
                    .standPose(StandPose.RANGED_ATTACK).shout(ModSounds.AVDOL_CROSSFIRE_HURRICANE).standSound(ModSounds.MAGICIANS_RED_FIRE_BLAST)
                    .partsRequired(StandPart.MAIN_BODY)));
    
    public static final RegistryObject<StandEntityAction> MAGICIANS_RED_CROSSFIRE_HURRICANE_SPECIAL = ACTIONS.registerEntry("magicians_red_ch_special", 
            () -> new MagiciansRedCrossfireHurricane(new StandEntityAction.Builder().staminaCost(500)
//                    .noResolveUnlock()
                    .standPose(StandPose.RANGED_ATTACK).shout(ModSounds.AVDOL_CROSSFIRE_HURRICANE_SPECIAL).standSound(ModSounds.MAGICIANS_RED_FIRE_BLAST)
                    .partsRequired(StandPart.MAIN_BODY)
                    .shiftVariationOf(MAGICIANS_RED_CROSSFIRE_HURRICANE)));
    
    public static final RegistryObject<StandEntityAction> MAGICIANS_RED_BLOCK = ACTIONS.registerEntry("magicians_red_block", 
            () -> new StandEntityBlock());
    
    public static final RegistryObject<StandEntityAction> MAGICIANS_RED_RED_BIND = ACTIONS.registerEntry("magicians_red_red_bind", 
            () -> new MagiciansRedRedBind(new StandEntityAction.Builder().staminaCostTick(1).holdType().heldSlowDownFactor(0.3F)
//                    .resolveLevelToUnlock(1)
                    .standOffsetFront().standPose(MagiciansRedRedBind.RED_BIND_POSE)
                    .shout(ModSounds.AVDOL_RED_BIND).standSound(ModSounds.MAGICIANS_RED_FIRE_BLAST)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandAction> MAGICIANS_RED_DETECTOR = ACTIONS.registerEntry("magicians_red_detector", 
            () -> new MagiciansRedDetector(new StandAction.Builder().autoSummonStand()
//                    .resolveLevelToUnlock(3)
                    .partsRequired(StandPart.MAIN_BODY)));
    
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<MagiciansRedEntity>> STAND_MAGICIANS_RED = 
            new EntityStandRegistryObject<>("magicians_red", 
                    STANDS, 
                    () -> new EntityStandType<>(
                            0xDE203A, PART_3_NAME,
                            
                            new StandAction[] {
//                                    MAGICIANS_RED_PUNCH.get(), 
//                                    MAGICIANS_RED_FLAME_BURST.get(), 
//                                    MAGICIANS_RED_FIREBALL.get(), 
                                    MAGICIANS_RED_CROSSFIRE_HURRICANE.get()
                                    },
                            new StandAction[] {
//                                    MAGICIANS_RED_BLOCK.get(), 
                                    MAGICIANS_RED_RED_BIND.get(), 
//                                    MAGICIANS_RED_DETECTOR.get()
                                    },
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(12.0)
                            .speed(12.0)
                            .range(5.0, 10.0)
                            .durability(12.0)
                            .precision(8.0)
                            .build("Magician's Red"), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.AVDOL_MAGICIANS_RED)
                            .addOst(ModSounds.MAGICIANS_RED_OST)), 
                    
                    ENTITIES, 
                    () -> new StandEntityType<MagiciansRedEntity>(MagiciansRedEntity::new, 0.65F, 1.95F)
                    .summonSound(ModSounds.MAGICIANS_RED_SUMMON))
            .withDefaultStandAttributes();
    
    
    
// ======================================== Crazy Diamond ========================================
    
    public static final RegistryObject<StandEntityAction> CRAZY_DIAMOND_PUNCH = ACTIONS.registerEntry("crazy_diamond_punch", 
            () -> new StandEntityLightAttack(new StandEntityLightAttack.Builder()
                    .punchSound(ModSounds.CRAZY_DIAMOND_PUNCH_LIGHT)
                    .standSound(Phase.WINDUP, ModSounds.CRAZY_DIAMOND_DORA)
                    ));
    
    public static final RegistryObject<StandEntityMeleeBarrage> CRAZY_DIAMOND_BARRAGE = ACTIONS.registerEntry("crazy_diamond_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder()
                    .barrageHitSound(ModSounds.CRAZY_DIAMOND_PUNCH_BARRAGE)
                    .standSound(ModSounds.CRAZY_DIAMOND_DORARARA)));
    
    public static final RegistryObject<StandEntityHeavyAttack> CRAZY_DIAMOND_COMBO_PUNCH = ACTIONS.registerEntry("crazy_diamond_misshaping_punch", 
            () -> new CrazyDiamondMisshapingPunch(new StandEntityHeavyAttack.Builder()
                    .punchSound(ModSounds.CRAZY_DIAMOND_PUNCH_HEAVY)
                    .standSound(Phase.WINDUP, ModSounds.CRAZY_DIAMOND_DORA_LONG)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityActionModifier> CRAZY_DIAMOND_MISSHAPE_FACE = ACTIONS.registerEntry("crazy_diamond_misshape_face", 
            () -> new CrazyDiamondMisshapeBodyPart(new StandAction.Builder().staminaCost(50)));
    
    public static final RegistryObject<StandEntityActionModifier> CRAZY_DIAMOND_MISSHAPE_ARMS = ACTIONS.registerEntry("crazy_diamond_misshape_arms", 
            () -> new CrazyDiamondMisshapeBodyPart(new StandAction.Builder().staminaCost(50)));
    
    public static final RegistryObject<StandEntityActionModifier> CRAZY_DIAMOND_MISSHAPE_LEGS = ACTIONS.registerEntry("crazy_diamond_misshape_legs", 
            () -> new CrazyDiamondMisshapeBodyPart(new StandAction.Builder().staminaCost(50)));
    
    public static final RegistryObject<StandEntityAction> CRAZY_DIAMOND_HEAVY_PUNCH = ACTIONS.registerEntry("crazy_diamond_heavy_punch", 
            () -> new CrazyDiamondHeavyPunch(new StandEntityHeavyAttack.Builder()
                    .punchSound(ModSounds.CRAZY_DIAMOND_PUNCH_HEAVY)
                    .standSound(Phase.WINDUP, ModSounds.CRAZY_DIAMOND_DORA_LONG)
                    .partsRequired(StandPart.ARMS)
                    .setFinisherVariation(CRAZY_DIAMOND_COMBO_PUNCH)
                    .shiftVariationOf(CRAZY_DIAMOND_PUNCH).shiftVariationOf(CRAZY_DIAMOND_BARRAGE)));
    
    public static final RegistryObject<StandEntityActionModifier> CRAZY_DIAMOND_LEAVE_OBJECT = ACTIONS.registerEntry("crazy_diamond_leave_object", 
            () -> new CrazyDiamondLeaveObject(new StandAction.Builder().staminaCost(50)));
    
    public static final RegistryObject<StandEntityAction> CRAZY_DIAMOND_BLOCK_BULLET = ACTIONS.registerEntry("crazy_diamond_block_bullet", 
            () -> new CrazyDiamondBlockBullet(new StandEntityAction.Builder().standWindupDuration(15).staminaCost(40).staminaCostTick(2F)
//                    .resolveLevelToUnlock(4)
                    .standPose(CrazyDiamondBlockBullet.BLOCK_BULLET_SHOT_POSE)
                    .standSound(Phase.WINDUP, ModSounds.CRAZY_DIAMOND_FIX_STARTED).standSound(Phase.PERFORM, ModSounds.CRAZY_DIAMOND_BULLET_SHOT)
                    .standOffsetFromUser(0.25, -0.5, 0)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> CRAZY_DIAMOND_BLOOD_CUTTER = ACTIONS.registerEntry("crazy_diamond_blood_cutter", 
            () -> new CrazyDiamondBloodCutter(new StandEntityAction.Builder().standWindupDuration(5).standRecoveryTicks(5).staminaCost(25).cooldown(300)
//                    .resolveLevelToUnlock(4)
                    .standPose(CrazyDiamondBloodCutter.BLOOD_CUTTER_SHOT_POSE)
                    .standSound(Phase.WINDUP, ModSounds.CRAZY_DIAMOND_FIX_STARTED, ModSounds.CRAZY_DIAMOND_DORA).standSound(Phase.PERFORM, ModSounds.CRAZY_DIAMOND_BLOOD_CUTTER_SHOT)
                    .standOffsetFromUser(-0.1, -0.5)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> CRAZY_DIAMOND_BLOCK = ACTIONS.registerEntry("crazy_diamond_block", 
            () -> new StandEntityBlock());
    
    public static final RegistryObject<CrazyDiamondRepairItem> CRAZY_DIAMOND_REPAIR = ACTIONS.registerEntry("crazy_diamond_repair", 
            () -> new CrazyDiamondRepairItem(new StandEntityAction.Builder().holdType().staminaCostTick(0.2F)
//                    .resolveLevelToUnlock(0).isTrained()
                    .standOffsetFromUser(0.667, 0.2, 0).standPose(CrazyDiamondRepairItem.ITEM_FIX_POSE)
                    .standSound(Phase.PERFORM, ModSounds.CRAZY_DIAMOND_FIX_STARTED)
                    .standAutoSummonMode(AutoSummonMode.OFF_ARM)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> CRAZY_DIAMOND_PREVIOUS_STATE = ACTIONS.registerEntry("crazy_diamond_previous_state", 
            () -> new CrazyDiamondPreviousState(new StandEntityAction.Builder().holdType().staminaCostTick(0.2F)
//                    .resolveLevelToUnlock(-1)
                    .standOffsetFromUser(0.667, 0.2, 0).standPose(CrazyDiamondRepairItem.ITEM_FIX_POSE)
                    .standSound(Phase.PERFORM, ModSounds.CRAZY_DIAMOND_FIX_STARTED).barrageVisuals(CRAZY_DIAMOND_BARRAGE)
                    .standAutoSummonMode(AutoSummonMode.OFF_ARM)
                    .partsRequired(StandPart.ARMS)
                    .shiftVariationOf(CRAZY_DIAMOND_REPAIR)));
    
    public static final RegistryObject<CrazyDiamondHeal> CRAZY_DIAMOND_HEAL = ACTIONS.registerEntry("crazy_diamond_heal", 
            () -> new CrazyDiamondHeal(new StandEntityAction.Builder().holdType().staminaCostTick(1)
//                    .resolveLevelToUnlock(1)
                    .standSound(Phase.PERFORM, ModSounds.CRAZY_DIAMOND_FIX_STARTED).barrageVisuals(CRAZY_DIAMOND_BARRAGE)
                    .standAutoSummonMode(AutoSummonMode.MAIN_ARM)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> CRAZY_DIAMOND_RESTORE_TERRAIN = ACTIONS.registerEntry("crazy_diamond_restore_terrain", 
            () -> new CrazyDiamondRestoreTerrain(new StandEntityAction.Builder().holdType().staminaCostTick(2) // cost per block rather than per tick
//                    .resolveLevelToUnlock(2)
                    .shout(ModSounds.JOSUKE_FIX).standSound(Phase.PERFORM, ModSounds.CRAZY_DIAMOND_FIX_STARTED)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> CRAZY_DIAMOND_BLOCK_ANCHOR_MOVE = ACTIONS.registerEntry("crazy_diamond_anchor_move", 
            () -> new CrazyDiamondBlockCheckpointMove(new StandEntityAction.Builder().holdType().staminaCostTick(1)
//                    .resolveLevelToUnlock(3)
                    .standSound(Phase.PERFORM, ModSounds.CRAZY_DIAMOND_FIX_STARTED)
                    .standAutoSummonMode(AutoSummonMode.OFF_ARM)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> CRAZY_DIAMOND_BLOCK_ANCHOR_MAKE = ACTIONS.registerEntry("crazy_diamond_anchor_make", 
            () -> new CrazyDiamondBlockCheckpointMake(new StandEntityAction.Builder().standWindupDuration(10).standRecoveryTicks(5).staminaCost(25)
//                    .resolveLevelToUnlock(3)
                    .standPose(StandPose.HEAVY_ATTACK_COMBO)
                    .partsRequired(StandPart.ARMS)
                    .shiftVariationOf(CRAZY_DIAMOND_BLOCK_ANCHOR_MOVE)));
    
    
//    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<CrazyDiamondEntity>> STAND_CRAZY_DIAMOND = 
//            new EntityStandRegistryObject<>("crazy_diamond", 
//                    STANDS, 
//                    () -> new EntityStandType<>(
//                            0xEA6E7C, PART_4_NAME,
//                            
//                            new StandAction[] {
////                                    CRAZY_DIAMOND_PUNCH.get(), 
//                                    CRAZY_DIAMOND_BARRAGE.get(), 
////                                    CRAZY_DIAMOND_BLOCK_BULLET.get(), 
////                                    CRAZY_DIAMOND_BLOOD_CUTTER.get()
//                                    },
//                            new StandAction[] {
////                                    CRAZY_DIAMOND_BLOCK.get(), 
////                                    CRAZY_DIAMOND_REPAIR.get(), 
//                                    CRAZY_DIAMOND_HEAL.get(), 
////                                    CRAZY_DIAMOND_RESTORE_TERRAIN.get(), 
////                                    CRAZY_DIAMOND_BLOCK_ANCHOR_MOVE.get()
//                                    },
//                            
//                            StandStats.class, new StandStats.Builder()
//                            .tier(5)
//                            .power(14.0)
//                            .speed(14.0)
//                            .range(2.0, 4.0)
//                            .durability(12.0)
//                            .precision(12.0)
//                            .build("Crazy Diamond"), 
//                            
//                            new StandType.StandTypeOptionals()
//                            .addSummonShout(ModSounds.JOSUKE_CRAZY_DIAMOND)
//                            .addOst(ModSounds.CRAZY_DIAMOND_OST)),
//                    
//                    ENTITIES,
//                    () -> new StandEntityType<CrazyDiamondEntity>(CrazyDiamondEntity::new, 0.65F, 1.95F)
//                    .summonSound(ModSounds.CRAZY_DIAMOND_SUMMON)
//                    .unsummonSound(ModSounds.CRAZY_DIAMOND_UNSUMMON))
//            .withDefaultStandAttributes();
    
    
    
    

    
    public static final RegistryObject<TimeResume> TIME_RESUME = ACTIONS.registerEntry("time_resume", 
            () -> new TimeResume(new StandAction.Builder()
                    .shiftVariationOf(STAR_PLATINUM_TIME_STOP).shiftVariationOf(THE_WORLD_TIME_STOP)));
    
    
    
    public static final RegistryObject<StandEntityMeleeBarrage> DARK_BLUE_MOON_BARRAGE = ACTIONS.registerEntry("dark_blue_moon_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.dark_blue_moon_punch_barrage)));

    public static final RegistryObject<StandEntityHeavyAttack> DARK_BLUE_MOON_HEAVY_PUNCH = ACTIONS.registerEntry("dark_blue_moon_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(DARK_BLUE_MOON_BARRAGE).punchSound(ModSounds.dark_blue_moon_punch_heavy)));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<DarkBlueMoonEntity>> STAND_DARK_BLUE_MOON = 
            new EntityStandRegistryObject<>("dark_blue_moon", 
                    STANDS, () -> new EntityStandType<>(
                            0x286EB0, PART_3_NAME,
                            
                            new StandAction[] {DARK_BLUE_MOON_BARRAGE.get()},
                            new StandAction[] {/*_______________________________________________________________________*/},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(10.0)
                            .speed(8.0)
                            .range(10.0, 10.0)
                            .durability(12.0)
                            .precision(8.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.tennille_dark_blue_moon)
                            .addOst(ModSounds.DARK_BLUE_MOON_OST)),
                    ENTITIES, () -> new StandEntityType<>(DarkBlueMoonEntity::new, 0.65F, 1.95F)
                    .summonSound(ModSounds.DARK_BLUE_MOON_SUMMON)
                    .unsummonSound(ModSounds.DARK_BLUE_MOON_UNSUMMON)).withDefaultStandAttributes();
    
    
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<CrazyDiamondEntity>> STAND_CRAZY_DIAMOND = 
            new EntityStandRegistryObject<>("crazy_diamond", 
                    STANDS, 
                    () -> new EntityStandType<>(
                            0xEA6E7C, PART_4_NAME,
                            
                            new StandAction[] {
//                                    CRAZY_DIAMOND_PUNCH.get(), 
                                    CRAZY_DIAMOND_BARRAGE.get(), 
//                                    CRAZY_DIAMOND_BLOCK_BULLET.get(), 
//                                    CRAZY_DIAMOND_BLOOD_CUTTER.get()
                                    },
                            new StandAction[] {
//                                    CRAZY_DIAMOND_BLOCK.get(), 
//                                    CRAZY_DIAMOND_REPAIR.get(), 
                                    CRAZY_DIAMOND_HEAL.get(), 
//                                    CRAZY_DIAMOND_RESTORE_TERRAIN.get(), 
//                                    CRAZY_DIAMOND_BLOCK_ANCHOR_MOVE.get()
                                    },
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(14.0)
                            .speed(14.0)
                            .range(2.0, 4.0)
                            .durability(12.0)
                            .precision(12.0)
                            .build("Crazy Diamond"), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.JOSUKE_CRAZY_DIAMOND)
                            .addOst(ModSounds.CRAZY_DIAMOND_OST)),
                    
                    ENTITIES,
                    () -> new StandEntityType<CrazyDiamondEntity>(CrazyDiamondEntity::new, 0.65F, 1.95F)
                    .summonSound(ModSounds.CRAZY_DIAMOND_SUMMON)
                    .unsummonSound(ModSounds.CRAZY_DIAMOND_UNSUMMON))
            .withDefaultStandAttributes();
    
    
    
    public static final RegistryObject<StandEntityMeleeBarrage> KILLER_QUEEN_BARRAGE = ACTIONS.registerEntry("killer_queen_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.killer_queen_punch_barrage)));

    public static final RegistryObject<StandEntityHeavyAttack> KILLER_QUEEN_HEAVY_PUNCH = ACTIONS.registerEntry("killer_queen_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(KILLER_QUEEN_BARRAGE).punchSound(ModSounds.killer_queen_punch_heavy)));
                
    public static final RegistryObject<KillerQueenFirstBomb> KILLER_QUEEN_BOMB = ACTIONS.registerEntry("killer_queen_bomb", 
            () -> new KillerQueenFirstBomb(new StandEntityAction.Builder()));
                            
    public static final RegistryObject<KillerQueenDetonate> KILLER_QUEEN_DETONATE = ACTIONS.registerEntry("killer_queen_detonate", 
            () -> new KillerQueenDetonate(new StandEntityAction.Builder().shiftVariationOf(KILLER_QUEEN_BOMB)));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StandEntity>> STAND_KILLER_QUEEN = 
            new EntityStandRegistryObject<>("killer_queen", 
                    STANDS, () -> new EntityStandType<>(
                            0xD9C7F0, PART_4_NAME,
                            
                            new StandAction[] {KILLER_QUEEN_BARRAGE.get()},
                            new StandAction[] {KILLER_QUEEN_BOMB.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(14.0)
                            .speed(9.0)
                            .range(2.0, 4.0)
                            .durability(12.0)
                            .precision(11.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.kira_killer_queen)
                            .addOst(ModSounds.KILLER_QUEEN_OST)),
                    ENTITIES, () -> StandEntityType.basicEntity(0.65F, 1.95F)
                    .summonSound(ModSounds.KILLER_QUEEN_SUMMON)
                    .unsummonSound(ModSounds.KILLER_QUEEN_UNSUMMON)).withDefaultStandAttributes();
    
    
    
    public static final RegistryObject<StandEntityMeleeBarrage> THE_HAND_BARRAGE = ACTIONS.registerEntry("the_hand_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.the_hand_punch_barrage)));

    public static final RegistryObject<StandEntityHeavyAttack> THE_HAND_HEAVY_PUNCH = ACTIONS.registerEntry("the_hand_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(THE_HAND_BARRAGE).punchSound(ModSounds.the_hand_punch_heavy)));
    
    public static final RegistryObject<StandEntityAction> THE_HAND_SWIPE = ACTIONS.registerEntry("the_hand_swipe", 
            () -> new TheHandSwipe(new StandEntityAction.Builder().standSound(ModSounds.the_hand_erase)));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StandEntity>> STAND_THE_HAND = 
            new EntityStandRegistryObject<>("the_hand", 
                    STANDS, () -> new EntityStandType<>(
                            0x05028F, PART_4_NAME,
                            
                            new StandAction[] {THE_HAND_BARRAGE.get()},
                            new StandAction[] {THE_HAND_SWIPE.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(12.0)
                            .speed(10.0)
                            .range(2.0, 4.0)
                            .durability(8.0)
                            .precision(8.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.okuyasu_the_hand)
                            .addOst(ModSounds.THE_HAND_OST)),
                    ENTITIES, () -> StandEntityType.basicEntity(0.65F, 1.95F)
                    .summonSound(ModSounds.THE_HAND_SUMMON)
                    .unsummonSound(ModSounds.THE_HAND_UNSUMMON)).withDefaultStandAttributes();
    
    
    
    public static final RegistryObject<StandEntityMeleeBarrage> ECHOES_ACT_3_BARRAGE = ACTIONS.registerEntry("echoes_act_3_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.echoes_act_3_punch_barrage)));

    public static final RegistryObject<StandEntityHeavyAttack> ECHOES_ACT_3_HEAVY_PUNCH = ACTIONS.registerEntry("echoes_act_3_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(ECHOES_ACT_3_BARRAGE).punchSound(ModSounds.echoes_act_3_punch_heavy)));
            
    public static final RegistryObject<EchoesThreeFreeze> ECHOES_ACT_3_THREE_FREEZE = ACTIONS.registerEntry("echoes_act_3_three_freeze", 
            () -> new EchoesThreeFreeze(new StandEntityAction.Builder().holdType()));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StandEntity>> STAND_ECHOES_ACT_3 = 
            new EntityStandRegistryObject<>("echoes_act_3", 
                    STANDS, () -> new EntityStandType<>(
                            0x3A9967, PART_4_NAME,
                            
                            new StandAction[] {ECHOES_ACT_3_BARRAGE.get()},
                            new StandAction[] {ECHOES_ACT_3_THREE_FREEZE.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(11.0)
                            .speed(13.0)
                            .range(2.0, 5.0)
                            .durability(12.0)
                            .precision(10.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.koichi_echoes_act_3)
                            .addOst(ModSounds.ECHOES_ACT_3_OST)),
                    ENTITIES, () -> StandEntityType.basicEntity(0.65F, 1.95F)
                    .summonSound(ModSounds.ECHOES_ACT_3_SUMMON)
                    .unsummonSound(ModSounds.ECHOES_ACT_3_UNSUMMON)).withDefaultStandAttributes();
    

    
    public static final RegistryObject<StandEntityMeleeBarrage> HEAVENS_DOOR_BARRAGE = ACTIONS.registerEntry("heavens_door_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.heavens_door_punch_barrage)));

    public static final RegistryObject<StandEntityHeavyAttack> HEAVENS_DOOR_HEAVY_PUNCH = ACTIONS.registerEntry("heavens_door_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(HEAVENS_DOOR_BARRAGE).punchSound(ModSounds.heavens_door_punch_heavy)));
                        
    public static final RegistryObject<HeavensDoorSafetyLock> HEAVENS_DOOR_SAFETY_LOCK = ACTIONS.registerEntry("heavens_door_safety_lock", 
            () -> new HeavensDoorSafetyLock(new StandEntityAction.Builder().shout(ModSounds.rohan_safety_lock)));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StandEntity>> STAND_HEAVENS_DOOR = 
            new EntityStandRegistryObject<>("heavens_door", 
                    STANDS, () -> new EntityStandType<>(
                            0xEBE4D2, PART_4_NAME,
                            
                            new StandAction[] {HEAVENS_DOOR_BARRAGE.get()},
                            new StandAction[] {HEAVENS_DOOR_SAFETY_LOCK.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(6.0)
                            .speed(12.0)
                            .range(5.0, 10.0)
                            .durability(12.0)
                            .precision(10.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.rohan_heavens_door)
                            .addOst(ModSounds.HEAVENS_DOOR_OST)),
                    ENTITIES, () -> StandEntityType.basicEntity(0.65F, 1.95F)
                    .summonSound(ModSounds.HEAVENS_DOOR_SUMMON)
                    .unsummonSound(ModSounds.HEAVENS_DOOR_UNSUMMON)).withDefaultStandAttributes();
    

    
    public static final RegistryObject<StandEntityMeleeBarrage> BOY_II_MAN_BARRAGE = ACTIONS.registerEntry("boy_ii_man_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.boy_ii_man_punch_barrage)));

    public static final RegistryObject<StandEntityHeavyAttack> BOY_II_MAN_HEAVY_PUNCH = ACTIONS.registerEntry("boy_ii_man_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(BOY_II_MAN_BARRAGE).punchSound(ModSounds.boy_ii_man_punch_heavy)));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StandEntity>> STAND_BOY_II_MAN = 
            new EntityStandRegistryObject<>("boy_ii_man", 
                    STANDS, () -> new EntityStandType<>(
                            0x000000, PART_4_NAME,
                            
                            new StandAction[] {BOY_II_MAN_BARRAGE.get()},
                            new StandAction[] {/*_______________________________________________________________________*/},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(8.0)
                            .speed(10.0)
                            .range(5.0, 10.0)
                            .durability(12.0)
                            .precision(8.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.ken_boy_ii_man)
                            .addOst(ModSounds.BOY_II_MAN_OST)
                            .setPlayerAccess(false)),
                    ENTITIES, () -> StandEntityType.basicEntity(0.65F, 1.95F)
                    .summonSound(ModSounds.BOY_II_MAN_SUMMON)
                    .unsummonSound(ModSounds.BOY_II_MAN_UNSUMMON)).withDefaultStandAttributes();
    
    
    
    public static final RegistryObject<StandEntityMeleeBarrage> GOLD_EXPERIENCE_BARRAGE = ACTIONS.registerEntry("gold_experience_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().standSound(ModSounds.gold_experience_muda_muda_muda).barrageHitSound(ModSounds.gold_experience_punch_barrage).standSound(ModSounds.gold_experience_muda_muda_muda)));

    public static final RegistryObject<StandEntityHeavyAttack> GOLD_EXPERIENCE_HEAVY_PUNCH = ACTIONS.registerEntry("gold_experience_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(GOLD_EXPERIENCE_BARRAGE).standSound(ModSounds.gold_experience_punch_heavy).standSound(ModSounds.gold_experience_muda)));
    
    public static final RegistryObject<StandEntityAction> GOLD_EXPERIENCE_CREATE_LIFE = ACTIONS.registerEntry("gold_experience_create_life", 
            () -> new GoldExperienceCreateLife(new StandEntityAction.Builder().standSound(ModSounds.gold_experience_life_creation)));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StandEntity>> STAND_GOLD_EXPERIENCE = 
            new EntityStandRegistryObject<>("gold_experience", 
                    STANDS, () -> new EntityStandType<>(
                            0xd88f1f, PART_5_NAME,
                            
                            new StandAction[] {GOLD_EXPERIENCE_BARRAGE.get()},
                            new StandAction[] {GOLD_EXPERIENCE_CREATE_LIFE.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(10.0)
                            .speed(14.0)
                            .range(2.0, 4.0)
                            .durability(7.0)
                            .precision(10.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.giorno_gold_experience)
                            .addOst(ModSounds.GOLD_EXPERIENCE_OST)),
                    ENTITIES, () -> StandEntityType.basicEntity(0.65F, 1.95F)
                    .summonSound(ModSounds.GOLD_EXPERIENCE_SUMMON)
                    .unsummonSound(ModSounds.GOLD_EXPERIENCE_UNSUMMON)).withDefaultStandAttributes();
    

    
    public static final RegistryObject<StandEntityMeleeBarrage> GOLD_EXPERIENCE_REQUIEM_BARRAGE = ACTIONS.registerEntry("gold_experience_requiem_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.gold_experience_punch_barrage).standSound(ModSounds.gold_experience_muda_muda_muda)));

    public static final RegistryObject<StandEntityHeavyAttack> GOLD_EXPERIENCE_REQUIEM_HEAVY_PUNCH = ACTIONS.registerEntry("gold_experience_requiem_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(GOLD_EXPERIENCE_REQUIEM_BARRAGE).punchSound(ModSounds.gold_experience_punch_heavy).standSound(ModSounds.gold_experience_muda)));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<GoldExperienceRequiemEntity>> STAND_GOLD_EXPERIENCE_REQUIEM = 
            new EntityStandRegistryObject<>("gold_experience_requiem", 
                    STANDS, () -> new EntityStandType<>(
                            0xFEECA5, PART_5_NAME,
                            
                            new StandAction[] {GOLD_EXPERIENCE_REQUIEM_BARRAGE.get()},
                            new StandAction[] {},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(20.0)
                            .speed(20.0)
                            .range(20.0, 40.0)
                            .durability(20.0)
                            .precision(20.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.giorno_gold_experience_requiem)
                            .addOst(ModSounds.GOLD_EXPERIENCE_REQUIEM_OST)),
                    ENTITIES, () -> new StandEntityType<GoldExperienceRequiemEntity>(GoldExperienceRequiemEntity::new, 0.65F, 1.95F)
                    .summonSound(ModSounds.GOLD_EXPERIENCE_REQUIEM_SUMMON)
                    .unsummonSound(ModSounds.GOLD_EXPERIENCE_REQUIEM_UNSUMMON)).withDefaultStandAttributes();
    
    
    
    public static final RegistryObject<StandEntityMeleeBarrage> KING_CRIMSON_BARRAGE = ACTIONS.registerEntry("king_crimson_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.king_crimson_punch_barrage)));

    public static final RegistryObject<StandEntityHeavyAttack> KING_CRIMSON_HEAVY_PUNCH = ACTIONS.registerEntry("king_crimson_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(KING_CRIMSON_BARRAGE).punchSound(ModSounds.king_crimson_punch_heavy)));
    
    public static final RegistryObject<StandEntityAction> KING_CRIMSON_TIME_ERASE = ACTIONS.registerEntry("king_crimson_time_erase", 
            () -> new KingCrimsonTimeErase(new StandEntityAction.Builder().standSound(ModSounds.king_crimson_time_erase)));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StandEntity>> STAND_KING_CRIMSON = 
            new EntityStandRegistryObject<>("king_crimson", 
                    STANDS, () -> new EntityStandType<>(
                            0xA82525, PART_5_NAME,
                            
                            new StandAction[] {KING_CRIMSON_BARRAGE.get()},
                            new StandAction[] {KING_CRIMSON_TIME_ERASE.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(16.0)
                            .speed(14.0)
                            .range(2.0, 4.0)
                            .durability(4.0)
                            .precision(8.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.diavolo_king_crimson)
                            .addOst(ModSounds.KING_CRIMSON_OST)),
                    ENTITIES, () -> StandEntityType.basicEntity(0.65F, 1.95F)
                    .summonSound(ModSounds.KING_CRIMSON_SUMMON)
                    .unsummonSound(ModSounds.KING_CRIMSON_UNSUMMON)).withDefaultStandAttributes();
    

    
    public static final RegistryObject<StandEntityMeleeBarrage> STICKY_FINGERS_BARRAGE = ACTIONS.registerEntry("sticky_fingers_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.sticky_fingers_punch_barrage).standSound(ModSounds.sticky_fingers_ari_ari_ari)));

    public static final RegistryObject<StandEntityHeavyAttack> STICKY_FINGERS_HEAVY_PUNCH = ACTIONS.registerEntry("sticky_fingers_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(STICKY_FINGERS_BARRAGE).punchSound(ModSounds.sticky_fingers_punch_heavy).standSound(ModSounds.bruno_arrivederci)));
    
    public static final RegistryObject<StickyFingersTp> STICKY_FINGERS_ZIPPER_TP = ACTIONS.registerEntry("sticky_fingers_zipper_tp", 
            () -> new StickyFingersTp(new StandAction.Builder()));
            
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StandEntity>> STAND_STICKY_FINGERS = 
            new EntityStandRegistryObject<>("sticky_fingers", 
                    STANDS, () -> new EntityStandType<>(
                            0x2258AB, PART_5_NAME,
                            
                            new StandAction[] {STICKY_FINGERS_BARRAGE.get()},
                            new StandAction[] {STICKY_FINGERS_ZIPPER_TP.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(14.0)
                            .speed(14.0)
                            .range(2.0, 4.0)
                            .durability(7.0)
                            .precision(10.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.bruno_sticky_fingers)
                            .addOst(ModSounds.STICKY_FINGERS_OST)),
                    ENTITIES, () -> StandEntityType.basicEntity(0.65F, 1.95F)
                    .summonSound(ModSounds.STICKY_FINGERS_SUMMON)
                    .unsummonSound(ModSounds.STICKY_FINGERS_UNSUMMON)).withDefaultStandAttributes();
    

    
//    public static final RegistryObject<StandEntityMeleeBarrage> PURPLE_HAZE_BARRAGE = ACTIONS.registerEntry("purple_haze_barrage", 
//            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.purple_haze_punch_barrage)));
//
//    public static final RegistryObject<StandEntityHeavyAttack> PURPLE_HAZE_HEAVY_PUNCH = ACTIONS.registerEntry("purple_haze_heavy_punch", 
//            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(PURPLE_HAZE_BARRAGE).punchSound(ModSounds.purple_haze_punch_heavy)));
//    
//    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StandEntity>> STAND_PURPLE_HAZE = 
//            new EntityStandRegistryObject<>("purple_haze", 
//                    STANDS, () -> new EntityStandType<>(
//                            0x000000, PART_5_NAME,
//                            
//                            new StandAction[] {PURPLE_HAZE_BARRAGE.get()},
//                            new StandAction[] {/*_______________________________________________________________________*/},
//                            
//                            StandStats.class, new StandStats.Builder()
//                            .tier(5)
//                            .power(14.0)
//                            .speed(12.0)
//                            .range(2.0, 4.0)
//                            .durability(4.0)
//                            .precision(4.0)
//                            .build(""), 
//                            
//                            new StandType.StandTypeOptionals()
//                            .addSummonShout(ModSounds.fugo_purple_haze)
//                            .addOst(ModSounds.PURPLE_HAZE_OST)),
//                    ENTITIES, () -> StandEntityType.basicEntity(0.65F, 1.95F)
//                    .summonSound(ModSounds.PURPLE_HAZE_SUMMON)
//                    .unsummonSound(ModSounds.PURPLE_HAZE_UNSUMMON)).withDefaultStandAttributes();
    

    
    public static final RegistryObject<StandEntityMeleeBarrage> KRAFT_WORK_BARRAGE = ACTIONS.registerEntry("kraft_work_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.kraft_work_punch_barrage)));

    public static final RegistryObject<StandEntityHeavyAttack> KRAFT_WORK_HEAVY_PUNCH = ACTIONS.registerEntry("kraft_work_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(KRAFT_WORK_BARRAGE).punchSound(ModSounds.kraft_work_punch_heavy)));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<KraftWorkEntity>> STAND_KRAFT_WORK = 
            new EntityStandRegistryObject<>("kraft_work", 
                    STANDS, () -> new EntityStandType<>(
                            0x6F8F53, PART_5_NAME,
                            
                            new StandAction[] {KRAFT_WORK_BARRAGE.get()},
                            new StandAction[] {/*_______________________________________________________________________*/},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(14.0)
                            .speed(14.0)
                            .range(2.0, 4.0)
                            .durability(8.0)
                            .precision(4.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.sale_kraft_work)
                            .addOst(ModSounds.KRAFT_WORK_OST)),
                    ENTITIES, () -> new StandEntityType<>(KraftWorkEntity::new, 0.65F, 1.95F)
                    .summonSound(ModSounds.KRAFT_WORK_SUMMON)
                    .unsummonSound(ModSounds.KRAFT_WORK_UNSUMMON)).withDefaultStandAttributes();
    
    
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<ChariotRequiemEntity>> STAND_CHARIOT_REQUIEM = 
            new EntityStandRegistryObject<>("chariot_requiem", 
                    STANDS, () -> new EntityStandType<>(
                            0x15112B, PART_5_NAME,
                            
                            new StandAction[] {},
                            new StandAction[] {},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(4.0)
                            .speed(4.0)
                            .range(256.0, 256.0)
                            .durability(14.0)
                            .precision(4.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.polnareff_chariot_requiem)
                            .addOst(ModSounds.CHARIOT_REQUIEM_OST)),
                    ENTITIES, () -> new StandEntityType<>(ChariotRequiemEntity::new, 0.65F, 1.95F)
                    .summonSound(ModSounds.CHARIOT_REQUIEM_SUMMON)
                    .unsummonSound(ModSounds.CHARIOT_REQUIEM_UNSUMMON)).withDefaultStandAttributes();
    
    
        
    public static final RegistryObject<MetallicaKnives> METALLICA_KNIVES = ACTIONS.registerEntry("metallica_knives", 
            () -> new MetallicaKnives(new StandAction.Builder()));
        
    public static final RegistryObject<MetallicaInvisibility> METALLICA_INVISIBILITY = ACTIONS.registerEntry("metallica_invisibility", 
            () -> new MetallicaInvisibility(new StandAction.Builder()));
    
    public static final RegistryObject<StandType<StandStats>> STAND_METALLICA = STANDS.registerEntry("metallica", 
                    () -> new NoManifestationStandType<>(
                            0xD6DDE1, PART_5_NAME,
                            
                            new StandAction[] {METALLICA_KNIVES.get()},
                            new StandAction[] {METALLICA_INVISIBILITY.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(8.0)
                            .speed(8.0)
                            .range(10.0, 20.0)
                            .durability(12.0)
                            .precision(8.0)
                            .build(""), 
                            
                            null));
    
    
    
    
//    public static final RegistryObject<StandType<StandStats>> STAND_BEACH_BOY = STANDS.registerEntry("beach_boy", 
//                    () -> new NoManifestationStandType<>(
//                            0x000000, PART_5_NAME,
//                            
//                            new StandAction[] {},
//                            new StandAction[] {},
//                            
//                            StandStats.class, new StandStats.Builder()
//                            .tier(5)
//                            .power(8.0)
//                            .speed(12.0)
//                            .range(10.0, 15.0)
//                            .durability(8.0)
//                            .precision(1280)
//                            .build(""), 
//                            
//                            null));
    

    
    public static final RegistryObject<StandEntityMeleeBarrage> GREEN_DAY_BARRAGE = ACTIONS.registerEntry("green_day_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.green_day_punch_barrage)));

    public static final RegistryObject<StandEntityHeavyAttack> GREEN_DAY_HEAVY_PUNCH = ACTIONS.registerEntry("green_day_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(GREEN_DAY_BARRAGE).punchSound(ModSounds.green_day_punch_heavy)));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<GreenDayEntity>> STAND_GREEN_DAY = 
            new EntityStandRegistryObject<>("green_day", 
                    STANDS, () -> new EntityStandType<>(
                            0x1D5648, PART_5_NAME,
                            
                            new StandAction[] {GREEN_DAY_BARRAGE.get()},
                            new StandAction[] {},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(14.0)
                            .speed(8.0)
                            .range(32.0, 32.0)
                            .durability(14.0)
                            .precision(0.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.cioccolata_green_day)
                            .addOst(ModSounds.GREEN_DAY_OST)),
                    ENTITIES, () -> new StandEntityType<>(GreenDayEntity::new, 0.65F, 1.95F)
                    .summonSound(ModSounds.GREEN_DAY_SUMMON)
                    .unsummonSound(ModSounds.GREEN_DAY_UNSUMMON)).withDefaultStandAttributes();
    
    
    
    public static final RegistryObject<StandEntityMeleeBarrage> STONE_FREE_BARRAGE = ACTIONS.registerEntry("stone_free_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.stone_free_punch_barrage).standSound(ModSounds.stone_free_ora_ora_ora)));

    public static final RegistryObject<StandEntityHeavyAttack> STONE_FREE_HEAVY_PUNCH = ACTIONS.registerEntry("stone_free_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(STONE_FREE_BARRAGE).punchSound(ModSounds.stone_free_punch_heavy).standSound(ModSounds.stone_free_ora)));
    
    public static final RegistryObject<StandEntityAction> STONE_FREE_GRAPPLE = ACTIONS.registerEntry("stone_free_grapple", 
            () -> new StoneFreeGrapple(new StandEntityAction.Builder().holdType().standUserSlowDownFactor(1.0F)
                    .standOffsetFromUser(-0.75, 0.25)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> STONE_FREE_GRAPPLE_ENTITY = ACTIONS.registerEntry("stone_free_grapple_entity", 
            () -> new StoneFreeGrapple(new StandEntityAction.Builder().holdType().standUserSlowDownFactor(1.0F)
                    .standPose(HierophantGreenGrapple.GRAPPLE_POSE)
                    .standOffsetFromUser(-0.75, 0.25)
                    .partsRequired(StandPart.ARMS)
                    .shiftVariationOf(STONE_FREE_GRAPPLE)));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StandEntity>> STAND_STONE_FREE = 
            new EntityStandRegistryObject<>("stone_free", 
                    STANDS, () -> new EntityStandType<>(
                            0x32C8F9, PART_6_NAME,
                            
                            new StandAction[] {STONE_FREE_BARRAGE.get()},
                            new StandAction[] {STONE_FREE_GRAPPLE.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(14.0)
                            .speed(12.0)
                            .range(2.0, 4.0)
                            .durability(14.0)
                            .precision(8.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.jolyne_stone_free)
                            .addOst(ModSounds.STONE_FREE_OST)),
                    ENTITIES, () -> StandEntityType.basicEntity(0.65F, 1.95F)
                    .summonSound(ModSounds.STONE_FREE_SUMMON)
                    .unsummonSound(ModSounds.STONE_FREE_UNSUMMON)).withDefaultStandAttributes();
    

    
    public static final RegistryObject<StandEntityMeleeBarrage> WHITESNAKE_BARRAGE = ACTIONS.registerEntry("whitesnake_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.whitesnake_punch_barrage)));

    public static final RegistryObject<StandEntityHeavyAttack> WHITESNAKE_HEAVY_PUNCH = ACTIONS.registerEntry("whitesnake_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(WHITESNAKE_BARRAGE).punchSound(ModSounds.whitesnake_punch_heavy)));
    
    public static final RegistryObject<WhitesnakeRemoveStandDisc> WHITESNAKE_STAND_DISC = ACTIONS.registerEntry("whitesnake_take_stand_disc", 
            () -> new WhitesnakeRemoveStandDisc(new StandEntityAction.Builder()));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StandEntity>> STAND_WHITESNAKE = 
            new EntityStandRegistryObject<>("whitesnake", 
                    STANDS, () -> new EntityStandType<>(
                            0xD5D5D5, PART_6_NAME,
                            
                            new StandAction[] {WHITESNAKE_BARRAGE.get()},
                            new StandAction[] {WHITESNAKE_STAND_DISC.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(8.0)
                            .speed(10.0)
                            .range(20.0, 20.0)
                            .durability(14.0)
                            .precision(8.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.pucci_whitesnake)
                            .addOst(ModSounds.WHITESNAKE_OST)),
                    ENTITIES, () -> StandEntityType.basicEntity(0.65F, 1.95F)
                    .summonSound(ModSounds.WHITESNAKE_SUMMON)
                    .unsummonSound(ModSounds.WHITESNAKE_UNSUMMON)).withDefaultStandAttributes();
    
    
    
    public static final RegistryObject<StandEntityAction> C_MOON_PUNCH = ACTIONS.registerEntry("c_moon_punch", 
            () -> new CMoonPunch(new StandEntityLightAttack.Builder()
                    .punchSound(ModSounds.c_moon_punch_light)));
        
    public static final RegistryObject<CMoonRepel> C_MOON_REPEL = ACTIONS.registerEntry("c_moon_repel", 
            () -> new CMoonRepel(new StandEntityAction.Builder()));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<CMoonEntity>> STAND_C_MOON = 
            new EntityStandRegistryObject<>("c_moon", 
                    STANDS, () -> new EntityStandType<>(
                            0x8EDD68, PART_6_NAME,
                            
                            new StandAction[] {C_MOON_PUNCH.get()},
                            new StandAction[] {C_MOON_REPEL.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(0.0)
                            .speed(14.0)
                            .range(20.0, 20.0)
                            .durability(14.0)
                            .precision(14.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.pucci_c_moon)
                            .addOst(ModSounds.C_MOON_OST)),
                    ENTITIES, () -> new StandEntityType<>(CMoonEntity::new, 0.65F, 1.95F)
                    .summonSound(ModSounds.C_MOON_SUMMON)
                    .unsummonSound(ModSounds.C_MOON_UNSUMMON)).withDefaultStandAttributes();
    
    
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<MadeInHeavenEntity>> STAND_MADE_IN_HEAVEN = 
            new EntityStandRegistryObject<>("made_in_heaven", 
                    STANDS, () -> new EntityStandType<>(
                            0xFFFFFF, PART_6_NAME,
                            
                            new StandAction[] {/*_______________________________________________________________________*/},
                            new StandAction[] {/*_______________________________________________________________________*/},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(12.0)
                            .speed(100.0)
                            .range(5.0, 5.0)
                            .durability(16.0)
                            .precision(8.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.pucci_made_in_heaven)
                            .addOst(ModSounds.MADE_IN_HEAVEN_OST)),
                    ENTITIES, () -> new StandEntityType<>(MadeInHeavenEntity::new, 0.65F, 1.95F)
                    .summonSound(ModSounds.MADE_IN_HEAVEN_SUMMON)
                    .unsummonSound(ModSounds.MADE_IN_HEAVEN_UNSUMMON)).withDefaultStandAttributes();
    

    
    public static final RegistryObject<StandEntityMeleeBarrage> KISS_BARRAGE = ACTIONS.registerEntry("kiss_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.kiss_punch_barrage)));

    public static final RegistryObject<StandEntityHeavyAttack> KISS_HEAVY_PUNCH = ACTIONS.registerEntry("kiss_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(KISS_BARRAGE).punchSound(ModSounds.kiss_punch_heavy)));
    
    public static final RegistryObject<KissDupe> KISS_DUPE = ACTIONS.registerEntry("kiss_dupe", 
            () -> new KissDupe(new StandAction.Builder()));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StandEntity>> STAND_KISS = 
            new EntityStandRegistryObject<>("kiss", 
                    STANDS, () -> new EntityStandType<>(
                            0x962966, PART_6_NAME,
                            
                            new StandAction[] {KISS_BARRAGE.get()},
                            new StandAction[] {KISS_DUPE.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(14.0)
                            .speed(14.0)
                            .range(10.0, 10.0)
                            .durability(14.0)
                            .precision(8.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.ermes_kiss)
                            .addOst(ModSounds.KISS_OST)),
                    ENTITIES, () -> StandEntityType.basicEntity(0.65F, 1.95F)
                    .summonSound(ModSounds.KISS_SUMMON)
                    .unsummonSound(ModSounds.KISS_UNSUMMON)).withDefaultStandAttributes();
    

    
    public static final RegistryObject<StandEntityMeleeBarrage> WEATHER_REPORT_BARRAGE = ACTIONS.registerEntry("weather_report_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.weather_report_punch_barrage)));

    public static final RegistryObject<StandEntityHeavyAttack> WEATHER_REPORT_HEAVY_PUNCH = ACTIONS.registerEntry("weather_report_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(WEATHER_REPORT_BARRAGE).punchSound(ModSounds.weather_report_punch_heavy)));
    
    public static final RegistryObject<WeatherReportLightning> WEATHER_REPORT_LIGHTNING = ACTIONS.registerEntry("weather_report_lightning", 
            () -> new WeatherReportLightning(new StandEntityAction.Builder()));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StandEntity>> STAND_WEATHER_REPORT = 
            new EntityStandRegistryObject<>("weather_report", 
                    STANDS, () -> new EntityStandType<>(
                            0xEEEEEE, PART_6_NAME,
                            
                            new StandAction[] {WEATHER_REPORT_BARRAGE.get()},
                            new StandAction[] {WEATHER_REPORT_LIGHTNING.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(14.0)
                            .speed(14.0)
                            .range(2.0, 4.0)
                            .durability(12.0)
                            .precision(12.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.wes_weather_report)
                            .addOst(ModSounds.WEATHER_REPORT_OST)),
                    ENTITIES, () -> StandEntityType.basicEntity(0.65F, 1.95F)
                    .summonSound(ModSounds.WEATHER_REPORT_SUMMON)
                    .unsummonSound(ModSounds.WEATHER_REPORT_UNSUMMON)).withDefaultStandAttributes();
    

    
    public static final RegistryObject<StandEntityMeleeBarrage> DIVER_DOWN_BARRAGE = ACTIONS.registerEntry("diver_down_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.diver_down_punch_barrage)));

    public static final RegistryObject<StandEntityHeavyAttack> DIVER_DOWN_HEAVY_PUNCH = ACTIONS.registerEntry("diver_down_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(DIVER_DOWN_BARRAGE).punchSound(ModSounds.diver_down_punch_heavy)));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<DiverDownEntity>> STAND_DIVER_DOWN = 
            new EntityStandRegistryObject<>("diver_down", 
                    STANDS, () -> new EntityStandType<>(
                            0x00C9A1, PART_6_NAME,
                            
                            new StandAction[] {DIVER_DOWN_BARRAGE.get()},
                            new StandAction[] {/*_______________________________________________________________________*/},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(14.0)
                            .speed(14.0)
                            .range(10.0, 10.0)
                            .durability(8.0)
                            .precision(12.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.anasui_diver_down)
                            .addOst(ModSounds.DIVER_DOWN_OST)),
                    ENTITIES, () -> new StandEntityType<>(DiverDownEntity::new, 0.65F, 1.95F)
                    .summonSound(ModSounds.DIVER_DOWN_SUMMON)
                    .unsummonSound(ModSounds.DIVER_DOWN_UNSUMMON)).withDefaultStandAttributes();
    
    
    
    public static final RegistryObject<LimBizkitSummonZombie> LIMP_BIZKIT_SUMMON_ZOMBIE = ACTIONS.registerEntry("limp_bizkit_summon_zombie", 
            () -> new LimBizkitSummonZombie(new StandAction.Builder()));
    
    public static final RegistryObject<StandType<StandStats>> STAND_LIMP_BIZKIT = STANDS.registerEntry("limp_bizkit", 
                    () -> new NoManifestationStandType<>(
                            0x729C5A, PART_6_NAME,
                            
                            new StandAction[] {},
                            new StandAction[] {LIMP_BIZKIT_SUMMON_ZOMBIE.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(0.0)
                            .speed(12.0)
                            .range(32.0, 32.0)
                            .durability(14.0)
                            .precision(8.0)
                            .build(""), 
                            
                            null));
    

    
    public static final RegistryObject<JumpinJackFlashShootScrap> JUMPIN_JACK_FLASH_SHOOT_SCRAPS = ACTIONS.registerEntry("jumpin_jack_flash_shoot_scrap", 
            () -> new JumpinJackFlashShootScrap(new StandEntityAction.Builder()));
    
    public static final RegistryObject<JumpinJackFlashSpit> JUMPIN_JACK_FLASH_SPIT = ACTIONS.registerEntry("jumpin_jack_flash_spit", 
            () -> new JumpinJackFlashSpit(new StandAction.Builder()));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StandEntity>> STAND_JUMPIN_JACK_FLASH = 
            new EntityStandRegistryObject<>("jumpin_jack_flash", 
                    STANDS, () -> new EntityStandType<>(
                            0xD9D9D9, PART_6_NAME,
                            
                            new StandAction[] {JUMPIN_JACK_FLASH_SHOOT_SCRAPS.get()},
                            new StandAction[] {JUMPIN_JACK_FLASH_SPIT.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(12.0)
                            .speed(14.0)
                            .range(10.0, 10.0)
                            .durability(14.0)
                            .precision(6.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.lang_jumpin_jack_flash)
                            .addOst(ModSounds.JUMPIN_JACK_FLASH_OST)),
                    ENTITIES, () -> StandEntityType.basicEntity(0.65F, 1.95F)
                    .summonSound(ModSounds.JUMPIN_JACK_FLASH_SUMMON)
                    .unsummonSound(ModSounds.JUMPIN_JACK_FLASH_UNSUMMON)).withDefaultStandAttributes();
    

    
    public static final RegistryObject<StandEntityMeleeBarrage> TUSK_ACT_4_BARRAGE = ACTIONS.registerEntry("tusk_act_4_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.tusk_act_4_punch_barrage).standSound(ModSounds.tusk_act_4_ora_ora_ora)));

    public static final RegistryObject<StandEntityHeavyAttack> TUSK_ACT_4_HEAVY_PUNCH = ACTIONS.registerEntry("tusk_act_4_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(TUSK_ACT_4_BARRAGE).punchSound(ModSounds.tusk_act_4_punch_heavy).standSound(ModSounds.tusk_act_4_ora)));
    
    public static final RegistryObject<TuskAct4Bullet> TUSK_ACT_4_BULLET = ACTIONS.registerEntry("tusk_act_4_bullet", 
            () -> new TuskAct4Bullet(new StandEntityAction.Builder()));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StandEntity>> STAND_TUSK_ACT_4 = 
            new EntityStandRegistryObject<>("tusk_act_4", 
                    STANDS, () -> new EntityStandType<>(
                            0xFF5488, PART_7_NAME,
                            
                            new StandAction[] {TUSK_ACT_4_BARRAGE.get()},
                            new StandAction[] {TUSK_ACT_4_BULLET.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(16.0)
                            .speed(12.0)
                            .range(20.0, 20.0)
                            .durability(16.0)
                            .precision(12.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.johnny_tusk_act_4)
                            .addOst(ModSounds.TUSK_ACT_4_OST)),
                    ENTITIES, () -> StandEntityType.basicEntity(0.65F, 1.95F)
                    .summonSound(ModSounds.TUSK_ACT_4_SUMMON)
                    .unsummonSound(ModSounds.TUSK_ACT_4_UNSUMMON)).withDefaultStandAttributes();
    

    
    public static final RegistryObject<StandEntityMeleeBarrage> D4C_BARRAGE = ACTIONS.registerEntry("d4c_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.d4c_punch_barrage)));

    public static final RegistryObject<StandEntityHeavyAttack> D4C_HEAVY_PUNCH = ACTIONS.registerEntry("d4c_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(D4C_BARRAGE).punchSound(ModSounds.d4c_punch_heavy)));
    
    public static final RegistryObject<D4CSummonClone> D4C_CREATE_CLONE = ACTIONS.registerEntry("d4c_create_clone", 
            () -> new D4CSummonClone(new StandEntityAction.Builder()));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StandEntity>> STAND_D4C = 
            new EntityStandRegistryObject<>("d4c", 
                    STANDS, () -> new EntityStandType<>(
                            0x99E9FF, PART_7_NAME,
                            
                            new StandAction[] {D4C_BARRAGE.get()},
                            new StandAction[] {D4C_CREATE_CLONE.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(14.0)
                            .speed(14.0)
                            .range(2.0, 4.0)
                            .durability(14.0)
                            .precision(14.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.valentine_d4c)
                            .addOst(ModSounds.D4C_OST)),
                    ENTITIES, () -> StandEntityType.basicEntity(0.65F, 1.95F)
                    .summonSound(ModSounds.D4C_SUMMON)
                    .unsummonSound(ModSounds.D4C_UNSUMMON)).withDefaultStandAttributes();
    

    
    public static final RegistryObject<StandEntityMeleeBarrage> SOFT_AND_WET_BARRAGE = ACTIONS.registerEntry("soft_and_wet_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder().barrageHitSound(ModSounds.soft_and_wet_punch_barrage).standSound(ModSounds.soft_and_wet_ora_ora_ora)));

    public static final RegistryObject<StandEntityHeavyAttack> SOFT_AND_WET_HEAVY_PUNCH = ACTIONS.registerEntry("soft_and_wet_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder().partsRequired(StandPart.ARMS).shiftVariationOf(SOFT_AND_WET_BARRAGE).punchSound(ModSounds.soft_and_wet_punch_heavy).standSound(ModSounds.soft_and_wet_ora)));
    
    public static final RegistryObject<SoftAndWetBubbles> SOFT_AND_WET_BUBBLES = ACTIONS.registerEntry("soft_and_wet_bubbles", 
            () -> new SoftAndWetBubbles(new StandEntityAction.Builder().holdType()));
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StandEntity>> STAND_SOFT_AND_WET = 
            new EntityStandRegistryObject<>("soft_and_wet", 
                    STANDS, () -> new EntityStandType<>(
                            0xD2F5FA, PART_8_NAME,
                            
                            new StandAction[] {SOFT_AND_WET_BARRAGE.get()},
                            new StandAction[] {SOFT_AND_WET_BUBBLES.get()},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(10.0)
                            .speed(13.0)
                            .range(2.0, 4.0)
                            .durability(12.0)
                            .precision(10.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.josuke_soft_and_wet)
                            .addOst(ModSounds.SOFT_AND_WET_OST)),
                    ENTITIES, () -> StandEntityType.basicEntity(0.65F, 1.95F)
                    .summonSound(ModSounds.SOFT_AND_WET_SUMMON)
                    .unsummonSound(ModSounds.SOFT_AND_WET_UNSUMMON)).withDefaultStandAttributes();
    
    
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<WonderOfUEntity>> STAND_WONDER_OF_U = 
            new EntityStandRegistryObject<>("wonder_of_u", 
                    STANDS, () -> new EntityStandType<>(
                            0x2B0A28, PART_8_NAME,
                            
                            new StandAction[] {},
                            new StandAction[] {},
                            
                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(2.0)
                            .speed(2.0)
                            .range(128.0, 128.9)
                            .durability(12.0)
                            .precision(2.0)
                            .build(""), 
                            
                            new StandType.StandTypeOptionals()
                            .addSummonShout(ModSounds.toru_wonder_of_u)
                            .addOst(ModSounds.WONDER_OF_U_OST)),
                    ENTITIES, () -> new StandEntityType<>(WonderOfUEntity::new, 0.65F, 1.95F)
                    .summonSound(ModSounds.WONDER_OF_U_SUMMON)
                    .unsummonSound(ModSounds.WONDER_OF_U_UNSUMMON)).withDefaultStandAttributes();
    
    
    
//    public static final RegistryObject<StandType<StandStats>> BOY_II_MAN = STANDS.registerEntry("boy_ii_man", 
//            () -> {
////                StandArrowEntity.EntityPierce.addBehavior(
////                        () -> RockPaperScissorsKidEntity::canTurnFromArrow, 
////                        () -> RockPaperScissorsKidEntity::turnFromArrow);
//                return new BoyIIManStandType<>(
//                        0x749FA5, PART_4_NAME,
//                        
//                        new StandAction[] {},
//                        new StandAction[] {},
//                        StandStats.class, new StandStats.Builder()
//                        .tier(0)
//                        .power(0)
//                        .speed(0)
//                        .range(0)
//                        .durability(0)
//                        .precision(0)
//                        .build("Boy II Man"), 
//                        
//                        new StandType.StandTypeOptionals()
//                        .setPlayerAccess(false));
//            });
    
}
