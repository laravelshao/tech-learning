# MongoDB 客户端使用

## 使用

- 创建连接

```java
MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
```

- 获取数据库

```java
MongoDatabase demoDB = mongoClient.getDatabase("demo");
```

- 创建集合

```java
demoDB.createCollection("javaclient");
```

- 删除集合

```java
MongoCollection<Document> collection = demoDB.getCollection("javaclient");
collection.drop();
```

## 索引

### 获取集合索引列表

```java
for (Document doc : activity.listIndexes()) {
    System.out.println(doc.toJson());
}
```

```json
// 默认索引
{"v": 2, "key": {"_id": 1}, "name": "_id_", "ns": "demo.activity"}
// 混合递增索引
{"v": 2, "key": {"activityType": 1, "activityId": 1}, "name": "activityType_1_activityId_1", "ns": "demo.activity"}
// 文本索引
{"v": 2, "key": {"_fts": "text", "_ftsx": 1}, "name": "title_text", "ns": "demo.activity", "weights": {"title": 1}, "default_language": "english", "language_override": "language", "textIndexVersion": 3}
// 哈希索引
{"v": 2, "key": {"_id": "hashed"}, "name": "_id_hashed", "ns": "demo.activity"}
```



### 创建索引

#### 递增索引

```java
// 简单递增索引
collection.createIndex(Indexes.ascending("activityId"));
// 混合递增索引
activity.createIndex(Indexes.ascending("activityType", "activityId"));
```

#### 递减索引

```java
// 简单递减索引
collection.createIndex(Indexes.descending("activityId"));
// 混合递减索引
activity.createIndex(Indexes.descending("activityType", "activityId"));
```

#### 混合索引

```java
activity.createIndex(Indexes.compoundIndex(Indexes.ascending("field1"), Indexes.descending("field2")));
```

#### 文本索引

```java
activity.createIndex(Indexes.text("title"));
```

#### 哈希索引

```java
activity.createIndex(Indexes.hashed("_id"));
```



### 索引选项

#### 唯一索引

```java
IndexOptions indexOptions = new IndexOptions().unique(true);
activity.createIndex(Indexes.ascending("activityType", "activityId"), indexOptions);
```

#### 局部索引

```java
IndexOptions partialFilterIndexOptions = new IndexOptions()
        .partialFilterExpression(Filters.exists("contact.email"));
activity.createIndex(Indexes.descending("activityType", "activityId"), partialFilterIndexOptions);
```