package com.example.mydemo.mapper;

import com.example.mydemo.model.Question;
import com.example.mydemo.model.QuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface QuestionExtMapper {
    int incView(Question record);
    int inCommentCount(Question record);
}