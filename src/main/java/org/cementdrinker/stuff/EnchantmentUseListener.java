package org.cementdrinker.stuff;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import net.kyori.adventure.key.Key;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.cementdrinker.stuff.Enchantments;

public class EnchantmentUseListener implements Listener {

    // This code would make an italian proud with how spaghetti it is
    @EventHandler
    public void onTakeDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            if(((Player) event.getEntity()).getInventory().getChestplate() == null) {
                return;
            }
            ItemStack chestplate = ((Player) event.getEntity()).getInventory().getChestplate();
            if ((chestplate.containsEnchantment(Enchantments.ABSORBENCY))) {
                event.getEntity().sendMessage("" + (chestplate.getEnchantments()));
                ((Player) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 100, (((int) Math.round(event.getDamage())) / 2) + chestplate.getEnchantmentLevel(Enchantments.ABSORBENCY) / 2, false));
            }
        }

    }

    @EventHandler
    public void onGiveDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {

            if (((Player) event.getDamager()).getInventory().getChestplate() != null) {
                ItemStack chestplate = ((Player) event.getDamager()).getInventory().getChestplate();
                Damageable chestplatemeta = (Damageable) chestplate.getItemMeta();
                if ((chestplate.containsEnchantment(Enchantments.ABSORBENCY))) {
                    event.getDamager().sendMessage("Ench LVL:" + (chestplate.getEnchantmentLevel(Enchantments.ABSORBENCY)));
                    ((Player) event.getDamager()).removePotionEffect(PotionEffectType.STRENGTH);
                    chestplatemeta.setDamage(chestplatemeta.getDamage() + (5 - chestplate.getEnchantmentLevel(Enchantments.ABSORBENCY)));
                    chestplate.setItemMeta(chestplatemeta);
                }
            }

            if(((Player) event.getDamager()).getInventory().getItemInMainHand().containsEnchantment(Enchantments.FROSTASPECT)) {
                event.getEntity().setFreezeTicks(140 + ((4 + ((Player) event.getDamager()).getInventory().getItemInMainHand().getEnchantmentLevel(Enchantments.FROSTASPECT)) * 20 ));
                ((LivingEntity)event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 60, 5, true));
            }
        }
    }
}