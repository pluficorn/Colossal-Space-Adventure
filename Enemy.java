import java.lang.Math;

/**
 * Extention Class of Actors for all enemy actors
 *
 * @author M. Kok,
 * @version 2020.01.21
 */
public class Enemy extends Actor
{
    private int health;
    private int attackDamage, attackModifier;

    /**
     * Constructor for objects of class Enemy.
     * @param name Name of the creature
     * @param description desc
     * @param health
     * @param attackDamage
     * @param randomPlusMinDmg
     */
    public Enemy(String name, String description, int health, int attackDamage, int attackModifier)
    {
        // Super provides the necessary variables to the constructor of the parent class; see Actor.class
        super(name, description);
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackModifier = attackModifier;
    }

    /**
     * Method to remove health from enemy actor.
     * @param damage the amount the health will decrease with.
     */
    public void removeHealth(int damage)
    {
        health -= damage;
    }

    public int getHealth()
    {
        return health;
    }

    public int getAttackDamage()
    {
        return attackDamage;
    }

    public int getAttackModifier()
    {
        return attackModifier;
    }
}
