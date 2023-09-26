package com.omz.demo.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omz.demo.review.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>{

}
