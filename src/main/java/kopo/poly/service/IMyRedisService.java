package kopo.poly.service;

import kopo.poly.dto.RedisDTO;

import java.util.List;

public interface IMyRedisService {

    /**
     * String 타입 저장하기
     */
    int saveRedisString() throws Exception;

    /**
     * String 타입 가져오기
     */
    RedisDTO getRedisString() throws Exception;

    /**
     * String 타입 가져오기
     */
    int saveRedisStringJSON() throws Exception;

    /**
     * List타입에 여러 문자열로 저장하기(동기화)
     */
    int saveRedisList() throws Exception;

    /**
     * List타입에 여러 문자열로 저장된 데이터 가져오기
     */
    List<String> getRedisList() throws Exception;

    /**
     * List타입에 JSON 형태로 저장하기
     */
    int saveRedisListJSON() throws Exception;

    /**
     * List타입에 JSON 형태로 저장된 데이터 가져오기
     */
    List<RedisDTO> getRedisListJSON() throws Exception;

    /**
     * List타입에 JSON 형태로 람다식으 이용하여 저장하기(비동기화
     */
    int saveRedisListJSONRamda() throws Exception;

    /**
     * List타입에 JSON 형태로 람다식으로 저장된 데이터 가져오기
     */
    List<RedisDTO> getRedisListJSONRamda() throws Exception;

    /**
     * Hash 타입에 문자열 형태로 저장하기
     */
    int saveRedisHash() throws Exception;
}
