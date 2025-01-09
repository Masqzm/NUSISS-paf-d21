package paf.day21.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paf.day21.model.Room;
import paf.day21.repo.RoomRepo;

@Service
public class RoomService {
    @Autowired
    RoomRepo roomRepo;

    public List<Room> getAllRooms() {
        return roomRepo.getRooms();
    }
}
