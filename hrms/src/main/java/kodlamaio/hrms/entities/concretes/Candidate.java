package kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name="candidates")
@PrimaryKeyJoinColumn(name="id",referencedColumnName = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Candidate extends User {

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="national_identity")
    private String nationalIdentity;

    @Column(name="birth_year")
    private int birthYear;

    @Transient 
    private String passwordValidation;

}