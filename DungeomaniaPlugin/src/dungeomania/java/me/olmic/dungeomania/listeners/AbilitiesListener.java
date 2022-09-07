package me.olmic.dungeomania.listeners;

import me.olmic.dungeomania.Main;
import me.olmic.dungeomania.frameworks.Class;
import me.olmic.dungeomania.frameworks.Profile;
import me.olmic.dungeomania.managers.ProfileManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;

import static me.olmic.dungeomania.Utils.color;

public class AbilitiesListener implements Listener {

    private ProfileManager profileManager;
    private Main main;
    private ArrayList<Player> cooldown = new ArrayList<>();
    private ArrayList<Player> Leftcooldown = new ArrayList<>();
    private ArrayList<Player> ShiftRightcooldown = new ArrayList<>();
    private ArrayList<Material> swords = new ArrayList<>();
    private ArrayList<Material> scythes = new ArrayList<>();
    private ArrayList<Material> axes = new ArrayList<>();
    private ArrayList<Material> wands = new ArrayList<>();
    private ArrayList<Material> priest = new ArrayList<>();

    private boolean isCharged;

    public AbilitiesListener(Main main) {
        this.main = main;
        profileManager = main.getProfileManager();

        //swords
        swords.add(Material.NETHERITE_SWORD);
        swords.add(Material.DIAMOND_SWORD);
        swords.add(Material.IRON_SWORD);
        swords.add(Material.GOLDEN_SWORD);
        swords.add(Material.WOODEN_SWORD);
        swords.add(Material.STONE_SWORD);

        //scythes
        scythes.add(Material.NETHERITE_HOE);
        scythes.add(Material.DIAMOND_HOE);
        scythes.add(Material.IRON_HOE);
        scythes.add(Material.GOLDEN_HOE);
        scythes.add(Material.STONE_HOE);
        scythes.add(Material.WOODEN_HOE);

        //axes
        axes.add(Material.NETHERITE_AXE);
        axes.add(Material.DIAMOND_AXE);
        axes.add(Material.IRON_AXE);
        axes.add(Material.GOLDEN_AXE);
        axes.add(Material.STONE_AXE);
        axes.add(Material.WOODEN_AXE);

        axes.add(Material.NETHERITE_SHOVEL);
        axes.add(Material.DIAMOND_SHOVEL);
        axes.add(Material.IRON_SHOVEL);
        axes.add(Material.GOLDEN_SHOVEL);
        axes.add(Material.STONE_SHOVEL);
        axes.add(Material.WOODEN_SHOVEL);

        //wands
        wands.add(Material.STICK);
        wands.add(Material.BLAZE_ROD);
        wands.add(Material.BONE);

        //priest
        priest.add(Material.BOOK);
        priest.add(Material.LANTERN);
        priest.add(Material.SOUL_LANTERN);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Profile profile = profileManager.getProfile(player.getUniqueId());
        Class[] classes = Class.values();

        //knight
        if (profile.getClazz() == classes[4]) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (event.getItem() != null) {
                    if (swords.contains(event.getItem().getType())) {
                        if (!cooldown.contains(player)) {
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&eUsed Slash")));

                            //ability here

                            Vector playerLocation = player.getEyeLocation().toVector();
                            Vector playerViewDirection = player.getEyeLocation().getDirection().normalize();

                            for (Entity entity : player.getNearbyEntities(4,2,4)) {
                                if (!(entity instanceof Player)) {
                                    if (entity instanceof LivingEntity) {

                                        LivingEntity livingEntity = (LivingEntity) entity;
                                        Vector toTarget = livingEntity.getEyeLocation().toVector().subtract(playerLocation).normalize();
                                        double dotProduct = toTarget.dot(playerViewDirection);

                                        if (dotProduct >= 0.63) {
                                            livingEntity.damage(20);
                                            livingEntity.setVelocity(livingEntity.getLocation().toVector().subtract(player.getLocation().toVector()).multiply(0.6));
                                        }
                                    }
                                }
                            }

                            Vector playerDirection = player.getEyeLocation().getDirection().normalize();
                            playerDirection = playerDirection.multiply(1.5);
                            Location pos = player.getEyeLocation().add(playerDirection);

                            player.spawnParticle(Particle.SWEEP_ATTACK, pos, 1);

                            cooldown.add(player);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                                cooldown.remove(player);
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&eSlash off cooldown")));
                            }, 80);
                        }
                    }
                }
            }
        }

        //priest
        if (profile.getClazz() == classes[5]) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getItem() != null) {
                    if (priest.contains(event.getItem().getType())) {
                        if (!(cooldown.contains(player))) {
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&eUsed Heal")));

                            // ability here

                            for (Entity e : player.getNearbyEntities(4, 4, 4)) {
                                if (e.getType() == EntityType.PLAYER) {
                                    Player entity = (Player) e;

                                    if (entity.getHealth() + 8 > entity.getMaxHealth()) {
                                        entity.setHealth(entity.getMaxHealth());
                                    } else {
                                        entity.setHealth(entity.getHealth() + 8);
                                    }
                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                        players.spawnParticle(Particle.HEART, entity.getEyeLocation(), 4, 1, 1, 1);
                                    }
                                }
                            }

                            if (player.getHealth() + 6 > player.getMaxHealth()) {
                                player.setHealth(player.getMaxHealth());
                            } else {
                                player.setHealth(player.getHealth() + 6);
                            }

                            for (Player players : Bukkit.getOnlinePlayers()) {
                                players.spawnParticle(Particle.HEART, player.getEyeLocation(), 4, 1, 1, 1);
                            }

                            cooldown.add(player);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                                cooldown.remove(player);
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&eHeal off cooldown")));
                            }, 100);
                        }
                    }
                }
            }
        }

        //mage
        if (profile.getClazz() == classes[2]) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getItem() != null) {
                    if (wands.contains(event.getItem().getType())) {
                        if (!(cooldown.contains(player))) {
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&eUsed Fireball")));

                            Fireball fireball = player.launchProjectile(Fireball.class);
                            fireball.setShooter(player);
                            fireball.setYield(2f);

                            cooldown.add(player);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                                cooldown.remove(player);
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color(
                                        "&eFireball off cooldown")));
                            }, 100);
                        }
                    }
                }
            }
        }

        //mage
        if (profile.getClazz() == classes[2]) {
            if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                if (event.getItem() != null) {
                    if (wands.contains(event.getItem().getType())) {
                        if (!(Leftcooldown.contains(player))) {

                            for (Entity entity : player.getNearbyEntities(10,10,10)) {
                                if (!(entity instanceof Player)) {
                                    if (entity instanceof LivingEntity) {

                                        Vector playerLocation = player.getEyeLocation().toVector();
                                        Vector playerViewDirection = player.getEyeLocation().getDirection().normalize();

                                        LivingEntity livingEntity = (LivingEntity) entity;
                                        Vector toTarget = livingEntity.getEyeLocation().toVector().subtract(playerLocation).normalize();
                                        double dotProduct = toTarget.dot(playerViewDirection);

                                        if (dotProduct >= 0.99) {
                                            livingEntity.damage(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
                                        }
                                    }
                                }
                            }

                            Location origin = player.getEyeLocation();
                            Vector direction = origin.getDirection();
                            direction.multiply(10);
                            Location destination = origin.clone().add(direction);

                            direction.normalize();
                            for (int i = 0; i < 10; i++) {
                                Location loc = origin.add(direction);
                                loc.getWorld().spawnParticle(Particle.END_ROD, loc, 0, 0, 0, 0);
                            }

                            long attackCooldown = (new Double((1/player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getValue())*20).longValue());

                            Leftcooldown.add(player);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                                Leftcooldown.remove(player);

                            }, attackCooldown);
                        }
                    }
                }
            }
        }

        //archer
        if (profile.getClazz() == classes[1]) {
            if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                if (event.getItem() != null) {
                    if (event.getItem().getType() == Material.BOW) {
                        if (!(cooldown.contains(player))) {
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&eBomb used")));


                            Arrow arrow = player.launchProjectile(Arrow.class);
                            arrow.setKnockbackStrength(2);

                            cooldown.add(player);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&eBomb off cooldown")));
                                cooldown.remove(player);
                            }, 100);
                        }
                    }
                }
            }
        }



        //warlock
        if (profile.getClazz() == classes[3]) {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (!player.isSneaking()) {
                    if (event.getItem() != null) {
                        if (scythes.contains(event.getItem().getType())) {
                            if (!(cooldown.contains(player))) {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&eDash used")));

                                Vector dash = player.getLocation().getDirection().normalize();

                                player.setVelocity(dash);

                                player.getWorld().spawnParticle(Particle.SWEEP_ATTACK, player.getLocation().add(0, 0.5, 0), 10);

                                cooldown.add(player);
                                Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&eDash off cooldown")));
                                    cooldown.remove(player);
                                }, 100);
                            }
                        }
                    }
                }
            }
        }

        //warlock
        if (profile.getClazz() == classes[3]) {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if  (player.isSneaking()) {
                    if (event.getItem() != null) {
                        if (scythes.contains(event.getItem().getType())) {
                            if (!(ShiftRightcooldown.contains(player))) {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&eSoul Reap used")));

                                for (Entity entity : player.getNearbyEntities(10,10,10)) {
                                    if (!(entity instanceof Player)) {
                                        if (entity instanceof LivingEntity) {

                                            Vector playerLocation = player.getEyeLocation().toVector();
                                            Vector playerViewDirection = player.getEyeLocation().getDirection().normalize();

                                            LivingEntity livingEntity = (LivingEntity) entity;
                                            Vector toTarget = livingEntity.getEyeLocation().toVector().subtract(playerLocation).normalize();
                                            double dotProduct = toTarget.dot(playerViewDirection);

                                            if (dotProduct >= 0.5) {
                                                livingEntity.damage(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue()*2);

                                                player.getWorld().spawnParticle(Particle.SPELL_WITCH,
                                                        livingEntity.getLocation(), 15, 1.0, 1.0, 1.0);
                                            }
                                        }
                                    }
                                }

                                ShiftRightcooldown.add(player);
                                Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&eSoul Reap off cooldown")));
                                    ShiftRightcooldown.remove(player);
                                }, 160);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {

            Player player = (Player) event.getDamager();
            Entity entity = event.getEntity();
            Class[] classes = Class.values();
            Profile profile = profileManager.getProfile(player.getUniqueId());

            //warlock ability
            if (profile.getClazz() == classes[3]) {
                if (!(Leftcooldown.contains(player))) {
                    if (scythes.contains(player.getInventory().getItemInMainHand().getType())) {

                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&eUsed Soul Strike")));

                        for (Entity e : entity.getNearbyEntities(5, 5, 5)) {

                            if (e instanceof LivingEntity) {
                                if (!(e instanceof Player)) {
                                    LivingEntity entity1 = (LivingEntity) e;

                                    entity1.damage(event.getDamage());
                                    entity1.setVelocity(entity1.getLocation().toVector().subtract(entity.getLocation().toVector()).multiply(-0.3));
                                }
                            }
                        }

                        if (entity instanceof LivingEntity) {

                            LivingEntity livingEntity = (LivingEntity) entity;

                            livingEntity.damage(event.getDamage());

                            player.spawnParticle(Particle.SONIC_BOOM, livingEntity.getEyeLocation(), 1);
                        }

                        Leftcooldown.add(player);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                            Leftcooldown.remove(player);
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&eSoul Strike off cooldown")));
                        }, 100);
                    }
                }

                //warlock melee

                if (scythes.contains(player.getInventory().getItemInMainHand().getType())) {

                    for (Entity e : entity.getNearbyEntities(1.5, 1.5, 1.5)) {
                        if (e instanceof LivingEntity) {
                            if (!(e instanceof Player)) {
                                LivingEntity entity1 = (LivingEntity) e;

                                entity1.damage(event.getDamage());
                            }
                        }
                    }
                }
            }

            //warrior
            if (profile.getClazz() == classes[0]) {
                if (axes.contains(player.getInventory().getItemInMainHand().getType())) {
                    if (isCharged == true) {

                        for (Entity e : player.getNearbyEntities(10, 5, 10)) {
                            if (e instanceof LivingEntity) {
                                if (!(e instanceof Player)) {
                                    LivingEntity entity1 = (LivingEntity) e;

                                    entity1.setVelocity(entity1.getLocation().toVector().subtract(player.getLocation().toVector()).multiply(0.6));
                                }
                            }
                        }

                        if (entity instanceof LivingEntity) {
                            LivingEntity livingEntity = (LivingEntity) entity;

                            livingEntity.damage(event.getDamage()*1.5);

                            for (Player players : Bukkit.getOnlinePlayers()) {
                                players.spawnParticle(Particle.EXPLOSION_NORMAL, livingEntity.getEyeLocation(), 10);
                            }
                        }

                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&eUsed Charged attack")));

                        isCharged = false;
                    }
                }
            }

            //priest
            if (profile.getClazz() == classes[5]) {
                if (priest.contains(player.getInventory().getItemInMainHand().getType())) {
                    if (player.getHealth() + event.getDamage()*0.1 > getMaxHealth(player)) {
                        player.setHealth(getMaxHealth(player));
                    }
                    else {
                        player.setHealth(player.getHealth() + event.getDamage()*0.1);
                    }

                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if (entity instanceof LivingEntity) {
                            LivingEntity livingEntity = (LivingEntity) entity;
                            players.spawnParticle(Particle.SPELL_WITCH, livingEntity.getEyeLocation(), 20);
                        }
                    }
                }
            }

            //knight
            if (profile.getClazz() == classes[4]) {

            }
        }
    }

    private double getMaxHealth(Player player) {
        return player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
    }

    private boolean chargeCancel;

    @EventHandler
    public void onCrouch(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        Class[] classes = Class.values();
        Profile profile = profileManager.getProfile(player.getUniqueId());
        Boolean isSneak;

        if (profile.getClazz() == classes[0]) {
            if (!(player.isSneaking())) {
                if (isCharged == false) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&eCharging attack")));
                    isSneak = true;
                    Boolean finalIsSneak = isSneak;
                    chargeCancel = false;

                    Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {

                        if (finalIsSneak == true) {
                            if (isCharged == false) {
                                if (chargeCancel == false) {
                                    isCharged = true;
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&eAttack charged!")));
                                }
                            }
                        }
                    }, 40);
                }
            }

            if (player.isSneaking()) {
                isSneak = false;
                if (isCharged == false) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&cCharge Canceled")));

                    chargeCancel = true;
                }
            }
        }
    }

    @EventHandler
    public void arrowLand(ProjectileHitEvent event) {
        if (event.getEntity().getType() == EntityType.ARROW) {
            Arrow arrow = (Arrow) event.getEntity();

            if (arrow.getKnockbackStrength() == 2) {

                for (Entity e : arrow.getNearbyEntities(5, 5, 5)) {
                    if (e instanceof LivingEntity) {
                        if (!(e instanceof Player)) {
                            LivingEntity entity1 = (LivingEntity) e;

                            entity1.setVelocity(entity1.getLocation().toVector().subtract(arrow.getLocation().toVector()).multiply(0.6));
                            entity1.damage(10);
                        }
                    }
                }

                Location arrowLoc = arrow.getLocation();

                arrowLoc.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, arrowLoc, 50);
            }
        }
    }
}