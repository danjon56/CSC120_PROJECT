public class Family {
    private static final int MAX_NUMBER_OF_PEOPLE = 10;
    private int numberOfFamilyMembers;
    private Person[] family;


    public Family(){

        numberOfFamilyMembers = 0;
        family = new Person[MAX_NUMBER_OF_PEOPLE];

    }

    public boolean addPerson(String name, int age) {


        if (numberOfFamilyMembers < MAX_NUMBER_OF_PEOPLE) {
            family[numberOfFamilyMembers] = new Person(name, age);
            numberOfFamilyMembers++;
            return true;
        } else {
            // Sets total number of family members

            return false;
        }

    }

    public int getNumberOfPeople(){
        //Produces number of family members for UseFamily
        return numberOfFamilyMembers;
    }

    public int getTotalAge(){
        int i;
        int totalAge=0;

        for (i=0; i < numberOfFamilyMembers; i++){

            // Adds all ages together
            totalAge = totalAge + family[i].getAge();
        }

        return totalAge;
    }

    public void birthday(String name) {
        int nameIncrement;
        for (nameIncrement = 0; nameIncrement < numberOfFamilyMembers; nameIncrement++) {

            if (name.equalsIgnoreCase(family[nameIncrement].getName())) {
                // Increases age by 1
                family[nameIncrement].incrementAge();
            }
        }
    }

    public void display(){
    int familyIncrement;
        for (familyIncrement = 0; familyIncrement < numberOfFamilyMembers; familyIncrement++) {

            // Displays ages of family members
            System.out.println(family[familyIncrement]);

        }

    }

}
