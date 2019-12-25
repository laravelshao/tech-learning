package com.laravelshao.learning.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

/**
 * Mongo 读操作测试
 *
 * @author qinghua.shao
 * @date 2019/12/24
 * @since 1.0.0
 */
public class MongoReadTest {

    public static void main(String[] args) {

        // 创建连接
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase demoDB = mongoClient.getDatabase("demo");
        MongoCollection<Document> restaurants = demoDB.getCollection("restaurants");

        // 查询集合
        /**
         * 需要强制转换原因
         *
         * See <a href="https://stackoverflow.com/questions/45855131/using-foreach-on-documents-in-mongodb">Using forEach on Documents in MongoDB</a>.
         */
        //restaurants.find().forEach((Consumer<Document>) (doc) -> System.out.println(doc.toJson()));

        // 不筛选
        //restaurants.find(new Document()).forEach((Consumer<Document>) (doc) -> System.out.println(doc.toJson()));

        // 条件筛选 2 <= stars < 5 and categories.contains(Bakery)
        //// 方式一
        //restaurants.find(new Document("stars", new Document("$gte", 2).append("$lt", 5))
        //        .append("categories", "Bakery")).forEach((Consumer<Document>) (doc) -> System.out.println(doc.toJson()));
        //// 方式二
        //restaurants.find(and(gte("stars", 2), lt("stars", 5), eq("categories", "Bakery")))
        //        .forEach((Consumer<Document>) (doc) -> System.out.println(doc.toJson()));

        // 设置结果集字段
        //// 方式一
        //restaurants.find(and(gte("stars", 2), lt("stars", 5), eq("categories", "Bakery")))
        //        .projection(new Document("name", 1)
        //                .append("stars", 1)
        //                .append("categories", 1)
        //                .append("_id", 0))
        //        .forEach((Consumer<Document>) (doc) -> System.out.println(doc.toJson()));
        //// 方式二
        //restaurants.find(and(gte("stars", 2), lt("stars", 5), eq("categories", "Bakery")))
        //        .projection(fields(include("name", "stars", "categories"), excludeId()))
        //        .forEach((Consumer<Document>) (doc) -> System.out.println(doc.toJson()));

        // 排序
        //restaurants.find(and(gte("stars", 2), lt("stars", 5), eq("categories", "Bakery")))
        //        .sort(Sorts.ascending("name"))
        //        .forEach((Consumer<Document>) (doc) -> System.out.println(doc.toJson()));


    }
}
