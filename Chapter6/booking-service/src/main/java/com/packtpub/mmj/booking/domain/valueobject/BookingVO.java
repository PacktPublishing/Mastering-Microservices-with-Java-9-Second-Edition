package com.packtpub.mmj.booking.domain.valueobject;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Sourabh Sharma
 */
public class BookingVO {

    private String name;
    private String id;
    private String restaurantId;
    private String userId;
    private LocalDate date;

    private LocalTime time;
    private String tableId;

    /**
     *
     * @return
     */
    public String getTableId() {
        return tableId;
    }

    /**
     *
     * @param tableId
     */
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    /**
     *
     * @return
     */
    public String getRestaurantId() {
        return restaurantId;
    }

    /**
     *
     * @param restaurantId
     */
    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     *
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Default Constructor
     */
    public BookingVO() {
    }

    /**
     * Custom Constructor
     *
     * @param name
     * @param id
     * @param restaurantId
     * @param userId
     * @param time
     * @param date
     */
    public BookingVO(String id, String name, String restaurantId, String tableId, String userId, LocalDate date, LocalTime time) {
        this.id = id;
        this.name = name;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.date = date;
        this.time = time;
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
                .append(name).append(", userId: ").append(userId)
                .append(", restaurantId: ").append(restaurantId)
                .append(", tableId: ").append(tableId)
                .append(", date: ").append(date).append(", time: ").append(time).append("}").toString();
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
}
