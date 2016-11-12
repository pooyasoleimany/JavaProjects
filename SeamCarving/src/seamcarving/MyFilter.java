/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seamcarving;

import java.util.function.Predicate;

/**
 *
 * @author Pouneh
 */

class MyFilter implements Predicate<String> {

    public MyFilter() {
    }

    @Override
    public boolean test(String t) {
        return t.startsWith("line3");
    }    
}
