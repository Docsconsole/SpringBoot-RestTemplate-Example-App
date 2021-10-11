package com.docsconsole.tutorials.repository;

import com.docsconsole.tutorials.model.entity.ProductDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetailsEntity, Long> {

}
