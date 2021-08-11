import java.util.Scanner; 
/**
 * The Program is checking if the both triangle are the same.
 *
 * @Written By Leonid Mazarsky 
 * @version 1.0
 */
public class Congruent
{
    public static void main (String [] args)
    {
        double x11,y11,x12,y12,x13,y13; //The ***FIRST*** 3 couples of cordinate of  the triangle.
        double x21,y21,x22,y22,x23,y23;//The ***SECOND*** 3 couple of the cordinate of the triangle.
        double a1,b1,c1;//The length of 3 sides of ***FIRST*** trinagle.
        double a2,b2,c2;//The length of 3 sides of the ***SECOND*** triangle.
        double max1,min1;//This will have the max length and min length of ****First**** Triangle
        double max2,min2;//This will have the max length and min length of ****Second**** Triangle
        final double DECIMAL_PRECISION=0.00001;
        Scanner scan = new Scanner (System.in);
        System.out.println ("This program calculates if the triangle's are congruent. ");
        //--------------------***FIRST*** Triangle Cordinate----------------------------
        System.out.println ("Now enter the first triangle cordinate: ");
        System.out.println ("Please enter The First X cordiante: ");
        x11=scan.nextDouble();
        System.out.println ("Please enter The First Y cordiante: "); 
        y11=scan.nextDouble();
        System.out.println ("Please enter The Second X cordiante: ");
        x12=scan.nextDouble();
        System.out.println ("Please enter The Second Y cordiante: "); 
        y12=scan.nextDouble();
        System.out.println ("Please enter The Third X cordiante: ");
        x13=scan.nextDouble();
        System.out.println ("Please enter The Third Y cordiante: "); 
        y13=scan.nextDouble();
        //--------------------***SECOND*** Triangle Cordinate----------------------------
        System.out.println ("Now enter the second triangle cordinate: ");
        System.out.println ("Please enter The First X cordiante: ");
        x21=scan.nextDouble();
        System.out.println ("Please enter The First Y cordiante: "); 
        y21=scan.nextDouble();
        System.out.println ("Please enter The Second X cordiante: ");
        x22=scan.nextDouble();
        System.out.println ("Please enter The Second Y cordiante: "); 
        y22=scan.nextDouble();
        System.out.println ("Please enter The Third X cordiante: ");
        x23=scan.nextDouble();
        System.out.println ("Please enter The Third Y cordiante: "); 
        y23=scan.nextDouble();
        //--------------------Calculating the lengt of the sides of the ***FIRST*** Triangle------
        a1=Math.sqrt(Math.pow(x13-x11,2)+Math.pow(y13-y11,2));
        b1=Math.sqrt(Math.pow(x13-x12,2)+Math.pow(y13-y12,2));
        c1=Math.sqrt(Math.pow(x12-x11,2)+Math.pow(y12-y11,2));
        //--------------------Calculating the lengt of the sides of the ***SECOND*** Triangle------
        a2=Math.sqrt(Math.pow(x23-x21,2)+Math.pow(y23-y21,2));
        b2=Math.sqrt(Math.pow(x23-x22,2)+Math.pow(y23-y22,2));
        c2=Math.sqrt(Math.pow(x22-x21,2)+Math.pow(y22-y21,2));
        //--------The Output that the user will recive------------------------------
        System.out.println("The first triangle is: " + "("+x11+", "+y11+") " + "("+x12+", "+y12+") " + "("+x13+", "+y13+") "+".");
        System.out.println("Its lengths are: "+a1+ ", " + b1+", " +c1+".");
        System.out.println("The second triangle is: " + "("+x21+", "+y21+") " + "("+x22+", "+y22+") " + "("+x23+", "+y23+") "+".");
        System.out.println("Its lengths are: "+a2+", " + b2+", " +c2+".");
        //--------Cheking if they are the same Triangles and output the right message-------------------------------------
        if((a1==a2||a1==b2||a1==c2)&&(b1==a2 || b1==b2 || b1==c2)&&(c1==a2 || c1==b2 || c1==c2))
        {
      
            System.out.println("The triangles are congruent.");
        }
        else
        {
            System.out.println("The triangles are not congruent.");
        }

    }
}
