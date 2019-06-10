package com.gaoxiong.utils;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * @author gaoxiong
 * @ClassName MongoUtil
 * @Description TODO
 * @date 2019/6/7 0007 下午 2:05
 */
public class MongoUtil {
    private static MongoClient mongoClient = new MongoClient("192.168.2.171", 27017);

    /**
     * 查找
     * @param tableName 表名称
     * @param dataBase 库名
     * @param yearbasetype
     * @return
     */
    public static Document findOneBy(String tableName,String dataBase,
                                     String yearbasetype
                                    ){
        MongoDatabase mongoDatabase = mongoClient.getDatabase(dataBase);
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(tableName);
        Document doc = new Document();
        doc.put("yearbasetype", yearbasetype);
        FindIterable<Document> iter = mongoCollection.find(doc);
        MongoCursor<Document> mongoCursor = iter.iterator();
        if (mongoCursor.hasNext()) {
            return mongoCursor.next();
        } else {
            return null;
        }
    }

    public static void saveorupdatemongo(String tableName,Document doc,String database){
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(tableName);
        if (!doc.containsKey("_id")) {
            ObjectId objectId = new ObjectId();
            doc.put("_id", objectId);
            mongoCollection.insertOne(doc);
            return;
        }
        Document matchDocument = new Document();
        String objectId = doc.get("_id").toString();
        matchDocument.put("_id", new ObjectId(objectId));
        FindIterable<Document> findIterable = mongoCollection.find(matchDocument);
        if (findIterable.iterator().hasNext()) {
            mongoCollection.updateOne(matchDocument, new Document("$set", doc));
            System.out.println("come into saveorupdatemongo ---- update ----" + JSONObject.toJSONString(doc));

        }else {
            mongoCollection.insertOne(doc);
            System.out.println("come into saveorupdatemongo ---- insert ----" + JSONObject.toJSONString(doc));
        }
    }





}
