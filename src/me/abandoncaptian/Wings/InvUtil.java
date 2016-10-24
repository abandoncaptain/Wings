package me.abandoncaptian.Wings;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InvUtil {
	Main plugin;
	public InvUtil(Main plugin){
		this.plugin = plugin;
	}
	
	public ArrayList<ItemStack> permParticles(Player p){
		ArrayList<ItemStack> particles = new ArrayList<ItemStack>();
		if(p.hasPermission("wings.heart")){
			ItemStack item = new ItemStack(Material.BOOK, 1);
			ItemMeta iM = item.getItemMeta();
			iM.setDisplayName("Heart Particle");
			item.setItemMeta(iM);
			particles.add(item);
		}
		if(p.hasPermission("wings.flame")){
			ItemStack item = new ItemStack(Material.BOOK, 1);
			ItemMeta iM = item.getItemMeta();
			iM.setDisplayName("Flame Particle");
			item.setItemMeta(iM);
			particles.add(item);
		}
		if(p.hasPermission("wings.enchantment_table")){
			ItemStack item = new ItemStack(Material.BOOK, 1);
			ItemMeta iM = item.getItemMeta();
			iM.setDisplayName("Enchantment Table Particle");
			item.setItemMeta(iM);
			particles.add(item);
		}
		if(p.hasPermission("wings.spell")){
			ItemStack item = new ItemStack(Material.BOOK, 1);
			ItemMeta iM = item.getItemMeta();
			iM.setDisplayName("Spell Particle");
			item.setItemMeta(iM);
			particles.add(item);
		}
		if(p.hasPermission("wings.crit_magic")){
			ItemStack item = new ItemStack(Material.BOOK, 1);
			ItemMeta iM = item.getItemMeta();
			iM.setDisplayName("Magic Crit Particle");
			item.setItemMeta(iM);
			particles.add(item);
		}
		if(p.hasPermission("wings.crit")){
			ItemStack item = new ItemStack(Material.BOOK, 1);
			ItemMeta iM = item.getItemMeta();
			iM.setDisplayName("Crit Particle");
			item.setItemMeta(iM);
			particles.add(item);
		}
		if(p.hasPermission("wings.note")){
			ItemStack item = new ItemStack(Material.BOOK, 1);
			ItemMeta iM = item.getItemMeta();
			iM.setDisplayName("Note Particle");
			item.setItemMeta(iM);
			particles.add(item);
		}
		if(p.hasPermission("wings.spell_instant")){
			ItemStack item = new ItemStack(Material.BOOK, 1);
			ItemMeta iM = item.getItemMeta();
			iM.setDisplayName("Spell Instant Particle");
			item.setItemMeta(iM);
			particles.add(item);
		}
		if(p.hasPermission("wings.water_wake")){
			ItemStack item = new ItemStack(Material.BOOK, 1);
			ItemMeta iM = item.getItemMeta();
			iM.setDisplayName("Water Wake Particle");
			item.setItemMeta(iM);
			particles.add(item);
		}
		if(p.hasPermission("wings.spell_mob")){
			ItemStack item = new ItemStack(Material.BOOK, 1);
			ItemMeta iM = item.getItemMeta();
			iM.setDisplayName("Spell Mob Particle");
			item.setItemMeta(iM);
			particles.add(item);
		}
		if(p.hasPermission("wings.spell_witch")){
			ItemStack item = new ItemStack(Material.BOOK, 1);
			ItemMeta iM = item.getItemMeta();
			iM.setDisplayName("Spell Witch Particle");
			item.setItemMeta(iM);
			particles.add(item);
		}
		if(p.hasPermission("wings.spell_mob_ambient")){
			ItemStack item = new ItemStack(Material.BOOK, 1);
			ItemMeta iM = item.getItemMeta();
			iM.setDisplayName("Spell Ambient Mob Particle");
			item.setItemMeta(iM);
			particles.add(item);
		}
		if(p.hasPermission("wings.suspended_depth")){
			ItemStack item = new ItemStack(Material.BOOK, 1);
			ItemMeta iM = item.getItemMeta();
			iM.setDisplayName("Depth Particle");
			item.setItemMeta(iM);
			particles.add(item);
		}
		if(p.hasPermission("wings.villager_happy")){
			ItemStack item = new ItemStack(Material.BOOK, 1);
			ItemMeta iM = item.getItemMeta();
			iM.setDisplayName("Happy Villager Particle");
			item.setItemMeta(iM);
			particles.add(item);
		}
		if(p.hasPermission("wings.end_rod")){
			ItemStack item = new ItemStack(Material.BOOK, 1);
			ItemMeta iM = item.getItemMeta();
			iM.setDisplayName("End Rod Particle");
			item.setItemMeta(iM);
			particles.add(item);
		}
		if(p.hasPermission("wings.damage_indicator")){
			ItemStack item = new ItemStack(Material.BOOK, 1);
			ItemMeta iM = item.getItemMeta();
			iM.setDisplayName("Black Heart Particle");
			item.setItemMeta(iM);
			particles.add(item);
		}
		if(particles.size()==0){
			ItemStack item = new ItemStack(Material.BARRIER, 1);
			ItemMeta iM = item.getItemMeta();
			iM.setDisplayName("§cYou have no particles!");
			item.setItemMeta(iM);
			particles.add(item);
		}
		return particles;
	}

}
