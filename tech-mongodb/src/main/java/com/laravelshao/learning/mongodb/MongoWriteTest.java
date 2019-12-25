package com.laravelshao.learning.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.currentDate;
import static com.mongodb.client.model.Updates.set;

/**
 * Mongo 写操作测试
 *
 * @author qinghua.shao
 * @date 2019/12/25
 * @since 1.0.0
 */
public class MongoWriteTest {

    public static void main(String[] args) {

        // 创建连接
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase demoDB = mongoClient.getDatabase("demo");
        MongoCollection<Document> restaurants = demoDB.getCollection("restaurants");

        // 新增单个文档
        //Document document = new Document("name", "Café Con Leche")
        //        .append("contact", new Document("phone", "228-555-0149")
        //                .append("email", "cafeconleche@example.com")
        //                .append("location", Arrays.asList(-73.92502, 40.8279556)))
        //        .append("stars", 3)
        //        .append("categories", Arrays.asList("Bakery", "Coffee", "Pastries"));
        //
        //restaurants.insertOne(document);

        // 新增多个文档
        //Document doc1 = new Document("name", "Amarcord Pizzeria")
        //        .append("contact", new Document("phone", "264-555-0193")
        //                .append("email", "amarcord.pizzeria@example.net")
        //                .append("location",Arrays.asList(-73.88502, 40.749556)))
        //        .append("stars", 2)
        //        .append("categories", Arrays.asList("Pizzeria", "Italian", "Pasta"));
        //
        //
        //Document doc2 = new Document("name", "Blue Coffee Bar")
        //        .append("contact", new Document("phone", "604-555-0102")
        //                .append("email", "bluecoffeebar@example.com")
        //                .append("location",Arrays.asList(-73.97902, 40.8479556)))
        //        .append("stars", 5)
        //        .append("categories", Arrays.asList("Coffee", "Pastries"));
        //
        //List<Document> documents = new ArrayList<Document>();
        //documents.add(doc1);
        //documents.add(doc2);
        //
        //restaurants.insertMany(documents);

        // 更新单个文档
        //restaurants.updateOne(
        //        eq("_id", new ObjectId("57506d62f57802807471dd41")),
        //        combine(set("stars", 1), set("contact.phone", "228-555-9999"), currentDate("lastModified")));

        // 更新多个文档
        //restaurants.updateMany(
        //        eq("stars", 2),
        //        combine(set("stars", 0), currentDate("lastModified")));

        // 设置更新选项
        //// #upsert() true:存在则更新，不存在则新增
        //restaurants.updateOne(
        //        eq("_id", 1),
        //        combine(set("name", "Fresh Breads and Tulips"), currentDate("lastModified")),
        //        new UpdateOptions().upsert(true).bypassDocumentValidation(true));

        // 替换文档
        //restaurants.replaceOne(
        //        eq("_id", new ObjectId("57506d62f57802807471dd41")),
        //        new Document("name", "Green Salads Buffet")
        //                .append("contact", "TBD")
        //                .append("categories", Arrays.asList("Salads", "Health Foods", "Buffet")));

        // 替换文档(添加更新选项)
        //restaurants.replaceOne(
        //        eq("name", "Orange Patisserie and Gelateria"),
        //        new Document("stars", 5)
        //                .append("contact", "TBD")
        //                .append("categories", Arrays.asList("Cafe", "Pastries", "Ice Cream")),
        //        new UpdateOptions().upsert(true).bypassDocumentValidation(true));

        // 删除文档
        //restaurants.deleteOne(eq("_id", new ObjectId("57506d62f57802807471dd41")));

        // 删除多个文档
        //restaurants.deleteMany(eq("stars", 4));
    }
}
