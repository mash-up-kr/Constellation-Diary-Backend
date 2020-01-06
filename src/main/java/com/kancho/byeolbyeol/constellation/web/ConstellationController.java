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
public class ConstellationController {

    private final ConstellationService constellationService;

}
