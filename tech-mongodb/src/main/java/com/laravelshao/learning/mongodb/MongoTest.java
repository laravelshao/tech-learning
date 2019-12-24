package com.laravelshao.learning.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @author qinghua.shao
 * @date 2019/12/24
 * @since 1.0.0
 */
public class MongoTest {

    public static void main(String[] args) {

        // 创建连接
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        MongoDatabase demoDB = mongoClient.getDatabase("demo");

        // 创建集合
        //demoDB.createCollection("restaurants");

        // 删除集合
        //MongoCollection<Document> collection = demoDB.getCollection(" restaurants");
        //collection.drop();

        // 获取集合名称列表
        for (String name : demoDB.listCollectionNames()) {
            System.out.println(name);
        }

    }
}
