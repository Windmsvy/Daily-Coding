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
        Scanner scan = new Scanner(System.in);
        int m = scan.nextInt();
        int n = scan.nextInt();
        char [][] board = new char [m][n];
        int start = 0;
        for(int i = 0; i < m; i++){
            String tmp = scan.next();
            for(int j = 0; j < n; j++){
                board[i][j] = tmp.charAt(j);
                if(board[i][j] == '2') start = i * n + j;
            }
        }
        System.out.println(bfs(start,board));
    }
    private static int bfs(int start, char [][] board){
        int m = board.length;
        int n = board[0].length;
        int [] dx = {1,0,-1,0};
        int [] dy = {0,1,0,-1};
        Queue<Pair> queue = new LinkedList<>();
        int [][] infomation = new int [m * n][1025];
        queue.offer(new Pair(start,0,0));
        while(!queue.isEmpty()){
            Pair tmp = queue.poll();
            int x = tmp.loc / n;
            int y = tmp.loc % n;
            int step = tmp.step;
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                int key = tmp.key;
                if(nx < 0 || ny < 0 || nx == m || ny == n || board[nx][ny] == '0' || (board[nx][ny] <= 'Z' && board[nx][ny] >= 'A' && (key & (1 << (board[nx][ny] - 'A'))) == 0)) continue;
                else{ 
                	if(board[nx][ny] == '3') return step + 1;
	                else if(board[nx][ny] <= 'z' && board[nx][ny] >= 'a') key = key | (1 << (board[nx][ny] - 'a'));
	                if(infomation[nx * n + ny][key] == 0){
	                	//System.out.println(nx + " " + ny + " " + key + " " + step);
	                    infomation[nx * n + ny][key] = 1;
	                    queue.offer(new Pair(nx * n + ny,step + 1,key));
	                }
            	}
            }
        }
        return -1;
    }
}
class Pair{
    int loc;
    int step;
    int key;
    public Pair(int loc,int step,int key){
        this.loc = loc;
        this.step = step;
        this.key = key;
    }
}