# MongoDB 客户端使用

## 1. 使用

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



## 2. 查询

### 2.1 集合查询

```java
restaurants.find().forEach((Consumer<Document>) (doc) -> System.out.println(doc.toJson()));
```

### 2.2 筛选查询

#### 2.2.1 无筛选

```java
restaurants.find().forEach((Consumer<Document>) (doc) -> System.out.println(doc.toJson()));
```

#### 2.2.2 条件筛选

方式一：

```java
// 筛选 2 <= stars < 5 and categories.contains(Bakery)
restaurants.find(new Document("stars", new Document("$gte", 2).append("$lt", 5))
        .append("categories", "Bakery")).forEach((Consumer<Document>) (doc) -> System.out.println(doc.toJson()));
```

方式二：使用 `Filters `辅助方法

```java
// 筛选 2 <= stars < 5 and categories.contains(Bakery)
restaurants.find(and(gte("stars", 2), lt("stars", 5), eq("categories", "Bakery")))
        .forEach((Consumer<Document>) (doc) -> System.out.println(doc.toJson()));
```

#### 2.2.3 设置结果集字段

方式一：

```java
// 返回name、stars、categories字段
restaurants.find(and(gte("stars", 2), lt("stars", 5), eq("categories", "Bakery")))
        .projection(new Document("name", 1)
                .append("stars", 1)
                .append("categories", 1)
                .append("_id", 0))
        .forEach((Consumer<Document>) (doc) -> System.out.println(doc.toJson()));
```

方式二：使用 `Projections` 辅助工具类

```java
// 返回name、stars、categories字段
restaurants.find(and(gte("stars", 2), lt("stars", 5), eq("categories", "Bakery")))
        .projection(fields(include("name", "stars", "categories"), excludeId()))
        .forEach((Consumer<Document>) (doc) -> System.out.println(doc.toJson()));
```

#### 2.2.4 排序

```java
// 根据name升序排列
restaurants.find(and(gte("stars", 2), lt("stars", 5), eq("categories", "Bakery")))
        .sort(Sorts.ascending("name"))
        .forEach((Consumer<Document>) (doc) -> System.out.println(doc.toJson()));
```

## 3. 更新

### 3.1 新增文档

#### 3.1.1 新增单个文档

```java
Document document = new Document("name", "Café Con Leche")
        .append("contact", new Document("phone", "228-555-0149")
                .append("email", "cafeconleche@example.com")
                .append("location", Arrays.asList(-73.92502, 40.8279556)))
        .append("stars", 3)
        .append("categories", Arrays.asList("Bakery", "Coffee", "Pastries"));

restaurants.insertOne(document);
```

> 注意：如果不存在顶级 `_id` 字段，将会自动添加 `_id` 字段。
>

#### 3.1.2 新增多个文档

```java
Document doc1 = new Document("name", "Amarcord Pizzeria")
        .append("contact", new Document("phone", "264-555-0193")
                .append("email", "amarcord.pizzeria@example.net")
                .append("location",Arrays.asList(-73.88502, 40.749556)))
        .append("stars", 2)
        .append("categories", Arrays.asList("Pizzeria", "Italian", "Pasta"));


Document doc2 = new Document("name", "Blue Coffee Bar")
        .append("contact", new Document("phone", "604-555-0102")
                .append("email", "bluecoffeebar@example.com")
                .append("location",Arrays.asList(-73.97902, 40.8479556)))
        .append("stars", 5)
        .append("categories", Arrays.asList("Coffee", "Pastries"));

List<Document> documents = new ArrayList<Document>();
documents.add(doc1);
documents.add(doc2);

restaurants.insertMany(documents);
```

### 3.2 更新文档

#### 3.2.1 更新单个文档

```java
// 更新stars、contact.phone字段，并附加更新时间字段lastModified
restaurants.updateOne(
        eq("_id", new ObjectId("57506d62f57802807471dd41")),
        combine(set("stars", 1), set("contact.phone", "228-555-9999"), currentDate("lastModified")));
```

#### 3.2.2 更新多个文档

```java
// 更新stars等于2的修改为0，并附加更新时间字段lastModified
restaurants.updateMany(
        eq("stars", 2),
        combine(set("stars", 0), currentDate("lastModified")));
```

#### 3.2.3 设置更新选项

```java
// #upsert() true:存在则更新，不存在则新增
restaurants.updateOne(
        eq("_id", 1),
        combine(set("name", "Fresh Breads and Tulips"), currentDate("lastModified")),
        new UpdateOptions().upsert(true).bypassDocumentValidation(true));
```

### 3.3 替换文档

#### 3.3.1 替换单个文档

```java
restaurants.replaceOne(
        eq("_id", new ObjectId("57506d62f57802807471dd41")),
        new Document("name", "Green Salads Buffet")
                .append("contact", "TBD")
                .append("categories", Arrays.asList("Salads", "Health Foods", "Buffet")));
```

#### 3.3.2 替换单个文档(添加更新选项)

```java
restaurants.replaceOne(
        eq("name", "Orange Patisserie and Gelateria"),
        new Document("stars", 5)
                .append("contact", "TBD")
                .append("categories", Arrays.asList("Cafe", "Pastries", "Ice Cream")),
        new UpdateOptions().upsert(true).bypassDocumentValidation(true));
```

### 3.4 删除文档

#### 3.4.1 删除单个文档

```java
restaurants.deleteOne(eq("_id", new ObjectId("57506d62f57802807471dd41")));
```

#### 3.4.2 删除多个文档

```java
restaurants.deleteMany(eq("stars", 4));
```

## 4. 索引

### 4.1 获取集合索引列表

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



### 4.2 创建索引

#### 4.2.1 递增索引

```java
// 简单递增索引
collection.createIndex(Indexes.ascending("activityId"));
// 混合递增索引
activity.createIndex(Indexes.ascending("activityType", "activityId"));
```

#### 4.2.2 递减索引

```java
// 简单递减索引
collection.createIndex(Indexes.descending("activityId"));
// 混合递减索引
activity.createIndex(Indexes.descending("activityType", "activityId"));
```

#### 4.2.3 混合索引

```java
activity.createIndex(Indexes.compoundIndex(Indexes.ascending("field1"), Indexes.descending("field2")));
```

#### 4.2.4 文本索引

```java
activity.createIndex(Indexes.text("title"));
```

#### 4.2.5 哈希索引

```java
activity.createIndex(Indexes.hashed("_id"));
```



### 4.3 索引选项

#### 4.3.1 唯一索引

```java
IndexOptions indexOptions = new IndexOptions().unique(true);
activity.createIndex(Indexes.ascending("activityType", "activityId"), indexOptions);
```

#### 4.3.2 局部索引

```java
IndexOptions partialFilterIndexOptions = new IndexOptions()
        .partialFilterExpression(Filters.exists("contact.email"));
activity.createIndex(Indexes.descending("activityType", "activityId"), partialFilterIndexOptions);
```

## 5. 聚合

### 5.1 聚合操作

-  利用 `Aggregates` 辅助工具类

```java
// 首先筛选中categories中包含Bakery，然后根据stars分组聚合统计数量
restaurants.aggregate(
        Arrays.asList(
                Aggregates.match(Filters.eq("categories", "Bakery")),
                Aggregates.group("$stars", Accumulators.sum("count", 1))
        )
).forEach((Consumer<Document>) (doc) -> System.out.println(doc.toJson()));
```

- 手动构建表达式文档

```java
// 只筛选中name、categories的第一个元素
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
```

