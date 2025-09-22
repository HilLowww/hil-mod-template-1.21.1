package net.hil.hilmod.basics.item.custom;

import net.hil.hilmod.magic.Mana.ManaRegenManager;
import net.hil.hilmod.magic.Mana.ModMana;
import net.hil.hilmod.magic.Mana.PlayerManaManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SimpleMagicItem extends Item {
    private final int manaCost;

    public SimpleMagicItem(Settings settings, int manaCost) {
        super(settings);
        this.manaCost = manaCost;
    }
    public int getManaCost() {
        return manaCost;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            int cost = this.getManaCost();
            ModMana mana = PlayerManaManager.getMana(user.getUuid());
            if (mana != null && mana.getCurrentMana() >= 10) {
                mana.useMana(cost);
                ManaRegenManager.resetRegenDelay((ServerPlayerEntity) user);
                user.sendMessage(Text.literal("You cast a spell!"), true);
            } else {
                user.sendMessage(Text.literal("You cast a spell!"), true);
            }
        }
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient);
    }
}
