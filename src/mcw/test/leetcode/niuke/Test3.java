package mcw.test.leetcode.niuke;


import mcw.test.common.Point;

/**
 * @author mcw 2019\11\27 0027-21:44
 *
 * 对于给定的 n 个位于同一二维平面上的点，求最多能有多少个点位于同一直线上
 */
public class Test3 {

    public static int maxPoints(Point[] points){
        if(points==null||points.length<3)
            return points.length;
        int flag=0;
        for (int i = 1; i <points.length ; i++) {
            int count=0;
            long a=points[i].x;
            long b=points[i].y;
            long dx=a-points[i-1].x;
            long dy=b-points[i-1].y;
            if(dx==0&&dy==0){
                for (int j = 0; j <points.length ; j++) {
                    if(points[i].x==a&&points[j].y==b){
                        count++;
                    }
                }
            }else{
                for (int j = 0; j <points.length ; j++) {
                    if ((points[j].x-a)*dx==(points[j].y-b)*dy){
                        count++;
                    }
                }

            }
            flag=Math.max(flag,count);
        }
        return flag;
    }

    public static void main(String[] args) {
        Point a=new Point(10,10);
        Point b=new Point(10,10);
        Point c=new Point(20,20);
        Point d=new Point(1,1);
        Point e=new Point(12,11);
        Point f=new Point(10,5);
        Point g=new Point(1,45);
        Point h=new Point(1,10);
        Point i=new Point(2,10);
        Point[] points={a,b,c,d,e,f,g,h,i};

        int num = maxPoints(points);
        System.out.println("最多有"+num+"点在同一条直线上");


    }
}

