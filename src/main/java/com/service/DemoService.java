package com.service;

import com.dao.DemoRepository;
import com.entity.DemoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    @Autowired
    private DemoRepository demoRepository;


    public DemoEntity getDemoEntityByString2(String string2) {
        return demoRepository.findFirstByString2(string2);
    }

}
