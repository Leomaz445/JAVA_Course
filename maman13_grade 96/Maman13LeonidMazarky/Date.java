
/**
 * Using a class to represent a given date in the Gregorian Calendar
 * @Leonid Mazarsky ID: 319401980
 * @12-12-2019 17:21
 */
public class Date {
    private int _day;// can be from 1 to 31
    private int _month;// can be from 1 to 12
    private int _year;// 4 positive digits
    private final int DEFULT_DAY = 1;
    private final int DEFULT_MONTH = 1;
    private final int FEBRUARY=2;
    private final int DEFULT_YEAR = 2000;
    private final int MIN_DAY=1,MIN_MONTH=1;//Minimum day and Minimum month number
    private final int MIN_MONTH_DAYS=30;//The 30 days is Minimum month days number
    private final int MAX_MONTH_DAYS=31;//The 31 days is the largest month (maximum)
    private final int MAX_FEBRUARY_DAYS=29;//In a leap year this is the maximum days in february
    private final int MIN_FEBRUARY_DAYS=28;//Regular february days
    private final int MAX_MONTH=12;//you have 12 month in a year
    private final int MAX_YEAR=9999;//maximum year
    private final int MIN_YEAR=1000;//minimum year

    /**
     * Constructs a new Date with the specified day,month and year
     * If the date invalid - constructs a default date
     * @param day The day.
     * @param month The month.
     * @param year The year.
     */
    public Date(int day, int month, int year)
    {
        if(validDate(day,month,year)==true)
        {
            _day = day;
            _month = month;
            _year = year;
        }
        else //Illegal date
        {         
            _day = DEFULT_DAY;
            _month = DEFULT_MONTH;
            _year = DEFULT_YEAR;
        }
    }

    /**
     * Constructs a new Date equivalent to the given Date object
     * @param other The Date object to be copied
     */
    public Date(Date other)
    {
        if (other != null)
        {
            _day = other._day;
            _month = other._month;
            _year = other._year;
        } 
        else //Illegal date
        {
            _day = DEFULT_DAY;
            _month = DEFULT_MONTH;
            _year = DEFULT_YEAR;

        }
    }

    // ------------------------------------------------------------------------------------ Getters/Setters--------------------
    /**
     * Returns the day of the object
     * @return The day
     */ 
    public int getDay()
    {
        return (_day);
    }

    /**
     * Returns the month of the object
     * @return The month
     */ 
    public int getMonth()
    {
        return (_month);
    }

    /**
     * Returns the year of the object
     * @return The year
     */ 
    public int getYear()
    {
        return (_year);
    }

    /**
     * Sets the day of the object (only if the given day is a valid)
     * @param dayToSet The day you want to set
     */ 
    public void setDay(int dayToSet)
    {
        if (validDate(dayToSet,_month,_year))
            _day = dayToSet;
    }

    /**
     * Sets the month of the object (only if the given Month is a valid)
     * @param monthToSet The month you want to set
     */ 
    public void setMonth(int monthToSet)
    {
        if (validDate(_day,monthToSet,_year))
            _month = monthToSet;
    }

    /**
     * Sets the year of the object (only if the given year is a valid)
     * @param yearToSet The year you want to set
     */ 
    public void setYear(int yearToSet) 
    {
        if (validDate(_day,_month,yearToSet))
            _year = yearToSet;
    }
    //--------------------------------------------------------------------------End of Getter/Setters-------------------------
    //---------------------------------------------------------------------------------  Methods------------------------------
    /**
     * Compares this object to the specified object.
     * The result is true if and only if the argument is not null and both objects represent the same date.
     * @param other The given date object
     * @return true if the given object has the represent the same date as this object, false otherwise
     */
    public boolean equals(Date other)
    {
        if (other == null || validDate(other._day,other._month,other._year)==false )
            return false;
        return (_day == other._day && _month == other._month && _year == other._year);
    }

    /**
     * Returns a String representation of the  date object
     * @return The String representation of the date looking like this 00/00/0000
     */ 
    public String toString() {
        return ((_day < 10 ? "0" + _day : _day) + "/" + (_month < 10 ? "0" + _month : _month) + "/" + _year);
    }

    /**
     * Returns the date difference in days between this object and a give date object
     * If the given object is null - return the difference in days
     * @param other A Date object
     * @return date difference in days - always positive number
     */ 
    public int difference(Date other)
    {
        if (other == null || validDate(other._day,other._month,other._year)==false )
        {
            return calculateDate(_day, _month, _year);
        }

        int date1;
        int date2;
        date1 = calculateDate(_day, _month, _year);
        date2 = calculateDate(other._day, other._month, other._year);
        return Math.abs(date1 - date2);

    }

    /**
     * Compares this object to the specified object.
     * The result is true if and only if date of this object is earlier than the date of the given object.
     * @param other The given date object
     * @return true if the date of this object is earlier than the date of the given object, false otherwise
     */
    public boolean before(Date other)
    {
        if (other == null || validDate(other._day,other._month,other._year)==false )
            return false;
        if(other.equals(this)) 
            return (false);
        if (_year < other._year)
            return (true);
        if (_year == other._year && _month < other._month)
            return (true);
        if (_year == other._year && _month == other._month && _day < other._day)
            return (true);
        return (false);
    }

    /**
     * Compares this object to the specified object.
     * The result is true if and only if date of this object is later than the date of the given object.
     * @param other The given time object
     * @return true if the date of this object is later than the date of the given object, false otherwise
     */
    public boolean after(Date other)
    {
        if (other == null || validDate(other._day,other._month,other._year)==false )
            return (false);
        if (this.before(other)==false && other.before(this)==false)
            return (false);
        if (this.before(other)==false)
            return (true);
        return (false);
    }

    /**
     * Returning the day name by counting them like this: 0 Saturday,1 Sunday,2 Monday...etc untill 6 is Friday
     * The result is true if and only if date of this object is valid
     * @return day - The Day number
     */
    public int dayInWeek()
    {
        int day;
        int d=this._day;
        int m=this._month;
        int y=this._year;
        int c;
        if(!validDate(this._day,this._month,this._year))
        {
            day=-1;
        }
        else
        {   //checking if the month is february
            if(m==2)
            {
                m=14;
                y--;
            }
            //checking if the month is jenuary
            if(m==1)
            {
                m=13;
                y--;
            }
            c=y/100;
            y=y%100;
            day = (d + (26*(m+1))/10 + y + y/4 + c/4 - 2*c);
            day=Math.floorMod(day,7);

        }
        return day;

    }

    /**
     * taking exicting date and creating a tommorow day(adding 1 day to the givin day)
     * The result is true if and only if date of this object is valid
     * @return nextday date 
     */
    public Date tomorrow()
    {
        //Creting tommorow
        int newDay=_day+1;
        Date DefultDay=new Date(DEFULT_DAY,DEFULT_MONTH,DEFULT_YEAR);
        //Cheking if the Date is valid
        if(validDate(this._day,this._month,this._year)==false)
            return(DefultDay);
        //Creating a Date object
        Date nextDay=new Date(this._day,this._month,this._year);
        //Cheking if this is a last day of the month.
        if(numOfDaysInMonth(_day,_month,_year)<=newDay)
        {
            if(this._month==MAX_MONTH)
            {
                nextDay._day=DEFULT_DAY;
                nextDay._month=DEFULT_MONTH;
                //Cheking if this year>MAX_YEAR)
                if(this._year>=MAX_YEAR)
                {
                    nextDay._year=DEFULT_YEAR;
                }
                else
                {
                    nextDay._year=this._year+1;
                }
                return (nextDay);
            }
            if(this._month==FEBRUARY)
            {
                if(newDay>MAX_FEBRUARY_DAYS)
                {
                    nextDay._day=DEFULT_DAY;
                    nextDay._month=this._month;
                    nextDay._year=this._year;
                }
                else if(newDay==MIN_FEBRUARY_DAYS || newDay<MAX_FEBRUARY_DAYS)
                {
                    nextDay._day=this._day+1;
                    nextDay._month=this._month;
                    nextDay._year=this._year;
                }
            }

            else if(this._month!=MAX_MONTH)
            {
                nextDay._day=DEFULT_DAY;
                nextDay._month=this._month+1;
                return (nextDay);
            }
        }

        nextDay._day=this._day+1;
        nextDay._month=this._month;
        nextDay._year=this._year;

        return (nextDay);

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------Private Methods---------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    //Calculate the days from the begining of the calender to the given day,month year
    private int calculateDate(int day, int month, int year)
    {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62);
    }

    //Checking if the year is leap year - If the month February have 29 or 28 days
    //Return true if its a leap year and Return False if its not.
    private boolean leapYear(int year)
    {
        if (year%4==0 && year%100!=0)
            return true;
        else if(year%400==0)
            return true;

        return false;
    } 

    //Cheking if the date is valid
    //return true if its valid or false if its not.
    private boolean validDate(int day,int month,int year)
    {
        int numberofDaysInMonth=0;
        int flag=-1;//invaled date
        //Chiking if the year is valid in not return false
        if(year>=MIN_YEAR && year<=MAX_YEAR)
        {
            //Cheking if the year is leapyear return True if its is or false if not.
            boolean leapyear=leapYear(year);
            //Cheking if the Months are valid months
            if(month>=MIN_MONTH && month<=MAX_MONTH)
            {
                //Saving the number of the days in the month
                numberofDaysInMonth=numOfDaysInMonth(day,month,year);
                //chekinf if the number of days in a month is can be exicting
                if(numberofDaysInMonth==flag)
                    return (false);
                //Cheking if the day valuse is possible to be by compering it to the given month
                switch (month)
                {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12: 
                    if(day>=MIN_DAY && day<=numberofDaysInMonth)
                        return (true);
                    break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                    if(day>=MIN_DAY && day<=numberofDaysInMonth)
                        return (true);
                    break;
                    case 2:
                    {
                        if(leapyear==true)
                        {

                            if(day>=MIN_DAY &&numberofDaysInMonth<=MAX_FEBRUARY_DAYS)
                                return true;
                        }
                        else if(day>=MIN_DAY &&numberofDaysInMonth<=MIN_FEBRUARY_DAYS)
                            return true;
                    }
                    break;

                }
            }

        }
        return (false);
    }

    //Cheking if its a leap year and the number of Days in the month.
    //returning the number of days in a month
    private int numOfDaysInMonth(int day,int month,int year)
    {
        //Seting a numDays as -1 if the days dont match the month.
        int numDays=-1;
        //cheking if its a leapyear.
        boolean leapyear=leapYear(year);
        //cheking wich month is and how many days there is.
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: 
            if(day>=MIN_DAY &&day<=MAX_MONTH_DAYS)
                numDays = MAX_MONTH_DAYS;
            break;
            case 4:
            case 6:
            case 9:
            case 11:
            if(day>=MIN_DAY &&day<=MIN_MONTH_DAYS)
                numDays = MIN_MONTH_DAYS;
            break;
            case 2:
            {
                if(leapyear==true)
                {
                    if(day>=MIN_DAY &&day<=MAX_FEBRUARY_DAYS)
                        numDays=MAX_FEBRUARY_DAYS;
                }
                else if(day>=MIN_DAY &&day<=MIN_FEBRUARY_DAYS)
                    numDays=MIN_FEBRUARY_DAYS;
                break;
            }
        }
        return numDays;

    }

}