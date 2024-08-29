import java.util.Random;
public class Game {
    private int playerHealth;
    private int playerAttackDamage;
    private int healthPotionAmount;
    private int enemiesKilled;
    Random rnd = new Random();

    public Game(int playerHealth, int playerAttackDamage, int healthPotionAmount, int enemiesKilled) {
        this.playerHealth = playerHealth;
        this.playerAttackDamage = playerAttackDamage;
        this.healthPotionAmount = healthPotionAmount;
        this.enemiesKilled = enemiesKilled;
    }

    public void newGame(int playerHealth, int playerAttackDamage, int healthPotionAmount, int enemiesKilled) {
        this.playerHealth = playerHealth;
        this.playerAttackDamage = playerAttackDamage;
        this.healthPotionAmount = healthPotionAmount;
        this.enemiesKilled = enemiesKilled;
    }

    public void attack(Enemy enemy) {
        int damageDealt = rnd.nextInt(playerAttackDamage - 2, playerAttackDamage + 3);
        System.out.println("\n# Attacked enemy, " + damageDealt + " damage dealt.");
        enemy.takeDamage(damageDealt);
    }

    public void heal() {
        if (healthPotionAmount > 0) {
            playerHealth += 25; 
            healthPotionAmount--;
            setHealthPotionAmount(healthPotionAmount);
            System.out.println("\n# Healed 25 health. Remaining potions: " + healthPotionAmount);
        } else {
            System.out.println("\n# No potions remaining!");
        }
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }

    public int getPlayerAttackDamage() {
        return playerAttackDamage;
    }

    public int getHealthPotionAmount() {
        return healthPotionAmount;
    }

    public void setHealthPotionAmount(int healthPotionAmount) {
        this.healthPotionAmount = healthPotionAmount;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public void incrementEnemiesKilled() {
        enemiesKilled++;
    }

    @Override
    public String toString() {
        return String.format("\n# Game Progress:\nPlayer [%d HP | %d-%d Damage | %d Potions | %s%s]",
        playerHealth,
        playerAttackDamage - 2, playerAttackDamage + 2,
        healthPotionAmount,
        enemiesKilled == 0 ? "" : enemiesKilled + "",
        enemiesKilled == 0 ? "No enemy slain" : enemiesKilled == 1 ? " Enemies slain" : " Enemies slain");
    }
}
