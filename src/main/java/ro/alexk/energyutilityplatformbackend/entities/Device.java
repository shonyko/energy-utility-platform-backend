package ro.alexk.energyutilityplatformbackend.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
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

    @ToString.Exclude
    @OneToMany(mappedBy = "device")
    private Set<Measurement> measurements = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Device device = (Device) o;
        return id != null && Objects.equals(id, device.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
