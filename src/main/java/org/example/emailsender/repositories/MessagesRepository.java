package org.example.emailsender.repositories;

import org.example.emailsender.entites.Messages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends CrudRepository<Messages, String> {

    Page<Messages> findAll(Pageable pageable);

}
