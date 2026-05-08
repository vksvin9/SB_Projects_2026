package com.vin.service;

import com.vin.entity.Comment;
import com.vin.entity.Post;
import com.vin.entity.Tag;
import com.vin.entity.User;

import com.vin.repository.CommentRepository;
import com.vin.repository.PostRepository;
import com.vin.repository.TagRepository;
import com.vin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final TagRepository tagRepository;

    // Dashboard Posts
    public Page<Post> getAllPosts(
            Pageable pageable) {

        return postRepository.findAll(
                pageable
        );
    }

    // Search Posts
    public Page<Post> searchPosts(
            String keyword,
            Pageable pageable) {

        return postRepository
                .findByTitleContainingIgnoreCase(
                        keyword,
                        pageable
                );
    }

    // Create Post
    public Post createPost(
            Post post,
            String tagNames,
            Authentication authentication) {

        User user =
                userRepository.findByEmail(
                        authentication.getName()
                ).orElseThrow();

        post.setUser(user);

        post.setCreatedAt(
                LocalDateTime.now()
        );

        post.setSlug(
                post.getTitle()
                        .toLowerCase()
                        .replace(" ", "-")
        );

        List<Tag> tags =
                new ArrayList<>();

        String[] splitTags =
                tagNames.split(",");

        for (String tagName : splitTags) {

            String cleanTag =
                    tagName.trim()
                            .toLowerCase();

            Tag tag =
                    tagRepository.findByName(
                                    cleanTag
                            )
                            .orElseGet(() -> {

                                Tag newTag =
                                        Tag.builder()
                                                .name(cleanTag)
                                                .build();

                                return tagRepository.save(
                                        newTag
                                );
                            });

            tags.add(tag);
        }

        post.setTags(tags);

        return postRepository.save(post);
    }

    // Get Post By ID
    public Post getPostById(Long id) {

        return postRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Post not found"
                        ));
    }

    // Add Comment
    public Comment addComment(
            Long postId,
            Comment comment) {

        Post post =
                getPostById(postId);

        comment.setPost(post);

        comment.setCreatedAt(
                LocalDateTime.now()
        );

        return commentRepository.save(
                comment
        );
    }

    // Update Post
    public Post updatePost(Post post) {

        return postRepository.save(post);
    }

    // Delete Post
    public void deletePost(Long id) {

        postRepository.deleteById(id);
    }
}