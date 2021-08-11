/**
 * Matrix is a class that represnt a 2D array,that represnt a picture 
 * it can rotate the 2D array clockwise and counter clockwise, give you the nagativ of the image
 * and calculate Neighbors avrage
 * @Leonid Mazarsky ID: 319401980
 * @28-12-2019 17:21
 */
public class Matrix
{
    private int[][] _matrix;
    private final int maxValue=255;
    private final int minValue=0;

    /**
     * Constructs a new Matrix coping the number of rows and colums and the value in it
     * @param array represnt the array you wish to copy
     */

    public Matrix(int[][] array)
    {
        _matrix=new int[array.length][array[0].length];
        for(int i=0;i<array.length;i++)
        {
            for(int j=0;j<array[i].length;j++)
            {
                _matrix[i][j]=array[i][j];
            }
        }

    }

    /**
     * Constructs a new Matrix with 2 giving parameters and puts zero in each cell.
     * @param size1 represnt the rows of the array
     * @param size2 represnt the colums of the array
     */
    public Matrix (int size1,int size2)
    {
        _matrix=new int[size1][size2];
        for(int i=0;i<size1;i++)
        {
            for(int j=0;j<size2;j++)
            {
                _matrix[i][j]=0;
            }
        }
    }

    /**
     * Return and prints a string of the 2D array
     * @return newString that will prind the 2D array
     * 
     */
    public String toString()
    {
        int i=0;
        int j=0;
        String newString=new String();
        for (i=0;i<_matrix.length;i++)
        {
            for (j=0;j<_matrix[0].length-1;j++)
            {
                newString+=_matrix[i][j]+"\t"; 

            }
            if(j==_matrix[0].length-1)
            {
                newString+=_matrix[i][j]; 
            }
            if(i!=_matrix.length)
            {
                newString+="\n";
            }

        }
        return newString;
    }

    /**
     * Return and prints a string of the 2D array
     * @return newMatrix that is the negative image of the matrix
     */
    public Matrix makeNegative() 
    {
        //Matrix newMatrix=new Matrix(_matrix.length,_matrix[0].length);
        int[][] newMatrix=new int[this._matrix.length][this._matrix[0].length];
        for(int i=0;i<_matrix.length;i++)
        {
            for(int j=0;j<_matrix[0].length;j++)
            {
                newMatrix[i][j]=Math.abs(maxValue-this._matrix[i][j]);
            }
        }

        return (new Matrix(newMatrix));//newMatrix;

    }

    /**
     * Return a new 2D array that contain the agrave of the cells near it.
     * @return newMatrix that is the avrage of the cells near it.
     */
    public Matrix imageFilterAverage()
    {
        int[][] newMatrix=new int[this._matrix.length][this._matrix[0].length];
        for(int i=0;i<_matrix.length;i++)
        {
            for(int j=0;j<_matrix[i].length;j++)
            {
                newMatrix[i][j]=avrageNeighbors(i,j);

            }
        }
        return (new Matrix(newMatrix));
    }

    /**
     * Return a new 2D matrix that is the rotation 90 degree clock wise of the original 2D matrix.
     * @return newMatrix that is the matrix after it was rotated clockwise
     */
    public Matrix rotateClockwise()
    {
        int[][] newMatrix;
        int row=this._matrix.length;
        int colum=this._matrix[0].length;
        if(row>colum || colum>row)
        {
            newMatrix=new int[colum][row];
            for(int i=0;i<colum;i++)
            {
                for(int j=0;j<row;j++)
                {
                    newMatrix[i][j]=this._matrix[row-j-1][i];

                }
            }

        }
        else 
        {
            newMatrix=new int[row][colum];
            for(int i=0;i<row;i++)
            {
                for(int j=0;j<colum;j++)
                {
                    newMatrix[i][j]=this._matrix[j][colum-i-1];

                }
            }
        }
        return (new Matrix(newMatrix));
    }

    /**
     * Return a new 2D matrix that is the rotation 90 degree clock wise of the original 2D matrix.
     * @return newMatrix that is the matrix after it was rotated clockwise
     */
    public Matrix rotateCounterClockwise()
    {
        int[][] newMatrix;
        int row=this._matrix.length;
        int colum=this._matrix[0].length;
        if(row>colum || colum>row)
        {
            newMatrix=new int[colum][row];
            for(int i=0;i<colum;i++)
            {
                for(int j=0;j<row;j++)
                {
                    newMatrix[i][j]=this._matrix[j][colum-i-1];
                }
            }

        }
        else
        {
            newMatrix=new int[row][colum];
            for(int i=0;i<row;i++)
            {
                for(int j=0;j<colum;j++)
                {
                    newMatrix[i][j]=this._matrix[colum-1-j][i];

                }
            }
        }

        return (new Matrix(newMatrix));

    }
    //----------------------------------------------------------------------------------------
    //--------------------------------------------------------Private Methods-----------------
    //----------------------------------------------------------------------------------------
    //calculate the avrage neigbors near the cells
    private int avrageNeighbors(int row,int colum)
    {
        int avrage=0;
        int counter=0;

        for(int i=row-1;i<=row+1;i++)
        {
            for(int j=colum-1;j<=colum+1;j++)
            {
                if(isValid(i,j))
                {
                    avrage+=this._matrix[i][j];
                    counter++;
                }
            }
        }
        if(counter>0)
        {
            avrage=avrage/counter;
        }
        return avrage;
    }
    //cheking if this a valid cell in the matrix
    private boolean isValid(int row , int colum)
    {
        if(row>=0 && row<this._matrix.length && colum>=0 && colum<this._matrix[0].length)
        {
            return (true);
        }
        return (false);
    }
}
