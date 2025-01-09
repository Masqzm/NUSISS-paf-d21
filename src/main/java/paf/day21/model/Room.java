package paf.day21.model;

public class Room {
    private int id;
    private String roomType;
    private float price;
    
    public Room() {}
    public Room(int id, String roomType, float price) {
        this.id = id;
        this.roomType = roomType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room [id=" + id + ", roomType=" + roomType + ", price=" + price + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    
}
