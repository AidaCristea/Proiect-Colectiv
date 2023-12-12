package com.example.ContentSubscription.repository;

import com.example.ContentSubscription.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PostRepo extends JpaRepository<Post, Long> {

}
