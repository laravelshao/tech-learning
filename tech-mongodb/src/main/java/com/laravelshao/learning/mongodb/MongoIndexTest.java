package com.laravelshao.learning.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.Document;

/**
 * Mongo 索引使用
 *
 * @author qinghua.shao
 * @date 2019/12/24
 * @since 1.0.0
 */
public class MongoIndexTest {

    public static void main(String[] args) {

        // 创建连接
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase demoDB = mongoClient.getDatabase("demo");
        MongoCollection<Document> activity = demoDB.getCollection("activity");

        // 获取集合索引列表
        for (Document doc : activity.listIndexes()) {
            System.out.println(doc.toJson());
        }

        // 递增索引(单个或多个)
        //activity.createIndex(Indexes.ascending("activityType", "activityId"));

        // 混合索引
        //activity.createIndex(Indexes.compoundIndex(Indexes.ascending("field1"), Indexes.descending("field2")));

        // 文本索引
        //activity.createIndex(Indexes.text("title"));

        // 哈希索引
        //activity.createIndex(Indexes.hashed("_id"));

        // 唯一索引
        //IndexOptions indexOptions = new IndexOptions().unique(true);
        //activity.createIndex(Indexes.ascending("activityType", "activityId"), indexOptions);

        // 局部索引
        //IndexOptions partialFilterIndexOptions = new IndexOptions()
        //        .partialFilterExpression(Filters.exists("contact.email"));
        //activity.createIndex(Indexes.descending("activityType", "activityId"), partialFilterIndexOptions);
    }
}
