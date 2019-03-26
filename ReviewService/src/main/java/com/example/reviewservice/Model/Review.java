package com.example.reviewservice.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REVIEW")
public class Review {

    @Id
    @Column(name = "REVIEW_ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reviewId;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "HOTEL_ID", nullable = false)
//    private Hotel hotel;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "USER_ID", nullable = false)
//    private User user;


    @Column(name = "REVIEW_COMMENT")
    private String reviewComment;

    @Column(name = "REVIEW_DATE")
    private Date reviewDate;


    public Review(long id, String reviewComment, Date reviewDate) {
        this.reviewId = id;
        this.reviewComment = reviewComment;
        this.reviewDate = reviewDate;
    }

    public Review() {
    }

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}
