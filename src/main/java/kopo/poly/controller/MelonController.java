package kopo.poly.controller;

import kopo.poly.dto.MelonDTO;
import kopo.poly.service.IMelonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class MelonController {

    /*
     * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
     */

    // Map 객체를 사용한 데이터 처리
    @Resource(name = "MelonService")
    private IMelonService melonService;

    /**
     * 멜론 노래 리스트 저장하기
     */
    @GetMapping(value = "melon/collectMelonSong")
    public String collectMelonRank() throws Exception {

        log.info(this.getClass().getName() + ".collectMelonSong Start!");

        // 수집 결과 출력
        String msg;

        int res = melonService.collectMelonSong();

        if (res == 1) {
            msg = "success";

        }else{
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".collectMelonSong End!");

        return msg;
    }

    /**
     * 오늘 수집된 멜론 노래리스트 가져오기
     */
    @GetMapping(value = "melon/getSongList")
    public List<MelonDTO> getSongList() throws Exception {

        log.info(this.getClass().getName() + ".getSongList Start!");

        List<MelonDTO> rList = melonService.getSongList();

        log.info(this.getClass().getName() + ".getSongList End!");

        return rList;
    }

    /**
     * 가수별 수집된 노래의 수 가져오기
     */
    @GetMapping(value = "melon/getSingerSongCnt")
    public List<Map<String, Object>> getSingerSongCnt()
            throws Exception {

        log.info(this.getClass().getName() + ".getSingerSongCnt Start!");

        List<Map<String, Object>> rList = melonService.getSingerSongCnt();

        log.info(this.getClass().getName() + ".getSingerSongCnt End!");

        return rList;
    }
    /**
     * 가수별 수집된 노래의 수 가져오기
     */
    @GetMapping(value = "melon/getSingerSong")
    public List<MelonDTO> getSingerSong()
            throws Exception {

        log.info(this.getClass().getName() + ".getSingerSongCnt Start!");

        List<MelonDTO> rList = melonService.getSingerSong();

        log.info(this.getClass().getName() + ".getSingerSongCnt End!");

        return rList;
    }
    /**
     * 가수별 수집된 노래의 수 가져오기
     */
    @GetMapping(value = "melon/collectMelonSongMany")
    public String collectMelonSongMany() throws Exception {

        log.info(this.getClass().getName() + ".collectMelonSongMany Start!");

        //수집 결과 출력
        String msg;

        int res = melonService.collectMelonSongMany();

        if (res == 1) {
            msg = "success";

        }else {
            msg = "fail";
        }
        log.info(this.getClass().getName() + ".collectMelonSongMany End!");

        return msg;
    }
    /**
     * 가수 이름이 방탄소년단을 BTS로 변경하기
     */
    @GetMapping(value = "melon/bts/updateBTSName")
    public String btsupdatename() throws Exception {

        log.info(this.getClass().getName() + ".updateBTSName Start!");

        //결과 출력
        String msg;

        int res = melonService.updateBTSName();

        if (res == 1) {
            msg = "success";
        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".updateBTSName End!");

        return msg;
    }
    /**
     * 가수 이름이 방탄소년단을 nickname 추가
     */
    @GetMapping(value = "melon/btsAddNickname")
    public String btsAddField() throws Exception {

        log.info(this.getClass().getName() + ".btsAddNickname");

        //결과 출력
        String msg;

        int res = melonService.updateAddBTSNickname();

        if (res == 1) {
            msg = "success";
        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".updateAddBTSNickname End!");

        return msg;
    }
    /**
     * 방탄소년단 member 추가하기
     */
    @GetMapping(value = "melon/btsAddMember")
    public  String BTSAddMember() throws Exception {

        log.info(this.getClass().getName() + ".btsAddMember Start!");

        //결과 출력
        String msg;

        int res = melonService.updateAddBTSMember();

        if (res == 1) {
            msg = "success";
        }else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".btsAddMember End!");

        return msg;
    }
    /**
     * 여러 필드 수정
     */
    @GetMapping(value = "melon/updateManySong")
    public String updateManySong() throws Exception {

        log.info(this.getClass().getName() + ".updateManySong Start!");

        //결과 출력
        String msg;

        int res = melonService.updateManySong();

        if (res == 1) {
            msg = "success";
        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".updateManySong End!");

        return msg;
    }
    /**
     * 가수의 노래 삭제
     */
    @GetMapping(value = "melon/deletSong")
    public String deleteSong() throws Exception {

        log.info(this.getClass().getName() + ".deleteSong Start!");

        String msg;

        int res = melonService.deleteSong();

        if (res == 1) {
            msg = "success";
        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".deleteSong End!");

        return msg;
    }
}
