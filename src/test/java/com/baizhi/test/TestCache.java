package com.baizhi.test;


import com.baizhi.App;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class TestCache {

    @Autowired
    private UserService service;

    @Test
    public void testQueryAll() {
        for (int i = 0; i < 3; i++) {
            service.queryAll(null);
        }

    }
}
