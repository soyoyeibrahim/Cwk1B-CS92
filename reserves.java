/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author is21abb
 */
public class reserves {
    private final List<ship> reserveShips;

    public reserves() {
        this.reserveShips = new ArrayList<>();
    }

    public void addToReserve(ship ships) {
        reserveShips.add(ships);
    }

    public void deductFromReserve(ship ships) {
        reserveShips.remove(ships);
    }

    @Override
    public String toString() {
        return "Reserve Ships: " + reserveShips.size();
    }
    
}
