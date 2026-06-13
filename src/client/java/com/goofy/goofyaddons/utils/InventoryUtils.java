package com.goofy.goofyaddons.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerInput;

public class InventoryUtils {

    public enum ClickType {
        LEFT, RIGHT
    }

    public static void clickSlot(int slot, ClickType clickType, boolean shift) {
        Minecraft minecraft = Minecraft.getInstance();
        AbstractContainerMenu menu = minecraft.player.containerMenu;

        int button = clickType == ClickType.RIGHT ? 1 : 0;
        ContainerInput input = shift ? ContainerInput.QUICK_MOVE : ContainerInput.PICKUP;

        minecraft.gameMode.handleContainerInput(menu.containerId, slot, button, input, minecraft.player);
    }
}