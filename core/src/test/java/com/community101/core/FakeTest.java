package com.community101.core;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by chenbojian on 7/16/15.
 */
public class FakeTest {
    @Test
    public void should_not_pass() {
        System.out.println("hello, this is a fake test in core!");
        assertThat(1, is(2));
    }
}
