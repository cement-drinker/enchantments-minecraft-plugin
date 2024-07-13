package org.cementdrinker.stuff;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.keys.EnchantmentKeys;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class BootStrapperCustom implements PluginBootstrap
{
    @Override
    public void bootstrap(@NotNull BootstrapContext context) {
        // Register a new handled for the freeze lifecycle event on the enchantment registry
        LifecycleEventManager<BootstrapContext> lifecycleMGR = context.getLifecycleManager();
        lifecycleMGR.registerEventHandler(RegistryEvents.ENCHANTMENT.freeze().newHandler(event -> {
            event.registry().register(
                    // The key of the registry
                    // Plugins should use their own namespace instead of minecraft or papermc
                    TypedKey.create(RegistryKey.ENCHANTMENT, Key.key("stuff:absorbency")),
                    b -> b.description(Component.text("Absorbency")).supportedItems(event.getOrCreateTag(ItemTypeTagKeys.CHEST_ARMOR)).anvilCost(1).maxLevel(3).weight(10)
                            .minimumCost(EnchantmentRegistryEntry.EnchantmentCost.of(1, 1))
                            .maximumCost(EnchantmentRegistryEntry.EnchantmentCost.of(3, 1))
                            .activeSlots(EquipmentSlotGroup.CHEST)
            );
        }));
        lifecycleMGR.registerEventHandler(RegistryEvents.ENCHANTMENT.freeze().newHandler(event -> {
            event.registry().register(
                    // The key of the registry
                    // Plugins should use their own namespace instead of minecraft or papermc
                    TypedKey.create(RegistryKey.ENCHANTMENT, Key.key("stuff:frost_aspect")),
                    b -> b.description(Component.text("Frost Aspect")).supportedItems(event.getOrCreateTag(ItemTypeTagKeys.SWORDS)).anvilCost(3).maxLevel(2).weight(10)
                            .minimumCost(EnchantmentRegistryEntry.EnchantmentCost.of(1, 1))
                            .maximumCost(EnchantmentRegistryEntry.EnchantmentCost.of(3, 1))
                            .activeSlots(EquipmentSlotGroup.CHEST)
            );
        }));

    }
    @Override
    public @NotNull JavaPlugin createPlugin(@NotNull PluginProviderContext context) {
        return new Stuff();
    }
}
