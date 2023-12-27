
/*
Strategy Pattern
=================
- It defines a family of algoritms, encapsulates each one and makes them interchangeable.
Strategy lets  algorithm to vary independently from the clients that use it.

- Below is the example of strategy pattern for weapons. 
Here weapon usage for different characters is unique.

- Putting in base class will cause classes to do nothing in implementation. 
class Characters{
    
    void useKnife();
    void useAxe();
    void useSword();
    .
    .
    void useArrow();
    void fight();
}

class Queen extends Characters{
    void useKnife(){
        //use knife to fight
    }
    void fight(){
        //fight with knife
    }
    void useAxe(){
        //Not applicable so nothing to implement
    }
    void useSword(){
        //Not applicable so nothing to implement
    }
}

- Thus defining a group of weapon is better than putting each weapon type in base class.

- Hence we use strategy pattern which is better in this case as implemented below

- relationship representation

 Character  (has a relationship)  IWeapon
 
 (extends)                       (Implements)
 
Queen Knight Troll             KnifeWeapon SwordWeapon AxeWeapon

- Important Object Oriented Principles(OO principles) to learn from this pattern:-
1) Encapsulate what varies
2) Favor composition over inheritance
3) Program to interace not implementation

*/

//Weapon class group
interface IWeapon{
    
     void useWeapon(Character c);
}


class KnifeWeapon implements IWeapon{
     public void useWeapon(Character c){
         System.out.println(c.getClass().getSimpleName() +" : Using knife to attack");
     }
}

class ArrowWeapon implements IWeapon{
    public void useWeapon(Character c){
         System.out.println(c.getClass().getSimpleName() + " : Using Arrow to attack");
     }
}

class AxeWeapon implements IWeapon{
     public void useWeapon(Character c){
         System.out.println(c.getClass().getSimpleName() + " : Using Axe to attack");
     }
}

class SwordWeapon implements IWeapon{
     public void useWeapon(Character c){
         System.out.println(c.getClass().getSimpleName() +  " : Using Sword to attack");
     }
}

//Character class group
abstract  class Character{
    IWeapon wp;
    
    void setWeapon(IWeapon wp){
        this.wp = wp;
    }
    
    public abstract void fight();
    
}

class Queen extends Character{
    
    public Queen(IWeapon wp){
        setWeapon(wp);
    }
    public void fight(){
        wp.useWeapon(this);
    }
}

class Knight extends Character{
    
    public Knight(IWeapon wp){
         setWeapon(wp);
    }
    public void fight(){
        wp.useWeapon(this);
    }
}


class Troll extends Character{
    
    public Troll(IWeapon wp){
         setWeapon(wp);
    }
    public void fight(){
        wp.useWeapon(this);
    }
}


public class StrategyPattern{
    public static void main(String[] args){
    Character q1 = new Queen(new KnifeWeapon());
    Character k1 = new Knight(new SwordWeapon());
    Character t1 = new Troll(new AxeWeapon());
    
    q1.fight();
    k1.fight();
    t1.fight();
    }
}

//O/p:-
/*
Queen : Using knife to attack
Knight : Using Sword to attack
Troll : Using Axe to attack
*/
