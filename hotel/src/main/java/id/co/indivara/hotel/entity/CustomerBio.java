package id.co.indivara.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customers_bio")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerBio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String customerName;
    private String customerGender;
    private String customerAddress;
    private Long customerNo;
    private LocalDate customerBirthDate;
    private Long idCardNumber;
}
