
import java.util.ArrayList;
import java.util.Random;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {
    private static Random rng = new Random();
    private int tWeight = 0;
    private int tValue = 0;

    /**
     * constructor method for Chromosome and set empty
     */
    public Chromosome() {

    }

    /**
     * method to add acopy of each items to the arraylist items and uses random
     * number to set the items included to tru or false
     * 
     * @param items passes the items arralist to the method
     */
    public Chromosome(ArrayList<Item> items) {
        for (Item item : items) {
            Item newItem = new Item(item);
            newItem.setIncluded(rng.nextBoolean());
            add(newItem);
        }
    }

    /**
     * METHOD TO GET chromosomes from each parent and create a ch chromosome by
     * crossover and this method has been created
     * with the help of chatGpt
     * 
     * @param other passes the chromosome of other parent
     * @return child arraylist with genes of both parent
     */
    public Chromosome crossover(Chromosome other) {
        Chromosome ch = new Chromosome();/*ch is used for child */
        for (int i = 0; i < this.size(); i++) {
            int ind = rng.nextInt(10);/*ind is used just for an index */
            if (ind < 5) {
                ch.add(new Item(this.get(i)));/* adds genes of parent 1 */
            } else {
                ch.add(new Item(other.get(i)));/* adds genes of parent 2 */
            }

        }
        return ch;
    }

    /**
     * method to mutate the child form each generation and flips the included using
     * random number
     * 
     * @return its a void method so it doesnot return anything
     */
    public void Mutate() {
        for (int i = 0; i < this.size(); i++) {
            if (rng.nextInt(10) == 5) {
                boolean incl = this.get(i).isIncluded();/*incl is used to take the value of included from item class  */
                this.get(i).setIncluded(!incl);

            }

        }
    }

    /**
     * method to get the fitness for each chromosome from the file
     * 
     * @return the tvalue of the items
     */
    public int getFitness() {
        tWeight = 0;/* tWeight is used to add and store the total weight of all item */
        tValue = 0;/* tValue is used to store total price of all the items */
        for (Item item : this) {
            if (item.isIncluded()) {
                tWeight += item.getWeight();
                tValue += item.getValue();
            }
        }
        if (tWeight > 10) {
            return 0;

        }
        return tValue;
    }

    /**
     * method to compare chromosomes
     * 
     * @param other passes the chromosome other to the method
     */
    @Override
    public int compareTo(Chromosome other) {
        return Integer.compare(other.getFitness(), this.getFitness());
    }

    /**
     * method to convert the data into string
     */
    @Override
    public String toString() {
        String result = "";
        for (Item item : this) {
            if (item.isIncluded()) {
                result += item.toString() + "\n";
            }

        }
        System.out.print(result);

        return "Fitness: " + getFitness();
    }

}