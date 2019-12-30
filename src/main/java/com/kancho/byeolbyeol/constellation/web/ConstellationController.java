package com.kancho.byeolbyeol.constellation.web;

import com.kancho.byeolbyeol.constellation.dto.response.ResConstellationInfoDto;
import com.kancho.byeolbyeol.constellation.service.ConstellationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Api(description = "별자리 관련 API")
public class ConstellationController {

    private final ConstellationService constellationService;

    @ApiOperation(value = "별자리 정보 리스트 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/constellations/included-icon")
    public ResponseEntity<List<ResConstellationInfoDto>> getConstellationInfoList() {
        return ResponseEntity.ok().body(constellationService.getConstellationInfoList());
    }
}
