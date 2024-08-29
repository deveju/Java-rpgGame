public class Enemy {
    private int health;

    public Enemy(int health) {
        this.health = health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            System.out.println("\n# The enemy was slain. +1 Health Potion.");
        } else {
            System.out.println("The enemy has " + health + " life remaining.");
        }
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return String.format("Enemy [%d HP | 0 Damage]\n", health);
    }
}