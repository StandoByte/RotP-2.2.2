package com.github.standobyte.jojo.init.power.hamon;

import java.util.ArrayList;

import com.github.standobyte.jojo.JojoMod;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.init.power.CustomRegistryHolder;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.AbstractHamonSkill;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.AbstractHamonSkill.RewardType;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.BaseHamonSkill;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.BaseHamonSkill.HamonStat;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.CharacterHamonTechnique;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.CharacterTechniqueHamonSkill;

import net.minecraft.util.Util;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;

/*
 * FIXME !!! (hamon) skills to rework
 * 
 * OVERDRIVE                                        3 damage
 * SENDO_OVERDRIVE              7                   
 * TURQUOISE_BLUE_OVERDRIVE                         ~4-7 muti-hits of 1.5 damage
 * SUNLIGHT_YELLOW_OVERDRIVE    6                   
 *                                                  
 * THROWABLES_INFUSION          6                   
 * PLANT_INFUSION               4                   
 * ARROW_INFUSION               4                   
 * ANIMAL_INFUSION              8                   
 *                                                  
 * ZOOM_PUNCH                                       ~4-7 muti-hits of 0.75 damage
 * JUMP                         4                   
 * SPEED_BOOST                  4                   
 *                                                  
 * WALL_CLIMBING                4                   
 * DETECTOR                     4                   
 * LIFE_MAGNETISM               6                   
 * HAMON_SPREAD                 7                   
 *     capability for burns
 *                                                  
 * PROJECTILE_SHIELD            6                   
 * LIQUID_WALKING               6                   
 * REPELLING_OVERDRIVE          8                   
 * 
 */
@EventBusSubscriber(modid = JojoMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModHamonSkills {
    public static final CustomRegistryHolder<AbstractHamonSkill> HAMON_SKILLS = new CustomRegistryHolder<>(
            DeferredRegister.create(AbstractHamonSkill.class, JojoMod.MOD_ID), "hamon_skill");
    
    public static final CustomRegistryHolder<CharacterHamonTechnique> HAMON_CHARACTER_TECHNIQUES = new CustomRegistryHolder<>(
            DeferredRegister.create(CharacterHamonTechnique.class, JojoMod.MOD_ID), "hamon_techniques");
    
    
    
    public static final RegistryObject<BaseHamonSkill> OVERDRIVE = HAMON_SKILLS.registerEntry("overdrive", 
            () -> new BaseHamonSkill.Builder("overdrive", HamonStat.STRENGTH, RewardType.PASSIVE)
            .unlockedByDefault().build());
    
    public static final RegistryObject<BaseHamonSkill> SENDO_OVERDRIVE = HAMON_SKILLS.registerEntry("sendo_overdrive", 
            () -> new BaseHamonSkill.Builder("sendo_overdrive", HamonStat.STRENGTH, RewardType.ATTACK)
            .unlocks(ModHamon.HAMON_SENDO_OVERDRIVE)
            .requiredSkill(OVERDRIVE).build());
    
    public static final RegistryObject<BaseHamonSkill> TURQUOISE_BLUE_OVERDRIVE = HAMON_SKILLS.registerEntry("turquoise_blue_overdrive", 
            () -> new BaseHamonSkill.Builder("turquoise_blue_overdrive", HamonStat.STRENGTH, RewardType.ATTACK)
            .unlocks(ModHamon.HAMON_TURQUOISE_BLUE_OVERDRIVE)
            .requiredSkill(OVERDRIVE).build());
    
    public static final RegistryObject<BaseHamonSkill> SUNLIGHT_YELLOW_OVERDRIVE = HAMON_SKILLS.registerEntry("sunlight_yellow_overdrive", 
            () -> new BaseHamonSkill.Builder("sunlight_yellow_overdrive", HamonStat.STRENGTH, RewardType.ATTACK)
            .unlocks(ModHamon.HAMON_SUNLIGHT_YELLOW_OVERDRIVE)
            .requiredSkill(SENDO_OVERDRIVE).requiredSkill(TURQUOISE_BLUE_OVERDRIVE).build());

    
    public static final RegistryObject<BaseHamonSkill> THROWABLES_INFUSION = HAMON_SKILLS.registerEntry("throwables_infusion", 
            () -> new BaseHamonSkill.Builder("throwables_infusion", HamonStat.STRENGTH, RewardType.PASSIVE)
            .build());
    
    public static final RegistryObject<BaseHamonSkill> PLANT_INFUSION = HAMON_SKILLS.registerEntry("plant_infusion", 
            () -> new BaseHamonSkill.Builder("plant_infusion", HamonStat.STRENGTH, RewardType.ATTACK)
            .unlocks(ModHamon.HAMON_PLANT_INFUSION)
            .requiredSkill(THROWABLES_INFUSION).build());
    
    public static final RegistryObject<BaseHamonSkill> ARROW_INFUSION = HAMON_SKILLS.registerEntry("arrow_infusion", 
            () -> new BaseHamonSkill.Builder("arrow_infusion", HamonStat.STRENGTH, RewardType.PASSIVE)
            .requiredSkill(THROWABLES_INFUSION).build());
    
    public static final RegistryObject<BaseHamonSkill> ANIMAL_INFUSION = HAMON_SKILLS.registerEntry("animal_infusion", 
            () -> new BaseHamonSkill.Builder("animal_infusion", HamonStat.STRENGTH, RewardType.PASSIVE)
            .unlocks(ModHamon.HAMON_ORGANISM_INFUSION)
            .requiredSkill(PLANT_INFUSION).requiredSkill(ARROW_INFUSION).build());
    
            
    public static final RegistryObject<BaseHamonSkill> ZOOM_PUNCH = HAMON_SKILLS.registerEntry("zoom_punch", 
            () -> new BaseHamonSkill.Builder("zoom_punch", HamonStat.STRENGTH, RewardType.ATTACK)
            .unlocks(ModHamon.HAMON_ZOOM_PUNCH)
            .build());
            
    public static final RegistryObject<BaseHamonSkill> JUMP = HAMON_SKILLS.registerEntry("jump", 
            () -> new BaseHamonSkill.Builder("jump", HamonStat.STRENGTH, RewardType.ABILITY)
            .requiredSkill(ZOOM_PUNCH).build());
            
    public static final RegistryObject<BaseHamonSkill> SPEED_BOOST = HAMON_SKILLS.registerEntry("speed_boost", 
            () -> new BaseHamonSkill.Builder("speed_boost", HamonStat.STRENGTH, RewardType.ABILITY)
            .unlocks(ModHamon.HAMON_SPEED_BOOST)
            .requiredSkill(ZOOM_PUNCH).build());
    
    public static final RegistryObject<BaseHamonSkill> AFTERIMAGES = HAMON_SKILLS.registerEntry("afterimages", 
            () -> new BaseHamonSkill.Builder("afterimages", HamonStat.STRENGTH, RewardType.PASSIVE)
            .requiredSkill(JUMP).requiredSkill(SPEED_BOOST).build());
    
            
    public static final RegistryObject<BaseHamonSkill> HEALING = HAMON_SKILLS.registerEntry("healing", 
            () -> new BaseHamonSkill.Builder("healing", HamonStat.CONTROL, RewardType.ABILITY)
            .unlocks(ModHamon.HAMON_HEALING)
            .unlockedByDefault().build());
    
    public static final RegistryObject<BaseHamonSkill> PLANTS_GROWTH = HAMON_SKILLS.registerEntry("plants_growth", 
            () -> new BaseHamonSkill.Builder("plants_growth", HamonStat.CONTROL, RewardType.PASSIVE)
            .requiredSkill(HEALING).build());
            
    public static final RegistryObject<BaseHamonSkill> EXPEL_VENOM = HAMON_SKILLS.registerEntry("expel_venom", 
            () -> new BaseHamonSkill.Builder("expel_venom", HamonStat.CONTROL, RewardType.PASSIVE)
            .requiredSkill(HEALING).build());
    
    public static final RegistryObject<BaseHamonSkill> HEALING_TOUCH = HAMON_SKILLS.registerEntry("healing_touch", 
            () -> new BaseHamonSkill.Builder("healing_touch", HamonStat.CONTROL, RewardType.PASSIVE)
            .requiredSkill(PLANTS_GROWTH).requiredSkill(EXPEL_VENOM).build());

            
    public static final RegistryObject<BaseHamonSkill> WALL_CLIMBING = HAMON_SKILLS.registerEntry("wall_climbing", 
            () -> new BaseHamonSkill.Builder("wall_climbing", HamonStat.CONTROL, RewardType.ABILITY)
            .unlocks(ModHamon.HAMON_WALL_CLIMBING)
            .build());
    
    public static final RegistryObject<BaseHamonSkill> DETECTOR = HAMON_SKILLS.registerEntry("detector", 
            () -> new BaseHamonSkill.Builder("detector", HamonStat.CONTROL, RewardType.ABILITY)
            .unlocks(ModHamon.HAMON_DETECTOR)
            .requiredSkill(WALL_CLIMBING).build());
    
    public static final RegistryObject<BaseHamonSkill> LIFE_MAGNETISM = HAMON_SKILLS.registerEntry("life_magnetism", 
            () -> new BaseHamonSkill.Builder("life_magnetism", HamonStat.CONTROL, RewardType.ABILITY)
            .unlocks(ModHamon.HAMON_LIFE_MAGNETISM)
            .requiredSkill(WALL_CLIMBING).build());
    
    public static final RegistryObject<BaseHamonSkill> HAMON_SPREAD = HAMON_SKILLS.registerEntry("hamon_spread", 
            () -> new BaseHamonSkill.Builder("hamon_spread", HamonStat.CONTROL, RewardType.PASSIVE)
            .requiredSkill(DETECTOR).requiredSkill(LIFE_MAGNETISM).build());

            
    public static final RegistryObject<BaseHamonSkill> WATER_WALKING = HAMON_SKILLS.registerEntry("water_walking", 
            () -> new BaseHamonSkill.Builder("water_walking", HamonStat.CONTROL, RewardType.PASSIVE)
            .build());
            
    public static final RegistryObject<BaseHamonSkill> PROJECTILE_SHIELD = HAMON_SKILLS.registerEntry("projectile_shield", 
            () -> new BaseHamonSkill.Builder("projectile_shield", HamonStat.CONTROL, RewardType.ABILITY)
            .unlocks(ModHamon.HAMON_PROJECTILE_SHIELD)
            .requiredSkill(WATER_WALKING).build());
    
    public static final RegistryObject<BaseHamonSkill> LAVA_WALKING = HAMON_SKILLS.registerEntry("lava_walking", 
            () -> new BaseHamonSkill.Builder("lava_walking", HamonStat.CONTROL, RewardType.PASSIVE)
            .requiredSkill(WATER_WALKING).build());
    
    public static final RegistryObject<BaseHamonSkill> REPELLING_OVERDRIVE = HAMON_SKILLS.registerEntry("repelling_overdrive", 
            () -> new BaseHamonSkill.Builder("repelling_overdrive", HamonStat.CONTROL, RewardType.ABILITY)
            .unlocks(ModHamon.HAMON_REPELLING_OVERDRIVE)
            .requiredSkill(PROJECTILE_SHIELD).requiredSkill(LAVA_WALKING).build());
    

    
    
    public static final RegistryObject<CharacterTechniqueHamonSkill> NATURAL_TALENT = HAMON_SKILLS.registerEntry("natural_talent",
            () -> new CharacterTechniqueHamonSkill.Builder("natural_talent", RewardType.PASSIVE).build());
    
    public static final RegistryObject<CharacterTechniqueHamonSkill> OVERDRIVE_BARRAGE = HAMON_SKILLS.registerEntry("overdrive_barrage",
            () -> new CharacterTechniqueHamonSkill.Builder("overdrive_barrage", RewardType.ATTACK)
            .unlocks(ModHamon.JONATHAN_OVERDRIVE_BARRAGE)
            .requiredSkill(SUNLIGHT_YELLOW_OVERDRIVE).build());

    public static final RegistryObject<CharacterTechniqueHamonSkill> SCARLET_OVERDRIVE = HAMON_SKILLS.registerEntry("scarlet_overdrive",
            () -> new CharacterTechniqueHamonSkill.Builder("scarlet_overdrive", RewardType.ATTACK)
            .unlocks(ModHamon.JONATHAN_SCARLET_OVERDRIVE)
            .requiredSkill(SUNLIGHT_YELLOW_OVERDRIVE).build());
    
    public static final RegistryObject<CharacterTechniqueHamonSkill> METAL_SILVER_OVERDRIVE = HAMON_SKILLS.registerEntry("metal_silver_overdrive",
            () -> new CharacterTechniqueHamonSkill.Builder("metal_silver_overdrive", RewardType.PASSIVE)
            .requiredSkill(SENDO_OVERDRIVE).build());
    
    public static final RegistryObject<CharacterHamonTechnique> CHARACTER_JONATHAN = HAMON_CHARACTER_TECHNIQUES.registerEntry("jonathan", 
            () -> new CharacterHamonTechnique.Builder("jonathan", Util.make(new ArrayList<>(), list -> {
                list.add(ModHamonSkills.OVERDRIVE_BARRAGE);
                list.add(ModHamonSkills.SCARLET_OVERDRIVE);
                list.add(ModHamonSkills.METAL_SILVER_OVERDRIVE);
            }))
            .perkOnPick(ModHamonSkills.NATURAL_TALENT)
            .musicOnPick(ModSounds.HAMON_PICK_JONATHAN).build());


    // FIXME !!! (hamon) add a Zeppeli perk for keepHamonOnDeath == true
    public static final RegistryObject<CharacterTechniqueHamonSkill> DEEP_PASS = HAMON_SKILLS.registerEntry("deep_pass",
            () -> new CharacterTechniqueHamonSkill.Builder("deep_pass", RewardType.PASSIVE).build());
    
    public static final RegistryObject<CharacterTechniqueHamonSkill> HAMON_CUTTER = HAMON_SKILLS.registerEntry("hamon_cutter",
            () -> new CharacterTechniqueHamonSkill.Builder("hamon_cutter", RewardType.ATTACK)
            .unlocks(ModHamon.ZEPPELI_HAMON_CUTTER)
            .requiredSkill(THROWABLES_INFUSION).build());
    
    public static final RegistryObject<CharacterTechniqueHamonSkill> SENDO_WAVE_KICK = HAMON_SKILLS.registerEntry("sendo_wave_kick",
            () -> new CharacterTechniqueHamonSkill.Builder("sendo_wave_kick", RewardType.ATTACK)
            .unlocks(ModHamon.ZEPPELI_SENDO_WAVE_KICK)
            .requiredSkill(JUMP).build());
    
    public static final RegistryObject<CharacterTechniqueHamonSkill> TORNADO_OVERDRIVE = HAMON_SKILLS.registerEntry("tornado_overdrive",
            () -> new CharacterTechniqueHamonSkill.Builder("tornado_overdrive", RewardType.ATTACK)
            .unlocks(ModHamon.ZEPPELI_TORNADO_OVERDRIVE)
            .requiredSkill(JUMP).build());
    
    public static final RegistryObject<CharacterHamonTechnique> CHARACTER_ZEPPELI = HAMON_CHARACTER_TECHNIQUES.registerEntry("zeppeli", 
            () -> new CharacterHamonTechnique.Builder("zeppeli", Util.make(new ArrayList<>(), list -> {
                list.add(ModHamonSkills.HAMON_CUTTER);
                list.add(ModHamonSkills.SENDO_WAVE_KICK);
                list.add(ModHamonSkills.TORNADO_OVERDRIVE);
            }))
            .perkOnPick(ModHamonSkills.DEEP_PASS)
            .musicOnPick(ModSounds.HAMON_PICK_ZEPPELI).build());

    
    public static final RegistryObject<CharacterTechniqueHamonSkill> CHEAT_DEATH = HAMON_SKILLS.registerEntry("cheat_death",
            () -> new CharacterTechniqueHamonSkill.Builder("cheat_death", RewardType.PASSIVE)
            .build());

    public static final RegistryObject<CharacterTechniqueHamonSkill> CLACKER_VOLLEY = HAMON_SKILLS.registerEntry("clacker_volley",
            () -> new CharacterTechniqueHamonSkill.Builder("clacker_volley", RewardType.ITEM)
            .requiredSkill(THROWABLES_INFUSION).build());
    
    public static final RegistryObject<CharacterTechniqueHamonSkill> ROPE_TRAP = HAMON_SKILLS.registerEntry("rope_trap",
            () -> new CharacterTechniqueHamonSkill.Builder("rope_trap", RewardType.ITEM)
            .build());

    // FIXME !!! (hamon) rebuff overdrive
    public static final RegistryObject<CharacterTechniqueHamonSkill> JOSEPH_3 = HAMON_SKILLS.registerEntry("joseph_3",
            () -> new CharacterTechniqueHamonSkill.Builder("joseph_3", RewardType.PASSIVE)
            .build());
    
    public static final RegistryObject<CharacterHamonTechnique> CHARACTER_JOSEPH = HAMON_CHARACTER_TECHNIQUES.registerEntry("joseph", 
            () -> new CharacterHamonTechnique.Builder("joseph", Util.make(new ArrayList<>(), list -> {
                list.add(ModHamonSkills.CLACKER_VOLLEY);
                list.add(ModHamonSkills.ROPE_TRAP);
                list.add(ModHamonSkills.JOSEPH_3);
            }))
            .perkOnPick(ModHamonSkills.CHEAT_DEATH)
            .musicOnPick(ModSounds.HAMON_PICK_JOSEPH).build());


    public static final RegistryObject<CharacterTechniqueHamonSkill> BUBBLE_LAUNCHER = HAMON_SKILLS.registerEntry("bubble_launcher",
            () -> new CharacterTechniqueHamonSkill.Builder("bubble_launcher", RewardType.ATTACK)
            .unlocks(ModHamon.CAESAR_BUBBLE_LAUNCHER)
            .requiredSkill(THROWABLES_INFUSION).build());
    
    public static final RegistryObject<CharacterTechniqueHamonSkill> CRIMSON_BUBBLE = HAMON_SKILLS.registerEntry("crimson_bubble",
            () -> new CharacterTechniqueHamonSkill.Builder("crimson_bubble", RewardType.PASSIVE).build());

    public static final RegistryObject<CharacterTechniqueHamonSkill> BUBBLE_BARRIER = HAMON_SKILLS.registerEntry("bubble_barrier",
            () -> new CharacterTechniqueHamonSkill.Builder("bubble_barrier", RewardType.ATTACK)
            .unlocks(ModHamon.CAESAR_BUBBLE_BARRIER)
            .requiredSkill(THROWABLES_INFUSION).build());
    
    public static final RegistryObject<CharacterTechniqueHamonSkill> BUBBLE_CUTTER = HAMON_SKILLS.registerEntry("bubble_cutter",
            () -> new CharacterTechniqueHamonSkill.Builder("bubble_cutter", RewardType.ATTACK)
            .unlocks(ModHamon.CAESAR_BUBBLE_CUTTER)
            .requiredSkill(THROWABLES_INFUSION).build());
    
    public static final RegistryObject<CharacterTechniqueHamonSkill> BUBBLE_LENSES = HAMON_SKILLS.registerEntry("bubble_lenses",
            () -> new CharacterTechniqueHamonSkill.Builder("bubble_lenses", RewardType.PASSIVE)
            .requiredSkill(BUBBLE_CUTTER).build());
    
    public static final RegistryObject<CharacterHamonTechnique> CHARACTER_CAESAR = HAMON_CHARACTER_TECHNIQUES.registerEntry("caesar", 
            () -> new CharacterHamonTechnique.Builder("caesar", Util.make(new ArrayList<>(), list -> {
                list.add(ModHamonSkills.BUBBLE_BARRIER);
                list.add(ModHamonSkills.BUBBLE_CUTTER);
                list.add(ModHamonSkills.BUBBLE_LENSES);
            }))
            .perkOnPick(ModHamonSkills.BUBBLE_LAUNCHER).perkOnPick(ModHamonSkills.CRIMSON_BUBBLE)
            .musicOnPick(ModSounds.HAMON_PICK_CAESAR).build());


    public static final RegistryObject<CharacterTechniqueHamonSkill> SATIPOROJA_SCARF = HAMON_SKILLS.registerEntry("satiporoja_scarf",
            () -> new CharacterTechniqueHamonSkill.Builder("satiporoja_scarf", RewardType.ITEM).build());
    
    public static final RegistryObject<CharacterTechniqueHamonSkill> AJA_STONE_KEEPER = HAMON_SKILLS.registerEntry("aja_stone_keeper",
            () -> new CharacterTechniqueHamonSkill.Builder("aja_stone_keeper", RewardType.ITEM)
            .build());
    
    public static final RegistryObject<CharacterTechniqueHamonSkill> SCARF_WEAPON = HAMON_SKILLS.registerEntry("scarf_weapon",
            () -> new CharacterTechniqueHamonSkill.Builder("scarf_weapon", RewardType.ITEM)
            .requiredSkill(SENDO_OVERDRIVE).build());
    
    public static final RegistryObject<CharacterTechniqueHamonSkill> SNAKE_MUFFLER = HAMON_SKILLS.registerEntry("snake_muffler",
            () -> new CharacterTechniqueHamonSkill.Builder("snake_muffler", RewardType.ITEM)
            .requiredSkill(DETECTOR).build());
    
    public static final RegistryObject<CharacterHamonTechnique> CHARACTER_LISA_LISA = HAMON_CHARACTER_TECHNIQUES.registerEntry("lisa_lisa", 
            () -> new CharacterHamonTechnique.Builder("lisa_lisa", Util.make(new ArrayList<>(), list -> {
                list.add(ModHamonSkills.SCARF_WEAPON);
                list.add(ModHamonSkills.AJA_STONE_KEEPER);
                list.add(ModHamonSkills.SNAKE_MUFFLER);
            }))
            .perkOnPick(ModHamonSkills.SATIPOROJA_SCARF)
            .musicOnPick(ModSounds.HAMON_PICK_LISA_LISA).build());
    
}
