package com.example.ContentSubscription.repository;

import com.example.ContentSubscription.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PostRepo extends JpaRepository<Post, Long> {
    //all posts that have subscriptionTypeId= givenSTID
    @Query("SELECT P.postId FROM Post P WHERE P.subscriptionType.subscriptionTypeId = :subscriptionTypeId")
    List<Long> findBySubscriptionTypeId(@Param("subscriptionTypeId") Long subscriptionTypeId);


}
