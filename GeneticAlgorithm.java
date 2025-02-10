import java.io.*;
import java.util.*;

public class GeneticAlgorithm {

    private static Random rng = new Random();/* Creates a random object rng */

    /**
     * method to read the data from files and add the items to items arraylist
     * 
     * @param filename passes the path and name of file to the method
     * @return returns the items Arraylist
     * @throws FileNotFoundException manages the exception for file handling
     */
    public static ArrayList<Item> readData(String filename) throws FileNotFoundException {
        ArrayList<Item> items = new ArrayList<>();
        Scanner scnr = new Scanner(new File(filename));

        while (scnr.hasNextLine()) {/* loops until the file has a next line */
            String item = scnr.nextLine(); /* takes the lines as input from the file */
            String[] line = item.split(",");/*
                                             * creates an array of line and stores the data from item splits between by
                                             * ,
                                             */
            String name = line[0].trim();
            double weight = Double.parseDouble(line[1].trim());/*
                                                                * parse the weight to doubletaken from the item at index
                                                                * 1 of array line
                                                                */
            int value = Integer.parseInt(line[2].trim());/*
                                                          * parse the value to integer taken from the item at index 1 of
                                                          * array line
                                                          */
            items.add(new Item(name, weight, value));/* adds the data to the arraylist */
        }

        scnr.close();
        return items;
    }

    /**
     * method to create and return an arryalist of population size Chromosome
     * objects each of them contains item
     * 
     * @param items          passes the items arraylist
     * @param populationSize passes the totalpopulation
     * @return the population arraylist
     */
    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize) {
        ArrayList<Chromosome> population = new ArrayList<>();
        while (population.size() < populationSize) {
            population.add(new Chromosome(items));
        }
        return population;
    }

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Item> items = readData("Items.txt");/* calls the readData method */

        ArrayList<Chromosome> population = initializePopulation(items, 10); /* calls the initalizepopulation method */

        for (int generation = 0; generation < 20; generation++) {/* loops for 20generation */

            Collections.sort(population);

            ArrayList<Chromosome> nextGeneration = new ArrayList<>(population.subList(0, 10));/*
                                                                                               * created with help of
                                                                                               * chatgpt
                                                                                               */

            while (nextGeneration.size() < 20) {/* loops until the size of nextGeneration array is less than 20 */
                Chromosome p1 = population.get(rng.nextInt(10));/* p1 is used fro parent 1 */
                Chromosome p2 = population.get(rng.nextInt(10));/* p2 is used for parent 2 */
                Chromosome ch = p1.crossover(p2); /* calls the crossover method and ch is used for child*/
                nextGeneration.add(ch);
            }

            for (int i = 0; i < nextGeneration.size(); i++) {/*created with chatgpt */
                if (rng.nextInt(10) < 1) { // 10% chance to mutate
                    nextGeneration.get(i).Mutate(); /* calls the mutate method */
                }
            }

            population = nextGeneration;/* sets population to nextGeneration */

            System.out.println("\nGeneration " + (generation + 1) + " best solution:");/*
                                                                                        * prints the result for each
                                                                                        * generation
                                                                                        */
            System.out.println(population.get(0).toString());
        }

        Collections.sort(population);
        System.out.println("\nFittest Individual: ");
        System.out.println(population.get(0).toString());

        // System.out.println("\nLeast Fittest Individual: ");
        // System.out.println(population.get(population.size() - 1).toString());
    }
}
