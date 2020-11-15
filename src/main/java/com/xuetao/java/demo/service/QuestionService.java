package com.xuetao.java.demo.service;

import com.alibaba.fastjson.JSON;
import com.xuetao.java.demo.dto.PaginationDTO;
import com.xuetao.java.demo.dto.QuestionDTO;
import com.xuetao.java.demo.mapper.QuestionMapper;
import com.xuetao.java.demo.mapper.UserMapper;
import com.xuetao.java.demo.model.Question;
import com.xuetao.java.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: TestRookie * @Date: 2020/11/8 21:51 * @Description: *
 */
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO list(Integer page, Integer size) {
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
           User user = userMapper.findById(question.getCreator());
           QuestionDTO questionDTO = new QuestionDTO();
           //将question属性拷贝到questionDTO对象
           BeanUtils.copyProperties(question,questionDTO);
           BeanUtils.copyProperties(questionDTO,paginationDTO);
           questionDTO.setUser(user);
           questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);

        return paginationDTO;
        //return questionDTOList;
    }
}
