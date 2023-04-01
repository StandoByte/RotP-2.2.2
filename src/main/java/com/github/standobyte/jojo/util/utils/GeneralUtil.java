package com.github.standobyte.jojo.util.utils;

import java.util.function.Predicate;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class GeneralUtil {

    public static ItemStack findInInventory(IInventory inventory, Predicate<ItemStack> itemMatches) {
        int size = inventory.getContainerSize();
        for (int i = 0; i < size; i++) {
            ItemStack item = inventory.getItem(i);
            if (itemMatches.test(item)) {
                return item;
            }
        }
        
        return ItemStack.EMPTY;
    }
}
