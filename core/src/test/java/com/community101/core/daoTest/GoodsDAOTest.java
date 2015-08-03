package com.community101.core.daoTest;

import com.community101.core.Goods;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springTest.xml")
@TransactionConfiguration
@Transactional
public class GoodsDAOTest {
    @Autowired
    private GoodsDAO goodsDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DataSource dataSource;

    @Transactional
    @Rollback
    @Test
    public void should_return_correct_number_of_goods(){
        List<Goods> goodsList=goodsDAO.goodsList();
        assertEquals(5,goodsList.size());
        assertEquals("cookie",goodsList.get(1).getName());
    }

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

//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        List<Goods> goodses = jdbcTemplate.queryForList("SELECT * FROM GOODS WHERE GOODS.name='test_name'", Goods.class);
        List<Goods> goodses = sessionFactory.getCurrentSession()
                .createQuery("from Goods where name='test_name'")
                .list();
        assertThat(goodses.size(), is(1));

    }

    @Transactional
    @Rollback
    @Test
    public void should_find_goods_by_id() {
        Goods goods = goodsDAO.findGoodsById(0);
        assertNull(goods);
    }
}
