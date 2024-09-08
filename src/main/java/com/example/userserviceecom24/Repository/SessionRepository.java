package com.example.userserviceecom24.Repository;

//import jakarta.websocket.Session;
import com.example.userserviceecom24.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

//      @Override
//      Optional<Session> findById(Long aLong);
//      Optional<Session> findByTokenAndUser(String token, Long userId);

//      void save(com.example.userserviceecom24.Models.Session session);

}
