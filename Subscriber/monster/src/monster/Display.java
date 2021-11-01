package monster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Display {
    private static char[] arrayAlphabate = {'A','B','C','D','E','F','G','H','I','J'};
    private Map<Character,Integer> mapAlphabetWithNumber = new HashMap<Character,Integer>();


    public Display() {
        this.mapAlphabetWithNumber.put(arrayAlphabate[0], 0);
        this.mapAlphabetWithNumber.put(arrayAlphabate[1], 1);
        this.mapAlphabetWithNumber.put(arrayAlphabate[2], 2);
        this.mapAlphabetWithNumber.put(arrayAlphabate[3], 3);
        this.mapAlphabetWithNumber.put(arrayAlphabate[4], 4);
        this.mapAlphabetWithNumber.put(arrayAlphabate[5], 5);
        this.mapAlphabetWithNumber.put(arrayAlphabate[6], 6);
        this.mapAlphabetWithNumber.put(arrayAlphabate[7], 7);
        this.mapAlphabetWithNumber.put(arrayAlphabate[8], 8);
        this.mapAlphabetWithNumber.put(arrayAlphabate[9], 9);
    }

    
    /** 
     * @param positions
     * @return List<List<Integer>>
     */
    private List<List<Integer>> getCoord(List<StringBuilder> positions) {
        List<String> positionList = new ArrayList<String>();
        
        for (StringBuilder position : positions) {
            positionList.add(position.toString());
        }

        List<List<Integer>> list = new ArrayList<>();
        
        for (String position : positionList) {
            List<Integer> localList = new ArrayList<>();

            if (position.contains("OK")) {
                continue;
            }

            List<String> splitedArrayList =  Util.getSplitedStringArray(position); // Getting the splitedStringArray from the string
            if (splitedArrayList.get(0).equalsIgnoreCase("victory")) {
                List<Integer> ll = new ArrayList<Integer>();
                ll.add(null);
                list.add(ll);
                return list;
            }
          

            localList.add(this.mapAlphabetWithNumber.get(splitedArrayList.get(1).charAt(1))); // this is row vlaue of the grid
            String ch = ""+splitedArrayList.get(1).charAt(2);
            localList.add(Integer.parseInt(ch)); // this is the column value of the grid where the monster is placed


            String ch2 = ""+splitedArrayList.get(2).charAt(0);
            localList.add(Integer.parseInt(ch2)); // this the confidence value of the senesor
            list.add(localList);
        }
        return list;
    }


    
    /** 
     * @param position
     * @return int
     */
    public int play(List<StringBuilder> position) {
        List<List<Integer>> positionList= getCoord(position);
        List<List<Integer>> intersect = new ArrayList<>();
        List<List<Integer>> finalIntersect = new ArrayList<>();


        Util util = new Util();

        if (positionList.size() == 2) {
            intersect = util.getIntersect(positionList.get(0), positionList.get(1));
            
        } else if (positionList.size() == 3) {
            intersect = util.getIntersect(positionList.get(0), positionList.get(1));
            finalIntersect = util.getIntersectWithThirdRect(intersect, positionList.get(2));
            intersect = finalIntersect;
            
        }
        

        if (positionList.isEmpty()) {
            return 1;
        }

        
        if (positionList.get(0).get(0) == null) {
            return 2021;
        }
        System.out.println("Welcome to the Monster Hunting Game!");
        System.out.println("The monster is situated somewhere on the tiles marked by a cross");

        int control = 0;

        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                System.out.printf("\t");
                for (int j = 0; j < 10; j++) {
                    System.out.printf("%d\t",j+1);
                }
             }
            System.out.printf("\n");
            System.out.printf("%c\t",arrayAlphabate[i]);
		    for (int j = 1; j < 10; j++) {

		        if (j == intersect.get(1).get(1)){
                    if (i < intersect.get(1).get(0)) {
		                continue;
		            }
		            if (i > intersect.get(3).get(0)) {
		                continue; // stopoing the x cordinate to populate more
		            }
		            
		            System.out.printf("%s\t","|X|");
		            for (int kilo = intersect.get(1).get(1); kilo < intersect.get(0).get(1); kilo++) {
		                
		                
		                
		                System.out.printf("%s\t","|X|");    
		                
		                
		               control++;
		            }
		          
		           j = (j + control);
		          
		            
		            
		        } else {
		            
		            System.out.printf("%s\t","| |");
		        }

		    }
		    System.out.println("\n");
		    for(int j = 0; j < 10; j++) {
		        System.out.printf("\t%s","---");
		    }
		    
		    System.out.println("\n");
		}
        
        return 0;
    }

    
}
