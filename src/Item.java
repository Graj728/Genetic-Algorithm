public class Item {
   /* Created fields for the class Item */
   private final String name;
   private final double weight;
   private final int value;
   private boolean included;

   /**
    * method to set the field of the class
    * 
    * @param name   passes the name of items
    * @param weight passes sthe weigh of item
    * @param value  passes the price of items
    */
   public Item(String name, double weight, int value) {
      this.name = name;
      this.weight = weight;
      this.value = value;
      this.included = false; // Initially not included
   }

   /**
    * method to set the fielsd of other item
    * 
    * @param item passes the other item to the method
    */
   public Item(Item other) {
      this.name = other.name;
      this.weight = other.weight;
      this.value = other.value;
      this.included = other.included;
   }

   /**
    * method to get the name of items
    * 
    * @return retuns the name o f item
    */
   public String getName() {
      return name;
   }

   /**
    * method to get the weight of each item
    * 
    * @return the weight of each item
    */
   public double getWeight() {
      return weight;
   }

   /**
    * method to get the price of ecah item
    * 
    * @return returns the price of each item
    */
   public int getValue() {
      return value;
   }

   /**
    * method to get the true false value for inclusion of each item
    * 
    * @return the boolean value of inclusion for each item
    */
   public boolean isIncluded() {
      return included;
   }

   /**
    * method to ste the value of includes
    * 
    * @param included passes the value for inclusiveness for each item
    */
   public void setIncluded(boolean included) {
      this.included = included;
   }

   /**
    * method to give String representation of the items
    */
   @Override
   public String toString() {
      String item=name + " (" + weight + " lbs, $" + value + ")";
      return item;
   }

}
