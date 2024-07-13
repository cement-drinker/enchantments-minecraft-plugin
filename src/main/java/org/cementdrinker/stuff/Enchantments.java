package org.cementdrinker.stuff;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import net.kyori.adventure.key.Key;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;

public class Enchantments {
    public static Registry<Enchantment> enchReg = RegistryAccess.registryAccess().getRegistry(RegistryKey.ENCHANTMENT);

    public static Enchantment ABSORBENCY = enchReg.getOrThrow(TypedKey.create(RegistryKey.ENCHANTMENT, Key.key(("stuff:absorbency"))));
    public static Enchantment FROSTASPECT = enchReg.getOrThrow(TypedKey.create(RegistryKey.ENCHANTMENT, Key.key(("stuff:frost_aspect"))));
}
