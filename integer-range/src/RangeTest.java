import java.util.Iterator;

/**
 * Created by ferrao on 30/05/16.
 */
public class RangeTest {

    public static void main(String[] args) {

        Range r = new Range(-3, 5);

        System.out.println("--- USING ITERATOR ---");
        Iterator<Integer> it = r.iterator();

        r.setReverse(true);

        while (it.hasNext()) {

            int i = it.next();

           if (i == 1 || i == 2 || i == 3) {
                   it.remove();
           }
        }

        for (Integer i : r) {
            System.out.println("Iterated: " + i);
        }

    }

}
