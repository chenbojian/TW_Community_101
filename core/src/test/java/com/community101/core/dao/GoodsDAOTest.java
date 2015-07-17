package com.community101.core.dao;

import com.community101.core.Goods;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@TransactionConfiguration
@Transactional
public class GoodsDAOTest {
    @Autowired
    private GoodsDAO goodsDAO;

    private DataSource dataSource;

    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    @Rollback
    @Test
    public void should_return_correct_number_of_goods(){
        List<Goods> goodsList=goodsDAO.goodsList();
        assertEquals(5,goodsList.size());
        assertEquals("cookie",goodsList.get(1).getName());
    }

    private JdbcTemplate jdbcTemplate;

    @Test
    @Rollback
    public void should_add_goods_correct() {
        Goods goods= new Goods();
        goods.setName("test_name");
        goods.setPrice(100);
        goods.setStatus("ok");
        goods.setPictureUrl("url");
        goods.setDescription("aaaa");
        goodsDAO.save(goods);
        sessionFactory.getCurrentSession().flush();

//        List<Goods> goodses = jdbcTemplate.queryForList("SELECT * FROM GOODS WHERE GOODS.name='test_name'", Goods.class);
        List<Goods> goodses = sessionFactory.getCurrentSession()
                .createQuery("from Goods where name='test_name'")
                .list();
        assertThat(goodses.size(), is(1));

    }
}
