/**
 * Stock is a class that represnting the stock in the shop with max food item of 100 items.
 * This class can add items to the stock,see how many items you have,which one is the most expsive,in wich temeratory is
 * best to keep them in the fridge,its can tell how much items you need to order,which item need to be romved because he
 * has expaird,and update stock.
 * @Leonid Mazarsky ID: 319401980
 * @28-12-2019 17:21
 */
public class Stock
{
    private FoodItem[]_stock;
    private int _noOfItems;
    private final int MAX_FOOD_ITEM=100;

    /**
     * Constructs a new stock of FoodItem class,with a max size- MAX FOOD ITEM.
     */
    public Stock()
    {
        _stock= new FoodItem[MAX_FOOD_ITEM];
        _noOfItems=0;
    }

    /**
     * Returns the number of items in the stock
     * @return The _noOfItems
     */ 
    public int getNumOfItems()
    {
        if(_noOfItems==0)
            return 0;
        return _noOfItems;

    }

    /**
     * add the item to the stock if possible and retrun True if he was able to or false if he faild.
     * @param newItem represnt an item of a FoodItem calss you want to add to the stock
     * @return true if its was possible to add the item.
     */ 
    public boolean addItem (FoodItem newItem)
    {
        //If The stock is empty add the first Item.
        if(_noOfItems==0)
        {
            _stock[0]=new FoodItem(newItem);
            _noOfItems++;
            return (true);
        }
        //Cheking if the stack is full that maybe one of the items is equal.
        if(_noOfItems == MAX_FOOD_ITEM)
        {
            for(int i =0; i<_noOfItems; i++)
            {
                if(_stock[i].equals(newItem))
                {
                    _stock[i].setQuantity(_stock[i].getQuantity()+newItem.getQuantity());
                    return (true);
                }
                return (false);
            }
        }

        else if(_noOfItems != MAX_FOOD_ITEM)
        {
            //Cheking if one of the items is equal and just adding the quantity
            for(int i =0; i<_noOfItems; i++)
            {
                if(_stock[i].equals(newItem))
                {
                    _stock[i].setQuantity(_stock[i].getQuantity()+newItem.getQuantity());
                    return (true);
                }
            }
            //Cheking if one of items are the same with diffrent days and putting the new one before.
            for(int i=0; i<_noOfItems; i++)
            {
                if(_stock[i].getName().equals(newItem.getName()) && _stock[i].getCatalogueNumber()==(newItem.getCatalogueNumber()))
                {
                    int j = _noOfItems;
                    for(; j>i ; j--)
                    {  
                        _stock[j] = new FoodItem(_stock[j-1]) ;
                        _stock[j-1] = null;
                    }
                    _stock[i] = new FoodItem(newItem);
                    _noOfItems++;
                    return (true);
                }
            }
            //cheking if the newItem catalogue nuber is bigger that some other catalogue number in the stock.
            int k = 0;
            while(k<_noOfItems && _stock[k].getCatalogueNumber()<newItem.getCatalogueNumber())
            {
                k++;
            }
            //creating an epmy space for the new item
            int j = _noOfItems;
            while(j>k)
            {
                _stock[j] = new FoodItem(_stock[j-1]) ;
                _stock[j-1] = null;
                j--;
            }
            //putting a new FoodItem in q position and adding 1 to the number of items
            _stock[k] =  new FoodItem (newItem);
            _noOfItems++;
            return true;
        }
        return (false);
    }

    /**
     * Return a string of items is lasse that amount that you need to order
     * @param amount represnt a number that every item you have tht is lasse. 
     * @return productToOrder that a string with all the item you need to order that was lasse that the amount.
     */ 
    public String order(int amount)
    {
        String productToOrder=new String();
        int i=0;
        int counter=0;
        int sumAmount=0;
        boolean flag=true;
        while(i!=_noOfItems)
        {
            sumAmount+=_stock[i].getQuantity();
            for(int k=i+1;k<_noOfItems;k++)
            {
                if(_stock[i].getCatalogueNumber()==_stock[k].getCatalogueNumber() && 
                _stock[i].getName().equals(_stock[k].getName()))
                {
                    sumAmount+=_stock[k].getQuantity();
                    counter++;

                }
            }
            //This is my answear to the "fance" problem so the print will be correct
            if(flag )
            {
                if(sumAmount<amount)
                {
                    productToOrder+=_stock[i].getName();
                    flag=false;

                }
            }
            else if(sumAmount<amount)
            {
                productToOrder+=", " +_stock[i].getName();

            }
            i+=counter+1;
            counter=0;
            sumAmount=0;
        }
        return productToOrder;
    }

    /**
     * Return how many of the items can go to the fridge in a given tempetuare.
     * @param temp represnt a number that every item you have tht is lasse. 
     * @return counter with number of products that can go to the fridge.
     */ 
    public int howMany(int temp)
    {
        int counter=0;
        if(_noOfItems==0)
            return counter;

        for(int i=0;i<_noOfItems;i++)
        {
            if(_stock[i].getMinTemperature() <= temp && _stock[i].getMaxTemperature()>=temp)
            {
                counter+=_stock[i].getQuantity();
            }

        }
        return (counter);
    }

    /**
     * Remove all the dates tath after a given date.
     * @param d  represnt the date you wish to check and remove the item that was expaird.
     */ 
    public void removeAfterDate(Date d)
    {
        //int temp=_noOfItems;
        for(int i=0;i<_noOfItems;i++)
        {
            if(_stock[i].getExpiryDate().before(d)==true)
            {
                removeFrom(_stock[i]);
                i=0;

            }
            if(_stock[i].getExpiryDate().equals(d)==true)
            {
                removeFrom(_stock[i]);
                i=0;
            }

        }

    }

    /**
     * Return the most expensive item in the stock
     * @return temp that is the most expensive food item in the stock
     */ 
    public FoodItem mostExpensive()
    {
        if(_noOfItems==0)
            return null;

        FoodItem temp=new FoodItem(_stock[0]);
        int maxPrice=temp.getPrice();
        for(int i=1;i<_noOfItems;i++)
        {
            if(_stock[i].getPrice()>maxPrice)
            {
                temp=new FoodItem(_stock[i]);
                maxPrice=_stock[i].getPrice();

            }

        }
        return new FoodItem(temp);

    }

    /**
     * Return how many of the piecess you have totall.
     * @return numOfPieces represnting all the quantity of all the pieces.
     */ 
    public int howManyPieces()
    {
        if(_noOfItems==0)
            return 0;

        int numOfPieces=0;
        for(int i=0;i<_noOfItems;i++)
        {
            numOfPieces+=_stock[i].getQuantity();
        }
        return numOfPieces;
    }

    /**
     * Return a string with all the items in the stock.
     * @return newString that a string prints all the items in the stock.
     */ 
    public String toString () 
    {
        String newString ="";
        if(_noOfItems==0)
            return (newString);

        for (int i =0; i<_noOfItems; i++)
        {
            newString+=_stock[i]+"\n";
        }
        return newString;
    }

    /**
     * Return how many of the piecess you have totall.
     * @param  itemList represent all the items you wand to remove from the stock
     */ 
    public void updateStock(String[] itemsList)
    {
        if(_noOfItems>0 && itemsList.length>0)
        {
            for(int i=0;i<itemsList.length;i++)
            {
                for(int j=0;j<_noOfItems;j++)
                {
                    if(itemsList!=null && itemsList[i].equals(_stock[j].getName()))
                    {
                        _stock[j].setQuantity(_stock[j].getQuantity()-1);
                        if(_stock[j].getQuantity()==0)
                        {
                            removeFrom(_stock[j]);
                            j=_noOfItems;

                        }
                        j=_noOfItems;
                    }

                }

            }
        }

    }

    /**
     * Return the minimum temperature for all the items, if its dosent exist will return Integer.MAX_VALUE.
     * @return  _minTemp that is the minimum temperature for the stock items
     * if its dose not find it it will retrurn Integer.MAX_VALUE
     */ 
    public int getTempOfStock()
    {
        if(_noOfItems == 0)
            return Integer.MAX_VALUE;
        int _minTemp = _stock[0].getMinTemperature();
        int _maxTemp = _stock[0].getMaxTemperature();
        for(int i=1; i<_noOfItems; i++){
            if(_stock[i].getMinTemperature() > _minTemp)
                _minTemp = _stock[i].getMinTemperature();
            if(_stock[i].getMaxTemperature() < _maxTemp)
                _maxTemp = _stock[i].getMaxTemperature();                
        }
        if(_minTemp <= _maxTemp){
            return _minTemp;
        }else{
            return Integer.MAX_VALUE;
        }
    }
    //----------------------------------------------------------------------------------------
    //--------------------------------------------------------Private Methods-----------------
    //----------------------------------------------------------------------------------------

    //remove an fooditem from the stock
    private void removeFrom(FoodItem remove)
    {
        if(_noOfItems>0)
        {
            for (int i=0; i<_noOfItems; i++) {
                if (_stock[i].equals(remove)) {
                    for (int j=i;j<_noOfItems-1;j++)
                        _stock[j] = _stock[j+1];
                    _stock[_noOfItems-1] = null;
                    _noOfItems--;

                }
            }
        }
    }
}

