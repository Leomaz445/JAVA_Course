
/***
 * This is a Polynomial class that represnt a node that can be use in a list its have a power,coefficiet and a next.
 * This class have 3 construcors that can help you build a node (set power,coefficient with or without next and copy a node)
 * It can set and get power,coefficient and a next and its can a print the node with the toString function.
 * @Leonid Mazarsky ID: 319401980
 * @05-02-2019 18:10
 */
public class PolyNode
{
    private int _power;// The Power of a polynom.
    private double _coefficient;// The coefficient of th polynom.
    private PolyNode _next; //The Next node.

    /**
     * Constructs a new node(polynom node) with a power and a coefficient and the _next value is null.
     * the power should be positive.
     * @param power - the power of the polynom.
     * @param coefficient - the coefficient of the polynom.
     * Time complexity: O(1).
     * Space complexity:O(1).
     */
    public PolyNode(int power, double coefficient)
    {
        if(power<0)//checking if the power positive if not seting defult value of zero
        {
            _power=0;
            _coefficient=0;
        }
        else
        {
            _power=power;
            _coefficient=coefficient;
        }
        _next=null;
    }

    /**
     * Constructs a new node(polynom node) with a power and a coefficient and the _next value.
     * the power should be positive.
     * @param power - the power of the polynom.
     * @param coefficient - the coefficient of the polynom.
     * @param next - the type is class and its puiting on the next node.
     * Time complexity: O(1).
     * Space complexity:O(1).
     */
    public PolyNode(int power, double coefficient,PolyNode next)
    {
        if(power<0)
        {
            _power=0;
            _coefficient=0;
        }
        else
        {
            _power=power;
            _coefficient=coefficient;
        }
        _next=next;
    }

    /**
     * This is a copy Constructs that copy the value of a giving node.
     * @param p - Represting the given node that his values will be copied.
     * Time complexity: O(1).
     * Space complexity:O(1).
     */
    public PolyNode(PolyNode p)
    {
        this._power=p._power;
        this._coefficient=p._coefficient;
        this._next=p._next;
    }

    /**
     * Returns the Power of a given node
     * @return The power.
     * Time complexity: O(1).
     * Space complexity:O(1).
     */ 
    public int getPower()
    {
        return _power;
    }

    /**
     * Returns the Coefficient of a given node
     * @return The Coefficient.
     * Time complexity: O(1).
     * Space complexity:O(1).
     */ 
    public double getCoefficient()
    {
        return _coefficient;
    }

    /**
     * Returns the next node on the list 
     * @return _next node.
     * Time complexity: O(1).
     * Space complexity:O(1).
     */ 
    public PolyNode getNext()
    {
        return _next;
    }

    /**
     * Setting the power of the given node.
     * @param power - the power of the node.
     * Time complexity: O(1).
     * Space complexity:O(1).
     * 
     */ 
    public void setPower(int power)
    {
        if(power >= 0)
        {
            this._power=power;
        }
    }

    /**
     * Setting the coefficient of the given node.
     * @param coefficient - the coefficient of the node.
     * Time complexity: O(1).
     * Space complexity:O(1).
     */ 
    public void setCoefficient( double coefficient)
    {
        this._coefficient=coefficient;
    }

    /**
     * Setting the next node on the list.
     * @param next - the next node in the list(the type is PolyNode)
     * Time complexity: O(1).
     * Space complexity:O(1).
     */ 
    public void setNext(PolyNode next)
    {
        this._next=next;
    }

    /**
     * Returns a String representation of the node
     * @return a string like (5x^2 + 6x -5 )
     *  Time complexity: O(1).
     * Space complexity:O(1).
     */ 
    public String toString()
    {

        if(this._coefficient==0)
        {
            return (""); //Print empty string
        }

        if(this._power==0)
        {
            return (Double.toString(this._coefficient));//Print only the cofficient like "5.0" 
        }

        if(this._power>1 && this._coefficient ==1)
        {
            return ("x^"+this._power);//Print in something like x^5
        }

        if(this._power>1 && this._coefficient==-1)
        {
            return("-x^"+this._power);//Print in something like -x^5
        }

        if(this._power==1 && this._coefficient>=1)
        {
            if(this._coefficient==1)
            {
                return ("x");// Print x
            }
            else
            {
                return (this._coefficient+"x");// Print 5x
            }

        }
        if(this._power==1 && this._coefficient<=-1)
        {
            if(this._coefficient==-1)
            {
                return ("-x");// Print -x
            }
            else
            {
                return ( + this._coefficient+"x");// Print -5x
            }
        }

        if(this._power>1 && ((this._coefficient > 1) ||(this._coefficient  <1)))
        { 
            return (this._coefficient+"x^"+this._power);//Print -5x^2 or 5x^2

        }

        return "";
    }
    
   
}
