import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/*
 * Activity 1.1.4
 */
public class CompoundOperators_1_1_4
{
  public final double gradYear = 13;
  public int numPeople = 0;
  public int totalYears = 0;
  /* Collection to store <key, value> pairs of peoples names and years */
  public Map<String, Double> people = new HashMap<String, Double>();
  
  public static void main(String[] args)
  {
    CompoundOperators_1_1_4 compound = new CompoundOperators_1_1_4();
    /* Use method refrence operator to refrence the static method createPerson bc CompoundOperators_1_1_4 is long to use each time */
    BiFunction<String, Double, Person> personFactory = CompoundOperators_1_1_4::createPerson;

    compound.addPerson("bob", 11.75);
    compound.addPerson("joe", 5.5);
    compound.addPerson("sam", 2.25);

    compound.addPersons(personFactory.apply("person1", 7.0));

    System.out.println(compound.toString());
  }

  /**
   * Creates an instance of the person class to easily be passed into addPerson / addPersons
   * 
   * @param name The name of the person
   * @param years What year of school they are in
   * @return
   */
  public static Person createPerson(String name, double years) {
    return new Person(name, years);
  }
  
  /**
   * Adds a person with a given name and year
   * 
   * @param name The name of the person
   * @param years What year of school the person is in
   */
  public void addPerson(String name, double years) {
    this.totalYears += years;
    this.people.put(name, years);
    this.numPeople++;
  }

  /**
   * Adds a person through a Person object
   * 
   * @param person The person to add
   */
  public void addPerson(Person person) {
    this.addPerson(person.name, person.years);
  }

  /**
   * Adds a variable number of people through Person objects
   * i.e. addPersons(createPerson("test", 5.5), createPerson("test2", 2.75), createPerson("test3", 8.5));
   */
  public void addPersons(Person... people) {
      for (Person p : people) {
        this.addPerson(p.name, p.years);
      }
  }

  /**
   * Gets a person by name
   * 
   * @param name The name of the person to get
   * @return
   */
  public double getPerson(String name) {
      return this.people.get(name);
  }

  @Override
  public String toString() {
    StringBuilder toPrint = new StringBuilder();
    toPrint.append("All People\n----------\n");
    double totalDays = 0;
    for (Map.Entry<String, Double> entry : this.people.entrySet()) {
        toPrint.append(entry.getKey() + ": Grade " + entry.getValue() + " | Graduates in " + (gradYear - entry.getValue()) + " years" + "\n");
        totalDays += 180;
    }
    toPrint.append("----------\n");
    toPrint.append("Total Years: " + this.totalYears);
    toPrint.append("\n  -> Average years in school: " + (totalYears / this.people.size()));
    toPrint.append("\n  -> Total days in school: " + totalDays);
    toPrint.append("\n  -> Average days in school: " + (totalDays / this.people.size()));
    toPrint.append("\nTotal People: " + this.numPeople);
    return toPrint.toString();
  }

}

/**
 * Data class for a person
 */
class Person {
  public double years=0;
  public String name="";
  public Person(String name, double years) { this.name = name; this.years = years; }
}
