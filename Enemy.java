import java.lang.Math;

/**
 * Write a description of class Ally here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private int health;
    private int attackDamage, attackModifier;

    /**
     * Constructor for objects of class Ally
     */
    public Enemy(String name, String description, int health, int attackDamage, int attackModifier)
    {
        // Super provides the necessary variables to the constructor of the parent class; see Actor.class
        super(name, description);
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackModifier = attackModifier;
    }

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
    public void terminate()
    {
    
    }
}