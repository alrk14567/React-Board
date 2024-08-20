package com.nc13.react_board.controller;

import com.nc13.react_board.Service.BoardService;
import com.nc13.react_board.model.BoardDTO;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/board/")
public class BoardController {
    private final HikariDataSource dataSource;
    private BoardService BOARD_SERVICE;

    @Autowired
    public BoardController(BoardService boardService, HikariDataSource dataSource) {
        BOARD_SERVICE = boardService;
        this.dataSource = dataSource;
    }

    @GetMapping("showOne/{id}")
    public BoardDTO selectOne(@PathVariable int id) {
        return BOARD_SERVICE.selectOne(id);
    }

    @GetMapping("showList/{pageNo}")
    public HashMap<String, Object> selectList(@PathVariable int pageNo) {
        HashMap<String, Object> resultMap = new HashMap<>();

        int maxPage = BOARD_SERVICE.selectMaxPage();
        int startPage = 1;
        int endPage = 1;

        if (maxPage < 5) {
            endPage = maxPage;
        } else if (pageNo <= 3) {
            endPage = 5;
        } else if (pageNo >= maxPage - 2) {
            startPage = maxPage - 4;
            endPage = maxPage;
        } else {
            startPage = pageNo - 2;
            endPage = pageNo + 2;
        }

        resultMap.put("currentPage", pageNo);
        resultMap.put("startPage", startPage);
        resultMap.put("endPage",endPage);
        resultMap.put("maxPage",maxPage);
        resultMap.put("boardList",BOARD_SERVICE.selectAll(pageNo));
        return resultMap;
    }

    @PostMapping("write")
    public HashMap<String, Object> write(@RequestBody BoardDTO boardDTO) {
        System.out.println(boardDTO);
        boardDTO.setWriterId(1);
        HashMap<String,Object> resultMap = new HashMap<>();
        try{
            BOARD_SERVICE.insert(boardDTO);
            resultMap.put("result","success");
            resultMap.put("resultId",boardDTO.getId());

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result","fail");
        }
        return resultMap;
    }

    @PostMapping("update")
    public HashMap<String,Object> update(@RequestBody BoardDTO boardDTO) {
        HashMap<String, Object> resultMap=new HashMap<>();
        BOARD_SERVICE.update(boardDTO);
        resultMap.put("destId",boardDTO.getId());

        try{
            BOARD_SERVICE.update(boardDTO);
            resultMap.put("result","success");
            resultMap.put("resultId",boardDTO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result","fail");
        }
        return resultMap;
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        BOARD_SERVICE.delete(id);

        return ResponseEntity.ok().build();
    }
}
