package com.example.app.repositories;

import com.example.app.models.other.ClientRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ClientRequestRepository extends JpaRepository<ClientRequest, UUID> {

    @Query("SELECT count(cr) from ClientRequest cr")
    Integer countAll();

}
