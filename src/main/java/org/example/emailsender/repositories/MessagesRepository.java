package org.example.emailsender.repositories;

import org.example.emailsender.entites.Messages;
import org.example.emailsender.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends CrudRepository<Messages, String> {

    Page<Messages> findAll(Pageable pageable);

    List<Messages> findByStatus(String status);

}
