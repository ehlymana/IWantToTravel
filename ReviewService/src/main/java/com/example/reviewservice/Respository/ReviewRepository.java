package com.example.reviewservice.Respository;

import com.example.reviewservice.Model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    Review findByReviewId(Long id);

    Review getOne(Long id);
}
