import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Scooby
{


    private int [][]  edgeList;
    private boolean []  visited;
    private int start;
    private int end;


    public static void main (String [] args) throws FileNotFoundException {
        Scanner input = new Scanner (new File("scooby.dat"));
        int paths = input.nextInt();
        input.nextLine();
        while (paths>0)
        {
            String edges = input.nextLine();
            String path = input.nextLine();
            Scooby map = new Scooby(edges,path);
            if (map.findPath())
                System.out.println("yes");
            else
                System.out.println("no");
            paths--;
        }
    }

    public  Scooby (String edges, String path) {
        edgeList = new int [26][26];
        visited = new boolean[26];
        this.start = (int)path.charAt(0)-65;
        this.end = (int)path.charAt(1)-65;
        for (int i = 0; i<edges.length()-1; i+=3)
        {
            if (edges.charAt(i) == ' ')
            {
                continue;
            }
            int s1 = ((int) edges.charAt(i))- 65;
            int s2 = ((int) edges.charAt(i+1)) - 65;
            edgeList [s1][s2]++;
            edgeList [s2][s1]++;
        }
    }
    public boolean findPath ()
    {
        if (edgeList[start][end] == 1)
        {
            return true;
        }
        Stack <Integer> S = new Stack<>();
        S.push(start);
        visited[start] = true;

        while (!S.isEmpty())
        {
            int val = S.peek();
            if (val == end)
                return true;
            for (int i = 0; i<edgeList[val].length; i++)
            {
                if  (edgeList[val][i] == 1 && !visited[i])
                {
                    S.push(i);
                    visited[i] = true;
                    break;
                }
                if (i == edgeList[val].length-1)
                {
                    S.pop();
                }
            }
        }
        return false;

    }

}
