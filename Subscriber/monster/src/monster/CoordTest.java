package monster;

public class CoordTest {
    static Coords coord = new Coords(2,7);
    
    
    /** 
     * @param arg
     */
    public static void main(String[] arg) {
        // coord.setXy(5);
        // coord.setXy(3);

        System.out.println("Value of up : "+coord.getUp(1));
        System.out.println("Value of down : "+coord.getDown(1));
        System.out.println("Value of left : "+coord.getLeft(1));
        System.out.println("Value of right : "+coord.getRight(1));
        System.out.println("Value of northWest : "+coord.getNorthWest(1));
        System.out.println("Value of southWest : "+coord.getSouthWest(1));
        System.out.println("Value of northEast : "+coord.getNorthEast(1));
        System.out.println("Value of southEast : "+coord.getSouthEast(1));

    }


}
