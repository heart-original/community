package com.example.mydemo.mapper;

import com.example.mydemo.dto.QuestionQueryDTO;
import com.example.mydemo.model.Question;
import com.example.mydemo.model.QuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface QuestionExtMapper {
    int incView(Question record);
    int inCommentCount(Question record);
    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}