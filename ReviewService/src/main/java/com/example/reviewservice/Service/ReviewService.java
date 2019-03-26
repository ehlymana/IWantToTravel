package com.example.reviewservice.Service;

import com.example.reviewservice.Model.Review;
import com.example.reviewservice.Respository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Iterable<Review> findAll() {
        return reviewRepository.findAll();
    }

    public void save(Review review) {
        reviewRepository.save(review);
    }


    public void delete(Review review) {
        reviewRepository.delete(review);
    }

    public Review findById(Long id) {
        return reviewRepository.findByReviewId(id);
    }


    public Review getOne(Long id) {
        return reviewRepository.getOne(id);
    }

    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

}
