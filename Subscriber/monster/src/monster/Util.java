package monster;

import java.util.ArrayList;
import java.util.List;

// Utility class for util methods
public class Util {
    
    /** 
     * @param str
     * @return List<String>
     */
    // this method split the response string into some chunks so that program can work with it easily
    public static List<String> getSplitedStringArray(String str) {
        String[] topicList = {
            "position",
            "guess",
            "victory"
        };
        List<String> splitedStringList = new ArrayList<String>();

        for (String topic : topicList) {
            if (str.contains("victory")) {
                List<String> victoryString = new ArrayList<>();
                victoryString.add("victory");
                
                return victoryString;
            }
            List<String> twoBigString = new ArrayList<>();
            if (str.contains(topic) == true) {
                List<String> list = new ArrayList<String>();

                splitedStringList.add(topic);
                
                String secondHalf = str.split(topic)[1];
                List<String> splitedSecondHalf = new ArrayList<>();
                if (secondHalf.length() > 3) {
                    splitedSecondHalf.add(secondHalf.split(":")[0]);
                    splitedSecondHalf.add(secondHalf.split(":")[1]);

                    splitedStringList.add(splitedSecondHalf.get(0));
                    splitedStringList.add(splitedSecondHalf.get(1));
                }
                
            }
        }

        return splitedStringList;
    }


    
    /** 
     * @param coord1
     * @param coord2
     * @return int
     */
    public int getStraightLineDistance(List<Integer> coord1, List<Integer> coord2) {
        int val = coord2.get(0) - coord1.get(1);
        int val2 = coord2.get(1) - coord1.get(1);

        int distance = (int) Math.sqrt((val*val)+(val2*val2));
        return distance;
    }

    
    /** 
     * @param distance1
     * @param distance2
     * @return int
     */
    public int aread(int distance1,int distance2) {
        return distance1 * distance2;
    }

    
    /** 
     * @param rectOne
     * @param rectTwo
     * @return List<List<Integer>>
     */
    // per list contain 3 vlaues : alphabateNumber(rowNumber) ,columnNumber, confidenceNumber
    public List<List<Integer>> getIntersect(List<Integer> rectOne,List<Integer> rectTwo) {
        Coords coordOne = new Coords(rectOne.get(0),rectOne.get(1));
        Coords coordTwo = new Coords(rectTwo.get(0),rectTwo.get(1));


        int whichIsSmallest = 0; // 1 means rectOne; 2 means rectTwo

        if (rectOne.get(2) == rectTwo.get(2)) {
            whichIsSmallest = 1; // if both are equal then use just one. In this case we used first rect
        } else if (rectOne.get(2) > rectTwo.get(2)) {
            whichIsSmallest = 2;
        } else if (rectOne.get(2) < rectTwo.get(2)) {
            whichIsSmallest = 1;
        }
        // we have determined the smallest rectangle.
        // now we need up,down ,left and right value of the rectagle for check if the smallest squar fall into the biggest squar
        List<Integer> upOne = coordOne.getUp(rectOne.get(2));
        
        List<Integer> downOne = coordOne.getDown(rectOne.get(2));
        List<Integer> rightOne = coordOne.getRight(rectOne.get(2));
        List<Integer> leftOne = coordOne.getLeft(rectOne.get(2));

        List<Integer> upTwo = coordTwo.getUp(rectTwo.get(2));
        List<Integer> downTwo = coordTwo.getDown(rectTwo.get(2));
        List<Integer> rightTwo = coordTwo.getRight(rectTwo.get(2));
        List<Integer> leftTwo = coordTwo.getLeft(rectTwo.get(2));

        // for up and down we have to think about the rows
        // for up  the bigger value will get most priority and for down the smallest squar will get priority
        // this is for up
        List<Integer> upCoord = new ArrayList<>();
        if (upOne.get(0) > upTwo.get(0)) {
            upCoord.add(upOne.get(0));
            upCoord.add(upTwo.get(1));
        } else if (upOne.get(0) < upTwo.get(0)) {
            upCoord.add(upTwo.get(0));
            upCoord.add(upOne.get(1));
        } else if (upOne.get(0) == upTwo.get(0)) {
            upCoord = upOne;
        }

        // this is for down
        List<Integer> downCoord = new ArrayList<>();
        if (downOne.get(0) > downTwo.get(0)) {
            downCoord = downTwo;
        } else if (downOne.get(0) == downTwo.get(0)){
            downCoord = downOne;
        } else if (downOne.get(0) < downTwo.get(0)) {
            downCoord = downOne;
        }

        // for left and right we have to think about the columns
        // for left the bigger number will get priority and for right the smallest number will get most priority
        // this is for left
        List<Integer> leftCoord = new ArrayList<>();

        if (leftOne.get(1) > leftTwo.get(1)) {
            leftCoord = leftOne;
        } else if (leftOne.get(1) == leftTwo.get(1)) {
            if (whichIsSmallest == 1) {
                leftCoord = leftOne;
            } else {
                leftCoord = leftTwo;
            }
            
        } else if (leftOne.get(1) < leftTwo.get(1)) {
            leftCoord = leftTwo;
        }


        // this is for right
        List<Integer> rightCoord = new ArrayList<>();
        if (rightOne.get(1) < rightTwo.get(1)) {
            rightCoord = rightOne;
        } else if (rightOne.get(1) == rightTwo.get(1)) {
            rightCoord = rightOne;
        } else if (rightOne.get(1) > rightTwo.get(1)) {
            rightCoord = rightTwo;
        }

        


        // now we have to find the diagonals (northWest, southWest,northEast,southEast)
        // this is little bit tricky
        // for northWest (upRightDiagonal)
        // System.out.println("Value of UpOne"+upOne);

        List<Integer> northWest = new ArrayList<>();
        northWest.add(upCoord.get(0));
        int sumable1 = upCoord.get(1) - rightCoord.get(1);
        northWest.add(upCoord.get(1) + Math.abs(sumable1));
        // for southWest (UpLeftDiagonal)
        List<Integer> southWest = new ArrayList<>();
        southWest.add(upCoord.get(0));
        int sumable2 = upCoord.get(1) - leftCoord.get(1);
        southWest.add(upCoord.get(1) - sumable2);
        // for northEast (downRightDiagonal)
        List<Integer> northEast = new ArrayList<>();
        northEast.add(downCoord.get(0));
        int sumable3 = Math.abs(downCoord.get(1) - rightCoord.get(1));
        northEast.add(downCoord.get(1)+sumable3);
        // for southEast (downLeftDiagonal)
        List<Integer> southEast = new ArrayList<>();
        southEast.add(downCoord.get(0));
        int sumable4 = downCoord.get(1) - leftCoord.get(1);
        southEast.add(downCoord.get(1) - sumable4);
        List<List<Integer>> interSectList = new ArrayList<>();
        
        interSectList.add(northWest);
        interSectList.add(southWest); 
        interSectList.add(northEast);
        interSectList.add(southEast);
        interSectList.add(upCoord); // up 
        interSectList.add(downCoord); // down 
        interSectList.add(leftCoord); // left
        interSectList.add(rightCoord); // right

        return interSectList;
    }

    
    /** 
     * @param new_rect
     * @param third_rect
     * @return List<List<Integer>>
     */
    public List<List<Integer>> getIntersectWithThirdRect(List<List<Integer>> new_rect,List<Integer> third_rect) {
        Coords coord = new Coords(third_rect.get(0),third_rect.get(1));
        List<Integer> upThird = coord.getUp(third_rect.get(2));
        List<Integer> downThird = coord.getDown(third_rect.get(2));
        List<Integer> leftThird = coord.getLeft(third_rect.get(2));
        List<Integer> rightThird = coord.getRight(third_rect.get(2));

        // for up and down we have to think about the rows
        // for up  the bigger value will get most priority and for down the smallest squar will get priority
        // this is for up
        List<Integer> upCoord = new ArrayList<>();
        if (upThird.get(0) > new_rect.get(4).get(0)) {
            upCoord.add(upThird.get(0));
            upCoord.add(new_rect.get(4).get(1));
        } else if (upThird.get(0) < new_rect.get(4).get(0)) {
            upCoord.add(new_rect.get(4).get(0));
            upCoord.add(upThird.get(1));
        } else if (upThird.get(0) == new_rect.get(4).get(0)) {
            upCoord = upThird;
        }

        // this is for down
        List<Integer> downCoord = new ArrayList<>();
        if (downThird.get(0) > new_rect.get(5).get(0)) {
            downCoord = new_rect.get(5);
        } else if (downThird.get(0) == new_rect.get(5).get(0)){
            downCoord = new_rect.get(5);
        } else if (downThird.get(0) < new_rect.get(5).get(0)) {
            downCoord = downThird;
        }

        // for left and right we have to think about the columns
        // for left the bigger number will get priority and for right the smallest number will get most priority
        // this is for left
        List<Integer> leftCoord = new ArrayList<>();


        List<Integer> thirdVal1 = new ArrayList<>();
        thirdVal1.add(coord.getNorthWest(third_rect.get(2)).get(0));
        thirdVal1.add(coord.getNorthWest(third_rect.get(2)).get(1));
        
        List<Integer> thirdVal2 = new ArrayList<>();
        thirdVal2.add(coord.getNorthEast(third_rect.get(2)).get(0));
        thirdVal2.add(coord.getNorthEast(third_rect.get(2)).get(1));

                
        List<Integer> thirdVal3 = new ArrayList<>();
        thirdVal3.add(coord.getSouthEast(third_rect.get(2)).get(0));
        thirdVal3.add(coord.getSouthEast(third_rect.get(2)).get(1));
        Util util = new Util();
        int thirdDistance1 = util.getStraightLineDistance(thirdVal1,thirdVal2);
        int thirdDistance2 = util.getStraightLineDistance(thirdVal3,thirdVal2);
        int areaThird = thirdDistance1 * thirdDistance2;

        List<Integer> newVal1 = new ArrayList<>();
        newVal1.add(new_rect.get(0).get(0));
        newVal1.add(new_rect.get(0).get(1));

        List<Integer> newVal2 = new ArrayList<>();
        newVal2.add(new_rect.get(2).get(0));
        newVal2.add(new_rect.get(2).get(1));

        List<Integer> newVal3 = new ArrayList<>();
        newVal3.add(new_rect.get(3).get(0));
        newVal3.add(new_rect.get(3).get(1));

        int newDistance1 = util.getStraightLineDistance(newVal1, newVal2);
        int newDistance2 = util.getStraightLineDistance(newVal3, newVal2);
        int areaNew = newDistance1 * newDistance2;
        int whichIsSmallest = 0;

        if (areaThird > areaNew) {
            whichIsSmallest = 2;
        } else if (areaThird < areaNew) {
            whichIsSmallest = 1;
        } else if (areaThird == areaNew){
            whichIsSmallest = 1;
        }


        if (leftThird.get(1) > new_rect.get(6).get(1)) {
            leftCoord = leftThird;
        } else if (leftThird.get(1) == new_rect.get(6).get(1)) {
            if (whichIsSmallest == 1) {
                leftCoord = leftThird;
            } else {
                leftCoord = new_rect.get(6);
            }
        } else if (leftThird.get(1) < new_rect.get(6).get(1)) {
            leftCoord = new_rect.get(6);
        }


        // this is for right
        List<Integer> rightCoord = new ArrayList<>();
        if (rightThird.get(1) < new_rect.get(7).get(1)) {
            rightCoord = rightThird;
        } else if (rightThird.get(1) == new_rect.get(7).get(1)) {
            rightCoord = rightThird;
        } else if (rightThird.get(1) > new_rect.get(7).get(1)) {
            rightCoord = new_rect.get(7);
        }

        // now we have to find the diagonals (northWest, southWest,northEast,southEast)
        // this is little bit tricky
        // for northWest (upRightDiagonal)
        // System.out.println("Value of UpOne"+upOne);

        List<Integer> northWest = new ArrayList<>();
        northWest.add(upCoord.get(0));
        int sumable1 = upCoord.get(1) - rightCoord.get(1);
        northWest.add(upCoord.get(1) + Math.abs(sumable1));
        // for southWest (UpLeftDiagonal)
        List<Integer> southWest = new ArrayList<>();
        southWest.add(upCoord.get(0));
        int sumable2 = upCoord.get(1) - leftCoord.get(1);
        southWest.add(upCoord.get(1) - sumable2);
        // for northEast (downRightDiagonal)
        List<Integer> northEast = new ArrayList<>();
        northEast.add(downCoord.get(0));
        int sumable3 = Math.abs(downCoord.get(1) - rightCoord.get(1));
        northEast.add(downCoord.get(1)+sumable3);
        // for southEast (downLeftDiagonal)
        List<Integer> southEast = new ArrayList<>();
        southEast.add(downCoord.get(0));
        int sumable4 = downCoord.get(1) - leftCoord.get(1);
        southEast.add(downCoord.get(1) - sumable4);
        List<List<Integer>> interSectList = new ArrayList<>();
        
        interSectList.add(northWest);
        interSectList.add(southWest); 
        interSectList.add(northEast);
        interSectList.add(southEast);



        return interSectList;
    }
}
