
import java.util.ArrayList;
import java.util.Random;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {
    private  Random rng = new Random();
    private  int totalWeight = 0;
    private  int totalValue = 0;

    /**
     * constructor method for Chromosome and set empty
     */
    public Chromosome() {

    }

    /**
     * method to add acopy of each items to the arraylist items and uses random number to set the items included to tru or false
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
     * METHOD TO GET chromosomes from each parent and create a child chromosome by crossover and this method has been created
     * with the help of chatGpt
     * 
     * @param other passes the chromosome of other parent
     * @return child arraylist with genes of both parent
     */
    public Chromosome crossover(Chromosome other) {
        Chromosome child = new Chromosome();
        for (int i = 0; i < this.size(); i++) {
            int ind = rng.nextInt(10);
            if (ind < 5) {
                child.add(new Item(this.get(i)));/* adds genes of parent 1 */
            } else {
                child.add(new Item(other.get(i)));/* adds genes of parent 2 */
            }

        }
        return child;
    }

    /**
     * method to mutate the child form each generation and flips the included using random number
     * 
     * @return its a void method so it doesnot return anything
     */
    public void Mutate() {
        for (int i = 0; i < this.size(); i++) {
            if (rng.nextInt(10) == 5) {
                boolean included = this.get(i).isIncluded();
                this.get(i).setIncluded(!included);

            }

        }
    }

    /**
     * method to get the fitness for each chromosome from the file
     * 
     * @return the totalvalue of the items
     */
    public int getFitness() {
        totalWeight = 0;
        totalValue = 0;
        for (Item item : this) {
            if (item.isIncluded()) {
                totalWeight += item.getWeight();
                totalValue += item.getValue();
            }
        }
        if (totalWeight > 10) {
            return 0;

        }
        return totalValue;
    }

    /**
     * method to compare chromosomes
     * 
     * @param other passes the other other to the method
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
        String result="";
        for (Item item : this) {
            if (item.isIncluded()) {
                result+=item.toString()+"\n";
            }
        }
        
        return result;
    }

}