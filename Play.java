import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Play {

    private int [][]  edgeList;
    private int [] hand;
    private boolean []  visited;
    private int n, m, l;

    public static void main (String [] args) throws FileNotFoundException {
        Scanner input = new Scanner (new File("play.dat"));
        int plays = input.nextInt();
        input.nextLine();
        //first num = total dominoes
            //make a double array with this number - when traverse array start at index 1
        //
        while (plays>0)
        {
            int n = input.nextInt();
            int m = input.nextInt();
            int l = input.nextInt();
            input.nextLine();
            Play map = new Play(n,m,l,input);
            if (input.hasNextLine())
                input.nextLine();
            System.out.println(map.fallenDominoes());
            plays--;
        }
    }
    public Play (int n, int m, int l, Scanner scan)
    {
        this.n = n;
        this.m = m;
        this.l = l;

        edgeList = new int[n+1][n+1];
        hand = new int [l];
        visited = new boolean[n+1];

        for (int i = 0; i<m; i++)
        {
          int num1 = scan.nextInt();
          int num2 = scan.nextInt();
          edgeList [num1][num2]++;
          //edgeList [num2][num1]++;
          scan.nextLine();
        }
        for (int i=0; i<hand.length;i++)
        {
            int num = scan.nextInt();
            hand[i] = num;
            visited[num] = true;
            //scan.nextLine();
        }

    }

    public int fallenDominoes ()
    {
        for (int i = 0; i<hand.length;i++)
        {
            //value of the domino that is knocked by hand
            int index = hand[i];
            //make stack for that path
            Stack <Integer> S = new Stack<>();
            S.push(index);

            while (!S.isEmpty())
            {
                int val = S.peek();
                {
                    for (int x = 1; x<edgeList[val].length; x++)
                    {
                        if  (edgeList[val][x] == 1 && !visited[x])
                        {
                            S.push(x);
                            visited[x] = true;
                            break;
                        }
                        if (x == edgeList[val].length-1)
                        {
                            S.pop();
                        }
                    }
                }
            }
        }

       //find total
        int sum = 0;
        for (boolean i: visited)
        {
            if (i)
                sum++;
        }
        return sum;
    }
}
