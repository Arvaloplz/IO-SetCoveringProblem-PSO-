/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setcoveringproblemii;

import java.util.ArrayList;

/**
 *
 * @author arval
 */
public class Area {
    ArrayList <int[]> area;

    public Area() {
        area=new ArrayList<int[]>();
    }
    public boolean addArea(int[] comuna){
    return area.add(comuna);
    }
    public Object getComunaN(int n){
    return area.get(n);
    }

    int getSize() {
       return area.size();
    }
    
    
}
