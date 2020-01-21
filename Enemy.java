import java.lang.Math;

/**
 * Extention Class of Actors for all enemy actors
 *
 * @author M. Kok,
 * @version 2020.01.21
 */
public class Enemy extends Actor
{
    int health;
    int attackDamage;
    int randomPlusMinDmg;

    /**
     * Constructor for objects of class Enemy.
     * @param name Name of the creature
     * @param description desc
     * @param health
     * @param attackDamage
     * @param randomPlusMinDmg
     */
    public Enemy(String name, String description, int health, int attackDamage, int randomPlusMinDmg)
    {
        // Super provides the necessary variables to the constructor of the parent class; see Actor.class
        super(name, description);
        this.health = health;
        this.randomPlusMinDmg = randomPlusMinDmg;
    }
    
    /**
     * Method to remove health from enemy actor.
     * @param damage the amount the health will decrease with.
     */
    public void removeHealth(int damage)
    {
        health -= damage;
    }

    /**
     * Returns an int between the given range (inclusive).
     * 
     * @param low first value.
     * @param high second value.
     * 
     * @return int between the range of low and high.
     */
    private int randomRange(int value1, int value2)
    {
        // Make sure value1 is always the lower number
        if(value1 > value2)
        {
            // Swap value1 and value2
            int tempHigh = value1;
            value1 = value2;
            value2 = tempHigh;
        }

        // Calculate a random number between the range
        return (int)(Math.random()*(value2-value1)+value1);
    }

    /**
     * Method to attack the player.
     * @param attacker Player that will be attacked.
     */
    public void attack(Player attacker)
    {
        attacker.removeHealth(attackDamage + randomRange(-randomPlusMinDmg, randomPlusMinDmg));
    }
}