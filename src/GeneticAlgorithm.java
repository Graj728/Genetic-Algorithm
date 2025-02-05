import java.io.*;
import java.util.*;

public class GeneticAlgorithm {

    private static Random rng = new Random();/* Creates a random objectrng */

    /**
     * method to read the datafrom files
     * 
     * @param filename passes the patha nd name of file to the method
     * @return returns the items Arraylist
     * @throws FileNotFoundException manages the exception for file handling
     */
    public static ArrayList<Item> readData(String filename) throws FileNotFoundException {
        ArrayList<Item> items = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));

        while (scanner.hasNextLine()) {/* loops until the file has a next line */
            String[] line = scanner.nextLine().split(",");/* creates an array of line */
            String name = line[0].trim();
            double weight = Double.parseDouble(line[1].trim());
            int value = Integer.parseInt(line[2].trim());
            items.add(new Item(name, weight, value));/* adds the data to the arraylist */
        }

        scanner.close();
        return items;
    }

    /**
     * method to initialize each popultaion with items
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

        ArrayList<Chromosome> population = initializePopulation(items, 10); /* calls the initalize population method */

        for (int generation = 0; generation < 20; generation++) {/* loops for 20generation */

            Collections.sort(population);

            ArrayList<Chromosome> nextGeneration = new ArrayList<>(population.subList(0, 10));

            while (nextGeneration.size() < 20) {/* loops until the size of nextGeneration array is less than 20 */
                Chromosome parent1 = population.get(rng.nextInt(10));
                Chromosome parent2 = population.get(rng.nextInt(10));
                Chromosome child = parent1.crossover(parent2); /* calls the crossover method */
                nextGeneration.add(child);
            }

            for (int i = 0; i < nextGeneration.size(); i++) {
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

        System.out.println("\nLeast Fittest Individual: ");
        System.out.println(population.get(population.size() - 1).toString());
    }
}
