//package com.es;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.awifi.cloudnative.container.common.basic.ResponseJson;
//import com.awifi.cloudnative.container.common.basic.Page;
//import com.awifi.cloudnative.container.common.enums.ErrorCode;
//import com.awifi.cloudnative.container.common.redis.RedisTemplateUtil;
//import com.awifi.cloudnative.container.component.ElasticsearchTemplate;
//import com.awifi.cloudnative.container.configuration.ElasticsearchProperties;
//import com.awifi.cloudnative.container.data.permission.RoleDataPermissionManager;
//import com.awifi.cloudnative.container.enterprise.api.bean.ProjectDto;
//import com.awifi.cloudnative.container.enterprise.api.feign.FeignProjectService;
//import com.awifi.cloudnative.container.enums.EncryptTypeEnum;
//import com.awifi.cloudnative.container.manage.provider.constant.K8sBaseConstants;
//import com.awifi.cloudnative.container.manage.provider.entity.DO.ClusterManage;
//import com.awifi.cloudnative.container.manage.provider.entity.dto.LogDto;
//import com.awifi.cloudnative.container.manage.provider.mapper.K8sClusterInfoMapper;
//import com.awifi.cloudnative.container.manage.provider.service.K8sClusterLogService;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.HttpHost;
//import org.apache.lucene.queryparser.classic.QueryParser;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.index.query.*;
//import org.elasticsearch.search.aggregations.Aggregation;
//import org.elasticsearch.search.aggregations.AggregationBuilders;
//import org.elasticsearch.search.aggregations.Aggregations;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.*;
//
//
///**
// * @Author lyuf
// * @Date 2022/3/14 10:29
// * @Version 1.0
// */
//@Service
//@Slf4j
//public class EsLogServiceImpl implements K8sClusterLogService {
//
//    @Autowired
//    private RedisTemplateUtil redisTemplateUtil;
//
//    @Autowired
//    private K8sClusterInfoMapper k8sClusterInfoMapper;
//
//    @Autowired
//    private RoleDataPermissionManager roleDataPermissionManager;
//
//
//    // es ?????? indexName
//    String topics = "k8s-*";
//
//    @Override
//    public ResponseJson queryLogList(LogDto logDTO) {
//        ResponseJson result = new ResponseJson();
//        String userName = "";
//        String pwd = "";
//        String ip = "";
//        if (logDTO.getClusterId() != null) {
//            QueryWrapper<ClusterManage> clusterManageQueryWrapper = new QueryWrapper<>();
//            clusterManageQueryWrapper.eq("cluster_id", logDTO.getClusterId());
//            List<ClusterManage> clusterManages = k8sClusterInfoMapper.selectList(clusterManageQueryWrapper);
//            String clusterUrl = clusterManages.get(0).getClusterUrl();
//            ip = clusterUrl.substring(8, clusterUrl.length() - 4) + "30756";
//        } else {
//            log.error("??????id?????????");
//            result.setErrorMsg("??????id?????????");
//            result.setErrorCode(ErrorCode.CLUSTER_NOT_EXIST.getCode());
//            return result;
//        }
//        ElasticsearchTemplate elasticsearchTemplate = this.esTemplateUtil(ip, userName, pwd);
//        try {
//            //????????????????????????????????????
//            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//            RangeQueryBuilder rangeBuilder = QueryBuilders.rangeQuery("@timestamp");
//
//            // ????????????
////            String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
////            rangeBuilder.to(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowTime).getTime());
//            // 12??????????????????
////            Calendar c = Calendar.getInstance();
////            c.setTime(new Date());
////            String durationAlias = logDTO.getDurationAlias();
////            Integer time = Integer.valueOf(durationAlias.substring(0, durationAlias.length() - 1));
////            if (durationAlias.endsWith("d")) {
////                c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) - (time * 24));
////            } else if (durationAlias.endsWith("m")) {
////                c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) - time);
////            } else if (durationAlias.endsWith("h")) {
////                c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) - time);
////            }
////            String beforTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
////            rangeBuilder.from(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beforTime).getTime());
////            log.info(beforTime + "->" + nowTime);
//            // ????????????
//            long lte = System.currentTimeMillis();
//            if (logDTO.getEndTime() != null) {
//                lte = logDTO.getEndTime();
//            }
//            // ????????????
//            long gte = System.currentTimeMillis() - 43200000;
//            if (logDTO.getStartTime() != null) {
//                gte = logDTO.getStartTime();
//            }
//            log.info(gte + "->" + lte);
//
//            rangeBuilder.from(gte);
//            rangeBuilder.to(lte);
//            //????????????????????????????????????
//            boolQueryBuilder.filter(rangeBuilder);
//
//            if (logDTO.getContainerNames() != null) {
//                List<String> containerNames = Arrays.asList(logDTO.getContainerNames().split(","));
//                boolQueryBuilder.must(QueryBuilders.termsQuery("kubernetes.container_name", containerNames));
////                boolQueryBuilder.must(QueryBuilders.matchQuery("kubernetes.container_name", containerName));
//            }
//            if (logDTO.getKeyWord() != null) {
//                String keyWords = QueryParser.escape(logDTO.getKeyWord());
//                // ????????????
////                boolQueryBuilder.must(QueryBuilders.wildcardQuery("log", "*" + keyWords + "*"));
//                MatchQueryBuilder log = QueryBuilders.matchQuery("log", keyWords);
//                log.fuzziness("auto");
//                boolQueryBuilder.filter(log);
//            }
//            if (logDTO.getPodNames() != null) {
//                List<String> podNames = Arrays.asList(logDTO.getPodNames().split(","));
//                boolQueryBuilder.must(QueryBuilders.termsQuery("kubernetes.pod_name", podNames));
//            }
//
//            List<String> indices = new ArrayList<>();
//            indices.add(topics);
//            List<Map<String, Object>> list = elasticsearchTemplate.queryByPage(indices, logDTO.getPageSize(), logDTO.getPageNo(), "@timestamp", boolQueryBuilder);
//            long totalCount = elasticsearchTemplate.queryByPageCount(indices, boolQueryBuilder);
//
//            Page page = new Page<>();
//            page.setRecords(list);
//            page.setPageNo(logDTO.getPageNo());
//            page.setPageSize(logDTO.getPageSize());
//            page.setTotalCount((int) totalCount);
//            result.setData(page);
//            log.info("??????");
//        } catch (Exception e) {
//            log.error("?????????????????????" + e);
//            result.setErrorMsg("?????????????????????" + e);
//            result.setErrorCode(ErrorCode.CODE_17001.getCode());
//            return result;
//        }
//        return result;
//    }
//
//    @Override
//    public ResponseJson queryLogCount(LogDto logDto) {
//        ResponseJson responseJson = new ResponseJson();
//        ProjectDto projectDto = new ProjectDto();
//        projectDto.setUserId(logDto.getUserId());
//        projectDto.setPageNo(1);
//        projectDto.setPageSize(10000);
//        HashMap<String, String> map = new HashMap<>();
//
//        // ?????????????????????????????????
//        Set<Long> projectIds = new HashSet<>();
//        List<com.awifi.cloudnative.container.enterprise.api.bean.ProjectDto> userProjectList = roleDataPermissionManager.getUserProjectList(null,projectDto.getUserId(), 1, 10000);
//        for (com.awifi.cloudnative.container.enterprise.api.bean.ProjectDto dto : userProjectList) {
//            projectIds.add(dto.getId());
//        }
////        String projectListJson = feignProjectService.queryProjectList(JSON.toJSONString(projectDto));
////        JSONObject obj = JSON.parseObject(projectListJson);
////        if (!obj.get("errorCode").equals("0")) {
////            responseJson.setErrorCode(ErrorCode.CODE_01.getCode());
////            responseJson.setErrorMsg("???????????????????????????????????????");
////            return responseJson;
////        }
////        JSONObject data = JSONObject.parseObject(obj.get("data").toString());
////        JSONArray jsonArray = data.getJSONArray("records");
////        List<ProjectDto> list = jsonArray.toJavaList(ProjectDto.class);
////        List<Long> projectIds = list.stream().map(ProjectDto::getId).collect(Collectors.toList());
//        String projectCount = String.valueOf(projectIds.size());
//        map.put("projectCount", projectCount);
//
//        // ????????????id?????????????????????????????????
//        QueryWrapper<ClusterManage> clusterManageQueryWrapper = new QueryWrapper<>();
//        if (projectIds.size() == 0) {
//            map.put("clusterCount", "0");
//            map.put("podCount", "0");
//            map.put("logCoubt", "0");
//        } else {
//            clusterManageQueryWrapper.in("project_id", projectIds);
//            clusterManageQueryWrapper.eq("status", 1);
//            List<ClusterManage> clusterManages = k8sClusterInfoMapper.selectList(clusterManageQueryWrapper);
//            if (clusterManages.size() != 0) {
//                String clusterCount = String.valueOf(clusterManages.size());
//                map.put("clusterCount", clusterCount);
//                String clusterPodCount = redisTemplateUtil.get(K8sBaseConstants.CLUSTERPODCOUNT);
//                clusterPodCount = clusterPodCount.substring(1, clusterPodCount.length() - 1);
//                if (StringUtils.isNotEmpty(clusterPodCount)) {
//                    Map<String, Object> clusterPodCountMap = getStringToMap(clusterPodCount);
//                    Integer podCount = 0;
//                    // ?????????????????????????????????
//                    for (ClusterManage clusterManage : clusterManages) {
//                        Integer count = Integer.parseInt(String.valueOf(clusterPodCountMap.get(clusterManage.getClusterId()) == null ? 0 : clusterPodCountMap.get(clusterManage.getClusterId())));
//                        podCount += count;
//                    }
//                    map.put("podCount", podCount.toString());
//                } else {
//                    map.put("podCount", "0");
//                }
//
//                // ?????????????????????????????????
//                String clusterLogCount = redisTemplateUtil.get(K8sBaseConstants.CLUSTERLOGCOUNT);
//                clusterLogCount = clusterLogCount.substring(1, clusterLogCount.length() - 1);
//                if (StringUtils.isNotEmpty(clusterLogCount)) {
//                    Map<String, Object> clusterLogCountMap = getStringToMap(clusterLogCount);
//                    Integer logCount = 0;
//                    // ?????????????????????????????????
//                    for (ClusterManage clusterManage : clusterManages) {
//                        Integer count = Integer.parseInt(String.valueOf(clusterLogCountMap.get(clusterManage.getClusterId()) == null ? 0 : clusterLogCountMap.get(clusterManage.getClusterId())));
//                        logCount += count;
//                    }
//                    map.put("logCount", logCount.toString());
//                } else {
//                    map.put("logCount", "0");
//                }
//            } else {
//                map.put("clusterCount", "0");
//                map.put("podCount", "0");
//                map.put("logCount", "0");
//            }
//
//
//        }
//
//        responseJson.setData(map);
//        return responseJson;
//    }
//
//    @Override
//    public ResponseJson queryLogHistogram(LogDto logDTO) {
//
//        ResponseJson responseJson = new ResponseJson();
//
//        try {
//            String ip;
//            if (logDTO.getClusterId() != null) {
//                QueryWrapper<ClusterManage> clusterManageQueryWrapper = new QueryWrapper<>();
//                clusterManageQueryWrapper.eq("cluster_id", logDTO.getClusterId());
//                List<ClusterManage> clusterManages = k8sClusterInfoMapper.selectList(clusterManageQueryWrapper);
//                String clusterUrl = clusterManages.get(0).getClusterUrl();
//                ip = clusterUrl.substring(8, clusterUrl.length() - 5);
//            } else {
//                log.error("??????id?????????");
//                responseJson.setErrorMsg("??????id?????????");
//                responseJson.setErrorCode(ErrorCode.CLUSTER_NOT_EXIST.getCode());
//                return responseJson;
//            }
//            RestHighLevelClient client = new RestHighLevelClient(
//                    RestClient.builder(new HttpHost(ip, 30756, "http"))
//            );
//            SearchRequest request = new SearchRequest().indices("k8s-*");
//
//            // ????????????????????????
//            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//            //????????????????????????????????????
//            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//            // ????????????
//            long lte = System.currentTimeMillis();
//            if (logDTO.getEndTime() != null) {
//                lte = logDTO.getEndTime();
//            }
//            // ????????????
//            long gte = System.currentTimeMillis() - 43200000;
//            if (logDTO.getStartTime() != null) {
//                gte = logDTO.getStartTime();
//            }
//            log.info(gte + "->" + lte);
//
//            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("@timestamp");
//            // ????????????
//            rangeQuery.gte(gte);
//            // ????????????
//            rangeQuery.lte(lte);
//            sourceBuilder.trackTotalHits(true);
//
//            //????????????????????????????????????
//            boolQueryBuilder.filter(rangeQuery);
//            if (logDTO.getContainerNames() != null) {
//                List<String> containerNames = Arrays.asList(logDTO.getContainerNames().split(","));
//                boolQueryBuilder.must(QueryBuilders.termsQuery("kubernetes.container_name", containerNames));
//            }
//            if (logDTO.getKeyWord() != null) {
//                String keyWords = QueryParser.escape(logDTO.getKeyWord());
//                // ????????????
////                boolQueryBuilder.must(QueryBuilders.wildcardQuery("log", "*" + keyWords + "*"));
//                MatchQueryBuilder log = QueryBuilders.matchQuery("log", keyWords);
//                log.fuzziness("auto");
//                boolQueryBuilder.filter(log);
//            }
//            if (logDTO.getPodNames() != null) {
//                List<String> podNames = Arrays.asList(logDTO.getPodNames().split(","));
//                boolQueryBuilder.must(QueryBuilders.termsQuery("kubernetes.pod_name", podNames));
//            }
//            sourceBuilder.query(boolQueryBuilder);
//            double interval = 0.0;
//            if (logDTO.getStep().endsWith("m")) {
//                interval = Double.parseDouble(logDTO.getStep().substring(0, logDTO.getStep().length() - 1)) * 60000;
//            } else if (logDTO.getStep().endsWith("h")) {
//                interval = Double.parseDouble(logDTO.getStep().substring(0, logDTO.getStep().length() - 1)) * 3600000;
//            }
//            sourceBuilder.aggregation(AggregationBuilders.histogram("@timestamp").field("@timestamp").interval(interval));
//            request.source(sourceBuilder);
//
//            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//            client.close();
//
//            Aggregations aggregations = response.getAggregations();
//            if (aggregations != null) {
//                long value = response.getHits().getTotalHits().value;
//                Map<String, Aggregation> asMap = aggregations.getAsMap();
//                String timestamp = JSONObject.toJSONString(asMap.get("@timestamp"));
//                Object buckets = JSON.parseObject(timestamp).get("buckets");
//                HashMap<String, Object> map = new HashMap<>();
//                map.put("buckets", buckets);
//                map.put("totleHits", value);
//                responseJson.setData(map);
//            } else {
//                responseJson.setData(null);
//            }
//            return responseJson;
//        } catch (Exception e) {
//            log.error("??????????????????????????????" + e);
//            responseJson.setErrorMsg("??????????????????????????????" + e);
//            responseJson.setErrorCode(ErrorCode.CODE_17001.getCode());
//            return responseJson;
//        }
//
//    }
//
//    @Override
//    public ResponseJson logCount(String clusterUrl) {
//
//        ResponseJson responseJson = new ResponseJson();
//        RestHighLevelClient client = null;
//        try {
//            String ip;
//            ip = clusterUrl.substring(8, clusterUrl.length() - 5);
//            client = new RestHighLevelClient(
//                    RestClient.builder(new HttpHost(ip, 30756, "http"))
//            );
//            SearchRequest request = new SearchRequest().indices("k8s-*");
//
//            // ????????????????????????
//            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//            //????????????????????????????????????
//            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//            // ????????????
//            long lte = System.currentTimeMillis();
//            // ???????????? 12????????????
//            long gte = System.currentTimeMillis() - 43200000;
//
//            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("@timestamp");
//            // ????????????
//            rangeQuery.gte(gte);
//            // ????????????
//            rangeQuery.lte(lte);
//            //????????????????????????????????????
//            boolQueryBuilder.filter(rangeQuery);
//            sourceBuilder.query(boolQueryBuilder);
//            sourceBuilder.trackTotalHits(true);
//            request.source(sourceBuilder);
//
//            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//
//            if (response.getHits() != null) {
//                long value = response.getHits().getTotalHits().value;
//                responseJson.setData(value);
//            } else {
//                responseJson.setData(0L);
//            }
//            return responseJson;
//        } catch (Exception e) {
//            log.info("?????????????????????" + e);
//            responseJson.setData(0L);
//            return responseJson;
//        } finally {
//            assert client != null;
//            try {
//                client.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//
//    private  ElasticsearchTemplate esTemplateUtil(String ip, String userName, String password) {
//        ElasticsearchProperties elasticsearchProperties = new ElasticsearchProperties();
//        //??????IP ??????
//        List<String> hostAndPortList = Arrays.asList(ip);
//        elasticsearchProperties.setHostAndPortList(hostAndPortList);
//        //????????????????????????????????????
//        elasticsearchProperties.setEncryptTypeEnum(EncryptTypeEnum.AUTH);
//        //?????????????????????
//        elasticsearchProperties.setUsername(userName);
//        elasticsearchProperties.setPassword(password);
//
//        ElasticsearchTemplate elasticsearchTemplate = ElasticsearchTemplate.getInstance(elasticsearchProperties);
//        log.info(elasticsearchTemplate.toString());
//        return elasticsearchTemplate;
//    }
//
//
//    private Map<String, Object> getStringToMap(String str) {
//        log.info("??????ID??????" + str);
//        //?????????????????????????????????
//        String[] str1 = str.split(",");
//        //??????Map??????
//        Map<String, Object> map = new HashMap<>();
//        //????????????map??????
//        for (int i = 0; i < str1.length; i++) {
//            //??????":"?????????????????????
//            String trim = str1[i].trim();
//            String[] str2 = trim.split("=");
//            //str2[0]???KEY,str2[1]??????
//            map.put(str2[0], str2[1]);
//        }
//        return map;
//    }
//
//
//}
