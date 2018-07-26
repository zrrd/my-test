package com.example.mytest;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTestApplicationTests {

	@Test
	public void contextLoads() {
		Object[] subs={7,5,3,9,8,4};
		Map<Object, Object> map= ArrayUtils.toMap(subs);
	}

}
