package ua.kruart.workout.model.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Base class for all business entities which contains name property
 *
 * @author kruart on 16.05.2017.
 */
@MappedSuperclass
public class NamedEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    protected String name;

    public NamedEntity() {
    }

    protected NamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}
