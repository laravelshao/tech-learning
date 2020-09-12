# ElasticSearch

> 倒排索引：https://blog.csdn.net/starzhou/article/details/87519973

## ES核心概念

- `Near Realtime（NRT）`：近实时，两层意思
  - 从写入数据到数据可以被搜索到有一个小延迟（大概`1`秒）
  - 基于 `es` 执行搜索和分析可以达到秒级

- `Cluster`：集群，包含多个节点，节点属于哪个集群可通过配置来决定（集群名称默认是`elasticsearch`），对于中小型应用来说，刚开始一个集群就一个节点很正常
- `Node`：节点，集群中的一个节点，节点也有一个名称（默认是随机分配的），节点名称很重要（在执行运维管理操作的时候），默认节点会去加入一个名称为"`elasticsearch`"的集群，如果直接启动一堆节点，那么它们会自动组成一个 `elasticsearch` 集群，当然一个节点也可以组成一个 `elasticsearch` 集群
- `shard`：单台机器无法存储大量数据，`es` 可以将一个索引中的数据切分为多个 `shard` ，分布在多台服务器上存储。每个 `shard` 都是一个 `lucene index`
  - 横向扩展，数据增加后可以重建具有更多 `shard` 的索引，并将原有数据导入进去
  - 提升吞吐量和性能，数据分布在多个 `shard`，多台服务器上，所有的操作都会在多台服务器上并行分布式执行
- `replica`：任何一个服务器随时可能故障或宕机，此时 `shard` 可能就会丢失，因此可以为每个 `shard` 创建多个 `replica` 副本。 `primary shard`（建立索引时一次设置，不能修改，默认 `5` 个），`replica shard`（随时修改数量，默认 `1` 个），默认每个索引 `10` 个 `shard` ，`5` 个 `primary shard` ，`5` 个 `replica shard` ，最小的高可用配置，是 `2` 台服务器，`es` 限制 `shard` 和 `replica` 不在同一节点上
  - 保证高可用性，一个 `shard` 宕机，数据不丢，可继续提供服务
  - 提升搜索请求的吞吐量和性能

- `Document&field`：文档，`es` 中最小数据单元，一个 `document` 可以是一条客户数据，一条商品分类数据，一条订单数据，通常以 `JSON` 数据结构表示，每个 `index` 下的 `type` 中，都可以去存储多个 `document` 。一个 `document` 里面有多个 `field` ，每个 `field` 就是一个数据字段

- `Index`：索引，包含一堆有相似结构的文档数据，比如可以有一个客户索引，商品分类索引，订单索引，索引有一个名称。一个 `index` 包含很多 `document` ，一个 `index` 就代表了一类类似的或者相同的 `document` 。比如说建立一个 `product index` ，商品索引，里面可能就存放了所有的商品数据，所有的商品 `document`
- `Type`：类型，每个索引里都可以有一个或多个 `type` ， `type` 是 `index` 中的一个逻辑数据分类，一个 `type` 下的 `document` ，都有相同的 `field` ，比如博客系统，有一个索引，可以定义用户数据 `type` ，博客数据 `type` ，评论数据 `type` 

| ElasticSearch | 数据库 |
| ------------- | ------ |
| Document      | 行     |
| Type          | 表     |
| Index         | 库     |



## ES安装

ES启动检查：http://localhost:9200/?pretty

```json
{
  name: "LaravelShaodeMacBook-Pro.local",
  cluster_name: "elasticsearch",
  cluster_uuid: "hKq41f-ZTXGjFA9LM6aacA",
  version: {
    number: "7.5.2",
    build_flavor: "default",
    build_type: "tar",
    build_hash: "8bec50e1e0ad29dad5653712cf3bb580cd1afcdf",
    build_date: "2020-01-15T12:11:52.313576Z",
    build_snapshot: false,
    lucene_version: "8.3.0",
    minimum_wire_compatibility_version: "6.8.0",
    minimum_index_compatibility_version: "6.0.0-beta1"
  },
  tagline: "You Know, for Search"
}
```

Kibana访问地址：http://localhost:5601

## ES基础操作

### 简单集群管理

- 检查ES集群健康状况

```
GET _cluster/health
```

- 检查集群的健康状况

```
GET /_cat/health?v
```

- 查询集群中索引

```
GET /_cat/indices?v
```

- 创建索引

```
PUT /test_index?pretty
```

- 删除索引

```
DELETE /test_index?pretty
```

### 文档操作

#### 基础操作

- 新增文档：`es` 会自动建立 `index` 和 `type` ，无需提前创建，而且 `es` 默认会对 `document` 中每个 `field` 都建立倒排索引，使其可以被搜索

```
PUT /index/type/id JSON数据
```

```json
PUT /ecommerce/product/1
{
    "name" : "gaolujie yagao",
    "desc" :  "gaoxiao meibai",
    "price" :  30,
    "producer" :      "gaolujie producer",
    "tags": [ "meibai", "fangzhu" ]
}

PUT /ecommerce/product/2
{
    "name" : "jiajieshi yagao",
    "desc" :  "youxiao fangzhu",
    "price" :  25,
    "producer" :      "jiajieshi producer",
    "tags": [ "fangzhu" ]
}

PUT /ecommerce/product/3
{
    "name" : "zhonghua yagao",
    "desc" :  "caoben zhiwu",
    "price" :  40,
    "producer" :      "zhonghua producer",
    "tags": [ "qingxin" ]
}
```

- 查询文档

```json
GET /index/type/id
```

- 替换文档：需要添加所有字段进行数据修改

```json
PUT /index/type/id JSON数据
```

- 更新文档

```json
POST /index/type/1/_update
{
  "doc": {
    "name": "jiaqiangban gaolujie yagao"
  }
}
```

- 删除文档

```json
DELETE /index/type/id
```

#### mget批量查询

```json
## 批量查询不同index的document
GET /_mget
{
  "docs": [
    {
      "_index": "ecommerce",
      "_type": "product",
      "_id": 1
    },
    {
      "_index": "ecommerce",
      "_type": "product",
      "_id": 2
    } 
  ]
}

## 批量查询执行index下不同type的document
GET /ecommerce/_mget
{
  "docs": [
    {
      "_type": "product",
      "_id": 1
    },
    {
      "_type": "product",
      "_id": 2
    } 
  ]
}

## 批量查询指定index下指定type的document
GET /ecommerce/product/_mget
{
  "ids": [1,2]
}
```

#### bulk批量操作

可以进行bulk操作的类型：

- delete：删除一个文档，只需要一个json串就可以
- create：PUT /index/type/id/_create，强制创建
- index：普通的put操作，可以是创建文档，也可以是全量替换文档
- update：执行partial update操作

> 特殊说明：bulk api对json有严格限制，每个json串不能换行，只能放一行，并且一个json与另一个json串之间必须有换行



```json
## 初始数据
PUT /test_index/test_type/1
{
  "test_field": "test field",
  "test_field2": "test field2"
}

PUT /test_index/test_type/2
{
  "test_field": "test2",
  "test_field2": "test2"
}

## 批量bulk操作
POST /_bulk
{ "delete": { "_index": "test_index", "_type": "test_type", "_id": "1"} }
{ "create": { "_index": "test_index", "_type": "test_type", "_id": "3"} }
{ "test_field": "test3"}
{ "index": { "_index": "test_index", "_type": "test_type", "_id": "4"} }
{ "test_field":    "replaced test4" }
{"update": { "_index": "test_index", "_type": "test_type", "_id": "2", "retry_on_conflict": 3} }
{ "doc" : {"test_field2" : "bulk test2"} }
```




### 搜索方式

#### query string search

search` 参数都以 `http` 请求的 `query string` 来附加，适用于简单查询，比如 `curl`，对于复杂请求难以构建，生产环境很少使用。

```json
GET /index/type/_search
## 搜索商品名称包含yagao的商品 且 按售价降序
GET /ecommerce/product/_search?q=name:yagao&sort=price:desc
```

- `query DSL`：Domain Specified Language，通过JSON格式构建查询语法，可以构建各种复杂语法

```json
## 查询所有数据
GET /index/type/_search
{
  "query": {
    "match_all": {}
  }
}
## 查询名称包含yagao的商品 且 按照价格降序排序
GET /ecommerce/product/_search
{
    "query" : {
        "match" : {
            "name" : "yagao"
        }
    },
    "sort": [
        { "price": "desc" }
    ]
}

## 分页查询商品
GET /ecommerce/product/_search
{
  "query": { "match_all": {} },
  "from": 1,
  "size": 1
}

## 仅查询商品名称及价格字段
GET /ecommerce/product/_search
{
  "query": { "match_all": {} },
  "_source": ["name", "price"]
}
```

#### query filter

```json
## 搜索商品名称包含yagao 且 售价大于25元的商品
GET /ecommerce/product/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "name": "yagao"
          }
        }
      ],
      "filter": {
        "range": {
          "price": {
            "gt": 25
          }
        }
      }
    }
  }
}
```

`query` 和 `filter` 对比：

- `query`：会去计算每个 `document` 相对于搜索条件的相关度，并按照相关度进行排序，无法 `cache` 结果

- `filter`：仅仅只会按照搜索条件过滤出数据，不计算相关度分数，不需要根据相关度分数排序，同时存在内置的自动 `cache` 最常使用 `filter` 的数据

在进行搜索如果需要将最匹配搜索条件数据先返回则使用 `query`，如果只需要根据条件筛选出一部分数据，不关注其排序，那么用 `filter` 。除非希望越符合这些搜索条件的 `document` 越排在前面返回，那么这些搜索条件要放在 `query` 中；如果你不希望一些搜索条件来影响你的 `document` 排序，那么就放在 `filter` 中即可。

#### full-text search

全文检索会将输入搜索串拆解开，去倒排索引中一一匹配，只要能匹配上任意一个拆解后的单词，就可以作为结果返回

```json
## 会将搜索串"yagao producer"分词去倒排索引中匹配
GET /ecommerce/product/_search
{
    "query" : {
        "match" : {
            "producer" : "yagao producer"
        }
    }
}
```

#### phrase search

短语搜索要求输入的搜索串，必须在指定的字段文本中，完全包含一模一样的，才可以算匹配，才能作为结果返回

```json
## 完全包含搜索串"yagao producer"
GET /ecommerce/product/_search
{
    "query" : {
        "match_phrase" : {
            "producer" : "yagao producer"
        }
    }
}
```

#### highlight search

高亮搜索结果

```json
## 高亮显示"producer"字段
GET /ecommerce/product/_search
{
    "query" : {
        "match" : {
            "producer" : "producer"
        }
    },
    "highlight": {
        "fields" : {
            "producer" : {}
        }
    }
}
```

### 聚合分析

```json
## 1.计算每个tag下的商品数量
GET /ecommerce/product/_search
{
  "aggs": {
    "group_by_tags": {
      "terms": { "field": "tags" }
    }
  }
}
```

默认执行会报错，需要将将文本 `field` 的 `fielddata` 属性设置为 `true`

```json
PUT /ecommerce/_mapping/product
{
  "properties": {
    "tags": {
      "type": "string",
      "fielddata": true
    }
  }
}
```

ES7下执行会报"Types cannot be provided in put mapping requests, unless the include_type_name parameter is set to true"。可在请求url后面添加配置"include_type_name=true"

```json
{
  "error": {
    "root_cause": [
      {
        "type": "illegal_argument_exception",
        "reason": "Types cannot be provided in put mapping requests, unless the include_type_name parameter is set to true."
      }
    ],
    "type": "illegal_argument_exception",
    "reason": "Types cannot be provided in put mapping requests, unless the include_type_name parameter is set to true."
  },
  "status": 400
}
```

```json
PUT /ecommerce/_mapping/product?include_type_name=true
{
  "properties": {
    "tags": {
      "type": "text",
      "fielddata": true
    }
  }
}
```



```json
## 1.计算每个tag下商品的平均价格(先分组再计算价格平均值)
GET /ecommerce/product/_search
{
  "size": 0, 
  "aggs": {
    "group_by_tags": {
      "terms": {
        "field": "tags"
      },
      "aggs": {
        "avg_price": {
          "avg": {
            "field": "price"
          }
        }
      }
    }
  }
}
```

```json
## 1.计算每个tag下商品的平均价格并按照降序排列
GET /ecommerce/product/_search
{
  "size": 0, 
  "aggs": {
    "group_by_tags": {
      "terms": {
        "field": "tags",
        "order": {
          "avg_price": "desc"
        }
      },
      "aggs": {
        "avg_price": {
          "avg": {
            "field": "price"
          }
        }
      }
    }
  }
}
```

```json
## 按照指定的价格范围区间进行分组，在每组内按照tag分组计算平均价格
GET /ecommerce/product/_search
{
  "size": 0, 
  "aggs": {
    "group_by_price": {
      "range": {
        "field": "price",
        "ranges": [
          {
            "from": 0,
            "to": 20
          },
          {
            "from": 20,
            "to": 40
          },
          {
            "from": 40,
            "to": 60
          }
        ]
      },
      "aggs": {
        "group_by_tags": {
          "terms": {
            "field": "tags"
          },
          "aggs": {
            "avg_price": {
              "avg": {
                "field": "price"
              }
            }
          }
        }
      }
    }
  }
}
```

## 基础架构

单node环境下创建index

```json
PUT /test_inde
{
	"settings": {
    "number_of_shards": 3,
    "number_of_replicas": 1
  }
}
```

> 单node环境下，创建一个index，有3个primary shard，3个replica shard，集群status为yellow。此时只会将3个primary shard分配到仅有的一个node上，另外3个replica shard是无法分配的。
>
> 集群可以正常工作，但是一旦出现节点宕机，数据全部丢失且集群不可用，无法承接任何请求。

### document核心元数据

_index：代表一个document存放在那个index中，类似的数据放在一个index中。

_type：代表document属于index中的那个类型，一个index通常会划分为多个type，逻辑上对index中有些许不同的几类数据进行分类，因为一批相同的数据，可能有很多相同的fields，但是可能会有少部分字段不同，比如商品，可以划分为电子商品、生鲜商品、日化商品等。

_id：代表document的唯一标识，与index和type一起，可以唯一标识和定位一个document，可手动指定document的id，也可以不指定而由es自动创建一个id

### document路由

路由算法：shard=hash(routig) % number_of_primary_shards

默认的routing就是_id，也可以发送请求时手动指定一个routing value，例如：

```json
PUT /index/type/id?routing=user_id
```

> 说明：手动指定routing value很有用，可以保证某一类document一定被路由至同一个shard上，后续在进行应用级别的负载均衡，以及提升批量读取性能时，很有帮助作用。

document路由策略也是导致primary shard数量不可变的原因，如果变化会导致找不到document。