package me.abandoncaptian.Wings;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin implements Listener{
	Logger myPluginLogger = Bukkit.getLogger();
	MyConfigManager manager;
	MyConfig Config;
	Player player;
	InvUtil iV;
	ParticleEffect effect;

	@Override
	public void onEnable()
	{
		myPluginLogger.info("--------------------------------");
		myPluginLogger.info("      Perk Effects Enabled");
		myPluginLogger.info("--------------------------------");
		getServer().getPluginManager().registerEvents(this, this);
		manager = new MyConfigManager(this);
		Config = manager.getNewConfig("PlayerData.yml", new String[] {"Perk Effects Player Data","Don't edit anything in this file!"});
		configSetup();
	    Bukkit.getScheduler().runTaskTimer(this, scheduling(), 0L, 1L);
	}

	@Override
	public void onDisable()
	{
		myPluginLogger.info("--------------------------------");
		myPluginLogger.info("      Perk Effects Disabled");
		myPluginLogger.info("--------------------------------");
	}

	public void configSetup() {
		if (!Config.contains("Wings")) {
			Config.set("Wings", null);
		}
		Config.saveConfig();
		Config.reloadConfig();
	}

	public void configWingSetup(Player p) {
		if (!Config.contains("Wings." + p.getName() + ".Enabled")) {
			Config.set("Wings." + p.getName() + ".Enabled", Boolean.valueOf(true));
		}
		if (!Config.contains("Wings." + p.getName() + ".Particle")) {
			Config.set("Wings." + p.getName() + ".Particle", "FLAME");
		}
		Config.saveConfig();
		Config.reloadConfig();
	}

	public void useInvGUI(Player p)
	{
		Inventory inv = Bukkit.createInventory(null, 9, "        §b§lParticle Wings GUI");
		ItemStack particle = new ItemStack(Material.BOOK, 1);
		ItemMeta partM = particle.getItemMeta();
		ItemMeta toggleM = particle.getItemMeta();
		ItemStack toggle;
		if (Config.getBoolean("Wings." + p.getName() + ".Enabled")) {
			toggle = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
			toggleM.setDisplayName("§aToggled On");
		} else {
			toggle = new ItemStack(Material.TORCH, 1);
			toggleM.setDisplayName("§cToggled Off");
		}
		partM.setDisplayName("§aSet Wing Particle");
		particle.setItemMeta(partM);
		toggle.setItemMeta(toggleM);
		inv.setItem(2, toggle);
		inv.setItem(6, particle);
		p.openInventory(inv);
	}

	public void useInvParticlePicker(Player p)
	{
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items = iV.permParticles(p);
		int size;
		if (items.size() <= 9)
			size = 9;
		else {
			size = 18;
		}
		Inventory inv = Bukkit.createInventory(null, size, "        §b§lSet Wings Particle");
		for (int i = 0; i <= items.size(); i++) {
			inv.addItem(new ItemStack[] { (ItemStack)items.get(i) });
		}
		p.openInventory(inv);
	}

	@EventHandler
	public void guiInvClick(InventoryClickEvent e)
	{
		Player p = (Player)e.getWhoClicked();
		p.sendMessage(e.getInventory().getTitle());
		if (e.getInventory().getTitle().contains("Particle Wings GUI")) {
			e.setCancelled(true);
			ItemStack clicked = e.getCurrentItem();
			if (clicked.getItemMeta().getDisplayName().contains("Toggled On")) {
				Config.set("Wings." + p.getName() + ".Enabled", Boolean.valueOf(false));
				p.closeInventory();
				useInvGUI(p);
			} else if (clicked.getItemMeta().getDisplayName().contains("Toggled Off")) {
				Config.set("Wings." + p.getName() + ".Enabled", Boolean.valueOf(true));
				p.closeInventory();
				useInvGUI(p);
			} else if (clicked.getItemMeta().getDisplayName().contains("Set Wing Particle")) {
				p.closeInventory();
				useInvParticlePicker(p);
			}
			Config.saveConfig();
			Config.reloadConfig();
			return;
		}

		if (e.getInventory().getTitle().contains("Particle Wings GUI")) {
			e.setCancelled(true);
			ItemStack clicked = e.getCurrentItem();
			if (clicked.getItemMeta().getDisplayName().equals("§cHeart Particle")) Config.set("Wings." + p.getName() + ".particle", "HEART");
			if (clicked.getItemMeta().getDisplayName().equals("§cFlame Particle")) Config.set("Wings." + p.getName() + ".particle", "FLAME");
			if (clicked.getItemMeta().getDisplayName().equals("§cEnchantment Table Particle")) Config.set("Wings." + p.getName() + ".particle", "ENCHANTMENT_TABE");
			if (clicked.getItemMeta().getDisplayName().equals("§cSpell Particle")) Config.set("Wings." + p.getName() + ".particle", "SPELL");
			if (clicked.getItemMeta().getDisplayName().equals("§cMagic Crit Particle")) Config.set("Wings." + p.getName() + ".particle", "CRIT_MAGIC");
			if (clicked.getItemMeta().getDisplayName().equals("§cCrit Particle")) Config.set("Wings." + p.getName() + ".particle", "CRIT");
			if (clicked.getItemMeta().getDisplayName().equals("§cNote Particle")) Config.set("Wings." + p.getName() + ".particle", "NOTE");
			if (clicked.getItemMeta().getDisplayName().equals("§cSpell Instant Particle")) Config.set("Wings." + p.getName() + ".particle", "SPELL_INSTANT");
			if (clicked.getItemMeta().getDisplayName().equals("§cSpell Mob Particle")) Config.set("Wings." + p.getName() + ".particle", "SPELL_MOB");
			if (clicked.getItemMeta().getDisplayName().equals("§cSpell Witch Particle")) Config.set("Wings." + p.getName() + ".particle", "SPELL_WITCH");
			if (clicked.getItemMeta().getDisplayName().equals("§cSpell Ambient Mob Particle")) Config.set("Wings." + p.getName() + ".particle", "SPELL_MOB_AMBIENT");
			if (clicked.getItemMeta().getDisplayName().equals("§cDepth Particle")) Config.set("Wings." + p.getName() + ".particle", "SUSPENDED_DEPTH");
			if (clicked.getItemMeta().getDisplayName().equals("§cHappy Villager Particle")) Config.set("Wings." + p.getName() + ".particle", "VILLAGER_HAPPY");
			if (clicked.getItemMeta().getDisplayName().equals("§cWater Splash Particle")) Config.set("Wings." + p.getName() + ".particle", "WATER_SPLASH");
			Config.saveConfig();
			Config.reloadConfig();
			p.closeInventory();
			p.sendMessage("§bWing particle is now set to " + clicked.getItemMeta().getDisplayName());
			return;
		}
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args)
	{
		if ((commandLabel.equalsIgnoreCase("wings")) && ((theSender instanceof Player))) {
			Player p = (Player)theSender;
			if (args.length == 1) {
				if ((args[0].equalsIgnoreCase("reload")) && (
						(p.hasPermission("wings.reload")) || (p.hasPermission("wings.*")))) {
					configSetup();
					configWingSetup(p);
					p.sendMessage("§aConfig Reloaded");
					return true;
				}
			} else {
				if ((args.length == 0) && ((p.hasPermission("wings.wings")  || (p.hasPermission("wings.*"))))) {
					useInvGUI(p);
					return true;
				}
				p.sendMessage("§cYou don't have permission to the wings!");
				p.sendMessage("§7Visit §b§nhttps://www.superfuntime.org/shops");
				return true;
			}
		}
		return true;
	}

	public Runnable scheduling() {
		return new BukkitRunnable(){
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()){
					if (Config.getBoolean("Wings." + p.getName() + ".Enabled")){
						if (ParticleEffect.valueOf(Config.getString("Wings." + p.getName() + ".Particle")) != null) {
							ParticleEffect part = ParticleEffect.valueOf(Config.getString("Wings." + p.getName() + ".Particle"));
							wingParticles(p, part);
						}
					}
				}
			}
		};
	}
	public void wingParticles(Player p, ParticleEffect part)
	{
		Location loc = p.getEyeLocation().subtract(0.0D, 0.3D, 0.0D);
		loc.setPitch(0.0F);
		loc.setYaw(p.getEyeLocation().getYaw());
		Vector v1 = loc.getDirection().normalize().multiply(-0.3D);
		v1.setY(0);
		loc.add(v1);
		float steps;
		if ((part == ParticleEffect.HEART) || (part == ParticleEffect.BARRIER))
			steps = 0.2F;
		else {
			steps = 0.1F;
		}
		/**
		//Option 1
		for (double i = -20.199999999999999D; i < 5.0D; i += 0.1) {
	        double x = 1.75D * (1.0D - Math.sin(i)) * Math.cos(i * 0.25D) / 2.0D;
	        double z = 2.0D * (Math.sin(i) - 1.0D) / 2.0D;
	        z *= -1.0D;
	        Vector v = new Vector(x, 0.0D, z);
	        rotateAroundAxisX(v, 2.007128715515137D);
	        rotateAroundAxisY(v, -loc.getYaw() * 0.01745329F);
			loc = loc.add(v);
			ParticleEffect.FLAME.display(0.0F, 0.0F, 0.0F, 0, 3, loc, 257.0D);
			loc = loc.subtract(v);
		}
		 */
		/**
		//Option 1
		for (double i = -20.199999999999999D; i < 5.0D; i += 0.1) {
	        double x = 1.75D * (1.0D - Math.sin(i)) * Math.cos(i * 0.5D) / 2.0D;
	        double z = 2.0D * (Math.sin(i) - 1.0D) / 2.0D;
	        z *= -1.0D;
	        Vector v = new Vector(x, 0.0D, z);
	        rotateAroundAxisX(v, 2.007128715515137D);
	        rotateAroundAxisY(v, -loc.getYaw() * 0.01745329F);
			loc = loc.add(v);
			ParticleEffect.FLAME.display(0.0F, 0.0F, 0.0F, 0, 3, loc, 257.0D);
			loc = loc.subtract(v);
	    }
		 */
		//Option 2
		for (double i = -10.0D; i < 6.2D; i += steps) {
			double var = Math.sin(i / 12.0D);
			double x = Math.sin(i) * (Math.exp(Math.cos(i)) - 2.0D * Math.cos(4.0D * i) - Math.pow(var, 5.0D)) / 2.0D;
			double z = Math.cos(i) * (Math.exp(Math.cos(i)) - 2.0D * Math.cos(4.0D * i) - Math.pow(var, 5.0D)) / 2.0D;
			Vector v = new Vector(-x, 0.0D, -z);
			rotateAroundAxisX(v, (loc.getPitch() + 90.0F) * 0.01745329F);
			rotateAroundAxisY(v, -loc.getYaw() * 0.01745329F);
			loc = loc.add(v);
			ParticleEffect.FLAME.display(0.0F, 0.0F, 0.0F, 0, 1, loc, 257.0D);
			loc = loc.subtract(v);
		}
	}

	public static final Vector rotateAroundAxisX(Vector v, double angle)
	{
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		double y = v.getY() * cos - v.getZ() * sin;
		double z = v.getY() * sin + v.getZ() * cos;
		return v.setY(y).setZ(z);
	}

	public static final Vector rotateAroundAxisY(Vector v, double angle)
	{
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		double x = v.getX() * cos + v.getZ() * sin;
		double z = v.getX() * -sin + v.getZ() * cos;
		return v.setX(x).setZ(z);
	}


}
