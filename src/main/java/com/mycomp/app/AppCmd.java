package com.mycomp.app;

import com.mycomp.cache.CacheResult;
import com.mycomp.cache.MongoClientWithCache;
import com.mycomp.cache.token.primitive.LongOperand;
import com.mycomp.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

import static java.lang.System.exit;

public class AppCmd {
    private static final Logger logger = LogManager.getLogger(AppCmd.class);

    public static void main(String[] args) {
        if(args.length < 4){
            logger.info("Please execute the following command\n");
            logger.info("java -jar mongodb-cache.jar -f /filepath -url <mongodb connection string>");
            exit(0);

        }
        String fileName = StringUtils.EMPTY;
        String urlName = StringUtils.EMPTY;
        for(int i=0; i< args.length; i++){
            if(args[i].equals("-url")){
                i++;
                urlName = args[i];
            }
            if(args[i].equals("-f")){
                i++;
                fileName = args[i];
            }
        }
        List<String> queryList = null;
        try {
            queryList = FileHandler.readQueryFromFile(fileName);
        } catch (IOException e) {
            logger.error("Failed to read the file {}",e.getCause());
            exit(0);
        }

        if(queryList != null){
            MongoClientWithCache mongoClientWithCache = new MongoClientWithCache(urlName);
            int count = 1;
            for(String queryStr: queryList){
                try{

                    logger.info("Executing query{}: {}",count++,StringUtils.deleteWhitespace(queryStr));
                    CacheResult cacheResult = mongoClientWithCache.excuteQuery(queryStr);
//                if(cacheResult.isSingleResult()){
//                    logger.info(cacheResult.getValue());
//                }else{
//                    cacheResult.getHomeProperties().forEach(item -> logger.info(JsonUtil.toJsonString(item)));
//                }
                }catch (Exception e){
                    logger.error("Exception {} occure executing {}",e.getCause(),queryStr);
                }
            }
        }

        logger.info("Program executed successfully!!");
        return;
    }
}
