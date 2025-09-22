package net.hil.hilmod.magic.mana;

import net.minecraft.server.network.ServerPlayerEntity;

public class ModMana {
    private int maxMana;
    private int currentMana;

    public ModMana(int maxMana) {
        this.maxMana = maxMana;
        this.currentMana = maxMana;
    }

    public int getMaxMana() { return maxMana; }
    public int getCurrentMana() { return currentMana; }

    public void setMaxMana(int maxMana) {
        this.maxMana = Math.max(0, maxMana);
        if (currentMana > maxMana) currentMana = maxMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = Math.min(currentMana, maxMana);
    }

    public void addMana(int amount) { setCurrentMana(currentMana + amount); }

    public void useMana(int amount, ServerPlayerEntity player) {
        if (currentMana >= amount) {
            currentMana -= amount;
            ManaRegenManager.resetRegenDelay(player);
        }
    }
}

