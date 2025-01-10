package paf.day21.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import paf.day21.SQLQueries;
import paf.day21.model.Room;

@Repository
public class RoomRepo {
    @Autowired
    JdbcTemplate template;

    public List<Room> getRooms() {
        List<Room> rooms = new ArrayList<>();

        SqlRowSet sqlRowSet = template.queryForRowSet(SQLQueries.SQL_GET_ALLROOMS);
        while (sqlRowSet.next()) {
            Room rm = new Room(sqlRowSet.getInt("id"),
                                sqlRowSet.getString("room_type"),
                                sqlRowSet.getFloat("price"));
            rooms.add(rm);
        }

        return rooms;
    }

    public Room getRoomByID(int id) {
        return template.queryForObject(SQLQueries.SQL_GET_ROOM, BeanPropertyRowMapper.newInstance(Room.class), id);
    }

    public boolean deleteRoomByID(int id) {
        int roomDeleted = template.update(SQLQueries.SQL_DEL_ROOM, id);

        return roomDeleted > 0; 
    }
    
    public boolean updateRoomByID(Room room) {
        int roomUpdated = template.update(SQLQueries.SQL_UPDATE_ROOM, room.getRoomType(), room.getPrice(), room.getId());

        return roomUpdated > 0; 
    }    

    public boolean insertRoom(Room room) {
        int roomInserted = template.update(SQLQueries.SQL_INSERT_ROOM, room.getRoomType(), room.getPrice());

        return roomInserted > 0; 
    }
}