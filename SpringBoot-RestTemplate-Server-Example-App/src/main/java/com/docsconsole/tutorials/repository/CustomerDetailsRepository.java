package com.docsconsole.tutorials.repository;

import com.docsconsole.tutorials.model.entity.CustomerDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetailsEntity, Long> {

}
