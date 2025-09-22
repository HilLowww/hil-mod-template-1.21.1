package net.hil.hilmod.magic.Mana;

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

    public boolean useMana(int amount) {
        if (currentMana >= amount) {
            currentMana -= amount;
            return true;
        }
        return false;
    }
}

