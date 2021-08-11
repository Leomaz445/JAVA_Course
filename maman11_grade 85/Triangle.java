import java.util.Scanner; 
/**
 * The Program is Calculating the area and the perimeter of a given triangle.
 *
 * @Written By Leonid Mazarsky 
 * @version 1.0
 */
public class Triangle
{
    public static void main (String [] args)
    {

        Scanner scan = new Scanner (System.in);
        System.out.println ("This program calculates the area "
            + "and the perimeter of a given triangle. ");
        System.out.println ("Please enter the three lengths"
            + " of the triangle's sides");
        int a = scan.nextInt();//Side 1 of the triangle.
        int b = scan.nextInt();//Side 2 of the triangle.
        int c = scan.nextInt();//Side 3 of the triangle.
        double p;//Triangular circumference.
        double s;//The half of the circumference.
        double area;//Triangular area.

        /*After the user has entered the lengths of the triangle's sides 
        the next code will check if they are valid, and this is a triangle.*/

        if(a+b>c && a+c>b && b+c>a && a!=0 && b!=0 && c!=0)
        
        {
            p=a+b+c; //calculating the circumference.
            s=p/2;//half of the circumference.
            if(a>=b && b>=c)//this is spesific case for small angle triangles - using a heron formula
            {
                area=(a+(b+c))*(c-(a-b))*(c+(a-b))*(a+(b-c));
                area=Math.sqrt(area);
                area=0.25*area;
                System.out.println("The area of the triangle is: " + area);
                System.out.println("The circumference of the triangle is: " + p);
            }
            else //this is calculating the traingle area by a heron formula, not a sepesfic case.
            {

                area=s*(s-a)*(s-b)*(s-c);
                area=Math.sqrt(area);
                System.out.println("The area of the triangle is: " + area);
                System.out.println("The circumference of the triangle is: " + p);
            }
        }
        else //The output if its isent a triangle.
        {
            System.out.println("The Sides of the triangle you entered: " +a+ "\t" + 
                + b+ "\t" + c +"\t" + "is invalid");

        }

    }
}
