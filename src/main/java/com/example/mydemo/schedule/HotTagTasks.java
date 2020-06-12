package com.example.mydemo.schedule;

import com.example.mydemo.cache.HotTagCache;
import com.example.mydemo.mapper.QuestionMapper;
import com.example.mydemo.model.Question;
import com.example.mydemo.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Slf4j
public class HotTagTasks {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void hotTagSchedule() {
        int offset = 0;
        int limit = 20;
        log.info("hotTagSchedule start {}", new Date());
        List<Question> list = new ArrayList<>();

        Map<String, Integer> priorities = new HashMap<>();
        Map<String, Integer> tagNum = new HashMap<>();
        while (offset == 0 || list.size() == limit) {
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, limit));
            for (Question question : list) {
                String[] tags = StringUtils.split(question.getTag(), ",");
                for (String tag : tags) {
                    Integer priority = priorities.get(tag);
                    Integer num=tagNum.get(tag);
                    /*System.out.println("priority:"+priority+" .... "+"tag:"+tag);*/
                    if (priority != null) {
                        priorities.put(tag, priority + 5 + question.getCommentCount());
                    } else {
                        priorities.put(tag, 5 + question.getCommentCount());
                    }
                    if(num != null){
                        tagNum.put(tag,num+1);
                    }else{
                        tagNum.put(tag,1);
                    }
                }
            }
            offset += limit;
        }
       /*Iterator<Map.Entry<String,Integer>> iterator=tagNum.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,Integer> entry=iterator.next();
            System.out.println("key:"+entry.getKey()+"value:"+entry.getValue());
        }
        Iterator<Map.Entry<String,Integer>> iterator1=priorities.entrySet().iterator();
        while(iterator1.hasNext()){
            Map.Entry<String,Integer> entry1=iterator1.next();
            System.out.println("key:"+entry1.getKey()+"value:"+entry1.getValue());
        }*/
        hotTagCache.updateTags(priorities);
        hotTagCache.setNums(tagNum);
        log.info("hotTagSchedule stop {}", new Date());
    }
}
