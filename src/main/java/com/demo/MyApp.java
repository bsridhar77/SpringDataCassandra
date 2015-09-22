package com.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.demo.model.User;


@Configuration
@PropertySource(value = { "classpath:cassandra.properties" })
public class MyApp {

	
	 
	 private static final Logger LOG = LoggerFactory.getLogger(MyApp.class); 
	 
	 private static Cluster cluster; 
	 private static Session session; 
	 
	 public static void main(String[] args) { 
	 
	  cluster = Cluster.builder().addContactPoints("localhost").build(); 
	 
	   session = cluster.connect("myspace"); 
	 
	   CassandraOperations cassandraOps = new CassandraTemplate(session); 
	 
	 /*  cassandraOps.insert(new User(100, "Penny", "Hofstadter"));
	   cassandraOps.insert(new User(200, "Sheldon", "Cooper")); */
	 
	   //Fetch One Record By user_id using CQL
	   Select select = QueryBuilder.select().from("users");
	   select.where(QueryBuilder.eq("user_id", 300));

	   User p = cassandraOps.selectOne(select, User.class);
	   
	   
	   System.out.println(String.format("Found User with FirstName [%s] for id [%s]", p.getFname(), p.getUser_id()));
	   

	   
	   //Fetch One Record using CQL
	   String cqlOne = "select * from users where user_id = 300";

	   User user = cassandraOps.selectOne(cqlOne, User.class);
	   System.out.println(String.format("Found Again User with Name [%s] for id [%s]", user.getFname(), user.getUser_id()));
	   
	   
	   //Fetch All Records using CQL
	   
	   String cqlAll = "select * from users";

	   List<User> results = cassandraOps.select(cqlAll, User.class);
	   for (User userObj : results) {
		   System.out.println(String.format("Found People with Name [%s] for id [%s]", userObj.getFname(), userObj.getUser_id()));
	   }
	   
	   
	   //LOG.info("Output is: " + user_id); 
	 
	   //cassandraOps.truncate("users"); 
	 
	 } 
	} 
	

