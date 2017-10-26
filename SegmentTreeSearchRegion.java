import java.util.*;
public class SegmentTreeSearchRegion{
    public static void main(String [] args){
        //System.out.println(args.length);
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            int n = scan.nextInt();
            int time = scan.nextInt();
            SegmentTree root = new SegmentTree(1,n);
            for(int i = 1; i <= n; i++){
                root.update(i,scan.nextInt());
            }
            int first = 0, second = 0;
            for(int i = 0; i < time; i++){
                String operation = scan.next();
                first = scan.nextInt();
                second = scan.nextInt();
                if(operation.equals("U")){
                    root.update(first,second);
                }
                else{
                    System.out.println(root.query(first,second));
                }
            }
        }
    }
}
class SegmentTree{
    SegmentTree left,right;
    int start,end,max;
    public SegmentTree(int start,int end){
        this.left = null;
        this.right = null;
        this.start = start;
        this.end = end;
        this.max = Integer.MIN_VALUE;
    }
    public void update(int order,int number){
        if(start == end){
            max = number;
        }
        else{
            int mid = start + (end - start)/2;
            if(mid >= order){
                if(left == null){
                    left = new SegmentTree(start,mid);
                }
                left.update(order,number);
            }
            else{
                if(right == null){
                    right = new SegmentTree(mid + 1,end);
                }
                right.update(order,number);
            }
            if(left == null) max = right.max;
            else if(right == null) max = left.max;
            else max = Math.max(left.max,right.max);
        }
    }
    public int query(int leftV,int rightV){
        if(leftV > rightV){
            leftV ^= rightV;
            rightV ^= leftV;
            leftV ^= rightV;
        }
        if(leftV == start && rightV == end){
            return max;
        }
        int mid = start + (end - start)/2;
        if(mid >= rightV){
            return left.query(leftV,rightV);
        }
        else if(mid < leftV){
            return right.query(leftV,rightV);
        }
        else{
            return Math.max(left.query(leftV,mid),right.query(mid + 1,rightV));
        }
    }
}