package kopo.poly.persistance.redis.impl;

import kopo.poly.dto.RedisDTO;
import kopo.poly.persistance.redis.IMyRedisMapper;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component("MyRedisMapper")
public class MyRedisMapper implements IMyRedisMapper {

    @Autowired
    public RedisTemplate<String, Object> redisDB;

@Override
public int saveRedisString(String redisKey, RedisDTO pDTO) throws Exception {

    log.info(this.getClass().getName() + ".saveredisString Start!");

    int res = 0;

    String saveDate = CmmUtil.nvl(pDTO.getTest_text()); // 저장할 값

    /*
    redis 저장 및 읽기에 대한 데이터 타입 저장(String 타입으로 저장함)
     */
    redisDB.setKeySerializer(new StringRedisSerializer()); //String 타입
    redisDB.setValueSerializer(new StringRedisSerializer()); //String 타입

    if (!redisDB.hasKey(redisKey)) {

        //데이터 저장하기
        redisDB.opsForValue().set(redisKey, saveDate);

        //RedisDB에 저장되는 데이터의 유효시간 설정(TTL 설정)
        //2일이 지나면, 자동으로 데이터가 삭제되도록 설정함
        redisDB.expire(redisKey, 2, TimeUnit.DAYS);

        log.info("save Data!!");

        res = 1;
    }

    log.info(this.getClass().getName() + "saveRedisString End!");

    return res;
}
@Override
public RedisDTO getRedisString(String redisKey) throws Exception {

    log.info(this.getClass().getName() + ".getRedisString Start!");

    log.info("String redisKey : " + redisKey);
    RedisDTO rDTO = new RedisDTO();

    redisDB.setKeySerializer(new StringRedisSerializer());
    redisDB.setValueSerializer(new StringRedisSerializer());

    if (redisDB.hasKey(redisKey)) { //데이터가 존재하지 않으면 저장하기

        String res = (String) redisDB.opsForValue().get(redisKey);

        log.info("res : " + res);

        //RedisDB에 저장된 데이터를 DTO에 저장하기
        rDTO.setTest_text(res);
    }

    log.info(this.getClass().getName() + ".getRedisString End!");

    return rDTO;
}
@Override
public int saveRedisStringJSON(String redisKey, RedisDTO pDTO) throws Exception {

    log.info(this.getClass().getName() + ".saveRedisStringJSON Start!");

    int res = 0;

    //redisDB의 기의 데이터 타입을 String으로 정의(항상 String으로 설정함)
    redisDB.setKeySerializer(new StringRedisSerializer()); // String 타입

    //RedisDTO에 저장된 데이터를 자동으로 JSON으로 변형하기
    redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisDTO.class));

    if (!redisDB.hasKey(redisKey)) {

        //데이터 저장하기
        redisDB.opsForValue().set(redisKey, pDTO);

        //redisDB에 저장되는 데이터의 유효시간 설정(TTL)
        redisDB.expire(redisKey, 2, TimeUnit.DAYS);

        log.info("save Data");

        res = 1;
    }

    log.info(this.getClass().getName() + ".saveRedisStringJSON End!");

    return res;
}
@Override
public int saveRedisList(String redisKey, List<RedisDTO> pList) throws Exception {

    log.info(this.getClass().getName() + ".saveRedisList Start!");

    int res = 0;
    /*
    redis 저장 및 읽기에 대한 데이터 타입 지정(String 타입으로 지정함)
     */
    redisDB.setKeySerializer(new StringRedisSerializer());//String 타입
    redisDB.setValueSerializer(new StringRedisSerializer());//String 타입

    for (RedisDTO dto : pList) {

        //오름차순 저장
       // redisDB.opsForList().rightPush(redisKey, CmmUtil.nvl(dto.getTest_text()));

        //내림차순 저장
        redisDB.opsForList().rightPush(redisKey, CmmUtil.nvl(dto.getTest_text()));
    }

    //저장되는 데이터의 유효기간(TTL) 3시간
    redisDB.expire(redisKey, 5,TimeUnit.HOURS);

    res = 1;

    log.info(this.getClass().getName() + ".saveRedisList End!");

    return res;
  }
@Override
public List<String> getRedisList(String redisKey) throws Exception {

    log.info(this.getClass().getName() + ".getRedisList Start!");

    List<String> rList = null;

    redisDB.setKeySerializer(new StringRedisSerializer());
    redisDB.setValueSerializer(new StringRedisSerializer());

    if (redisDB.hasKey(redisKey)) {
        rList = (List) redisDB.opsForList().range(redisKey, 0, -1);
    }

    log.info(this.getClass().getName() + ".getRedisList End!");

    return rList;
}
@Override
public int saveRedisListJSON(String redisKey, List<RedisDTO> pList) throws Exception {

    log.info(this.getClass().getName() + ".saveRedisListJSON Start!");

    int res = 0;
    /*
    redis 저장 및 읽기에 대한 데이터 타입 지정(String 타입으로 지정함)
     */
    redisDB.setKeySerializer(new StringRedisSerializer());//String 타입

    //redisDTO에 저장된 데이터를 자동으로 JSON으로 변경하기
    redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisDTO.class));

    for (RedisDTO dto : pList) {

        //오름차순 저장
        redisDB.opsForList().rightPush(redisKey, dto);

        //내림차순 저장
        //redisDB.opsForList().leftPush(redisKey, dto);
    }

    //저장되는 데이터의 유효기간(TTL) 3시간
    redisDB.expire(redisKey, 5,TimeUnit.HOURS);

    res = 1;

    log.info(this.getClass().getName() + ".saveRedisListJSON End!");

    return res;
}
    @Override
    public List<RedisDTO> getRedisListJSON(String redisKey) throws Exception {

        log.info(this.getClass().getName() + ".getRedisListJSON Start!");

        List<RedisDTO> rList = null;

        //redisDB 키의 데이터 타입을 String으로 정의
        redisDB.setKeySerializer(new StringRedisSerializer());

        //RedisDTO에 저장된 데이터를 자동으로 JSON으로 변경하기
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisDTO.class));

        if (redisDB.hasKey(redisKey)) {
            rList = (List) redisDB.opsForList().range(redisKey, 0, -1);
        }

        log.info(this.getClass().getName() + ".getRedisList End!");

        return rList;
    }
    @Override
    public int saveRedisListJSONRamda(String redisKey, List<RedisDTO> pList) throws Exception {

        log.info(this.getClass().getName() + ".saveRedisListJSONRamda Start!");

        int res = 0;
    /*
    redis 저장 및 읽기에 대한 데이터 타입 지정(String 타입으로 지정함)
     */
        redisDB.setKeySerializer(new StringRedisSerializer());//String 타입

        //redisDTO에 저장된 데이터를 자동으로 JSON으로 변경하기
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisDTO.class));

        pList.stream().forEach(dto -> redisDB.opsForList().rightPush(redisKey, dto));

        //저장되는 데이터의 유효기간(TTL) 3시간
        redisDB.expire(redisKey, 5,TimeUnit.HOURS);

        res = 1;

        log.info(this.getClass().getName() + ".saveRedisListJSONRamda End!");

        return res;
    }
    @Override
    public List<RedisDTO> getRedisListJSONRamda(String redisKey) throws Exception {

        log.info(this.getClass().getName() + ".getRedisListJSONRamda Start!");

        List<RedisDTO> rList = null;

        //redisDB 키의 데이터 타입을 String으로 정의
        redisDB.setKeySerializer(new StringRedisSerializer());

        //RedisDTO에 저장된 데이터를 자동으로 JSON으로 변경하기
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisDTO.class));

        if (redisDB.hasKey(redisKey)) {
            rList = (List) redisDB.opsForList().range(redisKey, 0, -1);
        }

        log.info(this.getClass().getName() + ".getRedisListJSONRamda End!");

        return rList;
    }
}
