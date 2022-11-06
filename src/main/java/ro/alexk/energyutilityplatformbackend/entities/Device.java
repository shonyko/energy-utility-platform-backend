package ro.alexk.energyutilityplatformbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "devices")
public class Device implements BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String description;

    @ManyToOne(optional = false)
    private Address address;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "device")
    private Set<Measurement> measurements = new HashSet<>();
}
