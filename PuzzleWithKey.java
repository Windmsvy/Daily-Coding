/*
 *
 *  Windmsvy, Oct 27
 *	Puzzle with Key and Gate
 *	In a m * n puzzle, there is exist wall(0),road(1),key(lowercase),gate(uppercase),start(2),exit(3)
 *	Find the shortest path for start to exit
 *	Number of key : <= 10
 *	Using bfs, and using mem[loc][key] to justify whether it is checked before to simplify
 *
 */


import java.util.*;
public class PuzzleWithKey{
    public static void main(String [] args){
         public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int m = scan.nextInt();
        int n = scan.nextInt();
        char [][] board = new char [m][n];
        for(int i = 0; i < m; i++){
            board[i] = scan.next().toCharArray();
        }
        int start = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == '2'){
                    start = i * n + j;
                    break;
                }
            }
        }
        System.out.println(bfs(start,board));
    }
    private static int bfs(int start,char [][] board){
        int m = board.length;
        int n = board[0].length;
        int [][] choice = new int [m * n][1025];
        int [] dx = {0,1,0,-1};
        int [] dy = {1,0,-1,0};
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.offer(new Pair(start,0,0));
        while(!queue.isEmpty()){
            Pair tmp = queue.poll();
            int x = tmp.loc / n;
            int y = tmp.loc % n;
            int count = tmp.count;
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                int key = tmp.key;
                if(nx < 0 || ny < 0 || nx == m || ny == n || board[nx][ny] == '0') continue;
                else if(board[nx][ny] == '3') return count + 1;
                else if(board[nx][ny] >= 'A' && board[nx][ny] <= 'Z' && (key & (1 << (board[nx][ny] - 'A'))) == 0) continue;
                else if(board[nx][ny] >= 'a' && board[nx][ny] <= 'z') key = (key | (1 << (board[nx][ny] - 'a')));
                if(choice[nx * n + ny][key] == 0){
                    choice[nx * n + ny][key] = 1;
                    queue.offer(new Pair(nx * n + ny,count + 1,key));
                }
            }
        }
        return -1;
    }
}
class Pair{
    int loc,count,key;
    public Pair(int loc,int count,int key){
        this.loc = loc;
        this.count = count;
        this.key = key;
    }
}