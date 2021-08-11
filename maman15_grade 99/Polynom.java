
/***
 * This is a Polynom list that every node is made from the "PolyNode" calss. This class can add a new node to the list,
 * the list is arranged by Order by the power of each polynom, the first Node is the heigest Power and the last node in
 * the list is the lower power. This class can :
 * 1)Multiplay by scalar - every node on the list will be multiplay by a given number.
 * 2)Add two polynoms together.
 * 3)Multiplay to polynoms.
 * 4)Can calculate the differential of each polynom
 * 5)Print the polynom.
 * @Leonid Mazarsky ID: 319401980
 * @05-02-2019 18:10
 */
public class Polynom
{
    private PolyNode _head;//The head of the polynom the heigts power in the list

    /**
     * Constructs a new  polynom list with the _head value is null.
     * Time complexity: O(1).
     * Space complexity:O(1).
     */
    public Polynom()
    {
        _head=null;
    }

    /**
     * Constructs a new polynom list with a given node that is seted to be the head.
     * @param p - a given node that is setted to be the first (the head of the list).
     * Time complexity: O(1).
     * Space complexity:O(1).
     */
    public Polynom(PolyNode p)
    {
        _head=p;
    }

    /**
     * Return the first node on the list .
     * @return _head - the first node in the polynom (arrange by orther from highest power to lowest power).
     * Time complexity: O(1).
     * Space complexity:O(1).
     */
    public PolyNode getHead()
    {
        return _head;
    }

    /**
     * Setting a new head to the list
     * @param p - is a given node that is setted to be the first, if we already have a head it will be replaced.
     * Time complexity: O(1).
     * Space complexity:O(1).
     */
    public void setHead(PolyNode p)
    {
        if(_head==null)
            _head=p;
        else//if the head isent null we weill replase the head and put the 
        {
            PolyNode temp=_head;
            _head=p;
            p.setNext(temp);
        }

    }

    /**
     * Adding a new node to the list. the node will be added by order from highest power to lowest power.
     * @param p - the given node that the user want to add to the list.
     * @return "this" - the method will return the list after adding the node.
     * Time complexity: O(n).
     * Space complexity:O(1).
     */
    public Polynom addNode(PolyNode p)
    {
        if(_head==null)//Cheking if the ,ist is null
            _head=p;
        else
        {
            //cheking if the new node have the same power as th head and if yes will calc them
            if(p.getPower()==_head.getPower())
            {

                _head.setCoefficient(p.getCoefficient()+_head.getCoefficient());//using a private calc method.
                return this;
            }
            if(p.getPower()>_head.getPower())//if the given power is heigher that it will be the new head.
            {
                p.setNext(_head);
                _head=p;
            }

            else
            {
                PolyNode current = _head;
                PolyNode behind= new PolyNode(0,0);//svae the behind 
                //Looking to see where to put the given power
                while(current.getNext()!=null && current.getNext().getPower()>=p.getPower())
                {
                    behind=current;
                    current=current.getNext();
                }
                // If its have the same power it will add them using the calc method.
                if(current.getPower()==p.getPower() )
                {
                    if(p.getCoefficient()+current.getCoefficient()==0)//if its zero i will remove it from the list
                    {
                        behind.setNext(current.getNext());
                    }
                    else
                    {
                        current.setCoefficient(p.getCoefficient()+current.getCoefficient());
                        return this;
                    }
                }
                else//if not set the new node
                {
                    p.setNext(current.getNext());
                    current.setNext(p);
                }
            }

        }
        return this;
    }

    /**
     * Method that will multiplay a given polynom(list by a given number)
     * @param num - a number tht the user want to mutiplay the scalar by.
     * @return "this" - the method will return the list after mutiplayin the scalar
     * Time complexity: O(n).
     * Space complexity:O(1).
     */
    public Polynom multByScalar (int num)
    {
        PolyNode current=_head;
        PolyNode next=_head.getNext();
        while(current!=null)//go from the begining and multiplay each node by a given number
        {
            current.setCoefficient(current.getCoefficient()*num);
            current=current.getNext();
        }

        return this;
    }

    /**
     * Method that add two polynoms together.
     * @param other - a given polynom that the user want to add to the polynom he is using.
     * the other polynom will not be changed only the one that the method was activated for.
     * @return "this" - the method will return the list after adding both of the list.
     * Time complexity: O(1).
     * Space complexity:O(1).
     */
    public Polynom addPol(Polynom other)
    {   
        if( this._head==null && other==null) //cheking to see that is nothing is null
            return (this);
        if(this._head==null)
        {
            this.setHead(other.getHead());// if the polynom that we using is null add the other polynom like not change
            return this;
        }
        if(other==null)
        {
            return this;
        }

        addPol(this._head,other.getHead());//sending the head of the both list to the private method.
        return this;
    }

    /**
     * Method will multiplay both polynoms.
     * @param other - a given polynom that the user want to multiplay to the polynom he is using.
     * the other polynom will not be changed only the one that the method was activated for.
     * @return "this" - the method will return the list after mutiplayin the scalar
     * Time complexity: O(n*m).
     * Space complexity:O(n*m).
     */

    public Polynom multPol(Polynom other)
    {
        //Cheking if the lists are null
        if(this==null)
            return this;
        if(other==null || other.getHead()==null)
            return this;
        if(other.getHead().getCoefficient()==0)//if you multiplay be zero return zero
        {
            PolyNode newPolyNode=new PolyNode(0,0);
            this._head=newPolyNode;
            return this;

        }

        PolyNode tempThis=this.getHead();
        PolyNode tempOther=other.getHead();
        PolyNode newPolyNode=null;
        Polynom multList=new Polynom();//creting a new list that we will put the mutiplications of the both polynom.
        //using a varibles to save time and call the function get and set one time only.
        int tempThisPower=0;
        double tempThisCoefficient=0;
        while(tempThis!=null)
        {
            tempThisPower=tempThis.getPower();
            tempThisCoefficient=tempThis.getCoefficient();

            while(tempOther!=null)
            {
                newPolyNode=new PolyNode(tempThisPower+tempOther.getPower(),tempThisCoefficient*tempOther.getCoefficient());

                multList.addNode(newPolyNode);//puting the new node that created by multiplication.
                tempOther=tempOther.getNext();

            }
            tempThis=tempThis.getNext();
            tempOther=other.getHead();

        }
        this._head=multList.getHead();//this _head becomething the new list that we multiplayed .
        return this;
    }

    /**
     * Method that calculating the differential of the polynom.
     * @return "this" - the method will return the list after calculating the differential.
     * Time complexity: O(n).
     * Space complexity:O(1).
     */
    public Polynom differential()
    {
        PolyNode current=this.getHead();
        while(current.getNext()!=null)
        {
            current.setCoefficient(current.getCoefficient()*current.getPower());
            current.setPower(current.getPower()-1);

            current=current.getNext();
        }
        //cheking the last node to see what type and power he is.
        if(current.getPower()>=1)
        {
            current.setCoefficient(current.getCoefficient()*current.getPower());
            current.setPower(current.getPower()-1);
        }
        else
        {
            current.setCoefficient(0);
        }
        return this;
    }

    /**
     * Method that print all the nodes like a full polynom
     * @return newString - the full polynom concting all the nodes in the string
     * Time complexity: O(n).
     * Space complexity:O(1).
     */
    public String toString()
    {
        PolyNode temp=_head;
        String newString="";
        if(temp==null)
            return newString;

        while(temp!=null)
        {

            if(temp.getCoefficient()==0)//making sure not to print node is zero
            {
                temp=temp.getNext();
            }

            else 
            {
                if(temp.getCoefficient()>0 && newString.length()>0)
                {
                    newString+="+";
                }

                newString+=temp.toString();
                temp=temp.getNext();
            }
        }
        return newString;
    }
    //-----------------------------------------------------------------------Private Methods------------------------------>
    /**
     * Private method that adding two list in recursion.
     * @param current - the head of the list that the method is actived on.
     * @param other - the head of the other list that needed to be add
     * @return current - the method will return the list after adding the two polynoms.
     * Time complexity: O(n).
     * Space complexity:O(n).
     */
    private PolyNode addPol(PolyNode current,PolyNode other)
    {

        if(current ==null) return other;
        if(other==null) return current;
        //if its the same power add the coefficients together.
        if(current.getPower()==other.getPower())
        {
            current.setCoefficient(current.getCoefficient()+other.getCoefficient());
            current.setNext(addPol(current.getNext(),other.getNext()));
            return current;

        }
        //if the power is bigger go to next node on the list
        if(current.getPower()>other.getPower())
        {
            current.setNext(addPol(current.getNext(),other));
            return current;
        }
        //if the power is smaller add it before
        if(current.getPower()<other.getPower())
        {
            PolyNode newPolyNode=new PolyNode(other.getPower(),other.getCoefficient());
            //if its the biggest set as the head.
            if(current==this.getHead())
            {
                setHead(newPolyNode);
                newPolyNode.setNext(addPol(current,other.getNext()));
                return newPolyNode;
            }
            else
            {
                newPolyNode.setNext(addPol(current,other.getNext()));
                return newPolyNode;
            }
        }
        return current;
    }

}