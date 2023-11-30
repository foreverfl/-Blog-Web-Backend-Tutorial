package dao;

import model.Comment;
import java.util.List;

public interface CommentDao {

    List<Comment> findAll();

    Comment findById(Long commentId);

    void save(Comment comment);

    void delete(Long id);

}