package com.github.standobyte.jojo.item;

import com.github.standobyte.jojo.init.power.stand.ModStandActions;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class DioBoneItem extends Item {

    public DioBoneItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
        // TODO Auto-generated constructor stub
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack item = player.getItemInHand(hand);
        IStandPower stand = IStandPower.getPlayerStandPower(player);
        boolean ws = stand.getType() == ModStandActions.STAND_WHITESNAKE.getStandType();
        if (!world.isClientSide()) {
            if (ws) {
                player.sendMessage(new StringTextComponent("What I need is my Stand: The World."), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("What lies beyond my Stand is the way forward for humanity. What I need is a trustworthy friend."), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("He must be someone who can control his desires. Someone without a lust for power, honor, wealth, or sexual gratification. He must be someone who puts the laws of God before the laws of humans. Will I, DIO, meet someone like this one day?"), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("What I need are the souls of at least 36 sinners. The souls of sinners contain incredible power."), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("What I need are 14 phrases:"), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("    Spiral staircase"), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("    Rhinoceros beetle"), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("    Desolation Row"), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("    Fig tart"), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("    Rhinoceros beetle"), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("    Via Dolorosa"), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("    Rhinoceros beetle"), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("    Singularity point"), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("    Giotto"), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("    Angel"), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("    Hydrangea"), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("    Rhinoceros beetle"), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("    Singularity point"), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("    Secret emperor"), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("I'll engrave these words onto my Stand so that I won't forget myself. What I need is courage. I must have the courage to part with my Stand. As it disintegrates, my Stand will absorb the souls of the 36 sinners. As a result, something new will be born."), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("Whatever is born will awaken. It will recognize the 14 phrases that my trusted friend will utter... I will trust my friend and become his friend."), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("Finally, what I need is an appropriate location. Go to 28 degrees 24 minutes north latitude, 80 degrees 36 minutes west longitude..."), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("Wait there for the new moon..."), Util.NIL_UUID);
                player.sendMessage(new StringTextComponent("That will be the time for heaven..."), Util.NIL_UUID);
                item.shrink(1);
                stand.clear();
                stand.givePower(ModStandActions.STAND_C_MOON.getStandType());
            }
        }
        return ws ? ActionResult.consume(item) : ActionResult.fail(item);
    }

}
