package com.packtpub.mmj.restaurant.domain.valueobject;

import com.packtpub.mmj.restaurant.domain.model.entity.Table;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sourabh Sharma
 */
public class RestaurantVO {

    private List<Table> tables = new ArrayList<>();
    private String name;
    private String id;
    private String address;

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Constructor
     */
    public RestaurantVO() {
    }

    /**
     *
     * @param tables
     */
    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    /**
     *
     * @return
     */
    public List<Table> getTables() {
        return tables;
    }

    /**
     * Overridden toString() method that return String presentation of the
     * Object
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("{id: %s, name: %s, address: %s, tables: %s}", this.getId(),
                this.getName(), this.getAddress(), this.getTables());
    }
}
