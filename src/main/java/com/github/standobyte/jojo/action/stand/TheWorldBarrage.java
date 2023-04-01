package com.github.standobyte.jojo.action.stand;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.capability.entity.ClientPlayerUtilCapProvider;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvent;

public class TheWorldBarrage extends StandEntityMeleeBarrage {
    private final Supplier<SoundEvent> wryyyyyyyyyyy;

    public TheWorldBarrage(Builder builder, Supplier<SoundEvent> greatestHighShout) {
        super(builder);
        this.wryyyyyyyyyyy = greatestHighShout == null ? () -> null : greatestHighShout;
    }
}
