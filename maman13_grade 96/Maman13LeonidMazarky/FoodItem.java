/**
 * Food Item is a class that represnting each Food by given him a name,catalogue number,date experation, date of production min and max temperature 
 *and the price of the item.
 * @Leonid Mazarsky ID: 319401980
 * @12-12-2019 17:21
 */
public class FoodItem
{
    private  String _name;
    private  long _catalogueNumber;
    private int _quantity;
    private Date _productionDate;
    private Date _expiryDate;
    private  int _minTemperature;
    private  int _maxTemperature;
    private int _price;
    private String DEFULT_NAME="item";
    private long DEFULT_CATALOGUE_NUM=9999;
    private int CATALOGUE_NUMBER_MIN=1000;
    private int CATALOGUE_NUMBER_MAX=9999;
    private int DEFULT_QUANTITY=0;
    private int DEFUL_PRICE=0;
    /**
     * Constructs a new FoodItem with the specified name,catlogue number,quantity,date of production, date of expiration, min temperture , max temperature and the price
     * If one of the characteristic is wrong constructs a defult value
     * @param name The name the product.
     * @param catalogueNumber The catalogue Number of the product.
     * @param quantity The quantity of the product.
     * @param productionDate The production Date of the product made from Date class.
     * @param expiryDate The expiry Date of the product made from Date Class.
     * @param minTemperature The minumum temperature of the product.
     * @param maxTemerature The maximum temperature of the product.
     * @param price The Price of the product.
     */
    public FoodItem(String name, long catalogueNumber,int quantity,Date productionDate, Date expiryDate,int minTemperature, int maxTemperature, int price)
    {
        //Cheking if the string is empty
        if(name==null || name.length()==0)
        {
            _name=DEFULT_NAME;
        }
        else
        {
            _name=name;
        }
        //Cheking if the catalogue number is a posative number with 4 digits
        if(catalogueNumber>=CATALOGUE_NUMBER_MIN && catalogueNumber<=CATALOGUE_NUMBER_MAX)
        {
            _catalogueNumber=catalogueNumber;
        }
        else
        {
            _catalogueNumber=DEFULT_CATALOGUE_NUM;
        }
        //Cheking if the quantity is positve or 0
        if(quantity<=DEFULT_QUANTITY)
        {
            _quantity=DEFULT_QUANTITY;
        }
        else
        {
            _quantity=quantity;
        }
        //Cheking if the quantity is positve or 0
        if(price>DEFUL_PRICE)
        {
             _price=price;
        }
        else
        {
           _price=DEFUL_PRICE;
        }
        //Cheking if the minTemperature is begger that maxTemperature and changing them if not or equal live them 
        if(minTemperature>maxTemperature)
        {
            int temp;//Will use to save the min value not to overwrite it
            temp=maxTemperature;
            _maxTemperature=minTemperature;
            _minTemperature=temp;

        }
        else
        {
            _minTemperature=minTemperature;
            _maxTemperature=maxTemperature;
        }
        //Cheking if the expiry date is before production date .
        if(expiryDate.before(productionDate))
        {
            _expiryDate=new Date(productionDate.tomorrow());
            _productionDate=new Date(productionDate);

        }
        else
        {
            _expiryDate=new Date(expiryDate);
            _productionDate=new Date(productionDate);
        }

    }

    /**
     * Constructs a new FoodItem equivalent to the given FoodItem object
     * @param other The FoodItem object to be copied
     */
    public FoodItem(FoodItem other)
    {

        _name=other._name;
        _catalogueNumber=other._catalogueNumber;
        _quantity=other._quantity;
        _productionDate=new Date(other._productionDate);
        _expiryDate=new Date(other._expiryDate);
        _minTemperature=other._minTemperature;
        _maxTemperature=other._maxTemperature;
        _price=other._price;

    }
    // ------------------------------------------------------------------------------------------ Getters/Setters----------------------------------------
    /**
     * Returns the catalogueNumber of the object
     * @return The catalogueNumber
     */ 
    public long getCatalogueNumber()
    {
        return(_catalogueNumber);
    }

    /**
     * Returns the name of the object
     * @return The name
     */ 
    public String getName()
    {
        return(_name);
    }

    /**
     * Returns the quantity  of the object
     * @return The quantity
     */ 
    public int getQuantity()
    {
        return(_quantity);
    }

    /**
     * Returns the production Date  of the object
     * @return The productionDate
     */ 
    public Date getProductionDate()
    {
        return(new Date(_productionDate));
    }

    /**
     * Returns the expiration Date  of the object
     * @return The expiryDate
     */ 
    public Date getExpiryDate()
    {
        return(new Date(_expiryDate));
    }

    /**
     * Returns the minimum temperature of the object
     * @return The minTemperature
     */ 
    public int getMinTemperature()
    {
        return(_minTemperature);
    }

    /**
     * Returns the maximum temperature of the object
     * @return The maxTemperature
     */ 
    public int getMaxTemperature()
    {
        return(_maxTemperature);
    }

    /**
     * Returns the price  of the object
     * @return The price
     */ 
    public int getPrice()
    {
        return(_price);
    }

    /**
     * Sets the quantity of the object (only if the given day is a valid)
     * @param i The new quantity you want to set
     */ 
    public void setQuantity(int i)
    {
        if(i>=0)
            _quantity=i;

    }

    /**
     * Sets the production Date of the object (only if the given date is a valid)
     * @param d the new production Date you want to set
     */ 
    public void setProductionDate(Date d)
    {
        if(!(d.after(_expiryDate )))
            _productionDate=new Date(d);

    }

    /**
     * Sets the expiration Date of the object (only if the given date is a valid)
     * @param d the new expiration Date you want to set
     */ 
    public void setExpiryDate(Date d)
    {
        if(!(d.before(_productionDate)))
            _expiryDate=new Date (d);

    }

    /**
     * Sets the price  of the object 
     * @param n the new price you want to set
     */ 
    public void setPrice(int n)
    {
        if(n>0)
            _price=n;
    }
    // ---------------------------------------------------------End of Getters/Setters----------------------------------------
    // -------------------------------------------------------------- Methods ------------------------------------------------
    /**
     * Compares this object to the specified object.
     * The result is true if and only if the argument of both objects represent the same product beside the quantity.
     * @param other The given FoodItem object
     * @return true if the given object has the represent the same FoodItem as this object, false otherwise
     */
    public boolean equals(FoodItem other)
    {
        {
            if(_name.equals(other._name) && _catalogueNumber==other._catalogueNumber &&  _minTemperature==other._minTemperature &&
            _maxTemperature==other._maxTemperature && _price==other._price && (_productionDate.equals(other._productionDate))
            && (_expiryDate.equals(other._expiryDate)))
                return true;

            return false;

        }

    }

    /**
     * Cheking if the product is fresh by giving a date and cheking if its in the range including the same day
     * The result is true if and only if the range of the productionDate and expiryDate includeing the same day
     * @param d The given Date
     * @return true if  the date is the range of the productionDate and expiryDate includeing the same day
     */
    public boolean isFresh(Date d)
    {
        if(d.after(_productionDate) && d.before(_expiryDate))
            return (true);
        if(d.equals(_productionDate) ||d.equals(_expiryDate))
            return (true);

        return (false);

    }

    /**
     * Returns a String representation of the  FoodItem object
     * @return The String representation of the date looking like this FoodItem: item   CatalogueNumber: 9999   ProductionDate: 1/1/2001 ExpiryDate: 1/1/2001 Quantity: 0
     */ 
    public String toString()
    {
        return ("FoodItem: "+_name+"\t"+"CatalogueNumber: "+_catalogueNumber+"\t" + "ProductionDate: "+_productionDate+"\t"+ 
            "ExpiryDate: "+_expiryDate+"\t" +"Quantity: "+_quantity);
    }

    /**
     * Cheking if the giving FoodItem production date is before the production date of the FoodItem
     * The result is true if the FoodItem production date is before.
     * @param other The given FoodItem object
     * @return true if the FoodItem production date is before, false otherwise
     */
    public boolean olderFoodItem(FoodItem other)
    {
        if(other._productionDate.after(_productionDate))
            return (true);

        return (false);
    }

    /**
     * Cheking how many items you can by of the product givin a spesific amount of money
     * @param amount The amount of giving money.
     * @return num how many items of the product you can buy
     */
    public int howManyItems(int amount)
    {
        int stockPrice=_price*_quantity;//this is the price to buy all the items
        int num=0;//this contains the number of item you can by
        if(_price>amount)
        {
            return (num);
        }
        if(stockPrice<=amount)
        {
            return (_quantity);
        }
        if(stockPrice>amount)
        {
            num=amount/_price;
        }

        return num;
    }

    /**
     * Cheking if the givin FoodItem is cheaper that the FoodItem is work for
     * Returns if the product is cheaper or not
     * @param other The given FoodItem object
     * @return True if cheaper of False if not
     */
    public boolean isCheaper(FoodItem other)
    {
        if(other._price>_price)
            return (true);

        return (false);
    }

}