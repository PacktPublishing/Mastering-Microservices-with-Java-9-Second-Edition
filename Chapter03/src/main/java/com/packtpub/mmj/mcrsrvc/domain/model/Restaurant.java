package com.packtpub.mmj.mcrsrvc.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sourabh Sharma
 */
public class Restaurant extends BaseEntity<String> {

    private List<Table> tables = new ArrayList<>();

    /**
     *
     * @param name
     * @param id
     * @param tables
     */
    public Restaurant(String name, String id, List<Table> tables) {
        super(id, name);
        this.tables = tables;
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
        return new StringBuilder("{id: ").append(id).append(", name: ")
                .append(name).append(", tables: ").append(tables).append("}").toString();
    }
}
