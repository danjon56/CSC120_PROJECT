import java.util.Scanner;
//=================================================================================================
public class UsePerson {
    //-------------------------------------------------------------------------------------------------
    private static final Scanner keyboard = new Scanner(System.in);
    //-------------------------------------------------------------------------------------------------
    public static void main(String[] args) {

        Person onePerson;
        String name;
        int age;

//----Get person information
        System.out.print("Enter name of person : ");
        name = keyboard.nextLine();
        System.out.print("How old is that person    : ");
        age = keyboard.nextInt();
        keyboard.nextLine();
        onePerson = new Person(name,age);

//----Print person information
        System.out.println(onePerson);

//----Let person have a birthday
        onePerson.incrementAge();
        System.out.println(onePerson);

//----Print person information via accessors
        System.out.println("Raw access shows that " + onePerson.getName() + " is " +
                onePerson.getAge() + " years old");
    }
//-------------------------------------------------------------------------------------------------
}
//=================================================================================================
