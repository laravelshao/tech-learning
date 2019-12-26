package com.laravelshao.learning.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Mongo 聚合测试
 *
 * @author qinghua.shao
 * @date 2019/12/26
 * @since 1.0.0
 */
public class MongoAggregationTest {

    public static void main(String[] args) {

        // 创建连接
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase demoDB = mongoClient.getDatabase("demo");
        MongoCollection<Document> restaurants = demoDB.getCollection("restaurants");

        restaurants.aggregate(
                Arrays.asList(
                        Aggregates.match(Filters.eq("categories", "Bakery")),
                        Aggregates.group("$stars", Accumulators.sum("count", 1))
                )
        ).forEach((Consumer<Document>) (doc) -> System.out.println(doc.toJson()));

        restaurants.aggregate(
                Arrays.asList(
                        Aggregates.project(
                                Projections.fields(
                                        Projections.excludeId(),
                                        Projections.include("name"),
                                        Projections.computed(
                                                "firstCategory",
                                                new Document("$arrayElemAt", Arrays.asList("$categories", 0))
                                        )
                                )
                        )
                )
        ).forEach((Consumer<Document>) (doc) -> System.out.println(doc.toJson()));
    }
}
