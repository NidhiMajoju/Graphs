public class Location {
    int x;
    int y;
    int len;
    public Location (int r, int c)
    {
        x = r;
        y = c;
        len = 0;
    }
    public Location (int r, int c, int len)
    {
        x = r;
        y = c;
        this.len = len;
    }
}