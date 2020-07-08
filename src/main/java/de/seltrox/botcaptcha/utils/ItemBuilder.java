package de.seltrox.botcaptcha.utils;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemBuilder {

    private ItemStack itemStack;

    public ItemBuilder(Material material) {
        itemStack = new ItemStack(material);
    }

    public ItemBuilder(Material material, byte subId) {
        itemStack = new ItemStack(material, 1, subId);
    }

    public ItemBuilder setDisplayName(String displayName) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(displayName);
        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(ArrayList<String> lore) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addLore(String... lore) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(new ArrayList<>(Arrays.asList(lore)));
        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(String lore) {
        ItemMeta meta = itemStack.getItemMeta();
        ArrayList<String> list = new ArrayList<>();
        list.add(lore);
        meta.setLore(list);
        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setHead(String skullOwner) {
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        meta.setOwner(skullOwner);
        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.addEnchant(enchantment, level, false);
        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setColor(Color color) {
        if (itemStack.getType() == Material.BANNER) {
            BannerMeta bannerMeta = (BannerMeta) itemStack.getItemMeta();
            bannerMeta.setBaseColor(DyeColor.getByColor(color));
            itemStack.setItemMeta(bannerMeta);
        } else {
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
            leatherArmorMeta.setColor(color);
            itemStack.setItemMeta(leatherArmorMeta);
        }
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.spigot().setUnbreakable(unbreakable);
        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag itemFlag) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.addItemFlags(itemFlag);
        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemStack build() {
        return itemStack;
    }

}