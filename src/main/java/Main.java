import org.joda.time.LocalDate;

public class Main { public static void main(String[] args) {

    int count = 0;

    Person pers1 = new Person(++count,"Rina Klee",new LocalDate(2000,1,1),"female");
    Person viktor = new Person(++count,"Viktor Vasiliev",new LocalDate(1992,3,4),"man");

    System.out.println("ID: " + pers1.getId());
    System.out.println("Full name: " + pers1.getFullName());
    System.out.println("Gender: " + pers1.getGender());
    System.out.println("Age : " + pers1.getAge());

    System.out.println("ID: " + viktor.getId());
    System.out.println("Full name: " + viktor.getFullName());
    System.out.println("Gender: " + viktor.getGender());
    System.out.println("Age : " + viktor.getAge());
}

}
