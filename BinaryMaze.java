import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BinaryMaze {
    private int startR, startC, endR, endC;
    private int [][] matrix;
    private boolean [][] visited;
    public static void main (String [] args) throws FileNotFoundException {
        Scanner input = new Scanner (new File("maze.dat"));
        int rows = input.nextInt();
        int col = input.nextInt();
        int cases = input.nextInt();
        input.nextLine();
        int [][] arr = new int [rows][col];
        for (int i = 0; i<rows; i++)
        {
            for (int x = 0; x<col; x++)
            {
                arr[i][x] = input.nextInt();
            }
            input.nextLine();
        }
        while (cases>0)
        {
            int startR = input.nextInt();
            int startC = input.nextInt();
            int endR = input.nextInt();
            int endC = input.nextInt();
            BinaryMaze maze = new BinaryMaze(arr, startR, startC, endR, endC);
            System.out.println(maze.totalSteps());
            cases--;
        }
    }
    public BinaryMaze (int[][] arr, int startR, int startC, int endR, int endC)
    {
        this.matrix = arr;
        this.startR = startR;
        this.startC = startC;
        this.endR = endR;
        this.endC = endC;
        visited = new boolean[matrix.length][matrix[0].length];
    }
    public int totalSteps ()
    {
        Location start = new Location(startR, startC,0);
        Location end = new Location(endR,endC,0);

        Queue <Location> Q = new LinkedList<>();
        Q.offer(start);

        while (!Q.isEmpty())
        {
            Location head = Q.poll();
            if (visited[head.x][head.y])
            {
                continue;
            }
            visited[head.x][head.y] = true;
            if (head.x == endR && head.y == endC)
            {
                return head.len;
            }
            if (head.x+1<matrix.length && matrix[head.x+1][head.y] == 1 && !visited[head.x+1][head.y] )
            {
                Q.offer(new Location(head.x+1,head.y, head.len+1 ));
            }
            if (head.x-1>=0 && matrix[head.x-1][head.y] == 1 && !visited[head.x-1][head.y] )
            {
                Q.offer(new Location(head.x-1,head.y,head.len+1 ));
            }
            if (head.y+1<matrix[0].length && matrix[head.x][head.y+1] == 1 && !visited[head.x][head.y+1] )
            {
                Q.offer(new Location(head.x,head.y+1,head.len+1));
            }
            if (head.y-1>=0 && matrix[head.x][head.y-1] == 1 && !visited[head.x][head.y-1])
            {
                Q.offer(new Location(head.x,head.y-1,head.len+1 ));
            }

        }
        return -1;
    }
}