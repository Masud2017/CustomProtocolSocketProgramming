package monster;

import java.util.ArrayList;
import java.util.List;

public class Coords {
    
    private Integer Xx; // X = alphabate and x = number of the column
    private Integer Xy;

    public Coords (Integer Xx, Integer Xy) {
        this.Xx = Xx;
        this.Xy = Xy;
    }

    public Coords () {
        
    }

    
    /** 
     * @param Xx
     */
    public void setXx(Integer Xx) {
        this.Xx = Xx;
    }

    
    /** 
     * @return Integer
     */
    public Integer getXx() {
        return this.Xx;
    }

    
    /** 
     * @param Xy
     */
    public void setXy(Integer Xy) {
        this.Xy = Xy;
    }

    
    /** 
     * @return Integer
     */
    public Integer getXy() {
        return this.Xy;
    }

    /**
     * @return
     * 
     */
    public List<Integer> getUp(int confidenceValue) {
        int up = this.Xx - confidenceValue;
        List<Integer> upList = new ArrayList<Integer>();
        upList.add(up);
        upList.add(this.Xy);

        return upList;
    }

    
    /** 
     * @param confidenceValue
     * @return List<Integer>
     */
    public List<Integer> getDown(int confidenceValue) {
        int down = this.Xx+confidenceValue;
        List<Integer> downList = new ArrayList<Integer>();
        downList.add(down);
        downList.add(this.Xy);
        
        return downList;
    }

    
    /** 
     * @param confidenceValue
     * @return List<Integer>
     */
    public List<Integer> getRight(int confidenceValue) {
        int right = this.Xy + confidenceValue;
        List<Integer> downList = new ArrayList<Integer>();

        downList.add(this.Xx);
        downList.add(right);
        
        return downList;
    }

    
    /** 
     * @param confidenceValue
     * @return List<Integer>
     */
    public List<Integer> getLeft(int confidenceValue) {
        int left = this.Xy - confidenceValue;
        List<Integer> leftList = new ArrayList<>();

        leftList.add(this.Xx);
        leftList.add(left);

        return leftList;
    }

    
    /** 
     * @param confidenceValue
     * @return List<Integer>
     */
    public List<Integer> getNorthWest(int confidenceValue) {
        List<Integer> upList = this.getUp(confidenceValue);
        int northWest = upList.get(1) + confidenceValue;
        
        List<Integer> northWestList = new ArrayList<>();
        northWestList.add(upList.get(0));
        northWestList.add(northWest);
        
        return northWestList;
    }

    
    /** 
     * @param confidenceValue
     * @return List<Integer>
     */
    public List<Integer> getSouthWest(int confidenceValue) {
        List<Integer> upList = this.getUp(confidenceValue);
        int southWest = upList.get(1) - confidenceValue;
        
        List<Integer> southWestList = new ArrayList<>();
        southWestList.add(upList.get(0));
        southWestList.add(southWest);

        return southWestList;
    }

    
    /** 
     * @param confidenceValue
     * @return List<Integer>
     */
    public List<Integer> getNorthEast(int confidenceValue) {
        List<Integer> downList = this.getDown(confidenceValue);
        int northEast = downList.get(1) + confidenceValue;

        List<Integer> southEasList = new ArrayList<>();
        southEasList.add(downList.get(0));
        southEasList.add(northEast);

        return southEasList;
    }

    
    /** 
     * @param confidenceValue
     * @return List<Integer>
     */
    public List<Integer> getSouthEast(int confidenceValue) {
        List<Integer> downList = this.getDown(confidenceValue);
        int southEast = downList.get(1) - confidenceValue;

        List<Integer> southEastList = new ArrayList<>();
        southEastList.add(downList.get(0));
        southEastList.add(southEast);

        return southEastList;
    }

}
