package models;

public class ModelInheritedThrowable extends Throwable {

    private Person person = new Person();

    public Person getPerson() {
        return person;
    }
}
