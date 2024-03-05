import java.util.Iterator;

public class Subset {

    public static void main(String[] args) {

        RandomizedBag<String> bag = new RandomizedBag<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                bag.put(item);
            else if (!bag.isEmpty())
                StdOut.print(bag.get() + " ");
        }

        int k = Integer.parseInt(args[0]);

        if (k > bag.size()) {
            throw new IllegalArgumentException("k must be less than or equal to the number of items in the bag");
        } else if (k < 0) {
            throw new IllegalArgumentException("k must be a non-negative integer");
        }

        Iterator<String> itr = bag.iterator();
        for (int i = 0; i < k; i++) {
            if (itr.hasNext()) {
                StdOut.println(itr.next());
            }
        }

    }

}