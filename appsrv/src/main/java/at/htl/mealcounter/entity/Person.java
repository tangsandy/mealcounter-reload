package at.htl.mealcounter.entity;
import javax.persistence.*;
import at.htl.mealcounter.entity.NfcCard;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import java.util.Objects;


@Entity
@Table(name = "M_PERSON")
public class Person extends PanacheEntityBase {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @JoinColumn(name = "NFC_CARD")
    NfcCard nfcCard;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ENTRY_YEAR")
    private int entryYear;  // eintrittsjahr

    public Person() {
    }

    public Person(String firstName, String lastName, int entryYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.entryYear = entryYear;
    }

    public Person(NfcCard nfcCard, String firstName, String lastName, int entryYear) {
        this.nfcCard = nfcCard;
        this.firstName = firstName;
        this.lastName = lastName;
        this.entryYear = entryYear;
    }

    //region getter and setter
    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NfcCard getNfcCard() {
        return nfcCard;
    }

    public void setNfcCard(NfcCard nfcCard) {
        this.nfcCard = nfcCard;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getentryYear() {
        return entryYear;
    }

    public void setentryYear(int entryYear) {
        this.entryYear = entryYear;
    }

    //endregion


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return entryYear == person.entryYear && Objects.equals(id, person.id) && Objects.equals(nfcCard, person.nfcCard) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nfcCard, firstName, lastName, entryYear);
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", nfcCard=" + nfcCard +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", entryYear=" + entryYear +
                '}';
    }
}
