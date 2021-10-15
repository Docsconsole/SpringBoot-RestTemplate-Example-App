package com.docsconsole.tutorials.repository;

import com.docsconsole.tutorials.model.entity.CustomerDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.concurrent.Future;


@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetailsEntity, Long> {

    @Query("select customerDet from CustomerDetailsEntity customerDet where customerDet.id = :id and customerDet.firstName = :firstName")
    Future<CustomerDetailsEntity> findByIdAndByFirstName(@Param("id") Long id, @Param("firstName") String firstName);
}
