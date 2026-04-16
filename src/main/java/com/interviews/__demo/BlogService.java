package com.interviews.__demo;

import com.interviews.__demo.json.model.BlogPostDto;
import com.interviews.__demo.json.model.CommentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final Logger logger = LoggerFactory.getLogger(BlogService.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final BlogPostRepository blogPostRepository;

    public BlogService(UserRepository userRepository, CommentRepository commentRepository, BlogPostRepository blogPostRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.blogPostRepository = blogPostRepository;
    }

    public boolean addUser(String username) {
        // verify if a user exists already
        List<BlogUser> existingUsers = userRepository.findByUsername(username);

        if (CollectionUtils.isEmpty(existingUsers)) {
            // return code 201
            userRepository.save(createUser(username));
            return true;
        } else {
            logger.info("User with username {} and id {} already present in the database..", username, existingUsers.getFirst().getUserId());
            // return code 403
            return false;
        }
    }

    public BlogUser findUserByUsername(String username) {
        Optional<BlogUser> optionalBlogUser = Optional.ofNullable(userRepository.findByUsername(username).getFirst());
        if (optionalBlogUser.isPresent()) {
            return optionalBlogUser.get();
        }
        String errorMsg = String.format("The given user %s was not found. Please register before continuing.", username);
        logger.error(errorMsg);
        throw new RuntimeException(errorMsg);
    }

    public boolean addComment(CommentDto comment) {
        BlogPostComment blogPostComment = createComment(comment);
        commentRepository.save(blogPostComment);
        return true;
    }

    public boolean addPost(BlogPostDto blogPostDto) {
        BlogPost blogPost = new BlogPost();
        blogPost.setBlogUser(findUserByUsername(blogPostDto.username()));
        blogPost.setTitle(blogPostDto.title());
        blogPost.setTextContent(blogPostDto.text());
        blogPostRepository.save(blogPost);
        return true;
    }

    private BlogPostComment createComment(CommentDto commentDto) {
        BlogPostComment blogPostComment = new BlogPostComment();
        blogPostComment.setText(commentDto.text());
        blogPostComment.setBlogUser(findUserByUsername(commentDto.username()));
        //blogPostComment.set
        return blogPostComment;
    }

    private BlogUser createUser(String username) {
        BlogUser blogUser = new BlogUser();
        blogUser.setUsername(username);
        blogUser.setCreatedAt(LocalDateTime.now().format(DATE_TIME_FORMATTER));
        return blogUser;
    }

}