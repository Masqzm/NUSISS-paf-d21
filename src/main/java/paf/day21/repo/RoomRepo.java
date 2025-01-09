package paf.day21.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
