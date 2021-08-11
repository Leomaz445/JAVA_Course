
/***
 * This is a class name Ex14 with 4 Methods:
 * 1)SubStrC (String c , char c) - Medthod that get a string and a char counts and returns how many substring you have 
 * in the given string with a given c.
 * 2)subStrMaxC(String s, char c, int k) - Method that get 3 parametrs: a Sring, a char and k that represnt the maximum number
 * of time that char can be inside the given string as a substring that start and ends with that char.
 * 3)zeroDistance(int []a) -  Method that take an array and couting the distance from zero of each cell in the array.
 * 4)isTrans(String s, String t) -  Methond that take two strings  and compering them to each other to find if the went
 * a transformation.
 * 5)countPaths(int mat[][]) - Method that getting an 2D array and cotuing the number of paths from the first cell to 
 * the last cell.
 * @Leonid Mazarsky ID: 319401980
 * @31-01-2019 16:46
 */

public class Ex14
{
    /**
     * Medthod that get a string and a char count and returns how many substring you have 
     * in the given string with a given c.
     * @param s represting a given substring.
     * @param c represting a given char.
     * @return countCharC the number of  substrings or 0 if he didnt find any substring.
     * Time complexity: O(n).
     * Space complexity:O(1).
     */ 

    public static int subStrC( String s, char c)
    {
        int countCharC =-2;// We start from -2 because each char c after 2 will be one Substrig.

        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)==c)//If we found the char we were looking for we will add 1.
            {
                countCharC++;
            }
        }

        return(Math.max(countCharC,0));//if we will find only less that 2 chars it will return 0.
    }

    /**
     * Methods that get 3 parametrs: a Sring, a char and k that represnt the maximum number
     * of time that char can be inside the given string as a substring that start and ends with that char.
     * @param s represting a given substring.
     * @param c represting a given char.
     * @param k represting a max number of times that a given char can be inside the string.
     * @return counterSubString the number substring with max k times of the c char.
     * Time complexity: O(n).
     * Space complexity:O(1).
     */ 
    public static int subStrMaxC(String s, char c, int k)
    {
        int counterSubString = 0;// counting the substring
        int countCharC = 0;//counting the Char

        //going and looking for char 'c' in the string
        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) == c)
            {
                //if the number of char c is bigger or equal adding k+1 chars
                if(k+1<=countCharC)
                {
                    counterSubString+=k+1;
                }
                //adding a substring from the second c he finds
                else
                {
                    counterSubString+=countCharC;
                }
                countCharC++;

            }
        }	
        return counterSubString;
    }

    /**
     * Method that take an array and couting the distance from zero of each cell in the array.
     * @param a represting an array
     * Time complexity: O(n).
     * Space complexity:O(1).
     */ 
    public static void zeroDistance(int []a)
    {
        int counter=-1;
        //Going from left to right
        for(int i=0;i<a.length;i++)
        {
            //if you found zero put zero and start the counter
            if(a[i]==0)
            {
                counter=0;
            }
            else
            {
                //if you didnt find zero and need to put "fake" numbers use Integer.MAX_VALUE
                if (counter == -1) 
                    a[i] = Integer.MAX_VALUE;
                else
                {
                    //Counting plase to the next zero
                    counter++;
                    a[i]=counter;
                }
            }

        }

        //Now lets go from rigt to left
        counter=-1;
        for(int i=a.length-1;i>=0;i--)
        {
            if(a[i]==0)
            {
                counter=0;
            }
            //if the number in the cell is bigger than ++ counter( first adding 1 ) then put it in the place
            else if (counter != -1 && a[i] > ++counter)
            {
                a[i] = counter;   
            }

        }
    }

    /**
     * Methond that take two strings  and compering them to each other to find if the went
     * a transformation.
     * @param s represting the original string
     * @param t represting a string that may have been transformed.
     * @retrun True if the t string went a transformation or false if its not.
     */ 
    public static boolean isTrans(String s, String t)
    {
        return isTrans(s,t,0,0);
    }
    //----Private Method: That retrun if the to string were transformed.
    private static boolean isTrans(String s, String t,int indexS,int indexT)
    {

        if (t.length() < s.length()) //if the original string is bigger that the transfored retrun false.
            return false;

        if(t.length()-1>=indexT+1 && s.length()-1>=indexS+1) // continue to go the untill we got the end of the strings
        {

            //cheking if the char are the same and the following char ot t string not the same
            if(s.charAt(indexS) == t.charAt(indexT)&& t.charAt(indexT)!=t.charAt(indexT+1))
            {
                return(isTrans(s,t,indexS+1,indexT+1));
            }
            //cheking if the char are the same and the following char ot t string is the same
            if((s.charAt(indexS) == t.charAt(indexT)) && (t.charAt(indexT)==t.charAt(indexT+1)))
            {
                return((isTrans(s,t,indexS,indexT+1)));
            }
            //chekinf if the char in the s string and t string are not the same
            if(s.charAt(indexS)!=t.charAt(indexT))
            {
                return((isTrans(s,t,indexS+1,indexT)));

            }
        }
        else 
        {
            //chek in recursion if char are equal
            if(s.charAt(indexS)==t.charAt(indexT))
            {
                return true;
            }
            else
            {
                return false;
            }

        }
        return false;

    }

    /**
     * Method that getting an 2D array and cotuing the number of paths from the first cell to 
     * the last cell. the method using a private method countPaths.
     * @param mat represting the 2D array
     * @retrun number of paths that have been found.
     */ 
    public static int countPaths(int mat[][])
    {
        return countPaths(mat,0,0);
    }
  //----Private Method: That counts the number of paths.
    private static int countPaths(int mat[][],int i,int j)
    {
        if(i==mat.length-1 && j==mat[i].length-1) // If we found a path return 1
            return 1;
        if(i<0 || i>=mat.length || j<0 || j>=mat[i].length)//if we went out of the border return 0
            return 0;

        return countPaths(mat,i+mat[i][j]/10,j+mat[i][j]%10) +
        countPaths(mat,i+mat[i][j]%10,j+mat[i][j]/10);

    }
}

