package paf.day21.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paf.day21.model.Room;
import paf.day21.service.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomRestController {
    @Autowired
    RoomService roomSvc;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomSvc.getAllRooms();

        return ResponseEntity.ok().body(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getCustomerByID(@PathVariable int id) {
        Room room = roomSvc.getRoomByID(id);

        return ResponseEntity.ok().body(room);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCustomerByID(@PathVariable int id) {
        boolean success = roomSvc.deleteRoomByID(id);

        return ResponseEntity.ok().body(success);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateCustomerByID(@PathVariable int id, @RequestBody Room room) {
        boolean success = false;

        // Retrieve to check customer exists
        if(roomSvc.getRoomByID(id) != null)
            success = roomSvc.updateRoomByID(room);

        return ResponseEntity.ok().body(success);
    }

    @PostMapping("/new")
    public ResponseEntity<Boolean> insertCustomer(@RequestBody Room room) {
        boolean success = roomSvc.insertRoom(room);

        return ResponseEntity.ok().body(success);
    }
}
