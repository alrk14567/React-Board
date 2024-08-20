package com.nc13.react_board.controller;

import com.nc13.react_board.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/")
public class UserController {

    @RequestMapping("authOk")
    public ResponseEntity<Map<String,Object>> authOk(Authentication authentication) {
        Map<String,Object> resultMap = new HashMap<>();
        UserDTO userDTO = (UserDTO) authentication.getPrincipal();
        // getPrincipal을 바로 resultMap에 넣는건 좋지 않다 왜냐면 패스워드가 넘어가니깐
        resultMap.put("result","success");
        resultMap.put("id",userDTO.getId());
        resultMap.put("nickname",userDTO.getNickname());
        resultMap.put("role",userDTO.getRole());


        return ResponseEntity.ok(resultMap);
    }

    @RequestMapping("authFail")
    public ResponseEntity<Map<String,Object>> authFail(){
        System.out.println("Auth has failed");
        Map<String ,Object> resultMap = new HashMap<>();
        resultMap.put("result","fail");

        return ResponseEntity.ok(resultMap);
    }

    @RequestMapping("logOutSuccess")
    public ResponseEntity<Void> logOutSuccess() {
        System.out.println("log out success");

        //위에선 resultMap을 통해서 ok 했지만 로그아웃은 아무것도 없으므로 어쩌라고 듣지 않기 위해 .build()를 해준다.
        return ResponseEntity.ok().build();
    }

}
