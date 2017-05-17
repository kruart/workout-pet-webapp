package ua.kruart.workout.model;

import javax.persistence.*;

/**
 * Base class for all business entities
 *
 * @author kruart on 16.05.2017.
 */
@MappedSuperclass
public class BaseEntity {

    /**
     * Unique entity identifier
     */
    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "entity_id_seq", name = "entity_id_seq")
    @GeneratedValue(generator="entity_id_seq", strategy=GenerationType.SEQUENCE)
    private Integer id;

    public BaseEntity() {
    }

    public BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
