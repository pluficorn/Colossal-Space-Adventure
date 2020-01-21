import java.lang.Math;

/**
 * Write a description of class Ally here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    int health;
    int attackDamage;
    int randomPlusMinDmg;

    /**
     * Constructor for objects of class Ally
     */
    public Enemy(String name, String description, int health, int attackDamage, int randomPlusMinDmg)
    {
        // Super provides the necessary variables to the constructor of the parent class; see Actor.class
        super(name, description);
        this.health = health;
        this.randomPlusMinDmg = randomPlusMinDmg;
    }

    public void removeHealth(int damage)
    {
        health -= damage;
    }

    /**
     * Returns an int between the given range (inclusive)
     * 
     * @param low first value
     * @param high second value
     * @return int between the range of low and high
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

    public void attack(Player attacker)
    {
        attacker.removeHealth(attackDamage + randomRange(-randomPlusMinDmg, randomPlusMinDmg));
    }
}