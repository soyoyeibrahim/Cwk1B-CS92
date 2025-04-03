/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

import java.util.List;

/**
 *
 * @author is21abb
 */
public class squadron {
    private List<ship> squadron;

    public squadron(List<ship> squadron) {
        this.squadron = squadron;
    }

    public int getNoOfShips() {
        return squadron.size();
    }
    
}
