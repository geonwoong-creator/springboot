package kopo.poly.service.impl;

import kopo.poly.dto.RedisDTO;
import kopo.poly.persistance.redis.IMyRedisMapper;
import kopo.poly.service.IMyRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("MyRedisService")
public class MyRedisService implements IMyRedisService {

    @Resource(name = "MyRedisMapper")
    private IMyRedisMapper myRedisMapper;

    @Override
    public int saveRedisString() throws Exception {
        log.info(this.getClass().getName() + ".saveRedisString Start!");

        String redisKey = "myRedis_String";

        RedisDTO pDTO = new RedisDTO();
        pDTO.setTest_text("난 String타입으로 저장할 일반 문자열이다. 2120110011 박건웅");

        int res = myRedisMapper.saveRedisString(redisKey, pDTO);

        log.info(this.getClass().getName() + ".saveRedisString End!");

        return res;
    }
    @Override
    public RedisDTO getRedisString() throws Exception {
        log.info(this.getClass().getName() + ".getRedisString Start!");

        String redisKey = "myRedis_String";

        RedisDTO rDTO = myRedisMapper.getRedisString(redisKey);

        if (rDTO == null) {
            rDTO = new RedisDTO();
        }

        log.info(this.getClass().getName() + ".getRedisString End!");

        return rDTO;
    }
    @Override
    public int saveRedisStringJSON() throws Exception {
        log.info(this.getClass().getName() + ".saveRedisStringJSON Start!");

        String redisKey = "myRedis_String_JSON";

        RedisDTO pDTO = new RedisDTO();
        pDTO.setTest_text("난 String타입에 JSON 구조로 저장할 일반 문자열이다.");
        pDTO.setName("박건웅");
        pDTO.setEmail("rjsdnd0420@naver.com");
        pDTO.setAddr("의정부");

        int res = myRedisMapper.saveRedisStringJSON(redisKey, pDTO);

        log.info(this.getClass().getName() + ".saveRedisStringJSON End!");

        return res;
    }
}
