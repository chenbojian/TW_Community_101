package com.community101.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by chenbojian on 7/17/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-servlet.xml", "classpath:applicationContext.xml"})
@WebAppConfiguration
public class GoodsManageControllerTest {

    @Autowired
    private GoodsManageController goodsManageController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(goodsManageController).build();
    }

    @Test
    @Transactional
    @Rollback
    public void should_post_data_successful() throws Exception {
        MockMultipartFile pictureFile =
                new MockMultipartFile("pictureFile", "testfile.jpg", "text/plain", "this is test\nnew test\n".getBytes());
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/input-goods")
                .file(pictureFile)
                .param("name", "testfile")
                .param("category.id", "1")
                .param("floatPrice", "1.10")
                .param("description", "foods"))
                .andExpect(status().isOk());
    }
}