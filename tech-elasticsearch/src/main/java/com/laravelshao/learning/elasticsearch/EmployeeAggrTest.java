package com.laravelshao.learning.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.Iterator;
import java.util.Map;

/**
 * es 聚合查询测试
 *
 * @author qinghua.shao
 * @date 2020/10/3
 * @since 1.0.0
 */
public class EmployeeAggrTest {

    public static void main(String[] args) throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // 执行聚合查询操作
        executeAggrSearch(client);

        client.close();
    }

    /**
     * 执行聚合查询操作
     *
     * @param client
     */
    private static void executeAggrSearch(RestHighLevelClient client) throws Exception {

        /**
         * 聚合查询要求：
         *1.首先按照 country 国家来进行分组
         *2.然后在每个 country 分组内，再按照入职年限进行分组
         *3.最后计算每个分组内的平均薪资
         */
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().aggregation(
                AggregationBuilders.terms("group_by_country").field("country")
                        .subAggregation(AggregationBuilders.dateHistogram("group_by_join_date").field("join_date").calendarInterval(DateHistogramInterval.YEAR)
                                .subAggregation(AggregationBuilders.avg("avg_salary").field("salary"))
                        )
        );

        SearchRequest searchRequest = new SearchRequest().source(searchSourceBuilder).indices("employee");

        // 执行查询
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        Map<String, Aggregation> aggrMap = searchResponse.getAggregations().asMap();

        Terms groupByCountry = (Terms) aggrMap.get("group_by_country");
        Iterator<? extends Terms.Bucket> groupByCountryBucketIterator = groupByCountry.getBuckets().iterator();
        while (groupByCountryBucketIterator.hasNext()) {
            Terms.Bucket groupByCountryBucket = groupByCountryBucketIterator.next();
            System.out.println(groupByCountryBucket.getKey() + ":" + groupByCountryBucket.getDocCount());
            Histogram groupByJoinDate = (Histogram) groupByCountryBucket.getAggregations().asMap().get("group_by_join_date");
            Iterator<Histogram.Bucket> groupByJoinDateBucketIterator = (Iterator<Histogram.Bucket>) groupByJoinDate.getBuckets().iterator();
            while (groupByJoinDateBucketIterator.hasNext()) {
                Histogram.Bucket groupByJoinDateBucket = groupByJoinDateBucketIterator.next();
                System.out.println(groupByJoinDateBucket.getKey() + ":" + groupByJoinDateBucket.getDocCount());

                Avg avg = (Avg) groupByJoinDateBucket.getAggregations().asMap().get("avg_salary");
                System.out.println(avg.getValue());
            }
        }

        SearchHit[] searchHits = searchResponse.getHits().getHits();
        for (int i = 0; i < searchHits.length; i++) {
            System.out.println(searchHits[i].getSourceAsString());
        }
    }
}
