package com.vin.controller;

import com.vin.entity.Comment;
import com.vin.entity.Post;

import com.vin.service.BlogService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BlogController {

    private final BlogService service;

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard(
            Model model,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "")
            String keyword) {

        Pageable pageable =
                PageRequest.of(page, 5);

        Page<Post> posts;

        if (keyword.isEmpty()) {

            posts =
                    service.getAllPosts(pageable);

        } else {

            posts =
                    service.searchPosts(
                            keyword,
                            pageable
                    );
        }

        model.addAttribute(
                "posts",
                posts.getContent()
        );

        model.addAttribute(
                "currentPage",
                page
        );

        model.addAttribute(
                "totalPages",
                posts.getTotalPages()
        );

        model.addAttribute(
                "keyword",
                keyword
        );

        return "index";
    }

    // Create Form
    @GetMapping("/create")
    public String createForm(Model model) {

        model.addAttribute(
                "post",
                new Post()
        );

        return "create-post";
    }

    // Save Post
    @PostMapping("/save")
    public String savePost(
            @Valid @ModelAttribute Post post,

            BindingResult result,

            @RequestParam String tags,

            Authentication authentication) {

        if (result.hasErrors()) {

            return "create-post";
        }

        service.createPost(
                post,
                tags,
                authentication
        );

        return "redirect:/dashboard";
    }

    // View Post
    @GetMapping("/post/{id}")
    public String viewPost(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "post",
                service.getPostById(id)
        );

        model.addAttribute(
                "comment",
                new Comment()
        );

        return "view-post";
    }

    // Add Comment
    @PostMapping("/comment/{postId}")
    public String addComment(
            @PathVariable Long postId,

            @Valid @ModelAttribute Comment comment,

            BindingResult result,

            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "post",
                    service.getPostById(postId)
            );

            return "view-post";
        }

        service.addComment(
                postId,
                comment
        );

        return "redirect:/post/" + postId;
    }

    // Edit Form
    @GetMapping("/edit/{id}")
    public String editForm(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "post",
                service.getPostById(id)
        );

        return "edit-post";
    }

    // Update Post
    @PostMapping("/update")
    public String updatePost(
            @Valid @ModelAttribute Post post,
            BindingResult result) {

        if (result.hasErrors()) {

            return "edit-post";
        }

        service.updatePost(post);

        return "redirect:/dashboard";
    }

    // Delete Post
    @GetMapping("/delete/{id}")
    public String deletePost(
            @PathVariable Long id) {

        service.deletePost(id);

        return "redirect:/dashboard";
    }
}