package com.hotel.booking;

public class Room {

    private double pernightprice;
    private String rimgone;
    private String rimgtwo;
    private String roomname;

    public Room(double pernightprice, String rimgone, String rimgtwo, String roomname) {
        this.pernightprice = pernightprice;
        this.rimgone = rimgone;
        this.rimgtwo = rimgtwo;
        this.roomname = roomname;
    }

    public Double getPernightprice() {
        return pernightprice;
    }

    public void setPernightprice(Double pernightprice) {
        this.pernightprice = pernightprice;
    }

    public String getRimgone() {
        return rimgone;
    }

    public void setRimgone(String rimgone) {
        this.rimgone = rimgone;
    }

    public String getRimgtwo() {
        return rimgtwo;
    }

    public void setRimgtwo(String rimgtwo) {
        this.rimgtwo = rimgtwo;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }
}
