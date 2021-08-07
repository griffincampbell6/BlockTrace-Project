package edu.example.blockTraceData;

// Main Data source for the User
public class Person
{
    public int id ;
    public String firstName ;
    public String lastName ;
    public int age;
    public String gender;
    public String phone;
    public boolean isHealthy;
    public String userName ;
    public String password;
    //actually, yes, this class will have a constructor, this should be used for when Account Creation.
    //Note that this constructor doesnt let you set the Perosn ID!
    // See The CreateAccount method for why, since thats the only time this constructor should get used as far as I know of
    public Person(String newFirstName,String newLastName,int newAge,String newGender,String newPhone,String newUserName,String newPassword)
    {
        firstName = newFirstName;
        lastName = newLastName;
        age = newAge;
        gender = newGender;
        phone = newPhone;
        userName = newUserName;
        password = newPassword;
        isHealthy =true;
    }
}
