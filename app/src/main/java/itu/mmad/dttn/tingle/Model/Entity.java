package itu.mmad.dttn.tingle.Model;

import java.util.Objects;
import java.util.UUID;

/**
 * Abstract entity class that represents some given item in a database.
 */
public abstract class Entity {

    private final String id;

    public Entity()
    {
        id = UUID.randomUUID().toString();
    }

    /**
     * Returns unqiue id of item
     * @return
     */
    public String getId(){
        return id;
    }

    /**
     * Used to compare entities
     * @param o to compare with
     * @return if equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(id, ((Entity) o).getId());
    }

    /**
     * Hashcode of entity
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
