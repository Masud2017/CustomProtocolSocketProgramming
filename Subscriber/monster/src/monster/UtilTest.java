package monster;

import java.util.ArrayList;
import java.util.List;

public class UtilTest {

    
    /** 
     * @param main
     */
    public static void main(String[] main) {
        Util util = new Util();
        
        List<Integer> rectOne = new ArrayList<>();
        rectOne.add(2);
        rectOne.add(5);
        rectOne.add(1);

        List<Integer> rectTwo = new ArrayList<>();
        rectTwo.add(2);
        rectTwo.add(5);
        rectTwo.add(1);



        List<Integer> rectThird = new ArrayList<>();
        rectThird.add(4);
        rectThird.add(6);
        rectThird.add(1);
        List<List<Integer>> intersect = util.getIntersect(rectOne, rectTwo);
        System.out.println("value of two intersection : "+intersect);

        List<List<Integer>> finalIntersect = util.getIntersectWithThirdRect(intersect, rectThird);
        System.out.println("VAlue of the data  :"+finalIntersect);
      

 
    }
}
