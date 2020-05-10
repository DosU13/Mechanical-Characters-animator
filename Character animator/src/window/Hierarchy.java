package window;

import objects.Machine;

import java.util.ArrayList;

public class Hierarchy extends ArrayList<Machine> {
    private int count;

    public Hierarchy() {
        count = 1;
    }



    public ArrayList<String> getNames(){
        ArrayList<String> result = new ArrayList<>();
        for (Machine m: this){
            result.add(m.getName());
        }
        return result;
    }
}