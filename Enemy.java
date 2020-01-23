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
     * @param description description of the enemy
     * @param health health of the enemy
     * @param attackDamage attackdamage of the enemy
     * @param attackModifier attackmodifier of the enemy. Used for randomizing the attackdamage
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

    /**
     * returns the health of the enemy.
     * @return the health of the enemy.
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * returns the attackdamage of the enemy.
     * @return the attackdamage of the enemy.
     */
    public int getAttackDamage()
    {
        return attackDamage;
    }

    /**
     * returns the attack modifier of the enemy.
     * @return the attack modifier of the enemy.
     */
    public int getAttackModifier()
    {
        return attackModifier;
    }
}
