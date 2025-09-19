package net.hil.hilmod.entity;

import net.hil.hilmod.HilMod;
import net.hil.hilmod.entity.custom.MantisEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<MantisEntity> MANTIS = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(HilMod.MOD_ID, "mantis"),
            EntityType.Builder.create(MantisEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1f,2.5f).build());



    public static void registerEntities() {
        HilMod.LOGGER.info("Registering Mobs for" + HilMod.MOD_ID);
    }
}
