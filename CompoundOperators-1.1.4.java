import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/*
 * Activity 1.1.4
 */
public class CompoundOperators
{
  public final double gradYear = 13;
  public int numPeople = 0;
  public int totalYears = 0;
  public Map<String, Double> people = new HashMap<String, Double>();
  
  public static void main(String[] args)
  {
    CompoundOperators compound = new CompoundOperators();
    BiFunction<String, Double, Person> personFactory = CompoundOperators::createPerson;

    compound.addPerson("bob", 11.75);
    compound.addPerson("joe", 5.5);
    compound.addPerson("sam", 2.25);

    compound.addPersons(personFactory.apply("person1", 7.0));

    System.out.println(compound.toString());
  }

  public static Person createPerson(String name, double years) {
    return new Person(name, years);
  }
  
  public void addPerson(String name, double years) {
    this.totalYears += years;
    this.people.put(name, years);
    this.numPeople++;
  }

  public void addPersons(Person... people) {
      for (Person p : people) {
        this.people.put(p.name, p.years);
      }
  }

  public double getPerson(String name) {
      return this.people.get(name);
  }

  @Override
  public String toString() {
    StringBuilder toPrint = new StringBuilder();
    toPrint.append("All People\n----------\n");
    double average = 0;
    double totalDays = 0;
    for (Map.Entry<String, Double> entry : this.people.entrySet()) {
        toPrint.append(entry.getKey() + ": Grade " + entry.getValue() + " | Graduates in " + (gradYear - entry.getValue()) + " years" + "\n");
        average += entry.getValue();
        totalDays += 180;
    }
    toPrint.append("----------\n");
    toPrint.append("Total Years: " + this.totalYears);
    toPrint.append("\n  -> Average years in school: " + (average / this.people.size()));
    toPrint.append("\n  -> Total days in school: " + totalDays);
    toPrint.append("\n  -> Average days in school: " + (totalDays / this.people.size()));
    toPrint.append("\nTotal People: " + this.numPeople);
    return toPrint.toString();
  }

}

class Person {
  public double years=0;
  public String name="";
  public Person(String name, double years) { this.name = name; this.years = years; }
}