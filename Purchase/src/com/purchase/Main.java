/**
 *
 * @author windmsvy
 * @Activity project, related information: http://blog.csdn.net/a389850155/article/details/77803339
 * Achieve functions:
 *      1) Add one activity
 *      2) Purchase
 *      3) Sort activity
 *  TODO: 11/1/17
 *      1) Multi-thread
 *      2) Make code easier to be read
 *      3) Sort list (promotion)
 *      4) Adding some exception to make program robust
 */
package com.purchase;

public class Main {
    public static void main(String[] args) {
	// write your code here
        Activity activity = new Activity();
        activity.loadInformation();
        while(activity.hasNextLine()){
            activity.operation(activity.getRequest());
        }
    }
}



